package cn.lycan.kk.service;

/**
 * @author Makkapakka
 * @date 2022-6-7
 * @package_name cn.lycan.kk.service
 * @description
 */

import cn.lycan.kk.dao.AdminRoleMenuDAO;
import cn.lycan.kk.entity.AdminRoleMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AdminRoleMenuService {
    
    @Autowired
    AdminRoleMenuDAO adminRoleMenuDAO;
    
    public List<AdminRoleMenu> findAllByRid(int rid) {
        log.info("通过角色id:" + rid + "找到所有对应的功能菜单id:" + adminRoleMenuDAO.findAllByRid(rid));
        return adminRoleMenuDAO.findAllByRid(rid);
    }
    
    public List<AdminRoleMenu> findAllByRidIn(List<Integer> ridList) {
        log.info("通过角色id列表:" + ridList + "找到所有对应的功能菜单id:" + adminRoleMenuDAO.findAllByRidIn(ridList));
        return adminRoleMenuDAO.findAllByRidIn(ridList);
    }
    
    public void save(AdminRoleMenu adminRoleMenu) {
        adminRoleMenuDAO.save(adminRoleMenu);
    }
    
    @Modifying
    @Transactional
    public void updateRoleMenu(int rid, Map<String, List<Integer>> menuIds) {
        log.warn("根据角色id" + rid + "删除对应的所有菜单");
        adminRoleMenuDAO.deleteAllByRid(rid);
        List<AdminRoleMenu> adminRoleMenus = new ArrayList<>();
        log.info("------遍历开始------");
        for (Integer mid : menuIds.get("menusIds")) {
            log.info("获取到功能菜单id:" + mid);
            AdminRoleMenu adminRoleMenu = new AdminRoleMenu();
            log.info("对:" + adminRoleMenu + "设置角色id:" + rid);
            adminRoleMenu.setRid(rid);
            log.info("对:" + adminRoleMenu + "设置功能菜单id:" + mid);
            adminRoleMenu.setMid(mid);
            adminRoleMenus.add(adminRoleMenu);
            log.info("将:" + adminRoleMenu + "添加进:" + adminRoleMenus);
        }
        log.info("------遍历结束------");
        log.info("adminRoleMenu" + adminRoleMenus);
        adminRoleMenuDAO.saveAll(adminRoleMenus);
    }
}
