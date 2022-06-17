create table admin_permission
(
    id    int auto_increment
        primary key,
    name  varchar(100) null,
    desc_ varchar(100) null,
    url   varchar(100) null
) charset = utf8;

INSERT INTO kittykitty.admin_permission (id, name, desc_, url)
VALUES (1, 'users_management', '用户管理', '/api/admin/user');
INSERT INTO kittykitty.admin_permission (id, name, desc_, url)
VALUES (2, 'roles_management', '角色管理', '/api/admin/role');
INSERT INTO kittykitty.admin_permission (id, name, desc_, url)
VALUES (3, 'content_management', '内容管理', '/api/admin/content');
