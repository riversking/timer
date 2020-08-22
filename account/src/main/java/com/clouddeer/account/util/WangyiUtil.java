package com.clouddeer.account.util;

import com.clouddeer.account.entity.CdAccount;
import com.clouddeer.account.entity.CdAccountWangYi;
import com.rivers.core.util.ExceptionUtil;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class WangyiUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public CdAccountWangYi getWangyiInfo(CdAccount cdAccount) {
        CdAccountWangYi cdAccountWangYi = new CdAccountWangYi();
        try {
//            WebDriver driver = getWangYiDrive(cdAccount);
//            WebElement subscribeCount = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[1]/div/dl[1]/dd"));
//            WebElement readCount = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[1]/div/dl[2]/dd"));
//           //头像
//            WebElement headImg = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[2]/div/div[1]/a/img"));
//            //昵称
//            WebElement name = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[2]/div/div[2]/div[1]/a"));
//            cdAccount.setNickname(name.getText());
//            //微博数
//            cdAccount.setHeadPortrait(headImg.getAttribute("src"));
//            cdAccountWangYi.setSubscribeCount(Integer.parseInt(subscribeCount.getText()));
//            cdAccountWangYi.setReadCount(Integer.parseInt(readCount.getText()));
        } catch (Exception e) {
            ExceptionUtil.throwBusinessException("111", e);
        }
        return cdAccountWangYi;
    }

    public void sendWangYi(CdAccount cdAccount){
        //待
        try {
            WebDriver driver = getWangYiDrive(cdAccount);
            WebElement butoon =driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[1]/button"));
            butoon.click();
            WebElement wyTitle = driver.findElement(By.xpath("//*[@id=\"title\"]"));
            WebElement wycontainer = driver.findElement(By.xpath("//*[@id=\"container\"]"));
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowDate = sdf.format(date);
            wyTitle.sendKeys("测试下微博发送！！！！dfgdfg" + nowDate);
            WebElement contentSendBtn = driver.findElement(By.xpath("//*[@id=\"v6_pl_content_publishertop\"]/div/div[3]/div[1]/a"));
            contentSendBtn.click();
        } catch (Exception e){
            ExceptionUtil.throwBusinessException("111", e);
        }

    }

    public WebDriver getWangYiDrive(CdAccount cdAccount){
        try {
            WebDriver driver = DriverFactory.create();
            driver.get("http://mp.163.com/login.html");
            Thread.sleep(10000L);
            WebElement iframe = driver.findElement(By.xpath("//*[@id=\"login-URS-iframe\"]/iframe"));
            driver.switchTo().frame(iframe);
//            driver.get("http://mail.126.com/");
            WebElement wbAccount = driver.findElement(By.name("email"));
            WebElement wbPwd = driver.findElement(By.name("password"));
            wbAccount.sendKeys(cdAccount.getAccountName());
            wbPwd.sendKeys(cdAccount.getAccountPwd());
            WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"dologin\"]"));
            loginBtn.click();
            Thread.sleep(10000L);
            return driver;
        } catch (Exception e){
            ExceptionUtil.throwBusinessException("111", e);
        }
        return DriverFactory.create();
    }
}
