package live.cilicili.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: payne
 * @createDate: 2022/9/26 11:19
 * @description:
 */
@Api("投稿对象")
@Data
public class CreateVideoRequest {

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 封面图
     */
    @ApiModelProperty(value = "封面图")
    private String coverImg;

    /**
     * 详情
     */
    @ApiModelProperty(value = "详情")
    private String detail;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    private String tag;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String type;

}
