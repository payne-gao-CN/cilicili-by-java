package live.cilicili.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import live.cilicili.constant.CacheKey;
import live.cilicili.enums.BizCodeEnum;
import live.cilicili.enums.SendCodeEnum;
import live.cilicili.mapper.UserMapper;
import live.cilicili.model.LoginUser;
import live.cilicili.model.UserDO;
import live.cilicili.request.UserLoginRequest;
import live.cilicili.request.UserRegisterRequest;
import live.cilicili.service.INotifyService;
import live.cilicili.service.IUserService;
import live.cilicili.util.CommonUtil;
import live.cilicili.util.JWTUtil;
import live.cilicili.util.JsonData;
import live.cilicili.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户服务实现类
 * @authoer: payne
 * @createDate: 2022/9/1
 * @description:
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private INotifyService iNotifyService;

    @Autowired
    private RedisTemplate redisTemplate;

    public static final String DEFAULT_AVATAR = "https://cilicili-useravatar.oss-cn-beijing.aliyuncs.com/default_avatar.png";

    @Override
    public JsonData register(UserRegisterRequest request) {
        boolean checkCode = false;

        if (checkPhoneOnlyOne(request.getUsername())){
            log.info(BizCodeEnum.PHONE_BE_USED.getMsg());
            return JsonData.buildResult(BizCodeEnum.PHONE_BE_USED);
        }

        if (StringUtils.isNotBank(request.getUsername())){
            checkCode = iNotifyService.checkCode(request.getUsername(),request.getCode());
        }

        if (!checkCode){
            log.info(BizCodeEnum.CODE_ERROR.getMsg());
            return JsonData.buildResult(BizCodeEnum.CODE_ERROR);
        }

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(request,userDO);
        userDO.setCreatedAt(new Date());
        userDO.setUpdatedAt(new Date());
        userDO.setAvatar(DEFAULT_AVATAR);
        userDO.setSlogan("cilicili up up ~");

        StringBuilder default_nickname = new StringBuilder();
        default_nickname.append("cili_").append(CommonUtil.getCurrentTimestamp()).append(CommonUtil.getRandomCode(5));
        userDO.setNickname(StringUtils.toString(default_nickname));

        //密码要进行加密，生成密码盐
        userDO.setSecret("$1$" + CommonUtil.getRandomStringNum(8));

        //密码加盐处理
        userDO.setPassword(Md5Crypt.md5Crypt(request.getPassword().getBytes(), userDO.getSecret()));
        userMapper.insert(userDO);
        log.info("账号注册成功:{}", request.getUsername());


        return JsonData.buildSuccess();
    }

    /**
     * 用户登录
     * 1.验证用户是否存在
     * 2.验证密码是否正确
     * 3.根据用户信息，生成JWT token
     * 4.将token存储在redis中
     * 5.返回前端
     * @param request 用户登录请求对象
     * @return token
     */
    @Override
    public JsonData login(UserLoginRequest request) {

        UserDO userDO = userMapper.selectOne(new QueryWrapper<UserDO>().eq("user_name", request.getUsername()));

        if (userDO == null){
            return JsonData.buildResult(BizCodeEnum.USER_NOT_EXIST);
        }

        String md5PassWord = Md5Crypt.md5Crypt(request.getPassword().getBytes(), userDO.getSecret());

        if (!StringUtils.equals(userDO.getPassword(),md5PassWord)){
            return JsonData.buildResult(BizCodeEnum.PASSWORD_NO_TRUE);
        }

        LoginUser loginUser = LoginUser.builder()
                .id(userDO.getId())
                .userName(userDO.getUserName())
                .nickname(userDO.getNickname())
                .avatar(userDO.getAvatar())
                .build();

        Map<String, Object> tokenMap = JWTUtil.generateWebToken(loginUser);

        //
        String redisKey = CommonUtil.generateUUID();
        redisTemplate.opsForValue().set(redisKey,"1",JWTUtil.EXPIRE, TimeUnit.MILLISECONDS);


        tokenMap.put("redisKey",redisKey);


        return JsonData.buildSuccess(tokenMap);
    }

    /**
     * 检查用户名唯一性
     * @param username 用户名
     * @return 是否唯一
     */
    public boolean checkPhoneOnlyOne(String username){
        return userMapper.selectOne(new QueryWrapper<UserDO>().eq("user_name", username)) != null;
    }
}
