package live.cilicili.config;

import com.aliyun.dysmsapi20170525.Client;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @authoer: payne
 * @createDate: 2022/9/1
 * @description:
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.message")
public class MessageConfig {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    @Bean
    public Client initMessageClient() throws Exception{
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);

        config.endpoint = endpoint;
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

}
