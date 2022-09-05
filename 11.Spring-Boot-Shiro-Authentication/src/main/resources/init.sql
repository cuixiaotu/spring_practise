CREATE TABLE "t_user" (
   "id" NUMBER NOT NULL ,
   "username" VARCHAR(20) NOT NULL commit '用户名' ,
   "passwd" VARCHAR(128) NOT NULL commit '密码',
   "create_time" DATE NULL commit '创建时间',
   "status" CHAR(1) NOT NULL commit '状态 1：有效  0：锁定'
);


INSERT INTO "t_user" VALUES ('2', 'test', '7a38c13ec5e9310aed731de58bbc4214', TO_DATE('2017-11-19 17:20:21', 'YYYY-MM-DD HH24:MI:SS'), '0');
INSERT INTO "t_user" VALUES ('1', 'xiaotu', '42ee25d1e43e9f57119a00d0a39e5250', TO_DATE('2017-11-19 10:52:48', 'YYYY-MM-DD HH24:MI:SS'), '1');
