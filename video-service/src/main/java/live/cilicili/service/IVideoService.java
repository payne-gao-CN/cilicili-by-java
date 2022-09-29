package live.cilicili.service;

import live.cilicili.request.CreateVideoRequest;
import live.cilicili.vo.VideoVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: payne
 * @createDate: 2022/9/26 10:17
 * @description:
 */
public interface IVideoService {

  /**
   * 投稿（创建新稿件）
   * @param request 创建新稿件的详细信息
   */
  void createVideo(CreateVideoRequest request);

  /**
   * 根据稿件ID修改稿件封面图
   * @param file 封面文件
   * @param cvid 稿件ID
   */
  void updateVideoCoverImg(MultipartFile file , Long cvid );

  /**
   * 查看当前登录用户是否有该稿件的修改权限
   * @param cvid 当前稿件ID
   * @return 是够拥有修改稿件信息的权限
   */
  boolean checkUserAndUp(Long cvid);

  /**
   * 根据稿件ID获取稿件详细信息()
   * @param cvid 稿件id
   * @return 视频详细信息
   */
  VideoVO getVideo(Long cvid);
}
