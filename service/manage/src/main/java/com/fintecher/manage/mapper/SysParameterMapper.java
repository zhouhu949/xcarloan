package com.fintecher.manage.mapper;

import com.fintecher.entity.SysParameter;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.SysParameterModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysParameterMapper extends MyMapper<SysParameter> {

    List<SysParameterModel> findSysParameterByCondition(@Param("paramName") String paramName, @Param("paramStatus") Integer paramStatus, @Param("sort") String sort);
}
