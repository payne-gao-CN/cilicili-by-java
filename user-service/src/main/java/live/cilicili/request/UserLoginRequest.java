package live.cilicili.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @authoer: payne
 * @createDate: 2022/9/2
 * @description:
 */
@Api("用户登录对象")
@Data
public class UserLoginRequest {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;
}
