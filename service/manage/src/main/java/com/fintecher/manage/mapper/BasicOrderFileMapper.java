package com.fintecher.manage.mapper;

import com.fintecher.entity.BasicCustomerOrder;
import com.fintecher.entity.BasicOrderFile;
import com.fintecher.manage.util.MyMapper;
import com.fintecher.manage.vo.BasicOrderFileModel;
import com.fintecher.manage.vo.CustomerOrderFileCountModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: dwx
 * @Date: Create on 2018/6/14 11:16
 * @Description:
 */
public interface BasicOrderFileMapper extends MyMapper<BasicOrderFile> {
    BasicOrderFile findUploadBasicFile(@Param("fileId")Long fileId);
    List<CustomerOrderFileCountModel> findCountByOrderFile(@Param("orderId")Long orderId);

}
