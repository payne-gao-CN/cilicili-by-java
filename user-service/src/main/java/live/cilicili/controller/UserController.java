package live.cilicili.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import live.cilicili.request.UserRegisterRequest;
import live.cilicili.service.IFileService;
import live.cilicili.service.IUserService;
import live.cilicili.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户模块控制器
 *
 * @author payne
 * @since 2022-08-23
 */
@Api("用户模块")
@RestController
@RequestMapping("/api/user/v1")
public class UserController {

    @Autowired
    private IFileService iFileService;

    @Autowired
    private IUserService iUserService;

    @ApiOperation("用户头像上传")
    @RequestMapping("/upload")
    public JsonData upLoadImg(@RequestPart("file") MultipartFile file){
        iFileService.uploadUserAvatar(file);
        return JsonData.buildSuccess();
    }

    @ApiOperation("用户注册")
    @RequestMapping("register")
    public JsonData userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        return iUserService.register(userRegisterRequest);
    }

}
