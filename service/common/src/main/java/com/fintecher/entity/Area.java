package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description: 区域实体类
 * @Date:Created on 2017/12/25/025 13:13
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "区域实体类")
@EqualsAndHashCode(callSuper = false)
@Table(name = "area_code")
public class Area extends BaseEntity {

    /**
     * 父级节点
     */
    @ApiModelProperty(value = "父级节点", notes = "父级节点")
    @Column(name = "parent_id")
    private Long parentId;
    /**
     * 地域路由
     */
    @ApiModelProperty(value = "地域路由", notes = "地域路由")
    @Column(name = "tree_path")
    private String treePath;
    /**
     * 地域编码
     */
    @ApiModelProperty(value = "地域编码", notes = "地域编码")
    @Column(name = "area_code")
    private String areaCode;
    /**
     * 地域名称
     */
    @ApiModelProperty(value = "地域名称", notes = "地域名称")
    @Column(name = "area_name")
    private String areaName;
    /**
     * 地区名称拼音
     */
    @ApiModelProperty(value = "地域名称拼音", notes = "地域名称拼音")
    @Column(name = "area_english_name")
    private String areaEnglishName;
    /**
     * 银行地域支付编码
     */
    @ApiModelProperty(value = "银行地域支付编码", notes = "银行地域支付编码")
    @Column(name = "bank_code")
    private String bankCode;
    /**
     * 地区邮编
     */
    @ApiModelProperty(value = "地区邮编", notes = "地区邮编")
    @Column(name = "zip_code")
    private String zipCode;
    /**
     * 地区邮编
     */
    @ApiModelProperty(value = "电话区号", notes = "电话区号")
    @Column(name = "zone_code")
    private String zoneCode;


    @ApiModelProperty(notes = "操作员")
    @Column(name = "operator")
    private Long operator;

    @ApiModelProperty(notes = "操作时间")
    @Column(name = "operate_time")
    private Date operateTime;
}
