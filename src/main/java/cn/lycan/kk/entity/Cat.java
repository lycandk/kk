package cn.lycan.kk.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Makkapakka
 * @date 2022-5-27
 * @package_name cn.lycan.kk.entity
 * @description 猫咪实体类
 */
@Data
@Component
public class Cat {
    int id;
    String cover;
    String nickname;
    String variety;
    String scientificname;
    String latinname;
    String placeoforigin;
    String color;
    String birthdate;
    String abs;
    private Variety varieties;


}
