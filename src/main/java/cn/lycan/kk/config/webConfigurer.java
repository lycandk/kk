package cn.lycan.kk.config;

import org.springframework.boot.SpringBootConfiguration;
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
    @Override
    /**
     * 将前端图片URL与后端资源文件夹d:/Software/webpfojects/kittykitty/kk/src/main/resources/workspace/image/对应
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "d:/Software/webpfojects/kittykitty/kk/src/main/resources/workspace/image/");
    }
}
