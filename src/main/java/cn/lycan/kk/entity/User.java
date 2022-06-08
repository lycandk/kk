package cn.lycan.kk.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Makkapakka
 * @date 2022-5-26
 * @package_name cn.lycan.kk.entity
 * @description 需要引入shiro安全框架因此加上salt字段
 */
@Data
@Component
public class User {
    private String username;
    private String name;
    private String password;
    private String phone;
    private String email;
    private String salt;
    private Integer id;
    private boolean enabled;
}
