package live.cilicili.service;

import live.cilicili.util.JsonData;

/**
 * @authoer: payne
 * @createDate: 2022/9/1
 * @description:
 */
public interface INotifyService {
    JsonData sendPhoneCode(String username);

    boolean checkCode(String username,String code);
}
