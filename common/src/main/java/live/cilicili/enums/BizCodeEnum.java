package live.cilicili.enums;

import lombok.Getter;

public enum BizCodeEnum {

    /**
     * 用户返回频繁请求的错误
     */
    OPS_REPEAT(110001,"重复操作"),


    SEND_PHONE_CODE_ERROR(310001,"发送手机验证码发生错误"),
    USER_NAME_ERROR(310002,"用户名为空"),
    PHONE_BE_USED(310003,"用户名已存在"),
    SEND_PHONE_CODE_FREQUENTLY(310004,"发送手机验证码频繁，请稍后重试"),
    CODE_ERROR(310005,"验证码错误或已失效，请重试"),
    USER_NOT_EXIST(310006,"用户不存在"),
    PASSWORD_NO_TRUE(310007,"密码错误"),
    USER_TOKEN_INVALID(310008,"用户登录过期"),
    USER_NO_LOGIN(310009,"用户未登录"),

    NO_AUTHORITY(910001,"无该权限"),
    ;
    @Getter
    private Integer code;
    @Getter
    private String msg;

    private BizCodeEnum(int code,String message){
        this.code  = code;
        this.msg = message;
    }
}
