package com.qly.mall.exception;

public enum ErrorNo implements BaseErrorInterface {
    //数据操作定义
    PARAM_ERROR("100", "参数错误"),
    PHONE_EMPTY_OR_FORMAT_ERROR("111", "手机号为空或格式错误"),
    PHONE_HAVE_REGISTERED("112", "手机号已经注册"),
    PASSWORD_EMPTY_OR_FORMAT_ERROR("113", "密码为空或格式错误"),
    LOGIN_FAIL("114", "登录失败-手机号错误或密码错误"),
    USER_NOT_EXIST("115", "用户不存在"),
    UPDATE_PASSWORD_FAIL("116", "更新密码失败"),
    REGISTER_FAIL("117", "注册失败"),
    CREATE_ORDER_FAIL("118", "创建订单失败"),
    CREATE_SUBORDER_FAIL("119", "创建子订单失败"),
    GET_ID_FAIL("120", "创建8位数id"),
    SKU_EXCEPTION("121", "商品异常"), //商品不存在、商品已下架、商品未出售中
    USER_NOT_PERMISSION("122", "用户没有权限"),
    RV_USER_HAS_EXISTING("124", "不允许重复创建重复旅人"),
    RV_USER_NOT_EXISTING("125", "不存在此旅人"),
    CREATE_REFUND_FAIL("126", "创建退款失败"),
    CREATE_SUB_REFUND_FAIL("127", "创建子退款失败"),
    CREATE_Banner_FAIL("128", "创建banner链接失败"),
    UPDATE_GOODS_INVENTORY_FAIL("129", "更新商品库存失败"),
    UPDATE_GOODS_CAPACITY_FAIL("130", "更新商品容量失败"),
    ENCRYPT_FAIL("131", "加密失败"),
    DECRYPT_FAIL("132", "解密失败"),
    RV_USER_INFO_ERROR("133", "旅人信息存在错误"),
    ORDER_EMPTY_REFUND_FAIL("134", "空订单或订单状态未支付退款错误"),


    SUCCESS("200","成功"),


    BODY_NOT_MATCH("400", "空指针异常"),
    EXPIRE_TIME("401", "用户已失效或无token或用户不存在，请重新登录"),
    NOT_FOUND("404", "未找到资源"),


    INTERNAL_SERVER_ERR("500", "服务器内部错误"),
    SERVER_BUSY("503","服务器正忙，请稍后再试")
    ;

    private String resultCode;
    private String resultMsg;

    ErrorNo(String code, String msg){
        this.resultCode = code;
        this.resultMsg = msg;
    }

    @Override
    public String code() {
        return resultCode;
    }

    @Override
    public String msg() {
        return resultMsg;
    }
}
