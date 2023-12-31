package cn.ecnu.mapper;

import cn.ecnu.model.dto.ConditionDTO;
import cn.ecnu.model.dto.MenuDTO;
import cn.ecnu.model.vo.MenuOption;
import cn.ecnu.model.vo.MenuTree;
import cn.ecnu.model.vo.MenuVO;
import cn.ecnu.model.vo.UserMenuVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.ecnu.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单 Mapper
 *
 * @author zachary
 * @date 2022/12/04 16:24
 **/
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据角色id查询对应权限
     *
     * @param roleId id
     * @return 权限标识
     */
    List<String> selectPermissionByRoleId(@Param("roleId") String roleId);

    /**
     * 查询菜单列表
     *
     * @param condition 查询条件
     * @return 菜单列表
     */
    List<MenuVO> selectMenuVOList(@Param("condition") ConditionDTO condition);

    /**
     * 根据用户id查询用户菜单列表
     *
     * @param userId 用户id
     * @return 用户菜单列表
     */
    List<UserMenuVO> selectMenuByUserId(@Param("userId") Integer userId);

    /**
     * 查询菜单下拉树
     *
     * @return 菜单下拉树
     */
    List<MenuTree> selectMenuTree();

    /**
     * 查询菜单选项树
     *
     * @return 菜单选项树
     */
    List<MenuOption> selectMenuOptions();

    /**
     * 根据id查询菜单信息
     *
     * @param menuId 菜单id
     * @return 菜单
     */
    MenuDTO selectMenuById(@Param("menuId") Integer menuId);
}