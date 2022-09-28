package live.cilicili.conf;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: payne
 * @createDate: 2022/9/13 10:48
 * @description:
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class OSSConfig {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    public OSS initOssClient(){
        //创建OSS客户端对象
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

}
