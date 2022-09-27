package live.cilicili.interceptor;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import live.cilicili.enums.BizCodeEnum;
import live.cilicili.model.LoginUser;
import live.cilicili.util.CommonUtil;
import live.cilicili.util.JWTUtil;
import live.cilicili.util.JsonData;
import live.cilicili.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: payne
 * @createDate: 2022/9/6 16:14
 * @description: 用户信息拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    public static ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String accessToken = request.getHeader("token");

        if (accessToken == null){
            accessToken = request.getParameter("token");
        }

        if (StringUtils.isNotBank(accessToken)){
            Claims claims = JWTUtil.checkToken(accessToken);

            if (claims == null){
                CommonUtil.sendJsonMessage(response, JsonData.buildResult(BizCodeEnum.USER_TOKEN_INVALID));
                return false;
            }
            Long id = Long.valueOf(claims.get("id").toString());
            String userName = (String) claims.get("user_name");
            String nickName = (String) claims.get("nick_name");
            String avatar = (String) claims.get("avatar");

            LoginUser loginUser = LoginUser.builder()
                            .id(id)
                    .userName(userName)
                    .nickname(nickName)
                    .avatar(avatar)
                    .build();

            //通过threadLocal共享用户登录信息
            threadLocal.set(loginUser);
            return true;
        }


        CommonUtil.sendJsonMessage(response, JsonData.buildResult(BizCodeEnum.USER_NO_LOGIN));
        return false;
    }
}
