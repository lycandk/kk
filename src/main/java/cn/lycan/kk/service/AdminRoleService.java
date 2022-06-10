package cn.lycan.kk.service;

import cn.lycan.kk.dao.AdminRoleDao;
import cn.lycan.kk.entity.AdminMenu;
import cn.lycan.kk.entity.AdminPermission;
import cn.lycan.kk.entity.AdminRole;
import cn.lycan.kk.entity.AdminUserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
@Slf4j
public class AdminRoleService {
    
    @Autowired
    AdminRoleDao adminRoleDao;
    
    @Autowired
    UserService userService;
    
    @Lazy
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
        log.info("--------遍历开始---------");
        for (AdminRole adminRole : adminRoles) {
            adminPermissions = adminPermissionService.listPermsByRoleId(adminRole.getId());
            adminMenus = adminMenuService.getMenusByRoleId(adminRole.getId());
            adminRole.setPerms(adminPermissions);
            adminRole.setMenus(adminMenus);
            log.info("对" + adminRole + "设置功能权限:" + adminPermissions + ";" + "\n设置菜单权限:" + adminMenus);
        }
        log.info("--------遍历结束---------");
        log.info("汇总AdminRoles:" + adminRoles);
        return adminRoles;
    }
    
    public List<AdminRole> findAll() {
        return adminRoleDao.findAll();
    }
    
    public void addOrUpdate(AdminRole adminRole) {
        log.info("添加或更新角色：" + adminRole);
        adminRoleDao.save(adminRole);
    }
    
    
    public List<AdminRole> listRoleByUser(String username) {
        int uid = userService.getByName(username).getId();
        List<Integer> rids = adminUserRoleService.findAllByUid(uid).stream().map(AdminUserRole::getRid).collect(Collectors.toList());
        log.info("通过用户id:" + uid + "\n获取对应的所有角色:" + rids);
        return adminRoleDao.findAllById(rids);
    }
    
    public AdminRole updateRoleStatus(AdminRole adminRole) {
        AdminRole adminRoleInDb = adminRoleDao.getById(adminRole.getId());
        adminRoleInDb.setEnabled(adminRole.isEnabled());
        log.info("对角色:" + adminRole + "使能：" + adminRole.isEnabled());
        return adminRoleDao.save(adminRoleInDb);
    }
    
    public void editRole(@RequestBody AdminRole adminRole) {
        adminRoleDao.save(adminRole);
        adminRolePermissionService.savePermChanges(adminRole.getId(), adminRole.getPerms());
        log.info("保存角色:" + adminRole + "\n保存该角色对应的功能权限：" + adminRole.getPerms().toString());
    }
}
