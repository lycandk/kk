package cn.lycan.kk.service;

import cn.lycan.kk.dao.AdminRolePermissionDao;
import cn.lycan.kk.entity.AdminPermission;
import cn.lycan.kk.entity.AdminRolePermission;
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
public class AdminRolePermissionService {
    @Autowired
    AdminRolePermissionDao adminRolePermissionDao;
    
    public List<AdminRolePermission> findAllByRid(int rid) {
        return adminRolePermissionDao.findAllByRid(rid);
    }
    
    @Transactional
    public void savePermChanges(int rid, List<AdminPermission> perms) {
        adminRolePermissionDao.deleteAllByRid(rid);
        List<AdminRolePermission> adminRolePermissions = new ArrayList<>();
        perms.forEach(p -> {
            AdminRolePermission adminRolePermission = new AdminRolePermission();
            adminRolePermission.setRid(rid);
            adminRolePermission.setPid(p.getId());
            adminRolePermissions.add(adminRolePermission);
        });
        
        adminRolePermissionDao.saveAll(adminRolePermissions);
    }
}
