package com.fintecher.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description: 数据字典实体类
 * @Date:Created on 2017/12/25/025 13:13
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "数据字典类型实体类")
@EqualsAndHashCode(callSuper = false)
@Table(name = "sys_dict")
public class SysDict implements java.io.Serializable {
    /**
     * 主键id
     */
    @Id
    @ApiModelProperty(value = "主键ID", notes = "主键ID")
    @Column(name = "id")
    private Integer id;
    /**
     * 数据字典项code
     */
    @ApiModelProperty(value = "数据字典项code", notes = "数据字典项code")
    @Column(name = "dict_type")
    private Integer dictType;
    /**
     * 数据字典code
     */
    @ApiModelProperty(value = "数据字典code", notes = "数据字典code")
    @Column(name = "dict_code")
    private String dictCode;
    /**
     * 数据字典名称
     */
    @ApiModelProperty(value = "数据字典名称", notes = "数据字典名称")
    @Column(name = "dict_name")
    private String dictName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", notes = "备注")
    @Column(name = "remark")
    private String remark;
    /**
     * 数据字典排序
     */
    @ApiModelProperty(value = "数据字典排序", notes = "数据字典排序")
    @Column(name = "dict_sort")
    private Integer dicSort;

    /**
     *
    * 数据字典类型枚举
     */
    public enum DictType{
        SYSTEM_DICTIONARY(10000,"系统字典"),
        USER_DICTIONARY(10001,"机构用户自定义字典");
        private Integer value;
        private String type;
        DictType(Integer value,String type){
            this.value = value;
            this.type = type;
        }
        public Integer getValue(Integer value){
            return value;
        }
        public String getType(String type){
            return type;
        }
    }
}
