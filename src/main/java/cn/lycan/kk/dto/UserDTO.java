package cn.lycan.kk.dto;

import cn.lycan.kk.dto.base.OutputConverter;
import cn.lycan.kk.entity.AdminRole;
import cn.lycan.kk.entity.User;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Makkapakka
 * @date 2022-6-8
 * @package_name cn.lycan.kk.dto
 * @description
 */
@Data
@ToString
public class UserDTO implements OutputConverter<UserDTO, User> {
    private int id;
    
    private String username;
    
    private String name;
    
    private String phone;
    
    private String email;
    
    private boolean enabled;
    
    private List<AdminRole> roles;
}
