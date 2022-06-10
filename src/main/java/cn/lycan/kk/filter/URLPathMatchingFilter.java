package cn.lycan.kk.filter;

import cn.lycan.kk.service.AdminPermissionService;
import cn.lycan.kk.utils.SpringContextUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author Makkapakka
 * @date 2022-6-10
 * @package_name cn.lycan.kk.filter
 * @description PathMatchingFilter 是 Shiro 提供的路径过滤器，我们可以通过继承它来编写过滤放行条件，即判断是否具有相应权限。判断的逻辑为：
 * <p>
 * 首先，判断当前会话对应的用户是否登录，如果未登录直接 false
 * 第二步，判断访问的接口是否有对应的权限，如果没有视为不需要权限即可访问，直接 true
 * 如果需要权限，查询出当前用户对应的所有权限，遍历并与需要访问的接口进行比对，如果存在相应权限则 true，否则 false
 */
@Log4j2
public class URLPathMatchingFilter extends PathMatchingFilter {
    
    @Autowired
    AdminPermissionService adminPermissionService;
    
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        
        //放行options请求
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }
        
        /**
         * 在 Shiro 的配置文件中，我们不能把 URLPathMatchingFilter 用 @Bean 被 Spring 管理起来。
         * 原因是 Shiro 存在 bug, 这个也是过滤器，ShiroFilterFactoryBean 也是过滤器，当他们都出现的时候，
         * 默认的什么 anno,authc 过滤器就失效了。所以不能把他声明为 @Bean。
         * 因此，无法在 URLPathMatchingFilter 中使用 @Autowired 注入 AdminPermissionService 类，
         * 所以需要借助一个工具类利用 Spring 应用上下文获取 AdminPermissionService 的实例。
         */
        if (null == adminPermissionService) {
            adminPermissionService = SpringContextUtils.getContext().getBean(AdminPermissionService.class);
        }
        
        String requestAPI = getPathWithinApplication(request);
        log.warn("请求的API路径为:" + requestAPI);
        Subject subject = SecurityUtils.getSubject();
        log.warn("用户授权情况:" + subject.isAuthenticated());
        if (!subject.isAuthenticated()) {
            log.info("未登录用户尝试访问需要登录的接口");
            return false;
        }
        
        // 判断访问接口是否需要过滤（数据库中是否有对应信息）
        boolean needFilter = adminPermissionService.needFilter(requestAPI);
        if (!needFilter) {
            log.info("接口：" + requestAPI + "无需权限");
            return true;
        } else {
            // 判断当前用户是否有相应权限
            boolean hasPermission = false;
            String username = subject.getPrincipal().toString();
            log.warn("获取到shiro中用户:" + username);
            Set<String> permissionAPIs = adminPermissionService.listPermissonByURLsByUser(username);
            log.warn("获取到该用户允许调用的接口:" + permissionAPIs);
            for (String api : permissionAPIs) {
                // 匹配前缀
                if (requestAPI.startsWith(api)) {
                    hasPermission = true;
                    log.warn("该用户是否允许调用:" + hasPermission);
                    break;
                }
            }
            if (hasPermission) {
                log.warn("用户：" + username + "访问了：" + requestAPI + "接口");
                return true;
            } else {
                log.warn("用户：" + username + "访问了没有权限的接口：" + requestAPI);
                return false;
            }
        }
    }
}
