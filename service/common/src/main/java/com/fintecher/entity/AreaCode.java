package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;

@ApiModel(description = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "area_code")
public class AreaCode extends BaseEntity{

    @ApiModelProperty(value="父级节点",notes="父级节点")
    @Column(name ="parent_id")
    //typeInDatabase = int(11)
    private Integer parentId;

    @ApiModelProperty(value="地域路由",notes="地域路由")
    @Column(name ="tree_path")
    //typeInDatabase = varchar(50)
    private String treePath;

    @ApiModelProperty(value="地域编码",notes="地域编码")
    @Column(name ="area_code")
    //typeInDatabase = varchar(20)
    private String areaCode;

    @ApiModelProperty(value="地域名称",notes="地域名称")
    @Column(name ="area_name")
    //typeInDatabase = varchar(100)
    private String areaName;

    @ApiModelProperty(value="地区名称拼音",notes="地区名称拼音")
    @Column(name ="area_english_name")
    //typeInDatabase = varchar(200)
    private String areaEnglishName;

    @ApiModelProperty(value="银行地域支付编码",notes="银行地域支付编码")
    @Column(name ="bank_code")
    //typeInDatabase = varchar(20)
    private String bankCode;

    @ApiModelProperty(value="地区邮编",notes="地区邮编")
    @Column(name ="zip_code")
    //typeInDatabase = varchar(10)
    private String zipCode;

    @ApiModelProperty(value="区号",notes="区号")
    @Column(name ="zone_code")
    //typeInDatabase = varchar(5)
    private String zoneCode;


}