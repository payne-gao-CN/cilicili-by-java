package live.cilicili.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@authoer: payne
 *@createDate: 2022-09-01
 *@description: 登录用户类
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickname;


    /**
     * 用户头像
     */
    private String avatar;

}
