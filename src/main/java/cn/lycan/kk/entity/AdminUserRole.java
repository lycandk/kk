package cn.lycan.kk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Makkapakka
 * @date 2022-6-7
 * @package_name cn.lycan.kk.entity
 * @description
 */
@Entity
@Data
@Table(name = "admin_user_role")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    /**
     * User id.
     */
    private int uid;
    
    /**
     * Role id.
     */
    private int rid;
}
