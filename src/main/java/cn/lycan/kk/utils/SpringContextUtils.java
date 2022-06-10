package cn.lycan.kk.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Makkapakka
 * @date 2022-6-10
 * @package_name cn.lycan.kk.utils
 * @description
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
    
    private static ApplicationContext context;
    
    public static ApplicationContext getContext() {
        return context;
    }
    
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringContextUtils.context = context;
    }
}
