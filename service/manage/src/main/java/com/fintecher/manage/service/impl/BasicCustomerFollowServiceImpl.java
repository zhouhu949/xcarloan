package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicCustomerFollow;
import com.fintecher.entity.BasicCustomerIntention;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.mapper.BasicCustomerIntentionMapper;
import com.fintecher.manage.service.BasicCustomerFollowService;
import com.fintecher.manage.vo.BasicCustomerFollowParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: BasicCustomerFollowServiceImpl
 * @ProjectName xcarloan
 * @Description:
 * @date 2018/6/19 0019下午 18:37
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class BasicCustomerFollowServiceImpl extends BaseServiceImpl<BasicCustomerFollow> implements BasicCustomerFollowService {

    @Autowired
    private BasicCustomerIntentionMapper customerIntentionMapper;
    @Override
    public void saveBasicCustomerFollow(BasicCustomerFollowParams basicCustomerFollowParams, SysUser user) {
        BasicCustomerFollow basicCustomerFollow = new BasicCustomerFollow();
        //先将跟进记录更新为不是最后一次跟进
        basicCustomerFollow.setIntentionId(basicCustomerFollowParams.getIntentionId());
        basicCustomerFollow.setIsLastIntention(BasicCustomerFollow.IsLastIntention.TRUE.getValue());
        List<BasicCustomerFollow> list = this.findListByWhere(basicCustomerFollow);
        for (BasicCustomerFollow follow : list) {
            follow.setIsLastIntention(BasicCustomerFollow.IsLastIntention.FALSE.getValue());
            follow.setOperatorTime(new Date());
            follow.setOperator(user.getId());
            this.updateSelective(follow);
        }

        BasicCustomerFollow follow = new BasicCustomerFollow();
        BeanUtils.copyProperties(basicCustomerFollowParams, follow);
        follow.setOperator(user.getId());
        follow.setOperatorTime(new Date());
        follow.setIsLastIntention(BasicCustomerFollow.IsLastIntention.TRUE.getValue());
        follow.setFollowDate(new Date());
        this.save(follow);
        BasicCustomerIntention basicCustomerIntention = customerIntentionMapper.selectByPrimaryKey(basicCustomerFollowParams.getIntentionId());
        basicCustomerIntention.setIntentionStatus(BasicCustomerIntention.IntentionStatus.BASIC_INTENTION_STATUS_YGJ.getValue());
        customerIntentionMapper.updateByPrimaryKey(basicCustomerIntention);
    }
}
