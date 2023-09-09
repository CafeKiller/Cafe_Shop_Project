package com.coffee.mall_tiny01.common.api;

public class CommonResult<T> {

    private long code;
    private String message;
    private T data;

    protected CommonResult(){ }
    protected CommonResult(long code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 响应成功, 并返回结果
     * @param data 获取的数据
     * */
    public static <T> CommonResult<T> success(T data){
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 响应错误, 并返回错误代码
     * @param errorCode 错误码
     * */
    public static <T> CommonResult<T> failed(IErrorCode errorCode){
        return new CommonResult<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 响应错误, 并返回提示信息
     * @param message 提示信息
     * */
    public static <T> CommonResult<T> failed(String message){
        return new CommonResult<>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 参数校验失败, 响应结果
     * @param message 提示信息
     * */
    public static <T> CommonResult<T> validateFailed(String message){
        return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}