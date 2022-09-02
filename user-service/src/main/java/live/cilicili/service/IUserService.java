package live.cilicili.service;

import live.cilicili.request.UserRegisterRequest;
import live.cilicili.util.JsonData;

public interface IUserService {

    JsonData register(UserRegisterRequest userRegisterRequest);
}
