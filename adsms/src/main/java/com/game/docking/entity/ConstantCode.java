package com.game.docking.entity;

public enum ConstantCode implements ErrorCode {
    /** OK(0, "操作成功") */
    OK(0, "操作成功"),
    /** ERROR_NOTEXIST_CID(101, "客户ID不存在") */
    ERROR_NOTEXIST_CID(101, "客户ID不存在"),
    /** ERROR_NOTEXIST_DEVICEID(102, "设备ID为空") */
    ERROR_NOTEXIST_DEVICEID(102, "设备ID为空"),
    /** ERROR_NOTEXIST_BODY(105, "包体为空，解析失败") */
    ERROR_NOTEXIST_BODY(105, "包体为空，解析失败"),
    /** ERROR_UNDEFINE(901, "未知错误") */
    ERROR_UNDEFINE(901, "未知错误");

    private int code;
    private String message;

    ConstantCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
