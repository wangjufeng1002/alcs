package xy.alcs.common.utils;

import xy.alcs.common.enums.AlcsErrorCode;
import xy.alcs.common.errcode.ErrorCode;

import java.io.Serializable;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 14:49 2018-03-03
 */
public class Result<T> implements Serializable {
    private String code ;
    private String msg;
    private T data;

    public Result(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.msg  = errorCode.getDescription();

    }
    public Result(T data){
        this.data = data;
    }

    public Result(ErrorCode errorCode, T data){
        this.code = errorCode.getCode();
        this.msg  = errorCode.getDescription();
        this.data = data;
    }
    public Result(String code, String msg, T data ){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static  <R> Result<R> buildSuccessResult(ErrorCode errorCode, R data){
        return new Result<R>(errorCode,data);
    }
    public static  <R> Result<R> buildSuccessResult(ErrorCode errorCode){
        return new Result<R>(errorCode);
    }
    public static  <R> Result<R> buildSuccessResult(){
        return new Result<R>(AlcsErrorCode.SUCCESS);
    }


    public static  <R> Result<R> buildSuccessResult(R data){
        return new Result<R>(data);
    }
    public static<R> Result<R> buildErrorResult(ErrorCode errorCode, R data){
        return  new Result<R>(errorCode,null);
    }
    public static<R> Result<R> buildErrorResult(ErrorCode errorCode){
        return  new Result<R>(errorCode,null);
    }
    public static <R> Result<R> buildCustomizeResult(String errorCode, String msg, R data){
        return  new Result<R>(errorCode,msg,data);
    }
    public static <R> Result<R> buildCustomizeResult(String errorCode, String msg){
        return  new Result<R>(errorCode,msg,null);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
