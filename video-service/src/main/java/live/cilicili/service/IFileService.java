package live.cilicili.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: payne
 * @createDate: 2022/9/26 10:17
 * @description:
 */
public interface IFileService {
    String uploadVideoFile(MultipartFile file);
}
