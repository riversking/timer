/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : cloud_deer_admin

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 20/01/2019 09:03:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(50) NOT NULL COMMENT '修改人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (1, '山东农信', 1, 'tester', '2018-01-22 19:00:23', 'tester', '2019-01-20 08:59:09', 0, 0);
INSERT INTO `sys_dept` VALUES (2, '沙县国际', 1, 'tester', '2018-01-22 19:00:38', 'tester', '2019-01-20 08:59:10', 0, 0);
INSERT INTO `sys_dept` VALUES (3, '潍坊农信', 1, 'tester', '2018-01-22 19:00:44', 'tester', '2019-01-20 08:59:12', 0, 1);
INSERT INTO `sys_dept` VALUES (4, '高新农信', 1, 'tester', '2018-01-22 19:00:52', 'tester', '2019-01-20 08:59:13', 0, 3);
INSERT INTO `sys_dept` VALUES (5, '院校农信', 1, 'tester', '2018-01-22 19:00:57', 'tester', '2019-01-20 08:59:14', 0, 4);
INSERT INTO `sys_dept` VALUES (6, '潍坊学院农信', 115, 'tester', '2018-01-22 19:01:06', 'tester', '2019-01-20 08:59:15', 0, 5);
INSERT INTO `sys_dept` VALUES (7, '山东沙县', 1, 'tester', '2018-01-22 19:01:57', 'tester', '2019-01-20 08:59:16', 0, 2);
INSERT INTO `sys_dept` VALUES (8, '潍坊沙县', 1, 'tester', '2018-01-22 19:02:03', 'tester', '2019-01-20 08:59:18', 0, 7);
INSERT INTO `sys_dept` VALUES (9, '高新沙县', 1, 'tester', '2018-01-22 19:02:14', 'tester', '2019-01-20 08:59:17', 0, 8);
INSERT INTO `sys_dept` VALUES (10, '测试', 110, 'tester', '2018-06-26 21:26:59', 'tester', '2019-01-20 08:59:21', 1, 5);
COMMIT;

-- ----------------------------
-- Table structure for sys_dept_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_relation`;
CREATE TABLE `sys_dept_relation` (
  `ancestor` int(11) NOT NULL COMMENT '祖先节点',
  `descendant` int(11) NOT NULL COMMENT '后代节点',
  PRIMARY KEY (`ancestor`,`descendant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_dept_relation
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept_relation` VALUES (1, 1);
INSERT INTO `sys_dept_relation` VALUES (1, 3);
INSERT INTO `sys_dept_relation` VALUES (1, 4);
INSERT INTO `sys_dept_relation` VALUES (1, 5);
INSERT INTO `sys_dept_relation` VALUES (1, 6);
INSERT INTO `sys_dept_relation` VALUES (2, 2);
INSERT INTO `sys_dept_relation` VALUES (2, 7);
INSERT INTO `sys_dept_relation` VALUES (2, 8);
INSERT INTO `sys_dept_relation` VALUES (2, 9);
INSERT INTO `sys_dept_relation` VALUES (2, 11);
INSERT INTO `sys_dept_relation` VALUES (3, 3);
INSERT INTO `sys_dept_relation` VALUES (3, 4);
INSERT INTO `sys_dept_relation` VALUES (3, 5);
INSERT INTO `sys_dept_relation` VALUES (3, 6);
INSERT INTO `sys_dept_relation` VALUES (4, 4);
INSERT INTO `sys_dept_relation` VALUES (4, 5);
INSERT INTO `sys_dept_relation` VALUES (4, 6);
INSERT INTO `sys_dept_relation` VALUES (5, 5);
INSERT INTO `sys_dept_relation` VALUES (5, 6);
INSERT INTO `sys_dept_relation` VALUES (6, 6);
INSERT INTO `sys_dept_relation` VALUES (7, 7);
INSERT INTO `sys_dept_relation` VALUES (7, 8);
INSERT INTO `sys_dept_relation` VALUES (7, 9);
INSERT INTO `sys_dept_relation` VALUES (7, 11);
INSERT INTO `sys_dept_relation` VALUES (8, 8);
INSERT INTO `sys_dept_relation` VALUES (8, 9);
INSERT INTO `sys_dept_relation` VALUES (8, 11);
INSERT INTO `sys_dept_relation` VALUES (9, 9);
INSERT INTO `sys_dept_relation` VALUES (11, 11);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据值',
  `label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '备注信息',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES (2, '9', '异常', 'log_type', '日志异常', 1, '2017-12-28 13:06:39', '2018-01-06 10:54:41', '', '0');
INSERT INTO `sys_dict` VALUES (3, '0', '正常', 'log_type', '正常', 1, '2018-05-11 23:52:57', '2018-05-11 23:52:57', '123', '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '日志标题',
  `service_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '服务ID',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remote_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '操作IP地址',
  `user_agent` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '用户代理',
  `request_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '请求URI',
  `method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '操作方式',
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '操作提交的数据',
  `time` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '执行时间',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '删除标记',
  `exception` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '异常信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL COMMENT '菜单ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `permission` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '菜单权限标识',
  `path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '前端URL',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父菜单ID',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '图标',
  `component` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT 'VUE页面',
  `sort` int(11) DEFAULT '1' COMMENT '排序值',
  `type` int(1) NOT NULL DEFAULT '0' COMMENT '菜单类型 （0菜单 1按钮）',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `iframe` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT 'iframe路径',
  `is_content` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '0表示有 1表示没有',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '0--正常 1--删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', '', '/setting', -1, 'settings', 'Main', 1, '0', 'admin', '2017-11-07 20:56:00', 'admin', '2019-02-14 14:10:01', '', '1', '0');
INSERT INTO `sys_menu` VALUES (2, '用户管理', '', 'user', 1, 'person-add', 'Setting/User', 2, '0', 'admin', '2017-11-02 22:24:37', 'admin', '2019-02-14 14:10:01', '', '0', '0');
INSERT INTO `sys_menu` VALUES (3, '菜单管理', '', 'menu', 1, 'ios-list-outline', 'Setting/Menu', 3, '0', 'admin', '2017-11-08 09:57:27', 'admin', '2019-02-14 14:10:01', '', '1', '0');
INSERT INTO `sys_menu` VALUES (4, '角色管理', '', 'role', 1, 'person-stalker', 'Setting/Role', 4, '0', 'admin', '2017-11-08 10:13:37', 'admin', '2019-02-14 14:10:01', '', '0', '0');
INSERT INTO `sys_menu` VALUES (5, '日志管理', '', 'log', 1, 'ios-eye', 'Setting/Log', 5, '0', 'admin', '2017-11-20 14:06:22', 'admin', '2019-02-14 14:10:01', '', '1', '1');
INSERT INTO `sys_menu` VALUES (6, '字典管理', '', 'dict', 1, 'ios-book-outline', 'Setting/Dict', 6, '0', 'admin', '2017-11-29 11:30:52', 'admin', '2019-02-14 14:10:01', '', '0', '1');
INSERT INTO `sys_menu` VALUES (7, '部门管理', '', 'dept', 1, 'ios-people-outline', 'Setting/Dept', 7, '0', 'admin', '2018-01-20 13:17:19', 'admin', '2019-02-14 14:10:01', '', '1', '1');
INSERT INTO `sys_menu` VALUES (8, '服务监控', '', 'server', 1, 'stats-bars', 'main-components/iframe', 8, '0', 'admin', '2018-01-22 12:30:41', 'admin', '2019-02-14 14:10:01', 'http://127.0.0.1:5000', '1', '1');
INSERT INTO `sys_menu` VALUES (9, '分布式任务监控', '', 'xxlJob', 1, 'monitor', 'main-components/iframe', 9, '0', 'admin', '2018-01-23 10:53:33', 'admin', '2019-02-14 14:10:01', 'http://127.0.0.1:8181', '1', '1');
INSERT INTO `sys_menu` VALUES (10, 'zipkin监控', '', 'http://139.224.200.249:5002', 8, 'icon-jiankong', '', 11, '0', 'admin', '2018-01-23 10:55:18', 'admin', '2019-02-14 14:10:01', '', '', '1');
INSERT INTO `sys_menu` VALUES (11, 'pinpoint监控', '', 'https://pinpoint.pig4cloud.com', 8, 'icon-xiazaihuancun', '', 10, '0', 'admin', '2018-01-25 11:08:52', 'admin', '2019-02-14 14:10:01', '', '', '1');
INSERT INTO `sys_menu` VALUES (12, '缓存状态', '', 'http://139.224.200.249:8585', 8, 'icon-ecs-status', '', 12, '0', 'admin', '2018-01-23 10:56:11', 'admin', '2019-02-14 14:10:01', '', '', '1');
INSERT INTO `sys_menu` VALUES (13, 'ELK状态', '', 'http://139.224.200.249:5601', 8, 'icon-ecs-status', '', 13, '0', 'admin', '2018-01-23 10:55:47', 'admin', '2019-02-14 14:10:01', '', '', '1');
INSERT INTO `sys_menu` VALUES (14, '接口文档', '', 'http://139.224.200.249:9999/swagger-ui.html', 8, 'icon-wendangdocument72', '', 14, '0', 'admin', '2018-01-23 10:56:43', 'admin', '2019-02-14 14:10:01', '', '', '1');
INSERT INTO `sys_menu` VALUES (15, '任务监控', '', 'http://139.224.200.249:8899', 8, 'icon-jiankong', '', 15, '0', 'admin', '2018-01-23 10:55:18', 'admin', '2019-02-14 14:10:01', '', '', '1');
INSERT INTO `sys_menu` VALUES (22, '用户新增', 'sys_user_add', '', 2, '', '', NULL, '1', 'admin', '2017-11-08 09:52:09', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (23, '用户修改', 'sys_user_edit', '', 2, '', '', NULL, '1', 'admin', '2017-11-08 09:52:48', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (24, '用户删除', 'sys_user_del', '', 2, '', '', NULL, '1', 'admin', '2017-11-08 09:54:01', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (25, 'haha', 'sys', '', 2, '', '', 1, '', 'admin', '2018-07-20 05:53:35', 'admin', '2019-02-14 14:10:01', '', '', '1');
INSERT INTO `sys_menu` VALUES (32, '菜单新增', 'sys_menu_add', '', 3, '', '', NULL, '1', 'admin', '2017-11-08 10:15:53', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (33, '菜单修改', 'sys_menu_edit', '', 3, '', '', NULL, '1', 'admin', '2017-11-08 10:16:23', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (34, '菜单删除', 'sys_menu_del', '', 3, '', '', NULL, '1', 'admin', '2017-11-08 10:16:43', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (42, '角色新增', 'sys_role_add', '', 4, '', '', NULL, '1', 'admin', '2017-11-08 10:14:18', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (43, '角色修改', 'sys_role_edit', '', 4, '', '', NULL, '1', 'admin', '2017-11-08 10:14:41', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (44, '角色删除', 'sys_role_del', '', 4, '', '', NULL, '1', 'admin', '2017-11-08 10:14:59', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (45, '分配权限', 'sys_role_perm', '', 4, '', '', NULL, '1', 'admin', '2018-04-20 07:22:55', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (52, '日志删除', 'sys_log_del', '', 5, '', '', NULL, '1', 'admin', '2017-11-20 20:37:37', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (56, '哈哈', 'sys_user_del', '', 24, '', '', 1, '', 'admin', '2018-07-24 07:51:18', 'admin', '2019-02-14 14:10:01', '', '', '1');
INSERT INTO `sys_menu` VALUES (62, '字典删除', 'sys_dict_del', '', 6, '', '', NULL, '1', 'admin', '2017-11-29 11:30:11', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (63, '字典新增', 'sys_dict_add', '', 6, '', '', NULL, '1', 'admin', '2018-05-11 22:34:55', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (64, '字典修改', 'sys_dict_edit', '', 6, '', '', NULL, '1', 'admin', '2018-05-11 22:36:03', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (72, '部门新增', 'sys_dept_add', '', 7, '', '', NULL, '1', 'admin', '2018-01-20 14:56:16', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (73, '部门修改', 'sys_dept_edit', '', 7, '', '', NULL, '1', 'admin', '2018-01-20 14:56:59', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (74, '部门删除', 'sys_dept_del', '', 7, '', '', NULL, '1', 'admin', '2018-01-20 14:57:28', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (100, '客户端管理', '', 'client', 1, 'steam', 'Setting/Client', 10, '0', 'admin', '2018-01-20 13:17:19', 'admin', '2019-02-14 14:10:01', '', '0', '0');
INSERT INTO `sys_menu` VALUES (101, '客户端新增', 'sys_client_add', '', 100, '1', '', NULL, '1', 'admin', '2018-05-15 21:35:18', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (102, '客户端修改', 'sys_client_edit', '', 100, '', '', NULL, '1', 'admin', '2018-05-15 21:37:06', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (103, '客户端删除', 'sys_client_del', '', 100, '', '', NULL, '1', 'admin', '2018-05-15 21:39:16', 'admin', '2019-02-14 14:10:01', '', '', '0');
INSERT INTO `sys_menu` VALUES (200, '服务监控', '', 'http://127.0.0.1:5001', 1, 'icon-msnui-supervise', '', 9, '0', 'admin', '2018-06-26 10:50:32', 'admin', '2019-02-14 14:10:01', '', '', '1');
COMMIT;



-- ----------------------------
-- Table structure for sys_oauth_client
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client`;
CREATE TABLE `sys_oauth_client` (
  `client_id` varchar(32) NOT NULL,
  `resource_ids` varchar(256) DEFAULT '',
  `client_secret` varchar(256) DEFAULT '',
  `scope` varchar(256) DEFAULT '',
  `authorized_grant_types` varchar(256) DEFAULT '',
  `web_server_redirect_uri` varchar(256) DEFAULT '',
  `authorities` varchar(256) DEFAULT '',
  `access_token_validity` int(11) DEFAULT '0',
  `refresh_token_validity` int(11) DEFAULT '0',
  `additional_information` varchar(4096) DEFAULT '',
  `autoapprove` varchar(256) DEFAULT '',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_oauth_client
-- ----------------------------
BEGIN;
INSERT INTO `sys_oauth_client` VALUES ('admin', '', '97db7920a2894341b8642d628b3b192d', 'server', 'password,refresh_token', '', '', NULL, NULL, '', 'true');
INSERT INTO `sys_oauth_client` VALUES ('app', '', '283fef90f24a457b9f698cba972b16ec', 'server', 'password,refresh_token', '', '', NULL, NULL, '', 'true');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `role_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, 'admin', 'ROLE_ADMIN', '超级管理员', '', '2017-10-29 15:45:51', '', '2018-04-22 11:40:29', 0);
INSERT INTO `sys_role` VALUES (14, 'demo', 'demo', 'demo用户', '', '2018-04-20 07:14:32', '', '2018-06-12 18:43:22', 0);
INSERT INTO `sys_role` VALUES (15, 'demo2222222', 'demo2', 'demo2', '', '2018-06-12 18:59:38', '', '2018-08-02 10:48:09', 0);
INSERT INTO `sys_role` VALUES (16, 'demo3', 'demo3', 'demo', '', '2018-06-13 07:07:25', '', '2018-06-24 17:16:47', 1);
INSERT INTO `sys_role` VALUES (17, 'manager', 'ROLE_MANAGER', '经理级', '', '2019-01-19 19:30:38', '', '2019-01-19 19:30:38', 0);
INSERT INTO `sys_role` VALUES (18, 'root', 'ROLE_ROOT', '超级管理员', '', '2019-01-19 19:31:19', '', '2019-01-19 19:31:19', 0);
INSERT INTO `sys_role` VALUES (19, '123321', '123123', 'ddddd', '', '2019-01-19 20:21:15', '', '2019-01-19 21:30:46', 1);
INSERT INTO `sys_role` VALUES (20, 'aa', 'aaaaaaa', 'aaaaaaaa', '', '2019-01-19 20:47:20', '', '2019-01-19 21:30:44', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) DEFAULT '0' COMMENT '角色ID',
  `dept_id` int(20) DEFAULT '0' COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_dept` VALUES (11, 1, 1);
INSERT INTO `sys_role_dept` VALUES (16, 14, 6);
INSERT INTO `sys_role_dept` VALUES (18, 16, 8);
INSERT INTO `sys_role_dept` VALUES (23, 15, 5);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '随机盐',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '简介',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '头像',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '0-正常，1-删除',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
