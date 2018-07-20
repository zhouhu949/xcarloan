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
 * @Description: 系统备份实体类
 * @Date:Created on 2017/12/25/025 13:13
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "系统备份实体类")
@EqualsAndHashCode(callSuper = false)
@Table(name = "system_backup")
public class SysBackup extends BaseEntity {
    /**
     * 备份类型
     */
    @ApiModelProperty(value = "备份类型", notes = "0：自动 1：手动")
    @Column(name = "type")
    private Integer type;
    /**
     * mysql数据库文件名称
     */
    @ApiModelProperty(value = "mysql数据库文件名称", notes = "mysql数据库文件名称")
    @Column(name = "mysql_name")
    private String mysqlName;
    /**
     * mongdb数据库名称
     */
    @ApiModelProperty(value = "mongdb数据库名称", notes = "mongdb数据库名称")
    @Column(name = "mongdb_name")
    private String mongdbName;
    @ApiModelProperty(notes = "操作员")
    @Column(name = "operator")
    private Long operator;
    @ApiModelProperty(notes = "操作时间")
    @Column(name = "operate_time")
    private Date operateTime;

}
