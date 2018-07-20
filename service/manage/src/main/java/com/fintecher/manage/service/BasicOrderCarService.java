package com.fintecher.manage.service;

import com.fintecher.entity.BasicOrderCar;
import com.fintecher.entity.SysUser;

import java.util.List;

public interface BasicOrderCarService extends BaseService<BasicOrderCar> {

    //订单车车辆

    List<BasicOrderCar> findOrderCar(SysUser user,Integer hasSupplierLoan);
}
