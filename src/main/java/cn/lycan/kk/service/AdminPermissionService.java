package cn.lycan.kk.service;

import cn.lycan.kk.dao.AdminPermissionDao;
import cn.lycan.kk.dao.AdminRolePermissionDao;
import cn.lycan.kk.entity.AdminPermission;
import cn.lycan.kk.entity.AdminRole;
import cn.lycan.kk.entity.AdminRolePermission;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        log.info("获取所有功能权限");
        return adminPermissionDao.findAll();
    }
    
    /**
     * Determine whether client requires permission when requests
     * a certain API.
     * 用于判断用户请求接口的是否在权限列表中。如果没有对应权限，说明不需要维护。
     *
     * @param requestAPI
     * @return
     */
    public boolean needFilter(String requestAPI) {
        List<AdminPermission> adminPermissions = adminPermissionDao.findAll();
        for (AdminPermission adminPermission : adminPermissions) {
            //match prefix
            if (requestAPI.startsWith(adminPermission.getUrl())) {
                log.info("requestAPI:" + requestAPI + "\nadminPermission.getUrl():" + adminPermission.getUrl());
                return true;
            }
        }
        return false;
    }
    
    public List<AdminPermission> listPermsByRoleId(int rid) {
        List<Integer> pids = adminRolePermissionService.findAllByRid(rid).stream().map(AdminRolePermission::getPid).collect(Collectors.toList());
        log.info("根据角色id:" + rid + "获取功能权限id:" + pids);
        return adminPermissionDao.findAllById(pids);
    }
    
    /**
     * 根据当前用户获取所有权限，只需要 url 一个字段
     *
     * @param username
     * @return
     */
    public Set<String> listPermissonByURLsByUser(String username) {
        List<Integer> rids = adminRoleService.listRoleByUser(username).stream().map(AdminRole::getId).collect(Collectors.toList());
        
        List<Integer> pids = adminRolePermissionDao.findAllByRidIn(rids).stream().map(AdminRolePermission::getPid).collect(Collectors.toList());
        
        List<AdminPermission> adminPermissions = adminPermissionDao.findAllById(pids);
        
        Set<String> URLs = adminPermissions.stream().map(AdminPermission::getUrl).collect(Collectors.toSet());
        log.info("URLs:" + URLs);
        return URLs;
    }
}
