package live.cilicili.controller;

import live.cilicili.enums.BizCodeEnum;
import live.cilicili.request.CreateVideoRequest;
import live.cilicili.service.IFileService;
import live.cilicili.service.IVideoService;
import live.cilicili.util.JsonData;
import live.cilicili.vo.VideoVO;
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

    /**
     * 上传视频文件
     * @param file 视频文件
     * @return 视频文件地址
     */
    @RequestMapping("/uploadVideoFile")
    public JsonData uploadVideoFile(@RequestPart("videoFile") MultipartFile file){
        String fileUrl = iFileService.uploadVideoFile(file);
        return JsonData.buildSuccess(fileUrl);
    }

    /**
     * 创建视频信息
     * @param request 视频相关信息
     * @return 统一返回封装结果
     */
    @RequestMapping("/createVideo")
    public JsonData createVideos(@RequestBody CreateVideoRequest request){
        iVideoService.createVideo(request);
        return JsonData.buildSuccess();
    }

    /**
     * 上传视频封面文件
     * @param file 视频封面文件
     * @return 视频封面文件地址
     */
    @RequestMapping("/uploadVideoCoverImg")
    public JsonData uploadVideoCoverImg(@RequestPart("videoCoverImg") MultipartFile file){
        String fileUrl = iFileService.uploadVideoCoverImg(file);
        return JsonData.buildSuccess(fileUrl);
    }

    /**
     * 修改视频封面图
     * @param file 上传的新的封面图
     * @return 统一封装返回类
     */
    @PutMapping("/updateVideoCoverImg")
    public JsonData updateVideoCoverImg(@RequestPart("videoCoverImg") MultipartFile file , Long cvid){
        if (iVideoService.checkUserAndUp(cvid)){
            iVideoService.updateVideoCoverImg(file, cvid);
            return JsonData.buildSuccess();
        }
        return JsonData.buildResult(BizCodeEnum.NO_AUTHORITY);
    }

    @GetMapping("/getVideoDetail")
    public JsonData getVideo(@RequestParam("cvid") Long cvid){
        VideoVO video = iVideoService.getVideo(cvid);
        return JsonData.buildSuccess(video);
    }


}
