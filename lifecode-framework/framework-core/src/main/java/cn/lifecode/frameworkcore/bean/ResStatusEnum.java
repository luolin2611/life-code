package cn.lifecode.frameworkcore.bean;

/**
 * 状态返回
 * @author rollin
 */
public enum ResStatusEnum {

    /**
     * 请求成功，0开头
     */
    SUCCESS("0000", "请求成功"),

    /**
     * 请求异常，9开头
     */
    SERVER_ERROR("9999", "服务器正在偷懒，请稍后再试"),
    LOGIN_TIMEOUT("9998", "登录超时"),
    NOT_OPEN_ACCOUNT("9997", "未开通电子账户"),

    /**
     * 通用，1开头
     */


    /**
     * 用户类，2开头
     */
    USER_ACCOUNT_NO_EXIST("2000", "账户不存在"),
    USER_INFO_ERROR("2001", "账户登录密码错误"),
    USER_REGISTER_USER_EXIST("2002", "用户名已存在"),
    USER_REGISTER_EMAIL_EXIST("2003", "邮箱已存在"),
    USER_REGISTER_MOBILE_EXIST("2004", "手机号已存在"),

    /**
     * 用户，3开头
     */

    /**
     * 账户，4开头
     */

    /**
     * 积分，5开头
     */

    /**
     * 其他，9开头
     */

    ;

    ResStatusEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回码
     */
    private String code;
    /**
     * 返回信息
     */
    private String msg;

    public String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    void setMsg(String msg) {
        this.msg = msg;
    }

}
