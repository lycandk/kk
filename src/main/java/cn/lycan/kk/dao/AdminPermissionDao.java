package cn.lycan.kk.dao;

import cn.lycan.kk.entity.AdminPermission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Makkapakka
 * @date 2022-6-7
 * @package_name cn.lycan.kk.dao
 * @description
 */
public interface AdminPermissionDao extends JpaRepository<AdminPermission, Integer> {
    AdminPermission findById(int id);
}
