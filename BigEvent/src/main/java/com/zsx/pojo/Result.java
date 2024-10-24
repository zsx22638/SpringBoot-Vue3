package com.zsx.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;

//统一响应结果
@Data
@AllArgsConstructor
public class Result<T> {
    private Integer code;//业务状态码  0-成功  1-失败
    private String message;//提示信息
    private T data;//响应数据

    //快速返回操作成功响应结果(带响应数据)
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "操作成功", data);
    }

    //快速返回操作成功响应结果  重载了success()方法
    public static Result success() {
        return new Result(0, "操作成功", null);
    }
    public static Result success(String message ) {
        return new Result(0, message, null);
    }
    public static <E> Result<E> success(String message,E data) {
        return new Result(0, message, data);
    }

    public static Result error(String message) {
        return new Result(1, message, null);
    }
    public static <E> Result error(String message,E data) {
        return new Result(1, message, data);
    }
}
