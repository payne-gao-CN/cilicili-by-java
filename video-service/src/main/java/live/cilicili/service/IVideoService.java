package live.cilicili.service;

import live.cilicili.request.CreateVideoRequest;
import live.cilicili.util.JsonData;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: payne
 * @createDate: 2022/9/26 10:17
 * @description:
 */
public interface IVideoService {

    JsonData createVideo(CreateVideoRequest request);
}
