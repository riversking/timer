/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : cloud_deer_account

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 03/12/2018 17:21:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cd_account
-- ----------------------------
DROP TABLE IF EXISTS `cd_account`;
CREATE TABLE `cd_account`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '授权账号',
  `account_pwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '授权账户密码',
  `user_id` int(10) NOT NULL DEFAULT 0 COMMENT '关联用户id',
  `is_share` int(3) NOT NULL DEFAULT 0 COMMENT '是否共享0.否1.是',
  `head_portrait` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '\'\'' COMMENT '授权账户头像',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `is_sort` int(1) NOT NULL DEFAULT 0 COMMENT '排序',
  `uuid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'uuid',
  `price` decimal(4, 1) NOT NULL DEFAULT 0.0 COMMENT '账号价格',
  `platform_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '授权平台',
  `create_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_user_id` int(11) NOT NULL DEFAULT 0 COMMENT '创建人id',
  `update_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_user_id` int(11) NOT NULL DEFAULT 0 COMMENT '更新人id',
  `record_status` int(1) NOT NULL DEFAULT 1 COMMENT '是否有效：0 无效，1 有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '授权账户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cd_account
-- ----------------------------
INSERT INTO `cd_account` VALUES (1, 'kingjames-6236@hotmail.com', 'king1992', 1, 0, 'https://tva3.sinaimg.cn/crop.1.0.747.747.180/9c97410djw8f8v0g94c8fj20ku0krdhh.jpg', 'Cleck-King', 0, '738de9c4-cb58-45ae-8df7-03038659a2bc', 0.0, 'weibo', '2018-10-24 15:00:30', 1, '2018-10-24 15:00:30', 1, 1);

-- ----------------------------
-- Table structure for cd_account_toutiao
-- ----------------------------
DROP TABLE IF EXISTS `cd_account_toutiao`;
CREATE TABLE `cd_account_toutiao`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` int(10) NOT NULL DEFAULT 0 COMMENT '授权账户id',
  `followers_count` int(11) NOT NULL DEFAULT 0 COMMENT '粉丝数',
  `publish_count` int(11) NOT NULL DEFAULT 0 COMMENT '发布数量',
  `read_count` int(11) NOT NULL DEFAULT 0 COMMENT '阅读次数',
  `create_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_user_id` int(11) NOT NULL DEFAULT 0 COMMENT '创建人id',
  `update_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_user_id` int(11) NOT NULL DEFAULT 0 COMMENT '更新人id',
  `record_status` int(1) NOT NULL DEFAULT 1 COMMENT '是否有效：0 无效，1 有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '授权账户-头条表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cd_account_wechat
-- ----------------------------
DROP TABLE IF EXISTS `cd_account_wechat`;
CREATE TABLE `cd_account_wechat`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` int(10) NOT NULL DEFAULT 0 COMMENT '授权账户id',
  `share_user` int(5) NOT NULL DEFAULT 0 COMMENT '总用户量',
  `send_count` int(5) NOT NULL DEFAULT 0 COMMENT '发布量',
  `share_count` int(5) NOT NULL DEFAULT 0 COMMENT '转发次数',
  `create_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_user_id` int(11) NOT NULL DEFAULT 0 COMMENT '创建人id',
  `update_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_user_id` int(11) NOT NULL DEFAULT 0 COMMENT '更新人id',
  `record_status` int(1) NOT NULL DEFAULT 1 COMMENT '是否有效：0 无效，1 有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '授权账户-微信表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cd_account_weibo
-- ----------------------------
DROP TABLE IF EXISTS `cd_account_weibo`;
CREATE TABLE `cd_account_weibo`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` int(10) NOT NULL DEFAULT 0 COMMENT '授权账户id',
  `followers_count` int(5) NOT NULL DEFAULT 0 COMMENT '粉丝数',
  `friends_count` int(5) NOT NULL DEFAULT 0 COMMENT '关注数',
  `statuses_count` int(5) NOT NULL DEFAULT 0 COMMENT '微博数',
  `create_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_user_id` int(11) NOT NULL DEFAULT 0 COMMENT '创建人id',
  `update_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_user_id` int(11) NOT NULL DEFAULT 0 COMMENT '更新人id',
  `record_status` int(1) NOT NULL DEFAULT 1 COMMENT '是否有效：0 无效，1 有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '授权账户-微博表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cd_account_weibo
-- ----------------------------
INSERT INTO `cd_account_weibo` VALUES (1, 1, 114, 655, 1183, '2018-10-24 15:00:30', 1, '2018-10-24 15:00:30', 1, 1);

-- ----------------------------
-- Table structure for cd_toutiao_monitor_2018
-- ----------------------------
DROP TABLE IF EXISTS `cd_toutiao_monitor_2018`;
CREATE TABLE `cd_toutiao_monitor_2018`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` int(11) NOT NULL COMMENT '授权账户id',
  `send_volume` int(11) NOT NULL DEFAULT 0 COMMENT '发布量',
  `read_volume` int(11) NOT NULL DEFAULT 0 COMMENT '阅读量',
  `repost_volume` int(11) NOT NULL DEFAULT 0 COMMENT '转发量',
  `fans_num` int(11) NOT NULL DEFAULT 0 COMMENT '粉丝数',
  `comment_volume` int(11) NOT NULL DEFAULT 0 COMMENT '评论量',
  `likes_volume` int(11) NOT NULL DEFAULT 0 COMMENT '点赞量',
  `private_letter_volume` int(11) NOT NULL DEFAULT 0 COMMENT '私信量',
  `message_volume` int(11) NOT NULL DEFAULT 0 COMMENT '留言量',
  `create_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_user_id` int(11) NOT NULL DEFAULT 0 COMMENT '创建人id',
  `update_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_user_id` int(11) NOT NULL DEFAULT 0 COMMENT '更新人id',
  `record_status` int(1) NOT NULL DEFAULT 1 COMMENT '是否有效：0 无效，1 有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cd_weibo_monitor_2018
-- ----------------------------
DROP TABLE IF EXISTS `cd_weibo_monitor_2018`;
CREATE TABLE `cd_weibo_monitor_2018`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` int(11) NOT NULL COMMENT '授权账户id',
  `send_volume` int(11) NOT NULL DEFAULT 0 COMMENT '发布量',
  `read_volume` int(11) NOT NULL DEFAULT 0 COMMENT '阅读量',
  `repost_volume` int(11) NOT NULL DEFAULT 0 COMMENT '转发量',
  `fans_num` int(11) NOT NULL DEFAULT 0 COMMENT '粉丝数',
  `comment_volume` int(11) NOT NULL DEFAULT 0 COMMENT '评论量',
  `likes_volume` int(11) NOT NULL DEFAULT 0 COMMENT '点赞量',
  `private_letter_volume` int(11) NOT NULL DEFAULT 0 COMMENT '私信量',
  `message_volume` int(11) NOT NULL DEFAULT 0 COMMENT '留言量',
  `create_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_user_id` int(11) NOT NULL DEFAULT 0 COMMENT '创建人id',
  `update_date` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_user_id` int(11) NOT NULL DEFAULT 0 COMMENT '更新人id',
  `record_status` int(1) NOT NULL DEFAULT 1 COMMENT '是否有效：0 无效，1 有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
