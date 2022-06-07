package cn.lycan.kk.dao;

import cn.lycan.kk.entity.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Makkapakka
 * @date 2022-6-7
 * @package_name cn.lycan.kk.dao
 * @description
 */
public interface AdminRoleDao extends JpaRepository<AdminRole, Integer> {
    
    /**
     * 通过ID找到角色
     *
     * @param id
     * @return
     */
    AdminRoleDao findById(int id);
}
