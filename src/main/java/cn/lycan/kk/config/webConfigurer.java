package cn.lycan.kk.config;

import cn.lycan.kk.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Makkapakka
 * @date 2022-5-31
 * @package_name cn.lycan.kk.config
 * @description
 */
@SpringBootConfiguration
public class webConfigurer implements WebMvcConfigurer {
    
    /**
     * 允许跨域的 cookie ：allowCredentials(true)
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:8081")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*");
    }
    
    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/api/login").
                excludePathPatterns("/api/logout").excludePathPatterns("/api/register");
    }
    
    @Override
    /**
     * 将前端图片URL与后端资源文件夹d:/Software/webpfojects/kittykitty/kk/src/main/resources/workspace/image/对应
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "d:/Software/webpfojects/kittykitty/kk/src/main/resources/workspace/image/");
    }
}
