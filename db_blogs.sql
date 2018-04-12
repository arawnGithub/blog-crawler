/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : db_blogs

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2018-04-12 14:34:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_arctype
-- ----------------------------
DROP TABLE IF EXISTS `t_arctype`;
CREATE TABLE `t_arctype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_name` varchar(50) DEFAULT '' COMMENT '类别名称',
  `sort_no` int(11) DEFAULT '0' COMMENT '排列序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='文章类别表';

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(200) DEFAULT '' COMMENT '文章名称',
  `content` longtext COMMENT '内容',
  `summary` varchar(400) DEFAULT '' COMMENT '摘要',
  `crawler_date` datetime DEFAULT NULL COMMENT '爬虫时间',
  `click_hit` int(11) DEFAULT '0' COMMENT '点击次数',
  `type_id` int(11) DEFAULT '0' COMMENT '类别ID',
  `tags` varchar(200) DEFAULT '' COMMENT '标签',
  `original_url` varchar(1000) DEFAULT '' COMMENT '原始URL',
  `state` int(11) DEFAULT '0' COMMENT '状态 0-未发布 1-发布',
  `release_date` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Table structure for t_link
-- ----------------------------
DROP TABLE IF EXISTS `t_link`;
CREATE TABLE `t_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) DEFAULT '' COMMENT '链接名称',
  `url` varchar(200) DEFAULT '' COMMENT '链接URL',
  `sort_no` int(11) DEFAULT '0' COMMENT '排列序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='友情链接表';

-- ----------------------------
-- Table structure for t_manager
-- ----------------------------
DROP TABLE IF EXISTS `t_manager`;
CREATE TABLE `t_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) DEFAULT '' COMMENT '用户名',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员表';
SET FOREIGN_KEY_CHECKS=1;
