package com.hc.utils;

/**
 * 规则运算工具类
 */
public class RuleUtil {

    /**
     * 是否满足规则
     * @param operand 运算数
     * @param destination 目标
     * @param ruleStr 规则
     * @return
     */
    public static boolean isFulfilled(Integer operand, Integer destination, String ruleStr){
        if(operand == null || destination == null || ruleStr == null){
            throw new RuntimeException("参数不能为空");
        }
        switch(ruleStr){
            case ">=":
                return operand >= destination;
            case "<=":
                return operand <= destination;
            case "==":
                return operand.equals(destination);
            default:
                throw new RuntimeException("没有对应的规则");
        }

    }


}
