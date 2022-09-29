package live.cilicili.vo;

import com.baomidou.mybatisplus.annotation.TableField;
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
  @TableField("nick_name")
  private String nickname;

  /**
   * 用户头像
   */
  @TableField("avatar")
  private String avatar;

  /**
   * 用户签名
   */
  @TableField("slogan")
  private String slogan;

}
