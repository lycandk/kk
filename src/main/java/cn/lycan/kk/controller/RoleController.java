package cn.lycan.kk.controller;

import cn.lycan.kk.entity.AdminRole;
import cn.lycan.kk.result.Result;
import cn.lycan.kk.result.ResultFactory;
import cn.lycan.kk.service.AdminPermissionService;
import cn.lycan.kk.service.AdminRoleMenuService;
import cn.lycan.kk.service.AdminRolePermissionService;
import cn.lycan.kk.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Makkapakka
 * @date 2022-6-8
 * @package_name cn.lycan.kk.controller
 * @description
 */
@RestController
public class RoleController {
    
    @Autowired
    AdminRoleService adminRoleService;
    
    @Autowired
    AdminPermissionService adminPermissionService;
    
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    
    @Autowired
    AdminRoleMenuService adminRoleMenuService;
    
    @GetMapping("/api/admin/role")
    public Result listRoles() {
        return ResultFactory.buildSuccessResult(adminRoleService.listWithPermsAndMenus());
    }
    
    @GetMapping("api/admin/role/perm")
    public Result listPerms() {
        return ResultFactory.buildSuccessResult(adminPermissionService.list());
    }
    
    @PutMapping("/api/admin/role/status")
    public Result updateRoleStatus(@RequestBody AdminRole requestAdminRole) {
        AdminRole adminRole = adminRoleService.updateRoleStatus(requestAdminRole);
        String message = "用户" + adminRole.getNameZh() + "状态更新成功";
        return ResultFactory.buildSuccessResult(message);
    }
    
    @PutMapping("/api/admin/role")
    public Result editRole(@RequestBody AdminRole requestAdminRole) {
        adminRoleService.addOrUpdate(requestAdminRole);
        adminRolePermissionService.savePermChanges(requestAdminRole.getId(), requestAdminRole.getPerms());
        String message = "修改角色信息成功";
        return ResultFactory.buildSuccessResult(message);
    }
    
    @PutMapping("/api/admin/role/menu")
    public Result updateRoleMenu(@RequestParam int rid, @RequestBody Map<String, List<Integer>> menusIds) {
        adminRoleMenuService.updateRoleMenu(rid, menusIds);
        return ResultFactory.buildSuccessResult("更新成功");
    }
    
    @PostMapping("/api/admin/role")
    public Result addRole(@RequestBody AdminRole requestAdminRole) {
        adminRoleService.editRole(requestAdminRole);
        return ResultFactory.buildSuccessResult("修改用户成功");
    }
}
