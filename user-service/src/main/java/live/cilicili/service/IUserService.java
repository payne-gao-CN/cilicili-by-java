package live.cilicili.service;

import live.cilicili.request.UserEditRequest;
import live.cilicili.request.UserLoginRequest;
import live.cilicili.request.UserRegisterRequest;
import live.cilicili.util.JsonData;

public interface IUserService {

    /**
     *
     * @param userRegisterRequest
     * @return
     */
    JsonData register(UserRegisterRequest userRegisterRequest);

    JsonData login(UserLoginRequest userLoginRequest);

    void uploadUserAvatar(String avatarUrl);

  JsonData getUserDetail();

  void editUserDetail(UserEditRequest request);

  void editUserPassword(String password);
}
