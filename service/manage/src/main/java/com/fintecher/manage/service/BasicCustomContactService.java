package com.fintecher.manage.service;


import com.fintecher.entity.BasicCustomContact;

import java.util.List;

public interface BasicCustomContactService extends BaseService<BasicCustomContact> {

    /**
     * 客户联系人信息列表
     * @param
     */

    List<BasicCustomContact> findCustomContactList(Long customId);

}
