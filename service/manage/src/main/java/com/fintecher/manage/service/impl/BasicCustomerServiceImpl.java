package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicCustomer;
import com.fintecher.entity.BasicCustomerBank;
import com.fintecher.entity.BasicCustomerIntention;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.mapper.BasicCustomerBankMapper;
import com.fintecher.manage.mapper.BasicCustomerIntentionMapper;
import com.fintecher.manage.mapper.BasicCustomerMapper;
import com.fintecher.manage.service.BasicCustomerService;
import com.fintecher.manage.vo.BasicCustomerBankModel;
import com.fintecher.manage.vo.BasicCustomerParams;
import com.fintecher.manage.vo.BasicCustomerSearch;
import com.fintecher.manage.vo.EditBasicCustomerParams;
import com.fintecher.util.ZWDateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author ZhangYaJun
 * @Title: BasicCustomerServiceImpl
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/19 0019下午 14:51
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class BasicCustomerServiceImpl extends BaseServiceImpl<BasicCustomer> implements BasicCustomerService {

    @Autowired
    private BasicCustomerBankMapper basicCustomerBankMapper;
    @Autowired
    private BasicCustomerMapper basicCustomerMapper;
    @Autowired
    private BasicCustomerIntentionMapper basicCustomerIntentionMapper;


    @Override
    public List<BasicCustomer> findAllCustomerList(String customerName, Long orgId) {

        Example example1 = new Example(BasicCustomer.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("orgId", orgId);
        if (Objects.nonNull(customerName)) {
            criteria1.andLike("customerName", "%" + customerName + "%");
        }
        List<BasicCustomer> basicCustomers = selectByExample(example1);

        return basicCustomers;

    }

    @Override
    public List findCustomerList(BasicCustomerSearch basicCustomerSearch, Long orgId, Integer customerStatus) {

        BasicCustomer basicCustomer = new BasicCustomer();
        BeanUtils.copyProperties(basicCustomerSearch, basicCustomer);
        basicCustomer.setOrgId(orgId);
        basicCustomer.setCustomerStatus(customerStatus);
        List basicCustomers = basicCustomerMapper.queryCustomerByCondition(basicCustomer);

        return basicCustomers;

    }

    @Override
    public List findNotBlackCustomerList(BasicCustomerSearch basicCustomerSearch, Long orgId) {

        Example example1 = new Example(BasicCustomer.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("orgId", orgId);
        if (Objects.nonNull(basicCustomerSearch.getCustomerName())) {
            criteria1.andLike("customerName", "%" + basicCustomerSearch.getCustomerName() + "%");
        }

        BasicCustomer basicCustomer = new BasicCustomer();
        BeanUtils.copyProperties(basicCustomerSearch, basicCustomer);
        basicCustomer.setOrgId(orgId);
        basicCustomer.setCustomerStatus(BasicCustomer.CustomerStatus.CUSTOMER_STATUS_HMD.getValue());
        List basicCustomers = basicCustomerMapper.findNotBlackCustomerList(basicCustomer);

        return basicCustomers;

    }

    @Override
    public void saveCustomerAccount(BasicCustomerBankModel basicCustomerBankModel, SysUser sysUser) {
        BasicCustomerBank basicCustomerBank = new BasicCustomerBank();
        BeanUtils.copyProperties(basicCustomerBankModel, basicCustomerBank);
        basicCustomerBank.setClientNumber(UUID.randomUUID().toString());
        basicCustomerBank.setAccountStatus(BasicCustomerBank.OpenAccountStatus.SUCCESS.getValue());
        basicCustomerBank.setOperator(sysUser.getId());
        basicCustomerBank.setOperatorTime(ZWDateUtil.getNowDateTime());
        basicCustomerBankMapper.updateByPrimaryKey(basicCustomerBank);
        BasicCustomer basicCustomer = this.findById(basicCustomerBank.getCustomerId());
        basicCustomer.setAccountStatus(BasicCustomer.OpenAccountStatus.SUCCESS.getValue());
        basicCustomer.setOperator(sysUser.getId());
        basicCustomerMapper.updateByPrimaryKey(basicCustomer);
    }

    @Override
    public void addBasicCustomer(BasicCustomerParams basicCustomerParams, SysUser user) {
        // 保存客户
        BasicCustomer basicCustomer = new BasicCustomer();
        //判断添加客户时身份证号和手机号是否存在
        basicCustomer.setCustomerPhone(basicCustomerParams.getCustomerPhone());
        basicCustomer.setIdCard(basicCustomerParams.getIdCard());
        BasicCustomer basicCustomer1 = basicCustomerMapper.findCustomerInfoByCustomer(basicCustomer.getCustomerPhone(),basicCustomer.getIdCard());
        if (Objects.nonNull(basicCustomer1)){
            throw new RuntimeException("此客户信息已存在，请核对后输入");
        }

        BeanUtils.copyProperties(basicCustomerParams, basicCustomer);
        basicCustomer.setCustomerCode(UUID.randomUUID().toString());
        basicCustomer.setOrgId(user.getOrgId());
        basicCustomer.setOperator(user.getId());
        basicCustomer.setCreateTime(ZWDateUtil.getNowDateTime());
        basicCustomer.setOperatorTime(ZWDateUtil.getNowDateTime());
        basicCustomer.setCustomerStatus(BasicCustomer.CustomerStatus.CUSTOMER_STATUS_YXKH.getValue());
        this.save(basicCustomer);

        // 意向记录
        if (Objects.nonNull(basicCustomerParams.getIntentionLevel())) {
            BasicCustomerIntention basicCustomerIntention = new BasicCustomerIntention();
            basicCustomerIntention.setCustomerId(basicCustomer.getId());
            basicCustomerIntention.setIntentionLevel(basicCustomerParams.getIntentionLevel());
            basicCustomerIntention.setIntentionType(basicCustomerParams.getIntentionType());
            basicCustomerIntention.setIsLastIntention(BasicCustomerIntention.IsLastIntention.TRUE.getValue());
            basicCustomerIntention.setOperator(user.getId());
            basicCustomerIntention.setIntentionStatus(BasicCustomerIntention.IntentionStatus.BASIC_INTENTION_STATUS_WGJ.getValue());
            basicCustomerIntention.setOperatorTime(ZWDateUtil.getNowDateTime());
            basicCustomerIntentionMapper.insert(basicCustomerIntention);
        }

    }

    @Override
    public void editBasicCustomer(EditBasicCustomerParams editBasicCustomerParams, SysUser user) {
        BasicCustomer basicCustomer = this.findById(editBasicCustomerParams.getId());
        BeanUtils.copyProperties(editBasicCustomerParams, basicCustomer);
        basicCustomer.setId(basicCustomer.getId());
        basicCustomer.setOperator(user.getId());
        basicCustomer.setOperatorTime(ZWDateUtil.getNowDateTime());
        this.updateSelective(basicCustomer);
    }

    /**
     * @auther: dwx
     * @Description:获取签约列表
     */
    @Override
    public List<BasicCustomer> findCustomerSignList(String customerName) {
        return basicCustomerMapper.findCustomerSignList(customerName);
    }

    /**
     * @auther: dwx
     * @Description:获取意向客户列表
     */
    @Override
    public List findCustomerIntentionList(BasicCustomerSearch basicCustomerSearch, Long orgId, Integer customerStatus) {
        BasicCustomer basicCustomer = new BasicCustomer();
        BeanUtils.copyProperties(basicCustomerSearch, basicCustomer);
        basicCustomer.setOrgId(orgId);
        basicCustomer.setCustomerStatus(customerStatus);
        List list = basicCustomerMapper.findCustomerIntentionList(basicCustomer);
        return list;
    }

    @Override
    public BasicCustomer findCustomerInfoByOrderId(Long orderId) {
        return basicCustomerMapper.findCustomerInfoByOrderId(orderId);
    }

}
