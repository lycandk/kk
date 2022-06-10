package cn.lycan.kk.config;

import cn.lycan.kk.filter.URLPathMatchingFilter;
import cn.lycan.kk.realm.LCRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Makkapakka
 * @date 2022-6-1
 * @package_name cn.lycan.kk.config
 * @description shiro配置类
 * 决定了一些关键的选择
 */
@Configuration
public class ShiroConfiguration {
    
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("http://localhost:8081/login");
        
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 自定义过滤器设置 1
        Map<String, Filter> customizedFilter = new HashMap<>();
        // 自定义过滤器设置 2，命名，需在设置过滤路径前
        customizedFilter.put("url", getURLPathMatchingFilter());
        // 防鸡贼登录
        filterChainDefinitionMap.put("/api/authentication", "authc");
//        filterChainDefinitionMap.put("/api/admin","authc");
        filterChainDefinitionMap.put("/api/menu", "authc");
        filterChainDefinitionMap.put("/api/admin/**", "authc");
        // 自定义过滤器设置 3，设置过滤路径
        filterChainDefinitionMap.put("/api/admin/**", "url");
        // 自定义过滤器设置 4，启用
        shiroFilterFactoryBean.setFilters(customizedFilter);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    
    /**
     * 这里不能使用 @Bean
     *
     * @return
     */
    public URLPathMatchingFilter getURLPathMatchingFilter() {
        return new URLPathMatchingFilter();
    }
    
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }
    
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(getLCRealm());
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        return defaultWebSecurityManager;
    }
    
    
    @Bean
    public LCRealm getLCRealm() {
        LCRealm lcRealm = new LCRealm();
        lcRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return lcRealm;
    }
    
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }
    
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("MAKKAPAKKA_LYCAN".getBytes());
        return cookieRememberMeManager;
    }
    
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }
    
}
