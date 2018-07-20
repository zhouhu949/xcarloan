package com.fintecher.manage.service;

import com.fintecher.entity.SysUser;
import com.fintecher.manage.vo.AddOrderCarStockParams;
import com.fintecher.manage.vo.BasicEnterShellSaveParams;
import com.fintecher.manage.vo.BasicGetOrderCarDetil;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: BasicEnterShellSaveService
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/22 0022下午 19:57
 */
public interface BasicEnterShellSaveService {

   List<BasicEnterShellSaveParams> getOutSideList(String orderNo, String customerName, Long stockStatus, SysUser user,List<String>allDataAuth,List<String>allExceptDataAuth);

   BasicGetOrderCarDetil getOrderCarById(Long id);

   void updateOrderCarStock(AddOrderCarStockParams addOrderCarStockParams, SysUser user);

}
