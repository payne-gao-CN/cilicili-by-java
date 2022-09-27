package live.cilicili.service.impl;

import com.aliyun.oss.OSS;
import live.cilicili.conf.OSSConfig;
import live.cilicili.service.IFileService;
import live.cilicili.service.IOssService;
import live.cilicili.util.CommonUtil;
import live.cilicili.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: payne
 * @createDate: 2022/9/26 11:59
 * @description:
 */
@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private IOssService iOssService;

    private static final String COVER_IMG_FOLDER = "cover_imf_folder";

    @Override
    public String uploadVideoFile(MultipartFile file) {
        return iOssService.uploadFileToOss("",file);
    }

    @Override
    public String uploadVideoCoverImg(MultipartFile file) {
        return iOssService.uploadFileToOss(COVER_IMG_FOLDER,file);
    }

}
