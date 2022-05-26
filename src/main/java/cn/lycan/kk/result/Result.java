package cn.lycan.kk.result;

import lombok.Data;

/**
 * @author Makkapakka
 * @date 2022-5-26
 * @package_name cn.lycan.kk.result
 * @description
 */
@Data
public class Result {
    int code;

    public Result(int code) {
        this.code = code;
    }
}
