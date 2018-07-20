package com.fintecher.manage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.omg.CORBA.INTERNAL;

import javax.persistence.Id;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/13 15:59
 * @Description:
 */
@Data
public class SysDictModel {
    @Id
    @ApiModelProperty(value = "主键ID", notes = "主键ID")
    private Integer id;
    /**
     * 数据字典项code
     */
    @ApiModelProperty(value = "数据字典项code", notes = "数据字典项code")
    private Integer dictType;
    /**
     * 数据字典code
     */
    @ApiModelProperty(value = "数据字典code", notes = "数据字典code")
    private String dictCode;
    /**
     * 数据字典名称
     */
    @ApiModelProperty(value = "数据字典名称", notes = "数据字典名称")
    private String dictName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", notes = "备注")
    private String remark;
    /**
     * 数据字典排序
     */
    @ApiModelProperty(value = "数据字典排序", notes = "数据字典排序")
    private Integer dicSort;
}
