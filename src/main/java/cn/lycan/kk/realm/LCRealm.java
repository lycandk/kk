package cn.lycan.kk.realm;

import cn.lycan.kk.entity.User;
import cn.lycan.kk.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Makkapakka
 * @date 2022-6-1
 * @package_name cn.lycan.kk.realm
 * @description
 * Shiro配置的顺序如下：
 * 创建 Realm 并重写获取认证与授权信息的方法
 * 创建配置类，包括创建并配置 SecurityManager 等。也可以通过 web.xml 启用 Shiro 过滤器，
 * 再通过 shiro.ini 文件进行配置，不过我们并没有 web.xml。既然用了 Spring Boot，就尽情地使用 Java 类吧
 */
public class LCRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    /**
     * 重写获取授权信息方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        return s;
    }

    /**
     * 获取认证信息，根据token中的用户名获取密码，盐并返回
     * SimpleAuthenticationInfo 中的 salt 非得存储成 byte[],因此之前的salt是String，现在需要重新转换成Byte数组
     * 这里的 byte[] 并不是我们当初随机生成的那个，而是随机生成的 byte[] 按 base64 编码成 String 又按 UTF-8 编码成的 byte[]。
     * 原因在于：Shiro 提供的 hash 算法本质上是由 Java 提供的 MessageDigest 类实现，其输入和输出都是 byte[]
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
        //从token中获取用户名信息
        String username = token.getPrincipal().toString();
        //从数据库中按照用户名获取用户实体
        User user = userService.getByName(username);
        //获取user实体在数据库中的密码
        String passwordInDB = user.getPassword();
        //获取user实体在数据库中的盐
        String salt = user.getSalt();
        //构造认证信息并返回
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,passwordInDB, ByteSource.Util.bytes(salt),getName());
        return authenticationInfo;
    }


}
