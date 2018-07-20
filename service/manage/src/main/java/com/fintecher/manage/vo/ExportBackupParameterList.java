package com.fintecher.manage.vo;

import lombok.Data;

import java.util.List;

/**
 * @System:
 * @Auther: DangWenXiang
 * @Description:
 * @Date:Created on 2018/2/6 12:05
 * @Modified By:
 */
@Data
public class ExportBackupParameterList {
    private List<Long> paramIds;
    private List<ExportBackupParameter> exportBackupParameters;
} 