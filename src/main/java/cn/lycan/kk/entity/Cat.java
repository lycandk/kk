package cn.lycan.kk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Makkapakka
 * @date 2022-5-27
 * @package_name cn.lycan.kk.entity
 * @description 猫咪实体类
 */
@Data
@Entity
@Table(name = "cat")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    private String cover;
    private String nickname;
    private String variety;
    private String scientificname;
    private String latinname;
    private String placeoforigin;
    private String color;
    private String birthdate;
    private String abs;
    
    @ManyToOne
    @JoinColumn(name = "vid")
    private Variety varieties;
    
    
}
