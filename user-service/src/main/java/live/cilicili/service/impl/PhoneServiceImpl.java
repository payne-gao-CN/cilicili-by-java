package live.cilicili.service.impl;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.tea.TeaException;
import live.cilicili.enums.BizCodeEnum;
import live.cilicili.service.IPhoneService;
import live.cilicili.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @authoer: payne
 * @createDate: 2022/9/1
 * @description:
 */
@Slf4j
@Service
public class PhoneServiceImpl implements IPhoneService {

    @Autowired
    private Client client;

    @Override
    public JsonData sendPhoneCode(String username, String code) {
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                //短信签名名称
                .setSignName("阿里云短信测试")
                //短信模板CODE
                .setTemplateCode("SMS_154950909")
                //接收短信的手机号码
                .setPhoneNumbers(username)
                //短信模板变量对应的实际值
                .setTemplateParam("{\"code\":\"" + code + "\"}");
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            // 调用阿里云接口
//            client.sendSmsWithOptions(sendSmsRequest, runtime);
        } catch (TeaException error) {
            log.error(error.message);
            return JsonData.buildResult(BizCodeEnum.SEND_PHONE_CODE_ERROR);
        } catch (Exception _error) {
            log.error(_error.getMessage());
            return JsonData.buildResult(BizCodeEnum.SEND_PHONE_CODE_ERROR);
        }
        return null;
    }
}
