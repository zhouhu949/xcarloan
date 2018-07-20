package com.fintecher.manage.vo;

import lombok.Data;

/**
 * @System:
 * @Auther: DangWenXiang
 * @Description:
 * @Date:Created on 2018/2/6 12:04
 * @Modified By:
 */
@Data
public class ExportBackupParameter {
    private String paramName;
    private String paramCode;//参数自定义code
    private String paramType;
    private String status;
    private String paramValue;
    private String remark;
} 