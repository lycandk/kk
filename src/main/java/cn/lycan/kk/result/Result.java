package cn.lycan.kk.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Makkapakka
 * @date 2022-5-26
 * @package_name cn.lycan.kk.result
 * @description 封装结果实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    int code;
    String message;
    Object result;
    
    public Result(int code) {
        this.code = code;
    }
}
