package cn.lycan.kk.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Makkapakka
 * @date 2022-5-27
 * @package_name cn.lycan.kk.entity
 * @description 品种分类实体类
 */
@Data
@Component
public class Variety {
    int id;
    String name;
}
