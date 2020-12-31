package com.rivers.file.config;

import java.io.File;

public class WindowsOrMac {

    public static String pathName() {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            return "C:\\Users\\wangyichuan\\Desktop\\upload\\";
        } else if (os.toLowerCase().startsWith("linux")) {
            String strPath = "/upload/files/";
            File file = new File(strPath);
            if(!file.exists()){
                file.mkdirs();
            }
            return "/upload/file/";
        } else {
            return "/Users/riversking/Desktop/upload/";
        }
    }
}
