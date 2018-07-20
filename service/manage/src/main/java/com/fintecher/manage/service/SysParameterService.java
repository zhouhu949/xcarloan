package com.fintecher.manage.service;


import com.fintecher.entity.SysParameter;
import com.fintecher.manage.vo.SysParameterModel;

import java.util.List;

public interface SysParameterService extends BaseService<SysParameter> {

    List<SysParameterModel> findSysParameterByCondition(String paramName, Integer paramStatus, String sort);
}
