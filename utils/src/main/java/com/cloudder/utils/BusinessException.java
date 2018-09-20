package com.cloudder.utils;

public class BusinessException extends  RuntimeException {

    public BusinessException(String errorCode){
       super(errorCode);
   }

    public BusinessException(String errorCode, Throwable e) {
        super(errorCode,e);
    }

}
