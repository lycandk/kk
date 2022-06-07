package cn.lycan.kk.dao;

import cn.lycan.kk.entity.AdminUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Makkapakka
 * @date 2022-6-7
 * @package_name cn.lycan.kk.dao
 * @description
 */
public interface AdminUserRoleDAO extends JpaRepository<AdminUserRole, Integer> {
    /**
     * 通过用户id找到所有对应的角色
     *
     * @param uid
     * @return
     */
    List<AdminUserRole> findAllByUid(int uid);
    
    /**
     * 通过用户id删除所有对应的角色
     *
     * @param uid
     */
    void deleteAllByUid(int uid);
}
