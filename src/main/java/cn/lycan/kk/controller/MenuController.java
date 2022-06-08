package cn.lycan.kk.controller;

import cn.lycan.kk.result.Result;
import cn.lycan.kk.result.ResultFactory;
import cn.lycan.kk.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result getMenu() {
        return ResultFactory.buildSuccessResult(adminMenuService.getMenusByCurrentUser());
    }
    
    @GetMapping("/api/admin/role/menu")
    public Result listMenus() {
        return ResultFactory.buildSuccessResult(adminMenuService.getMenusByRoleId(1));
    }
}
