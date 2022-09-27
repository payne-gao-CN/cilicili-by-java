package live.cilicili.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String uploadUserAvatar(MultipartFile file);

    String removeAvatar(String fileName);
}
