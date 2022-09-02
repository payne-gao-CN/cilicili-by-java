package live.cilicili.service.impl;

import live.cilicili.constant.CacheKey;
import live.cilicili.enums.BizCodeEnum;
import live.cilicili.enums.SendCodeEnum;
import live.cilicili.service.INotifyService;
import live.cilicili.service.IPhoneService;
import live.cilicili.util.CommonUtil;
import live.cilicili.util.JsonData;
import live.cilicili.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @authoer: payne
 * @createDate: 2022/9/1
 * @description:
 */
@Slf4j
@Service
public class NotifyServiceImpl implements INotifyService {



    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IPhoneService iPhoneService;

    /**
     * 验证码有效期
     * 3分钟
     */
    private static final long CODE_EXPIRED = 3 * 60 * 1000;

    /**
     * 向前端发送短信验证码
     * 1.接受需要发送的短信验证码的手机号
     * 2.在数据库中查询，该用户是否已经注册
     * 3.在redis中查询是否存在该手机号的验证码
     * 4，调用阿里云接口并将数据存入redis
     * @param username 发送短信的手机号
     */
    @Override
    public JsonData sendPhoneCode(String username) {
        String cacheKey = String.format(CacheKey.CHECK_CODE_KEY, SendCodeEnum.USER_REGISTER.name(), username);
        String cacheValue = StringUtils.toString(redisTemplate.opsForValue().get(cacheKey));

        if (StringUtils.isBank(username)){
            return JsonData.buildResult(BizCodeEnum.USER_NAME_ERROR);
        }

        //检查是否依旧存在验证码，过去了多久
        if (StringUtils.isNotBank(cacheValue)) {
            long ttl = Long.parseLong(cacheValue.split("_")[1]);
            if (CommonUtil.getCurrentTimestamp() - ttl < CODE_EXPIRED) {
                log.info("重新发送验证码，时间间隔：{}秒", (CommonUtil.getCurrentTimestamp() - ttl) / 100);
                return JsonData.buildResult(BizCodeEnum.SEND_PHONE_CODE_FREQUENTLY);
            }
        }

        String code = CommonUtil.getRandomCode(4);
        String value = code + "_" + CommonUtil.getCurrentTimestamp();


        log.info("发送验证码成功：cacheKey={}，value={}",cacheKey,value);
        iPhoneService.sendPhoneCode(username, code);

        redisTemplate.opsForValue().set(cacheKey, value, CODE_EXPIRED, TimeUnit.MILLISECONDS);


        return JsonData.buildSuccess();
    }

    /**
     * 检查手机验证码
     * @param username 手机号码
     * @param code 前端发送的验证码
     * @return 验证码是否匹配
     */
    @Override
    public boolean checkCode(String username, String code) {
        String cacheKey = String.format(CacheKey.CHECK_CODE_KEY, SendCodeEnum.USER_REGISTER.name(), username);
        String cacheValue = StringUtils.toString(redisTemplate.opsForValue().get(cacheKey));
        if (StringUtils.isNotBank(cacheValue)){
            String cacheCode = cacheValue.split("_")[0];
            if (cacheCode.equals(code)){
                redisTemplate.delete(cacheKey);
                return true;
            }
        }
        return false;
    }
}
