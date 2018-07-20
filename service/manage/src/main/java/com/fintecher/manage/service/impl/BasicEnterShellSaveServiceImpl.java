package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicCarModel;
import com.fintecher.entity.BasicOrderCar;
import com.fintecher.entity.BasicStockCar;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.mapper.BasicEnterShellSaveMapper;
import com.fintecher.manage.mapper.BasicOrderCarMapper;
import com.fintecher.manage.mapper.BasicStockCarMapper;
import com.fintecher.manage.mapper.CarModelMapper;
import com.fintecher.manage.service.BasicEnterShellSaveService;
import com.fintecher.manage.service.UserService;
import com.fintecher.manage.vo.AddOrderCarStockParams;
import com.fintecher.manage.vo.BasicEnterShellSaveParams;
import com.fintecher.manage.vo.BasicGetOrderCarDetil;
import com.fintecher.util.ZWDateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: BasicEnterShellSaveServiceImpl
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/22 0022下午 19:58
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class BasicEnterShellSaveServiceImpl implements BasicEnterShellSaveService {


    @Autowired
    private BasicEnterShellSaveMapper basicEnterShellSaveMapper;
    @Autowired
    private BasicStockCarMapper basicStockCarMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<BasicEnterShellSaveParams> getOutSideList(String orderNo, String customerName, Long stockStatus, SysUser user,List<String>allDataAuth,List<String>allExceptDataAuth) {

        return basicEnterShellSaveMapper.getOutSideList(orderNo, customerName, stockStatus, allDataAuth, allExceptDataAuth);
    }

    @Override
    public BasicGetOrderCarDetil getOrderCarById(Long id) {
        return basicEnterShellSaveMapper.getOrderCarById(id);
    }

    @Override
    public void updateOrderCarStock(AddOrderCarStockParams addOrderCarStockParams, SysUser user) {

        BasicStockCar basicStockCar = new BasicStockCar();
        basicStockCar.setOperator(user.getId());
        basicStockCar.setId(addOrderCarStockParams.getStockId());
        BeanUtils.copyProperties(addOrderCarStockParams, basicStockCar);
        basicStockCar.setSupplierId(addOrderCarStockParams.getSupplierId());
        basicStockCar.setOperatorTime(ZWDateUtil.getNowDateTime());
        basicStockCar.setStockStatus(BasicStockCar.StockStatus.BASIC_STOCKSTATUS_ZBZ.getValue());
        basicStockCar.setStockInDate(ZWDateUtil.getNowDateTime());
        basicStockCarMapper.updateByPrimaryKeySelective(basicStockCar);

    }
}
