package com.fintecher.manage.service;


import com.fintecher.entity.SysDict;
import com.fintecher.entity.SysDictItem;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */
public interface DataDictService {
    List<SysDict> findDataDictByTypeCode(Integer id, String dictItemName);

    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 查询数据字典项
     * @Modified By:
     */
    List<SysDictItem> getAll();

    List<SysDictItem>findAll();

    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 删除数据字典项
     * @Modified By:
     */
    Integer deleteDataDict(SysDictItem sysDictItem);

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
     * @Description: 保存数据字典项
     * @Modified By:
     */
    void saveDataDict(SysDictItem sysDictItem);

    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 更新数据字典项
     * @Modified By:
     */
    void updateDataDict(SysDictItem sysDictItem);

    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 获取最大排序
     * @Modified By:
     */
    Integer getMaxSort(String typeCode);

    /**
     * @System: 车贷金融
     * @Auther: mouliang
     * @Description: 查询DataDict
     * @Modified By:
     */
    SysDictItem findOneByDataDict(SysDictItem sysDictItem);

    /**
     * @auther: dwx
     * @Description:通过字典类型查询字典
     */
    List<SysDictItem> findDataDictByType(Integer dictType);

    /**
     * @auther: dwx
     * @Description:通过字典类型id获取字典项列表
     */
    List<SysDict>getSysDictList(Integer id);

    /**
     * @auther: dwx
     * @Description:根据条件查询一条数据，如果有多条数据会抛出异常
     */
    SysDictItem findOne(SysDictItem record);

}
