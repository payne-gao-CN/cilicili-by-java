package live.cilicili.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

/**
 * @Author: payne
 * @createDate: 2022/9/28 16:53
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cilicili_video_comment")
public class CommentDO implements Serializable {

}
