package com.powernode.springbootdemo.commonutils;

/**
 * 定义不同信息跟状态码
 *
 * 枚举中定义的变量类型都是当前枚举对象类型\
 * 1.变量和变量之间使用逗号分开，最后一个变量使用分号结束
 * 2.枚举类就是一个普通的class类
 * 3.枚举类中是默认存在一个无参的构造函数
 */
public enum ResultEnum {



    SUCCESS("本次操作成功",200),
    NOT_FOUND("未找到数据",1004),
    NOT_FOUND_USER("账户未注册",1004),
    PWD_ERROR("密码错误",1004),
    MUST_NOT_NULL("必填内容不能为空",1004),
    FAILED("操作失败",1005),
    USER_IP_LIMIT("IP受限制",1005),
    USER_LOCK("账户被锁定",1005),
    ADD_FATLED("添加失败",1005),
    CODE_NOTOK("验证码错误",1005),
    MONEY_ERROR("金额不能为空",1005),
    OWNER_ERROR("所有者不能为空",1005),
    NAME_ERROR("名称不能为空",1005),
    DATA_ERROR("日期不能为空",1005),
    CUST_NAME_ERROR("客户名称不能为空",1005),
    STAGE_ERROR("阶段不能为空",1005),
    MONEY_NOTMATH("金额非法",1005),
    TRNAME_NAME_ERROR("交易名称不能为空",1005),
    TRNAME_DATE_ERROR("预计成交日期不能为空",1005),
    DATA_DUPLICATION("数据重复",1005),
    VERIFIED_ERROR("数据重复",1005),
    UNKNOWN_EXCEPTION("未知异常",1007),
    NOT_ROOT("你非管理员，禁止此操作",1008),
    NOT_PKID("没有主键",1008),
    USER_EXPIRETIME("账户过期",1005);


    private String message;

    private Integer code;

    ResultEnum(String message, Integer code) {
        this.message = message;
        this.code = code;
    }



    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ResultEnum{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }}
