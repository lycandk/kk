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
        return adminRoleMenuDAO.findAllByRid(rid);
    }
    
    public List<AdminRoleMenu> findAllByRidIn(List<Integer> ridList) {
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
        for (Integer mid : menuIds.get("menusIds")) {
            AdminRoleMenu adminRoleMenu = new AdminRoleMenu();
            adminRoleMenu.setRid(rid);
            adminRoleMenu.setMid(mid);
            adminRoleMenus.add(adminRoleMenu);
        }
        log.info("对id为：" + rid + "的角色添加菜单：" + adminRoleMenus.toString());
        adminRoleMenuDAO.saveAll(adminRoleMenus);
    }
}
