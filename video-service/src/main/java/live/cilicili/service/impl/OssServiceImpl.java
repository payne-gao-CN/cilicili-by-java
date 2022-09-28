package live.cilicili.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import live.cilicili.conf.OSSConfig;
import live.cilicili.service.IOssService;
import live.cilicili.util.CommonUtil;
import live.cilicili.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: payne
 * @createDate: 2022/9/27 14:36
 * @description:
 */
@Slf4j
@Service
public class OssServiceImpl implements IOssService {

    @Autowired
    private OSSConfig ossConfig;


    @Override
    public String uploadFileToOss(String folder, MultipartFile file) {
        OSS ossClient = ossConfig.initOssClient();
        String originalFilename = file.getOriginalFilename();
        String[] split = originalFilename.split("\\.");
        String videoCoverImgName = CommonUtil.generateUUID();

        String fileName = folder + "/" + videoCoverImgName + StringUtils.POINT + split[1];
        try {
            ossClient.putObject(ossConfig.getBucketName(), fileName, file.getInputStream());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ossClient.shutdown();
        }
        return "https://" + ossConfig.getBucketName() + "." + ossConfig.getEndpoint() + "/" + fileName;
    }

    @Override
    public void removeFile(String fileUrl) {
        OSS ossClient = ossConfig.initOssClient();
        String[] fileNames = fileUrl.split("/");
        StringBuilder fileName = new StringBuilder();
        for(int i = 3; i < fileNames.length; i++) {
            if (i != fileNames.length-1){
                fileName.append(fileNames[i]).append("/");
            }else {
                fileName.append(fileNames[i]);
            }
        }
        try {
            ossClient.deleteObject(ossConfig.getBucketName(), fileName.toString());
            log.info("删除封面图成功，fileName:{}", fileName.toString());
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
