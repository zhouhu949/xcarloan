package com.fintecher.manage.util;

import java.math.BigDecimal;

public class FinanceUtil {
    /**
     * 等额本金
     * 本金 referencePrice
     * 已归还本金累计额 repayments
     * 每期利率 interestRate
     *
     * @return 利息
     */
    public static BigDecimal equalPrincipalInterest(BigDecimal referencePrice, BigDecimal repayments, BigDecimal interestRate) {
        BigDecimal interest = (referencePrice.subtract(repayments)).multiply(interestRate.setScale(4,BigDecimal.ROUND_HALF_UP));
        return interest.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 等额本金
     * 本金 referencePrice
     * 还款期数 periods
     *
     * @return 每期要还本金（贷款本金/ 还款期数）
     */
    public static BigDecimal equalPrincipalPeriod(BigDecimal referencePrice, int periods) {
        return referencePrice.divide(new BigDecimal(periods), 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 按期付息还本
     * 本金×每期利率×贷款期数
     * 本金 referencePrice
     * 每期利率 interestRate
     * 还款期数 periods
     *
     * @return 利息
     */
    public static BigDecimal payInterestOnSchedule(BigDecimal referencePrice, int periods, BigDecimal interestRate) {
        return (referencePrice.multiply(interestRate.setScale(4,BigDecimal.ROUND_HALF_UP))).multiply(new BigDecimal(periods)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 等额本息
     * 每期贷款本金剩余余额 referencePrice
     * 每期利率 interestRate
     *
     * @return 利息
     */
    public static BigDecimal averageCapitalPlusInterest(BigDecimal referencePrice, BigDecimal interestRate) {
        return referencePrice.multiply(interestRate.setScale(4,BigDecimal.ROUND_HALF_UP)).setScale(4, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 等额本息 每期应还贷款金额-本期利息
     * 每期应还贷款金额 eachPayment
     * 本期利息 interestRate
     *
     * @return 本金
     */
    public static BigDecimal principle(BigDecimal eachPayment, BigDecimal interest) {
        return eachPayment.subtract(interest.setScale(4,BigDecimal.ROUND_HALF_UP)).setScale(4, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 等额本息
     *
     * @param principle    贷款本金
     * @param periods      还款期数
     * @param interestRate 利率
     * @return 每月应还款金额
     */
    public static BigDecimal eachPayment(BigDecimal principle, int periods, BigDecimal interestRate) {
        //每月应还款金额
        interestRate = interestRate.setScale(4,BigDecimal.ROUND_HALF_UP) ;
        return principle.multiply(interestRate).multiply((interestRate.add(new BigDecimal(1))).pow(periods)).divide((interestRate.add(new BigDecimal(1))).pow(periods).subtract(new BigDecimal(1)), 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * @param principle  总金额
     * @param proportion 首付款比例
     * @return
     */
    public static BigDecimal firstPay(BigDecimal principle, BigDecimal proportion) {
        return principle.multiply(proportion).setScale(4, BigDecimal.ROUND_HALF_UP);
    }


}
