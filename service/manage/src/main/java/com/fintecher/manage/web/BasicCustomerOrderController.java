package com.fintecher.manage.web;

import com.fintecher.common.vo.manage.ResponseResult;
import com.fintecher.entity.*;
import com.fintecher.manage.mapper.BasicCustomerOrderMapper;
import com.fintecher.manage.service.*;
import com.fintecher.manage.vo.*;
import com.fintecher.util.Constants;
import com.fintecher.util.ZWDateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/basicCustomerOrderController")
@Api(description = "客户订单操作")
public class BasicCustomerOrderController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private BasicCustomerOrderService basicCustomerOrderService;
    @Autowired
    private BasicOrderRecordService basicOrderRecordService;
    @Autowired
    private BasicCustomerOrderMapper basicCustomerOrderMapper;
    @Autowired
    private FinanceReceivableService financeReceivableService;
    @Autowired
    private FinanceLoanService financeLoanService;
    @Autowired
    private FinanceSettleService financeSettleService;
    @Autowired
    private FinanceTakebackService financeTakebackService;
    @Autowired
    private BasicCustomerService basicCustomerService;
    @Autowired
    private BasicOrderRepaySchemeService basicOrderRepaySchemeService;
    @Autowired
    private FinanceFrozenService financeFrozenService;
    @Autowired
    private FinanceRemitService financeRemitService;
    @Autowired
    private BasicOrderCarService basicOrderCarService;
    @GetMapping("/findFinancingRepayDetail")
    @ApiOperation(value = "融资租赁贷款计算器", notes = "融资租赁贷款计算器")
    public ResponseResult<Map<String, Object>> findFinancingRepayDetail(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                        @RequestParam @ApiParam(value = "产品ID", required = true) Long productId) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Map<String, Object> repayDetail = basicCustomerOrderService.findFinancingRepayDetail(productId);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, repayDetail);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/findMortgageRepayDetail")
    @ApiOperation(value = "抵押贷款贷款计算器", notes = "抵押贷款贷款计算器")
    public ResponseResult<Map<String, Object>> findMortgageRepayDetail(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                       @RequestParam @ApiParam(value = "还款方案ID", required = true) Long schemeId,
                                                                       @RequestParam @ApiParam(value = "抵押贷款贷款金额", required = true) BigDecimal amount) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            if (Objects.isNull(amount) || amount.compareTo(new BigDecimal(0)) < 0) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, "金额不能为空且必须大于0");
            }
            Map<String, Object> repayDetail = basicCustomerOrderService.findMortgageRepayDetail(schemeId, amount);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, repayDetail);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/createFinancingOrder")
    @ApiOperation(value = "创建融资租赁订单", notes = "创建融资租赁订单")
    public ResponseResult createFinancingOrder(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                               @Validated @RequestBody CreateFinancingOrderParams createOrderParams) {
        logger.debug("Rest request createFinancingOrder {}", createOrderParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            basicCustomerOrderService.createFinancingOrder(user, createOrderParams);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "创建订单成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @PostMapping("/createMortgageOrder")
    @ApiOperation(value = "创建抵押贷款订单", notes = "创建抵押贷款订单")
    public ResponseResult createMortgageOrder(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                              @Validated @RequestBody CreateMortgageOrderParams createOrderParams) {
        logger.debug("Rest request createMortgageOrder {}", createOrderParams);
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            basicCustomerOrderService.createMortgageOrder(user, createOrderParams);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "创建订单成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/query")
    @ApiOperation(value = "订单分页查询", notes = "订单分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<PageInfo<OrderInfoModel>> query(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                          @ApiIgnore PageParam pageParam,
                                                          SearchOrderParams searchOrderParams) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<String> allDataAuth = userService.findAllDataAuth(user.getId());
            List<String> allExceptDataAuth = userService.findAllExceptDataAuth(user.getId());
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<OrderInfoModel> query = basicCustomerOrderService.query(searchOrderParams, allDataAuth, allExceptDataAuth);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, new PageInfo<>(query));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    @GetMapping("/getOrderCar/{orderId}")
    @ApiOperation(value = "查询订单车辆" ,notes = "查询订单车辆")
    public ResponseResult<List<BasicOrderCar>> getOrderCar(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                           @PathVariable("orderId") @ApiParam("订单ID") Long orderId) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicOrderCar basicOrderCar = new BasicOrderCar();
            basicOrderCar.setOrderId(orderId);
            List<BasicOrderCar> list = basicOrderCarService.findListByWhere(basicOrderCar);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult<>(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    /**
     * @auther: dwx
     * @Description:查看押品资料
     */
    @GetMapping("/findCustomerCollateral")
    @ApiOperation(value = "查看押品资料",notes = "查看押品资料")
    public ResponseResult<CustomerOrderMortgageModel> findCustomerCollateral(@RequestParam Long orderId, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
            try{
                SysUser user = getUserByAuth(authorization);
                if (Objects.isNull(user)) {
                    return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
                }
                List list = basicCustomerOrderMapper.findCustomerCollateral(orderId);
                return new ResponseResult(ResponseResult.Status.SUCCESS,list);
            }catch (Exception e){
                logger.error(e.getMessage(),e);
                return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
            }
    }

    /**
     * @auther: dwx
     * @Description:根据车产信息查询抵押记录
     */
    @GetMapping("/findMortgageByCustomerCar")
    @ApiOperation(value = "根据车产信息查询抵押记录",notes = "根据车产信息查询抵押记录")
    public ResponseResult findMortgageByCustomerCar(@RequestParam Long carId, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try{
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<BasicFinanceMortgageModel> list = basicCustomerOrderMapper.findMortgageByCustomerCar(carId);
            return new ResponseResult(ResponseResult.Status.SUCCESS,list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
        }
    }

    /**
     * @auther: dwx
     * @Description:根据车产信息查询质押记录
     */
    @GetMapping("/findPledgeByCustomerCar")
    @ApiOperation(value = "根据车产信息查询质押记录",notes = "根据车产信息查询质押记录")
    public ResponseResult findPledgeByCustomerCar(@RequestParam Long carId, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try{
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<BasicFinancePledgeModel> list = basicCustomerOrderMapper.findPledgeByCustomerCar(carId);
            return new ResponseResult(ResponseResult.Status.SUCCESS,list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
        }
    }

    /**
     * @auther: dwx
     * @Description:抵押记录
     */
    @GetMapping("/findAssessmentByCustomerCar")
    @ApiOperation(value = "根据车产信息查询评估记录",notes = "根据车产信息查询评估记录")
    public ResponseResult findAssessmentByCustomerCar(@RequestParam Long carId, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try{
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            List<BasicCustomerCollateralModel> list = basicCustomerOrderMapper.findAssessmentByCustomerCar(carId);
            return new ResponseResult(ResponseResult.Status.SUCCESS,list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
        }
    }


    /**
     * @auther: dwx
     * @Description:查看操作记录
     */
    @GetMapping("/findCustomerOrderRecord")
    @ApiOperation(value = "查看操作记录",notes = "查看操作记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<BasicOrderRecord> findCustomerOrderRecord(@RequestParam Long orderId, @ApiIgnore PageParam pageParam,
                                                  @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try{
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            PageHelper.startPage(pageParam.getPage(),pageParam.getSize());
            BasicOrderRecord basicOrderRecord = new BasicOrderRecord();
            basicOrderRecord.setOrderId(orderId);
            List<BasicOrderRecord> list = basicOrderRecordService.findListByWhere(basicOrderRecord);
            PageInfo pageInfo = new PageInfo(list);
            return new ResponseResult(ResponseResult.Status.SUCCESS,pageInfo);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
        }
    }

    /**
     * @auther: dwx
     * @Description:订单收款记录查询
     */
    @GetMapping("/findCustomerOrderFinancialSituationList")
    @ApiOperation(value ="订单收款记录查询",notes = "订单收款记录查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult<FinanceReceivable> findCustomerOrderFinancialSituationList(@RequestParam Long orderId, @ApiIgnore PageParam pageParam,
                                                                  @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            FinanceReceivable financeReceivable = new FinanceReceivable();
            financeReceivable.setOrderId(orderId);
            List<FinanceReceivable> list = financeReceivableService.findListByWhere(financeReceivable);
            return new ResponseResult(ResponseResult.Status.SUCCESS,list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
        }
    }
    /**
     * @auther: dwx
     * @Description:获取订单放款记录
     */
    @GetMapping("/findCustomerOrderFinanceLoanList")
    @ApiOperation(value = "获取订单放款记录",notes = "获取订单放款记录")
    public ResponseResult<FinanceLoan> findCustomerOrderFinanceLoanList(@RequestParam Long orderId,
                                                           @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try {
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            FinanceLoan financeLoan = new FinanceLoan();
            financeLoan.setOrderId(orderId);
            List<FinanceLoan> loans = financeLoanService.findListByWhere(financeLoan);
            return new ResponseResult(ResponseResult.Status.SUCCESS,loans);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
        }
    }

    /**
     * @auther: dwx
     * @Description:获取提前结清记录
     */
    @GetMapping("/findCustomerOrderFinanceSettleList")
    @ApiOperation(value = "获取提前结清记录",notes = "获取提前结清记录")
    public ResponseResult<FinanceSettle> findCustomerOrderFinanceSettleList(@RequestParam Long orderId,
                                                             @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try{
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            FinanceSettle financeSettle = new FinanceSettle();
            financeSettle.setOrderId(orderId);
            List<FinanceSettle> list = financeSettleService.findListByWhere(financeSettle);
            return new ResponseResult(ResponseResult.Status.SUCCESS,list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
        }
    }

    /**
     * @auther: dwx
     * @Description:获取订单提前收回记录
     */
    @GetMapping("/findCustomerOrderFinanceTakeBack")
    @ApiOperation(value = "获取订单提前收回记录",notes = "获取订单提前收回记录")
    public ResponseResult<FinanceTakeback> findCustomerOrderFinanceTakeBack(@RequestParam Long orderId,
                                                           @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try{
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            FinanceTakeback financeTakeback = new FinanceTakeback();
            financeTakeback.setOrderId(orderId);
            List<FinanceTakeback> list = financeTakebackService.findListByWhere(financeTakeback);
            return new ResponseResult(ResponseResult.Status.SUCCESS,list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
        }
    }

    /**
     * @auther: dwx
     * @Description:根据订单获取客户信息
     */
    @GetMapping("/getCustomerInfoByOrderId")
    @ApiOperation(value = "根据订单获取客户信息",notes = "根据订单获取客户信息")
    public ResponseResult<BasicCustomer> getCustomerInfoByOrderId(@RequestParam Long orderId,
                                                                  @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
        try{
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomer basicCustomer = basicCustomerService.findCustomerInfoByOrderId(orderId);
            return new ResponseResult(ResponseResult.Status.SUCCESS,basicCustomer);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
        }
    }

    /**
     * @auther: dwx
     * @Description:还款计划
     */
    @GetMapping("/findCustomerOrderSchedule")
    @ApiOperation(value = "还款计划",notes = "还款计划")
    public ResponseResult<CustomerPaymentScheduleModel> findCustomerOrderSchedule(@RequestParam Long orderId, @RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
            try{
                SysUser user = getUserByAuth(authorization);
                if (Objects.isNull(user)) {
                    return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
                }
                List<CustomerPaymentScheduleModel> list = basicCustomerOrderMapper.customerPaymentScheduleList(orderId);
                return new ResponseResult(ResponseResult.Status.SUCCESS,list);
            }catch (Exception e){
                logger.error(e.getMessage(),e);
                return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
            }
    }

    /**
     * @auther: dwx
     * @Description:冻结记录
     */
    @GetMapping("findFinanceFrozenList")
    @ApiOperation(value = "冻结记录",notes = "冻结记录")
    public ResponseResult findFinanceFrozenList(@RequestHeader(value = Constants.AUTHORIZATION) String authorization, @RequestParam Long orderId) {
        try{
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            FinanceFrozen financeFrozen = new FinanceFrozen();
            financeFrozen.setOrderId(orderId);
            List<FinanceFrozen> list = financeFrozenService.findListByWhere(financeFrozen);
            return new ResponseResult(ResponseResult.Status.SUCCESS,list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
        }
    }

    /**
     * @auther: dwx
     * @Description:减免记录
     */
    @GetMapping("/findFinanceRemitList")
    @ApiOperation(value = "减免记录",notes = "减免记录")
    public ResponseResult findFinanceRemitList(@RequestHeader(value = Constants.AUTHORIZATION) String authorization, @RequestParam Long orderId) {
        try{
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            FinanceRemit financeRemit = new FinanceRemit();
            financeRemit.setOrderId(orderId);
            List<FinanceRemit> list = financeRemitService.findListByWhere(financeRemit);
            return new ResponseResult(ResponseResult.Status.SUCCESS,list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
        }
    }

    /**
     * @auther: dwx
     * @Description:还款列表
     */
    @GetMapping("/findOrderPaymentList")
    @ApiOperation(value = "还款列表",notes = "还款列表")
    public ResponseResult findOrderPaymentList(@RequestHeader(value = Constants.AUTHORIZATION) String authorization, @RequestParam Long orderId) {
        try{
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            Example example = new Example(BasicOrderRepayScheme.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("orderId",orderId);
            List<BasicOrderRepayScheme> list = basicOrderRepaySchemeService.selectByExample(example);
            return new ResponseResult(ResponseResult.Status.SUCCESS,list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
        }
    }

    /**
     * @auther: dwx
     * @Description:订单详情
     */
    @GetMapping("/findCustomerOrderInfo")
    @ApiOperation(value = "订单基本资料",notes = "订单基本资料")
    public ResponseResult<BasicCustomerOrder> findCustomerOrderInfo(@RequestHeader(value = Constants.AUTHORIZATION) String authorization, @RequestParam Long orderId){
        try{
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerOrder basicCustomerOrder = basicCustomerOrderService.findById(orderId);
            return new ResponseResult(ResponseResult.Status.SUCCESS,basicCustomerOrder);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
        }
    }

    /**
     * @auther: dwx
     * @Description:查询待补填资料订单
     */

    @GetMapping("/queryCustomerOrderFile")
    @ApiOperation(value = "查询待补填资料订单",notes = "查询待补填资料订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", defaultValue = "1", dataType = "integer", paramType = "query",
                    value = "页数 (1..N)", required = true),
            @ApiImplicitParam(name = "size", defaultValue = "10", dataType = "integer", paramType = "query",
                    value = "每页大小", required = true),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query",
                    value = "依据什么排序: 属性名1 asc,属性名2 desc. ")
    })
    public ResponseResult queryCustomerOrderFile(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                @ApiIgnore PageParam pageParam,
                                                SearchOrderParams searchOrderParams){
        try{
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            PageHelper.startPage(pageParam.getPage(), pageParam.getSize());
            List<OrderInfoModel> list = basicCustomerOrderService.findCustomerOrderFile(user, searchOrderParams);
            return new ResponseResult(ResponseResult.Status.SUCCESS,list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"系统异常");
        }

    }

    @PutMapping("/updateOrderPrice")
    @ApiOperation(value = "修改订单金额", notes = "修改订单金额")
    public ResponseResult updateOrderPrice(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                     @Validated @RequestBody UpdateOrderPriceParams updateOrderPriceParams) {
        logger.debug("Rest request updateRole {}", updateOrderPriceParams);
        try {

            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerOrder order = new BasicCustomerOrder();
            order.setId(updateOrderPriceParams.getOrderId());
            order.setOrderPrice(updateOrderPriceParams.getPrice());
            basicCustomerOrderService.updateSelective(order);
            return new ResponseResult<>(ResponseResult.Status.SUCCESS, "修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_SYSTEM_ERROR);
        }
    }

    /**
     * @auther: dwx
     * @Description:订单结案操作
     */
    @PostMapping("/updateOrderStatus")
    @ApiOperation(value = "订单结案操作",notes = "订单结案操作")
    public ResponseResult updateOrderStatus(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,@RequestBody CustomerOrderPendingClosedModel model){
        try{
            SysUser user = getUserByAuth(authorization);
            if (Objects.isNull(user)) {
                return new ResponseResult(ResponseResult.Status.FAILURE, ResponseResult.ERROR_MSG_USER_NOT_LOGIN_IN);
            }
            BasicCustomerOrder basicCustomerOrder = basicCustomerOrderService.findById(model.getOrderId());
            if (Objects.isNull(basicCustomerOrder)) {
                return new ResponseResult(ResponseResult.Status.FAILURE,"无此订单");
            }
            if (basicCustomerOrder.getOrderStatus().equals(BasicCustomerOrder.OrderStatus.PENDING_OVER.getValue()) ||
                    basicCustomerOrder.getOrderStatus().equals(BasicCustomerOrder.OrderStatus.FINANCE_SETTLEMENT.getValue()) ||
                    basicCustomerOrder.getOrderStatus().equals(BasicCustomerOrder.OrderStatus.FINANCE_TAKE_BACK.getValue())) {
                basicCustomerOrder.setOrderStatus(BasicCustomerOrder.OrderStatus.PENDING_CLOSED.getValue());
                basicCustomerOrderService.updateSelective(basicCustomerOrder);
            }
            BasicOrderRecord basicOrderRecord = new BasicOrderRecord();
            basicOrderRecord.setOrderId(model.getOrderId());
            basicOrderRecord.setOrderLink(BasicOrderRecord.OrderLink.END.getValue());
            basicOrderRecord.setOrderStatus(basicCustomerOrder.getOrderStatus());
            basicOrderRecord.setOperator(user.getId());
            basicOrderRecord.setOperatorTime(ZWDateUtil.getNowDate());
            basicOrderRecordService.updateSelective(basicOrderRecord);
            return new ResponseResult(ResponseResult.Status.SUCCESS,"结案成功");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new ResponseResult(ResponseResult.Status.FAILURE,"结案失败");
        }
    }


}
