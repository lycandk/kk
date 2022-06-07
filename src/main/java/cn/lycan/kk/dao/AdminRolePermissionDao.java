package cn.lycan.kk.dao;

import cn.lycan.kk.entity.AdminPermission;
import cn.lycan.kk.entity.AdminRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Makkapakka
 * @date 2022-6-7
 * @package_name cn.lycan.kk.dao
 * @description
 */
public interface AdminRolePermissionDao extends JpaRepository<AdminRolePermission, Integer> {
    
    List<AdminRolePermission> findAllByRid(int rid);
    
    List<AdminRolePermission> findAllByRidIn(List<Integer> ridList);
    
    void deleteAllByRid(int rid);
}
