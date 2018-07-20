package com.fintecher.manage.service;

import com.fintecher.entity.BasicSupplier;
import com.fintecher.manage.vo.BasicSupplierParams;
import com.fintecher.manage.vo.BasicSupplierSearch;
import com.fintecher.manage.vo.PageParam;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: BasicSupplierService
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/14 0014上午 10:45
 */
public interface BasicSupplierService  extends  BaseService<BasicSupplier> {




   List<BasicSupplier> getSupplierInfo(BasicSupplierSearch basicSupplierSearch, Long orgId);
}
