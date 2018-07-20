package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicCustomer;
import com.fintecher.entity.BasicCustomerCar;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.mapper.BasicCustomerCarMapper;
import com.fintecher.manage.service.BasicCustomerCarService;
import com.fintecher.manage.service.BasicCustomerService;
import com.fintecher.manage.vo.BasicCustomerCarModel;
import com.fintecher.util.ZWDateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/15 15:25
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BasicCustomerCarServiceImpl extends BaseServiceImpl<BasicCustomerCar> implements BasicCustomerCarService {
    @Autowired
    private BasicCustomerCarMapper basicCustomerCarMapper;

    @Override
    public void addAssessmentApplication(BasicCustomerCarModel basicCustomerCarModel, SysUser sysUser) {
        BasicCustomerCar basicCustomerCar = new BasicCustomerCar();
        BeanUtils.copyProperties(basicCustomerCarModel,basicCustomerCar);
        basicCustomerCar.setCustomerId(basicCustomerCarModel.getCustomerId());
        basicCustomerCar.setOperator(sysUser.getId());
        basicCustomerCar.setOperatorTime(ZWDateUtil.getNowDate());
        this.save(basicCustomerCar);
    }

    @Override
    public void updateAssessmentApplication(BasicCustomerCarModel basicCustomerCarModel,SysUser sysUser){
        BasicCustomerCar basicCustomerCar = new BasicCustomerCar();
        basicCustomerCar.setId(basicCustomerCarModel.getId());
        BasicCustomerCar basicCustomerCar1 = basicCustomerCarMapper.selectOne(basicCustomerCar);
        if (Objects.isNull(basicCustomerCar1)){
            throw new RuntimeException("客户无此车产，请核对后再修改");
        }
        BeanUtils.copyProperties(basicCustomerCarModel,basicCustomerCar);
        basicCustomerCar.setId(basicCustomerCarModel.getId());
        basicCustomerCar.setCustomerId(basicCustomerCarModel.getCustomerId());
        basicCustomerCar.setOperator(sysUser.getId());
        basicCustomerCar.setOperatorTime(ZWDateUtil.getNowDate());
        this.update(basicCustomerCar);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Map<String,Object>> findCustomerCarList(Long id){
        return basicCustomerCarMapper.getBasicCustomerCarList(id);
    }
}
