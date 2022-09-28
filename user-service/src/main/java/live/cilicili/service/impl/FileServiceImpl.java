package live.cilicili.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import live.cilicili.conf.OSSConfig;
import live.cilicili.service.IFileService;
import live.cilicili.util.CommonUtil;
import live.cilicili.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Slf4j
@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    OSSConfig ossConfig;

    @Override
    public String uploadUserAvatar(MultipartFile file) {
        OSS ossClient = ossConfig.initOssClient();
        String originalFilename = file.getOriginalFilename();

        String[] split = originalFilename.split("\\.");
        String videoName = CommonUtil.generateUUID();

        String fileName = videoName + StringUtils.POINT + split[1];

        try {
            ossClient.putObject(ossConfig.getBucketName(), fileName, file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            ossClient.shutdown();
        }
        return "https://" + ossConfig.getBucketName() + "." + ossConfig.getEndpoint() + "/" + fileName;
    }

    @Override
    public void removeAvatar(String fileName) {
        OSS ossClient = ossConfig.initOssClient();
        String[] fileNames = fileName.split("/");
        try {
            ossClient.deleteObject(ossConfig.getBucketName(),fileNames[3]);
        } catch (OSSException oe) {
            log.error("捕获了一个OSSException，这意味着您的请求发送到了OSS，但由于某种原因被错误响应拒绝。");
            log.error("Error Message:" + oe.getErrorMessage());
            log.error("Error Code:" + oe.getErrorCode());
            log.error("Request ID:" + oe.getRequestId());
            log.error("Host ID:" + oe.getHostId());
        }catch (ClientException ce){
            log.error("捕获到ClientException，这意味着客户端在尝试与OSS通信时遇到了严重的内部问题，例如无法访问网络。");
            log.error("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
