package cn.lycan.kk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Makkapakka
 * @date 2022-5-27
 * @package_name cn.lycan.kk.entity
 * @description 品种分类实体类
 */
@Data
@Entity
@Table(name = "variety")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Variety {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    private String name;
}
