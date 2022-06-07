package cn.lycan.kk.service;

/**
 * @author Makkapakka
 * @date 2022-6-7
 * @package_name cn.lycan.kk.service
 * @description
 */

import cn.lycan.kk.dao.AdminRoleMenuDAO;
import cn.lycan.kk.entity.AdminRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
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
        adminRoleMenuDAO.deleteAllByRid(rid);
        List<AdminRoleMenu> adminRoleMenus = new ArrayList<>();
        for (Integer mid : menuIds.get("menuIds")) {
            AdminRoleMenu adminRoleMenu = new AdminRoleMenu();
            adminRoleMenu.setRid(rid);
            adminRoleMenu.setMid(mid);
            adminRoleMenus.add(adminRoleMenu);
        }
        
        adminRoleMenuDAO.saveAll(adminRoleMenus);
    }
}
