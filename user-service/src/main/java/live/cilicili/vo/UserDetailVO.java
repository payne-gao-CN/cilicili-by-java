package live.cilicili.vo;

import lombok.Data;

/**
 * @Author: payne
 * @createDate: 2022/9/28 10:27
 * @description:
 */
@Data
public class UserDetailVO {


  /**
   * 用户名
   */
  private String username;

  /**
   * 昵称
   */
  private String nickname;

  /**
   * 用户头像
   */
  private String avatar;

  /**
   * 用户签名
   */
  private String slogan;

}
