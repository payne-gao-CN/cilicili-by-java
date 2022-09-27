package live.cilicili.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: payne
 * @createDate: 2022/9/26 10:52
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cilicili_video")
public class VideoDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 封面图
     */
    @TableField("cover_img")
    private String coverImg;

    /**
     * 详情
     */
    @TableField("detail")
    private String detail;

    /**
     * 标签
     */
    @TableField("tag")
    private String tag;

    /**
     * 类型
     */
    @TableField("type")
    private String type;

    /**
     * 地址
     */
    @TableField("url")
    private String url;

    /**
     * 地址
     */
    @TableField("upuser")
    private String upuser;

    /**
     * 创建时间
     */
    @TableField("created_at")
    private String createdAt;

    /**
     * 修改时间
     */
    @TableField("updated_at")
    private String updatedAt;

    /**
     * 删除时间
     */
    @TableField("deleted_at")
    private String deletedAt;

}
