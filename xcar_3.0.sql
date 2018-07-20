/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : xcar_3.0

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 20/07/2018 15:37:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for basic_car_attribute
-- ----------------------------
DROP TABLE IF EXISTS `basic_car_attribute`;
CREATE TABLE `basic_car_attribute`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `attrType` int(4) DEFAULT NULL COMMENT '属性类型',
  `attrName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '属性名称',
  `attrValue` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '属性值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of basic_car_attribute
-- ----------------------------
INSERT INTO `basic_car_attribute` VALUES (1, 10204, 'fdjg', '发动机盖');
INSERT INTO `basic_car_attribute` VALUES (2, 10204, 'zw', '中网');
INSERT INTO `basic_car_attribute` VALUES (3, 10204, 'qdd', '前大灯');
INSERT INTO `basic_car_attribute` VALUES (4, 10204, 'zhsj', '左后视镜');
INSERT INTO `basic_car_attribute` VALUES (5, 10204, 'zqm', '左前门');
INSERT INTO `basic_car_attribute` VALUES (6, 10204, 'zqmbs', '左前门把手');
INSERT INTO `basic_car_attribute` VALUES (7, 10204, 'zqmbl', '左前门玻璃');
INSERT INTO `basic_car_attribute` VALUES (8, 10204, 'zccs', '左侧车身');
INSERT INTO `basic_car_attribute` VALUES (9, 10204, 'xlxg', '行李箱盖');
INSERT INTO `basic_car_attribute` VALUES (10, 10204, 'hwd', '后尾灯');
INSERT INTO `basic_car_attribute` VALUES (11, 10204, 'hbxs', '后备箱锁');
INSERT INTO `basic_car_attribute` VALUES (12, 10204, 'bt', '备胎');
INSERT INTO `basic_car_attribute` VALUES (13, 10204, 'scgj', '随车工具');
INSERT INTO `basic_car_attribute` VALUES (14, 10204, 'yhyzb', '右后翼子板');
INSERT INTO `basic_car_attribute` VALUES (15, 10204, 'yhm', '右后门');
INSERT INTO `basic_car_attribute` VALUES (16, 10204, 'yhmbs', '右后门把手');
INSERT INTO `basic_car_attribute` VALUES (17, 10204, 'yhmbl', '右后门玻璃');
INSERT INTO `basic_car_attribute` VALUES (18, 10204, 'yccs', '右侧车身');
INSERT INTO `basic_car_attribute` VALUES (19, 10204, 'yhsj', '右后视镜');
INSERT INTO `basic_car_attribute` VALUES (20, 10204, 'yqyzb', '右前翼子板');
INSERT INTO `basic_car_attribute` VALUES (21, 10205, 'ybb', '仪表板');
INSERT INTO `basic_car_attribute` VALUES (22, 10205, 'ybzsd', '仪表指示灯');
INSERT INTO `basic_car_attribute` VALUES (23, 10205, 'syj', '收音机');
INSERT INTO `basic_car_attribute` VALUES (24, 10205, 'kdkg', '空调开关');
INSERT INTO `basic_car_attribute` VALUES (25, 10205, 'zhkg', '组合开关');
INSERT INTO `basic_car_attribute` VALUES (26, 10205, 'CDj', 'CD机');
INSERT INTO `basic_car_attribute` VALUES (27, 10205, 'yxlb', '音响喇叭');
INSERT INTO `basic_car_attribute` VALUES (28, 10205, 'zxkg', '转向开关');
INSERT INTO `basic_car_attribute` VALUES (29, 10205, 'dcld', '倒车雷达');
INSERT INTO `basic_car_attribute` VALUES (30, 10205, 'ydd', '阅读灯');
INSERT INTO `basic_car_attribute` VALUES (31, 10205, 'tc', '天窗');
INSERT INTO `basic_car_attribute` VALUES (32, 10205, 'ss', '手刹');
INSERT INTO `basic_car_attribute` VALUES (33, 10205, 'aqd', '安全带');
INSERT INTO `basic_car_attribute` VALUES (34, 10205, 'mskg', '门锁开关');
INSERT INTO `basic_car_attribute` VALUES (35, 10205, 'nhsj', '内后视镜');
INSERT INTO `basic_car_attribute` VALUES (36, 10205, 'zy', '座椅');
INSERT INTO `basic_car_attribute` VALUES (37, 10206, 'qhl', '前横梁');
INSERT INTO `basic_car_attribute` VALUES (38, 10206, 'fdjbc', '发动机标尺');
INSERT INTO `basic_car_attribute` VALUES (39, 10206, 'sx', '水箱');
INSERT INTO `basic_car_attribute` VALUES (40, 10206, 'dhxt', '点火系统');
INSERT INTO `basic_car_attribute` VALUES (41, 10206, 'lnq', '冷暖气');
INSERT INTO `basic_car_attribute` VALUES (42, 10206, 'zly', '助力油');
INSERT INTO `basic_car_attribute` VALUES (43, 10206, 'fdjls', '发动机螺栓');
INSERT INTO `basic_car_attribute` VALUES (44, 10206, 'kdysj', '空调压缩机');
INSERT INTO `basic_car_attribute` VALUES (45, 10206, 'dp', '电瓶');
INSERT INTO `basic_car_attribute` VALUES (46, 10206, 'fdjy', '发动机油');
INSERT INTO `basic_car_attribute` VALUES (47, 10206, 'ct', '插头');
INSERT INTO `basic_car_attribute` VALUES (48, 10206, 'zdxt', '制动系统');
INSERT INTO `basic_car_attribute` VALUES (49, 10206, 'qfzl', '前防撞梁');
INSERT INTO `basic_car_attribute` VALUES (50, 10206, 'zdy', '制动液');
INSERT INTO `basic_car_attribute` VALUES (51, 10206, 'dcyx', '倒车影像');
INSERT INTO `basic_car_attribute` VALUES (52, 10206, 'ylxt', '油路系统');
INSERT INTO `basic_car_attribute` VALUES (53, 10206, 'yg', '油管');
INSERT INTO `basic_car_attribute` VALUES (54, 10206, 'fdjyx', '发动机异响');
INSERT INTO `basic_car_attribute` VALUES (55, 10206, 'dlzx', '动力转向');
INSERT INTO `basic_car_attribute` VALUES (56, 10206, 'ltgg', '轮胎规格');

-- ----------------------------
-- Table structure for basic_car_attrvalue
-- ----------------------------
DROP TABLE IF EXISTS `basic_car_attrvalue`;
CREATE TABLE `basic_car_attrvalue`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `basicid` bigint(20) DEFAULT NULL COMMENT '申请id',
  `attrname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '属性id',
  `attrvalue` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '属性值',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_3`(`basicid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_car_brand
-- ----------------------------
DROP TABLE IF EXISTS `basic_car_brand`;
CREATE TABLE `basic_car_brand`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '品牌主键',
  `org_id` bigint(32) DEFAULT NULL COMMENT '组织id',
  `brand_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '品牌名称',
  `brand_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '品牌code',
  `dept_id` bigint(32) DEFAULT NULL COMMENT '部门id',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  `brand_photo_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '品牌图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_car_brand_series
-- ----------------------------
DROP TABLE IF EXISTS `basic_car_brand_series`;
CREATE TABLE `basic_car_brand_series`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '车系主键',
  `brand_id` bigint(32) DEFAULT NULL COMMENT '品牌id',
  `series_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车系名称',
  `series_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车系code',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_car_config
-- ----------------------------
DROP TABLE IF EXISTS `basic_car_config`;
CREATE TABLE `basic_car_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `car_id` bigint(20) DEFAULT NULL COMMENT '车辆值_主键id',
  `factory_time` datetime(0) DEFAULT NULL COMMENT '出厂日期',
  `mile_age` bigint(10) DEFAULT NULL COMMENT '行驶里程',
  `driving_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '行驶证号',
  `transfer_no` int(2) DEFAULT NULL COMMENT '过户次数',
  `car_purpose` int(4) DEFAULT NULL COMMENT '车辆用途',
  `transmission` int(4) DEFAULT NULL COMMENT '变速箱形式',
  `driver` int(4) DEFAULT NULL COMMENT '驱动形式',
  `displacement` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排量',
  `car_situation` int(4) DEFAULT NULL COMMENT '车况',
  `evaluation` decimal(10, 0) UNSIGNED ZEROFILL DEFAULT NULL COMMENT '估价',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_3`(`car_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车辆信息配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_car_introduce
-- ----------------------------
DROP TABLE IF EXISTS `basic_car_introduce`;
CREATE TABLE `basic_car_introduce`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '车型介绍主键',
  `model_id` bigint(32) DEFAULT NULL COMMENT '车型主键',
  `introduce_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '介绍名称',
  `introduce_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '介绍图片',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_car_model
-- ----------------------------
DROP TABLE IF EXISTS `basic_car_model`;
CREATE TABLE `basic_car_model`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '车型主键',
  `series_id` bigint(32) DEFAULT NULL COMMENT '车系id',
  `model_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车型名称',
  `model_colors` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车型颜色',
  `reference_price` decimal(18, 4) DEFAULT NULL COMMENT '参考价格',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_carmodel_param
-- ----------------------------
DROP TABLE IF EXISTS `basic_carmodel_param`;
CREATE TABLE `basic_carmodel_param`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '配置主键',
  `model_id` bigint(32) DEFAULT NULL COMMENT '车型id',
  `car_param_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '参数名称',
  `car_param_value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '参数值',
  `model_volume` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '长x宽x高',
  `structure` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车身结构',
  `diveway` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '驱动方式',
  `fulyway` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '燃料形式',
  `model_fuel` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '综合耗油',
  `displacement` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排量',
  `inner_color` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '内饰颜色',
  `is_sys_param` int(11) DEFAULT NULL COMMENT '是否基本参数',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_carmodel_photo
-- ----------------------------
DROP TABLE IF EXISTS `basic_carmodel_photo`;
CREATE TABLE `basic_carmodel_photo`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '车辆图库id',
  `model_id` bigint(32) DEFAULT NULL COMMENT '车型id',
  `photo_url` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '照片路径',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_customer
-- ----------------------------
DROP TABLE IF EXISTS `basic_customer`;
CREATE TABLE `basic_customer`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '客户主键',
  `org_id` bigint(32) DEFAULT NULL COMMENT '组织id',
  `customer_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户编号',
  `customer_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户姓名',
  `customer_status` int(11) DEFAULT NULL COMMENT '客户状态',
  `customer_sex` int(11) DEFAULT NULL COMMENT '性别',
  `birth_time` date DEFAULT NULL COMMENT '出生日期',
  `qq` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'QQ号码',
  `wechat` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '微信号码',
  `facebook` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'facebook号码',
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电子邮箱地址',
  `education` int(11) DEFAULT NULL COMMENT '教育程度',
  `school` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '毕业院校',
  `marital` int(11) DEFAULT NULL COMMENT '婚姻状况',
  `id_card` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份证号码',
  `customer_issuer` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发证机关',
  `id_card_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份证地址',
  `id_card_address_detail` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份证地址门牌号',
  `id_card_validity_period_type` int(11) DEFAULT NULL COMMENT '身份证有效期类型',
  `id_card_validity_period_section` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份证有效期区间',
  `local_home_addr` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现居住地址',
  `local_home_addr_detail` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '现居住地址门牌号',
  `local_home_phone` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '居住地家庭座机',
  `house_prospecting` int(11) DEFAULT NULL COMMENT '是否接受勘查',
  `is_delete` int(11) DEFAULT NULL COMMENT '客户是否删除',
  `postal_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮政编码',
  `personal_password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录App密码',
  `personal_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户上传头像',
  `nation` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '民族',
  `health_status` int(11) DEFAULT NULL COMMENT '健康状况',
  `home_status` int(11) DEFAULT NULL COMMENT '居住状况',
  `message_addr` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '通讯地址',
  `customer_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户电话',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  `account_status` int(11) DEFAULT NULL COMMENT '开户状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of basic_customer
-- ----------------------------
INSERT INTO `basic_customer` VALUES (1, 1, '9ad5f4c0-f32c-4f73-a3ff-47cd370d391e', '张亚军', 10077, 10024, NULL, NULL, NULL, NULL, NULL, 10084, NULL, 10089, '142332199404023614', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '17600562066', '2018-07-18 09:17:50', NULL, 1, '2018-07-18 09:17:50', NULL);

-- ----------------------------
-- Table structure for basic_customer_bank
-- ----------------------------
DROP TABLE IF EXISTS `basic_customer_bank`;
CREATE TABLE `basic_customer_bank`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '银行卡id',
  `customer_id` bigint(32) DEFAULT NULL COMMENT '客户id',
  `bank_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '开户行名称',
  `bank_branch` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '开户支行',
  `card_no` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '银行卡号',
  `client_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户号',
  `account_type` int(11) DEFAULT NULL COMMENT '账户类型',
  `deposit_city` int(11) DEFAULT NULL COMMENT '开户城市',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` int(200) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  `account_status` int(11) DEFAULT NULL COMMENT '开户状态',
  `province` int(11) DEFAULT NULL COMMENT '开户省份',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_customer_blacklist_record
-- ----------------------------
DROP TABLE IF EXISTS `basic_customer_blacklist_record`;
CREATE TABLE `basic_customer_blacklist_record`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '名单id',
  `customer_id` bigint(32) DEFAULT NULL COMMENT '客户id',
  `begin_time` datetime(0) DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) DEFAULT NULL COMMENT '结束时间',
  `blacklist_type` int(11) DEFAULT NULL COMMENT '名单类型',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_customer_car
-- ----------------------------
DROP TABLE IF EXISTS `basic_customer_car`;
CREATE TABLE `basic_customer_car`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '车辆id',
  `order_id` bigint(32) DEFAULT NULL COMMENT '订单ID',
  `customer_id` bigint(32) DEFAULT NULL COMMENT '客户ID',
  `car_no` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车牌号',
  `car_price` decimal(18, 4) DEFAULT NULL COMMENT '购车价格',
  `car_type` int(11) DEFAULT NULL COMMENT '车辆类型',
  `is_second_hand` int(11) DEFAULT NULL COMMENT '是否二手车',
  `register_time` datetime(0) DEFAULT NULL COMMENT '初次登记时间',
  `mortgage_num` int(11) DEFAULT NULL COMMENT '抵押登记次数',
  `buy_type` int(11) DEFAULT NULL COMMENT '购买方式',
  `is_loan_finished` int(11) DEFAULT NULL COMMENT '是否贷款已还清',
  `car_status` int(11) DEFAULT NULL COMMENT '抵押状态',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  `car_model_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车型名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户车产' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_customer_car_assessment
-- ----------------------------
DROP TABLE IF EXISTS `basic_customer_car_assessment`;
CREATE TABLE `basic_customer_car_assessment`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '评估id',
  `assessment_apply_date` datetime(0) DEFAULT NULL COMMENT '申请评估日期',
  `assessment_date` datetime(0) DEFAULT NULL COMMENT '评估日期',
  `assessment_result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '评估结果',
  `assessment_status` int(32) DEFAULT NULL COMMENT '评估状态',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  `car_id` bigint(32) DEFAULT NULL COMMENT '车产id',
  `assessment_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '评估编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车辆评估' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_customer_contact
-- ----------------------------
DROP TABLE IF EXISTS `basic_customer_contact`;
CREATE TABLE `basic_customer_contact`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(32) DEFAULT NULL COMMENT '客户id',
  `contact_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系人名称',
  `contact_sex` int(11) DEFAULT NULL COMMENT '联系人性别',
  `contact_relation` int(11) DEFAULT NULL,
  `contact_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `contact_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `operator` int(11) DEFAULT NULL,
  `operator_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_customer_data
-- ----------------------------
DROP TABLE IF EXISTS `basic_customer_data`;
CREATE TABLE `basic_customer_data`  (
  `id` bigint(32) NOT NULL COMMENT '资料id',
  `customer_id` bigint(32) DEFAULT NULL COMMENT '客户id',
  `data_type` int(11) DEFAULT NULL COMMENT '资料类型',
  `file_type` int(11) DEFAULT NULL COMMENT '文件类型',
  `file_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '路径',
  `operator` int(200) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_customer_follow
-- ----------------------------
DROP TABLE IF EXISTS `basic_customer_follow`;
CREATE TABLE `basic_customer_follow`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '客户跟进主键',
  `user_id` bigint(32) DEFAULT NULL COMMENT '用户主键',
  `intention_id` bigint(32) DEFAULT NULL COMMENT '意向主键',
  `follow_type` int(11) DEFAULT NULL COMMENT '跟进方式',
  `follow_result` int(11) DEFAULT NULL COMMENT '跟进结果',
  `follow_date` datetime(0) DEFAULT NULL COMMENT '跟进执行日期',
  `operator` int(11) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `is_last_intention` int(11) DEFAULT NULL COMMENT '是否最后一次跟进记录',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_customer_house
-- ----------------------------
DROP TABLE IF EXISTS `basic_customer_house`;
CREATE TABLE `basic_customer_house`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(32) DEFAULT NULL COMMENT '客户ID',
  `house_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '房产地址',
  `house_type` int(11) DEFAULT NULL COMMENT '户型',
  `house_area` int(8) DEFAULT NULL COMMENT '面积',
  `operator` int(20) DEFAULT NULL COMMENT '操作人',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_customer_intention
-- ----------------------------
DROP TABLE IF EXISTS `basic_customer_intention`;
CREATE TABLE `basic_customer_intention`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '意向客户主键',
  `order_id` bigint(32) DEFAULT NULL COMMENT '订单主键',
  `customer_id` bigint(32) DEFAULT NULL COMMENT '客户主键',
  `intention_type` int(11) DEFAULT NULL COMMENT '意向类型',
  `intention_status` int(11) DEFAULT NULL COMMENT '意向状态',
  `intention_level` double(8, 1) DEFAULT NULL COMMENT '意向等级',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  `is_last_intention` int(11) DEFAULT NULL COMMENT '是否最后一次意向跟进记录',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of basic_customer_intention
-- ----------------------------
INSERT INTO `basic_customer_intention` VALUES (1, NULL, 1, NULL, 10065, 4.0, NULL, 1, '2018-07-18 09:17:50', 10002);

-- ----------------------------
-- Table structure for basic_customer_job
-- ----------------------------
DROP TABLE IF EXISTS `basic_customer_job`;
CREATE TABLE `basic_customer_job`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_id` bigint(64) DEFAULT NULL COMMENT '客户id',
  `job_type` int(32) DEFAULT NULL COMMENT '职业类型',
  `company_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位名称',
  `company_nature` int(32) DEFAULT NULL COMMENT '单位性质',
  `rank` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职级',
  `company_address` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位地址',
  `company_address_detail` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位地址详细',
  `company_phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位固定电话',
  `department` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门',
  `duty` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职务',
  `access_company_time` datetime(0) DEFAULT NULL COMMENT '何时进入公司',
  `basic_salary` decimal(18, 4) DEFAULT 0.0000 COMMENT '基本月薪（元）',
  `pay_day` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '每月发薪日',
  `pay_way` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发薪方式',
  `yearly_salaries` decimal(18, 4) DEFAULT 0.0000 COMMENT '年收入（万元）',
  `month_other_income` decimal(18, 4) DEFAULT NULL COMMENT '每月其他收入（元）',
  `other_income_source` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '其他收入来源',
  `identity` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份',
  `stock_scale` decimal(18, 4) DEFAULT 0.0000 COMMENT '股占比',
  `enterprise_manage_years` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业经营年限',
  `enterprise_manage_belong` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '经营地归属',
  `employees_number` int(4) DEFAULT NULL COMMENT '员工人数',
  `registered_capital` decimal(18, 4) DEFAULT 0.0000 COMMENT '注册资本（万元）',
  `industry` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属行业',
  `pastyear_income` decimal(18, 4) DEFAULT 0.0000 COMMENT '过去一年营业收入（万元）',
  `pastyear_profit` decimal(18, 4) DEFAULT 0.0000 COMMENT '过去一年利润',
  `operator` int(32) DEFAULT NULL COMMENT '操作员',
  `operate_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  `province` int(4) DEFAULT NULL COMMENT '单位省份',
  `city` int(4) DEFAULT NULL COMMENT '单位城市',
  `other_industry` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '其他行业',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户职业信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_customer_order
-- ----------------------------
DROP TABLE IF EXISTS `basic_customer_order`;
CREATE TABLE `basic_customer_order`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `scheme_id` bigint(32) DEFAULT NULL COMMENT '还款方案id',
  `repay_template_id` bigint(32) DEFAULT NULL COMMENT '还款计划ID',
  `product_id` bigint(32) DEFAULT NULL COMMENT '产品ID',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构ID',
  `customer_id` bigint(32) DEFAULT NULL COMMENT '客户ID',
  `work_flow_key` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审核流程KEY',
  `work_flow_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审核流程ID',
  `work_flow_link_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审核环节名称',
  `order_no` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单编号',
  `order_price` decimal(18, 4) DEFAULT NULL COMMENT '成交价格',
  `order_type` int(11) DEFAULT NULL COMMENT '订单类型',
  `order_status` int(11) DEFAULT NULL COMMENT '订单状态',
  `order_repay_type` int(11) DEFAULT NULL COMMENT '还款方式',
  `order_mortgage_type` int(11) DEFAULT NULL COMMENT '抵押方式',
  `order_credit_days` int(11) DEFAULT NULL COMMENT '征信保护天数',
  `order_overdue_days` int(11) DEFAULT NULL COMMENT '逾期保护天数',
  `order_interest_rate` decimal(18, 8) DEFAULT NULL COMMENT '利率',
  `order_cycle_type` int(11) DEFAULT NULL COMMENT '周期类型',
  `order_account_day` int(11) DEFAULT NULL COMMENT '还款日',
  `order_periods` int(11) DEFAULT NULL COMMENT '期数',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_expense
-- ----------------------------
DROP TABLE IF EXISTS `basic_expense`;
CREATE TABLE `basic_expense`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构ID',
  `expense_template_id` bigint(32) DEFAULT NULL COMMENT '费用项ID2',
  `expense_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '费用项名称',
  `expense_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '费用项编码',
  `is_system` bigint(11) DEFAULT NULL COMMENT '是否系统费用项',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '费用项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_expense_template
-- ----------------------------
DROP TABLE IF EXISTS `basic_expense_template`;
CREATE TABLE `basic_expense_template`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `expense_template_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '费用项名称',
  `expense_template_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '费用项编码',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(50) DEFAULT NULL COMMENT '操作人',
  `operator_time` timestamp(0) DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '费用项模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of basic_expense_template
-- ----------------------------
INSERT INTO `basic_expense_template` VALUES (1, '押金', 'yj', NULL, NULL, NULL);
INSERT INTO `basic_expense_template` VALUES (2, 'GPS费用', 'gpsfy', NULL, NULL, NULL);
INSERT INTO `basic_expense_template` VALUES (3, '管理费', 'glf', NULL, NULL, NULL);
INSERT INTO `basic_expense_template` VALUES (4, '本金', 'bj', NULL, NULL, NULL);
INSERT INTO `basic_expense_template` VALUES (5, '利息', 'lx', NULL, NULL, NULL);
INSERT INTO `basic_expense_template` VALUES (6, '罚息', 'fxjmbj', NULL, NULL, NULL);
INSERT INTO `basic_expense_template` VALUES (7, '减免本金', 'jmlx', NULL, NULL, NULL);
INSERT INTO `basic_expense_template` VALUES (8, '减免利息', 'jmlx', NULL, NULL, NULL);
INSERT INTO `basic_expense_template` VALUES (9, '减免罚息', 'jmfx', NULL, NULL, NULL);
INSERT INTO `basic_expense_template` VALUES (10, '冻结罚息', 'djfx', NULL, NULL, NULL);
INSERT INTO `basic_expense_template` VALUES (11, '提前结清手续费', 'tqjqsxf', NULL, NULL, NULL);
INSERT INTO `basic_expense_template` VALUES (12, '罚金', 'fj', NULL, NULL, NULL);
INSERT INTO `basic_expense_template` VALUES (13, '首付款', 'sfk', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for basic_offset
-- ----------------------------
DROP TABLE IF EXISTS `basic_offset`;
CREATE TABLE `basic_offset`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构ID',
  `offset_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '冲抵名称',
  `offset_type` int(11) DEFAULT NULL COMMENT '冲抵类型',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(200) DEFAULT NULL COMMENT '操作人',
  `operator_time` timestamp(0) DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '冲抵策略' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of basic_offset
-- ----------------------------
INSERT INTO `basic_offset` VALUES (4, 1, '分期付', 10076, '11', 1, '2018-06-21 21:50:31');
INSERT INTO `basic_offset` VALUES (5, 1, '提前结清', 10075, '提前结清所有款项', 1, '2018-06-21 22:24:44');
INSERT INTO `basic_offset` VALUES (7, 1, '测试冲抵策略', 10076, '备注', 1, '2018-06-21 22:24:52');
INSERT INTO `basic_offset` VALUES (9, 1, '正确的冲抵', 10076, '正确的冲抵 分期还款', 1, '2018-06-30 09:28:22');
INSERT INTO `basic_offset` VALUES (10, 1, 'Tom-冲抵项', 10076, '测试', 1, '2018-06-30 09:39:17');

-- ----------------------------
-- Table structure for basic_offset_item
-- ----------------------------
DROP TABLE IF EXISTS `basic_offset_item`;
CREATE TABLE `basic_offset_item`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `item_order` bigint(32) DEFAULT NULL COMMENT '序号',
  `offset_id` bigint(32) DEFAULT NULL COMMENT '冲抵ID',
  `expense_id` bigint(32) DEFAULT NULL COMMENT '费用项ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '冲抵项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_order_car
-- ----------------------------
DROP TABLE IF EXISTS `basic_order_car`;
CREATE TABLE `basic_order_car`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '订单车型ID',
  `model_id` bigint(32) DEFAULT NULL COMMENT '车型ID',
  `supplier_id` bigint(32) DEFAULT NULL COMMENT '供应商ID',
  `order_id` bigint(32) DEFAULT NULL COMMENT '订单ID',
  `stock_id` bigint(32) DEFAULT NULL COMMENT '库存车辆ID',
  `order_car_name` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车型名称',
  `order_car_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车型描述',
  `order_car_color` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车型颜色',
  `order_car_param_desc` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车型参数描述',
  `is_supplier` int(11) DEFAULT NULL COMMENT '是否外采',
  `has_supplier_loan` int(11) DEFAULT NULL COMMENT '是否供应商放款',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(200) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单车型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_order_file
-- ----------------------------
DROP TABLE IF EXISTS `basic_order_file`;
CREATE TABLE `basic_order_file`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '订单附件ID',
  `order_file_type` int(11) DEFAULT NULL COMMENT '订单资料类型',
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '路径',
  `order_id` bigint(32) DEFAULT NULL COMMENT '订单关联id',
  `data_type` int(11) DEFAULT NULL COMMENT '资料类型·',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单补充上传资料' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_order_record
-- ----------------------------
DROP TABLE IF EXISTS `basic_order_record`;
CREATE TABLE `basic_order_record`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(32) DEFAULT NULL COMMENT '订单ID',
  `order_link` int(32) DEFAULT NULL COMMENT '订单所在环节',
  `order_status` int(32) DEFAULT NULL COMMENT '订单状态',
  `relate_id` bigint(32) DEFAULT NULL COMMENT '关联id',
  `link_table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '关联表名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单操作记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_order_repay_scheme
-- ----------------------------
DROP TABLE IF EXISTS `basic_order_repay_scheme`;
CREATE TABLE `basic_order_repay_scheme`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '订单还款计划ID',
  `expense_id` bigint(32) DEFAULT NULL COMMENT '费用项ID',
  `repay_template_id` bigint(32) DEFAULT NULL COMMENT '还款计划ID',
  `order_id` bigint(32) DEFAULT NULL COMMENT '订单ID',
  `periods` int(11) DEFAULT NULL COMMENT '期数',
  `repay_money` decimal(18, 4) DEFAULT NULL COMMENT '还款金额',
  `is_repay_money` decimal(18, 4) DEFAULT 0.0000 COMMENT '已还款',
  `repay_status` int(11) DEFAULT NULL COMMENT '还款状态',
  `expense_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '费用项名称',
  `expense_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '费用项编码',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单还款详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_product
-- ----------------------------
DROP TABLE IF EXISTS `basic_product`;
CREATE TABLE `basic_product`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `scheme_id` bigint(32) DEFAULT NULL COMMENT '方案ID',
  `config_id` bigint(32) DEFAULT NULL COMMENT '车型ID',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构ID',
  `product_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '产品名称',
  `file_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '路径',
  `product_status` int(11) DEFAULT NULL,
  `product_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '车型产品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_product_repay_template
-- ----------------------------
DROP TABLE IF EXISTS `basic_product_repay_template`;
CREATE TABLE `basic_product_repay_template`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '还款计划ID',
  `product_id` bigint(32) DEFAULT NULL COMMENT '产品ID',
  `repay_scheme_expense_id` bigint(32) DEFAULT NULL COMMENT '还款方案比例详情ID',
  `periods` int(11) DEFAULT NULL COMMENT '期数',
  `repay_money` decimal(18, 4) DEFAULT NULL COMMENT '还款金额',
  `account_day` int(11) DEFAULT NULL COMMENT '还款日',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品还款计划模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_repay_scheme
-- ----------------------------
DROP TABLE IF EXISTS `basic_repay_scheme`;
CREATE TABLE `basic_repay_scheme`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `offset_id` bigint(32) DEFAULT NULL COMMENT '冲抵ID',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构ID',
  `scheme_type` int(11) DEFAULT NULL COMMENT '方案类型',
  `scheme_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '方案名称',
  `repay_type` int(11) DEFAULT NULL COMMENT '还款方式',
  `mortgage_type` int(11) DEFAULT NULL COMMENT '抵押方式',
  `scheme_status` int(11) DEFAULT NULL COMMENT '发布状态',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  `credit_days` int(11) DEFAULT 0 COMMENT '征信保护天数',
  `overdue_days` int(11) DEFAULT 0 COMMENT '逾期保护天数',
  `periods` int(11) DEFAULT NULL COMMENT '期数',
  `interest_rate` decimal(18, 8) DEFAULT 0.00000000 COMMENT '利率',
  `cycle_type` int(11) DEFAULT NULL COMMENT '周期类型',
  `money_min` decimal(18, 4) DEFAULT 0.0000 COMMENT '融资最小金额',
  `money_max` decimal(18, 4) DEFAULT 0.0000 COMMENT '融资最大金额',
  `account_period_type` int(11) DEFAULT NULL COMMENT '账期类型',
  `account_day` int(11) DEFAULT NULL COMMENT '还款日',
  `work_flow_key` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审核流程KEY',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '还款方案' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_repay_scheme_expense
-- ----------------------------
DROP TABLE IF EXISTS `basic_repay_scheme_expense`;
CREATE TABLE `basic_repay_scheme_expense`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `scheme_id` bigint(32) DEFAULT NULL COMMENT '方案ID',
  `expense_id` bigint(32) DEFAULT NULL COMMENT '费用项ID',
  `is_first` int(11) DEFAULT NULL COMMENT '是否首付款',
  `repay_proportion` decimal(18, 8) DEFAULT 0.00000000 COMMENT '收取总额比例',
  `fixed_cost` decimal(18, 4) DEFAULT 0.0000 COMMENT '固定费用',
  `repay_type` int(11) DEFAULT NULL COMMENT '还款方式',
  `is_last` int(11) DEFAULT NULL COMMENT '是否尾款',
  `is_refund` int(11) DEFAULT NULL COMMENT '是否退款',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '还款方案比例详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_stock_car
-- ----------------------------
DROP TABLE IF EXISTS `basic_stock_car`;
CREATE TABLE `basic_stock_car`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '库存主键',
  `supplier_id` bigint(32) DEFAULT NULL COMMENT '供应商id',
  `org_id` bigint(32) DEFAULT NULL COMMENT '组织id',
  `order_id` bigint(32) DEFAULT NULL COMMENT '订单id',
  `model_id` bigint(32) DEFAULT NULL COMMENT '车型id',
  `stock_car_no` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车架号',
  `stock_engine_no` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发动机号',
  `stock_car_color` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车辆颜色',
  `stock_status` int(11) DEFAULT NULL COMMENT '库存状态',
  `has_supplier_loan` int(11) DEFAULT NULL COMMENT '是否供应商放款',
  `stock_price` decimal(18, 4) DEFAULT NULL COMMENT '采购价格',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作人时间',
  `stock_in_date` datetime(0) DEFAULT NULL COMMENT '入库时间',
  `stock_out_date` datetime(0) DEFAULT NULL COMMENT '出库时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for basic_supplier
-- ----------------------------
DROP TABLE IF EXISTS `basic_supplier`;
CREATE TABLE `basic_supplier`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '供应商主键',
  `org_id` bigint(32) DEFAULT NULL COMMENT '组织id',
  `supplier_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '供应商名称',
  `supplier_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `supplier_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '供应商电话',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finance_frozen
-- ----------------------------
DROP TABLE IF EXISTS `finance_frozen`;
CREATE TABLE `finance_frozen`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '冻结ID',
  `order_repay_id` bigint(11) DEFAULT NULL COMMENT '订单还款计划ID',
  `order_id` bigint(11) DEFAULT NULL COMMENT '订单ID',
  `org_id` bigint(11) DEFAULT NULL COMMENT '机构ID',
  `frozen_money` decimal(18, 4) DEFAULT NULL COMMENT '冻结金额',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` int(11) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '冻结记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finance_loan
-- ----------------------------
DROP TABLE IF EXISTS `finance_loan`;
CREATE TABLE `finance_loan`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构ID',
  `order_id` bigint(32) DEFAULT NULL COMMENT '订单ID',
  `loan_type` int(11) DEFAULT NULL COMMENT '放款类型',
  `loan_money` decimal(18, 4) DEFAULT NULL COMMENT '放款金额',
  `loan_date` datetime(0) DEFAULT NULL COMMENT '放款日期',
  `has_invoice` int(11) DEFAULT NULL COMMENT '是否收到发票',
  `has_receipt` int(11) DEFAULT NULL COMMENT '是否收到收据',
  `file_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '路径',
  `operator` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作人',
  `operator_time` timestamp(0) DEFAULT NULL COMMENT '操作日期',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '放款记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finance_loan_detial
-- ----------------------------
DROP TABLE IF EXISTS `finance_loan_detial`;
CREATE TABLE `finance_loan_detial`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `loan_id` bigint(32) DEFAULT NULL COMMENT '放款ID',
  `expense_id` bigint(32) DEFAULT NULL COMMENT '费用项ID',
  `card_id` bigint(32) DEFAULT NULL COMMENT '银行卡ID',
  `loan_money` decimal(18, 4) DEFAULT NULL COMMENT '放款金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '放款明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finance_receivable
-- ----------------------------
DROP TABLE IF EXISTS `finance_receivable`;
CREATE TABLE `finance_receivable`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '收款记录ID',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构ID',
  `settle_id` bigint(32) DEFAULT NULL COMMENT '提前结清ID',
  `takeback_id` bigint(32) DEFAULT NULL COMMENT '提前收回ID',
  `card_id` bigint(32) DEFAULT NULL COMMENT '银行卡ID',
  `order_id` bigint(32) DEFAULT NULL COMMENT '订单ID',
  `receivable_type` int(11) DEFAULT NULL COMMENT '收款类型',
  `receivable_detial_money` decimal(18, 4) DEFAULT NULL COMMENT '收款金额',
  `receivable_date` datetime(0) DEFAULT NULL COMMENT '收款日期',
  `is_invoice` int(11) DEFAULT NULL COMMENT '是否已开发票',
  `is_receipt` int(11) DEFAULT NULL COMMENT '是否已开收据',
  `file_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '路径',
  `operator` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收款记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finance_receivable_detial
-- ----------------------------
DROP TABLE IF EXISTS `finance_receivable_detial`;
CREATE TABLE `finance_receivable_detial`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '收款详情ID',
  `order_repay_id` bigint(32) DEFAULT NULL COMMENT '订单还款计划ID',
  `receivable_id` bigint(32) DEFAULT NULL COMMENT '收款记录ID',
  `receivable_detial_money` decimal(18, 4) DEFAULT NULL COMMENT '收款金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收款明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finance_refund
-- ----------------------------
DROP TABLE IF EXISTS `finance_refund`;
CREATE TABLE `finance_refund`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint(32) DEFAULT NULL COMMENT 'order_id',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构id',
  `refund_money` decimal(18, 4) DEFAULT NULL COMMENT '退款金额',
  `refund_date` timestamp(0) DEFAULT NULL COMMENT '退款日期',
  `is_invoice` int(11) DEFAULT NULL COMMENT '是否收到发票',
  `is_receipt` int(11) DEFAULT NULL COMMENT '是否收到收据',
  `file_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '路径',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` timestamp(0) DEFAULT NULL COMMENT '操作日期',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '退款记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finance_refund_detial
-- ----------------------------
DROP TABLE IF EXISTS `finance_refund_detial`;
CREATE TABLE `finance_refund_detial`  (
  `refund_detial_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '退款详情ID主键',
  `card_id` bigint(32) DEFAULT NULL COMMENT '银行卡Id',
  `refund_id` bigint(32) DEFAULT NULL COMMENT '退款记录ID',
  `expense_id` bigint(32) DEFAULT NULL COMMENT '费用项ID',
  `refund_detial_money` decimal(18, 4) DEFAULT NULL COMMENT '退款金额',
  PRIMARY KEY (`refund_detial_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finance_remit
-- ----------------------------
DROP TABLE IF EXISTS `finance_remit`;
CREATE TABLE `finance_remit`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '减免ID',
  `order_repay_id` bigint(11) DEFAULT NULL COMMENT '订单还款计划ID',
  `org_id` bigint(11) DEFAULT NULL COMMENT '机构ID',
  `order_id` bigint(11) DEFAULT NULL COMMENT '订单ID',
  `remit_money` decimal(18, 4) DEFAULT NULL COMMENT '减免金额',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` int(11) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '减免记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finance_settle
-- ----------------------------
DROP TABLE IF EXISTS `finance_settle`;
CREATE TABLE `finance_settle`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '提前结清ID',
  `receivable_id` bigint(32) DEFAULT NULL COMMENT '收款记录ID',
  `order_id` bigint(32) DEFAULT NULL COMMENT '订单ID',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构ID',
  `settle_date` datetime(0) DEFAULT NULL COMMENT '提前结清日期',
  `settle_money` decimal(10, 0) DEFAULT NULL COMMENT '提前结清金额',
  `operator` int(200) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '提前结清记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finance_storage
-- ----------------------------
DROP TABLE IF EXISTS `finance_storage`;
CREATE TABLE `finance_storage`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_id` bigint(32) DEFAULT NULL COMMENT '订单ID',
  `car_id` bigint(32) DEFAULT NULL COMMENT '车辆ID',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构ID',
  `stock_in_date` datetime(0) DEFAULT NULL COMMENT '入库日期',
  `stock_out_date` datetime(0) DEFAULT NULL COMMENT '出库日期',
  `mortgage_status` int(11) DEFAULT NULL COMMENT '押品状态',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '押品出入库记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finance_storage_mortgage
-- ----------------------------
DROP TABLE IF EXISTS `finance_storage_mortgage`;
CREATE TABLE `finance_storage_mortgage`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '抵押ID',
  `order_id` bigint(32) DEFAULT NULL COMMENT '订单ID',
  `car_id` bigint(32) DEFAULT NULL COMMENT '车辆ID',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构ID',
  `mortgage_record_id` bigint(32) DEFAULT NULL COMMENT '抵押记录ID',
  `stock_in_date` datetime(0) DEFAULT NULL COMMENT '入库日期',
  `stock_out_date` datetime(0) DEFAULT NULL COMMENT '出库日期',
  `mortgage_status` int(11) DEFAULT NULL COMMENT '押品状态',
  `mortgage_no` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '抵押号',
  `gps_status` int(11) DEFAULT NULL COMMENT 'GPS安装状态',
  `gps_no` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备号',
  `gps_manufactor` int(11) DEFAULT NULL COMMENT '设备厂家',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '抵押记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finance_storage_pledge
-- ----------------------------
DROP TABLE IF EXISTS `finance_storage_pledge`;
CREATE TABLE `finance_storage_pledge`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '质押ID',
  `order_id` bigint(32) DEFAULT NULL COMMENT '订单ID',
  `car_id` bigint(32) DEFAULT NULL COMMENT '车辆ID',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构ID',
  `mortgage_record_id` bigint(32) DEFAULT NULL COMMENT '抵押记录ID',
  `stock_in_date` datetime(0) DEFAULT NULL COMMENT '入库日期',
  `stock_out_date` datetime(0) DEFAULT NULL COMMENT '出库日期',
  `mortgage_status` int(11) DEFAULT NULL COMMENT '押品状态',
  `pledge_place` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '质押地点',
  `pledge_position` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '质押位置',
  `pledge_no` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '质押号',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '质押记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finance_takeback
-- ----------------------------
DROP TABLE IF EXISTS `finance_takeback`;
CREATE TABLE `finance_takeback`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '提前收回ID',
  `receivable_id` bigint(32) DEFAULT NULL COMMENT '收款记录ID',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构ID',
  `order_id` bigint(32) DEFAULT NULL COMMENT '订单ID',
  `takeback_date` datetime(0) DEFAULT NULL COMMENT '提前收回时间',
  `takeback_money` decimal(18, 4) DEFAULT NULL COMMENT '提前收回金额',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '提前收回记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_column_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_column_resource`;
CREATE TABLE `sys_column_resource`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `resource_pid` bigint(32) DEFAULT NULL COMMENT '父页面id',
  `resource_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源名称',
  `resource_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源码',
  `resource_level` int(11) DEFAULT NULL COMMENT '资源级别',
  `resource_status` int(11) DEFAULT NULL COMMENT '状态',
  `resource_icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源图标',
  `resource_file_type` int(4) DEFAULT NULL COMMENT '资源文件类型',
  `resource_sort` int(4) DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `operate_time` datetime(0) DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 556 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户资源管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_column_resource
-- ----------------------------
INSERT INTO `sys_column_resource` VALUES (1, 69, '操作人', 'operatorName', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (2, 69, '操作时间', 'operateTime', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (3, 69, 'mysql文件名', 'mysqlName', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (4, 69, 'mongdb文件名', 'mongdbName', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (5, 69, '备份类型', 'type', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (6, 9, '用户名', 'userUsername', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (7, 9, '姓名', 'userRealname', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (8, 9, '部门', 'deptName', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (9, 9, '状态', 'status', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (10, 9, '电话', 'userPhone', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (11, 9, '备注', 'userRemark', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (12, 9, '操作人', 'operatorName', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (13, 9, '操作时间', 'operateTime', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (14, 57, '操作人', 'realName', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (15, 57, '客户端IP', 'clientIp', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (16, 57, '执行方法', 'exeMethod', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (17, 57, '备注', 'logRemark', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (18, 57, '请求执行时长（秒）', 'exeTime', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (19, 57, '执行类型', 'exeType', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (20, 57, '操作时间', 'operateTime', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (21, 63, '任务名称', 'jobName', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (22, 63, '任务所在组', 'jobGroup', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (23, 63, '任务描述', 'jobDescribe', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (24, 63, '任务类名', 'jobClassName', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (25, 63, '触发器名称', 'triggerName', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (26, 63, '触发器所在组', 'triggerGroup', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (27, 63, '下次执行时间', 'nextExecutionTime', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (28, 63, '表达式', 'expression', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (29, 63, '状态', 'triggerState', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (30, 63, '时区', 'timeZone', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (31, 77, '参数代码', 'paramCode', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (32, 77, '参数名称', 'paramName', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (33, 77, '参数值', 'paramValue', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (34, 77, '是否启用', 'paramStatus', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (35, 77, '说明', 'paramRemark', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (36, 20, '状态', 'roleStatus', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (37, 20, '角色名称', 'roleName', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (38, 20, '备注', 'roleRemark', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (39, 20, '操作人', 'realName', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (40, 20, '创建时间', 'operateTime', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (41, 34, '资源初始化名称', 'resoInitName', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (42, 34, '资源名称', 'resoName', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (43, 34, '资源初始化图标', 'resoInitIcon', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (44, 34, '资源图标', 'resoIcon', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (45, 34, '重置', 'moduleId', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (46, 34, '状态', 'resoStatus', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (47, 34, '备注', 'resoRemark', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (48, 109, '类型', 'type', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (49, 109, '一级', 'first', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (50, 109, '二级', 'second', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (51, 109, 'CRC编码', 'crc', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (52, 109, '详细内容', 'detail', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (53, 109, '创建人', 'operator', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (54, 109, '创建时间', 'operatorTime', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (55, 175, '公司简称', 'companyChinaname', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (56, 175, '省份', 'companyProvince', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (57, 175, '城市', 'companyCity', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (58, 175, '户名', 'bankAccount', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (59, 175, '开户银行', 'depositBank', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (60, 175, '银行卡号', 'cardNumber', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (61, 175, '支行名称', 'branchName', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (62, 175, '状态', 'companyStatus', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (63, 144, '名称', 'name', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (64, 144, '是否必传', 'isNecessary', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (65, 108, '名称', 'name', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (66, 154, '产品包名称', 'productPackageName', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (67, 154, '经销商', 'quotationName', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (68, 154, '品牌', 'carBrandName', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (69, 154, '系列', 'carSeriesName', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (70, 154, '型号', 'carName', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (71, 154, '颜色', 'carColor', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (72, 154, '市场指导价', 'marketGuidingPrice', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (73, 154, '经销商价格', 'dealerGuidingPrice', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (74, 154, '首期金额', 'firstPayment', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (75, 154, '融资金额', 'financeAmount', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (76, 154, '融资期数', 'periods', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (77, 154, '租金（月）', 'monthPay', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (78, 154, '购置税', 'purchaseTaxMoney', NULL, NULL, NULL, 439, 13, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (79, 154, '路桥费', 'roadBridgeFee', NULL, NULL, NULL, 439, 14, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (80, 154, '保险费', 'annualAmount', NULL, NULL, NULL, 439, 15, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (81, 154, 'GPS费', 'gpsFee', NULL, NULL, NULL, 439, 16, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (82, 154, '其他费用', 'otherFee', NULL, NULL, NULL, 439, 17, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (83, 154, '状态', 'status', NULL, NULL, NULL, 439, 18, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (84, 91, '文件名', 'fileName', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (85, 91, '上传时间', 'uploadTime', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (86, 91, '操作人', 'operatorName', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (87, 91, '备注', 'remark', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (88, 180, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (89, 180, '证件号码', 'idCard', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (90, 180, '联系号码', 'mobileMain', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (91, 180, '是否开户', 'isAccount', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (92, 180, '绑卡银行', 'boundBank', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (93, 180, '银行卡号', 'bankCard', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (94, 180, '客户号', 'customId', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (95, 180, '验卡失败原因', 'faileReason', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (96, 180, '结算通道', 'settlementChannel', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (97, 184, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (98, 184, '订单创建时间', 'orderCreateTime', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (99, 184, '订单类型', 'orderType', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (100, 184, '产品名称', 'productName', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (101, 184, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (102, 184, '证件号码', 'idCard', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (103, 184, '联系号码', 'mobileMain', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (104, 184, '最近合同生成日期', 'latelyContractTime', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (105, 192, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (106, 192, '制单人', 'recorderName', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (107, 192, '所属部门', 'deptName', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (108, 192, '转交人', 'transferName', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (109, 192, '订单创建时间', 'createTime', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (110, 192, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (111, 192, '证件号码', 'idCard', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (112, 192, '联系号码', 'mobileMain', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (113, 192, '产品名称', 'productName', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (114, 192, '产品期数', 'periods', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (115, 192, '环节', 'orderLink', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (116, 192, '审批状态', 'orderStatus', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (117, 235, '环节', 'orderLink', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (118, 235, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (119, 235, '订单创建时间', 'createTime', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (120, 235, '进入资源池时间', 'intoPoolDate', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (121, 235, '省份', 'province', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (122, 235, '城市', 'city', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (123, 235, '订单类型', 'orderType', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (124, 235, '产品名称', 'productName', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (125, 235, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (126, 235, '证件号码', 'idCard', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (127, 235, '手机号', 'mobileMain', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (128, 244, '环节', 'orderLink', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (129, 244, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (130, 244, '订单创建时间', 'createTime', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (131, 244, '进入资源池时间', 'intoPoolDate', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (132, 244, '省份', 'province', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (133, 244, '城市', 'city', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (134, 244, '订单类型', 'orderType', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (135, 244, '产品名称', 'productName', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (136, 244, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (137, 244, '证件号码', 'idCard', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (138, 244, '手机号', 'mobileMain', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (139, 253, '环节', 'orderLink', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (140, 253, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (141, 253, '订单创建时间', 'createTime', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (142, 253, '进入资源池时间', 'intoPoolDate', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (143, 253, '省份', 'province', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (144, 253, '城市', 'city', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (145, 253, '订单类型', 'orderType', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (146, 253, '产品名称', 'productName', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (147, 253, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (148, 253, '证件号码', 'idCard', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (149, 253, '手机号', 'mobileMain', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (150, 262, '环节', 'orderLink', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (151, 262, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (152, 262, '订单创建时间', 'createTime', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (153, 262, '进入资源池时间', 'intoPoolDate', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (154, 262, '省份', 'province', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (155, 262, '城市', 'city', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (156, 262, '订单类型', 'orderType', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (157, 262, '产品名称', 'productName', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (158, 262, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (159, 262, '证件号码', 'idCard', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (160, 262, '手机号', 'mobileMain', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (161, 271, '环节', 'orderLink', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (162, 271, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (163, 271, '订单创建时间', 'createTime', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (164, 271, '进入资源池时间', 'intoPoolDate', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (165, 271, '省份', 'province', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (166, 271, '城市', 'city', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (167, 271, '订单类型', 'orderType', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (168, 271, '产品名称', 'productName', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (169, 271, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (170, 271, '证件号码', 'idCard', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (171, 271, '手机号', 'mobileMain', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (172, 280, '环节', 'orderLink', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (173, 280, '订单状态', 'orderStatus', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (174, 280, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (175, 280, '订单创建时间', 'createTime', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (176, 280, '领取时间', 'receiveDate', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (177, 280, '处理时间', 'approvalDate', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (178, 280, '省份', 'province', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (179, 280, '城市', 'city', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (180, 280, '订单类型', 'orderType', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (181, 280, '产品名称', 'productName', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (182, 280, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (183, 280, '证件号码', 'idCard', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (184, 280, '手机号', 'mobileMain', NULL, NULL, NULL, 439, 13, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (185, 295, '环节', 'orderLink', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (186, 295, '订单状态', 'orderStatus', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (187, 295, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (188, 295, '订单创建时间', 'createTime', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (189, 295, '进入资源池时间', 'intoPoolDate', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (190, 295, '省份', 'province', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (191, 295, '城市', 'city', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (192, 295, '订单类型', 'orderType', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (193, 295, '产品名称', 'productName', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (194, 295, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (195, 295, '证件号码', 'idCard', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (196, 295, '手机号', 'mobileMain', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (197, 304, '环节', 'orderLink', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (198, 304, '订单状态', 'orderStatus', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (199, 304, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (200, 304, '订单创建时间', 'createTime', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (201, 304, '进入资源池时间', 'intoPoolDate', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (202, 304, '省份', 'province', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (203, 304, '城市', 'city', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (204, 304, '订单类型', 'orderType', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (205, 304, '产品名称', 'productName', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (206, 304, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (207, 304, '证件号码', 'idCard', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (208, 304, '手机号', 'mobileMain', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (209, 313, '环节', 'orderLink', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (210, 313, '订单状态', 'orderStatus', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (211, 313, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (212, 313, '订单创建时间', 'createTime', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (213, 313, '进入资源池时间', 'intoPoolDate', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (214, 313, '省份', 'province', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (215, 313, '城市', 'city', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (216, 313, '订单类型', 'orderType', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (217, 313, '产品名称', 'productName', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (218, 313, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (219, 313, '证件号码', 'idCard', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (220, 313, '手机号', 'mobileMain', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (221, 322, '环节', 'orderLink', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (222, 322, '订单状态', 'orderStatus', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (223, 322, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (224, 322, '订单创建时间', 'createTime', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (225, 322, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (226, 322, '证件号码', 'idCard', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (227, 322, '手机号', 'mobileMain', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (228, 322, '订单类型', 'orderType', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (229, 322, '订单归属公司', 'orderCompany', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (230, 322, '归属部门', 'orderDept', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (231, 322, '制单人', 'recorder', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (232, 331, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (233, 331, '客户结算号', 'clientNumber', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (234, 331, '客户姓名', 'name', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (235, 331, '证件号码', 'idCard', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (236, 331, '手机号', 'mobileMain', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (237, 331, '订单创建时间', 'createTime', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (238, 331, '合同生效日', 'contractDate', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (239, 331, '待还本金', 'principalReceivable', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (240, 331, '待还利息', 'interestReceivable', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (241, 331, '待还罚息', 'penaltyReceivable', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (242, 331, '利率%/月', 'productRate', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (243, 331, '结算通道', 'settlementChannel', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (244, 331, '归属公司', 'companyChinaName', NULL, NULL, NULL, 439, 13, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (268, 356, '审核时间', 'approvalDate', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (269, 356, '省份', 'province', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (270, 356, '城市', 'city', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (271, 356, '网点', 'dot', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (272, 356, '业务员', 'salesmanName', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (273, 356, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (274, 356, '证件号码', 'idCard', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (275, 356, '审核状态', 'approveStatus', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (276, 356, '是否提车', 'isDeliveryCar', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (277, 356, '拒单原因', 'refuseResource', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (278, 356, '拒单细节', 'refuseDetails', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (279, 356, '备注', 'remark', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (280, 356, '审核人员', 'approvalPersonal', NULL, NULL, NULL, 439, 13, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (281, 376, '资料上传', 'isUploadFile', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (282, 376, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (283, 376, '订单创建时间', 'createTime', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (284, 376, '订单类型', 'orderType', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (285, 376, '产品名称', 'productName', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (286, 376, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (287, 376, '证件号码', 'idCard', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (288, 376, '联系号码', 'mobileMain', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (289, 381, '处理状态', 'approvalDealStatus', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (290, 381, '处理时间', 'dealDate', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (291, 381, '处理人', 'dealerName', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (292, 381, '收款类型', 'applicationType', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (293, 381, '收款金额', 'totalPayment', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (294, 381, '收款账户名', 'accountName', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (295, 381, '申请日期', 'operatorTime', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (296, 381, '申请人', 'operatorName', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (297, 390, '处理状态', 'processStatus', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (298, 390, '处理时间', 'processTime', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (299, 390, '处理人', 'processName', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (300, 390, '付款类型', 'refundType', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (301, 390, '付款金额', 'refundTotalAmount', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (302, 390, '客户账户名', 'customerName', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (303, 390, '开户银行', 'depositBank', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (304, 390, '银行卡号', 'cardNumber', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (305, 390, '申请日期', 'operateTime', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (306, 390, '制单人', 'operator', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (307, 392, '文件名', 'fileName', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (308, 392, '上传时间', 'uploadTime', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (309, 392, '操作人', 'operatorName', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (310, 392, '备注', 'remark', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (311, 399, '处理状态', 'collectMoneyDealStatus', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (312, 399, '处理时间', 'dealTime', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (313, 399, '处理人', 'dealerName', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (314, 399, '收款类型', 'applicationType', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (315, 399, '收款总金额', 'totalPayment', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (316, 399, '收款账户名', 'accountName', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (317, 399, '申请日期', 'operatorTime', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (318, 399, '申请人', 'operatorName', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (319, 405, '处理状态', 'processStatus', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (320, 405, '处理时间', 'processTime', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (321, 405, '处理人', 'processName', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (322, 405, '付款类型', 'refundType', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (323, 405, '付款总金额', 'refundTotalAmount', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (324, 405, '付款账户名', 'customerName', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (325, 405, '申请日期', 'operateTime', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (326, 405, '制单人', 'operator', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (327, 412, '订单号', 'orderNumber', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (328, 412, '客户结算号', 'clientNumber', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (329, 412, '客户姓名', 'name', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (330, 412, '证件号', 'idCard', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (331, 412, '手机号', 'mobileMain', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (332, 412, '订单创建时间', 'createTime', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (333, 412, '合同生效日', 'contractDate', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (334, 412, '待还本金', 'principalReceivable', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (335, 412, '待还利息', 'interestReceivable', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (336, 412, '待还罚息', 'penaltyReceivable', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (337, 412, '利率%/月', 'productRate', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (338, 412, '结算通道', 'settlementChannel', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (339, 412, '归属公司', 'companyChinaName', NULL, NULL, NULL, 439, 13, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (340, 420, '订单号', 'orderNumber', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (341, 420, '客户结算号', 'clientNumber', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (342, 420, '客户姓名', 'name', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (343, 420, '证件号', 'idCard', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (344, 420, '手机号', 'mobileMain', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (345, 420, '订单创建时间', 'createTime', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (346, 420, '合同生效日', 'contractDate', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (347, 420, '待还本金', 'principalReceivable', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (348, 420, '待还利息', 'interestReceivable', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (349, 420, '待还罚息', 'penaltyReceivable', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (350, 420, '利率%/月', 'productRate', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (351, 420, '结算通道', 'settlementChannel', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (352, 420, '归属公司', 'companyChinaName', NULL, NULL, NULL, 439, 13, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (353, 428, '订单号', 'orderNumber', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (354, 428, '客户结算号', 'clientNumber', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (355, 428, '客户姓名', 'name', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (356, 428, '证件号', 'idCard', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (357, 428, '手机号', 'mobileMain', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (358, 428, '订单创建时间', 'createTime', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (359, 428, '合同生效日', 'contractDate', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (360, 428, '结算通道', 'settlementChannel', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (361, 428, '归属公司', 'companyChinaName', NULL, NULL, NULL, 439, 13, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (362, 435, '订单号', 'orderId', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (363, 435, '客户结算号', 'clientNumber', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (364, 435, '客户姓名', 'customerName', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (365, 435, '证件号', 'idCard', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (366, 435, '手机号', 'mobileMain', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (367, 435, '合同生效日', 'contractDate', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (368, 435, '结清日期', 'settlementDay', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (369, 435, '利率%/月', 'productRate', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (370, 435, '结算通道', 'settlementChannel', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (371, 435, '结清状态', 'settlementType', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (372, 435, '归属公司', 'companyChinaName', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (373, 447, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (374, 447, '收款类型', 'applicationType', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (375, 447, '申请状态', 'approvalStatus', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (376, 447, '收款客户名', 'accountName', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (377, 447, '收款金额', 'totalPayment', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (378, 447, '申请时间', 'operatorTime', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (379, 447, '制单人', 'userUserName', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (380, 455, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (381, 455, '付款类型', 'refundType', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (382, 455, '申请状态', 'applicationStatus', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (383, 455, '付款客户名', 'customerName', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (384, 455, '付款金额', 'refundTotalAmount', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (385, 455, '申请时间', 'operateTime', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (386, 455, '制单人', 'operator', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (387, 456, '开户日期', 'openAccountDate', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (388, 456, '开户类型', 'accountType', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (389, 456, '客户号', 'clientNumber', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (390, 456, '客户姓名', 'name', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (391, 456, '证件号码', 'certificateNumber', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (392, 456, '预留手机', 'reservedPhoneNumber', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (393, 463, '出账日期', 'paymentDate', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (394, 463, '出账客户号', 'clientNumber', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (395, 463, '出账卡号', 'cardNumber', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (396, 463, '客户姓名', 'clientName', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (397, 463, '支付银行', 'depositBank', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (398, 463, '支付金额', 'paymentAmount', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (399, 463, '订单号', 'orderNumber', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (400, 463, '交易状态', 'tradingStatus', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (401, 463, '失败原因', 'failReason', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (402, 463, '操作人', 'operateName', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (403, 48, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (404, 48, '环节', 'orderLink', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (405, 48, '证件号码', 'idCard', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (406, 48, '联系号码', 'mobileMain', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (407, 48, '订单创建时间', 'createTime', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (408, 48, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (409, 48, '订单类型', 'orderType', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (410, 48, '产品名称', 'productName', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (411, 48, '产品期数', 'periods', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (412, 48, '利率（月）', 'productRate', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (413, 48, '还款方式', 'payWay', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (414, 48, '融资总额', 'financingAmount', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (415, 48, '订单状态', 'orderStatus', NULL, NULL, NULL, 439, 13, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (416, 9, '数据权限', 'userManager', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (417, 466, '订单号', 'orderNumber', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (418, 466, '订单创建时间', 'createTime', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (419, 466, '客户', 'personalName', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (420, 466, '订单类型', 'orderType', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (421, 466, '产品名称', 'productName', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (422, 466, '产品期数', 'periods', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (423, 466, '利率(月)', 'productRate', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (424, 466, '还款方式', 'payWay', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (425, 466, '融资总额', 'financingAmount', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (427, 466, '订单状态', 'orderStatus', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (428, 340, '减免金额', 'remitAmount', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (429, 340, '减免期数', 'periods', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (430, 340, '订单环节', 'orderLink', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (431, 340, '订单状态', 'orderStatus', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (432, 340, '申请时间', 'applyDate', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (433, 340, '客户姓名', 'name', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (434, 340, '证件号码', 'idCard', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (435, 340, '手机号', 'mobileNumber', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (436, 340, '订单创建时间', 'orderCreateTime', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (437, 340, '合同生效日期', 'contractDate', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (438, 340, '备注', 'remitRemark', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (439, 340, '订单号', 'orderNumber', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (440, 348, '冻结金额', 'remitAmount', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (441, 348, '冻结期数', 'periods', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (442, 348, '订单环节', 'orderLink', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (443, 348, '订单状态', 'orderStatus', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (444, 348, '申请时间', 'applyDate', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (445, 348, '客户姓名', 'name', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (446, 348, '证件号码', 'idCard', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (447, 348, '手机号', 'mobileNumber', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (448, 348, '订单创建时间', 'orderCreateTime', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (449, 348, '合同生效日期', 'contractDate', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (450, 348, '订单号', 'orderNumber', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (451, 348, '备注', 'remitRemark', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (452, 466, '环节', 'orderLink\r\n', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (453, 412, '待还管理费', 'manageFeeReceivable', NULL, NULL, NULL, 439, 14, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (454, 412, '待还总金额', 'sumReceivable', NULL, NULL, NULL, 439, 15, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (455, 474, '模板名称', 'templateName', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (456, 474, '创建人', 'operator', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (457, 235, '滞留天数', 'detainedDays', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (458, 390, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (459, 428, '违约金', 'violateAmount', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (460, 428, '提前收回手续费', 'advanceRevokeFee', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (461, 428, '提前收回总额', 'totalPayment', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (462, 280, '滞留天数', 'detainedDays', NULL, NULL, NULL, 439, 14, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (463, 244, '滞留天数', 'detainedDays', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (464, 253, '滞留天数', 'detainedDays', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (465, 262, '滞留天数', 'detainedDays', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (466, 271, '滞留天数', 'detainedDays', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (467, 405, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (468, 399, '订单编号', 'orderNumber', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (469, 485, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (470, 485, '证件类型', 'certificateType', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (471, 485, '证件号码', 'idCard', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (472, 485, '手机号码', 'mobileMain', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (473, 485, '意向级别', 'intentionalLevel', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (474, 485, '所属地区', 'city', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (475, 485, '创建时间', 'createTime', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (476, 485, '归属业务员', 'operator', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (477, 486, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (478, 486, '证件类型', 'certificateType', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (479, 486, '证件号码', 'idCard', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (480, 486, '手机号码', 'mobileMain', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (481, 486, '意向级别', 'intentionalLevel', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (482, 486, '所属地区', 'city', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (483, 486, '创建时间', 'createTime', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (484, 486, '归属业务员', 'operator', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (485, 487, '客户姓名', 'personalName', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (486, 487, '证件类型', 'certificateType', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (487, 487, '证件号码', 'idCard', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (488, 487, '手机号码', 'mobileMain', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (489, 487, '意向级别', 'intentionalLevel', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (490, 487, '所属地区', 'city', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (491, 487, '创建时间', 'createTime', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (492, 487, '归属业务员', 'operator', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (493, 475, '评估编号', 'assessmentNo', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (494, 475, '状态', 'assessmentStatus', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (495, 475, '品牌', 'brandName', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (496, 475, '系列', 'seriesName', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (497, 475, '车型', 'carName', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (498, 475, '颜色', 'carColor', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (499, 475, '车牌号码', 'carNo', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (500, 475, '车架号', 'frameNo', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (501, 475, '发动机号', 'engineNo', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (502, 475, '客户姓名', 'ownerName', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (503, 475, '手机号码', 'ownPhone', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (504, 475, '申请人', 'applicant', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (505, 475, '申请时间', 'applyTime', NULL, NULL, NULL, 439, 13, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (506, 475, '评估人', 'assessmentPerson', NULL, NULL, NULL, 439, 14, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (507, 476, '评估编号', 'assessmentNo', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (508, 476, '品牌', 'brandName', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (509, 476, '系列', 'seriesName', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (510, 476, '车型', 'carName', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (511, 476, '颜色', 'carColor', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (512, 476, '车牌号码', 'carNo', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (513, 476, '车架号', 'frameNo', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (514, 476, '发动机号', 'engineNo', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (515, 476, '客户姓名', 'ownerName', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (516, 476, '手机号', 'ownPhone', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (517, 477, '评估编号', 'assessmentNo', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (518, 477, '状态', 'assessmentStatus', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (519, 477, '品牌', 'brandName', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (520, 477, '系列', 'seriesName', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (521, 477, '车型', 'carName', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (522, 477, '颜色', 'carColor', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (523, 477, '车牌号码', 'carNo', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (524, 477, '车架号', 'frameNo', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (525, 477, '发动机号', 'engineNo', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (526, 477, '客户姓名', 'ownerName', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (527, 477, '手机号', 'ownPhone', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (528, 477, '车况级别', 'carSituation', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (529, 477, '估计（元）', 'evaluation', NULL, NULL, NULL, 439, 13, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (530, 477, '评估日期', 'assessmentTime', NULL, NULL, NULL, 439, 14, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (531, 477, '估计员', 'assessmentPerson', NULL, NULL, NULL, 439, 15, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (532, 481, '入库状态', 'warehousingStatus', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (533, 481, '操作时间', 'warehousingOperateTime', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (534, 481, '操作人', 'warehousingOperator', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (535, 481, '订单编号', 'assessmentNo', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (536, 481, '品牌', 'brandName', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (537, 481, '系列', 'seriesName', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (538, 481, '车型', 'carName', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (539, 481, '车身颜色', 'carColor', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (540, 481, '车牌号码', 'carNo', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (541, 481, '车架号', 'frameNo', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (542, 481, '发动机号', 'engineNo', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (543, 482, '状态', 'placingStatus', NULL, NULL, NULL, 439, 1, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (544, 482, '操作日期', 'placingOperateTime', NULL, NULL, NULL, 439, 2, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (545, 482, '操作人', 'placingOperator', NULL, NULL, NULL, 439, 3, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (546, 482, '订单编号', 'assessmentNo', NULL, NULL, NULL, 439, 4, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (547, 482, '品牌', 'brandName', NULL, NULL, NULL, 439, 5, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (548, 482, '系列', 'seriesName', NULL, NULL, NULL, 439, 6, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (549, 482, '车型', 'carName', NULL, NULL, NULL, 439, 7, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (550, 482, '车身颜色', 'carColor', NULL, NULL, NULL, 439, 8, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (551, 482, '车牌号码', 'carNo', NULL, NULL, NULL, 439, 9, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (552, 482, '车架号', 'frameNo', NULL, NULL, NULL, 439, 10, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (553, 482, '发动机号', 'engineNo', NULL, NULL, NULL, 439, 11, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (554, 482, '客户姓名', 'ownerName', NULL, NULL, NULL, 439, 12, NULL, NULL, NULL);
INSERT INTO `sys_column_resource` VALUES (555, 482, '手机号', 'ownPhone', NULL, NULL, NULL, 439, 13, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `dict_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典名称',
  `dict_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典编码',
  `dict_sort` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典排序号',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `dict_type` int(11) DEFAULT NULL COMMENT '字典类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10059 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (10000, '字典类型', '0000', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10001, '是否状态', '0001', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10002, '机构性质', '0002', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10003, '机构等级', '0003', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10004, '用户类型', '0004', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10005, '用户登陆类型', '0005', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10006, '用户设备类型', '0006', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10007, '启用禁用', '0007', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10008, '性别', '0008', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10010, '资源文件类型', '0010', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10011, '系统环境', '0011', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10013, '客户资料类型', '0013', NULL, NULL, 10001);
INSERT INTO `sys_dict` VALUES (10014, '库存状态', '0014', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10015, '方案类型', '0015', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10016, '还款方式', '0016', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10017, '抵押方式', '0017', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10018, '方案发布状态', '0018', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10019, '周期类型', '0019', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10020, '账期类型', '0020', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10021, '评估状态', '0021', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10022, '费用项还款方式', '0022', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10023, '意向状态', '0023', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10024, '跟进结果', '0024', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10025, '冲抵策略类型', '0025', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10026, '跟进方式', '0026', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10027, '客户状态', '0027', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10028, '教育程度', '0028', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10029, '婚姻状况', '0029', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10030, '开户状态', '0030', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10031, '订单环节', '0031', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10032, '订单状态', '0032', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10033, '还款状态', '0033', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10034, '户型', '0034', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10035, '收款类型', '0035', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10036, '订单资料类型', '0036', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10037, '押品状态', '0037', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10038, '审核流程', '0038', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10039, '黑白灰名单类型', '0039', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10040, '安装状态', '0040', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10041, '身份证有效期类型', '0041', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10042, '健康状况', '0042', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10043, '居住情况', '0043', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10044, '账户类型', '0044', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10045, 'GPS设备厂商', '0045', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10046, '车况', '0046', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10047, '车辆用途', '0047', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10048, '变速箱形式', '0048', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10049, '驱动形式', '0049', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10050, '购买方式', '0050', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10051, '抵押状态', '0051', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10052, '车辆类型', '0052', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10053, '单位性质', '0053', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10054, '职业类型', '0054', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10055, '所属行业', '0055', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10056, '身份关系', '0056', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10057, '配件状态', '0057', NULL, NULL, 10000);
INSERT INTO `sys_dict` VALUES (10058, '车身部件', '0058', NULL, NULL, 10000);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '字典项ID',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构ID',
  `dict_id` int(32) DEFAULT NULL COMMENT '字典ID',
  `dict_item_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典项名称',
  `dict_item_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典项编码',
  `dict_item_tree_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典项树形编码',
  `dict_item_status` int(11) DEFAULT NULL COMMENT '字典状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10207 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES (10000, NULL, 10000, '系统字典', '0000', '00000000', 0);
INSERT INTO `sys_dict_item` VALUES (10001, NULL, 10000, '机构用户自定义字典', '0001', '00000001', 0);
INSERT INTO `sys_dict_item` VALUES (10002, NULL, 10001, '是', '0002', '00010002', 0);
INSERT INTO `sys_dict_item` VALUES (10003, NULL, 10001, '否', '0003', '00010003', 0);
INSERT INTO `sys_dict_item` VALUES (10004, NULL, 10002, '总公司', '0004', '00020004', 0);
INSERT INTO `sys_dict_item` VALUES (10005, NULL, 10002, '分公司', '0005', '00020005', 0);
INSERT INTO `sys_dict_item` VALUES (10006, NULL, 10002, '部门', '0006', '00020006', 0);
INSERT INTO `sys_dict_item` VALUES (10007, NULL, 10003, '一级机构', '0007', '00030007', 0);
INSERT INTO `sys_dict_item` VALUES (10008, NULL, 10003, '二级机构', '0008', '00030008', 0);
INSERT INTO `sys_dict_item` VALUES (10009, NULL, 10003, '三级机构', '0009', '00030009', 0);
INSERT INTO `sys_dict_item` VALUES (10010, NULL, 10003, '四级机构', '0010', '00030010', 0);
INSERT INTO `sys_dict_item` VALUES (10011, NULL, 10003, '五级机构', '0011', '00030011', 0);
INSERT INTO `sys_dict_item` VALUES (10012, NULL, 10003, '六级机构', '0012', '00030012', 0);
INSERT INTO `sys_dict_item` VALUES (10013, NULL, 10003, '七级机构', '0013', '00030013', 0);
INSERT INTO `sys_dict_item` VALUES (10014, NULL, 10003, '八级机构', '0014', '00030014', 0);
INSERT INTO `sys_dict_item` VALUES (10015, NULL, 10004, '正式用户', '0015', '00040015', 0);
INSERT INTO `sys_dict_item` VALUES (10016, NULL, 10004, '试用用户', '0016', '00040016', 0);
INSERT INTO `sys_dict_item` VALUES (10017, NULL, 10005, '密码登陆', '0017', '00050017', 0);
INSERT INTO `sys_dict_item` VALUES (10018, NULL, 10005, '二维码登陆', '0018', '00050018', 0);
INSERT INTO `sys_dict_item` VALUES (10019, NULL, 10005, '人脸识别', '0019', '00050019', 0);
INSERT INTO `sys_dict_item` VALUES (10020, NULL, 10006, 'PC', '0020', '00060020', 0);
INSERT INTO `sys_dict_item` VALUES (10021, NULL, 10006, 'APP', '0021', '00060021', 0);
INSERT INTO `sys_dict_item` VALUES (10022, NULL, 10007, '启用', '0022', '00070022', 0);
INSERT INTO `sys_dict_item` VALUES (10023, NULL, 10007, '停用', '0023', '00070023', 0);
INSERT INTO `sys_dict_item` VALUES (10024, NULL, 10008, '男', '0024', '00080024', 0);
INSERT INTO `sys_dict_item` VALUES (10025, NULL, 10008, '女', '0025', '00080025', 0);
INSERT INTO `sys_dict_item` VALUES (10028, NULL, 10010, '菜单', '0028', '00100028', 0);
INSERT INTO `sys_dict_item` VALUES (10029, NULL, 10010, '一级目录', '0029', '00100029', 0);
INSERT INTO `sys_dict_item` VALUES (10030, NULL, 10010, '二级目录', '0030', '00100030', 0);
INSERT INTO `sys_dict_item` VALUES (10031, NULL, 10010, '系统', '0031', '00100031', 0);
INSERT INTO `sys_dict_item` VALUES (10032, NULL, 10010, '列表', '0032', '00100032', 0);
INSERT INTO `sys_dict_item` VALUES (10033, NULL, 10010, '列表项', '0033', '00100033', 0);
INSERT INTO `sys_dict_item` VALUES (10034, NULL, 10010, '输入框', '0034', '00100034', 0);
INSERT INTO `sys_dict_item` VALUES (10035, NULL, 10010, '按钮', '0035', '00100035', 0);
INSERT INTO `sys_dict_item` VALUES (10036, NULL, 10002, '集团', '0036', '00020036', 0);
INSERT INTO `sys_dict_item` VALUES (10037, NULL, 10011, 'SAAS', '0037', '00110037', 0);
INSERT INTO `sys_dict_item` VALUES (10038, NULL, 10011, 'PROJECT', '0038', '00110038', 0);
INSERT INTO `sys_dict_item` VALUES (10039, 1, 10013, '身份证', '0039', '00130039', 0);
INSERT INTO `sys_dict_item` VALUES (10040, 1, 10013, '驾驶证', '0040', '00130040', 0);
INSERT INTO `sys_dict_item` VALUES (10041, 1, 10013, '房产证', '0041', '00130041', 0);
INSERT INTO `sys_dict_item` VALUES (10042, 1, 10013, '其他证件', '0042', '00130042', 0);
INSERT INTO `sys_dict_item` VALUES (10046, NULL, 10014, '整备中', '0046', '00140047', 0);
INSERT INTO `sys_dict_item` VALUES (10047, NULL, 10014, '整备完成', '0047', '00140048', 0);
INSERT INTO `sys_dict_item` VALUES (10048, NULL, 10014, '已提车', '0048', '00140123', 0);
INSERT INTO `sys_dict_item` VALUES (10049, NULL, 10015, '融资租赁', '0049', '00150049', 0);
INSERT INTO `sys_dict_item` VALUES (10050, NULL, 10015, '抵押贷款', '0050', '00150050', 0);
INSERT INTO `sys_dict_item` VALUES (10051, NULL, 10016, '按期付息还本', '0051', '00160051', 0);
INSERT INTO `sys_dict_item` VALUES (10052, NULL, 10016, '等额本息', '0052', '00160052', 0);
INSERT INTO `sys_dict_item` VALUES (10053, NULL, 10016, '等额本金', '0053', '00160053', 0);
INSERT INTO `sys_dict_item` VALUES (10054, NULL, 10017, '质押', '0054', '00170054', 0);
INSERT INTO `sys_dict_item` VALUES (10055, NULL, 10017, '抵押', '0055', '00170055', 0);
INSERT INTO `sys_dict_item` VALUES (10056, NULL, 10018, '未发布', '0056', '00180056', 0);
INSERT INTO `sys_dict_item` VALUES (10057, NULL, 10018, '已发布', '0057', '00180057', 0);
INSERT INTO `sys_dict_item` VALUES (10058, NULL, 10019, '月', '0058', '00190058', 0);
INSERT INTO `sys_dict_item` VALUES (10059, NULL, 10020, '正常账期', '0059', '00200059', 0);
INSERT INTO `sys_dict_item` VALUES (10060, NULL, 10020, '固定账期', '0060', '00200060', 0);
INSERT INTO `sys_dict_item` VALUES (10061, NULL, 10021, '已评估', '0061', '00210061', 0);
INSERT INTO `sys_dict_item` VALUES (10062, NULL, 10021, '待评估', '0062', '00210062', 0);
INSERT INTO `sys_dict_item` VALUES (10063, NULL, 10022, '一次性收取', '0063', '00220063', 0);
INSERT INTO `sys_dict_item` VALUES (10064, NULL, 10022, '分期', '0064', '00220064', 0);
INSERT INTO `sys_dict_item` VALUES (10065, NULL, 10023, '未跟进', '0065', '00230065', 0);
INSERT INTO `sys_dict_item` VALUES (10066, NULL, 10023, '已跟进', '0066', '00230066', 0);
INSERT INTO `sys_dict_item` VALUES (10067, NULL, 10023, '已下单', '0067', '00230067', 0);
INSERT INTO `sys_dict_item` VALUES (10068, NULL, 10023, '已完成', '0068', '00230068', 0);
INSERT INTO `sys_dict_item` VALUES (10069, NULL, 10023, '已过期', '0069', '00230069', 0);
INSERT INTO `sys_dict_item` VALUES (10070, NULL, 10024, '继续跟进', '0070', '00240070', 0);
INSERT INTO `sys_dict_item` VALUES (10071, NULL, 10024, '不再跟进', '0071', '00240071', 0);
INSERT INTO `sys_dict_item` VALUES (10072, NULL, 10024, '已预约成功', '0072', '00240072', 0);
INSERT INTO `sys_dict_item` VALUES (10073, NULL, 10026, '电话', '0073', '00260073', 0);
INSERT INTO `sys_dict_item` VALUES (10074, NULL, 10026, '邮件', '0074', '00260074', 0);
INSERT INTO `sys_dict_item` VALUES (10075, NULL, 10025, '提前结清', '0075', '00250075', 0);
INSERT INTO `sys_dict_item` VALUES (10076, NULL, 10025, '分期还款', '0076', '00250076', 0);
INSERT INTO `sys_dict_item` VALUES (10077, NULL, 10027, '意向客户', '0077', '00270077', 0);
INSERT INTO `sys_dict_item` VALUES (10078, NULL, 10027, '正式客户', '0078', '00270078', 0);
INSERT INTO `sys_dict_item` VALUES (10079, NULL, 10027, '历史订单客户', '0079', '00270079', 0);
INSERT INTO `sys_dict_item` VALUES (10080, NULL, 10027, '黑名单客户', '0080', '00270080', 0);
INSERT INTO `sys_dict_item` VALUES (10081, NULL, 10027, '灰名单客户', '0081', '00270081', 0);
INSERT INTO `sys_dict_item` VALUES (10082, NULL, 10027, '白名单客户', '0082', '00270082', 0);
INSERT INTO `sys_dict_item` VALUES (10083, NULL, 10028, '博士及以上', '0083', '00280083', 0);
INSERT INTO `sys_dict_item` VALUES (10084, NULL, 10028, '硕士', '0084', '00280084', 0);
INSERT INTO `sys_dict_item` VALUES (10085, NULL, 10028, '本科', '0085', '00280085', 0);
INSERT INTO `sys_dict_item` VALUES (10086, NULL, 10028, '大专', '0086', '00280086', 0);
INSERT INTO `sys_dict_item` VALUES (10087, NULL, 10028, '高中', '0087', '00280087', 0);
INSERT INTO `sys_dict_item` VALUES (10088, NULL, 10028, '中专/技校', '0088', '00280088', 0);
INSERT INTO `sys_dict_item` VALUES (10089, NULL, 10029, '未婚', '0089', '00290089', 0);
INSERT INTO `sys_dict_item` VALUES (10090, NULL, 10029, '已婚', '0090', '00290090', 0);
INSERT INTO `sys_dict_item` VALUES (10091, NULL, 10029, '离婚', '0091', '00290091', 0);
INSERT INTO `sys_dict_item` VALUES (10092, NULL, 10029, '丧偶', '0092', '00290092', 0);
INSERT INTO `sys_dict_item` VALUES (10093, NULL, 10030, '已开户', '0093', '00300093', 0);
INSERT INTO `sys_dict_item` VALUES (10094, NULL, 10030, '未开户', '0094', '00300094', 0);
INSERT INTO `sys_dict_item` VALUES (10095, NULL, 10031, '进件申请', '0095', '00310095', 0);
INSERT INTO `sys_dict_item` VALUES (10096, NULL, 10031, '审核', '0096', '00310096', 0);
INSERT INTO `sys_dict_item` VALUES (10097, NULL, 10031, '收款', '0097', '00310097', 0);
INSERT INTO `sys_dict_item` VALUES (10098, NULL, 10031, '放款', '0098', '00310098', 0);
INSERT INTO `sys_dict_item` VALUES (10099, NULL, 10031, '还款', '0099', '00310099', 0);
INSERT INTO `sys_dict_item` VALUES (10100, NULL, 10031, '补填资料', '0100', '00310100', 0);
INSERT INTO `sys_dict_item` VALUES (10101, NULL, 10031, '供应商放款', '0101', '00310101', 0);
INSERT INTO `sys_dict_item` VALUES (10102, NULL, 10031, '提前结清', '0102', '00310102', 0);
INSERT INTO `sys_dict_item` VALUES (10103, NULL, 10031, '提前收回', '0103', '00310103', 0);
INSERT INTO `sys_dict_item` VALUES (10104, NULL, 10031, '减免', '0104', '00310104', 0);
INSERT INTO `sys_dict_item` VALUES (10105, NULL, 10031, '冻结', '0105', '00310105', 0);
INSERT INTO `sys_dict_item` VALUES (10106, NULL, 10031, '结案', '0106', '00310106', 0);
INSERT INTO `sys_dict_item` VALUES (10107, NULL, 10032, '待补填资料', '0107', '00320107', 0);
INSERT INTO `sys_dict_item` VALUES (10108, NULL, 10032, '待审核', '0108', '00320108', 0);
INSERT INTO `sys_dict_item` VALUES (10109, NULL, 10032, '待收款', '0109', '00320109', 0);
INSERT INTO `sys_dict_item` VALUES (10110, NULL, 10032, '待提车', '0110', '00320110', 0);
INSERT INTO `sys_dict_item` VALUES (10111, NULL, 10032, '还款中', '0111', '00320111', 0);
INSERT INTO `sys_dict_item` VALUES (10112, NULL, 10032, '提前结清', '0112', '00320112', 0);
INSERT INTO `sys_dict_item` VALUES (10113, NULL, 10032, '提前回收', '0113', '00320113', 0);
INSERT INTO `sys_dict_item` VALUES (10114, NULL, 10032, '逾期还款', '0114', '00320114', 0);
INSERT INTO `sys_dict_item` VALUES (10115, NULL, 10032, '待结案', '0115', '00320115', 0);
INSERT INTO `sys_dict_item` VALUES (10116, NULL, 10032, '已结案', '0116', '00320116', 0);
INSERT INTO `sys_dict_item` VALUES (10117, NULL, 10033, '待还', '0117', '00330117', 0);
INSERT INTO `sys_dict_item` VALUES (10118, NULL, 10033, '结清', '0118', '00330118', 0);
INSERT INTO `sys_dict_item` VALUES (10119, NULL, 10033, '逾期', '0119', '00330119', 0);
INSERT INTO `sys_dict_item` VALUES (10120, NULL, 10033, '部分还款', '0120', '00330120', 0);
INSERT INTO `sys_dict_item` VALUES (10121, NULL, 10033, '提前结清', '0121', '00330121', 0);
INSERT INTO `sys_dict_item` VALUES (10122, NULL, 10033, '提前收回', '0122', '00330122', 0);
INSERT INTO `sys_dict_item` VALUES (10123, NULL, 10014, '待采购', '0123', '00140046', 0);
INSERT INTO `sys_dict_item` VALUES (10124, NULL, 10034, '一居室', '0124', '00340124', 0);
INSERT INTO `sys_dict_item` VALUES (10125, NULL, 10034, '俩室一厅', '0125', '00340125', 0);
INSERT INTO `sys_dict_item` VALUES (10126, NULL, 10034, '三室一厅', '0126', '00340126', 0);
INSERT INTO `sys_dict_item` VALUES (10127, NULL, 10034, '三室俩厅', '0127', '00340127', 0);
INSERT INTO `sys_dict_item` VALUES (10128, NULL, 10034, '四室一厅', '0128', '00340128', 0);
INSERT INTO `sys_dict_item` VALUES (10129, NULL, 10034, '四室俩厅', '0129', '00340129', 0);
INSERT INTO `sys_dict_item` VALUES (10130, NULL, 10034, '别墅', '0130', '00340130', 0);
INSERT INTO `sys_dict_item` VALUES (10131, NULL, 10026, '到店面谈', '0131', '00260131', 0);
INSERT INTO `sys_dict_item` VALUES (10132, NULL, 10035, '还款', '0132', '00350132', 0);
INSERT INTO `sys_dict_item` VALUES (10133, NULL, 10035, '提前结清', '0133', '00350133', 0);
INSERT INTO `sys_dict_item` VALUES (10134, NULL, 10035, '提前收回', '0134', '00350134', 0);
INSERT INTO `sys_dict_item` VALUES (10135, NULL, 10035, '首付款', '0135', '00350135', 0);
INSERT INTO `sys_dict_item` VALUES (10136, NULL, 10036, '合同', '0136', '00350136', 0);
INSERT INTO `sys_dict_item` VALUES (10137, NULL, 10036, '协议', '0137', '00360137', 0);
INSERT INTO `sys_dict_item` VALUES (10138, NULL, 10036, '其他', '0138', '00360138', 0);
INSERT INTO `sys_dict_item` VALUES (10139, NULL, 10037, '未入库', '0139', '00370139', 0);
INSERT INTO `sys_dict_item` VALUES (10140, NULL, 10037, '已入库', '0140', '00370140', 0);
INSERT INTO `sys_dict_item` VALUES (10141, NULL, 10037, '已出库', '0141', '00370141', 0);
INSERT INTO `sys_dict_item` VALUES (10142, NULL, 10038, 'process', '0142', '00380142', 0);
INSERT INTO `sys_dict_item` VALUES (10143, NULL, 10031, '待退款', '0143', '00310143', 0);
INSERT INTO `sys_dict_item` VALUES (10144, NULL, 10032, '待放款', '0144', '00320144', 0);
INSERT INTO `sys_dict_item` VALUES (10145, NULL, 10032, '拒件', '0145', '00320145', 0);
INSERT INTO `sys_dict_item` VALUES (10146, NULL, 10039, '黑名单', '0146', '00390146', 0);
INSERT INTO `sys_dict_item` VALUES (10147, NULL, 10039, '白名单', '0147', '00390147', 0);
INSERT INTO `sys_dict_item` VALUES (10148, NULL, 10039, '灰名单', '0148', '00390148', 0);
INSERT INTO `sys_dict_item` VALUES (10149, NULL, 10031, '客户放款', '0149', '00390149', 0);
INSERT INTO `sys_dict_item` VALUES (10150, NULL, 10040, '已安装', '0150', '00400150', 0);
INSERT INTO `sys_dict_item` VALUES (10151, NULL, 10040, '未安装', '0151', '00400151', 0);
INSERT INTO `sys_dict_item` VALUES (10152, NULL, 10041, '临时', '0152', '00410152', 0);
INSERT INTO `sys_dict_item` VALUES (10153, NULL, 10041, '长期', '0153', '00410153', 0);
INSERT INTO `sys_dict_item` VALUES (10154, NULL, 10042, '健康', '0154', '00420154', 0);
INSERT INTO `sys_dict_item` VALUES (10155, NULL, 10042, '一般', '0155', '00420155', 0);
INSERT INTO `sys_dict_item` VALUES (10156, NULL, 10043, '全款', '0156', '00430156', 0);
INSERT INTO `sys_dict_item` VALUES (10157, NULL, 10043, '按揭', '0157', '00430157', 0);
INSERT INTO `sys_dict_item` VALUES (10158, NULL, 10044, '一类卡', '0158', '00440158', 0);
INSERT INTO `sys_dict_item` VALUES (10159, NULL, 10044, '二类卡', '0159', '00440159', 0);
INSERT INTO `sys_dict_item` VALUES (10160, NULL, 10044, '存折', '0160', '00440160', 0);
INSERT INTO `sys_dict_item` VALUES (10161, NULL, 10045, '智高醒', '0161', '00450161', 0);
INSERT INTO `sys_dict_item` VALUES (10162, NULL, 10045, '天易', '0162', '00450162', 0);
INSERT INTO `sys_dict_item` VALUES (10163, NULL, 10046, '1级/综合车况较好', '0163', '00460163', 0);
INSERT INTO `sys_dict_item` VALUES (10164, NULL, 10046, '2级/综合车况一般', '0164', '00460164', 0);
INSERT INTO `sys_dict_item` VALUES (10165, NULL, 10046, '3级/综合车况较差', '0165', '00460165', 0);
INSERT INTO `sys_dict_item` VALUES (10166, NULL, 10046, '4级/综合车况很差', '0166', '00460165', 0);
INSERT INTO `sys_dict_item` VALUES (10167, NULL, 10047, '自用', '0167', '00470167', 0);
INSERT INTO `sys_dict_item` VALUES (10168, NULL, 10047, '运营', '0168', '00470168', 0);
INSERT INTO `sys_dict_item` VALUES (10169, NULL, 10047, '政府用车', '0169', '00470169', 0);
INSERT INTO `sys_dict_item` VALUES (10170, NULL, 10048, 'MT', '0170', '00480170', 0);
INSERT INTO `sys_dict_item` VALUES (10171, NULL, 10048, 'AT', '0171', '00480171', 0);
INSERT INTO `sys_dict_item` VALUES (10172, NULL, 10048, 'CVT', '0172', '00480172', 0);
INSERT INTO `sys_dict_item` VALUES (10173, NULL, 10048, 'DCT', '0173', '00480173', 0);
INSERT INTO `sys_dict_item` VALUES (10174, NULL, 10049, '前驱', '0174', '00490174', 0);
INSERT INTO `sys_dict_item` VALUES (10175, NULL, 10049, '后驱', '0175', '00490175', 0);
INSERT INTO `sys_dict_item` VALUES (10176, NULL, 10049, '全时四驱', '0176', '00490176', 0);
INSERT INTO `sys_dict_item` VALUES (10177, NULL, 10049, '适时四驱', '0177', '00490177', 0);
INSERT INTO `sys_dict_item` VALUES (10178, NULL, 10050, '全款', '0178', '00500178', 0);
INSERT INTO `sys_dict_item` VALUES (10179, NULL, 10050, '分期', '0179', '00500179', 0);
INSERT INTO `sys_dict_item` VALUES (10180, NULL, 10051, '已入库', '0180', '00510180', 0);
INSERT INTO `sys_dict_item` VALUES (10181, NULL, 10051, '未入库', '0181', '00510181', 0);
INSERT INTO `sys_dict_item` VALUES (10182, NULL, 10051, '已出库', '0182', '00510182', 0);
INSERT INTO `sys_dict_item` VALUES (10183, NULL, 10052, '轿车', '0183', '00520183', 0);
INSERT INTO `sys_dict_item` VALUES (10184, NULL, 10052, '客车', '0184', '00520184', 0);
INSERT INTO `sys_dict_item` VALUES (10185, NULL, 10052, '货车', '0185', '00520185', 0);
INSERT INTO `sys_dict_item` VALUES (10186, NULL, 10053, '国企', '0186', '00530186', 0);
INSERT INTO `sys_dict_item` VALUES (10187, NULL, 10053, '个体', '0187', '00530187', 0);
INSERT INTO `sys_dict_item` VALUES (10188, NULL, 10053, '机关事业', '0188', '00530188', 0);
INSERT INTO `sys_dict_item` VALUES (10189, NULL, 10053, '外资', '0189', '00530189', 0);
INSERT INTO `sys_dict_item` VALUES (10190, NULL, 10053, '合资', '0190', '00530190', 0);
INSERT INTO `sys_dict_item` VALUES (10191, NULL, 10053, '私营有限公司', '0191', '00530191', 0);
INSERT INTO `sys_dict_item` VALUES (10192, NULL, 10054, '工薪者', '0192', '00540192', 0);
INSERT INTO `sys_dict_item` VALUES (10193, NULL, 10054, '企业经营者', '0193', '00540193', 0);
INSERT INTO `sys_dict_item` VALUES (10194, NULL, 10055, '经济行业', '0194', '00550194', 0);
INSERT INTO `sys_dict_item` VALUES (10195, NULL, 10055, '建筑业', '0195', '00550195', 0);
INSERT INTO `sys_dict_item` VALUES (10196, NULL, 10055, '金融业', '0196', '00550196', 0);
INSERT INTO `sys_dict_item` VALUES (10197, NULL, 10055, '其他行业', '0197', '00550197', 0);
INSERT INTO `sys_dict_item` VALUES (10198, NULL, 10056, '父母', '0198', '00560198', 0);
INSERT INTO `sys_dict_item` VALUES (10199, NULL, 10056, '子女', '0199', '00560199', 0);
INSERT INTO `sys_dict_item` VALUES (10200, NULL, 10056, '配偶', '0200', '00560200', 0);
INSERT INTO `sys_dict_item` VALUES (10201, NULL, 10056, '朋友', '0201', '00560201', 0);
INSERT INTO `sys_dict_item` VALUES (10202, NULL, 10057, '正常', '0202', '00570202', 0);
INSERT INTO `sys_dict_item` VALUES (10203, NULL, 10057, '异常', '0203', '00570203', 0);
INSERT INTO `sys_dict_item` VALUES (10204, NULL, 10058, '外观', '0204', '00580204', 0);
INSERT INTO `sys_dict_item` VALUES (10205, NULL, 10058, '内饰', '0205', '00580205', 0);
INSERT INTO `sys_dict_item` VALUES (10206, NULL, 10058, '机舱/底座', '0206', '00580206', 0);

-- ----------------------------
-- Table structure for sys_logs
-- ----------------------------
DROP TABLE IF EXISTS `sys_logs`;
CREATE TABLE `sys_logs`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '操作ID',
  `client_ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户ip',
  `operator` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作人',
  `excute_method` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '执行方法',
  `excute_params` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '执行参数',
  `excute_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '执行类型',
  `log_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '日志描述',
  `operate_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  `excute_time` bigint(32) DEFAULT NULL COMMENT '执行时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 148 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logs
-- ----------------------------
INSERT INTO `sys_logs` VALUES (1, '192.168.22.143', '用户获取失败', 'com.fintecher.manage.web.SysDictController.getAll', '[]', 'GET', '查询系统数据字典项', '2018-07-17 14:50:38', 70);
INSERT INTO `sys_logs` VALUES (2, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysOrgController.findAllOrganizationByAuth', '[1_2a8d6ff13fed437bbd43dd6829bf5dc4]', 'GET', '获取数据权限下所有的组织机构', '2018-07-17 15:12:44', 276);
INSERT INTO `sys_logs` VALUES (3, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.getCarAllInfo', '[1_2a8d6ff13fed437bbd43dd6829bf5dc4]', 'GET', '车辆品牌列表', '2018-07-17 15:12:44', 242);
INSERT INTO `sys_logs` VALUES (4, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysDictController.getAll', '[]', 'GET', '查询系统数据字典项', '2018-07-17 15:15:02', 17);
INSERT INTO `sys_logs` VALUES (5, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.LoginController.getUserInfoByToken', '[1_2a8d6ff13fed437bbd43dd6829bf5dc4]', 'GET', '通过token获取用户信息', '2018-07-17 15:15:02', 43);
INSERT INTO `sys_logs` VALUES (6, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysOrgController.findAllOrganizationByAuth', '[1_2a8d6ff13fed437bbd43dd6829bf5dc4]', 'GET', '获取数据权限下所有的组织机构', '2018-07-17 15:15:02', 32);
INSERT INTO `sys_logs` VALUES (7, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.getCarAllInfo', '[1_2a8d6ff13fed437bbd43dd6829bf5dc4]', 'GET', '车辆品牌列表', '2018-07-17 15:15:02', 11);
INSERT INTO `sys_logs` VALUES (8, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysUserController.findUserByOrgAuth', '[1_2a8d6ff13fed437bbd43dd6829bf5dc4, com.fintecher.manage.vo.PageParam@84251c, SearchUserParams(orgId=null, userName=null, realName=null, userStatus=null)]', 'GET', '根据组织机构分页查询数据权限下的用户', '2018-07-17 15:15:07', 108);
INSERT INTO `sys_logs` VALUES (9, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findPotentialCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@5b373f, 1_2a8d6ff13fed437bbd43dd6829bf5dc4]', 'GET', '意向客户列表', '2018-07-17 15:15:15', 122);
INSERT INTO `sys_logs` VALUES (10, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findFormalCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@136615f, 1_2a8d6ff13fed437bbd43dd6829bf5dc4]', 'GET', '正式客户列表', '2018-07-17 15:15:17', 31);
INSERT INTO `sys_logs` VALUES (11, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findHistoryCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@1e27e4c, 1_2a8d6ff13fed437bbd43dd6829bf5dc4]', 'GET', '历史客户列表', '2018-07-17 15:15:18', 31);
INSERT INTO `sys_logs` VALUES (12, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findCustomerList', '[null, null, null, null, com.fintecher.manage.vo.PageParam@13c8580, 1_2a8d6ff13fed437bbd43dd6829bf5dc4]', 'GET', '补填资料客户列表', '2018-07-17 15:15:18', 45);
INSERT INTO `sys_logs` VALUES (13, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.FinancialQueryController.findRepayOrderUnpaidList', '[1_2a8d6ff13fed437bbd43dd6829bf5dc4, com.fintecher.manage.vo.PageParam@11b2723, SearchOrderParams(customerName=null, orderNo=null, idCard=null, customerPhone=null)]', 'GET', '查询待放款的订单', '2018-07-17 15:15:29', 199);
INSERT INTO `sys_logs` VALUES (14, '0:0:0:0:0:0:0:1', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.getCarAllInfo', '[1_4a852ebc9d56459e96101e248672cde1]', 'GET', '车辆品牌列表', '2018-07-17 21:26:06', 153);
INSERT INTO `sys_logs` VALUES (15, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.getCarAllInfo', '[1_cba9bd7d476145ee871a286e16691d92]', 'GET', '车辆品牌列表', '2018-07-18 09:15:38', 587);
INSERT INTO `sys_logs` VALUES (16, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysOrgController.findAllOrganizationByAuth', '[1_92a8884153d346889f3e90e24f412ac6]', 'GET', '获取数据权限下所有的组织机构', '2018-07-18 09:15:38', 1075);
INSERT INTO `sys_logs` VALUES (17, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.getCarAllInfo', '[1_cba9bd7d476145ee871a286e16691d92]', 'GET', '车辆品牌列表', '2018-07-18 09:15:40', 173);
INSERT INTO `sys_logs` VALUES (18, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysOrgController.findAllOrganizationByAuth', '[1_cba9bd7d476145ee871a286e16691d92]', 'GET', '获取数据权限下所有的组织机构', '2018-07-18 09:15:41', 471);
INSERT INTO `sys_logs` VALUES (19, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findPotentialCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@13df314, 1_cba9bd7d476145ee871a286e16691d92]', 'GET', '意向客户列表', '2018-07-18 09:16:57', 898);
INSERT INTO `sys_logs` VALUES (20, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.addBasicCustomer', '[BasicCustomerParams(customerId=null, customerName=张亚军, customerStatus=null, customerSex=10024, birthTime=null, qq=null, wechat=null, facebook=null, email=null, education=10084, school=null, marital=10089, idCard=142332199404023614, customerIssuer=null, idCardAddress=null, idCardAddressDetail=null, idCardValidityPeriodType=null, idCardValidityPeriodSection=null, localHomeAddr=null, localHomeAddrDetail=null, localHomePhone=null, houseProspecting=null, postalCode=null, nation=null, healthStatus=null, homeStatus=null, messageAddr=null, customerPhone=17600562066, intentionLevel=4.0, intentionType=null), 1_cba9bd7d476145ee871a286e16691d92]', 'POST', '添加意向客户', '2018-07-18 09:17:51', 737);
INSERT INTO `sys_logs` VALUES (21, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findPotentialCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@1310619, 1_cba9bd7d476145ee871a286e16691d92]', 'GET', '意向客户列表', '2018-07-18 09:17:51', 176);
INSERT INTO `sys_logs` VALUES (22, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findCustomerList', '[null, null, null, null, com.fintecher.manage.vo.PageParam@526d6a, 1_cba9bd7d476145ee871a286e16691d92]', 'GET', '补填资料客户列表', '2018-07-18 09:18:01', 36);
INSERT INTO `sys_logs` VALUES (23, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.findCustomerById', '[1, 1_cba9bd7d476145ee871a286e16691d92]', 'GET', '查找客户', '2018-07-18 09:18:03', 72);
INSERT INTO `sys_logs` VALUES (24, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.BasicCustomerAssessmentCarController.getBasicCustomerCarList', '[1, 1_cba9bd7d476145ee871a286e16691d92]', 'GET', '客户车产列表', '2018-07-18 09:18:06', 227);
INSERT INTO `sys_logs` VALUES (25, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findNotBlackCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@ae3071, 1_cba9bd7d476145ee871a286e16691d92]', 'GET', '非黑名单客户列表', '2018-07-18 09:19:18', 48);
INSERT INTO `sys_logs` VALUES (26, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.queryCarModel', '[1_cba9bd7d476145ee871a286e16691d92, com.fintecher.manage.vo.PageParam@15f531f, SearchCarParams(brandName=null, seriesName=null, modelName=null)]', 'GET', '分页获取当前登陆人组织机构下的车型', '2018-07-18 09:19:18', 488);
INSERT INTO `sys_logs` VALUES (27, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.WorkFlowApprovalController.queryAllWaitApprovalByAuth', '[1_cba9bd7d476145ee871a286e16691d92, com.fintecher.manage.vo.PageParam@10a74fa, SearchWaitApproveParams(customerName=null, idCard=null, customerPhone=null, orderNo=null)]', 'GET', '分页查询所有数据权限下待审核的订单', '2018-07-18 09:19:53', 498);
INSERT INTO `sys_logs` VALUES (28, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.WorkFlowApprovalController.queryAllWaitApprovalByAuth', '[1_cba9bd7d476145ee871a286e16691d92, com.fintecher.manage.vo.PageParam@1679ad6, SearchWaitApproveParams(customerName=null, idCard=null, customerPhone=null, orderNo=null)]', 'GET', '分页查询所有数据权限下待审核的订单', '2018-07-18 09:19:56', 99);
INSERT INTO `sys_logs` VALUES (29, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.WorkFlowApprovalController.queryWaitApproval', '[1_cba9bd7d476145ee871a286e16691d92, com.fintecher.manage.vo.PageParam@1aa2c4d, SearchWaitApproveParams(customerName=null, idCard=null, customerPhone=null, orderNo=null)]', 'GET', '分页查询所有待当前登录人审核的订单', '2018-07-18 09:20:15', 21185);
INSERT INTO `sys_logs` VALUES (30, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysModuleController.getRoleMenu', '[1_cba9bd7d476145ee871a286e16691d92]', 'GET', '获取角色下的所有的菜单', '2018-07-18 09:20:24', 166);
INSERT INTO `sys_logs` VALUES (31, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysDictController.getAllUserDictType', '[1_cba9bd7d476145ee871a286e16691d92]', 'GET', '查询用户自定义数据字典类型', '2018-07-18 09:21:36', 144);
INSERT INTO `sys_logs` VALUES (32, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysDictController.getDataDictByTypeCode', '[10013, null, 1_cba9bd7d476145ee871a286e16691d92]', 'GET', '查询数据字典类型对应的数据字典项', '2018-07-18 09:21:36', 111);
INSERT INTO `sys_logs` VALUES (33, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysDictController.getAllSysDictType', '[1_cba9bd7d476145ee871a286e16691d92]', 'GET', '查询系统数据字典类型', '2018-07-18 09:21:42', 68);
INSERT INTO `sys_logs` VALUES (34, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysDictController.getSysItemBySysDictType', '[10000, 1_cba9bd7d476145ee871a286e16691d92]', 'GET', '根据数据字典类型查询数据字典项', '2018-07-18 09:21:42', 43);
INSERT INTO `sys_logs` VALUES (35, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysDictController.getSysItemBySysDictType', '[10050, 1_cba9bd7d476145ee871a286e16691d92]', 'GET', '根据数据字典类型查询数据字典项', '2018-07-18 09:21:54', 23);
INSERT INTO `sys_logs` VALUES (36, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysRoleController.findAllRoleByAuth', '[1_cba9bd7d476145ee871a286e16691d92, com.fintecher.manage.vo.PageParam@f195fa, SearchRoleParams(roleName=null, roleStatus=null)]', 'GET', '多条件查询-获取数据权限内的所有角色', '2018-07-18 09:21:59', 75);
INSERT INTO `sys_logs` VALUES (37, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysModuleController.findChildMenu', '[1_cba9bd7d476145ee871a286e16691d92, com.fintecher.manage.vo.PageParam@1aceb05, 1]', 'GET', '获取菜单下的子菜单', '2018-07-18 09:22:05', 77);
INSERT INTO `sys_logs` VALUES (38, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysUserController.findUserByOrgAuth', '[1_cba9bd7d476145ee871a286e16691d92, com.fintecher.manage.vo.PageParam@434320, SearchUserParams(orgId=null, userName=null, realName=null, userStatus=null)]', 'GET', '根据组织机构分页查询数据权限下的用户', '2018-07-18 09:22:12', 110);
INSERT INTO `sys_logs` VALUES (39, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.BasicExpenseController.findBasicExpensePageByOrg', '[com.fintecher.manage.vo.PageParam@161f0c1, 1_cba9bd7d476145ee871a286e16691d92]', 'GET', '分页查询获取当前用户所属机构下的费用项', '2018-07-18 09:26:55', 184);
INSERT INTO `sys_logs` VALUES (40, '0:0:0:0:0:0:0:1', '超级管理员', 'com.fintecher.manage.web.SysUserController.findUserByOrgAuth', '[1_dc1027d4de1c4f379d7e951fea011a48, com.fintecher.manage.vo.PageParam@b70628, SearchUserParams(orgId=null, userName=null, realName=null, userStatus=null)]', 'GET', '根据组织机构分页查询数据权限下的用户', '2018-07-18 13:42:36', 1931);
INSERT INTO `sys_logs` VALUES (41, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysOrgController.findAllOrganizationByAuth', '[1_2b292c6cf8084b43ae3181396f7ed463]', 'GET', '获取数据权限下所有的组织机构', '2018-07-18 13:45:07', 220);
INSERT INTO `sys_logs` VALUES (42, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.getCarAllInfo', '[1_2b292c6cf8084b43ae3181396f7ed463]', 'GET', '车辆品牌列表', '2018-07-18 13:45:07', 210);
INSERT INTO `sys_logs` VALUES (43, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysUserController.findUserByOrgAuth', '[1_2b292c6cf8084b43ae3181396f7ed463, com.fintecher.manage.vo.PageParam@8d69cc, SearchUserParams(orgId=null, userName=null, realName=null, userStatus=null)]', 'GET', '根据组织机构分页查询数据权限下的用户', '2018-07-18 13:45:11', 43);
INSERT INTO `sys_logs` VALUES (44, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysRoleController.findAllRoleByAuth', '[1_2b292c6cf8084b43ae3181396f7ed463, com.fintecher.manage.vo.PageParam@137b83f, SearchRoleParams(roleName=null, roleStatus=null)]', 'GET', '多条件查询-获取数据权限内的所有角色', '2018-07-18 13:45:46', 75);
INSERT INTO `sys_logs` VALUES (45, '192.168.22.110', '超级管理员', 'com.fintecher.manage.web.SysDictController.getAll', '[]', 'GET', '查询系统数据字典项', '2018-07-18 16:30:21', 6154);
INSERT INTO `sys_logs` VALUES (46, '192.168.22.110', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.getCarAllInfo', '[1_a4f4734c27ba4a29b9f7affea814cc0c]', 'GET', '车辆品牌列表', '2018-07-18 16:30:24', 402);
INSERT INTO `sys_logs` VALUES (47, '192.168.22.110', '超级管理员', 'com.fintecher.manage.web.SysOrgController.findAllOrganizationByAuth', '[1_fdb608d8c4ef4593a8ed8311421d39b8]', 'GET', '获取数据权限下所有的组织机构', '2018-07-18 16:30:24', 559);
INSERT INTO `sys_logs` VALUES (48, '192.168.22.110', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.getCarAllInfo', '[1_a4f4734c27ba4a29b9f7affea814cc0c]', 'GET', '车辆品牌列表', '2018-07-18 16:30:24', 268);
INSERT INTO `sys_logs` VALUES (49, '192.168.22.110', '超级管理员', 'com.fintecher.manage.web.SysOrgController.findAllOrganizationByAuth', '[1_a4f4734c27ba4a29b9f7affea814cc0c]', 'GET', '获取数据权限下所有的组织机构', '2018-07-18 16:30:25', 311);
INSERT INTO `sys_logs` VALUES (50, '192.168.22.110', '超级管理员', 'com.fintecher.manage.web.SysUserController.findUserByOrgAuth', '[1_a4f4734c27ba4a29b9f7affea814cc0c, com.fintecher.manage.vo.PageParam@be3624, SearchUserParams(orgId=null, userName=null, realName=null, userStatus=null)]', 'GET', '根据组织机构分页查询数据权限下的用户', '2018-07-18 16:30:34', 262);
INSERT INTO `sys_logs` VALUES (51, '192.168.22.143', '超级管理员', 'com.fintecher.manage.web.SysDictController.getAll', '[]', 'GET', '查询系统数据字典项', '2018-07-18 16:31:02', 36);
INSERT INTO `sys_logs` VALUES (52, '0:0:0:0:0:0:0:1', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.getCarAllInfo', '[1_da82f9e5e1304088bf75ee71fabbdfdb]', 'GET', '车辆品牌列表', '2018-07-18 17:14:55', 123);
INSERT INTO `sys_logs` VALUES (53, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.SysOrgController.findAllOrganizationByAuth', '[1_10cca96a2d914fabad59026e11603444]', 'GET', '获取数据权限下所有的组织机构', '2018-07-18 18:01:50', 313);
INSERT INTO `sys_logs` VALUES (54, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.getCarAllInfo', '[1_10cca96a2d914fabad59026e11603444]', 'GET', '车辆品牌列表', '2018-07-18 18:01:50', 358);
INSERT INTO `sys_logs` VALUES (55, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.SysUserController.findUserByOrgAuth', '[1_10cca96a2d914fabad59026e11603444, com.fintecher.manage.vo.PageParam@3030d131, SearchUserParams(orgId=null, userName=null, realName=null, userStatus=null)]', 'GET', '根据组织机构分页查询数据权限下的用户', '2018-07-18 18:01:59', 272);
INSERT INTO `sys_logs` VALUES (56, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.SysRoleController.findAllRoleByAuth', '[1_10cca96a2d914fabad59026e11603444, com.fintecher.manage.vo.PageParam@77a65f7e, SearchRoleParams(roleName=null, roleStatus=null)]', 'GET', '多条件查询-获取数据权限内的所有角色', '2018-07-18 18:02:01', 50);
INSERT INTO `sys_logs` VALUES (57, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerAssessmentCarController.findCustomerAssessmentList', '[com.fintecher.manage.vo.PageParam@61ae9ba4, 1_10cca96a2d914fabad59026e11603444, null, null, null]', 'GET', '客户评估列表', '2018-07-18 18:02:14', 111);
INSERT INTO `sys_logs` VALUES (58, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.FinanceDetainController.getFinanceStorageList', '[com.fintecher.manage.vo.PageParam@1a4130c, 1_10cca96a2d914fabad59026e11603444, null, null, null]', 'GET', '出入库记录列表', '2018-07-18 18:02:16', 1750);
INSERT INTO `sys_logs` VALUES (59, '0:0:0:0:0:0:0:1', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.queryCarModel', '[1_da82f9e5e1304088bf75ee71fabbdfdb, com.fintecher.manage.vo.PageParam@7b7d28a3, SearchCarParams(brandName=null, seriesName=null, modelName=null)]', 'GET', '分页获取当前登陆人组织机构下的车型', '2018-07-19 11:09:09', 149);
INSERT INTO `sys_logs` VALUES (60, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.getCarAllInfo', '[1_705933389af84efc85709a412a3a9bdb]', 'GET', '车辆品牌列表', '2018-07-19 11:09:57', 894);
INSERT INTO `sys_logs` VALUES (61, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.SysOrgController.findAllOrganizationByAuth', '[1_705933389af84efc85709a412a3a9bdb]', 'GET', '获取数据权限下所有的组织机构', '2018-07-19 11:09:57', 1175);
INSERT INTO `sys_logs` VALUES (62, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findPotentialCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@21e60322, 1_705933389af84efc85709a412a3a9bdb]', 'GET', '意向客户列表', '2018-07-19 11:10:08', 3181);
INSERT INTO `sys_logs` VALUES (63, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.findCustomerById', '[1, 1_705933389af84efc85709a412a3a9bdb]', 'GET', '查找客户', '2018-07-19 11:10:21', 305);
INSERT INTO `sys_logs` VALUES (64, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.findCustomerIntentionList', '[1, 1_705933389af84efc85709a412a3a9bdb]', 'GET', '根据客户id查找意向记录列表', '2018-07-19 11:10:43', 225);
INSERT INTO `sys_logs` VALUES (65, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findCustomerBlackListRecord', '[1, 1_705933389af84efc85709a412a3a9bdb]', 'GET', '获取客户黑白灰名单记录', '2018-07-19 11:10:58', 436);
INSERT INTO `sys_logs` VALUES (66, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomContactController.findCustomerContactList', '[1, 1_705933389af84efc85709a412a3a9bdb]', 'GET', '客户联系人信息列表', '2018-07-19 11:11:11', 328);
INSERT INTO `sys_logs` VALUES (67, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.getCustomerBankInfo', '[1, 1_705933389af84efc85709a412a3a9bdb]', 'GET', '银行卡信息查询', '2018-07-19 11:11:13', 252);
INSERT INTO `sys_logs` VALUES (68, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findCustomerBlackListRecord', '[1, 1_705933389af84efc85709a412a3a9bdb]', 'GET', '获取客户黑白灰名单记录', '2018-07-19 11:11:15', 515);
INSERT INTO `sys_logs` VALUES (69, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.getCustomerBankInfo', '[1, 1_705933389af84efc85709a412a3a9bdb]', 'GET', '银行卡信息查询', '2018-07-19 11:11:16', 65);
INSERT INTO `sys_logs` VALUES (70, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.SysUserController.findUserByOrgAuth', '[1_705933389af84efc85709a412a3a9bdb, com.fintecher.manage.vo.PageParam@25b13f89, SearchUserParams(orgId=null, userName=null, realName=null, userStatus=null)]', 'GET', '根据组织机构分页查询数据权限下的用户', '2018-07-19 11:12:11', 79);
INSERT INTO `sys_logs` VALUES (71, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.SysUserController.findUserRole', '[1_705933389af84efc85709a412a3a9bdb, 1, null, com.fintecher.manage.vo.PageParam@3f6c0295]', 'GET', '分页获取数据权限下的所有的启用角色，并标出该用户已选中的角色', '2018-07-19 11:20:09', 563);
INSERT INTO `sys_logs` VALUES (72, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.SysModuleController.getRoleMenu', '[1_705933389af84efc85709a412a3a9bdb]', 'GET', '获取角色下的所有的菜单', '2018-07-19 11:20:18', 380);
INSERT INTO `sys_logs` VALUES (73, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.SysModuleController.findChildMenu', '[1_705933389af84efc85709a412a3a9bdb, com.fintecher.manage.vo.PageParam@3319600e, 117]', 'GET', '获取菜单下的子菜单', '2018-07-19 11:20:22', 325);
INSERT INTO `sys_logs` VALUES (74, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.SysModuleController.findChildMenu', '[1_705933389af84efc85709a412a3a9bdb, com.fintecher.manage.vo.PageParam@2eeebc3d, 103]', 'GET', '获取菜单下的子菜单', '2018-07-19 11:20:23', 39);
INSERT INTO `sys_logs` VALUES (75, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.SysModuleController.findChildMenu', '[1_705933389af84efc85709a412a3a9bdb, com.fintecher.manage.vo.PageParam@63550dcb, 151]', 'GET', '获取菜单下的子菜单', '2018-07-19 11:20:37', 59);
INSERT INTO `sys_logs` VALUES (76, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicExpenseController.findBasicExpenseByOrg', '[1_705933389af84efc85709a412a3a9bdb]', 'GET', '获取当前用户所属机构下的费用项(下拉选用)', '2018-07-19 11:20:46', 631);
INSERT INTO `sys_logs` VALUES (77, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicOffsetController.findBasicOffsetByOrg', '[1_705933389af84efc85709a412a3a9bdb]', 'GET', '获取当前用户所属机构下的冲抵策略', '2018-07-19 11:20:47', 1122);
INSERT INTO `sys_logs` VALUES (78, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerOrderController.query', '[1_705933389af84efc85709a412a3a9bdb, com.fintecher.manage.vo.PageParam@6cf2bd39, SearchOrderParams(customerName=null, orderNo=null, idCard=null, customerPhone=null)]', 'GET', '订单分页查询', '2018-07-19 11:20:56', 748);
INSERT INTO `sys_logs` VALUES (79, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.FinancialQueryController.findRepayOrderUnpaidList', '[1_705933389af84efc85709a412a3a9bdb, com.fintecher.manage.vo.PageParam@6969384d, SearchOrderParams(customerName=null, orderNo=null, idCard=null, customerPhone=null)]', 'GET', '查询待放款的订单', '2018-07-19 11:21:18', 661);
INSERT INTO `sys_logs` VALUES (80, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.FinancialQueryController.findBasicOrderCarList', '[1_705933389af84efc85709a412a3a9bdb, com.fintecher.manage.vo.PageParam@4a72599e, OrederId(supplier=null)]', 'GET', '查询供应商待放款列表', '2018-07-19 11:21:19', 90);
INSERT INTO `sys_logs` VALUES (81, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicSupplierController.getBasicSupplierList', '[1_705933389af84efc85709a412a3a9bdb]', 'GET', '查询供应商列表无分页 ', '2018-07-19 11:21:19', 438);
INSERT INTO `sys_logs` VALUES (82, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerOrderController.query', '[1_705933389af84efc85709a412a3a9bdb, com.fintecher.manage.vo.PageParam@2458329f, SearchOrderParams(customerName=null, orderNo=null, idCard=null, customerPhone=null)]', 'GET', '订单分页查询', '2018-07-19 11:22:37', 55);
INSERT INTO `sys_logs` VALUES (83, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicExpenseController.findBasicExpenseByOrg', '[1_705933389af84efc85709a412a3a9bdb]', 'GET', '获取当前用户所属机构下的费用项(下拉选用)', '2018-07-19 11:22:39', 55);
INSERT INTO `sys_logs` VALUES (84, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicOffsetController.findBasicOffsetByOrg', '[1_705933389af84efc85709a412a3a9bdb]', 'GET', '获取当前用户所属机构下的冲抵策略', '2018-07-19 11:22:39', 159);
INSERT INTO `sys_logs` VALUES (85, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerOrderController.queryCustomerOrderFile', '[1_705933389af84efc85709a412a3a9bdb, com.fintecher.manage.vo.PageParam@14fb2711, SearchOrderParams(customerName=null, orderNo=null, idCard=null, customerPhone=null)]', 'GET', '查询待补填资料订单', '2018-07-19 11:32:31', 142);
INSERT INTO `sys_logs` VALUES (86, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findNotBlackCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@1b0a0a2e, 1_705933389af84efc85709a412a3a9bdb]', 'GET', '非黑名单客户列表', '2018-07-19 14:24:48', 716);
INSERT INTO `sys_logs` VALUES (87, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.queryCarModel', '[1_705933389af84efc85709a412a3a9bdb, com.fintecher.manage.vo.PageParam@e138e70, SearchCarParams(brandName=null, seriesName=null, modelName=null)]', 'GET', '分页获取当前登陆人组织机构下的车型', '2018-07-19 14:24:48', 831);
INSERT INTO `sys_logs` VALUES (88, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerOrderController.query', '[1_705933389af84efc85709a412a3a9bdb, com.fintecher.manage.vo.PageParam@b66b9f9, SearchOrderParams(customerName=null, orderNo=null, idCard=null, customerPhone=null)]', 'GET', '订单分页查询', '2018-07-19 14:24:50', 79);
INSERT INTO `sys_logs` VALUES (89, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findWhiteCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@684de6c8, 1_705933389af84efc85709a412a3a9bdb]', 'GET', '黑名单客户列表', '2018-07-19 14:24:58', 81);
INSERT INTO `sys_logs` VALUES (90, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findGrayCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@4f08cbfa, 1_705933389af84efc85709a412a3a9bdb]', 'GET', '灰名单客户列表', '2018-07-19 14:24:59', 29);
INSERT INTO `sys_logs` VALUES (91, '0:0:0:0:0:0:0:1', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.queryCarModel', '[1_da82f9e5e1304088bf75ee71fabbdfdb1_15aee3f8b564401ca5d3bb6e2c8b2ce6, com.fintecher.manage.vo.PageParam@3efc6a76, SearchCarParams(brandName=null, seriesName=null, modelName=null)]', 'GET', '分页获取当前登陆人组织机构下的车型', '2018-07-19 14:29:02', 8);
INSERT INTO `sys_logs` VALUES (92, '0:0:0:0:0:0:0:1', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.queryCarModel', '[1_da82f9e5e1304088bf75ee71fabbdfdb1_15aee3f8b564401ca5d3bb6e2c8b2ce6, com.fintecher.manage.vo.PageParam@4d93bd84, SearchCarParams(brandName=null, seriesName=null, modelName=null)]', 'GET', '分页获取当前登陆人组织机构下的车型', '2018-07-19 14:29:04', 3);
INSERT INTO `sys_logs` VALUES (93, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerOrderController.queryCustomerOrderFile', '[1_705933389af84efc85709a412a3a9bdb, com.fintecher.manage.vo.PageParam@6570ffc3, SearchOrderParams(customerName=null, orderNo=null, idCard=null, customerPhone=null)]', 'GET', '查询待补填资料订单', '2018-07-19 15:38:06', 33);
INSERT INTO `sys_logs` VALUES (94, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.LoginController.logout', '[1_705933389af84efc85709a412a3a9bdb]', 'POST', '注销当前用户登陆状态（退出）', '2018-07-19 15:38:59', 249);
INSERT INTO `sys_logs` VALUES (95, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.getCarAllInfo', '[1_fc07c694126b4cfe8b85ed37327cec40]', 'GET', '车辆品牌列表', '2018-07-19 15:39:10', 31);
INSERT INTO `sys_logs` VALUES (96, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.SysOrgController.findAllOrganizationByAuth', '[1_fc07c694126b4cfe8b85ed37327cec40]', 'GET', '获取数据权限下所有的组织机构', '2018-07-19 15:39:11', 125);
INSERT INTO `sys_logs` VALUES (97, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.getCarAllInfo', '[1_8fbcd59819ae447380bd6e4938192abd]', 'GET', '车辆品牌列表', '2018-07-19 15:39:31', 29);
INSERT INTO `sys_logs` VALUES (98, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.SysOrgController.findAllOrganizationByAuth', '[1_8fbcd59819ae447380bd6e4938192abd]', 'GET', '获取数据权限下所有的组织机构', '2018-07-19 15:39:31', 80);
INSERT INTO `sys_logs` VALUES (99, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicRepaySchemeController.getAllBasicSchemeByOrgId', '[1_8fbcd59819ae447380bd6e4938192abd]', 'GET', '获取当前用户下组织机构下所有的还款方案', '2018-07-19 15:39:37', 479);
INSERT INTO `sys_logs` VALUES (100, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findPotentialCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@30d7f7c7, 1_8fbcd59819ae447380bd6e4938192abd]', 'GET', '意向客户列表', '2018-07-19 16:04:18', 125);
INSERT INTO `sys_logs` VALUES (101, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.findCustomerIntentionList', '[1, 1_8fbcd59819ae447380bd6e4938192abd]', 'GET', '根据客户id查找意向记录列表', '2018-07-19 16:04:22', 58);
INSERT INTO `sys_logs` VALUES (102, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.findCustomerIntentionList', '[1, 1_8fbcd59819ae447380bd6e4938192abd]', 'GET', '根据客户id查找意向记录列表', '2018-07-19 16:05:08', 47);
INSERT INTO `sys_logs` VALUES (103, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.findCustomerFollowByIntentionId', '[1, 1_8fbcd59819ae447380bd6e4938192abd]', 'GET', '根据意向id查找跟踪记录列表', '2018-07-19 16:05:13', 129);
INSERT INTO `sys_logs` VALUES (104, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findPotentialCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@16155549, 1_8fbcd59819ae447380bd6e4938192abd]', 'GET', '意向客户列表', '2018-07-19 16:05:33', 44);
INSERT INTO `sys_logs` VALUES (105, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findHistoryCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@3c05b381, 1_8fbcd59819ae447380bd6e4938192abd]', 'GET', '历史客户列表', '2018-07-19 17:44:19', 55);
INSERT INTO `sys_logs` VALUES (106, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findCustomerList', '[null, null, null, null, com.fintecher.manage.vo.PageParam@69c6445e, 1_8fbcd59819ae447380bd6e4938192abd]', 'GET', '补填资料客户列表', '2018-07-19 17:44:22', 141);
INSERT INTO `sys_logs` VALUES (107, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findBlackCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@43ca36a4, 1_8fbcd59819ae447380bd6e4938192abd]', 'GET', '黑名单客户列表', '2018-07-19 17:44:23', 48);
INSERT INTO `sys_logs` VALUES (108, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findCustomerList', '[null, null, null, null, com.fintecher.manage.vo.PageParam@116128ec, 1_8fbcd59819ae447380bd6e4938192abd]', 'GET', '补填资料客户列表', '2018-07-19 17:44:26', 63);
INSERT INTO `sys_logs` VALUES (109, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findFormalCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@5c6ef581, 1_8fbcd59819ae447380bd6e4938192abd]', 'GET', '正式客户列表', '2018-07-19 17:44:26', 20);
INSERT INTO `sys_logs` VALUES (110, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findPotentialCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@446e7374, 1_8fbcd59819ae447380bd6e4938192abd]', 'GET', '意向客户列表', '2018-07-19 17:44:27', 59);
INSERT INTO `sys_logs` VALUES (111, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.SysDictController.getAll', '[]', 'GET', '查询系统数据字典项', '2018-07-20 09:40:12', 1414);
INSERT INTO `sys_logs` VALUES (112, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.getCarAllInfo', '[1_e750e77f26544c89a81b9baadadccca4]', 'GET', '车辆品牌列表', '2018-07-20 09:40:13', 85);
INSERT INTO `sys_logs` VALUES (113, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.SysOrgController.findAllOrganizationByAuth', '[1_e750e77f26544c89a81b9baadadccca4]', 'GET', '获取数据权限下所有的组织机构', '2018-07-20 09:40:13', 127);
INSERT INTO `sys_logs` VALUES (114, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.SysOrgController.findAllOrganizationByAuth', '[1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '获取数据权限下所有的组织机构', '2018-07-20 09:40:16', 57);
INSERT INTO `sys_logs` VALUES (115, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCarManageController.getCarAllInfo', '[1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '车辆品牌列表', '2018-07-20 09:40:16', 21);
INSERT INTO `sys_logs` VALUES (116, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findPotentialCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@75813df, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '意向客户列表', '2018-07-20 09:42:57', 174);
INSERT INTO `sys_logs` VALUES (117, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findPotentialCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@6e96d0e1, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '意向客户列表', '2018-07-20 10:24:21', 21);
INSERT INTO `sys_logs` VALUES (118, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.findCustomerIntentionList', '[1, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '根据客户id查找意向记录列表', '2018-07-20 10:24:26', 28);
INSERT INTO `sys_logs` VALUES (119, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findGrayCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@332bc1be, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '灰名单客户列表', '2018-07-20 10:24:45', 21);
INSERT INTO `sys_logs` VALUES (120, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findFormalCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@5bea47be, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '正式客户列表', '2018-07-20 10:24:45', 15);
INSERT INTO `sys_logs` VALUES (121, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findCustomerList', '[null, null, null, null, com.fintecher.manage.vo.PageParam@3a5f96dc, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '补填资料客户列表', '2018-07-20 10:24:45', 13);
INSERT INTO `sys_logs` VALUES (122, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.findCustomerById', '[1, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '查找客户', '2018-07-20 10:24:47', 20);
INSERT INTO `sys_logs` VALUES (123, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.findCustomerById', '[1, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '查找客户', '2018-07-20 10:24:53', 12);
INSERT INTO `sys_logs` VALUES (124, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.findCustomerById', '[1, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '查找客户', '2018-07-20 10:25:33', 18);
INSERT INTO `sys_logs` VALUES (125, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findCustomerList', '[null, null, null, null, com.fintecher.manage.vo.PageParam@4ec52da6, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '补填资料客户列表', '2018-07-20 11:25:02', 30);
INSERT INTO `sys_logs` VALUES (126, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findPotentialCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@837bbf9, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '意向客户列表', '2018-07-20 11:25:04', 17);
INSERT INTO `sys_logs` VALUES (127, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.findCustomerById', '[1, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '查找客户', '2018-07-20 11:25:05', 10);
INSERT INTO `sys_logs` VALUES (128, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerAssessmentCarController.getBasicCustomerCarList', '[1, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '客户车产列表', '2018-07-20 11:25:49', 46);
INSERT INTO `sys_logs` VALUES (129, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomHouseController.findCustomerHouseList', '[1, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '客户房产信息列表', '2018-07-20 11:25:50', 41);
INSERT INTO `sys_logs` VALUES (130, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerJobController.findCustomPersonalJob', '[1, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '客户职业信息列表', '2018-07-20 11:25:51', 38);
INSERT INTO `sys_logs` VALUES (131, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.getCustomerBankInfo', '[1, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '银行卡信息查询', '2018-07-20 11:25:52', 52);
INSERT INTO `sys_logs` VALUES (132, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.SysDictController.getDataDictByTypeCode', '[10013, null, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '查询数据字典类型对应的数据字典项', '2018-07-20 11:25:53', 10);
INSERT INTO `sys_logs` VALUES (133, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerDataController.getCustomerData', '[1, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '查询资料', '2018-07-20 11:25:53', 239);
INSERT INTO `sys_logs` VALUES (134, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findCustomerBlackListRecord', '[1, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '获取客户黑白灰名单记录', '2018-07-20 11:25:54', 27);
INSERT INTO `sys_logs` VALUES (135, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.findCustomerIntentionList', '[1, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '根据客户id查找意向记录列表', '2018-07-20 11:26:01', 10);
INSERT INTO `sys_logs` VALUES (136, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findBasicCustomerOrderList', '[1, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '订单记录', '2018-07-20 11:26:07', 34);
INSERT INTO `sys_logs` VALUES (137, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.findCustomerIntentionList', '[1, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '根据客户id查找意向记录列表', '2018-07-20 11:26:08', 9);
INSERT INTO `sys_logs` VALUES (138, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.findCustomerById', '[1, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '查找客户', '2018-07-20 11:30:39', 10);
INSERT INTO `sys_logs` VALUES (139, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findGrayCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@5aa79323, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '灰名单客户列表', '2018-07-20 13:53:06', 15);
INSERT INTO `sys_logs` VALUES (140, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findGrayCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@11f53b07, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '灰名单客户列表', '2018-07-20 14:09:38', 19);
INSERT INTO `sys_logs` VALUES (141, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findGrayCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@1138f361, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '灰名单客户列表', '2018-07-20 14:09:44', 18);
INSERT INTO `sys_logs` VALUES (142, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findPotentialCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@57ddc395, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '意向客户列表', '2018-07-20 14:09:45', 19);
INSERT INTO `sys_logs` VALUES (143, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findGrayCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@137b1d41, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '灰名单客户列表', '2018-07-20 14:25:42', 16);
INSERT INTO `sys_logs` VALUES (144, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findPotentialCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@169b9900, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '意向客户列表', '2018-07-20 14:25:43', 17);
INSERT INTO `sys_logs` VALUES (145, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerController.findCustomerById', '[1, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '查找客户', '2018-07-20 14:25:51', 9);
INSERT INTO `sys_logs` VALUES (146, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findPotentialCustomerList', '[null, null, null, com.fintecher.manage.vo.PageParam@77d78a44, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '意向客户列表', '2018-07-20 14:47:17', 18);
INSERT INTO `sys_logs` VALUES (147, '192.168.22.194', '超级管理员', 'com.fintecher.manage.web.BasicCustomerCenterController.findCustomerList', '[null, null, null, null, com.fintecher.manage.vo.PageParam@7e98c10d, 1_a0b5e3edeadb4e9db90801a3441636a2]', 'GET', '补填资料客户列表', '2018-07-20 14:47:22', 27);

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '机构ID',
  `org_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构名称',
  `org_type` int(11) DEFAULT NULL COMMENT '机构性质',
  `org_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构编号',
  `org_pid` bigint(32) DEFAULT NULL COMMENT '上级机构ID',
  `org_tree_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构树形编码',
  `org_level` int(11) DEFAULT NULL COMMENT '机构等级',
  `org_status` int(11) DEFAULT NULL COMMENT '机构状态',
  `org_remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织机构' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES (1, '中资联', 10036, 'administrator', 0, 'administrator', 10007, 10022, '集团', 1, '2018-06-11 18:11:13');
INSERT INTO `sys_org` VALUES (20, '中资联-业务部', 10006, 'administrator_VjCU9GsA', 1, 'administrator_VjCU9GsA', 10008, 10022, '中资联-业务部', 1, '2018-06-26 14:33:19');
INSERT INTO `sys_org` VALUES (21, '中资联-财务部', 10006, 'administrator_jVTFzYLs', 1, 'administrator_jVTFzYLs', 10008, 10022, NULL, 1, '2018-06-26 18:05:47');
INSERT INTO `sys_org` VALUES (22, '中资联-人事部', 10006, 'administrator_uOrUP5h6', 1, 'administrator_uOrUP5h6', 10008, 10022, '中资联-人事部', 1, '2018-06-26 14:34:13');
INSERT INTO `sys_org` VALUES (23, '指旺金科', 10004, 'administrator_iZxkvx14', 1, 'administrator_iZxkvx14', 10008, 10022, '指旺金科', 1, '2018-06-26 14:35:08');
INSERT INTO `sys_org` VALUES (24, '指旺金科-人事部', 10006, 'administrator_iZxkvx14_ALLKG2bn', 23, 'administrator_iZxkvx14_ALLKG2bn', 10009, 10022, '指旺金科-人事部', 1, '2018-06-26 14:39:10');
INSERT INTO `sys_org` VALUES (25, '指旺金科-财务部', 10005, 'administrator_iZxkvx14_56fgX5XF', 23, 'administrator_iZxkvx14_56fgX5XF', 10009, 10022, '指旺金科-财务部', 1, '2018-06-26 14:40:18');
INSERT INTO `sys_org` VALUES (26, '华南研发中心', 10005, 'administrator_iZxkvx14_N11LpBfs', 23, 'administrator_iZxkvx14_N11LpBfs', 10009, 10022, '华南研发中心', 1, '2018-06-26 14:40:57');
INSERT INTO `sys_org` VALUES (27, '华东研发中心', 10005, 'administrator_iZxkvx14_EZeB9dpP', 23, 'administrator_iZxkvx14_EZeB9dpP', 10009, 10022, '华东研发中心', 1, '2018-06-26 14:41:15');
INSERT INTO `sys_org` VALUES (28, '华中研发中心', 10005, 'administrator_iZxkvx14_ziUn3pzB', 23, 'administrator_iZxkvx14_ziUn3pzB', 10009, 10022, '华中研发中心', 1, '2018-06-26 14:41:38');
INSERT INTO `sys_org` VALUES (29, '西北研发中心', 10005, 'administrator_iZxkvx14_vOj9ivoO', 23, 'administrator_iZxkvx14_vOj9ivoO', 10009, 10022, '西北研发中心', 1, '2018-06-26 14:41:55');
INSERT INTO `sys_org` VALUES (30, '华南业务部', 10006, 'administrator_iZxkvx14_N11LpBfs_bHhTvqq6', 26, 'administrator_iZxkvx14_N11LpBfs_bHhTvqq6', 10010, 10022, '华南业务部', 1, '2018-06-26 14:44:24');
INSERT INTO `sys_org` VALUES (31, '华东业务部', 10006, 'administrator_iZxkvx14_EZeB9dpP_l6CkCUJ1', 27, 'administrator_iZxkvx14_EZeB9dpP_l6CkCUJ1', 10010, 10022, '华东业务部', 1, '2018-06-26 14:44:48');
INSERT INTO `sys_org` VALUES (32, '华中业务部', 10006, 'administrator_iZxkvx14_ziUn3pzB_F9dDNGwB', 28, 'administrator_iZxkvx14_ziUn3pzB_F9dDNGwB', 10010, 10022, '华中业务部', 1, '2018-06-26 14:45:01');
INSERT INTO `sys_org` VALUES (33, '西北业务部', 10006, 'administrator_iZxkvx14_vOj9ivoO_uVM7JAqZ', 29, 'administrator_iZxkvx14_vOj9ivoO_uVM7JAqZ', 10010, 10022, '西北业务部', 1, '2018-06-26 14:45:13');

-- ----------------------------
-- Table structure for sys_parameter
-- ----------------------------
DROP TABLE IF EXISTS `sys_parameter`;
CREATE TABLE `sys_parameter`  (
  `id` bigint(32) NOT NULL COMMENT '参数ID',
  `param_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '参数名称',
  `param_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '参数编码',
  `param_status` int(11) DEFAULT NULL COMMENT '是否启用',
  `param_type` int(11) DEFAULT NULL COMMENT '参数类型',
  `is_readonly` int(11) DEFAULT NULL COMMENT '是否允许修改',
  `param_value` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '参数值',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统参数' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_parameter
-- ----------------------------
INSERT INTO `sys_parameter` VALUES (1, '用户默认密码', 'PersonalDefaultPassword', 10002, NULL, 10003, '888888', '用户默认密码', '1', '2018-06-11 19:13:29');
INSERT INTO `sys_parameter` VALUES (2, '服务环境', 'ProjectDefaultEnv', 10002, NULL, NULL, '10038', '服务环境', '1', '2018-06-21 00:00:00');
INSERT INTO `sys_parameter` VALUES (3, '密码过时天数', 'PasswordObsoleteDays', 10002, NULL, NULL, '30', '密码过时天数', '1', '2018-06-26 00:00:00');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `resource_pid` bigint(32) DEFAULT NULL COMMENT '上级资源ID',
  `resource_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源名称',
  `resource_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源码',
  `resource_type` int(11) DEFAULT NULL COMMENT '资源类型',
  `resource_level` int(11) DEFAULT NULL COMMENT '资源级别',
  `resource_status` int(11) DEFAULT NULL COMMENT '状态',
  `resource_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源路径',
  `resource_ico_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `resource_file_type` int(11) DEFAULT NULL COMMENT '资源文件类型',
  `resource_order` int(11) DEFAULT NULL COMMENT '序号',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 503 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统资源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES (1, 0, '系统管理', NULL, NULL, NULL, NULL, 'system-manage', 'xitongshezhigen', 10029, 1, NULL, 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (2, 0, '基础数据', NULL, NULL, NULL, NULL, 'base-data', 'jichushujugen', 10029, 2, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (103, 1, '机构与用户管理', NULL, NULL, NULL, NULL, 'system-manage/power-config/org-user-manage', '', 10028, 3, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (104, 103, '添加机构', NULL, NULL, NULL, NULL, NULL, '1', 10035, 4, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (105, 103, '搜索', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 5, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (106, 103, '新增用户', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 6, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (107, 103, '批量分配角色', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 7, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (108, 103, '批量管理设备', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 8, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (109, 103, '机构与用户管理列表', NULL, NULL, NULL, NULL, NULL, NULL, 10032, 9, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (110, 103, '分配角色', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 10, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (111, 103, '修改', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 11, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (112, 103, '重置密码', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 12, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (113, 103, '设备管理', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 13, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (114, 103, '用户名', NULL, NULL, NULL, NULL, NULL, NULL, 10034, 14, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (115, 103, '姓名', NULL, NULL, NULL, NULL, NULL, NULL, 10034, 15, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (116, 103, '姓名', NULL, NULL, NULL, NULL, NULL, NULL, 10034, 16, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (117, 1, '角色维护', NULL, NULL, NULL, NULL, 'system-manage/power-config/role-maintenance', NULL, 10028, 17, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (118, 117, '搜索', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 18, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (119, 117, '新增角色', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 19, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (120, 117, '角色维护列表', NULL, NULL, NULL, NULL, NULL, NULL, 10032, 20, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (121, 117, '修改', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 21, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (122, 117, '删除', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 22, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (123, 117, '模块权限', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 23, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (124, 117, '用户列表', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 24, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (125, 117, '待办事项配置', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 25, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (126, 117, '角色名称', NULL, NULL, NULL, NULL, NULL, NULL, 10034, 26, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (127, 117, '状态', NULL, NULL, NULL, NULL, NULL, NULL, 10034, 27, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (128, 1, '模块功能', NULL, NULL, NULL, NULL, 'system-manage/power-config/module-function', NULL, 10028, 28, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (129, 128, '重置名称', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 29, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (130, 128, '重置图标', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 30, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (131, 128, '修改图标', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 31, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (132, 128, '修改名称', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 32, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (133, 128, '查看', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 33, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (134, 128, '模块功能列表', NULL, NULL, NULL, NULL, NULL, NULL, 10032, 34, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (135, 1, '系统日志', NULL, NULL, NULL, NULL, 'system-manage/operation-config/system-log-download', NULL, 10028, 51, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (136, 151, '用户姓名', NULL, NULL, NULL, NULL, NULL, 'qqqqq', 10034, 52, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (137, 151, '用户端IP', NULL, NULL, NULL, NULL, NULL, NULL, 10034, 53, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (138, 151, '执行参数', NULL, NULL, NULL, NULL, NULL, NULL, 10034, 54, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (139, 151, '执行类型', NULL, NULL, NULL, NULL, NULL, NULL, 10034, 55, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (140, 151, '执行时间', NULL, NULL, NULL, NULL, NULL, NULL, 10034, 56, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (141, 151, '系统日志下载列表', NULL, NULL, NULL, NULL, NULL, NULL, 10032, 57, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (142, 151, '搜索', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 58, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (143, 151, '下载', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 59, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (144, 1, '系统参数管理', NULL, NULL, NULL, NULL, 'system-manage/operation-config/system-param-manage', NULL, 10028, 74, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (145, 174, '参数名称', NULL, NULL, NULL, NULL, NULL, NULL, 10034, 75, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (146, 174, '是否启用', NULL, NULL, NULL, NULL, NULL, NULL, 10034, 76, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (147, 174, '系统参数管理列表', NULL, NULL, NULL, NULL, NULL, NULL, 10032, 77, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (148, 174, '搜索', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 78, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (149, 174, '修改', NULL, NULL, NULL, NULL, NULL, NULL, 10035, 79, '我是描述', 1, '2018-06-13 16:59:04');
INSERT INTO `sys_resource` VALUES (150, 1, '系统字典', NULL, NULL, NULL, NULL, 'system-manage/operation-config/data-dict', NULL, 10028, 81, NULL, 1, '2018-06-21 10:08:11');
INSERT INTO `sys_resource` VALUES (151, 1, '用户字典', NULL, NULL, NULL, NULL, 'system-manage/operation-config/user-data-dict', NULL, 10028, 82, NULL, 1, '2018-06-21 10:08:09');
INSERT INTO `sys_resource` VALUES (152, 2, '车辆维护', NULL, NULL, NULL, NULL, 'base-data/vehicle-maintenance', NULL, 10028, 83, NULL, 1, '2018-06-21 10:08:05');
INSERT INTO `sys_resource` VALUES (153, 2, '费用项管理', NULL, NULL, NULL, NULL, 'base-data/basic-expense-manage', NULL, 10028, 84, NULL, 1, '2018-06-20 14:30:20');
INSERT INTO `sys_resource` VALUES (154, 2, '还款方案管理', NULL, NULL, NULL, NULL, 'base-data/repay-scheme', NULL, 10028, 86, NULL, 1, '2018-06-21 10:08:03');
INSERT INTO `sys_resource` VALUES (155, 2, '供应商管理', NULL, NULL, NULL, NULL, 'base-data/supplier-manage', NULL, 10028, 87, NULL, 1, '2018-06-21 15:38:22');
INSERT INTO `sys_resource` VALUES (156, 0, '客户中心', NULL, NULL, NULL, NULL, 'customer-center', 'kehuzhongxingen', 10029, 88, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (157, 0, '运营中心', NULL, NULL, NULL, NULL, 'operate-center', 'yunyingguanligen', 10029, 89, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (158, 0, '风控审核', NULL, NULL, NULL, NULL, 'risk-manage', 'shenhegen', 10029, 90, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (159, 0, '进销存管理', NULL, NULL, NULL, NULL, 'purchase-sales-inventory', 'kucunguanligen', 10029, 91, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (160, 0, '押品管理', NULL, NULL, NULL, NULL, 'collateral-manage', 'yapinguanligen', 10029, 92, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (161, 0, '开户签约', NULL, NULL, NULL, NULL, 'account-sign', 'kaihuqianyuegen', 10029, 93, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (162, 0, '订单中心', NULL, NULL, NULL, NULL, 'order-manage', 'zonghechaxungen', 10029, 94, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (163, 0, '财务管理', NULL, NULL, NULL, NULL, 'finance-manage', 'caiwu', 10029, 94, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (164, 2, '冲抵项管理', NULL, NULL, NULL, NULL, 'base-data/basic-offset-manage', NULL, 10028, 95, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (165, 2, '产品管理', NULL, NULL, NULL, NULL, 'base-data/product-manage', '', 10028, 96, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (166, 156, '意向客户', NULL, NULL, NULL, NULL, 'customer-center/potential-clients', NULL, 10028, 97, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (167, 156, '正式客户', NULL, NULL, NULL, NULL, 'customer-center/formal-customer', NULL, 10028, 98, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (168, 156, '历史客户', NULL, NULL, NULL, NULL, 'customer-center/history-customer', NULL, 10028, 100, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (169, 156, '补填资料', NULL, NULL, NULL, NULL, 'customer-center/update-customer-data', NULL, 10028, 101, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (170, 156, '黑名单', NULL, NULL, NULL, NULL, 'customer-center/black-list-customer', NULL, 10028, 102, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (171, 156, '灰名单', NULL, NULL, NULL, NULL, 'customer-center/gray-list-customer', NULL, 10028, 103, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (172, 156, '白名单', NULL, NULL, NULL, NULL, 'customer-center/white-list-customer', NULL, 10028, 104, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (173, 157, '订单查询', NULL, NULL, NULL, NULL, 'operate-center/order-query', NULL, 10028, 105, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (174, 157, '融资租赁申请', NULL, NULL, NULL, NULL, 'operate-center/finance-apply', NULL, 10028, 106, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (175, 157, '订单维护', NULL, NULL, NULL, NULL, 'operate-center/update-order-data', NULL, 10028, 129, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (176, 158, '订单查询', NULL, NULL, NULL, NULL, 'risk-manage/finish-order-query', NULL, 10028, 108, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (177, 158, '订单审核', NULL, NULL, NULL, NULL, 'risk-manage/order-check', NULL, 10028, 109, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (178, 159, '外采管理', NULL, NULL, NULL, NULL, 'purchase-sales-inventory/purchase-manage', NULL, 10028, 110, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (179, 159, '客户提车', NULL, NULL, NULL, NULL, 'purchase-sales-inventory/sales-car-checkout', NULL, 10028, 111, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (180, 159, '库存查看', NULL, NULL, NULL, NULL, 'purchase-sales-inventory/inventory-query', NULL, 10028, 112, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (181, 160, '押品评估', NULL, NULL, NULL, NULL, 'collateral-manage/assessment', NULL, 10028, 113, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (182, 160, '押品入库', NULL, NULL, NULL, NULL, 'collateral-manage/in-storage', NULL, 10028, 114, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (183, 160, '押品出库', NULL, NULL, NULL, NULL, 'collateral-manage/out-storage', NULL, 10028, 115, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (184, 161, '客户开户', NULL, NULL, NULL, NULL, 'account-sign/open-account', NULL, 10028, 116, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (185, 161, '客户签约', NULL, NULL, NULL, NULL, 'account-sign/sign-contract', NULL, 10028, 117, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (186, 162, '订单查看', NULL, NULL, NULL, NULL, 'order-manage/order-review', NULL, 10028, 118, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (187, 162, '还款查询', NULL, NULL, NULL, NULL, 'order-manage/repayment-query', NULL, 10028, 119, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (188, 163, '客户放款', NULL, NULL, NULL, NULL, 'finance-manage/customer-loan', NULL, 10028, 120, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (189, 163, '供应商放款', NULL, NULL, NULL, NULL, 'finance-manage/supplier-loan', NULL, 10028, 121, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (190, 163, '收款', NULL, NULL, NULL, NULL, 'finance-manage/proceeds-customer', NULL, 10028, 122, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (191, 163, '还款', NULL, NULL, NULL, NULL, 'finance-manage/customer-repayment', NULL, 10028, 123, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (192, 163, '退款', NULL, NULL, NULL, NULL, 'finance-manage/refund-customer', NULL, 10028, 124, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (193, 163, '提前结清', NULL, NULL, NULL, NULL, 'finance-manage/early-settlement', NULL, 10028, 125, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (194, 163, '提前收回', NULL, NULL, NULL, NULL, 'finance-manage/ahead-cost-recover', NULL, 10028, 126, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (195, 163, '财务开票', NULL, NULL, NULL, NULL, 'finance-manage/finance-invoice', NULL, 10028, 127, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (196, 163, '供应商发票', NULL, NULL, NULL, NULL, 'finance-manage/supplier-invoice', NULL, 10028, 128, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (502, 157, '抵押贷款申请', NULL, NULL, NULL, NULL, 'operate-center/mortgage-apply', NULL, 10028, 107, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构ID',
  `role_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  `role_status` int(11) DEFAULT NULL COMMENT '角色状态',
  `dept_id` bigint(32) DEFAULT NULL COMMENT '部门ID',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 1, '系统管理员', '系统管理员', 10022, 1, 1, '2018-06-21 10:42:32');

-- ----------------------------
-- Table structure for sys_role_except_orgs
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_except_orgs`;
CREATE TABLE `sys_role_except_orgs`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色ID',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '排除范围' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_except_orgs
-- ----------------------------
INSERT INTO `sys_role_except_orgs` VALUES (2, 2, 2);

-- ----------------------------
-- Table structure for sys_role_orgs
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_orgs`;
CREATE TABLE `sys_role_orgs`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构ID',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据范围' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_orgs
-- ----------------------------
INSERT INTO `sys_role_orgs` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `resource_id` bigint(32) DEFAULT NULL COMMENT '资源ID',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1575 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色资源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES (1, 1, 1);
INSERT INTO `sys_role_resource` VALUES (1479, 1, 1);
INSERT INTO `sys_role_resource` VALUES (1480, 2, 1);
INSERT INTO `sys_role_resource` VALUES (1481, 103, 1);
INSERT INTO `sys_role_resource` VALUES (1482, 104, 1);
INSERT INTO `sys_role_resource` VALUES (1483, 105, 1);
INSERT INTO `sys_role_resource` VALUES (1484, 106, 1);
INSERT INTO `sys_role_resource` VALUES (1485, 107, 1);
INSERT INTO `sys_role_resource` VALUES (1486, 108, 1);
INSERT INTO `sys_role_resource` VALUES (1487, 109, 1);
INSERT INTO `sys_role_resource` VALUES (1488, 110, 1);
INSERT INTO `sys_role_resource` VALUES (1489, 111, 1);
INSERT INTO `sys_role_resource` VALUES (1490, 112, 1);
INSERT INTO `sys_role_resource` VALUES (1491, 113, 1);
INSERT INTO `sys_role_resource` VALUES (1492, 114, 1);
INSERT INTO `sys_role_resource` VALUES (1493, 115, 1);
INSERT INTO `sys_role_resource` VALUES (1494, 116, 1);
INSERT INTO `sys_role_resource` VALUES (1495, 117, 1);
INSERT INTO `sys_role_resource` VALUES (1496, 118, 1);
INSERT INTO `sys_role_resource` VALUES (1497, 119, 1);
INSERT INTO `sys_role_resource` VALUES (1498, 120, 1);
INSERT INTO `sys_role_resource` VALUES (1499, 121, 1);
INSERT INTO `sys_role_resource` VALUES (1500, 122, 1);
INSERT INTO `sys_role_resource` VALUES (1501, 123, 1);
INSERT INTO `sys_role_resource` VALUES (1502, 124, 1);
INSERT INTO `sys_role_resource` VALUES (1503, 125, 1);
INSERT INTO `sys_role_resource` VALUES (1504, 126, 1);
INSERT INTO `sys_role_resource` VALUES (1505, 127, 1);
INSERT INTO `sys_role_resource` VALUES (1506, 128, 1);
INSERT INTO `sys_role_resource` VALUES (1507, 129, 1);
INSERT INTO `sys_role_resource` VALUES (1508, 130, 1);
INSERT INTO `sys_role_resource` VALUES (1509, 131, 1);
INSERT INTO `sys_role_resource` VALUES (1510, 132, 1);
INSERT INTO `sys_role_resource` VALUES (1511, 133, 1);
INSERT INTO `sys_role_resource` VALUES (1512, 134, 1);
INSERT INTO `sys_role_resource` VALUES (1513, 135, 1);
INSERT INTO `sys_role_resource` VALUES (1514, 136, 1);
INSERT INTO `sys_role_resource` VALUES (1515, 137, 1);
INSERT INTO `sys_role_resource` VALUES (1516, 138, 1);
INSERT INTO `sys_role_resource` VALUES (1517, 139, 1);
INSERT INTO `sys_role_resource` VALUES (1518, 140, 1);
INSERT INTO `sys_role_resource` VALUES (1519, 141, 1);
INSERT INTO `sys_role_resource` VALUES (1520, 142, 1);
INSERT INTO `sys_role_resource` VALUES (1521, 143, 1);
INSERT INTO `sys_role_resource` VALUES (1522, 144, 1);
INSERT INTO `sys_role_resource` VALUES (1523, 145, 1);
INSERT INTO `sys_role_resource` VALUES (1524, 146, 1);
INSERT INTO `sys_role_resource` VALUES (1525, 147, 1);
INSERT INTO `sys_role_resource` VALUES (1526, 148, 1);
INSERT INTO `sys_role_resource` VALUES (1527, 149, 1);
INSERT INTO `sys_role_resource` VALUES (1528, 150, 1);
INSERT INTO `sys_role_resource` VALUES (1529, 151, 1);
INSERT INTO `sys_role_resource` VALUES (1530, 152, 1);
INSERT INTO `sys_role_resource` VALUES (1531, 153, 1);
INSERT INTO `sys_role_resource` VALUES (1532, 154, 1);
INSERT INTO `sys_role_resource` VALUES (1533, 155, 1);
INSERT INTO `sys_role_resource` VALUES (1534, 156, 1);
INSERT INTO `sys_role_resource` VALUES (1535, 157, 1);
INSERT INTO `sys_role_resource` VALUES (1536, 158, 1);
INSERT INTO `sys_role_resource` VALUES (1537, 159, 1);
INSERT INTO `sys_role_resource` VALUES (1538, 160, 1);
INSERT INTO `sys_role_resource` VALUES (1539, 161, 1);
INSERT INTO `sys_role_resource` VALUES (1540, 162, 1);
INSERT INTO `sys_role_resource` VALUES (1541, 163, 1);
INSERT INTO `sys_role_resource` VALUES (1542, 164, 1);
INSERT INTO `sys_role_resource` VALUES (1543, 165, 1);
INSERT INTO `sys_role_resource` VALUES (1544, 166, 1);
INSERT INTO `sys_role_resource` VALUES (1545, 167, 1);
INSERT INTO `sys_role_resource` VALUES (1546, 168, 1);
INSERT INTO `sys_role_resource` VALUES (1547, 169, 1);
INSERT INTO `sys_role_resource` VALUES (1548, 170, 1);
INSERT INTO `sys_role_resource` VALUES (1549, 171, 1);
INSERT INTO `sys_role_resource` VALUES (1550, 172, 1);
INSERT INTO `sys_role_resource` VALUES (1551, 173, 1);
INSERT INTO `sys_role_resource` VALUES (1552, 174, 1);
INSERT INTO `sys_role_resource` VALUES (1553, 175, 1);
INSERT INTO `sys_role_resource` VALUES (1554, 176, 1);
INSERT INTO `sys_role_resource` VALUES (1555, 177, 1);
INSERT INTO `sys_role_resource` VALUES (1556, 178, 1);
INSERT INTO `sys_role_resource` VALUES (1557, 179, 1);
INSERT INTO `sys_role_resource` VALUES (1558, 180, 1);
INSERT INTO `sys_role_resource` VALUES (1559, 181, 1);
INSERT INTO `sys_role_resource` VALUES (1560, 182, 1);
INSERT INTO `sys_role_resource` VALUES (1561, 183, 1);
INSERT INTO `sys_role_resource` VALUES (1562, 184, 1);
INSERT INTO `sys_role_resource` VALUES (1563, 185, 1);
INSERT INTO `sys_role_resource` VALUES (1564, 186, 1);
INSERT INTO `sys_role_resource` VALUES (1565, 187, 1);
INSERT INTO `sys_role_resource` VALUES (1566, 188, 1);
INSERT INTO `sys_role_resource` VALUES (1567, 189, 1);
INSERT INTO `sys_role_resource` VALUES (1568, 190, 1);
INSERT INTO `sys_role_resource` VALUES (1569, 191, 1);
INSERT INTO `sys_role_resource` VALUES (1570, 192, 1);
INSERT INTO `sys_role_resource` VALUES (1571, 193, 1);
INSERT INTO `sys_role_resource` VALUES (1572, 194, 1);
INSERT INTO `sys_role_resource` VALUES (1573, 195, 1);
INSERT INTO `sys_role_resource` VALUES (1574, 196, 1);

-- ----------------------------
-- Table structure for sys_seq
-- ----------------------------
DROP TABLE IF EXISTS `sys_seq`;
CREATE TABLE `sys_seq`  (
  `seq_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '序列名称',
  `current_seq` int(11) NOT NULL COMMENT '当前值',
  PRIMARY KEY (`seq_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_seq
-- ----------------------------
INSERT INTO `sys_seq` VALUES ('order_no', 3);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构ID',
  `user_realname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `user_sex` int(11) DEFAULT NULL COMMENT '性别',
  `user_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `user_email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `user_photo` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户头像',
  `user_type` int(11) DEFAULT NULL COMMENT '用户类型',
  `tral_time` int(11) DEFAULT NULL COMMENT '试用时长',
  `login_type` int(11) DEFAULT NULL COMMENT '登录类型',
  `login_device` int(11) DEFAULT NULL COMMENT '登录设备类型',
  `login_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录地址',
  `password_time` datetime(0) DEFAULT NULL COMMENT '密码过期时间',
  `dept_id` bigint(32) DEFAULT NULL COMMENT '部门ID',
  `user_message_pushid` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户绑定的消息推送',
  `user_status` int(11) DEFAULT NULL COMMENT '状态',
  `user_username` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `user_password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `user_remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 1, '超级管理员', 10024, '10000000000', 'admin@fintecher.cn', NULL, NULL, NULL, NULL, NULL, NULL, '2018-06-14 17:34:30', 1, NULL, 10022, 'administrator', '$2a$10$9hCzK0JkL7hrKxxYKG9kmOtRZrdNiPyXJSJg8znzouT.mFQYC.H6i', '超级管理员', NULL, '2018-06-14 17:34:30');

-- ----------------------------
-- Table structure for sys_user_column
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_column`;
CREATE TABLE `sys_user_column`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(32) DEFAULT NULL COMMENT '用户id',
  `column_id` bigint(32) DEFAULT NULL COMMENT '列资源id',
  `column_pid` bigint(32) DEFAULT NULL COMMENT '列资源父页面id',
  `column_check` int(11) DEFAULT NULL COMMENT '是否勾选',
  `column_sort` int(11) DEFAULT NULL,
  `column_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源code',
  `operator` bigint(32) DEFAULT NULL,
  `operator_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1968 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户资源中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_column
-- ----------------------------
INSERT INTO `sys_user_column` VALUES (1140, 1, 97, 184, 10002, 1, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1141, 1, 88, 180, 10002, 1, 'personalName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1142, 1, 209, 313, 10002, 1, 'orderLink', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1143, 1, 98, 184, 10002, 2, 'orderCreateTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1144, 1, 89, 180, 10002, 2, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1145, 1, 210, 313, 10002, 2, 'orderStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1146, 1, 99, 184, 10002, 3, 'orderType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1147, 1, 90, 180, 10002, 3, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1148, 1, 211, 313, 10002, 3, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1149, 1, 161, 271, 10002, 1, 'orderLink', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1150, 1, 212, 313, 10002, 4, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1151, 1, 91, 180, 10002, 4, 'isAccount', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1152, 1, 162, 271, 10002, 2, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1153, 1, 92, 180, 10002, 5, 'boundBank', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1154, 1, 213, 313, 10002, 5, 'intoPoolDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1155, 1, 100, 184, 10002, 4, 'productName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1156, 1, 117, 235, 10002, 1, 'orderLink', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1157, 1, 289, 381, 10002, 1, 'approvalDealStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1158, 1, 163, 271, 10002, 3, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1159, 1, 290, 381, 10002, 2, 'dealDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1160, 1, 93, 180, 10002, 6, 'bankCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1161, 1, 214, 313, 10002, 6, 'province', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1162, 1, 291, 381, 10002, 3, 'dealerName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1163, 1, 94, 180, 10002, 7, 'customId', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1164, 1, 118, 235, 10002, 2, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1165, 1, 95, 180, 10002, 8, 'faileReason', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1166, 1, 96, 180, 10002, 9, 'settlementChannel', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1167, 1, 101, 184, 10002, 5, 'personalName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1168, 1, 215, 313, 10002, 7, 'city', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1169, 1, 164, 271, 10002, 4, 'intoPoolDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1170, 1, 292, 381, 10002, 4, 'applicationType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1171, 1, 119, 235, 10002, 3, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1172, 1, 216, 313, 10002, 8, 'orderType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1173, 1, 102, 184, 10002, 6, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1174, 1, 165, 271, 10002, 5, 'province', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1175, 1, 293, 381, 10002, 5, 'totalPayment', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1176, 1, 120, 235, 10002, 4, 'intoPoolDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1177, 1, 217, 313, 10002, 9, 'productName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1178, 1, 103, 184, 10002, 8, 'mobileMain', 1, '2018-06-19 18:15:04');
INSERT INTO `sys_user_column` VALUES (1179, 1, 166, 271, 10002, 6, 'city', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1180, 1, 294, 381, 10002, 6, 'accountName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1181, 1, 218, 313, 10002, 10, 'personalName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1182, 1, 167, 271, 10002, 7, 'orderType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1183, 1, 121, 235, 10002, 5, 'province', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1184, 1, 104, 184, 10003, 7, 'latelyContractTime', 1, '2018-06-19 18:15:04');
INSERT INTO `sys_user_column` VALUES (1185, 1, 295, 381, 10002, 7, 'operatorTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1186, 1, 122, 235, 10002, 6, 'city', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1187, 1, 219, 313, 10002, 11, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1188, 1, 168, 271, 10002, 8, 'productName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1189, 1, 296, 381, 10002, 8, 'operatorName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1190, 1, 123, 235, 10002, 7, 'orderType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1191, 1, 220, 313, 10002, 12, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1192, 1, 169, 271, 10002, 9, 'personalName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1193, 1, 124, 235, 10002, 8, 'productName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1194, 1, 170, 271, 10002, 10, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1195, 1, 171, 271, 10002, 11, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1196, 1, 125, 235, 10002, 9, 'personalName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1197, 1, 466, 271, 10002, 12, 'detainedDays', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1198, 1, 126, 235, 10002, 10, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1199, 1, 127, 235, 10002, 11, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1200, 1, 457, 235, 10002, 12, 'detainedDays', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1201, 1, 417, 466, 10002, 1, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1202, 1, 418, 466, 10002, 2, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1203, 1, 419, 466, 10002, 3, 'personalName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1204, 1, 420, 466, 10002, 4, 'orderType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1205, 1, 421, 466, 10002, 5, 'productName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1206, 1, 422, 466, 10002, 6, 'periods', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1207, 1, 423, 466, 10002, 7, 'productRate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1208, 1, 424, 466, 10002, 8, 'payWay', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1209, 1, 425, 466, 10002, 9, 'financingAmount', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1210, 1, 427, 466, 10002, 11, 'orderStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1211, 1, 452, 466, 10002, 10, 'orderLink\r\n', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1212, 1, 311, 399, 10002, 1, 'collectMoneyDealStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1213, 1, 312, 399, 10002, 2, 'dealTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1214, 1, 313, 399, 10002, 3, 'dealerName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1215, 1, 314, 399, 10002, 4, 'applicationType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1216, 1, 315, 399, 10002, 5, 'totalPayment', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1217, 1, 172, 280, 10002, 1, 'orderLink', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1218, 1, 316, 399, 10002, 6, 'accountName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1219, 1, 173, 280, 10002, 2, 'orderStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1220, 1, 317, 399, 10002, 7, 'operatorTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1221, 1, 174, 280, 10002, 3, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1222, 1, 318, 399, 10002, 8, 'operatorName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1223, 1, 175, 280, 10002, 4, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1224, 1, 176, 280, 10002, 5, 'receiveDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1225, 1, 468, 399, 10002, 9, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1226, 1, 177, 280, 10002, 6, 'approvalDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1227, 1, 178, 280, 10002, 7, 'province', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1228, 1, 179, 280, 10002, 8, 'city', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1229, 1, 180, 280, 10002, 9, 'orderType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1230, 1, 181, 280, 10002, 10, 'productName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1231, 1, 182, 280, 10002, 11, 'personalName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1232, 1, 183, 280, 10002, 12, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1233, 1, 184, 280, 10002, 13, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1234, 1, 462, 280, 10002, 14, 'detainedDays', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1235, 1, 232, 331, 10002, 1, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1236, 1, 233, 331, 10002, 2, 'clientNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1237, 1, 234, 331, 10002, 3, 'name', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1238, 1, 235, 331, 10002, 4, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1239, 1, 236, 331, 10002, 5, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1240, 1, 237, 331, 10002, 6, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1241, 1, 238, 331, 10002, 7, 'contractDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1242, 1, 239, 331, 10002, 8, 'principalReceivable', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1243, 1, 240, 331, 10002, 9, 'interestReceivable', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1244, 1, 241, 331, 10002, 10, 'penaltyReceivable', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1245, 1, 242, 331, 10002, 11, 'productRate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1246, 1, 243, 331, 10002, 12, 'settlementChannel', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1247, 1, 244, 331, 10002, 13, 'companyChinaName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1248, 1, 469, 485, 10002, 1, 'personalName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1249, 1, 470, 485, 10002, 2, 'certificateType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1250, 1, 471, 485, 10002, 3, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1251, 1, 472, 485, 10002, 4, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1252, 1, 473, 485, 10002, 5, 'intentionalLevel', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1253, 1, 474, 485, 10002, 6, 'city', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1254, 1, 475, 485, 10002, 7, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1255, 1, 476, 485, 10002, 8, 'operator', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1256, 1, 477, 486, 10002, 1, 'personalName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1257, 1, 478, 486, 10002, 2, 'certificateType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1258, 1, 479, 486, 10002, 3, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1259, 1, 480, 486, 10002, 4, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1260, 1, 481, 486, 10002, 5, 'intentionalLevel', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1261, 1, 482, 486, 10002, 6, 'city', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1262, 1, 483, 486, 10002, 7, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1263, 1, 484, 486, 10002, 8, 'operator', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1264, 1, 485, 487, 10002, 1, 'personalName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1265, 1, 486, 487, 10002, 2, 'certificateType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1266, 1, 487, 487, 10002, 3, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1267, 1, 488, 487, 10002, 4, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1268, 1, 489, 487, 10002, 5, 'intentionalLevel', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1269, 1, 490, 487, 10002, 6, 'city', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1270, 1, 491, 487, 10002, 7, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1271, 1, 492, 487, 10002, 8, 'operator', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1272, 1, 493, 475, 10002, 1, 'assessmentNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1273, 1, 494, 475, 10002, 2, 'assessmentStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1274, 1, 495, 475, 10002, 3, 'brandName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1275, 1, 496, 475, 10002, 4, 'seriesName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1276, 1, 497, 475, 10002, 5, 'carName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1277, 1, 498, 475, 10002, 6, 'carColor', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1278, 1, 499, 475, 10002, 7, 'carNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1279, 1, 500, 475, 10002, 8, 'frameNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1280, 1, 501, 475, 10002, 9, 'engineNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1281, 1, 502, 475, 10002, 10, 'ownerName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1282, 1, 503, 475, 10002, 11, 'ownPhone', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1283, 1, 504, 475, 10002, 12, 'applicant', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1284, 1, 505, 475, 10002, 13, 'applyTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1285, 1, 506, 475, 10002, 14, 'assessmentPerson', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1286, 1, 507, 476, 10002, 1, 'assessmentNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1287, 1, 508, 476, 10002, 2, 'brandName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1288, 1, 509, 476, 10002, 3, 'seriesName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1289, 1, 510, 476, 10002, 4, 'carName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1290, 1, 511, 476, 10002, 5, 'carColor', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1291, 1, 512, 476, 10002, 6, 'carNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1292, 1, 513, 476, 10002, 7, 'frameNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1293, 1, 514, 476, 10002, 8, 'engineNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1294, 1, 515, 476, 10002, 9, 'ownerName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1295, 1, 516, 476, 10002, 10, 'ownPhone', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1296, 1, 517, 477, 10002, 1, 'assessmentNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1297, 1, 518, 477, 10002, 2, 'assessmentStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1298, 1, 519, 477, 10002, 3, 'brandName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1299, 1, 520, 477, 10002, 4, 'seriesName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1300, 1, 521, 477, 10002, 5, 'carName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1301, 1, 522, 477, 10002, 6, 'carColor', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1302, 1, 523, 477, 10002, 7, 'carNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1303, 1, 524, 477, 10002, 8, 'frameNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1304, 1, 525, 477, 10002, 9, 'engineNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1305, 1, 526, 477, 10002, 10, 'ownerName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1306, 1, 527, 477, 10002, 11, 'ownPhone', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1307, 1, 528, 477, 10002, 12, 'carSituation', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1308, 1, 529, 477, 10002, 13, 'evaluation', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1309, 1, 530, 477, 10002, 14, 'assessmentTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1310, 1, 531, 477, 10002, 15, 'assessmentPerson', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1311, 1, 543, 482, 10002, 1, 'placingStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1312, 1, 544, 482, 10002, 2, 'placingOperateTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1313, 1, 545, 482, 10002, 3, 'placingOperator', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1314, 1, 546, 482, 10002, 4, 'assessmentNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1315, 1, 547, 482, 10002, 5, 'brandName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1316, 1, 548, 482, 10002, 6, 'seriesName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1317, 1, 549, 482, 10002, 7, 'carName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1318, 1, 550, 482, 10002, 8, 'carColor', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1319, 1, 551, 482, 10002, 9, 'carNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1320, 1, 552, 482, 10002, 10, 'frameNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1321, 1, 553, 482, 10002, 11, 'engineNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1322, 1, 554, 482, 10002, 12, 'ownerName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1323, 1, 555, 482, 10002, 13, 'ownPhone', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1324, 1, 532, 481, 10002, 1, 'warehousingStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1325, 1, 533, 481, 10002, 2, 'warehousingOperateTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1326, 1, 534, 481, 10002, 3, 'warehousingOperator', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1327, 1, 535, 481, 10002, 4, 'assessmentNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1328, 1, 536, 481, 10002, 5, 'brandName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1329, 1, 537, 481, 10002, 6, 'seriesName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1330, 1, 538, 481, 10002, 7, 'carName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1331, 1, 539, 481, 10002, 8, 'carColor', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1332, 1, 540, 481, 10002, 9, 'carNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1333, 1, 541, 481, 10002, 10, 'frameNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1334, 1, 542, 481, 10002, 11, 'engineNo', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1335, 1, 297, 390, 10002, 1, 'processStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1336, 1, 298, 390, 10002, 2, 'processTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1337, 1, 299, 390, 10002, 3, 'processName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1338, 1, 300, 390, 10002, 4, 'refundType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1339, 1, 301, 390, 10002, 5, 'refundTotalAmount', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1340, 1, 302, 390, 10002, 6, 'customerName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1341, 1, 303, 390, 10002, 7, 'depositBank', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1342, 1, 304, 390, 10002, 8, 'cardNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1343, 1, 305, 390, 10002, 9, 'operateTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1344, 1, 306, 390, 10002, 10, 'operator', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1345, 1, 458, 390, 10002, 11, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1346, 1, 319, 405, 10002, 1, 'processStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1347, 1, 320, 405, 10002, 2, 'processTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1348, 1, 321, 405, 10002, 3, 'processName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1349, 1, 322, 405, 10002, 4, 'refundType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1350, 1, 323, 405, 10002, 5, 'refundTotalAmount', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1351, 1, 324, 405, 10002, 6, 'customerName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1352, 1, 325, 405, 10002, 7, 'operateTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1353, 1, 326, 405, 10002, 8, 'operator', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1354, 1, 467, 405, 10002, 9, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1355, 1, 327, 412, 10002, 1, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1356, 1, 328, 412, 10002, 2, 'clientNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1357, 1, 329, 412, 10002, 3, 'name', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1358, 1, 330, 412, 10002, 4, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1359, 1, 331, 412, 10002, 5, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1360, 1, 332, 412, 10002, 6, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1361, 1, 333, 412, 10002, 7, 'contractDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1362, 1, 334, 412, 10002, 8, 'principalReceivable', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1363, 1, 335, 412, 10002, 9, 'interestReceivable', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1364, 1, 336, 412, 10002, 10, 'penaltyReceivable', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1365, 1, 337, 412, 10002, 11, 'productRate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1366, 1, 338, 412, 10002, 12, 'settlementChannel', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1367, 1, 339, 412, 10002, 13, 'companyChinaName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1368, 1, 453, 412, 10002, 14, 'manageFeeReceivable', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1369, 1, 454, 412, 10002, 15, 'sumReceivable', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1370, 1, 387, 456, 10002, 1, 'openAccountDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1371, 1, 388, 456, 10002, 2, 'accountType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1372, 1, 389, 456, 10002, 3, 'clientNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1373, 1, 390, 456, 10002, 4, 'name', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1374, 1, 391, 456, 10002, 5, 'certificateNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1375, 1, 392, 456, 10002, 6, 'reservedPhoneNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1376, 1, 393, 463, 10002, 1, 'paymentDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1377, 1, 394, 463, 10002, 2, 'clientNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1378, 1, 395, 463, 10002, 3, 'cardNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1379, 1, 396, 463, 10002, 4, 'clientName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1380, 1, 397, 463, 10002, 5, 'depositBank', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1381, 1, 398, 463, 10002, 6, 'paymentAmount', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1382, 1, 399, 463, 10002, 7, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1383, 1, 400, 463, 10002, 8, 'tradingStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1384, 1, 401, 463, 10002, 9, 'failReason', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1385, 1, 402, 463, 10002, 10, 'operateName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1386, 1, 105, 192, 10002, 1, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1387, 1, 106, 192, 10002, 2, 'recorderName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1388, 1, 107, 192, 10002, 3, 'deptName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1389, 1, 108, 192, 10002, 4, 'transferName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1390, 1, 109, 192, 10002, 5, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1391, 1, 110, 192, 10002, 6, 'personalName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1392, 1, 111, 192, 10002, 7, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1393, 1, 112, 192, 10002, 8, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1394, 1, 113, 192, 10002, 9, 'productName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1395, 1, 114, 192, 10002, 10, 'periods', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1396, 1, 115, 192, 10002, 11, 'orderLink', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1397, 1, 116, 192, 10002, 12, 'orderStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1398, 1, 6, 9, 10002, 1, 'userUsername', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1399, 1, 7, 9, 10002, 2, 'userRealname', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1400, 1, 8, 9, 10002, 3, 'deptName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1401, 1, 9, 9, 10002, 4, 'status', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1402, 1, 10, 9, 10002, 5, 'userPhone', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1403, 1, 11, 9, 10002, 6, 'userRemark', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1404, 1, 12, 9, 10002, 7, 'operatorName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1405, 1, 13, 9, 10002, 8, 'operateTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1406, 1, 416, 9, 10002, 9, 'userManager', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1407, 1, 185, 295, 10002, 1, 'orderLink', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1408, 1, 186, 295, 10002, 2, 'orderStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1409, 1, 187, 295, 10002, 3, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1410, 1, 188, 295, 10002, 4, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1411, 1, 189, 295, 10002, 5, 'intoPoolDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1412, 1, 190, 295, 10002, 6, 'province', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1413, 1, 191, 295, 10002, 7, 'city', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1414, 1, 192, 295, 10002, 8, 'orderType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1415, 1, 193, 295, 10002, 9, 'productName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1416, 1, 194, 295, 10002, 10, 'personalName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1417, 1, 195, 295, 10002, 11, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1418, 1, 196, 295, 10002, 12, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1419, 1, 128, 244, 10002, 1, 'orderLink', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1420, 1, 129, 244, 10002, 2, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1421, 1, 130, 244, 10002, 3, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1422, 1, 131, 244, 10002, 4, 'intoPoolDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1423, 1, 132, 244, 10002, 5, 'province', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1424, 1, 133, 244, 10002, 6, 'city', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1425, 1, 134, 244, 10002, 7, 'orderType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1426, 1, 135, 244, 10002, 8, 'productName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1427, 1, 136, 244, 10002, 9, 'personalName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1428, 1, 137, 244, 10002, 10, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1429, 1, 138, 244, 10002, 11, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1430, 1, 463, 244, 10002, 12, 'detainedDays', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1431, 1, 139, 253, 10002, 1, 'orderLink', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1432, 1, 140, 253, 10002, 2, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1433, 1, 141, 253, 10002, 3, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1434, 1, 142, 253, 10002, 4, 'intoPoolDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1435, 1, 143, 253, 10002, 5, 'province', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1436, 1, 144, 253, 10002, 6, 'city', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1437, 1, 145, 253, 10002, 7, 'orderType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1438, 1, 146, 253, 10002, 8, 'productName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1439, 1, 147, 253, 10002, 9, 'personalName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1440, 1, 148, 253, 10002, 10, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1441, 1, 149, 253, 10002, 11, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1442, 1, 464, 253, 10002, 12, 'detainedDays', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1443, 1, 428, 340, 10002, 1, 'remitAmount', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1444, 1, 429, 340, 10002, 2, 'periods', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1445, 1, 430, 340, 10002, 3, 'orderLink', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1446, 1, 431, 340, 10002, 4, 'orderStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1447, 1, 432, 340, 10002, 5, 'applyDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1448, 1, 433, 340, 10002, 6, 'name', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1449, 1, 434, 340, 10002, 7, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1450, 1, 435, 340, 10002, 8, 'mobileNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1451, 1, 436, 340, 10002, 9, 'orderCreateTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1452, 1, 437, 340, 10002, 10, 'contractDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1453, 1, 438, 340, 10002, 11, 'remitRemark', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1454, 1, 439, 340, 10002, 12, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1455, 1, 440, 348, 10002, 1, 'remitAmount', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1456, 1, 441, 348, 10002, 2, 'periods', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1457, 1, 442, 348, 10002, 3, 'orderLink', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1458, 1, 443, 348, 10002, 4, 'orderStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1459, 1, 444, 348, 10002, 5, 'applyDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1460, 1, 445, 348, 10002, 6, 'name', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1461, 1, 446, 348, 10002, 7, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1462, 1, 447, 348, 10002, 8, 'mobileNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1463, 1, 448, 348, 10002, 9, 'orderCreateTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1464, 1, 449, 348, 10002, 10, 'contractDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1465, 1, 450, 348, 10002, 11, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1466, 1, 451, 348, 10002, 12, 'remitRemark', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1467, 1, 281, 376, 10002, 1, 'isUploadFile', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1468, 1, 282, 376, 10002, 2, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1469, 1, 283, 376, 10002, 3, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1470, 1, 284, 376, 10002, 4, 'orderType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1471, 1, 285, 376, 10002, 5, 'productName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1472, 1, 286, 376, 10002, 6, 'personalName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1473, 1, 287, 376, 10002, 7, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1474, 1, 288, 376, 10002, 8, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1475, 1, 307, 392, 10002, 1, 'fileName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1476, 1, 308, 392, 10002, 2, 'uploadTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1477, 1, 309, 392, 10002, 3, 'operatorName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1478, 1, 310, 392, 10002, 4, 'remark', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1479, 1, 1, 69, 10002, 1, 'operatorName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1480, 1, 2, 69, 10002, 2, 'operateTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1481, 1, 3, 69, 10002, 3, 'mysqlName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1482, 1, 4, 69, 10002, 4, 'mongdbName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1483, 1, 5, 69, 10002, 5, 'type', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1484, 1, 31, 77, 10002, 1, 'paramCode', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1485, 1, 32, 77, 10002, 2, 'paramName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1486, 1, 33, 77, 10002, 3, 'paramValue', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1487, 1, 34, 77, 10002, 4, 'paramStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1488, 1, 35, 77, 10002, 5, 'paramRemark', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1489, 1, 197, 304, 10002, 1, 'orderLink', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1490, 1, 198, 304, 10002, 2, 'orderStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1491, 1, 199, 304, 10002, 3, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1492, 1, 200, 304, 10002, 4, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1493, 1, 201, 304, 10002, 5, 'intoPoolDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1494, 1, 202, 304, 10002, 6, 'province', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1495, 1, 203, 304, 10002, 7, 'city', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1496, 1, 204, 304, 10002, 8, 'orderType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1497, 1, 205, 304, 10002, 9, 'productName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1498, 1, 206, 304, 10002, 10, 'personalName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1499, 1, 207, 304, 10002, 11, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1500, 1, 208, 304, 10002, 12, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1501, 1, 41, 34, 10002, 1, 'resoInitName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1502, 1, 42, 34, 10002, 2, 'resoName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1503, 1, 43, 34, 10002, 3, 'resoInitIcon', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1504, 1, 44, 34, 10002, 4, 'resoIcon', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1505, 1, 45, 34, 10002, 5, 'moduleId', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1506, 1, 46, 34, 10002, 6, 'resoStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1507, 1, 47, 34, 10002, 7, 'resoRemark', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1508, 1, 150, 262, 10002, 1, 'orderLink', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1509, 1, 151, 262, 10002, 2, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1510, 1, 152, 262, 10002, 3, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1511, 1, 153, 262, 10002, 4, 'intoPoolDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1512, 1, 154, 262, 10002, 5, 'province', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1513, 1, 155, 262, 10002, 6, 'city', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1514, 1, 156, 262, 10002, 7, 'orderType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1515, 1, 157, 262, 10002, 8, 'productName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1516, 1, 158, 262, 10002, 9, 'personalName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1517, 1, 159, 262, 10002, 10, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1518, 1, 160, 262, 10002, 11, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1519, 1, 465, 262, 10002, 12, 'detainedDays', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1520, 1, 340, 420, 10002, 1, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1521, 1, 341, 420, 10002, 2, 'clientNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1522, 1, 342, 420, 10002, 3, 'name', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1523, 1, 343, 420, 10002, 4, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1524, 1, 344, 420, 10002, 5, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1525, 1, 345, 420, 10002, 6, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1526, 1, 346, 420, 10002, 7, 'contractDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1527, 1, 347, 420, 10002, 8, 'principalReceivable', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1528, 1, 348, 420, 10002, 9, 'interestReceivable', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1529, 1, 349, 420, 10002, 10, 'penaltyReceivable', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1530, 1, 350, 420, 10002, 11, 'productRate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1531, 1, 351, 420, 10002, 12, 'settlementChannel', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1532, 1, 352, 420, 10002, 13, 'companyChinaName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1533, 1, 21, 63, 10002, 1, 'jobName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1534, 1, 22, 63, 10002, 2, 'jobGroup', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1535, 1, 23, 63, 10002, 3, 'jobDescribe', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1536, 1, 24, 63, 10002, 4, 'jobClassName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1537, 1, 25, 63, 10002, 5, 'triggerName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1538, 1, 26, 63, 10002, 6, 'triggerGroup', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1539, 1, 27, 63, 10002, 7, 'nextExecutionTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1540, 1, 28, 63, 10002, 8, 'expression', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1541, 1, 29, 63, 10002, 9, 'triggerState', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1542, 1, 30, 63, 10002, 10, 'timeZone', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1543, 1, 14, 57, 10002, 2, 'realName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1544, 1, 15, 57, 10002, 3, 'clientIp', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1545, 1, 16, 57, 10002, 4, 'exeMethod', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1546, 1, 17, 57, 10002, 5, 'logRemark', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1547, 1, 18, 57, 10002, 6, 'exeTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1548, 1, 19, 57, 10002, 7, 'exeType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1549, 1, 20, 57, 10002, 1, 'operateTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1550, 1, 36, 20, 10002, 1, 'roleStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1551, 1, 37, 20, 10002, 2, 'roleName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1552, 1, 38, 20, 10002, 3, 'roleRemark', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1553, 1, 39, 20, 10002, 4, 'realName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1554, 1, 40, 20, 10002, 5, 'operateTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1567, 1, 362, 435, 10002, 1, 'orderId', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1568, 1, 363, 435, 10002, 2, 'clientNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1569, 1, 364, 435, 10002, 3, 'customerName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1570, 1, 365, 435, 10002, 4, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1571, 1, 366, 435, 10002, 5, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1572, 1, 367, 435, 10002, 6, 'contractDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1573, 1, 368, 435, 10002, 7, 'settlementDay', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1574, 1, 369, 435, 10002, 8, 'productRate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1575, 1, 370, 435, 10002, 9, 'settlementChannel', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1576, 1, 371, 435, 10002, 10, 'settlementType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1577, 1, 372, 435, 10002, 11, 'companyChinaName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1578, 1, 380, 455, 10002, 1, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1579, 1, 381, 455, 10002, 2, 'refundType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1580, 1, 382, 455, 10002, 3, 'applicationStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1581, 1, 383, 455, 10002, 4, 'customerName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1582, 1, 384, 455, 10002, 5, 'refundTotalAmount', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1583, 1, 385, 455, 10002, 6, 'operateTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1584, 1, 386, 455, 10002, 7, 'operator', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1585, 1, 353, 428, 10002, 1, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1586, 1, 354, 428, 10002, 2, 'clientNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1587, 1, 355, 428, 10002, 3, 'name', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1588, 1, 356, 428, 10002, 4, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1589, 1, 357, 428, 10002, 5, 'mobileMain', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1590, 1, 358, 428, 10002, 6, 'createTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1591, 1, 359, 428, 10002, 7, 'contractDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1592, 1, 360, 428, 10002, 12, 'settlementChannel', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1593, 1, 361, 428, 10002, 13, 'companyChinaName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1594, 1, 459, 428, 10002, 8, 'violateAmount', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1595, 1, 460, 428, 10002, 9, 'advanceRevokeFee', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1596, 1, 461, 428, 10002, 10, 'totalPayment', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1597, 1, 48, 109, 10002, 1, 'type', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1598, 1, 49, 109, 10002, 2, 'first', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1599, 1, 50, 109, 10002, 3, 'second', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1600, 1, 51, 109, 10002, 4, 'crc', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1601, 1, 52, 109, 10002, 5, 'detail', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1602, 1, 53, 109, 10002, 6, 'operator', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1603, 1, 54, 109, 10002, 7, 'operatorTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1604, 1, 455, 474, 10002, 1, 'templateName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1605, 1, 456, 474, 10002, 2, 'operator', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1606, 1, 63, 144, 10002, 1, 'name', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1607, 1, 64, 144, 10002, 2, 'isNecessary', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1608, 1, 84, 91, 10002, 1, 'fileName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1609, 1, 85, 91, 10002, 2, 'uploadTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1610, 1, 86, 91, 10002, 3, 'operatorName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1611, 1, 87, 91, 10002, 4, 'remark', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1612, 1, 373, 447, 10002, 1, 'orderNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1613, 1, 374, 447, 10002, 2, 'applicationType', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1614, 1, 375, 447, 10002, 3, 'approvalStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1615, 1, 376, 447, 10002, 4, 'accountName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1616, 1, 377, 447, 10002, 5, 'totalPayment', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1617, 1, 378, 447, 10002, 6, 'operatorTime', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1618, 1, 379, 447, 10002, 7, 'userUserName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1868, 1, 268, 356, 10002, 1, 'approvalDate', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1869, 1, 269, 356, 10002, 2, 'province', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1870, 1, 270, 356, 10002, 3, 'city', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1871, 1, 271, 356, 10002, 4, 'dot', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1872, 1, 272, 356, 10002, 5, 'salesmanName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1873, 1, 273, 356, 10002, 6, 'personalName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1874, 1, 274, 356, 10002, 7, 'idCard', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1875, 1, 275, 356, 10002, 8, 'approveStatus', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1876, 1, 276, 356, 10002, 9, 'isDeliveryCar', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1877, 1, 277, 356, 10002, 10, 'refuseResource', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1878, 1, 278, 356, 10002, 11, 'refuseDetails', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1879, 1, 279, 356, 10002, 12, 'remark', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1880, 1, 280, 356, 10002, 13, 'approvalPersonal', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1960, 1, 55, 175, 10002, 1, 'companyChinaname', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1961, 1, 56, 175, 10002, 2, 'companyProvince', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1962, 1, 57, 175, 10002, 3, 'companyCity', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1963, 1, 58, 175, 10002, 4, 'bankAccount', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1964, 1, 59, 175, 10002, 5, 'depositBank', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1965, 1, 60, 175, 10002, 6, 'cardNumber', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1966, 1, 61, 175, 10002, 7, 'branchName', 1, NULL);
INSERT INTO `sys_user_column` VALUES (1967, 1, 62, 175, 10002, 8, 'companyStatus', 1, NULL);

-- ----------------------------
-- Table structure for sys_user_device
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_device`;
CREATE TABLE `sys_user_device`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `user_id` bigint(32) DEFAULT NULL COMMENT '用户ID',
  `device_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备编号',
  `device_type` int(11) DEFAULT NULL COMMENT '设备类型',
  `device_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备名称',
  `device_validate` int(11) DEFAULT NULL COMMENT '是否开启设备验证',
  `device_status` int(11) DEFAULT NULL COMMENT '状态',
  `device_mac` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备识别码',
  `operator` bigint(32) DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime(0) DEFAULT NULL COMMENT '操作日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录设备' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_device
-- ----------------------------
INSERT INTO `sys_user_device` VALUES (1, 13, NULL, 10020, NULL, 10002, 10002, NULL, 1, '2018-06-23 11:16:20');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint(32) DEFAULT NULL COMMENT '关联ID',
  `operator_time` datetime(0) DEFAULT NULL,
  `operator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (39, 1, 1, '2018-06-23 11:21:32', '1');
INSERT INTO `sys_user_role` VALUES (40, 1, 2, '2018-06-23 11:21:32', '1');
INSERT INTO `sys_user_role` VALUES (41, 1, 3, '2018-06-23 11:21:32', '1');

-- ----------------------------
-- Function structure for next_seq
-- ----------------------------
DROP FUNCTION IF EXISTS `next_seq`;
delimiter ;;
CREATE DEFINER=`root`@`%` FUNCTION `next_seq`(`seq_name` varchar(20),`seqLength` int) RETURNS varchar(20) CHARSET utf8
BEGIN
	declare current integer;
    set current = 0;
    select t.current_seq into current from sys_seq t where t.seq_name =seq_name for update;
    update sys_seq t set t.current_seq = t.current_seq + 1 where t.seq_name = seq_name;
    set current = current + 1;
				  
        IF seq_name ='order_no' && current >9999 THEN   
            SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = "今日订单编号已到上限";
        END IF; 
	RETURN LPAD(current,seqLength,'0');
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
