package live.cilicili.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @authoer: payne
 * @createDate: 2022/9/1
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cilicili_user")
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String username;

    /**
     * 密码
     */
    @TableField("password_digest")
    private String password;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickname;

    /**
     * 状态
     */
    @TableField(value = "status")
    private String status;

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

    /**
     * 盐，用于个人敏感信息处理
     */
    @TableField("secret")
    private String secret;

    @TableField("created_at")
    private Date createdAt;

    @TableField("updated_at")
    private Date updatedAt;

    @TableField("deleted_at")
    private Date deletedAt;
}
