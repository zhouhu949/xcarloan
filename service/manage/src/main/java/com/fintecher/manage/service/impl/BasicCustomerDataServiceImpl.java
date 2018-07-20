package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicCustomer;
import com.fintecher.entity.BasicCustomerData;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.BasicCustomerDataService;
import com.fintecher.manage.service.BasicCustomerService;
import com.fintecher.manage.vo.BasicCustomerDataListModel;
import com.fintecher.manage.vo.BasicCustomerDataModel;
import com.fintecher.util.ZWDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/21 13:49
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BasicCustomerDataServiceImpl extends BaseServiceImpl<BasicCustomerData> implements BasicCustomerDataService {
    @Autowired
    private BasicCustomerService basicCustomerService;
    @Override
    public void addCustomerData(BasicCustomerDataListModel basicCustomerDataModel, SysUser sysUser){
        BasicCustomer basicCustomer = new BasicCustomer();
        basicCustomer.setId(basicCustomerDataModel.getBasicCustomerDataModels().get(0).getCustomerId());
        List<BasicCustomer> list = basicCustomerService.findListByWhere(basicCustomer);
        if (list.isEmpty()){
            throw new RuntimeException("无此客户，无法上传资料");
        }
        if (!basicCustomerDataModel.getBasicCustomerDataModels().isEmpty()){
            List<BasicCustomerDataModel> basicCustomerDataModels = basicCustomerDataModel.getBasicCustomerDataModels();
            for (BasicCustomerDataModel basicCustomerDataModel1 : basicCustomerDataModels){
                BasicCustomerData basicCustomerData = new BasicCustomerData();
                basicCustomerData.setCustomerId(basicCustomerDataModel1.getCustomerId());
                basicCustomerData.setDataType(basicCustomerDataModel1.getDataType());
                basicCustomerData.setFileUrl(basicCustomerDataModel1.getFileUrl());
                basicCustomerData.setFileName(basicCustomerDataModel1.getFileName());
                this.save(basicCustomerData);
            }
        }

    }
}
