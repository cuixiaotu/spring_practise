CREATE TABLE t_user (
   `id` int NOT NULL PRIMARY key,
   `username` VARCHAR(20) NOT NULL COMMENT "用户名",
   `passwd` VARCHAR(128) NOT NULL COMMENT "密码",
   `create_time` DATE NULL COMMENT "创建时间",
   `status` CHAR(1) NOT NULL COMMENT "状态 1：有效  0：锁定"
);


INSERT INTO t_user VALUES ('2', 'test', 'e10adc3949ba59abbe56e057f20f883e', '2022-09-07 17:20:21', '0');
INSERT INTO t_user VALUES ('1', 'xiaotu', 'e10adc3949ba59abbe56e057f20f883e', '2022-09-07 10:52:48', '1');
