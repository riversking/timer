package com.clouddeer.account.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * Created by Administrator on 2017/8/17 0017.
 */
@SuppressWarnings("all")
public class BaseUploadUtil {
    /**
     * 模拟前端发送Base64流（测试用）
     * @param imgStr
     * @return
     * @throws Exception
     */
   public static String GetImageStr(String img) {
       //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = img;//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }
    public static String GenerateImage (String imgStr) throws  Exception
    {//对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return "";
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = "E:\\project\\cloud-deer-account\\api\\v1\\account\\src\\main\\resources\\upload\\"+3+".png";
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return b+".jpg";
        }
        catch (Exception e)
        {
            return "";
        }
    }

}
