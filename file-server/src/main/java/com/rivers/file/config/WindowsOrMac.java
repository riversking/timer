package com.rivers.file.config;

public class WindowsorMac {

    public static String pathName() {
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            return "C:\\Users\\wangyichuan\\Desktop\\upload\\";
        } else {
            return "/Users/riversking/Desktop/upload/";
        }
    }
}
