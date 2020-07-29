package com.rivers.user.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Knife {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws NoSuchAlgorithmException, CloneNotSupportedException {
        Knife knife = new Knife();
        System.out.println("请输入您要加密的数");
        String str = scanner.next();
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(str.getBytes());
        MessageDigest tc1 = (MessageDigest) instance.clone();
        byte[] digest = tc1.digest();
        String md5 = knife.convertbyte2String(digest);
        System.out.println("请输入您要加密的次数:");
        int n = scanner.nextInt();
        String md51 = md5(str, n);
        System.out.println(str + "加密" + n + "次后的结果为" + "\n" + md51);
    }

    public static String md5(String keyword, int count) throws NoSuchAlgorithmException, CloneNotSupportedException {

        for (int i = 0; i < count; i++) {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(keyword.getBytes()); // 加密
            MessageDigest tc1 = (MessageDigest) instance.clone();
            byte[] digest = tc1.digest(); // 字母+数字
            keyword = Knife.convertbyte2String(digest);
        }
        return keyword;
    }

    private static String convertbyte2String(byte[] byteResult) {
        char[] hexDigits =
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        // 4位代表一个16进制，所以长度需要变为原来2倍
        char[] result = new char[byteResult.length * 2];

        int index = 0;
        for (byte b : byteResult) {
            // 先转换高4位
            result[index++] = hexDigits[(b >>> 4) & 0xf];
            result[index++] = hexDigits[b & 0xf];
        }
        return new String(result);
    }
}
