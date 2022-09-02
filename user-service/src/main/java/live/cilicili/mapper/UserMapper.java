package live.cilicili.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import live.cilicili.model.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper接口
 * @authoer: payne
 * @createDate: 2022/9/1
 * @description:
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
