package cn.lycan.kk.service;

import cn.lycan.kk.dao.AdminRolePermissionDao;
import cn.lycan.kk.entity.AdminPermission;
import cn.lycan.kk.entity.AdminRolePermission;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AdminRolePermissionService {
    @Autowired
    AdminRolePermissionDao adminRolePermissionDao;
    
    public List<AdminRolePermission> findAllByRid(int rid) {
        log.info("根据角色id:" + rid + "获取对应的所有功能权限:" + adminRolePermissionDao.findAllByRid(rid));
        return adminRolePermissionDao.findAllByRid(rid);
    }
    
    @Transactional
    public void savePermChanges(int rid, List<AdminPermission> perms) {
        adminRolePermissionDao.deleteAllByRid(rid);
        List<AdminRolePermission> adminRolePermissions = new ArrayList<>();
        log.info("------遍历开始------");
        perms.forEach(p -> {
            AdminRolePermission adminRolePermission = new AdminRolePermission();
            adminRolePermission.setRid(rid);
            adminRolePermission.setPid(p.getId());
            adminRolePermissions.add(adminRolePermission);
            log.info("将" + adminRolePermission + "添加进adminRolePermissions列表");
        });
        log.info("------遍历结束------");
        log.info("汇总adminRolePermissions列表:" + adminRolePermissions);
        adminRolePermissionDao.saveAll(adminRolePermissions);
    }
}
