create table user
(
    id       int unsigned auto_increment
        primary key,
    username varchar(255) null,
    password varchar(255) null,
    salt     varchar(255) null,
    name     varchar(255) null,
    phone    varchar(255) null,
    email    varchar(255) null,
    enabled  tinyint(1) null
) charset = utf8;

INSERT INTO kittykitty.user (id, username, password, salt, name, phone, email, enabled)
VALUES (14, 'admin', '388f019545056c034e2d5e06addc50ec', '3yt0CPXelgeg1XnVnD5s4A==', '管理员', '12312312312',
        'Makkapakka@dk.com', 1);
INSERT INTO kittykitty.user (id, username, password, salt, name, phone, email, enabled)
VALUES (15, 'test', 'a37efb7d16c4705198adca9d24c8182d', 'saHZegzb3nG926K5by3Xpw==', '测试', '12312312312',
        'lycan@check.com', 1);
INSERT INTO kittykitty.user (id, username, password, salt, name, phone, email, enabled)
VALUES (16, 'editor', 'c58951d09c635697c5b65f8206ec0255', 'Gcr7A/jixXNtjRXO2wO/ug==', '编辑', '132513515',
        '16651515@ajhfjkdskl.com', 1);
INSERT INTO kittykitty.user (id, username, password, salt, name, phone, email, enabled)
VALUES (17, 'A', 'b80ec00dbb6de40b2078d0fb7f723c9b', 'FCmbRpEjMVzQ/60oAE4GpA==', '猫咪信息维护', '1234567897',
        '1981895116@ksaldj.com', 1);
INSERT INTO kittykitty.user (id, username, password, salt, name, phone, email, enabled)
VALUES (18, 'B', 'cf004b24bd745c8ecc210cd520602dd2', 'Psv0bSCemCojCQwn3hWdUw==', '用户管理员', '1651561565',
        '1941999@klsdjafk.com', 1);
INSERT INTO kittykitty.user (id, username, password, salt, name, phone, email, enabled)
VALUES (20, 'C', '6a30ce54f6626a506f5373e582f9bfac', 'tvCu57fEsFkQEYyV3OJtvQ==', '系统配置员', '5616516515',
        '5615156161@asijf.com', 1);
