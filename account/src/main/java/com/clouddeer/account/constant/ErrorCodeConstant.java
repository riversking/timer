package com.clouddeer.account.constant;

/**
 * 账号异常CODE常量类
 * CODE格式=106(公司分配编号)+4位（业务CODE）
 * 4位业务CODE分配如下：
 */
public class ErrorCodeConstant {

    /**服务器错误*/
    public static final String ECODE_0000 = "500";

    /**查询账号失败*/
    public static final String ECODE_0301="1060301";
    /**新增账号失败*/
    public static final String ECODE_0302="1060302";
    /**保存验证码失败*/
    public static final String ECODE_0303="1060302";

}
