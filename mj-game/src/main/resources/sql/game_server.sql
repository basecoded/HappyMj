/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : game_server

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2016-09-21 16:43:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for id_gen
-- ----------------------------
DROP TABLE IF EXISTS `id_gen`;
CREATE TABLE `id_gen` (
  `id` varchar(100) NOT NULL DEFAULT '' COMMENT '主键',
  `module_name` varchar(36) NOT NULL DEFAULT '' COMMENT '模块名称',
  `value` bigint(10) unsigned NOT NULL COMMENT 'id值',
  `prefix` varchar(10) NOT NULL COMMENT '前缀',
  `version` int(10) unsigned NOT NULL COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role_account
-- ----------------------------
DROP TABLE IF EXISTS `role_account`;
CREATE TABLE `role_account` (
  `id` varchar(36) NOT NULL,
  `user_role_id` varchar(36) NOT NULL COMMENT '角色唯一id',
  `tongqian` bigint(11) NOT NULL COMMENT '游戏币数量',
  `bind_lingshi` bigint(11) NOT NULL COMMENT '绑定元宝数量',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL,
  `log_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_role_account_user_role_id` (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role_room
-- ----------------------------
DROP TABLE IF EXISTS `role_room`;
CREATE TABLE `role_room` (
  `user_role_id` varchar(36) NOT NULL,
  `number` int(11) NOT NULL COMMENT '房间号',
  `round` int(11) NOT NULL COMMENT '局',
  `serial` int(11) NOT NULL COMMENT '是否连中',
  `win` int(11) NOT NULL COMMENT '是否强制胡牌',
  `log_update_time` varchar(45) NOT NULL DEFAULT 'CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP',
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role_stage
-- ----------------------------
DROP TABLE IF EXISTS `role_stage`;
CREATE TABLE `role_stage` (
  `user_role_id` varchar(36) NOT NULL,
  `map_id` varchar(255) DEFAULT NULL,
  `map_x` int(11) DEFAULT NULL,
  `map_y` int(11) DEFAULT NULL,
  `hp` int(11) DEFAULT NULL,
  `mp` int(11) DEFAULT NULL,
  `max_hp` int(11) DEFAULT NULL,
  `max_mp` int(11) DEFAULT NULL,
  `buff` text,
  `props` text,
  `state` int(1) DEFAULT NULL,
  `map_node` text,
  `ti_li` int(11) DEFAULT '0',
  `line_no` int(11) DEFAULT '0',
  `pk_info` text,
  `shanbi_val` int(11) DEFAULT '0',
  `meiren_info` text,
  `zuoqi_info` text,
  `free_fly_count` int(11) DEFAULT '0' COMMENT '今日免费飞行次数',
  `fly_count_refresh_time` bigint(20) DEFAULT '0' COMMENT '免费飞行次数刷新时间',
  `copy_info` varchar(255) DEFAULT NULL,
  `log_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` bigint(20) NOT NULL,
  `user_role_id` varchar(36) NOT NULL COMMENT '创建者',
  `status` smallint(6) NOT NULL COMMENT '房间状态',
  `max_round` smallint(6) NOT NULL COMMENT '最大回合',
  `cur_round` smallint(6) NOT NULL COMMENT '当前回合',
  `serial` smallint(6) NOT NULL COMMENT '是否连中',
  `win` smallint(6) NOT NULL COMMENT '是否强制胡牌',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `log_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for server_info
-- ----------------------------
DROP TABLE IF EXISTS `server_info`;
CREATE TABLE `server_info` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开服时间',
  `prefix_id` varchar(20) DEFAULT NULL COMMENT '角色名前缀',
  `hefu_time` timestamp NULL DEFAULT NULL COMMENT '合服时间',
  `log_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `id` varchar(48) NOT NULL COMMENT '主键，根据user_role表里面的 user_id和server_id加@号拼接',
  `user_guid` varchar(36) NOT NULL COMMENT '对应user_role表里面的user_id',
  `lingshi` bigint(11) NOT NULL COMMENT '元宝数量',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `server_id` varchar(36) DEFAULT NULL COMMENT '服务器id',
  `recive_yb` int(11) DEFAULT NULL COMMENT '可领取元宝，存储无充值的玩家，所有获得的元宝（此功能已经作废）',
  `is_recharge` int(11) DEFAULT NULL COMMENT '是否充值',
  `log_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_account_index` (`user_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(36) NOT NULL COMMENT '角色唯一主键',
  `user_id` varchar(64) NOT NULL COMMENT '角色账号id',
  `name` varchar(64) CHARACTER SET utf8mb4 NOT NULL COMMENT '角色名称',
  `job` varchar(36) NOT NULL COMMENT '职业',
  `sex` int(1) NOT NULL COMMENT '性别',
  `card` int(1) NOT NULL COMMENT '房卡',
  `face` varchar(256) DEFAULT NULL COMMENT '头像',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `online_time` bigint(20) DEFAULT NULL COMMENT '上线时间',
  `offline_time` bigint(20) DEFAULT NULL COMMENT '下线时间',
  `server_id` varchar(36) DEFAULT NULL COMMENT '服务器id',
  `platform` varchar(36) DEFAULT NULL,
  `login_count` int(11) DEFAULT NULL COMMENT '登录次数',
  `log_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_role_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
