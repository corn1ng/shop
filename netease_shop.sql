/*
 Navicat Premium Data Transfer

 Source Server         : w
 Source Server Type    : MySQL
 Source Server Version : 50635
 Source Host           : localhost:3306
 Source Schema         : netease_shop

 Target Server Type    : MySQL
 Target Server Version : 50635
 File Encoding         : 65001

 Date: 12/02/2019 19:51:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(80) NOT NULL,
  `abstra` varchar(140) DEFAULT NULL,
  `weburl` varchar(1000) DEFAULT NULL,
  `picurl` varchar(1000) DEFAULT NULL,
  `content` varchar(10000) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for orderinfo
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '用户id',
  `goodsId` int(11) NOT NULL COMMENT '商品id',
  `nowTime` varchar(100) DEFAULT NULL,
  `orderTime` date DEFAULT NULL COMMENT '订单时间',
  `purchasedAmount` int(11) DEFAULT NULL COMMENT '商品数量',
  `purchasedUnitPrice` int(20) DEFAULT NULL COMMENT '购买时单价',
  `priceSum` int(20) DEFAULT NULL COMMENT '总价',
  `isCompleted` varchar(2) DEFAULT NULL COMMENT '是否完成，0-未完成 1-完成',
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for shopcart
-- ----------------------------
DROP TABLE IF EXISTS `shopcart`;
CREATE TABLE `shopcart` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `purchasedAmount` int(11) DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`user_id`,`goods_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
