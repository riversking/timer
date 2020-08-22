package com.rivers.file.config;

public class WindowsOrMac {

    public static String pathName() {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            return "C:\\Users\\wangyichuan\\Desktop\\upload\\";
        } else if (os.toLowerCase().startsWith("linux")) {
            return "/upload/file/";
        } else {
            return "/Users/riversking/Desktop/upload/";
        }
    }
}
