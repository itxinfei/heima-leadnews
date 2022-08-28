/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : heima-leadnews

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2019-09-02 14:41:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `admin_qrtz_blob_triggers`;
CREATE TABLE `admin_qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `ADMIN_QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `admin_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for admin_qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `admin_qrtz_calendars`;
CREATE TABLE `admin_qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for admin_qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `admin_qrtz_cron_triggers`;
CREATE TABLE `admin_qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `ADMIN_QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `admin_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_qrtz_cron_triggers
-- ----------------------------
INSERT INTO `admin_qrtz_cron_triggers` VALUES ('quartz.prod.admin', 'crawlerReviewArticleJobTrigger_0', 'admin_crawlerReviewArticleJobTrigger', '0 0/5 * * * ?', 'Etc/UTC');

-- ----------------------------
-- Table structure for admin_qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `admin_qrtz_fired_triggers`;
CREATE TABLE `admin_qrtz_fired_triggers` (
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
-- Records of admin_qrtz_fired_triggers
-- ----------------------------
INSERT INTO `admin_qrtz_fired_triggers` VALUES ('quartz.prod.admin', 'quartz.prod.admin-instanceId1566549833893', 'crawlerReviewArticleJobTrigger_0', 'admin_crawlerReviewArticleJobTrigger', 'quartz.prod.admin-instanceId', '1566891019686', '1566891019522', '0', 'EXECUTING', 'crawlerReviewArticleJobAutoJobDetail', 'admin_default', '1', '1');

-- ----------------------------
-- Table structure for admin_qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `admin_qrtz_job_details`;
CREATE TABLE `admin_qrtz_job_details` (
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
-- Records of admin_qrtz_job_details
-- ----------------------------
INSERT INTO `admin_qrtz_job_details` VALUES ('quartz.prod.admin', 'crawlerReviewArticleJobAutoJobDetail', 'admin_default', '', 'com.heima.admin.job.CrawlerReviewArticleJob', '1', '1', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `admin_qrtz_job_details` VALUES ('quartz.prod.admin', 'quartzScanJobDetail', 'init-group-admin_0', '初始化集群任务', 'com.heima.quartz.QuartzScanJob', '1', '1', '1', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400066272616E636874000E6272616E63682D64656661756C747800);

-- ----------------------------
-- Table structure for admin_qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `admin_qrtz_locks`;
CREATE TABLE `admin_qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_qrtz_locks
-- ----------------------------
INSERT INTO `admin_qrtz_locks` VALUES ('quartz.prod.admin', 'STATE_ACCESS');
INSERT INTO `admin_qrtz_locks` VALUES ('quartz.prod.admin', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for admin_qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `admin_qrtz_paused_trigger_grps`;
CREATE TABLE `admin_qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for admin_qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `admin_qrtz_scheduler_state`;
CREATE TABLE `admin_qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_qrtz_scheduler_state
-- ----------------------------
INSERT INTO `admin_qrtz_scheduler_state` VALUES ('quartz.prod.admin', 'quartz.prod.admin-instanceId', '1566892090150', '7500');

-- ----------------------------
-- Table structure for admin_qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `admin_qrtz_simple_triggers`;
CREATE TABLE `admin_qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `ADMIN_QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `admin_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for admin_qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `admin_qrtz_simprop_triggers`;
CREATE TABLE `admin_qrtz_simprop_triggers` (
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
  CONSTRAINT `ADMIN_QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `admin_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for admin_qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `admin_qrtz_triggers`;
CREATE TABLE `admin_qrtz_triggers` (
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
  CONSTRAINT `ADMIN_QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `admin_qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_qrtz_triggers
-- ----------------------------
INSERT INTO `admin_qrtz_triggers` VALUES ('quartz.prod.admin', 'crawlerReviewArticleJobTrigger_0', 'admin_crawlerReviewArticleJobTrigger', 'crawlerReviewArticleJobAutoJobDetail', 'admin_default', '每天晚上23:30执行', '1566891300000', '1566891019522', '0', 'BLOCKED', 'CRON', '1566526834000', '0', null, '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400096A6F6244657461696C7372001D6F72672E71756172747A2E696D706C2E4A6F6244657461696C496D706CABC3CAEC015A54AF0200075A000A6475726162696C6974795A000D73686F756C645265636F7665724C000B6465736372697074696F6E7400124C6A6176612F6C616E672F537472696E673B4C000567726F757071007E00094C00086A6F62436C6173737400114C6A6176612F6C616E672F436C6173733B4C000A6A6F62446174614D61707400174C6F72672F71756172747A2F4A6F62446174614D61703B4C00046E616D6571007E00097870010174000074000D61646D696E5F64656661756C747672002B636F6D2E6865696D612E61646D696E2E6A6F622E437261776C657252657669657741727469636C654A6F62000000000000000000000078707371007E0000007371007E00053F40000000000010770800000010000000007800740024637261776C657252657669657741727469636C654A6F624175746F4A6F6244657461696C7800);

-- ----------------------------
-- Table structure for ad_article_statistics
-- ----------------------------
DROP TABLE IF EXISTS `ad_article_statistics`;
CREATE TABLE `ad_article_statistics` (
  `id` int(11) unsigned NOT NULL COMMENT '主键',
  `article_we_media` int(11) unsigned DEFAULT NULL COMMENT '主账号ID',
  `article_crawlers` int(11) unsigned DEFAULT NULL COMMENT '子账号ID',
  `channel_id` int(11) unsigned DEFAULT NULL COMMENT '频道ID',
  `read_20` int(11) unsigned DEFAULT NULL COMMENT '草读量',
  `read_100` int(11) unsigned DEFAULT NULL COMMENT '读完量',
  `read_count` int(11) unsigned DEFAULT NULL COMMENT '阅读量',
  `comment` int(11) unsigned DEFAULT NULL COMMENT '评论量',
  `follow` int(11) unsigned DEFAULT NULL COMMENT '关注量',
  `collection` int(11) unsigned DEFAULT NULL COMMENT '收藏量',
  `forward` int(11) unsigned DEFAULT NULL COMMENT '转发量',
  `likes` int(11) unsigned DEFAULT NULL COMMENT '点赞量',
  `unlikes` int(11) unsigned DEFAULT NULL COMMENT '不喜欢',
  `unfollow` int(11) unsigned DEFAULT NULL COMMENT 'unfollow',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='文章数据统计表';

-- ----------------------------
-- Records of ad_article_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for ad_channel
-- ----------------------------
DROP TABLE IF EXISTS `ad_channel`;
CREATE TABLE `ad_channel` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '频道名称',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '频道描述',
  `is_default` tinyint(1) unsigned DEFAULT NULL COMMENT '是否默认频道',
  `status` tinyint(1) unsigned DEFAULT NULL,
  `ord` tinyint(3) unsigned DEFAULT NULL COMMENT '默认排序',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='频道信息表';

-- ----------------------------
-- Records of ad_channel
-- ----------------------------

-- ----------------------------
-- Table structure for ad_channel_label
-- ----------------------------
DROP TABLE IF EXISTS `ad_channel_label`;
CREATE TABLE `ad_channel_label` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `channel_id` int(11) unsigned DEFAULT NULL,
  `label_id` int(11) unsigned DEFAULT NULL COMMENT '标签ID',
  `ord` int(5) unsigned DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1118 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='频道标签信息表';

-- ----------------------------
-- Records of ad_channel_label
-- ----------------------------

-- ----------------------------
-- Table structure for ad_function
-- ----------------------------
DROP TABLE IF EXISTS `ad_function`;
CREATE TABLE `ad_function` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '功能名称',
  `code` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '功能代码',
  `parent_id` int(11) unsigned DEFAULT NULL COMMENT '父功能',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='页面功能信息表';

-- ----------------------------
-- Records of ad_function
-- ----------------------------

-- ----------------------------
-- Table structure for ad_label
-- ----------------------------
DROP TABLE IF EXISTS `ad_label`;
CREATE TABLE `ad_label` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '频道名称',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '0系统增加\r\n            1人工增加',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=27306 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='标签信息表';

-- ----------------------------
-- Records of ad_label
-- ----------------------------

-- ----------------------------
-- Table structure for ad_menu
-- ----------------------------
DROP TABLE IF EXISTS `ad_menu`;
CREATE TABLE `ad_menu` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单名称',
  `code` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单代码',
  `parent_id` int(11) unsigned DEFAULT NULL COMMENT '父菜单',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='菜单资源信息表';

-- ----------------------------
-- Records of ad_menu
-- ----------------------------

-- ----------------------------
-- Table structure for ad_recommend_strategy
-- ----------------------------
DROP TABLE IF EXISTS `ad_recommend_strategy`;
CREATE TABLE `ad_recommend_strategy` (
  `id` int(5) unsigned NOT NULL COMMENT '主键',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '策略名称',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '策略描述',
  `is_enable` tinyint(1) DEFAULT NULL COMMENT '是否有效',
  `group_id` int(5) unsigned DEFAULT NULL COMMENT '分组ID',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='推荐策略信息表';

-- ----------------------------
-- Records of ad_recommend_strategy
-- ----------------------------

-- ----------------------------
-- Table structure for ad_role
-- ----------------------------
DROP TABLE IF EXISTS `ad_role`;
CREATE TABLE `ad_role` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `description` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色描述',
  `is_enable` tinyint(1) unsigned DEFAULT NULL COMMENT '是否有效',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='角色信息表';

-- ----------------------------
-- Records of ad_role
-- ----------------------------

-- ----------------------------
-- Table structure for ad_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `ad_role_auth`;
CREATE TABLE `ad_role_auth` (
  `id` int(11) unsigned NOT NULL,
  `role_id` int(11) unsigned DEFAULT NULL COMMENT '角色ID',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '资源类型\r\n            0 菜单\r\n            1 功能',
  `entry_id` int(11) unsigned DEFAULT NULL COMMENT '资源ID',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='角色权限信息表';

-- ----------------------------
-- Records of ad_role_auth
-- ----------------------------

-- ----------------------------
-- Table structure for ad_sensitive
-- ----------------------------
DROP TABLE IF EXISTS `ad_sensitive`;
CREATE TABLE `ad_sensitive` (
  `id` int(11) unsigned NOT NULL COMMENT '主键',
  `sensitives` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '敏感词',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='敏感词信息表';

-- ----------------------------
-- Records of ad_sensitive
-- ----------------------------

-- ----------------------------
-- Table structure for ad_strategy_group
-- ----------------------------
DROP TABLE IF EXISTS `ad_strategy_group`;
CREATE TABLE `ad_strategy_group` (
  `id` int(5) unsigned NOT NULL COMMENT '主键',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '策略名称',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '策略描述',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='推荐策略分组信息表';

-- ----------------------------
-- Records of ad_strategy_group
-- ----------------------------

-- ----------------------------
-- Table structure for ad_user
-- ----------------------------
DROP TABLE IF EXISTS `ad_user`;
CREATE TABLE `ad_user` (
  `id` int(11) unsigned NOT NULL COMMENT '主键',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录用户名',
  `password` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录密码',
  `salt` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '盐',
  `nickname` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `phone` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `status` tinyint(11) unsigned DEFAULT NULL COMMENT '状态\r\n            0 暂时不可用\r\n            1 永久不可用\r\n            9 正常可用',
  `email` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='管理员用户信息表';

-- ----------------------------
-- Records of ad_user
-- ----------------------------

-- ----------------------------
-- Table structure for ad_user_equipment
-- ----------------------------
DROP TABLE IF EXISTS `ad_user_equipment`;
CREATE TABLE `ad_user_equipment` (
  `id` int(11) unsigned NOT NULL,
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '0PC\r\n            1ANDROID\r\n            2IOS\r\n            3PAD\r\n            9 其他',
  `version` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设备版本',
  `sys` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设备系统',
  `no` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设备唯一号，MD5加密',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='管理员设备信息表';

-- ----------------------------
-- Records of ad_user_equipment
-- ----------------------------

-- ----------------------------
-- Table structure for ad_user_login
-- ----------------------------
DROP TABLE IF EXISTS `ad_user_login`;
CREATE TABLE `ad_user_login` (
  `id` int(11) unsigned NOT NULL,
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `equipment_id` int(11) unsigned DEFAULT NULL COMMENT '登录设备ID',
  `ip` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录IP',
  `address` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录地址',
  `longitude` decimal(5,5) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(5,5) DEFAULT NULL COMMENT '纬度',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='管理员登录行为信息表';

-- ----------------------------
-- Records of ad_user_login
-- ----------------------------

-- ----------------------------
-- Table structure for ad_user_opertion
-- ----------------------------
DROP TABLE IF EXISTS `ad_user_opertion`;
CREATE TABLE `ad_user_opertion` (
  `id` int(11) unsigned NOT NULL,
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `equipment_id` int(11) unsigned DEFAULT NULL COMMENT '登录设备ID',
  `ip` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录IP',
  `address` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录地址',
  `type` int(5) DEFAULT NULL COMMENT '操作类型',
  `description` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作描述',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='管理员操作行为信息表';

-- ----------------------------
-- Records of ad_user_opertion
-- ----------------------------

-- ----------------------------
-- Table structure for ad_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ad_user_role`;
CREATE TABLE `ad_user_role` (
  `id` int(11) unsigned NOT NULL,
  `role_id` int(11) unsigned DEFAULT NULL COMMENT '角色ID',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='管理员角色信息表';

-- ----------------------------
-- Records of ad_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for ad_vistor_statistics
-- ----------------------------
DROP TABLE IF EXISTS `ad_vistor_statistics`;
CREATE TABLE `ad_vistor_statistics` (
  `id` int(11) unsigned NOT NULL COMMENT '主键',
  `activity` int(11) unsigned DEFAULT NULL COMMENT '日活',
  `vistor` int(11) unsigned DEFAULT NULL COMMENT '访问量',
  `ip` int(11) unsigned DEFAULT NULL COMMENT 'IP量',
  `register` int(11) unsigned DEFAULT NULL COMMENT '注册量',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='访问数据统计表';

-- ----------------------------
-- Records of ad_vistor_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for ap_article
-- ----------------------------
DROP TABLE IF EXISTS `ap_article`;
CREATE TABLE `ap_article` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  `author_id` int(11) unsigned DEFAULT NULL COMMENT '文章作者的ID',
  `author_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者昵称',
  `channel_id` int(10) unsigned DEFAULT NULL COMMENT '文章所属频道ID',
  `channel_name` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '频道名称',
  `layout` tinyint(1) unsigned DEFAULT NULL COMMENT '文章布局\r\n            0 无图文章\r\n            1 单图文章\r\n            2 多图文章',
  `flag` tinyint(3) unsigned DEFAULT NULL COMMENT '文章标记\r\n            0 普通文章\r\n            1 热点文章\r\n            2 置顶文章\r\n            3 精品文章\r\n            4 大V 文章',
  `images` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章图片\r\n            多张逗号分隔',
  `labels` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章标签最多3个 逗号分隔',
  `likes` int(5) unsigned DEFAULT NULL COMMENT '点赞数量',
  `collection` int(5) unsigned DEFAULT NULL COMMENT '收藏数量',
  `comment` int(5) unsigned DEFAULT NULL COMMENT '评论数量',
  `views` int(5) unsigned DEFAULT NULL COMMENT '阅读数量',
  `province_id` int(11) unsigned DEFAULT NULL COMMENT '省市',
  `city_id` int(11) unsigned DEFAULT NULL COMMENT '市区',
  `county_id` int(11) unsigned DEFAULT NULL COMMENT '区县',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `sync_status` tinyint(1) DEFAULT '0' COMMENT '同步状态',
  `origin` tinyint(1) unsigned DEFAULT '0' COMMENT '来源',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='文章信息表，存储已发布的文章';

-- ----------------------------
-- Records of ap_article
-- ----------------------------

-- ----------------------------
-- Table structure for ap_article_config
-- ----------------------------
DROP TABLE IF EXISTS `ap_article_config`;
CREATE TABLE `ap_article_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` int(11) unsigned DEFAULT NULL COMMENT '文章ID',
  `is_comment` tinyint(1) unsigned DEFAULT NULL COMMENT '是否可评论',
  `is_forward` tinyint(1) unsigned DEFAULT NULL COMMENT '是否转发',
  `is_down` tinyint(1) unsigned DEFAULT NULL COMMENT '是否下架',
  `is_delete` tinyint(1) unsigned DEFAULT NULL COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_article_id` (`article_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP已发布文章配置表';

-- ----------------------------
-- Records of ap_article_config
-- ----------------------------

-- ----------------------------
-- Table structure for ap_article_content
-- ----------------------------
DROP TABLE IF EXISTS `ap_article_content`;
CREATE TABLE `ap_article_content` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` int(11) unsigned DEFAULT NULL COMMENT '文章ID',
  `content` longtext COLLATE utf8mb4_unicode_ci COMMENT '文章内容',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_article_id` (`article_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP已发布文章内容表';

-- ----------------------------
-- Records of ap_article_content
-- ----------------------------

-- ----------------------------
-- Table structure for ap_article_label
-- ----------------------------
DROP TABLE IF EXISTS `ap_article_label`;
CREATE TABLE `ap_article_label` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` int(11) unsigned DEFAULT NULL,
  `label_id` int(11) unsigned DEFAULT NULL COMMENT '标签ID',
  `count` int(5) unsigned DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章标签信息表';

-- ----------------------------
-- Records of ap_article_label
-- ----------------------------

-- ----------------------------
-- Table structure for ap_associate_words
-- ----------------------------
DROP TABLE IF EXISTS `ap_associate_words`;
CREATE TABLE `ap_associate_words` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `associate_words` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联想词',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_associate_words` (`associate_words`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='联想词表';

-- ----------------------------
-- Records of ap_associate_words
-- ----------------------------

-- ----------------------------
-- Table structure for ap_author
-- ----------------------------
DROP TABLE IF EXISTS `ap_author`;
CREATE TABLE `ap_author` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '作者名称',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '0 爬取数据\r\n            1 签约合作商\r\n            2 平台自媒体人\r\n            ',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '社交账号ID',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `wm_user_id` int(11) unsigned DEFAULT NULL COMMENT '自媒体账号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_type_name` (`type`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3385 DEFAULT CHARSET=utf8 COMMENT='APP文章作者信息表';

-- ----------------------------
-- Records of ap_author
-- ----------------------------

-- ----------------------------
-- Table structure for ap_behavior_entry
-- ----------------------------
DROP TABLE IF EXISTS `ap_behavior_entry`;
CREATE TABLE `ap_behavior_entry` (
  `id` int(11) unsigned NOT NULL COMMENT '主键',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '实体类型\r\n            0终端设备\r\n            1用户',
  `entry_id` int(11) unsigned DEFAULT NULL COMMENT '实体ID',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `burst` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP行为实体表,一个行为实体可能是用户或者设备，或者其它';

-- ----------------------------
-- Records of ap_behavior_entry
-- ----------------------------

-- ----------------------------
-- Table structure for ap_collection
-- ----------------------------
DROP TABLE IF EXISTS `ap_collection`;
CREATE TABLE `ap_collection` (
  `id` int(11) unsigned NOT NULL,
  `behavior_entry_id` int(11) unsigned DEFAULT NULL COMMENT '实体ID',
  `entry_id` int(11) unsigned DEFAULT NULL COMMENT '文章ID',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '点赞内容类型\r\n            0文章\r\n            1动态',
  `collection_time` datetime DEFAULT NULL COMMENT '创建时间',
  `published_time` datetime DEFAULT NULL COMMENT '发布时间',
  `burst` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_type` (`behavior_entry_id`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP收藏信息表';

-- ----------------------------
-- Records of ap_collection
-- ----------------------------

-- ----------------------------
-- Table structure for ap_comment
-- ----------------------------
DROP TABLE IF EXISTS `ap_comment`;
CREATE TABLE `ap_comment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `author_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `author_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户昵称',
  `entry_id` int(11) unsigned DEFAULT NULL,
  `channel_id` int(11) unsigned DEFAULT NULL COMMENT '频道ID',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '评论内容类型\r\n            0 文章\r\n            1 动态',
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评论内容',
  `image` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `likes` int(5) unsigned DEFAULT NULL,
  `reply` int(5) unsigned DEFAULT NULL,
  `flag` tinyint(2) unsigned DEFAULT NULL COMMENT '文章标记\r\n            0 普通评论\r\n            1 热点评论\r\n            2 推荐评论\r\n            3 置顶评论\r\n            4 精品评论\r\n            5 大V 评论',
  `longitude` decimal(5,5) DEFAULT NULL,
  `latitude` decimal(5,5) DEFAULT NULL COMMENT '维度',
  `address` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地理位置',
  `ord` int(11) unsigned DEFAULT NULL COMMENT '评论排列序号',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP评论信息表';

-- ----------------------------
-- Records of ap_comment
-- ----------------------------

-- ----------------------------
-- Table structure for ap_comment_repay
-- ----------------------------
DROP TABLE IF EXISTS `ap_comment_repay`;
CREATE TABLE `ap_comment_repay` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `author_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `author_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户昵称',
  `comment_id` int(11) unsigned DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评论内容',
  `likes` int(5) unsigned DEFAULT NULL,
  `longitude` decimal(5,5) unsigned DEFAULT NULL,
  `latitude` decimal(5,5) unsigned DEFAULT NULL COMMENT '维度',
  `address` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地理位置',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP评论回复信息表';

-- ----------------------------
-- Records of ap_comment_repay
-- ----------------------------

-- ----------------------------
-- Table structure for ap_dynamic
-- ----------------------------
DROP TABLE IF EXISTS `ap_dynamic`;
CREATE TABLE `ap_dynamic` (
  `id` int(11) unsigned NOT NULL,
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '文章作者的ID',
  `user_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者昵称',
  `content` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '频道名称',
  `is_forward` tinyint(1) unsigned DEFAULT NULL COMMENT '是否转发',
  `article_id` int(11) unsigned DEFAULT NULL COMMENT '转发文章ID',
  `articel_title` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '转发文章标题',
  `article_image` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '转发文章图片',
  `layout` tinyint(1) unsigned DEFAULT NULL COMMENT '布局标识\r\n            0 无图动态\r\n            1 单图动态\r\n            2 多图动态\r\n            3 转发动态',
  `images` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章图片\r\n            多张逗号分隔',
  `likes` int(5) unsigned DEFAULT NULL COMMENT '点赞数量',
  `collection` int(5) unsigned DEFAULT NULL COMMENT '收藏数量',
  `comment` int(5) unsigned DEFAULT NULL COMMENT '评论数量',
  `views` int(5) unsigned DEFAULT NULL COMMENT '阅读数量',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP用户动态信息表';

-- ----------------------------
-- Records of ap_dynamic
-- ----------------------------

-- ----------------------------
-- Table structure for ap_equipment
-- ----------------------------
DROP TABLE IF EXISTS `ap_equipment`;
CREATE TABLE `ap_equipment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '0PC\r\n            1ANDROID\r\n            2IOS\r\n            3PAD\r\n            9 其他',
  `version` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设备版本',
  `sys` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设备系统',
  `no` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设备唯一号，MD5加密',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP设备信息表';

-- ----------------------------
-- Records of ap_equipment
-- ----------------------------

-- ----------------------------
-- Table structure for ap_equipment_code
-- ----------------------------
DROP TABLE IF EXISTS `ap_equipment_code`;
CREATE TABLE `ap_equipment_code` (
  `id` int(11) unsigned NOT NULL,
  `equipment_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `code` longtext COLLATE utf8mb4_unicode_ci COMMENT '设备码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP设备码信息表';

-- ----------------------------
-- Records of ap_equipment_code
-- ----------------------------

-- ----------------------------
-- Table structure for ap_follow_behavior
-- ----------------------------
DROP TABLE IF EXISTS `ap_follow_behavior`;
CREATE TABLE `ap_follow_behavior` (
  `id` int(11) unsigned NOT NULL,
  `entry_id` int(11) unsigned DEFAULT NULL COMMENT '实体ID',
  `article_id` int(11) unsigned DEFAULT NULL COMMENT '文章ID',
  `follow_id` int(11) unsigned DEFAULT NULL COMMENT '关注用户ID',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_entry_id` (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP关注行为表';

-- ----------------------------
-- Records of ap_follow_behavior
-- ----------------------------

-- ----------------------------
-- Table structure for ap_forward_behavior
-- ----------------------------
DROP TABLE IF EXISTS `ap_forward_behavior`;
CREATE TABLE `ap_forward_behavior` (
  `id` int(11) unsigned NOT NULL,
  `entry_id` int(11) unsigned DEFAULT NULL COMMENT '实体ID',
  `article_id` int(11) unsigned DEFAULT NULL COMMENT '文章ID',
  `dynamic_id` int(11) unsigned DEFAULT NULL,
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_entry_id` (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP转发行为表';

-- ----------------------------
-- Records of ap_forward_behavior
-- ----------------------------

-- ----------------------------
-- Table structure for ap_hot_articles
-- ----------------------------
DROP TABLE IF EXISTS `ap_hot_articles`;
CREATE TABLE `ap_hot_articles` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `entry_id` int(11) unsigned NOT NULL DEFAULT '0',
  `tag_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '频道ID',
  `tag_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '频道名称',
  `score` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '热度评分',
  `article_id` int(11) unsigned DEFAULT NULL COMMENT '文章ID',
  `province_id` int(11) unsigned DEFAULT NULL COMMENT '省市',
  `city_id` int(11) unsigned DEFAULT NULL COMMENT '市区',
  `county_id` int(11) unsigned DEFAULT NULL COMMENT '区县',
  `is_read` tinyint(2) unsigned DEFAULT NULL COMMENT '是否阅读',
  `release_date` datetime DEFAULT NULL,
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_entry_id` (`entry_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5362 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='热文章表';

-- ----------------------------
-- Records of ap_hot_articles
-- ----------------------------

-- ----------------------------
-- Table structure for ap_hot_words
-- ----------------------------
DROP TABLE IF EXISTS `ap_hot_words`;
CREATE TABLE `ap_hot_words` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `hot_words` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '热词',
  `type` tinyint(2) DEFAULT NULL COMMENT '0:热,1:荐,2:新,3:火,4:精,5:亮',
  `hot_date` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '热词日期',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_hot_date` (`hot_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='搜索热词表';

-- ----------------------------
-- Records of ap_hot_words
-- ----------------------------

-- ----------------------------
-- Table structure for ap_likes_behavior
-- ----------------------------
DROP TABLE IF EXISTS `ap_likes_behavior`;
CREATE TABLE `ap_likes_behavior` (
  `id` int(11) unsigned NOT NULL,
  `behavior_entry_id` int(11) unsigned DEFAULT NULL COMMENT '实体ID',
  `entry_id` int(11) unsigned DEFAULT NULL COMMENT '文章ID',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '点赞内容类型\r\n            0文章\r\n            1动态',
  `operation` tinyint(1) unsigned DEFAULT NULL COMMENT '0 点赞\r\n            1 取消点赞',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  `burst` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP点赞行为表';

-- ----------------------------
-- Records of ap_likes_behavior
-- ----------------------------

-- ----------------------------
-- Table structure for ap_read_behavior
-- ----------------------------
DROP TABLE IF EXISTS `ap_read_behavior`;
CREATE TABLE `ap_read_behavior` (
  `id` int(11) unsigned NOT NULL,
  `entry_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `article_id` int(11) unsigned DEFAULT NULL COMMENT '文章ID',
  `count` tinyint(3) unsigned DEFAULT NULL,
  `read_duration` int(11) unsigned DEFAULT NULL COMMENT '阅读时间单位秒',
  `percentage` tinyint(3) unsigned DEFAULT NULL COMMENT '阅读百分比',
  `load_duration` int(11) unsigned DEFAULT NULL COMMENT '文章加载时间',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  `updated_time` datetime DEFAULT NULL,
  `burst` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP阅读行为表';

-- ----------------------------
-- Records of ap_read_behavior
-- ----------------------------

-- ----------------------------
-- Table structure for ap_share_behavior
-- ----------------------------
DROP TABLE IF EXISTS `ap_share_behavior`;
CREATE TABLE `ap_share_behavior` (
  `id` int(11) unsigned NOT NULL,
  `entry_id` int(11) unsigned DEFAULT NULL COMMENT '实体ID',
  `article_id` int(11) unsigned DEFAULT NULL COMMENT '文章ID',
  `type` tinyint(2) unsigned DEFAULT NULL COMMENT '0 微信\r\n            1 微信朋友圈\r\n            2 QQ\r\n            3 QQ 空间\r\n            4 微博\r\n            ',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_entry_id` (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP分享行为表';

-- ----------------------------
-- Records of ap_share_behavior
-- ----------------------------

-- ----------------------------
-- Table structure for ap_show_behavior
-- ----------------------------
DROP TABLE IF EXISTS `ap_show_behavior`;
CREATE TABLE `ap_show_behavior` (
  `id` int(11) unsigned NOT NULL,
  `entry_id` int(11) unsigned DEFAULT NULL COMMENT '实体ID',
  `article_id` int(11) unsigned DEFAULT NULL COMMENT '文章ID',
  `is_click` tinyint(1) unsigned DEFAULT NULL COMMENT '是否点击',
  `show_time` datetime DEFAULT NULL COMMENT '文章加载时间',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_entry_id` (`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP文章展现行为表';

-- ----------------------------
-- Records of ap_show_behavior
-- ----------------------------

-- ----------------------------
-- Table structure for ap_unlikes_behavior
-- ----------------------------
DROP TABLE IF EXISTS `ap_unlikes_behavior`;
CREATE TABLE `ap_unlikes_behavior` (
  `id` int(11) unsigned NOT NULL,
  `entry_id` int(11) unsigned DEFAULT NULL COMMENT '实体ID',
  `article_id` int(11) unsigned DEFAULT NULL COMMENT '文章ID',
  `type` tinyint(2) unsigned DEFAULT NULL COMMENT '0 不喜欢\r\n            1 取消不喜欢',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP不喜欢行为表';

-- ----------------------------
-- Records of ap_unlikes_behavior
-- ----------------------------

-- ----------------------------
-- Table structure for ap_user
-- ----------------------------
DROP TABLE IF EXISTS `ap_user`;
CREATE TABLE `ap_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `salt` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码、通信等加密盐',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码,md5加密',
  `phone` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `sex` tinyint(1) unsigned DEFAULT NULL COMMENT '0 男\r\n            1 女\r\n            2 未知',
  `is_certification` tinyint(1) unsigned DEFAULT NULL COMMENT '0 未\r\n            1 是',
  `is_identity_authentication` tinyint(1) DEFAULT NULL COMMENT '是否身份认证',
  `status` tinyint(1) unsigned DEFAULT NULL COMMENT '0正常\r\n            1锁定',
  `flag` tinyint(1) unsigned DEFAULT NULL COMMENT '0 普通用户\r\n            1 自媒体人\r\n            2 大V',
  `created_time` datetime DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP用户信息表';

-- ----------------------------
-- Records of ap_user
-- ----------------------------

-- ----------------------------
-- Table structure for ap_user_article_list
-- ----------------------------
DROP TABLE IF EXISTS `ap_user_article_list`;
CREATE TABLE `ap_user_article_list` (
  `id` int(11) unsigned NOT NULL COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `channel_id` int(11) unsigned DEFAULT NULL COMMENT '频道ID',
  `article_id` int(11) unsigned DEFAULT NULL COMMENT '动态ID',
  `is_show` tinyint(1) unsigned DEFAULT NULL COMMENT '是否展示',
  `recommend_time` datetime DEFAULT NULL COMMENT '推荐时间',
  `is_read` tinyint(1) unsigned DEFAULT NULL COMMENT '是否阅读',
  `strategy_id` int(5) unsigned DEFAULT NULL COMMENT '推荐算法',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP用户文章列表';

-- ----------------------------
-- Records of ap_user_article_list
-- ----------------------------

-- ----------------------------
-- Table structure for ap_user_channel
-- ----------------------------
DROP TABLE IF EXISTS `ap_user_channel`;
CREATE TABLE `ap_user_channel` (
  `id` int(11) unsigned NOT NULL,
  `channel_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '文章ID',
  `name` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ord` tinyint(2) DEFAULT NULL COMMENT '频道排序',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP用户频道信息表';

-- ----------------------------
-- Records of ap_user_channel
-- ----------------------------

-- ----------------------------
-- Table structure for ap_user_dynamic_list
-- ----------------------------
DROP TABLE IF EXISTS `ap_user_dynamic_list`;
CREATE TABLE `ap_user_dynamic_list` (
  `id` int(11) unsigned NOT NULL COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `dynamic_id` int(11) unsigned DEFAULT NULL COMMENT '动态ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP用户动态列表';

-- ----------------------------
-- Records of ap_user_dynamic_list
-- ----------------------------

-- ----------------------------
-- Table structure for ap_user_equipment
-- ----------------------------
DROP TABLE IF EXISTS `ap_user_equipment`;
CREATE TABLE `ap_user_equipment` (
  `id` int(11) unsigned NOT NULL,
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `equipment_id` int(11) unsigned DEFAULT NULL COMMENT '设备ID',
  `last_time` datetime DEFAULT NULL COMMENT '上次使用时间',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP用户设备信息表';

-- ----------------------------
-- Records of ap_user_equipment
-- ----------------------------

-- ----------------------------
-- Table structure for ap_user_fan
-- ----------------------------
DROP TABLE IF EXISTS `ap_user_fan`;
CREATE TABLE `ap_user_fan` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `fans_id` int(11) unsigned DEFAULT NULL COMMENT '粉丝ID',
  `fans_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '粉丝昵称',
  `level` tinyint(1) unsigned DEFAULT NULL COMMENT '粉丝忠实度\r\n            0 正常\r\n            1 潜力股\r\n            2 勇士\r\n            3 铁杆\r\n            4 老铁',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_display` tinyint(1) unsigned DEFAULT NULL COMMENT '是否可见我动态',
  `is_shield_letter` tinyint(1) unsigned DEFAULT NULL COMMENT '是否屏蔽私信',
  `is_shield_comment` tinyint(1) unsigned DEFAULT NULL COMMENT '是否屏蔽评论',
  `burst` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP用户粉丝信息表';

-- ----------------------------
-- Records of ap_user_fan
-- ----------------------------

-- ----------------------------
-- Table structure for ap_user_feedback
-- ----------------------------
DROP TABLE IF EXISTS `ap_user_feedback`;
CREATE TABLE `ap_user_feedback` (
  `id` int(11) unsigned DEFAULT NULL COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发送人昵称',
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '私信内容',
  `images` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '反馈图片,多张逗号分隔',
  `is_solve` tinyint(1) unsigned DEFAULT NULL COMMENT '是否阅读',
  `solve_note` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `solved_time` datetime DEFAULT NULL COMMENT '阅读时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP用户反馈信息表';

-- ----------------------------
-- Records of ap_user_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for ap_user_follow
-- ----------------------------
DROP TABLE IF EXISTS `ap_user_follow`;
CREATE TABLE `ap_user_follow` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `follow_id` int(11) unsigned DEFAULT NULL COMMENT '粉丝ID',
  `follow_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '粉丝昵称',
  `level` tinyint(1) unsigned DEFAULT NULL COMMENT '关注度\r\n            0 偶尔感兴趣\r\n            1 一般\r\n            2 经常\r\n            3 高度',
  `is_notice` tinyint(1) unsigned DEFAULT NULL COMMENT '是否动态通知',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `burst` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP用户关注信息表';

-- ----------------------------
-- Records of ap_user_follow
-- ----------------------------

-- ----------------------------
-- Table structure for ap_user_identity
-- ----------------------------
DROP TABLE IF EXISTS `ap_user_identity`;
CREATE TABLE `ap_user_identity` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '账号ID',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idno` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
  `font_image` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '正面照片',
  `back_image` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '背面照片',
  `hold_image` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手持照片',
  `industry` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '行业',
  `status` tinyint(1) unsigned DEFAULT NULL COMMENT '状态\r\n            0 创建中\r\n            1 待审核\r\n            2 审核失败\r\n            9 审核通过',
  `reason` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '拒绝原因',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `submited_time` datetime DEFAULT NULL COMMENT '提交时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP身份认证信息表';

-- ----------------------------
-- Records of ap_user_identity
-- ----------------------------

-- ----------------------------
-- Table structure for ap_user_info
-- ----------------------------
DROP TABLE IF EXISTS `ap_user_info`;
CREATE TABLE `ap_user_info` (
  `id` int(11) unsigned NOT NULL COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL,
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '真是姓名',
  `idno` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '身份证号,aes加密',
  `company` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公司',
  `occupation` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '职业',
  `age` tinyint(3) unsigned DEFAULT NULL COMMENT '年龄',
  `birthday` datetime DEFAULT NULL,
  `introduction` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '个人格言',
  `location` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '归属地',
  `fans` int(10) unsigned DEFAULT NULL COMMENT '粉丝数量',
  `follows` int(10) unsigned DEFAULT NULL COMMENT '关注数量',
  `is_recommend_me` tinyint(1) unsigned DEFAULT NULL COMMENT '是否允许推荐我给好友',
  `is_recommend_friend` tinyint(1) unsigned DEFAULT NULL COMMENT '是否允许给我推荐好友',
  `is_display_image` tinyint(1) unsigned DEFAULT NULL COMMENT '是否分享页面显示头像',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP用户信息表';

-- ----------------------------
-- Records of ap_user_info
-- ----------------------------

-- ----------------------------
-- Table structure for ap_user_letter
-- ----------------------------
DROP TABLE IF EXISTS `ap_user_letter`;
CREATE TABLE `ap_user_letter` (
  `id` int(11) unsigned DEFAULT NULL COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `sender_id` int(11) unsigned DEFAULT NULL COMMENT '发送人ID',
  `sender_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发送人昵称',
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '私信内容',
  `is_read` tinyint(1) unsigned DEFAULT NULL COMMENT '是否阅读',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP用户私信信息表';

-- ----------------------------
-- Records of ap_user_letter
-- ----------------------------

-- ----------------------------
-- Table structure for ap_user_message
-- ----------------------------
DROP TABLE IF EXISTS `ap_user_message`;
CREATE TABLE `ap_user_message` (
  `id` int(11) unsigned DEFAULT NULL COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `sender_id` int(11) unsigned DEFAULT NULL COMMENT '发送人ID',
  `sender_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发送人昵称',
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '私信内容',
  `type` tinyint(3) unsigned DEFAULT NULL COMMENT '消息类型\r\n            0 关注\r\n            1 取消关注\r\n            2 点赞文章\r\n            3 取消点赞文章\r\n            4 转发文章\r\n            5 收藏文章\r\n            6 点赞评论\r\n            7 审核通过评论\r\n            8 私信通知\r\n            9 评论通知\r\n            10 分享通知\r\n            \r\n            100 身份证审核通过\r\n            101 身份证审核拒绝\r\n            102 实名认证通过\r\n            103 实名认证失败\r\n            104 自媒体人祝贺\r\n            105 异常登录通知\r\n            106 反馈回复\r\n            107 转发通知',
  `is_read` tinyint(1) unsigned DEFAULT NULL COMMENT '是否阅读',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP用户消息通知信息表';

-- ----------------------------
-- Records of ap_user_message
-- ----------------------------

-- ----------------------------
-- Table structure for ap_user_realname
-- ----------------------------
DROP TABLE IF EXISTS `ap_user_realname`;
CREATE TABLE `ap_user_realname` (
  `id` int(11) unsigned NOT NULL COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '账号ID',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idno` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
  `font_image` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '正面照片',
  `back_image` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '背面照片',
  `hold_image` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手持照片',
  `live_image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活体照片',
  `status` tinyint(1) unsigned DEFAULT NULL COMMENT '状态\r\n            0 创建中\r\n            1 待审核\r\n            2 审核失败\r\n            9 审核通过',
  `reason` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '拒绝原因',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `submited_time` datetime DEFAULT NULL COMMENT '提交时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP实名认证信息表';

-- ----------------------------
-- Records of ap_user_realname
-- ----------------------------

-- ----------------------------
-- Table structure for ap_user_search
-- ----------------------------
DROP TABLE IF EXISTS `ap_user_search`;
CREATE TABLE `ap_user_search` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `entry_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `keyword` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '搜索词',
  `status` tinyint(2) unsigned DEFAULT NULL COMMENT '当前状态0 无效 1有效',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='APP用户搜索信息表';

-- ----------------------------
-- Records of ap_user_search
-- ----------------------------

-- ----------------------------
-- Table structure for cl_ip_pool
-- ----------------------------
DROP TABLE IF EXISTS `cl_ip_pool`;
CREATE TABLE `cl_ip_pool` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `supplier` varchar(60) DEFAULT NULL COMMENT '供应商',
  `ip` varchar(60) DEFAULT NULL COMMENT 'ip地址',
  `port` int(5) DEFAULT NULL COMMENT '端口号',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `code` int(11) DEFAULT NULL COMMENT '状态码',
  `duration` int(11) DEFAULT NULL COMMENT '访问耗时',
  `error` varchar(100) DEFAULT NULL COMMENT '错误信息',
  `is_enable` tinyint(1) DEFAULT NULL COMMENT '是否启用',
  `ranges` varchar(30) DEFAULT NULL COMMENT '可用范围',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11455 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cl_ip_pool
-- ----------------------------

-- ----------------------------
-- Table structure for cl_material
-- ----------------------------
DROP TABLE IF EXISTS `cl_material`;
CREATE TABLE `cl_material` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '自媒体用户ID',
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片地址',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '素材类型\r\n            0 图片\r\n            1 视频',
  `is_collection` tinyint(1) DEFAULT NULL COMMENT '是否收藏',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='爬得图文素材信息表';

-- ----------------------------
-- Records of cl_material
-- ----------------------------

-- ----------------------------
-- Table structure for cl_news
-- ----------------------------
DROP TABLE IF EXISTS `cl_news`;
CREATE TABLE `cl_news` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_id` int(11) DEFAULT NULL COMMENT '任务ID',
  `url` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '连接URL',
  `title` varchar(108) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  `name` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者名称',
  `content` text COLLATE utf8mb4_unicode_ci COMMENT '图文内容',
  `type` int(11) DEFAULT NULL COMMENT '文章布局  0 无图文章 1 单图文章 2 多图文章',
  `channel_id` int(11) DEFAULT NULL COMMENT '图文频道ID',
  `labels` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `label_ids` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'label关联ID',
  `original_time` datetime DEFAULT NULL COMMENT '原文时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `submited_time` datetime DEFAULT NULL COMMENT '提交时间',
  `status` tinyint(2) DEFAULT NULL COMMENT '当前状态 0 草稿 1 提交（待审核） 2 审核失败 8 审核通过（待发布） 9 已发布',
  `publish_time` datetime DEFAULT NULL COMMENT '定时发布时间，不定时则为空',
  `reason` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '拒绝理由',
  `article_id` int(11) DEFAULT NULL COMMENT '发布库文章ID',
  `no` int(11) DEFAULT NULL COMMENT '任务批次',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31216 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of cl_news
-- ----------------------------

-- ----------------------------
-- Table structure for cl_news_additional
-- ----------------------------
DROP TABLE IF EXISTS `cl_news_additional`;
CREATE TABLE `cl_news_additional` (
  `id` int(11) unsigned NOT NULL COMMENT '主键',
  `news_id` int(11) unsigned DEFAULT NULL COMMENT '图文ID',
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原文地址',
  `read_count` int(11) unsigned DEFAULT NULL COMMENT '阅读量',
  `likes` int(11) DEFAULT NULL,
  `comment` int(11) DEFAULT NULL,
  `forward` int(11) DEFAULT NULL,
  `unlikes` int(11) DEFAULT NULL,
  `collection` int(11) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `count` datetime DEFAULT NULL,
  `update_num` int(11) DEFAULT NULL COMMENT '更新次数',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `next_update_time` datetime DEFAULT NULL COMMENT '下次更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='爬得图文附属信息表';

-- ----------------------------
-- Records of cl_news_additional
-- ----------------------------

-- ----------------------------
-- Table structure for cl_news_comments
-- ----------------------------
DROP TABLE IF EXISTS `cl_news_comments`;
CREATE TABLE `cl_news_comments` (
  `id` int(11) unsigned NOT NULL COMMENT '主键',
  `news_id` int(11) unsigned DEFAULT NULL COMMENT '图文ID',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `content` varchar(1000) DEFAULT NULL COMMENT '评论内容',
  `comment_date` date DEFAULT NULL COMMENT '评论日期',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='爬得图文评论信息表';

-- ----------------------------
-- Records of cl_news_comments
-- ----------------------------

-- ----------------------------
-- Table structure for cl_news_material
-- ----------------------------
DROP TABLE IF EXISTS `cl_news_material`;
CREATE TABLE `cl_news_material` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `material_id` int(11) unsigned DEFAULT NULL COMMENT '素材ID',
  `news_id` int(11) unsigned DEFAULT NULL COMMENT '图文ID',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '引用类型\r\n            0 内容引用\r\n            1 主图引用',
  `ord` tinyint(1) unsigned DEFAULT NULL COMMENT '引用排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='爬得图文引用素材信息表';

-- ----------------------------
-- Records of cl_news_material
-- ----------------------------

-- ----------------------------
-- Table structure for cl_task
-- ----------------------------
DROP TABLE IF EXISTS `cl_task`;
CREATE TABLE `cl_task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '供应商',
  `code` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '任务代码',
  `cron` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'cron表达式',
  `bean_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '完全限定名',
  `method` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '方法名称',
  `interval_time` int(11) DEFAULT NULL COMMENT '任务间隔(S)',
  `is_enable` tinyint(1) unsigned DEFAULT NULL COMMENT '是否有效',
  `last_time` datetime DEFAULT NULL COMMENT '最后运行时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='爬虫任务信息表';

-- ----------------------------
-- Records of cl_task
-- ----------------------------

-- ----------------------------
-- Table structure for cl_task_exception
-- ----------------------------
DROP TABLE IF EXISTS `cl_task_exception`;
CREATE TABLE `cl_task_exception` (
  `id` int(11) unsigned NOT NULL COMMENT '主键',
  `task_id` int(11) unsigned DEFAULT NULL COMMENT '任务ID',
  `no` int(11) unsigned DEFAULT NULL COMMENT '任务批次',
  `url` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '源网页地址',
  `content` text COLLATE utf8mb4_unicode_ci COMMENT '爬取内容',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '异常类型\r\n            0 验证码异常\r\n            1 字体异常',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='爬取异常信息表';

-- ----------------------------
-- Records of cl_task_exception
-- ----------------------------

-- ----------------------------
-- Table structure for cl_task_log
-- ----------------------------
DROP TABLE IF EXISTS `cl_task_log`;
CREATE TABLE `cl_task_log` (
  `id` int(11) unsigned NOT NULL COMMENT '主键',
  `task_id` int(11) unsigned DEFAULT NULL COMMENT '任务ID',
  `no` int(11) unsigned DEFAULT NULL COMMENT '任务批次',
  `count` int(5) unsigned DEFAULT NULL COMMENT '爬得数量',
  `is_success` tinyint(1) unsigned DEFAULT NULL COMMENT '是否有效',
  `start_time` datetime DEFAULT NULL COMMENT '创建时间',
  `end_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='爬取日志表';

-- ----------------------------
-- Records of cl_task_log
-- ----------------------------

-- ----------------------------
-- Table structure for crawler_qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `crawler_qrtz_blob_triggers`;
CREATE TABLE `crawler_qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `CRAWLER_QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `crawler_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of crawler_qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for crawler_qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `crawler_qrtz_calendars`;
CREATE TABLE `crawler_qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of crawler_qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for crawler_qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `crawler_qrtz_cron_triggers`;
CREATE TABLE `crawler_qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `CRAWLER_QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `crawler_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of crawler_qrtz_cron_triggers
-- ----------------------------
INSERT INTO `crawler_qrtz_cron_triggers` VALUES ('quartz.prod.crawler', 'crawlerForwardQuartzTrigger_0', 'crawler_crawlerForwardQuartzTrigger', '0 0/10 * * * ?', 'Asia/Shanghai');
INSERT INTO `crawler_qrtz_cron_triggers` VALUES ('quartz.prod.crawler', 'crawlerReverseQuartzTrigger_0', 'crawler_crawlerReverseQuartzTrigger', '0 0 0/1 * * ?', 'Asia/Shanghai');
INSERT INTO `crawler_qrtz_cron_triggers` VALUES ('quartz.prod.crawler', 'proxyIpUpdateQuartzTrigger_0', 'crawler_proxyIpUpdateQuartzTrigger', '0 0/30 * * * ?', 'Asia/Shanghai');
INSERT INTO `crawler_qrtz_cron_triggers` VALUES ('quartz.prod.crawler', 'proxyIpValidateQuartzTrigger_0', 'crawler_proxyIpValidateQuartzTrigger', '0 10,20,40,50 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for crawler_qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `crawler_qrtz_fired_triggers`;
CREATE TABLE `crawler_qrtz_fired_triggers` (
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
-- Records of crawler_qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for crawler_qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `crawler_qrtz_job_details`;
CREATE TABLE `crawler_qrtz_job_details` (
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
-- Records of crawler_qrtz_job_details
-- ----------------------------
INSERT INTO `crawler_qrtz_job_details` VALUES ('quartz.prod.crawler', 'crawlerForwardQuartzAutoJobDetail', 'crawler_default', '', 'com.heima.crawler.job.CrawlerForwardQuartz', '1', '1', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `crawler_qrtz_job_details` VALUES ('quartz.prod.crawler', 'crawlerReverseQuartzAutoJobDetail', 'crawler_default', '', 'com.heima.crawler.job.CrawlerReverseQuartz', '1', '1', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `crawler_qrtz_job_details` VALUES ('quartz.prod.crawler', 'proxyIpUpdateQuartzAutoJobDetail', 'crawler_default', '', 'com.heima.crawler.job.ProxyIpUpdateQuartz', '1', '1', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `crawler_qrtz_job_details` VALUES ('quartz.prod.crawler', 'proxyIpValidateQuartzAutoJobDetail', 'crawler_default', '', 'com.heima.crawler.job.ProxyIpValidateQuartz', '1', '1', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `crawler_qrtz_job_details` VALUES ('quartz.prod.crawler', 'quartzScanJobDetail', 'init-group-crawler_0', '初始化集群任务', 'com.heima.quartz.QuartzScanJob', '1', '1', '1', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400066272616E636874000E6272616E63682D64656661756C747800);

-- ----------------------------
-- Table structure for crawler_qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `crawler_qrtz_locks`;
CREATE TABLE `crawler_qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of crawler_qrtz_locks
-- ----------------------------
INSERT INTO `crawler_qrtz_locks` VALUES ('quartz.prod.crawler', 'STATE_ACCESS');
INSERT INTO `crawler_qrtz_locks` VALUES ('quartz.prod.crawler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for crawler_qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `crawler_qrtz_paused_trigger_grps`;
CREATE TABLE `crawler_qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of crawler_qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for crawler_qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `crawler_qrtz_scheduler_state`;
CREATE TABLE `crawler_qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of crawler_qrtz_scheduler_state
-- ----------------------------
INSERT INTO `crawler_qrtz_scheduler_state` VALUES ('quartz.prod.crawler', 'quartz.prod.crawler-instanceId', '1566892099343', '7500');

-- ----------------------------
-- Table structure for crawler_qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `crawler_qrtz_simple_triggers`;
CREATE TABLE `crawler_qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `CRAWLER_QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `crawler_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of crawler_qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for crawler_qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `crawler_qrtz_simprop_triggers`;
CREATE TABLE `crawler_qrtz_simprop_triggers` (
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
  CONSTRAINT `CRAWLER_QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `crawler_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of crawler_qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for crawler_qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `crawler_qrtz_triggers`;
CREATE TABLE `crawler_qrtz_triggers` (
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
  CONSTRAINT `CRAWLER_QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `crawler_qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of crawler_qrtz_triggers
-- ----------------------------
INSERT INTO `crawler_qrtz_triggers` VALUES ('quartz.prod.crawler', 'crawlerForwardQuartzTrigger_0', 'crawler_crawlerForwardQuartzTrigger', 'crawlerForwardQuartzAutoJobDetail', 'crawler_default', '', '1566892200000', '1566891600000', '0', 'WAITING', 'CRON', '1566532621000', '0', null, '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400096A6F6244657461696C7372001D6F72672E71756172747A2E696D706C2E4A6F6244657461696C496D706CABC3CAEC015A54AF0200075A000A6475726162696C6974795A000D73686F756C645265636F7665724C000B6465736372697074696F6E7400124C6A6176612F6C616E672F537472696E673B4C000567726F757071007E00094C00086A6F62436C6173737400114C6A6176612F6C616E672F436C6173733B4C000A6A6F62446174614D61707400174C6F72672F71756172747A2F4A6F62446174614D61703B4C00046E616D6571007E00097870010174000074000F637261776C65725F64656661756C747672002A636F6D2E6865696D612E637261776C65722E6A6F622E437261776C6572466F727761726451756172747A000000000000000000000078707371007E0000007371007E00053F40000000000010770800000010000000007800740021637261776C6572466F727761726451756172747A4175746F4A6F6244657461696C7800);
INSERT INTO `crawler_qrtz_triggers` VALUES ('quartz.prod.crawler', 'crawlerReverseQuartzTrigger_0', 'crawler_crawlerReverseQuartzTrigger', 'crawlerReverseQuartzAutoJobDetail', 'crawler_default', '', '1566892800000', '1566889200000', '0', 'WAITING', 'CRON', '1566532621000', '0', null, '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400096A6F6244657461696C7372001D6F72672E71756172747A2E696D706C2E4A6F6244657461696C496D706CABC3CAEC015A54AF0200075A000A6475726162696C6974795A000D73686F756C645265636F7665724C000B6465736372697074696F6E7400124C6A6176612F6C616E672F537472696E673B4C000567726F757071007E00094C00086A6F62436C6173737400114C6A6176612F6C616E672F436C6173733B4C000A6A6F62446174614D61707400174C6F72672F71756172747A2F4A6F62446174614D61703B4C00046E616D6571007E00097870010174000074000F637261776C65725F64656661756C747672002A636F6D2E6865696D612E637261776C65722E6A6F622E437261776C65725265766572736551756172747A000000000000000000000078707371007E0000007371007E00053F40000000000010770800000010000000007800740021637261776C65725265766572736551756172747A4175746F4A6F6244657461696C7800);
INSERT INTO `crawler_qrtz_triggers` VALUES ('quartz.prod.crawler', 'proxyIpUpdateQuartzTrigger_0', 'crawler_proxyIpUpdateQuartzTrigger', 'proxyIpUpdateQuartzAutoJobDetail', 'crawler_default', '', '1566892800000', '1566891000000', '0', 'WAITING', 'CRON', '1566532621000', '0', null, '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400096A6F6244657461696C7372001D6F72672E71756172747A2E696D706C2E4A6F6244657461696C496D706CABC3CAEC015A54AF0200075A000A6475726162696C6974795A000D73686F756C645265636F7665724C000B6465736372697074696F6E7400124C6A6176612F6C616E672F537472696E673B4C000567726F757071007E00094C00086A6F62436C6173737400114C6A6176612F6C616E672F436C6173733B4C000A6A6F62446174614D61707400174C6F72672F71756172747A2F4A6F62446174614D61703B4C00046E616D6571007E00097870010174000074000F637261776C65725F64656661756C7476720029636F6D2E6865696D612E637261776C65722E6A6F622E50726F7879497055706461746551756172747A000000000000000000000078707371007E0000007371007E00053F4000000000001077080000001000000000780074002070726F7879497055706461746551756172747A4175746F4A6F6244657461696C7800);
INSERT INTO `crawler_qrtz_triggers` VALUES ('quartz.prod.crawler', 'proxyIpValidateQuartzTrigger_0', 'crawler_proxyIpValidateQuartzTrigger', 'proxyIpValidateQuartzAutoJobDetail', 'crawler_default', '', '1566892200000', '1566891600000', '0', 'WAITING', 'CRON', '1566532621000', '0', null, '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400096A6F6244657461696C7372001D6F72672E71756172747A2E696D706C2E4A6F6244657461696C496D706CABC3CAEC015A54AF0200075A000A6475726162696C6974795A000D73686F756C645265636F7665724C000B6465736372697074696F6E7400124C6A6176612F6C616E672F537472696E673B4C000567726F757071007E00094C00086A6F62436C6173737400114C6A6176612F6C616E672F436C6173733B4C000A6A6F62446174614D61707400174C6F72672F71756172747A2F4A6F62446174614D61703B4C00046E616D6571007E00097870010174000074000F637261776C65725F64656661756C747672002B636F6D2E6865696D612E637261776C65722E6A6F622E50726F7879497056616C696461746551756172747A000000000000000000000078707371007E0000007371007E00053F4000000000001077080000001000000000780074002270726F7879497056616C696461746551756172747A4175746F4A6F6244657461696C7800);

-- ----------------------------
-- Table structure for migration_qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `migration_qrtz_blob_triggers`;
CREATE TABLE `migration_qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `MIGRATION_QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `migration_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of migration_qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for migration_qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `migration_qrtz_calendars`;
CREATE TABLE `migration_qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of migration_qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for migration_qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `migration_qrtz_cron_triggers`;
CREATE TABLE `migration_qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `MIGRATION_QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `migration_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of migration_qrtz_cron_triggers
-- ----------------------------
INSERT INTO `migration_qrtz_cron_triggers` VALUES ('quartz.prod.migration', 'migrationDbToHBaseQuartzTrigger_0', 'migration_migrationDbToHBaseQuartzTrigger', '0 0/5 * * * ?', 'Asia/Shanghai');
INSERT INTO `migration_qrtz_cron_triggers` VALUES ('quartz.prod.migration', 'migrationDeleteHotDataQuartzTrigger_0', 'migration_migrationDeleteHotDataQuartzTrigger', '0 30 22 * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for migration_qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `migration_qrtz_fired_triggers`;
CREATE TABLE `migration_qrtz_fired_triggers` (
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
-- Records of migration_qrtz_fired_triggers
-- ----------------------------
INSERT INTO `migration_qrtz_fired_triggers` VALUES ('quartz.prod.migration', 'quartz.prod.migration-instanceId1566527436038', 'migrationDbToHBaseQuartzTrigger_0', 'migration_migrationDbToHBaseQuartzTrigger', 'quartz.prod.migration-instanceId', '1566549602909', '1566549600000', '0', 'EXECUTING', 'migrationDbToHBaseQuartzAutoJobDetail', 'migration_default', '1', '1');

-- ----------------------------
-- Table structure for migration_qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `migration_qrtz_job_details`;
CREATE TABLE `migration_qrtz_job_details` (
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
-- Records of migration_qrtz_job_details
-- ----------------------------
INSERT INTO `migration_qrtz_job_details` VALUES ('quartz.prod.migration', 'migrationDbToHBaseQuartzAutoJobDetail', 'migration_default', '', 'com.heima.migration.quartz.MigrationDbToHBaseQuartz', '1', '1', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `migration_qrtz_job_details` VALUES ('quartz.prod.migration', 'migrationDeleteHotDataQuartzAutoJobDetail', 'migration_default', '', 'com.heima.migration.quartz.MigrationDeleteHotDataQuartz', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `migration_qrtz_job_details` VALUES ('quartz.prod.migration', 'quartzScanJobDetail', 'init-group-migration_0', '初始化集群任务', 'com.heima.quartz.QuartzScanJob', '1', '1', '1', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400066272616E636874000E6272616E63682D64656661756C747800);

-- ----------------------------
-- Table structure for migration_qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `migration_qrtz_locks`;
CREATE TABLE `migration_qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of migration_qrtz_locks
-- ----------------------------
INSERT INTO `migration_qrtz_locks` VALUES ('quartz.prod.migration', 'STATE_ACCESS');
INSERT INTO `migration_qrtz_locks` VALUES ('quartz.prod.migration', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for migration_qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `migration_qrtz_paused_trigger_grps`;
CREATE TABLE `migration_qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of migration_qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for migration_qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `migration_qrtz_scheduler_state`;
CREATE TABLE `migration_qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of migration_qrtz_scheduler_state
-- ----------------------------
INSERT INTO `migration_qrtz_scheduler_state` VALUES ('quartz.prod.migration', 'quartz.prod.migration-instanceId', '1566549614458', '7500');

-- ----------------------------
-- Table structure for migration_qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `migration_qrtz_simple_triggers`;
CREATE TABLE `migration_qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `MIGRATION_QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `migration_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of migration_qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for migration_qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `migration_qrtz_simprop_triggers`;
CREATE TABLE `migration_qrtz_simprop_triggers` (
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
  CONSTRAINT `MIGRATION_QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `migration_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of migration_qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for migration_qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `migration_qrtz_triggers`;
CREATE TABLE `migration_qrtz_triggers` (
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
  CONSTRAINT `MIGRATION_QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `migration_qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of migration_qrtz_triggers
-- ----------------------------
INSERT INTO `migration_qrtz_triggers` VALUES ('quartz.prod.migration', 'migrationDbToHBaseQuartzTrigger_0', 'migration_migrationDbToHBaseQuartzTrigger', 'migrationDbToHBaseQuartzAutoJobDetail', 'migration_default', '', '1566549900000', '1566549600000', '0', 'BLOCKED', 'CRON', '1565920257000', '0', null, '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400096A6F6244657461696C7372001D6F72672E71756172747A2E696D706C2E4A6F6244657461696C496D706CABC3CAEC015A54AF0200075A000A6475726162696C6974795A000D73686F756C645265636F7665724C000B6465736372697074696F6E7400124C6A6176612F6C616E672F537472696E673B4C000567726F757071007E00094C00086A6F62436C6173737400114C6A6176612F6C616E672F436C6173733B4C000A6A6F62446174614D61707400174C6F72672F71756172747A2F4A6F62446174614D61703B4C00046E616D6571007E0009787001017400007400116D6967726174696F6E5F64656661756C7476720033636F6D2E6865696D612E6D6967726174696F6E2E71756172747A2E4D6967726174696F6E4462546F484261736551756172747A000000000000000000000078707371007E0000007371007E00053F400000000000107708000000100000000078007400256D6967726174696F6E4462546F484261736551756172747A4175746F4A6F6244657461696C7800);
INSERT INTO `migration_qrtz_triggers` VALUES ('quartz.prod.migration', 'migrationDeleteHotDataQuartzTrigger_0', 'migration_migrationDeleteHotDataQuartzTrigger', 'migrationDeleteHotDataQuartzAutoJobDetail', 'migration_default', '', '1566570600000', '1566484200000', '0', 'WAITING', 'CRON', '1565920258000', '0', null, '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400096A6F6244657461696C7372001D6F72672E71756172747A2E696D706C2E4A6F6244657461696C496D706CABC3CAEC015A54AF0200075A000A6475726162696C6974795A000D73686F756C645265636F7665724C000B6465736372697074696F6E7400124C6A6176612F6C616E672F537472696E673B4C000567726F757071007E00094C00086A6F62436C6173737400114C6A6176612F6C616E672F436C6173733B4C000A6A6F62446174614D61707400174C6F72672F71756172747A2F4A6F62446174614D61703B4C00046E616D6571007E0009787001017400007400116D6967726174696F6E5F64656661756C7476720037636F6D2E6865696D612E6D6967726174696F6E2E71756172747A2E4D6967726174696F6E44656C657465486F744461746151756172747A000000000000000000000078707371007E0000007371007E00053F400000000000107708000000100000000078007400296D6967726174696F6E44656C657465486F744461746151756172747A4175746F4A6F6244657461696C7800);

-- ----------------------------
-- Table structure for quartz_qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `quartz_qrtz_blob_triggers`;
CREATE TABLE `quartz_qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QUARTZ_QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `quartz_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for quartz_qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `quartz_qrtz_calendars`;
CREATE TABLE `quartz_qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for quartz_qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `quartz_qrtz_cron_triggers`;
CREATE TABLE `quartz_qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QUARTZ_QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `quartz_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_qrtz_cron_triggers
-- ----------------------------
INSERT INTO `quartz_qrtz_cron_triggers` VALUES ('quartz.prod.quartz', 'wmNewsStatisticJobTrigger_0', 'quartz_wmNewsStatisticJobTrigger', '* */5 * * * ?', 'Etc/UTC');
INSERT INTO `quartz_qrtz_cron_triggers` VALUES ('quartz.test.quartz', 'wmNewsStatisticJobTrigger_0', 'quartz_wmNewsStatisticJobTrigger', '* */5 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for quartz_qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `quartz_qrtz_fired_triggers`;
CREATE TABLE `quartz_qrtz_fired_triggers` (
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
-- Records of quartz_qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for quartz_qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `quartz_qrtz_job_details`;
CREATE TABLE `quartz_qrtz_job_details` (
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
-- Records of quartz_qrtz_job_details
-- ----------------------------
INSERT INTO `quartz_qrtz_job_details` VALUES ('quartz.prod.quartz', 'quartzScanJobDetail', 'init-group-quartz_0', '初始化集群任务', 'com.heima.quartz.QuartzScanJob', '1', '1', '1', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400066272616E636874000E6272616E63682D64656661756C747800);
INSERT INTO `quartz_qrtz_job_details` VALUES ('quartz.prod.quartz', 'wmNewsStatisticJobAutoJobDetail', 'quartz_default', '', 'com.heima.quartz.jobs.WmNewsStatisticJob', '1', '1', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `quartz_qrtz_job_details` VALUES ('quartz.test.quartz', 'quartzScanJobDetail', 'init-group-quartz_0', '初始化集群任务', 'com.heima.quartz.QuartzScanJob', '1', '1', '1', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400066272616E636874000E6272616E63682D64656661756C747800);
INSERT INTO `quartz_qrtz_job_details` VALUES ('quartz.test.quartz', 'wmNewsStatisticJobAutoJobDetail', 'quartz_default', '', 'com.heima.quartz.jobs.WmNewsStatisticJob', '1', '1', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);

-- ----------------------------
-- Table structure for quartz_qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `quartz_qrtz_locks`;
CREATE TABLE `quartz_qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_qrtz_locks
-- ----------------------------
INSERT INTO `quartz_qrtz_locks` VALUES ('quartz.prod.quartz', 'STATE_ACCESS');
INSERT INTO `quartz_qrtz_locks` VALUES ('quartz.prod.quartz', 'TRIGGER_ACCESS');
INSERT INTO `quartz_qrtz_locks` VALUES ('quartz.test.quartz', 'STATE_ACCESS');
INSERT INTO `quartz_qrtz_locks` VALUES ('quartz.test.quartz', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for quartz_qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `quartz_qrtz_paused_trigger_grps`;
CREATE TABLE `quartz_qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for quartz_qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `quartz_qrtz_scheduler_state`;
CREATE TABLE `quartz_qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_qrtz_scheduler_state
-- ----------------------------
INSERT INTO `quartz_qrtz_scheduler_state` VALUES ('quartz.prod.quartz', 'quartz.prod.quartz-instanceId', '1566892108206', '7500');
INSERT INTO `quartz_qrtz_scheduler_state` VALUES ('quartz.test.quartz', 'quartz.test.quartz-instanceId', '1566553074818', '7500');

-- ----------------------------
-- Table structure for quartz_qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `quartz_qrtz_simple_triggers`;
CREATE TABLE `quartz_qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QUARTZ_QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `quartz_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for quartz_qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `quartz_qrtz_simprop_triggers`;
CREATE TABLE `quartz_qrtz_simprop_triggers` (
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
  CONSTRAINT `QUARTZ_QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `quartz_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for quartz_qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `quartz_qrtz_triggers`;
CREATE TABLE `quartz_qrtz_triggers` (
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
  CONSTRAINT `QUARTZ_QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `quartz_qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quartz_qrtz_triggers
-- ----------------------------
INSERT INTO `quartz_qrtz_triggers` VALUES ('quartz.prod.quartz', 'wmNewsStatisticJobTrigger_0', 'quartz_wmNewsStatisticJobTrigger', 'wmNewsStatisticJobAutoJobDetail', 'quartz_default', '每天晚上23:30执行', '1566892200000', '1566891959000', '0', 'WAITING', 'CRON', '1565451439000', '0', null, '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400096A6F6244657461696C7372001D6F72672E71756172747A2E696D706C2E4A6F6244657461696C496D706CABC3CAEC015A54AF0200075A000A6475726162696C6974795A000D73686F756C645265636F7665724C000B6465736372697074696F6E7400124C6A6176612F6C616E672F537472696E673B4C000567726F757071007E00094C00086A6F62436C6173737400114C6A6176612F6C616E672F436C6173733B4C000A6A6F62446174614D61707400174C6F72672F71756172747A2F4A6F62446174614D61703B4C00046E616D6571007E00097870010174000074000E71756172747A5F64656661756C7476720028636F6D2E6865696D612E71756172747A2E6A6F62732E576D4E6577735374617469737469634A6F62000000000000000000000078707371007E0000007371007E00053F4000000000001077080000001000000000780074001F776D4E6577735374617469737469634A6F624175746F4A6F6244657461696C7800);
INSERT INTO `quartz_qrtz_triggers` VALUES ('quartz.test.quartz', 'wmNewsStatisticJobTrigger_0', 'quartz_wmNewsStatisticJobTrigger', 'wmNewsStatisticJobAutoJobDetail', 'quartz_default', '每天晚上23:30执行', '1566553200000', '1566552959000', '0', 'WAITING', 'CRON', '1565415758000', '0', null, '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400096A6F6244657461696C7372001D6F72672E71756172747A2E696D706C2E4A6F6244657461696C496D706CABC3CAEC015A54AF0200075A000A6475726162696C6974795A000D73686F756C645265636F7665724C000B6465736372697074696F6E7400124C6A6176612F6C616E672F537472696E673B4C000567726F757071007E00094C00086A6F62436C6173737400114C6A6176612F6C616E672F436C6173733B4C000A6A6F62446174614D61707400174C6F72672F71756172747A2F4A6F62446174614D61703B4C00046E616D6571007E00097870010174000074000E71756172747A5F64656661756C7476720028636F6D2E6865696D612E71756172747A2E6A6F62732E576D4E6577735374617469737469634A6F62000000000000000000000078707371007E0000007371007E00053F4000000000001077080000001000000000780074001F776D4E6577735374617469737469634A6F624175746F4A6F6244657461696C7800);

-- ----------------------------
-- Table structure for wm_fans_portrait
-- ----------------------------
DROP TABLE IF EXISTS `wm_fans_portrait`;
CREATE TABLE `wm_fans_portrait` (
  `id` int(11) unsigned NOT NULL COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '账号ID',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '画像项目',
  `value` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
  `burst` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='自媒体粉丝画像信息表';

-- ----------------------------
-- Records of wm_fans_portrait
-- ----------------------------

-- ----------------------------
-- Table structure for wm_fans_statistics
-- ----------------------------
DROP TABLE IF EXISTS `wm_fans_statistics`;
CREATE TABLE `wm_fans_statistics` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '主账号ID',
  `article` int(11) unsigned DEFAULT NULL COMMENT '子账号ID',
  `read_count` int(11) unsigned DEFAULT NULL,
  `comment` int(11) unsigned DEFAULT NULL,
  `follow` int(11) unsigned DEFAULT NULL,
  `collection` int(11) unsigned DEFAULT NULL,
  `forward` int(11) unsigned DEFAULT NULL,
  `likes` int(11) unsigned DEFAULT NULL,
  `unlikes` int(11) unsigned DEFAULT NULL,
  `unfollow` int(11) unsigned DEFAULT NULL,
  `burst` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_user_id_time` (`user_id`,`created_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='自媒体粉丝数据统计表';

-- ----------------------------
-- Records of wm_fans_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for wm_material
-- ----------------------------
DROP TABLE IF EXISTS `wm_material`;
CREATE TABLE `wm_material` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '自媒体用户ID',
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片地址',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '素材类型\r\n            0 图片\r\n            1 视频',
  `is_collection` tinyint(1) DEFAULT NULL COMMENT '是否收藏',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='自媒体图文素材信息表';

-- ----------------------------
-- Records of wm_material
-- ----------------------------

-- ----------------------------
-- Table structure for wm_news
-- ----------------------------
DROP TABLE IF EXISTS `wm_news`;
CREATE TABLE `wm_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '自媒体用户ID',
  `title` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  `content` longtext COLLATE utf8mb4_unicode_ci COMMENT '图文内容',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '文章布局\r\n            0 无图文章\r\n            1 单图文章\r\n            2 多图文章',
  `channel_id` int(11) unsigned DEFAULT NULL COMMENT '图文频道ID',
  `labels` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `submited_time` datetime DEFAULT NULL COMMENT '提交时间',
  `status` tinyint(2) unsigned DEFAULT NULL COMMENT '当前状态\r\n            0 草稿\r\n            1 提交（待审核）\r\n            2 审核失败\r\n            8 审核通过（待发布）\r\n            9 已发布',
  `publish_time` datetime DEFAULT NULL COMMENT '定时发布时间，不定时则为空',
  `reason` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '拒绝理由',
  `article_id` int(11) unsigned DEFAULT NULL COMMENT '发布库文章ID',
  `images` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '//图片用逗号分隔',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='自媒体图文内容信息表';

-- ----------------------------
-- Records of wm_news
-- ----------------------------

-- ----------------------------
-- Table structure for wm_news_material
-- ----------------------------
DROP TABLE IF EXISTS `wm_news_material`;
CREATE TABLE `wm_news_material` (
  `id` int(11) unsigned NOT NULL COMMENT '主键',
  `material_id` int(11) unsigned DEFAULT NULL COMMENT '素材ID',
  `news_id` int(11) unsigned DEFAULT NULL COMMENT '图文ID',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '引用类型\r\n            0 内容引用\r\n            1 主图引用',
  `ord` tinyint(1) unsigned DEFAULT NULL COMMENT '引用排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='自媒体图文引用素材信息表';

-- ----------------------------
-- Records of wm_news_material
-- ----------------------------

-- ----------------------------
-- Table structure for wm_news_statistics
-- ----------------------------
DROP TABLE IF EXISTS `wm_news_statistics`;
CREATE TABLE `wm_news_statistics` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '主账号ID',
  `article` int(11) unsigned DEFAULT NULL COMMENT '子账号ID',
  `read_count` int(11) unsigned DEFAULT NULL COMMENT '阅读量',
  `comment` int(11) unsigned DEFAULT NULL COMMENT '评论量',
  `follow` int(11) unsigned DEFAULT NULL COMMENT '关注量',
  `collection` int(11) unsigned DEFAULT NULL COMMENT '收藏量',
  `forward` int(11) unsigned DEFAULT NULL COMMENT '转发量',
  `likes` int(11) unsigned DEFAULT NULL COMMENT '点赞量',
  `unlikes` int(11) unsigned DEFAULT NULL COMMENT '不喜欢',
  `unfollow` int(11) unsigned DEFAULT NULL COMMENT '取消关注量',
  `burst` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_user_id_time` (`user_id`,`created_time`)
) ENGINE=InnoDB AUTO_INCREMENT=295732 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='自媒体图文数据统计表';

-- ----------------------------
-- Records of wm_news_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for wm_sub_user
-- ----------------------------
DROP TABLE IF EXISTS `wm_sub_user`;
CREATE TABLE `wm_sub_user` (
  `id` int(11) unsigned NOT NULL COMMENT '主键',
  `parent_id` int(11) unsigned DEFAULT NULL COMMENT '主账号ID',
  `children_id` int(11) unsigned DEFAULT NULL COMMENT '子账号ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='自媒体子账号信息表';

-- ----------------------------
-- Records of wm_sub_user
-- ----------------------------

-- ----------------------------
-- Table structure for wm_user
-- ----------------------------
DROP TABLE IF EXISTS `wm_user`;
CREATE TABLE `wm_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ap_user_id` int(11) DEFAULT NULL,
  `ap_author_id` int(11) DEFAULT NULL,
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录用户名',
  `password` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录密码',
  `salt` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '盐',
  `nickname` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `location` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '归属地',
  `phone` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `status` tinyint(11) unsigned DEFAULT NULL COMMENT '状态\r\n            0 暂时不可用\r\n            1 永久不可用\r\n            9 正常可用',
  `email` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '账号类型\r\n            0 个人 \r\n            1 企业\r\n            2 子账号',
  `score` tinyint(3) unsigned DEFAULT NULL COMMENT '运营评分',
  `login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='自媒体用户信息表';

-- ----------------------------
-- Records of wm_user
-- ----------------------------

-- ----------------------------
-- Table structure for wm_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `wm_user_auth`;
CREATE TABLE `wm_user_auth` (
  `id` int(11) unsigned NOT NULL COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '账号ID',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '资源类型\r\n            0 菜单\r\n            1 频道\r\n            2 按钮',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='自媒体子账号权限信息表';

-- ----------------------------
-- Records of wm_user_auth
-- ----------------------------

-- ----------------------------
-- Table structure for wm_user_equipment
-- ----------------------------
DROP TABLE IF EXISTS `wm_user_equipment`;
CREATE TABLE `wm_user_equipment` (
  `id` int(11) unsigned NOT NULL,
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '0PC\r\n            1ANDROID\r\n            2IOS\r\n            3PAD\r\n            9 其他',
  `version` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设备版本',
  `sys` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设备系统',
  `no` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设备唯一号，MD5加密',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='自媒体用户设备信息表';

-- ----------------------------
-- Records of wm_user_equipment
-- ----------------------------

-- ----------------------------
-- Table structure for wm_user_login
-- ----------------------------
DROP TABLE IF EXISTS `wm_user_login`;
CREATE TABLE `wm_user_login` (
  `id` int(11) unsigned NOT NULL,
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `equipment_id` int(11) unsigned DEFAULT NULL COMMENT '登录设备ID',
  `ip` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录IP',
  `address` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录地址',
  `longitude` decimal(5,5) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(5,5) DEFAULT NULL COMMENT '纬度',
  `created_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='自媒体用户登录行为信息表';

-- ----------------------------
-- Records of wm_user_login
-- ----------------------------
