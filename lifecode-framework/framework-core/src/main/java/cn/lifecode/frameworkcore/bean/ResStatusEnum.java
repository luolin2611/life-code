package cn.lifecode.frameworkcore.bean;

/**
 * 状态返回
 * @author yuanmin
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
    INCORRECT_SUBMISSION_INFORMATION("1000", "提交信息有误"),
    LOGIN_STEP_ERROR("1001", "请按步骤流程操作"),
    CHECK_MOBILE_VALID("1002", "手机号不合理，请输入合理的手机号"),

    /**
     * 登录注册，2开头
     */

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
