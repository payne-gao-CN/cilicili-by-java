package live.cilicili.enums;

import lombok.Getter;

public enum BizCodeEnum {

    OPS_REPEAT(110001,"重复操作"),


    SEND_PHONE_CODE_ERROR(310001,"发送手机验证码发生错误"),
    USER_NAME_ERROR(310002,"用户名为空"),
    PHONE_BE_USED(310003,"用户名已存在"),
    SEND_PHONE_CODE_FREQUENTLY(310004,"发送手机验证码频繁，请稍后重试"),
    CODE_ERROR(310005,"验证码错误或已失效，请重试")
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
