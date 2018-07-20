package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "系统序列值管理表，暂时用来管理订单编号")
@Entity
@Table(name = "sys_seq")
public class SysSeq {
    @ApiModelProperty("序列名称")
    private String seqName;
    @ApiModelProperty("当前值")
    private Integer currentSeq;
}
