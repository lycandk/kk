package cn.lycan.kk.exception;

/**
 * @author Makkapakka
 * @date 2022-6-8
 * @package_name cn.lycan.kk.exception
 * @description BeanUtils exception.
 */
public class BeanUtilsException extends RuntimeException {
    
    public BeanUtilsException(String message) {
        super(message);
    }
    
    public BeanUtilsException(String message, Throwable cause) {
        super(message, cause);
    }
}
