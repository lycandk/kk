package cn.lycan.kk.controller;

import cn.lycan.kk.entity.User;
import cn.lycan.kk.result.Result;
import cn.lycan.kk.result.ResultFactory;
import cn.lycan.kk.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

/**
 * @author Makkapakka
 * @date 2022-5-26
 * @package_name cn.lycan.kk.controller
 * @description
 */
@RestController
@CrossOrigin
@Log4j2
public class LoginController {
    
    @Autowired
    UserService userService;
    
    @PostMapping("api/login")
    public Result login(@RequestBody User user) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = user.getUsername();
        username = HtmlUtils.htmlEscape(username);
        Subject subject = SecurityUtils.getSubject();
        
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, user.getPassword());
        usernamePasswordToken.setRememberMe(true);
        try {
            subject.login(usernamePasswordToken);
            return ResultFactory.buildSuccessResult(username);
        } catch (AuthenticationException e) {
            String message = "账户或密码错误！";
            return ResultFactory.buildFailureResult(message);
        }
    }
    
    @PostMapping("api/register")
    public Result register(@RequestBody User user) {
//        String username = user.getUsername();
//        String password = user.getPassword();
//        username = HtmlUtils.htmlEscape(username);
//        user.setUsername(username);
//
//        /**
//         * 判断是否存在用户
//         */
//        boolean exist = userService.isExist(username);
//        if(exist){
//            String message = "用户名已占用！";
//            return ResultFactory.buildFailureResult(message);
//        }
        //将多余的业务逻辑移到UserService中，此处只做switch结果分支以及返回结果封装给前端。
        int status = userService.add(user);
        switch (status) {
            case 0:
                return ResultFactory.buildFailureResult("用户名和密码不能为空");
            case 1:
                return ResultFactory.buildSuccessResult(user);
            case 2:
                return ResultFactory.buildFailureResult("用户名已存在");
        }
        return ResultFactory.buildFailureResult("未知错误！");
//        //生成随机盐，16位,调用的是shiro的生成随机数方法，先生成了随机的 byte 数组，又转换成了字符串类型的 base64 编码并返回。
//        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
//        //hash迭代次数
//        int times = 2;
//        //hash后的密码
//        String encodedPassword = new SimpleHash("md5",password,salt,times).toString();
//        //储存用户信息
//        user.setSalt(salt);
//        user.setPassword(encodedPassword);
//        //调用add方法添加用户
//        userService.add(user);
//
//        //返回结果封装
//        return ResultFactory.buildSuccessResult(user);
    }
    
    @GetMapping("api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        //logout方法由DelegatingSubject实现Subject接口后实现该方法，
        // 该方法会清除 session、principals，并把 authenticated 设置为 false。
        subject.logout();
        String message = "成功登出";
        return ResultFactory.buildSuccessResult(message);
    }
    
    /**
     * 访问每个页面前都向后端发送一个请求，目的是经由拦截器验证服务器端的登录状态，暂时留空
     *
     * @return
     */
    @GetMapping("api/authentication")
    public String authentication() {
        return "身份认证成功";
    }
    
}
