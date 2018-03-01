
-- ----------------------------
-- Table structure for `TIME_TASK`
-- ----------------------------

DROP TABLE IF EXISTS `TIME_TASK`;

CREATE TABLE `TIME_TASK` (
`ID`  char(50) NOT NULL COMMENT '主键' ,
`TASK_NAME`  varchar(200) NULL COMMENT '任务名称' ,
`CRON_SCHEDULE_EXPRESSION`  varchar(200) NULL COMMENT '定时任务的时间的表达式' ,
`TIME_TASK_SWITCH` tinyint(1)  NULL COMMENT '定时任务开关(0关闭，1 开启)' ,
`TASK_CLASS_NAME`  varchar(200) NULL COMMENT '定时任务需要执行的类' ,
`TASK_CLASS_METHOD`  varchar(200) NULL COMMENT '定时任务需要执行的方法' ,
`DESCRIPTION`  varchar(2000) NULL COMMENT '定时任务的描述' ,
PRIMARY KEY (`ID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='定时任务调度信息';

insert into TIME_TASK (ID, TASK_NAME, CRON_SCHEDULE_EXPRESSION, TIME_TASK_SWITCH, TASK_CLASS_NAME, TASK_CLASS_METHOD, DESCRIPTION) values ('1','testTask','0/5 * * * * ?',1,'testService','run','测试定时任务');