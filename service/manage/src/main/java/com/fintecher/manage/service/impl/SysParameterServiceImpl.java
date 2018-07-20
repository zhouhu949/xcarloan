package com.fintecher.manage.service.impl;

import com.fintecher.entity.SysParameter;
import com.fintecher.manage.mapper.SysParameterMapper;
import com.fintecher.manage.service.SysParameterService;
import com.fintecher.manage.vo.SysParameterModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class SysParameterServiceImpl extends BaseServiceImpl<SysParameter> implements SysParameterService {

    @Resource
    private SysParameterMapper sysParamMapper;

    @Override
    public List<SysParameterModel> findSysParameterByCondition(String paramName, Integer paramStatus, String sort) {
        return sysParamMapper.findSysParameterByCondition(paramName, paramStatus, sort);
    }
}
