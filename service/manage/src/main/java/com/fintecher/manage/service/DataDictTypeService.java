package com.fintecher.manage.service;


import com.fintecher.entity.SysDict;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */
public interface DataDictTypeService {


    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 获取数据字典项类型最大ID
     * @Modified By:
     */
    Integer getMaxId();

    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 保存数据字典项类型
     * @Modified By:
     */
    Integer saveDataDict(SysDict dataDictType);

    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 更新数据字典项类型
     * @Modified By:
     */
    Integer updateDataDict(SysDict dataDictType);

    /**
     * @auther: dwx
     * @Description:根据条件查询一条数据，如果有多条数据会抛出异常
     */
     SysDict findOne(SysDict record);

     List<SysDict>selectListAll();
     List<SysDict>findAll();




}
