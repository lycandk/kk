package cn.lycan.kk.dao;

import cn.lycan.kk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Makkapakka
 * @date 2022-6-8
 * @package_name cn.lycan.kk.dao
 * @description
 */
public interface UserDao extends JpaRepository<User, Integer> {
    /**
     * 通过用户名查询用户
     *
     * @param username
     * @return
     */
    User findByUsername(String username);
    
    /**
     * 通过用户名和密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    User getByUsernameAndPassword(String username, String password);
}
