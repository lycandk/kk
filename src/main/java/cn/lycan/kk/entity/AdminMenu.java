package cn.lycan.kk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @author Makkapakka
 * @date 2022-6-7
 * @package_name cn.lycan.kk.entity
 * @description
 */
@Data
@Entity
@Table(name = "admin_menu")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminMenu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    /**
     * Menu access path.
     */
    private String path;
    
    /**
     * Menu name.
     */
    private String name;
    
    /**
     * Menu name in Chinese.
     */
    private String nameZh;
    
    /**
     * Menu icon class(use element-ui icons).
     */
    private String iconCls;
    
    /**
     * Front-end component name corresponding to menu.
     */
    private String component;
    
    /**
     * Parent menu.
     * 父节点ID
     */
    private int parentId;
    
    /**
     * Transient property for storing children menus.
     * 数据库不存在的属性，但需要做为临时变量，用于存储子节点。
     */
    @Transient
    private List<AdminMenu> children;
}
