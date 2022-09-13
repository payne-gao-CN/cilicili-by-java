package live.cilicili.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import live.cilicili.model.LoginUser;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @authoer: payne
 * @createDate:2022/9/1
 * @description:
 */
@Slf4j
public class JWTUtil {

    /**
     * token过期时间，正常是7天，方便测试改为70天
     */
    public static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;
    /**
     * 加密的密钥
     */
    public static final String SECRET = "cilicili.live";
    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "CILICILI";
    /**
     * subject 颁布地址
     */
    public static final String SUBJECT = "cilicili.live";

    /**
     * 根据用户信息生成token
     *
     * @param loginUser 登录用户对象
     * @return Map  token字符串以及过期时间
     */
    public static Map<String, Object> generateWebToken(LoginUser loginUser) {
        if (loginUser == null) {
            throw new NullPointerException("loginUser对象为空");
        }
        Date endDate = new Date(System.currentTimeMillis() + EXPIRE);

        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("avatar", loginUser.getAvatar())
                .claim("id", loginUser.getId())
                .claim("user_name", loginUser.getUserName())
                .claim("nick_name", loginUser.getNickname())
                .setIssuedAt(new Date())
                .setExpiration(endDate)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();

        token = TOKEN_PREFIX + token;

        Map<String,Object> map = new HashMap<>();
        map.put("accessToken",token);
        map.put("accessTokenExpires",endDate);

        return map;
    }

    /**
     * 检验token方法
     * @param token 需要检验的token
     * @return 解析出来的token信息
     */
    public static Claims checkToken(String token){
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            return claims;
        }catch (Exception e){
            log.error("JWT token解析失败");
            return null;
        }

    }

}
