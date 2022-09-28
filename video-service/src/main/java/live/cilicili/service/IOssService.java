package live.cilicili.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: payne
 * @createDate: 2022/9/27 14:35
 * @description:
 */
public interface IOssService {

    /**
     * 上传文件到阿里云OSS服务
     * @param folder 传入的文件夹 多层由 “/” 嵌套，最后一个  "/"  不需要加   示例 ：  “aaa/bbb/ccc”
     * @param file 需要上传至阿里云OSS的文件
     * @return 上传的文件再阿里云OSS中获取时的URL
     */
    String uploadFileToOss(String folder, MultipartFile file);

    /**
     * 删除阿里云OSS服务上的某个文件
     * @param fileUrl 文件再阿里云OSS上的链接
     */
    void removeFile(String fileUrl);
}
