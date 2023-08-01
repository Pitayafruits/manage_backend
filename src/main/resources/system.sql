/*
 Navicat MySQL Data Transfer

 Source Server         : datasource
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : management

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 01/08/2023 21:13:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comm_facilities
-- ----------------------------
DROP TABLE IF EXISTS `comm_facilities`;
CREATE TABLE `comm_facilities`  (
  `fa_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设施ID',
  `fa_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设施名称',
  `fa_person` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone_number` bigint(30) NULL DEFAULT NULL COMMENT '联系电话',
  `fa_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`fa_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comm_facilities
-- ----------------------------
INSERT INTO `comm_facilities` VALUES (3, '3号设施', '3号负责人', 13533333333, '3号设施简介');
INSERT INTO `comm_facilities` VALUES (4, '1号设施', '1号负责人', 13511111111, '1号设施简介');
INSERT INTO `comm_facilities` VALUES (5, '4号', '4号', 18992981234, '4号');

-- ----------------------------
-- Table structure for comm_info
-- ----------------------------
DROP TABLE IF EXISTS `comm_info`;
CREATE TABLE `comm_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `comm_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小区名称',
  `comm_man` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小区负责人',
  `create_time` date NULL DEFAULT NULL COMMENT '建成日期',
  `build_num` int(3) NULL DEFAULT NULL COMMENT '楼宇数量',
  `comm_adr` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小区地址',
  `phone_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `comm_area` int(10) NULL DEFAULT NULL COMMENT '建筑面积',
  `green_area` int(10) NULL DEFAULT NULL COMMENT '绿化面积',
  `road_area` int(10) NULL DEFAULT NULL COMMENT '道路面积',
  `stop_area` int(10) NULL DEFAULT NULL COMMENT '停车场面积',
  `comm_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '小区简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comm_info
-- ----------------------------
INSERT INTO `comm_info` VALUES (5, '测试小区', '测试小区', '2023-02-07', 999, '测试小区', '测试小区', 999, 999, 999, 999, '测试小区');

-- ----------------------------
-- Table structure for comm_notice
-- ----------------------------
DROP TABLE IF EXISTS `comm_notice`;
CREATE TABLE `comm_notice`  (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `notice_title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `notice_text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `notice_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comm_notice
-- ----------------------------
INSERT INTO `comm_notice` VALUES (9, 2, '测试公告', '测试公告哈哈哈哈', '2023-02-21 15:14:41');
INSERT INTO `comm_notice` VALUES (10, 2, '测试公告2', '测试2222222222222222222222222222222222222222222222222222', '2023-03-08 17:05:07');
INSERT INTO `comm_notice` VALUES (11, 2, ' 测试公告3', ' 测试公告3 测试公告3 测试公告3 测试公告3 测试公告3 测试公告3', '2023-03-08 17:26:54');
INSERT INTO `comm_notice` VALUES (12, 2, ' 测试公告4', ' 测试公告4 测试公告4 测试公告4 测试公告4 测试公告4 测试公告4 测试公告4 测试公告4 测试公告4 测试公告4 测试公告4 测试公告4', '2023-03-08 17:27:04');
INSERT INTO `comm_notice` VALUES (13, 2, '测试公告5', '测试公告5测试公告5测试公告5测试公告5测试公告5测试公告5测试公告5测试公告5测试公告5测试公告5测试公告5测试公告5测试公告5', '2023-03-08 17:29:14');

-- ----------------------------
-- Table structure for def_complaint
-- ----------------------------
DROP TABLE IF EXISTS `def_complaint`;
CREATE TABLE `def_complaint`  (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `liver_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `complaint_title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投诉标题',
  `complaint_text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '投诉内容',
  `complaint_time` datetime NULL DEFAULT NULL COMMENT '投诉时间',
  `slove_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理状态  0：未处理 1：已处理',
  `complaint_reply` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '处理回复',
  PRIMARY KEY (`complaint_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of def_complaint
-- ----------------------------
INSERT INTO `def_complaint` VALUES (7, 5, ' 测试2', '测试2', '2023-02-10 10:03:49', '1', '测试2');
INSERT INTO `def_complaint` VALUES (10, 6, '测试3', '测试3', '2023-02-14 10:03:49', '1', '处理');

-- ----------------------------
-- Table structure for def_repair
-- ----------------------------
DROP TABLE IF EXISTS `def_repair`;
CREATE TABLE `def_repair`  (
  `repair_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `liver_id` int(11) NULL DEFAULT NULL COMMENT '报修业主id',
  `repair_place` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '维修地址',
  `repair_text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '维修内容',
  `repair_time` datetime NULL DEFAULT NULL COMMENT '报修时间',
  `repair_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修状态 0:未维修 1：已维修',
  PRIMARY KEY (`repair_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of def_repair
-- ----------------------------
INSERT INTO `def_repair` VALUES (3, 4, '测试地点', '测试内容', '2023-02-14 07:50:38', '1');
INSERT INTO `def_repair` VALUES (4, 5, 'A栋2单元3楼', '走廊的声控灯坏了', '2023-04-15 15:46:40', '1');
INSERT INTO `def_repair` VALUES (5, 12, 'XXX某地', 'XXX坏了', '2023-05-24 18:27:24', '1');

-- ----------------------------
-- Table structure for house_building
-- ----------------------------
DROP TABLE IF EXISTS `house_building`;
CREATE TABLE `house_building`  (
  `building_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '楼宇id',
  `building_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '楼宇类型 0:普通房 1:电梯房',
  `building_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '楼宇名称',
  `building_number` int(11) NULL DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`building_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house_building
-- ----------------------------
INSERT INTO `house_building` VALUES (2, '1', 'B栋', 2);
INSERT INTO `house_building` VALUES (3, '1', 'A栋', 1);
INSERT INTO `house_building` VALUES (4, '1', 'C栋', 3);
INSERT INTO `house_building` VALUES (5, '0', 'D栋', 4);

-- ----------------------------
-- Table structure for house_list
-- ----------------------------
DROP TABLE IF EXISTS `house_list`;
CREATE TABLE `house_list`  (
  `house_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '房屋id',
  `unit_id` int(11) NULL DEFAULT NULL COMMENT '单元id',
  `house_num` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房屋编号',
  `house_area` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房屋面积',
  `house_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用状态 0：未使用 1：已使用',
  PRIMARY KEY (`house_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house_list
-- ----------------------------
INSERT INTO `house_list` VALUES (5, 2, 'B1-101', '120', '1');
INSERT INTO `house_list` VALUES (6, 2, 'B1-102', '120', '0');
INSERT INTO `house_list` VALUES (7, 2, 'B1-201', '100', '1');
INSERT INTO `house_list` VALUES (9, 3, 'A1-101', '120', '0');
INSERT INTO `house_list` VALUES (10, 3, '测试房屋', '130', '1');
INSERT INTO `house_list` VALUES (11, 4, 'C1-101', '130', '0');
INSERT INTO `house_list` VALUES (12, 5, 'D1-101', '100', '0');

-- ----------------------------
-- Table structure for house_unit
-- ----------------------------
DROP TABLE IF EXISTS `house_unit`;
CREATE TABLE `house_unit`  (
  `unit_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '单元id',
  `building_id` int(11) NULL DEFAULT NULL COMMENT '楼宇id',
  `unit_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单元名称',
  PRIMARY KEY (`unit_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house_unit
-- ----------------------------
INSERT INTO `house_unit` VALUES (2, 2, 'B1');
INSERT INTO `house_unit` VALUES (3, 3, 'A1');
INSERT INTO `house_unit` VALUES (4, 4, 'C1');
INSERT INTO `house_unit` VALUES (5, 5, 'D1');

-- ----------------------------
-- Table structure for liver_house
-- ----------------------------
DROP TABLE IF EXISTS `liver_house`;
CREATE TABLE `liver_house`  (
  `liver_house_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `liver_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `house_id` int(11) NULL DEFAULT NULL COMMENT '房屋id',
  `liver_house_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用状态 0:入住 1:退房',
  PRIMARY KEY (`liver_house_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of liver_house
-- ----------------------------
INSERT INTO `liver_house` VALUES (5, 4, 5, '0');
INSERT INTO `liver_house` VALUES (7, 5, 9, '1');
INSERT INTO `liver_house` VALUES (8, 6, 10, '1');
INSERT INTO `liver_house` VALUES (9, 6, 10, '1');
INSERT INTO `liver_house` VALUES (10, 6, 10, '0');
INSERT INTO `liver_house` VALUES (11, 7, 7, '0');
INSERT INTO `liver_house` VALUES (12, 11, 11, '1');

-- ----------------------------
-- Table structure for liver_parking
-- ----------------------------
DROP TABLE IF EXISTS `liver_parking`;
CREATE TABLE `liver_parking`  (
  `liver_parking_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `park_id` int(11) NULL DEFAULT NULL COMMENT '车位id',
  `liver_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `liver_parking_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用状态 0:使用中 1:解绑',
  PRIMARY KEY (`liver_parking_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of liver_parking
-- ----------------------------
INSERT INTO `liver_parking` VALUES (1, 5, 4, '0');
INSERT INTO `liver_parking` VALUES (2, 7, 6, '1');
INSERT INTO `liver_parking` VALUES (3, 7, 6, '1');
INSERT INTO `liver_parking` VALUES (4, 7, 6, '0');
INSERT INTO `liver_parking` VALUES (5, 8, 5, '1');
INSERT INTO `liver_parking` VALUES (6, 8, 5, '1');
INSERT INTO `liver_parking` VALUES (7, 8, 7, '0');

-- ----------------------------
-- Table structure for liver_role
-- ----------------------------
DROP TABLE IF EXISTS `liver_role`;
CREATE TABLE `liver_role`  (
  `liver_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `liver_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`liver_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of liver_role
-- ----------------------------
INSERT INTO `liver_role` VALUES (18, 6, 19);
INSERT INTO `liver_role` VALUES (20, 7, 19);
INSERT INTO `liver_role` VALUES (23, 10, 19);
INSERT INTO `liver_role` VALUES (25, 11, 19);
INSERT INTO `liver_role` VALUES (26, 5, 19);
INSERT INTO `liver_role` VALUES (31, 4, 19);
INSERT INTO `liver_role` VALUES (32, 12, 19);

-- ----------------------------
-- Table structure for liver_user
-- ----------------------------
DROP TABLE IF EXISTS `liver_user`;
CREATE TABLE `liver_user`  (
  `liver_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '业主id',
  `liver_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业主姓名',
  `liver_phone` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业主电话',
  `liver_sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业主性别 0:男 1:女',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `liver_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号状态 0:启用 1:禁用',
  `is_account_non_expired` int(255) NULL DEFAULT NULL COMMENT '帐户是否过期(1 未过期，0已过期)',
  `is_account_non_locked` int(255) NULL DEFAULT NULL COMMENT '帐户是否被锁定(1 未过期，0已过期)',
  `is_credentials_non_expired` int(255) NULL DEFAULT NULL COMMENT '密码是否过期(1 未过期，0已过期)',
  `is_enabled` int(255) NULL DEFAULT NULL COMMENT ' 帐户是否可用(1 可用，0 删除用户)	',
  PRIMARY KEY (`liver_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of liver_user
-- ----------------------------
INSERT INTO `liver_user` VALUES (4, '测试a', '13412345678', '0', 'abc12345', '$2a$10$/dRuS2n4YERV1gE0lI1yQOhqOwR.k8vOVcGZ1JwSeVQnhgwYvflUi', '0', 1, 1, 1, 1);
INSERT INTO `liver_user` VALUES (5, '测试2', '13571571610', '1', 'test222', '$2a$10$uQuxHL6Sr1S0Qwj09A8mqedyDp6ZIeQhPeyhM1mqN8PncAn.ib1ai', '0', 1, 1, 1, 1);
INSERT INTO `liver_user` VALUES (6, '测试业主', '13512345678', '0', 'test0123', 'e10adc3949ba59abbe56e057f20f883e', '0', NULL, NULL, NULL, NULL);
INSERT INTO `liver_user` VALUES (7, '测试3', '19912345678', '0', 'zhangsan01', '$2a$10$Xuwe1PujJ7tuOCIn7RnQke/KFAOavXCV9ENRIBwlIh0aACyjKYQpO', '0', 1, 1, 1, 1);
INSERT INTO `liver_user` VALUES (10, '张三三', '15289296784', '0', 'zhang123', '$2a$10$AQCKgR.BFRag91ADXm5LxOb6HiPOQOtLVM2uCvSN.YOGlZH3RD1vW', '0', 1, 1, 1, 1);

-- ----------------------------
-- Table structure for parking_list
-- ----------------------------
DROP TABLE IF EXISTS `parking_list`;
CREATE TABLE `parking_list`  (
  `park_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '车位id',
  `park_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车位类型（0地上 1地下）',
  `park_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车位名称',
  `park_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车位使用状态（0未使用 1已使用)',
  `park_number` int(11) NULL DEFAULT NULL COMMENT '车位序号',
  PRIMARY KEY (`park_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parking_list
-- ----------------------------
INSERT INTO `parking_list` VALUES (3, '0', 'A002', '1', 2);
INSERT INTO `parking_list` VALUES (5, '1', 'A001', '1', 1);
INSERT INTO `parking_list` VALUES (7, '0', '测试', '1', 0);
INSERT INTO `parking_list` VALUES (8, '1', '张三的车位', '1', 3);
INSERT INTO `parking_list` VALUES (9, '1', '不用的测试车位', '0', 4);

-- ----------------------------
-- Table structure for pay_electric
-- ----------------------------
DROP TABLE IF EXISTS `pay_electric`;
CREATE TABLE `pay_electric`  (
  `electric_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `liver_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `house_id` int(11) NULL DEFAULT NULL COMMENT '房屋id',
  `electric_date` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缴费年月',
  `electric_money` decimal(18, 2) NULL DEFAULT NULL COMMENT '缴费金额',
  `electric_num` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用电额度',
  `electric_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缴费状态 0：未缴费 1：已缴费',
  `electric_time` datetime NULL DEFAULT NULL COMMENT '缴费时间',
  PRIMARY KEY (`electric_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pay_electric
-- ----------------------------
INSERT INTO `pay_electric` VALUES (2, 4, 5, '2023-01', 30.00, '60', '1', NULL);
INSERT INTO `pay_electric` VALUES (4, 5, 9, '2023-01', 30.00, '15', '1', NULL);
INSERT INTO `pay_electric` VALUES (5, 6, 10, '2023-02', 999.00, '999', '1', NULL);

-- ----------------------------
-- Table structure for pay_parking
-- ----------------------------
DROP TABLE IF EXISTS `pay_parking`;
CREATE TABLE `pay_parking`  (
  `parking_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `park_id` int(11) NULL DEFAULT NULL COMMENT '车位id',
  `liver_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `parking_date` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缴费年月',
  `parking_money` decimal(18, 2) NULL DEFAULT NULL COMMENT '缴费金额',
  `parking_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缴费状态 0：未缴费 1：已缴费',
  `parking_time` datetime NULL DEFAULT NULL COMMENT '缴费时间',
  PRIMARY KEY (`parking_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pay_parking
-- ----------------------------
INSERT INTO `pay_parking` VALUES (1, 5, 4, '2023-02', 100.00, '1', NULL);
INSERT INTO `pay_parking` VALUES (3, 5, 4, '2023-02', 1.00, '1', NULL);
INSERT INTO `pay_parking` VALUES (4, 7, 6, '2023-02', 999.00, '0', NULL);

-- ----------------------------
-- Table structure for pay_water
-- ----------------------------
DROP TABLE IF EXISTS `pay_water`;
CREATE TABLE `pay_water`  (
  `water_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `liver_id` int(11) NULL DEFAULT NULL COMMENT '业主id',
  `house_id` int(11) NULL DEFAULT NULL COMMENT '房屋id',
  `water_date` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缴费年月',
  `water_money` decimal(18, 2) NULL DEFAULT NULL COMMENT '缴费金额',
  `water_num` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用水额度',
  `water_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缴费状态 0：未缴费 1：已缴费',
  `water_time` datetime NULL DEFAULT NULL COMMENT '缴费时间',
  PRIMARY KEY (`water_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pay_water
-- ----------------------------
INSERT INTO `pay_water` VALUES (2, 5, 9, '2022-12', 20.00, '30', '1', NULL);
INSERT INTO `pay_water` VALUES (4, 4, 5, '2023-01', 10.00, '10', '1', NULL);
INSERT INTO `pay_water` VALUES (5, 6, 10, '2023-02', 999.00, '999', '1', NULL);

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级菜单id',
  `menu_label` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `menu_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限字段',
  `route_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由名称',
  `route_path` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `route_url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件路由',
  `type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型 目录:0  菜单:1 按钮:2',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '序号',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `parent_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级菜单名称',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 114 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES (2, 0, '权限管理', 'user:index', '', '/user', '', '0', 'el-icon-tickets', 1, '', '顶级菜单');
INSERT INTO `tb_menu` VALUES (3, 2, '物业人员管理', 'user:worker', 'worker', '/worker', '/user/worker', '1', '', 1, '', '人员管理');
INSERT INTO `tb_menu` VALUES (4, 2, '角色管理', 'user:role', 'role', '/role', '/user/role', '1', '', 2, '', '人员管理');
INSERT INTO `tb_menu` VALUES (5, 2, '权限管理', 'user:menu', 'menu', '/menu', '/user/menu', '1', '', 3, '', '人员管理');
INSERT INTO `tb_menu` VALUES (6, 3, '添加', 'user:worker:add', '', '', '', '2', '', NULL, '', '物业人员管理');
INSERT INTO `tb_menu` VALUES (7, 0, '楼盘管理', 'houseBuilding:index', '', '/houseBuilding', '', '0', 'el-icon-office-building', 2, '', '顶级菜单');
INSERT INTO `tb_menu` VALUES (8, 7, '楼宇管理', 'houseBuilding:building', 'building', '/building', '/houseBuilding/building', '1', '', 1, '', '楼盘管理');
INSERT INTO `tb_menu` VALUES (9, 7, '单元管理', 'houseBuilding:unit', 'unit', '/unit', '/houseBuilding/unit', '1', '', 2, '', '楼盘管理');
INSERT INTO `tb_menu` VALUES (10, 7, '房屋管理', 'houseBuilding:house', 'house', '/house', '/houseBuilding/house', '1', '', 3, '', '楼盘管理');
INSERT INTO `tb_menu` VALUES (11, 0, '车辆管理', 'parking:index', '', '/parking', '', '0', 'el-icon-truck', 3, '', '顶级菜单');
INSERT INTO `tb_menu` VALUES (12, 11, '车位管理', 'parking:parkingList', 'parkingList', '/parkingList', '/parking/parkingList', '1', '', NULL, '', '车辆管理');
INSERT INTO `tb_menu` VALUES (13, 0, '业主管理', 'liver:index', '', '/liver', '', '0', 'el-icon-user', 4, '', '顶级菜单');
INSERT INTO `tb_menu` VALUES (14, 13, '业主列表', 'liver:liver', 'liverList', '/liverList', '/liver/liver', '1', '', NULL, '', '业主管理');
INSERT INTO `tb_menu` VALUES (15, 0, '收费管理', 'pay:index', '', '/pay', '', '0', 'el-icon-money', 5, '', '顶级菜单');
INSERT INTO `tb_menu` VALUES (16, 15, '电费管理', 'pay:electric', 'electricList', '/electricList', '/pay/electric', '1', '', NULL, '', '收费管理');
INSERT INTO `tb_menu` VALUES (17, 15, '水费管理', 'pay:water', 'waterList', '/waterList', '/pay/water', '1', '', NULL, '', '收费管理');
INSERT INTO `tb_menu` VALUES (18, 15, '停车费管理', 'pay:park', 'parkList', '/parkList', '/pay/park', '1', '', NULL, '', '收费管理');
INSERT INTO `tb_menu` VALUES (19, 0, '小区管理', 'community:index', '', '/community', '', '0', 'el-icon-bangzhu', 6, '', '顶级菜单');
INSERT INTO `tb_menu` VALUES (20, 19, '小区信息管理', 'community:commInfo', 'commInfo', '/commInfo', '/community/commInfo', '1', '', NULL, '', '小区管理');
INSERT INTO `tb_menu` VALUES (21, 19, '小区设施管理', 'community:facilities', 'facilities', '/facilities', '/community/facilities', '1', '', NULL, '', '小区管理');
INSERT INTO `tb_menu` VALUES (22, 19, '物业公告管理', 'community:notice', 'notice', '/notice', '/community/notice', '1', '', NULL, '', '小区管理');
INSERT INTO `tb_menu` VALUES (23, 0, '投诉管理', 'liverComplaint:index', '', '/liverComplaint', '', '0', 'el-icon-edit-outline', 7, '', '顶级菜单');
INSERT INTO `tb_menu` VALUES (24, 23, '我的投诉', 'complaint:myComplaint', 'myComplaint', '/myComplaint', '/complaint/myComplaint', '1', '', NULL, '', '投诉管理');
INSERT INTO `tb_menu` VALUES (25, 23, '投诉列表', 'complaint:complaintList', 'complaintList', '/complaintList', '/complaint/complaintList', '1', '', NULL, '', '投诉管理');
INSERT INTO `tb_menu` VALUES (26, 0, '维修管理', 'liverRepair:index', '', '/liverRepair', '', '0', 'el-icon-thumb', 8, '', '顶级菜单');
INSERT INTO `tb_menu` VALUES (27, 26, '我的报修', 'repair:myRepair', 'myRepair', '/myRepair', '/repair/myRepair', '1', '', NULL, '', '维修管理');
INSERT INTO `tb_menu` VALUES (28, 26, '维修列表', 'repair:repairList', 'repairList', '/repairList', '/repair/repairList', '1', '', NULL, '', '维修管理');
INSERT INTO `tb_menu` VALUES (29, 3, '编辑', 'user:worker:edit', '', '', '', '2', '', NULL, '', '物业人员管理');
INSERT INTO `tb_menu` VALUES (30, 3, '删除', 'user:worker:delete', '', '', '', '2', '', NULL, '', '物业人员管理');
INSERT INTO `tb_menu` VALUES (31, 3, '分配角色', 'user:worker:allocation', '', '', '', '2', '', NULL, '', '物业人员管理');
INSERT INTO `tb_menu` VALUES (32, 8, '添加', 'houseBuilding:building:add', '', '', '', '2', '', NULL, '', '楼宇管理');
INSERT INTO `tb_menu` VALUES (33, 8, '编辑', 'houseBuilding:building:edit', '', '', '', '2', '', NULL, '', '楼宇管理');
INSERT INTO `tb_menu` VALUES (34, 8, '删除', 'houseBuilding:building:delete', '', '', '', '2', '', NULL, '', '楼宇管理');
INSERT INTO `tb_menu` VALUES (35, 8, '分页查询', 'houseBuilding:building:list', '', '', '', '2', '', NULL, '', '楼宇管理');
INSERT INTO `tb_menu` VALUES (36, 9, '添加', 'houseBuilding:unit:add', '', '', '', '2', '', NULL, '', '单元管理');
INSERT INTO `tb_menu` VALUES (37, 9, '编辑', 'houseBuilding:unit:edit', '', '', '', '2', '', NULL, '', '单元管理');
INSERT INTO `tb_menu` VALUES (38, 9, '删除', 'houseBuilding:unit:delete', '', '', '', '2', '', NULL, '', '单元管理');
INSERT INTO `tb_menu` VALUES (39, 9, '分页查询', 'houseBuilding:unit:list', '', '', '', '2', '', NULL, '', '单元管理');
INSERT INTO `tb_menu` VALUES (40, 10, '添加', 'houseBuilding:house:add', '', '', '', '2', '', NULL, '', '房屋管理');
INSERT INTO `tb_menu` VALUES (41, 10, '编辑', 'houseBuilding:house:edit', '', '', '', '2', '', NULL, '', '房屋管理');
INSERT INTO `tb_menu` VALUES (42, 10, '删除', 'houseBuilding:house:delete', '', '', '', '2', '', NULL, '', '房屋管理');
INSERT INTO `tb_menu` VALUES (43, 10, '分页查询', 'houseBuilding:house:list', '', '', '', '2', '', NULL, '', '房屋管理');
INSERT INTO `tb_menu` VALUES (44, 12, '添加', 'parking:parkingList:add', '', '', '', '2', '', NULL, '', '车位管理');
INSERT INTO `tb_menu` VALUES (45, 12, '编辑', 'parking:parkingList:edit', '', '', '', '2', '', NULL, '', '车位管理');
INSERT INTO `tb_menu` VALUES (46, 12, '删除', 'parking:parkingList:delete', '', '', '', '2', '', NULL, '', '车位管理');
INSERT INTO `tb_menu` VALUES (47, 12, '分页查询', 'parking:parkingList:list', '', '', '', '2', '', NULL, '', '车位管理');
INSERT INTO `tb_menu` VALUES (48, 14, '添加', 'liver:liver:add', '', '', '', '2', '', NULL, '', '业主列表');
INSERT INTO `tb_menu` VALUES (49, 14, '编辑', 'liver:liver:edit', '', '', '', '2', '', NULL, '', '业主列表');
INSERT INTO `tb_menu` VALUES (50, 14, '删除', 'liver:liver:delete', '', '', '', '2', '', NULL, '', '业主列表');
INSERT INTO `tb_menu` VALUES (51, 14, '分配房屋', 'liver:liver:avgHouse', '', '', '', '2', '', NULL, '', '业主列表');
INSERT INTO `tb_menu` VALUES (52, 14, '分配车位', 'liver:liver:avgParking', '', '', '', '2', '', NULL, '', '业主列表');
INSERT INTO `tb_menu` VALUES (53, 14, '退房', 'liver:liver:returnHouse', '', '', '', '2', '', NULL, '', '业主列表');
INSERT INTO `tb_menu` VALUES (54, 14, '退车位', 'liver:liver:returnParking', '', '', '', '2', '', NULL, '', '业主列表');
INSERT INTO `tb_menu` VALUES (55, 14, '分页查询', 'liver:liver:list', '', '', '', '2', '', NULL, '', '业主列表');
INSERT INTO `tb_menu` VALUES (56, 16, '添加', 'pay:electric:add', '', '', '', '2', '', NULL, '', '电费管理');
INSERT INTO `tb_menu` VALUES (57, 16, '编辑', 'pay:electric:edit', '', '', '', '2', '', NULL, '', '电费管理');
INSERT INTO `tb_menu` VALUES (58, 16, '删除', 'pay:electric:delete', '', '', '', '2', '', NULL, '', '电费管理');
INSERT INTO `tb_menu` VALUES (59, 16, '缴费', 'pay:electric:finish', '', '', '', '2', '', NULL, '', '电费管理');
INSERT INTO `tb_menu` VALUES (60, 16, '分页查询', 'pay:electric:list', '', '', '', '2', '', NULL, '', '电费管理');
INSERT INTO `tb_menu` VALUES (61, 17, '添加', 'pay:water:add', '', '', '', '2', '', NULL, '', '水费管理');
INSERT INTO `tb_menu` VALUES (62, 17, '编辑', 'pay:water:edit', '', '', '', '2', '', NULL, '', '水费管理');
INSERT INTO `tb_menu` VALUES (63, 17, '删除', 'pay:water:delete', '', '', '', '2', '', NULL, '', '水费管理');
INSERT INTO `tb_menu` VALUES (64, 17, '缴费', 'pay:water:finish', '', '', '', '2', '', NULL, '', '水费管理');
INSERT INTO `tb_menu` VALUES (65, 17, '分页查询', 'pay:water:list', '', '', '', '2', '', NULL, '', '水费管理');
INSERT INTO `tb_menu` VALUES (66, 18, '添加', 'pay:park:add', '', '', '', '2', '', NULL, '', '停车费管理');
INSERT INTO `tb_menu` VALUES (67, 18, '编辑', 'pay:park:edit', '', '', '', '2', '', NULL, '', '停车费管理');
INSERT INTO `tb_menu` VALUES (68, 18, '删除', 'pay:park:delete', '', '', '', '2', '', NULL, '', '停车费管理');
INSERT INTO `tb_menu` VALUES (69, 18, '缴费', 'pay:park:finish', '', '', '', '2', '', NULL, '', '停车费管理');
INSERT INTO `tb_menu` VALUES (70, 18, '分页查询', 'pay:park:list', '', '', '', '2', '', NULL, '', '停车费管理');
INSERT INTO `tb_menu` VALUES (71, 20, '保存', 'community:commInfo:save', '', '', '', '2', '', NULL, '', '小区信息管理');
INSERT INTO `tb_menu` VALUES (72, 20, '数据回显', 'community:commInfo:info', '', '', '', '2', '', NULL, '', '小区信息管理');
INSERT INTO `tb_menu` VALUES (73, 22, '添加', 'community:notice:add', '', '', '', '2', '', NULL, '', '物业公告管理');
INSERT INTO `tb_menu` VALUES (74, 22, '编辑', 'community:notice:edit', '', '', '', '2', '', NULL, '', '物业公告管理');
INSERT INTO `tb_menu` VALUES (75, 22, '删除', 'community:notice:delete', '', '', '', '2', '', NULL, '', '物业公告管理');
INSERT INTO `tb_menu` VALUES (76, 22, '分页查询', 'community:notice:list', '', '', '', '2', '', NULL, '', '物业公告管理');
INSERT INTO `tb_menu` VALUES (77, 24, '添加', 'complaint:myComplaint:add', '', '', '', '2', '', NULL, '', '我的投诉');
INSERT INTO `tb_menu` VALUES (78, 24, '编辑', 'complaint:myComplaint:edit', '', '', '', '2', '', NULL, '', '我的投诉');
INSERT INTO `tb_menu` VALUES (79, 24, '删除', 'complaint:myComplaint:delete', '', '', '', '2', '', NULL, '', '我的投诉');
INSERT INTO `tb_menu` VALUES (80, 24, '分页查询', 'complaint:myComplaint:list', '', '', '', '2', '', NULL, '', '我的投诉');
INSERT INTO `tb_menu` VALUES (81, 25, '分页查询', 'complaint:complaintList:list', '', '', '', '2', '', NULL, '', '投诉列表');
INSERT INTO `tb_menu` VALUES (82, 25, '处理', 'complaint:complaintList:slove', '', '', '', '2', '', NULL, '', '投诉列表');
INSERT INTO `tb_menu` VALUES (83, 27, '添加', 'repair:myRepair:add', '', '', '', '2', '', NULL, '', '我的报修');
INSERT INTO `tb_menu` VALUES (84, 27, ' 编辑', 'repair:myRepair:edit', '', '', '', '2', '', NULL, '', '我的报修');
INSERT INTO `tb_menu` VALUES (85, 27, '删除', 'repair:myRepair:delete', '', '', '', '2', '', NULL, '', '我的报修');
INSERT INTO `tb_menu` VALUES (86, 27, '分页查询', 'repair:myRepair:list', '', '', '', '2', '', NULL, '', '我的报修');
INSERT INTO `tb_menu` VALUES (87, 28, '分页查询', 'repair:repairList:list', '', '', '', '2', '', NULL, '', '维修列表');
INSERT INTO `tb_menu` VALUES (88, 28, '处理', 'repair:repairList:slove', '', '', '', '2', '', NULL, '', '维修列表');
INSERT INTO `tb_menu` VALUES (89, 4, '添加', 'user:role:add', '', '', '', '2', '', NULL, '', '角色管理');
INSERT INTO `tb_menu` VALUES (90, 4, '编辑', 'user:role:edit', '', '', '', '2', '', NULL, '', '角色管理');
INSERT INTO `tb_menu` VALUES (91, 4, '删除', 'user:role:delete', '', '', '', '2', '', NULL, '', '角色管理');
INSERT INTO `tb_menu` VALUES (92, 4, '分配权限', 'user:role:allocation', '', '', '', '2', '', NULL, '', '角色管理');
INSERT INTO `tb_menu` VALUES (93, 4, '分页查询', 'user:role:list', '', '', '', '2', '', NULL, '', '角色管理');
INSERT INTO `tb_menu` VALUES (94, 5, '添加', 'user:menu:add', '', '', '', '2', '', NULL, '', '权限管理');
INSERT INTO `tb_menu` VALUES (95, 5, '编辑', 'user:menu:edit', '', '', '', '2', '', NULL, '', '权限管理');
INSERT INTO `tb_menu` VALUES (96, 5, '删除', 'user:menu:delete', '', '', '', '2', '', NULL, '', '权限管理');
INSERT INTO `tb_menu` VALUES (97, 5, '展示', 'user:menu:show', '', '', '', '2', '', NULL, '', '权限管理');
INSERT INTO `tb_menu` VALUES (98, 3, '分页查询', 'user:worker:list', '', '', '', '2', '', NULL, '', '物业人员管理');
INSERT INTO `tb_menu` VALUES (101, 0, '物业数据', 'statistics:index', '', '/statistics', '', '0', 'el-icon-monitor', 0, '', '顶级菜单');
INSERT INTO `tb_menu` VALUES (102, 101, '统计报表', 'statistics:statement', 'statement', '/statement', '/statistics/statement', '1', '', 1, '', '物业数据');
INSERT INTO `tb_menu` VALUES (103, 21, '添加', 'community:facilities:add', '', '', '', '2', '', 0, '', '小区设施管理');
INSERT INTO `tb_menu` VALUES (104, 21, '编辑', 'community:facilities:edit', '', '', '', '2', '', 1, '', '小区设施管理');
INSERT INTO `tb_menu` VALUES (105, 21, '删除', 'community:facilities:delete', '', '', '', '2', '', 2, '', '小区设施管理');
INSERT INTO `tb_menu` VALUES (106, 21, '分页查询', 'community:facilities:list', '', '', '', '2', '', 3, '', '小区设施管理');
INSERT INTO `tb_menu` VALUES (107, 0, '缴费记录', 'payRecords:index', '', '/payRecords', '', '0', 'el-icon-notebook-2', 9, '', '顶级菜单');
INSERT INTO `tb_menu` VALUES (108, 107, '停车费记录', 'records:myParkList', 'myParkList', '/myParkList', '/records/myParkList', '1', '', 0, '', '缴费记录');
INSERT INTO `tb_menu` VALUES (109, 107, '电费记录', 'records:myElectricList', 'myElectricList', '/myElectricList', '/records/myElectricList', '1', '', 1, '', '缴费记录');
INSERT INTO `tb_menu` VALUES (110, 107, '水费记录', 'records:myWaterList', 'myWaterList', '/myWaterList', '/records/myWaterList', '1', '', 1, '', '缴费记录');
INSERT INTO `tb_menu` VALUES (111, 110, '分页查询', 'records:myWaterList:list', '', '', '', '2', '', 0, '', '水费记录');
INSERT INTO `tb_menu` VALUES (112, 109, '分页查询', 'records:myElectricList:list', '', '', '', '2', '', 0, '', '电费记录');
INSERT INTO `tb_menu` VALUES (113, 108, '分页查询', 'records:myParkList:list', '', '', '', '2', '', 0, '', '停车费记录');

-- ----------------------------
-- Table structure for tb_notice
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice`  (
  `notice_id` int(11) NOT NULL COMMENT '???????',
  `notice_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '???????',
  `show_time` date NULL DEFAULT NULL COMMENT '????ʱ?',
  `author` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????',
  `notice_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '???????',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_notice
-- ----------------------------

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (2, '管理员', '管理员');
INSERT INTO `tb_role` VALUES (16, '物业', '');
INSERT INTO `tb_role` VALUES (19, '业主', '');

-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu`  (
  `role_menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3653 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role_menu
-- ----------------------------
INSERT INTO `tb_role_menu` VALUES (3440, 2, 2);
INSERT INTO `tb_role_menu` VALUES (3441, 2, 3);
INSERT INTO `tb_role_menu` VALUES (3442, 2, 6);
INSERT INTO `tb_role_menu` VALUES (3443, 2, 29);
INSERT INTO `tb_role_menu` VALUES (3444, 2, 30);
INSERT INTO `tb_role_menu` VALUES (3445, 2, 31);
INSERT INTO `tb_role_menu` VALUES (3446, 2, 98);
INSERT INTO `tb_role_menu` VALUES (3447, 2, 4);
INSERT INTO `tb_role_menu` VALUES (3448, 2, 89);
INSERT INTO `tb_role_menu` VALUES (3449, 2, 90);
INSERT INTO `tb_role_menu` VALUES (3450, 2, 91);
INSERT INTO `tb_role_menu` VALUES (3451, 2, 92);
INSERT INTO `tb_role_menu` VALUES (3452, 2, 93);
INSERT INTO `tb_role_menu` VALUES (3453, 2, 5);
INSERT INTO `tb_role_menu` VALUES (3454, 2, 94);
INSERT INTO `tb_role_menu` VALUES (3455, 2, 95);
INSERT INTO `tb_role_menu` VALUES (3456, 2, 96);
INSERT INTO `tb_role_menu` VALUES (3457, 2, 97);
INSERT INTO `tb_role_menu` VALUES (3458, 2, 7);
INSERT INTO `tb_role_menu` VALUES (3459, 2, 8);
INSERT INTO `tb_role_menu` VALUES (3460, 2, 32);
INSERT INTO `tb_role_menu` VALUES (3461, 2, 33);
INSERT INTO `tb_role_menu` VALUES (3462, 2, 34);
INSERT INTO `tb_role_menu` VALUES (3463, 2, 35);
INSERT INTO `tb_role_menu` VALUES (3464, 2, 9);
INSERT INTO `tb_role_menu` VALUES (3465, 2, 36);
INSERT INTO `tb_role_menu` VALUES (3466, 2, 37);
INSERT INTO `tb_role_menu` VALUES (3467, 2, 38);
INSERT INTO `tb_role_menu` VALUES (3468, 2, 39);
INSERT INTO `tb_role_menu` VALUES (3469, 2, 10);
INSERT INTO `tb_role_menu` VALUES (3470, 2, 40);
INSERT INTO `tb_role_menu` VALUES (3471, 2, 41);
INSERT INTO `tb_role_menu` VALUES (3472, 2, 42);
INSERT INTO `tb_role_menu` VALUES (3473, 2, 43);
INSERT INTO `tb_role_menu` VALUES (3474, 2, 11);
INSERT INTO `tb_role_menu` VALUES (3475, 2, 12);
INSERT INTO `tb_role_menu` VALUES (3476, 2, 44);
INSERT INTO `tb_role_menu` VALUES (3477, 2, 45);
INSERT INTO `tb_role_menu` VALUES (3478, 2, 46);
INSERT INTO `tb_role_menu` VALUES (3479, 2, 47);
INSERT INTO `tb_role_menu` VALUES (3480, 2, 13);
INSERT INTO `tb_role_menu` VALUES (3481, 2, 14);
INSERT INTO `tb_role_menu` VALUES (3482, 2, 48);
INSERT INTO `tb_role_menu` VALUES (3483, 2, 49);
INSERT INTO `tb_role_menu` VALUES (3484, 2, 50);
INSERT INTO `tb_role_menu` VALUES (3485, 2, 51);
INSERT INTO `tb_role_menu` VALUES (3486, 2, 52);
INSERT INTO `tb_role_menu` VALUES (3487, 2, 53);
INSERT INTO `tb_role_menu` VALUES (3488, 2, 54);
INSERT INTO `tb_role_menu` VALUES (3489, 2, 55);
INSERT INTO `tb_role_menu` VALUES (3490, 2, 15);
INSERT INTO `tb_role_menu` VALUES (3491, 2, 16);
INSERT INTO `tb_role_menu` VALUES (3492, 2, 56);
INSERT INTO `tb_role_menu` VALUES (3493, 2, 57);
INSERT INTO `tb_role_menu` VALUES (3494, 2, 58);
INSERT INTO `tb_role_menu` VALUES (3495, 2, 59);
INSERT INTO `tb_role_menu` VALUES (3496, 2, 60);
INSERT INTO `tb_role_menu` VALUES (3497, 2, 17);
INSERT INTO `tb_role_menu` VALUES (3498, 2, 61);
INSERT INTO `tb_role_menu` VALUES (3499, 2, 62);
INSERT INTO `tb_role_menu` VALUES (3500, 2, 63);
INSERT INTO `tb_role_menu` VALUES (3501, 2, 64);
INSERT INTO `tb_role_menu` VALUES (3502, 2, 65);
INSERT INTO `tb_role_menu` VALUES (3503, 2, 18);
INSERT INTO `tb_role_menu` VALUES (3504, 2, 66);
INSERT INTO `tb_role_menu` VALUES (3505, 2, 67);
INSERT INTO `tb_role_menu` VALUES (3506, 2, 68);
INSERT INTO `tb_role_menu` VALUES (3507, 2, 69);
INSERT INTO `tb_role_menu` VALUES (3508, 2, 70);
INSERT INTO `tb_role_menu` VALUES (3509, 2, 19);
INSERT INTO `tb_role_menu` VALUES (3510, 2, 20);
INSERT INTO `tb_role_menu` VALUES (3511, 2, 71);
INSERT INTO `tb_role_menu` VALUES (3512, 2, 72);
INSERT INTO `tb_role_menu` VALUES (3513, 2, 21);
INSERT INTO `tb_role_menu` VALUES (3514, 2, 103);
INSERT INTO `tb_role_menu` VALUES (3515, 2, 104);
INSERT INTO `tb_role_menu` VALUES (3516, 2, 105);
INSERT INTO `tb_role_menu` VALUES (3517, 2, 106);
INSERT INTO `tb_role_menu` VALUES (3518, 2, 22);
INSERT INTO `tb_role_menu` VALUES (3519, 2, 73);
INSERT INTO `tb_role_menu` VALUES (3520, 2, 74);
INSERT INTO `tb_role_menu` VALUES (3521, 2, 75);
INSERT INTO `tb_role_menu` VALUES (3522, 2, 76);
INSERT INTO `tb_role_menu` VALUES (3523, 2, 23);
INSERT INTO `tb_role_menu` VALUES (3524, 2, 24);
INSERT INTO `tb_role_menu` VALUES (3525, 2, 77);
INSERT INTO `tb_role_menu` VALUES (3526, 2, 78);
INSERT INTO `tb_role_menu` VALUES (3527, 2, 79);
INSERT INTO `tb_role_menu` VALUES (3528, 2, 80);
INSERT INTO `tb_role_menu` VALUES (3529, 2, 25);
INSERT INTO `tb_role_menu` VALUES (3530, 2, 81);
INSERT INTO `tb_role_menu` VALUES (3531, 2, 82);
INSERT INTO `tb_role_menu` VALUES (3532, 2, 26);
INSERT INTO `tb_role_menu` VALUES (3533, 2, 27);
INSERT INTO `tb_role_menu` VALUES (3534, 2, 83);
INSERT INTO `tb_role_menu` VALUES (3535, 2, 84);
INSERT INTO `tb_role_menu` VALUES (3536, 2, 85);
INSERT INTO `tb_role_menu` VALUES (3537, 2, 86);
INSERT INTO `tb_role_menu` VALUES (3538, 2, 28);
INSERT INTO `tb_role_menu` VALUES (3539, 2, 87);
INSERT INTO `tb_role_menu` VALUES (3540, 2, 88);
INSERT INTO `tb_role_menu` VALUES (3541, 2, 101);
INSERT INTO `tb_role_menu` VALUES (3542, 2, 102);
INSERT INTO `tb_role_menu` VALUES (3543, 2, 107);
INSERT INTO `tb_role_menu` VALUES (3544, 2, 108);
INSERT INTO `tb_role_menu` VALUES (3545, 2, 113);
INSERT INTO `tb_role_menu` VALUES (3546, 2, 109);
INSERT INTO `tb_role_menu` VALUES (3547, 2, 112);
INSERT INTO `tb_role_menu` VALUES (3548, 2, 110);
INSERT INTO `tb_role_menu` VALUES (3549, 2, 111);
INSERT INTO `tb_role_menu` VALUES (3563, 19, 24);
INSERT INTO `tb_role_menu` VALUES (3564, 19, 77);
INSERT INTO `tb_role_menu` VALUES (3565, 19, 78);
INSERT INTO `tb_role_menu` VALUES (3566, 19, 79);
INSERT INTO `tb_role_menu` VALUES (3567, 19, 80);
INSERT INTO `tb_role_menu` VALUES (3568, 19, 27);
INSERT INTO `tb_role_menu` VALUES (3569, 19, 83);
INSERT INTO `tb_role_menu` VALUES (3570, 19, 84);
INSERT INTO `tb_role_menu` VALUES (3571, 19, 85);
INSERT INTO `tb_role_menu` VALUES (3572, 19, 86);
INSERT INTO `tb_role_menu` VALUES (3573, 19, 107);
INSERT INTO `tb_role_menu` VALUES (3574, 19, 108);
INSERT INTO `tb_role_menu` VALUES (3575, 19, 113);
INSERT INTO `tb_role_menu` VALUES (3576, 19, 109);
INSERT INTO `tb_role_menu` VALUES (3577, 19, 112);
INSERT INTO `tb_role_menu` VALUES (3578, 19, 110);
INSERT INTO `tb_role_menu` VALUES (3579, 19, 111);
INSERT INTO `tb_role_menu` VALUES (3580, 19, 23);
INSERT INTO `tb_role_menu` VALUES (3581, 19, 26);
INSERT INTO `tb_role_menu` VALUES (3582, 16, 7);
INSERT INTO `tb_role_menu` VALUES (3583, 16, 8);
INSERT INTO `tb_role_menu` VALUES (3584, 16, 32);
INSERT INTO `tb_role_menu` VALUES (3585, 16, 33);
INSERT INTO `tb_role_menu` VALUES (3586, 16, 34);
INSERT INTO `tb_role_menu` VALUES (3587, 16, 35);
INSERT INTO `tb_role_menu` VALUES (3588, 16, 9);
INSERT INTO `tb_role_menu` VALUES (3589, 16, 36);
INSERT INTO `tb_role_menu` VALUES (3590, 16, 37);
INSERT INTO `tb_role_menu` VALUES (3591, 16, 38);
INSERT INTO `tb_role_menu` VALUES (3592, 16, 39);
INSERT INTO `tb_role_menu` VALUES (3593, 16, 10);
INSERT INTO `tb_role_menu` VALUES (3594, 16, 40);
INSERT INTO `tb_role_menu` VALUES (3595, 16, 41);
INSERT INTO `tb_role_menu` VALUES (3596, 16, 42);
INSERT INTO `tb_role_menu` VALUES (3597, 16, 43);
INSERT INTO `tb_role_menu` VALUES (3598, 16, 11);
INSERT INTO `tb_role_menu` VALUES (3599, 16, 12);
INSERT INTO `tb_role_menu` VALUES (3600, 16, 44);
INSERT INTO `tb_role_menu` VALUES (3601, 16, 45);
INSERT INTO `tb_role_menu` VALUES (3602, 16, 46);
INSERT INTO `tb_role_menu` VALUES (3603, 16, 47);
INSERT INTO `tb_role_menu` VALUES (3604, 16, 13);
INSERT INTO `tb_role_menu` VALUES (3605, 16, 14);
INSERT INTO `tb_role_menu` VALUES (3606, 16, 48);
INSERT INTO `tb_role_menu` VALUES (3607, 16, 49);
INSERT INTO `tb_role_menu` VALUES (3608, 16, 50);
INSERT INTO `tb_role_menu` VALUES (3609, 16, 51);
INSERT INTO `tb_role_menu` VALUES (3610, 16, 52);
INSERT INTO `tb_role_menu` VALUES (3611, 16, 53);
INSERT INTO `tb_role_menu` VALUES (3612, 16, 54);
INSERT INTO `tb_role_menu` VALUES (3613, 16, 55);
INSERT INTO `tb_role_menu` VALUES (3614, 16, 15);
INSERT INTO `tb_role_menu` VALUES (3615, 16, 16);
INSERT INTO `tb_role_menu` VALUES (3616, 16, 56);
INSERT INTO `tb_role_menu` VALUES (3617, 16, 57);
INSERT INTO `tb_role_menu` VALUES (3618, 16, 58);
INSERT INTO `tb_role_menu` VALUES (3619, 16, 59);
INSERT INTO `tb_role_menu` VALUES (3620, 16, 60);
INSERT INTO `tb_role_menu` VALUES (3621, 16, 17);
INSERT INTO `tb_role_menu` VALUES (3622, 16, 61);
INSERT INTO `tb_role_menu` VALUES (3623, 16, 62);
INSERT INTO `tb_role_menu` VALUES (3624, 16, 63);
INSERT INTO `tb_role_menu` VALUES (3625, 16, 64);
INSERT INTO `tb_role_menu` VALUES (3626, 16, 65);
INSERT INTO `tb_role_menu` VALUES (3627, 16, 18);
INSERT INTO `tb_role_menu` VALUES (3628, 16, 66);
INSERT INTO `tb_role_menu` VALUES (3629, 16, 67);
INSERT INTO `tb_role_menu` VALUES (3630, 16, 68);
INSERT INTO `tb_role_menu` VALUES (3631, 16, 69);
INSERT INTO `tb_role_menu` VALUES (3632, 16, 70);
INSERT INTO `tb_role_menu` VALUES (3633, 16, 23);
INSERT INTO `tb_role_menu` VALUES (3634, 16, 24);
INSERT INTO `tb_role_menu` VALUES (3635, 16, 77);
INSERT INTO `tb_role_menu` VALUES (3636, 16, 78);
INSERT INTO `tb_role_menu` VALUES (3637, 16, 79);
INSERT INTO `tb_role_menu` VALUES (3638, 16, 80);
INSERT INTO `tb_role_menu` VALUES (3639, 16, 25);
INSERT INTO `tb_role_menu` VALUES (3640, 16, 81);
INSERT INTO `tb_role_menu` VALUES (3641, 16, 82);
INSERT INTO `tb_role_menu` VALUES (3642, 16, 26);
INSERT INTO `tb_role_menu` VALUES (3643, 16, 27);
INSERT INTO `tb_role_menu` VALUES (3644, 16, 83);
INSERT INTO `tb_role_menu` VALUES (3645, 16, 84);
INSERT INTO `tb_role_menu` VALUES (3646, 16, 85);
INSERT INTO `tb_role_menu` VALUES (3647, 16, 86);
INSERT INTO `tb_role_menu` VALUES (3648, 16, 28);
INSERT INTO `tb_role_menu` VALUES (3649, 16, 87);
INSERT INTO `tb_role_menu` VALUES (3650, 16, 88);
INSERT INTO `tb_role_menu` VALUES (3651, 16, 101);
INSERT INTO `tb_role_menu` VALUES (3652, 16, 102);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录账户',
  `full_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone_number` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `is_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '帐号状态（0正常 1停用）',
  `is_admin` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否是管理员 0：不是 1：是',
  `is_account_non_expired` int(255) NULL DEFAULT NULL COMMENT '帐户是否过期(1 未过期，0已过期)',
  `is_account_non_locked` int(255) NULL DEFAULT NULL COMMENT '帐户是否被锁定(1 未过期，0已过期)',
  `is_credentials_non_expired` int(255) NULL DEFAULT NULL COMMENT '密码是否过期(1 未过期，0已过期)',
  `is_enabled` int(255) NULL DEFAULT NULL COMMENT ' 帐户是否可用(1 可用，0 删除用户)	',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (2, 'admin', '管理员', '$2a$10$cQ/qM/UET4EwWIhZ.koeWe9JcOuGUQyZGIxH/HftIX0PgB2c/6fHS', '13512345678', '0', '1', 1, 1, 1, 1);
INSERT INTO `tb_user` VALUES (21, 'test01', '测试物业', '$2a$10$BiH5WBqUq.DbcYS/a5ucz.1G25AWOorEBCUkJ.rij9F.WhIxfC5Ua', '18912345678', '0', '0', 1, 1, 1, 1);
INSERT INTO `tb_user` VALUES (22, 'aaa123', '张三', '$2a$10$kEh/xVdCtV9oQbU3J1C1D.I3OuAn00FCfDV7zFchx0IL1T8O/Plza', '12312345678', '0', '0', 1, 1, 1, 1);

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role`  (
  `user_role_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES (16, 2, 2);
INSERT INTO `tb_user_role` VALUES (17, 16, 21);
INSERT INTO `tb_user_role` VALUES (18, 16, 22);

SET FOREIGN_KEY_CHECKS = 1;
