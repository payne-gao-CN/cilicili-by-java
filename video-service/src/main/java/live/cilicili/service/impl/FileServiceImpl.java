package live.cilicili.service.impl;

import com.aliyun.oss.OSS;
import live.cilicili.conf.OSSConfig;
import live.cilicili.service.IFileService;
import live.cilicili.util.CommonUtil;
import live.cilicili.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: payne
 * @createDate: 2022/9/26 11:59
 * @description:
 */
@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private OSSConfig ossConfig;

    @Override
    public String uploadVideoFile(MultipartFile file) {
        OSS ossClient = ossConfig.initOssClient();
        String originalFilename = file.getOriginalFilename();
        String[] split = originalFilename.split("\\.");
        String videoName = CommonUtil.generateUUID();

        String fileName = videoName + StringUtils.POINT + split[1];

        System.out.println(fileName);
        try {
            ossClient.putObject(ossConfig.getBucketName(), fileName, file.getInputStream());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ossClient.shutdown();
        }
        return "https://" + ossConfig.getBucketName() + "." + ossConfig.getEndpoint() + "/" + fileName;
    }
}
