package live.cilicili.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectResult;
import live.cilicili.config.OSSConfig;
import live.cilicili.service.IFileService;
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
        String originalFilename = file.getOriginalFilename();
        try {
            ossClient.putObject(ossConfig.getBucketName(), originalFilename, file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            ossClient.shutdown();
        }
        return null;
    }
}
