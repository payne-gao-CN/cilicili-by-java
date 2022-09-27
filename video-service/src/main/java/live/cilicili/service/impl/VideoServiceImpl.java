package live.cilicili.service.impl;

import live.cilicili.enums.VideoStateEnum;
import live.cilicili.interceptor.LoginInterceptor;
import live.cilicili.mapper.VideoMapper;
import live.cilicili.model.LoginUser;
import live.cilicili.model.VideoDO;
import live.cilicili.request.CreateVideoRequest;
import live.cilicili.service.IVideoService;
import live.cilicili.util.JsonData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: payne
 * @createDate: 2022/9/26 11:01
 * @description:
 */
@Service
public class VideoServiceImpl implements IVideoService {

    @Autowired
    private VideoMapper videoMapper;

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
}
