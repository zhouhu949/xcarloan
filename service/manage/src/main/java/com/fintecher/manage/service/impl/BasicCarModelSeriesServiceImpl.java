package com.fintecher.manage.service.impl;

import com.fintecher.entity.BasicCarBrandSeries;
import com.fintecher.manage.service.BasicCarBrandSeriesService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 2018/6/12 0012.
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class BasicCarModelSeriesServiceImpl extends  BaseServiceImpl<BasicCarBrandSeries>  implements BasicCarBrandSeriesService {


}
