package cn.lycan.kk.exception;

import cn.lycan.kk.result.Result;
import cn.lycan.kk.result.ResultFactory;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Makkapakka
 * @date 2022-6-17
 * @package_name cn.lycan.kk.exception
 * @description
 */
@ControllerAdvice
@ResponseBody
public class DefaultExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e) {
        String message = null;
        if (e instanceof IllegalAccessException) {
            message = "传入了错误的参数";
        }
        
        if (e instanceof MethodArgumentNotValidException) {
            message = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
        }
        
        if (e instanceof UnauthorizedException) {
            message = "认证失败";
        }
        
        return ResultFactory.buildSuccessResult(message);
    }
}
