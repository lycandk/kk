package cn.lycan.kk.service;

import cn.lycan.kk.dao.AdminPermissionDao;
import cn.lycan.kk.dao.AdminRolePermissionDao;
import cn.lycan.kk.entity.AdminPermission;
import cn.lycan.kk.entity.AdminRole;
import cn.lycan.kk.entity.AdminRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Makkapakka
 * @date 2022-6-7
 * @package_name cn.lycan.kk.service
 * @description
 */
@Service
public class AdminPermissionService {
    
    @Autowired
    AdminPermissionDao adminPermissionDao;
    
    @Autowired
    AdminUserRoleService adminUserRoleService;
    
    @Lazy
    @Autowired
    AdminRoleService adminRoleService;
    
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    
    @Autowired
    AdminRolePermissionDao adminRolePermissionDao;
    
    @Autowired
    UserService userService;
    
    public List<AdminPermission> list() {
        return adminPermissionDao.findAll();
    }
    
    /**
     * Determine whether client requires permission when requests
     * a certain API.
     *
     * @param requestAPI
     * @return
     */
    public boolean needFilter(String requestAPI) {
        List<AdminPermission> adminPermissions = adminPermissionDao.findAll();
        for (AdminPermission adminPermission : adminPermissions) {
            //match prefix
            if (requestAPI.startsWith(adminPermission.getUrl())) {
                return true;
            }
        }
        return false;
    }
    
    public List<AdminPermission> listPermsByRoleId(int rid) {
        List<Integer> pids = adminRolePermissionService.findAllByRid(rid).stream().map(AdminRolePermission::getPid).collect(Collectors.toList());
        return adminPermissionDao.findAllById(pids);
    }
    
    public Set<String> listPermissonByURLsByUser(String username) {
        List<Integer> rids = adminRoleService.listRoleByUser(username).stream().map(AdminRole::getId).collect(Collectors.toList());
        
        List<Integer> pids = adminRolePermissionDao.findAllByRidIn(rids).stream().map(AdminRolePermission::getPid).collect(Collectors.toList());
        
        List<AdminPermission> adminPermissions = adminPermissionDao.findAllById(pids);
        
        Set<String> URLs = adminPermissions.stream().map(AdminPermission::getUrl).collect(Collectors.toSet());
        
        return URLs;
    }
}
