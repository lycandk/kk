package cn.lycan.kk.controller;

import cn.lycan.kk.entity.User;
import cn.lycan.kk.result.Result;
import cn.lycan.kk.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.util.Objects;
import java.util.logging.LogManager;

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
    public Result login(@RequestBody User user){
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = user.getUsername();
        username = HtmlUtils.htmlEscape(username);


        if(null==userService.get(username,user.getPassword())){
            String message = "用户名密码错误！";
            log.error(message);
            return new Result(400);
        }
        else {
            log.info("登陆成功！");
            return new Result(200);
        }
    }
}
