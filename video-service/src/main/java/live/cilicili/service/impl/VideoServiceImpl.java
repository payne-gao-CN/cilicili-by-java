package live.cilicili.service.impl;

import live.cilicili.request.CreateVideoRequest;
import live.cilicili.service.IFileService;
import live.cilicili.service.IVideoService;
import live.cilicili.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: payne
 * @createDate: 2022/9/26 11:01
 * @description:
 */
@Service
public class VideoServiceImpl implements IVideoService {

    @Override
    public JsonData createVideo(CreateVideoRequest request) {

        return null;
    }
}
