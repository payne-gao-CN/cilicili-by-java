package live.cilicili.service.impl;

import com.aliyun.oss.OSS;
import live.cilicili.conf.OSSConfig;
import live.cilicili.service.IFileService;
import live.cilicili.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    OSSConfig ossConfig;

    @Override
    public String uploadUserAvatar(MultipartFile file) {
        OSS ossClient = ossConfig.initOssClient();

        String filename = CommonUtil.generateUUID() + ".jpg";

        try {
            ossClient.putObject(ossConfig.getBucketName(), filename, file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            ossClient.shutdown();
        }
        return "https://" + ossConfig.getBucketName() + "." + ossConfig.getEndpoint() + "/" + filename;
    }
}
