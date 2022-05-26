package cn.lycan.kk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
    private Integer id;
    String username;
    String password;
}
