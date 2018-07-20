package com.fintecher.manage.service.impl;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.BasicCustomer;
import com.fintecher.entity.BasicCustomerIntention;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.BasicCustomerIntentionService;
import com.fintecher.manage.service.BasicCustomerService;
import com.fintecher.manage.vo.BasicCustomerAndIntentionParams;
import com.fintecher.manage.vo.BasicCustomerIntentionParams;
import com.fintecher.manage.vo.EditBasicCustomerIntentionParams;
import com.fintecher.util.ZWDateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author ZhangYaJun
 * @Title: BasicCustomerIntentionServiceImpl
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/19 0019下午 16:58
 */

@Service
@Transactional(rollbackOn = Exception.class)
public class BasicCustomerIntentionServiceImpl extends BaseServiceImpl<BasicCustomerIntention> implements BasicCustomerIntentionService {

    @Autowired
    private BasicCustomerService basicCustomerService;

    @Override
    public void saveIntentionCustomer(BasicCustomerIntentionParams basicCustomerIntentionParams, SysUser user) {
        BasicCustomerIntention basicCustomerIntention = new BasicCustomerIntention();
        //判断是否是最后一次意向纪录
        basicCustomerIntention.setCustomerId(basicCustomerIntentionParams.getCustomerId());
        basicCustomerIntention.setIsLastIntention(BasicCustomerIntention.IsLastIntention.TRUE.getValue());
        List<BasicCustomerIntention> list = this.findListByWhere(basicCustomerIntention);
        for (BasicCustomerIntention customerIntention : list) {
            customerIntention.setIsLastIntention(BasicCustomerIntention.IsLastIntention.FALSE.getValue());
            customerIntention.setOperator(user.getId());
            customerIntention.setOperatorTime(new Date());
            this.updateSelective(customerIntention);
        }
        BasicCustomerIntention intention = new BasicCustomerIntention();
        intention.setOperator(user.getId());
        intention.setOperatorTime(ZWDateUtil.getNowDateTime());
        intention.setIsLastIntention(BasicCustomerIntention.IsLastIntention.TRUE.getValue());
        intention.setIntentionType(basicCustomerIntentionParams.getIntentionType());
        intention.setIntentionStatus(basicCustomerIntentionParams.getIntentionStatus());
        intention.setIntentionLevel(basicCustomerIntentionParams.getIntentionLevel());
        intention.setCustomerId(basicCustomerIntentionParams.getCustomerId());
        this.save(intention);
    }

    @Override
    public ResponseResult addBasicCustomerIntenTion(BasicCustomerAndIntentionParams basicCustomerAndIntentionParams, SysUser user) {
        BasicCustomer basicCustomer = new BasicCustomer();
        BeanUtils.copyProperties(basicCustomerAndIntentionParams, basicCustomer);
        basicCustomer.setOrgId(user.getOrgId());
        basicCustomer.setOperator(user.getId());
        basicCustomer.setCreateTime(ZWDateUtil.getNowDateTime());
        basicCustomer.setOperatorTime(ZWDateUtil.getNowDateTime());
        int education = basicCustomerAndIntentionParams.getEducation().intValue();
        if (education == BasicCustomer.Education.EDUCATION_MORE_DR.getValue() ||
                education == BasicCustomer.Education.EDUCATION_DEGREE.getValue() ||
                education == BasicCustomer.Education.EDUCATION_COURSE.getValue() ||
                education == BasicCustomer.Education.EDUCATION_COLLEGE.getValue() ||
                education == BasicCustomer.Education.EDUCATION_HIGE.getValue() ||
                education == BasicCustomer.Education.EDUCATION_TECHNICAL.getValue()) {
            basicCustomer.setEducation(education);
        } else {
            return new ResponseResult(ResponseResult.Status.FAILURE, "学历不正确");
        }
        int intValue = basicCustomerAndIntentionParams.getMarital().intValue();
        if (intValue == BasicCustomer.Marital.MARITAL_MARRIED.getValue() ||
                intValue == BasicCustomer.Marital.MARITAL_UNMARRIED.getValue() ||
                intValue == BasicCustomer.Marital.MARITAL_DIVOECE.getValue() ||
                intValue == BasicCustomer.Marital.MARITAL_DEATH.getValue()) {
            basicCustomer.setMarital(intValue);
        } else {
            return new ResponseResult(ResponseResult.Status.FAILURE, "婚姻状况不正确");
        }

        Integer integer = basicCustomerService.saveSelective(basicCustomer);
        if (Objects.nonNull(integer)) {
            Long id = basicCustomer.getId();
            if (Objects.nonNull(id)) {
                //  同时插入意向记录
                BasicCustomerIntention basicCustomerIntention = new BasicCustomerIntention();
                basicCustomerIntention.setCustomerId(id);
                basicCustomerIntention.setIntentionType(basicCustomerAndIntentionParams.getIntentionType());
                basicCustomerIntention.setIsLastIntention(BasicCustomerIntention.IsLastIntention.TRUE.getValue());
                basicCustomerIntention.setIntentionStatus(BasicCustomerIntention.IntentionStatus.BASIC_INTENTION_STATUS_WGJ.getValue());
                basicCustomerIntention.setOperatorTime(ZWDateUtil.getNowDateTime());
                basicCustomerIntention.setOperator(user.getId());
                if (Objects.nonNull(basicCustomerAndIntentionParams.getIntentionRemark())) {
                    basicCustomerIntention.setRemark(basicCustomerAndIntentionParams.getIntentionRemark());
                }
                basicCustomerIntention.setOrderId(basicCustomerAndIntentionParams.getOrderId());
                this.saveSelective(basicCustomerIntention);
                return new ResponseResult(ResponseResult.Status.SUCCESS, "增加成功");
            } else {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
            }
        } else {
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }

    }


    @Override
    public ResponseResult updateBasicCustomerIntention(EditBasicCustomerIntentionParams editBasicCustomerIntentionParams, SysUser user) {
        BasicCustomerIntention basicCustomerIntention = new BasicCustomerIntention();
        int status = editBasicCustomerIntentionParams.getIntentionStatus().intValue();
        if (status == BasicCustomerIntention.IntentionStatus.BASIC_INTENTION_STATUS_WGJ.getValue() ||
                status == BasicCustomerIntention.IntentionStatus.BASIC_INTENTION_STATUS_YGJ.getValue() ||
                status == BasicCustomerIntention.IntentionStatus.BASIC_INTENTION_STATUS_YGQ.getValue() ||
                status == BasicCustomerIntention.IntentionStatus.BASIC_INTENTION_STATUS_YWC.getValue() ||
                status == BasicCustomerIntention.IntentionStatus.BASIC_INTENTION_STATUS_YXD.getValue()) {
            basicCustomerIntention.setIntentionStatus(status);
        } else {
            return new ResponseResult<>(ResponseResult.Status.FAILURE, "该状态不存在");
        }
        basicCustomerIntention.setOperatorTime(ZWDateUtil.getNowDateTime());
        basicCustomerIntention.setOperator(user.getId());
        BeanUtils.copyProperties(editBasicCustomerIntentionParams, basicCustomerIntention);
        if (Objects.nonNull(editBasicCustomerIntentionParams.getRemark())) {
            basicCustomerIntention.setRemark(editBasicCustomerIntentionParams.getRemark());
        }
        this.updateSelective(basicCustomerIntention);
        return new ResponseResult(ResponseResult.Status.SUCCESS, "修改成功");
    }
}
