package com.fintecher.manage.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserModel {

    private Long id;
    private Integer userType;

    private Integer userManager;

    private String userUsername;

    private String userRealname;

    private Integer userSex;

    private String userPhone;

    private String userEmail;

    private Integer userStatus;

    private String userRemark;
    private String operatorName;
    private Date operateTime;
    private Long deptId;
    private String deptName;

}
