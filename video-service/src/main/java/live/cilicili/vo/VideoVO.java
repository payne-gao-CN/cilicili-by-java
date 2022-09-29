package live.cilicili.vo;

import lombok.Data;

/**
 * @Author: payne
 * @createDate: 2022/9/28 16:37
 * @description:
 */
@Data
public class VideoVO {

    private Long cvid;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面图
     */
    private String coverImg;

    /**
     * 详情
     */
    private String detail;

    /**
     * 标签
     */
    private String tag;

    /**
     * 类型
     */
    private String type;

    /**
     * 地址
     */
    private String url;

    /**
     * 上传者
     */
    private Long upuser;
}
