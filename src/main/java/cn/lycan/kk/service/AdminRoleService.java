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
        log.info("获取所有角色：" + adminRoles);
        List<AdminPermission> adminPermissions;
        List<AdminMenu> adminMenus;
        log.info("--------遍历开始---------");
        for (AdminRole adminRole : adminRoles) {
            log.info("根据角色id：" + adminRole.getId() + "获取对应的功能权限：" + adminPermissionService.listPermsByRoleId(adminRole.getId()));
            adminPermissions = adminPermissionService.listPermsByRoleId(adminRole.getId());
            log.info("将获取的功能权限赋值给功能权限列表:" + adminPermissions);
            log.info("根据角色id：" + adminRole.getId() + "获取对应的菜单权限：" + adminMenuService.getMenusByRoleId(adminRole.getId()));
            adminMenus = adminMenuService.getMenusByRoleId(adminRole.getId());
            log.info("将获取的菜单权限赋值给菜单权限列表:" + adminMenus);
            adminRole.setPerms(adminPermissions);
            adminRole.setMenus(adminMenus);
            log.info("对" + adminRole + "设置功能权限:" + adminPermissions + ";" + "设置菜单权限:" + adminMenus);
        }
        log.info("--------遍历结束---------");
        log.info("AdminRoles:" + adminRoles);
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
        log.info("根据:" + username + "获取用户id：" + uid);
        List<Integer> rids = adminUserRoleService.findAllByUid(uid).stream().map(AdminUserRole::getRid).collect(Collectors.toList());
        log.info("通过用户id:" + uid + "获取对应的所有角色:" + rids);
        return adminRoleDao.findAllById(rids);
    }
    
    public AdminRole updateRoleStatus(AdminRole adminRole) {
        AdminRole adminRoleInDb = adminRoleDao.getById(adminRole.getId());
        log.info("根据角色id:" + adminRole + "从数据库中获取角色：" + adminRoleInDb);
        adminRoleInDb.setEnabled(adminRole.isEnabled());
        log.info("使能角色：" + adminRole.isEnabled());
        return adminRoleDao.save(adminRoleInDb);
    }
    
    public void editRole(@RequestBody AdminRole adminRole) {
        log.info("保存角色:" + adminRole);
        adminRoleDao.save(adminRole);
        log.info("保存该角色对应的功能权限：" + adminRole.getPerms().toString());
        adminRolePermissionService.savePermChanges(adminRole.getId(), adminRole.getPerms());
    }
}
