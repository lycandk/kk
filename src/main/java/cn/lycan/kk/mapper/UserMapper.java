package cn.lycan.kk.mapper;

import cn.lycan.kk.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Makkapakka
 * @date 2022-5-26
 * @package_name cn.lycan.kk.mapper
 * @description
 */
@Repository
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Update("UPDATE user SET ")
    User updateUser(@Param("username") String username, @Param("password") String password);

    @Insert("INSERT INTO user (username,password) VALUES (#{username},#{password})")
    void insertUser(@Param("username") String username, @Param("password") String password);
}
