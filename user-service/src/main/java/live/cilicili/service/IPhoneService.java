package live.cilicili.service;

import live.cilicili.util.JsonData;

/**
 * @authoer: payne
 * @createDate: 2022/9/1
 * @description:
 */
public interface IPhoneService {
    /**
     * 发送手机验证码
     * @param username 需要发送的手机号
     * @param code 需要发送到手机号的验证码
     * @return 统一接口返回类
     */
    JsonData sendPhoneCode(String username, String code);
}
