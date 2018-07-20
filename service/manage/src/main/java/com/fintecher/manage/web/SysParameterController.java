package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.SysParameter;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.SysParameterService;
import com.fintecher.manage.vo.ExportBackupParameter;
import com.fintecher.manage.vo.ExportBackupParameterList;
import com.fintecher.manage.vo.PageParam;
import com.fintecher.manage.vo.SysParameterModel;
import com.fintecher.util.Constants;
import com.fintecher.util.ExcelUtil;
import com.fintecher.util.ZWDateUtil;
import com.github.pagehelper.PageInfo;
import com.xiaoleilu.hutool.date.DateTime;
import io.swagger.annotations.*;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description: 系统参数管理
 * @Modified By:
 */
@RestController
@RequestMapping("/sysParameterController")
@Api(description = "系统参数管理")
public class SysParameterController extends BaseController {
    @Autowired
    private SysParameterService sysParameterService;
    @Resource
    RestTemplate restTemplate;

    /**
     * @System: 车贷金融
     * @Auther: hukaijia
     * @Description: 创建系统参数
     * @Modified By:
     */
    @PostMapping(value = "/createSysParameter")
    @ApiOperation(value = "创建系统参数", notes = "创建系统参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constants.AUTHORIZATION, value = Constants.AUTHORIZATION, required = true, dataType = "string", paramType = "header"),
    })
    public ResponseResult<SysParameter> createSysParameter(@Validated @ApiParam("系统参数对象")
                                                @RequestBody SysParameterModel sysParameter,
                                                           @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        logger.debug("request to create user ");
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            SysParameter sysParam = new SysParameter();
            sysParam.setParamName(sysParameter.getParamName());
            SysParameter oldSysParam = sysParameterService.findOne(sysParam);
            if (Objects.nonNull(oldSysParam)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "该参数名已被占用，请重新输入");
            }
            sysParam.setRemark(sysParameter.getRemark());
            sysParam.setOperator(user.getId());
            sysParam.setOperatorTime(ZWDateUtil.getNowDateTime());
            sysParameterService.save(sysParam);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    /**
     * @System: 车贷金融
     * @Auther: hukaijia
     * @Description: 修改系统参数
     * @Modified By:
     */
    @PostMapping(value = "/updateSysParameter")
    @ApiOperation(value = "修改系统参数", notes = "修改系统参数")
    public ResponseResult updateSysParameter(@Validated @ApiParam("系统参数对象") @RequestBody SysParameterModel sysParameter,
                                             @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            SysParameter sysParameter1 = new SysParameter();
            BeanUtils.copyProperties(sysParameter,sysParameter1);
            sysParameter1.setOperator(user.getId());
            sysParameter1.setOperatorTime(ZWDateUtil.getNowDate());
            sysParameterService.update(sysParameter1);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    /**
     * @System: 车贷金融
     * @Auther: hukaijia
     * @Description: 系统参数带条件的分页查询
     * @Modified By:
     */
    @GetMapping("/querySysParameterPage")
    @ApiOperation(value = "系统参数带条件的分页查询", notes = "系统参数带条件的分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)"),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小"),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult querySysParameterPage
    (@RequestParam(value = "paramName", required = false) @ApiParam(value = "参数名称") String paramName,
     @RequestParam(value = "paramStatus", required = false) @ApiParam(value = "参数状态") Integer paramStatus,
     @ApiIgnore PageParam pageParam, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<SysParameterModel> sysParameterList = sysParameterService.findSysParameterByCondition(paramName, paramStatus, pageParam.getSort());
            logger.debug("分页查询系统参数列表! pageNum = {}", pageParam.getPage());
            PageInfo<SysParameterModel> pageInfo = new PageInfo<>(sysParameterList);
            logger.info("分页查询系统参数列表！ pageInfo = {}", pageInfo);
            return new ResponseResult(ResponseResult.Status.SUCCESS, "", pageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

   /* *//**
     * @Description:系统参数列表导出
     * @Modified By:
     */
    @PostMapping("/exportBackupParameter")
    @ApiOperation(value = "导出系统参数", notes = "导出系统参数")
    public ResponseResult exportBackupParameter(@RequestBody ExportBackupParameterList exportBackupParameterList) {
        HSSFWorkbook workbook = null;
        File file = null;
        ByteArrayOutputStream out = null;
        FileOutputStream fileOutputStream = null;
        List<ExportBackupParameter> backupParameterList = new ArrayList<>();
        for (Long paramId : exportBackupParameterList.getParamIds()) {
            SysParameter sysParameter = sysParameterService.findById(paramId);
            ExportBackupParameter exportBackupParameter = new ExportBackupParameter();
            exportBackupParameter.setParamName(sysParameter.getParamName());
            exportBackupParameter.setParamCode(sysParameter.getParamCode());
            String status = null;
            switch (sysParameter.getParamStatus()) {
                case 0:
                    status = "启用";
                    break;
                case 1:
                    status = "停用";
                    break;
                default:
                    status = "未知类型";
                    break;
            }
            exportBackupParameter.setStatus(status);
            exportBackupParameter.setParamValue(sysParameter.getParamValue());
            exportBackupParameter.setRemark(sysParameter.getRemark());
            backupParameterList.add(exportBackupParameter);
        }
        try {
            String[] titleList = {"参数名", "参数码", "参数类型", "状态", "参数值", "备注"};
            String[] proNames = {"paramName", "paramCode", "paramType", "status", "paramValue", "remark"};
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("sheet1");
            ExcelUtil.createExcel(workbook, sheet, backupParameterList, titleList, proNames, 0, 0);
            out = new ByteArrayOutputStream();
            workbook.write(out);
            String filePath = FileUtils.getTempDirectoryPath().concat(File.separator).concat(DateTime.now().toString("yyyyMMddhhmmss") + "exportParams.xls");
            file = new File(filePath);
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(out.toByteArray());
            FileSystemResource resource = new FileSystemResource(file);
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("file", resource);
            ResponseEntity<String> url = restTemplate.postForEntity("http://service-file/uploadFile/addUploadFileUrl", param, String.class);
            if (url == null) {
                return new ResponseResult(ResponseResult.Status.FAILURE, "上传服务器失败");
            } else {
                return new ResponseResult(ResponseResult.Status.SUCCESS, "操作成功", url.getBody());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        } finally {
            // 关闭流
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
            }
            // 删除文件
            if (file != null) {
                file.delete();
            }
        }
    }

}
