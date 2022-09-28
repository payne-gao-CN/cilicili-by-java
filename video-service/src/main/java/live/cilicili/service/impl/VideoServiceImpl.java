package live.cilicili.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Date;
import live.cilicili.enums.VideoStateEnum;
import live.cilicili.interceptor.LoginInterceptor;
import live.cilicili.mapper.VideoMapper;
import live.cilicili.model.LoginUser;
import live.cilicili.model.VideoDO;
import live.cilicili.request.CreateVideoRequest;
import live.cilicili.service.IFileService;
import live.cilicili.service.IVideoService;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private IFileService iFileService;



    @Override
    public void createVideo(CreateVideoRequest request) {

        LoginUser loginUser = LoginInterceptor.threadLocal.get();

        VideoDO videoDO = new VideoDO();
        BeanUtils.copyProperties(request,videoDO);
        videoDO.setUpuser(loginUser.getId());
        videoDO.setState(VideoStateEnum.UN_CHECK.toString());
        videoDO.setCreatedAt(new Date());

        videoMapper.insert(videoDO);
    }

    @Override
    public void updateVideoCoverImg(MultipartFile file , Long cvid) {
        String videoCoverImgUrl = iFileService.uploadVideoCoverImg(file);

        VideoDO videoDO = getVideoDetailById(cvid);
        iFileService.removeVideoCoverImg(videoDO.getCoverImg());

        VideoDO updateDO = new VideoDO();
        updateDO.setCvid(cvid);
        updateDO.setCoverImg(videoCoverImgUrl);

        videoMapper.updateById(updateDO);
    }

    @Override
    public boolean checkUserAndUp(Long cvid) {
      LoginUser loginUser = LoginInterceptor.threadLocal.get();
      VideoDO videoDO = getVideoDetailById(cvid);
      return loginUser.getId().equals(videoDO.getUpuser());
    }

    public VideoDO getVideoDetailById(Long id){
          return videoMapper.selectOne(new QueryWrapper<VideoDO>().eq("cvid",id));
      }
}
