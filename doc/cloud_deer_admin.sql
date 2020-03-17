/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : cloud_deer_admin

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 635

 Date: 20/02/2019 14:36:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除  -1：已删除  0：正常',
  `parent_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
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

-- ----------------------------
-- Table structure for sys_dept_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_relation`;
CREATE TABLE `sys_dept_relation`  (
  `ancestor` int(11) NOT NULL COMMENT '祖先节点',
  `descendant` int(11) NOT NULL COMMENT '后代节点',
  PRIMARY KEY (`ancestor`, `descendant`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept_relation
-- ----------------------------
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

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据值',
  `label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `sort` decimal(10, 0) NOT NULL COMMENT '排序（升序）',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注信息',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (2, '9', '异常', 'log_type', '日志异常', 1, '2017-12-28 13:06:39', '2018-01-06 10:54:41', '', '0');
INSERT INTO `sys_dict` VALUES (3, '0', '正常', 'log_type', '正常', 1, '2018-05-11 23:52:57', '2018-05-11 23:52:57', '123', '0');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志标题',
  `service_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '服务ID',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remote_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作IP地址',
  `user_agent` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户代理',
  `request_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求URI',
  `method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作方式',
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作提交的数据',
  `time` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '执行时间',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记',
  `exception` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL COMMENT '菜单ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `permission` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '菜单权限标识',
  `path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端URL',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父菜单ID',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `component` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'VUE页面',
  `sort` int(11) NULL DEFAULT 1 COMMENT '排序值',
  `keep_alive` int(1) NULL DEFAULT 0 COMMENT '0-开启，1- 关闭',
  `type` int(1) NULL DEFAULT NULL COMMENT '菜单类型 （0菜单 1按钮）',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '逻辑删除标记(0--正常 1--删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', NULL, '/setting', -1, 'icon-quanxianguanli', 'Layout', 0, 0, 0, NULL, '2018-09-28 08:29:53', NULL, '2019-02-20 10:56:08', 0);
INSERT INTO `sys_menu` VALUES (2, '用户管理', NULL, 'user', 1, 'icon-yonghuguanli', 'views/admin/user/index', 1, 0, 0, NULL, '2017-11-02 22:24:37', NULL, '2018-09-28 09:00:41', 0);
INSERT INTO `sys_menu` VALUES (3, '用户新增', 'sys_user_add', NULL, 2, NULL, NULL, NULL, 0, 1, NULL, '2017-11-08 09:52:09', NULL, '2018-09-28 09:06:34', 0);
INSERT INTO `sys_menu` VALUES (4, '用户修改', 'sys_user_edit', NULL, 2, NULL, NULL, NULL, 0, 1, NULL, '2017-11-08 09:52:48', NULL, '2018-09-28 09:06:37', 0);
INSERT INTO `sys_menu` VALUES (5, '用户删除', 'sys_user_del', NULL, 2, NULL, NULL, NULL, 0, 1, NULL, '2017-11-08 09:54:01', NULL, '2018-09-28 09:06:42', 0);
INSERT INTO `sys_menu` VALUES (6, '菜单管理', NULL, 'menu', 1, 'icon-caidanguanli', 'views/admin/menu/index', 2, 0, 0, NULL, '2017-11-08 09:57:27', NULL, '2018-09-28 09:00:45', 0);
INSERT INTO `sys_menu` VALUES (7, '菜单新增', 'sys_menu_add', NULL, 6, NULL, NULL, NULL, 0, 1, NULL, '2017-11-08 10:15:53', NULL, '2018-09-28 09:07:16', 0);
INSERT INTO `sys_menu` VALUES (8, '菜单修改', 'sys_menu_edit', NULL, 6, NULL, NULL, NULL, 0, 1, NULL, '2017-11-08 10:16:23', NULL, '2018-09-28 09:07:18', 0);
INSERT INTO `sys_menu` VALUES (9, '菜单删除', 'sys_menu_del', NULL, 6, NULL, NULL, NULL, 0, 1, NULL, '2017-11-08 10:16:43', NULL, '2018-09-28 09:07:22', 0);
INSERT INTO `sys_menu` VALUES (10, '角色管理', NULL, 'role', 1, 'icon-jiaoseguanli', 'views/admin/role/index', 3, 0, 0, NULL, '2017-11-08 10:13:37', NULL, '2018-09-28 09:00:48', 0);
INSERT INTO `sys_menu` VALUES (11, '角色新增', 'sys_role_add', NULL, 10, NULL, NULL, NULL, 0, 1, NULL, '2017-11-08 10:14:18', NULL, '2018-09-28 09:07:46', 0);
INSERT INTO `sys_menu` VALUES (12, '角色修改', 'sys_role_edit', NULL, 10, NULL, NULL, NULL, 0, 1, NULL, '2017-11-08 10:14:41', NULL, '2018-09-28 09:07:49', 0);
INSERT INTO `sys_menu` VALUES (13, '角色删除', 'sys_role_del', NULL, 10, NULL, NULL, NULL, 0, 1, NULL, '2017-11-08 10:14:59', NULL, '2018-09-28 09:07:53', 0);
INSERT INTO `sys_menu` VALUES (14, '分配权限', 'sys_role_perm', NULL, 10, NULL, NULL, NULL, 0, 1, NULL, '2018-04-20 07:22:55', NULL, '2018-09-28 09:13:23', 0);
INSERT INTO `sys_menu` VALUES (15, '部门管理', NULL, 'dept', 1, 'icon-web-icon-', 'views/admin/dept/index', 4, 0, 0, NULL, '2018-01-20 13:17:19', NULL, '2018-12-09 16:35:12', 0);
INSERT INTO `sys_menu` VALUES (16, '部门新增', 'sys_dept_add', NULL, 15, NULL, NULL, NULL, 0, 1, NULL, '2018-01-20 14:56:16', NULL, '2018-09-28 09:08:13', 0);
INSERT INTO `sys_menu` VALUES (17, '部门修改', 'sys_dept_edit', NULL, 15, NULL, NULL, NULL, 0, 1, NULL, '2018-01-20 14:56:59', NULL, '2018-09-28 09:08:16', 0);
INSERT INTO `sys_menu` VALUES (18, '部门删除', 'sys_dept_del', NULL, 15, NULL, NULL, NULL, 0, 1, NULL, '2018-01-20 14:57:28', NULL, '2018-09-28 09:08:18', 0);
INSERT INTO `sys_menu` VALUES (19, 'admin管理', NULL, '/admin', -1, 'icon-xitongguanli', 'Layout', 1, 0, 0, NULL, '2017-11-07 20:56:00', NULL, '2019-02-20 10:56:31', 0);
INSERT INTO `sys_menu` VALUES (20, '日志管理', NULL, 'log', 19, 'icon-rizhiguanli', 'views/admin/log/index', 5, 0, 0, NULL, '2017-11-20 14:06:22', NULL, '2018-09-28 09:01:52', 0);
INSERT INTO `sys_menu` VALUES (21, '日志删除', 'sys_log_del', NULL, 20, NULL, NULL, NULL, 0, 1, NULL, '2017-11-20 20:37:37', NULL, '2018-09-28 09:08:44', 0);
INSERT INTO `sys_menu` VALUES (22, '字典管理', NULL, 'dict', 19, 'icon-navicon-zdgl', 'views/admin/dict/index', 6, 0, 0, NULL, '2017-11-29 11:30:52', NULL, '2018-09-28 09:01:47', 0);
INSERT INTO `sys_menu` VALUES (23, '字典删除', 'sys_dict_del', NULL, 22, NULL, NULL, NULL, 0, 1, NULL, '2017-11-29 11:30:11', NULL, '2018-09-28 09:09:10', 0);
INSERT INTO `sys_menu` VALUES (24, '字典新增', 'sys_dict_add', NULL, 22, NULL, NULL, NULL, 0, 1, NULL, '2018-05-11 22:34:55', NULL, '2018-09-28 09:09:12', 0);
INSERT INTO `sys_menu` VALUES (25, '字典修改', 'sys_dict_edit', NULL, 22, NULL, NULL, NULL, 0, 1, NULL, '2018-05-11 22:36:03', NULL, '2018-09-28 09:09:16', 0);
INSERT INTO `sys_menu` VALUES (26, '代码生成', '', 'gen', 19, 'icon-weibiaoti46', 'views/gen/index', 8, 0, 0, NULL, '2018-01-20 13:17:19', NULL, '2018-11-24 05:21:01', 0);
INSERT INTO `sys_menu` VALUES (27, '终端管理', '', 'client', 19, 'icon-shouji', 'views/admin/client/index', 9, 0, 0, NULL, '2018-01-20 13:17:19', NULL, '2018-09-28 09:01:43', 0);
INSERT INTO `sys_menu` VALUES (28, '客户端新增', 'sys_client_add', NULL, 27, '1', NULL, NULL, 0, 1, NULL, '2018-05-15 21:35:18', NULL, '2018-09-28 09:10:25', 0);
INSERT INTO `sys_menu` VALUES (29, '客户端修改', 'sys_client_edit', NULL, 27, NULL, NULL, NULL, 0, 1, NULL, '2018-05-15 21:37:06', NULL, '2018-09-28 09:10:27', 0);
INSERT INTO `sys_menu` VALUES (30, '客户端删除', 'sys_client_del', NULL, 27, NULL, NULL, NULL, 0, 1, NULL, '2018-05-15 21:39:16', NULL, '2018-09-28 09:10:30', 0);
INSERT INTO `sys_menu` VALUES (31, '服务监控', NULL, 'http://139.224.200.249:135', 19, 'icon-server', NULL, 10, 0, 0, NULL, '2018-06-26 10:50:32', NULL, '2019-02-01 20:41:30', 0);
INSERT INTO `sys_menu` VALUES (32, '令牌管理', NULL, 'token', 19, 'icon-denglvlingpai', 'views/admin/token/index', 11, 0, 0, NULL, '2018-09-04 05:58:41', NULL, '2018-09-28 09:01:38', 0);
INSERT INTO `sys_menu` VALUES (33, '令牌删除', 'sys_token_del', NULL, 32, NULL, NULL, 1, 0, 1, NULL, '2018-09-04 05:59:50', NULL, '2018-09-28 09:11:24', 0);
INSERT INTO `sys_menu` VALUES (34, '一级菜单', NULL, '/crud', -1, 'icon-caidanguanli', '', 4, 0, 0, NULL, '2018-08-28 01:50:22', NULL, '2018-09-28 08:58:20', 0);
INSERT INTO `sys_menu` VALUES (35, '一级菜单', NULL, 'index', 34, 'icon-caidanguanli', 'views/crud/index', 1, 0, 0, NULL, '2018-08-28 01:50:48', NULL, '2018-11-21 17:48:19', 1);
INSERT INTO `sys_menu` VALUES (36, '二级菜单', NULL, 'crud', 35, 'icon-caidanguanli', 'views/crud/index', 1, 0, 0, NULL, '2018-08-28 01:51:23', NULL, '2018-11-21 17:47:40', 1);
INSERT INTO `sys_menu` VALUES (37, '二级菜单', NULL, '', 34, 'icon-caidanguanli', '', 1, 0, 0, NULL, '2018-11-21 17:49:18', NULL, '2018-11-21 17:53:25', 0);
INSERT INTO `sys_menu` VALUES (38, '二级菜单', NULL, 'index', 37, 'icon-caidanguanli', 'views/crud/index', 1, 0, 0, NULL, '2018-11-21 17:53:51', NULL, '2018-12-20 14:26:53', 1);
INSERT INTO `sys_menu` VALUES (39, '系统官网', NULL, 'https://pig4cloud.com/#/', -1, 'icon-guanwangfangwen', NULL, 9, 0, 0, NULL, '2019-01-17 17:05:19', NULL, '2019-01-17 17:29:06', 0);

-- ----------------------------
-- Table structure for sys_oauth_client
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client`;
CREATE TABLE `sys_oauth_client`  (
  `client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
  `access_token_validity` int(11) NULL DEFAULT 0,
  `refresh_token_validity` int(11) NULL DEFAULT 0,
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oauth_client
-- ----------------------------
INSERT INTO `sys_oauth_client` VALUES ('admin', '', '97db7920a2894341b8642d628b3b192d', 'server', 'password,refresh_token', '', '', NULL, NULL, '', 'true');
INSERT INTO `sys_oauth_client` VALUES ('app', '', '283fef90f24a457b9f698cba972b16ec', 'server', 'password,refresh_token', '', '', NULL, NULL, '', 'true');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `role_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'admin', 'ROLE_ADMIN', '超级管理员', '', '2017-10-29 15:45:51', '', '2018-04-22 11:40:29', 0);
INSERT INTO `sys_role` VALUES (14, 'demo', 'demo', 'demo用户', '', '2018-04-20 07:14:32', '', '2018-06-12 18:43:22', 0);
INSERT INTO `sys_role` VALUES (15, 'demo2222222', 'demo2', 'demo2', '', '2018-06-12 18:59:38', '', '2018-08-02 10:48:09', 0);
INSERT INTO `sys_role` VALUES (16, 'demo3', 'demo3', 'demo', '', '2018-06-13 07:07:25', '', '2018-06-24 17:16:47', 1);
INSERT INTO `sys_role` VALUES (17, 'manager', 'ROLE_MANAGER', '经理级', '', '2019-01-19 19:30:38', '', '2019-01-19 19:30:38', 0);
INSERT INTO `sys_role` VALUES (18, 'root', 'ROLE_ROOT', '超级管理员', '', '2019-01-19 19:31:19', '', '2019-01-19 19:31:19', 0);
INSERT INTO `sys_role` VALUES (19, '123321', '123123', 'ddddd', '', '2019-01-19 20:21:15', '', '2019-01-19 21:30:46', 1);
INSERT INTO `sys_role` VALUES (20, 'aa', 'aaaaaaa', 'aaaaaaaa', '', '2019-01-19 20:47:20', '', '2019-01-19 21:30:44', 1);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) NULL DEFAULT 0 COMMENT '角色ID',
  `dept_id` int(20) NULL DEFAULT 0 COMMENT '部门ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (11, 1, 1);
INSERT INTO `sys_role_dept` VALUES (16, 14, 6);
INSERT INTO `sys_role_dept` VALUES (18, 16, 8);
INSERT INTO `sys_role_dept` VALUES (23, 15, 5);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  `is_delete` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (1, 1, 1);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (2, 1, 2);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (3, 1, 3);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (4, 1, 4);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (5, 1, 5);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (6, 1, 6);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (7, 1, 7);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (8, 1, 8);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (9, 1, 9);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (10, 1, 10);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (11, 1, 11);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (12, 1, 12);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (13, 1, 13);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (14, 1, 14);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (15, 1, 15);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (16, 1, 16);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (17, 1, 17);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (18, 1, 18);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (19, 1, 19);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (20, 1, 20);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (21, 1, 21);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (22, 1, 22);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (23, 1, 23);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (24, 1, 24);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (25, 1, 25);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (26, 1, 26);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (27, 1, 27);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (28, 1, 28);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (29, 1, 29);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (30, 1, 30);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (31, 1, 31);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (32, 1, 32);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (33, 1, 33);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (34, 1, 39);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (35, 2, 1);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (36, 2, 2);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (37, 2, 3);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (38, 2, 4);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (39, 2, 5);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (40, 2, 6);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (41, 2, 7);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (42, 2, 8);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (43, 2, 9);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (44, 2, 10);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (45, 2, 11);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (46, 2, 12);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (47, 2, 13);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (48, 2, 14);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (49, 2, 15);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (50, 2, 16);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (51, 2, 17);
INSERT INTO `sys_role_menu` (id,role_id,menu_id) VALUES (52, 2, 18);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '随机盐',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '简介',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '头像',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '0-正常，1-删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$Mxg6tIAw3sHY1oa/yzaWqujV7KE4zd2mjNa5xrRrWPzQDc0wzaw6K', '0b6ba786-a522-42ca-92a5-abc1a523c02e', '13817633957', 'c3535780-3006-4be3-963a-8dd5827a9ea9.png', 'admin', '2019-01-24 15:41:09', 'admin', '2019-01-24 15:41:09', 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
