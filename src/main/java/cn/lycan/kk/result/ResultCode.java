package cn.lycan.kk.result;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Makkapakka
 * @date 2022-6-1
 * @package_name cn.lycan.kk.result
 * @description 状态码枚举
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode {
    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);
    public int code;
    
}
