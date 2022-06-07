package cn.lycan.kk.dao;

import cn.lycan.kk.entity.AdminMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Makkapakka
 * @date 2022-6-7
 * @package_name cn.lycan.kk.dao
 * @description
 */
public interface AdminMenuDAO extends JpaRepository<AdminMenu, Integer> {
    /**
     * 通过ID找到菜单
     *
     * @param id
     * @return
     */
    AdminMenu findById(int id);
    
    /**
     * 通过父节点ID找到对应菜单
     *
     * @param parentId
     * @return
     */
    List<AdminMenu> findAllByParentId(int parentId);
}
