package cn.ecnu.mapper;

import cn.ecnu.entity.User;
import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.vo.UserBackVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户 Mapper
 *
 * @author zachary
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户后台数量
     *
     * @param condition 条件
     * @return 用户数量
     */
    Long countUser(@Param("condition") ConditionDTO condition);

    /**
     * 查询后台用户列表
     *
     * @param limit     页码
     * @param size      大小
     * @param condition 条件
     * @return 用户后台列表
     */
    List<UserBackVO> listUserBackVO(@Param("limit") Long limit, @Param("size") Long size, @Param("condition") ConditionDTO condition);
}