package cn.lycan.kk.service;

import cn.lycan.kk.dao.AdminRoleDao;
import cn.lycan.kk.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Makkapakka
 * @date 2022-6-7
 * @package_name cn.lycan.kk.service
 * @description
 */
@Service
public class AdminRoleService {
    
    @Autowired
    AdminRoleDao adminRoleDao;
    
    @Autowired
    UserService userService;
    
    @Autowired
    AdminUserRoleService adminUserRoleService;
    
    @Autowired
    AdminPermissionService adminPermissionService;
    
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    
    @Autowired
    AdminMenuService adminMenuService;
    
    public List<AdminRole> listWithPermsAndMenus() {
        List<AdminRole> adminRoles = adminRoleDao.findAll();
        List<AdminPermission> adminPermissions;
        List<AdminMenu> adminMenus;
        for (AdminRole adminRole : adminRoles) {
            adminPermissions = adminPermissionService.listPermsByRoleId(adminRole.getId());
            adminMenus = adminMenuService.getMenusByRoleId(adminRole.getId());
            adminRole.setPerms(adminPermissions);
            adminRole.setMenus(adminMenus);
        }
        return adminRoles;
    }
    
    public List<AdminRole> findAll() {
        return adminRoleDao.findAll();
    }
    
    public void addOrUpdate(AdminRole adminRole) {
        adminRoleDao.save(adminRole);
    }
    
    
    public List<AdminRole> listRoleByUser(String username) {
        int uid = userService.getByName(username).getId();
        List<Integer> rids = adminUserRoleService.findAllByUid(uid).stream().map(AdminUserRole::getRid).collect(Collectors.toList());
        return adminRoleDao.findAllById(rids);
    }
    
    public AdminRole updateRoleStatus(AdminRole adminRole) {
        AdminRole adminRoleInDb = adminRoleDao.getById(adminRole.getId());
        adminRoleInDb.setEnabled(adminRole.isEnabled());
        return adminRoleDao.save(adminRoleInDb);
    }
    
    public void editRole(@RequestBody AdminRole adminRole) {
        adminRoleDao.save(adminRole);
        adminRolePermissionService.savePermChanges(adminRole.getId(), adminRole.getPerms());
    }
}
