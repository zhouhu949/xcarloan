package com.fintecher.manage.service;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.BasicStockCar;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.BasicStockCarListParams;
import com.fintecher.manage.vo.BasicStockCarParams;
import com.fintecher.manage.vo.BasicStockCarSearch;
import com.fintecher.manage.vo.EditBasicStockCarParams;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: BasicStockCarService
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/14 0014下午 17:14
 */
public interface BasicStockCarService   extends BaseService<BasicStockCar> {


   void addBasicStock(BasicStockCarParams basicStockCarParams, SysUser user);

   List<BasicStockCarListParams> findAllStockCarList(BasicStockCarSearch basicStockCarSearch, Long orgId);


   ResponseResult updateeditCarStockStatus(EditBasicStockCarParams editBasicStockCarParams, SysUser user);

   ResponseResult editCarStockStatus(Long id, SysUser user, Long orderId);

   List<BasicStockCar> selectBySupplier(BasicStockCar basicStockCar);
}
