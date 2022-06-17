create table admin_menu
(
    id        int auto_increment
        primary key,
    path      varchar(64) null,
    name      varchar(64) null,
    name_zh   varchar(64) null,
    icon_cls  varchar(64) null,
    component varchar(64) null,
    parent_id int null
);

INSERT INTO kittykitty.admin_menu (id, path, name, name_zh, icon_cls, component, parent_id)
VALUES (1, '/admin', 'AdminIndex', '首页', 'el-icon-s-home', 'AdminIndex', 0);
INSERT INTO kittykitty.admin_menu (id, path, name, name_zh, icon_cls, component, parent_id)
VALUES (2, '/admin/dashboard', 'DashboardAdmin', '运行情况', null, 'dashboard/admin/index', 1);
INSERT INTO kittykitty.admin_menu (id, path, name, name_zh, icon_cls, component, parent_id)
VALUES (3, '/admin', 'User', '用户管理', 'el-icon-user', 'AdminIndex', 0);
INSERT INTO kittykitty.admin_menu (id, path, name, name_zh, icon_cls, component, parent_id)
VALUES (4, '/admin', 'Content', '内容管理', 'el-icon-tickets', 'AdminIndex', 0);
INSERT INTO kittykitty.admin_menu (id, path, name, name_zh, icon_cls, component, parent_id)
VALUES (5, '/admin', 'System', '系统配置', 'el-icon-s-tools', 'AdminIndex', 0);
INSERT INTO kittykitty.admin_menu (id, path, name, name_zh, icon_cls, component, parent_id)
VALUES (6, '/admin/user/profile', 'Profile', '用户信息', null, 'user/UserProfile', 3);
INSERT INTO kittykitty.admin_menu (id, path, name, name_zh, icon_cls, component, parent_id)
VALUES (7, '/admin/user/role', 'Role', '角色配置', null, 'user/Role', 3);
INSERT INTO kittykitty.admin_menu (id, path, name, name_zh, icon_cls, component, parent_id)
VALUES (8, '/admin/content/cat', 'CatManagement', '猫咪管理', null, 'content/CatManagement', 4);
INSERT INTO kittykitty.admin_menu (id, path, name, name_zh, icon_cls, component, parent_id)
VALUES (9, '/admin/content/banner', 'BannerManagement', '广告管理', null, 'content/BannerManagement', 4);
INSERT INTO kittykitty.admin_menu (id, path, name, name_zh, icon_cls, component, parent_id)
VALUES (10, '/admin/content/article', 'ArticleManagement', '文章管理', null, 'content/ArticleManagement', 4);
