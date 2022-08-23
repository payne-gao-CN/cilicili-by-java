package live.cilicili.enums;

import lombok.Getter;

public enum BizCodeEnum {

    OPS_REPEAT(110001,"重复操作");
    @Getter
    private Integer code;
    @Getter
    private String msg;

    private BizCodeEnum(int code,String message){
        this.code  = code;
        this.msg = message;
    }
}
