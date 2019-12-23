/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : mysqldb

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-12-23 17:57:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('MyScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('MyScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('MyScheduler', 'server091574236683260', '1574240722857', '15000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `DEPT_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `PARENT_ID` bigint(20) NOT NULL COMMENT '上级部门ID',
  `DEPT_NAME` varchar(100) NOT NULL COMMENT '部门名称',
  `ORDER_NUM` double(20,0) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`DEPT_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('1', '0', '开发部', '1', '2018-01-04 15:42:26', '2019-01-05 21:08:27');
INSERT INTO `t_dept` VALUES ('2', '1', '开发一部', '1', '2018-01-04 15:42:34', '2019-01-18 00:59:37');
INSERT INTO `t_dept` VALUES ('3', '1', '开发二部', '2', '2018-01-04 15:42:29', '2019-01-05 14:09:39');
INSERT INTO `t_dept` VALUES ('4', '0', '市场部', '2', '2018-01-04 15:42:36', '2019-01-23 06:27:56');
INSERT INTO `t_dept` VALUES ('5', '0', '人事部', '3', '2018-01-04 15:42:32', '2019-01-23 06:27:59');
INSERT INTO `t_dept` VALUES ('6', '0', '测试部', '4', '2018-01-04 15:42:38', '2019-01-17 08:15:47');

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `DICT_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `KEYY` bigint(20) NOT NULL COMMENT '键',
  `VALUEE` varchar(100) NOT NULL COMMENT '值',
  `FIELD_NAME` varchar(100) NOT NULL COMMENT '字段名称',
  `TABLE_NAME` varchar(100) NOT NULL COMMENT '表名',
  PRIMARY KEY (`DICT_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES ('1', '0', '男', 'ssex', 't_user');
INSERT INTO `t_dict` VALUES ('2', '1', '女', 'ssex', 't_user');
INSERT INTO `t_dict` VALUES ('3', '2', '保密', 'ssex', 't_user');
INSERT INTO `t_dict` VALUES ('4', '1', '有效', 'status', 't_user');
INSERT INTO `t_dict` VALUES ('5', '0', '锁定', 'status', 't_user');
INSERT INTO `t_dict` VALUES ('6', '0', '菜单', 'type', 't_menu');
INSERT INTO `t_dict` VALUES ('7', '1', '按钮', 'type', 't_menu');
INSERT INTO `t_dict` VALUES ('30', '0', '正常', 'status', 't_job');
INSERT INTO `t_dict` VALUES ('31', '1', '暂停', 'status', 't_job');
INSERT INTO `t_dict` VALUES ('32', '0', '成功', 'status', 't_job_log');
INSERT INTO `t_dict` VALUES ('33', '1', '失败', 'status', 't_job_log');

-- ----------------------------
-- Table structure for t_job
-- ----------------------------
DROP TABLE IF EXISTS `t_job`;
CREATE TABLE `t_job` (
  `JOB_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `BEAN_NAME` varchar(100) NOT NULL COMMENT 'spring bean名称',
  `METHOD_NAME` varchar(100) NOT NULL COMMENT '方法名',
  `PARAMS` varchar(200) DEFAULT NULL COMMENT '参数',
  `CRON_EXPRESSION` varchar(100) NOT NULL COMMENT 'cron表达式',
  `STATUS` char(2) NOT NULL COMMENT '任务状态  0：正常  1：暂停',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`JOB_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_job
-- ----------------------------

-- ----------------------------
-- Table structure for t_job_log
-- ----------------------------
DROP TABLE IF EXISTS `t_job_log`;
CREATE TABLE `t_job_log` (
  `LOG_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `JOB_ID` bigint(20) NOT NULL COMMENT '任务id',
  `BEAN_NAME` varchar(100) NOT NULL COMMENT 'spring bean名称',
  `METHOD_NAME` varchar(100) NOT NULL COMMENT '方法名',
  `PARAMS` varchar(200) DEFAULT NULL COMMENT '参数',
  `STATUS` char(2) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `ERROR` text COMMENT '失败信息',
  `TIMES` decimal(11,0) DEFAULT NULL COMMENT '耗时(单位：毫秒)',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`LOG_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `USERNAME` varchar(50) DEFAULT NULL COMMENT '操作用户',
  `OPERATION` text COMMENT '操作内容',
  `TIME` decimal(11,0) DEFAULT NULL COMMENT '耗时',
  `METHOD` text COMMENT '操作方法',
  `PARAMS` text COMMENT '方法参数',
  `IP` varchar(64) DEFAULT NULL COMMENT '操作者IP',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `location` varchar(50) DEFAULT NULL COMMENT '操作地点',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1843 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('1815', 'mrbird', '删除用户', '301', 'cc.mrbird.febs.system.controller.UserController.deleteUsers()', ' userIds: \"11\"', '127.0.0.1', '2019-01-23 06:26:43', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1816', 'mrbird', '修改菜单/按钮', '170', 'cc.mrbird.febs.system.controller.MenuController.updateMenu()', ' menu: \"Menu(menuId=2, parentId=0, menuName=系统监控, path=/monitor, component=PageView, perms=null, icon=dashboard, type=0, orderNum=2.0, createTime=null, modifyTime=Wed Jan 23 14:27:12 CST 2019, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2019-01-23 06:27:13', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1817', 'mrbird', '修改部门', '90', 'cc.mrbird.febs.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=4, parentId=0, deptName=市场部, orderNum=2.0, createTime=null, modifyTime=Wed Jan 23 14:27:55 CST 2019, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2019-01-23 06:27:56', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1818', 'mrbird', '修改部门', '596', 'cc.mrbird.febs.system.controller.DeptController.updateDept()', ' dept: \"Dept(deptId=5, parentId=0, deptName=人事部, orderNum=3.0, createTime=null, modifyTime=Wed Jan 23 14:27:59 CST 2019, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2019-01-23 06:28:00', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1819', 'mrbird', '执行定时任务', '146', 'cc.mrbird.febs.job.controller.JobController.runJob()', ' jobId: \"1\"', '127.0.0.1', '2019-01-23 06:28:58', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1820', 'mrbird', '新增菜单/按钮', '160', 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=130, parentId=3, menuName=导出Excel, path=null, component=null, perms=user:export, icon=null, type=1, orderNum=null, createTime=Wed Jan 23 14:35:15 CST 2019, modifyTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2019-01-23 06:35:16', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1821', 'mrbird', '新增菜单/按钮', '255', 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=131, parentId=4, menuName=导出Excel, path=null, component=null, perms=role:export, icon=null, type=1, orderNum=null, createTime=Wed Jan 23 14:35:36 CST 2019, modifyTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2019-01-23 06:35:36', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1822', 'mrbird', '新增菜单/按钮', '172', 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=132, parentId=5, menuName=导出Excel, path=null, component=null, perms=menu:export, icon=null, type=1, orderNum=null, createTime=Wed Jan 23 14:36:04 CST 2019, modifyTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2019-01-23 06:36:05', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1823', 'mrbird', '新增菜单/按钮', '188', 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=133, parentId=6, menuName=导出Excel, path=null, component=null, perms=dept:export, icon=null, type=1, orderNum=null, createTime=Wed Jan 23 14:36:24 CST 2019, modifyTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2019-01-23 06:36:25', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1824', 'mrbird', '新增菜单/按钮', '186', 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=134, parentId=64, menuName=导出Excel, path=null, component=null, perms=dict:export, icon=null, type=1, orderNum=null, createTime=Wed Jan 23 14:36:43 CST 2019, modifyTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2019-01-23 06:36:44', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1825', 'mrbird', '新增菜单/按钮', '160', 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=135, parentId=3, menuName=密码重置, path=null, component=null, perms=user:reset, icon=null, type=1, orderNum=null, createTime=Wed Jan 23 14:36:59 CST 2019, modifyTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2019-01-23 06:37:00', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1826', 'mrbird', '新增菜单/按钮', '181', 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=136, parentId=10, menuName=导出Excel, path=null, component=null, perms=log:export, icon=null, type=1, orderNum=null, createTime=Wed Jan 23 14:37:26 CST 2019, modifyTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2019-01-23 06:37:27', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1827', 'mrbird', '新增菜单/按钮', '146', 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=137, parentId=102, menuName=导出Excel, path=null, component=null, perms=job:export, icon=null, type=1, orderNum=null, createTime=Wed Jan 23 14:37:59 CST 2019, modifyTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2019-01-23 06:37:59', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1828', 'mrbird', '新增菜单/按钮', '164', 'cc.mrbird.febs.system.controller.MenuController.addMenu()', ' menu: \"Menu(menuId=138, parentId=109, menuName=导出Excel, path=null, component=null, perms=jobLog:export, icon=null, type=1, orderNum=null, createTime=Wed Jan 23 14:38:32 CST 2019, modifyTime=null, createTimeFrom=null, createTimeTo=null)\"', '127.0.0.1', '2019-01-23 06:38:33', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1829', 'mrbird', '修改角色', '3132', 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=1, roleName=管理员, remark=管理员, createTime=null, modifyTime=Wed Jan 23 14:45:28 CST 2019, createTimeFrom=null, createTimeTo=null, menuId=1,3,11,12,13,4,14,15,16,5,17,18,19,6,20,21,22,64,65,66,67,2,8,23,10,24,113,121,122,124,123,125,101,102,103,104,105,106,107,108,109,110,58,59,61,81,82,83,127,128,129,130,135,131,132,133,134,136,137,138)\"', '127.0.0.1', '2019-01-23 06:45:32', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1830', 'mrbird', '修改角色', '1730', 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=2, roleName=注册用户, remark=只可查看不可操作, createTime=null, modifyTime=Wed Jan 23 15:31:07 CST 2019, createTimeFrom=null, createTimeTo=null, menuId=3,1,4,5,6,64,2,8,10,113,121,122,124,123,125,101,102,109,58,59,61,81,82,83,127,128,129)\"', '127.0.0.1', '2019-01-23 07:31:09', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1831', 'mrbird', '修改角色', '1997', 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=2, roleName=注册用户, remark=可查看，新增，导出, createTime=null, modifyTime=Wed Jan 23 15:32:20 CST 2019, createTimeFrom=null, createTimeTo=null, menuId=3,1,4,5,6,64,2,8,10,113,121,122,124,123,125,101,102,109,58,59,61,81,82,83,127,128,129,130,14,17,132,20,133,65,134,136,103,137,138)\"', '127.0.0.1', '2019-01-23 07:32:22', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1832', 'mrbird', '新增角色', '1428', 'cc.mrbird.febs.system.controller.RoleController.addRole()', ' role: \"Role(roleId=72, roleName=普通用户, remark=只可查看，好可怜哦, createTime=Wed Jan 23 15:33:20 CST 2019, modifyTime=null, createTimeFrom=null, createTimeTo=null, menuId=1,3,4,5,6,64,2,8,10,113,121,122,124,123,127,101,102,109,58,59,61,81,82,83,128,129)\"', '127.0.0.1', '2019-01-23 07:33:22', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1833', 'mrbird', '新增用户', '338', 'cc.mrbird.febs.system.controller.UserController.addUser()', ' user: \"User(userId=12, username=jack, password=552649f10640385d0728a80a4242893e, deptId=6, deptName=null, email=jack@hotmail.com, mobile=null, status=1, createTime=Wed Jan 23 15:34:05 CST 2019, modifyTime=null, lastLoginTime=null, ssex=0, description=null, avatar=default.jpg, roleId=72, roleName=null, sortField=null, sortOrder=null, createTimeFrom=null, createTimeTo=null, id=null)\"', '127.0.0.1', '2019-01-23 07:34:06', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1834', 'mrbird', '修改角色', '2160', 'cc.mrbird.febs.system.controller.RoleController.updateRole()', ' role: \"Role(roleId=2, roleName=注册用户, remark=可查看，新增，导出, createTime=null, modifyTime=Wed Jan 23 15:37:08 CST 2019, createTimeFrom=null, createTimeTo=null, menuId=3,1,4,5,6,64,2,8,10,113,121,122,124,123,125,101,102,109,58,59,61,81,82,83,127,128,129,130,14,17,132,20,133,65,134,136,103,137,138,131)\"', '127.0.0.1', '2019-01-23 07:37:11', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1835', 'mrbird', '新增角色', '169', 'cc.mrbird.febs.system.controller.RoleController.addRole()', ' role: \"Role(roleId=73, roleName=测试xss, remark=<style>body{background:red !important}</style>, createTime=Wed Jan 23 15:47:04 CST 2019, modifyTime=null, createTimeFrom=null, createTimeTo=null, menuId=1,3)\"', '127.0.0.1', '2019-01-23 07:47:04', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1836', 'mrbird', '删除角色', '54', 'cc.mrbird.febs.system.controller.RoleController.deleteRoles()', ' roleIds: \"73\"', '218.104.237.213', '2019-01-24 03:03:41', '中国|华东|福建省|福州市|联通');
INSERT INTO `t_log` VALUES ('1837', 'mrbird', '修改用户', '39', 'cc.mrbird.febs.system.controller.UserController.updateUser()', ' user: \"User(userId=12, username=jack, password=null, deptId=6, deptName=null, email=jack@hotmail.com, mobile=null, status=1, createTime=null, modifyTime=Thu Jan 24 11:08:00 CST 2019, lastLoginTime=null, ssex=0, description=null, avatar=null, roleId=72, roleName=null, sortField=null, sortOrder=null, createTimeFrom=null, createTimeTo=null, id=null)\"', '218.104.237.213', '2019-01-24 03:08:01', '中国|华东|福建省|福州市|联通');
INSERT INTO `t_log` VALUES ('1838', 'mrbird', '执行定时任务', '41', 'cc.mrbird.febs.job.controller.JobController.runJob()', ' jobId: \"1\"', '218.104.237.213', '2019-01-24 05:39:59', '中国|华东|福建省|福州市|联通');
INSERT INTO `t_log` VALUES ('1839', 'mrbird', '删除定时任务', '20', 'cc.mrbird.febs.job.controller.JobController.deleteJob()', ' jobIds: \"11\"', '127.0.0.1', '2019-11-20 04:58:22', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1840', 'mrbird', '删除定时任务', '38', 'cc.mrbird.febs.job.controller.JobController.deleteJob()', ' jobIds: \"3,2,1\"', '127.0.0.1', '2019-11-20 04:58:27', '内网IP|0|0|内网IP|内网IP');
INSERT INTO `t_log` VALUES ('1841', 'mrbird', '访问测试接口', '13', 'com.example.demo.web.controller.system.TestController.test()', '', '127.0.0.1', '2019-12-19 17:54:24', '');
INSERT INTO `t_log` VALUES ('1842', 'mrbird', '访问测试接口', '0', 'com.example.demo.web.controller.system.TestController.test()', '', '127.0.0.1', '2019-12-19 17:58:58', '');

-- ----------------------------
-- Table structure for t_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log` (
  `USERNAME` varchar(100) NOT NULL COMMENT '用户名',
  `LOGIN_TIME` datetime NOT NULL COMMENT '登录时间',
  `LOCATION` varchar(255) DEFAULT NULL COMMENT '登录地点',
  `IP` varchar(100) DEFAULT NULL COMMENT 'IP地址'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_login_log
-- ----------------------------
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-12 03:18:33', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-10 03:18:33', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-09 03:18:33', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-11 03:18:33', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-12 04:23:45', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-15 03:31:18', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-15 03:36:28', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-15 06:05:36', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-15 08:44:39', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-15 09:02:42', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('scott', '2019-01-15 09:24:21', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('scott', '2019-01-15 09:25:16', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-15 10:14:20', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('scott', '2019-01-15 10:48:59', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-14 11:02:04', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('scott', '2019-01-13 11:02:04', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-15 11:02:04', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-16 01:20:24', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-16 02:25:47', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-16 03:25:11', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-16 03:44:23', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-16 05:44:05', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-16 05:51:12', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('scott', '2019-01-16 05:51:21', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-16 05:54:03', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-16 06:18:57', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-16 06:31:19', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-16 07:32:02', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 01:10:42', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 02:21:12', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 06:07:00', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 06:45:24', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 06:46:40', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 06:54:23', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 06:54:53', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 06:55:38', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 07:38:37', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 07:39:14', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 07:40:48', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 07:41:41', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('scott', '2019-01-17 07:42:53', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('scott', '2019-01-17 07:43:39', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 08:13:29', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 08:39:56', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 09:26:19', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 09:26:58', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 09:30:15', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbirdd', '2019-01-17 10:31:40', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('jack', '2019-01-17 10:41:14', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('abcd', '2019-01-17 10:47:48', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('abcd', '2019-01-17 10:48:06', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('abcd', '2019-01-17 10:48:44', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('abcd', '2019-01-17 10:51:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('wuyouzhugu', '2019-01-17 10:54:56', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 10:56:53', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 10:59:15', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 10:59:53', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 11:01:54', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 11:08:43', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 11:12:55', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-17 11:13:21', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-18 00:56:15', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-18 01:21:54', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-18 01:33:06', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-18 02:03:32', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-18 02:27:12', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-18 02:36:26', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-18 02:41:49', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-18 02:53:12', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-18 02:56:00', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-18 03:00:35', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-18 05:36:02', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-18 05:57:39', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-18 06:50:27', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-18 07:09:37', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-18 08:57:02', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-18 09:00:06', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-19 01:13:17', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-19 01:14:42', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-19 01:50:38', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-19 02:05:44', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-19 02:06:52', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-19 02:11:47', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-19 02:12:13', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-19 02:12:27', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-19 02:33:21', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-19 02:40:19', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-21 03:05:20', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-21 03:16:03', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-21 05:43:32', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-21 05:44:20', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-21 06:47:04', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-21 06:49:51', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-21 07:48:30', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-21 07:50:34', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-21 07:55:22', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-21 07:57:39', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-21 08:35:07', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-21 08:58:37', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-21 11:05:26', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-22 00:47:44', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-22 01:02:23', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-22 01:38:19', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-22 02:39:18', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-22 05:39:47', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-22 05:44:25', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-22 06:04:18', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-22 06:04:34', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-22 06:13:00', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-22 06:13:17', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('scott', '2019-01-22 06:13:43', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('scott', '2019-01-22 06:14:41', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('scott', '2019-01-22 06:15:10', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('hello', '2019-01-22 06:15:48', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('hello', '2019-01-22 06:17:19', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('hello', '2019-01-22 06:18:39', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-22 06:19:03', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-22 06:20:48', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-22 07:04:26', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-22 07:06:07', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-22 07:06:57', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-22 08:37:28', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-22 10:29:50', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-23 00:50:47', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-23 01:51:42', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-23 02:58:49', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-23 06:11:14', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('scott', '2019-01-23 06:46:30', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-23 06:48:25', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('scott', '2019-01-23 06:51:20', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-23 07:30:25', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('scott', '2019-01-23 07:34:28', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('jack', '2019-01-23 07:35:56', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-23 07:36:46', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-24 01:30:13', '中国|华东|福建省|福州市|联通', '218.104.237.213');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-24 01:42:03', '中国|华东|福建省|福州市|联通', '218.104.237.213');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-24 01:48:10', '中国|华东|福建省|福州市|联通', '218.104.237.213');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-24 01:50:12', '中国|华东|福建省|福州市|联通', '218.104.237.213');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-24 01:50:28', '中国|华东|福建省|福州市|联通', '218.104.237.213');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-24 02:57:48', '中国|华东|福建省|福州市|联通', '218.104.237.213');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-24 03:02:53', '中国|华东|福建省|福州市|联通', '218.104.237.213');
INSERT INTO `t_login_log` VALUES ('scott', '2019-01-24 03:14:51', '中国|华东|福建省|厦门市|电信', '120.36.172.239');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-24 03:41:10', '中国|华东|福建省|福州市|联通', '218.104.237.213');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-24 05:38:30', '中国|华东|福建省|福州市|联通', '218.104.237.213');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-24 08:50:44', '中国|华东|福建省|福州市|联通', '218.104.237.213');
INSERT INTO `t_login_log` VALUES ('jack', '2019-01-24 08:52:03', '中国|华东|福建省|福州市|联通', '218.104.237.213');
INSERT INTO `t_login_log` VALUES ('scott', '2019-01-24 08:52:31', '中国|华东|福建省|福州市|联通', '218.104.237.213');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-24 11:12:33', '中国|华东|福建省|福州市|联通', '218.104.237.213');
INSERT INTO `t_login_log` VALUES ('scott', '2019-01-24 11:24:04', '中国|华东|福建省|福州市|联通', '218.104.237.213');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-24 11:47:56', '中国|华东|福建省|福州市|电信', '27.155.195.27');
INSERT INTO `t_login_log` VALUES ('scott', '2019-01-24 11:48:28', '中国|华东|福建省|福州市|电信', '27.155.195.27');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-28 01:53:06', '中国|华东|福建省|福州市|联通', '218.104.237.213');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-01-28 01:53:58', '中国|华东|福建省|福州市|联通', '218.104.237.213');
INSERT INTO `t_login_log` VALUES ('scott', '2019-01-28 01:54:09', '中国|华东|福建省|福州市|联通', '218.104.237.213');
INSERT INTO `t_login_log` VALUES ('mrbird', '2019-11-20 04:57:09', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');
INSERT INTO `t_login_log` VALUES ('scott', '2019-11-20 08:01:08', '内网IP|0|0|内网IP|内网IP', '127.0.0.1');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `MENU_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮ID',
  `PARENT_ID` bigint(20) NOT NULL COMMENT '上级菜单ID',
  `MENU_NAME` varchar(50) NOT NULL COMMENT '菜单/按钮名称',
  `PATH` varchar(255) DEFAULT NULL COMMENT '对应路由path',
  `COMPONENT` varchar(255) DEFAULT NULL COMMENT '对应路由组件component',
  `PERMS` varchar(50) DEFAULT NULL COMMENT '权限标识',
  `ICON` varchar(50) DEFAULT NULL COMMENT '图标',
  `TYPE` char(2) NOT NULL COMMENT '类型 0菜单 1按钮',
  `ORDER_NUM` double(20,0) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `DELETED` tinyint(4) unsigned zerofill DEFAULT '0000' COMMENT '逻辑删除 0：未删除 1已删除',
  PRIMARY KEY (`MENU_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '0', '系统管理', '/system', 'PageView', null, 'appstore-o', '0', '1', '2017-12-27 16:39:07', '2019-01-05 11:13:14', '0000');
INSERT INTO `t_menu` VALUES ('2', '0', '系统监控', '/monitor', 'PageView', null, 'dashboard', '0', '2', '2017-12-27 16:45:51', '2019-01-23 06:27:12', '0000');
INSERT INTO `t_menu` VALUES ('3', '1', '用户管理', '/system/user', 'system/user/User', 'user:view', '', '0', '1', '2017-12-27 16:47:13', '2019-01-22 06:45:55', '0000');
INSERT INTO `t_menu` VALUES ('4', '1', '角色管理', '/system/role', 'system/role/Role', 'role:view', '', '0', '2', '2017-12-27 16:48:09', '2018-04-25 09:01:12', '0000');
INSERT INTO `t_menu` VALUES ('5', '1', '菜单管理', '/system/menu', 'system/menu/Menu', 'menu:view', '', '0', '3', '2017-12-27 16:48:57', '2018-04-25 09:01:30', '0000');
INSERT INTO `t_menu` VALUES ('6', '1', '部门管理', '/system/dept', 'system/dept/Dept', 'dept:view', '', '0', '4', '2017-12-27 16:57:33', '2018-04-25 09:01:40', '0000');
INSERT INTO `t_menu` VALUES ('8', '2', '在线用户', '/monitor/online', 'monitor/Online', 'user:online', '', '0', '1', '2017-12-27 16:59:33', '2018-04-25 09:02:04', '0000');
INSERT INTO `t_menu` VALUES ('10', '2', '系统日志', '/monitor/systemlog', 'monitor/SystemLog', 'log:view', '', '0', '2', '2017-12-27 17:00:50', '2018-04-25 09:02:18', '0000');
INSERT INTO `t_menu` VALUES ('11', '3', '新增用户', '', '', 'user:add', null, '1', null, '2017-12-27 17:02:58', null, '0000');
INSERT INTO `t_menu` VALUES ('12', '3', '修改用户', '', '', 'user:update', null, '1', null, '2017-12-27 17:04:07', null, '0000');
INSERT INTO `t_menu` VALUES ('13', '3', '删除用户', '', '', 'user:delete', null, '1', null, '2017-12-27 17:04:58', null, '0000');
INSERT INTO `t_menu` VALUES ('14', '4', '新增角色', '', '', 'role:add', null, '1', null, '2017-12-27 17:06:38', null, '0000');
INSERT INTO `t_menu` VALUES ('15', '4', '修改角色', '', '', 'role:update', null, '1', null, '2017-12-27 17:06:38', null, '0000');
INSERT INTO `t_menu` VALUES ('16', '4', '删除角色', '', '', 'role:delete', null, '1', null, '2017-12-27 17:06:38', null, '0000');
INSERT INTO `t_menu` VALUES ('17', '5', '新增菜单', '', '', 'menu:add', null, '1', null, '2017-12-27 17:08:02', null, '0000');
INSERT INTO `t_menu` VALUES ('18', '5', '修改菜单', '', '', 'menu:update', null, '1', null, '2017-12-27 17:08:02', null, '0000');
INSERT INTO `t_menu` VALUES ('19', '5', '删除菜单', '', '', 'menu:delete', null, '1', null, '2017-12-27 17:08:02', null, '0000');
INSERT INTO `t_menu` VALUES ('20', '6', '新增部门', '', '', 'dept:add', null, '1', null, '2017-12-27 17:09:24', null, '0000');
INSERT INTO `t_menu` VALUES ('21', '6', '修改部门', '', '', 'dept:update', null, '1', null, '2017-12-27 17:09:24', null, '0000');
INSERT INTO `t_menu` VALUES ('22', '6', '删除部门', '', '', 'dept:delete', null, '1', null, '2017-12-27 17:09:24', null, '0000');
INSERT INTO `t_menu` VALUES ('23', '8', '踢出用户', '', '', 'user:kickout', null, '1', null, '2017-12-27 17:11:13', null, '0000');
INSERT INTO `t_menu` VALUES ('24', '10', '删除日志', '', '', 'log:delete', null, '1', null, '2017-12-27 17:11:45', null, '0000');
INSERT INTO `t_menu` VALUES ('58', '0', '网络资源', '/web', 'PageView', null, 'compass', '0', '4', '2018-01-12 15:28:48', '2018-01-22 19:49:26', '0000');
INSERT INTO `t_menu` VALUES ('59', '58', '天气查询', '/web/weather', 'web/Weather', 'weather:view', '', '0', '1', '2018-01-12 15:40:02', '2019-01-22 05:43:19', '0000');
INSERT INTO `t_menu` VALUES ('61', '58', '每日一文', '/web/dailyArticle', 'web/DailyArticle', 'article:view', '', '0', '2', '2018-01-15 17:17:14', '2019-01-22 05:43:27', '0000');
INSERT INTO `t_menu` VALUES ('64', '1', '字典管理', '/system/dict', 'system/dict/Dict', 'dict:view', '', '0', '5', '2018-01-18 10:38:25', '2018-04-25 09:01:50', '0000');
INSERT INTO `t_menu` VALUES ('65', '64', '新增字典', '', '', 'dict:add', null, '1', null, '2018-01-18 19:10:08', null, '0000');
INSERT INTO `t_menu` VALUES ('66', '64', '修改字典', '', '', 'dict:update', null, '1', null, '2018-01-18 19:10:27', null, '0000');
INSERT INTO `t_menu` VALUES ('67', '64', '删除字典', '', '', 'dict:delete', null, '1', null, '2018-01-18 19:10:47', null, '0000');
INSERT INTO `t_menu` VALUES ('81', '58', '影视资讯', '/web/movie', 'EmptyPageView', null, null, '0', '3', '2018-01-22 14:12:59', '2019-01-22 05:43:35', '0000');
INSERT INTO `t_menu` VALUES ('82', '81', '正在热映', '/web/movie/hot', 'web/MovieHot', 'movie:hot', '', '0', '1', '2018-01-22 14:13:47', '2019-01-22 05:43:52', '0000');
INSERT INTO `t_menu` VALUES ('83', '81', '即将上映', '/web/movie/coming', 'web/MovieComing', 'movie:coming', '', '0', '2', '2018-01-22 14:14:36', '2019-01-22 05:43:58', '0000');
INSERT INTO `t_menu` VALUES ('101', '0', '任务调度', '/job', 'PageView', null, 'clock-circle-o', '0', '3', '2018-01-11 15:52:57', null, '0000');
INSERT INTO `t_menu` VALUES ('102', '101', '定时任务', '/job/job', 'quartz/job/Job', 'job:view', '', '0', '1', '2018-02-24 15:53:53', '2019-01-22 05:42:50', '0000');
INSERT INTO `t_menu` VALUES ('103', '102', '新增任务', '', '', 'job:add', null, '1', null, '2018-02-24 15:55:10', null, '0000');
INSERT INTO `t_menu` VALUES ('104', '102', '修改任务', '', '', 'job:update', null, '1', null, '2018-02-24 15:55:53', null, '0000');
INSERT INTO `t_menu` VALUES ('105', '102', '删除任务', '', '', 'job:delete', null, '1', null, '2018-02-24 15:56:18', null, '0000');
INSERT INTO `t_menu` VALUES ('106', '102', '暂停任务', '', '', 'job:pause', null, '1', null, '2018-02-24 15:57:08', null, '0000');
INSERT INTO `t_menu` VALUES ('107', '102', '恢复任务', '', '', 'job:resume', null, '1', null, '2018-02-24 15:58:21', null, '0000');
INSERT INTO `t_menu` VALUES ('108', '102', '立即执行任务', '', '', 'job:run', null, '1', null, '2018-02-24 15:59:45', null, '0000');
INSERT INTO `t_menu` VALUES ('109', '101', '调度日志', '/job/log', 'quartz/log/JobLog', 'jobLog:view', '', '0', '2', '2018-02-24 16:00:45', '2019-01-22 05:42:59', '0000');
INSERT INTO `t_menu` VALUES ('110', '109', '删除日志', '', '', 'jobLog:delete', null, '1', null, '2018-02-24 16:01:21', null, '0000');
INSERT INTO `t_menu` VALUES ('113', '2', 'Redis监控', '/monitor/redis/info', 'monitor/RedisInfo', 'redis:view', '', '0', '3', '2018-06-28 14:29:42', null, '0000');
INSERT INTO `t_menu` VALUES ('121', '2', '请求追踪', '/monitor/httptrace', 'monitor/Httptrace', null, null, '0', '4', '2019-01-18 02:30:29', null, '0000');
INSERT INTO `t_menu` VALUES ('122', '2', '系统信息', '/monitor/system', 'EmptyPageView', null, null, '0', '5', '2019-01-18 02:31:48', '2019-01-18 02:39:46', '0000');
INSERT INTO `t_menu` VALUES ('123', '122', 'Tomcat信息', '/monitor/system/tomcatinfo', 'monitor/TomcatInfo', null, null, '0', '2', '2019-01-18 02:32:53', '2019-01-18 02:46:57', '0000');
INSERT INTO `t_menu` VALUES ('124', '122', 'JVM信息', '/monitor/system/jvminfo', 'monitor/JvmInfo', null, null, '0', '1', '2019-01-18 02:33:30', '2019-01-18 02:46:51', '0000');
INSERT INTO `t_menu` VALUES ('127', '122', '服务器信息', '/monitor/system/info', 'monitor/SystemInfo', null, null, '0', '3', '2019-01-21 07:53:43', '2019-01-21 07:57:00', '0000');
INSERT INTO `t_menu` VALUES ('128', '0', '其他模块', '/others', 'PageView', null, 'coffee', '0', '5', '2019-01-22 06:49:59', '2019-01-22 06:50:13', '0000');
INSERT INTO `t_menu` VALUES ('129', '128', '导入导出', '/others/excel', 'others/Excel', null, null, '0', '1', '2019-01-22 06:51:36', '2019-01-22 07:06:45', '0000');
INSERT INTO `t_menu` VALUES ('130', '3', '导出Excel', null, null, 'user:export', null, '1', null, '2019-01-23 06:35:16', null, '0000');
INSERT INTO `t_menu` VALUES ('131', '4', '导出Excel', null, null, 'role:export', null, '1', null, '2019-01-23 06:35:36', null, '0000');
INSERT INTO `t_menu` VALUES ('132', '5', '导出Excel', null, null, 'menu:export', null, '1', null, '2019-01-23 06:36:05', null, '0000');
INSERT INTO `t_menu` VALUES ('133', '6', '导出Excel', null, null, 'dept:export', null, '1', null, '2019-01-23 06:36:25', null, '0000');
INSERT INTO `t_menu` VALUES ('134', '64', '导出Excel', null, null, 'dict:export', null, '1', null, '2019-01-23 06:36:43', null, '0000');
INSERT INTO `t_menu` VALUES ('135', '3', '密码重置', null, null, 'user:reset', null, '1', null, '2019-01-23 06:37:00', null, '0000');
INSERT INTO `t_menu` VALUES ('136', '10', '导出Excel', null, null, 'log:export', null, '1', null, '2019-01-23 06:37:27', null, '0000');
INSERT INTO `t_menu` VALUES ('137', '102', '导出Excel', null, null, 'job:export', null, '1', null, '2019-01-23 06:37:59', null, '0000');
INSERT INTO `t_menu` VALUES ('138', '109', '导出Excel', null, null, 'jobLog:export', null, '1', null, '2019-01-23 06:38:32', null, '0000');

-- ----------------------------
-- Table structure for t_person
-- ----------------------------
DROP TABLE IF EXISTS `t_person`;
CREATE TABLE `t_person` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `education` int(1) DEFAULT NULL COMMENT '学历',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '录入时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `del_flg` int(1) DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_person
-- ----------------------------
INSERT INTO `t_person` VALUES ('1', '赵', '26', '萌', '上海路成都胡同33号二单元4楼401', '1', '1', '2019-10-28 15:41:20', '张三', '0');
INSERT INTO `t_person` VALUES ('8', '刘强', '26', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('9', '刘强', '24', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('10', '刘强', '43', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('11', '刘强', '33', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('12', '刘强', '45', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('13', '刘强', '42', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('14', '刘强', '38', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('15', '刘强', '33', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('16', '刘强', '42', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('17', '刘强', '23', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('18', '刘强', '25', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('19', '刘强', '27', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('20', '刘强', '36', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('21', '刘强', '34', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('22', '刘强', '26', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('23', '刘强', '36', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('24', '刘强', '33', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('25', '刘强', '23', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('26', '刘强', '43', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('27', '刘强', '29', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('28', '刘强', '38', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('29', '刘强', '45', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('30', '刘强', '43', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('31', '刘强', '45', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('32', '刘强', '37', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('33', '刘强', '34', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('34', '刘强', '28', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('35', '刘强', '36', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('36', '刘强', '35', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('37', '刘强', '34', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('38', '刘强', '45', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('39', '刘强', '26', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('40', '刘强', '27', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('41', '刘强', '31', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('42', '刘强', '29', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('43', '刘强', '30', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('44', '刘强', '27', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('45', '刘强', '30', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('46', '刘强', '43', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('47', '刘强', '41', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('48', '刘强', '44', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('49', '刘强', '31', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('50', '刘强', '37', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('51', '刘强', '34', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('52', '刘强', '32', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('53', '刘强', '38', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('54', '刘强', '42', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('55', '刘强', '38', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('56', '刘强', '29', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('57', '刘强', '42', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('58', '刘强', '38', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('59', '刘强', '38', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('60', '刘强', '31', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('61', '刘强', '29', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('62', '刘强', '25', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('63', '刘强', '28', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('64', '刘强', '23', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('65', '刘强', '45', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('66', '刘强', '35', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('67', '刘强', '39', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('68', '刘强', '42', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('69', '刘强', '32', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('70', '刘强', '23', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('71', '刘强', '39', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('72', '刘强', '38', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('73', '刘强', '39', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('74', '刘强', '30', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('75', '刘强', '40', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('76', '刘强', '43', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('77', '刘强', '42', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('78', '刘强', '27', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('79', '刘强', '38', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('80', '刘强', '23', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('81', '刘强', '36', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('82', '刘强', '42', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('83', '刘强', '46', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('84', '刘强', '39', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('85', '刘强', '43', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('86', '刘强', '28', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('87', '刘强', '38', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('88', '刘强', '26', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('89', '刘强', '37', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('90', '刘强', '34', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('91', '刘强', '23', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('92', '刘强', '38', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('93', '刘强', '31', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('94', '刘强', '33', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('95', '刘强', '24', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('96', '刘强', '41', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('97', '刘强', '29', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('98', '刘强', '36', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('99', '刘强', '35', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('100', '刘强', '36', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('101', '刘强', '38', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('102', '刘强', '45', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('103', '刘强', '29', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('104', '刘强', '26', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('105', '刘强', '39', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('106', '刘强', '29', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('107', '刘强', '38', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-10-28 16:32:41', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('108', '刘强', '24', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('109', '刘强', '32', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('110', '刘强', '46', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('111', '刘强', '33', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('112', '刘强', '44', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('113', '刘强', '34', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('114', '刘强', '31', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('115', '刘强', '43', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('116', '刘强', '32', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('117', '刘强', '45', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('118', '刘强', '34', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('119', '刘强', '25', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('120', '刘强', '29', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('121', '刘强', '30', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('122', '刘强', '35', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('123', '刘强', '42', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('124', '刘强', '28', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('125', '刘强', '34', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('126', '刘强', '37', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('127', '刘强', '32', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('128', '刘强', '29', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('129', '刘强', '31', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('130', '刘强', '44', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('131', '刘强', '37', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('132', '刘强', '30', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('133', '刘强', '45', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('134', '刘强', '34', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('135', '刘强', '26', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('136', '刘强', '33', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('137', '刘强', '31', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('138', '刘强', '25', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('139', '刘强', '32', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('140', '刘强', '26', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('141', '刘强', '39', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('142', '刘强', '45', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('143', '刘强', '34', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('144', '刘强', '35', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('145', '刘强', '32', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('146', '刘强', '29', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('147', '刘强', '24', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('148', '刘强', '33', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('149', '刘强', '28', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('150', '刘强', '27', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('151', '刘强', '27', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('152', '刘强', '36', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('153', '刘强', '46', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('154', '刘强', '44', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('155', '刘强', '39', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('156', '刘强', '30', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('157', '刘强', '30', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('158', '刘强', '25', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('159', '刘强', '23', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('160', '刘强', '46', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('161', '刘强', '43', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('162', '刘强', '23', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('163', '刘强', '31', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('164', '刘强', '32', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('165', '刘强', '30', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('166', '刘强', '25', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('167', '刘强', '36', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('168', '刘强', '26', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('169', '刘强', '31', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('170', '刘强', '35', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('171', '刘强', '45', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('172', '刘强', '27', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('173', '刘强', '46', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('174', '刘强', '34', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('175', '刘强', '24', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('176', '刘强', '43', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('177', '刘强', '45', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('178', '刘强', '42', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('179', '刘强', '45', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('180', '刘强', '27', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('181', '刘强', '24', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('182', '刘强', '32', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('183', '刘强', '46', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('184', '刘强', '34', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('185', '刘强', '32', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('186', '刘强', '25', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('187', '刘强', '29', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('188', '刘强', '25', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('189', '刘强', '29', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('190', '刘强', '30', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('191', '刘强', '24', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('192', '刘强', '30', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('193', '刘强', '31', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('194', '刘强', '29', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('195', '刘强', '23', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('196', '刘强', '42', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('197', '刘强', '31', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('198', '刘强', '28', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('199', '刘强', '33', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('200', '刘强', '37', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('201', '刘强', '29', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('202', '刘强', '37', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('203', '刘强', '38', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('204', '刘强', '31', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('205', '刘强', '46', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('206', '刘强', '46', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');
INSERT INTO `t_person` VALUES ('207', '刘强', '33', '无趣钱财', '上海路成都胡同33号二单元4楼402', '0', '2', '2019-11-26 09:46:16', 'KIKI', '0');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `ROLE_NAME` varchar(10) DEFAULT NULL COMMENT '角色名称',
  `ROLE_NICKNAME` varchar(10) DEFAULT NULL COMMENT '角色昵称',
  `REMARK` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ROLE_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin', '管理员', '管理员', '2017-12-27 16:23:11', '2019-01-23 06:45:29');
INSERT INTO `t_role` VALUES ('2', 'member', '注册用户', '可查看，新增，导出', '2019-01-04 14:11:28', '2019-01-23 07:37:08');
INSERT INTO `t_role` VALUES ('72', 'ordinar', '普通用户', '只可查看，好可怜哦', '2019-01-23 07:33:20', null);

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `ROLE_ID` bigint(20) NOT NULL,
  `MENU_ID` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('1', '1');
INSERT INTO `t_role_menu` VALUES ('1', '3');
INSERT INTO `t_role_menu` VALUES ('1', '11');
INSERT INTO `t_role_menu` VALUES ('1', '12');
INSERT INTO `t_role_menu` VALUES ('1', '13');
INSERT INTO `t_role_menu` VALUES ('1', '4');
INSERT INTO `t_role_menu` VALUES ('1', '14');
INSERT INTO `t_role_menu` VALUES ('1', '15');
INSERT INTO `t_role_menu` VALUES ('1', '16');
INSERT INTO `t_role_menu` VALUES ('1', '5');
INSERT INTO `t_role_menu` VALUES ('1', '17');
INSERT INTO `t_role_menu` VALUES ('1', '18');
INSERT INTO `t_role_menu` VALUES ('1', '19');
INSERT INTO `t_role_menu` VALUES ('1', '6');
INSERT INTO `t_role_menu` VALUES ('1', '20');
INSERT INTO `t_role_menu` VALUES ('1', '21');
INSERT INTO `t_role_menu` VALUES ('1', '22');
INSERT INTO `t_role_menu` VALUES ('1', '64');
INSERT INTO `t_role_menu` VALUES ('1', '65');
INSERT INTO `t_role_menu` VALUES ('1', '66');
INSERT INTO `t_role_menu` VALUES ('1', '67');
INSERT INTO `t_role_menu` VALUES ('1', '2');
INSERT INTO `t_role_menu` VALUES ('1', '8');
INSERT INTO `t_role_menu` VALUES ('1', '23');
INSERT INTO `t_role_menu` VALUES ('1', '10');
INSERT INTO `t_role_menu` VALUES ('1', '24');
INSERT INTO `t_role_menu` VALUES ('1', '113');
INSERT INTO `t_role_menu` VALUES ('1', '121');
INSERT INTO `t_role_menu` VALUES ('1', '122');
INSERT INTO `t_role_menu` VALUES ('1', '124');
INSERT INTO `t_role_menu` VALUES ('1', '123');
INSERT INTO `t_role_menu` VALUES ('1', '125');
INSERT INTO `t_role_menu` VALUES ('1', '101');
INSERT INTO `t_role_menu` VALUES ('1', '102');
INSERT INTO `t_role_menu` VALUES ('1', '103');
INSERT INTO `t_role_menu` VALUES ('1', '104');
INSERT INTO `t_role_menu` VALUES ('1', '105');
INSERT INTO `t_role_menu` VALUES ('1', '106');
INSERT INTO `t_role_menu` VALUES ('1', '107');
INSERT INTO `t_role_menu` VALUES ('1', '108');
INSERT INTO `t_role_menu` VALUES ('1', '109');
INSERT INTO `t_role_menu` VALUES ('1', '110');
INSERT INTO `t_role_menu` VALUES ('1', '58');
INSERT INTO `t_role_menu` VALUES ('1', '59');
INSERT INTO `t_role_menu` VALUES ('1', '61');
INSERT INTO `t_role_menu` VALUES ('1', '81');
INSERT INTO `t_role_menu` VALUES ('1', '82');
INSERT INTO `t_role_menu` VALUES ('1', '83');
INSERT INTO `t_role_menu` VALUES ('1', '127');
INSERT INTO `t_role_menu` VALUES ('1', '128');
INSERT INTO `t_role_menu` VALUES ('1', '129');
INSERT INTO `t_role_menu` VALUES ('1', '130');
INSERT INTO `t_role_menu` VALUES ('1', '135');
INSERT INTO `t_role_menu` VALUES ('1', '131');
INSERT INTO `t_role_menu` VALUES ('1', '132');
INSERT INTO `t_role_menu` VALUES ('1', '133');
INSERT INTO `t_role_menu` VALUES ('1', '134');
INSERT INTO `t_role_menu` VALUES ('1', '136');
INSERT INTO `t_role_menu` VALUES ('1', '137');
INSERT INTO `t_role_menu` VALUES ('1', '138');
INSERT INTO `t_role_menu` VALUES ('72', '1');
INSERT INTO `t_role_menu` VALUES ('72', '3');
INSERT INTO `t_role_menu` VALUES ('72', '4');
INSERT INTO `t_role_menu` VALUES ('72', '5');
INSERT INTO `t_role_menu` VALUES ('72', '6');
INSERT INTO `t_role_menu` VALUES ('72', '64');
INSERT INTO `t_role_menu` VALUES ('72', '2');
INSERT INTO `t_role_menu` VALUES ('72', '8');
INSERT INTO `t_role_menu` VALUES ('72', '10');
INSERT INTO `t_role_menu` VALUES ('72', '113');
INSERT INTO `t_role_menu` VALUES ('72', '121');
INSERT INTO `t_role_menu` VALUES ('72', '122');
INSERT INTO `t_role_menu` VALUES ('72', '124');
INSERT INTO `t_role_menu` VALUES ('72', '123');
INSERT INTO `t_role_menu` VALUES ('72', '127');
INSERT INTO `t_role_menu` VALUES ('72', '101');
INSERT INTO `t_role_menu` VALUES ('72', '102');
INSERT INTO `t_role_menu` VALUES ('72', '109');
INSERT INTO `t_role_menu` VALUES ('72', '58');
INSERT INTO `t_role_menu` VALUES ('72', '59');
INSERT INTO `t_role_menu` VALUES ('72', '61');
INSERT INTO `t_role_menu` VALUES ('72', '81');
INSERT INTO `t_role_menu` VALUES ('72', '82');
INSERT INTO `t_role_menu` VALUES ('72', '83');
INSERT INTO `t_role_menu` VALUES ('72', '128');
INSERT INTO `t_role_menu` VALUES ('72', '129');
INSERT INTO `t_role_menu` VALUES ('2', '3');
INSERT INTO `t_role_menu` VALUES ('2', '1');
INSERT INTO `t_role_menu` VALUES ('2', '4');
INSERT INTO `t_role_menu` VALUES ('2', '5');
INSERT INTO `t_role_menu` VALUES ('2', '6');
INSERT INTO `t_role_menu` VALUES ('2', '64');
INSERT INTO `t_role_menu` VALUES ('2', '2');
INSERT INTO `t_role_menu` VALUES ('2', '8');
INSERT INTO `t_role_menu` VALUES ('2', '10');
INSERT INTO `t_role_menu` VALUES ('2', '113');
INSERT INTO `t_role_menu` VALUES ('2', '121');
INSERT INTO `t_role_menu` VALUES ('2', '122');
INSERT INTO `t_role_menu` VALUES ('2', '124');
INSERT INTO `t_role_menu` VALUES ('2', '123');
INSERT INTO `t_role_menu` VALUES ('2', '125');
INSERT INTO `t_role_menu` VALUES ('2', '101');
INSERT INTO `t_role_menu` VALUES ('2', '102');
INSERT INTO `t_role_menu` VALUES ('2', '109');
INSERT INTO `t_role_menu` VALUES ('2', '58');
INSERT INTO `t_role_menu` VALUES ('2', '59');
INSERT INTO `t_role_menu` VALUES ('2', '61');
INSERT INTO `t_role_menu` VALUES ('2', '81');
INSERT INTO `t_role_menu` VALUES ('2', '82');
INSERT INTO `t_role_menu` VALUES ('2', '83');
INSERT INTO `t_role_menu` VALUES ('2', '127');
INSERT INTO `t_role_menu` VALUES ('2', '128');
INSERT INTO `t_role_menu` VALUES ('2', '129');
INSERT INTO `t_role_menu` VALUES ('2', '130');
INSERT INTO `t_role_menu` VALUES ('2', '14');
INSERT INTO `t_role_menu` VALUES ('2', '17');
INSERT INTO `t_role_menu` VALUES ('2', '132');
INSERT INTO `t_role_menu` VALUES ('2', '20');
INSERT INTO `t_role_menu` VALUES ('2', '133');
INSERT INTO `t_role_menu` VALUES ('2', '65');
INSERT INTO `t_role_menu` VALUES ('2', '134');
INSERT INTO `t_role_menu` VALUES ('2', '136');
INSERT INTO `t_role_menu` VALUES ('2', '103');
INSERT INTO `t_role_menu` VALUES ('2', '137');
INSERT INTO `t_role_menu` VALUES ('2', '138');
INSERT INTO `t_role_menu` VALUES ('2', '131');

-- ----------------------------
-- Table structure for t_test
-- ----------------------------
DROP TABLE IF EXISTS `t_test`;
CREATE TABLE `t_test` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `FIELD1` varchar(20) NOT NULL,
  `FIELD2` int(11) NOT NULL,
  `FIELD3` varchar(100) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_test
-- ----------------------------
INSERT INTO `t_test` VALUES ('1', '字段1', '1', 'mrbird@gmail.com', '2019-01-22 16:26:51');
INSERT INTO `t_test` VALUES ('2', '字段1', '1', 'mrbird0@gmail.com', '2019-01-23 03:01:03');
INSERT INTO `t_test` VALUES ('3', '字段1', '2', 'mrbird1@gmail.com', '2019-01-23 03:01:03');
INSERT INTO `t_test` VALUES ('4', '字段1', '3', 'mrbird2@gmail.com', '2019-01-23 03:01:03');
INSERT INTO `t_test` VALUES ('5', '字段1', '4', 'mrbird3@gmail.com', '2019-01-23 03:01:03');
INSERT INTO `t_test` VALUES ('6', '字段1', '5', 'mrbird4@gmail.com', '2019-01-23 03:01:03');
INSERT INTO `t_test` VALUES ('7', '字段1', '10', 'mrbird9@gmail.com', '2019-01-23 03:01:03');
INSERT INTO `t_test` VALUES ('8', '字段1', '1', 'mrbird0@gmail.com', '2019-01-23 03:03:49');
INSERT INTO `t_test` VALUES ('9', '字段1', '2', 'mrbird1@gmail.com', '2019-01-23 03:03:49');
INSERT INTO `t_test` VALUES ('10', '字段1', '3', 'mrbird2@gmail.com', '2019-01-23 03:03:49');
INSERT INTO `t_test` VALUES ('11', '字段1', '4', 'mrbird3@gmail.com', '2019-01-23 03:03:49');
INSERT INTO `t_test` VALUES ('12', '字段1', '5', 'mrbird4@gmail.com', '2019-01-23 03:03:49');
INSERT INTO `t_test` VALUES ('13', '字段1', '10', 'mrbird9@gmail.com', '2019-01-23 03:03:49');
INSERT INTO `t_test` VALUES ('14', '字段1', '1', 'mrbird0@gmail.com', '2019-01-23 03:07:47');
INSERT INTO `t_test` VALUES ('15', '字段1', '2', 'mrbird1@gmail.com', '2019-01-23 03:07:47');
INSERT INTO `t_test` VALUES ('16', '字段1', '3', 'mrbird2@gmail.com', '2019-01-23 03:07:47');
INSERT INTO `t_test` VALUES ('17', '字段1', '4', 'mrbird3@gmail.com', '2019-01-23 03:07:47');
INSERT INTO `t_test` VALUES ('18', '字段1', '5', 'mrbird4@gmail.com', '2019-01-23 03:07:47');
INSERT INTO `t_test` VALUES ('19', '字段1', '6', 'mrbird5@gmail.com', '2019-01-23 03:07:47');
INSERT INTO `t_test` VALUES ('20', '字段1', '1', 'mrbird0@gmail.com', '2019-01-23 03:08:09');
INSERT INTO `t_test` VALUES ('21', '字段1', '2', 'mrbird1@gmail.com', '2019-01-23 03:08:09');
INSERT INTO `t_test` VALUES ('22', '字段1', '3', 'mrbird2@gmail.com', '2019-01-23 03:08:09');
INSERT INTO `t_test` VALUES ('23', '字段1', '4', 'mrbird3@gmail.com', '2019-01-23 03:08:09');
INSERT INTO `t_test` VALUES ('24', '字段1', '5', 'mrbird4@gmail.com', '2019-01-23 03:08:09');
INSERT INTO `t_test` VALUES ('25', '字段1', '10', 'mrbird9@gmail.com', '2019-01-23 03:08:09');
INSERT INTO `t_test` VALUES ('26', '字段1', '1', 'mrbird0@gmail.com', '2019-01-23 03:19:52');
INSERT INTO `t_test` VALUES ('27', '字段1', '2', 'mrbird1@gmail.com', '2019-01-23 03:19:52');
INSERT INTO `t_test` VALUES ('28', '字段1', '3', 'mrbird2@gmail.com', '2019-01-23 03:19:52');
INSERT INTO `t_test` VALUES ('29', '字段1', '4', 'mrbird3@gmail.com', '2019-01-23 03:19:52');
INSERT INTO `t_test` VALUES ('30', '字段1', '5', 'mrbird4@gmail.com', '2019-01-23 03:19:52');
INSERT INTO `t_test` VALUES ('31', '字段1', '6', 'mrbird5@gmail.com', '2019-01-23 03:19:52');
INSERT INTO `t_test` VALUES ('32', '字段1', '1', 'mrbird0@gmail.com', '2019-01-23 03:20:56');
INSERT INTO `t_test` VALUES ('33', '字段1', '2', 'mrbird1@gmail.com', '2019-01-23 03:20:56');
INSERT INTO `t_test` VALUES ('34', '字段1', '3', 'mrbird2@gmail.com', '2019-01-23 03:20:56');
INSERT INTO `t_test` VALUES ('35', '字段1', '4', 'mrbird3@gmail.com', '2019-01-23 03:20:56');
INSERT INTO `t_test` VALUES ('36', '字段1', '5', 'mrbird4@gmail.com', '2019-01-23 03:20:56');
INSERT INTO `t_test` VALUES ('37', '字段1', '6', 'mrbird5@gmail.com', '2019-01-23 03:20:56');
INSERT INTO `t_test` VALUES ('38', '字段1', '1', 'mrbird0@gmail.com', '2019-01-23 06:12:38');
INSERT INTO `t_test` VALUES ('39', '字段1', '2', 'mrbird1@gmail.com', '2019-01-23 06:12:38');
INSERT INTO `t_test` VALUES ('40', '字段1', '3', 'mrbird2@gmail.com', '2019-01-23 06:12:38');
INSERT INTO `t_test` VALUES ('41', '字段1', '4', 'mrbird3@gmail.com', '2019-01-23 06:12:38');
INSERT INTO `t_test` VALUES ('42', '字段1', '5', 'mrbird4@gmail.com', '2019-01-23 06:12:38');
INSERT INTO `t_test` VALUES ('43', '字段1', '6', 'mrbird5@gmail.com', '2019-01-23 06:12:38');
INSERT INTO `t_test` VALUES ('44', '字段1', '7', 'mrbird6@gmail.com', '2019-01-23 06:12:38');
INSERT INTO `t_test` VALUES ('45', '字段1', '8', 'mrbird7@gmail.com', '2019-01-23 06:12:38');
INSERT INTO `t_test` VALUES ('46', '字段1', '9', 'mrbird8@gmail.com', '2019-01-23 06:12:38');
INSERT INTO `t_test` VALUES ('47', '字段1', '11', 'mrbird10@gmail.com', '2019-01-23 06:12:38');
INSERT INTO `t_test` VALUES ('48', '字段1', '12', 'mrbird11@gmail.com', '2019-01-23 06:12:38');
INSERT INTO `t_test` VALUES ('49', '字段1', '14', 'mrbird13@gmail.com', '2019-01-23 06:12:38');
INSERT INTO `t_test` VALUES ('50', '字段1', '15', 'mrbird14@gmail.com', '2019-01-23 06:12:38');
INSERT INTO `t_test` VALUES ('51', '字段1', '16', 'mrbird15@gmail.com', '2019-01-23 06:12:38');
INSERT INTO `t_test` VALUES ('52', '字段1', '18', 'mrbird17@gmail.com', '2019-01-23 06:12:38');
INSERT INTO `t_test` VALUES ('53', '字段1', '19', 'mrbird18@gmail.com', '2019-01-23 06:12:38');
INSERT INTO `t_test` VALUES ('54', '字段1', '20', 'mrbird19@gmail.com', '2019-01-23 06:12:38');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `USER_ID` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `USERNAME` varchar(50) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(128) NOT NULL COMMENT '密码',
  `DEPT_ID` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `EMAIL` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `MOBILE` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `STATUS` smallint(2) NOT NULL COMMENT '状态 0锁定 1有效',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `LAST_LOGIN_TIME` datetime DEFAULT NULL COMMENT '最近访问时间',
  `SSEX` smallint(2) DEFAULT NULL COMMENT '性别 0男 1女 2保密',
  `DESCRIPTION` varchar(100) DEFAULT NULL COMMENT '描述',
  `AVATAR` varchar(100) DEFAULT NULL COMMENT '用户头像',
  `DELETED` tinyint(4) DEFAULT '0' COMMENT '逻辑删除 0未删除，1已删除。',
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '458dcc406632343b19f970947b567150', '1', 'mrbird123@hotmail.com', '13455533233', '1', '2017-12-27 15:47:19', '2019-01-17 02:34:19', '2019-11-20 04:57:08', '2', '我是帅比作者。', 'ubnKSIfAJTxIgXOKlciN.png', '0');
INSERT INTO `t_user` VALUES ('2', 'liruyi', '458dcc406632343b19f970947b567150', '6', 'scott@qq.com', '15134627380', '1', '2017-12-29 16:16:39', '2019-01-18 00:59:09', '2019-11-20 08:01:08', '0', '我是scott，嗯嗯', 'jZUIxmJycoymBprLOUbT.png', '0');
INSERT INTO `t_user` VALUES ('12', 'xixi', '458dcc406632343b19f970947b567150', '6', 'jack@hotmail.com', null, '1', '2019-01-23 07:34:05', '2019-01-24 03:08:01', '2019-01-24 08:52:03', '0', null, 'default.jpg', '0');

-- ----------------------------
-- Table structure for t_user_config
-- ----------------------------
DROP TABLE IF EXISTS `t_user_config`;
CREATE TABLE `t_user_config` (
  `USER_ID` bigint(20) NOT NULL COMMENT '用户ID',
  `THEME` varchar(10) DEFAULT NULL COMMENT '系统主题 dark暗色风格，light明亮风格',
  `LAYOUT` varchar(10) DEFAULT NULL COMMENT '系统布局 side侧边栏，head顶部栏',
  `MULTI_PAGE` char(1) DEFAULT NULL COMMENT '页面风格 1多标签页 0单页',
  `FIX_SIDERBAR` char(1) DEFAULT NULL COMMENT '页面滚动是否固定侧边栏 1固定 0不固定',
  `FIX_HEADER` char(1) DEFAULT NULL COMMENT '页面滚动是否固定顶栏 1固定 0不固定',
  `COLOR` varchar(20) DEFAULT NULL COMMENT '主题颜色 RGB值',
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_user_config
-- ----------------------------
INSERT INTO `t_user_config` VALUES ('1', 'light', 'side', '1', '1', '1', 'rgb(24, 144, 255)');
INSERT INTO `t_user_config` VALUES ('2', 'light', 'head', '0', '1', '1', 'rgb(24, 144, 255)');
INSERT INTO `t_user_config` VALUES ('12', 'dark', 'side', '1', '1', '1', 'rgb(66, 185, 131)');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `USER_ID` bigint(20) NOT NULL COMMENT '用户ID',
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1');
INSERT INTO `t_user_role` VALUES ('2', '2');
INSERT INTO `t_user_role` VALUES ('12', '72');
INSERT INTO `t_user_role` VALUES ('1', '2');

-- ----------------------------
-- Procedure structure for num_from_person
-- ----------------------------
DROP PROCEDURE IF EXISTS `num_from_person`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `num_from_person`(IN age_num INT, OUT count_num INT)
    READS SQL DATA
BEGIN
SELECT COUNT(*) INTO count_num
FROM t_person
WHERE age=age_num;
END
;;
DELIMITER ;
