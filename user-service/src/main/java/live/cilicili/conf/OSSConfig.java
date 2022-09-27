package live.cilicili.conf;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author payne
 * @version 1.0
 * @Date 2022/4/22 14:03
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class OSSConfig {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    @Bean
    public OSS initOssClient(){
        //创建OSS客户端对象
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
