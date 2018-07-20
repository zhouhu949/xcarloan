package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicCustomerOrder;
import com.fintecher.entity.BasicOrderFile;
import com.fintecher.entity.BasicOrderRecord;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.mapper.BasicOrderFileMapper;
import com.fintecher.manage.mapper.BasicOrderRecordMapper;
import com.fintecher.manage.service.BasicCustomerOrderService;
import com.fintecher.manage.service.BasicOrderFileService;
import com.fintecher.manage.vo.BasicOrderFileModel;
import com.fintecher.manage.vo.CustomerOrderFileModel;
import com.fintecher.util.ZWDateUtil;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/14 11:18
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BasicOrderFileServiceImpl extends BaseServiceImpl<BasicOrderFile> implements BasicOrderFileService {
    /**
     * @auther: dwx
     * @Description:补充上传资料
     */
    @Autowired
    private BasicCustomerOrderService basicCustomerOrderService;
    @Autowired
    private BasicOrderRecordMapper basicOrderRecordMapper;
    @Autowired
    private BasicOrderFileMapper basicOrderFileMapper;
    @Override
    public void uploadBasicOrderFile(BasicOrderFileModel basicOrderFileModel, SysUser sysUser){
        BasicOrderRecord basicOrderRecord = new BasicOrderRecord();
        Long orderId = basicOrderFileModel.getCustomerOrderFileModels().get(0).getOrderId();
        BasicCustomerOrder basicCustomerOrder = new BasicCustomerOrder();
        basicCustomerOrder.setId(orderId);
        List<BasicCustomerOrder> list = basicCustomerOrderService.findListByWhere(basicCustomerOrder);
        if (list.isEmpty()){
            throw new ServiceException("无此订单，无法进行上传资料");
        }
        if (!basicOrderFileModel.getCustomerOrderFileModels().isEmpty()){
            List<CustomerOrderFileModel> customerOrderFileModelList = basicOrderFileModel.getCustomerOrderFileModels();
            for (CustomerOrderFileModel customerOrderFileModel:customerOrderFileModelList){
                BasicOrderFile basicOrderFile = new BasicOrderFile();
                basicOrderFile.setOrderFileType(customerOrderFileModel.getFileType());
                basicOrderFile.setFileUrl(customerOrderFileModel.getFileUrl());
                basicOrderFile.setOrderId(customerOrderFileModel.getOrderId());
                basicOrderFile.setFileName(customerOrderFileModel.getFileName());
                this.save(basicOrderFile);
            }
        }
        basicOrderRecord.setOperator(sysUser.getId());
        basicOrderRecord.setOperatorTime(ZWDateUtil.getNowDate());
        basicOrderRecordMapper.insert(basicOrderRecord);

    }

    @Override
    public void updateBasicOrderFile(BasicOrderFileModel basicOrderFileModel,SysUser sysUser){
        BasicOrderFile basicOrderFile = new BasicOrderFile();
        BasicOrderRecord basicOrderRecord = new BasicOrderRecord();
        basicOrderFile.setId(basicOrderFileModel.getId());
        BasicOrderFile orderFile = basicOrderFileMapper.selectByPrimaryKey(basicOrderFile.getId());
        if (orderFile == null){
            throw new ServiceException("此文件不存在，无法修改");
        }else {
            basicOrderFile.setOrderId(basicOrderFileModel.getCustomerOrderFileModels().get(0).getOrderId());
            basicOrderFileMapper.updateByPrimaryKey(basicOrderFile);
            basicOrderRecord.setOperator(sysUser.getId());
            basicOrderRecord.setOperatorTime(ZWDateUtil.getNowDate());
            basicOrderRecordMapper.insert(basicOrderRecord);

        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public BasicOrderFile findUploadBasicFile(Long fileId){
        return basicOrderFileMapper.findUploadBasicFile(fileId);
    }

}
