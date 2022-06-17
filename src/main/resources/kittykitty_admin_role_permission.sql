create table admin_role_permission
(
    id  int auto_increment
        primary key,
    rid int null,
    pid int null
) charset = utf8;

create index fk_role_permission_permission_1
    on admin_role_permission (pid);

create index fk_role_permission_role_1
    on admin_role_permission (rid);

INSERT INTO kittykitty.admin_role_permission (id, rid, pid)
VALUES (83, 5, 3);
INSERT INTO kittykitty.admin_role_permission (id, rid, pid)
VALUES (108, 1, 1);
INSERT INTO kittykitty.admin_role_permission (id, rid, pid)
VALUES (109, 1, 2);
INSERT INTO kittykitty.admin_role_permission (id, rid, pid)
VALUES (110, 1, 3);
INSERT INTO kittykitty.admin_role_permission (id, rid, pid)
VALUES (139, 2, 3);
INSERT INTO kittykitty.admin_role_permission (id, rid, pid)
VALUES (173, 10, 2);
INSERT INTO kittykitty.admin_role_permission (id, rid, pid)
VALUES (174, 11, 3);
INSERT INTO kittykitty.admin_role_permission (id, rid, pid)
VALUES (177, 9, 1);
