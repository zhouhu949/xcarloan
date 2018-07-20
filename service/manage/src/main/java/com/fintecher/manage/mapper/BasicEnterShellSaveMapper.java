package com.fintecher.manage.mapper;

import com.fintecher.manage.vo.BasicEnterShellSaveParams;
import com.fintecher.manage.vo.BasicGetOrderCarDetil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: BasicEnterShellSaveMapper
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/22 0022下午 19:59
 */
public interface BasicEnterShellSaveMapper {
   List<BasicEnterShellSaveParams> getOutSideList(@Param("orderNo") String orderNo, @Param("customerName") String customerName, @Param("stockStatus") Long stockStatus, @Param("allDataAuth") List<String> allDataAuth, @Param("allExceptDataAuth") List<String> allExceptDataAuth);

   BasicGetOrderCarDetil getOrderCarById(@Param("id") Long id);
}
