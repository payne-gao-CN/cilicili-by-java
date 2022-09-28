package live.cilicili.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: payne
 * @createDate: 2022/9/26 10:17
 * @description:
 */
public interface IFileService {

    /**
     *  上传视频文件
     * @param file 封面图文件
     * @return 封面图地址
     */
    String uploadVideoFile(MultipartFile file);

    /**
     * 上传视频文件
     * @param file  封面图文件
     * @return 封面图地址
     */
    String uploadVideoCoverImg(MultipartFile file);

    /**
     * 删除视频封面图
     * @param videoCoverUrl 需要删除的视频封面图地址
     */
    void removeVideoCoverImg(String videoCoverUrl);
}
