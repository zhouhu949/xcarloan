package com.fintecher.manage.mapper;


import com.fintecher.entity.SysDict;
import com.fintecher.entity.SysDictItem;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.ExportDataDict;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */
public interface DataDictMapper extends MyMapper<SysDictItem> {
    List<SysDict> findDataDictByTypeCode(@Param("id") Integer id,
                                          @Param("dictItemName") String dictItemName);

    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 查询数据字典类型对应的数据字典项
     * @Modified By:
     */
    List<SysDict> getSysDictList(@Param("id") Integer id);

    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 获取数据字典项最大ID
     * @Modified By:
     */
    Integer getMaxId();

    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 获取最大排序
     * @Modified By:
     */
    Integer getMaxSort(@Param("typeCode") String typeCode);
    /**
     * @Description:导出数据字典项
     * @Modified By:
     */


    @Select("select t1.name as typeName,t2.name as dataName ,t2.sort from data_dict_type t1 INNER JOIN data_dict t2  ON  t1.code = t2.type_code")
    List<ExportDataDict> findAllDataDict();


    /**
     * @System: 车贷金融
     * @Auther: mouliang
     * @Description: 查询DataDict
     * @Modified By:
     */
    SysDictItem findOneByDataDict(SysDictItem sysDictItem);

    /**
     * @auther: dwx
     * @Description:通过字典类型查询数据字典
     */
    List<SysDictItem> findDataDictByType(@Param("dictType") Integer dictType);

    /**
     * @auther: dwx
     * @Description:获取全部系统字典
     */
    List<SysDictItem> getAll();

    List<SysDictItem>findAll();

    /**
     * @auther: dwx
     * @Description:根据字典名称查询
     */
    List<SysDictItem> queryDictItemName(@Param("id") Integer id);
}
