package cn.lycan.kk.service;

import cn.lycan.kk.entity.User;
import cn.lycan.kk.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void add(User user) {
        int id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();
        if (null == userMapper.getByUsernameAndPassword(username, password)) {
            userMapper.insertUser(username, password);
            log.info("插入用户：" + user);

        } else {
            userMapper.updateUser(username, password);
            log.info("更新用户：" + user);
        }
    }


}
