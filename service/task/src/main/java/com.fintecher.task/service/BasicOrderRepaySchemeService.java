package com.fintecher.task.service;

import com.fintecher.entity.BasicOrderRepayScheme;

public interface BasicOrderRepaySchemeService extends BaseService<BasicOrderRepayScheme> {
    /**
     * 逾期检查
     */
    void overdueCheck();
}
