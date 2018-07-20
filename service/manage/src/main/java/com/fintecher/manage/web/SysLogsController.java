package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.SysLogs;
import com.fintecher.entity.SysUser;
import com.fintecher.manage.service.SysLogsService;
import com.fintecher.manage.service.UserService;
import com.fintecher.manage.vo.*;
import com.fintecher.util.Constants;
import com.fintecher.util.ExcelUtil;
import com.fintecher.util.ZWDateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoleilu.hutool.date.DateTime;
import io.swagger.annotations.*;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @System: 车贷金融
 * @Auther: dwx
 * @Description: 系统日志管理
 * @Modified By:
 */
@RestController
@RequestMapping("/sysLogsController")
@Api(description = "系统日志管理")
public class SysLogsController extends BaseController {
    @Resource
    private SysLogsService sysLogsService;
    @Resource
    RestTemplate restTemplate;
    @Resource
    private UserService userService;


    /**
     * @System: 车贷金融
     * @Auther: dwx
     * @Description: 系统日志管理的分页查询
     * @Modified By:
     */
    @GetMapping("/querySysLogsPage")
    @ApiOperation(value = "系统日志管理的分页查询", notes = "系统日志管理的分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)"),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小"),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<SysLogsModel>> querySysLogsPage(@RequestParam(value = "clientIp", required = false) @ApiParam(value = "操作的用户端ip") String clientIp,
                                                                   @RequestParam(value = "excuteMethod", required = false) @ApiParam(value = "执行方法") String excuteMethod,
                                                                   @RequestParam(value = "operator", required = false) @ApiParam(value = "操作人") String operator,
                                                                   @RequestParam(value = "excuteTime", required = false) @ApiParam(value = "执行时间") String excuteTime,
                                                                   @RequestParam(value = "excuteParams", required = false) @ApiParam(value = "执行参数") String excuteParams,
                                                                   @RequestParam(value = "excuteType", required = false) @ApiParam(value = "执行类型") String excuteType,
                                                                   @RequestParam(value = "startTime", required = false) @ApiParam(value = "起始时间") Date startTime,
                                                                   @RequestParam(value = "endTime", required = false) @ApiParam(value = "结束时间") Date endTime,
                                                                   @ApiIgnore PageParam pageParam,
                                                                   @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize(), true);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(sysLogsService.findSysLogsByConditions(clientIp,excuteMethod,operator,excuteTime,excuteParams,excuteType,startTime,endTime,pageParam.getSort())));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, e.getMessage());
        }
    }

    /**
     * @Description:系统日志导出
     * @Modified By:
     */
    @PostMapping("/exportSysLogs")
    @ApiOperation(value = "导出系统日志", notes = "导出系统日志")
    public ResponseResult exportSysLogs(@RequestBody ExportSystemLogsList exportSystemLogsList) {
        HSSFWorkbook workbook = null;
        File file = null;
        ByteArrayOutputStream out = null;
        FileOutputStream fileOutputStream = null;
        List<ExportSystemLogs> logss = new ArrayList<>();
        for (Long logId : exportSystemLogsList.getSysLogsIds()) {
            SysLogs sysLogs = sysLogsService.findById(logId);
            ExportSystemLogs exportSysLogs = new ExportSystemLogs();
            exportSysLogs.setExeMethod(Objects.isNull(sysLogs.getExcuteMethod()) ? " " : sysLogs.getExcuteMethod());
            exportSysLogs.setExeType(Objects.isNull(sysLogs.getExcuteType()) ? " " : sysLogs.getExcuteType());
            exportSysLogs.setLogRemark(Objects.isNull(sysLogs.getLogRemark()) ? " " : sysLogs.getLogRemark());
            exportSysLogs.setOperator(Objects.isNull(sysLogs.getOperator()) ? " " : sysLogs.getOperator());
            exportSysLogs.setClientIp(Objects.isNull(sysLogs.getClientIp()) ? " " : sysLogs.getClientIp());
            exportSysLogs.setExeTime(Objects.isNull(sysLogs.getExcuteTime()) ? " " : sysLogs.getExcuteTime().toString());
            exportSysLogs.setOperateTime(Objects.isNull(sysLogs.getOperateTime()) ? " " : ZWDateUtil.fomratterDate(sysLogs.getOperateTime(),null));
            logss.add(exportSysLogs);
        }
        try {
            String[] titleList = {"操作时间", "操作者", "客户端ip", "执行方法", "请求执行时长(秒)", "执行类型", "备注"};
            String[] proNames = {"operateTime", "operator", "clientIp", "exeMethod", "exeTime", "exeType", "logRemark"};
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("sheet1");
            ExcelUtil.createExcel(workbook, sheet, logss, titleList, proNames, 0, 0);
            out = new ByteArrayOutputStream();
            workbook.write(out);
            String filePath = FileUtils.getTempDirectoryPath().concat(File.separator).concat(DateTime.now().toString("yyyyMMddhhmmss") + "exportLogs.xls");
            file = new File(filePath);
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(out.toByteArray());
            FileSystemResource resource = new FileSystemResource(file);
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("file", resource);
            ResponseEntity<String> url = restTemplate.postForEntity("http://service-file/uploadFile/addUploadFileUrl", param, String.class);
            ExportModel exportModel = new ExportModel();
            exportModel.setUrl(url.getBody());
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, exportModel);

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
