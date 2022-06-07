package cn.lycan.kk.dao;

import cn.lycan.kk.entity.AdminRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Makkapakka
 * @date 2022-6-7
 * @package_name cn.lycan.kk.dao
 * @description
 */
public interface AdminRoleMenuDAO extends JpaRepository<AdminRoleMenu, Integer> {
    
    /**
     * 通过多个角色ID（列表）找到列表中所有角色对应的菜单
     *
     * @param ridsIn
     * @return
     */
    List<AdminRoleMenu> findAllByRidIn(List<Integer> ridsIn);
    
    /**
     * 通过单个角色ID找到对应的所有菜单
     *
     * @param rid
     * @return
     */
    List<AdminRoleMenu> findAllByRid(int rid);
    
    /**
     * 通过角色ID删除对应的所有菜单
     *
     * @param rid
     */
    void deleteAllByRid(int rid);
    
}
