package live.cilicili.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.UUID;

/**
 * @authoer: payne
 * @createDate: 2022/9/1
 * @description:
 */
@Slf4j
public class CommonUtil {
    private static final String ALL_CATCH_NUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * 获取验证码随机数
     * @param length 长度
     * @return 返回的验证码
     */
    public static String getRandomCode(int length){
        String source = "0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(source.charAt(random.nextInt(9)));
        }
        return stringBuffer.toString();
    }

    /**
     * 获取当前时间戳
     */
    public static long getCurrentTimestamp(){
        return System.currentTimeMillis();
    }

    /**
     * 产生随机长度的字符串
     * @param length 字符创的长度
     * @return 生成的字符串
     */
    public static String getRandomStringNum(int length){
        //生成随机的数字个字母
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(ALL_CATCH_NUM.charAt(random.nextInt(ALL_CATCH_NUM.length())));
        }
        return stringBuilder.toString();
    }


    /**
     * 响应json数据给前端
     * @param response
     * @param obj
     */
    public static void sendJsonMessage(HttpServletResponse response, Object obj){

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = null;

        try {
            writer = response.getWriter();
            writer.println(objectMapper.writeValueAsString(obj));
            response.flushBuffer();
        } catch (IOException e) {
            log.warn("响应json数据给前端异常");
        }finally {
            if (writer != null){
                writer.close();
            }
        }


    }

    /**
     * 生成UUID
     * @return 32位随机字符串
     */
    public static String generateUUID(){
        return UUID.randomUUID().toString().replace("-","").substring(0,32);
    }
}
