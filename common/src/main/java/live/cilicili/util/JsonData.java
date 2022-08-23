package live.cilicili.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import live.cilicili.enums.BizCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一结果数据返回封装类
 *
 * @author payne
 * @since 2022-08-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonData {
    /**
     * 状态码  0表示成功，1表示处理中  -1表示 失败
     */
    private Integer code;
    /**
     * 描述信息
     */
    private String msg;
    /**
     * 返回 数据
     */
    private Object data;

    /**
     * 成功，默认数据
     * @return 统一结果数据返回封装类对象
     */
    public static JsonData buildSuccess(){
        return new JsonData(0,null,null);
    }

    /**
     * 成功，传入数据
     * @param data 需要返回的数据
     * @return 统一结果数据返回封装类对象
     */
    public static JsonData buildSuccess(Object data){
        return new JsonData(0,null, data);
    }

    /**
     * 失败，传入描述信息
     * @param msg 传入描述信息
     * @return 统一结果数据返回封装类对象
     */
    public static JsonData buildError(String msg){
        return new JsonData(0,msg, null);
    }

    /**
     * 自定义状态码和错误信息
     * @param code 传入状态码
     * @param msg 传入描述信息
     * @return 统一结果数据返回封装类对象
     */
    public static JsonData buildCodeAndMsg(Integer code,String msg){
        return new JsonData(code ,msg , null);
    }

    /**
     * 传入枚举类，返回信息
     * @param bizCodeEnum 自定义错误枚举类
     * @return 统一结果数据返回封装类对象
     */
    public static JsonData buildResult(BizCodeEnum bizCodeEnum){
        return new JsonData(bizCodeEnum.getCode(), bizCodeEnum.getMsg(), null);
    }

    /**
     * 获取远程调用数据
     * @return 统一结果数据返回封装类对象
     */
    public <T> T getData(TypeReference<T> tTypeReference){
        return JSON.parseObject(JSON.toJSONString(data),tTypeReference);
    }
}
