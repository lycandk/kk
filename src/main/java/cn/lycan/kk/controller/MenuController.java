package cn.lycan.kk.controller;

import cn.lycan.kk.entity.AdminMenu;
import cn.lycan.kk.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Makkapakka
 * @date 2022-6-7
 * @package_name cn.lycan.kk.controller
 * @description
 */
@RestController
public class MenuController {
    
    @Autowired
    AdminMenuService adminMenuService;
    
    @GetMapping("/api/menu")
    public List<AdminMenu> getMenu() {
        return adminMenuService.getMenusByCurrentUser();
    }
}
