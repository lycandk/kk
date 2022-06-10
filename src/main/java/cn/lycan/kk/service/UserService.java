package cn.lycan.kk.service;

import cn.lycan.kk.dao.UserDao;
import cn.lycan.kk.dto.UserDTO;
import cn.lycan.kk.entity.AdminRole;
import cn.lycan.kk.entity.User;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.stream.Collectors;

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
    UserDao userDao;
    
    @Lazy
    @Autowired
    AdminRoleService adminRoleService;
    
    @Autowired
    AdminUserRoleService adminUserRoleService;
    
    public List<UserDTO> list() {
        List<User> users = userDao.findAll();
        // Find all roles in DB to enable JPA persistence context.
        // List<AdminRole> allRoles = adminRoleService.findAll();
        
        List<UserDTO> userDTOS = users
                .stream().map(user -> (UserDTO) new UserDTO().convertFrom(user)).collect(Collectors.toList());
        
        userDTOS.forEach(u -> {
            // 给查询的用户带上角色信息
            List<AdminRole> roles = adminRoleService.listRoleByUser(u.getUsername());
            u.setRoles(roles);
        });
        
        log.info("获取userDTOS:" + userDTOS);
        return userDTOS;
    }
    
    public boolean isExist(String username) {
        User user = userDao.findByUsername(username);
        return null != user;
    }
    
    public User getByName(String username) {
        log.info("执行getByName()方法，根据用户名获取用户：" + username);
        return userDao.findByUsername(username);
    }
    
    public User get(String username, String password) {
        log.info("根据用户名以及密码获取用户：" + username + " , " + password);
        return userDao.getByUsernameAndPassword(username, password);
    }
    
    public int add(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String name = user.getName();
        String phone = user.getPhone();
        String email = user.getEmail();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        phone = HtmlUtils.htmlEscape(phone);
        user.setPhone(phone);
        email = HtmlUtils.htmlEscape(email);
        user.setEmail(email);
        user.setEnabled(true);
    
        if (username.equals("") || password.equals("")) {
            log.error("用户名或者密码为空");
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
        userDao.save(user);
        log.info("插入或更新用户：" + user);
    
        return 1;
    }
    
    public void updateUserStatus(User user) {
        User userInDB = userDao.findByUsername(user.getUsername());
        userInDB.setEnabled(user.isEnabled());
        log.info("对用户:" + userInDB + "使能：" + user.isEnabled());
        userDao.save(userInDB);
    }
    
    
    public void editUser(User user) {
        User userInDB = userDao.findByUsername(user.getUsername());
        userInDB.setName(user.getName());
        userInDB.setPhone(user.getPhone());
        userInDB.setEmail(user.getEmail());
        userDao.save(userInDB);
        adminUserRoleService.saveRoleChanges(userInDB.getId(), user.getRoles());
        log.info("存入用户：" + userInDB + "\n为id：" + userInDB.getId() + "的用户设置角色：" + user.getRoles());
    }
    
    
    public User resetPassword(User user) {
        User userInDB = userDao.findByUsername(user.getUsername());
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        userInDB.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", "123", salt, times).toString();
        userInDB.setPassword(encodedPassword);
        log.info("存入用户：" + userInDB);
        return userDao.save(userInDB);
    }
    
    public void deleteById(int id) {
        userDao.deleteById(id);
    }
}
