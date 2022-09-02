package live.cilicili.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import live.cilicili.service.INotifyService;
import live.cilicili.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @authoer: payne
 * @createDate: 2022/9/1
 * @description:
 */

@Api("通知模块")
@Slf4j
@RestController
@RequestMapping("/api/notify/v1")
public class NotifyController {

    @Autowired
    private INotifyService iNotifyService;

    @ApiOperation("发送短信验证码")
    @RequestMapping("send_code")
    public JsonData sendPhoneCode(@RequestParam(value = "username", required = true) String username){
        iNotifyService.sendPhoneCode(username);
        return JsonData.buildSuccess();
    }

}
