package cn.lycan.kk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author Makkapakka
 * @date 2022-6-7
 * @package_name cn.lycan.kk.entity
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "admin_role")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    /**
     * Role name.
     */
    private String name;
    
    /**
     * Role name in Chinese.
     */
    @Column(name = "name_zh")
    private String nameZh;
    
    /**
     * Role status.
     */
    private boolean enabled;
    
    
    /**
     * Transient property for storing permissions owned by current role.
     * 数据库不存在的属性，但需要做为临时变量
     */
    @Transient
    private List<AdminPermission> perms = new java.util.ArrayList<>();
    
    /**
     * Transient property for storing menus owned by current role.
     * 数据库不存在的属性，但需要做为临时变量
     */
    @Transient
    private List<AdminMenu> menus;
}
