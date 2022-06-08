package cn.lycan.kk.controller;

import cn.lycan.kk.entity.User;
import cn.lycan.kk.result.Result;
import cn.lycan.kk.result.ResultFactory;
import cn.lycan.kk.service.AdminUserRoleService;
import cn.lycan.kk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Makkapakka
 * @date 2022-6-8
 * @package_name cn.lycan.kk.controller
 * @description
 */
@RestController
public class UserController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    AdminUserRoleService adminUserRoleService;
    
    @GetMapping("/api/admin/user")
    public Result listUsers() {
        return ResultFactory.buildSuccessResult(userService.list());
    }
    
    @PutMapping("/api/admin/user/status")
    public Result updateUserStatus(@RequestBody @Valid User requestUser) {
        userService.updateUserStatus(requestUser);
        return ResultFactory.buildSuccessResult("用户状态更新成功");
    }
}
