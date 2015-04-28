/*
Navicat MySQL Data Transfer

Source Server         : 5306
Source Server Version : 50524
Source Host           : localhost:5306
Source Database       : soofac

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2014-11-10 15:57:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `address_city`
-- ----------------------------
DROP TABLE IF EXISTS `address_city`;
CREATE TABLE `address_city` (
  `id` int(5) NOT NULL,
  `province_id` int(3) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `en_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address_city
-- ----------------------------
INSERT INTO `address_city` VALUES ('1', '1', '辖市区', 'municipal_district');
INSERT INTO `address_city` VALUES ('2', '2', '辖市区', 'municipal_district');
INSERT INTO `address_city` VALUES ('3', '3', '石家庄市', 'shi_jia_zhuang');
INSERT INTO `address_city` VALUES ('4', '3', '唐山市', 'tang_shan');
INSERT INTO `address_city` VALUES ('5', '3', '秦皇岛市', 'qin_huang_dao');
INSERT INTO `address_city` VALUES ('6', '3', '邯郸市', 'han_dan');
INSERT INTO `address_city` VALUES ('7', '3', '邢台市', 'xing_tai');
INSERT INTO `address_city` VALUES ('8', '3', '保定市', 'bao_ding');
INSERT INTO `address_city` VALUES ('9', '3', '张家口市', 'zhang_jia_kou');
INSERT INTO `address_city` VALUES ('10', '3', '承德市', 'cheng_de');
INSERT INTO `address_city` VALUES ('11', '3', '沧州市', 'cang_zhou');
INSERT INTO `address_city` VALUES ('12', '3', '廊坊市', 'lang_fang');
INSERT INTO `address_city` VALUES ('13', '3', '衡水市', 'heng_shui');
INSERT INTO `address_city` VALUES ('14', '4', '太原市', 'tai_yuan');
INSERT INTO `address_city` VALUES ('15', '4', '大同市', 'da_tong');
INSERT INTO `address_city` VALUES ('16', '4', '阳泉市', 'yang_quan');
INSERT INTO `address_city` VALUES ('17', '4', '长治市', 'chang_zhi');
INSERT INTO `address_city` VALUES ('18', '4', '晋城市', 'jin_cheng');
INSERT INTO `address_city` VALUES ('19', '4', '朔州市', 'shuo_zhou');
INSERT INTO `address_city` VALUES ('20', '4', '晋中市', 'jin_zhong');
INSERT INTO `address_city` VALUES ('21', '4', '运城市', 'yun_cheng');
INSERT INTO `address_city` VALUES ('22', '4', '忻州市', 'xin_zhou');
INSERT INTO `address_city` VALUES ('23', '4', '临汾市', 'lin_fen');
INSERT INTO `address_city` VALUES ('24', '4', '吕梁市', 'lv_liang');
INSERT INTO `address_city` VALUES ('25', '5', '呼和浩特市', 'hu_he_hao_te');
INSERT INTO `address_city` VALUES ('26', '5', '包头市', 'bao_tou');
INSERT INTO `address_city` VALUES ('27', '5', '乌海市', 'wu_hai');
INSERT INTO `address_city` VALUES ('28', '5', '赤峰市', 'chi_feng');
INSERT INTO `address_city` VALUES ('29', '5', '通辽市', 'tong_liao');
INSERT INTO `address_city` VALUES ('30', '5', '鄂尔多斯市', 'e_er_duo_si');
INSERT INTO `address_city` VALUES ('31', '5', '呼伦贝尔市', 'hu_lun_bei_er');
INSERT INTO `address_city` VALUES ('32', '5', '巴彦淖尔市', 'ba_yan_nao_er');
INSERT INTO `address_city` VALUES ('33', '5', '乌兰察布市', 'wu_lan_cha_bu');
INSERT INTO `address_city` VALUES ('34', '5', '兴安盟', 'xing_an_meng');
INSERT INTO `address_city` VALUES ('35', '5', '锡林郭勒盟', 'xi_lin_guo_le_meng');
INSERT INTO `address_city` VALUES ('36', '5', '阿拉善盟', 'a_la_shan_meng');
INSERT INTO `address_city` VALUES ('37', '6', '沈阳市', 'shen_yang');
INSERT INTO `address_city` VALUES ('38', '6', '大连市', 'da_lian');
INSERT INTO `address_city` VALUES ('39', '6', '鞍山市', 'an_shan');
INSERT INTO `address_city` VALUES ('40', '6', '抚顺市', 'fu_shun');
INSERT INTO `address_city` VALUES ('41', '6', '本溪市', 'ben_xi');
INSERT INTO `address_city` VALUES ('42', '6', '丹东市', 'dan_dong');
INSERT INTO `address_city` VALUES ('43', '6', '锦州市', 'jin_zhou');
INSERT INTO `address_city` VALUES ('44', '6', '营口市', 'ying_kou');
INSERT INTO `address_city` VALUES ('45', '6', '阜新市', 'fu_xin');
INSERT INTO `address_city` VALUES ('46', '6', '辽阳市', 'liao_yang');
INSERT INTO `address_city` VALUES ('47', '6', '盘锦市', 'pan_jin');
INSERT INTO `address_city` VALUES ('48', '6', '铁岭市', 'tie_ling');
INSERT INTO `address_city` VALUES ('49', '6', '朝阳市', 'chao_yang');
INSERT INTO `address_city` VALUES ('50', '6', '葫芦岛市', 'hu_lu_dao');
INSERT INTO `address_city` VALUES ('51', '7', '长春市', 'chang_chun');
INSERT INTO `address_city` VALUES ('52', '7', '吉林市', 'ji_lin');
INSERT INTO `address_city` VALUES ('53', '7', '四平市', 'si_ping');
INSERT INTO `address_city` VALUES ('54', '7', '辽源市', 'liao_yuan');
INSERT INTO `address_city` VALUES ('55', '7', '通化市', 'tong_hua');
INSERT INTO `address_city` VALUES ('56', '7', '白山市', 'bai_shan');
INSERT INTO `address_city` VALUES ('57', '7', '松原市', 'song_yuan');
INSERT INTO `address_city` VALUES ('58', '7', '白城市', 'bai_cheng');
INSERT INTO `address_city` VALUES ('59', '7', '延边朝鲜族自治州', 'yan_bian_chao_county_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('60', '8', '哈尔滨市', 'ha_er_bin');
INSERT INTO `address_city` VALUES ('61', '8', '齐齐哈尔市', 'qi_qi_ha_er');
INSERT INTO `address_city` VALUES ('62', '8', '鸡西市', 'ji_xi');
INSERT INTO `address_city` VALUES ('63', '8', '鹤岗市', 'he_gang');
INSERT INTO `address_city` VALUES ('64', '8', '双鸭山市', 'shuang_ya_shan');
INSERT INTO `address_city` VALUES ('65', '8', '大庆市', 'da_qing');
INSERT INTO `address_city` VALUES ('66', '8', '伊春市', 'yi_chun');
INSERT INTO `address_city` VALUES ('67', '8', '佳木斯市', 'jia_mu_si');
INSERT INTO `address_city` VALUES ('68', '8', '七台河市', 'qi_tai_he');
INSERT INTO `address_city` VALUES ('69', '8', '牡丹江市', 'mu_dan_jiang');
INSERT INTO `address_city` VALUES ('70', '8', '黑河市', 'hei_he');
INSERT INTO `address_city` VALUES ('71', '8', '绥化市', 'sui_hua');
INSERT INTO `address_city` VALUES ('72', '8', '大兴安岭地区', 'da_xing_an_ling_region');
INSERT INTO `address_city` VALUES ('73', '9', '上海市', 'shang_hai');
INSERT INTO `address_city` VALUES ('74', '10', '南京市', 'nan_jing');
INSERT INTO `address_city` VALUES ('75', '10', '无锡市', 'wu_xi');
INSERT INTO `address_city` VALUES ('76', '10', '徐州市', 'xu_zhou');
INSERT INTO `address_city` VALUES ('77', '10', '常州市', 'chang_zhou');
INSERT INTO `address_city` VALUES ('78', '10', '苏州市', 'su_zhou');
INSERT INTO `address_city` VALUES ('79', '10', '南通市', 'nan_tong');
INSERT INTO `address_city` VALUES ('80', '10', '连云港市', 'lian_yun_gang');
INSERT INTO `address_city` VALUES ('81', '10', '淮安市', 'huai_an');
INSERT INTO `address_city` VALUES ('82', '10', '盐城市', 'yan_cheng');
INSERT INTO `address_city` VALUES ('83', '10', '扬州市', 'yang_zhou');
INSERT INTO `address_city` VALUES ('84', '10', '镇江市', 'zhen_jiang');
INSERT INTO `address_city` VALUES ('85', '10', '泰州市', 'tai_zhou');
INSERT INTO `address_city` VALUES ('86', '10', '宿迁市', 'su_qian');
INSERT INTO `address_city` VALUES ('87', '11', '杭州市', 'hang_zhou');
INSERT INTO `address_city` VALUES ('88', '11', '宁波市', 'ning_bo');
INSERT INTO `address_city` VALUES ('89', '11', '温州市', 'wen_zhou');
INSERT INTO `address_city` VALUES ('90', '11', '嘉兴市', 'jia_xing');
INSERT INTO `address_city` VALUES ('91', '11', '湖州市', 'hu_zhou');
INSERT INTO `address_city` VALUES ('92', '11', '绍兴市', 'shao_xing');
INSERT INTO `address_city` VALUES ('93', '11', '金华市', 'jin_hua');
INSERT INTO `address_city` VALUES ('94', '11', '衢州市', 'zuo_zhou');
INSERT INTO `address_city` VALUES ('95', '11', '舟山市', 'zhou_shan');
INSERT INTO `address_city` VALUES ('96', '11', '台州市', 'tai_zhou');
INSERT INTO `address_city` VALUES ('97', '11', '丽水市', 'li_shui');
INSERT INTO `address_city` VALUES ('98', '12', '合肥市', 'he_fei');
INSERT INTO `address_city` VALUES ('99', '12', '芜湖市', 'wu_hu');
INSERT INTO `address_city` VALUES ('100', '12', '蚌埠市', 'bang_bu');
INSERT INTO `address_city` VALUES ('101', '12', '淮南市', 'huai_nan');
INSERT INTO `address_city` VALUES ('102', '12', '马鞍山市', 'ma_an_shan');
INSERT INTO `address_city` VALUES ('103', '12', '淮北市', 'huai_bei');
INSERT INTO `address_city` VALUES ('104', '12', '铜陵市', 'tong_ling');
INSERT INTO `address_city` VALUES ('105', '12', '安庆市', 'an_qing');
INSERT INTO `address_city` VALUES ('106', '12', '黄山市', 'huang_shan');
INSERT INTO `address_city` VALUES ('107', '12', '滁州市', 'chu_zhou');
INSERT INTO `address_city` VALUES ('108', '12', '阜阳市', 'fu_yang');
INSERT INTO `address_city` VALUES ('109', '12', '宿州市', 'su_zhou');
INSERT INTO `address_city` VALUES ('110', '12', '六安市', 'liu_an');
INSERT INTO `address_city` VALUES ('111', '12', '亳州市', 'zuo_zhou');
INSERT INTO `address_city` VALUES ('112', '12', '池州市', 'chi_zhou');
INSERT INTO `address_city` VALUES ('113', '12', '宣城市', 'xuan_cheng');
INSERT INTO `address_city` VALUES ('114', '13', '福州市', 'fu_zhou');
INSERT INTO `address_city` VALUES ('115', '13', '厦门市', 'xia_men');
INSERT INTO `address_city` VALUES ('116', '13', '莆田市', 'pu_tian');
INSERT INTO `address_city` VALUES ('117', '13', '三明市', 'san_ming');
INSERT INTO `address_city` VALUES ('118', '13', '泉州市', 'quan_zhou');
INSERT INTO `address_city` VALUES ('119', '13', '漳州市', 'zhang_zhou');
INSERT INTO `address_city` VALUES ('120', '13', '南平市', 'nan_ping');
INSERT INTO `address_city` VALUES ('121', '13', '龙岩市', 'long_yan');
INSERT INTO `address_city` VALUES ('122', '13', '宁德市', 'ning_de');
INSERT INTO `address_city` VALUES ('123', '14', '南昌市', 'nan_chang');
INSERT INTO `address_city` VALUES ('124', '14', '景德镇市', 'jing_de_zhen');
INSERT INTO `address_city` VALUES ('125', '14', '萍乡市', 'ping_countyg');
INSERT INTO `address_city` VALUES ('126', '14', '九江市', 'jiu_jiang');
INSERT INTO `address_city` VALUES ('127', '14', '新余市', 'xin_yu');
INSERT INTO `address_city` VALUES ('128', '14', '鹰潭市', 'ying_tan');
INSERT INTO `address_city` VALUES ('129', '14', '赣州市', 'gan_zhou');
INSERT INTO `address_city` VALUES ('130', '14', '吉安市', 'ji_an');
INSERT INTO `address_city` VALUES ('131', '14', '宜春市', 'yi_chun');
INSERT INTO `address_city` VALUES ('132', '14', '抚州市', 'fu_zhou');
INSERT INTO `address_city` VALUES ('133', '14', '上饶市', 'shang_rao');
INSERT INTO `address_city` VALUES ('134', '15', '济南市', 'ji_nan');
INSERT INTO `address_city` VALUES ('135', '15', '青岛市', 'qing_dao');
INSERT INTO `address_city` VALUES ('136', '15', '淄博市', 'zi_bo');
INSERT INTO `address_city` VALUES ('137', '15', '枣庄市', 'zao_zhuang');
INSERT INTO `address_city` VALUES ('138', '15', '东营市', 'dong_ying');
INSERT INTO `address_city` VALUES ('139', '15', '烟台市', 'yan_tai');
INSERT INTO `address_city` VALUES ('140', '15', '潍坊市', 'wei_fang');
INSERT INTO `address_city` VALUES ('141', '15', '济宁市', 'ji_ning');
INSERT INTO `address_city` VALUES ('142', '15', '泰安市', 'tai_an');
INSERT INTO `address_city` VALUES ('143', '15', '威海市', 'wei_hai');
INSERT INTO `address_city` VALUES ('144', '15', '日照市', 'ri_zhao');
INSERT INTO `address_city` VALUES ('145', '15', '莱芜市', 'lai_wu');
INSERT INTO `address_city` VALUES ('146', '15', '临沂市', 'lin_yi');
INSERT INTO `address_city` VALUES ('147', '15', '德州市', 'de_zhou');
INSERT INTO `address_city` VALUES ('148', '15', '聊城市', 'liao_cheng');
INSERT INTO `address_city` VALUES ('149', '15', '滨州市', 'bin_zhou');
INSERT INTO `address_city` VALUES ('150', '15', '菏泽市', 'he_ze');
INSERT INTO `address_city` VALUES ('151', '16', '郑州市', 'zheng_zhou');
INSERT INTO `address_city` VALUES ('152', '16', '开封市', 'kai_feng');
INSERT INTO `address_city` VALUES ('153', '16', '洛阳市', 'luo_yang');
INSERT INTO `address_city` VALUES ('154', '16', '平顶山市', 'ping_ding_shan');
INSERT INTO `address_city` VALUES ('155', '16', '安阳市', 'an_yang');
INSERT INTO `address_city` VALUES ('156', '16', '鹤壁市', 'he_bi');
INSERT INTO `address_city` VALUES ('157', '16', '新乡市', 'xin_countyg');
INSERT INTO `address_city` VALUES ('158', '16', '焦作市', 'jiao_zuo');
INSERT INTO `address_city` VALUES ('159', '16', '濮阳市', 'zuo_yang');
INSERT INTO `address_city` VALUES ('160', '16', '许昌市', 'xu_chang');
INSERT INTO `address_city` VALUES ('161', '16', '漯河市', 'zuo_he');
INSERT INTO `address_city` VALUES ('162', '16', '三门峡市', 'san_men_xia');
INSERT INTO `address_city` VALUES ('163', '16', '南阳市', 'nan_yang');
INSERT INTO `address_city` VALUES ('164', '16', '商丘市', 'shang_qiu');
INSERT INTO `address_city` VALUES ('165', '16', '信阳市', 'xin_yang');
INSERT INTO `address_city` VALUES ('166', '16', '周口市', 'zhou_kou');
INSERT INTO `address_city` VALUES ('167', '16', '驻马店市', 'zhu_ma_dian');
INSERT INTO `address_city` VALUES ('168', '17', '武汉市', 'wu_han');
INSERT INTO `address_city` VALUES ('169', '17', '黄石市', 'huang_shi');
INSERT INTO `address_city` VALUES ('170', '17', '十堰市', 'shi_yan');
INSERT INTO `address_city` VALUES ('171', '17', '宜昌市', 'yi_chang');
INSERT INTO `address_city` VALUES ('172', '17', '襄阳市', 'countyg_yang');
INSERT INTO `address_city` VALUES ('173', '17', '鄂州市', 'e_zhou');
INSERT INTO `address_city` VALUES ('174', '17', '荆门市', 'jing_men');
INSERT INTO `address_city` VALUES ('175', '17', '孝感市', 'xiao_gan');
INSERT INTO `address_city` VALUES ('176', '17', '荆州市', 'jing_zhou');
INSERT INTO `address_city` VALUES ('177', '17', '黄冈市', 'huang_gang');
INSERT INTO `address_city` VALUES ('178', '17', '咸宁市', 'county_ning');
INSERT INTO `address_city` VALUES ('179', '17', '随州市', 'sui_zhou');
INSERT INTO `address_city` VALUES ('180', '17', '恩施土家族苗族自治州', 'en_shi_tu_jia_zu_miao_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('181', '18', '长沙市', 'chang_sha');
INSERT INTO `address_city` VALUES ('182', '18', '株洲市', 'zhu_zhou');
INSERT INTO `address_city` VALUES ('183', '18', '湘潭市', 'countyg_tan');
INSERT INTO `address_city` VALUES ('184', '18', '衡阳市', 'heng_yang');
INSERT INTO `address_city` VALUES ('185', '18', '邵阳市', 'shao_yang');
INSERT INTO `address_city` VALUES ('186', '18', '岳阳市', 'yue_yang');
INSERT INTO `address_city` VALUES ('187', '18', '常德市', 'chang_de');
INSERT INTO `address_city` VALUES ('188', '18', '张家界市', 'zhang_jia_jie');
INSERT INTO `address_city` VALUES ('189', '18', '益阳市', 'yi_yang');
INSERT INTO `address_city` VALUES ('190', '18', '郴州市', 'chen_zhou');
INSERT INTO `address_city` VALUES ('191', '18', '永州市', 'yong_zhou');
INSERT INTO `address_city` VALUES ('192', '18', '怀化市', 'huai_hua');
INSERT INTO `address_city` VALUES ('193', '18', '娄底市', 'lou_di');
INSERT INTO `address_city` VALUES ('194', '18', '湘西土家族苗族自治州', 'countyg_xi_tu_jia_zu_miao_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('195', '19', '广州市', 'guang_zhou');
INSERT INTO `address_city` VALUES ('196', '19', '韶关市', 'shao_guan');
INSERT INTO `address_city` VALUES ('197', '19', '深圳市', 'shen_zuo');
INSERT INTO `address_city` VALUES ('198', '19', '珠海市', 'zhu_hai');
INSERT INTO `address_city` VALUES ('199', '19', '汕头市', 'shan_tou');
INSERT INTO `address_city` VALUES ('200', '19', '佛山市', 'fo_shan');
INSERT INTO `address_city` VALUES ('201', '19', '江门市', 'jiang_men');
INSERT INTO `address_city` VALUES ('202', '19', '湛江市', 'zhan_jiang');
INSERT INTO `address_city` VALUES ('203', '19', '茂名市', 'mao_ming');
INSERT INTO `address_city` VALUES ('204', '19', '肇庆市', 'zhao_qing');
INSERT INTO `address_city` VALUES ('205', '19', '惠州市', 'hui_zhou');
INSERT INTO `address_city` VALUES ('206', '19', '梅州市', 'mei_zhou');
INSERT INTO `address_city` VALUES ('207', '19', '汕尾市', 'shan_wei');
INSERT INTO `address_city` VALUES ('208', '19', '河源市', 'he_yuan');
INSERT INTO `address_city` VALUES ('209', '19', '阳江市', 'yang_jiang');
INSERT INTO `address_city` VALUES ('210', '19', '清远市', 'qing_yuan');
INSERT INTO `address_city` VALUES ('211', '19', '东莞市', 'dong_zuo');
INSERT INTO `address_city` VALUES ('212', '19', '中山市', 'zhong_shan');
INSERT INTO `address_city` VALUES ('213', '19', '潮州市', 'chao_zhou');
INSERT INTO `address_city` VALUES ('214', '19', '揭阳市', 'jie_yang');
INSERT INTO `address_city` VALUES ('215', '19', '云浮市', 'yun_fu');
INSERT INTO `address_city` VALUES ('216', '20', '南宁市', 'nan_ning');
INSERT INTO `address_city` VALUES ('217', '20', '柳州市', 'liu_zhou');
INSERT INTO `address_city` VALUES ('218', '20', '桂林市', 'gui_lin');
INSERT INTO `address_city` VALUES ('219', '20', '梧州市', 'wu_zhou');
INSERT INTO `address_city` VALUES ('220', '20', '北海市', 'bei_hai');
INSERT INTO `address_city` VALUES ('221', '20', '防城港市', 'fang_cheng_gang');
INSERT INTO `address_city` VALUES ('222', '20', '钦州市', 'qin_zhou');
INSERT INTO `address_city` VALUES ('223', '20', '贵港市', 'gui_gang');
INSERT INTO `address_city` VALUES ('224', '20', '玉林市', 'yu_lin');
INSERT INTO `address_city` VALUES ('225', '20', '百色市', 'bai_se');
INSERT INTO `address_city` VALUES ('226', '20', '贺州市', 'he_zhou');
INSERT INTO `address_city` VALUES ('227', '20', '河池市', 'he_chi');
INSERT INTO `address_city` VALUES ('228', '20', '来宾市', 'lai_bin');
INSERT INTO `address_city` VALUES ('229', '20', '崇左市', 'chong_zuo');
INSERT INTO `address_city` VALUES ('230', '21', '海口市', 'hai_kou');
INSERT INTO `address_city` VALUES ('231', '21', '三亚市', 'san_ya');
INSERT INTO `address_city` VALUES ('232', '21', '三沙市', 'san_sha');
INSERT INTO `address_city` VALUES ('233', '22', '重庆市', 'zhong_qing');
INSERT INTO `address_city` VALUES ('234', '23', '成都市', 'cheng_du');
INSERT INTO `address_city` VALUES ('235', '23', '自贡市', 'zi_gong');
INSERT INTO `address_city` VALUES ('236', '23', '攀枝花市', 'pan_zhi_hua');
INSERT INTO `address_city` VALUES ('237', '23', '泸州市', 'zuo_zhou');
INSERT INTO `address_city` VALUES ('238', '23', '德阳市', 'de_yang');
INSERT INTO `address_city` VALUES ('239', '23', '绵阳市', 'mian_yang');
INSERT INTO `address_city` VALUES ('240', '23', '广元市', 'guang_yuan');
INSERT INTO `address_city` VALUES ('241', '23', '遂宁市', 'sui_ning');
INSERT INTO `address_city` VALUES ('242', '23', '内江市', 'nei_jiang');
INSERT INTO `address_city` VALUES ('243', '23', '乐山市', 'le_shan');
INSERT INTO `address_city` VALUES ('244', '23', '南充市', 'nan_chong');
INSERT INTO `address_city` VALUES ('245', '23', '眉山市', 'mei_shan');
INSERT INTO `address_city` VALUES ('246', '23', '宜宾市', 'yi_bin');
INSERT INTO `address_city` VALUES ('247', '23', '广安市', 'guang_an');
INSERT INTO `address_city` VALUES ('248', '23', '达州市', 'da_zhou');
INSERT INTO `address_city` VALUES ('249', '23', '雅安市', 'ya_an');
INSERT INTO `address_city` VALUES ('250', '23', '巴中市', 'ba_zhong');
INSERT INTO `address_city` VALUES ('251', '23', '资阳市', 'zi_yang');
INSERT INTO `address_city` VALUES ('252', '23', '阿坝藏族羌族自治州', 'a_ba_cang_zu_qiang_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('253', '23', '甘孜藏族自治州', 'gan_zi_cang_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('254', '23', '凉山彝族自治州', 'liang_shan_yi_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('255', '24', '贵阳市', 'gui_yang');
INSERT INTO `address_city` VALUES ('256', '24', '六盘水市', 'liu_pan_shui');
INSERT INTO `address_city` VALUES ('257', '24', '遵义市', 'zun_yi');
INSERT INTO `address_city` VALUES ('258', '24', '安顺市', 'an_shun');
INSERT INTO `address_city` VALUES ('259', '24', '毕节市', 'bi_jie');
INSERT INTO `address_city` VALUES ('260', '24', '铜仁市', 'tong_ren');
INSERT INTO `address_city` VALUES ('261', '24', '黔西南布依族苗族自治州', 'qian_xi_nan_bu_yi_zu_miao_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('262', '24', '黔东南苗族侗族自治州', 'qian_dong_nan_miao_zu_dong_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('263', '24', '黔南布依族苗族自治州', 'qian_nan_bu_yi_zu_miao_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('264', '25', '昆明市', 'kun_ming');
INSERT INTO `address_city` VALUES ('265', '25', '曲靖市', 'qu_jing');
INSERT INTO `address_city` VALUES ('266', '25', '玉溪市', 'yu_xi');
INSERT INTO `address_city` VALUES ('267', '25', '保山市', 'bao_shan');
INSERT INTO `address_city` VALUES ('268', '25', '昭通市', 'zhao_tong');
INSERT INTO `address_city` VALUES ('269', '25', '丽江市', 'li_jiang');
INSERT INTO `address_city` VALUES ('270', '25', '普洱市', 'pu_er');
INSERT INTO `address_city` VALUES ('271', '25', '临沧市', 'lin_cang');
INSERT INTO `address_city` VALUES ('272', '25', '楚雄彝族自治州', 'chu_xiong_yi_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('273', '25', '红河哈尼族彝族自治州', 'hong_he_ha_ni_zu_yi_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('274', '25', '文山壮族苗族自治州', 'wen_shan_zhuang_zu_miao_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('275', '25', '西双版纳傣族自治州', 'xi_shuang_ban_na_dai_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('276', '25', '大理白族自治州', 'da_li_bai_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('277', '25', '德宏傣族景颇族自治州', 'de_hong_dai_zu_jing_po_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('278', '25', '怒江傈僳族自治州', 'nu_jiang_li_su_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('279', '25', '迪庆藏族自治州', 'di_qing_cang_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('280', '26', '拉萨市', 'la_sa');
INSERT INTO `address_city` VALUES ('281', '26', '昌都地区', 'chang_du_region');
INSERT INTO `address_city` VALUES ('282', '26', '山南地区', 'shan_nan_region');
INSERT INTO `address_city` VALUES ('283', '26', '日喀则地区', 'ri_ka_ze_region');
INSERT INTO `address_city` VALUES ('284', '26', '那曲地区', 'na_qu_region');
INSERT INTO `address_city` VALUES ('285', '26', '阿里地区', 'a_li_region');
INSERT INTO `address_city` VALUES ('286', '26', '林芝地区', 'lin_zhi_region');
INSERT INTO `address_city` VALUES ('287', '27', '西安市', 'xi_an');
INSERT INTO `address_city` VALUES ('288', '27', '铜川市', 'tong_chuan');
INSERT INTO `address_city` VALUES ('289', '27', '宝鸡市', 'bao_ji');
INSERT INTO `address_city` VALUES ('290', '27', '咸阳市', 'county_yang');
INSERT INTO `address_city` VALUES ('291', '27', '渭南市', 'wei_nan');
INSERT INTO `address_city` VALUES ('292', '27', '延安市', 'yan_an');
INSERT INTO `address_city` VALUES ('293', '27', '汉中市', 'han_zhong');
INSERT INTO `address_city` VALUES ('294', '27', '榆林市', 'yu_lin');
INSERT INTO `address_city` VALUES ('295', '27', '安康市', 'an_kang');
INSERT INTO `address_city` VALUES ('296', '27', '商洛市', 'shang_luo');
INSERT INTO `address_city` VALUES ('297', '28', '兰州市', 'lan_zhou');
INSERT INTO `address_city` VALUES ('298', '28', '嘉峪关市', 'jia_yu_guan');
INSERT INTO `address_city` VALUES ('299', '28', '金昌市', 'jin_chang');
INSERT INTO `address_city` VALUES ('300', '28', '白银市', 'bai_yin');
INSERT INTO `address_city` VALUES ('301', '28', '天水市', 'tian_shui');
INSERT INTO `address_city` VALUES ('302', '28', '武威市', 'wu_wei');
INSERT INTO `address_city` VALUES ('303', '28', '张掖市', 'zhang_ye');
INSERT INTO `address_city` VALUES ('304', '28', '平凉市', 'ping_liang');
INSERT INTO `address_city` VALUES ('305', '28', '酒泉市', 'jiu_quan');
INSERT INTO `address_city` VALUES ('306', '28', '庆阳市', 'qing_yang');
INSERT INTO `address_city` VALUES ('307', '28', '定西市', 'ding_xi');
INSERT INTO `address_city` VALUES ('308', '28', '陇南市', 'long_nan');
INSERT INTO `address_city` VALUES ('309', '28', '临夏回族自治州', 'lin_xia_hui_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('310', '28', '甘南藏族自治州', 'gan_nan_cang_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('311', '29', '西宁市', 'xi_ning');
INSERT INTO `address_city` VALUES ('312', '29', '海东地区', 'hai_dong_region');
INSERT INTO `address_city` VALUES ('313', '29', '海北藏族自治州', 'hai_bei_cang_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('314', '29', '黄南藏族自治州', 'huang_nan_cang_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('315', '29', '海南藏族自治州', 'hai_nan_cang_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('316', '29', '果洛藏族自治州', 'guo_luo_cang_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('317', '29', '玉树藏族自治州', 'yu_shu_cang_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('318', '29', '海西蒙古族藏族自治州', 'hai_xi_meng_gu_zu_cang_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('319', '30', '银川市', 'yin_chuan');
INSERT INTO `address_city` VALUES ('320', '30', '石嘴山市', 'shi_zui_shan');
INSERT INTO `address_city` VALUES ('321', '30', '吴忠市', 'wu_zhong');
INSERT INTO `address_city` VALUES ('322', '30', '固原市', 'gu_yuan');
INSERT INTO `address_city` VALUES ('323', '30', '中卫市', 'zhong_wei');
INSERT INTO `address_city` VALUES ('324', '31', '乌鲁木齐市', 'wu_lu_mu_qi');
INSERT INTO `address_city` VALUES ('325', '31', '克拉玛依市', 'ke_la_ma_yi');
INSERT INTO `address_city` VALUES ('326', '31', '吐鲁番地区', 'tu_lu_fan_region');
INSERT INTO `address_city` VALUES ('327', '31', '哈密地区', 'ha_mi_region');
INSERT INTO `address_city` VALUES ('328', '31', '昌吉回族自治州', 'chang_ji_hui_zu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('329', '31', '博尔塔拉蒙古自治州', 'bo_er_ta_la_meng_gu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('330', '31', '巴音郭楞蒙古自治州', 'ba_yin_guo_leng_meng_gu_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('331', '31', '阿克苏地区', 'a_ke_su_region');
INSERT INTO `address_city` VALUES ('332', '31', '克孜勒苏柯尔克孜自治州', 'ke_zi_le_su_ke_er_ke_zi_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('333', '31', '喀什地区', 'ka_shi_region');
INSERT INTO `address_city` VALUES ('334', '31', '和田地区', 'he_tian_region');
INSERT INTO `address_city` VALUES ('335', '31', '伊犁哈萨克自治州', 'yi_li_ha_sa_ke_autonomous_prefecture');
INSERT INTO `address_city` VALUES ('336', '31', '塔城地区', 'ta_cheng_region');
INSERT INTO `address_city` VALUES ('337', '31', '阿勒泰地区', 'a_le_tai_region');
INSERT INTO `address_city` VALUES ('338', '31', '直辖县', 'zi_zhi_qu_zhi_xia_county_administrative_division\r\n');

-- ----------------------------
-- Table structure for `address_county`
-- ----------------------------
DROP TABLE IF EXISTS `address_county`;
CREATE TABLE `address_county` (
  `id` int(5) NOT NULL,
  `city_id` int(3) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `en_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address_county
-- ----------------------------
INSERT INTO `address_county` VALUES ('1', '1', '东城区', 'dong_cheng_district');
INSERT INTO `address_county` VALUES ('2', '1', '西城区', 'xi_cheng_district');
INSERT INTO `address_county` VALUES ('3', '1', '朝阳区', 'chao_yang_district');
INSERT INTO `address_county` VALUES ('4', '1', '丰台区', 'feng_tai_district');
INSERT INTO `address_county` VALUES ('5', '1', '石景山区', 'shi_jing_shan_district');
INSERT INTO `address_county` VALUES ('6', '1', '海淀区', 'hai_dian_district');
INSERT INTO `address_county` VALUES ('7', '1', '门头沟区', 'men_tou_gou_district');
INSERT INTO `address_county` VALUES ('8', '1', '房山区', 'fang_shan_district');
INSERT INTO `address_county` VALUES ('9', '1', '通州区', 'tong_zhou_district');
INSERT INTO `address_county` VALUES ('10', '1', '顺义区', 'shun_yi_district');
INSERT INTO `address_county` VALUES ('11', '1', '昌平区', 'chang_ping_district');
INSERT INTO `address_county` VALUES ('12', '1', '大兴区', 'da_xing_district');
INSERT INTO `address_county` VALUES ('13', '1', '怀柔区', 'huai_rou_district');
INSERT INTO `address_county` VALUES ('14', '1', '平谷区', 'ping_gu_district');
INSERT INTO `address_county` VALUES ('15', '1', '密云县', 'mi_yun_county');
INSERT INTO `address_county` VALUES ('16', '1', '延庆县', 'yan_qing_county');
INSERT INTO `address_county` VALUES ('17', '2', '和平区', 'he_ping_district');
INSERT INTO `address_county` VALUES ('18', '2', '河东区', 'he_dong_district');
INSERT INTO `address_county` VALUES ('19', '2', '河西区', 'he_xi_district');
INSERT INTO `address_county` VALUES ('20', '2', '南开区', 'nan_kai_district');
INSERT INTO `address_county` VALUES ('21', '2', '河北区', 'he_bei_district');
INSERT INTO `address_county` VALUES ('22', '2', '红桥区', 'hong_qiao_district');
INSERT INTO `address_county` VALUES ('23', '2', '东丽区', 'dong_li_district');
INSERT INTO `address_county` VALUES ('24', '2', '西青区', 'xi_qing_district');
INSERT INTO `address_county` VALUES ('25', '2', '津南区', 'jin_nan_district');
INSERT INTO `address_county` VALUES ('26', '2', '北辰区', 'bei_chen_district');
INSERT INTO `address_county` VALUES ('27', '2', '武清区', 'wu_qing_district');
INSERT INTO `address_county` VALUES ('28', '2', '宝坻区', 'bao_zuo_district');
INSERT INTO `address_county` VALUES ('29', '2', '滨海新区', 'bin_hai_xin_district');
INSERT INTO `address_county` VALUES ('30', '2', '宁河县', 'ning_he_county');
INSERT INTO `address_county` VALUES ('31', '2', '静海县', 'jing_hai_county');
INSERT INTO `address_county` VALUES ('32', '2', '蓟县', 'ji_county');
INSERT INTO `address_county` VALUES ('33', '3', '市辖区', 'shi_xia_district');
INSERT INTO `address_county` VALUES ('34', '3', '长安区', 'chang_an_district');
INSERT INTO `address_county` VALUES ('35', '3', '桥东区', 'qiao_dong_district');
INSERT INTO `address_county` VALUES ('36', '3', '桥西区', 'qiao_xi_district');
INSERT INTO `address_county` VALUES ('37', '3', '新华区', 'xin_hua_district');
INSERT INTO `address_county` VALUES ('38', '3', '井陉矿区', 'jing_zuo_kuang_district');
INSERT INTO `address_county` VALUES ('39', '3', '裕华区', 'yu_hua_district');
INSERT INTO `address_county` VALUES ('40', '3', '井陉县', 'jing_zuo_county');
INSERT INTO `address_county` VALUES ('41', '3', '正定县', 'zheng_ding_county');
INSERT INTO `address_county` VALUES ('42', '3', '栾城县', 'zuo_cheng_county');
INSERT INTO `address_county` VALUES ('43', '3', '行唐县', 'xing_tang_county');
INSERT INTO `address_county` VALUES ('44', '3', '灵寿县', 'ling_shou_county');
INSERT INTO `address_county` VALUES ('45', '3', '高邑县', 'gao_yi_county');
INSERT INTO `address_county` VALUES ('46', '3', '深泽县', 'shen_ze_county');
INSERT INTO `address_county` VALUES ('47', '3', '赞皇县', 'zan_huang_county');
INSERT INTO `address_county` VALUES ('48', '3', '无极县', 'wu_ji_county');
INSERT INTO `address_county` VALUES ('49', '3', '平山县', 'ping_shan_county');
INSERT INTO `address_county` VALUES ('50', '3', '元氏县', 'yuan_shi_county');
INSERT INTO `address_county` VALUES ('51', '3', '赵县', 'zhao_county');
INSERT INTO `address_county` VALUES ('52', '3', '辛集市', 'xin_ji_city');
INSERT INTO `address_county` VALUES ('53', '3', '藁城市', 'zuo_cheng_city');
INSERT INTO `address_county` VALUES ('54', '3', '晋州市', 'jin_zhou_city');
INSERT INTO `address_county` VALUES ('55', '3', '新乐市', 'xin_le_city');
INSERT INTO `address_county` VALUES ('56', '3', '鹿泉市', 'lu_quan_city');
INSERT INTO `address_county` VALUES ('57', '4', '路南区', 'lu_nan_district');
INSERT INTO `address_county` VALUES ('58', '4', '路北区', 'lu_bei_district');
INSERT INTO `address_county` VALUES ('59', '4', '古冶区', 'gu_ye_district');
INSERT INTO `address_county` VALUES ('60', '4', '开平区', 'kai_ping_district');
INSERT INTO `address_county` VALUES ('61', '4', '丰南区', 'feng_nan_district');
INSERT INTO `address_county` VALUES ('62', '4', '丰润区', 'feng_run_district');
INSERT INTO `address_county` VALUES ('63', '4', '滦县', 'luan_county');
INSERT INTO `address_county` VALUES ('64', '4', '滦南县', 'luan_nan_county');
INSERT INTO `address_county` VALUES ('65', '4', '乐亭县', 'le_ting_county');
INSERT INTO `address_county` VALUES ('66', '4', '迁西县', 'qian_xi_county');
INSERT INTO `address_county` VALUES ('67', '4', '玉田县', 'yu_tian_county');
INSERT INTO `address_county` VALUES ('68', '4', '唐海县', 'tang_hai_county');
INSERT INTO `address_county` VALUES ('69', '4', '遵化市', 'zun_hua_city');
INSERT INTO `address_county` VALUES ('70', '4', '迁安市', 'qian_an_city');
INSERT INTO `address_county` VALUES ('71', '5', '海港区', 'hai_gang_district');
INSERT INTO `address_county` VALUES ('72', '5', '山海关区', 'shan_hai_guan_district');
INSERT INTO `address_county` VALUES ('73', '5', '北戴河区', 'bei_dai_he_district');
INSERT INTO `address_county` VALUES ('74', '5', '青龙满族自治县', 'qing_long_man_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('75', '5', '昌黎县', 'chang_li_county');
INSERT INTO `address_county` VALUES ('76', '5', '抚宁县', 'fu_ning_county');
INSERT INTO `address_county` VALUES ('77', '5', '卢龙县', 'lu_long_county');
INSERT INTO `address_county` VALUES ('78', '6', '邯山区', 'han_shan_district');
INSERT INTO `address_county` VALUES ('79', '6', '丛台区', 'cong_tai_district');
INSERT INTO `address_county` VALUES ('80', '6', '复兴区', 'fu_xing_district');
INSERT INTO `address_county` VALUES ('81', '6', '峰峰矿区', 'feng_feng_kuang_district');
INSERT INTO `address_county` VALUES ('82', '6', '邯郸县', 'han_dan_county');
INSERT INTO `address_county` VALUES ('83', '6', '临漳县', 'lin_zhang_county');
INSERT INTO `address_county` VALUES ('84', '6', '成安县', 'cheng_an_county');
INSERT INTO `address_county` VALUES ('85', '6', '大名县', 'da_ming_county');
INSERT INTO `address_county` VALUES ('86', '6', '涉县', 'she_county');
INSERT INTO `address_county` VALUES ('87', '6', '磁县', 'ci_county');
INSERT INTO `address_county` VALUES ('88', '6', '肥乡县', 'fei_xiang_county');
INSERT INTO `address_county` VALUES ('89', '6', '永年县', 'yong_nian_county');
INSERT INTO `address_county` VALUES ('90', '6', '邱县', 'qiu_county');
INSERT INTO `address_county` VALUES ('91', '6', '鸡泽县', 'ji_ze_county');
INSERT INTO `address_county` VALUES ('92', '6', '广平县', 'guang_ping_county');
INSERT INTO `address_county` VALUES ('93', '6', '馆陶县', 'guan_tao_county');
INSERT INTO `address_county` VALUES ('94', '6', '魏县', 'wei_county');
INSERT INTO `address_county` VALUES ('95', '6', '曲周县', 'qu_zhou_county');
INSERT INTO `address_county` VALUES ('96', '6', '武安市', 'wu_an_city');
INSERT INTO `address_county` VALUES ('97', '7', '邢台县', 'xing_tai_county');
INSERT INTO `address_county` VALUES ('98', '7', '临城县', 'lin_cheng_county');
INSERT INTO `address_county` VALUES ('99', '7', '内丘县', 'nei_qiu_county');
INSERT INTO `address_county` VALUES ('100', '7', '柏乡县', 'bai_xiang_county');
INSERT INTO `address_county` VALUES ('101', '7', '隆尧县', 'long_yao_county');
INSERT INTO `address_county` VALUES ('102', '7', '任县', 'ren_county');
INSERT INTO `address_county` VALUES ('103', '7', '南和县', 'nan_he_county');
INSERT INTO `address_county` VALUES ('104', '7', '宁晋县', 'ning_jin_county');
INSERT INTO `address_county` VALUES ('105', '7', '巨鹿县', 'ju_lu_county');
INSERT INTO `address_county` VALUES ('106', '7', '新河县', 'xin_he_county');
INSERT INTO `address_county` VALUES ('107', '7', '广宗县', 'guang_zong_county');
INSERT INTO `address_county` VALUES ('108', '7', '平乡县', 'ping_xiang_county');
INSERT INTO `address_county` VALUES ('109', '7', '威县', 'wei_county');
INSERT INTO `address_county` VALUES ('110', '7', '清河县', 'qing_he_county');
INSERT INTO `address_county` VALUES ('111', '7', '临西县', 'lin_xi_county');
INSERT INTO `address_county` VALUES ('112', '7', '南宫市', 'nan_gong_city');
INSERT INTO `address_county` VALUES ('113', '7', '沙河市', 'sha_he_city');
INSERT INTO `address_county` VALUES ('114', '8', '新市区', 'xin_shi_district');
INSERT INTO `address_county` VALUES ('115', '8', '北市区', 'bei_shi_district');
INSERT INTO `address_county` VALUES ('116', '8', '南市区', 'nan_shi_district');
INSERT INTO `address_county` VALUES ('117', '8', '满城县', 'man_cheng_county');
INSERT INTO `address_county` VALUES ('118', '8', '清苑县', 'qing_yuan_county');
INSERT INTO `address_county` VALUES ('119', '8', '涞水县', 'zuo_shui_county');
INSERT INTO `address_county` VALUES ('120', '8', '阜平县', 'fu_ping_county');
INSERT INTO `address_county` VALUES ('121', '8', '徐水县', 'xu_shui_county');
INSERT INTO `address_county` VALUES ('122', '8', '定兴县', 'ding_xing_county');
INSERT INTO `address_county` VALUES ('123', '8', '唐县', 'tang_county');
INSERT INTO `address_county` VALUES ('124', '8', '高阳县', 'gao_yang_county');
INSERT INTO `address_county` VALUES ('125', '8', '容城县', 'rong_cheng_county');
INSERT INTO `address_county` VALUES ('126', '8', '涞源县', 'zuo_yuan_county');
INSERT INTO `address_county` VALUES ('127', '8', '望都县', 'wang_du_county');
INSERT INTO `address_county` VALUES ('128', '8', '安新县', 'an_xin_county');
INSERT INTO `address_county` VALUES ('129', '8', '易县', 'yi_county');
INSERT INTO `address_county` VALUES ('130', '8', '曲阳县', 'qu_yang_county');
INSERT INTO `address_county` VALUES ('131', '8', '蠡县', 'zuo_county');
INSERT INTO `address_county` VALUES ('132', '8', '顺平县', 'shun_ping_county');
INSERT INTO `address_county` VALUES ('133', '8', '博野县', 'bo_ye_county');
INSERT INTO `address_county` VALUES ('134', '8', '雄县', 'xiong_county');
INSERT INTO `address_county` VALUES ('135', '8', '涿州市', 'zuo_zhou_city');
INSERT INTO `address_county` VALUES ('136', '8', '定州市', 'ding_zhou_city');
INSERT INTO `address_county` VALUES ('137', '8', '安国市', 'an_guo_city');
INSERT INTO `address_county` VALUES ('138', '8', '高碑店市', 'gao_bei_dian_city');
INSERT INTO `address_county` VALUES ('139', '9', '宣化区', 'xuan_hua_district');
INSERT INTO `address_county` VALUES ('140', '9', '下花园区', 'xia_hua_yuan_district');
INSERT INTO `address_county` VALUES ('141', '9', '宣化县', 'xuan_hua_county');
INSERT INTO `address_county` VALUES ('142', '9', '张北县', 'zhang_bei_county');
INSERT INTO `address_county` VALUES ('143', '9', '康保县', 'kang_bao_county');
INSERT INTO `address_county` VALUES ('144', '9', '沽源县', 'gu_yuan_county');
INSERT INTO `address_county` VALUES ('145', '9', '尚义县', 'shang_yi_county');
INSERT INTO `address_county` VALUES ('146', '9', '蔚县', 'wei_county');
INSERT INTO `address_county` VALUES ('147', '9', '阳原县', 'yang_yuan_county');
INSERT INTO `address_county` VALUES ('148', '9', '怀安县', 'huai_an_county');
INSERT INTO `address_county` VALUES ('149', '9', '万全县', 'wan_quan_county');
INSERT INTO `address_county` VALUES ('150', '9', '怀来县', 'huai_lai_county');
INSERT INTO `address_county` VALUES ('151', '9', '涿鹿县', 'zuo_lu_county');
INSERT INTO `address_county` VALUES ('152', '9', '赤城县', 'chi_cheng_county');
INSERT INTO `address_county` VALUES ('153', '9', '崇礼县', 'chong_li_county');
INSERT INTO `address_county` VALUES ('154', '10', '双桥区', 'shuang_qiao_district');
INSERT INTO `address_county` VALUES ('155', '10', '双滦区', 'shuang_luan_district');
INSERT INTO `address_county` VALUES ('156', '10', '鹰手营子矿区', 'ying_shou_ying_zi_kuang_district');
INSERT INTO `address_county` VALUES ('157', '10', '承德县', 'cheng_de_county');
INSERT INTO `address_county` VALUES ('158', '10', '兴隆县', 'xing_long_county');
INSERT INTO `address_county` VALUES ('159', '10', '平泉县', 'ping_quan_county');
INSERT INTO `address_county` VALUES ('160', '10', '滦平县', 'luan_ping_county');
INSERT INTO `address_county` VALUES ('161', '10', '隆化县', 'long_hua_county');
INSERT INTO `address_county` VALUES ('162', '10', '丰宁满族自治县', 'feng_ning_man_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('163', '10', '宽城满族自治县', 'kuan_cheng_man_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('164', '10', '围场满族蒙古族自治县', 'wei_chang_man_zu_meng_gu_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('165', '11', '运河区', 'yun_he_district');
INSERT INTO `address_county` VALUES ('166', '11', '沧县', 'cang_county');
INSERT INTO `address_county` VALUES ('167', '11', '青县', 'qing_county');
INSERT INTO `address_county` VALUES ('168', '11', '东光县', 'dong_guang_county');
INSERT INTO `address_county` VALUES ('169', '11', '海兴县', 'hai_xing_county');
INSERT INTO `address_county` VALUES ('170', '11', '盐山县', 'yan_shan_county');
INSERT INTO `address_county` VALUES ('171', '11', '肃宁县', 'su_ning_county');
INSERT INTO `address_county` VALUES ('172', '11', '南皮县', 'nan_pi_county');
INSERT INTO `address_county` VALUES ('173', '11', '吴桥县', 'wu_qiao_county');
INSERT INTO `address_county` VALUES ('174', '11', '献县', 'xian_county');
INSERT INTO `address_county` VALUES ('175', '11', '孟村回族自治县', 'meng_cun_hui_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('176', '11', '泊头市', 'bo_tou_city');
INSERT INTO `address_county` VALUES ('177', '11', '任丘市', 'ren_qiu_city');
INSERT INTO `address_county` VALUES ('178', '11', '黄骅市', 'huang_zuo_city');
INSERT INTO `address_county` VALUES ('179', '11', '河间市', 'he_jian_city');
INSERT INTO `address_county` VALUES ('180', '12', '安次区', 'an_ci_district');
INSERT INTO `address_county` VALUES ('181', '12', '广阳区', 'guang_yang_district');
INSERT INTO `address_county` VALUES ('182', '12', '固安县', 'gu_an_county');
INSERT INTO `address_county` VALUES ('183', '12', '永清县', 'yong_qing_county');
INSERT INTO `address_county` VALUES ('184', '12', '香河县', 'xiang_he_county');
INSERT INTO `address_county` VALUES ('185', '12', '大城县', 'da_cheng_county');
INSERT INTO `address_county` VALUES ('186', '12', '文安县', 'wen_an_county');
INSERT INTO `address_county` VALUES ('187', '12', '大厂回族自治县', 'da_chang_hui_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('188', '12', '霸州市', 'ba_zhou_city');
INSERT INTO `address_county` VALUES ('189', '12', '三河市', 'san_he_city');
INSERT INTO `address_county` VALUES ('190', '13', '桃城区', 'tao_cheng_district');
INSERT INTO `address_county` VALUES ('191', '13', '枣强县', 'zao_qiang_county');
INSERT INTO `address_county` VALUES ('192', '13', '武邑县', 'wu_yi_county');
INSERT INTO `address_county` VALUES ('193', '13', '武强县', 'wu_qiang_county');
INSERT INTO `address_county` VALUES ('194', '13', '饶阳县', 'rao_yang_county');
INSERT INTO `address_county` VALUES ('195', '13', '安平县', 'an_ping_county');
INSERT INTO `address_county` VALUES ('196', '13', '故城县', 'gu_cheng_county');
INSERT INTO `address_county` VALUES ('197', '13', '景县', 'jing_county');
INSERT INTO `address_county` VALUES ('198', '13', '阜城县', 'fu_cheng_county');
INSERT INTO `address_county` VALUES ('199', '13', '冀州市', 'ji_zhou_city');
INSERT INTO `address_county` VALUES ('200', '13', '深州市', 'shen_zhou_city');
INSERT INTO `address_county` VALUES ('201', '14', '小店区', 'xiao_dian_district');
INSERT INTO `address_county` VALUES ('202', '14', '迎泽区', 'ying_ze_district');
INSERT INTO `address_county` VALUES ('203', '14', '杏花岭区', 'xing_hua_ling_district');
INSERT INTO `address_county` VALUES ('204', '14', '尖草坪区', 'jian_cao_ping_district');
INSERT INTO `address_county` VALUES ('205', '14', '万柏林区', 'wan_bai_lin_district');
INSERT INTO `address_county` VALUES ('206', '14', '晋源区', 'jin_yuan_district');
INSERT INTO `address_county` VALUES ('207', '14', '清徐县', 'qing_xu_county');
INSERT INTO `address_county` VALUES ('208', '14', '阳曲县', 'yang_qu_county');
INSERT INTO `address_county` VALUES ('209', '14', '娄烦县', 'lou_fan_county');
INSERT INTO `address_county` VALUES ('210', '14', '古交市', 'gu_jiao_city');
INSERT INTO `address_county` VALUES ('211', '15', '城区', 'cheng_district');
INSERT INTO `address_county` VALUES ('212', '15', '矿区', 'kuang_district');
INSERT INTO `address_county` VALUES ('213', '15', '南郊区', 'nan_jiao_district');
INSERT INTO `address_county` VALUES ('214', '15', '新荣区', 'xin_rong_district');
INSERT INTO `address_county` VALUES ('215', '15', '阳高县', 'yang_gao_county');
INSERT INTO `address_county` VALUES ('216', '15', '天镇县', 'tian_zhen_county');
INSERT INTO `address_county` VALUES ('217', '15', '广灵县', 'guang_ling_county');
INSERT INTO `address_county` VALUES ('218', '15', '灵丘县', 'ling_qiu_county');
INSERT INTO `address_county` VALUES ('219', '15', '浑源县', 'hun_yuan_county');
INSERT INTO `address_county` VALUES ('220', '15', '左云县', 'zuo_yun_county');
INSERT INTO `address_county` VALUES ('221', '15', '大同县', 'da_tong_county');
INSERT INTO `address_county` VALUES ('222', '16', '郊区', 'jiao_district');
INSERT INTO `address_county` VALUES ('223', '16', '平定县', 'ping_ding_county');
INSERT INTO `address_county` VALUES ('224', '16', '盂县', 'yu_county');
INSERT INTO `address_county` VALUES ('225', '17', '长治县', 'chang_zhi_county');
INSERT INTO `address_county` VALUES ('226', '17', '襄垣县', 'xiang_yuan_county');
INSERT INTO `address_county` VALUES ('227', '17', '屯留县', 'tun_liu_county');
INSERT INTO `address_county` VALUES ('228', '17', '平顺县', 'ping_shun_county');
INSERT INTO `address_county` VALUES ('229', '17', '黎城县', 'li_cheng_county');
INSERT INTO `address_county` VALUES ('230', '17', '壶关县', 'hu_guan_county');
INSERT INTO `address_county` VALUES ('231', '17', '长子县', 'chang_zi_county');
INSERT INTO `address_county` VALUES ('232', '17', '武乡县', 'wu_xiang_county');
INSERT INTO `address_county` VALUES ('233', '17', '沁县', 'qin_county');
INSERT INTO `address_county` VALUES ('234', '17', '沁源县', 'qin_yuan_county');
INSERT INTO `address_county` VALUES ('235', '17', '潞城市', 'lu_cheng_city');
INSERT INTO `address_county` VALUES ('236', '18', '沁水县', 'qin_shui_county');
INSERT INTO `address_county` VALUES ('237', '18', '阳城县', 'yang_cheng_county');
INSERT INTO `address_county` VALUES ('238', '18', '陵川县', 'ling_chuan_county');
INSERT INTO `address_county` VALUES ('239', '18', '泽州县', 'ze_zhou_county');
INSERT INTO `address_county` VALUES ('240', '18', '高平市', 'gao_ping_city');
INSERT INTO `address_county` VALUES ('241', '19', '朔城区', 'shuo_cheng_district');
INSERT INTO `address_county` VALUES ('242', '19', '平鲁区', 'ping_lu_district');
INSERT INTO `address_county` VALUES ('243', '19', '山阴县', 'shan_yin_county');
INSERT INTO `address_county` VALUES ('244', '19', '应县', 'ying_county');
INSERT INTO `address_county` VALUES ('245', '19', '右玉县', 'you_yu_county');
INSERT INTO `address_county` VALUES ('246', '19', '怀仁县', 'huai_ren_county');
INSERT INTO `address_county` VALUES ('247', '20', '榆次区', 'yu_ci_district');
INSERT INTO `address_county` VALUES ('248', '20', '榆社县', 'yu_she_county');
INSERT INTO `address_county` VALUES ('249', '20', '左权县', 'zuo_quan_county');
INSERT INTO `address_county` VALUES ('250', '20', '和顺县', 'he_shun_county');
INSERT INTO `address_county` VALUES ('251', '20', '昔阳县', 'xi_yang_county');
INSERT INTO `address_county` VALUES ('252', '20', '寿阳县', 'shou_yang_county');
INSERT INTO `address_county` VALUES ('253', '20', '太谷县', 'tai_gu_county');
INSERT INTO `address_county` VALUES ('254', '20', '祁县', 'qi_county');
INSERT INTO `address_county` VALUES ('255', '20', '平遥县', 'ping_yao_county');
INSERT INTO `address_county` VALUES ('256', '20', '灵石县', 'ling_shi_county');
INSERT INTO `address_county` VALUES ('257', '20', '介休市', 'jie_xiu_city');
INSERT INTO `address_county` VALUES ('258', '21', '盐湖区', 'yan_hu_district');
INSERT INTO `address_county` VALUES ('259', '21', '临猗县', 'lin_zuo_county');
INSERT INTO `address_county` VALUES ('260', '21', '万荣县', 'wan_rong_county');
INSERT INTO `address_county` VALUES ('261', '21', '闻喜县', 'wen_xi_county');
INSERT INTO `address_county` VALUES ('262', '21', '稷山县', 'zuo_shan_county');
INSERT INTO `address_county` VALUES ('263', '21', '新绛县', 'xin_zuo_county');
INSERT INTO `address_county` VALUES ('264', '21', '绛县', 'zuo_county');
INSERT INTO `address_county` VALUES ('265', '21', '垣曲县', 'yuan_qu_county');
INSERT INTO `address_county` VALUES ('266', '21', '夏县', 'xia_county');
INSERT INTO `address_county` VALUES ('267', '21', '平陆县', 'ping_lu_county');
INSERT INTO `address_county` VALUES ('268', '21', '芮城县', 'zuo_cheng_county');
INSERT INTO `address_county` VALUES ('269', '21', '永济市', 'yong_ji_city');
INSERT INTO `address_county` VALUES ('270', '21', '河津市', 'he_jin_city');
INSERT INTO `address_county` VALUES ('271', '22', '忻府区', 'xin_fu_district');
INSERT INTO `address_county` VALUES ('272', '22', '定襄县', 'ding_xiang_county');
INSERT INTO `address_county` VALUES ('273', '22', '五台县', 'wu_tai_county');
INSERT INTO `address_county` VALUES ('274', '22', '代县', 'dai_county');
INSERT INTO `address_county` VALUES ('275', '22', '繁峙县', 'fan_zhi_county');
INSERT INTO `address_county` VALUES ('276', '22', '宁武县', 'ning_wu_county');
INSERT INTO `address_county` VALUES ('277', '22', '静乐县', 'jing_le_county');
INSERT INTO `address_county` VALUES ('278', '22', '神池县', 'shen_chi_county');
INSERT INTO `address_county` VALUES ('279', '22', '五寨县', 'wu_zhai_county');
INSERT INTO `address_county` VALUES ('280', '22', '岢岚县', 'zuo_zuo_county');
INSERT INTO `address_county` VALUES ('281', '22', '河曲县', 'he_qu_county');
INSERT INTO `address_county` VALUES ('282', '22', '保德县', 'bao_de_county');
INSERT INTO `address_county` VALUES ('283', '22', '偏关县', 'pian_guan_county');
INSERT INTO `address_county` VALUES ('284', '22', '原平市', 'yuan_ping_city');
INSERT INTO `address_county` VALUES ('285', '23', '尧都区', 'yao_du_district');
INSERT INTO `address_county` VALUES ('286', '23', '曲沃县', 'qu_wo_county');
INSERT INTO `address_county` VALUES ('287', '23', '翼城县', 'yi_cheng_county');
INSERT INTO `address_county` VALUES ('288', '23', '襄汾县', 'xiang_fen_county');
INSERT INTO `address_county` VALUES ('289', '23', '洪洞县', 'hong_dong_county');
INSERT INTO `address_county` VALUES ('290', '23', '古县', 'gu_county');
INSERT INTO `address_county` VALUES ('291', '23', '安泽县', 'an_ze_county');
INSERT INTO `address_county` VALUES ('292', '23', '浮山县', 'fu_shan_county');
INSERT INTO `address_county` VALUES ('293', '23', '吉县', 'ji_county');
INSERT INTO `address_county` VALUES ('294', '23', '乡宁县', 'xiang_ning_county');
INSERT INTO `address_county` VALUES ('295', '23', '大宁县', 'da_ning_county');
INSERT INTO `address_county` VALUES ('296', '23', '隰县', 'zuo_county');
INSERT INTO `address_county` VALUES ('297', '23', '永和县', 'yong_he_county');
INSERT INTO `address_county` VALUES ('298', '23', '蒲县', 'pu_county');
INSERT INTO `address_county` VALUES ('299', '23', '汾西县', 'fen_xi_county');
INSERT INTO `address_county` VALUES ('300', '23', '侯马市', 'hou_ma_city');
INSERT INTO `address_county` VALUES ('301', '23', '霍州市', 'huo_zhou_city');
INSERT INTO `address_county` VALUES ('302', '24', '离石区', 'li_shi_district');
INSERT INTO `address_county` VALUES ('303', '24', '文水县', 'wen_shui_county');
INSERT INTO `address_county` VALUES ('304', '24', '交城县', 'jiao_cheng_county');
INSERT INTO `address_county` VALUES ('305', '24', '兴县', 'xing_county');
INSERT INTO `address_county` VALUES ('306', '24', '临县', 'lin_county');
INSERT INTO `address_county` VALUES ('307', '24', '柳林县', 'liu_lin_county');
INSERT INTO `address_county` VALUES ('308', '24', '石楼县', 'shi_lou_county');
INSERT INTO `address_county` VALUES ('309', '24', '岚县', 'zuo_county');
INSERT INTO `address_county` VALUES ('310', '24', '方山县', 'fang_shan_county');
INSERT INTO `address_county` VALUES ('311', '24', '中阳县', 'zhong_yang_county');
INSERT INTO `address_county` VALUES ('312', '24', '交口县', 'jiao_kou_county');
INSERT INTO `address_county` VALUES ('313', '24', '孝义市', 'xiao_yi_city');
INSERT INTO `address_county` VALUES ('314', '24', '汾阳市', 'fen_yang_city');
INSERT INTO `address_county` VALUES ('315', '25', '新城区', 'xin_cheng_district');
INSERT INTO `address_county` VALUES ('316', '25', '回民区', 'hui_min_district');
INSERT INTO `address_county` VALUES ('317', '25', '玉泉区', 'yu_quan_district');
INSERT INTO `address_county` VALUES ('318', '25', '赛罕区', 'sai_han_district');
INSERT INTO `address_county` VALUES ('319', '25', '土默特左旗', 'tu_mo_te_zuo_qi');
INSERT INTO `address_county` VALUES ('320', '25', '托克托县', 'tuo_ke_tuo_county');
INSERT INTO `address_county` VALUES ('321', '25', '和林格尔县', 'he_lin_ge_er_county');
INSERT INTO `address_county` VALUES ('322', '25', '清水河县', 'qing_shui_he_county');
INSERT INTO `address_county` VALUES ('323', '25', '武川县', 'wu_chuan_county');
INSERT INTO `address_county` VALUES ('324', '26', '东河区', 'dong_he_district');
INSERT INTO `address_county` VALUES ('325', '26', '昆都仑区', 'kun_du_lun_district');
INSERT INTO `address_county` VALUES ('326', '26', '青山区', 'qing_shan_district');
INSERT INTO `address_county` VALUES ('327', '26', '石拐区', 'shi_guai_district');
INSERT INTO `address_county` VALUES ('328', '26', '白云鄂博矿区', 'bai_yun_e_bo_kuang_district');
INSERT INTO `address_county` VALUES ('329', '26', '九原区', 'jiu_yuan_district');
INSERT INTO `address_county` VALUES ('330', '26', '土默特右旗', 'tu_mo_te_you_qi');
INSERT INTO `address_county` VALUES ('331', '26', '固阳县', 'gu_yang_county');
INSERT INTO `address_county` VALUES ('332', '26', '达尔罕茂明安联合旗', 'da_er_han_mao_ming_an_lian_he_qi');
INSERT INTO `address_county` VALUES ('333', '27', '海勃湾区', 'hai_bo_wan_district');
INSERT INTO `address_county` VALUES ('334', '27', '海南区', 'hai_nan_district');
INSERT INTO `address_county` VALUES ('335', '27', '乌达区', 'wu_da_district');
INSERT INTO `address_county` VALUES ('336', '28', '红山区', 'hong_shan_district');
INSERT INTO `address_county` VALUES ('337', '28', '元宝山区', 'yuan_bao_shan_district');
INSERT INTO `address_county` VALUES ('338', '28', '松山区', 'song_shan_district');
INSERT INTO `address_county` VALUES ('339', '28', '阿鲁科尔沁旗', 'a_lu_ke_er_qin_qi');
INSERT INTO `address_county` VALUES ('340', '28', '巴林左旗', 'ba_lin_zuo_qi');
INSERT INTO `address_county` VALUES ('341', '28', '巴林右旗', 'ba_lin_you_qi');
INSERT INTO `address_county` VALUES ('342', '28', '林西县', 'lin_xi_county');
INSERT INTO `address_county` VALUES ('343', '28', '克什克腾旗', 'ke_shi_ke_teng_qi');
INSERT INTO `address_county` VALUES ('344', '28', '翁牛特旗', 'weng_niu_te_qi');
INSERT INTO `address_county` VALUES ('345', '28', '喀喇沁旗', 'ka_la_qin_qi');
INSERT INTO `address_county` VALUES ('346', '28', '宁城县', 'ning_cheng_county');
INSERT INTO `address_county` VALUES ('347', '28', '敖汉旗', 'ao_han_qi');
INSERT INTO `address_county` VALUES ('348', '29', '科尔沁区', 'ke_er_qin_district');
INSERT INTO `address_county` VALUES ('349', '29', '科尔沁左翼中旗', 'ke_er_qin_zuo_yi_zhong_qi');
INSERT INTO `address_county` VALUES ('350', '29', '科尔沁左翼后旗', 'ke_er_qin_zuo_yi_hou_qi');
INSERT INTO `address_county` VALUES ('351', '29', '开鲁县', 'kai_lu_county');
INSERT INTO `address_county` VALUES ('352', '29', '库伦旗', 'ku_lun_qi');
INSERT INTO `address_county` VALUES ('353', '29', '奈曼旗', 'nai_man_qi');
INSERT INTO `address_county` VALUES ('354', '29', '扎鲁特旗', 'zha_lu_te_qi');
INSERT INTO `address_county` VALUES ('355', '29', '霍林郭勒市', 'huo_lin_guo_le_city');
INSERT INTO `address_county` VALUES ('356', '30', '东胜区', 'dong_sheng_district');
INSERT INTO `address_county` VALUES ('357', '30', '达拉特旗', 'da_la_te_qi');
INSERT INTO `address_county` VALUES ('358', '30', '准格尔旗', 'zhun_ge_er_qi');
INSERT INTO `address_county` VALUES ('359', '30', '鄂托克前旗', 'e_tuo_ke_qian_qi');
INSERT INTO `address_county` VALUES ('360', '30', '鄂托克旗', 'e_tuo_ke_qi');
INSERT INTO `address_county` VALUES ('361', '30', '杭锦旗', 'hang_jin_qi');
INSERT INTO `address_county` VALUES ('362', '30', '乌审旗', 'wu_shen_qi');
INSERT INTO `address_county` VALUES ('363', '30', '伊金霍洛旗', 'yi_jin_huo_luo_qi');
INSERT INTO `address_county` VALUES ('364', '31', '海拉尔区', 'hai_la_er_district');
INSERT INTO `address_county` VALUES ('365', '31', '阿荣旗', 'a_rong_qi');
INSERT INTO `address_county` VALUES ('366', '31', '莫力达瓦达斡尔族自治旗', 'mo_li_da_wa_da_wo_er_zu_zi_zhi_qi');
INSERT INTO `address_county` VALUES ('367', '31', '鄂伦春自治旗', 'e_lun_chun_zi_zhi_qi');
INSERT INTO `address_county` VALUES ('368', '31', '鄂温克族自治旗', 'e_wen_ke_zu_zi_zhi_qi');
INSERT INTO `address_county` VALUES ('369', '31', '陈巴尔虎旗', 'chen_ba_er_hu_qi');
INSERT INTO `address_county` VALUES ('370', '31', '新巴尔虎左旗', 'xin_ba_er_hu_zuo_qi');
INSERT INTO `address_county` VALUES ('371', '31', '新巴尔虎右旗', 'xin_ba_er_hu_you_qi');
INSERT INTO `address_county` VALUES ('372', '31', '满洲里市', 'man_zhou_li_city');
INSERT INTO `address_county` VALUES ('373', '31', '牙克石市', 'ya_ke_shi_city');
INSERT INTO `address_county` VALUES ('374', '31', '扎兰屯市', 'zha_lan_tun_city');
INSERT INTO `address_county` VALUES ('375', '31', '额尔古纳市', 'e_er_gu_na_city');
INSERT INTO `address_county` VALUES ('376', '31', '根河市', 'gen_he_city');
INSERT INTO `address_county` VALUES ('377', '32', '临河区', 'lin_he_district');
INSERT INTO `address_county` VALUES ('378', '32', '五原县', 'wu_yuan_county');
INSERT INTO `address_county` VALUES ('379', '32', '磴口县', 'zuo_kou_county');
INSERT INTO `address_county` VALUES ('380', '32', '乌拉特前旗', 'wu_la_te_qian_qi');
INSERT INTO `address_county` VALUES ('381', '32', '乌拉特中旗', 'wu_la_te_zhong_qi');
INSERT INTO `address_county` VALUES ('382', '32', '乌拉特后旗', 'wu_la_te_hou_qi');
INSERT INTO `address_county` VALUES ('383', '32', '杭锦后旗', 'hang_jin_hou_qi');
INSERT INTO `address_county` VALUES ('384', '33', '集宁区', 'ji_ning_district');
INSERT INTO `address_county` VALUES ('385', '33', '卓资县', 'zhuo_zi_county');
INSERT INTO `address_county` VALUES ('386', '33', '化德县', 'hua_de_county');
INSERT INTO `address_county` VALUES ('387', '33', '商都县', 'shang_du_county');
INSERT INTO `address_county` VALUES ('388', '33', '兴和县', 'xing_he_county');
INSERT INTO `address_county` VALUES ('389', '33', '凉城县', 'liang_cheng_county');
INSERT INTO `address_county` VALUES ('390', '33', '察哈尔右翼前旗', 'cha_ha_er_you_yi_qian_qi');
INSERT INTO `address_county` VALUES ('391', '33', '察哈尔右翼中旗', 'cha_ha_er_you_yi_zhong_qi');
INSERT INTO `address_county` VALUES ('392', '33', '察哈尔右翼后旗', 'cha_ha_er_you_yi_hou_qi');
INSERT INTO `address_county` VALUES ('393', '33', '四子王旗', 'si_zi_wang_qi');
INSERT INTO `address_county` VALUES ('394', '33', '丰镇市', 'feng_zhen_city');
INSERT INTO `address_county` VALUES ('395', '34', '乌兰浩特市', 'wu_lan_hao_te_city');
INSERT INTO `address_county` VALUES ('396', '34', '阿尔山市', 'a_er_shan_city');
INSERT INTO `address_county` VALUES ('397', '34', '科尔沁右翼前旗', 'ke_er_qin_you_yi_qian_qi');
INSERT INTO `address_county` VALUES ('398', '34', '科尔沁右翼中旗', 'ke_er_qin_you_yi_zhong_qi');
INSERT INTO `address_county` VALUES ('399', '34', '扎赉特旗', 'zha_zuo_te_qi');
INSERT INTO `address_county` VALUES ('400', '34', '突泉县', 'tu_quan_county');
INSERT INTO `address_county` VALUES ('401', '35', '二连浩特市', 'er_lian_hao_te_city');
INSERT INTO `address_county` VALUES ('402', '35', '锡林浩特市', 'xi_lin_hao_te_city');
INSERT INTO `address_county` VALUES ('403', '35', '阿巴嘎旗', 'a_ba_ga_qi');
INSERT INTO `address_county` VALUES ('404', '35', '苏尼特左旗', 'su_ni_te_zuo_qi');
INSERT INTO `address_county` VALUES ('405', '35', '苏尼特右旗', 'su_ni_te_you_qi');
INSERT INTO `address_county` VALUES ('406', '35', '东乌珠穆沁旗', 'dong_wu_zhu_mu_qin_qi');
INSERT INTO `address_county` VALUES ('407', '35', '西乌珠穆沁旗', 'xi_wu_zhu_mu_qin_qi');
INSERT INTO `address_county` VALUES ('408', '35', '太仆寺旗', 'tai_pu_si_qi');
INSERT INTO `address_county` VALUES ('409', '35', '镶黄旗', 'xiang_huang_qi');
INSERT INTO `address_county` VALUES ('410', '35', '正镶白旗', 'zheng_xiang_bai_qi');
INSERT INTO `address_county` VALUES ('411', '35', '正蓝旗', 'zheng_lan_qi');
INSERT INTO `address_county` VALUES ('412', '35', '多伦县', 'duo_lun_county');
INSERT INTO `address_county` VALUES ('413', '36', '阿拉善左旗', 'a_la_shan_zuo_qi');
INSERT INTO `address_county` VALUES ('414', '36', '阿拉善右旗', 'a_la_shan_you_qi');
INSERT INTO `address_county` VALUES ('415', '36', '额济纳旗', 'e_ji_na_qi');
INSERT INTO `address_county` VALUES ('416', '37', '沈河区', 'shen_he_district');
INSERT INTO `address_county` VALUES ('417', '37', '大东区', 'da_dong_district');
INSERT INTO `address_county` VALUES ('418', '37', '皇姑区', 'huang_gu_district');
INSERT INTO `address_county` VALUES ('419', '37', '铁西区', 'tie_xi_district');
INSERT INTO `address_county` VALUES ('420', '37', '苏家屯区', 'su_jia_tun_district');
INSERT INTO `address_county` VALUES ('421', '37', '东陵区', 'dong_ling_district');
INSERT INTO `address_county` VALUES ('422', '37', '沈北新区', 'shen_bei_xin_district');
INSERT INTO `address_county` VALUES ('423', '37', '于洪区', 'yu_hong_district');
INSERT INTO `address_county` VALUES ('424', '37', '辽中县', 'liao_zhong_county');
INSERT INTO `address_county` VALUES ('425', '37', '康平县', 'kang_ping_county');
INSERT INTO `address_county` VALUES ('426', '37', '法库县', 'fa_ku_county');
INSERT INTO `address_county` VALUES ('427', '37', '新民市', 'xin_min_city');
INSERT INTO `address_county` VALUES ('428', '38', '中山区', 'zhong_shan_district');
INSERT INTO `address_county` VALUES ('429', '38', '西岗区', 'xi_gang_district');
INSERT INTO `address_county` VALUES ('430', '38', '沙河口区', 'sha_he_kou_district');
INSERT INTO `address_county` VALUES ('431', '38', '甘井子区', 'gan_jing_zi_district');
INSERT INTO `address_county` VALUES ('432', '38', '旅顺口区', 'lv_shun_kou_district');
INSERT INTO `address_county` VALUES ('433', '38', '金州区', 'jin_zhou_district');
INSERT INTO `address_county` VALUES ('434', '38', '长海县', 'chang_hai_county');
INSERT INTO `address_county` VALUES ('435', '38', '瓦房店市', 'wa_fang_dian_city');
INSERT INTO `address_county` VALUES ('436', '38', '普兰店市', 'pu_lan_dian_city');
INSERT INTO `address_county` VALUES ('437', '38', '庄河市', 'zhuang_he_city');
INSERT INTO `address_county` VALUES ('438', '39', '铁东区', 'tie_dong_district');
INSERT INTO `address_county` VALUES ('439', '39', '立山区', 'li_shan_district');
INSERT INTO `address_county` VALUES ('440', '39', '千山区', 'qian_shan_district');
INSERT INTO `address_county` VALUES ('441', '39', '台安县', 'tai_an_county');
INSERT INTO `address_county` VALUES ('442', '39', '岫岩满族自治县', 'zuo_yan_man_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('443', '39', '海城市', 'hai_cheng_city');
INSERT INTO `address_county` VALUES ('444', '40', '新抚区', 'xin_fu_district');
INSERT INTO `address_county` VALUES ('445', '40', '东洲区', 'dong_zhou_district');
INSERT INTO `address_county` VALUES ('446', '40', '望花区', 'wang_hua_district');
INSERT INTO `address_county` VALUES ('447', '40', '顺城区', 'shun_cheng_district');
INSERT INTO `address_county` VALUES ('448', '40', '抚顺县', 'fu_shun_county');
INSERT INTO `address_county` VALUES ('449', '40', '新宾满族自治县', 'xin_bin_man_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('450', '40', '清原满族自治县', 'qing_yuan_man_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('451', '41', '平山区', 'ping_shan_district');
INSERT INTO `address_county` VALUES ('452', '41', '溪湖区', 'xi_hu_district');
INSERT INTO `address_county` VALUES ('453', '41', '明山区', 'ming_shan_district');
INSERT INTO `address_county` VALUES ('454', '41', '南芬区', 'nan_fen_district');
INSERT INTO `address_county` VALUES ('455', '41', '本溪满族自治县', 'ben_xi_man_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('456', '41', '桓仁满族自治县', 'huan_ren_man_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('457', '42', '元宝区', 'yuan_bao_district');
INSERT INTO `address_county` VALUES ('458', '42', '振兴区', 'zhen_xing_district');
INSERT INTO `address_county` VALUES ('459', '42', '振安区', 'zhen_an_district');
INSERT INTO `address_county` VALUES ('460', '42', '宽甸满族自治县', 'kuan_dian_man_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('461', '42', '东港市', 'dong_gang_city');
INSERT INTO `address_county` VALUES ('462', '42', '凤城市', 'feng_cheng_city');
INSERT INTO `address_county` VALUES ('463', '43', '古塔区', 'gu_ta_district');
INSERT INTO `address_county` VALUES ('464', '43', '凌河区', 'ling_he_district');
INSERT INTO `address_county` VALUES ('465', '43', '太和区', 'tai_he_district');
INSERT INTO `address_county` VALUES ('466', '43', '黑山县', 'hei_shan_county');
INSERT INTO `address_county` VALUES ('467', '43', '义县', 'yi_county');
INSERT INTO `address_county` VALUES ('468', '43', '凌海市', 'ling_hai_city');
INSERT INTO `address_county` VALUES ('469', '43', '北镇市', 'bei_zhen_city');
INSERT INTO `address_county` VALUES ('470', '44', '站前区', 'zhan_qian_district');
INSERT INTO `address_county` VALUES ('471', '44', '西市区', 'xi_shi_district');
INSERT INTO `address_county` VALUES ('472', '44', '鲅鱼圈区', 'zuo_yu_quan_district');
INSERT INTO `address_county` VALUES ('473', '44', '老边区', 'lao_bian_district');
INSERT INTO `address_county` VALUES ('474', '44', '盖州市', 'gai_zhou_city');
INSERT INTO `address_county` VALUES ('475', '44', '大石桥市', 'da_shi_qiao_city');
INSERT INTO `address_county` VALUES ('476', '45', '海州区', 'hai_zhou_district');
INSERT INTO `address_county` VALUES ('477', '45', '新邱区', 'xin_qiu_district');
INSERT INTO `address_county` VALUES ('478', '45', '太平区', 'tai_ping_district');
INSERT INTO `address_county` VALUES ('479', '45', '清河门区', 'qing_he_men_district');
INSERT INTO `address_county` VALUES ('480', '45', '细河区', 'xi_he_district');
INSERT INTO `address_county` VALUES ('481', '45', '阜新蒙古族自治县', 'fu_xin_meng_gu_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('482', '45', '彰武县', 'zhang_wu_county');
INSERT INTO `address_county` VALUES ('483', '46', '白塔区', 'bai_ta_district');
INSERT INTO `address_county` VALUES ('484', '46', '文圣区', 'wen_sheng_district');
INSERT INTO `address_county` VALUES ('485', '46', '宏伟区', 'hong_wei_district');
INSERT INTO `address_county` VALUES ('486', '46', '弓长岭区', 'gong_chang_ling_district');
INSERT INTO `address_county` VALUES ('487', '46', '太子河区', 'tai_zi_he_district');
INSERT INTO `address_county` VALUES ('488', '46', '辽阳县', 'liao_yang_county');
INSERT INTO `address_county` VALUES ('489', '46', '灯塔市', 'deng_ta_city');
INSERT INTO `address_county` VALUES ('490', '47', '双台子区', 'shuang_tai_zi_district');
INSERT INTO `address_county` VALUES ('491', '47', '兴隆台区', 'xing_long_tai_district');
INSERT INTO `address_county` VALUES ('492', '47', '大洼县', 'da_wa_county');
INSERT INTO `address_county` VALUES ('493', '47', '盘山县', 'pan_shan_county');
INSERT INTO `address_county` VALUES ('494', '48', '银州区', 'yin_zhou_district');
INSERT INTO `address_county` VALUES ('495', '48', '清河区', 'qing_he_district');
INSERT INTO `address_county` VALUES ('496', '48', '铁岭县', 'tie_ling_county');
INSERT INTO `address_county` VALUES ('497', '48', '西丰县', 'xi_feng_county');
INSERT INTO `address_county` VALUES ('498', '48', '昌图县', 'chang_tu_county');
INSERT INTO `address_county` VALUES ('499', '48', '调兵山市', 'diao_bing_shan_city');
INSERT INTO `address_county` VALUES ('500', '48', '开原市', 'kai_yuan_city');
INSERT INTO `address_county` VALUES ('501', '49', '双塔区', 'shuang_ta_district');
INSERT INTO `address_county` VALUES ('502', '49', '龙城区', 'long_cheng_district');
INSERT INTO `address_county` VALUES ('503', '49', '朝阳县', 'chao_yang_county');
INSERT INTO `address_county` VALUES ('504', '49', '建平县', 'jian_ping_county');
INSERT INTO `address_county` VALUES ('505', '49', '喀喇沁左翼蒙古族自治县', 'ka_la_qin_zuo_yi_meng_gu_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('506', '49', '北票市', 'bei_piao_city');
INSERT INTO `address_county` VALUES ('507', '49', '凌源市', 'ling_yuan_city');
INSERT INTO `address_county` VALUES ('508', '50', '连山区', 'lian_shan_district');
INSERT INTO `address_county` VALUES ('509', '50', '龙港区', 'long_gang_district');
INSERT INTO `address_county` VALUES ('510', '50', '南票区', 'nan_piao_district');
INSERT INTO `address_county` VALUES ('511', '50', '绥中县', 'sui_zhong_county');
INSERT INTO `address_county` VALUES ('512', '50', '建昌县', 'jian_chang_county');
INSERT INTO `address_county` VALUES ('513', '50', '兴城市', 'xing_cheng_city');
INSERT INTO `address_county` VALUES ('514', '51', '南关区', 'nan_guan_district');
INSERT INTO `address_county` VALUES ('515', '51', '宽城区', 'kuan_cheng_district');
INSERT INTO `address_county` VALUES ('516', '51', '二道区', 'er_dao_district');
INSERT INTO `address_county` VALUES ('517', '51', '绿园区', 'lv_yuan_district');
INSERT INTO `address_county` VALUES ('518', '51', '双阳区', 'shuang_yang_district');
INSERT INTO `address_county` VALUES ('519', '51', '农安县', 'nong_an_county');
INSERT INTO `address_county` VALUES ('520', '51', '九台市', 'jiu_tai_city');
INSERT INTO `address_county` VALUES ('521', '51', '榆树市', 'yu_shu_city');
INSERT INTO `address_county` VALUES ('522', '51', '德惠市', 'de_hui_city');
INSERT INTO `address_county` VALUES ('523', '52', '昌邑区', 'chang_yi_district');
INSERT INTO `address_county` VALUES ('524', '52', '龙潭区', 'long_tan_district');
INSERT INTO `address_county` VALUES ('525', '52', '船营区', 'chuan_ying_district');
INSERT INTO `address_county` VALUES ('526', '52', '丰满区', 'feng_man_district');
INSERT INTO `address_county` VALUES ('527', '52', '永吉县', 'yong_ji_county');
INSERT INTO `address_county` VALUES ('528', '52', '蛟河市', 'zuo_he_city');
INSERT INTO `address_county` VALUES ('529', '52', '桦甸市', 'zuo_dian_city');
INSERT INTO `address_county` VALUES ('530', '52', '舒兰市', 'shu_lan_city');
INSERT INTO `address_county` VALUES ('531', '52', '磐石市', 'pan_shi_city');
INSERT INTO `address_county` VALUES ('532', '53', '梨树县', 'li_shu_county');
INSERT INTO `address_county` VALUES ('533', '53', '伊通满族自治县', 'yi_tong_man_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('534', '53', '公主岭市', 'gong_zhu_ling_city');
INSERT INTO `address_county` VALUES ('535', '53', '双辽市', 'shuang_liao_city');
INSERT INTO `address_county` VALUES ('536', '54', '龙山区', 'long_shan_district');
INSERT INTO `address_county` VALUES ('537', '54', '西安区', 'xi_an_district');
INSERT INTO `address_county` VALUES ('538', '54', '东丰县', 'dong_feng_county');
INSERT INTO `address_county` VALUES ('539', '54', '东辽县', 'dong_liao_county');
INSERT INTO `address_county` VALUES ('540', '55', '东昌区', 'dong_chang_district');
INSERT INTO `address_county` VALUES ('541', '55', '二道江区', 'er_dao_jiang_district');
INSERT INTO `address_county` VALUES ('542', '55', '通化县', 'tong_hua_county');
INSERT INTO `address_county` VALUES ('543', '55', '辉南县', 'hui_nan_county');
INSERT INTO `address_county` VALUES ('544', '55', '柳河县', 'liu_he_county');
INSERT INTO `address_county` VALUES ('545', '55', '梅河口市', 'mei_he_kou_city');
INSERT INTO `address_county` VALUES ('546', '55', '集安市', 'ji_an_city');
INSERT INTO `address_county` VALUES ('547', '56', '八道江区', 'ba_dao_jiang_district');
INSERT INTO `address_county` VALUES ('548', '56', '江源区', 'jiang_yuan_district');
INSERT INTO `address_county` VALUES ('549', '56', '抚松县', 'fu_song_county');
INSERT INTO `address_county` VALUES ('550', '56', '靖宇县', 'jing_yu_county');
INSERT INTO `address_county` VALUES ('551', '56', '长白朝鲜族自治县', 'chang_bai_chao_xian_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('552', '56', '临江市', 'lin_jiang_city');
INSERT INTO `address_county` VALUES ('553', '57', '宁江区', 'ning_jiang_district');
INSERT INTO `address_county` VALUES ('554', '57', '前郭尔罗斯蒙古族自治县', 'qian_guo_er_luo_si_meng_gu_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('555', '57', '长岭县', 'chang_ling_county');
INSERT INTO `address_county` VALUES ('556', '57', '乾安县', 'qian_an_county');
INSERT INTO `address_county` VALUES ('557', '57', '扶余县', 'fu_yu_county');
INSERT INTO `address_county` VALUES ('558', '58', '洮北区', 'zuo_bei_district');
INSERT INTO `address_county` VALUES ('559', '58', '镇赉县', 'zhen_zuo_county');
INSERT INTO `address_county` VALUES ('560', '58', '通榆县', 'tong_yu_county');
INSERT INTO `address_county` VALUES ('561', '58', '洮南市', 'zuo_nan_city');
INSERT INTO `address_county` VALUES ('562', '58', '大安市', 'da_an_city');
INSERT INTO `address_county` VALUES ('563', '59', '延吉市', 'yan_ji_city');
INSERT INTO `address_county` VALUES ('564', '59', '图们市', 'tu_men_city');
INSERT INTO `address_county` VALUES ('565', '59', '敦化市', 'dun_hua_city');
INSERT INTO `address_county` VALUES ('566', '59', '珲春市', 'zuo_chun_city');
INSERT INTO `address_county` VALUES ('567', '59', '龙井市', 'long_jing_city');
INSERT INTO `address_county` VALUES ('568', '59', '和龙市', 'he_long_city');
INSERT INTO `address_county` VALUES ('569', '59', '汪清县', 'wang_qing_county');
INSERT INTO `address_county` VALUES ('570', '59', '安图县', 'an_tu_county');
INSERT INTO `address_county` VALUES ('571', '60', '道里区', 'dao_li_district');
INSERT INTO `address_county` VALUES ('572', '60', '南岗区', 'nan_gang_district');
INSERT INTO `address_county` VALUES ('573', '60', '道外区', 'dao_wai_district');
INSERT INTO `address_county` VALUES ('574', '60', '平房区', 'ping_fang_district');
INSERT INTO `address_county` VALUES ('575', '60', '松北区', 'song_bei_district');
INSERT INTO `address_county` VALUES ('576', '60', '香坊区', 'xiang_fang_district');
INSERT INTO `address_county` VALUES ('577', '60', '呼兰区', 'hu_lan_district');
INSERT INTO `address_county` VALUES ('578', '60', '阿城区', 'a_cheng_district');
INSERT INTO `address_county` VALUES ('579', '60', '依兰县', 'yi_lan_county');
INSERT INTO `address_county` VALUES ('580', '60', '方正县', 'fang_zheng_county');
INSERT INTO `address_county` VALUES ('581', '60', '宾县', 'bin_county');
INSERT INTO `address_county` VALUES ('582', '60', '巴彦县', 'ba_yan_county');
INSERT INTO `address_county` VALUES ('583', '60', '木兰县', 'mu_lan_county');
INSERT INTO `address_county` VALUES ('584', '60', '通河县', 'tong_he_county');
INSERT INTO `address_county` VALUES ('585', '60', '延寿县', 'yan_shou_county');
INSERT INTO `address_county` VALUES ('586', '60', '双城市', 'shuang_cheng_city');
INSERT INTO `address_county` VALUES ('587', '60', '尚志市', 'shang_zhi_city');
INSERT INTO `address_county` VALUES ('588', '60', '五常市', 'wu_chang_city');
INSERT INTO `address_county` VALUES ('589', '61', '龙沙区', 'long_sha_district');
INSERT INTO `address_county` VALUES ('590', '61', '建华区', 'jian_hua_district');
INSERT INTO `address_county` VALUES ('591', '61', '铁锋区', 'tie_feng_district');
INSERT INTO `address_county` VALUES ('592', '61', '昂昂溪区', 'ang_ang_xi_district');
INSERT INTO `address_county` VALUES ('593', '61', '富拉尔基区', 'fu_la_er_ji_district');
INSERT INTO `address_county` VALUES ('594', '61', '碾子山区', 'nian_zi_shan_district');
INSERT INTO `address_county` VALUES ('595', '61', '梅里斯达斡尔族区', 'mei_li_si_da_wo_er_zu_district');
INSERT INTO `address_county` VALUES ('596', '61', '龙江县', 'long_jiang_county');
INSERT INTO `address_county` VALUES ('597', '61', '依安县', 'yi_an_county');
INSERT INTO `address_county` VALUES ('598', '61', '泰来县', 'tai_lai_county');
INSERT INTO `address_county` VALUES ('599', '61', '甘南县', 'gan_nan_county');
INSERT INTO `address_county` VALUES ('600', '61', '富裕县', 'fu_yu_county');
INSERT INTO `address_county` VALUES ('601', '61', '克山县', 'ke_shan_county');
INSERT INTO `address_county` VALUES ('602', '61', '克东县', 'ke_dong_county');
INSERT INTO `address_county` VALUES ('603', '61', '拜泉县', 'bai_quan_county');
INSERT INTO `address_county` VALUES ('604', '61', '讷河市', 'zuo_he_city');
INSERT INTO `address_county` VALUES ('605', '62', '鸡冠区', 'ji_guan_district');
INSERT INTO `address_county` VALUES ('606', '62', '恒山区', 'heng_shan_district');
INSERT INTO `address_county` VALUES ('607', '62', '滴道区', 'di_dao_district');
INSERT INTO `address_county` VALUES ('608', '62', '梨树区', 'li_shu_district');
INSERT INTO `address_county` VALUES ('609', '62', '城子河区', 'cheng_zi_he_district');
INSERT INTO `address_county` VALUES ('610', '62', '麻山区', 'ma_shan_district');
INSERT INTO `address_county` VALUES ('611', '62', '鸡东县', 'ji_dong_county');
INSERT INTO `address_county` VALUES ('612', '62', '虎林市', 'hu_lin_city');
INSERT INTO `address_county` VALUES ('613', '62', '密山市', 'mi_shan_city');
INSERT INTO `address_county` VALUES ('614', '63', '向阳区', 'xiang_yang_district');
INSERT INTO `address_county` VALUES ('615', '63', '工农区', 'gong_nong_district');
INSERT INTO `address_county` VALUES ('616', '63', '南山区', 'nan_shan_district');
INSERT INTO `address_county` VALUES ('617', '63', '兴安区', 'xing_an_district');
INSERT INTO `address_county` VALUES ('618', '63', '东山区', 'dong_shan_district');
INSERT INTO `address_county` VALUES ('619', '63', '兴山区', 'xing_shan_district');
INSERT INTO `address_county` VALUES ('620', '63', '萝北县', 'luo_bei_county');
INSERT INTO `address_county` VALUES ('621', '63', '绥滨县', 'sui_bin_county');
INSERT INTO `address_county` VALUES ('622', '64', '尖山区', 'jian_shan_district');
INSERT INTO `address_county` VALUES ('623', '64', '岭东区', 'ling_dong_district');
INSERT INTO `address_county` VALUES ('624', '64', '四方台区', 'si_fang_tai_district');
INSERT INTO `address_county` VALUES ('625', '64', '宝山区', 'bao_shan_district');
INSERT INTO `address_county` VALUES ('626', '64', '集贤县', 'ji_xian_county');
INSERT INTO `address_county` VALUES ('627', '64', '友谊县', 'you_yi_county');
INSERT INTO `address_county` VALUES ('628', '64', '宝清县', 'bao_qing_county');
INSERT INTO `address_county` VALUES ('629', '64', '饶河县', 'rao_he_county');
INSERT INTO `address_county` VALUES ('630', '65', '萨尔图区', 'sa_er_tu_district');
INSERT INTO `address_county` VALUES ('631', '65', '龙凤区', 'long_feng_district');
INSERT INTO `address_county` VALUES ('632', '65', '让胡路区', 'rang_hu_lu_district');
INSERT INTO `address_county` VALUES ('633', '65', '红岗区', 'hong_gang_district');
INSERT INTO `address_county` VALUES ('634', '65', '大同区', 'da_tong_district');
INSERT INTO `address_county` VALUES ('635', '65', '肇州县', 'zhao_zhou_county');
INSERT INTO `address_county` VALUES ('636', '65', '肇源县', 'zhao_yuan_county');
INSERT INTO `address_county` VALUES ('637', '65', '林甸县', 'lin_dian_county');
INSERT INTO `address_county` VALUES ('638', '65', '杜尔伯特蒙古族自治县', 'du_er_bo_te_meng_gu_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('639', '66', '伊春区', 'yi_chun_district');
INSERT INTO `address_county` VALUES ('640', '66', '南岔区', 'nan_cha_district');
INSERT INTO `address_county` VALUES ('641', '66', '友好区', 'you_hao_district');
INSERT INTO `address_county` VALUES ('642', '66', '西林区', 'xi_lin_district');
INSERT INTO `address_county` VALUES ('643', '66', '翠峦区', 'cui_luan_district');
INSERT INTO `address_county` VALUES ('644', '66', '新青区', 'xin_qing_district');
INSERT INTO `address_county` VALUES ('645', '66', '美溪区', 'mei_xi_district');
INSERT INTO `address_county` VALUES ('646', '66', '金山屯区', 'jin_shan_tun_district');
INSERT INTO `address_county` VALUES ('647', '66', '五营区', 'wu_ying_district');
INSERT INTO `address_county` VALUES ('648', '66', '乌马河区', 'wu_ma_he_district');
INSERT INTO `address_county` VALUES ('649', '66', '汤旺河区', 'tang_wang_he_district');
INSERT INTO `address_county` VALUES ('650', '66', '带岭区', 'dai_ling_district');
INSERT INTO `address_county` VALUES ('651', '66', '乌伊岭区', 'wu_yi_ling_district');
INSERT INTO `address_county` VALUES ('652', '66', '红星区', 'hong_xing_district');
INSERT INTO `address_county` VALUES ('653', '66', '上甘岭区', 'shang_gan_ling_district');
INSERT INTO `address_county` VALUES ('654', '66', '嘉荫县', 'jia_yin_county');
INSERT INTO `address_county` VALUES ('655', '66', '铁力市', 'tie_li_city');
INSERT INTO `address_county` VALUES ('656', '67', '前进区', 'qian_jin_district');
INSERT INTO `address_county` VALUES ('657', '67', '东风区', 'dong_feng_district');
INSERT INTO `address_county` VALUES ('658', '67', '桦南县', 'zuo_nan_county');
INSERT INTO `address_county` VALUES ('659', '67', '桦川县', 'zuo_chuan_county');
INSERT INTO `address_county` VALUES ('660', '67', '汤原县', 'tang_yuan_county');
INSERT INTO `address_county` VALUES ('661', '67', '抚远县', 'fu_yuan_county');
INSERT INTO `address_county` VALUES ('662', '67', '同江市', 'tong_jiang_city');
INSERT INTO `address_county` VALUES ('663', '67', '富锦市', 'fu_jin_city');
INSERT INTO `address_county` VALUES ('664', '68', '新兴区', 'xin_xing_district');
INSERT INTO `address_county` VALUES ('665', '68', '桃山区', 'tao_shan_district');
INSERT INTO `address_county` VALUES ('666', '68', '茄子河区', 'qie_zi_he_district');
INSERT INTO `address_county` VALUES ('667', '68', '勃利县', 'bo_li_county');
INSERT INTO `address_county` VALUES ('668', '69', '东安区', 'dong_an_district');
INSERT INTO `address_county` VALUES ('669', '69', '阳明区', 'yang_ming_district');
INSERT INTO `address_county` VALUES ('670', '69', '爱民区', 'ai_min_district');
INSERT INTO `address_county` VALUES ('671', '69', '东宁县', 'dong_ning_county');
INSERT INTO `address_county` VALUES ('672', '69', '林口县', 'lin_kou_county');
INSERT INTO `address_county` VALUES ('673', '69', '绥芬河市', 'sui_fen_he_city');
INSERT INTO `address_county` VALUES ('674', '69', '海林市', 'hai_lin_city');
INSERT INTO `address_county` VALUES ('675', '69', '宁安市', 'ning_an_city');
INSERT INTO `address_county` VALUES ('676', '69', '穆棱市', 'mu_leng_city');
INSERT INTO `address_county` VALUES ('677', '70', '爱辉区', 'ai_hui_district');
INSERT INTO `address_county` VALUES ('678', '70', '嫩江县', 'nen_jiang_county');
INSERT INTO `address_county` VALUES ('679', '70', '逊克县', 'xun_ke_county');
INSERT INTO `address_county` VALUES ('680', '70', '孙吴县', 'sun_wu_county');
INSERT INTO `address_county` VALUES ('681', '70', '北安市', 'bei_an_city');
INSERT INTO `address_county` VALUES ('682', '70', '五大连池市', 'wu_da_lian_chi_city');
INSERT INTO `address_county` VALUES ('683', '71', '北林区', 'bei_lin_district');
INSERT INTO `address_county` VALUES ('684', '71', '望奎县', 'wang_kui_county');
INSERT INTO `address_county` VALUES ('685', '71', '兰西县', 'lan_xi_county');
INSERT INTO `address_county` VALUES ('686', '71', '青冈县', 'qing_gang_county');
INSERT INTO `address_county` VALUES ('687', '71', '庆安县', 'qing_an_county');
INSERT INTO `address_county` VALUES ('688', '71', '明水县', 'ming_shui_county');
INSERT INTO `address_county` VALUES ('689', '71', '绥棱县', 'sui_leng_county');
INSERT INTO `address_county` VALUES ('690', '71', '安达市', 'an_da_city');
INSERT INTO `address_county` VALUES ('691', '71', '肇东市', 'zhao_dong_city');
INSERT INTO `address_county` VALUES ('692', '71', '海伦市', 'hai_lun_city');
INSERT INTO `address_county` VALUES ('693', '72', '呼玛县', 'hu_ma_county');
INSERT INTO `address_county` VALUES ('694', '72', '塔河县', 'ta_he_county');
INSERT INTO `address_county` VALUES ('695', '72', '漠河县', 'mo_he_county');
INSERT INTO `address_county` VALUES ('696', '73', '黄浦区', 'huang_pu_district');
INSERT INTO `address_county` VALUES ('697', '73', '徐汇区', 'xu_hui_district');
INSERT INTO `address_county` VALUES ('698', '73', '长宁区', 'chang_ning_district');
INSERT INTO `address_county` VALUES ('699', '73', '静安区', 'jing_an_district');
INSERT INTO `address_county` VALUES ('700', '73', '普陀区', 'pu_tuo_district');
INSERT INTO `address_county` VALUES ('701', '73', '闸北区', 'zha_bei_district');
INSERT INTO `address_county` VALUES ('702', '73', '虹口区', 'hong_kou_district');
INSERT INTO `address_county` VALUES ('703', '73', '杨浦区', 'yang_pu_district');
INSERT INTO `address_county` VALUES ('704', '73', '闵行区', 'zuo_xing_district');
INSERT INTO `address_county` VALUES ('705', '73', '嘉定区', 'jia_ding_district');
INSERT INTO `address_county` VALUES ('706', '73', '浦东新区', 'pu_dong_xin_district');
INSERT INTO `address_county` VALUES ('707', '73', '金山区', 'jin_shan_district');
INSERT INTO `address_county` VALUES ('708', '73', '松江区', 'song_jiang_district');
INSERT INTO `address_county` VALUES ('709', '73', '青浦区', 'qing_pu_district');
INSERT INTO `address_county` VALUES ('710', '73', '奉贤区', 'feng_xian_district');
INSERT INTO `address_county` VALUES ('711', '73', '崇明县', 'chong_ming_county');
INSERT INTO `address_county` VALUES ('712', '74', '玄武区', 'xuan_wu_district');
INSERT INTO `address_county` VALUES ('713', '74', '白下区', 'bai_xia_district');
INSERT INTO `address_county` VALUES ('714', '74', '秦淮区', 'qin_huai_district');
INSERT INTO `address_county` VALUES ('715', '74', '建邺区', 'jian_zuo_district');
INSERT INTO `address_county` VALUES ('716', '74', '鼓楼区', 'gu_lou_district');
INSERT INTO `address_county` VALUES ('717', '74', '下关区', 'xia_guan_district');
INSERT INTO `address_county` VALUES ('718', '74', '浦口区', 'pu_kou_district');
INSERT INTO `address_county` VALUES ('719', '74', '栖霞区', 'qi_xia_district');
INSERT INTO `address_county` VALUES ('720', '74', '雨花台区', 'yu_hua_tai_district');
INSERT INTO `address_county` VALUES ('721', '74', '江宁区', 'jiang_ning_district');
INSERT INTO `address_county` VALUES ('722', '74', '六合区', 'liu_he_district');
INSERT INTO `address_county` VALUES ('723', '74', '溧水县', 'zuo_shui_county');
INSERT INTO `address_county` VALUES ('724', '74', '高淳县', 'gao_chun_county');
INSERT INTO `address_county` VALUES ('725', '75', '崇安区', 'chong_an_district');
INSERT INTO `address_county` VALUES ('726', '75', '南长区', 'nan_chang_district');
INSERT INTO `address_county` VALUES ('727', '75', '北塘区', 'bei_tang_district');
INSERT INTO `address_county` VALUES ('728', '75', '锡山区', 'xi_shan_district');
INSERT INTO `address_county` VALUES ('729', '75', '惠山区', 'hui_shan_district');
INSERT INTO `address_county` VALUES ('730', '75', '滨湖区', 'bin_hu_district');
INSERT INTO `address_county` VALUES ('731', '75', '江阴市', 'jiang_yin_city');
INSERT INTO `address_county` VALUES ('732', '75', '宜兴市', 'yi_xing_city');
INSERT INTO `address_county` VALUES ('733', '76', '云龙区', 'yun_long_district');
INSERT INTO `address_county` VALUES ('734', '76', '贾汪区', 'jia_wang_district');
INSERT INTO `address_county` VALUES ('735', '76', '泉山区', 'quan_shan_district');
INSERT INTO `address_county` VALUES ('736', '76', '铜山区', 'tong_shan_district');
INSERT INTO `address_county` VALUES ('737', '76', '丰县', 'feng_county');
INSERT INTO `address_county` VALUES ('738', '76', '沛县', 'pei_county');
INSERT INTO `address_county` VALUES ('739', '76', '睢宁县', 'zuo_ning_county');
INSERT INTO `address_county` VALUES ('740', '76', '新沂市', 'xin_yi_city');
INSERT INTO `address_county` VALUES ('741', '76', '邳州市', 'zuo_zhou_city');
INSERT INTO `address_county` VALUES ('742', '77', '天宁区', 'tian_ning_district');
INSERT INTO `address_county` VALUES ('743', '77', '钟楼区', 'zhong_lou_district');
INSERT INTO `address_county` VALUES ('744', '77', '戚墅堰区', 'qi_shu_yan_district');
INSERT INTO `address_county` VALUES ('745', '77', '新北区', 'xin_bei_district');
INSERT INTO `address_county` VALUES ('746', '77', '武进区', 'wu_jin_district');
INSERT INTO `address_county` VALUES ('747', '77', '溧阳市', 'zuo_yang_city');
INSERT INTO `address_county` VALUES ('748', '77', '金坛市', 'jin_tan_city');
INSERT INTO `address_county` VALUES ('749', '78', '沧浪区', 'cang_lang_district');
INSERT INTO `address_county` VALUES ('750', '78', '平江区', 'ping_jiang_district');
INSERT INTO `address_county` VALUES ('751', '78', '金阊区', 'jin_zuo_district');
INSERT INTO `address_county` VALUES ('752', '78', '虎丘区', 'hu_qiu_district');
INSERT INTO `address_county` VALUES ('753', '78', '吴中区', 'wu_zhong_district');
INSERT INTO `address_county` VALUES ('754', '78', '相城区', 'xiang_cheng_district');
INSERT INTO `address_county` VALUES ('755', '78', '常熟市', 'chang_shu_city');
INSERT INTO `address_county` VALUES ('756', '78', '张家港市', 'zhang_jia_gang_city');
INSERT INTO `address_county` VALUES ('757', '78', '昆山市', 'kun_shan_city');
INSERT INTO `address_county` VALUES ('758', '78', '吴江市', 'wu_jiang_city');
INSERT INTO `address_county` VALUES ('759', '78', '太仓市', 'tai_cang_city');
INSERT INTO `address_county` VALUES ('760', '79', '崇川区', 'chong_chuan_district');
INSERT INTO `address_county` VALUES ('761', '79', '港闸区', 'gang_zha_district');
INSERT INTO `address_county` VALUES ('762', '79', '海安县', 'hai_an_county');
INSERT INTO `address_county` VALUES ('763', '79', '如东县', 'ru_dong_county');
INSERT INTO `address_county` VALUES ('764', '79', '启东市', 'qi_dong_city');
INSERT INTO `address_county` VALUES ('765', '79', '如皋市', 'ru_gao_city');
INSERT INTO `address_county` VALUES ('766', '79', '海门市', 'hai_men_city');
INSERT INTO `address_county` VALUES ('767', '80', '连云区', 'lian_yun_district');
INSERT INTO `address_county` VALUES ('768', '80', '新浦区', 'xin_pu_district');
INSERT INTO `address_county` VALUES ('769', '80', '赣榆县', 'gan_yu_county');
INSERT INTO `address_county` VALUES ('770', '80', '东海县', 'dong_hai_county');
INSERT INTO `address_county` VALUES ('771', '80', '灌云县', 'guan_yun_county');
INSERT INTO `address_county` VALUES ('772', '80', '灌南县', 'guan_nan_county');
INSERT INTO `address_county` VALUES ('773', '81', '楚州区', 'chu_zhou_district');
INSERT INTO `address_county` VALUES ('774', '81', '淮阴区', 'huai_yin_district');
INSERT INTO `address_county` VALUES ('775', '81', '清浦区', 'qing_pu_district');
INSERT INTO `address_county` VALUES ('776', '81', '涟水县', 'lian_shui_county');
INSERT INTO `address_county` VALUES ('777', '81', '洪泽县', 'hong_ze_county');
INSERT INTO `address_county` VALUES ('778', '81', '盱眙县', 'zuo_zuo_county');
INSERT INTO `address_county` VALUES ('779', '81', '金湖县', 'jin_hu_county');
INSERT INTO `address_county` VALUES ('780', '82', '亭湖区', 'ting_hu_district');
INSERT INTO `address_county` VALUES ('781', '82', '盐都区', 'yan_du_district');
INSERT INTO `address_county` VALUES ('782', '82', '响水县', 'xiang_shui_county');
INSERT INTO `address_county` VALUES ('783', '82', '滨海县', 'bin_hai_county');
INSERT INTO `address_county` VALUES ('784', '82', '阜宁县', 'fu_ning_county');
INSERT INTO `address_county` VALUES ('785', '82', '射阳县', 'she_yang_county');
INSERT INTO `address_county` VALUES ('786', '82', '建湖县', 'jian_hu_county');
INSERT INTO `address_county` VALUES ('787', '82', '东台市', 'dong_tai_city');
INSERT INTO `address_county` VALUES ('788', '82', '大丰市', 'da_feng_city');
INSERT INTO `address_county` VALUES ('789', '83', '广陵区', 'guang_ling_district');
INSERT INTO `address_county` VALUES ('790', '83', '邗江区', 'zuo_jiang_district');
INSERT INTO `address_county` VALUES ('791', '83', '江都区', 'jiang_du_district');
INSERT INTO `address_county` VALUES ('792', '83', '宝应县', 'bao_ying_county');
INSERT INTO `address_county` VALUES ('793', '83', '仪征市', 'yi_zheng_city');
INSERT INTO `address_county` VALUES ('794', '83', '高邮市', 'gao_you_city');
INSERT INTO `address_county` VALUES ('795', '84', '京口区', 'jing_kou_district');
INSERT INTO `address_county` VALUES ('796', '84', '润州区', 'run_zhou_district');
INSERT INTO `address_county` VALUES ('797', '84', '丹徒区', 'dan_tu_district');
INSERT INTO `address_county` VALUES ('798', '84', '丹阳市', 'dan_yang_city');
INSERT INTO `address_county` VALUES ('799', '84', '扬中市', 'yang_zhong_city');
INSERT INTO `address_county` VALUES ('800', '84', '句容市', 'ju_rong_city');
INSERT INTO `address_county` VALUES ('801', '85', '海陵区', 'hai_ling_district');
INSERT INTO `address_county` VALUES ('802', '85', '高港区', 'gao_gang_district');
INSERT INTO `address_county` VALUES ('803', '85', '兴化市', 'xing_hua_city');
INSERT INTO `address_county` VALUES ('804', '85', '靖江市', 'jing_jiang_city');
INSERT INTO `address_county` VALUES ('805', '85', '泰兴市', 'tai_xing_city');
INSERT INTO `address_county` VALUES ('806', '85', '姜堰市', 'jiang_yan_city');
INSERT INTO `address_county` VALUES ('807', '86', '宿城区', 'su_cheng_district');
INSERT INTO `address_county` VALUES ('808', '86', '宿豫区', 'su_yu_district');
INSERT INTO `address_county` VALUES ('809', '86', '沭阳县', 'zuo_yang_county');
INSERT INTO `address_county` VALUES ('810', '86', '泗阳县', 'zuo_yang_county');
INSERT INTO `address_county` VALUES ('811', '86', '泗洪县', 'zuo_hong_county');
INSERT INTO `address_county` VALUES ('812', '87', '上城区', 'shang_cheng_district');
INSERT INTO `address_county` VALUES ('813', '87', '下城区', 'xia_cheng_district');
INSERT INTO `address_county` VALUES ('814', '87', '江干区', 'jiang_gan_district');
INSERT INTO `address_county` VALUES ('815', '87', '拱墅区', 'gong_shu_district');
INSERT INTO `address_county` VALUES ('816', '87', '西湖区', 'xi_hu_district');
INSERT INTO `address_county` VALUES ('817', '87', '滨江区', 'bin_jiang_district');
INSERT INTO `address_county` VALUES ('818', '87', '萧山区', 'xiao_shan_district');
INSERT INTO `address_county` VALUES ('819', '87', '余杭区', 'yu_hang_district');
INSERT INTO `address_county` VALUES ('820', '87', '桐庐县', 'tong_lu_county');
INSERT INTO `address_county` VALUES ('821', '87', '淳安县', 'chun_an_county');
INSERT INTO `address_county` VALUES ('822', '87', '建德市', 'jian_de_city');
INSERT INTO `address_county` VALUES ('823', '87', '富阳市', 'fu_yang_city');
INSERT INTO `address_county` VALUES ('824', '87', '临安市', 'lin_an_city');
INSERT INTO `address_county` VALUES ('825', '88', '海曙区', 'hai_shu_district');
INSERT INTO `address_county` VALUES ('826', '88', '江东区', 'jiang_dong_district');
INSERT INTO `address_county` VALUES ('827', '88', '江北区', 'jiang_bei_district');
INSERT INTO `address_county` VALUES ('828', '88', '北仑区', 'bei_lun_district');
INSERT INTO `address_county` VALUES ('829', '88', '镇海区', 'zhen_hai_district');
INSERT INTO `address_county` VALUES ('830', '88', '鄞州区', 'zuo_zhou_district');
INSERT INTO `address_county` VALUES ('831', '88', '象山县', 'xiang_shan_county');
INSERT INTO `address_county` VALUES ('832', '88', '宁海县', 'ning_hai_county');
INSERT INTO `address_county` VALUES ('833', '88', '余姚市', 'yu_yao_city');
INSERT INTO `address_county` VALUES ('834', '88', '慈溪市', 'ci_xi_city');
INSERT INTO `address_county` VALUES ('835', '88', '奉化市', 'feng_hua_city');
INSERT INTO `address_county` VALUES ('836', '89', '鹿城区', 'lu_cheng_district');
INSERT INTO `address_county` VALUES ('837', '89', '龙湾区', 'long_wan_district');
INSERT INTO `address_county` VALUES ('838', '89', '瓯海区', 'zuo_hai_district');
INSERT INTO `address_county` VALUES ('839', '89', '洞头县', 'dong_tou_county');
INSERT INTO `address_county` VALUES ('840', '89', '永嘉县', 'yong_jia_county');
INSERT INTO `address_county` VALUES ('841', '89', '平阳县', 'ping_yang_county');
INSERT INTO `address_county` VALUES ('842', '89', '苍南县', 'cang_nan_county');
INSERT INTO `address_county` VALUES ('843', '89', '文成县', 'wen_cheng_county');
INSERT INTO `address_county` VALUES ('844', '89', '泰顺县', 'tai_shun_county');
INSERT INTO `address_county` VALUES ('845', '89', '瑞安市', 'rui_an_city');
INSERT INTO `address_county` VALUES ('846', '89', '乐清市', 'le_qing_city');
INSERT INTO `address_county` VALUES ('847', '90', '南湖区', 'nan_hu_district');
INSERT INTO `address_county` VALUES ('848', '90', '秀洲区', 'xiu_zhou_district');
INSERT INTO `address_county` VALUES ('849', '90', '嘉善县', 'jia_shan_county');
INSERT INTO `address_county` VALUES ('850', '90', '海盐县', 'hai_yan_county');
INSERT INTO `address_county` VALUES ('851', '90', '海宁市', 'hai_ning_city');
INSERT INTO `address_county` VALUES ('852', '90', '平湖市', 'ping_hu_city');
INSERT INTO `address_county` VALUES ('853', '90', '桐乡市', 'tong_xiang_city');
INSERT INTO `address_county` VALUES ('854', '91', '吴兴区', 'wu_xing_district');
INSERT INTO `address_county` VALUES ('855', '91', '南浔区', 'nan_zuo_district');
INSERT INTO `address_county` VALUES ('856', '91', '德清县', 'de_qing_county');
INSERT INTO `address_county` VALUES ('857', '91', '长兴县', 'chang_xing_county');
INSERT INTO `address_county` VALUES ('858', '91', '安吉县', 'an_ji_county');
INSERT INTO `address_county` VALUES ('859', '92', '越城区', 'yue_cheng_district');
INSERT INTO `address_county` VALUES ('860', '92', '绍兴县', 'shao_xing_county');
INSERT INTO `address_county` VALUES ('861', '92', '新昌县', 'xin_chang_county');
INSERT INTO `address_county` VALUES ('862', '92', '诸暨市', 'zhu_zuo_city');
INSERT INTO `address_county` VALUES ('863', '92', '上虞市', 'shang_yu_city');
INSERT INTO `address_county` VALUES ('864', '92', '嵊州市', 'zuo_zhou_city');
INSERT INTO `address_county` VALUES ('865', '93', '婺城区', 'zuo_cheng_district');
INSERT INTO `address_county` VALUES ('866', '93', '金东区', 'jin_dong_district');
INSERT INTO `address_county` VALUES ('867', '93', '武义县', 'wu_yi_county');
INSERT INTO `address_county` VALUES ('868', '93', '浦江县', 'pu_jiang_county');
INSERT INTO `address_county` VALUES ('869', '93', '磐安县', 'pan_an_county');
INSERT INTO `address_county` VALUES ('870', '93', '兰溪市', 'lan_xi_city');
INSERT INTO `address_county` VALUES ('871', '93', '义乌市', 'yi_wu_city');
INSERT INTO `address_county` VALUES ('872', '93', '东阳市', 'dong_yang_city');
INSERT INTO `address_county` VALUES ('873', '93', '永康市', 'yong_kang_city');
INSERT INTO `address_county` VALUES ('874', '94', '柯城区', 'ke_cheng_district');
INSERT INTO `address_county` VALUES ('875', '94', '衢江区', 'zuo_jiang_district');
INSERT INTO `address_county` VALUES ('876', '94', '常山县', 'chang_shan_county');
INSERT INTO `address_county` VALUES ('877', '94', '开化县', 'kai_hua_county');
INSERT INTO `address_county` VALUES ('878', '94', '龙游县', 'long_you_county');
INSERT INTO `address_county` VALUES ('879', '94', '江山市', 'jiang_shan_city');
INSERT INTO `address_county` VALUES ('880', '95', '定海区', 'ding_hai_district');
INSERT INTO `address_county` VALUES ('881', '95', '岱山县', 'zuo_shan_county');
INSERT INTO `address_county` VALUES ('882', '95', '嵊泗县', 'zuo_zuo_county');
INSERT INTO `address_county` VALUES ('883', '96', '椒江区', 'jiao_jiang_district');
INSERT INTO `address_county` VALUES ('884', '96', '黄岩区', 'huang_yan_district');
INSERT INTO `address_county` VALUES ('885', '96', '路桥区', 'lu_qiao_district');
INSERT INTO `address_county` VALUES ('886', '96', '玉环县', 'yu_huan_county');
INSERT INTO `address_county` VALUES ('887', '96', '三门县', 'san_men_county');
INSERT INTO `address_county` VALUES ('888', '96', '天台县', 'tian_tai_county');
INSERT INTO `address_county` VALUES ('889', '96', '仙居县', 'xian_ju_county');
INSERT INTO `address_county` VALUES ('890', '96', '温岭市', 'wen_ling_city');
INSERT INTO `address_county` VALUES ('891', '96', '临海市', 'lin_hai_city');
INSERT INTO `address_county` VALUES ('892', '97', '莲都区', 'lian_du_district');
INSERT INTO `address_county` VALUES ('893', '97', '青田县', 'qing_tian_county');
INSERT INTO `address_county` VALUES ('894', '97', '缙云县', 'zuo_yun_county');
INSERT INTO `address_county` VALUES ('895', '97', '遂昌县', 'sui_chang_county');
INSERT INTO `address_county` VALUES ('896', '97', '松阳县', 'song_yang_county');
INSERT INTO `address_county` VALUES ('897', '97', '云和县', 'yun_he_county');
INSERT INTO `address_county` VALUES ('898', '97', '庆元县', 'qing_yuan_county');
INSERT INTO `address_county` VALUES ('899', '97', '景宁畲族自治县', 'jing_ning_zuo_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('900', '97', '龙泉市', 'long_quan_city');
INSERT INTO `address_county` VALUES ('901', '98', '瑶海区', 'yao_hai_district');
INSERT INTO `address_county` VALUES ('902', '98', '庐阳区', 'lu_yang_district');
INSERT INTO `address_county` VALUES ('903', '98', '蜀山区', 'shu_shan_district');
INSERT INTO `address_county` VALUES ('904', '98', '包河区', 'bao_he_district');
INSERT INTO `address_county` VALUES ('905', '98', '长丰县', 'chang_feng_county');
INSERT INTO `address_county` VALUES ('906', '98', '肥东县', 'fei_dong_county');
INSERT INTO `address_county` VALUES ('907', '98', '肥西县', 'fei_xi_county');
INSERT INTO `address_county` VALUES ('908', '98', '庐江县', 'lu_jiang_county');
INSERT INTO `address_county` VALUES ('909', '98', '巢湖市', 'chao_hu_city');
INSERT INTO `address_county` VALUES ('910', '99', '镜湖区', 'jing_hu_district');
INSERT INTO `address_county` VALUES ('911', '99', '弋江区', 'zuo_jiang_district');
INSERT INTO `address_county` VALUES ('912', '99', '鸠江区', 'zuo_jiang_district');
INSERT INTO `address_county` VALUES ('913', '99', '三山区', 'san_shan_district');
INSERT INTO `address_county` VALUES ('914', '99', '芜湖县', 'wu_hu_county');
INSERT INTO `address_county` VALUES ('915', '99', '繁昌县', 'fan_chang_county');
INSERT INTO `address_county` VALUES ('916', '99', '南陵县', 'nan_ling_county');
INSERT INTO `address_county` VALUES ('917', '99', '无为县', 'wu_wei_county');
INSERT INTO `address_county` VALUES ('918', '100', '龙子湖区', 'long_zi_hu_district');
INSERT INTO `address_county` VALUES ('919', '100', '蚌山区', 'bang_shan_district');
INSERT INTO `address_county` VALUES ('920', '100', '禹会区', 'yu_hui_district');
INSERT INTO `address_county` VALUES ('921', '100', '淮上区', 'huai_shang_district');
INSERT INTO `address_county` VALUES ('922', '100', '怀远县', 'huai_yuan_county');
INSERT INTO `address_county` VALUES ('923', '100', '五河县', 'wu_he_county');
INSERT INTO `address_county` VALUES ('924', '100', '固镇县', 'gu_zhen_county');
INSERT INTO `address_county` VALUES ('925', '101', '大通区', 'da_tong_district');
INSERT INTO `address_county` VALUES ('926', '101', '田家庵区', 'tian_jia_zuo_district');
INSERT INTO `address_county` VALUES ('927', '101', '谢家集区', 'xie_jia_ji_district');
INSERT INTO `address_county` VALUES ('928', '101', '八公山区', 'ba_gong_shan_district');
INSERT INTO `address_county` VALUES ('929', '101', '潘集区', 'pan_ji_district');
INSERT INTO `address_county` VALUES ('930', '101', '凤台县', 'feng_tai_county');
INSERT INTO `address_county` VALUES ('931', '102', '金家庄区', 'jin_jia_zhuang_district');
INSERT INTO `address_county` VALUES ('932', '102', '花山区', 'hua_shan_district');
INSERT INTO `address_county` VALUES ('933', '102', '雨山区', 'yu_shan_district');
INSERT INTO `address_county` VALUES ('934', '102', '当涂县', 'dang_tu_county');
INSERT INTO `address_county` VALUES ('935', '102', '含山县', 'han_shan_county');
INSERT INTO `address_county` VALUES ('936', '102', '和县', 'he_county');
INSERT INTO `address_county` VALUES ('937', '103', '杜集区', 'du_ji_district');
INSERT INTO `address_county` VALUES ('938', '103', '相山区', 'xiang_shan_district');
INSERT INTO `address_county` VALUES ('939', '103', '烈山区', 'lie_shan_district');
INSERT INTO `address_county` VALUES ('940', '103', '濉溪县', 'zuo_xi_county');
INSERT INTO `address_county` VALUES ('941', '104', '铜官山区', 'tong_guan_shan_district');
INSERT INTO `address_county` VALUES ('942', '104', '狮子山区', 'shi_zi_shan_district');
INSERT INTO `address_county` VALUES ('943', '104', '铜陵县', 'tong_ling_county');
INSERT INTO `address_county` VALUES ('944', '105', '迎江区', 'ying_jiang_district');
INSERT INTO `address_county` VALUES ('945', '105', '大观区', 'da_guan_district');
INSERT INTO `address_county` VALUES ('946', '105', '宜秀区', 'yi_xiu_district');
INSERT INTO `address_county` VALUES ('947', '105', '怀宁县', 'huai_ning_county');
INSERT INTO `address_county` VALUES ('948', '105', '枞阳县', 'zuo_yang_county');
INSERT INTO `address_county` VALUES ('949', '105', '潜山县', 'qian_shan_county');
INSERT INTO `address_county` VALUES ('950', '105', '太湖县', 'tai_hu_county');
INSERT INTO `address_county` VALUES ('951', '105', '宿松县', 'su_song_county');
INSERT INTO `address_county` VALUES ('952', '105', '望江县', 'wang_jiang_county');
INSERT INTO `address_county` VALUES ('953', '105', '岳西县', 'yue_xi_county');
INSERT INTO `address_county` VALUES ('954', '105', '桐城市', 'tong_cheng_city');
INSERT INTO `address_county` VALUES ('955', '106', '屯溪区', 'tun_xi_district');
INSERT INTO `address_county` VALUES ('956', '106', '黄山区', 'huang_shan_district');
INSERT INTO `address_county` VALUES ('957', '106', '徽州区', 'hui_zhou_district');
INSERT INTO `address_county` VALUES ('958', '106', '歙县', 'zuo_county');
INSERT INTO `address_county` VALUES ('959', '106', '休宁县', 'xiu_ning_county');
INSERT INTO `address_county` VALUES ('960', '106', '黟县', 'zuo_county');
INSERT INTO `address_county` VALUES ('961', '106', '祁门县', 'qi_men_county');
INSERT INTO `address_county` VALUES ('962', '107', '琅琊区', 'lang_zuo_district');
INSERT INTO `address_county` VALUES ('963', '107', '南谯区', 'nan_zuo_district');
INSERT INTO `address_county` VALUES ('964', '107', '来安县', 'lai_an_county');
INSERT INTO `address_county` VALUES ('965', '107', '全椒县', 'quan_jiao_county');
INSERT INTO `address_county` VALUES ('966', '107', '定远县', 'ding_yuan_county');
INSERT INTO `address_county` VALUES ('967', '107', '凤阳县', 'feng_yang_county');
INSERT INTO `address_county` VALUES ('968', '107', '天长市', 'tian_chang_city');
INSERT INTO `address_county` VALUES ('969', '107', '明光市', 'ming_guang_city');
INSERT INTO `address_county` VALUES ('970', '108', '颍州区', 'zuo_zhou_district');
INSERT INTO `address_county` VALUES ('971', '108', '颍东区', 'zuo_dong_district');
INSERT INTO `address_county` VALUES ('972', '108', '颍泉区', 'zuo_quan_district');
INSERT INTO `address_county` VALUES ('973', '108', '临泉县', 'lin_quan_county');
INSERT INTO `address_county` VALUES ('974', '108', '太和县', 'tai_he_county');
INSERT INTO `address_county` VALUES ('975', '108', '阜南县', 'fu_nan_county');
INSERT INTO `address_county` VALUES ('976', '108', '颍上县', 'zuo_shang_county');
INSERT INTO `address_county` VALUES ('977', '108', '界首市', 'jie_shou_city');
INSERT INTO `address_county` VALUES ('978', '109', '埇桥区', 'qiao_district');
INSERT INTO `address_county` VALUES ('979', '109', '砀山县', 'zuo_shan_county');
INSERT INTO `address_county` VALUES ('980', '109', '萧县', 'xiao_county');
INSERT INTO `address_county` VALUES ('981', '109', '灵璧县', 'ling_zuo_county');
INSERT INTO `address_county` VALUES ('982', '109', '泗县', 'zuo_county');
INSERT INTO `address_county` VALUES ('983', '110', '金安区', 'jin_an_district');
INSERT INTO `address_county` VALUES ('984', '110', '裕安区', 'yu_an_district');
INSERT INTO `address_county` VALUES ('985', '110', '寿县', 'shou_county');
INSERT INTO `address_county` VALUES ('986', '110', '霍邱县', 'huo_qiu_county');
INSERT INTO `address_county` VALUES ('987', '110', '舒城县', 'shu_cheng_county');
INSERT INTO `address_county` VALUES ('988', '110', '金寨县', 'jin_zhai_county');
INSERT INTO `address_county` VALUES ('989', '110', '霍山县', 'huo_shan_county');
INSERT INTO `address_county` VALUES ('990', '111', '谯城区', 'zuo_cheng_district');
INSERT INTO `address_county` VALUES ('991', '111', '涡阳县', 'wo_yang_county');
INSERT INTO `address_county` VALUES ('992', '111', '蒙城县', 'meng_cheng_county');
INSERT INTO `address_county` VALUES ('993', '111', '利辛县', 'li_xin_county');
INSERT INTO `address_county` VALUES ('994', '112', '贵池区', 'gui_chi_district');
INSERT INTO `address_county` VALUES ('995', '112', '东至县', 'dong_zhi_county');
INSERT INTO `address_county` VALUES ('996', '112', '石台县', 'shi_tai_county');
INSERT INTO `address_county` VALUES ('997', '112', '青阳县', 'qing_yang_county');
INSERT INTO `address_county` VALUES ('998', '113', '宣州区', 'xuan_zhou_district');
INSERT INTO `address_county` VALUES ('999', '113', '郎溪县', 'lang_xi_county');
INSERT INTO `address_county` VALUES ('1000', '113', '广德县', 'guang_de_county');
INSERT INTO `address_county` VALUES ('1001', '113', '泾县', 'zuo_county');
INSERT INTO `address_county` VALUES ('1002', '113', '绩溪县', 'ji_xi_county');
INSERT INTO `address_county` VALUES ('1003', '113', '旌德县', 'zuo_de_county');
INSERT INTO `address_county` VALUES ('1004', '113', '宁国市', 'ning_guo_city');
INSERT INTO `address_county` VALUES ('1005', '114', '台江区', 'tai_jiang_district');
INSERT INTO `address_county` VALUES ('1006', '114', '仓山区', 'cang_shan_district');
INSERT INTO `address_county` VALUES ('1007', '114', '马尾区', 'ma_wei_district');
INSERT INTO `address_county` VALUES ('1008', '114', '晋安区', 'jin_an_district');
INSERT INTO `address_county` VALUES ('1009', '114', '闽侯县', 'min_hou_county');
INSERT INTO `address_county` VALUES ('1010', '114', '连江县', 'lian_jiang_county');
INSERT INTO `address_county` VALUES ('1011', '114', '罗源县', 'luo_yuan_county');
INSERT INTO `address_county` VALUES ('1012', '114', '闽清县', 'min_qing_county');
INSERT INTO `address_county` VALUES ('1013', '114', '永泰县', 'yong_tai_county');
INSERT INTO `address_county` VALUES ('1014', '114', '平潭县', 'ping_tan_county');
INSERT INTO `address_county` VALUES ('1015', '114', '福清市', 'fu_qing_city');
INSERT INTO `address_county` VALUES ('1016', '114', '长乐市', 'chang_le_city');
INSERT INTO `address_county` VALUES ('1017', '115', '思明区', 'si_ming_district');
INSERT INTO `address_county` VALUES ('1018', '115', '海沧区', 'hai_cang_district');
INSERT INTO `address_county` VALUES ('1019', '115', '湖里区', 'hu_li_district');
INSERT INTO `address_county` VALUES ('1020', '115', '集美区', 'ji_mei_district');
INSERT INTO `address_county` VALUES ('1021', '115', '同安区', 'tong_an_district');
INSERT INTO `address_county` VALUES ('1022', '115', '翔安区', 'xiang_an_district');
INSERT INTO `address_county` VALUES ('1023', '116', '城厢区', 'cheng_xiang_district');
INSERT INTO `address_county` VALUES ('1024', '116', '涵江区', 'han_jiang_district');
INSERT INTO `address_county` VALUES ('1025', '116', '荔城区', 'li_cheng_district');
INSERT INTO `address_county` VALUES ('1026', '116', '秀屿区', 'xiu_yu_district');
INSERT INTO `address_county` VALUES ('1027', '116', '仙游县', 'xian_you_county');
INSERT INTO `address_county` VALUES ('1028', '117', '梅列区', 'mei_lie_district');
INSERT INTO `address_county` VALUES ('1029', '117', '三元区', 'san_yuan_district');
INSERT INTO `address_county` VALUES ('1030', '117', '明溪县', 'ming_xi_county');
INSERT INTO `address_county` VALUES ('1031', '117', '清流县', 'qing_liu_county');
INSERT INTO `address_county` VALUES ('1032', '117', '宁化县', 'ning_hua_county');
INSERT INTO `address_county` VALUES ('1033', '117', '大田县', 'da_tian_county');
INSERT INTO `address_county` VALUES ('1034', '117', '尤溪县', 'you_xi_county');
INSERT INTO `address_county` VALUES ('1035', '117', '沙县', 'sha_county');
INSERT INTO `address_county` VALUES ('1036', '117', '将乐县', 'jiang_le_county');
INSERT INTO `address_county` VALUES ('1037', '117', '泰宁县', 'tai_ning_county');
INSERT INTO `address_county` VALUES ('1038', '117', '建宁县', 'jian_ning_county');
INSERT INTO `address_county` VALUES ('1039', '117', '永安市', 'yong_an_city');
INSERT INTO `address_county` VALUES ('1040', '118', '鲤城区', 'li_cheng_district');
INSERT INTO `address_county` VALUES ('1041', '118', '丰泽区', 'feng_ze_district');
INSERT INTO `address_county` VALUES ('1042', '118', '洛江区', 'luo_jiang_district');
INSERT INTO `address_county` VALUES ('1043', '118', '泉港区', 'quan_gang_district');
INSERT INTO `address_county` VALUES ('1044', '118', '惠安县', 'hui_an_county');
INSERT INTO `address_county` VALUES ('1045', '118', '安溪县', 'an_xi_county');
INSERT INTO `address_county` VALUES ('1046', '118', '永春县', 'yong_chun_county');
INSERT INTO `address_county` VALUES ('1047', '118', '德化县', 'de_hua_county');
INSERT INTO `address_county` VALUES ('1048', '118', '金门县', 'jin_men_county');
INSERT INTO `address_county` VALUES ('1049', '118', '石狮市', 'shi_shi_city');
INSERT INTO `address_county` VALUES ('1050', '118', '晋江市', 'jin_jiang_city');
INSERT INTO `address_county` VALUES ('1051', '118', '南安市', 'nan_an_city');
INSERT INTO `address_county` VALUES ('1052', '119', '芗城区', 'zuo_cheng_district');
INSERT INTO `address_county` VALUES ('1053', '119', '龙文区', 'long_wen_district');
INSERT INTO `address_county` VALUES ('1054', '119', '云霄县', 'yun_xiao_county');
INSERT INTO `address_county` VALUES ('1055', '119', '漳浦县', 'zhang_pu_county');
INSERT INTO `address_county` VALUES ('1056', '119', '诏安县', 'zuo_an_county');
INSERT INTO `address_county` VALUES ('1057', '119', '长泰县', 'chang_tai_county');
INSERT INTO `address_county` VALUES ('1058', '119', '东山县', 'dong_shan_county');
INSERT INTO `address_county` VALUES ('1059', '119', '南靖县', 'nan_jing_county');
INSERT INTO `address_county` VALUES ('1060', '119', '平和县', 'ping_he_county');
INSERT INTO `address_county` VALUES ('1061', '119', '华安县', 'hua_an_county');
INSERT INTO `address_county` VALUES ('1062', '119', '龙海市', 'long_hai_city');
INSERT INTO `address_county` VALUES ('1063', '120', '延平区', 'yan_ping_district');
INSERT INTO `address_county` VALUES ('1064', '120', '顺昌县', 'shun_chang_county');
INSERT INTO `address_county` VALUES ('1065', '120', '浦城县', 'pu_cheng_county');
INSERT INTO `address_county` VALUES ('1066', '120', '光泽县', 'guang_ze_county');
INSERT INTO `address_county` VALUES ('1067', '120', '松溪县', 'song_xi_county');
INSERT INTO `address_county` VALUES ('1068', '120', '政和县', 'zheng_he_county');
INSERT INTO `address_county` VALUES ('1069', '120', '邵武市', 'shao_wu_city');
INSERT INTO `address_county` VALUES ('1070', '120', '武夷山市', 'wu_yi_shan_city');
INSERT INTO `address_county` VALUES ('1071', '120', '建瓯市', 'jian_zuo_city');
INSERT INTO `address_county` VALUES ('1072', '120', '建阳市', 'jian_yang_city');
INSERT INTO `address_county` VALUES ('1073', '121', '新罗区', 'xin_luo_district');
INSERT INTO `address_county` VALUES ('1074', '121', '长汀县', 'chang_ting_county');
INSERT INTO `address_county` VALUES ('1075', '121', '永定县', 'yong_ding_county');
INSERT INTO `address_county` VALUES ('1076', '121', '上杭县', 'shang_hang_county');
INSERT INTO `address_county` VALUES ('1077', '121', '武平县', 'wu_ping_county');
INSERT INTO `address_county` VALUES ('1078', '121', '连城县', 'lian_cheng_county');
INSERT INTO `address_county` VALUES ('1079', '121', '漳平市', 'zhang_ping_city');
INSERT INTO `address_county` VALUES ('1080', '122', '蕉城区', 'jiao_cheng_district');
INSERT INTO `address_county` VALUES ('1081', '122', '霞浦县', 'xia_pu_county');
INSERT INTO `address_county` VALUES ('1082', '122', '古田县', 'gu_tian_county');
INSERT INTO `address_county` VALUES ('1083', '122', '屏南县', 'ping_nan_county');
INSERT INTO `address_county` VALUES ('1084', '122', '寿宁县', 'shou_ning_county');
INSERT INTO `address_county` VALUES ('1085', '122', '周宁县', 'zhou_ning_county');
INSERT INTO `address_county` VALUES ('1086', '122', '柘荣县', 'zuo_rong_county');
INSERT INTO `address_county` VALUES ('1087', '122', '福安市', 'fu_an_city');
INSERT INTO `address_county` VALUES ('1088', '122', '福鼎市', 'fu_ding_city');
INSERT INTO `address_county` VALUES ('1089', '123', '东湖区', 'dong_hu_district');
INSERT INTO `address_county` VALUES ('1090', '123', '青云谱区', 'qing_yun_pu_district');
INSERT INTO `address_county` VALUES ('1091', '123', '湾里区', 'wan_li_district');
INSERT INTO `address_county` VALUES ('1092', '123', '青山湖区', 'qing_shan_hu_district');
INSERT INTO `address_county` VALUES ('1093', '123', '南昌县', 'nan_chang_county');
INSERT INTO `address_county` VALUES ('1094', '123', '新建县', 'xin_jian_county');
INSERT INTO `address_county` VALUES ('1095', '123', '安义县', 'an_yi_county');
INSERT INTO `address_county` VALUES ('1096', '123', '进贤县', 'jin_xian_county');
INSERT INTO `address_county` VALUES ('1097', '124', '昌江区', 'chang_jiang_district');
INSERT INTO `address_county` VALUES ('1098', '124', '珠山区', 'zhu_shan_district');
INSERT INTO `address_county` VALUES ('1099', '124', '浮梁县', 'fu_liang_county');
INSERT INTO `address_county` VALUES ('1100', '124', '乐平市', 'le_ping_city');
INSERT INTO `address_county` VALUES ('1101', '125', '安源区', 'an_yuan_district');
INSERT INTO `address_county` VALUES ('1102', '125', '湘东区', 'xiang_dong_district');
INSERT INTO `address_county` VALUES ('1103', '125', '莲花县', 'lian_hua_county');
INSERT INTO `address_county` VALUES ('1104', '125', '上栗县', 'shang_li_county');
INSERT INTO `address_county` VALUES ('1105', '125', '芦溪县', 'lu_xi_county');
INSERT INTO `address_county` VALUES ('1106', '126', '庐山区', 'lu_shan_district');
INSERT INTO `address_county` VALUES ('1107', '126', '浔阳区', 'zuo_yang_district');
INSERT INTO `address_county` VALUES ('1108', '126', '九江县', 'jiu_jiang_county');
INSERT INTO `address_county` VALUES ('1109', '126', '武宁县', 'wu_ning_county');
INSERT INTO `address_county` VALUES ('1110', '126', '修水县', 'xiu_shui_county');
INSERT INTO `address_county` VALUES ('1111', '126', '永修县', 'yong_xiu_county');
INSERT INTO `address_county` VALUES ('1112', '126', '德安县', 'de_an_county');
INSERT INTO `address_county` VALUES ('1113', '126', '星子县', 'xing_zi_county');
INSERT INTO `address_county` VALUES ('1114', '126', '都昌县', 'du_chang_county');
INSERT INTO `address_county` VALUES ('1115', '126', '湖口县', 'hu_kou_county');
INSERT INTO `address_county` VALUES ('1116', '126', '彭泽县', 'peng_ze_county');
INSERT INTO `address_county` VALUES ('1117', '126', '瑞昌市', 'rui_chang_city');
INSERT INTO `address_county` VALUES ('1118', '126', '共青城市', 'gong_qing_cheng_city');
INSERT INTO `address_county` VALUES ('1119', '127', '渝水区', 'yu_shui_district');
INSERT INTO `address_county` VALUES ('1120', '127', '分宜县', 'fen_yi_county');
INSERT INTO `address_county` VALUES ('1121', '128', '月湖区', 'yue_hu_district');
INSERT INTO `address_county` VALUES ('1122', '128', '余江县', 'yu_jiang_county');
INSERT INTO `address_county` VALUES ('1123', '128', '贵溪市', 'gui_xi_city');
INSERT INTO `address_county` VALUES ('1124', '129', '章贡区', 'zhang_gong_district');
INSERT INTO `address_county` VALUES ('1125', '129', '赣县', 'gan_county');
INSERT INTO `address_county` VALUES ('1126', '129', '信丰县', 'xin_feng_county');
INSERT INTO `address_county` VALUES ('1127', '129', '大余县', 'da_yu_county');
INSERT INTO `address_county` VALUES ('1128', '129', '上犹县', 'shang_you_county');
INSERT INTO `address_county` VALUES ('1129', '129', '崇义县', 'chong_yi_county');
INSERT INTO `address_county` VALUES ('1130', '129', '安远县', 'an_yuan_county');
INSERT INTO `address_county` VALUES ('1131', '129', '龙南县', 'long_nan_county');
INSERT INTO `address_county` VALUES ('1132', '129', '定南县', 'ding_nan_county');
INSERT INTO `address_county` VALUES ('1133', '129', '全南县', 'quan_nan_county');
INSERT INTO `address_county` VALUES ('1134', '129', '宁都县', 'ning_du_county');
INSERT INTO `address_county` VALUES ('1135', '129', '于都县', 'yu_du_county');
INSERT INTO `address_county` VALUES ('1136', '129', '兴国县', 'xing_guo_county');
INSERT INTO `address_county` VALUES ('1137', '129', '会昌县', 'hui_chang_county');
INSERT INTO `address_county` VALUES ('1138', '129', '寻乌县', 'xun_wu_county');
INSERT INTO `address_county` VALUES ('1139', '129', '石城县', 'shi_cheng_county');
INSERT INTO `address_county` VALUES ('1140', '129', '瑞金市', 'rui_jin_city');
INSERT INTO `address_county` VALUES ('1141', '129', '南康市', 'nan_kang_city');
INSERT INTO `address_county` VALUES ('1142', '130', '吉州区', 'ji_zhou_district');
INSERT INTO `address_county` VALUES ('1143', '130', '青原区', 'qing_yuan_district');
INSERT INTO `address_county` VALUES ('1144', '130', '吉安县', 'ji_an_county');
INSERT INTO `address_county` VALUES ('1145', '130', '吉水县', 'ji_shui_county');
INSERT INTO `address_county` VALUES ('1146', '130', '峡江县', 'xia_jiang_county');
INSERT INTO `address_county` VALUES ('1147', '130', '新干县', 'xin_gan_county');
INSERT INTO `address_county` VALUES ('1148', '130', '永丰县', 'yong_feng_county');
INSERT INTO `address_county` VALUES ('1149', '130', '泰和县', 'tai_he_county');
INSERT INTO `address_county` VALUES ('1150', '130', '遂川县', 'sui_chuan_county');
INSERT INTO `address_county` VALUES ('1151', '130', '万安县', 'wan_an_county');
INSERT INTO `address_county` VALUES ('1152', '130', '安福县', 'an_fu_county');
INSERT INTO `address_county` VALUES ('1153', '130', '永新县', 'yong_xin_county');
INSERT INTO `address_county` VALUES ('1154', '130', '井冈山市', 'jing_gang_shan_city');
INSERT INTO `address_county` VALUES ('1155', '131', '袁州区', 'yuan_zhou_district');
INSERT INTO `address_county` VALUES ('1156', '131', '奉新县', 'feng_xin_county');
INSERT INTO `address_county` VALUES ('1157', '131', '万载县', 'wan_zai_county');
INSERT INTO `address_county` VALUES ('1158', '131', '上高县', 'shang_gao_county');
INSERT INTO `address_county` VALUES ('1159', '131', '宜丰县', 'yi_feng_county');
INSERT INTO `address_county` VALUES ('1160', '131', '靖安县', 'jing_an_county');
INSERT INTO `address_county` VALUES ('1161', '131', '铜鼓县', 'tong_gu_county');
INSERT INTO `address_county` VALUES ('1162', '131', '丰城市', 'feng_cheng_city');
INSERT INTO `address_county` VALUES ('1163', '131', '樟树市', 'zhang_shu_city');
INSERT INTO `address_county` VALUES ('1164', '131', '高安市', 'gao_an_city');
INSERT INTO `address_county` VALUES ('1165', '132', '临川区', 'lin_chuan_district');
INSERT INTO `address_county` VALUES ('1166', '132', '南城县', 'nan_cheng_county');
INSERT INTO `address_county` VALUES ('1167', '132', '黎川县', 'li_chuan_county');
INSERT INTO `address_county` VALUES ('1168', '132', '南丰县', 'nan_feng_county');
INSERT INTO `address_county` VALUES ('1169', '132', '崇仁县', 'chong_ren_county');
INSERT INTO `address_county` VALUES ('1170', '132', '乐安县', 'le_an_county');
INSERT INTO `address_county` VALUES ('1171', '132', '宜黄县', 'yi_huang_county');
INSERT INTO `address_county` VALUES ('1172', '132', '金溪县', 'jin_xi_county');
INSERT INTO `address_county` VALUES ('1173', '132', '资溪县', 'zi_xi_county');
INSERT INTO `address_county` VALUES ('1174', '132', '东乡县', 'dong_xiang_county');
INSERT INTO `address_county` VALUES ('1175', '132', '广昌县', 'guang_chang_county');
INSERT INTO `address_county` VALUES ('1176', '133', '信州区', 'xin_zhou_district');
INSERT INTO `address_county` VALUES ('1177', '133', '上饶县', 'shang_rao_county');
INSERT INTO `address_county` VALUES ('1178', '133', '广丰县', 'guang_feng_county');
INSERT INTO `address_county` VALUES ('1179', '133', '玉山县', 'yu_shan_county');
INSERT INTO `address_county` VALUES ('1180', '133', '铅山县', 'qian_shan_county');
INSERT INTO `address_county` VALUES ('1181', '133', '横峰县', 'heng_feng_county');
INSERT INTO `address_county` VALUES ('1182', '133', '弋阳县', 'zuo_yang_county');
INSERT INTO `address_county` VALUES ('1183', '133', '余干县', 'yu_gan_county');
INSERT INTO `address_county` VALUES ('1184', '133', '鄱阳县', 'zuo_yang_county');
INSERT INTO `address_county` VALUES ('1185', '133', '万年县', 'wan_nian_county');
INSERT INTO `address_county` VALUES ('1186', '133', '婺源县', 'zuo_yuan_county');
INSERT INTO `address_county` VALUES ('1187', '133', '德兴市', 'de_xing_city');
INSERT INTO `address_county` VALUES ('1188', '134', '历下区', 'li_xia_district');
INSERT INTO `address_county` VALUES ('1189', '134', '市中区', 'shi_zhong_district');
INSERT INTO `address_county` VALUES ('1190', '134', '槐荫区', 'huai_yin_district');
INSERT INTO `address_county` VALUES ('1191', '134', '天桥区', 'tian_qiao_district');
INSERT INTO `address_county` VALUES ('1192', '134', '历城区', 'li_cheng_district');
INSERT INTO `address_county` VALUES ('1193', '134', '长清区', 'chang_qing_district');
INSERT INTO `address_county` VALUES ('1194', '134', '平阴县', 'ping_yin_county');
INSERT INTO `address_county` VALUES ('1195', '134', '济阳县', 'ji_yang_county');
INSERT INTO `address_county` VALUES ('1196', '134', '商河县', 'shang_he_county');
INSERT INTO `address_county` VALUES ('1197', '134', '章丘市', 'zhang_qiu_city');
INSERT INTO `address_county` VALUES ('1198', '135', '市南区', 'shi_nan_district');
INSERT INTO `address_county` VALUES ('1199', '135', '市北区', 'shi_bei_district');
INSERT INTO `address_county` VALUES ('1200', '135', '四方区', 'si_fang_district');
INSERT INTO `address_county` VALUES ('1201', '135', '黄岛区', 'huang_dao_district');
INSERT INTO `address_county` VALUES ('1202', '135', '崂山区', 'zuo_shan_district');
INSERT INTO `address_county` VALUES ('1203', '135', '李沧区', 'li_cang_district');
INSERT INTO `address_county` VALUES ('1204', '135', '城阳区', 'cheng_yang_district');
INSERT INTO `address_county` VALUES ('1205', '135', '胶州市', 'jiao_zhou_city');
INSERT INTO `address_county` VALUES ('1206', '135', '即墨市', 'ji_mo_city');
INSERT INTO `address_county` VALUES ('1207', '135', '平度市', 'ping_du_city');
INSERT INTO `address_county` VALUES ('1208', '135', '胶南市', 'jiao_nan_city');
INSERT INTO `address_county` VALUES ('1209', '135', '莱西市', 'lai_xi_city');
INSERT INTO `address_county` VALUES ('1210', '136', '淄川区', 'zi_chuan_district');
INSERT INTO `address_county` VALUES ('1211', '136', '张店区', 'zhang_dian_district');
INSERT INTO `address_county` VALUES ('1212', '136', '博山区', 'bo_shan_district');
INSERT INTO `address_county` VALUES ('1213', '136', '临淄区', 'lin_zi_district');
INSERT INTO `address_county` VALUES ('1214', '136', '周村区', 'zhou_cun_district');
INSERT INTO `address_county` VALUES ('1215', '136', '桓台县', 'huan_tai_county');
INSERT INTO `address_county` VALUES ('1216', '136', '高青县', 'gao_qing_county');
INSERT INTO `address_county` VALUES ('1217', '136', '沂源县', 'yi_yuan_county');
INSERT INTO `address_county` VALUES ('1218', '137', '薛城区', 'xue_cheng_district');
INSERT INTO `address_county` VALUES ('1219', '137', '峄城区', 'zuo_cheng_district');
INSERT INTO `address_county` VALUES ('1220', '137', '台儿庄区', 'tai_er_zhuang_district');
INSERT INTO `address_county` VALUES ('1221', '137', '山亭区', 'shan_ting_district');
INSERT INTO `address_county` VALUES ('1222', '137', '滕州市', 'zuo_zhou_city');
INSERT INTO `address_county` VALUES ('1223', '138', '东营区', 'dong_ying_district');
INSERT INTO `address_county` VALUES ('1224', '138', '河口区', 'he_kou_district');
INSERT INTO `address_county` VALUES ('1225', '138', '垦利县', 'ken_li_county');
INSERT INTO `address_county` VALUES ('1226', '138', '利津县', 'li_jin_county');
INSERT INTO `address_county` VALUES ('1227', '138', '广饶县', 'guang_rao_county');
INSERT INTO `address_county` VALUES ('1228', '139', '芝罘区', 'zhi_zuo_district');
INSERT INTO `address_county` VALUES ('1229', '139', '福山区', 'fu_shan_district');
INSERT INTO `address_county` VALUES ('1230', '139', '牟平区', 'mou_ping_district');
INSERT INTO `address_county` VALUES ('1231', '139', '莱山区', 'lai_shan_district');
INSERT INTO `address_county` VALUES ('1232', '139', '长岛县', 'chang_dao_county');
INSERT INTO `address_county` VALUES ('1233', '139', '龙口市', 'long_kou_city');
INSERT INTO `address_county` VALUES ('1234', '139', '莱阳市', 'lai_yang_city');
INSERT INTO `address_county` VALUES ('1235', '139', '莱州市', 'lai_zhou_city');
INSERT INTO `address_county` VALUES ('1236', '139', '蓬莱市', 'peng_lai_city');
INSERT INTO `address_county` VALUES ('1237', '139', '招远市', 'zhao_yuan_city');
INSERT INTO `address_county` VALUES ('1238', '139', '栖霞市', 'qi_xia_city');
INSERT INTO `address_county` VALUES ('1239', '139', '海阳市', 'hai_yang_city');
INSERT INTO `address_county` VALUES ('1240', '140', '潍城区', 'wei_cheng_district');
INSERT INTO `address_county` VALUES ('1241', '140', '寒亭区', 'han_ting_district');
INSERT INTO `address_county` VALUES ('1242', '140', '坊子区', 'fang_zi_district');
INSERT INTO `address_county` VALUES ('1243', '140', '奎文区', 'kui_wen_district');
INSERT INTO `address_county` VALUES ('1244', '140', '临朐县', 'lin_zuo_county');
INSERT INTO `address_county` VALUES ('1245', '140', '昌乐县', 'chang_le_county');
INSERT INTO `address_county` VALUES ('1246', '140', '青州市', 'qing_zhou_city');
INSERT INTO `address_county` VALUES ('1247', '140', '诸城市', 'zhu_cheng_city');
INSERT INTO `address_county` VALUES ('1248', '140', '寿光市', 'shou_guang_city');
INSERT INTO `address_county` VALUES ('1249', '140', '安丘市', 'an_qiu_city');
INSERT INTO `address_county` VALUES ('1250', '140', '高密市', 'gao_mi_city');
INSERT INTO `address_county` VALUES ('1251', '140', '昌邑市', 'chang_yi_city');
INSERT INTO `address_county` VALUES ('1252', '141', '任城区', 'ren_cheng_district');
INSERT INTO `address_county` VALUES ('1253', '141', '微山县', 'wei_shan_county');
INSERT INTO `address_county` VALUES ('1254', '141', '鱼台县', 'yu_tai_county');
INSERT INTO `address_county` VALUES ('1255', '141', '金乡县', 'jin_xiang_county');
INSERT INTO `address_county` VALUES ('1256', '141', '嘉祥县', 'jia_xiang_county');
INSERT INTO `address_county` VALUES ('1257', '141', '汶上县', 'zuo_shang_county');
INSERT INTO `address_county` VALUES ('1258', '141', '泗水县', 'zuo_shui_county');
INSERT INTO `address_county` VALUES ('1259', '141', '梁山县', 'liang_shan_county');
INSERT INTO `address_county` VALUES ('1260', '141', '曲阜市', 'qu_fu_city');
INSERT INTO `address_county` VALUES ('1261', '141', '兖州市', 'zuo_zhou_city');
INSERT INTO `address_county` VALUES ('1262', '141', '邹城市', 'zou_cheng_city');
INSERT INTO `address_county` VALUES ('1263', '142', '泰山区', 'tai_shan_district');
INSERT INTO `address_county` VALUES ('1264', '142', '岱岳区', 'zuo_yue_district');
INSERT INTO `address_county` VALUES ('1265', '142', '宁阳县', 'ning_yang_county');
INSERT INTO `address_county` VALUES ('1266', '142', '东平县', 'dong_ping_county');
INSERT INTO `address_county` VALUES ('1267', '142', '新泰市', 'xin_tai_city');
INSERT INTO `address_county` VALUES ('1268', '142', '肥城市', 'fei_cheng_city');
INSERT INTO `address_county` VALUES ('1269', '143', '环翠区', 'huan_cui_district');
INSERT INTO `address_county` VALUES ('1270', '143', '文登市', 'wen_deng_city');
INSERT INTO `address_county` VALUES ('1271', '143', '荣成市', 'rong_cheng_city');
INSERT INTO `address_county` VALUES ('1272', '143', '乳山市', 'ru_shan_city');
INSERT INTO `address_county` VALUES ('1273', '144', '东港区', 'dong_gang_district');
INSERT INTO `address_county` VALUES ('1274', '144', '岚山区', 'zuo_shan_district');
INSERT INTO `address_county` VALUES ('1275', '144', '五莲县', 'wu_lian_county');
INSERT INTO `address_county` VALUES ('1276', '144', '莒县', 'zuo_county');
INSERT INTO `address_county` VALUES ('1277', '145', '莱城区', 'lai_cheng_district');
INSERT INTO `address_county` VALUES ('1278', '145', '钢城区', 'gang_cheng_district');
INSERT INTO `address_county` VALUES ('1279', '146', '兰山区', 'lan_shan_district');
INSERT INTO `address_county` VALUES ('1280', '146', '罗庄区', 'luo_zhuang_district');
INSERT INTO `address_county` VALUES ('1281', '146', '沂南县', 'yi_nan_county');
INSERT INTO `address_county` VALUES ('1282', '146', '郯城县', 'zuo_cheng_county');
INSERT INTO `address_county` VALUES ('1283', '146', '沂水县', 'yi_shui_county');
INSERT INTO `address_county` VALUES ('1284', '146', '苍山县', 'cang_shan_county');
INSERT INTO `address_county` VALUES ('1285', '146', '费县', 'fei_county');
INSERT INTO `address_county` VALUES ('1286', '146', '平邑县', 'ping_yi_county');
INSERT INTO `address_county` VALUES ('1287', '146', '莒南县', 'zuo_nan_county');
INSERT INTO `address_county` VALUES ('1288', '146', '蒙阴县', 'meng_yin_county');
INSERT INTO `address_county` VALUES ('1289', '146', '临沭县', 'lin_zuo_county');
INSERT INTO `address_county` VALUES ('1290', '147', '德城区', 'de_cheng_district');
INSERT INTO `address_county` VALUES ('1291', '147', '陵县', 'ling_county');
INSERT INTO `address_county` VALUES ('1292', '147', '宁津县', 'ning_jin_county');
INSERT INTO `address_county` VALUES ('1293', '147', '庆云县', 'qing_yun_county');
INSERT INTO `address_county` VALUES ('1294', '147', '临邑县', 'lin_yi_county');
INSERT INTO `address_county` VALUES ('1295', '147', '齐河县', 'qi_he_county');
INSERT INTO `address_county` VALUES ('1296', '147', '平原县', 'ping_yuan_county');
INSERT INTO `address_county` VALUES ('1297', '147', '夏津县', 'xia_jin_county');
INSERT INTO `address_county` VALUES ('1298', '147', '武城县', 'wu_cheng_county');
INSERT INTO `address_county` VALUES ('1299', '147', '乐陵市', 'le_ling_city');
INSERT INTO `address_county` VALUES ('1300', '147', '禹城市', 'yu_cheng_city');
INSERT INTO `address_county` VALUES ('1301', '148', '东昌府区', 'dong_chang_fu_district');
INSERT INTO `address_county` VALUES ('1302', '148', '阳谷县', 'yang_gu_county');
INSERT INTO `address_county` VALUES ('1303', '148', '莘县', 'zuo_county');
INSERT INTO `address_county` VALUES ('1304', '148', '茌平县', 'zuo_ping_county');
INSERT INTO `address_county` VALUES ('1305', '148', '东阿县', 'dong_a_county');
INSERT INTO `address_county` VALUES ('1306', '148', '冠县', 'guan_county');
INSERT INTO `address_county` VALUES ('1307', '148', '高唐县', 'gao_tang_county');
INSERT INTO `address_county` VALUES ('1308', '148', '临清市', 'lin_qing_city');
INSERT INTO `address_county` VALUES ('1309', '149', '滨城区', 'bin_cheng_district');
INSERT INTO `address_county` VALUES ('1310', '149', '惠民县', 'hui_min_county');
INSERT INTO `address_county` VALUES ('1311', '149', '阳信县', 'yang_xin_county');
INSERT INTO `address_county` VALUES ('1312', '149', '无棣县', 'wu_zuo_county');
INSERT INTO `address_county` VALUES ('1313', '149', '沾化县', 'zhan_hua_county');
INSERT INTO `address_county` VALUES ('1314', '149', '博兴县', 'bo_xing_county');
INSERT INTO `address_county` VALUES ('1315', '149', '邹平县', 'zou_ping_county');
INSERT INTO `address_county` VALUES ('1316', '150', '牡丹区', 'mu_dan_district');
INSERT INTO `address_county` VALUES ('1317', '150', '曹县', 'cao_county');
INSERT INTO `address_county` VALUES ('1318', '150', '单县', 'dan_county');
INSERT INTO `address_county` VALUES ('1319', '150', '成武县', 'cheng_wu_county');
INSERT INTO `address_county` VALUES ('1320', '150', '巨野县', 'ju_ye_county');
INSERT INTO `address_county` VALUES ('1321', '150', '郓城县', 'zuo_cheng_county');
INSERT INTO `address_county` VALUES ('1322', '150', '鄄城县', 'zuo_cheng_county');
INSERT INTO `address_county` VALUES ('1323', '150', '定陶县', 'ding_tao_county');
INSERT INTO `address_county` VALUES ('1324', '150', '东明县', 'dong_ming_county');
INSERT INTO `address_county` VALUES ('1325', '151', '中原区', 'zhong_yuan_district');
INSERT INTO `address_county` VALUES ('1326', '151', '二七区', 'er_qi_district');
INSERT INTO `address_county` VALUES ('1327', '151', '管城回族区', 'guan_cheng_hui_zu_district');
INSERT INTO `address_county` VALUES ('1328', '151', '金水区', 'jin_shui_district');
INSERT INTO `address_county` VALUES ('1329', '151', '上街区', 'shang_jie_district');
INSERT INTO `address_county` VALUES ('1330', '151', '惠济区', 'hui_ji_district');
INSERT INTO `address_county` VALUES ('1331', '151', '中牟县', 'zhong_mou_county');
INSERT INTO `address_county` VALUES ('1332', '151', '巩义市', 'gong_yi_city');
INSERT INTO `address_county` VALUES ('1333', '151', '荥阳市', 'zuo_yang_city');
INSERT INTO `address_county` VALUES ('1334', '151', '新密市', 'xin_mi_city');
INSERT INTO `address_county` VALUES ('1335', '151', '新郑市', 'xin_zheng_city');
INSERT INTO `address_county` VALUES ('1336', '151', '登封市', 'deng_feng_city');
INSERT INTO `address_county` VALUES ('1337', '152', '龙亭区', 'long_ting_district');
INSERT INTO `address_county` VALUES ('1338', '152', '顺河回族区', 'shun_he_hui_zu_district');
INSERT INTO `address_county` VALUES ('1339', '152', '禹王台区', 'yu_wang_tai_district');
INSERT INTO `address_county` VALUES ('1340', '152', '金明区', 'jin_ming_district');
INSERT INTO `address_county` VALUES ('1341', '152', '杞县', 'zuo_county');
INSERT INTO `address_county` VALUES ('1342', '152', '通许县', 'tong_xu_county');
INSERT INTO `address_county` VALUES ('1343', '152', '尉氏县', 'wei_shi_county');
INSERT INTO `address_county` VALUES ('1344', '152', '开封县', 'kai_feng_county');
INSERT INTO `address_county` VALUES ('1345', '152', '兰考县', 'lan_kao_county');
INSERT INTO `address_county` VALUES ('1346', '153', '老城区', 'lao_cheng_district');
INSERT INTO `address_county` VALUES ('1347', '153', '西工区', 'xi_gong_district');
INSERT INTO `address_county` VALUES ('1348', '153', '瀍河回族区', 'he_hui_zu_district');
INSERT INTO `address_county` VALUES ('1349', '153', '涧西区', 'jian_xi_district');
INSERT INTO `address_county` VALUES ('1350', '153', '吉利区', 'ji_li_district');
INSERT INTO `address_county` VALUES ('1351', '153', '洛龙区', 'luo_long_district');
INSERT INTO `address_county` VALUES ('1352', '153', '孟津县', 'meng_jin_county');
INSERT INTO `address_county` VALUES ('1353', '153', '新安县', 'xin_an_county');
INSERT INTO `address_county` VALUES ('1354', '153', '栾川县', 'zuo_chuan_county');
INSERT INTO `address_county` VALUES ('1355', '153', '嵩县', 'zuo_county');
INSERT INTO `address_county` VALUES ('1356', '153', '汝阳县', 'ru_yang_county');
INSERT INTO `address_county` VALUES ('1357', '153', '宜阳县', 'yi_yang_county');
INSERT INTO `address_county` VALUES ('1358', '153', '洛宁县', 'luo_ning_county');
INSERT INTO `address_county` VALUES ('1359', '153', '伊川县', 'yi_chuan_county');
INSERT INTO `address_county` VALUES ('1360', '153', '偃师市', 'zuo_shi_city');
INSERT INTO `address_county` VALUES ('1361', '154', '卫东区', 'wei_dong_district');
INSERT INTO `address_county` VALUES ('1362', '154', '石龙区', 'shi_long_district');
INSERT INTO `address_county` VALUES ('1363', '154', '湛河区', 'zhan_he_district');
INSERT INTO `address_county` VALUES ('1364', '154', '宝丰县', 'bao_feng_county');
INSERT INTO `address_county` VALUES ('1365', '154', '叶县', 'ye_county');
INSERT INTO `address_county` VALUES ('1366', '154', '鲁山县', 'lu_shan_county');
INSERT INTO `address_county` VALUES ('1367', '154', '郏县', 'zuo_county');
INSERT INTO `address_county` VALUES ('1368', '154', '舞钢市', 'wu_gang_city');
INSERT INTO `address_county` VALUES ('1369', '154', '汝州市', 'ru_zhou_city');
INSERT INTO `address_county` VALUES ('1370', '155', '文峰区', 'wen_feng_district');
INSERT INTO `address_county` VALUES ('1371', '155', '北关区', 'bei_guan_district');
INSERT INTO `address_county` VALUES ('1372', '155', '殷都区', 'yin_du_district');
INSERT INTO `address_county` VALUES ('1373', '155', '龙安区', 'long_an_district');
INSERT INTO `address_county` VALUES ('1374', '155', '安阳县', 'an_yang_county');
INSERT INTO `address_county` VALUES ('1375', '155', '汤阴县', 'tang_yin_county');
INSERT INTO `address_county` VALUES ('1376', '155', '滑县', 'hua_county');
INSERT INTO `address_county` VALUES ('1377', '155', '内黄县', 'nei_huang_county');
INSERT INTO `address_county` VALUES ('1378', '155', '林州市', 'lin_zhou_city');
INSERT INTO `address_county` VALUES ('1379', '156', '鹤山区', 'he_shan_district');
INSERT INTO `address_county` VALUES ('1380', '156', '山城区', 'shan_cheng_district');
INSERT INTO `address_county` VALUES ('1381', '156', '淇滨区', 'zuo_bin_district');
INSERT INTO `address_county` VALUES ('1382', '156', '浚县', 'jun_county');
INSERT INTO `address_county` VALUES ('1383', '156', '淇县', 'zuo_county');
INSERT INTO `address_county` VALUES ('1384', '157', '红旗区', 'hong_qi_district');
INSERT INTO `address_county` VALUES ('1385', '157', '卫滨区', 'wei_bin_district');
INSERT INTO `address_county` VALUES ('1386', '157', '凤泉区', 'feng_quan_district');
INSERT INTO `address_county` VALUES ('1387', '157', '牧野区', 'mu_ye_district');
INSERT INTO `address_county` VALUES ('1388', '157', '新乡县', 'xin_xiang_county');
INSERT INTO `address_county` VALUES ('1389', '157', '获嘉县', 'huo_jia_county');
INSERT INTO `address_county` VALUES ('1390', '157', '原阳县', 'yuan_yang_county');
INSERT INTO `address_county` VALUES ('1391', '157', '延津县', 'yan_jin_county');
INSERT INTO `address_county` VALUES ('1392', '157', '封丘县', 'feng_qiu_county');
INSERT INTO `address_county` VALUES ('1393', '157', '长垣县', 'chang_yuan_county');
INSERT INTO `address_county` VALUES ('1394', '157', '卫辉市', 'wei_hui_city');
INSERT INTO `address_county` VALUES ('1395', '157', '辉县市', 'hui_xian_city');
INSERT INTO `address_county` VALUES ('1396', '158', '解放区', 'jie_fang_district');
INSERT INTO `address_county` VALUES ('1397', '158', '中站区', 'zhong_zhan_district');
INSERT INTO `address_county` VALUES ('1398', '158', '马村区', 'ma_cun_district');
INSERT INTO `address_county` VALUES ('1399', '158', '山阳区', 'shan_yang_district');
INSERT INTO `address_county` VALUES ('1400', '158', '修武县', 'xiu_wu_county');
INSERT INTO `address_county` VALUES ('1401', '158', '博爱县', 'bo_ai_county');
INSERT INTO `address_county` VALUES ('1402', '158', '武陟县', 'wu_zuo_county');
INSERT INTO `address_county` VALUES ('1403', '158', '温县', 'wen_county');
INSERT INTO `address_county` VALUES ('1404', '158', '沁阳市', 'qin_yang_city');
INSERT INTO `address_county` VALUES ('1405', '158', '孟州市', 'meng_zhou_city');
INSERT INTO `address_county` VALUES ('1406', '159', '华龙区', 'hua_long_district');
INSERT INTO `address_county` VALUES ('1407', '159', '清丰县', 'qing_feng_county');
INSERT INTO `address_county` VALUES ('1408', '159', '南乐县', 'nan_le_county');
INSERT INTO `address_county` VALUES ('1409', '159', '范县', 'fan_county');
INSERT INTO `address_county` VALUES ('1410', '159', '台前县', 'tai_qian_county');
INSERT INTO `address_county` VALUES ('1411', '159', '濮阳县', 'zuo_yang_county');
INSERT INTO `address_county` VALUES ('1412', '160', '魏都区', 'wei_du_district');
INSERT INTO `address_county` VALUES ('1413', '160', '许昌县', 'xu_chang_county');
INSERT INTO `address_county` VALUES ('1414', '160', '鄢陵县', 'zuo_ling_county');
INSERT INTO `address_county` VALUES ('1415', '160', '襄城县', 'xiang_cheng_county');
INSERT INTO `address_county` VALUES ('1416', '160', '禹州市', 'yu_zhou_city');
INSERT INTO `address_county` VALUES ('1417', '160', '长葛市', 'chang_ge_city');
INSERT INTO `address_county` VALUES ('1418', '161', '源汇区', 'yuan_hui_district');
INSERT INTO `address_county` VALUES ('1419', '161', '郾城区', 'zuo_cheng_district');
INSERT INTO `address_county` VALUES ('1420', '161', '召陵区', 'zhao_ling_district');
INSERT INTO `address_county` VALUES ('1421', '161', '舞阳县', 'wu_yang_county');
INSERT INTO `address_county` VALUES ('1422', '161', '临颍县', 'lin_zuo_county');
INSERT INTO `address_county` VALUES ('1423', '162', '湖滨区', 'hu_bin_district');
INSERT INTO `address_county` VALUES ('1424', '162', '渑池县', 'zuo_chi_county');
INSERT INTO `address_county` VALUES ('1425', '162', '陕县', 'shan_county');
INSERT INTO `address_county` VALUES ('1426', '162', '卢氏县', 'lu_shi_county');
INSERT INTO `address_county` VALUES ('1427', '162', '义马市', 'yi_ma_city');
INSERT INTO `address_county` VALUES ('1428', '162', '灵宝市', 'ling_bao_city');
INSERT INTO `address_county` VALUES ('1429', '163', '宛城区', 'wan_cheng_district');
INSERT INTO `address_county` VALUES ('1430', '163', '卧龙区', 'wo_long_district');
INSERT INTO `address_county` VALUES ('1431', '163', '南召县', 'nan_zhao_county');
INSERT INTO `address_county` VALUES ('1432', '163', '方城县', 'fang_cheng_county');
INSERT INTO `address_county` VALUES ('1433', '163', '西峡县', 'xi_xia_county');
INSERT INTO `address_county` VALUES ('1434', '163', '镇平县', 'zhen_ping_county');
INSERT INTO `address_county` VALUES ('1435', '163', '内乡县', 'nei_xiang_county');
INSERT INTO `address_county` VALUES ('1436', '163', '淅川县', 'zuo_chuan_county');
INSERT INTO `address_county` VALUES ('1437', '163', '社旗县', 'she_qi_county');
INSERT INTO `address_county` VALUES ('1438', '163', '唐河县', 'tang_he_county');
INSERT INTO `address_county` VALUES ('1439', '163', '新野县', 'xin_ye_county');
INSERT INTO `address_county` VALUES ('1440', '163', '桐柏县', 'tong_bai_county');
INSERT INTO `address_county` VALUES ('1441', '163', '邓州市', 'deng_zhou_city');
INSERT INTO `address_county` VALUES ('1442', '164', '梁园区', 'liang_yuan_district');
INSERT INTO `address_county` VALUES ('1443', '164', '睢阳区', 'zuo_yang_district');
INSERT INTO `address_county` VALUES ('1444', '164', '民权县', 'min_quan_county');
INSERT INTO `address_county` VALUES ('1445', '164', '睢县', 'zuo_county');
INSERT INTO `address_county` VALUES ('1446', '164', '宁陵县', 'ning_ling_county');
INSERT INTO `address_county` VALUES ('1447', '164', '柘城县', 'zuo_cheng_county');
INSERT INTO `address_county` VALUES ('1448', '164', '虞城县', 'yu_cheng_county');
INSERT INTO `address_county` VALUES ('1449', '164', '夏邑县', 'xia_yi_county');
INSERT INTO `address_county` VALUES ('1450', '164', '永城市', 'yong_cheng_city');
INSERT INTO `address_county` VALUES ('1451', '165', '浉河区', 'he_district');
INSERT INTO `address_county` VALUES ('1452', '165', '平桥区', 'ping_qiao_district');
INSERT INTO `address_county` VALUES ('1453', '165', '罗山县', 'luo_shan_county');
INSERT INTO `address_county` VALUES ('1454', '165', '光山县', 'guang_shan_county');
INSERT INTO `address_county` VALUES ('1455', '165', '新县', 'xin_county');
INSERT INTO `address_county` VALUES ('1456', '165', '商城县', 'shang_cheng_county');
INSERT INTO `address_county` VALUES ('1457', '165', '固始县', 'gu_shi_county');
INSERT INTO `address_county` VALUES ('1458', '165', '潢川县', 'zuo_chuan_county');
INSERT INTO `address_county` VALUES ('1459', '165', '淮滨县', 'huai_bin_county');
INSERT INTO `address_county` VALUES ('1460', '165', '息县', 'xi_county');
INSERT INTO `address_county` VALUES ('1461', '166', '川汇区', 'chuan_hui_district');
INSERT INTO `address_county` VALUES ('1462', '166', '扶沟县', 'fu_gou_county');
INSERT INTO `address_county` VALUES ('1463', '166', '西华县', 'xi_hua_county');
INSERT INTO `address_county` VALUES ('1464', '166', '商水县', 'shang_shui_county');
INSERT INTO `address_county` VALUES ('1465', '166', '沈丘县', 'shen_qiu_county');
INSERT INTO `address_county` VALUES ('1466', '166', '郸城县', 'dan_cheng_county');
INSERT INTO `address_county` VALUES ('1467', '166', '淮阳县', 'huai_yang_county');
INSERT INTO `address_county` VALUES ('1468', '166', '太康县', 'tai_kang_county');
INSERT INTO `address_county` VALUES ('1469', '166', '鹿邑县', 'lu_yi_county');
INSERT INTO `address_county` VALUES ('1470', '166', '项城市', 'xiang_cheng_city');
INSERT INTO `address_county` VALUES ('1471', '167', '驿城区', 'zuo_cheng_district');
INSERT INTO `address_county` VALUES ('1472', '167', '西平县', 'xi_ping_county');
INSERT INTO `address_county` VALUES ('1473', '167', '上蔡县', 'shang_cai_county');
INSERT INTO `address_county` VALUES ('1474', '167', '平舆县', 'ping_yu_county');
INSERT INTO `address_county` VALUES ('1475', '167', '正阳县', 'zheng_yang_county');
INSERT INTO `address_county` VALUES ('1476', '167', '确山县', 'que_shan_county');
INSERT INTO `address_county` VALUES ('1477', '167', '泌阳县', 'mi_yang_county');
INSERT INTO `address_county` VALUES ('1478', '167', '汝南县', 'ru_nan_county');
INSERT INTO `address_county` VALUES ('1479', '167', '遂平县', 'sui_ping_county');
INSERT INTO `address_county` VALUES ('1480', '167', '新蔡县', 'xin_cai_county');
INSERT INTO `address_county` VALUES ('1481', '167', '济源市', 'ji_yuan_city');
INSERT INTO `address_county` VALUES ('1482', '168', '江岸区', 'jiang_an_district');
INSERT INTO `address_county` VALUES ('1483', '168', '江汉区', 'jiang_han_district');
INSERT INTO `address_county` VALUES ('1484', '168', '硚口区', 'chang_kou_district');
INSERT INTO `address_county` VALUES ('1485', '168', '汉阳区', 'han_yang_district');
INSERT INTO `address_county` VALUES ('1486', '168', '武昌区', 'wu_chang_district');
INSERT INTO `address_county` VALUES ('1487', '168', '洪山区', 'hong_shan_district');
INSERT INTO `address_county` VALUES ('1488', '168', '东西湖区', 'dong_xi_hu_district');
INSERT INTO `address_county` VALUES ('1489', '168', '汉南区', 'han_nan_district');
INSERT INTO `address_county` VALUES ('1490', '168', '蔡甸区', 'cai_dian_district');
INSERT INTO `address_county` VALUES ('1491', '168', '江夏区', 'jiang_xia_district');
INSERT INTO `address_county` VALUES ('1492', '168', '黄陂区', 'huang_zuo_district');
INSERT INTO `address_county` VALUES ('1493', '168', '新洲区', 'xin_zhou_district');
INSERT INTO `address_county` VALUES ('1494', '169', '黄石港区', 'huang_shi_gang_district');
INSERT INTO `address_county` VALUES ('1495', '169', '西塞山区', 'xi_sai_shan_district');
INSERT INTO `address_county` VALUES ('1496', '169', '下陆区', 'xia_lu_district');
INSERT INTO `address_county` VALUES ('1497', '169', '铁山区', 'tie_shan_district');
INSERT INTO `address_county` VALUES ('1498', '169', '阳新县', 'yang_xin_county');
INSERT INTO `address_county` VALUES ('1499', '169', '大冶市', 'da_ye_city');
INSERT INTO `address_county` VALUES ('1500', '170', '茅箭区', 'mao_jian_district');
INSERT INTO `address_county` VALUES ('1501', '170', '张湾区', 'zhang_wan_district');
INSERT INTO `address_county` VALUES ('1502', '170', '郧县', 'yun_county');
INSERT INTO `address_county` VALUES ('1503', '170', '郧西县', 'yun_xi_county');
INSERT INTO `address_county` VALUES ('1504', '170', '竹山县', 'zhu_shan_county');
INSERT INTO `address_county` VALUES ('1505', '170', '竹溪县', 'zhu_xi_county');
INSERT INTO `address_county` VALUES ('1506', '170', '房县', 'fang_county');
INSERT INTO `address_county` VALUES ('1507', '170', '丹江口市', 'dan_jiang_kou_city');
INSERT INTO `address_county` VALUES ('1508', '171', '西陵区', 'xi_ling_district');
INSERT INTO `address_county` VALUES ('1509', '171', '伍家岗区', 'wu_jia_gang_district');
INSERT INTO `address_county` VALUES ('1510', '171', '点军区', 'dian_jun_district');
INSERT INTO `address_county` VALUES ('1511', '171', '猇亭区', 'ting_district');
INSERT INTO `address_county` VALUES ('1512', '171', '夷陵区', 'yi_ling_district');
INSERT INTO `address_county` VALUES ('1513', '171', '远安县', 'yuan_an_county');
INSERT INTO `address_county` VALUES ('1514', '171', '兴山县', 'xing_shan_county');
INSERT INTO `address_county` VALUES ('1515', '171', '秭归县', 'zuo_gui_county');
INSERT INTO `address_county` VALUES ('1516', '171', '长阳土家族自治县', 'chang_yang_tu_jia_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1517', '171', '五峰土家族自治县', 'wu_feng_tu_jia_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1518', '171', '宜都市', 'yi_du_city');
INSERT INTO `address_county` VALUES ('1519', '171', '当阳市', 'dang_yang_city');
INSERT INTO `address_county` VALUES ('1520', '171', '枝江市', 'zhi_jiang_city');
INSERT INTO `address_county` VALUES ('1521', '172', '襄城区', 'xiang_cheng_district');
INSERT INTO `address_county` VALUES ('1522', '172', '樊城区', 'fan_cheng_district');
INSERT INTO `address_county` VALUES ('1523', '172', '襄州区', 'xiang_zhou_district');
INSERT INTO `address_county` VALUES ('1524', '172', '南漳县', 'nan_zhang_county');
INSERT INTO `address_county` VALUES ('1525', '172', '谷城县', 'gu_cheng_county');
INSERT INTO `address_county` VALUES ('1526', '172', '保康县', 'bao_kang_county');
INSERT INTO `address_county` VALUES ('1527', '172', '老河口市', 'lao_he_kou_city');
INSERT INTO `address_county` VALUES ('1528', '172', '枣阳市', 'zao_yang_city');
INSERT INTO `address_county` VALUES ('1529', '172', '宜城市', 'yi_cheng_city');
INSERT INTO `address_county` VALUES ('1530', '173', '梁子湖区', 'liang_zi_hu_district');
INSERT INTO `address_county` VALUES ('1531', '173', '华容区', 'hua_rong_district');
INSERT INTO `address_county` VALUES ('1532', '173', '鄂城区', 'e_cheng_district');
INSERT INTO `address_county` VALUES ('1533', '174', '东宝区', 'dong_bao_district');
INSERT INTO `address_county` VALUES ('1534', '174', '掇刀区', 'duo_dao_district');
INSERT INTO `address_county` VALUES ('1535', '174', '京山县', 'jing_shan_county');
INSERT INTO `address_county` VALUES ('1536', '174', '沙洋县', 'sha_yang_county');
INSERT INTO `address_county` VALUES ('1537', '174', '钟祥市', 'zhong_xiang_city');
INSERT INTO `address_county` VALUES ('1538', '175', '孝南区', 'xiao_nan_district');
INSERT INTO `address_county` VALUES ('1539', '175', '孝昌县', 'xiao_chang_county');
INSERT INTO `address_county` VALUES ('1540', '175', '大悟县', 'da_wu_county');
INSERT INTO `address_county` VALUES ('1541', '175', '云梦县', 'yun_meng_county');
INSERT INTO `address_county` VALUES ('1542', '175', '应城市', 'ying_cheng_city');
INSERT INTO `address_county` VALUES ('1543', '175', '安陆市', 'an_lu_city');
INSERT INTO `address_county` VALUES ('1544', '175', '汉川市', 'han_chuan_city');
INSERT INTO `address_county` VALUES ('1545', '176', '沙市区', 'sha_shi_district');
INSERT INTO `address_county` VALUES ('1546', '176', '荆州区', 'jing_zhou_district');
INSERT INTO `address_county` VALUES ('1547', '176', '公安县', 'gong_an_county');
INSERT INTO `address_county` VALUES ('1548', '176', '监利县', 'jian_li_county');
INSERT INTO `address_county` VALUES ('1549', '176', '江陵县', 'jiang_ling_county');
INSERT INTO `address_county` VALUES ('1550', '176', '石首市', 'shi_shou_city');
INSERT INTO `address_county` VALUES ('1551', '176', '洪湖市', 'hong_hu_city');
INSERT INTO `address_county` VALUES ('1552', '176', '松滋市', 'song_zi_city');
INSERT INTO `address_county` VALUES ('1553', '177', '黄州区', 'huang_zhou_district');
INSERT INTO `address_county` VALUES ('1554', '177', '团风县', 'tuan_feng_county');
INSERT INTO `address_county` VALUES ('1555', '177', '红安县', 'hong_an_county');
INSERT INTO `address_county` VALUES ('1556', '177', '罗田县', 'luo_tian_county');
INSERT INTO `address_county` VALUES ('1557', '177', '英山县', 'ying_shan_county');
INSERT INTO `address_county` VALUES ('1558', '177', '浠水县', 'zuo_shui_county');
INSERT INTO `address_county` VALUES ('1559', '177', '蕲春县', 'zuo_chun_county');
INSERT INTO `address_county` VALUES ('1560', '177', '黄梅县', 'huang_mei_county');
INSERT INTO `address_county` VALUES ('1561', '177', '麻城市', 'ma_cheng_city');
INSERT INTO `address_county` VALUES ('1562', '177', '武穴市', 'wu_xue_city');
INSERT INTO `address_county` VALUES ('1563', '178', '咸安区', 'xian_an_district');
INSERT INTO `address_county` VALUES ('1564', '178', '嘉鱼县', 'jia_yu_county');
INSERT INTO `address_county` VALUES ('1565', '178', '通城县', 'tong_cheng_county');
INSERT INTO `address_county` VALUES ('1566', '178', '崇阳县', 'chong_yang_county');
INSERT INTO `address_county` VALUES ('1567', '178', '通山县', 'tong_shan_county');
INSERT INTO `address_county` VALUES ('1568', '178', '赤壁市', 'chi_bi_city');
INSERT INTO `address_county` VALUES ('1569', '179', '曾都区', 'zeng_du_district');
INSERT INTO `address_county` VALUES ('1570', '179', '随县', 'sui_county');
INSERT INTO `address_county` VALUES ('1571', '179', '广水市', 'guang_shui_city');
INSERT INTO `address_county` VALUES ('1572', '180', '恩施市', 'en_shi_city');
INSERT INTO `address_county` VALUES ('1573', '180', '利川市', 'li_chuan_city');
INSERT INTO `address_county` VALUES ('1574', '180', '建始县', 'jian_shi_county');
INSERT INTO `address_county` VALUES ('1575', '180', '巴东县', 'ba_dong_county');
INSERT INTO `address_county` VALUES ('1576', '180', '宣恩县', 'xuan_en_county');
INSERT INTO `address_county` VALUES ('1577', '180', '咸丰县', 'xian_feng_county');
INSERT INTO `address_county` VALUES ('1578', '180', '来凤县', 'lai_feng_county');
INSERT INTO `address_county` VALUES ('1579', '180', '鹤峰县', 'he_feng_county');
INSERT INTO `address_county` VALUES ('1580', '180', '仙桃市', 'xian_tao_city');
INSERT INTO `address_county` VALUES ('1581', '180', '潜江市', 'qian_jiang_city');
INSERT INTO `address_county` VALUES ('1582', '180', '天门市', 'tian_men_city');
INSERT INTO `address_county` VALUES ('1583', '180', '神农架林区', 'shen_nong_jia_lin_district');
INSERT INTO `address_county` VALUES ('1584', '181', '芙蓉区', 'zuo_rong_district');
INSERT INTO `address_county` VALUES ('1585', '181', '天心区', 'tian_xin_district');
INSERT INTO `address_county` VALUES ('1586', '181', '岳麓区', 'yue_lu_district');
INSERT INTO `address_county` VALUES ('1587', '181', '开福区', 'kai_fu_district');
INSERT INTO `address_county` VALUES ('1588', '181', '雨花区', 'yu_hua_district');
INSERT INTO `address_county` VALUES ('1589', '181', '望城区', 'wang_cheng_district');
INSERT INTO `address_county` VALUES ('1590', '181', '长沙县', 'chang_sha_county');
INSERT INTO `address_county` VALUES ('1591', '181', '宁乡县', 'ning_xiang_county');
INSERT INTO `address_county` VALUES ('1592', '181', '浏阳市', 'zuo_yang_city');
INSERT INTO `address_county` VALUES ('1593', '182', '荷塘区', 'he_tang_district');
INSERT INTO `address_county` VALUES ('1594', '182', '芦淞区', 'lu_zuo_district');
INSERT INTO `address_county` VALUES ('1595', '182', '石峰区', 'shi_feng_district');
INSERT INTO `address_county` VALUES ('1596', '182', '天元区', 'tian_yuan_district');
INSERT INTO `address_county` VALUES ('1597', '182', '株洲县', 'zhu_zhou_county');
INSERT INTO `address_county` VALUES ('1598', '182', '攸县', 'zuo_county');
INSERT INTO `address_county` VALUES ('1599', '182', '茶陵县', 'cha_ling_county');
INSERT INTO `address_county` VALUES ('1600', '182', '炎陵县', 'yan_ling_county');
INSERT INTO `address_county` VALUES ('1601', '182', '醴陵市', 'zuo_ling_city');
INSERT INTO `address_county` VALUES ('1602', '183', '雨湖区', 'yu_hu_district');
INSERT INTO `address_county` VALUES ('1603', '183', '岳塘区', 'yue_tang_district');
INSERT INTO `address_county` VALUES ('1604', '183', '湘潭县', 'xiang_tan_county');
INSERT INTO `address_county` VALUES ('1605', '183', '湘乡市', 'xiang_xiang_city');
INSERT INTO `address_county` VALUES ('1606', '183', '韶山市', 'shao_shan_city');
INSERT INTO `address_county` VALUES ('1607', '184', '珠晖区', 'zhu_zuo_district');
INSERT INTO `address_county` VALUES ('1608', '184', '雁峰区', 'yan_feng_district');
INSERT INTO `address_county` VALUES ('1609', '184', '石鼓区', 'shi_gu_district');
INSERT INTO `address_county` VALUES ('1610', '184', '蒸湘区', 'zheng_xiang_district');
INSERT INTO `address_county` VALUES ('1611', '184', '南岳区', 'nan_yue_district');
INSERT INTO `address_county` VALUES ('1612', '184', '衡阳县', 'heng_yang_county');
INSERT INTO `address_county` VALUES ('1613', '184', '衡南县', 'heng_nan_county');
INSERT INTO `address_county` VALUES ('1614', '184', '衡山县', 'heng_shan_county');
INSERT INTO `address_county` VALUES ('1615', '184', '衡东县', 'heng_dong_county');
INSERT INTO `address_county` VALUES ('1616', '184', '祁东县', 'qi_dong_county');
INSERT INTO `address_county` VALUES ('1617', '184', '耒阳市', 'zuo_yang_city');
INSERT INTO `address_county` VALUES ('1618', '184', '常宁市', 'chang_ning_city');
INSERT INTO `address_county` VALUES ('1619', '185', '双清区', 'shuang_qing_district');
INSERT INTO `address_county` VALUES ('1620', '185', '大祥区', 'da_xiang_district');
INSERT INTO `address_county` VALUES ('1621', '185', '北塔区', 'bei_ta_district');
INSERT INTO `address_county` VALUES ('1622', '185', '邵东县', 'shao_dong_county');
INSERT INTO `address_county` VALUES ('1623', '185', '新邵县', 'xin_shao_county');
INSERT INTO `address_county` VALUES ('1624', '185', '邵阳县', 'shao_yang_county');
INSERT INTO `address_county` VALUES ('1625', '185', '隆回县', 'long_hui_county');
INSERT INTO `address_county` VALUES ('1626', '185', '洞口县', 'dong_kou_county');
INSERT INTO `address_county` VALUES ('1627', '185', '绥宁县', 'sui_ning_county');
INSERT INTO `address_county` VALUES ('1628', '185', '新宁县', 'xin_ning_county');
INSERT INTO `address_county` VALUES ('1629', '185', '城步苗族自治县', 'cheng_bu_miao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1630', '185', '武冈市', 'wu_gang_city');
INSERT INTO `address_county` VALUES ('1631', '186', '岳阳楼区', 'yue_yang_lou_district');
INSERT INTO `address_county` VALUES ('1632', '186', '云溪区', 'yun_xi_district');
INSERT INTO `address_county` VALUES ('1633', '186', '君山区', 'jun_shan_district');
INSERT INTO `address_county` VALUES ('1634', '186', '岳阳县', 'yue_yang_county');
INSERT INTO `address_county` VALUES ('1635', '186', '华容县', 'hua_rong_county');
INSERT INTO `address_county` VALUES ('1636', '186', '湘阴县', 'xiang_yin_county');
INSERT INTO `address_county` VALUES ('1637', '186', '平江县', 'ping_jiang_county');
INSERT INTO `address_county` VALUES ('1638', '186', '汨罗市', 'zuo_luo_city');
INSERT INTO `address_county` VALUES ('1639', '186', '临湘市', 'lin_xiang_city');
INSERT INTO `address_county` VALUES ('1640', '187', '武陵区', 'wu_ling_district');
INSERT INTO `address_county` VALUES ('1641', '187', '鼎城区', 'ding_cheng_district');
INSERT INTO `address_county` VALUES ('1642', '187', '安乡县', 'an_xiang_county');
INSERT INTO `address_county` VALUES ('1643', '187', '汉寿县', 'han_shou_county');
INSERT INTO `address_county` VALUES ('1644', '187', '澧县', 'zuo_county');
INSERT INTO `address_county` VALUES ('1645', '187', '临澧县', 'lin_zuo_county');
INSERT INTO `address_county` VALUES ('1646', '187', '桃源县', 'tao_yuan_county');
INSERT INTO `address_county` VALUES ('1647', '187', '石门县', 'shi_men_county');
INSERT INTO `address_county` VALUES ('1648', '187', '津市市', 'jin_shi_city');
INSERT INTO `address_county` VALUES ('1649', '188', '永定区', 'yong_ding_district');
INSERT INTO `address_county` VALUES ('1650', '188', '武陵源区', 'wu_ling_yuan_district');
INSERT INTO `address_county` VALUES ('1651', '188', '慈利县', 'ci_li_county');
INSERT INTO `address_county` VALUES ('1652', '188', '桑植县', 'sang_zhi_county');
INSERT INTO `address_county` VALUES ('1653', '189', '资阳区', 'zi_yang_district');
INSERT INTO `address_county` VALUES ('1654', '189', '赫山区', 'he_shan_district');
INSERT INTO `address_county` VALUES ('1655', '189', '南县', 'nan_county');
INSERT INTO `address_county` VALUES ('1656', '189', '桃江县', 'tao_jiang_county');
INSERT INTO `address_county` VALUES ('1657', '189', '安化县', 'an_hua_county');
INSERT INTO `address_county` VALUES ('1658', '189', '沅江市', 'zuo_jiang_city');
INSERT INTO `address_county` VALUES ('1659', '190', '北湖区', 'bei_hu_district');
INSERT INTO `address_county` VALUES ('1660', '190', '苏仙区', 'su_xian_district');
INSERT INTO `address_county` VALUES ('1661', '190', '桂阳县', 'gui_yang_county');
INSERT INTO `address_county` VALUES ('1662', '190', '宜章县', 'yi_zhang_county');
INSERT INTO `address_county` VALUES ('1663', '190', '永兴县', 'yong_xing_county');
INSERT INTO `address_county` VALUES ('1664', '190', '嘉禾县', 'jia_he_county');
INSERT INTO `address_county` VALUES ('1665', '190', '临武县', 'lin_wu_county');
INSERT INTO `address_county` VALUES ('1666', '190', '汝城县', 'ru_cheng_county');
INSERT INTO `address_county` VALUES ('1667', '190', '桂东县', 'gui_dong_county');
INSERT INTO `address_county` VALUES ('1668', '190', '安仁县', 'an_ren_county');
INSERT INTO `address_county` VALUES ('1669', '190', '资兴市', 'zi_xing_city');
INSERT INTO `address_county` VALUES ('1670', '191', '零陵区', 'ling_ling_district');
INSERT INTO `address_county` VALUES ('1671', '191', '冷水滩区', 'leng_shui_tan_district');
INSERT INTO `address_county` VALUES ('1672', '191', '祁阳县', 'qi_yang_county');
INSERT INTO `address_county` VALUES ('1673', '191', '东安县', 'dong_an_county');
INSERT INTO `address_county` VALUES ('1674', '191', '双牌县', 'shuang_pai_county');
INSERT INTO `address_county` VALUES ('1675', '191', '道县', 'dao_county');
INSERT INTO `address_county` VALUES ('1676', '191', '江永县', 'jiang_yong_county');
INSERT INTO `address_county` VALUES ('1677', '191', '宁远县', 'ning_yuan_county');
INSERT INTO `address_county` VALUES ('1678', '191', '蓝山县', 'lan_shan_county');
INSERT INTO `address_county` VALUES ('1679', '191', '新田县', 'xin_tian_county');
INSERT INTO `address_county` VALUES ('1680', '191', '江华瑶族自治县', 'jiang_hua_yao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1681', '192', '鹤城区', 'he_cheng_district');
INSERT INTO `address_county` VALUES ('1682', '192', '中方县', 'zhong_fang_county');
INSERT INTO `address_county` VALUES ('1683', '192', '沅陵县', 'zuo_ling_county');
INSERT INTO `address_county` VALUES ('1684', '192', '辰溪县', 'chen_xi_county');
INSERT INTO `address_county` VALUES ('1685', '192', '溆浦县', 'zuo_pu_county');
INSERT INTO `address_county` VALUES ('1686', '192', '会同县', 'hui_tong_county');
INSERT INTO `address_county` VALUES ('1687', '192', '麻阳苗族自治县', 'ma_yang_miao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1688', '192', '新晃侗族自治县', 'xin_huang_dong_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1689', '192', '芷江侗族自治县', 'zuo_jiang_dong_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1690', '192', '靖州苗族侗族自治县', 'jing_zhou_miao_zu_dong_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1691', '192', '通道侗族自治县', 'tong_dao_dong_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1692', '192', '洪江市', 'hong_jiang_city');
INSERT INTO `address_county` VALUES ('1693', '193', '娄星区', 'lou_xing_district');
INSERT INTO `address_county` VALUES ('1694', '193', '双峰县', 'shuang_feng_county');
INSERT INTO `address_county` VALUES ('1695', '193', '新化县', 'xin_hua_county');
INSERT INTO `address_county` VALUES ('1696', '193', '冷水江市', 'leng_shui_jiang_city');
INSERT INTO `address_county` VALUES ('1697', '193', '涟源市', 'lian_yuan_city');
INSERT INTO `address_county` VALUES ('1698', '194', '吉首市', 'ji_shou_city');
INSERT INTO `address_county` VALUES ('1699', '194', '泸溪县', 'zuo_xi_county');
INSERT INTO `address_county` VALUES ('1700', '194', '凤凰县', 'feng_huang_county');
INSERT INTO `address_county` VALUES ('1701', '194', '花垣县', 'hua_yuan_county');
INSERT INTO `address_county` VALUES ('1702', '194', '保靖县', 'bao_jing_county');
INSERT INTO `address_county` VALUES ('1703', '194', '古丈县', 'gu_zhang_county');
INSERT INTO `address_county` VALUES ('1704', '194', '永顺县', 'yong_shun_county');
INSERT INTO `address_county` VALUES ('1705', '194', '龙山县', 'long_shan_county');
INSERT INTO `address_county` VALUES ('1706', '195', '荔湾区', 'li_wan_district');
INSERT INTO `address_county` VALUES ('1707', '195', '越秀区', 'yue_xiu_district');
INSERT INTO `address_county` VALUES ('1708', '195', '海珠区', 'hai_zhu_district');
INSERT INTO `address_county` VALUES ('1709', '195', '天河区', 'tian_he_district');
INSERT INTO `address_county` VALUES ('1710', '195', '白云区', 'bai_yun_district');
INSERT INTO `address_county` VALUES ('1711', '195', '黄埔区', 'huang_pu_district');
INSERT INTO `address_county` VALUES ('1712', '195', '番禺区', 'fan_zuo_district');
INSERT INTO `address_county` VALUES ('1713', '195', '花都区', 'hua_du_district');
INSERT INTO `address_county` VALUES ('1714', '195', '南沙区', 'nan_sha_district');
INSERT INTO `address_county` VALUES ('1715', '195', '萝岗区', 'luo_gang_district');
INSERT INTO `address_county` VALUES ('1716', '195', '增城市', 'zeng_cheng_city');
INSERT INTO `address_county` VALUES ('1717', '195', '从化市', 'cong_hua_city');
INSERT INTO `address_county` VALUES ('1718', '196', '武江区', 'wu_jiang_district');
INSERT INTO `address_county` VALUES ('1719', '196', '浈江区', 'zuo_jiang_district');
INSERT INTO `address_county` VALUES ('1720', '196', '曲江区', 'qu_jiang_district');
INSERT INTO `address_county` VALUES ('1721', '196', '始兴县', 'shi_xing_county');
INSERT INTO `address_county` VALUES ('1722', '196', '仁化县', 'ren_hua_county');
INSERT INTO `address_county` VALUES ('1723', '196', '翁源县', 'weng_yuan_county');
INSERT INTO `address_county` VALUES ('1724', '196', '乳源瑶族自治县', 'ru_yuan_yao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1725', '196', '新丰县', 'xin_feng_county');
INSERT INTO `address_county` VALUES ('1726', '196', '乐昌市', 'le_chang_city');
INSERT INTO `address_county` VALUES ('1727', '196', '南雄市', 'nan_xiong_city');
INSERT INTO `address_county` VALUES ('1728', '197', '罗湖区', 'luo_hu_district');
INSERT INTO `address_county` VALUES ('1729', '197', '福田区', 'fu_tian_district');
INSERT INTO `address_county` VALUES ('1730', '197', '宝安区', 'bao_an_district');
INSERT INTO `address_county` VALUES ('1731', '197', '龙岗区', 'long_gang_district');
INSERT INTO `address_county` VALUES ('1732', '197', '盐田区', 'yan_tian_district');
INSERT INTO `address_county` VALUES ('1733', '198', '香洲区', 'xiang_zhou_district');
INSERT INTO `address_county` VALUES ('1734', '198', '斗门区', 'dou_men_district');
INSERT INTO `address_county` VALUES ('1735', '198', '金湾区', 'jin_wan_district');
INSERT INTO `address_county` VALUES ('1736', '199', '龙湖区', 'long_hu_district');
INSERT INTO `address_county` VALUES ('1737', '199', '金平区', 'jin_ping_district');
INSERT INTO `address_county` VALUES ('1738', '199', '濠江区', 'zuo_jiang_district');
INSERT INTO `address_county` VALUES ('1739', '199', '潮阳区', 'chao_yang_district');
INSERT INTO `address_county` VALUES ('1740', '199', '潮南区', 'chao_nan_district');
INSERT INTO `address_county` VALUES ('1741', '199', '澄海区', 'cheng_hai_district');
INSERT INTO `address_county` VALUES ('1742', '199', '南澳县', 'nan_ao_county');
INSERT INTO `address_county` VALUES ('1743', '200', '禅城区', 'zuo_cheng_district');
INSERT INTO `address_county` VALUES ('1744', '200', '南海区', 'nan_hai_district');
INSERT INTO `address_county` VALUES ('1745', '200', '顺德区', 'shun_de_district');
INSERT INTO `address_county` VALUES ('1746', '200', '三水区', 'san_shui_district');
INSERT INTO `address_county` VALUES ('1747', '200', '高明区', 'gao_ming_district');
INSERT INTO `address_county` VALUES ('1748', '201', '蓬江区', 'peng_jiang_district');
INSERT INTO `address_county` VALUES ('1749', '201', '江海区', 'jiang_hai_district');
INSERT INTO `address_county` VALUES ('1750', '201', '新会区', 'xin_hui_district');
INSERT INTO `address_county` VALUES ('1751', '201', '台山市', 'tai_shan_city');
INSERT INTO `address_county` VALUES ('1752', '201', '开平市', 'kai_ping_city');
INSERT INTO `address_county` VALUES ('1753', '201', '鹤山市', 'he_shan_city');
INSERT INTO `address_county` VALUES ('1754', '201', '恩平市', 'en_ping_city');
INSERT INTO `address_county` VALUES ('1755', '202', '赤坎区', 'chi_kan_district');
INSERT INTO `address_county` VALUES ('1756', '202', '霞山区', 'xia_shan_district');
INSERT INTO `address_county` VALUES ('1757', '202', '坡头区', 'po_tou_district');
INSERT INTO `address_county` VALUES ('1758', '202', '麻章区', 'ma_zhang_district');
INSERT INTO `address_county` VALUES ('1759', '202', '遂溪县', 'sui_xi_county');
INSERT INTO `address_county` VALUES ('1760', '202', '徐闻县', 'xu_wen_county');
INSERT INTO `address_county` VALUES ('1761', '202', '廉江市', 'lian_jiang_city');
INSERT INTO `address_county` VALUES ('1762', '202', '雷州市', 'lei_zhou_city');
INSERT INTO `address_county` VALUES ('1763', '202', '吴川市', 'wu_chuan_city');
INSERT INTO `address_county` VALUES ('1764', '203', '茂南区', 'mao_nan_district');
INSERT INTO `address_county` VALUES ('1765', '203', '茂港区', 'mao_gang_district');
INSERT INTO `address_county` VALUES ('1766', '203', '电白县', 'dian_bai_county');
INSERT INTO `address_county` VALUES ('1767', '203', '高州市', 'gao_zhou_city');
INSERT INTO `address_county` VALUES ('1768', '203', '化州市', 'hua_zhou_city');
INSERT INTO `address_county` VALUES ('1769', '203', '信宜市', 'xin_yi_city');
INSERT INTO `address_county` VALUES ('1770', '204', '端州区', 'duan_zhou_district');
INSERT INTO `address_county` VALUES ('1771', '204', '鼎湖区', 'ding_hu_district');
INSERT INTO `address_county` VALUES ('1772', '204', '广宁县', 'guang_ning_county');
INSERT INTO `address_county` VALUES ('1773', '204', '怀集县', 'huai_ji_county');
INSERT INTO `address_county` VALUES ('1774', '204', '封开县', 'feng_kai_county');
INSERT INTO `address_county` VALUES ('1775', '204', '德庆县', 'de_qing_county');
INSERT INTO `address_county` VALUES ('1776', '204', '高要市', 'gao_yao_city');
INSERT INTO `address_county` VALUES ('1777', '204', '四会市', 'si_hui_city');
INSERT INTO `address_county` VALUES ('1778', '205', '惠城区', 'hui_cheng_district');
INSERT INTO `address_county` VALUES ('1779', '205', '惠阳区', 'hui_yang_district');
INSERT INTO `address_county` VALUES ('1780', '205', '博罗县', 'bo_luo_county');
INSERT INTO `address_county` VALUES ('1781', '205', '惠东县', 'hui_dong_county');
INSERT INTO `address_county` VALUES ('1782', '205', '龙门县', 'long_men_county');
INSERT INTO `address_county` VALUES ('1783', '206', '梅江区', 'mei_jiang_district');
INSERT INTO `address_county` VALUES ('1784', '206', '梅县', 'mei_county');
INSERT INTO `address_county` VALUES ('1785', '206', '大埔县', 'da_pu_county');
INSERT INTO `address_county` VALUES ('1786', '206', '丰顺县', 'feng_shun_county');
INSERT INTO `address_county` VALUES ('1787', '206', '五华县', 'wu_hua_county');
INSERT INTO `address_county` VALUES ('1788', '206', '平远县', 'ping_yuan_county');
INSERT INTO `address_county` VALUES ('1789', '206', '蕉岭县', 'jiao_ling_county');
INSERT INTO `address_county` VALUES ('1790', '206', '兴宁市', 'xing_ning_city');
INSERT INTO `address_county` VALUES ('1791', '207', '海丰县', 'hai_feng_county');
INSERT INTO `address_county` VALUES ('1792', '207', '陆河县', 'lu_he_county');
INSERT INTO `address_county` VALUES ('1793', '207', '陆丰市', 'lu_feng_city');
INSERT INTO `address_county` VALUES ('1794', '208', '源城区', 'yuan_cheng_district');
INSERT INTO `address_county` VALUES ('1795', '208', '紫金县', 'zi_jin_county');
INSERT INTO `address_county` VALUES ('1796', '208', '龙川县', 'long_chuan_county');
INSERT INTO `address_county` VALUES ('1797', '208', '连平县', 'lian_ping_county');
INSERT INTO `address_county` VALUES ('1798', '208', '和平县', 'he_ping_county');
INSERT INTO `address_county` VALUES ('1799', '208', '东源县', 'dong_yuan_county');
INSERT INTO `address_county` VALUES ('1800', '209', '江城区', 'jiang_cheng_district');
INSERT INTO `address_county` VALUES ('1801', '209', '阳西县', 'yang_xi_county');
INSERT INTO `address_county` VALUES ('1802', '209', '阳东县', 'yang_dong_county');
INSERT INTO `address_county` VALUES ('1803', '209', '阳春市', 'yang_chun_city');
INSERT INTO `address_county` VALUES ('1804', '210', '清城区', 'qing_cheng_district');
INSERT INTO `address_county` VALUES ('1805', '210', '佛冈县', 'fo_gang_county');
INSERT INTO `address_county` VALUES ('1806', '210', '阳山县', 'yang_shan_county');
INSERT INTO `address_county` VALUES ('1807', '210', '连山壮族瑶族自治县', 'lian_shan_zhuang_zu_yao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1808', '210', '连南瑶族自治县', 'lian_nan_yao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1809', '210', '清新县', 'qing_xin_county');
INSERT INTO `address_county` VALUES ('1810', '210', '英德市', 'ying_de_city');
INSERT INTO `address_county` VALUES ('1811', '210', '连州市', 'lian_zhou_city');
INSERT INTO `address_county` VALUES ('1812', '213', '湘桥区', 'xiang_qiao_district');
INSERT INTO `address_county` VALUES ('1813', '213', '潮安县', 'chao_an_county');
INSERT INTO `address_county` VALUES ('1814', '213', '饶平县', 'rao_ping_county');
INSERT INTO `address_county` VALUES ('1815', '214', '榕城区', 'zuo_cheng_district');
INSERT INTO `address_county` VALUES ('1816', '214', '揭东县', 'jie_dong_county');
INSERT INTO `address_county` VALUES ('1817', '214', '揭西县', 'jie_xi_county');
INSERT INTO `address_county` VALUES ('1818', '214', '惠来县', 'hui_lai_county');
INSERT INTO `address_county` VALUES ('1819', '214', '普宁市', 'pu_ning_city');
INSERT INTO `address_county` VALUES ('1820', '215', '云城区', 'yun_cheng_district');
INSERT INTO `address_county` VALUES ('1821', '215', '新兴县', 'xin_xing_county');
INSERT INTO `address_county` VALUES ('1822', '215', '郁南县', 'yu_nan_county');
INSERT INTO `address_county` VALUES ('1823', '215', '云安县', 'yun_an_county');
INSERT INTO `address_county` VALUES ('1824', '215', '罗定市', 'luo_ding_city');
INSERT INTO `address_county` VALUES ('1825', '216', '兴宁区', 'xing_ning_district');
INSERT INTO `address_county` VALUES ('1826', '216', '青秀区', 'qing_xiu_district');
INSERT INTO `address_county` VALUES ('1827', '216', '江南区', 'jiang_nan_district');
INSERT INTO `address_county` VALUES ('1828', '216', '西乡塘区', 'xi_xiang_tang_district');
INSERT INTO `address_county` VALUES ('1829', '216', '良庆区', 'liang_qing_district');
INSERT INTO `address_county` VALUES ('1830', '216', '邕宁区', 'zuo_ning_district');
INSERT INTO `address_county` VALUES ('1831', '216', '武鸣县', 'wu_ming_county');
INSERT INTO `address_county` VALUES ('1832', '216', '隆安县', 'long_an_county');
INSERT INTO `address_county` VALUES ('1833', '216', '马山县', 'ma_shan_county');
INSERT INTO `address_county` VALUES ('1834', '216', '上林县', 'shang_lin_county');
INSERT INTO `address_county` VALUES ('1835', '216', '宾阳县', 'bin_yang_county');
INSERT INTO `address_county` VALUES ('1836', '216', '横县', 'heng_county');
INSERT INTO `address_county` VALUES ('1837', '217', '城中区', 'cheng_zhong_district');
INSERT INTO `address_county` VALUES ('1838', '217', '鱼峰区', 'yu_feng_district');
INSERT INTO `address_county` VALUES ('1839', '217', '柳南区', 'liu_nan_district');
INSERT INTO `address_county` VALUES ('1840', '217', '柳北区', 'liu_bei_district');
INSERT INTO `address_county` VALUES ('1841', '217', '柳江县', 'liu_jiang_county');
INSERT INTO `address_county` VALUES ('1842', '217', '柳城县', 'liu_cheng_county');
INSERT INTO `address_county` VALUES ('1843', '217', '鹿寨县', 'lu_zhai_county');
INSERT INTO `address_county` VALUES ('1844', '217', '融安县', 'rong_an_county');
INSERT INTO `address_county` VALUES ('1845', '217', '融水苗族自治县', 'rong_shui_miao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1846', '217', '三江侗族自治县', 'san_jiang_dong_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1847', '218', '秀峰区', 'xiu_feng_district');
INSERT INTO `address_county` VALUES ('1848', '218', '叠彩区', 'die_cai_district');
INSERT INTO `address_county` VALUES ('1849', '218', '象山区', 'xiang_shan_district');
INSERT INTO `address_county` VALUES ('1850', '218', '七星区', 'qi_xing_district');
INSERT INTO `address_county` VALUES ('1851', '218', '雁山区', 'yan_shan_district');
INSERT INTO `address_county` VALUES ('1852', '218', '阳朔县', 'yang_shuo_county');
INSERT INTO `address_county` VALUES ('1853', '218', '临桂县', 'lin_gui_county');
INSERT INTO `address_county` VALUES ('1854', '218', '灵川县', 'ling_chuan_county');
INSERT INTO `address_county` VALUES ('1855', '218', '全州县', 'quan_zhou_county');
INSERT INTO `address_county` VALUES ('1856', '218', '兴安县', 'xing_an_county');
INSERT INTO `address_county` VALUES ('1857', '218', '永福县', 'yong_fu_county');
INSERT INTO `address_county` VALUES ('1858', '218', '灌阳县', 'guan_yang_county');
INSERT INTO `address_county` VALUES ('1859', '218', '龙胜各族自治县', 'long_sheng_ge_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1860', '218', '资源县', 'zi_yuan_county');
INSERT INTO `address_county` VALUES ('1861', '218', '平乐县', 'ping_le_county');
INSERT INTO `address_county` VALUES ('1862', '218', '荔蒲县', 'li_pu_county');
INSERT INTO `address_county` VALUES ('1863', '218', '恭城瑶族自治县', 'gong_cheng_yao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1864', '219', '万秀区', 'wan_xiu_district');
INSERT INTO `address_county` VALUES ('1865', '219', '蝶山区', 'die_shan_district');
INSERT INTO `address_county` VALUES ('1866', '219', '长洲区', 'chang_zhou_district');
INSERT INTO `address_county` VALUES ('1867', '219', '苍梧县', 'cang_wu_county');
INSERT INTO `address_county` VALUES ('1868', '219', '藤县', 'teng_county');
INSERT INTO `address_county` VALUES ('1869', '219', '蒙山县', 'meng_shan_county');
INSERT INTO `address_county` VALUES ('1870', '219', '岑溪市', 'zuo_xi_city');
INSERT INTO `address_county` VALUES ('1871', '220', '海城区', 'hai_cheng_district');
INSERT INTO `address_county` VALUES ('1872', '220', '银海区', 'yin_hai_district');
INSERT INTO `address_county` VALUES ('1873', '220', '铁山港区', 'tie_shan_gang_district');
INSERT INTO `address_county` VALUES ('1874', '220', '合浦县', 'he_pu_county');
INSERT INTO `address_county` VALUES ('1875', '221', '港口区', 'gang_kou_district');
INSERT INTO `address_county` VALUES ('1876', '221', '防城区', 'fang_cheng_district');
INSERT INTO `address_county` VALUES ('1877', '221', '上思县', 'shang_si_county');
INSERT INTO `address_county` VALUES ('1878', '221', '东兴市', 'dong_xing_city');
INSERT INTO `address_county` VALUES ('1879', '222', '钦南区', 'qin_nan_district');
INSERT INTO `address_county` VALUES ('1880', '222', '钦北区', 'qin_bei_district');
INSERT INTO `address_county` VALUES ('1881', '222', '灵山县', 'ling_shan_county');
INSERT INTO `address_county` VALUES ('1882', '222', '浦北县', 'pu_bei_county');
INSERT INTO `address_county` VALUES ('1883', '223', '港北区', 'gang_bei_district');
INSERT INTO `address_county` VALUES ('1884', '223', '港南区', 'gang_nan_district');
INSERT INTO `address_county` VALUES ('1885', '223', '覃塘区', 'zuo_tang_district');
INSERT INTO `address_county` VALUES ('1886', '223', '平南县', 'ping_nan_county');
INSERT INTO `address_county` VALUES ('1887', '223', '桂平市', 'gui_ping_city');
INSERT INTO `address_county` VALUES ('1888', '224', '玉州区', 'yu_zhou_district');
INSERT INTO `address_county` VALUES ('1889', '224', '容县', 'rong_county');
INSERT INTO `address_county` VALUES ('1890', '224', '陆川县', 'lu_chuan_county');
INSERT INTO `address_county` VALUES ('1891', '224', '博白县', 'bo_bai_county');
INSERT INTO `address_county` VALUES ('1892', '224', '兴业县', 'xing_ye_county');
INSERT INTO `address_county` VALUES ('1893', '224', '北流市', 'bei_liu_city');
INSERT INTO `address_county` VALUES ('1894', '225', '右江区', 'you_jiang_district');
INSERT INTO `address_county` VALUES ('1895', '225', '田阳县', 'tian_yang_county');
INSERT INTO `address_county` VALUES ('1896', '225', '田东县', 'tian_dong_county');
INSERT INTO `address_county` VALUES ('1897', '225', '平果县', 'ping_guo_county');
INSERT INTO `address_county` VALUES ('1898', '225', '德保县', 'de_bao_county');
INSERT INTO `address_county` VALUES ('1899', '225', '靖西县', 'jing_xi_county');
INSERT INTO `address_county` VALUES ('1900', '225', '那坡县', 'na_po_county');
INSERT INTO `address_county` VALUES ('1901', '225', '凌云县', 'ling_yun_county');
INSERT INTO `address_county` VALUES ('1902', '225', '乐业县', 'le_ye_county');
INSERT INTO `address_county` VALUES ('1903', '225', '田林县', 'tian_lin_county');
INSERT INTO `address_county` VALUES ('1904', '225', '西林县', 'xi_lin_county');
INSERT INTO `address_county` VALUES ('1905', '225', '隆林各族自治县', 'long_lin_ge_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1906', '226', '八步区', 'ba_bu_district');
INSERT INTO `address_county` VALUES ('1907', '226', '昭平县', 'zhao_ping_county');
INSERT INTO `address_county` VALUES ('1908', '226', '钟山县', 'zhong_shan_county');
INSERT INTO `address_county` VALUES ('1909', '226', '富川瑶族自治县', 'fu_chuan_yao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1910', '227', '金城江区', 'jin_cheng_jiang_district');
INSERT INTO `address_county` VALUES ('1911', '227', '南丹县', 'nan_dan_county');
INSERT INTO `address_county` VALUES ('1912', '227', '天峨县', 'tian_e_county');
INSERT INTO `address_county` VALUES ('1913', '227', '凤山县', 'feng_shan_county');
INSERT INTO `address_county` VALUES ('1914', '227', '东兰县', 'dong_lan_county');
INSERT INTO `address_county` VALUES ('1915', '227', '罗城仫佬族自治县', 'luo_cheng_zuo_lao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1916', '227', '环江毛南族自治县', 'huan_jiang_mao_nan_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1917', '227', '巴马瑶族自治县', 'ba_ma_yao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1918', '227', '都安瑶族自治县', 'du_an_yao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1919', '227', '大化瑶族自治县', 'da_hua_yao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1920', '227', '宜州市', 'yi_zhou_city');
INSERT INTO `address_county` VALUES ('1921', '228', '兴宾区', 'xing_bin_district');
INSERT INTO `address_county` VALUES ('1922', '228', '忻城县', 'xin_cheng_county');
INSERT INTO `address_county` VALUES ('1923', '228', '象州县', 'xiang_zhou_county');
INSERT INTO `address_county` VALUES ('1924', '228', '武宣县', 'wu_xuan_county');
INSERT INTO `address_county` VALUES ('1925', '228', '金秀瑶族自治县', 'jin_xiu_yao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1926', '228', '合山市', 'he_shan_city');
INSERT INTO `address_county` VALUES ('1927', '229', '江洲区', 'jiang_zhou_district');
INSERT INTO `address_county` VALUES ('1928', '229', '扶绥县', 'fu_sui_county');
INSERT INTO `address_county` VALUES ('1929', '229', '宁明县', 'ning_ming_county');
INSERT INTO `address_county` VALUES ('1930', '229', '龙州县', 'long_zhou_county');
INSERT INTO `address_county` VALUES ('1931', '229', '大新县', 'da_xin_county');
INSERT INTO `address_county` VALUES ('1932', '229', '天等县', 'tian_deng_county');
INSERT INTO `address_county` VALUES ('1933', '229', '凭祥市', 'ping_xiang_city');
INSERT INTO `address_county` VALUES ('1934', '230', '秀英区', 'xiu_ying_district');
INSERT INTO `address_county` VALUES ('1935', '230', '龙华区', 'long_hua_district');
INSERT INTO `address_county` VALUES ('1936', '230', '琼山区', 'qiong_shan_district');
INSERT INTO `address_county` VALUES ('1937', '230', '美兰区', 'mei_lan_district');
INSERT INTO `address_county` VALUES ('1938', '231', '五指山市', 'wu_zhi_shan_city');
INSERT INTO `address_county` VALUES ('1939', '231', '琼海市', 'qiong_hai_city');
INSERT INTO `address_county` VALUES ('1940', '231', '儋州市', 'zuo_zhou_city');
INSERT INTO `address_county` VALUES ('1941', '231', '文昌市', 'wen_chang_city');
INSERT INTO `address_county` VALUES ('1942', '231', '万宁市', 'wan_ning_city');
INSERT INTO `address_county` VALUES ('1943', '231', '东方市', 'dong_fang_city');
INSERT INTO `address_county` VALUES ('1944', '231', '定安县', 'ding_an_county');
INSERT INTO `address_county` VALUES ('1945', '231', '屯昌县', 'tun_chang_county');
INSERT INTO `address_county` VALUES ('1946', '231', '澄迈县', 'cheng_mai_county');
INSERT INTO `address_county` VALUES ('1947', '231', '临高县', 'lin_gao_county');
INSERT INTO `address_county` VALUES ('1948', '231', '白沙黎族自治县', 'bai_sha_li_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1949', '231', '昌江黎族自治县', 'chang_jiang_li_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1950', '231', '乐东黎族自治县', 'le_dong_li_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1951', '231', '陵水黎族自治县', 'ling_shui_li_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1952', '231', '保亭黎族苗族自治县', 'bao_ting_li_zu_miao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1953', '231', '琼中黎族苗族自治县', 'qiong_zhong_li_zu_miao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1954', '232', '西沙群岛', 'xi_sha_qun_dao');
INSERT INTO `address_county` VALUES ('1955', '232', '南沙群岛', 'nan_sha_qun_dao');
INSERT INTO `address_county` VALUES ('1956', '232', '中沙群岛的岛礁及其海域', 'zhong_sha_qun_dao_de_dao_jiao_ji_qi_hai_yu');
INSERT INTO `address_county` VALUES ('1957', '233', '万州区', 'wan_zhou_district');
INSERT INTO `address_county` VALUES ('1958', '233', '涪陵区', 'fu_ling_district');
INSERT INTO `address_county` VALUES ('1959', '233', '渝中区', 'yu_zhong_district');
INSERT INTO `address_county` VALUES ('1960', '233', '大渡口区', 'da_du_kou_district');
INSERT INTO `address_county` VALUES ('1961', '233', '沙坪坝区', 'sha_ping_ba_district');
INSERT INTO `address_county` VALUES ('1962', '233', '九龙坡区', 'jiu_long_po_district');
INSERT INTO `address_county` VALUES ('1963', '233', '南岸区', 'nan_an_district');
INSERT INTO `address_county` VALUES ('1964', '233', '北碚区', 'bei_zuo_district');
INSERT INTO `address_county` VALUES ('1965', '233', '綦江区', 'zuo_jiang_district');
INSERT INTO `address_county` VALUES ('1966', '233', '大足区', 'da_zu_district');
INSERT INTO `address_county` VALUES ('1967', '233', '渝北区', 'yu_bei_district');
INSERT INTO `address_county` VALUES ('1968', '233', '巴南区', 'ba_nan_district');
INSERT INTO `address_county` VALUES ('1969', '233', '黔江区', 'qian_jiang_district');
INSERT INTO `address_county` VALUES ('1970', '233', '长寿区', 'chang_shou_district');
INSERT INTO `address_county` VALUES ('1971', '233', '江津区', 'jiang_jin_district');
INSERT INTO `address_county` VALUES ('1972', '233', '合川区', 'he_chuan_district');
INSERT INTO `address_county` VALUES ('1973', '233', '永川区', 'yong_chuan_district');
INSERT INTO `address_county` VALUES ('1974', '233', '南川区', 'nan_chuan_district');
INSERT INTO `address_county` VALUES ('1975', '233', '潼南县', 'zuo_nan_county');
INSERT INTO `address_county` VALUES ('1976', '233', '铜梁县', 'tong_liang_county');
INSERT INTO `address_county` VALUES ('1977', '233', '荣昌县', 'rong_chang_county');
INSERT INTO `address_county` VALUES ('1978', '233', '璧山县', 'zuo_shan_county');
INSERT INTO `address_county` VALUES ('1979', '233', '梁平县', 'liang_ping_county');
INSERT INTO `address_county` VALUES ('1980', '233', '城口县', 'cheng_kou_county');
INSERT INTO `address_county` VALUES ('1981', '233', '丰都县', 'feng_du_county');
INSERT INTO `address_county` VALUES ('1982', '233', '垫江县', 'dian_jiang_county');
INSERT INTO `address_county` VALUES ('1983', '233', '武隆县', 'wu_long_county');
INSERT INTO `address_county` VALUES ('1984', '233', '忠县', 'zhong_county');
INSERT INTO `address_county` VALUES ('1985', '233', '开县', 'kai_county');
INSERT INTO `address_county` VALUES ('1986', '233', '云阳县', 'yun_yang_county');
INSERT INTO `address_county` VALUES ('1987', '233', '奉节县', 'feng_jie_county');
INSERT INTO `address_county` VALUES ('1988', '233', '巫山县', 'wu_shan_county');
INSERT INTO `address_county` VALUES ('1989', '233', '巫溪县', 'wu_xi_county');
INSERT INTO `address_county` VALUES ('1990', '233', '石柱土家族自治县', 'shi_zhu_tu_jia_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1991', '233', '秀山土家族苗族自治县', 'xiu_shan_tu_jia_zu_miao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1992', '233', '酉阳土家族苗族自治县', 'you_yang_tu_jia_zu_miao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1993', '233', '彭水苗族土家族自治县', 'peng_shui_miao_zu_tu_jia_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('1994', '234', '锦江区', 'jin_jiang_district');
INSERT INTO `address_county` VALUES ('1995', '234', '青羊区', 'qing_yang_district');
INSERT INTO `address_county` VALUES ('1996', '234', '金牛区', 'jin_niu_district');
INSERT INTO `address_county` VALUES ('1997', '234', '武侯区', 'wu_hou_district');
INSERT INTO `address_county` VALUES ('1998', '234', '成华区', 'cheng_hua_district');
INSERT INTO `address_county` VALUES ('1999', '234', '龙泉驿区', 'long_quan_zuo_district');
INSERT INTO `address_county` VALUES ('2000', '234', '青白江区', 'qing_bai_jiang_district');
INSERT INTO `address_county` VALUES ('2001', '234', '新都区', 'xin_du_district');
INSERT INTO `address_county` VALUES ('2002', '234', '温江区', 'wen_jiang_district');
INSERT INTO `address_county` VALUES ('2003', '234', '金堂县', 'jin_tang_county');
INSERT INTO `address_county` VALUES ('2004', '234', '双流县', 'shuang_liu_county');
INSERT INTO `address_county` VALUES ('2005', '234', '郫县', 'zuo_county');
INSERT INTO `address_county` VALUES ('2006', '234', '大邑县', 'da_yi_county');
INSERT INTO `address_county` VALUES ('2007', '234', '蒲江县', 'pu_jiang_county');
INSERT INTO `address_county` VALUES ('2008', '234', '新津县', 'xin_jin_county');
INSERT INTO `address_county` VALUES ('2009', '234', '都江堰市', 'du_jiang_yan_city');
INSERT INTO `address_county` VALUES ('2010', '234', '彭州市', 'peng_zhou_city');
INSERT INTO `address_county` VALUES ('2011', '234', '邛崃市', 'zuo_zuo_city');
INSERT INTO `address_county` VALUES ('2012', '234', '崇州市', 'chong_zhou_city');
INSERT INTO `address_county` VALUES ('2013', '235', '自流井区', 'zi_liu_jing_district');
INSERT INTO `address_county` VALUES ('2014', '235', '贡井区', 'gong_jing_district');
INSERT INTO `address_county` VALUES ('2015', '235', '大安区', 'da_an_district');
INSERT INTO `address_county` VALUES ('2016', '235', '沿滩区', 'yan_tan_district');
INSERT INTO `address_county` VALUES ('2017', '235', '荣县', 'rong_county');
INSERT INTO `address_county` VALUES ('2018', '235', '富顺县', 'fu_shun_county');
INSERT INTO `address_county` VALUES ('2019', '236', '东区', 'dong_district');
INSERT INTO `address_county` VALUES ('2020', '236', '西区', 'xi_district');
INSERT INTO `address_county` VALUES ('2021', '236', '仁和区', 'ren_he_district');
INSERT INTO `address_county` VALUES ('2022', '236', '米易县', 'mi_yi_county');
INSERT INTO `address_county` VALUES ('2023', '236', '盐边县', 'yan_bian_county');
INSERT INTO `address_county` VALUES ('2024', '237', '江阳区', 'jiang_yang_district');
INSERT INTO `address_county` VALUES ('2025', '237', '纳溪区', 'na_xi_district');
INSERT INTO `address_county` VALUES ('2026', '237', '龙马潭区', 'long_ma_tan_district');
INSERT INTO `address_county` VALUES ('2027', '237', '泸县', 'zuo_county');
INSERT INTO `address_county` VALUES ('2028', '237', '合江县', 'he_jiang_county');
INSERT INTO `address_county` VALUES ('2029', '237', '叙永县', 'xu_yong_county');
INSERT INTO `address_county` VALUES ('2030', '237', '古蔺县', 'gu_zuo_county');
INSERT INTO `address_county` VALUES ('2031', '238', '旌阳区', 'zuo_yang_district');
INSERT INTO `address_county` VALUES ('2032', '238', '中江县', 'zhong_jiang_county');
INSERT INTO `address_county` VALUES ('2033', '238', '罗江县', 'luo_jiang_county');
INSERT INTO `address_county` VALUES ('2034', '238', '广汉市', 'guang_han_city');
INSERT INTO `address_county` VALUES ('2035', '238', '什邡市', 'shi_zuo_city');
INSERT INTO `address_county` VALUES ('2036', '238', '绵竹市', 'mian_zhu_city');
INSERT INTO `address_county` VALUES ('2037', '239', '涪城区', 'fu_cheng_district');
INSERT INTO `address_county` VALUES ('2038', '239', '游仙区', 'you_xian_district');
INSERT INTO `address_county` VALUES ('2039', '239', '三台县', 'san_tai_county');
INSERT INTO `address_county` VALUES ('2040', '239', '盐亭县', 'yan_ting_county');
INSERT INTO `address_county` VALUES ('2041', '239', '安县', 'an_county');
INSERT INTO `address_county` VALUES ('2042', '239', '梓潼县', 'zuo_zuo_county');
INSERT INTO `address_county` VALUES ('2043', '239', '北川羌族自治县', 'bei_chuan_qiang_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2044', '239', '平武县', 'ping_wu_county');
INSERT INTO `address_county` VALUES ('2045', '239', '江油市', 'jiang_you_city');
INSERT INTO `address_county` VALUES ('2046', '240', '利州区', 'li_zhou_district');
INSERT INTO `address_county` VALUES ('2047', '240', '元坝区', 'yuan_ba_district');
INSERT INTO `address_county` VALUES ('2048', '240', '朝天区', 'chao_tian_district');
INSERT INTO `address_county` VALUES ('2049', '240', '旺苍县', 'wang_cang_county');
INSERT INTO `address_county` VALUES ('2050', '240', '青川县', 'qing_chuan_county');
INSERT INTO `address_county` VALUES ('2051', '240', '剑阁县', 'jian_ge_county');
INSERT INTO `address_county` VALUES ('2052', '240', '苍溪县', 'cang_xi_county');
INSERT INTO `address_county` VALUES ('2053', '241', '船山区', 'chuan_shan_district');
INSERT INTO `address_county` VALUES ('2054', '241', '安居区', 'an_ju_district');
INSERT INTO `address_county` VALUES ('2055', '241', '蓬溪县', 'peng_xi_county');
INSERT INTO `address_county` VALUES ('2056', '241', '射洪县', 'she_hong_county');
INSERT INTO `address_county` VALUES ('2057', '241', '大英县', 'da_ying_county');
INSERT INTO `address_county` VALUES ('2058', '242', '东兴区', 'dong_xing_district');
INSERT INTO `address_county` VALUES ('2059', '242', '威远县', 'wei_yuan_county');
INSERT INTO `address_county` VALUES ('2060', '242', '资中县', 'zi_zhong_county');
INSERT INTO `address_county` VALUES ('2061', '242', '隆昌县', 'long_chang_county');
INSERT INTO `address_county` VALUES ('2062', '243', '沙湾区', 'sha_wan_district');
INSERT INTO `address_county` VALUES ('2063', '243', '五通桥区', 'wu_tong_qiao_district');
INSERT INTO `address_county` VALUES ('2064', '243', '金口河区', 'jin_kou_he_district');
INSERT INTO `address_county` VALUES ('2065', '243', '犍为县', 'zuo_wei_county');
INSERT INTO `address_county` VALUES ('2066', '243', '井研县', 'jing_yan_county');
INSERT INTO `address_county` VALUES ('2067', '243', '夹江县', 'jia_jiang_county');
INSERT INTO `address_county` VALUES ('2068', '243', '沐川县', 'zuo_chuan_county');
INSERT INTO `address_county` VALUES ('2069', '243', '峨边彝族自治县', 'e_bian_yi_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2070', '243', '马边彝族自治县', 'ma_bian_yi_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2071', '243', '峨眉山市', 'e_mei_shan_city');
INSERT INTO `address_county` VALUES ('2072', '244', '顺庆区', 'shun_qing_district');
INSERT INTO `address_county` VALUES ('2073', '244', '高坪区', 'gao_ping_district');
INSERT INTO `address_county` VALUES ('2074', '244', '嘉陵区', 'jia_ling_district');
INSERT INTO `address_county` VALUES ('2075', '244', '南部县', 'nan_bu_county');
INSERT INTO `address_county` VALUES ('2076', '244', '营山县', 'ying_shan_county');
INSERT INTO `address_county` VALUES ('2077', '244', '蓬安县', 'peng_an_county');
INSERT INTO `address_county` VALUES ('2078', '244', '仪陇县', 'yi_long_county');
INSERT INTO `address_county` VALUES ('2079', '244', '西充县', 'xi_chong_county');
INSERT INTO `address_county` VALUES ('2080', '244', '阆中市', 'zuo_zhong_city');
INSERT INTO `address_county` VALUES ('2081', '245', '东坡区', 'dong_po_district');
INSERT INTO `address_county` VALUES ('2082', '245', '仁寿县', 'ren_shou_county');
INSERT INTO `address_county` VALUES ('2083', '245', '彭山县', 'peng_shan_county');
INSERT INTO `address_county` VALUES ('2084', '245', '洪雅县', 'hong_ya_county');
INSERT INTO `address_county` VALUES ('2085', '245', '丹棱县', 'dan_leng_county');
INSERT INTO `address_county` VALUES ('2086', '245', '青神县', 'qing_shen_county');
INSERT INTO `address_county` VALUES ('2087', '246', '翠屏区', 'cui_ping_district');
INSERT INTO `address_county` VALUES ('2088', '246', '南溪区', 'nan_xi_district');
INSERT INTO `address_county` VALUES ('2089', '246', '宜宾县', 'yi_bin_county');
INSERT INTO `address_county` VALUES ('2090', '246', '江安县', 'jiang_an_county');
INSERT INTO `address_county` VALUES ('2091', '246', '长宁县', 'chang_ning_county');
INSERT INTO `address_county` VALUES ('2092', '246', '高县', 'gao_county');
INSERT INTO `address_county` VALUES ('2093', '246', '珙县', 'zuo_county');
INSERT INTO `address_county` VALUES ('2094', '246', '筠连县', 'zuo_lian_county');
INSERT INTO `address_county` VALUES ('2095', '246', '兴文县', 'xing_wen_county');
INSERT INTO `address_county` VALUES ('2096', '246', '屏山县', 'ping_shan_county');
INSERT INTO `address_county` VALUES ('2097', '247', '广安区', 'guang_an_district');
INSERT INTO `address_county` VALUES ('2098', '247', '岳池县', 'yue_chi_county');
INSERT INTO `address_county` VALUES ('2099', '247', '武胜县', 'wu_sheng_county');
INSERT INTO `address_county` VALUES ('2100', '247', '邻水县', 'lin_shui_county');
INSERT INTO `address_county` VALUES ('2101', '247', '华蓥市', 'hua_zuo_city');
INSERT INTO `address_county` VALUES ('2102', '248', '通川区', 'tong_chuan_district');
INSERT INTO `address_county` VALUES ('2103', '248', '达县', 'da_county');
INSERT INTO `address_county` VALUES ('2104', '248', '宣汉县', 'xuan_han_county');
INSERT INTO `address_county` VALUES ('2105', '248', '开江县', 'kai_jiang_county');
INSERT INTO `address_county` VALUES ('2106', '248', '大竹县', 'da_zhu_county');
INSERT INTO `address_county` VALUES ('2107', '248', '渠县', 'qu_county');
INSERT INTO `address_county` VALUES ('2108', '248', '万源市', 'wan_yuan_city');
INSERT INTO `address_county` VALUES ('2109', '249', '雨城区', 'yu_cheng_district');
INSERT INTO `address_county` VALUES ('2110', '249', '名山县', 'ming_shan_county');
INSERT INTO `address_county` VALUES ('2111', '249', '荥经县', 'zuo_jing_county');
INSERT INTO `address_county` VALUES ('2112', '249', '汉源县', 'han_yuan_county');
INSERT INTO `address_county` VALUES ('2113', '249', '石棉县', 'shi_mian_county');
INSERT INTO `address_county` VALUES ('2114', '249', '天全县', 'tian_quan_county');
INSERT INTO `address_county` VALUES ('2115', '249', '芦山县', 'lu_shan_county');
INSERT INTO `address_county` VALUES ('2116', '249', '宝兴县', 'bao_xing_county');
INSERT INTO `address_county` VALUES ('2117', '250', '巴州区', 'ba_zhou_district');
INSERT INTO `address_county` VALUES ('2118', '250', '通江县', 'tong_jiang_county');
INSERT INTO `address_county` VALUES ('2119', '250', '南江县', 'nan_jiang_county');
INSERT INTO `address_county` VALUES ('2120', '250', '平昌县', 'ping_chang_county');
INSERT INTO `address_county` VALUES ('2121', '251', '雁江区', 'yan_jiang_district');
INSERT INTO `address_county` VALUES ('2122', '251', '安岳县', 'an_yue_county');
INSERT INTO `address_county` VALUES ('2123', '251', '乐至县', 'le_zhi_county');
INSERT INTO `address_county` VALUES ('2124', '251', '简阳市', 'jian_yang_city');
INSERT INTO `address_county` VALUES ('2125', '252', '汶川县', 'zuo_chuan_county');
INSERT INTO `address_county` VALUES ('2126', '252', '理县', 'li_county');
INSERT INTO `address_county` VALUES ('2127', '252', '茂县', 'mao_county');
INSERT INTO `address_county` VALUES ('2128', '252', '松潘县', 'song_pan_county');
INSERT INTO `address_county` VALUES ('2129', '252', '九寨沟县', 'jiu_zhai_gou_county');
INSERT INTO `address_county` VALUES ('2130', '252', '金川县', 'jin_chuan_county');
INSERT INTO `address_county` VALUES ('2131', '252', '小金县', 'xiao_jin_county');
INSERT INTO `address_county` VALUES ('2132', '252', '黑水县', 'hei_shui_county');
INSERT INTO `address_county` VALUES ('2133', '252', '马尔康县', 'ma_er_kang_county');
INSERT INTO `address_county` VALUES ('2134', '252', '壤塘县', 'rang_tang_county');
INSERT INTO `address_county` VALUES ('2135', '252', '阿坝县', 'a_ba_county');
INSERT INTO `address_county` VALUES ('2136', '252', '若尔盖县', 'ruo_er_gai_county');
INSERT INTO `address_county` VALUES ('2137', '252', '红原县', 'hong_yuan_county');
INSERT INTO `address_county` VALUES ('2138', '253', '康定县', 'kang_ding_county');
INSERT INTO `address_county` VALUES ('2139', '253', '泸定县', 'zuo_ding_county');
INSERT INTO `address_county` VALUES ('2140', '253', '丹巴县', 'dan_ba_county');
INSERT INTO `address_county` VALUES ('2141', '253', '九龙县', 'jiu_long_county');
INSERT INTO `address_county` VALUES ('2142', '253', '雅江县', 'ya_jiang_county');
INSERT INTO `address_county` VALUES ('2143', '253', '道孚县', 'dao_zuo_county');
INSERT INTO `address_county` VALUES ('2144', '253', '炉霍县', 'lu_huo_county');
INSERT INTO `address_county` VALUES ('2145', '253', '甘孜县', 'gan_zi_county');
INSERT INTO `address_county` VALUES ('2146', '253', '新龙县', 'xin_long_county');
INSERT INTO `address_county` VALUES ('2147', '253', '德格县', 'de_ge_county');
INSERT INTO `address_county` VALUES ('2148', '253', '白玉县', 'bai_yu_county');
INSERT INTO `address_county` VALUES ('2149', '253', '石渠县', 'shi_qu_county');
INSERT INTO `address_county` VALUES ('2150', '253', '色达县', 'se_da_county');
INSERT INTO `address_county` VALUES ('2151', '253', '理塘县', 'li_tang_county');
INSERT INTO `address_county` VALUES ('2152', '253', '巴塘县', 'ba_tang_county');
INSERT INTO `address_county` VALUES ('2153', '253', '乡城县', 'xiang_cheng_county');
INSERT INTO `address_county` VALUES ('2154', '253', '稻城县', 'dao_cheng_county');
INSERT INTO `address_county` VALUES ('2155', '253', '得荣县', 'de_rong_county');
INSERT INTO `address_county` VALUES ('2156', '254', '西昌市', 'xi_chang_city');
INSERT INTO `address_county` VALUES ('2157', '254', '木里藏族自治县', 'mu_li_cang_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2158', '254', '盐源县', 'yan_yuan_county');
INSERT INTO `address_county` VALUES ('2159', '254', '德昌县', 'de_chang_county');
INSERT INTO `address_county` VALUES ('2160', '254', '会理县', 'hui_li_county');
INSERT INTO `address_county` VALUES ('2161', '254', '会东县', 'hui_dong_county');
INSERT INTO `address_county` VALUES ('2162', '254', '宁南县', 'ning_nan_county');
INSERT INTO `address_county` VALUES ('2163', '254', '普格县', 'pu_ge_county');
INSERT INTO `address_county` VALUES ('2164', '254', '布拖县', 'bu_tuo_county');
INSERT INTO `address_county` VALUES ('2165', '254', '金阳县', 'jin_yang_county');
INSERT INTO `address_county` VALUES ('2166', '254', '昭觉县', 'zhao_jue_county');
INSERT INTO `address_county` VALUES ('2167', '254', '喜德县', 'xi_de_county');
INSERT INTO `address_county` VALUES ('2168', '254', '冕宁县', 'mian_ning_county');
INSERT INTO `address_county` VALUES ('2169', '254', '越西县', 'yue_xi_county');
INSERT INTO `address_county` VALUES ('2170', '254', '甘洛县', 'gan_luo_county');
INSERT INTO `address_county` VALUES ('2171', '254', '美姑县', 'mei_gu_county');
INSERT INTO `address_county` VALUES ('2172', '254', '雷波县', 'lei_bo_county');
INSERT INTO `address_county` VALUES ('2173', '255', '南明区', 'nan_ming_district');
INSERT INTO `address_county` VALUES ('2174', '255', '云岩区', 'yun_yan_district');
INSERT INTO `address_county` VALUES ('2175', '255', '花溪区', 'hua_xi_district');
INSERT INTO `address_county` VALUES ('2176', '255', '乌当区', 'wu_dang_district');
INSERT INTO `address_county` VALUES ('2177', '255', '小河区', 'xiao_he_district');
INSERT INTO `address_county` VALUES ('2178', '255', '开阳县', 'kai_yang_county');
INSERT INTO `address_county` VALUES ('2179', '255', '息烽县', 'xi_feng_county');
INSERT INTO `address_county` VALUES ('2180', '255', '修文县', 'xiu_wen_county');
INSERT INTO `address_county` VALUES ('2181', '255', '清镇市', 'qing_zhen_city');
INSERT INTO `address_county` VALUES ('2182', '256', '钟山区', 'zhong_shan_district');
INSERT INTO `address_county` VALUES ('2183', '256', '六枝特区', 'liu_zhi_te_district');
INSERT INTO `address_county` VALUES ('2184', '256', '水城县', 'shui_cheng_county');
INSERT INTO `address_county` VALUES ('2185', '256', '盘县', 'pan_county');
INSERT INTO `address_county` VALUES ('2186', '257', '红花岗区', 'hong_hua_gang_district');
INSERT INTO `address_county` VALUES ('2187', '257', '汇川区', 'hui_chuan_district');
INSERT INTO `address_county` VALUES ('2188', '257', '遵义县', 'zun_yi_county');
INSERT INTO `address_county` VALUES ('2189', '257', '桐梓县', 'tong_zuo_county');
INSERT INTO `address_county` VALUES ('2190', '257', '绥阳县', 'sui_yang_county');
INSERT INTO `address_county` VALUES ('2191', '257', '正安县', 'zheng_an_county');
INSERT INTO `address_county` VALUES ('2192', '257', '道真仡佬族苗族自治县', 'dao_zhen_zuo_lao_zu_miao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2193', '257', '务川仡佬族苗族自治县', 'wu_chuan_zuo_lao_zu_miao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2194', '257', '凤冈县', 'feng_gang_county');
INSERT INTO `address_county` VALUES ('2195', '257', '湄潭县', 'zuo_tan_county');
INSERT INTO `address_county` VALUES ('2196', '257', '余庆县', 'yu_qing_county');
INSERT INTO `address_county` VALUES ('2197', '257', '习水县', 'xi_shui_county');
INSERT INTO `address_county` VALUES ('2198', '257', '赤水市', 'chi_shui_city');
INSERT INTO `address_county` VALUES ('2199', '257', '仁怀市', 'ren_huai_city');
INSERT INTO `address_county` VALUES ('2200', '258', '西秀区', 'xi_xiu_district');
INSERT INTO `address_county` VALUES ('2201', '258', '平坝县', 'ping_ba_county');
INSERT INTO `address_county` VALUES ('2202', '258', '普定县', 'pu_ding_county');
INSERT INTO `address_county` VALUES ('2203', '258', '镇宁布依族苗族自治县', 'zhen_ning_bu_yi_zu_miao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2204', '258', '关岭布依族苗族自治县', 'guan_ling_bu_yi_zu_miao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2205', '258', '紫云苗族布依族自治县', 'zi_yun_miao_zu_bu_yi_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2206', '259', '七星关区', 'qi_xing_guan_district');
INSERT INTO `address_county` VALUES ('2207', '259', '大方县', 'da_fang_county');
INSERT INTO `address_county` VALUES ('2208', '259', '黔西县', 'qian_xi_county');
INSERT INTO `address_county` VALUES ('2209', '259', '金沙县', 'jin_sha_county');
INSERT INTO `address_county` VALUES ('2210', '259', '织金县', 'zhi_jin_county');
INSERT INTO `address_county` VALUES ('2211', '259', '纳雍县', 'na_yong_county');
INSERT INTO `address_county` VALUES ('2212', '259', '威宁彝族回族苗族自治县', 'wei_ning_yi_zu_hui_zu_miao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2213', '259', '赫章县', 'he_zhang_county');
INSERT INTO `address_county` VALUES ('2214', '260', '碧江区', 'bi_jiang_district');
INSERT INTO `address_county` VALUES ('2215', '260', '万山区', 'wan_shan_district');
INSERT INTO `address_county` VALUES ('2216', '260', '江口县', 'jiang_kou_county');
INSERT INTO `address_county` VALUES ('2217', '260', '玉屏侗族自治县', 'yu_ping_dong_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2218', '260', '石阡县', 'shi_zuo_county');
INSERT INTO `address_county` VALUES ('2219', '260', '思南县', 'si_nan_county');
INSERT INTO `address_county` VALUES ('2220', '260', '印江土家族苗族自治县', 'yin_jiang_tu_jia_zu_miao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2221', '260', '德江县', 'de_jiang_county');
INSERT INTO `address_county` VALUES ('2222', '260', '沿河土家族自治县', 'yan_he_tu_jia_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2223', '260', '松桃苗族自治县', 'song_tao_miao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2224', '261', '兴义市', 'xing_yi_city');
INSERT INTO `address_county` VALUES ('2225', '261', '兴仁县', 'xing_ren_county');
INSERT INTO `address_county` VALUES ('2226', '261', '普安县', 'pu_an_county');
INSERT INTO `address_county` VALUES ('2227', '261', '晴隆县', 'qing_long_county');
INSERT INTO `address_county` VALUES ('2228', '261', '贞丰县', 'zhen_feng_county');
INSERT INTO `address_county` VALUES ('2229', '261', '望谟县', 'wang_zuo_county');
INSERT INTO `address_county` VALUES ('2230', '261', '册亨县', 'ce_heng_county');
INSERT INTO `address_county` VALUES ('2231', '261', '安龙县', 'an_long_county');
INSERT INTO `address_county` VALUES ('2232', '262', '凯里市', 'kai_li_city');
INSERT INTO `address_county` VALUES ('2233', '262', '黄平县', 'huang_ping_county');
INSERT INTO `address_county` VALUES ('2234', '262', '施秉县', 'shi_bing_county');
INSERT INTO `address_county` VALUES ('2235', '262', '三穗县', 'san_sui_county');
INSERT INTO `address_county` VALUES ('2236', '262', '镇远县', 'zhen_yuan_county');
INSERT INTO `address_county` VALUES ('2237', '262', '岑巩县', 'zuo_gong_county');
INSERT INTO `address_county` VALUES ('2238', '262', '天柱县', 'tian_zhu_county');
INSERT INTO `address_county` VALUES ('2239', '262', '锦屏县', 'jin_ping_county');
INSERT INTO `address_county` VALUES ('2240', '262', '剑河县', 'jian_he_county');
INSERT INTO `address_county` VALUES ('2241', '262', '台江县', 'tai_jiang_county');
INSERT INTO `address_county` VALUES ('2242', '262', '黎平县', 'li_ping_county');
INSERT INTO `address_county` VALUES ('2243', '262', '榕江县', 'zuo_jiang_county');
INSERT INTO `address_county` VALUES ('2244', '262', '从江县', 'cong_jiang_county');
INSERT INTO `address_county` VALUES ('2245', '262', '雷山县', 'lei_shan_county');
INSERT INTO `address_county` VALUES ('2246', '262', '麻江县', 'ma_jiang_county');
INSERT INTO `address_county` VALUES ('2247', '262', '丹寨县', 'dan_zhai_county');
INSERT INTO `address_county` VALUES ('2248', '263', '都匀市', 'du_yun_city');
INSERT INTO `address_county` VALUES ('2249', '263', '福泉市', 'fu_quan_city');
INSERT INTO `address_county` VALUES ('2250', '263', '荔波县', 'li_bo_county');
INSERT INTO `address_county` VALUES ('2251', '263', '贵定县', 'gui_ding_county');
INSERT INTO `address_county` VALUES ('2252', '263', '瓮安县', 'weng_an_county');
INSERT INTO `address_county` VALUES ('2253', '263', '独山县', 'du_shan_county');
INSERT INTO `address_county` VALUES ('2254', '263', '平塘县', 'ping_tang_county');
INSERT INTO `address_county` VALUES ('2255', '263', '罗甸县', 'luo_dian_county');
INSERT INTO `address_county` VALUES ('2256', '263', '长顺县', 'chang_shun_county');
INSERT INTO `address_county` VALUES ('2257', '263', '龙里县', 'long_li_county');
INSERT INTO `address_county` VALUES ('2258', '263', '惠水县', 'hui_shui_county');
INSERT INTO `address_county` VALUES ('2259', '263', '三都水族自治县', 'san_du_shui_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2260', '264', '五华区', 'wu_hua_district');
INSERT INTO `address_county` VALUES ('2261', '264', '盘龙区', 'pan_long_district');
INSERT INTO `address_county` VALUES ('2262', '264', '官渡区', 'guan_du_district');
INSERT INTO `address_county` VALUES ('2263', '264', '西山区', 'xi_shan_district');
INSERT INTO `address_county` VALUES ('2264', '264', '东川区', 'dong_chuan_district');
INSERT INTO `address_county` VALUES ('2265', '264', '呈贡区', 'cheng_gong_district');
INSERT INTO `address_county` VALUES ('2266', '264', '晋宁县', 'jin_ning_county');
INSERT INTO `address_county` VALUES ('2267', '264', '富民县', 'fu_min_county');
INSERT INTO `address_county` VALUES ('2268', '264', '宜良县', 'yi_liang_county');
INSERT INTO `address_county` VALUES ('2269', '264', '石林彝族自治县', 'shi_lin_yi_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2270', '264', '嵩明县', 'zuo_ming_county');
INSERT INTO `address_county` VALUES ('2271', '264', '禄劝彝族苗族自治县', 'lu_quan_yi_zu_miao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2272', '264', '寻甸回族彝族自治县', 'xun_dian_hui_zu_yi_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2273', '264', '安宁市', 'an_ning_city');
INSERT INTO `address_county` VALUES ('2274', '265', '麒麟区', 'zuo_zuo_district');
INSERT INTO `address_county` VALUES ('2275', '265', '马龙县', 'ma_long_county');
INSERT INTO `address_county` VALUES ('2276', '265', '陆良县', 'lu_liang_county');
INSERT INTO `address_county` VALUES ('2277', '265', '师宗县', 'shi_zong_county');
INSERT INTO `address_county` VALUES ('2278', '265', '罗平县', 'luo_ping_county');
INSERT INTO `address_county` VALUES ('2279', '265', '富源县', 'fu_yuan_county');
INSERT INTO `address_county` VALUES ('2280', '265', '会泽县', 'hui_ze_county');
INSERT INTO `address_county` VALUES ('2281', '265', '沾益县', 'zhan_yi_county');
INSERT INTO `address_county` VALUES ('2282', '265', '宣威市', 'xuan_wei_city');
INSERT INTO `address_county` VALUES ('2283', '266', '红塔区', 'hong_ta_district');
INSERT INTO `address_county` VALUES ('2284', '266', '江川县', 'jiang_chuan_county');
INSERT INTO `address_county` VALUES ('2285', '266', '澄江县', 'cheng_jiang_county');
INSERT INTO `address_county` VALUES ('2286', '266', '通海县', 'tong_hai_county');
INSERT INTO `address_county` VALUES ('2287', '266', '华宁县', 'hua_ning_county');
INSERT INTO `address_county` VALUES ('2288', '266', '易门县', 'yi_men_county');
INSERT INTO `address_county` VALUES ('2289', '266', '峨山彝族自治县', 'e_shan_yi_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2290', '266', '新平彝族傣族自治县', 'xin_ping_yi_zu_dai_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2291', '266', '元江哈尼族彝族傣族自治县', 'yuan_jiang_ha_ni_zu_yi_zu_dai_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2292', '267', '隆阳区', 'long_yang_district');
INSERT INTO `address_county` VALUES ('2293', '267', '施甸县', 'shi_dian_county');
INSERT INTO `address_county` VALUES ('2294', '267', '腾冲县', 'teng_chong_county');
INSERT INTO `address_county` VALUES ('2295', '267', '龙陵县', 'long_ling_county');
INSERT INTO `address_county` VALUES ('2296', '267', '昌宁县', 'chang_ning_county');
INSERT INTO `address_county` VALUES ('2297', '268', '昭阳区', 'zhao_yang_district');
INSERT INTO `address_county` VALUES ('2298', '268', '鲁甸县', 'lu_dian_county');
INSERT INTO `address_county` VALUES ('2299', '268', '巧家县', 'qiao_jia_county');
INSERT INTO `address_county` VALUES ('2300', '268', '盐津县', 'yan_jin_county');
INSERT INTO `address_county` VALUES ('2301', '268', '大关县', 'da_guan_county');
INSERT INTO `address_county` VALUES ('2302', '268', '永善县', 'yong_shan_county');
INSERT INTO `address_county` VALUES ('2303', '268', '绥江县', 'sui_jiang_county');
INSERT INTO `address_county` VALUES ('2304', '268', '镇雄县', 'zhen_xiong_county');
INSERT INTO `address_county` VALUES ('2305', '268', '彝良县', 'yi_liang_county');
INSERT INTO `address_county` VALUES ('2306', '268', '威信县', 'wei_xin_county');
INSERT INTO `address_county` VALUES ('2307', '268', '水富县', 'shui_fu_county');
INSERT INTO `address_county` VALUES ('2308', '269', '古城区', 'gu_cheng_district');
INSERT INTO `address_county` VALUES ('2309', '269', '玉龙纳西族自治县', 'yu_long_na_xi_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2310', '269', '永胜县', 'yong_sheng_county');
INSERT INTO `address_county` VALUES ('2311', '269', '华坪县', 'hua_ping_county');
INSERT INTO `address_county` VALUES ('2312', '269', '宁蒗彝族自治县', 'ning_zuo_yi_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2313', '270', '思茅区', 'si_mao_district');
INSERT INTO `address_county` VALUES ('2314', '270', '宁洱哈尼族彝族自治县', 'ning_er_ha_ni_zu_yi_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2315', '270', '墨江哈尼族自治县', 'mo_jiang_ha_ni_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2316', '270', '景东彝族自治县', 'jing_dong_yi_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2317', '270', '景谷傣族彝族自治县', 'jing_gu_dai_zu_yi_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2318', '270', '镇沅彝族哈尼族拉祜族自治县', 'zhen_zuo_yi_zu_ha_ni_zu_la_zuo_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2319', '270', '江城哈尼族彝族自治县', 'jiang_cheng_ha_ni_zu_yi_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2320', '270', '孟连傣族拉祜族佤族自治县', 'meng_lian_dai_zu_la_zuo_zu_zuo_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2321', '270', '澜沧拉祜族自治县', 'lan_cang_la_zuo_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2322', '270', '西盟佤族自治县', 'xi_meng_zuo_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2323', '271', '临翔区', 'lin_xiang_district');
INSERT INTO `address_county` VALUES ('2324', '271', '凤庆县', 'feng_qing_county');
INSERT INTO `address_county` VALUES ('2325', '271', '云县', 'yun_county');
INSERT INTO `address_county` VALUES ('2326', '271', '永德县', 'yong_de_county');
INSERT INTO `address_county` VALUES ('2327', '271', '镇康县', 'zhen_kang_county');
INSERT INTO `address_county` VALUES ('2328', '271', '双江拉祜族佤族布朗族傣族自治县', 'shuang_jiang_la_zuo_zu_zuo_zu_bu_lang_zu_dai_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2329', '271', '耿马傣族佤族自治县', 'geng_ma_dai_zu_zuo_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2330', '271', '沧源佤族自治县', 'cang_yuan_zuo_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2331', '272', '楚雄市', 'chu_xiong_city');
INSERT INTO `address_county` VALUES ('2332', '272', '双柏县', 'shuang_bai_county');
INSERT INTO `address_county` VALUES ('2333', '272', '牟定县', 'mou_ding_county');
INSERT INTO `address_county` VALUES ('2334', '272', '南华县', 'nan_hua_county');
INSERT INTO `address_county` VALUES ('2335', '272', '姚安县', 'yao_an_county');
INSERT INTO `address_county` VALUES ('2336', '272', '大姚县', 'da_yao_county');
INSERT INTO `address_county` VALUES ('2337', '272', '永仁县', 'yong_ren_county');
INSERT INTO `address_county` VALUES ('2338', '272', '元谋县', 'yuan_mou_county');
INSERT INTO `address_county` VALUES ('2339', '272', '武定县', 'wu_ding_county');
INSERT INTO `address_county` VALUES ('2340', '272', '禄丰县', 'lu_feng_county');
INSERT INTO `address_county` VALUES ('2341', '273', '个旧市', 'ge_jiu_city');
INSERT INTO `address_county` VALUES ('2342', '273', '开远市', 'kai_yuan_city');
INSERT INTO `address_county` VALUES ('2343', '273', '蒙自市', 'meng_zi_city');
INSERT INTO `address_county` VALUES ('2344', '273', '屏边苗族自治县', 'ping_bian_miao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2345', '273', '建水县', 'jian_shui_county');
INSERT INTO `address_county` VALUES ('2346', '273', '石屏县', 'shi_ping_county');
INSERT INTO `address_county` VALUES ('2347', '273', '弥勒县', 'mi_le_county');
INSERT INTO `address_county` VALUES ('2348', '273', '泸西县', 'zuo_xi_county');
INSERT INTO `address_county` VALUES ('2349', '273', '元阳县', 'yuan_yang_county');
INSERT INTO `address_county` VALUES ('2350', '273', '红河县', 'hong_he_county');
INSERT INTO `address_county` VALUES ('2351', '273', '金平苗族瑶族傣族自治县', 'jin_ping_miao_zu_yao_zu_dai_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2352', '273', '绿春县', 'lv_chun_county');
INSERT INTO `address_county` VALUES ('2353', '273', '河口瑶族自治县', 'he_kou_yao_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2354', '274', '文山市', 'wen_shan_city');
INSERT INTO `address_county` VALUES ('2355', '274', '砚山县', 'yan_shan_county');
INSERT INTO `address_county` VALUES ('2356', '274', '西畴县', 'xi_chou_county');
INSERT INTO `address_county` VALUES ('2357', '274', '麻栗坡县', 'ma_li_po_county');
INSERT INTO `address_county` VALUES ('2358', '274', '马关县', 'ma_guan_county');
INSERT INTO `address_county` VALUES ('2359', '274', '丘北县', 'qiu_bei_county');
INSERT INTO `address_county` VALUES ('2360', '274', '广南县', 'guang_nan_county');
INSERT INTO `address_county` VALUES ('2361', '274', '富宁县', 'fu_ning_county');
INSERT INTO `address_county` VALUES ('2362', '275', '景洪市', 'jing_hong_city');
INSERT INTO `address_county` VALUES ('2363', '275', '勐海县', 'zuo_hai_county');
INSERT INTO `address_county` VALUES ('2364', '275', '勐腊县', 'zuo_la_county');
INSERT INTO `address_county` VALUES ('2365', '276', '大理市', 'da_li_city');
INSERT INTO `address_county` VALUES ('2366', '276', '漾濞彝族自治县', 'yang_zuo_yi_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2367', '276', '祥云县', 'xiang_yun_county');
INSERT INTO `address_county` VALUES ('2368', '276', '宾川县', 'bin_chuan_county');
INSERT INTO `address_county` VALUES ('2369', '276', '弥渡县', 'mi_du_county');
INSERT INTO `address_county` VALUES ('2370', '276', '南涧彝族自治县', 'nan_jian_yi_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2371', '276', '巍山彝族回族自治县', 'wei_shan_yi_zu_hui_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2372', '276', '永平县', 'yong_ping_county');
INSERT INTO `address_county` VALUES ('2373', '276', '云龙县', 'yun_long_county');
INSERT INTO `address_county` VALUES ('2374', '276', '洱源县', 'er_yuan_county');
INSERT INTO `address_county` VALUES ('2375', '276', '剑川县', 'jian_chuan_county');
INSERT INTO `address_county` VALUES ('2376', '276', '鹤庆县', 'he_qing_county');
INSERT INTO `address_county` VALUES ('2377', '277', '瑞丽市', 'rui_li_city');
INSERT INTO `address_county` VALUES ('2378', '277', '芒市', 'mang_city');
INSERT INTO `address_county` VALUES ('2379', '277', '梁河县', 'liang_he_county');
INSERT INTO `address_county` VALUES ('2380', '277', '盈江县', 'ying_jiang_county');
INSERT INTO `address_county` VALUES ('2381', '277', '陇川县', 'long_chuan_county');
INSERT INTO `address_county` VALUES ('2382', '278', '泸水县', 'zuo_shui_county');
INSERT INTO `address_county` VALUES ('2383', '278', '福贡县', 'fu_gong_county');
INSERT INTO `address_county` VALUES ('2384', '278', '贡山独龙族怒族自治县', 'gong_shan_du_long_zu_nu_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2385', '278', '兰坪白族普米族自治县', 'lan_ping_bai_zu_pu_mi_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2386', '279', '香格里拉县', 'xiang_ge_li_la_county');
INSERT INTO `address_county` VALUES ('2387', '279', '德钦县', 'de_qin_county');
INSERT INTO `address_county` VALUES ('2388', '279', '维西傈僳族自治县', 'wei_xi_li_su_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2389', '280', '城关区', 'cheng_guan_district');
INSERT INTO `address_county` VALUES ('2390', '280', '林周县', 'lin_zhou_county');
INSERT INTO `address_county` VALUES ('2391', '280', '当雄县', 'dang_xiong_county');
INSERT INTO `address_county` VALUES ('2392', '280', '尼木县', 'ni_mu_county');
INSERT INTO `address_county` VALUES ('2393', '280', '曲水县', 'qu_shui_county');
INSERT INTO `address_county` VALUES ('2394', '280', '堆龙德庆县', 'dui_long_de_qing_county');
INSERT INTO `address_county` VALUES ('2395', '280', '达孜县', 'da_zi_county');
INSERT INTO `address_county` VALUES ('2396', '280', '墨竹工卡县', 'mo_zhu_gong_ka_county');
INSERT INTO `address_county` VALUES ('2397', '281', '昌都县', 'chang_du_county');
INSERT INTO `address_county` VALUES ('2398', '281', '江达县', 'jiang_da_county');
INSERT INTO `address_county` VALUES ('2399', '281', '贡觉县', 'gong_jue_county');
INSERT INTO `address_county` VALUES ('2400', '281', '类乌齐县', 'lei_wu_qi_county');
INSERT INTO `address_county` VALUES ('2401', '281', '丁青县', 'ding_qing_county');
INSERT INTO `address_county` VALUES ('2402', '281', '察雅县', 'cha_ya_county');
INSERT INTO `address_county` VALUES ('2403', '281', '八宿县', 'ba_su_county');
INSERT INTO `address_county` VALUES ('2404', '281', '左贡县', 'zuo_gong_county');
INSERT INTO `address_county` VALUES ('2405', '281', '芒康县', 'mang_kang_county');
INSERT INTO `address_county` VALUES ('2406', '281', '洛隆县', 'luo_long_county');
INSERT INTO `address_county` VALUES ('2407', '281', '边坝县', 'bian_ba_county');
INSERT INTO `address_county` VALUES ('2408', '282', '乃东县', 'nai_dong_county');
INSERT INTO `address_county` VALUES ('2409', '282', '扎囊县', 'zha_nang_county');
INSERT INTO `address_county` VALUES ('2410', '282', '贡嘎县', 'gong_ga_county');
INSERT INTO `address_county` VALUES ('2411', '282', '桑日县', 'sang_ri_county');
INSERT INTO `address_county` VALUES ('2412', '282', '琼结县', 'qiong_jie_county');
INSERT INTO `address_county` VALUES ('2413', '282', '曲松县', 'qu_song_county');
INSERT INTO `address_county` VALUES ('2414', '282', '措美县', 'cuo_mei_county');
INSERT INTO `address_county` VALUES ('2415', '282', '洛扎县', 'luo_zha_county');
INSERT INTO `address_county` VALUES ('2416', '282', '加查县', 'jia_cha_county');
INSERT INTO `address_county` VALUES ('2417', '282', '隆子县', 'long_zi_county');
INSERT INTO `address_county` VALUES ('2418', '282', '错那县', 'cuo_na_county');
INSERT INTO `address_county` VALUES ('2419', '282', '浪卡子县', 'lang_ka_zi_county');
INSERT INTO `address_county` VALUES ('2420', '283', '日喀则市', 'ri_ka_ze_city');
INSERT INTO `address_county` VALUES ('2421', '283', '南木林县', 'nan_mu_lin_county');
INSERT INTO `address_county` VALUES ('2422', '283', '江孜县', 'jiang_zi_county');
INSERT INTO `address_county` VALUES ('2423', '283', '定日县', 'ding_ri_county');
INSERT INTO `address_county` VALUES ('2424', '283', '萨迦县', 'sa_zuo_county');
INSERT INTO `address_county` VALUES ('2425', '283', '拉孜县', 'la_zi_county');
INSERT INTO `address_county` VALUES ('2426', '283', '昂仁县', 'ang_ren_county');
INSERT INTO `address_county` VALUES ('2427', '283', '谢通门县', 'xie_tong_men_county');
INSERT INTO `address_county` VALUES ('2428', '283', '白朗县', 'bai_lang_county');
INSERT INTO `address_county` VALUES ('2429', '283', '仁布县', 'ren_bu_county');
INSERT INTO `address_county` VALUES ('2430', '283', '康马县', 'kang_ma_county');
INSERT INTO `address_county` VALUES ('2431', '283', '定结县', 'ding_jie_county');
INSERT INTO `address_county` VALUES ('2432', '283', '仲巴县', 'zhong_ba_county');
INSERT INTO `address_county` VALUES ('2433', '283', '亚东县', 'ya_dong_county');
INSERT INTO `address_county` VALUES ('2434', '283', '吉隆县', 'ji_long_county');
INSERT INTO `address_county` VALUES ('2435', '283', '聂拉木县', 'nie_la_mu_county');
INSERT INTO `address_county` VALUES ('2436', '283', '萨嘎县', 'sa_ga_county');
INSERT INTO `address_county` VALUES ('2437', '283', '岗巴县', 'gang_ba_county');
INSERT INTO `address_county` VALUES ('2438', '284', '那曲县', 'na_qu_county');
INSERT INTO `address_county` VALUES ('2439', '284', '嘉黎县', 'jia_li_county');
INSERT INTO `address_county` VALUES ('2440', '284', '比如县', 'bi_ru_county');
INSERT INTO `address_county` VALUES ('2441', '284', '聂荣县', 'nie_rong_county');
INSERT INTO `address_county` VALUES ('2442', '284', '安多县', 'an_duo_county');
INSERT INTO `address_county` VALUES ('2443', '284', '申扎县', 'shen_zha_county');
INSERT INTO `address_county` VALUES ('2444', '284', '索县', 'suo_county');
INSERT INTO `address_county` VALUES ('2445', '284', '班戈县', 'ban_ge_county');
INSERT INTO `address_county` VALUES ('2446', '284', '巴青县', 'ba_qing_county');
INSERT INTO `address_county` VALUES ('2447', '284', '尼玛县', 'ni_ma_county');
INSERT INTO `address_county` VALUES ('2448', '285', '普兰县', 'pu_lan_county');
INSERT INTO `address_county` VALUES ('2449', '285', '札达县', 'zha_da_county');
INSERT INTO `address_county` VALUES ('2450', '285', '噶尔县', 'ga_er_county');
INSERT INTO `address_county` VALUES ('2451', '285', '日土县', 'ri_tu_county');
INSERT INTO `address_county` VALUES ('2452', '285', '革吉县', 'ge_ji_county');
INSERT INTO `address_county` VALUES ('2453', '285', '改则县', 'gai_ze_county');
INSERT INTO `address_county` VALUES ('2454', '285', '措勤县', 'cuo_qin_county');
INSERT INTO `address_county` VALUES ('2455', '286', '林芝县', 'lin_zhi_county');
INSERT INTO `address_county` VALUES ('2456', '286', '工布江达县', 'gong_bu_jiang_da_county');
INSERT INTO `address_county` VALUES ('2457', '286', '米林县', 'mi_lin_county');
INSERT INTO `address_county` VALUES ('2458', '286', '墨脱县', 'mo_tuo_county');
INSERT INTO `address_county` VALUES ('2459', '286', '波密县', 'bo_mi_county');
INSERT INTO `address_county` VALUES ('2460', '286', '察隅县', 'cha_yu_county');
INSERT INTO `address_county` VALUES ('2461', '286', '朗县', 'lang_county');
INSERT INTO `address_county` VALUES ('2462', '287', '碑林区', 'bei_lin_district');
INSERT INTO `address_county` VALUES ('2463', '287', '莲湖区', 'lian_hu_district');
INSERT INTO `address_county` VALUES ('2464', '287', '灞桥区', 'zuo_qiao_district');
INSERT INTO `address_county` VALUES ('2465', '287', '未央区', 'wei_yang_district');
INSERT INTO `address_county` VALUES ('2466', '287', '雁塔区', 'yan_ta_district');
INSERT INTO `address_county` VALUES ('2467', '287', '阎良区', 'yan_liang_district');
INSERT INTO `address_county` VALUES ('2468', '287', '临潼区', 'lin_zuo_district');
INSERT INTO `address_county` VALUES ('2469', '287', '蓝田县', 'lan_tian_county');
INSERT INTO `address_county` VALUES ('2470', '287', '周至县', 'zhou_zhi_county');
INSERT INTO `address_county` VALUES ('2471', '287', '户县', 'hu_county');
INSERT INTO `address_county` VALUES ('2472', '287', '高陵县', 'gao_ling_county');
INSERT INTO `address_county` VALUES ('2473', '288', '王益区', 'wang_yi_district');
INSERT INTO `address_county` VALUES ('2474', '288', '印台区', 'yin_tai_district');
INSERT INTO `address_county` VALUES ('2475', '288', '耀州区', 'yao_zhou_district');
INSERT INTO `address_county` VALUES ('2476', '288', '宜君县', 'yi_jun_county');
INSERT INTO `address_county` VALUES ('2477', '289', '渭滨区', 'wei_bin_district');
INSERT INTO `address_county` VALUES ('2478', '289', '金台区', 'jin_tai_district');
INSERT INTO `address_county` VALUES ('2479', '289', '陈仓区', 'chen_cang_district');
INSERT INTO `address_county` VALUES ('2480', '289', '凤翔县', 'feng_xiang_county');
INSERT INTO `address_county` VALUES ('2481', '289', '岐山县', 'zuo_shan_county');
INSERT INTO `address_county` VALUES ('2482', '289', '扶风县', 'fu_feng_county');
INSERT INTO `address_county` VALUES ('2483', '289', '眉县', 'mei_county');
INSERT INTO `address_county` VALUES ('2484', '289', '陇县', 'long_county');
INSERT INTO `address_county` VALUES ('2485', '289', '千阳县', 'qian_yang_county');
INSERT INTO `address_county` VALUES ('2486', '289', '麟游县', 'zuo_you_county');
INSERT INTO `address_county` VALUES ('2487', '289', '凤县', 'feng_county');
INSERT INTO `address_county` VALUES ('2488', '289', '太白县', 'tai_bai_county');
INSERT INTO `address_county` VALUES ('2489', '290', '秦都区', 'qin_du_district');
INSERT INTO `address_county` VALUES ('2490', '290', '杨陵区', 'yang_ling_district');
INSERT INTO `address_county` VALUES ('2491', '290', '渭城区', 'wei_cheng_district');
INSERT INTO `address_county` VALUES ('2492', '290', '三原县', 'san_yuan_county');
INSERT INTO `address_county` VALUES ('2493', '290', '泾阳县', 'zuo_yang_county');
INSERT INTO `address_county` VALUES ('2494', '290', '乾县', 'qian_county');
INSERT INTO `address_county` VALUES ('2495', '290', '礼泉县', 'li_quan_county');
INSERT INTO `address_county` VALUES ('2496', '290', '永寿县', 'yong_shou_county');
INSERT INTO `address_county` VALUES ('2497', '290', '彬县', 'bin_county');
INSERT INTO `address_county` VALUES ('2498', '290', '长武县', 'chang_wu_county');
INSERT INTO `address_county` VALUES ('2499', '290', '旬邑县', 'xun_yi_county');
INSERT INTO `address_county` VALUES ('2500', '290', '淳化县', 'chun_hua_county');
INSERT INTO `address_county` VALUES ('2501', '290', '武功县', 'wu_gong_county');
INSERT INTO `address_county` VALUES ('2502', '290', '兴平市', 'xing_ping_city');
INSERT INTO `address_county` VALUES ('2503', '291', '临渭区', 'lin_wei_district');
INSERT INTO `address_county` VALUES ('2504', '291', '华县', 'hua_county');
INSERT INTO `address_county` VALUES ('2505', '291', '潼关县', 'zuo_guan_county');
INSERT INTO `address_county` VALUES ('2506', '291', '大荔县', 'da_li_county');
INSERT INTO `address_county` VALUES ('2507', '291', '合阳县', 'he_yang_county');
INSERT INTO `address_county` VALUES ('2508', '291', '澄城县', 'cheng_cheng_county');
INSERT INTO `address_county` VALUES ('2509', '291', '蒲城县', 'pu_cheng_county');
INSERT INTO `address_county` VALUES ('2510', '291', '白水县', 'bai_shui_county');
INSERT INTO `address_county` VALUES ('2511', '291', '富平县', 'fu_ping_county');
INSERT INTO `address_county` VALUES ('2512', '291', '韩城市', 'han_cheng_city');
INSERT INTO `address_county` VALUES ('2513', '291', '华阴市', 'hua_yin_city');
INSERT INTO `address_county` VALUES ('2514', '292', '宝塔区', 'bao_ta_district');
INSERT INTO `address_county` VALUES ('2515', '292', '延长县', 'yan_chang_county');
INSERT INTO `address_county` VALUES ('2516', '292', '延川县', 'yan_chuan_county');
INSERT INTO `address_county` VALUES ('2517', '292', '子长县', 'zi_chang_county');
INSERT INTO `address_county` VALUES ('2518', '292', '安塞县', 'an_sai_county');
INSERT INTO `address_county` VALUES ('2519', '292', '志丹县', 'zhi_dan_county');
INSERT INTO `address_county` VALUES ('2520', '292', '吴起县', 'wu_qi_county');
INSERT INTO `address_county` VALUES ('2521', '292', '甘泉县', 'gan_quan_county');
INSERT INTO `address_county` VALUES ('2522', '292', '富县', 'fu_county');
INSERT INTO `address_county` VALUES ('2523', '292', '洛川县', 'luo_chuan_county');
INSERT INTO `address_county` VALUES ('2524', '292', '宜川县', 'yi_chuan_county');
INSERT INTO `address_county` VALUES ('2525', '292', '黄龙县', 'huang_long_county');
INSERT INTO `address_county` VALUES ('2526', '292', '黄陵县', 'huang_ling_county');
INSERT INTO `address_county` VALUES ('2527', '293', '汉台区', 'han_tai_district');
INSERT INTO `address_county` VALUES ('2528', '293', '南郑县', 'nan_zheng_county');
INSERT INTO `address_county` VALUES ('2529', '293', '城固县', 'cheng_gu_county');
INSERT INTO `address_county` VALUES ('2530', '293', '洋县', 'yang_county');
INSERT INTO `address_county` VALUES ('2531', '293', '西乡县', 'xi_xiang_county');
INSERT INTO `address_county` VALUES ('2532', '293', '勉县', 'mian_county');
INSERT INTO `address_county` VALUES ('2533', '293', '宁强县', 'ning_qiang_county');
INSERT INTO `address_county` VALUES ('2534', '293', '略阳县', 'lue_yang_county');
INSERT INTO `address_county` VALUES ('2535', '293', '镇巴县', 'zhen_ba_county');
INSERT INTO `address_county` VALUES ('2536', '293', '留坝县', 'liu_ba_county');
INSERT INTO `address_county` VALUES ('2537', '293', '佛坪县', 'fo_ping_county');
INSERT INTO `address_county` VALUES ('2538', '294', '榆阳区', 'yu_yang_district');
INSERT INTO `address_county` VALUES ('2539', '294', '神木县', 'shen_mu_county');
INSERT INTO `address_county` VALUES ('2540', '294', '府谷县', 'fu_gu_county');
INSERT INTO `address_county` VALUES ('2541', '294', '横山县', 'heng_shan_county');
INSERT INTO `address_county` VALUES ('2542', '294', '靖边县', 'jing_bian_county');
INSERT INTO `address_county` VALUES ('2543', '294', '定边县', 'ding_bian_county');
INSERT INTO `address_county` VALUES ('2544', '294', '绥德县', 'sui_de_county');
INSERT INTO `address_county` VALUES ('2545', '294', '米脂县', 'mi_zhi_county');
INSERT INTO `address_county` VALUES ('2546', '294', '佳县', 'jia_county');
INSERT INTO `address_county` VALUES ('2547', '294', '吴堡县', 'wu_bao_county');
INSERT INTO `address_county` VALUES ('2548', '294', '清涧县', 'qing_jian_county');
INSERT INTO `address_county` VALUES ('2549', '294', '子洲县', 'zi_zhou_county');
INSERT INTO `address_county` VALUES ('2550', '295', '汉滨区', 'han_bin_district');
INSERT INTO `address_county` VALUES ('2551', '295', '汉阴县', 'han_yin_county');
INSERT INTO `address_county` VALUES ('2552', '295', '石泉县', 'shi_quan_county');
INSERT INTO `address_county` VALUES ('2553', '295', '宁陕县', 'ning_shan_county');
INSERT INTO `address_county` VALUES ('2554', '295', '紫阳县', 'zi_yang_county');
INSERT INTO `address_county` VALUES ('2555', '295', '岚皋县', 'zuo_gao_county');
INSERT INTO `address_county` VALUES ('2556', '295', '平利县', 'ping_li_county');
INSERT INTO `address_county` VALUES ('2557', '295', '镇坪县', 'zhen_ping_county');
INSERT INTO `address_county` VALUES ('2558', '295', '旬阳县', 'xun_yang_county');
INSERT INTO `address_county` VALUES ('2559', '295', '白河县', 'bai_he_county');
INSERT INTO `address_county` VALUES ('2560', '296', '商州区', 'shang_zhou_district');
INSERT INTO `address_county` VALUES ('2561', '296', '洛南县', 'luo_nan_county');
INSERT INTO `address_county` VALUES ('2562', '296', '丹凤县', 'dan_feng_county');
INSERT INTO `address_county` VALUES ('2563', '296', '商南县', 'shang_nan_county');
INSERT INTO `address_county` VALUES ('2564', '296', '山阳县', 'shan_yang_county');
INSERT INTO `address_county` VALUES ('2565', '296', '镇安县', 'zhen_an_county');
INSERT INTO `address_county` VALUES ('2566', '296', '柞水县', 'zuo_shui_county');
INSERT INTO `address_county` VALUES ('2567', '297', '七里河区', 'qi_li_he_district');
INSERT INTO `address_county` VALUES ('2568', '297', '西固区', 'xi_gu_district');
INSERT INTO `address_county` VALUES ('2569', '297', '安宁区', 'an_ning_district');
INSERT INTO `address_county` VALUES ('2570', '297', '红古区', 'hong_gu_district');
INSERT INTO `address_county` VALUES ('2571', '297', '永登县', 'yong_deng_county');
INSERT INTO `address_county` VALUES ('2572', '297', '皋兰县', 'gao_lan_county');
INSERT INTO `address_county` VALUES ('2573', '297', '榆中县', 'yu_zhong_county');
INSERT INTO `address_county` VALUES ('2574', '299', '金川区', 'jin_chuan_district');
INSERT INTO `address_county` VALUES ('2575', '299', '永昌县', 'yong_chang_county');
INSERT INTO `address_county` VALUES ('2576', '300', '白银区', 'bai_yin_district');
INSERT INTO `address_county` VALUES ('2577', '300', '平川区', 'ping_chuan_district');
INSERT INTO `address_county` VALUES ('2578', '300', '靖远县', 'jing_yuan_county');
INSERT INTO `address_county` VALUES ('2579', '300', '会宁县', 'hui_ning_county');
INSERT INTO `address_county` VALUES ('2580', '300', '景泰县', 'jing_tai_county');
INSERT INTO `address_county` VALUES ('2581', '301', '秦州区', 'qin_zhou_district');
INSERT INTO `address_county` VALUES ('2582', '301', '麦积区', 'mai_ji_district');
INSERT INTO `address_county` VALUES ('2583', '301', '清水县', 'qing_shui_county');
INSERT INTO `address_county` VALUES ('2584', '301', '秦安县', 'qin_an_county');
INSERT INTO `address_county` VALUES ('2585', '301', '甘谷县', 'gan_gu_county');
INSERT INTO `address_county` VALUES ('2586', '301', '武山县', 'wu_shan_county');
INSERT INTO `address_county` VALUES ('2587', '301', '张家川回族自治县', 'zhang_jia_chuan_hui_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2588', '302', '凉州区', 'liang_zhou_district');
INSERT INTO `address_county` VALUES ('2589', '302', '民勤县', 'min_qin_county');
INSERT INTO `address_county` VALUES ('2590', '302', '古浪县', 'gu_lang_county');
INSERT INTO `address_county` VALUES ('2591', '302', '天祝藏族自治县', 'tian_zhu_cang_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2592', '303', '甘州区', 'gan_zhou_district');
INSERT INTO `address_county` VALUES ('2593', '303', '肃南裕固族自治县', 'su_nan_yu_gu_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2594', '303', '民乐县', 'min_le_county');
INSERT INTO `address_county` VALUES ('2595', '303', '临泽县', 'lin_ze_county');
INSERT INTO `address_county` VALUES ('2596', '303', '高台县', 'gao_tai_county');
INSERT INTO `address_county` VALUES ('2597', '303', '山丹县', 'shan_dan_county');
INSERT INTO `address_county` VALUES ('2598', '304', '崆峒区', 'zuo_zuo_district');
INSERT INTO `address_county` VALUES ('2599', '304', '泾川县', 'zuo_chuan_county');
INSERT INTO `address_county` VALUES ('2600', '304', '灵台县', 'ling_tai_county');
INSERT INTO `address_county` VALUES ('2601', '304', '崇信县', 'chong_xin_county');
INSERT INTO `address_county` VALUES ('2602', '304', '华亭县', 'hua_ting_county');
INSERT INTO `address_county` VALUES ('2603', '304', '庄浪县', 'zhuang_lang_county');
INSERT INTO `address_county` VALUES ('2604', '304', '静宁县', 'jing_ning_county');
INSERT INTO `address_county` VALUES ('2605', '305', '肃州区', 'su_zhou_district');
INSERT INTO `address_county` VALUES ('2606', '305', '金塔县', 'jin_ta_county');
INSERT INTO `address_county` VALUES ('2607', '305', '瓜州县', 'gua_zhou_county');
INSERT INTO `address_county` VALUES ('2608', '305', '肃北蒙古族自治县', 'su_bei_meng_gu_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2609', '305', '阿克塞哈萨克族自治县', 'a_ke_sai_ha_sa_ke_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2610', '305', '玉门市', 'yu_men_city');
INSERT INTO `address_county` VALUES ('2611', '305', '敦煌市', 'dun_huang_city');
INSERT INTO `address_county` VALUES ('2612', '306', '西峰区', 'xi_feng_district');
INSERT INTO `address_county` VALUES ('2613', '306', '庆城县', 'qing_cheng_county');
INSERT INTO `address_county` VALUES ('2614', '306', '环县', 'huan_county');
INSERT INTO `address_county` VALUES ('2615', '306', '华池县', 'hua_chi_county');
INSERT INTO `address_county` VALUES ('2616', '306', '合水县', 'he_shui_county');
INSERT INTO `address_county` VALUES ('2617', '306', '正宁县', 'zheng_ning_county');
INSERT INTO `address_county` VALUES ('2618', '306', '宁县', 'ning_county');
INSERT INTO `address_county` VALUES ('2619', '306', '镇原县', 'zhen_yuan_county');
INSERT INTO `address_county` VALUES ('2620', '307', '安定区', 'an_ding_district');
INSERT INTO `address_county` VALUES ('2621', '307', '通渭县', 'tong_wei_county');
INSERT INTO `address_county` VALUES ('2622', '307', '陇西县', 'long_xi_county');
INSERT INTO `address_county` VALUES ('2623', '307', '渭源县', 'wei_yuan_county');
INSERT INTO `address_county` VALUES ('2624', '307', '临洮县', 'lin_zuo_county');
INSERT INTO `address_county` VALUES ('2625', '307', '漳县', 'zhang_county');
INSERT INTO `address_county` VALUES ('2626', '307', '岷县', 'zuo_county');
INSERT INTO `address_county` VALUES ('2627', '308', '武都区', 'wu_du_district');
INSERT INTO `address_county` VALUES ('2628', '308', '成县', 'cheng_county');
INSERT INTO `address_county` VALUES ('2629', '308', '文县', 'wen_county');
INSERT INTO `address_county` VALUES ('2630', '308', '宕昌县', 'zuo_chang_county');
INSERT INTO `address_county` VALUES ('2631', '308', '康县', 'kang_county');
INSERT INTO `address_county` VALUES ('2632', '308', '西和县', 'xi_he_county');
INSERT INTO `address_county` VALUES ('2633', '308', '礼县', 'li_county');
INSERT INTO `address_county` VALUES ('2634', '308', '徽县', 'hui_county');
INSERT INTO `address_county` VALUES ('2635', '308', '两当县', 'liang_dang_county');
INSERT INTO `address_county` VALUES ('2636', '309', '临夏市', 'lin_xia_city');
INSERT INTO `address_county` VALUES ('2637', '309', '临夏县', 'lin_xia_county');
INSERT INTO `address_county` VALUES ('2638', '309', '康乐县', 'kang_le_county');
INSERT INTO `address_county` VALUES ('2639', '309', '永靖县', 'yong_jing_county');
INSERT INTO `address_county` VALUES ('2640', '309', '广河县', 'guang_he_county');
INSERT INTO `address_county` VALUES ('2641', '309', '和政县', 'he_zheng_county');
INSERT INTO `address_county` VALUES ('2642', '309', '东乡族自治县', 'dong_xiang_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2643', '309', '积石山保安族东乡族撒拉族自治县', 'ji_shi_shan_bao_an_zu_dong_xiang_zu_sa_la_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2644', '310', '合作市', 'he_zuo_city');
INSERT INTO `address_county` VALUES ('2645', '310', '临潭县', 'lin_tan_county');
INSERT INTO `address_county` VALUES ('2646', '310', '卓尼县', 'zhuo_ni_county');
INSERT INTO `address_county` VALUES ('2647', '310', '舟曲县', 'zhou_qu_county');
INSERT INTO `address_county` VALUES ('2648', '310', '迭部县', 'die_bu_county');
INSERT INTO `address_county` VALUES ('2649', '310', '玛曲县', 'ma_qu_county');
INSERT INTO `address_county` VALUES ('2650', '310', '碌曲县', 'lu_qu_county');
INSERT INTO `address_county` VALUES ('2651', '310', '夏河县', 'xia_he_county');
INSERT INTO `address_county` VALUES ('2652', '311', '城东区', 'cheng_dong_district');
INSERT INTO `address_county` VALUES ('2653', '311', '城西区', 'cheng_xi_district');
INSERT INTO `address_county` VALUES ('2654', '311', '城北区', 'cheng_bei_district');
INSERT INTO `address_county` VALUES ('2655', '311', '大通回族土族自治县', 'da_tong_hui_zu_tu_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2656', '311', '湟中县', 'zuo_zhong_county');
INSERT INTO `address_county` VALUES ('2657', '311', '湟源县', 'zuo_yuan_county');
INSERT INTO `address_county` VALUES ('2658', '312', '平安县', 'ping_an_county');
INSERT INTO `address_county` VALUES ('2659', '312', '民和回族土族自治县', 'min_he_hui_zu_tu_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2660', '312', '乐都县', 'le_du_county');
INSERT INTO `address_county` VALUES ('2661', '312', '互助土族自治县', 'hu_zhu_tu_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2662', '312', '化隆回族自治县', 'hua_long_hui_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2663', '312', '循化撒拉族自治县', 'xun_hua_sa_la_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2664', '313', '门源回族自治县', 'men_yuan_hui_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2665', '313', '祁连县', 'qi_lian_county');
INSERT INTO `address_county` VALUES ('2666', '313', '海晏县', 'hai_zuo_county');
INSERT INTO `address_county` VALUES ('2667', '313', '刚察县', 'gang_cha_county');
INSERT INTO `address_county` VALUES ('2668', '314', '同仁县', 'tong_ren_county');
INSERT INTO `address_county` VALUES ('2669', '314', '尖扎县', 'jian_zha_county');
INSERT INTO `address_county` VALUES ('2670', '314', '泽库县', 'ze_ku_county');
INSERT INTO `address_county` VALUES ('2671', '314', '河南蒙古族自治县', 'he_nan_meng_gu_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2672', '315', '共和县', 'gong_he_county');
INSERT INTO `address_county` VALUES ('2673', '315', '同德县', 'tong_de_county');
INSERT INTO `address_county` VALUES ('2674', '315', '贵德县', 'gui_de_county');
INSERT INTO `address_county` VALUES ('2675', '315', '兴海县', 'xing_hai_county');
INSERT INTO `address_county` VALUES ('2676', '315', '贵南县', 'gui_nan_county');
INSERT INTO `address_county` VALUES ('2677', '316', '玛沁县', 'ma_qin_county');
INSERT INTO `address_county` VALUES ('2678', '316', '班玛县', 'ban_ma_county');
INSERT INTO `address_county` VALUES ('2679', '316', '甘德县', 'gan_de_county');
INSERT INTO `address_county` VALUES ('2680', '316', '达日县', 'da_ri_county');
INSERT INTO `address_county` VALUES ('2681', '316', '久治县', 'jiu_zhi_county');
INSERT INTO `address_county` VALUES ('2682', '316', '玛多县', 'ma_duo_county');
INSERT INTO `address_county` VALUES ('2683', '317', '玉树县', 'yu_shu_county');
INSERT INTO `address_county` VALUES ('2684', '317', '杂多县', 'za_duo_county');
INSERT INTO `address_county` VALUES ('2685', '317', '称多县', 'cheng_duo_county');
INSERT INTO `address_county` VALUES ('2686', '317', '治多县', 'zhi_duo_county');
INSERT INTO `address_county` VALUES ('2687', '317', '囊谦县', 'nang_qian_county');
INSERT INTO `address_county` VALUES ('2688', '317', '曲麻莱县', 'qu_ma_lai_county');
INSERT INTO `address_county` VALUES ('2689', '318', '格尔木市', 'ge_er_mu_city');
INSERT INTO `address_county` VALUES ('2690', '318', '德令哈市', 'de_ling_ha_city');
INSERT INTO `address_county` VALUES ('2691', '318', '乌兰县', 'wu_lan_county');
INSERT INTO `address_county` VALUES ('2692', '318', '都兰县', 'du_lan_county');
INSERT INTO `address_county` VALUES ('2693', '318', '天峻县', 'tian_jun_county');
INSERT INTO `address_county` VALUES ('2694', '319', '兴庆区', 'xing_qing_district');
INSERT INTO `address_county` VALUES ('2695', '319', '西夏区', 'xi_xia_district');
INSERT INTO `address_county` VALUES ('2696', '319', '金凤区', 'jin_feng_district');
INSERT INTO `address_county` VALUES ('2697', '319', '永宁县', 'yong_ning_county');
INSERT INTO `address_county` VALUES ('2698', '319', '贺兰县', 'he_lan_county');
INSERT INTO `address_county` VALUES ('2699', '319', '灵武市', 'ling_wu_city');
INSERT INTO `address_county` VALUES ('2700', '320', '大武口区', 'da_wu_kou_district');
INSERT INTO `address_county` VALUES ('2701', '320', '惠农区', 'hui_nong_district');
INSERT INTO `address_county` VALUES ('2702', '320', '平罗县', 'ping_luo_county');
INSERT INTO `address_county` VALUES ('2703', '321', '利通区', 'li_tong_district');
INSERT INTO `address_county` VALUES ('2704', '321', '红寺堡区', 'hong_si_bao_district');
INSERT INTO `address_county` VALUES ('2705', '321', '盐池县', 'yan_chi_county');
INSERT INTO `address_county` VALUES ('2706', '321', '同心县', 'tong_xin_county');
INSERT INTO `address_county` VALUES ('2707', '321', '青铜峡市', 'qing_tong_xia_city');
INSERT INTO `address_county` VALUES ('2708', '322', '原州区', 'yuan_zhou_district');
INSERT INTO `address_county` VALUES ('2709', '322', '西吉县', 'xi_ji_county');
INSERT INTO `address_county` VALUES ('2710', '322', '隆德县', 'long_de_county');
INSERT INTO `address_county` VALUES ('2711', '322', '泾源县', 'zuo_yuan_county');
INSERT INTO `address_county` VALUES ('2712', '322', '彭阳县', 'peng_yang_county');
INSERT INTO `address_county` VALUES ('2713', '323', '沙坡头区', 'sha_po_tou_district');
INSERT INTO `address_county` VALUES ('2714', '323', '中宁县', 'zhong_ning_county');
INSERT INTO `address_county` VALUES ('2715', '323', '海原县', 'hai_yuan_county');
INSERT INTO `address_county` VALUES ('2716', '324', '天山区', 'tian_shan_district');
INSERT INTO `address_county` VALUES ('2717', '324', '沙依巴克区', 'sha_yi_ba_ke_district');
INSERT INTO `address_county` VALUES ('2718', '324', '水磨沟区', 'shui_mo_gou_district');
INSERT INTO `address_county` VALUES ('2719', '324', '头屯河区', 'tou_tun_he_district');
INSERT INTO `address_county` VALUES ('2720', '324', '达坂城区', 'da_zuo_cheng_district');
INSERT INTO `address_county` VALUES ('2721', '324', '米东区', 'mi_dong_district');
INSERT INTO `address_county` VALUES ('2722', '324', '乌鲁木齐县', 'wu_lu_mu_qi_county');
INSERT INTO `address_county` VALUES ('2723', '325', '独山子区', 'du_shan_zi_district');
INSERT INTO `address_county` VALUES ('2724', '325', '克拉玛依区', 'ke_la_ma_yi_district');
INSERT INTO `address_county` VALUES ('2725', '325', '白碱滩区', 'bai_jian_tan_district');
INSERT INTO `address_county` VALUES ('2726', '325', '乌尔禾区', 'wu_er_he_district');
INSERT INTO `address_county` VALUES ('2727', '326', '吐鲁番市', 'tu_lu_fan_city');
INSERT INTO `address_county` VALUES ('2728', '326', '鄯善县', 'zuo_shan_county');
INSERT INTO `address_county` VALUES ('2729', '326', '托克逊县', 'tuo_ke_xun_county');
INSERT INTO `address_county` VALUES ('2730', '327', '哈密市', 'ha_mi_city');
INSERT INTO `address_county` VALUES ('2731', '327', '巴里坤哈萨克自治县', 'ba_li_kun_ha_sa_ke_autonomous_county');
INSERT INTO `address_county` VALUES ('2732', '327', '伊吾县', 'yi_wu_county');
INSERT INTO `address_county` VALUES ('2733', '328', '昌吉市', 'chang_ji_city');
INSERT INTO `address_county` VALUES ('2734', '328', '阜康市', 'fu_kang_city');
INSERT INTO `address_county` VALUES ('2735', '328', '呼图壁县', 'hu_tu_bi_county');
INSERT INTO `address_county` VALUES ('2736', '328', '玛纳斯县', 'ma_na_si_county');
INSERT INTO `address_county` VALUES ('2737', '328', '奇台县', 'qi_tai_county');
INSERT INTO `address_county` VALUES ('2738', '328', '吉木萨尔县', 'ji_mu_sa_er_county');
INSERT INTO `address_county` VALUES ('2739', '328', '木垒哈萨克自治县', 'mu_lei_ha_sa_ke_autonomous_county');
INSERT INTO `address_county` VALUES ('2740', '329', '博乐市', 'bo_le_city');
INSERT INTO `address_county` VALUES ('2741', '329', '精河县', 'jing_he_county');
INSERT INTO `address_county` VALUES ('2742', '329', '温泉县', 'wen_quan_county');
INSERT INTO `address_county` VALUES ('2743', '330', '库尔勒市', 'ku_er_le_city');
INSERT INTO `address_county` VALUES ('2744', '330', '轮台县', 'lun_tai_county');
INSERT INTO `address_county` VALUES ('2745', '330', '尉犁县', 'wei_li_county');
INSERT INTO `address_county` VALUES ('2746', '330', '若羌县', 'ruo_qiang_county');
INSERT INTO `address_county` VALUES ('2747', '330', '且末县', 'qie_mo_county');
INSERT INTO `address_county` VALUES ('2748', '330', '焉耆回族自治县', 'yan_zuo_hui_zu_autonomous_county');
INSERT INTO `address_county` VALUES ('2749', '330', '和静县', 'he_jing_county');
INSERT INTO `address_county` VALUES ('2750', '330', '和硕县', 'he_shuo_county');
INSERT INTO `address_county` VALUES ('2751', '330', '博湖县', 'bo_hu_county');
INSERT INTO `address_county` VALUES ('2752', '331', '阿克苏市', 'a_ke_su_city');
INSERT INTO `address_county` VALUES ('2753', '331', '温宿县', 'wen_su_county');
INSERT INTO `address_county` VALUES ('2754', '331', '库车县', 'ku_che_county');
INSERT INTO `address_county` VALUES ('2755', '331', '沙雅县', 'sha_ya_county');
INSERT INTO `address_county` VALUES ('2756', '331', '新和县', 'xin_he_county');
INSERT INTO `address_county` VALUES ('2757', '331', '拜城县', 'bai_cheng_county');
INSERT INTO `address_county` VALUES ('2758', '331', '乌什县', 'wu_shi_county');
INSERT INTO `address_county` VALUES ('2759', '331', '阿瓦提县', 'a_wa_ti_county');
INSERT INTO `address_county` VALUES ('2760', '331', '柯坪县', 'ke_ping_county');
INSERT INTO `address_county` VALUES ('2761', '332', '阿图什市', 'a_tu_shi_city');
INSERT INTO `address_county` VALUES ('2762', '332', '阿克陶县', 'a_ke_tao_county');
INSERT INTO `address_county` VALUES ('2763', '332', '阿合奇县', 'a_he_qi_county');
INSERT INTO `address_county` VALUES ('2764', '332', '乌恰县', 'wu_qia_county');
INSERT INTO `address_county` VALUES ('2765', '333', '喀什市', 'ka_shi_city');
INSERT INTO `address_county` VALUES ('2766', '333', '疏附县', 'shu_fu_county');
INSERT INTO `address_county` VALUES ('2767', '333', '疏勒县', 'shu_le_county');
INSERT INTO `address_county` VALUES ('2768', '333', '英吉沙县', 'ying_ji_sha_county');
INSERT INTO `address_county` VALUES ('2769', '333', '泽普县', 'ze_pu_county');
INSERT INTO `address_county` VALUES ('2770', '333', '莎车县', 'sha_che_county');
INSERT INTO `address_county` VALUES ('2771', '333', '叶城县', 'ye_cheng_county');
INSERT INTO `address_county` VALUES ('2772', '333', '麦盖提县', 'mai_gai_ti_county');
INSERT INTO `address_county` VALUES ('2773', '333', '岳普湖县', 'yue_pu_hu_county');
INSERT INTO `address_county` VALUES ('2774', '333', '伽师县', 'zuo_shi_county');
INSERT INTO `address_county` VALUES ('2775', '333', '巴楚县', 'ba_chu_county');
INSERT INTO `address_county` VALUES ('2776', '333', '塔什库尔干塔吉克自治县', 'ta_shi_ku_er_gan_ta_ji_ke_autonomous_county');
INSERT INTO `address_county` VALUES ('2777', '334', '和田市', 'he_tian_city');
INSERT INTO `address_county` VALUES ('2778', '334', '和田县', 'he_tian_county');
INSERT INTO `address_county` VALUES ('2779', '334', '墨玉县', 'mo_yu_county');
INSERT INTO `address_county` VALUES ('2780', '334', '皮山县', 'pi_shan_county');
INSERT INTO `address_county` VALUES ('2781', '334', '洛浦县', 'luo_pu_county');
INSERT INTO `address_county` VALUES ('2782', '334', '策勒县', 'ce_le_county');
INSERT INTO `address_county` VALUES ('2783', '334', '于田县', 'yu_tian_county');
INSERT INTO `address_county` VALUES ('2784', '334', '民丰县', 'min_feng_county');
INSERT INTO `address_county` VALUES ('2785', '335', '伊宁市', 'yi_ning_city');
INSERT INTO `address_county` VALUES ('2786', '335', '奎屯市', 'kui_tun_city');
INSERT INTO `address_county` VALUES ('2787', '335', '伊宁县', 'yi_ning_county');
INSERT INTO `address_county` VALUES ('2788', '335', '察布查尔锡伯自治县', 'cha_bu_cha_er_xi_bo_autonomous_county');
INSERT INTO `address_county` VALUES ('2789', '335', '霍城县', 'huo_cheng_county');
INSERT INTO `address_county` VALUES ('2790', '335', '巩留县', 'gong_liu_county');
INSERT INTO `address_county` VALUES ('2791', '335', '新源县', 'xin_yuan_county');
INSERT INTO `address_county` VALUES ('2792', '335', '昭苏县', 'zhao_su_county');
INSERT INTO `address_county` VALUES ('2793', '335', '特克斯县', 'te_ke_si_county');
INSERT INTO `address_county` VALUES ('2794', '335', '尼勒克县', 'ni_le_ke_county');
INSERT INTO `address_county` VALUES ('2795', '336', '塔城市', 'ta_cheng_city');
INSERT INTO `address_county` VALUES ('2796', '336', '乌苏市', 'wu_su_city');
INSERT INTO `address_county` VALUES ('2797', '336', '额敏县', 'e_min_county');
INSERT INTO `address_county` VALUES ('2798', '336', '沙湾县', 'sha_wan_county');
INSERT INTO `address_county` VALUES ('2799', '336', '托里县', 'tuo_li_county');
INSERT INTO `address_county` VALUES ('2800', '336', '裕民县', 'yu_min_county');
INSERT INTO `address_county` VALUES ('2801', '336', '和布克赛尔蒙古自治县', 'he_bu_ke_sai_er_meng_gu_autonomous_county');
INSERT INTO `address_county` VALUES ('2802', '337', '阿勒泰市', 'a_le_tai_city');
INSERT INTO `address_county` VALUES ('2803', '337', '布尔津县', 'bu_er_jin_county');
INSERT INTO `address_county` VALUES ('2804', '337', '富蕴县', 'fu_yun_county');
INSERT INTO `address_county` VALUES ('2805', '337', '福海县', 'fu_hai_county');
INSERT INTO `address_county` VALUES ('2806', '337', '哈巴河县', 'ha_ba_he_county');
INSERT INTO `address_county` VALUES ('2807', '337', '青河县', 'qing_he_county');
INSERT INTO `address_county` VALUES ('2808', '337', '吉木乃县', 'ji_mu_nai_county');
INSERT INTO `address_county` VALUES ('2809', '338', '石河子市', 'shi_he_zi_city');
INSERT INTO `address_county` VALUES ('2810', '338', '阿拉尔市', 'a_la_er_city');
INSERT INTO `address_county` VALUES ('2811', '338', '图木舒克市', 'tu_mu_shu_ke_city');
INSERT INTO `address_county` VALUES ('2812', '338', '五家渠市', 'wu_jia_qu_city');

-- ----------------------------
-- Table structure for `address_province`
-- ----------------------------
DROP TABLE IF EXISTS `address_province`;
CREATE TABLE `address_province` (
  `id` int(5) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `en_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address_province
-- ----------------------------
INSERT INTO `address_province` VALUES ('1', '北京市', 'bei_jing');
INSERT INTO `address_province` VALUES ('2', '天津市', 'tian_jin');
INSERT INTO `address_province` VALUES ('3', '河北省', 'he_bei');
INSERT INTO `address_province` VALUES ('4', '山西省', 'shan_xi');
INSERT INTO `address_province` VALUES ('5', '内蒙古自治区', 'nei_meng_gu_autonomous_region');
INSERT INTO `address_province` VALUES ('6', '辽宁省', 'liao_ning');
INSERT INTO `address_province` VALUES ('7', '吉林省', 'ji_lin');
INSERT INTO `address_province` VALUES ('8', '黑龙江省', 'hei_long_jiang');
INSERT INTO `address_province` VALUES ('9', '上海市', 'shang_hai');
INSERT INTO `address_province` VALUES ('10', '江苏省', 'jiang_su');
INSERT INTO `address_province` VALUES ('11', '浙江省', 'zhe_jiang');
INSERT INTO `address_province` VALUES ('12', '安徽省', 'an_hui');
INSERT INTO `address_province` VALUES ('13', '福建省', 'fu_jian');
INSERT INTO `address_province` VALUES ('14', '江西省', 'jiang_xi');
INSERT INTO `address_province` VALUES ('15', '山东省', 'shan_dong');
INSERT INTO `address_province` VALUES ('16', '河南省', 'he_nan');
INSERT INTO `address_province` VALUES ('17', '湖北省', 'hu_bei');
INSERT INTO `address_province` VALUES ('18', '湖南省', 'hu_nan');
INSERT INTO `address_province` VALUES ('19', '广东省', 'guang_dong');
INSERT INTO `address_province` VALUES ('20', '广西壮族自治区', 'guang_xi_zhuang_zu_autonomous_region\r\n');
INSERT INTO `address_province` VALUES ('21', '海南省', 'hai_nan');
INSERT INTO `address_province` VALUES ('22', '重庆市', 'zhong_qing');
INSERT INTO `address_province` VALUES ('23', '四川省', 'si_chuan');
INSERT INTO `address_province` VALUES ('24', '贵州省', 'gui_zhou');
INSERT INTO `address_province` VALUES ('25', '云南省', 'yun_nan');
INSERT INTO `address_province` VALUES ('26', '西藏自治区', 'xi_cang_autonomous_region');
INSERT INTO `address_province` VALUES ('27', '陕西省', 'shen_xi');
INSERT INTO `address_province` VALUES ('28', '甘肃省', 'gan_su');
INSERT INTO `address_province` VALUES ('29', '青海省', 'qing_hai');
INSERT INTO `address_province` VALUES ('30', '宁夏回族自治区', 'ning_xia_hui_zu_autonomous_region\r\n');
INSERT INTO `address_province` VALUES ('31', '新疆维吾尔自治区', 'xin_jiang_wei_wu_er_autonomous_region\r\n');
INSERT INTO `address_province` VALUES ('32', '台湾', 'tai_wan');
INSERT INTO `address_province` VALUES ('33', '香港', 'xiang_gang');
INSERT INTO `address_province` VALUES ('34', '澳门', 'ao_men');

-- ----------------------------
-- Table structure for `admin_alarm_message`
-- ----------------------------
DROP TABLE IF EXISTS `admin_alarm_message`;
CREATE TABLE `admin_alarm_message` (
  `id` varchar(20) NOT NULL,
  `title` varchar(20) DEFAULT NULL,
  `content` text,
  `branch_id` varchar(20) DEFAULT NULL,
  `log_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_alarm_message
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_branch`
-- ----------------------------
DROP TABLE IF EXISTS `admin_branch`;
CREATE TABLE `admin_branch` (
  `id` varchar(30) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `parent_id` varchar(50) NOT NULL,
  `open_id` varchar(50) DEFAULT NULL,
  `short_name` varchar(30) DEFAULT NULL,
  `public_name` varchar(30) DEFAULT NULL,
  `app_id` varchar(50) DEFAULT NULL,
  `app_secret` varchar(50) DEFAULT NULL,
  `qr_code` varchar(50) DEFAULT NULL,
  `province` int(3) DEFAULT NULL,
  `city` int(3) DEFAULT NULL,
  `county` int(3) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `industry` int(3) DEFAULT NULL,
  `sub_industry` int(3) DEFAULT NULL,
  `contact_man` varchar(50) DEFAULT NULL,
  `contact_phone` varchar(20) DEFAULT NULL,
  `grade` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_branch
-- ----------------------------
INSERT INTO `admin_branch` VALUES ('00001', '广州云上昆酒店', '0', 'gh_481333aaaeb5', 'dddd', 'abc', 'wx26e27995ce4f8858', 'da718077f6849c8d4d5827be84e66b6d', '/soofac/acs/msite/00001_qrcode.jpg', '1', '1', '1', '白云酒店白云酒店', '1', '1', '陈先生', '020-90009000', '1');
INSERT INTO `admin_branch` VALUES ('00002', '德阳工行', '0', null, null, '', null, null, null, null, null, null, null, null, null, null, null, '1');

-- ----------------------------
-- Table structure for `admin_group`
-- ----------------------------
DROP TABLE IF EXISTS `admin_group`;
CREATE TABLE `admin_group` (
  `id` varchar(30) NOT NULL DEFAULT '0',
  `pid` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `branch_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `branch_id` (`branch_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_group
-- ----------------------------
INSERT INTO `admin_group` VALUES ('0006', '0', '分店002', '00001');
INSERT INTO `admin_group` VALUES ('0005', '0', '分店001', '00001');
INSERT INTO `admin_group` VALUES ('0003', '0', '超管G1', '0');
INSERT INTO `admin_group` VALUES ('0004', '0', '超管G2', '0');

-- ----------------------------
-- Table structure for `admin_license`
-- ----------------------------
DROP TABLE IF EXISTS `admin_license`;
CREATE TABLE `admin_license` (
  `mac` varchar(17) NOT NULL DEFAULT '',
  `log_time` datetime DEFAULT NULL,
  `branch_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`mac`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_license
-- ----------------------------
INSERT INTO `admin_license` VALUES ('78:D3:8D:AB:99:58', '2014-10-28 16:19:58', '00001');
INSERT INTO `admin_license` VALUES ('78:D3:8D:AB:99:88', '2014-10-21 17:12:00', '00001');
INSERT INTO `admin_license` VALUES ('78:D3:8D:AB:9D:A0', '2014-10-28 16:12:41', '00001');
INSERT INTO `admin_license` VALUES ('78:D3:8D:AF:86:90', '2014-10-28 16:19:58', '00001');
INSERT INTO `admin_license` VALUES ('78:D3:8D:B2:18:3C', '2014-10-28 16:19:58', '00001');

-- ----------------------------
-- Table structure for `admin_log`
-- ----------------------------
DROP TABLE IF EXISTS `admin_log`;
CREATE TABLE `admin_log` (
  `id` varchar(25) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `fullname` varchar(30) DEFAULT NULL,
  `operation` varchar(100) DEFAULT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `location` varchar(30) DEFAULT NULL,
  `log_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_log
-- ----------------------------
INSERT INTO `admin_log` VALUES ('1003129282766972', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-06 10:51:54');
INSERT INTO `admin_log` VALUES ('1003143591669312', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,重启设备', '0:0:0:0:0:0:0:1', '', '2014-11-06 10:52:08');
INSERT INTO `admin_log` VALUES ('1003250191586924', 'yunsk', 'yunsk', '登入', '192.168.1.161', '', '2014-11-06 10:53:55');
INSERT INTO `admin_log` VALUES ('1003261829940690', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,重启设备', '0:0:0:0:0:0:0:1', '', '2014-11-06 10:54:06');
INSERT INTO `admin_log` VALUES ('1003880888734825', 'yunsk', 'yunsk', '登入', '192.168.1.161', '', '2014-11-06 11:04:25');
INSERT INTO `admin_log` VALUES ('1014632306355335', 'yunsk', 'yunsk', '登入', '192.168.1.125', '', '2014-11-06 14:03:38');
INSERT INTO `admin_log` VALUES ('1015059327631682', 'yunsk', 'yunsk', '登入', '192.168.1.125', '', '2014-11-06 14:10:45');
INSERT INTO `admin_log` VALUES ('1017956224231183', 'yunsk', 'yunsk', '登入', '192.168.1.161', '', '2014-11-06 14:59:02');
INSERT INTO `admin_log` VALUES ('1017991733411120', 'yunsk', 'yunsk', '登入', '192.168.1.125', '', '2014-11-06 14:59:37');
INSERT INTO `admin_log` VALUES ('1018350710045005', 'yunsk', 'yunsk', '登入', '192.168.1.125', '', '2014-11-06 15:05:36');
INSERT INTO `admin_log` VALUES ('1022166301357941', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-06 16:09:12');
INSERT INTO `admin_log` VALUES ('1022880205168821', 'yunsk', 'yunsk', '登入', '192.168.1.161', '', '2014-11-06 16:21:06');
INSERT INTO `admin_log` VALUES ('1023036001138135', 'yunsk', 'yunsk', '登入', '192.168.1.161', '', '2014-11-06 16:23:42');
INSERT INTO `admin_log` VALUES ('1023223261519999', 'yunsk', 'yunsk', '登入', '192.168.1.161', '', '2014-11-06 16:26:49');
INSERT INTO `admin_log` VALUES ('1023847699574933', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-06 16:37:13');
INSERT INTO `admin_log` VALUES ('1042000634620495', 'yunsk', 'yunsk', '更新商家信息', '0:0:0:0:0:0:0:1', '', '2014-11-06 21:39:47');
INSERT INTO `admin_log` VALUES ('1042196177693243', 'yunsk', 'yunsk', '更新商家信息', '0:0:0:0:0:0:0:1', '', '2014-11-06 21:43:03');
INSERT INTO `admin_log` VALUES ('1042367024323762', 'yunsk', 'yunsk', '更新微信公号信息', '0:0:0:0:0:0:0:1', '', '2014-11-06 21:45:54');
INSERT INTO `admin_log` VALUES ('158072004484595', 'afunms', 'afunms', '删除商家:eee', '127.0.0.1', '', '2014-11-10 10:50:19');
INSERT INTO `admin_log` VALUES ('229173152855266', 'yunsk', 'yunsk', '登入', '192.168.1.113', '', '2014-10-28 11:51:26');
INSERT INTO `admin_log` VALUES ('229495711159076', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 11:56:49');
INSERT INTO `admin_log` VALUES ('229736054584164', 'yunsk', 'yunsk', '登入', '192.168.1.113', '', '2014-10-28 12:00:49');
INSERT INTO `admin_log` VALUES ('229896401731552', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 12:03:29');
INSERT INTO `admin_log` VALUES ('229910340277165', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 12:03:43');
INSERT INTO `admin_log` VALUES ('230039693014018', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 12:05:53');
INSERT INTO `admin_log` VALUES ('230471877737799', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 12:13:05');
INSERT INTO `admin_log` VALUES ('230687595521137', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 12:16:41');
INSERT INTO `admin_log` VALUES ('230761139201882', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 12:17:54');
INSERT INTO `admin_log` VALUES ('230862831020433', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 12:19:36');
INSERT INTO `admin_log` VALUES ('231064004579793', 'yunsk', 'yunsk', '登入', '192.168.1.113', '', '2014-10-28 12:22:57');
INSERT INTO `admin_log` VALUES ('234203931778022', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 13:15:17');
INSERT INTO `admin_log` VALUES ('234305385763320', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 13:16:59');
INSERT INTO `admin_log` VALUES ('234602355005692', 'yunsk', 'yunsk', '登入', '192.168.1.113', '', '2014-10-28 13:21:56');
INSERT INTO `admin_log` VALUES ('234665506604621', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 13:22:59');
INSERT INTO `admin_log` VALUES ('234786166257383', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 13:25:00');
INSERT INTO `admin_log` VALUES ('234989773053003', 'afunms', 'afunms', '登入', '192.168.1.113', '', '2014-10-28 13:28:23');
INSERT INTO `admin_log` VALUES ('235114555885624', 'afunms', 'afunms', '登入', '192.168.1.113', '', '2014-10-28 13:30:28');
INSERT INTO `admin_log` VALUES ('235233660224661', 'yunsk', 'yunsk', '登入', '192.168.1.113', '', '2014-10-28 13:32:27');
INSERT INTO `admin_log` VALUES ('235600233067308', 'yunsk', 'yunsk', '登入', '192.168.1.113', '', '2014-10-28 13:38:34');
INSERT INTO `admin_log` VALUES ('235605061876940', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 13:38:39');
INSERT INTO `admin_log` VALUES ('235852028890816', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 13:42:46');
INSERT INTO `admin_log` VALUES ('235921180470988', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 13:43:55');
INSERT INTO `admin_log` VALUES ('235925476527538', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 13:43:59');
INSERT INTO `admin_log` VALUES ('236425147114333', 'afunms', 'afunms', '登入', '192.168.1.113', '', '2014-10-28 13:52:19');
INSERT INTO `admin_log` VALUES ('236562563069753', 'yunsk', 'yunsk', '登入', '192.168.1.113', '', '2014-10-28 13:54:36');
INSERT INTO `admin_log` VALUES ('236711434012711', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 13:57:05');
INSERT INTO `admin_log` VALUES ('236718754611025', 'afunms', 'afunms', '登入', '192.168.1.113', '', '2014-10-28 13:57:12');
INSERT INTO `admin_log` VALUES ('236825943698014', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 13:58:59');
INSERT INTO `admin_log` VALUES ('236937024596972', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 14:00:51');
INSERT INTO `admin_log` VALUES ('239303362400037', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 14:40:17');
INSERT INTO `admin_log` VALUES ('239318765837377', 'yunsk', 'yunsk', '登入', '192.168.1.113', '', '2014-10-28 14:40:32');
INSERT INTO `admin_log` VALUES ('239331275904890', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 14:40:45');
INSERT INTO `admin_log` VALUES ('239486477873412', 'yunsk', 'yunsk', '登入', '192.168.1.113', '', '2014-10-28 14:43:20');
INSERT INTO `admin_log` VALUES ('239504291090299', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 14:43:38');
INSERT INTO `admin_log` VALUES ('239902491601469', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 14:50:16');
INSERT INTO `admin_log` VALUES ('240004951699648', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 14:51:59');
INSERT INTO `admin_log` VALUES ('240073610747598', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 14:53:07');
INSERT INTO `admin_log` VALUES ('240603487642412', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 15:01:57');
INSERT INTO `admin_log` VALUES ('241133847354594', 'yunsk', 'yunsk', '登入', '192.168.1.113', '', '2014-10-28 15:10:47');
INSERT INTO `admin_log` VALUES ('241145651062950', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 15:10:59');
INSERT INTO `admin_log` VALUES ('244510272863845', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 16:07:04');
INSERT INTO `admin_log` VALUES ('244582563611925', 'afunms', 'afunms', '登入', '192.168.1.113', '', '2014-10-28 16:08:16');
INSERT INTO `admin_log` VALUES ('244588993777411', 'afunms', 'afunms', '删除商家:三楼', '192.168.1.113', '', '2014-10-28 16:08:23');
INSERT INTO `admin_log` VALUES ('244593501626692', 'afunms', 'afunms', '增加商家:广州云上昆酒店2', '0:0:0:0:0:0:0:1', '', '2014-10-28 16:08:27');
INSERT INTO `admin_log` VALUES ('244812432953494', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 16:12:06');
INSERT INTO `admin_log` VALUES ('244825681180731', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 16:12:19');
INSERT INTO `admin_log` VALUES ('244847707639206', 'afunms', 'afunms', '分派1个序列号给:广州云上昆酒店', '0:0:0:0:0:0:0:1', '', '2014-10-28 16:12:41');
INSERT INTO `admin_log` VALUES ('244858663826281', 'afunms', 'afunms', '分派1个序列号给:广州云上昆酒店2', '0:0:0:0:0:0:0:1', '', '2014-10-28 16:12:52');
INSERT INTO `admin_log` VALUES ('244863473680783', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 16:12:57');
INSERT INTO `admin_log` VALUES ('245119457434388', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 16:17:13');
INSERT INTO `admin_log` VALUES ('245128124675785', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 16:17:22');
INSERT INTO `admin_log` VALUES ('245266116014131', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 16:19:40');
INSERT INTO `admin_log` VALUES ('245276517748161', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 16:19:50');
INSERT INTO `admin_log` VALUES ('245284327228239', 'afunms', 'afunms', '分派1个序列号给:广州云上昆酒店2', '0:0:0:0:0:0:0:1', '', '2014-10-28 16:19:58');
INSERT INTO `admin_log` VALUES ('245287967448108', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 16:20:02');
INSERT INTO `admin_log` VALUES ('245755967489868', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 16:27:50');
INSERT INTO `admin_log` VALUES ('246011727302133', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 16:32:05');
INSERT INTO `admin_log` VALUES ('246020575557044', 'afunms', 'afunms', '登入', '192.168.1.113', '', '2014-10-28 16:32:14');
INSERT INTO `admin_log` VALUES ('271587610511902', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-28 23:38:23');
INSERT INTO `admin_log` VALUES ('307914757033811', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-29 09:43:56');
INSERT INTO `admin_log` VALUES ('309898714784761', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-29 10:17:00');
INSERT INTO `admin_log` VALUES ('310336321238869', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-29 10:24:17');
INSERT INTO `admin_log` VALUES ('310593898268483', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-29 10:28:35');
INSERT INTO `admin_log` VALUES ('310612187910278', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,设置溯源', '0:0:0:0:0:0:0:1', '', '2014-10-29 10:28:53');
INSERT INTO `admin_log` VALUES ('311146142980097', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-29 10:37:47');
INSERT INTO `admin_log` VALUES ('311526837493581', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-29 10:44:08');
INSERT INTO `admin_log` VALUES ('312841347439353', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-29 11:06:02');
INSERT INTO `admin_log` VALUES ('312972490662224', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-29 11:08:14');
INSERT INTO `admin_log` VALUES ('312987447016693', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-29 11:08:29');
INSERT INTO `admin_log` VALUES ('313437693332526', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-29 11:15:59');
INSERT INTO `admin_log` VALUES ('313522471808662', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-29 11:17:24');
INSERT INTO `admin_log` VALUES ('313749294806582', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-29 11:21:10');
INSERT INTO `admin_log` VALUES ('314429395077562', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-29 11:32:30');
INSERT INTO `admin_log` VALUES ('315780412744857', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-29 11:55:02');
INSERT INTO `admin_log` VALUES ('324617635075254', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-29 14:22:20');
INSERT INTO `admin_log` VALUES ('324630198909781', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-29 14:22:32');
INSERT INTO `admin_log` VALUES ('324774096338111', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-29 14:24:56');
INSERT INTO `admin_log` VALUES ('334873559563332', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-29 17:13:16');
INSERT INTO `admin_log` VALUES ('335121554407392', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-29 17:17:24');
INSERT INTO `admin_log` VALUES ('337001322107689', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-29 17:48:44');
INSERT INTO `admin_log` VALUES ('337030926083449', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-29 17:49:13');
INSERT INTO `admin_log` VALUES ('337116910195199', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-29 17:50:39');
INSERT INTO `admin_log` VALUES ('346108953441180', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-29 20:20:32');
INSERT INTO `admin_log` VALUES ('349407837255053', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-29 21:15:31');
INSERT INTO `admin_log` VALUES ('349430741058501', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-29 21:15:54');
INSERT INTO `admin_log` VALUES ('349762452113178', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-29 21:21:25');
INSERT INTO `admin_log` VALUES ('349768772057842', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-29 21:21:32');
INSERT INTO `admin_log` VALUES ('355226780725866', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-29 22:52:30');
INSERT INTO `admin_log` VALUES ('355671066853807', 'afunms', 'afunms', '登入', '192.168.1.166', '', '2014-10-29 22:59:54');
INSERT INTO `admin_log` VALUES ('403743492675550', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-30 12:21:12');
INSERT INTO `admin_log` VALUES ('403758933830276', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,设置溯源', '0:0:0:0:0:0:0:1', '', '2014-10-30 12:21:28');
INSERT INTO `admin_log` VALUES ('403763714346635', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,设置溯源', '0:0:0:0:0:0:0:1', '', '2014-10-30 12:21:33');
INSERT INTO `admin_log` VALUES ('403779168826683', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,设置溯源', '0:0:0:0:0:0:0:1', '', '2014-10-30 12:21:48');
INSERT INTO `admin_log` VALUES ('403807203178145', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,设置溯源', '0:0:0:0:0:0:0:1', '', '2014-10-30 12:22:16');
INSERT INTO `admin_log` VALUES ('403900970580813', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,设置溯源', '192.168.1.166', '', '2014-10-30 12:23:50');
INSERT INTO `admin_log` VALUES ('404317611717633', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-30 12:30:46');
INSERT INTO `admin_log` VALUES ('404374753320713', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,设置溯源', '192.168.1.166', '', '2014-10-30 12:31:44');
INSERT INTO `admin_log` VALUES ('404661982172034', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-30 12:36:31');
INSERT INTO `admin_log` VALUES ('404674317467732', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,设置溯源', '192.168.1.166', '', '2014-10-30 12:36:43');
INSERT INTO `admin_log` VALUES ('407471309789915', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-30 13:23:21');
INSERT INTO `admin_log` VALUES ('407678854783267', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,设置溯源', '192.168.1.166', '', '2014-10-30 13:26:48');
INSERT INTO `admin_log` VALUES ('407696757471936', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,设置溯源', '192.168.1.166', '', '2014-10-30 13:27:06');
INSERT INTO `admin_log` VALUES ('408590339930699', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-30 13:42:00');
INSERT INTO `admin_log` VALUES ('408655241596957', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,设置溯源', '0:0:0:0:0:0:0:1', '', '2014-10-30 13:43:05');
INSERT INTO `admin_log` VALUES ('409229952014617', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-30 13:52:39');
INSERT INTO `admin_log` VALUES ('409972364043185', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-30 14:05:02');
INSERT INTO `admin_log` VALUES ('410021258062769', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,探测', '192.168.1.166', '', '2014-10-30 14:05:51');
INSERT INTO `admin_log` VALUES ('410117040386484', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-30 14:07:26');
INSERT INTO `admin_log` VALUES ('410154105267665', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,探测', '0:0:0:0:0:0:0:1', '', '2014-10-30 14:08:03');
INSERT INTO `admin_log` VALUES ('410913251742859', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-30 14:20:43');
INSERT INTO `admin_log` VALUES ('410952168721575', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,探测', '192.168.1.166', '', '2014-10-30 14:21:22');
INSERT INTO `admin_log` VALUES ('411021611846452', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-30 14:22:31');
INSERT INTO `admin_log` VALUES ('411040354185003', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,设置溯源', '192.168.1.166', '', '2014-10-30 14:22:50');
INSERT INTO `admin_log` VALUES ('411069154602461', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,探测', '192.168.1.166', '', '2014-10-30 14:23:19');
INSERT INTO `admin_log` VALUES ('411124423759932', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,探测', '192.168.1.166', '', '2014-10-30 14:24:14');
INSERT INTO `admin_log` VALUES ('411142531979815', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,设置溯源', '192.168.1.166', '', '2014-10-30 14:24:32');
INSERT INTO `admin_log` VALUES ('411425546792923', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-30 14:29:15');
INSERT INTO `admin_log` VALUES ('411434568418567', 'yunsk', 'yunsk', '78:D3:8D:AB:9D:A0,重启设备', '0:0:0:0:0:0:0:1', '', '2014-10-30 14:29:24');
INSERT INTO `admin_log` VALUES ('413706550375432', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-30 15:07:16');
INSERT INTO `admin_log` VALUES ('421185144009232', 'yunsk', 'yunsk', '登入', '192.168.1.166', '', '2014-10-30 17:11:55');
INSERT INTO `admin_log` VALUES ('4958369536390', 'yunsk', 'yunsk', '78:D3:8D:AB:99:58,设置溯源', '0:0:0:0:0:0:0:1', '', '2014-11-08 16:18:10');
INSERT INTO `admin_log` VALUES ('503616187477815', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-31 16:05:54');
INSERT INTO `admin_log` VALUES ('505355030457876', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-31 16:34:53');
INSERT INTO `admin_log` VALUES ('505582419224729', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-31 16:38:40');
INSERT INTO `admin_log` VALUES ('512539253864477', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-31 18:34:37');
INSERT INTO `admin_log` VALUES ('512607761444795', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-10-31 18:35:46');
INSERT INTO `admin_log` VALUES ('532774238790900', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 00:11:53');
INSERT INTO `admin_log` VALUES ('569761829548571', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 10:28:26');
INSERT INTO `admin_log` VALUES ('570177883151376', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 10:35:22');
INSERT INTO `admin_log` VALUES ('570261360773168', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 10:36:45');
INSERT INTO `admin_log` VALUES ('571502466895143', 'yunsk', 'yunsk', '登入', '192.168.1.165', '', '2014-11-01 10:57:27');
INSERT INTO `admin_log` VALUES ('571620500746901', 'yunsk', 'yunsk', '登入', '192.168.1.165', '', '2014-11-01 10:59:25');
INSERT INTO `admin_log` VALUES ('572561839100985', 'yunsk', 'yunsk', '登入', '192.168.1.165', '', '2014-11-01 11:15:06');
INSERT INTO `admin_log` VALUES ('572573040615456', 'yunsk', 'yunsk', '78:D3:8D:B2:18:3C,设置溯源', '192.168.1.30', '', '2014-11-01 11:15:17');
INSERT INTO `admin_log` VALUES ('572735626286552', 'yunsk', 'yunsk', '78:D3:8D:B2:18:3C,设置溯源', '192.168.1.165', '', '2014-11-01 11:18:00');
INSERT INTO `admin_log` VALUES ('572744686759225', 'yunsk', 'yunsk', '78:D3:8D:B2:18:3C,设置溯源', '192.168.1.165', '', '2014-11-01 11:18:09');
INSERT INTO `admin_log` VALUES ('573000992394024', 'yunsk', 'yunsk', '登入', '192.168.1.178', '', '2014-11-01 11:22:25');
INSERT INTO `admin_log` VALUES ('573042677496299', 'yunsk', 'yunsk', '78:D3:8D:B2:18:3C,设置溯源', '192.168.1.178', '', '2014-11-01 11:23:07');
INSERT INTO `admin_log` VALUES ('573085703540360', 'yunsk', 'yunsk', '78:D3:8D:B2:18:3C,设置溯源', '192.168.1.165', '', '2014-11-01 11:23:50');
INSERT INTO `admin_log` VALUES ('573900239177357', 'yunsk', 'yunsk', '登入', '192.168.1.165', '', '2014-11-01 11:37:24');
INSERT INTO `admin_log` VALUES ('573906960825969', 'yunsk', 'yunsk', '登入', '192.168.1.178', '', '2014-11-01 11:37:31');
INSERT INTO `admin_log` VALUES ('573936931421293', 'yunsk', 'yunsk', '78:D3:8D:B2:18:3C,设置溯源', '192.168.1.165', '', '2014-11-01 11:38:01');
INSERT INTO `admin_log` VALUES ('573948402466952', 'yunsk', 'yunsk', '登入', '192.168.1.165', '', '2014-11-01 11:38:13');
INSERT INTO `admin_log` VALUES ('573958983147731', 'yunsk', 'yunsk', '78:D3:8D:B2:18:3C,设置溯源', '192.168.1.178', '', '2014-11-01 11:38:23');
INSERT INTO `admin_log` VALUES ('574369473073848', 'yunsk', 'yunsk', '登入', '192.168.1.30', '', '2014-11-01 11:45:14');
INSERT INTO `admin_log` VALUES ('574397455392750', 'yunsk', 'yunsk', '更新微信公号信息', '192.168.1.30', '', '2014-11-01 11:45:42');
INSERT INTO `admin_log` VALUES ('574459648658921', 'yunsk', 'yunsk', '更新微信公号信息', '192.168.1.30', '', '2014-11-01 11:46:44');
INSERT INTO `admin_log` VALUES ('574579270459108', 'yunsk', 'yunsk', '登入', '192.168.1.165', '', '2014-11-01 11:48:43');
INSERT INTO `admin_log` VALUES ('574585583470557', 'yunsk', 'yunsk', '更新微信公号信息', '192.168.1.30', '', '2014-11-01 11:48:50');
INSERT INTO `admin_log` VALUES ('574714576568484', 'yunsk', 'yunsk', '更新微信公号信息', '192.168.1.165', '', '2014-11-01 11:50:59');
INSERT INTO `admin_log` VALUES ('584176787013571', 'yunsk', 'yunsk', '登入', '192.168.1.165', '', '2014-11-01 14:28:41');
INSERT INTO `admin_log` VALUES ('584242920883626', 'yunsk', 'yunsk', '登入', '192.168.1.165', '', '2014-11-01 14:29:47');
INSERT INTO `admin_log` VALUES ('585284619751117', 'yunsk', 'yunsk', '登入', '192.168.1.165', '', '2014-11-01 14:47:09');
INSERT INTO `admin_log` VALUES ('585453925232679', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 14:49:58');
INSERT INTO `admin_log` VALUES ('585566682470689', 'yunsk', 'yunsk', '登入', '192.168.1.165', '', '2014-11-01 14:51:51');
INSERT INTO `admin_log` VALUES ('585652718990130', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 14:53:17');
INSERT INTO `admin_log` VALUES ('591836024572199', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 16:36:21');
INSERT INTO `admin_log` VALUES ('592020766049008', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 16:39:26');
INSERT INTO `admin_log` VALUES ('597850700048802', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 18:16:37');
INSERT INTO `admin_log` VALUES ('598918374463004', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 18:34:24');
INSERT INTO `admin_log` VALUES ('602030222967059', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 19:26:16');
INSERT INTO `admin_log` VALUES ('602747150820707', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 19:38:13');
INSERT INTO `admin_log` VALUES ('603609479558675', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 19:52:35');
INSERT INTO `admin_log` VALUES ('603715509183829', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 19:54:21');
INSERT INTO `admin_log` VALUES ('604557213681484', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 20:08:23');
INSERT INTO `admin_log` VALUES ('605291782393079', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 20:20:38');
INSERT INTO `admin_log` VALUES ('605529093274062', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 20:24:35');
INSERT INTO `admin_log` VALUES ('605637366609299', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 20:26:23');
INSERT INTO `admin_log` VALUES ('606422058770070', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 20:39:28');
INSERT INTO `admin_log` VALUES ('606746948607995', 'yunsk', 'yunsk', '更新商家信息', '0:0:0:0:0:0:0:1', '', '2014-11-01 20:44:53');
INSERT INTO `admin_log` VALUES ('606815108562455', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 20:46:01');
INSERT INTO `admin_log` VALUES ('606820114446363', 'yunsk', 'yunsk', '更新商家信息', '0:0:0:0:0:0:0:1', '', '2014-11-01 20:46:06');
INSERT INTO `admin_log` VALUES ('607124471785659', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 20:51:11');
INSERT INTO `admin_log` VALUES ('607145761067540', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 20:51:32');
INSERT INTO `admin_log` VALUES ('609020235571136', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-01 21:22:46');
INSERT INTO `admin_log` VALUES ('655848914953358', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 10:23:21');
INSERT INTO `admin_log` VALUES ('656070157519996', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 10:27:02');
INSERT INTO `admin_log` VALUES ('656245677526734', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 10:29:57');
INSERT INTO `admin_log` VALUES ('658658424836844', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 11:10:10');
INSERT INTO `admin_log` VALUES ('658983347409626', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 11:15:35');
INSERT INTO `admin_log` VALUES ('660147396034856', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 11:34:59');
INSERT INTO `admin_log` VALUES ('660415452560858', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 11:39:27');
INSERT INTO `admin_log` VALUES ('661342371417357', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 11:54:54');
INSERT INTO `admin_log` VALUES ('661706276198625', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 12:00:58');
INSERT INTO `admin_log` VALUES ('661988760490686', 'yunsk', 'yunsk', '更新微信公号信息', '192.168.1.101', '', '2014-11-02 12:05:41');
INSERT INTO `admin_log` VALUES ('662500121016126', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 12:14:12');
INSERT INTO `admin_log` VALUES ('664939485163357', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 12:54:51');
INSERT INTO `admin_log` VALUES ('665110659362919', 'yunsk', 'yunsk', '登入', '119.33.97.113', '', '2014-11-02 12:57:43');
INSERT INTO `admin_log` VALUES ('665356438800243', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 13:01:48');
INSERT INTO `admin_log` VALUES ('665543745918936', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 13:04:56');
INSERT INTO `admin_log` VALUES ('665564758448695', 'yunsk', 'yunsk', '更新微信公号信息', '192.168.1.101', '', '2014-11-02 13:05:17');
INSERT INTO `admin_log` VALUES ('670873741350113', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 14:33:46');
INSERT INTO `admin_log` VALUES ('671158202186523', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 14:38:30');
INSERT INTO `admin_log` VALUES ('671163272930731', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 14:38:35');
INSERT INTO `admin_log` VALUES ('671172728146887', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 14:38:45');
INSERT INTO `admin_log` VALUES ('671332750829271', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 14:41:25');
INSERT INTO `admin_log` VALUES ('671358895170106', 'yunsk', 'yunsk', '78:D3:8D:AB:99:58,设置溯源', '0:0:0:0:0:0:0:1', '', '2014-11-02 14:41:51');
INSERT INTO `admin_log` VALUES ('671636691141190', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 14:46:29');
INSERT INTO `admin_log` VALUES ('671983335092560', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 14:52:16');
INSERT INTO `admin_log` VALUES ('674825285616205', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 15:39:38');
INSERT INTO `admin_log` VALUES ('675144793032314', 'afunms', 'afunms', '登入', '192.168.1.101', '', '2014-11-02 15:44:57');
INSERT INTO `admin_log` VALUES ('676119440237777', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 16:01:12');
INSERT INTO `admin_log` VALUES ('676133252864008', 'yunsk', 'yunsk', '78:D3:8D:AB:99:58,设置参数', '0:0:0:0:0:0:0:1', '', '2014-11-02 16:01:26');
INSERT INTO `admin_log` VALUES ('676135152698843', 'yunsk', 'yunsk', '78:D3:8D:AB:99:58,重启设备', '0:0:0:0:0:0:0:1', '', '2014-11-02 16:01:28');
INSERT INTO `admin_log` VALUES ('676569377808755', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 16:08:42');
INSERT INTO `admin_log` VALUES ('677027000598795', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 16:16:20');
INSERT INTO `admin_log` VALUES ('677169420180962', 'yunsk', 'yunsk', '78:D3:8D:AB:99:58,设置溯源', '192.168.1.101', '', '2014-11-02 16:18:42');
INSERT INTO `admin_log` VALUES ('678147365068074', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 16:35:00');
INSERT INTO `admin_log` VALUES ('678697628296692', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 16:44:10');
INSERT INTO `admin_log` VALUES ('679425432258439', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 16:56:18');
INSERT INTO `admin_log` VALUES ('679481778877102', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 16:57:14');
INSERT INTO `admin_log` VALUES ('679778098939261', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 17:02:11');
INSERT INTO `admin_log` VALUES ('679867592101067', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 17:03:40');
INSERT INTO `admin_log` VALUES ('680271560104442', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 17:10:24');
INSERT INTO `admin_log` VALUES ('682782962318357', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 17:52:16');
INSERT INTO `admin_log` VALUES ('683411663155644', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 18:02:44');
INSERT INTO `admin_log` VALUES ('683458816049132', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 18:03:32');
INSERT INTO `admin_log` VALUES ('683761784386233', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 18:08:35');
INSERT INTO `admin_log` VALUES ('684459518826488', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 18:20:12');
INSERT INTO `admin_log` VALUES ('684604893312141', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 18:22:38');
INSERT INTO `admin_log` VALUES ('684752367242069', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 18:25:05');
INSERT INTO `admin_log` VALUES ('684868044268745', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 18:27:01');
INSERT INTO `admin_log` VALUES ('685204362047554', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 18:32:37');
INSERT INTO `admin_log` VALUES ('689767442400447', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 19:48:41');
INSERT INTO `admin_log` VALUES ('689779117697624', 'yunsk', 'yunsk', '增加商家:abc', '0:0:0:0:0:0:0:1', '', '2014-11-02 19:48:52');
INSERT INTO `admin_log` VALUES ('689799108137424', 'yunsk', 'yunsk', '创建用户:abc', '0:0:0:0:0:0:0:1', '', '2014-11-02 19:49:12');
INSERT INTO `admin_log` VALUES ('689803108253148', 'abc', 'abc', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 19:49:16');
INSERT INTO `admin_log` VALUES ('690447086440292', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 20:00:00');
INSERT INTO `admin_log` VALUES ('690453270469725', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 20:00:06');
INSERT INTO `admin_log` VALUES ('690465770399476', 'afunms', 'afunms', '增加商家:eee', '0:0:0:0:0:0:0:1', '', '2014-11-02 20:00:19');
INSERT INTO `admin_log` VALUES ('690491363102170', 'afunms', 'afunms', '创建用户:eee', '0:0:0:0:0:0:0:1', '', '2014-11-02 20:00:45');
INSERT INTO `admin_log` VALUES ('690552334705749', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 20:01:45');
INSERT INTO `admin_log` VALUES ('690556098541834', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 20:01:49');
INSERT INTO `admin_log` VALUES ('690566059002934', 'afunms', 'afunms', '删除商家:eee', '192.168.1.101', '', '2014-11-02 20:01:59');
INSERT INTO `admin_log` VALUES ('690592804039250', 'afunms', 'afunms', '增加商家:rrr', '192.168.1.101', '', '2014-11-02 20:02:26');
INSERT INTO `admin_log` VALUES ('690729282518182', 'afunms', 'afunms', '登入', '192.168.1.101', '', '2014-11-02 20:04:42');
INSERT INTO `admin_log` VALUES ('690740311107123', 'afunms', 'afunms', '删除商家:rrr', '0:0:0:0:0:0:0:1', '', '2014-11-02 20:04:53');
INSERT INTO `admin_log` VALUES ('690742689127156', 'afunms', 'afunms', '删除商家:广州云上昆酒店2', '0:0:0:0:0:0:0:1', '', '2014-11-02 20:04:56');
INSERT INTO `admin_log` VALUES ('690745059767000', 'afunms', 'afunms', '删除商家:abc', '0:0:0:0:0:0:0:1', '', '2014-11-02 20:04:58');
INSERT INTO `admin_log` VALUES ('690751285224086', 'afunms', 'afunms', '删除帐号:dygh,33,', '0:0:0:0:0:0:0:1', '', '2014-11-02 20:05:04');
INSERT INTO `admin_log` VALUES ('690764824795721', 'afunms', 'afunms', '增加商家:abc', '0:0:0:0:0:0:0:1', '', '2014-11-02 20:05:18');
INSERT INTO `admin_log` VALUES ('690795343609115', 'afunms', 'afunms', '创建用户:abc', '0:0:0:0:0:0:0:1', '', '2014-11-02 20:05:49');
INSERT INTO `admin_log` VALUES ('690799649192599', 'abc', 'abc', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 20:05:53');
INSERT INTO `admin_log` VALUES ('691066708220307', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 20:10:20');
INSERT INTO `admin_log` VALUES ('691070723945764', 'afunms', 'afunms', '登入', '192.168.1.101', '', '2014-11-02 20:10:24');
INSERT INTO `admin_log` VALUES ('691082010959513', 'afunms', 'afunms', '增加商家:eee', '192.168.1.101', '', '2014-11-02 20:10:35');
INSERT INTO `admin_log` VALUES ('691102478575192', 'afunms', 'afunms', '创建用户:eee', '192.168.1.101', '', '2014-11-02 20:10:56');
INSERT INTO `admin_log` VALUES ('691107795274258', 'eee', 'eee', '登入', '192.168.1.101', '', '2014-11-02 20:11:01');
INSERT INTO `admin_log` VALUES ('691190466761723', 'eee', 'eee', '登入', '192.168.1.101', '', '2014-11-02 20:12:24');
INSERT INTO `admin_log` VALUES ('691333596870197', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 20:14:47');
INSERT INTO `admin_log` VALUES ('691345868059043', 'eee', 'eee', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 20:14:59');
INSERT INTO `admin_log` VALUES ('691378649212063', 'eee', 'eee', '登入', '192.168.1.101', '', '2014-11-02 20:15:32');
INSERT INTO `admin_log` VALUES ('691410585099549', 'eee', 'eee', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 20:16:04');
INSERT INTO `admin_log` VALUES ('693592670683055', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 20:52:26');
INSERT INTO `admin_log` VALUES ('693598186262883', 'afunms', 'afunms', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 20:52:31');
INSERT INTO `admin_log` VALUES ('693628410303315', 'afunms', 'afunms', '增加固件:1.2.28', '0:0:0:0:0:0:0:1', '', '2014-11-02 20:53:02');
INSERT INTO `admin_log` VALUES ('693642557757037', 'afunms', 'afunms', '78:D3:8D:AB:99:58,更新固件', '0:0:0:0:0:0:0:1', '', '2014-11-02 20:53:16');
INSERT INTO `admin_log` VALUES ('693955921533383', 'afunms', 'afunms', '78:D3:8D:AB:99:58,设置溯源', '192.168.1.101', '', '2014-11-02 20:58:29');
INSERT INTO `admin_log` VALUES ('694114502125334', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 21:01:08');
INSERT INTO `admin_log` VALUES ('694241932304622', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 21:03:15');
INSERT INTO `admin_log` VALUES ('694494376306812', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 21:07:28');
INSERT INTO `admin_log` VALUES ('694748530385150', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 21:11:42');
INSERT INTO `admin_log` VALUES ('694853831459266', 'yunsk', 'yunsk', '登入', '192.168.1.101', '', '2014-11-02 21:13:27');
INSERT INTO `admin_log` VALUES ('695516177556284', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 21:24:29');
INSERT INTO `admin_log` VALUES ('695561614426014', 'yunsk', 'yunsk', '78:D3:8D:AB:99:58,设置溯源', '192.168.1.101', '', '2014-11-02 21:25:15');
INSERT INTO `admin_log` VALUES ('695593004826460', 'yunsk', 'yunsk', '78:D3:8D:AB:99:58,设置溯源', '192.168.1.101', '', '2014-11-02 21:25:46');
INSERT INTO `admin_log` VALUES ('695642695611275', 'yunsk', 'yunsk', '78:D3:8D:AB:99:58,设置溯源', '192.168.1.102', '', '2014-11-02 21:26:36');
INSERT INTO `admin_log` VALUES ('699495832197143', 'yunsk', 'yunsk', '登入', '183.60.35.93', '', '2014-11-02 22:30:49');
INSERT INTO `admin_log` VALUES ('699638206994227', 'yunsk', 'yunsk', '登入', '183.5.247.120', '', '2014-11-02 22:33:12');
INSERT INTO `admin_log` VALUES ('699848116803678', 'yunsk', 'yunsk', '登入', '183.5.247.120', '', '2014-11-02 22:36:41');
INSERT INTO `admin_log` VALUES ('699946427915756', 'yunsk', 'yunsk', '登入', '183.5.247.120', '', '2014-11-02 22:38:20');
INSERT INTO `admin_log` VALUES ('700005235827004', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 22:39:19');
INSERT INTO `admin_log` VALUES ('700174616244696', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 22:42:08');
INSERT INTO `admin_log` VALUES ('700333244433450', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 22:44:47');
INSERT INTO `admin_log` VALUES ('700404229748099', 'yunsk', 'yunsk', '登入', '119.33.97.113', '', '2014-11-02 22:45:58');
INSERT INTO `admin_log` VALUES ('700489475661904', 'yunsk', 'yunsk', '登入', '119.33.97.113', '', '2014-11-02 22:47:23');
INSERT INTO `admin_log` VALUES ('700705176438674', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 22:50:59');
INSERT INTO `admin_log` VALUES ('700728954920473', 'yunsk', 'yunsk', '登入', '101.226.66.187', '', '2014-11-02 22:51:22');
INSERT INTO `admin_log` VALUES ('700749471348949', 'yunsk', 'yunsk', '登入', '119.33.97.113', '', '2014-11-02 22:51:43');
INSERT INTO `admin_log` VALUES ('700868345000996', 'yunsk', 'yunsk', '登入', '119.33.97.113', '', '2014-11-02 22:53:42');
INSERT INTO `admin_log` VALUES ('700944862290505', 'yunsk', 'yunsk', '登入', '119.33.97.113', '', '2014-11-02 22:54:58');
INSERT INTO `admin_log` VALUES ('701107208507495', 'yunsk', 'yunsk', '登入', '101.226.66.172', '', '2014-11-02 22:57:41');
INSERT INTO `admin_log` VALUES ('702817324096595', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-02 23:26:11');
INSERT INTO `admin_log` VALUES ('741497488949358', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 10:10:57');
INSERT INTO `admin_log` VALUES ('743266254101589', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 10:40:26');
INSERT INTO `admin_log` VALUES ('746656049200668', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 11:36:55');
INSERT INTO `admin_log` VALUES ('746721953064986', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 11:38:01');
INSERT INTO `admin_log` VALUES ('747967624115387', 'yunsk', 'yunsk', '登入', '192.168.1.30', '', '2014-11-03 11:58:47');
INSERT INTO `admin_log` VALUES ('747980039636575', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 11:58:59');
INSERT INTO `admin_log` VALUES ('750153434197800', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-03 12:35:13');
INSERT INTO `admin_log` VALUES ('754177938101471', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 13:42:18');
INSERT INTO `admin_log` VALUES ('754745218257739', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 13:51:45');
INSERT INTO `admin_log` VALUES ('754899241031074', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 13:54:19');
INSERT INTO `admin_log` VALUES ('754930100520123', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-03 13:54:50');
INSERT INTO `admin_log` VALUES ('755172413847603', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-03 13:58:53');
INSERT INTO `admin_log` VALUES ('755267060175399', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 14:00:27');
INSERT INTO `admin_log` VALUES ('755665377099544', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 14:07:05');
INSERT INTO `admin_log` VALUES ('755917900529891', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 14:11:18');
INSERT INTO `admin_log` VALUES ('756222055523056', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 14:16:22');
INSERT INTO `admin_log` VALUES ('756281181560994', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-03 14:17:21');
INSERT INTO `admin_log` VALUES ('756462464290453', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-03 14:20:23');
INSERT INTO `admin_log` VALUES ('756637156297632', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 14:23:17');
INSERT INTO `admin_log` VALUES ('756748994618627', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 14:25:09');
INSERT INTO `admin_log` VALUES ('757254587372990', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-03 14:33:35');
INSERT INTO `admin_log` VALUES ('758490215590409', 'yunsk', 'yunsk', '登入', '192.168.1.219', '', '2014-11-03 14:54:10');
INSERT INTO `admin_log` VALUES ('758736820986717', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 14:58:17');
INSERT INTO `admin_log` VALUES ('759203507734263', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 15:06:04');
INSERT INTO `admin_log` VALUES ('759402648796233', 'yunsk', 'yunsk', '登入', '0:0:0:0:0:0:0:1', '', '2014-11-03 15:09:23');
INSERT INTO `admin_log` VALUES ('759683261654480', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 15:14:03');
INSERT INTO `admin_log` VALUES ('759852436114865', 'yunsk', 'yunsk', '登入', '192.168.1.30', '', '2014-11-03 15:16:53');
INSERT INTO `admin_log` VALUES ('760063381357290', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 15:20:24');
INSERT INTO `admin_log` VALUES ('760784742406499', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 15:32:25');
INSERT INTO `admin_log` VALUES ('761066602685448', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-03 15:37:07');
INSERT INTO `admin_log` VALUES ('832337977933398', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-04 11:25:05');
INSERT INTO `admin_log` VALUES ('832879082361971', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-04 11:34:06');
INSERT INTO `admin_log` VALUES ('833032879747939', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-04 11:36:40');
INSERT INTO `admin_log` VALUES ('833772044323600', 'yunsk', 'yunsk', '登入', '192.168.1.175', '', '2014-11-04 11:48:59');
INSERT INTO `admin_log` VALUES ('94055774325620', 'dygh', 'dygh', '78:D3:8D:AB:99:58,设置溯源', '192.168.1.100', '', '2014-11-09 17:03:16');

-- ----------------------------
-- Table structure for `admin_role`
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `role` varchar(30) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `cn_name` varchar(30) DEFAULT NULL,
  `home` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('ROLE_ADMIN', 'Administrator', '管理员', '/project/redwave/main.jsp');
INSERT INTO `admin_role` VALUES ('ROLE_MERCHANT', 'merchant', '商家', '/project/redwave/main.jsp');
INSERT INTO `admin_role` VALUES ('ROLE_SOOFOUND', 'Soofound', '速方软件', '/common/main.jsp');

-- ----------------------------
-- Table structure for `admin_user`
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `username` varchar(30) NOT NULL,
  `fullname` varchar(30) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `branch_id` varchar(30) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `role` varchar(30) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('admin', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '00001', '13826129825', 'afunms@qq.com', 'ROLE_ADMIN', '2014-08-31 11:10:20');
INSERT INTO `admin_user` VALUES ('afunms', 'afunms', 'e10adc3949ba59abbe56e057f20f883e', '0', '13826129825', 'afunms@qq.com', 'ROLE_SOOFOUND', '2014-07-07 11:10:20');
INSERT INTO `admin_user` VALUES ('dygh', 'dygh', 'e10adc3949ba59abbe56e057f20f883e', '00002', '13826129825', 'afunms@qq.com', 'ROLE_MERCHANT', '2014-11-02 20:05:48');
INSERT INTO `admin_user` VALUES ('yunsk', 'yunsk', 'e10adc3949ba59abbe56e057f20f883e', '00001', '13826129825', 'afunms@qq.com', 'ROLE_MERCHANT', '2014-08-31 11:10:20');

-- ----------------------------
-- Table structure for `cpe_configuration`
-- ----------------------------
DROP TABLE IF EXISTS `cpe_configuration`;
CREATE TABLE `cpe_configuration` (
  `cfg_id` int(5) NOT NULL,
  `attribute_id` varchar(100) DEFAULT NULL,
  `attribute_value` varchar(100) DEFAULT NULL,
  KEY `cfg_id` (`cfg_id`),
  CONSTRAINT `cpe_configuration_ibfk_1` FOREIGN KEY (`cfg_id`) REFERENCES `cpe_software` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cpe_configuration
-- ----------------------------
INSERT INTO `cpe_configuration` VALUES ('2', '18', '90');
INSERT INTO `cpe_configuration` VALUES ('2', '17', '90');

-- ----------------------------
-- Table structure for `cpe_custom`
-- ----------------------------
DROP TABLE IF EXISTS `cpe_custom`;
CREATE TABLE `cpe_custom` (
  `ap_id` int(5) NOT NULL DEFAULT '0',
  `snr` int(2) DEFAULT NULL,
  `x` int(5) DEFAULT NULL,
  `y` int(5) DEFAULT NULL,
  PRIMARY KEY (`ap_id`),
  CONSTRAINT `cpe_custom_ibfk_1` FOREIGN KEY (`ap_id`) REFERENCES `cpe_host` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cpe_custom
-- ----------------------------

-- ----------------------------
-- Table structure for `cpe_custom_info`
-- ----------------------------
DROP TABLE IF EXISTS `cpe_custom_info`;
CREATE TABLE `cpe_custom_info` (
  `ap_id` int(5) NOT NULL DEFAULT '0',
  `serial_code` varchar(30) DEFAULT NULL,
  `location_id` varchar(15) DEFAULT NULL,
  `industry_id` varchar(15) DEFAULT NULL,
  `cover` varchar(30) DEFAULT NULL,
  `power_location` varchar(30) DEFAULT NULL,
  `trace` varchar(17) DEFAULT NULL,
  `detect` varchar(17) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ap_id`),
  KEY `ap_id` (`ap_id`),
  CONSTRAINT `cpe_custom_info_ibfk_1` FOREIGN KEY (`ap_id`) REFERENCES `cpe_host` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cpe_custom_info
-- ----------------------------
INSERT INTO `cpe_custom_info` VALUES ('1', null, null, null, null, null, '2.2.2.2', '', '2014-10-23 20:35:08');
INSERT INTO `cpe_custom_info` VALUES ('2', null, null, null, null, null, 'off', '', '2014-10-27 15:03:11');

-- ----------------------------
-- Table structure for `cpe_device_log`
-- ----------------------------
DROP TABLE IF EXISTS `cpe_device_log`;
CREATE TABLE `cpe_device_log` (
  `id` varchar(17) NOT NULL,
  `host_id` int(10) DEFAULT NULL,
  `message` varchar(300) DEFAULT NULL,
  `log_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `host_id` (`host_id`),
  CONSTRAINT `cpe_device_log_ibfk_1` FOREIGN KEY (`host_id`) REFERENCES `cpe_host` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cpe_device_log
-- ----------------------------
INSERT INTO `cpe_device_log` VALUES ('1003073400079556', '4', '设备重启', '2014-11-06 10:50:58');
INSERT INTO `cpe_device_log` VALUES ('1003112412714776', '1', '设备重启', '2014-11-06 10:51:37');
INSERT INTO `cpe_device_log` VALUES ('1003146434713075', '4', '获取设备参数', '2014-11-06 10:52:11');
INSERT INTO `cpe_device_log` VALUES ('1003257579001179', '1', '设备重启', '2014-11-06 10:54:02');
INSERT INTO `cpe_device_log` VALUES ('1003330477865627', '1', '获取设备参数', '2014-11-06 10:55:15');
INSERT INTO `cpe_device_log` VALUES ('1003853392769085', '1', '设备不在线', '2014-11-06 11:04:56');
INSERT INTO `cpe_device_log` VALUES ('1003854155041294', '1', '设备重启', '2014-11-06 11:03:59');
INSERT INTO `cpe_device_log` VALUES ('1003927026650288', '1', '获取设备参数', '2014-11-06 11:05:12');
INSERT INTO `cpe_device_log` VALUES ('1014556673907773', '1', '设备不在线', '2014-11-06 14:03:20');
INSERT INTO `cpe_device_log` VALUES ('1041913263342927', '4', '设备重启', '2014-11-06 21:38:20');
INSERT INTO `cpe_device_log` VALUES ('1042046078971982', '1', '设备不在线', '2014-11-06 21:41:31');
INSERT INTO `cpe_device_log` VALUES ('1123773048108185', '4', '设备不在线', '2014-11-07 20:23:46');
INSERT INTO `cpe_device_log` VALUES ('13071936625009', '6', '设备不在线', '2014-11-08 18:34:22');
INSERT INTO `cpe_device_log` VALUES ('158780391563001', '1', '设备上线', '2014-11-10 11:02:08');
INSERT INTO `cpe_device_log` VALUES ('158855787477885', '1', '获取设备参数', '2014-11-10 11:03:23');
INSERT INTO `cpe_device_log` VALUES ('170288954876944', '1', '设备上线', '2014-11-10 14:13:57');
INSERT INTO `cpe_device_log` VALUES ('170363526530139', '1', '获取设备参数', '2014-11-10 14:15:11');
INSERT INTO `cpe_device_log` VALUES ('170828771776139', '1', '设备上线', '2014-11-10 14:22:57');
INSERT INTO `cpe_device_log` VALUES ('175719871978463', '1', '设备上线', '2014-11-10 15:44:28');
INSERT INTO `cpe_device_log` VALUES ('175802882306268', '1', '获取设备参数', '2014-11-10 15:45:51');
INSERT INTO `cpe_device_log` VALUES ('234904947113838', '2', '设备不在线', '2014-10-28 13:27:57');
INSERT INTO `cpe_device_log` VALUES ('236216351278438', '2', '设备不在线', '2014-10-28 13:49:48');
INSERT INTO `cpe_device_log` VALUES ('238433273614816', '2', '设备不在线', '2014-10-28 14:26:45');
INSERT INTO `cpe_device_log` VALUES ('307962918063059', '2', '设备不在线', '2014-10-29 09:45:42');
INSERT INTO `cpe_device_log` VALUES ('316344512066328', '1', '设备重启', '2014-10-29 12:04:26');
INSERT INTO `cpe_device_log` VALUES ('316427138523105', '1', '获取设备参数', '2014-10-29 12:05:48');
INSERT INTO `cpe_device_log` VALUES ('324642241500697', '1', '设备重启', '2014-10-29 14:22:44');
INSERT INTO `cpe_device_log` VALUES ('324715773800790', '1', '获取设备参数', '2014-10-29 14:23:58');
INSERT INTO `cpe_device_log` VALUES ('325748038975139', '1', '设备不在线', '2014-10-29 14:42:08');
INSERT INTO `cpe_device_log` VALUES ('337544718779561', '2', '设备重启', '2014-10-29 17:57:47');
INSERT INTO `cpe_device_log` VALUES ('337610994488746', '2', '获取设备参数', '2014-10-29 17:58:53');
INSERT INTO `cpe_device_log` VALUES ('342173214092005', '2', '设备不在线', '2014-10-29 19:14:56');
INSERT INTO `cpe_device_log` VALUES ('345956447273269', '1', '设备重启', '2014-10-29 20:17:59');
INSERT INTO `cpe_device_log` VALUES ('346029149782370', '1', '获取设备参数', '2014-10-29 20:19:12');
INSERT INTO `cpe_device_log` VALUES ('355366807692185', '1', '设备重启', '2014-10-29 22:54:50');
INSERT INTO `cpe_device_log` VALUES ('355446004992438', '1', '获取设备参数', '2014-10-29 22:56:09');
INSERT INTO `cpe_device_log` VALUES ('355875839975467', '1', '设备不在线', '2014-10-29 23:04:17');
INSERT INTO `cpe_device_log` VALUES ('355966238465314', '1', '设备重启', '2014-10-29 23:04:49');
INSERT INTO `cpe_device_log` VALUES ('356045321722648', '1', '获取设备参数', '2014-10-29 23:06:08');
INSERT INTO `cpe_device_log` VALUES ('403731843285829', '1', '设备重启', '2014-10-30 12:21:01');
INSERT INTO `cpe_device_log` VALUES ('403805382874805', '1', '获取设备参数', '2014-10-30 12:22:14');
INSERT INTO `cpe_device_log` VALUES ('4098303259081', '6', '设备不在线', '2014-11-08 16:04:48');
INSERT INTO `cpe_device_log` VALUES ('4102761504604', '6', '设备重启', '2014-11-08 16:03:54');
INSERT INTO `cpe_device_log` VALUES ('411540817529841', '1', '设备不在线', '2014-10-30 14:32:09');
INSERT INTO `cpe_device_log` VALUES ('411541916186338', '1', '设备重启', '2014-10-30 14:31:11');
INSERT INTO `cpe_device_log` VALUES ('411621354499618', '1', '获取设备参数', '2014-10-30 14:32:31');
INSERT INTO `cpe_device_log` VALUES ('413599639922241', '1', '设备不在线', '2014-10-30 15:06:27');
INSERT INTO `cpe_device_log` VALUES ('416521619178864', '2', '设备重启', '2014-10-30 15:54:11');
INSERT INTO `cpe_device_log` VALUES ('416719639409797', '2', '设备不在线', '2014-10-30 15:58:27');
INSERT INTO `cpe_device_log` VALUES ('4182369053319', '6', '获取设备参数', '2014-11-08 16:05:14');
INSERT INTO `cpe_device_log` VALUES ('420919640681706', '1', '设备不在线', '2014-10-30 17:08:28');
INSERT INTO `cpe_device_log` VALUES ('4736657647930', '6', '设备重启', '2014-11-08 16:14:28');
INSERT INTO `cpe_device_log` VALUES ('4808922698061', '6', '获取设备参数', '2014-11-08 16:15:40');
INSERT INTO `cpe_device_log` VALUES ('572220936428949', '4', '设备不在线', '2014-11-01 11:10:23');
INSERT INTO `cpe_device_log` VALUES ('572224489568325', '4', '设备重启', '2014-11-01 11:09:29');
INSERT INTO `cpe_device_log` VALUES ('572305977217109', '4', '获取设备参数', '2014-11-01 11:10:50');
INSERT INTO `cpe_device_log` VALUES ('572617909430779', '4', '设备重启', '2014-11-01 11:16:02');
INSERT INTO `cpe_device_log` VALUES ('572690514463987', '4', '获取设备参数', '2014-11-01 11:17:15');
INSERT INTO `cpe_device_log` VALUES ('572803270591466', '4', '设备重启', '2014-11-01 11:19:07');
INSERT INTO `cpe_device_log` VALUES ('572875678812091', '4', '获取设备参数', '2014-11-01 11:20:20');
INSERT INTO `cpe_device_log` VALUES ('591771655583643', '4', '设备不在线', '2014-11-01 16:36:15');
INSERT INTO `cpe_device_log` VALUES ('659646128461786', '5', '设备接入', '2014-11-02 11:26:38');
INSERT INTO `cpe_device_log` VALUES ('659694093846669', '5', '设备不在线', '2014-11-02 11:28:24');
INSERT INTO `cpe_device_log` VALUES ('659718692107685', '5', '获取设备参数', '2014-11-02 11:27:51');
INSERT INTO `cpe_device_log` VALUES ('663574892259295', '5', '设备不在线', '2014-11-02 12:33:05');
INSERT INTO `cpe_device_log` VALUES ('67578367530496', '6', '设备不在线', '2014-11-09 09:42:56');
INSERT INTO `cpe_device_log` VALUES ('67581894762769', '6', '设备重启', '2014-11-09 09:42:01');
INSERT INTO `cpe_device_log` VALUES ('676193559100787', '5', '设备重启', '2014-11-02 16:02:26');
INSERT INTO `cpe_device_log` VALUES ('676273273052826', '5', '获取设备参数', '2014-11-02 16:03:46');
INSERT INTO `cpe_device_log` VALUES ('67654515875333', '6', '获取设备参数', '2014-11-09 09:43:14');
INSERT INTO `cpe_device_log` VALUES ('678753827976556', '5', '设备不在线', '2014-11-02 16:46:05');
INSERT INTO `cpe_device_log` VALUES ('692711813269971', '5', '设备不在线', '2014-11-02 20:38:43');
INSERT INTO `cpe_device_log` VALUES ('693594095688354', '5', '设备重启', '2014-11-02 20:52:27');
INSERT INTO `cpe_device_log` VALUES ('693642470152420', '5', '更新固件,文件名:a-hp-wifiant-1.2.28.bin,文件大小:6619136KB', '2014-11-02 20:53:16');
INSERT INTO `cpe_device_log` VALUES ('693667689019571', '5', '获取设备参数', '2014-11-02 20:53:41');
INSERT INTO `cpe_device_log` VALUES ('693763438018836', '5', '设备重启', '2014-11-02 20:55:17');
INSERT INTO `cpe_device_log` VALUES ('693839009080314', '5', '获取设备参数', '2014-11-02 20:56:32');
INSERT INTO `cpe_device_log` VALUES ('695544838250922', '5', '设备不在线', '2014-11-02 21:25:56');
INSERT INTO `cpe_device_log` VALUES ('695964838707451', '5', '设备不在线', '2014-11-02 21:32:56');
INSERT INTO `cpe_device_log` VALUES ('702873543439032', '5', '设备重启', '2014-11-02 23:27:07');
INSERT INTO `cpe_device_log` VALUES ('702949831476607', '5', '获取设备参数', '2014-11-02 23:28:23');
INSERT INTO `cpe_device_log` VALUES ('740915847709554', '4', '设备不在线', '2014-11-03 10:02:14');
INSERT INTO `cpe_device_log` VALUES ('740915848001520', '5', '设备不在线', '2014-11-03 10:02:14');
INSERT INTO `cpe_device_log` VALUES ('740930633008844', '4', '设备重启', '2014-11-03 10:01:30');
INSERT INTO `cpe_device_log` VALUES ('741010110425020', '4', '获取设备参数', '2014-11-03 10:02:49');
INSERT INTO `cpe_device_log` VALUES ('754196901498510', '4', '设备不在线', '2014-11-03 13:43:35');
INSERT INTO `cpe_device_log` VALUES ('785453836187343', '4', '设备重启', '2014-11-03 22:23:35');
INSERT INTO `cpe_device_log` VALUES ('785526809182803', '4', '获取设备参数', '2014-11-03 22:24:48');
INSERT INTO `cpe_device_log` VALUES ('832368099247506', '4', '设备重启', '2014-11-04 11:25:35');
INSERT INTO `cpe_device_log` VALUES ('832440515375983', '4', '获取设备参数', '2014-11-04 11:26:48');
INSERT INTO `cpe_device_log` VALUES ('839313168117471', '4', '设备不在线', '2014-11-04 13:21:44');
INSERT INTO `cpe_device_log` VALUES ('92501298292477', '6', '设备下线', '2014-11-09 16:38:20');

-- ----------------------------
-- Table structure for `cpe_host`
-- ----------------------------
DROP TABLE IF EXISTS `cpe_host`;
CREATE TABLE `cpe_host` (
  `id` int(5) NOT NULL DEFAULT '0',
  `name` varchar(30) DEFAULT NULL,
  `serial_number` varchar(30) DEFAULT NULL,
  `product_class` varchar(30) DEFAULT NULL,
  `software_version` varchar(20) DEFAULT NULL,
  `hardware_version` varchar(20) DEFAULT NULL,
  `branch_id` varchar(30) DEFAULT NULL,
  `ssid` varchar(50) DEFAULT NULL,
  `ip_address` varchar(15) DEFAULT NULL,
  `stun` varchar(100) DEFAULT NULL,
  `channel` varchar(10) DEFAULT NULL,
  `mode` varchar(10) DEFAULT NULL,
  `online` tinyint(1) DEFAULT NULL,
  `up_time` datetime DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  `trace` varchar(30) DEFAULT NULL,
  `detect` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_serial_no` (`serial_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cpe_host
-- ----------------------------
INSERT INTO `cpe_host` VALUES ('1', '78:D3:8D:AB:9D:A0', '78:D3:8D:AB:9D:A0', 'SmartCloudAP', 'v1.2.29', 'RW2400NGHSC-A', '00001', '微信认证/随机Portal/5.8G-微信认证/5.8G-随机Portal', '192.168.1.104', '192.168.1.104-7547', 'auto', 'AP', '1', '2014-11-10 15:44:28', '2014-11-10 15:56:51', '4.4.4.4', 'off');
INSERT INTO `cpe_host` VALUES ('2', '78:D3:8D:AF:86:90', '78:D3:8D:AF:86:90', 'SmartCloudAP', 'v1.2.25', 'RW2400NGHSC-M', '00001', 'WiFiBeijing', '192.168.1.192', '0.0.0.0-7547', 'auto', 'AP', '0', '2014-10-30 15:54:11', '2014-10-30 15:54:11', 'off', null);
INSERT INTO `cpe_host` VALUES ('4', '78:D3:8D:B2:18:3C', '78:D3:8D:B2:18:3C', 'SmartCloudAP', 'v1.2.29', 'RW2400NGHSC', '00002', 'RW2458_B2183C', '192.168.1.138', '192.168.1.138-7547', 'auto', 'AP', '0', '2014-11-06 21:38:20', '2014-11-07 16:49:17', '192.168.1.100', '');
INSERT INTO `cpe_host` VALUES ('5', '7号仓库', '78:D3:8D:AB:99:88', 'SmartCloudAP', 'v1.2.28', 'RW2400NGHSC', '00001', '速方软件/soofound', '192.168.1.101', '192.168.1.101-7547', 'auto', 'AP', '0', '2014-11-02 23:27:07', '2014-11-02 23:41:09', 'off', '');
INSERT INTO `cpe_host` VALUES ('6', '78:D3:8D:AB:99:58', '78:D3:8D:AB:99:58', 'SmartCloudAP', 'v1.2.28', 'RW2400NGHSC', '00002', '速方软件/soofound', '192.168.1.100', '192.168.1.100-7547', 'auto', 'AP', '0', '2014-11-09 09:42:01', '2014-11-09 21:04:14', 'off', '');

-- ----------------------------
-- Table structure for `cpe_host_property`
-- ----------------------------
DROP TABLE IF EXISTS `cpe_host_property`;
CREATE TABLE `cpe_host_property` (
  `host_id` int(10) NOT NULL,
  `pid` int(5) NOT NULL DEFAULT '0',
  `value` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`host_id`,`pid`),
  CONSTRAINT `cpe_host_property_ibfk_1` FOREIGN KEY (`host_id`) REFERENCES `cpe_host` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cpe_host_property
-- ----------------------------
INSERT INTO `cpe_host_property` VALUES ('1', '1', 'REDWAVE');
INSERT INTO `cpe_host_property` VALUES ('1', '2', 'SmartCloudAP');
INSERT INTO `cpe_host_property` VALUES ('1', '3', 'Redwave RW2400NGHSC Board');
INSERT INTO `cpe_host_property` VALUES ('1', '4', '8');
INSERT INTO `cpe_host_property` VALUES ('1', '5', 'www.wifiant.cn');
INSERT INTO `cpe_host_property` VALUES ('1', '6', 'www.wifiant.cn');
INSERT INTO `cpe_host_property` VALUES ('1', '7', '78:D3:8D:AB:9D:A0');
INSERT INTO `cpe_host_property` VALUES ('1', '8', '0');
INSERT INTO `cpe_host_property` VALUES ('1', '9', '7096 KB / 8192 KB 86%');
INSERT INTO `cpe_host_property` VALUES ('1', '10', '');
INSERT INTO `cpe_host_property` VALUES ('1', '11', '');
INSERT INTO `cpe_host_property` VALUES ('1', '12', 'RW2400NGHSC-A');
INSERT INTO `cpe_host_property` VALUES ('1', '13', '89');
INSERT INTO `cpe_host_property` VALUES ('1', '14', 'v1.2.29');
INSERT INTO `cpe_host_property` VALUES ('1', '15', '0');
INSERT INTO `cpe_host_property` VALUES ('1', '16', '随机Portal');
INSERT INTO `cpe_host_property` VALUES ('1', '17', '89');
INSERT INTO `cpe_host_property` VALUES ('1', '18', '89');
INSERT INTO `cpe_host_property` VALUES ('1', '19', '0');
INSERT INTO `cpe_host_property` VALUES ('1', '20', '240');
INSERT INTO `cpe_host_property` VALUES ('1', '21', '2000');
INSERT INTO `cpe_host_property` VALUES ('1', '22', '32');
INSERT INTO `cpe_host_property` VALUES ('1', '23', '0');
INSERT INTO `cpe_host_property` VALUES ('1', '24', '1');
INSERT INTO `cpe_host_property` VALUES ('1', '25', '微信认证');
INSERT INTO `cpe_host_property` VALUES ('1', '26', '192.168.1.104');
INSERT INTO `cpe_host_property` VALUES ('1', '27', 'auto');
INSERT INTO `cpe_host_property` VALUES ('1', '28', '100');
INSERT INTO `cpe_host_property` VALUES ('1', '29', '1');
INSERT INTO `cpe_host_property` VALUES ('1', '30', 'www.wifiant.cn');
INSERT INTO `cpe_host_property` VALUES ('1', '31', '0');
INSERT INTO `cpe_host_property` VALUES ('1', '32', '39544 KB / 126752 KB 31%');
INSERT INTO `cpe_host_property` VALUES ('1', '33', '8%');
INSERT INTO `cpe_host_property` VALUES ('1', '35', '3434');
INSERT INTO `cpe_host_property` VALUES ('1', '36', '0');
INSERT INTO `cpe_host_property` VALUES ('1', '37', '5.8G-微信认证');
INSERT INTO `cpe_host_property` VALUES ('1', '38', '5.8G-随机Portal');
INSERT INTO `cpe_host_property` VALUES ('1', '39', '149');
INSERT INTO `cpe_host_property` VALUES ('1', '40', '89');
INSERT INTO `cpe_host_property` VALUES ('1', '41', '20');
INSERT INTO `cpe_host_property` VALUES ('1', '42', '20');
INSERT INTO `cpe_host_property` VALUES ('1', '43', 'HT20');
INSERT INTO `cpe_host_property` VALUES ('1', '44', '');
INSERT INTO `cpe_host_property` VALUES ('1', '45', '');
INSERT INTO `cpe_host_property` VALUES ('1', '46', 'AP');
INSERT INTO `cpe_host_property` VALUES ('1', '47', '');
INSERT INTO `cpe_host_property` VALUES ('1', '48', '0');
INSERT INTO `cpe_host_property` VALUES ('1', '49', '102400');
INSERT INTO `cpe_host_property` VALUES ('1', '50', '102400');
INSERT INTO `cpe_host_property` VALUES ('2', '1', 'Wireless City (Beijing) Technology Co.,Ltd.');
INSERT INTO `cpe_host_property` VALUES ('2', '2', 'SmartCloudAP');
INSERT INTO `cpe_host_property` VALUES ('2', '3', 'Redwave RW2400NSC2-WALL Board');
INSERT INTO `cpe_host_property` VALUES ('2', '4', '8');
INSERT INTO `cpe_host_property` VALUES ('2', '5', 'www.wifiant.cn');
INSERT INTO `cpe_host_property` VALUES ('2', '6', 'www.wifiant.cn');
INSERT INTO `cpe_host_property` VALUES ('2', '7', '78:D3:8D:AF:86:90');
INSERT INTO `cpe_host_property` VALUES ('2', '8', '0');
INSERT INTO `cpe_host_property` VALUES ('2', '9', '5936 KB / 8192 KB 72%');
INSERT INTO `cpe_host_property` VALUES ('2', '10', '');
INSERT INTO `cpe_host_property` VALUES ('2', '11', '');
INSERT INTO `cpe_host_property` VALUES ('2', '12', 'AP5300-W');
INSERT INTO `cpe_host_property` VALUES ('2', '13', '89');
INSERT INTO `cpe_host_property` VALUES ('2', '14', 'v1.2.25');
INSERT INTO `cpe_host_property` VALUES ('2', '15', '0');
INSERT INTO `cpe_host_property` VALUES ('2', '16', 'N/A');
INSERT INTO `cpe_host_property` VALUES ('2', '17', '89');
INSERT INTO `cpe_host_property` VALUES ('2', '18', '89');
INSERT INTO `cpe_host_property` VALUES ('2', '19', '0');
INSERT INTO `cpe_host_property` VALUES ('2', '20', '240');
INSERT INTO `cpe_host_property` VALUES ('2', '21', '2000');
INSERT INTO `cpe_host_property` VALUES ('2', '22', '32');
INSERT INTO `cpe_host_property` VALUES ('2', '23', '0');
INSERT INTO `cpe_host_property` VALUES ('2', '24', '1');
INSERT INTO `cpe_host_property` VALUES ('2', '25', 'WiFiBeijing');
INSERT INTO `cpe_host_property` VALUES ('2', '26', '192.168.1.150');
INSERT INTO `cpe_host_property` VALUES ('2', '27', 'auto');
INSERT INTO `cpe_host_property` VALUES ('2', '28', '100');
INSERT INTO `cpe_host_property` VALUES ('2', '29', '1');
INSERT INTO `cpe_host_property` VALUES ('2', '30', 'www.wifiant.cn');
INSERT INTO `cpe_host_property` VALUES ('2', '31', '0');
INSERT INTO `cpe_host_property` VALUES ('2', '32', '22636 KB / 29296 KB 77%');
INSERT INTO `cpe_host_property` VALUES ('2', '33', '6%');
INSERT INTO `cpe_host_property` VALUES ('2', '35', '582');
INSERT INTO `cpe_host_property` VALUES ('2', '36', '0');
INSERT INTO `cpe_host_property` VALUES ('2', '37', '');
INSERT INTO `cpe_host_property` VALUES ('2', '38', 'N/A');
INSERT INTO `cpe_host_property` VALUES ('2', '39', '');
INSERT INTO `cpe_host_property` VALUES ('2', '40', '89');
INSERT INTO `cpe_host_property` VALUES ('2', '41', '');
INSERT INTO `cpe_host_property` VALUES ('2', '42', '20');
INSERT INTO `cpe_host_property` VALUES ('2', '43', '');
INSERT INTO `cpe_host_property` VALUES ('2', '44', '');
INSERT INTO `cpe_host_property` VALUES ('2', '45', '');
INSERT INTO `cpe_host_property` VALUES ('2', '46', 'AP');
INSERT INTO `cpe_host_property` VALUES ('2', '47', '');
INSERT INTO `cpe_host_property` VALUES ('2', '48', '0');
INSERT INTO `cpe_host_property` VALUES ('2', '49', '102400');
INSERT INTO `cpe_host_property` VALUES ('2', '50', '102400');
INSERT INTO `cpe_host_property` VALUES ('4', '1', 'REDWAVE');
INSERT INTO `cpe_host_property` VALUES ('4', '2', 'SmartCloudAP');
INSERT INTO `cpe_host_property` VALUES ('4', '3', 'Redwave RW2400NGHSC Board');
INSERT INTO `cpe_host_property` VALUES ('4', '4', '8');
INSERT INTO `cpe_host_property` VALUES ('4', '5', '119.6.241.44');
INSERT INTO `cpe_host_property` VALUES ('4', '6', 'www.wifiant.cn');
INSERT INTO `cpe_host_property` VALUES ('4', '7', '78:D3:8D:B2:18:3C');
INSERT INTO `cpe_host_property` VALUES ('4', '8', '0');
INSERT INTO `cpe_host_property` VALUES ('4', '9', '6908 KB / 8192 KB 84%');
INSERT INTO `cpe_host_property` VALUES ('4', '10', '');
INSERT INTO `cpe_host_property` VALUES ('4', '11', '');
INSERT INTO `cpe_host_property` VALUES ('4', '12', 'RW2400NGHSC');
INSERT INTO `cpe_host_property` VALUES ('4', '13', '89');
INSERT INTO `cpe_host_property` VALUES ('4', '14', 'v1.2.29');
INSERT INTO `cpe_host_property` VALUES ('4', '15', '0');
INSERT INTO `cpe_host_property` VALUES ('4', '16', 'N/A');
INSERT INTO `cpe_host_property` VALUES ('4', '17', '89');
INSERT INTO `cpe_host_property` VALUES ('4', '18', '89');
INSERT INTO `cpe_host_property` VALUES ('4', '19', '0');
INSERT INTO `cpe_host_property` VALUES ('4', '20', '240');
INSERT INTO `cpe_host_property` VALUES ('4', '21', '2000');
INSERT INTO `cpe_host_property` VALUES ('4', '22', '32');
INSERT INTO `cpe_host_property` VALUES ('4', '23', '1');
INSERT INTO `cpe_host_property` VALUES ('4', '24', '1');
INSERT INTO `cpe_host_property` VALUES ('4', '25', 'RW2458_B2183C');
INSERT INTO `cpe_host_property` VALUES ('4', '26', '192.168.1.161');
INSERT INTO `cpe_host_property` VALUES ('4', '27', 'auto');
INSERT INTO `cpe_host_property` VALUES ('4', '28', '100');
INSERT INTO `cpe_host_property` VALUES ('4', '29', '1');
INSERT INTO `cpe_host_property` VALUES ('4', '30', 'www.wifiant.cn');
INSERT INTO `cpe_host_property` VALUES ('4', '31', '0');
INSERT INTO `cpe_host_property` VALUES ('4', '32', '31928 KB / 126752 KB 25%');
INSERT INTO `cpe_host_property` VALUES ('4', '33', '13%');
INSERT INTO `cpe_host_property` VALUES ('4', '35', '6564');
INSERT INTO `cpe_host_property` VALUES ('4', '36', '0');
INSERT INTO `cpe_host_property` VALUES ('4', '37', '');
INSERT INTO `cpe_host_property` VALUES ('4', '38', 'N/A');
INSERT INTO `cpe_host_property` VALUES ('4', '39', '');
INSERT INTO `cpe_host_property` VALUES ('4', '40', '89');
INSERT INTO `cpe_host_property` VALUES ('4', '41', '');
INSERT INTO `cpe_host_property` VALUES ('4', '42', '20');
INSERT INTO `cpe_host_property` VALUES ('4', '43', '');
INSERT INTO `cpe_host_property` VALUES ('4', '44', '');
INSERT INTO `cpe_host_property` VALUES ('4', '45', '');
INSERT INTO `cpe_host_property` VALUES ('4', '46', 'AP');
INSERT INTO `cpe_host_property` VALUES ('4', '47', '');
INSERT INTO `cpe_host_property` VALUES ('4', '48', '0');
INSERT INTO `cpe_host_property` VALUES ('4', '49', '102400');
INSERT INTO `cpe_host_property` VALUES ('4', '50', '102400');
INSERT INTO `cpe_host_property` VALUES ('5', '1', 'REDWAVE');
INSERT INTO `cpe_host_property` VALUES ('5', '2', 'SmartCloudAP');
INSERT INTO `cpe_host_property` VALUES ('5', '3', 'Redwave RW2400NGHSC Board');
INSERT INTO `cpe_host_property` VALUES ('5', '4', '8');
INSERT INTO `cpe_host_property` VALUES ('5', '5', 'www.wifiant.cn');
INSERT INTO `cpe_host_property` VALUES ('5', '6', 'www.wifiant.cn');
INSERT INTO `cpe_host_property` VALUES ('5', '7', '78:D3:8D:AB:99:58');
INSERT INTO `cpe_host_property` VALUES ('5', '8', '0');
INSERT INTO `cpe_host_property` VALUES ('5', '9', '6932 KB / 8192 KB 84%');
INSERT INTO `cpe_host_property` VALUES ('5', '10', '');
INSERT INTO `cpe_host_property` VALUES ('5', '11', '');
INSERT INTO `cpe_host_property` VALUES ('5', '12', 'RW2400NGHSC');
INSERT INTO `cpe_host_property` VALUES ('5', '13', '89');
INSERT INTO `cpe_host_property` VALUES ('5', '14', 'v1.2.28');
INSERT INTO `cpe_host_property` VALUES ('5', '15', '1');
INSERT INTO `cpe_host_property` VALUES ('5', '16', 'soofound');
INSERT INTO `cpe_host_property` VALUES ('5', '17', '89');
INSERT INTO `cpe_host_property` VALUES ('5', '18', '89');
INSERT INTO `cpe_host_property` VALUES ('5', '19', '0');
INSERT INTO `cpe_host_property` VALUES ('5', '20', '240');
INSERT INTO `cpe_host_property` VALUES ('5', '21', '2000');
INSERT INTO `cpe_host_property` VALUES ('5', '22', '32');
INSERT INTO `cpe_host_property` VALUES ('5', '23', '1');
INSERT INTO `cpe_host_property` VALUES ('5', '24', '1');
INSERT INTO `cpe_host_property` VALUES ('5', '25', '速方软件');
INSERT INTO `cpe_host_property` VALUES ('5', '26', '192.168.1.101');
INSERT INTO `cpe_host_property` VALUES ('5', '27', 'auto');
INSERT INTO `cpe_host_property` VALUES ('5', '28', '100');
INSERT INTO `cpe_host_property` VALUES ('5', '29', '1');
INSERT INTO `cpe_host_property` VALUES ('5', '30', 'www.wifiant.cn');
INSERT INTO `cpe_host_property` VALUES ('5', '31', '0');
INSERT INTO `cpe_host_property` VALUES ('5', '32', '36384 KB / 126752 KB 28%');
INSERT INTO `cpe_host_property` VALUES ('5', '33', '6%');
INSERT INTO `cpe_host_property` VALUES ('5', '35', '4733');
INSERT INTO `cpe_host_property` VALUES ('5', '36', '0');
INSERT INTO `cpe_host_property` VALUES ('5', '37', '');
INSERT INTO `cpe_host_property` VALUES ('5', '38', 'N/A');
INSERT INTO `cpe_host_property` VALUES ('5', '39', '');
INSERT INTO `cpe_host_property` VALUES ('5', '40', '89');
INSERT INTO `cpe_host_property` VALUES ('5', '41', '');
INSERT INTO `cpe_host_property` VALUES ('5', '42', '20');
INSERT INTO `cpe_host_property` VALUES ('5', '43', '');
INSERT INTO `cpe_host_property` VALUES ('5', '44', '');
INSERT INTO `cpe_host_property` VALUES ('5', '45', '');
INSERT INTO `cpe_host_property` VALUES ('5', '46', 'AP');
INSERT INTO `cpe_host_property` VALUES ('5', '47', '');
INSERT INTO `cpe_host_property` VALUES ('5', '48', '');
INSERT INTO `cpe_host_property` VALUES ('5', '49', '');
INSERT INTO `cpe_host_property` VALUES ('5', '50', '');
INSERT INTO `cpe_host_property` VALUES ('6', '1', 'REDWAVE');
INSERT INTO `cpe_host_property` VALUES ('6', '2', 'SmartCloudAP');
INSERT INTO `cpe_host_property` VALUES ('6', '3', 'Redwave RW2400NGHSC Board');
INSERT INTO `cpe_host_property` VALUES ('6', '4', '8');
INSERT INTO `cpe_host_property` VALUES ('6', '5', 'www.wifiant.cn');
INSERT INTO `cpe_host_property` VALUES ('6', '6', 'www.wifiant.cn');
INSERT INTO `cpe_host_property` VALUES ('6', '7', '78:D3:8D:AB:99:58');
INSERT INTO `cpe_host_property` VALUES ('6', '8', '0');
INSERT INTO `cpe_host_property` VALUES ('6', '9', '6976 KB / 8192 KB 85%');
INSERT INTO `cpe_host_property` VALUES ('6', '10', '');
INSERT INTO `cpe_host_property` VALUES ('6', '11', '');
INSERT INTO `cpe_host_property` VALUES ('6', '12', 'RW2400NGHSC');
INSERT INTO `cpe_host_property` VALUES ('6', '13', '89');
INSERT INTO `cpe_host_property` VALUES ('6', '14', 'v1.2.28');
INSERT INTO `cpe_host_property` VALUES ('6', '15', '1');
INSERT INTO `cpe_host_property` VALUES ('6', '16', 'soofound');
INSERT INTO `cpe_host_property` VALUES ('6', '17', '89');
INSERT INTO `cpe_host_property` VALUES ('6', '18', '89');
INSERT INTO `cpe_host_property` VALUES ('6', '19', '0');
INSERT INTO `cpe_host_property` VALUES ('6', '20', '240');
INSERT INTO `cpe_host_property` VALUES ('6', '21', '2000');
INSERT INTO `cpe_host_property` VALUES ('6', '22', '32');
INSERT INTO `cpe_host_property` VALUES ('6', '23', '1');
INSERT INTO `cpe_host_property` VALUES ('6', '24', '1');
INSERT INTO `cpe_host_property` VALUES ('6', '25', '速方软件');
INSERT INTO `cpe_host_property` VALUES ('6', '26', '192.168.1.100');
INSERT INTO `cpe_host_property` VALUES ('6', '27', 'auto');
INSERT INTO `cpe_host_property` VALUES ('6', '28', '100');
INSERT INTO `cpe_host_property` VALUES ('6', '29', '1');
INSERT INTO `cpe_host_property` VALUES ('6', '30', 'www.wifiant.cn');
INSERT INTO `cpe_host_property` VALUES ('6', '31', '0');
INSERT INTO `cpe_host_property` VALUES ('6', '32', '31884 KB / 126752 KB 25%');
INSERT INTO `cpe_host_property` VALUES ('6', '33', '10%');
INSERT INTO `cpe_host_property` VALUES ('6', '35', '2182');
INSERT INTO `cpe_host_property` VALUES ('6', '36', '0');
INSERT INTO `cpe_host_property` VALUES ('6', '37', '');
INSERT INTO `cpe_host_property` VALUES ('6', '38', 'N/A');
INSERT INTO `cpe_host_property` VALUES ('6', '39', '');
INSERT INTO `cpe_host_property` VALUES ('6', '40', '89');
INSERT INTO `cpe_host_property` VALUES ('6', '41', '');
INSERT INTO `cpe_host_property` VALUES ('6', '42', '20');
INSERT INTO `cpe_host_property` VALUES ('6', '43', '');
INSERT INTO `cpe_host_property` VALUES ('6', '44', '');
INSERT INTO `cpe_host_property` VALUES ('6', '45', '');
INSERT INTO `cpe_host_property` VALUES ('6', '46', 'AP');
INSERT INTO `cpe_host_property` VALUES ('6', '47', '');
INSERT INTO `cpe_host_property` VALUES ('6', '48', '');
INSERT INTO `cpe_host_property` VALUES ('6', '49', '');
INSERT INTO `cpe_host_property` VALUES ('6', '50', '');

-- ----------------------------
-- Table structure for `cpe_property`
-- ----------------------------
DROP TABLE IF EXISTS `cpe_property`;
CREATE TABLE `cpe_property` (
  `id` int(5) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `cn_name` varchar(30) DEFAULT NULL,
  `en_name` varchar(50) DEFAULT NULL,
  `tag` tinyint(1) DEFAULT NULL,
  `descr` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cpe_property
-- ----------------------------
INSERT INTO `cpe_property` VALUES ('1', 'InternetGatewayDevice.DeviceInfo.Manufacturer', '生产厂家', 'manufacturer', '0', null);
INSERT INTO `cpe_property` VALUES ('2', 'InternetGatewayDevice.DeviceInfo.ProductClass', '产品类型', 'product class', '0', null);
INSERT INTO `cpe_property` VALUES ('3', 'InternetGatewayDevice.DeviceInfo.ProductModel', '产品描述', 'product model', '0', null);
INSERT INTO `cpe_property` VALUES ('4', 'InternetGatewayDevice.DeviceInfo.flash_size', '闪存大小', 'flash size', '0', null);
INSERT INTO `cpe_property` VALUES ('5', 'InternetGatewayDevice.DeviceInfo.white_list1', '白名单', 'white list1', '1', '所填网址无需认证即可访问(默认服务器平台网址)');
INSERT INTO `cpe_property` VALUES ('6', 'InternetGatewayDevice.DeviceInfo.white_list2', '白名单2', 'white list2', '1', '所填网址无需认证即可访问(默认服务器平台网址)');
INSERT INTO `cpe_property` VALUES ('7', 'InternetGatewayDevice.DeviceInfo.SerialNumber', 'MAC地址', 'mac address', '0', null);
INSERT INTO `cpe_property` VALUES ('8', 'InternetGatewayDevice.DeviceInfo.wireless_terminal_isolate', '无线终端隔离', 'wireless terminal isolate', '1', '无线终端之间完全隔离,只能访问AP接入的固定网络');
INSERT INTO `cpe_property` VALUES ('9', 'InternetGatewayDevice.DeviceInfo.flash_utilization', 'flash利用率', 'flash utilization', '0', null);
INSERT INTO `cpe_property` VALUES ('10', 'InternetGatewayDevice.DeviceInfo.ip_outside', '对外管理IP', 'ip outside', '0', null);
INSERT INTO `cpe_property` VALUES ('11', 'InternetGatewayDevice.DeviceInfo.port_outside', '对外管理端口', 'port outside', '0', null);
INSERT INTO `cpe_property` VALUES ('12', 'InternetGatewayDevice.DeviceInfo.HardwareVersion', '产品型号', 'hardware version', '0', null);
INSERT INTO `cpe_property` VALUES ('13', 'InternetGatewayDevice.DeviceInfo.KickStaRssiLow', '漫游阀值', 'KickStaRssiLow', '2', '移动终端在设定的信号强度以外才允许漫游到另一个接入点');
INSERT INTO `cpe_property` VALUES ('14', 'InternetGatewayDevice.DeviceInfo.SoftwareVersion', '软件版本', 'software version', '0', null);
INSERT INTO `cpe_property` VALUES ('15', 'InternetGatewayDevice.DeviceInfo.weixing_pass', '微信放行', 'wechat', '1', '开启后无需认证即可使用微信与QQ发送信息等基本功能');
INSERT INTO `cpe_property` VALUES ('16', 'InternetGatewayDevice.DeviceInfo.wireless_ssid2', '无线SSID2', 'wireless ssid2', '2', '无线信号名称(中英文)');
INSERT INTO `cpe_property` VALUES ('17', 'InternetGatewayDevice.DeviceInfo.AuthRssiThres', '认证阀值', 'AuthRssiThres', '1', '移动终端在设定的信号强度以内才允许认证');
INSERT INTO `cpe_property` VALUES ('18', 'InternetGatewayDevice.DeviceInfo.AssocReqRssiThres', '接入阀值', 'AssocReqRssiThres', '1', '移动终端在设定的信号强度以内才允许接入');
INSERT INTO `cpe_property` VALUES ('19', 'InternetGatewayDevice.DeviceInfo.wshaper_switch', '限速开关', 'wshaper switch', '1', '开启或关闭限速功能');
INSERT INTO `cpe_property` VALUES ('20', 'InternetGatewayDevice.DeviceInfo.wshaper_uplink', '上行最大带宽', 'wshaper uplink', '1', '上行最大带宽值');
INSERT INTO `cpe_property` VALUES ('21', 'InternetGatewayDevice.DeviceInfo.wshaper_downlink', '下行最大带宽', 'wshaper downlink', '1', '下行最大带宽值');
INSERT INTO `cpe_property` VALUES ('22', 'InternetGatewayDevice.DeviceInfo.MaxStaNum', '无线终端最大并发连接数', 'wireless station number max', '1', null);
INSERT INTO `cpe_property` VALUES ('23', 'InternetGatewayDevice.DeviceInfo.wireless_current_station_number', '当前无线终端数量', 'wireless station number currently', '0', null);
INSERT INTO `cpe_property` VALUES ('24', 'InternetGatewayDevice.DeviceInfo.http_redirect_enable', '页面重定向开关', 'http redirect enable', '1', '将用户请求的网页转向其它网页');
INSERT INTO `cpe_property` VALUES ('25', 'InternetGatewayDevice.DeviceInfo.wireless_ssid', '无线SSID', 'wireless ssid', '2', '无线信号名称(中英文)');
INSERT INTO `cpe_property` VALUES ('26', 'InternetGatewayDevice.DeviceInfo.ip_address', 'IP地址', 'ip address', '0', null);
INSERT INTO `cpe_property` VALUES ('27', 'InternetGatewayDevice.DeviceInfo.wireless_channel', '无线信道', 'wireless channel', '2', '通信的通道,信号传输的媒介');
INSERT INTO `cpe_property` VALUES ('28', 'InternetGatewayDevice.DeviceInfo.wireless_tx_power', '无线输出功率', 'wireless tx power', '2', '发射信号的强度，就是最大功率的百分之几');
INSERT INTO `cpe_property` VALUES ('29', 'InternetGatewayDevice.DeviceInfo.free_no_auth', 'AC无效时开放wifi', 'fault pass', '1', '当AC不在线时，关闭wifi(0)，或直接放行(1)');
INSERT INTO `cpe_property` VALUES ('30', 'InternetGatewayDevice.DeviceInfo.white_list3', '白名单3', 'white list3', '1', '所填网址无需认证即可访问(默认服务器平台网址)');
INSERT INTO `cpe_property` VALUES ('31', 'InternetGatewayDevice.DeviceInfo.weixing_pass_interval', '微信放行时长', 'weixing pass interval', '0', '在微信放行时，该参数有效。以分钟为单位，为0就是不限制了。');
INSERT INTO `cpe_property` VALUES ('32', 'InternetGatewayDevice.DeviceInfo.memory_utilization', '内存利用率', 'memory utilization', '0', null);
INSERT INTO `cpe_property` VALUES ('33', 'InternetGatewayDevice.DeviceInfo.cpu_utilization', 'cpu利用率', 'cpu utilization', '0', null);
INSERT INTO `cpe_property` VALUES ('35', 'InternetGatewayDevice.DeviceInfo.UpTime', '上电时间', 'uptime', '0', null);
INSERT INTO `cpe_property` VALUES ('36', 'InternetGatewayDevice.DeviceInfo.wireless_hide_essid', '隐藏SSID', 'wireless hide essid', '1', '隐藏SSID');
INSERT INTO `cpe_property` VALUES ('37', 'InternetGatewayDevice.DeviceInfo.wireless_ssid_5G', '无线SSID(5G)', 'wireless ssid for 5G', '3', '无线信号名称(中英文)');
INSERT INTO `cpe_property` VALUES ('38', 'InternetGatewayDevice.DeviceInfo.wireless_ssid2_5G', '无线SSID2(5G)', 'wireless ssid2 for 5G', '3', '无线信号名称(中英文)');
INSERT INTO `cpe_property` VALUES ('39', 'InternetGatewayDevice.DeviceInfo.wireless_channel_5G', '无线信道(5G)', 'wireless channel for 5G', '3', '通信的通道,信号传输的媒介');
INSERT INTO `cpe_property` VALUES ('40', 'InternetGatewayDevice.DeviceInfo.KickStaRssiLow_5G', '漫游阀值(5G)', 'KickStaRssiLow_5G', '3', '移动终端在设定的信号强度以外才允许漫游到另一个接入点');
INSERT INTO `cpe_property` VALUES ('41', 'InternetGatewayDevice.DeviceInfo.wireless_tx_power_5G', '无线输出功率(5G)', 'wireless tx power for 5G', '3', '发射信号的强度，就是最大功率的百分之几');
INSERT INTO `cpe_property` VALUES ('42', 'InternetGatewayDevice.DeviceInfo.wireless_ht', '信道宽度', 'wireless_ht', '3', '信道宽度，单位是MHZ');
INSERT INTO `cpe_property` VALUES ('43', 'InternetGatewayDevice.DeviceInfo.wireless_ht_mode', '信道宽度(5G)', 'wireless_ht_mode', '3', '信道宽度，单位是MHZ');
INSERT INTO `cpe_property` VALUES ('44', 'InternetGatewayDevice.DeviceInfo.MonURL', '溯源开关', 'monitor url', '1', '开启或关闭溯源功能');
INSERT INTO `cpe_property` VALUES ('45', 'InternetGatewayDevice.DeviceInfo.ADbyebye', '广告过滤', 'filter AD', '1', '是否过滤广告');
INSERT INTO `cpe_property` VALUES ('46', 'InternetGatewayDevice.DeviceInfo.work_mode', '工作模式', 'work mode', '0', '设备工作模式，AP或路由');
INSERT INTO `cpe_property` VALUES ('47', 'InternetGatewayDevice.DeviceInfo.MonWifi', '探测开关', 'monitor wifi', '2', '开启或关闭探测功能');
INSERT INTO `cpe_property` VALUES ('48', 'InternetGatewayDevice.DeviceInfo.qos_enabled', 'QoS开关', 'qos enabled', '2', 'QoS开关');
INSERT INTO `cpe_property` VALUES ('49', 'InternetGatewayDevice.DeviceInfo.qos_upload', 'QoS上行带宽', 'qos upload', '2', 'QoS上行带宽');
INSERT INTO `cpe_property` VALUES ('50', 'InternetGatewayDevice.DeviceInfo.qos_download', 'QoS下行带宽', 'qos download', '2', 'QoS下行带宽');
INSERT INTO `cpe_property` VALUES ('101', 'InternetGatewayDevice.ManagementServer.Wifiant_URL', 'wifiant入口', 'wifiant URL', '10', null);
INSERT INTO `cpe_property` VALUES ('102', 'InternetGatewayDevice.ManagementServer.Username', 'ACS用户名', 'ACS Username', '10', null);
INSERT INTO `cpe_property` VALUES ('103', 'InternetGatewayDevice.ManagementServer.Password', 'ACS密码', 'ACS Password', '10', null);
INSERT INTO `cpe_property` VALUES ('104', 'InternetGatewayDevice.ManagementServer.URL', 'ACS入口', 'ACS URL', '10', null);
INSERT INTO `cpe_property` VALUES ('105', 'InternetGatewayDevice.ManagementServer.Wifiant_URL2', 'wifiant入口2', 'wifiant URL2', '10', null);

-- ----------------------------
-- Table structure for `cpe_software`
-- ----------------------------
DROP TABLE IF EXISTS `cpe_software`;
CREATE TABLE `cpe_software` (
  `id` int(5) NOT NULL,
  `descr` varchar(100) DEFAULT NULL,
  `tag` tinyint(1) DEFAULT NULL,
  `product_model` varchar(30) DEFAULT NULL,
  `version` varchar(20) DEFAULT NULL,
  `version_code` int(5) DEFAULT NULL,
  `size` int(10) DEFAULT NULL,
  `file_name` varchar(100) DEFAULT NULL,
  `md5` varchar(32) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  `branch_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cpe_software
-- ----------------------------
INSERT INTO `cpe_software` VALUES ('7', 'zhike', '2', 'RW2400NGHSC', null, '0', '0', null, null, '2014-08-31 10:53:57', '00007');
INSERT INTO `cpe_software` VALUES ('11', 'supoin_RW2400NGHSC_接入', '2', 'RW2400NGHSC', 'access', '0', '0', null, null, '2014-09-16 16:34:22', '00022');
INSERT INTO `cpe_software` VALUES ('12', '无线奉化_默认', '2', 'RW2400NGHSC', '', '0', '0', null, null, '2014-09-12 22:10:40', '0000800001');
INSERT INTO `cpe_software` VALUES ('16', 'sola_default', '2', 'RW2400NGHSC', '', '0', '0', null, null, '2014-09-19 14:42:22', '0001300001');
INSERT INTO `cpe_software` VALUES ('17', 'supoin_RW2400NSC_接入', '2', 'RW2400NSC', 'access', '0', '0', null, null, '2014-09-16 16:36:19', '00022');
INSERT INTO `cpe_software` VALUES ('22', '1', '2', 'RW2400NGHSC', 'default', '0', '0', null, null, '2014-09-20 18:10:59', '0001200005');
INSERT INTO `cpe_software` VALUES ('23', 'tt6556', '2', 'RW2458N-7620', 'default', '0', '0', null, null, '2014-09-24 07:25:37', '00017');
INSERT INTO `cpe_software` VALUES ('25', 'to_soofac', '2', 'RW2400NGHSC', 'access', '0', '0', null, null, '2014-10-10 21:42:23', '00003');
INSERT INTO `cpe_software` VALUES ('27', '24', '1', 'RW2400NGHSC', '24', '24000', '6619136', 'a-hp-wifiant-1.2.24.bin', 'b490b6abea9eac3a1a635bc7d1eea31b', '2014-10-24 00:25:05', null);
INSERT INTO `cpe_software` VALUES ('28', '1.2.28', '1', 'RW2400NGHSC', '1.2.28', '1228', '6619136', 'a-hp-wifiant-1.2.28.bin', '24413b4d3b5b228e8f8a750a4e4301f2', '2014-11-02 20:53:01', null);

-- ----------------------------
-- Table structure for `cpe_ssid`
-- ----------------------------
DROP TABLE IF EXISTS `cpe_ssid`;
CREATE TABLE `cpe_ssid` (
  `ap_id` int(5) DEFAULT NULL,
  `ife` int(1) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `portal_id` int(5) DEFAULT NULL,
  `policy_id` int(5) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  KEY `ap_id` (`ap_id`),
  CONSTRAINT `cpe_ssid_ibfk_1` FOREIGN KEY (`ap_id`) REFERENCES `cpe_host` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cpe_ssid
-- ----------------------------
INSERT INTO `cpe_ssid` VALUES ('1', '0', '微信认证', '12', '2', '1');
INSERT INTO `cpe_ssid` VALUES ('1', '1', '随机Portal', '14', '0', '1');
INSERT INTO `cpe_ssid` VALUES ('2', '0', 'WiFiBeijing', '0', '0', '1');
INSERT INTO `cpe_ssid` VALUES ('2', '1', 'N/A', '0', '0', '0');
INSERT INTO `cpe_ssid` VALUES ('4', '0', 'RW2458_B2183C', '14', '0', '1');
INSERT INTO `cpe_ssid` VALUES ('4', '1', 'N/A', '14', '0', '0');
INSERT INTO `cpe_ssid` VALUES ('5', '0', '速方软件', '12', '3', '1');
INSERT INTO `cpe_ssid` VALUES ('5', '1', 'soofound', '12', '3', '1');
INSERT INTO `cpe_ssid` VALUES ('6', '0', '速方软件', '14', '0', '1');
INSERT INTO `cpe_ssid` VALUES ('6', '1', 'soofound', '18', '8', '1');
INSERT INTO `cpe_ssid` VALUES ('6', '2', '', '14', '0', '1');
INSERT INTO `cpe_ssid` VALUES ('6', '3', 'N/A', '14', '0', '0');
INSERT INTO `cpe_ssid` VALUES ('1', '2', '5.8G-微信认证', '12', '1', '1');
INSERT INTO `cpe_ssid` VALUES ('1', '3', '5.8G-随机Portal', '14', '1', '1');

-- ----------------------------
-- Table structure for `cpe_upgrade`
-- ----------------------------
DROP TABLE IF EXISTS `cpe_upgrade`;
CREATE TABLE `cpe_upgrade` (
  `branch_id` varchar(50) NOT NULL DEFAULT '',
  `flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`branch_id`),
  CONSTRAINT `cpe_upgrade_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `admin_branch` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cpe_upgrade
-- ----------------------------

-- ----------------------------
-- Table structure for `membership_ap_group`
-- ----------------------------
DROP TABLE IF EXISTS `membership_ap_group`;
CREATE TABLE `membership_ap_group` (
  `ap_id` int(5) DEFAULT NULL,
  `group_id` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of membership_ap_group
-- ----------------------------
INSERT INTO `membership_ap_group` VALUES ('1', '0003');
INSERT INTO `membership_ap_group` VALUES ('1', '0001');
INSERT INTO `membership_ap_group` VALUES ('3', '0001');
INSERT INTO `membership_ap_group` VALUES ('5', '0005');

-- ----------------------------
-- Table structure for `portal_instance`
-- ----------------------------
DROP TABLE IF EXISTS `portal_instance`;
CREATE TABLE `portal_instance` (
  `id` int(5) NOT NULL DEFAULT '0',
  `name` varchar(20) DEFAULT NULL,
  `branch_id` varchar(30) DEFAULT NULL,
  `tid` varchar(30) DEFAULT NULL,
  `tag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of portal_instance
-- ----------------------------
INSERT INTO `portal_instance` VALUES ('8', 'P3', '00001', 'soofound.sp', '0');
INSERT INTO `portal_instance` VALUES ('9', 'P2', '00001', 'soofound.sp', '0');
INSERT INTO `portal_instance` VALUES ('10', 'P1', '00001', 'soofound.sp', '0');
INSERT INTO `portal_instance` VALUES ('12', '招财猫', '00001', 'soofound.ad_fortuneCat', '0');
INSERT INTO `portal_instance` VALUES ('13', 'dddccc', '00001', 'soofound.sp', '0');
INSERT INTO `portal_instance` VALUES ('14', 'ttttt2', '00001', 'soofound.sp', '1');
INSERT INTO `portal_instance` VALUES ('15', 'ssss', '00001', 'soofound.ad_TLayout', '0');
INSERT INTO `portal_instance` VALUES ('16', '默认PORTAL', null, 'soofound.sp', '1');
INSERT INTO `portal_instance` VALUES ('17', '默认PORTAL', null, 'soofound.sp', '1');
INSERT INTO `portal_instance` VALUES ('18', '默认PORTAL', '00002', 'soofound.sp', '1');

-- ----------------------------
-- Table structure for `portal_instance_page`
-- ----------------------------
DROP TABLE IF EXISTS `portal_instance_page`;
CREATE TABLE `portal_instance_page` (
  `pid` int(5) NOT NULL DEFAULT '0',
  `page` varchar(30) NOT NULL DEFAULT '',
  `page_value` text,
  PRIMARY KEY (`pid`,`page`),
  CONSTRAINT `portal_instance_page_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `portal_instance` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of portal_instance_page
-- ----------------------------
INSERT INTO `portal_instance_page` VALUES ('8', 'authorized', '{\"fields\":[{\"name\":\"banners\",\"value\":\"[{\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_1.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_2.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_3.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_4.jpg\\\"}]\"},{\"name\":\"bannersHtml\",\"value\":\"<img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_1.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_2.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_3.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_4.jpg\\\" /> \"}]}');
INSERT INTO `portal_instance_page` VALUES ('8', 'global', '{\"fields\":[{\"name\":\"mini_logo\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/mini_logo.gif\"},{\"name\":\"msite_copyright\",\"value\":\"&copy;2012-2014 WiFi 设计团队\"}]}');
INSERT INTO `portal_instance_page` VALUES ('8', 'list', '{\"fields\":[{\"name\":\"banner\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner.jpg\"}]}');
INSERT INTO `portal_instance_page` VALUES ('8', 'signin', '{\"fields\":[{\"name\":\"wechatLoginSteps\",\"value\":\"关注本店并回复\'\'\'\'wifi\'\'\'\'即可获取登录密码！\"}]}');
INSERT INTO `portal_instance_page` VALUES ('8', 'welcome', '{\"fields\":[{\"name\":\"welcomeBg\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/welcomeBg.jpg\"}]}');
INSERT INTO `portal_instance_page` VALUES ('9', 'authorized', '{\"fields\":[{\"name\":\"banners\",\"value\":\"[{\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_1.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_2.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_3.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_4.jpg\\\"}]\"},{\"name\":\"bannersHtml\",\"value\":\"<img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_1.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_2.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_3.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_4.jpg\\\" /> \"}]}');
INSERT INTO `portal_instance_page` VALUES ('9', 'global', '{\"fields\":[{\"name\":\"mini_logo\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/mini_logo.gif\"},{\"name\":\"msite_copyright\",\"value\":\"&copy;2012-2014 WiFi 设计团队\"}]}');
INSERT INTO `portal_instance_page` VALUES ('9', 'list', '{\"fields\":[{\"name\":\"banner\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner.jpg\"}]}');
INSERT INTO `portal_instance_page` VALUES ('9', 'signin', '{\"fields\":[{\"name\":\"wechatLoginSteps\",\"value\":\"关注本店并回复\'\'\'\'wifi\'\'\'\'即可获取登录密码！\"}]}');
INSERT INTO `portal_instance_page` VALUES ('9', 'welcome', '{\"fields\":[{\"name\":\"welcomeBg\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/welcomeBg.jpg\"}]}');
INSERT INTO `portal_instance_page` VALUES ('10', 'authorized', '{\"fields\":[{\"name\":\"banners\",\"value\":\"[{\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_1.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_2.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_3.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_4.jpg\\\"}]\"},{\"name\":\"bannersHtml\",\"value\":\"<img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_1.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_2.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_3.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_4.jpg\\\" /> \"}]}');
INSERT INTO `portal_instance_page` VALUES ('10', 'global', '{\"fields\":[{\"name\":\"mini_logo\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/mini_logo.gif\"},{\"name\":\"msite_copyright\",\"value\":\"&copy;2012-2014 WiFi 设计团队\"}]}');
INSERT INTO `portal_instance_page` VALUES ('10', 'list', '{\"fields\":[{\"name\":\"banner\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner.jpg\"}]}');
INSERT INTO `portal_instance_page` VALUES ('10', 'signin', '{\"fields\":[{\"name\":\"wechatLoginSteps\",\"value\":\"关注本店并回复\'\'\'\'wifi\'\'\'\'即可获取登录密码！\"}]}');
INSERT INTO `portal_instance_page` VALUES ('10', 'welcome', '{\"fields\":[{\"name\":\"welcomeBg\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/welcomeBg.jpg\"}]}');
INSERT INTO `portal_instance_page` VALUES ('12', 'authorized', '{\"fields\":[{\"name\":\"topAdImage\",\"value\":\"/soofac/msite/admin/tpl/soofound/ad_fortuneCat//define/img/s_top.jpg\"},{\"name\":\"topAdHref\",\"value\":\"\"},{\"name\":\"bigAdImage\",\"value\":\"/soofac/msite/admin/tpl/soofound/ad_fortuneCat//define/img/s_big.jpg\"},{\"name\":\"bigAdHref\",\"value\":\"\"},{\"name\":\"passTip\",\"value\":\"您已通过验证，马上开始您的网上冲浪吧！\"}]}');
INSERT INTO `portal_instance_page` VALUES ('12', 'global', '{\"fields\":[{\"name\":\"msite_copyright\",\"value\":\"©2014 WiFi QDDX设计团队\"},{\"name\":\"currentTpl\",\"value\":\"soofound.sp\"},{\"name\":\"msite_logo\",\"value\":\"/soofac/msite/admin/tpl/soofound/ad_fortuneCat//define/img/logo.png\"},{\"name\":\"msite_title\",\"value\":\"This is my title\"}]}');
INSERT INTO `portal_instance_page` VALUES ('12', 'list', '{\"fields\":[{\"name\":\"banner\",\"value\":\"/soofac/msite/admin/tpl/soofound/ad_fortuneCat//define/img/list_banner.jpg\"}]}');
INSERT INTO `portal_instance_page` VALUES ('12', 'signin', '{\"fields\":[{\"name\":\"enablePreRoll\",\"value\":\"1\"},{\"name\":\"preRollDuration\",\"value\":\"15\"},{\"name\":\"preRollType\",\"value\":\"pic\"},{\"name\":\"picAdImage\",\"value\":\"/soofac/msite/admin/tpl/soofound/ad_fortuneCat//define/img/s_fullscreen.jpg\"},{\"name\":\"videoAd\",\"value\":\"/soofac/msite/admin/tpl/soofound/ad_fortuneCat//define/img/s_video.mp4\"}]}');
INSERT INTO `portal_instance_page` VALUES ('12', 'welcome', '{\"fields\":[{\"name\":\"adImages\",\"value\":\"[{\\\"src\\\":\\\"/soofac/msite/admin/tpl/soofound/ad_fortuneCat//define/img/s_1.jpg\\\",\\\"href\\\":\\\"\\\", \\\"cols\\\":2}, {\\\"src\\\":\\\"/soofac/msite/admin/tpl/soofound/ad_fortuneCat//define/img/s_2.jpg\\\",\\\"href\\\":\\\"\\\", \\\"cols\\\":1}, {\\\"src\\\":\\\"/soofac/msite/admin/tpl/soofound/ad_fortuneCat//define/img/s_3.jpg\\\",\\\"href\\\":\\\"\\\", \\\"cols\\\":1},  {\\\"src\\\":\\\"/soofac/msite/admin/tpl/soofound/ad_fortuneCat//define/img/s_4.jpg\\\",\\\"href\\\":\\\"\\\", \\\"cols\\\":1},  {\\\"src\\\":\\\"/soofac/msite/admin/tpl/soofound/ad_fortuneCat//define/img/s_5.jpg\\\",\\\"href\\\":\\\"\\\", \\\"cols\\\":1}]\"},{\"name\":\"startTip\",\"value\":\"点击“马上体验”按钮后开始上网\"},{\"name\":\"jumpBtnText\",\"value\":\"马上体验\"},{\"name\":\"logoBg\",\"value\":\"/soofac/msite/admin/tpl/soofound/ad_fortuneCat//define/img/hemisphere_n.jpg\"}]}');
INSERT INTO `portal_instance_page` VALUES ('13', 'authorized', '{\"fields\":[{\"name\":\"banners\",\"value\":\"[{\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_1.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_2.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_3.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_4.jpg\\\"}]\"},{\"name\":\"bannersHtml\",\"value\":\"<img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_1.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_2.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_3.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_4.jpg\\\" /> \"}]}');
INSERT INTO `portal_instance_page` VALUES ('13', 'global', '{\"fields\":[{\"name\":\"mini_logo\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/mini_logo.gif\"},{\"name\":\"msite_copyright\",\"value\":\"&copy;2012-2014 WiFi 设计团队\"}]}');
INSERT INTO `portal_instance_page` VALUES ('13', 'list', '{\"fields\":[{\"name\":\"banner\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner.jpg\"}]}');
INSERT INTO `portal_instance_page` VALUES ('13', 'signin', '{\"fields\":[{\"name\":\"wechatLoginSteps\",\"value\":\"关注本店并回复\'\'\'\'wifi\'\'\'\'即可获取登录密码！\"}]}');
INSERT INTO `portal_instance_page` VALUES ('13', 'welcome', '{\"fields\":[{\"name\":\"welcomeBg\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/welcomeBg.jpg\"}]}');
INSERT INTO `portal_instance_page` VALUES ('14', 'authorized', '{\"fields\":[{\"name\":\"BannerSlide_banners\",\"value\":\"[{\\\"src\\\":\\\"/soofac/msite/admin/module/BannerSlide/define/img/example-slide-1.jpg\\\",\\\"href\\\":\\\"\\\"}, {\\\"src\\\":\\\"/soofac/msite/admin/module/BannerSlide/define/img/example-slide-2.jpg\\\",\\\"href\\\":\\\"\\\"}, {\\\"src\\\":\\\"/soofac/msite/admin/module/BannerSlide/define/img/example-slide-3.jpg\\\",\\\"href\\\":\\\"\\\"}]\"},{\"name\":\"IconTile_tiles\",\"value\":\"[{\\\"text\\\":\\\" 示例磁贴一 \\\",\\\"cols\\\":\\\"1\\\",\\\"bgColor\\\":\\\"#9b59b6\\\",\\\"icon\\\":\\\"/soofac/msite/admin/module/IconTile/define/img/credit31_white.png\\\",\\\"itemWidth\\\":\\\"100px\\\"},{\\\"linkType\\\":\\\"cat\\\",\\\"cols\\\":1,\\\"itemWidth\\\":\\\"100px\\\",\\\"bgColor\\\":\\\"#f38433\\\",\\\"icon\\\":\\\"/soofac/resources/image/default/caticon/person25.png\\\",\\\"cat\\\":null,\\\"url\\\":\\\"/soofac/msite/ListView.do?branchId=00001&cid=null\\\",\\\"text\\\":\\\"\\\"},{\\\"linkType\\\":\\\"cat\\\",\\\"cols\\\":1,\\\"itemWidth\\\":\\\"100px\\\",\\\"bgColor\\\":\\\"#ff9900\\\",\\\"icon\\\":\\\"/soofac/resources/image/default/caticon/diamons.png\\\",\\\"cat\\\":null,\\\"url\\\":\\\"/soofac/msite/ListView.do?branchId=00001&cid=null\\\",\\\"text\\\":\\\"\\\"}]\"},{\"name\":\"BannerSlide_size\",\"value\":\"940,529\"},{\"name\":\"BannerSlide_bannersHtml\",\"value\":\"<img src=\\\"/soofac/msite/admin/module/BannerSlide/define/img/example-slide-1.jpg\\\" /><img src=\\\"/soofac/msite/admin/module/BannerSlide/define/img/example-slide-2.jpg\\\" /><img src=\\\"/soofac/msite/admin/module/BannerSlide/define/img/example-slide-3.jpg\\\" />\"}]}');
INSERT INTO `portal_instance_page` VALUES ('14', 'global', '{\"fields\":[{\"name\":\"msite_logo\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/logo.jpg\"},{\"name\":\"msite_title\",\"value\":\"免费 WiFi 上网\"},{\"name\":\"msite_tpl\",\"value\":\"soofound.sp\"},{\"name\":\"msite_copyright\",\"value\":\"&copy;2012-2014 WiFi 设计团队\"}]}');
INSERT INTO `portal_instance_page` VALUES ('14', 'list', '{\"fields\":[{\"name\":\"banner\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner.jpg\"}]}');
INSERT INTO `portal_instance_page` VALUES ('14', 'signin', '{\"fields\":[{\"name\":\"wechatLoginSteps\",\"value\":\"关注本店并回复\'\'\'\'wifi\'\'\'\'即可获取登录密码！\"}]}');
INSERT INTO `portal_instance_page` VALUES ('14', 'welcome', '{\"fields\":[{\"name\":\"welcomeBg\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/welcomeBg.jpg\"}]}');
INSERT INTO `portal_instance_page` VALUES ('15', 'authorized', '{\"fields\":[{\"name\":\"topADUrl\",\"value\":\"/soofac/msite/admin/tpl/soofound/ad_TLayout//define/img/s_top.jpg\"},{\"name\":\"adImages\",\"value\":\"[{\\\"src\\\":\\\"/soofac/msite/admin/tpl/soofound/ad_TLayout//define/img/s_big_1.jpg\\\",\\\"href\\\":\\\"\\\"}]\"},{\"name\":\"adImagesHtml\",\"value\":\"<a href=\\\"\\\"><img class=\\\"ad\\\" src=\\\"/soofac/msite/admin/tpl/soofound/ad_TLayout//define/img/s_big_1.jpg\\\" /></a>\"},{\"name\":\"passTip\",\"value\":\"您已通过验证，马上开始您的网上冲浪吧！\"}]}');
INSERT INTO `portal_instance_page` VALUES ('15', 'global', '{\"fields\":[{\"name\":\"msite_logo\",\"value\":\"/soofac/msite/admin/tpl/soofound/ad_TLayout//define/img/logo_WIFIBeijing.jpg\"},{\"name\":\"msite_title\",\"value\":\"免费 WiFi 上网\"},{\"name\":\"msite_tpl\",\"value\":\"soofound.sp\"},{\"name\":\"msite_copyright\",\"value\":\"&copy;2014 宽带北京 无线城市\"}]}');
INSERT INTO `portal_instance_page` VALUES ('15', 'welcome', '{\"fields\":[{\"name\":\"topADUrl\",\"value\":\"/soofac/msite/admin/tpl/soofound/ad_TLayout//define/img/ad_top.jpg\"},{\"name\":\"adImages\",\"value\":\"[{\\\"src\\\":\\\"/soofac/msite/admin/tpl/soofound/ad_TLayout//define/img/s_1.jpg\\\",\\\"href\\\":\\\"\\\"}, {\\\"src\\\":\\\"/soofac/msite/admin/tpl/soofound/ad_TLayout//define/img/s_2.jpg\\\",\\\"href\\\":\\\"\\\"}, {\\\"src\\\":\\\"/soofac/msite/admin/tpl/soofound/ad_TLayout//define/img/s_3.jpg\\\",\\\"href\\\":\\\"\\\"}, {\\\"src\\\":\\\"/soofac/msite/admin/tpl/soofound/ad_TLayout//define/img/s_4.jpg\\\",\\\"href\\\":\\\"\\\"}]\"},{\"name\":\"adImagesHtml\",\"value\":\"<a href=\\\"\\\"><img class=\\\"ad\\\" src=\\\"/soofac/msite/admin/tpl/soofound/ad_TLayout//define/img/s_1.jpg\\\" /></a><a href=\\\"\\\"><img class=\\\"ad\\\" src=\\\"/soofac/msite/admin/tpl/soofound/ad_TLayout//define/img/s_2.jpg\\\" /></a><a href=\\\"\\\"><img class=\\\"ad\\\" src=\\\"/soofac/msite/admin/tpl/soofound/ad_TLayout//define/img/s_3.jpg\\\" /></a><a href=\\\"\\\"><img class=\\\"ad\\\" src=\\\"/soofac/msite/admin/tpl/soofound/ad_TLayout//define/img/s_4.jpg\\\" /></a>\"},{\"name\":\"startTip\",\"value\":\"点击“同意”后开始上网\"},{\"name\":\"wifiAgreement\",\"value\":\"1.您在使用互联网业务时，应遵守国家的有关法律、法规和行政规章制度。<br /><br />2.您在互联网业务时，应遵守互联网的国际惯例，不得向他人发动恶意攻击，不得向他人发送恶意的、挑衅性的文件和商业广告。<br /><br />3.您在使用互联网业务时，不得发布含有病毒、恶意代码、色情、反动等不良信息或有害信息。<br /><br />4.您同意遵守以上各项规定，如违反规定，将接受有关部门的处罚直至承担法律责任。\"},{\"name\":\"jumpBtnText\",\"value\":\"同意\"}]}');
INSERT INTO `portal_instance_page` VALUES ('16', 'authorized', '{\"fields\":[{\"name\":\"banners\",\"value\":\"[{\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_1.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_2.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_3.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_4.jpg\\\"}]\"},{\"name\":\"bannersHtml\",\"value\":\"<img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_1.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_2.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_3.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_4.jpg\\\" /> \"}]}');
INSERT INTO `portal_instance_page` VALUES ('16', 'global', '{\"fields\":[{\"name\":\"msite_logo\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/logo.jpg\"},{\"name\":\"msite_title\",\"value\":\"免费 WiFi 上网\"},{\"name\":\"msite_tpl\",\"value\":\"soofound.sp\"},{\"name\":\"msite_copyright\",\"value\":\"&copy;2012-2014 WiFi 设计团队\"}]}');
INSERT INTO `portal_instance_page` VALUES ('16', 'list', '{\"fields\":[{\"name\":\"banner\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner.jpg\"}]}');
INSERT INTO `portal_instance_page` VALUES ('16', 'signin', '{\"fields\":[{\"name\":\"wechatLoginSteps\",\"value\":\"关注本店并回复\'\'\'\'wifi\'\'\'\'即可获取登录密码！\"}]}');
INSERT INTO `portal_instance_page` VALUES ('16', 'welcome', '{\"fields\":[{\"name\":\"welcomeBg\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/welcomeBg.jpg\"}]}');
INSERT INTO `portal_instance_page` VALUES ('17', 'authorized', '{\"fields\":[{\"name\":\"banners\",\"value\":\"[{\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_1.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_2.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_3.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_4.jpg\\\"}]\"},{\"name\":\"bannersHtml\",\"value\":\"<img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_1.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_2.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_3.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_4.jpg\\\" /> \"}]}');
INSERT INTO `portal_instance_page` VALUES ('17', 'global', '{\"fields\":[{\"name\":\"msite_logo\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/logo.jpg\"},{\"name\":\"msite_title\",\"value\":\"免费 WiFi 上网\"},{\"name\":\"msite_tpl\",\"value\":\"soofound.sp\"},{\"name\":\"msite_copyright\",\"value\":\"&copy;2012-2014 WiFi 设计团队\"}]}');
INSERT INTO `portal_instance_page` VALUES ('17', 'list', '{\"fields\":[{\"name\":\"banner\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner.jpg\"}]}');
INSERT INTO `portal_instance_page` VALUES ('17', 'signin', '{\"fields\":[{\"name\":\"wechatLoginSteps\",\"value\":\"关注本店并回复\'\'\'\'wifi\'\'\'\'即可获取登录密码！\"}]}');
INSERT INTO `portal_instance_page` VALUES ('17', 'welcome', '{\"fields\":[{\"name\":\"welcomeBg\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/welcomeBg.jpg\"}]}');
INSERT INTO `portal_instance_page` VALUES ('18', 'authorized', '{\"fields\":[{\"name\":\"banners\",\"value\":\"[{\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_1.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_2.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_3.jpg\\\"}, {\\\"src\\\" : \\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_4.jpg\\\"}]\"},{\"name\":\"bannersHtml\",\"value\":\"<img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_1.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_2.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_3.jpg\\\" /> <img src=\\\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner_4.jpg\\\" /> \"}]}');
INSERT INTO `portal_instance_page` VALUES ('18', 'global', '{\"fields\":[{\"name\":\"msite_logo\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/logo.jpg\"},{\"name\":\"msite_title\",\"value\":\"免费 WiFi 上网\"},{\"name\":\"msite_tpl\",\"value\":\"soofound.sp\"},{\"name\":\"msite_copyright\",\"value\":\"&copy;2012-2014 WiFi 设计团队\"}]}');
INSERT INTO `portal_instance_page` VALUES ('18', 'list', '{\"fields\":[{\"name\":\"banner\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/banner.jpg\"}]}');
INSERT INTO `portal_instance_page` VALUES ('18', 'signin', '{\"fields\":[{\"name\":\"wechatLoginSteps\",\"value\":\"关注本店并回复\'\'\'\'wifi\'\'\'\'即可获取登录密码！\"}]}');
INSERT INTO `portal_instance_page` VALUES ('18', 'welcome', '{\"fields\":[{\"name\":\"welcomeBg\",\"value\":\"/soofac/msite/admin/tpl/soofound/sp//define/img/welcomeBg.jpg\"}]}');

-- ----------------------------
-- Table structure for `portal_surfing_policy`
-- ----------------------------
DROP TABLE IF EXISTS `portal_surfing_policy`;
CREATE TABLE `portal_surfing_policy` (
  `id` int(5) NOT NULL,
  `branch_id` varchar(30) DEFAULT NULL,
  `redirect` tinyint(1) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `surfing_time` int(3) DEFAULT NULL,
  `idle_time` int(3) DEFAULT NULL,
  `stay_time` int(3) DEFAULT NULL,
  `auth` tinyint(1) DEFAULT '0',
  `pwd_auth` tinyint(1) DEFAULT NULL,
  `sms_auth` tinyint(1) DEFAULT NULL,
  `wechat_auth` tinyint(1) DEFAULT NULL,
  `jump_to` tinyint(1) DEFAULT NULL,
  `jump_url` varchar(100) DEFAULT NULL,
  `cmcc` tinyint(1) DEFAULT NULL,
  `portal_ip` varchar(30) DEFAULT NULL,
  `portal_port` varchar(5) DEFAULT NULL,
  `portal_url` varchar(100) DEFAULT NULL,
  `one_account_device` tinyint(1) DEFAULT NULL,
  `wechat_guide` tinyint(1) DEFAULT NULL,
  `tag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `branch_id` (`branch_id`),
  CONSTRAINT `portal_surfing_policy_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `admin_branch` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of portal_surfing_policy
-- ----------------------------
INSERT INTO `portal_surfing_policy` VALUES ('1', '00001', '1', '微信指引', '0', '30', null, '1', '0', '0', '1', '1', '', '0', 'null', 'null', 'null', '0', '1', '0');
INSERT INTO `portal_surfing_policy` VALUES ('2', '00001', '1', 'sola', '0', '30', null, '0', '0', '1', '0', '1', '', null, null, null, null, '0', '0', '0');
INSERT INTO `portal_surfing_policy` VALUES ('3', '00001', '1', 'default', '0', '30', '120', '0', '0', '0', '0', '1', '', null, null, null, null, '0', '0', '0');
INSERT INTO `portal_surfing_policy` VALUES ('4', null, '1', null, '0', '60', null, '0', '0', '0', '0', '1', null, '0', null, null, null, '0', '0', '1');
INSERT INTO `portal_surfing_policy` VALUES ('5', null, '1', null, '0', '60', null, '0', '0', '0', '0', '1', null, '0', null, null, null, '0', '0', '1');
INSERT INTO `portal_surfing_policy` VALUES ('8', '00002', '1', 'default', '120', '30', '60', '1', '1', '0', '0', '2', 'http://www.icbc.com.cn', null, null, null, null, '0', '0', '1');

-- ----------------------------
-- Table structure for `portal_template`
-- ----------------------------
DROP TABLE IF EXISTS `portal_template`;
CREATE TABLE `portal_template` (
  `id` varchar(30) NOT NULL DEFAULT '0',
  `name` varchar(30) DEFAULT NULL,
  `cover` varchar(100) DEFAULT NULL,
  `descr` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of portal_template
-- ----------------------------
INSERT INTO `portal_template` VALUES ('soofound.ad_fortuneCat', '招财猫', '/soofac/msite/admin/tpl/soofound/ad_fortuneCat//define/img/snap/welcome.jpg', '广告模板。广告就是衣裳，日新月异恰似那百花齐放。欢迎页以图片墙的形式展现广告，更有艺术性；认证后页支持大图广告，让广告更有视觉冲击力！', '2014-10-13 15:16:23');
INSERT INTO `portal_template` VALUES ('soofound.ad_TLayout', 'T 型告示牌', '/soofac/msite/admin/tpl/soofound/ad_TLayout//define/img/snap/welcome.jpg', '广告模板。欢迎页 T 字型布局，左侧明示 WiFi 用户协议，顶部和右侧可放置多幅广告；认证后页支持大图广告，让广告更有视觉冲击力！', '2014-10-15 12:27:23');
INSERT INTO `portal_template` VALUES ('soofound.sp', 'freeGo', '/soofac/msite/admin/tpl/soofound/sp//define/img/snap/1.jpg', '微网站模板。欢迎页支持大图海报可充分展示商家形象或产品促销信息；商家主页支持海报轮播，商品品类磁贴等丰富的展现形式。', '2014-10-13 16:18:32');

-- ----------------------------
-- Table structure for `portal_wechat_guide`
-- ----------------------------
DROP TABLE IF EXISTS `portal_wechat_guide`;
CREATE TABLE `portal_wechat_guide` (
  `branch_id` varchar(30) NOT NULL DEFAULT '',
  `flag` varchar(10) DEFAULT NULL,
  `page_value` text,
  PRIMARY KEY (`branch_id`),
  CONSTRAINT `portal_wechat_guide_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `admin_branch` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of portal_wechat_guide
-- ----------------------------
INSERT INTO `portal_wechat_guide` VALUES ('00001', 'default', '');

-- ----------------------------
-- Table structure for `portal_wechat_response`
-- ----------------------------
DROP TABLE IF EXISTS `portal_wechat_response`;
CREATE TABLE `portal_wechat_response` (
  `branch_id` varchar(30) NOT NULL DEFAULT '',
  `page_value` text,
  PRIMARY KEY (`branch_id`),
  KEY `branch_id` (`branch_id`),
  CONSTRAINT `portal_wechat_response_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `admin_branch` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of portal_wechat_response
-- ----------------------------
INSERT INTO `portal_wechat_response` VALUES ('00001', '{\"customWelcMsgCover\":\"http://www.mysite.com/welcome.jpg\",\"imageMsgs\":\"[]\",\"showPCAuth\":\"0\",\"welcMsgCoverType\":\"custom\",\"welcMsgHref\":\"http://www.soofound.com\",\"welcMsgTitle\":\"T1\"}');

-- ----------------------------
-- Table structure for `surfing_account`
-- ----------------------------
DROP TABLE IF EXISTS `surfing_account`;
CREATE TABLE `surfing_account` (
  `id` int(10) NOT NULL DEFAULT '0',
  `username` varchar(30) DEFAULT NULL,
  `open_id` varchar(30) DEFAULT NULL,
  `mac` varchar(17) DEFAULT NULL,
  `branch_id` varchar(30) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `flag` varchar(10) DEFAULT NULL,
  `times` int(5) DEFAULT NULL,
  `online` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_surfing_account` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of surfing_account
-- ----------------------------
INSERT INTO `surfing_account` VALUES ('2', 'D857EF32338F', null, 'D8:57:EF:32:33:8F', '00001', '2014-11-10 15:48:08', '-', '', 'free', '20', '1');
INSERT INTO `surfing_account` VALUES ('3', '38BC1A137A2A', null, '38:BC:1A:13:7A:2A', '00001', '2014-11-10 13:14:34', '-', '', 'free', '6', '0');
INSERT INTO `surfing_account` VALUES ('4', '00664B10F653', null, '00:66:4B:10:F6:53', '00001', '2014-10-30 15:53:27', '-', '', 'free', '8', '0');
INSERT INTO `surfing_account` VALUES ('5', '00F4B9468112', null, '00:F4:B9:46:81:12', '00001', '2014-10-29 11:38:19', '-', '', 'free', '2', '0');
INSERT INTO `surfing_account` VALUES ('6', '00242CA9E4CF', null, '00:24:2C:A9:E4:CF', '00001', '2014-10-30 15:27:27', '-', '', 'free', '4', '0');
INSERT INTO `surfing_account` VALUES ('7', '18DC56A00DC6', null, '18:DC:56:A0:0D:C6', '00001', '2014-11-04 12:08:54', '-', '', 'free', '4', '0');
INSERT INTO `surfing_account` VALUES ('8', '70720DD62BEB', null, '70:72:0D:D6:2B:EB', '00001', '2014-10-30 15:37:27', '-', '', 'free', '4', '0');
INSERT INTO `surfing_account` VALUES ('9', '68DFDD2A60D7', null, '68:DF:DD:2A:60:D7', '00001', '2014-10-30 16:02:46', '-', '', 'free', '2', '0');
INSERT INTO `surfing_account` VALUES ('10', '24E314098C18', null, '24:E3:14:09:8C:18', '00001', '2014-11-01 12:59:34', '-', '', 'free', '10', '0');
INSERT INTO `surfing_account` VALUES ('11', 's006', null, 'F8:A4:5F:56:EA:F2', '00001', '2014-11-06 16:23:49', '-', null, 'free', '2', '0');
INSERT INTO `surfing_account` VALUES ('12', 's001', null, 'D8:57:EF:32:33:8F', '00002', '2014-11-09 20:45:37', '8980', '', 'db', '0', '0');
INSERT INTO `surfing_account` VALUES ('13', 's002', null, 'D8:57:EF:32:33:8F', '00002', '2014-11-09 20:46:13', '2453', '', 'db', '0', '0');
INSERT INTO `surfing_account` VALUES ('14', 's003', null, null, '00002', '2014-11-09 20:46:18', '7185', null, 'db', '0', '0');
INSERT INTO `surfing_account` VALUES ('15', 's004', null, null, '00002', '2014-11-08 21:53:08', '1', null, 'db', '0', '0');
INSERT INTO `surfing_account` VALUES ('16', 's005', null, null, '00002', '2014-11-09 20:49:26', '6447', null, 'db', '0', '0');
INSERT INTO `surfing_account` VALUES ('17', '13826129825', null, null, '00002', '2014-11-09 20:46:23', '7278', null, 'mobile', '0', '0');
INSERT INTO `surfing_account` VALUES ('18', 's007', null, null, '00002', '2014-11-08 22:08:11', '1', null, 'db', '0', '0');

-- ----------------------------
-- Table structure for `surfing_advertisement`
-- ----------------------------
DROP TABLE IF EXISTS `surfing_advertisement`;
CREATE TABLE `surfing_advertisement` (
  `id` varchar(22) NOT NULL,
  `title` varchar(30) DEFAULT NULL,
  `branch_id` varchar(30) DEFAULT NULL,
  `content` text,
  `summary` varchar(200) DEFAULT NULL,
  `cover` varchar(50) DEFAULT NULL,
  `creator` varchar(30) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `category_id` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of surfing_advertisement
-- ----------------------------
INSERT INTO `surfing_advertisement` VALUES ('1648455123325582', 'wwww', '00001', 'wwwwwwwwwww', 'www', '/soofac/acs/upload/00001_cover_6565.jpg', 'yunsk', '2014-10-25 17:11:22', '0');

-- ----------------------------
-- Table structure for `surfing_advertise_category`
-- ----------------------------
DROP TABLE IF EXISTS `surfing_advertise_category`;
CREATE TABLE `surfing_advertise_category` (
  `id` int(3) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `branch_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of surfing_advertise_category
-- ----------------------------
INSERT INTO `surfing_advertise_category` VALUES ('1', 'aaa', '00001');
INSERT INTO `surfing_advertise_category` VALUES ('2', 'bbb', '00001');

-- ----------------------------
-- Table structure for `surfing_ad_material`
-- ----------------------------
DROP TABLE IF EXISTS `surfing_ad_material`;
CREATE TABLE `surfing_ad_material` (
  `id` varchar(22) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `branch_id` varchar(30) DEFAULT NULL,
  `file` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `link` varchar(100) DEFAULT NULL,
  `target` varchar(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of surfing_ad_material
-- ----------------------------
INSERT INTO `surfing_ad_material` VALUES ('1653382573834361', 'ddddddddddc', '00001', '/soofac/acs/upload/00001_imageMaterial_5003.jpg', 'dddddddddddddd', 'dddd', null, '2014-10-25 18:33:29');
INSERT INTO `surfing_ad_material` VALUES ('1657508491258927', 'dddcccc', '00001', '/soofac/acs/upload/00001_imageMaterial_2492.png', 'aaaaaaaaaaaa', 'dddddddddda', '_blank', '2014-10-25 19:42:15');

-- ----------------------------
-- Table structure for `surfing_alipay`
-- ----------------------------
DROP TABLE IF EXISTS `surfing_alipay`;
CREATE TABLE `surfing_alipay` (
  `trade_no` varchar(22) NOT NULL DEFAULT '',
  `trade_descr` varchar(30) DEFAULT NULL,
  `branch_id` varchar(20) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `fullname` varchar(30) DEFAULT NULL,
  `trade_status` varchar(10) DEFAULT NULL,
  `fee` float(10,2) DEFAULT NULL,
  `log_time` datetime DEFAULT NULL,
  PRIMARY KEY (`trade_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of surfing_alipay
-- ----------------------------

-- ----------------------------
-- Table structure for `surfing_black_white`
-- ----------------------------
DROP TABLE IF EXISTS `surfing_black_white`;
CREATE TABLE `surfing_black_white` (
  `id` int(3) NOT NULL DEFAULT '0',
  `mac` varchar(20) DEFAULT NULL,
  `bw` tinyint(1) DEFAULT NULL,
  `reason` varchar(30) DEFAULT NULL,
  `branch_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of surfing_black_white
-- ----------------------------

-- ----------------------------
-- Table structure for `surfing_sequence_account`
-- ----------------------------
DROP TABLE IF EXISTS `surfing_sequence_account`;
CREATE TABLE `surfing_sequence_account` (
  `branch_id` varchar(30) NOT NULL DEFAULT '',
  `sequence_id` int(4) DEFAULT NULL,
  `log_time` datetime DEFAULT NULL,
  PRIMARY KEY (`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of surfing_sequence_account
-- ----------------------------
INSERT INTO `surfing_sequence_account` VALUES ('00002', '6', '2014-11-09 20:49:26');

-- ----------------------------
-- Table structure for `surfing_session`
-- ----------------------------
DROP TABLE IF EXISTS `surfing_session`;
CREATE TABLE `surfing_session` (
  `session_id` varchar(25) NOT NULL DEFAULT '',
  `username` varchar(30) DEFAULT NULL,
  `cpe_id` int(5) DEFAULT NULL,
  `ip_address` varchar(15) DEFAULT NULL,
  `mac` varchar(17) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `stop_time` datetime DEFAULT NULL,
  `session_time` int(5) DEFAULT NULL,
  `input_octets` int(10) DEFAULT NULL,
  `output_octets` int(10) DEFAULT NULL,
  `snr` int(3) DEFAULT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `flag` varchar(10) DEFAULT NULL,
  `ife` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`session_id`),
  KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of surfing_session
-- ----------------------------
INSERT INTO `surfing_session` VALUES ('101843989315819-u6mq03', 's001', '6', '192.168.1.102', 'D8:57:EF:32:33:8F', '2014-11-09 19:13:05', '2014-11-09 19:15:24', '167', '853', '85', '-40', '三星', 'db', '1');
INSERT INTO `surfing_session` VALUES ('1018572049752749-cugpee', 's001', '1', '192.168.1.132', 'D8:57:EF:32:33:8F', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '3698', '545', '54', '-61', '三星', 'wechat', '1');
INSERT INTO `surfing_session` VALUES ('102118757948991-59n4wf', 's001', '6', '192.168.1.102', 'D8:57:EF:32:33:8F', '2014-11-09 19:17:40', '2014-11-09 19:18:28', '72', '801', '85', '-46', '三星', 'db', '1');
INSERT INTO `surfing_session` VALUES ('1023043560710315-3ntlqz', 's002', '4', '192.168.1.211', 'F8:A4:5F:56:EA:F2', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '236', '542', '101', '-95', '小米', 'free', '0');
INSERT INTO `surfing_session` VALUES ('1023242163306228-ddlhgw', 's003', '1', '192.168.1.211', 'F8:A4:5F:56:EA:F2', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '18863', '1055', '200', '-78', '小米', 'null', '0');
INSERT INTO `surfing_session` VALUES ('102629066852746-jw7gnj', 's002', '6', '192.168.1.102', 'D8:57:EF:32:33:8F', '2014-11-09 19:26:10', '2014-11-09 19:31:43', '333', '34', '12', '-44', '三星', 'db', '1');
INSERT INTO `surfing_session` VALUES ('103153222813467-qhcvc7', 's002', '6', '192.168.1.102', 'D8:57:EF:32:33:8F', '2014-11-09 19:34:54', '2014-11-09 19:36:42', '128', '37', '12', '-45', '三星', 'db', '1');
INSERT INTO `surfing_session` VALUES ('103927445896605-izindt', 's002', '6', '192.168.1.102', 'D8:57:EF:32:33:8F', '2014-11-09 19:47:48', '2014-11-09 20:10:01', '1333', '35', '13', '-40', '三星', 'db', '1');
INSERT INTO `surfing_session` VALUES ('105509309052038-kp8mtu', 's002', '6', '192.168.1.102', 'D8:57:EF:32:33:8F', '2014-11-09 20:14:10', '2014-11-09 20:14:19', '9', '0', '0', null, '三星', 'db', '1');
INSERT INTO `surfing_session` VALUES ('106215793380904-b6xjen', 's002', '6', '192.168.1.102', 'D8:57:EF:32:33:8F', '2014-11-09 20:25:57', '2014-11-09 20:27:14', '99', '34', '12', '-46', '三星', 'db', '1');
INSERT INTO `surfing_session` VALUES ('163134811527377-9z9b2w', '38BC1A137A2A', '1', '192.168.1.188', '38:BC:1A:13:7A:2A', '2014-11-10 12:14:42', '2014-11-10 13:13:39', '3537', '6603', '900', '-80', 'Meizu', 'free', '1');
INSERT INTO `surfing_session` VALUES ('166726938987986-ei0x9x', '38BC1A137A2A', '1', '192.168.1.188', '38:BC:1A:13:7A:2A', '2014-11-10 13:14:35', '2014-11-10 14:13:46', '3551', '400', '160', '-84', 'Meizu', 'free', '1');
INSERT INTO `surfing_session` VALUES ('175940670092753-t649j7', 'D857EF32338F', '1', '192.168.1.139', 'D8:57:EF:32:33:8F', '2014-11-10 15:48:09', null, '319', '467', '62', '-54', '三星', 'free', '1');
INSERT INTO `surfing_session` VALUES ('310793242348636-k1a4n9', 's004', '1', '192.168.1.173', 'D8:57:EF:32:33:8F', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '3745', '2', '159', '-60', '三星', 'free', '0');
INSERT INTO `surfing_session` VALUES ('313059509252486-5dv130', 's005', '1', '192.168.1.220', '38:BC:1A:13:7A:2A', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '803', '730', '70', '-73', 'Meizu', 'free', '0');
INSERT INTO `surfing_session` VALUES ('313455738141674-n0swrm', '00664B10F653', '1', '192.168.1.172', '00:66:4B:10:F6:53', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '11271', '984', '422', '-95', '华为', 'free', '0');
INSERT INTO `surfing_session` VALUES ('314777974424853-imdxt1', '00F4B9468112', '1', '192.168.1.133', '00:F4:B9:46:81:12', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '9950', '300', '101', '-73', '苹果', 'free', '0');
INSERT INTO `surfing_session` VALUES ('325457307008985-2rggx9', '00664B10F653', '1', '192.168.1.172', '00:66:4B:10:F6:53', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '349', '66', '67', '-58', '华为', 'free', '0');
INSERT INTO `surfing_session` VALUES ('346210501110354-ga9031', 'D857EF32338F', '1', '192.168.1.173', 'D8:57:EF:32:33:8F', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '3650', '7', '62', '-95', '三星', 'free', '0');
INSERT INTO `surfing_session` VALUES ('356321602470986-1jkr3m', 'D857EF32338F', '1', '192.168.1.173', 'D8:57:EF:32:33:8F', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '47528', '5', '16', '-53', '三星', 'free', '0');
INSERT INTO `surfing_session` VALUES ('403822011084792-jexk8i', '00242CA9E4CF', '1', '192.168.1.112', '00:24:2C:A9:E4:CF', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '7778', '2489', '21943', '-36', 'Hon', 'free', '0');
INSERT INTO `surfing_session` VALUES ('404073265493565-5qein1', '18DC56A00DC6', '1', '192.168.1.174', '18:DC:56:A0:0D:C6', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '3508', '50', '781', '-73', 'Coolpad', 'free', '0');
INSERT INTO `surfing_session` VALUES ('404665193912742-nlsbe3', '70720DD62BEB', '1', '192.168.1.138', '70:72:0D:D6:2B:EB', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '4716', '1541', '305', '-95', '联想', 'free', '0');
INSERT INTO `surfing_session` VALUES ('411326314608944-kzkny2', '00664B10F653', '1', '192.168.1.219', '00:66:4B:10:F6:53', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '273', '323', '449', '-48', '华为', 'free', '0');
INSERT INTO `surfing_session` VALUES ('414914489581774-m2quv2', '00242CA9E4CF', '1', '192.168.1.112', '00:24:2C:A9:E4:CF', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '6063', '538', '4391', '-41', 'Hon', 'free', '0');
INSERT INTO `surfing_session` VALUES ('415494479927790-63ntcw', '70720DD62BEB', '1', '192.168.1.138', '70:72:0D:D6:2B:EB', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '2663', '94', '1404', '-95', '联想', 'free', '0');
INSERT INTO `surfing_session` VALUES ('416449467901983-peh9a7', '00664B10F653', '1', '192.168.1.219', '00:66:4B:10:F6:53', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '4529', '362', '10629', '-95', '华为', 'free', '0');
INSERT INTO `surfing_session` VALUES ('417037086417288-pr2i1v', '68DFDD2A60D7', '1', '192.168.1.167', '68:DF:DD:2A:60:D7', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '1960', '8', '23', '-95', '小米', 'free', '0');
INSERT INTO `surfing_session` VALUES ('572686887089907-hhqmuo', '24E314098C18', '4', '192.168.1.142', '24:E3:14:09:8C:18', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '1316', '502', '136', '-95', '苹果', 'free', '0');
INSERT INTO `surfing_session` VALUES ('574127447567358-9irupy', '24E314098C18', '4', '192.168.1.142', '24:E3:14:09:8C:18', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '4402', '57394', '2404', '-95', '苹果', 'free', '0');
INSERT INTO `surfing_session` VALUES ('578798434616815-lib9yw', '24E314098C18', '4', '192.168.1.142', '24:E3:14:09:8C:18', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '6584', '173168', '5878', '-71', '苹果', 'free', '0');
INSERT INTO `surfing_session` VALUES ('677275284896376-e7qxz9', 'wx7427', '5', '192.168.1.102', 'D8:57:EF:32:33:8F', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '163', '818', '66', '-52', '三星', 'wechat', '0');
INSERT INTO `surfing_session` VALUES ('677659471446271-f3ovu0', 'wx7427', '5', '192.168.1.102', 'D8:57:EF:32:33:8F', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '1153', '121', '22', '-40', '三星', 'wechat', '0');
INSERT INTO `surfing_session` VALUES ('679505296771777-werujr', 'wx7427', '5', '192.168.1.102', 'D8:57:EF:32:33:8F', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '3294', '497', '53', '-39', '三星', 'wechat', '0');
INSERT INTO `surfing_session` VALUES ('682914500767427-8ucpci', 'wx7427', '5', '192.168.1.102', 'D8:57:EF:32:33:8F', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '505', '6', '3', '-44', '三星', 'wechat', '0');
INSERT INTO `surfing_session` VALUES ('683490356177094-i05yqg', 'wx7427', '5', '192.168.1.102', 'D8:57:EF:32:33:8F', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '1461', '290', '36', '-40', '三星', 'wechat', '1');
INSERT INTO `surfing_session` VALUES ('685037072347366-hnrhpv', 'D857EF32338F', '5', '192.168.1.102', 'D8:57:EF:32:33:8F', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '220', '1053', '113', '-39', '三星', 'free', '1');
INSERT INTO `surfing_session` VALUES ('685338730790151-blq5dd', 'D857EF32338F', '5', '192.168.1.102', 'D8:57:EF:32:33:8F', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '5326', '251', '48', '-52', '三星', 'free', '1');
INSERT INTO `surfing_session` VALUES ('695611732445332-crasn1', 'D857EF32338F', '5', '192.168.1.102', 'D8:57:EF:32:33:8F', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '411', '422', '46', '-44', '三星', 'free', '0');
INSERT INTO `surfing_session` VALUES ('759069066750823-tfq6ja', 'D857EF32338F', '4', '192.168.1.179', 'D8:57:EF:32:33:8F', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '26486', '868', '129', '-48', '三星', 'free', '0');
INSERT INTO `surfing_session` VALUES ('834951278546031-goalpe', '18DC56A00DC6', '4', '192.168.1.161', '18:DC:56:A0:0D:C6', '2014-11-09 15:07:15', '2014-11-09 15:07:30', '4376', '20141', '624', '-80', 'Coolpad', 'free', '0');
INSERT INTO `surfing_session` VALUES ('94126255368897-6prn8q', 's001', '6', '192.168.1.102', 'D8:57:EF:32:33:8F', '2014-11-09 17:04:27', '2014-11-09 18:09:07', '3880', '2554', '351', '-44', '三星', 'db', '1');
INSERT INTO `surfing_session` VALUES ('97667301881711-f2u53n', 's002', '6', '192.168.1.102', 'D8:57:EF:32:33:8F', '2014-11-09 18:03:28', '2014-11-09 18:07:32', '244', '789', '86', '-50', '三星', 'db', '1');

-- ----------------------------
-- Table structure for `surfing_sms`
-- ----------------------------
DROP TABLE IF EXISTS `surfing_sms`;
CREATE TABLE `surfing_sms` (
  `id` varchar(30) NOT NULL DEFAULT '',
  `mobile` varchar(13) DEFAULT NULL,
  `branch_id` varchar(30) DEFAULT NULL,
  `content` varchar(300) DEFAULT NULL,
  `log_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of surfing_sms
-- ----------------------------

-- ----------------------------
-- Table structure for `surfing_sms_stat`
-- ----------------------------
DROP TABLE IF EXISTS `surfing_sms_stat`;
CREATE TABLE `surfing_sms_stat` (
  `branch_id` varchar(30) NOT NULL,
  `pv_total` int(10) DEFAULT '0',
  `pv_biz` int(10) DEFAULT '0',
  `sms_used` int(5) DEFAULT NULL,
  `sms_remain` int(5) DEFAULT NULL,
  KEY `branch_id` (`branch_id`),
  CONSTRAINT `surfing_sms_stat_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `admin_branch` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of surfing_sms_stat
-- ----------------------------

-- ----------------------------
-- Table structure for `surfing_trade`
-- ----------------------------
DROP TABLE IF EXISTS `surfing_trade`;
CREATE TABLE `surfing_trade` (
  `order_no` varchar(20) NOT NULL DEFAULT '',
  `branch_id` varchar(30) DEFAULT NULL,
  `summary` varchar(20) DEFAULT NULL,
  `amount` int(5) DEFAULT NULL,
  `fee` int(6) DEFAULT NULL,
  `pay_way` tinyint(1) DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `trade_no` varchar(20) DEFAULT NULL,
  `log_time` datetime DEFAULT NULL,
  PRIMARY KEY (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of surfing_trade
-- ----------------------------

-- ----------------------------
-- View structure for `view_address_city`
-- ----------------------------
DROP VIEW IF EXISTS `view_address_city`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_address_city` AS select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`en_name` AS `en_name`,`a`.`province_id` AS `province_id`,`b`.`name` AS `province`,`b`.`en_name` AS `en_province` from (`address_city` `a` left join `address_province` `b` on((`a`.`province_id` = `b`.`id`))) ;

-- ----------------------------
-- View structure for `view_address_county`
-- ----------------------------
DROP VIEW IF EXISTS `view_address_county`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_address_county` AS select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`en_name` AS `en_name`,`a`.`city_id` AS `city_id`,`b`.`name` AS `city`,`b`.`en_name` AS `en_city`,`c`.`name` AS `province`,`c`.`en_name` AS `en_province` from ((`address_county` `a` left join `address_city` `b` on((`a`.`city_id` = `b`.`id`))) left join `address_province` `c` on((`b`.`province_id` = `c`.`id`))) ;

-- ----------------------------
-- View structure for `view_admin_branch`
-- ----------------------------
DROP VIEW IF EXISTS `view_admin_branch`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_admin_branch` AS select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`grade` AS `grade`,`a`.`open_id` AS `open_id`,`a`.`parent_id` AS `parent_id`,`b`.`name` AS `parent` from (`admin_branch` `a` left join `admin_branch` `b` on((`a`.`parent_id` = `b`.`id`))) ;

-- ----------------------------
-- View structure for `view_admin_group`
-- ----------------------------
DROP VIEW IF EXISTS `view_admin_group`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_admin_group` AS select `a`.`id` AS `id`,`a`.`pid` AS `pid`,`a`.`name` AS `name`,`a`.`branch_id` AS `branch_id`,`b`.`name` AS `branch` from (`admin_group` `a` left join `admin_branch` `b` on((`a`.`branch_id` = `b`.`id`))) ;

-- ----------------------------
-- View structure for `view_admin_license`
-- ----------------------------
DROP VIEW IF EXISTS `view_admin_license`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_admin_license` AS select `a`.`mac` AS `mac`,`a`.`log_time` AS `log_time`,`a`.`branch_id` AS `branch_id`,`b`.`name` AS `branch`,`b`.`grade` AS `grade` from (`admin_license` `a` left join `admin_branch` `b` on((`a`.`branch_id` = `b`.`id`))) ;

-- ----------------------------
-- View structure for `view_admin_log`
-- ----------------------------
DROP VIEW IF EXISTS `view_admin_log`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_admin_log` AS select `a`.`id` AS `id`,`a`.`username` AS `username`,`a`.`fullname` AS `fullname`,`a`.`operation` AS `operation`,`a`.`ip` AS `ip`,`a`.`log_time` AS `log_time`,`a`.`location` AS `location`,`b`.`branch_id` AS `branch_id` from (`admin_log` `a` left join `admin_user` `b` on((`a`.`username` = `b`.`username`))) ;

-- ----------------------------
-- View structure for `view_admin_user`
-- ----------------------------
DROP VIEW IF EXISTS `view_admin_user`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_admin_user` AS select `a`.`username` AS `username`,`a`.`fullname` AS `fullname`,`a`.`password` AS `password`,`a`.`branch_id` AS `branch_id`,`a`.`phone` AS `phone`,`a`.`email` AS `email`,`a`.`role` AS `role`,`a`.`create_time` AS `create_time`,`b`.`cn_name` AS `role_name`,`b`.`home` AS `home`,`c`.`name` AS `branch` from ((`admin_user` `a` left join `admin_role` `b` on((`a`.`role` = `b`.`role`))) left join `admin_branch` `c` on((`a`.`branch_id` = `c`.`id`))) ;

-- ----------------------------
-- View structure for `view_ad_material`
-- ----------------------------
DROP VIEW IF EXISTS `view_ad_material`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_ad_material` AS select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`branch_id` AS `branch_id`,`a`.`file` AS `file`,`a`.`description` AS `description`,`a`.`link` AS `link`,`a`.`target` AS `target`,`a`.`create_time` AS `create_time`,`b`.`name` AS `branch` from (`surfing_ad_material` `a` join `admin_branch` `b`) where (`a`.`branch_id` = `b`.`id`) ;

-- ----------------------------
-- View structure for `view_ap_online_session`
-- ----------------------------
DROP VIEW IF EXISTS `view_ap_online_session`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_ap_online_session` AS select count(0) AS `user_num`,`surfing_session`.`cpe_id` AS `cpe_id` from `surfing_session` where isnull(`surfing_session`.`stop_time`) group by `surfing_session`.`cpe_id` ;

-- ----------------------------
-- View structure for `view_branch_group`
-- ----------------------------
DROP VIEW IF EXISTS `view_branch_group`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_branch_group` AS select `a`.`ap_id` AS `ap_id`,`a`.`group_id` AS `group_id`,`b`.`branch_id` AS `branch_id` from ((`membership_ap_group` `a` left join `admin_group` `b` on((`a`.`group_id` = `b`.`id`))) left join `admin_branch` `c` on((`b`.`branch_id` = `c`.`id`))) ;

-- ----------------------------
-- View structure for `view_cpe_config`
-- ----------------------------
DROP VIEW IF EXISTS `view_cpe_config`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_cpe_config` AS select `a`.`cfg_id` AS `cfg_id`,`a`.`attribute_id` AS `attribute_id`,`a`.`attribute_value` AS `attribute_value`,`b`.`name` AS `attribute_name` from (`cpe_configuration` `a` left join `cpe_property` `b` on((`a`.`attribute_id` = `b`.`id`))) ;

-- ----------------------------
-- View structure for `view_cpe_host`
-- ----------------------------
DROP VIEW IF EXISTS `view_cpe_host`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_cpe_host` AS select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`serial_number` AS `serial_number`,`a`.`product_class` AS `product_class`,`a`.`software_version` AS `software_version`,`a`.`hardware_version` AS `hardware_version`,`a`.`branch_id` AS `branch_id`,`a`.`ssid` AS `ssid`,`a`.`ip_address` AS `ip_address`,`a`.`stun` AS `stun`,`a`.`channel` AS `channel`,`a`.`mode` AS `mode`,`a`.`online` AS `online`,`a`.`up_time` AS `up_time`,`a`.`last_time` AS `last_time`,`a`.`trace` AS `trace`,`a`.`detect` AS `detect`,`d`.`user_num` AS `user_num`,timestampdiff(SECOND,`a`.`up_time`,`a`.`last_time`) AS `diffsec`,`b`.`name` AS `branch` from ((`cpe_host` `a` left join `admin_branch` `b` on((`a`.`branch_id` = `b`.`id`))) left join `view_ap_online_session` `d` on((`a`.`id` = `d`.`cpe_id`))) ;

-- ----------------------------
-- View structure for `view_cpe_software`
-- ----------------------------
DROP VIEW IF EXISTS `view_cpe_software`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_cpe_software` AS select `a`.`id` AS `id`,`a`.`descr` AS `descr`,`a`.`tag` AS `tag`,`a`.`product_model` AS `product_model`,`a`.`version` AS `version`,`a`.`version_code` AS `version_code`,`a`.`size` AS `size`,`a`.`file_name` AS `file_name`,`a`.`md5` AS `md5`,`a`.`upload_time` AS `upload_time`,`a`.`branch_id` AS `branch_id`,`b`.`name` AS `branch` from (`cpe_software` `a` left join `admin_branch` `b` on((`a`.`branch_id` = `b`.`id`))) ;

-- ----------------------------
-- View structure for `view_cpe_ssid`
-- ----------------------------
DROP VIEW IF EXISTS `view_cpe_ssid`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_cpe_ssid` AS select `a`.`ap_id` AS `ap_id`,`a`.`ife` AS `ife`,`a`.`enable` AS `enable`,`a`.`name` AS `name`,`a`.`portal_id` AS `portal_id`,`a`.`policy_id` AS `policy_id`,`b`.`name` AS `portal_name`,`c`.`name` AS `policy_name` from ((`cpe_ssid` `a` left join `portal_instance` `b` on((`a`.`portal_id` = `b`.`id`))) left join `portal_surfing_policy` `c` on((`a`.`policy_id` = `c`.`id`))) ;

-- ----------------------------
-- View structure for `view_cpe_upgrade`
-- ----------------------------
DROP VIEW IF EXISTS `view_cpe_upgrade`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_cpe_upgrade` AS select `a`.`branch_id` AS `branch_id`,`a`.`flag` AS `flag`,`b`.`name` AS `branch` from (`cpe_upgrade` `a` left join `admin_branch` `b` on((`a`.`branch_id` = `b`.`id`))) ;

-- ----------------------------
-- View structure for `view_device_log`
-- ----------------------------
DROP VIEW IF EXISTS `view_device_log`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_device_log` AS select `a`.`id` AS `id`,`a`.`host_id` AS `host_id`,`a`.`message` AS `message`,`a`.`log_time` AS `log_time`,`b`.`name` AS `name`,`b`.`branch_id` AS `branch_id`,`c`.`name` AS `branch` from ((`cpe_device_log` `a` left join `cpe_host` `b` on((`a`.`host_id` = `b`.`id`))) left join `admin_branch` `c` on((`b`.`branch_id` = `c`.`id`))) ;

-- ----------------------------
-- View structure for `view_ebiz_trade`
-- ----------------------------
DROP VIEW IF EXISTS `view_ebiz_trade`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_ebiz_trade` AS select `b`.`name` AS `branch`,`a`.`order_no` AS `order_no`,`a`.`branch_id` AS `branch_id`,`a`.`summary` AS `summary`,`a`.`amount` AS `amount`,`a`.`fee` AS `fee`,`a`.`pay_way` AS `pay_way`,`a`.`state` AS `state`,`a`.`log_time` AS `log_time` from (`surfing_trade` `a` left join `admin_branch` `b` on((`a`.`branch_id` = `b`.`id`))) ;

-- ----------------------------
-- View structure for `view_host_property`
-- ----------------------------
DROP VIEW IF EXISTS `view_host_property`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_host_property` AS select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`cn_name` AS `cn_name`,`a`.`en_name` AS `en_name`,`a`.`descr` AS `descr`,`a`.`tag` AS `tag`,`b`.`host_id` AS `host_id`,`b`.`pid` AS `pid`,`b`.`value` AS `value` from (`cpe_property` `a` join `cpe_host_property` `b` on((`a`.`id` = `b`.`pid`))) ;

-- ----------------------------
-- View structure for `view_portal_instance`
-- ----------------------------
DROP VIEW IF EXISTS `view_portal_instance`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_portal_instance` AS select `a`.`id` AS `id`,`a`.`name` AS `name`,`a`.`branch_id` AS `branch_id`,`a`.`tid` AS `tid`,`a`.`tag` AS `tag`,`b`.`name` AS `branch`,`c`.`name` AS `template`,`c`.`cover` AS `cover` from ((`portal_instance` `a` left join `admin_branch` `b` on((`a`.`branch_id` = `b`.`id`))) left join `portal_template` `c` on((`a`.`tid` = `c`.`id`))) ;

-- ----------------------------
-- View structure for `view_surfing_account`
-- ----------------------------
DROP VIEW IF EXISTS `view_surfing_account`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_surfing_account` AS select `a`.`id` AS `id`,`a`.`username` AS `username`,`a`.`open_id` AS `open_id`,`a`.`mac` AS `mac`,`a`.`branch_id` AS `branch_id`,`a`.`create_time` AS `create_time`,`a`.`password` AS `password`,`a`.`nickname` AS `nickname`,`a`.`flag` AS `flag`,`a`.`times` AS `times`,`a`.`online` AS `online`,`b`.`name` AS `branch` from (`surfing_account` `a` left join `admin_branch` `b` on((`a`.`branch_id` = `b`.`id`))) ;

-- ----------------------------
-- View structure for `view_surfing_advertisement`
-- ----------------------------
DROP VIEW IF EXISTS `view_surfing_advertisement`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_surfing_advertisement` AS select `a`.`id` AS `id`,`a`.`title` AS `title`,`a`.`branch_id` AS `branch_id`,`a`.`content` AS `content`,`a`.`summary` AS `summary`,`a`.`cover` AS `cover`,`a`.`creator` AS `creator`,`a`.`create_time` AS `create_time`,`a`.`category_id` AS `category_id`,`b`.`name` AS `category`,`c`.`name` AS `branch` from ((`surfing_advertisement` `a` left join `surfing_advertise_category` `b` on((`a`.`category_id` = `b`.`id`))) left join `admin_branch` `c` on((`a`.`branch_id` = `c`.`id`))) ;

-- ----------------------------
-- View structure for `view_surfing_black_white`
-- ----------------------------
DROP VIEW IF EXISTS `view_surfing_black_white`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_surfing_black_white` AS select `a`.`id` AS `id`,`a`.`mac` AS `mac`,`a`.`bw` AS `bw`,`a`.`reason` AS `reason`,`a`.`branch_id` AS `branch_id`,`b`.`name` AS `branch` from (`surfing_black_white` `a` left join `admin_branch` `b` on((`a`.`branch_id` = `b`.`id`))) ;

-- ----------------------------
-- View structure for `view_surfing_policy`
-- ----------------------------
DROP VIEW IF EXISTS `view_surfing_policy`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_surfing_policy` AS select `a`.`id` AS `id`,`a`.`branch_id` AS `branch_id`,`a`.`redirect` AS `redirect`,`a`.`name` AS `name`,`a`.`surfing_time` AS `surfing_time`,`a`.`idle_time` AS `idle_time`,`a`.`stay_time` AS `stay_time`,`a`.`auth` AS `auth`,`a`.`pwd_auth` AS `pwd_auth`,`a`.`sms_auth` AS `sms_auth`,`a`.`wechat_auth` AS `wechat_auth`,`a`.`jump_to` AS `jump_to`,`a`.`jump_url` AS `jump_url`,`a`.`cmcc` AS `cmcc`,`a`.`portal_ip` AS `portal_ip`,`a`.`portal_port` AS `portal_port`,`a`.`portal_url` AS `portal_url`,`a`.`one_account_device` AS `one_account_device`,`a`.`wechat_guide` AS `wechat_guide`,`a`.`tag` AS `tag`,`b`.`name` AS `branch` from (`portal_surfing_policy` `a` left join `admin_branch` `b` on((`a`.`branch_id` = `b`.`id`))) ;

-- ----------------------------
-- View structure for `view_surfing_session`
-- ----------------------------
DROP VIEW IF EXISTS `view_surfing_session`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_surfing_session` AS select `a`.`session_id` AS `session_id`,`a`.`username` AS `username`,`a`.`cpe_id` AS `cpe_id`,`a`.`brand` AS `brand`,`a`.`ip_address` AS `ip_address`,`b`.`serial_number` AS `apmac`,`a`.`mac` AS `mac`,`a`.`start_time` AS `start_time`,`a`.`stop_time` AS `stop_time`,`a`.`session_time` AS `session_time`,`a`.`input_octets` AS `input_octets`,`a`.`output_octets` AS `output_octets`,`a`.`snr` AS `snr`,`a`.`flag` AS `flag`,`b`.`name` AS `ap_name`,`b`.`ip_address` AS `ap_ip`,`a`.`ife` AS `ife`,`b`.`branch_id` AS `branch_id`,`c`.`name` AS `branch` from ((`surfing_session` `a` left join `cpe_host` `b` on((`a`.`cpe_id` = `b`.`id`))) left join `admin_branch` `c` on((`b`.`branch_id` = `c`.`id`))) ;

-- ----------------------------
-- View structure for `view_surfing_sms`
-- ----------------------------
DROP VIEW IF EXISTS `view_surfing_sms`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_surfing_sms` AS select `a`.`id` AS `id`,`a`.`mobile` AS `mobile`,`a`.`branch_id` AS `branch_id`,`a`.`content` AS `content`,`a`.`log_time` AS `log_time`,`b`.`name` AS `branch` from (`surfing_sms` `a` left join `admin_branch` `b` on((`a`.`branch_id` = `b`.`id`))) ;

-- ----------------------------
-- View structure for `view_surfing_sms_stat`
-- ----------------------------
DROP VIEW IF EXISTS `view_surfing_sms_stat`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_surfing_sms_stat` AS select `a`.`branch_id` AS `branch_id`,`a`.`pv_total` AS `pv_total`,`a`.`pv_biz` AS `pv_biz`,`a`.`sms_used` AS `sms_used`,`a`.`sms_remain` AS `sms_remain`,`b`.`name` AS `branch` from (`surfing_sms_stat` `a` left join `admin_branch` `b` on((`a`.`branch_id` = `b`.`id`))) ;
