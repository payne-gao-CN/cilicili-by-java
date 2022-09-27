package live.cilicili.controller;

import live.cilicili.request.CreateVideoRequest;
import live.cilicili.service.IFileService;
import live.cilicili.service.IVideoService;
import live.cilicili.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: payne
 * @createDate: 2022/9/13 13:43
 * @description:
 */
@RestController
@RequestMapping("/api/video/v1")
public class VideoController {

    @Autowired
    private IVideoService iVideoService;

    @Autowired
    private IFileService iFileService;

    @RequestMapping("/uploadVideoFile")
    public JsonData uploadVideoFile(@RequestPart("videoFile") MultipartFile file){
        String fileUrl = iFileService.uploadVideoFile(file);
        return JsonData.buildSuccess(fileUrl);
    }

    @RequestMapping("/createVideo")
    public JsonData createVideos(@RequestBody CreateVideoRequest request){
        iVideoService.createVideo(request);
        return JsonData.buildSuccess();
    }
}
