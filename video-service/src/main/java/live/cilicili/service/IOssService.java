package live.cilicili.service;

import com.aliyun.oss.OSS;
import live.cilicili.util.CommonUtil;
import live.cilicili.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: payne
 * @createDate: 2022/9/27 14:35
 * @description:
 */
public interface IOssService {
    String uploadFileToOss(String folder, MultipartFile file);
}
