package cn.lycan.kk.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Makkapakka
 * @date 2022-5-26
 * @package_name cn.lycan.kk.entity
 * @description
 */
@Data
@Component
public class User {
    String username;
    String password;
    private Integer id;
}
