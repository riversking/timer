package com.cloudder.utils;

import org.springframework.util.Assert;

public class ExceptionUtil extends Assert
{
    public static void  throwBusinessException(String errorCode) {
        throw new BusinessException(errorCode);
    }
    public static void  throwBusinessException(String errorCode,Throwable e) {
        throw  new BusinessException(errorCode,e);
    }
public  static void main(String[] ars)
{
    try{
        ExceptionUtil.throwBusinessException("errorCode");
        System.out.print("2222222");
    }catch (Exception e)
    {
        System.out.print("11111");
      System.out.print(e.getMessage());
    }

}

}
