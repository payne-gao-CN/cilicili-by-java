package live.cilicili.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.luciad.imageio.webp.WebPWriteParam;
import live.cilicili.conf.OSSConfig;
import live.cilicili.service.IFileService;
import live.cilicili.util.CommonUtil;
import live.cilicili.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
@Slf4j
@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    OSSConfig ossConfig;

    private static final String WEBP_END = "webp";

    @Override
    public String uploadUserAvatar(MultipartFile file) {
        String fileName = null;
        try {
            fileName = imageToWebp(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    public String imageToWebp(MultipartFile file) throws IOException {
        String videoName = CommonUtil.generateUUID();
        String fileName = videoName + StringUtils.POINT + WEBP_END;
        BufferedImage image = ImageIO.read(file.getInputStream());
        ImageWriter writer = ImageIO.getImageWritersByMIMEType("image/webp").next();
        WebPWriteParam writeParam = new WebPWriteParam(writer.getLocale());
        //Notify encoder to consider WebPWriteParams
        writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        //Set lossy compression
        writeParam.setCompressionType(writeParam.getCompressionTypes()[WebPWriteParam.LOSSY_COMPRESSION]);
        //Set 80% quality. Allowed values are between 0 and 1
        writeParam.setCompressionQuality(0.7f);
        // Save the image
        writer.setOutput(new FileImageOutputStream(new File(fileName)));
        writer.write(null, new IIOImage(image, null, null), writeParam);
        uploadFileToOss("", fileName);
        return fileName;
    }

    public void uploadFileToOss(String folder,String filePath){
        OSS ossClient = ossConfig.initOssClient();
        File file = new File(filePath);
        String filename = file.getName();

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(ossConfig.getBucketName(),
                    StringUtils.isBank(folder) ? filename : (folder + "/" + filename),
                    file);
            ossClient.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
            boolean result = file.delete();
            for(int i = 0; !result && i++ < 10; result = file.delete()) {
                log.info(String.valueOf(result));
                // 垃圾回收
                System.gc();
            }
        }
    }
}
