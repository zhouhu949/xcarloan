package com.fintecher.manage.service.impl;

import com.fintecher.entity.*;
import com.fintecher.manage.mapper.BasicCarAttrvalueMapper;
import com.fintecher.manage.mapper.BasicCarConfigMapper;
import com.fintecher.manage.mapper.BasicCustomerCarAssessmentMapper;
import com.fintecher.manage.mapper.BasicCustomerCarMapper;
import com.fintecher.manage.service.BasicCustomerCarAssessmentService;
import com.fintecher.manage.service.BasicCustomerCarService;
import com.fintecher.manage.service.FinanceStorageService;
import com.fintecher.manage.vo.*;
import com.fintecher.util.ZWDateUtil;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/15 11:31
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BasicCustomerCarAssessmentServiceImpl extends BaseServiceImpl<BasicCustomerCarAssessment> implements BasicCustomerCarAssessmentService {
    @Autowired
    private BasicCarConfigMapper basicCarConfigMapper;
    @Autowired
    private BasicCustomerCarAssessmentMapper customerCarAssessmentMapper;
    @Autowired
    private BasicCarAttrvalueMapper basicCarAttrvalueMapper;
    @Autowired
    private FinanceStorageService financeStorageService;
    /**
     * @auther: dwx
     * @Description:新增评估
     */
    @Override
    public void addBasicCustomerAssessment(BasicCustomerCarAssessmentModel customerCarAssessmentModel,SysUser sysUser){
        BasicCustomerCarAssessment basicCustomerCarAssessment = new BasicCustomerCarAssessment();
        BasicCarConfig basicCarConfig = new BasicCarConfig();
        FindCarByFinanceStorageModel basicCustomerOrder =customerCarAssessmentMapper.findOrderByFinanceStorage(customerCarAssessmentModel.getCarId());
        if ((Objects.isNull(basicCustomerOrder)) || Objects.equals(basicCustomerOrder.getOrderStatus(),BasicCustomerOrder.OrderStatus.UNFILLED_DATA.getValue()))
        {
            checkData(customerCarAssessmentModel);
            BeanUtils.copyProperties(customerCarAssessmentModel, basicCustomerCarAssessment);
            //set评估编号
            StringBuffer stringBuffer = new StringBuffer("R");
            stringBuffer.append(ZWDateUtil.getYMDDate());
            stringBuffer.append("8719");
            stringBuffer.append(String.valueOf(new Random().nextInt(999) % 900 + 100));
            basicCustomerCarAssessment.setAssessmentNo(stringBuffer.toString());
            basicCustomerCarAssessment.setAssessmentStatus(BasicCustomerCarAssessment.AssessmentStatus.PASS_THROUGH.getValue());
            basicCustomerCarAssessment.setOperator(sysUser.getId());
            basicCustomerCarAssessment.setOperatorTime(ZWDateUtil.getNowDateTime());
            customerCarAssessmentMapper.insert(basicCustomerCarAssessment);
            BeanUtils.copyProperties(customerCarAssessmentModel, basicCarConfig);
            basicCarConfigMapper.insert(basicCarConfig);
            List<CarAttributeModel> list = customerCarAssessmentModel.getCarAttributeModelList();
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    CarAttributeModel carAttributeModel = new CarAttributeModel();
                    BeanUtils.copyProperties(list.get(i), carAttributeModel);
                    BasicCarAttrvalue basicCarAttrvalue = new BasicCarAttrvalue();
                    basicCarAttrvalue.setBasicId(basicCustomerCarAssessment.getId());
                    basicCarAttrvalue.setAttrName(carAttributeModel.getAttrCode());
                    basicCarAttrvalue.setAttrValue(carAttributeModel.getAttrValue());
                    basicCarAttrvalueMapper.insert(basicCarAttrvalue);
                }
            }
            FinanceStorage financeStorage = new FinanceStorage();
            FinanceStorage financeStorage1 = customerCarAssessmentMapper.findCarByFianceStorage(customerCarAssessmentModel.getCarId());
            if (Objects.nonNull(financeStorage1)) {
                financeStorage.setId(financeStorage1.getId());
                financeStorage.setAssessmentId(basicCustomerCarAssessment.getId());
                financeStorageService.updateSelective(financeStorage);
            }
        }else {
            throw new RuntimeException("抱歉，此客户车产不满足评估条件！！！");
        }
    }

    private void checkData(BasicCustomerCarAssessmentModel customerCarAssessmentModel) {
        if (customerCarAssessmentModel.getAssessmentApplyDate() ==null){
            throw new ServiceException("申请评估日期为空");
        }
        if (customerCarAssessmentModel.getAssessmentDate() ==null){
            throw new ServiceException("评估日期为空");
        }
        if (customerCarAssessmentModel.getAssessmentStatus() ==null){
            throw new ServiceException("评估状态为空");
        }
        if (customerCarAssessmentModel.getCarSituation() ==null){
            throw new ServiceException("车况为空");
        }
        if (customerCarAssessmentModel.getDisplacement() ==null){
            throw new ServiceException("车辆估计为空");
        }
    }

    @Override
    public List<CustomerAssessmentListModel> findCustomerAssessmentList(String customerName,String carNo,Integer assessmentStatus){
        return customerCarAssessmentMapper.findCustomerAssessmentList(customerName,carNo,assessmentStatus);
    }
    @Override
    public List findAssessmentConfigList(){
        return customerCarAssessmentMapper.findAssessmentConfigList();
    }

    @Override
    public List<CustomerAssessmentReportModel> findAssessmentReportList(Long carId){
        return customerCarAssessmentMapper.findAssessmentReportList(carId);
    }

    @Override
    public CustomerAssessmentInfoModel findAssessmentReportInfo(Long assessmentId){
        return customerCarAssessmentMapper.findAssessmentReportInfo(assessmentId);
    }

    @Override
    public List<CarAttributeModel> findAllCarAttrValue(Long assessmentId) {
        return customerCarAssessmentMapper.findAllCarAttrValue(assessmentId);
    }
}
