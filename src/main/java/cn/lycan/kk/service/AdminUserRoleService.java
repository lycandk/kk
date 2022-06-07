package cn.lycan.kk.service;

import cn.lycan.kk.dao.AdminUserRoleDAO;
import cn.lycan.kk.entity.AdminRole;
import cn.lycan.kk.entity.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Makkapakka
 * @date 2022-6-7
 * @package_name cn.lycan.kk.service
 * @description
 */
@Service
public class AdminUserRoleService {
    
    @Autowired
    AdminUserRoleDAO adminUserRoleDAO;
    
    public List<AdminUserRole> findAllByUid(int uid) {
        return adminUserRoleDAO.findAllByUid(uid);
    }
    
    @Transactional
    public void saveRoleChanges(int uid, List<AdminRole> rids) {
        //先删除对应用户ID的角色
        adminUserRoleDAO.deleteAllByUid(uid);
        //用户角色列表
        List<AdminUserRole> adminUserRoles = new ArrayList<>();
        rids.forEach(r -> {
            AdminUserRole adminUserRole = new AdminUserRole();
            //设置用户ID
            adminUserRole.setUid(uid);
            //设置角色id
            adminUserRole.setRid(r.getId());
            //添加进用户角色列表
            adminUserRoles.add(adminUserRole);
        });
        
        adminUserRoleDAO.saveAll(adminUserRoles);
    }
}
