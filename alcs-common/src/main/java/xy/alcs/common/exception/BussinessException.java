package xy.alcs.common.exception;

import xy.alcs.common.errcode.ErrorCode;

/**
 * @Author:ju
 * @Description: 业务异常类
 * @Date:Create in 16:28 2018-03-18
 */
public class BussinessException extends  RuntimeException {
    private ErrorCode errorCode;

    public BussinessException(ErrorCode errorCode){
        super(errorCode.toString());
        this.errorCode = errorCode;
    }

    public BussinessException(ErrorCode errorCode,String errorMessage){
        super(errorCode.toString() + " - " + errorMessage);
        this.errorCode = errorCode;
    }

    private BussinessException(ErrorCode errorCode,String errorMessage,Throwable cause){
        super(errorCode.toString() + " - " + getMessage(errorMessage) + " - " + getMessage(cause),cause);
    }


    public BussinessException(final  String errorCode,final String message){
        super(message);
        this.errorCode = new ErrorCode() {
            @Override
            public String getCode() {
                return errorCode;
            }

            @Override
            public String getDescription() {
                return message;
            }
        };

    }

    public static BussinessException asBussinessException(ErrorCode code){
        return new BussinessException(code);
    }
    public static BussinessException asBussinessException(ErrorCode code,String message){
        return new BussinessException(code,message);
    }
    public static BussinessException asBussinessException(String code,String message){
        return new BussinessException(code,message);
    }


    public static BussinessException asBussinessException(ErrorCode code,String message,Throwable cause){
        return  cause instanceof BussinessException?(BussinessException)cause : new BussinessException(code,message,cause);
    }



    public static BussinessException asBussinessException(ErrorCode errorCode,Throwable cause){
        return  cause instanceof BussinessException ? (BussinessException)cause : new BussinessException(errorCode,(String)null,cause);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    private static  String getMessage(Object obj){
        return obj == null ? "": (obj instanceof  Throwable?((Throwable)obj).getMessage() : obj.toString());
    }
}
