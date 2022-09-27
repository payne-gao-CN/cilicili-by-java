package live.cilicili.service.impl;

import com.aliyun.oss.OSS;
import live.cilicili.conf.OSSConfig;
import live.cilicili.service.IOssService;
import live.cilicili.util.CommonUtil;
import live.cilicili.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: payne
 * @createDate: 2022/9/27 14:36
 * @description:
 */
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
        return "https://" + ossConfig.getBucketName() + "." + ossConfig.getEndpoint() + "/" + folder + "/" + fileName;
    }
}
