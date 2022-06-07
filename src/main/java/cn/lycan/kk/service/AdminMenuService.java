package cn.lycan.kk.service;

import cn.lycan.kk.dao.AdminMenuDAO;
import cn.lycan.kk.entity.AdminMenu;
import cn.lycan.kk.entity.AdminRoleMenu;
import cn.lycan.kk.entity.AdminUserRole;
import cn.lycan.kk.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Makkapakka
 * @date 2022-6-7
 * @package_name cn.lycan.kk.service
 * @description 根据用户查询出对应菜单的步骤是：
 * 利用 shiro 获取当前登录用户的 id
 * 根据用户 id 查询出该用户对应所有角色的 id
 * 根据这些角色的 id，查询出所有可访问的菜单项
 * 根据 parentId 把子菜单放进父菜单对象中，整理返回有正确层级关系的菜单数据
 */
@Service
public class AdminMenuService {
    
    @Autowired
    AdminMenuDAO adminMenuDAO;
    
    @Autowired
    UserService userService;
    
    @Autowired
    AdminUserRoleService adminUserRoleService;
    
    @Autowired
    AdminRoleMenuService adminRoleMenuService;
    
    public List<AdminMenu> getAllByParentId(int parentId) {
        return adminMenuDAO.findAllByParentId(parentId);
    }
    
    /**
     * 通过当前用户查找对应的所有菜单
     *
     * @return
     */
    public List<AdminMenu> getMenusByCurrentUser() {
        
        //Get Current User in DB
        String userName = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getByName(userName);
        
        //Get roles' ids of current user
        //使用了 stream 来简化列表的处理，包括使用 map() 提取集合中的某一属性
        List<Integer> rids = adminUserRoleService.findAllByUid(user.getId()).stream().map(AdminUserRole::getRid).collect(Collectors.toList());
        
        //Get Menu items of these roles
        //通过 distinct() 对查询出的菜单项进行了去重操作，避免多角色情况下有冗余的菜单
        List<Integer> mids = adminRoleMenuService.findAllByRidIn(rids).stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDAO.findAllById(mids).stream().distinct().collect(Collectors.toList());
        
        //adjust the menu structure
        //调整菜单结构
        handleMenus(menus);
        return menus;
        
    }
    
    public List<AdminMenu> getMenusByRoleId(int rid) {
        List<Integer> menuIds = adminRoleMenuService.findAllByRid(rid).stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDAO.findAllById(menuIds);
        
        handleMenus(menus);
        return menus;
    }
    
    /**
     * 调整菜单结构的方法
     *
     * @param menus 把查询出来的菜单数据列表整合成具有层级关系的菜单树:
     *              遍历菜单项，根据每一项的 id 查询该项出所有的子项，并放进 children 属性
     *              剔除掉所有子项，只保留第一层的父项。比如 c 是 b 的子项，b 是 a 的子项，我们最后只要保留 a 就行，因为 a 包含了 b 和 c
     */
    private void handleMenus(List<AdminMenu> menus) {
        menus.forEach(m -> {
            List<AdminMenu> children = getAllByParentId(m.getId());
            m.setChildren(children);
        });
        
        menus.removeIf(m -> m.getParentId() != 0);
    }
}
