create table admin_user_role
(
    id  int auto_increment
        primary key,
    uid int null,
    rid int null
) charset = utf8;

create index fk_operator_role_operator_1
    on admin_user_role (uid);

create index fk_operator_role_role_1
    on admin_user_role (rid);

INSERT INTO kittykitty.admin_user_role (id, uid, rid)
VALUES (64, 14, 1);
INSERT INTO kittykitty.admin_user_role (id, uid, rid)
VALUES (67, 15, 9);
INSERT INTO kittykitty.admin_user_role (id, uid, rid)
VALUES (72, 18, 10);
INSERT INTO kittykitty.admin_user_role (id, uid, rid)
VALUES (73, 20, 12);
INSERT INTO kittykitty.admin_user_role (id, uid, rid)
VALUES (74, 17, 11);
INSERT INTO kittykitty.admin_user_role (id, uid, rid)
VALUES (75, 16, 2);
