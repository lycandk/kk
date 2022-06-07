package cn.lycan.kk.service;

import cn.lycan.kk.entity.User;
import cn.lycan.kk.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

/**
 * @author Makkapakka
 * @date 2022-5-26
 * @package_name cn.lycan.kk.service
 * @description
 */
@Service
@Log4j2
@Transactional(rollbackFor = Exception.class)
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    User user;
    
    public boolean isExist(String username) {
        User user = getByName(username);
        return null != user;
    }
    
    public User getByName(String username) {
        log.info("根据用户名获取用户：" + username);
        return userMapper.findByUsername(username);
    }
    
    public User get(String username, String password) {
        log.info("根据用户名以及密码获取用户：" + username + " , " + password);
        return userMapper.getByUsernameAndPassword(username, password);
    }
    
    public int add(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        
        if (username.equals("") || password.equals("")) {
            return 0;
        }
        
        boolean exist = isExist(username);
        if (exist) {
            log.warn("已经存在用户:" + username);
            return 2;
        }
        
        //生成随机16位盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        //hash迭代次数
        int times = 2;
        //MD5加密密码
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        //存储盐和加密后密码
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        //插入用户
        // 上面exist已经判断是否存在用户并返回状态码，此处无需再次判断是否存在，add方法只用作注册使用
        userMapper.insertUser(username, encodedPassword, salt);
        log.info("插入用户：" + user.getUsername() + " " + user.getPassword());
        
        return 1;
    }
    
    
}
