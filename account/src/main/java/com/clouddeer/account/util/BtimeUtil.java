package com.clouddeer.account.util;

import com.clouddeer.account.entity.CdAccount;
import com.clouddeer.account.entity.CdAccountBtime;
import com.rivers.core.util.ExceptionUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class BtimeUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public CdAccountBtime getBtimeInfo(CdAccount cdAccount) {
        CdAccountBtime cdAccountBtime = new CdAccountBtime();
        try {
            WebDriver driver = getDrive(cdAccount);
            driver.get("https://user.btime.com/");
            WebElement headImg = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/ul/li[3]/div/div[1]/img"));
            WebElement nickname = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/ul/li[1]/div/div[1]"));
            cdAccount.setNickname(nickname.getText());
            cdAccount.setHeadPortrait(headImg.getAttribute("src"));
            Thread.sleep(2000);
            driver.get("https://user.btime.com/following");
            WebElement friendsCount = driver.findElement(By.xpath("//*[@id=\"count\"]"));
//            WebElement followersCount = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div[2]/div[2]/div[1]/div/a[2]/div/strong"));
//            WebElement articleCount = driver.findElement(By.xpath("//*[@id=\"ProfileMain\"]/div[1]/ul/li[4]/a/span"));
//            cdAccountBtime.setFollowersCount(Integer.parseInt(followersCount.getText()));
            cdAccountBtime.setFriendsCount(Integer.parseInt(friendsCount.getText()));
//            cdAccountBtime.setArticleCount(Integer.parseInt(articleCount.getText()));
        } catch (Exception e) {
            ExceptionUtil.throwBusinessException("111", e);
        }
        return cdAccountBtime;
    }

    public void sendBtime(CdAccount cdAccount){
        try {
            WebDriver driver = getDrive(cdAccount);
            WebElement wbSendContent = driver.findElement(By.xpath("//*[@id=\"v6_pl_content_publishertop\"]/div/div[2]/textarea"));
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowDate = sdf.format(date);
            wbSendContent.sendKeys("ceshi！！！" + nowDate);
            WebElement contentSendBtn = driver.findElement(By.xpath("//*[@id=\"v6_pl_content_publishertop\"]/div/div[3]/div[1]/a"));
            contentSendBtn.click();
        } catch (Exception e){
            ExceptionUtil.throwBusinessException("111", e);
        }

    }

    public WebDriver getDrive(CdAccount cdAccount){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        try {
            WebDriver driver = DriverFactory.create();
            driver.get("https://www.btime.com/");
            WebElement login = driver.findElement(By.xpath("//*[@id=\"j-header-login\"]"));
            login.click();
            WebElement wbAccount = driver.findElement(By.xpath("//*[@id=\"loginPho\"]"));
            WebElement wbPwd = driver.findElement(By.xpath("//*[@id=\"ipt_passward\"]"));
            wbAccount.sendKeys(cdAccount.getAccountName());
            wbPwd.sendKeys(cdAccount.getAccountPwd());
            //获取验证码
            WebElement verifyCode = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[1]/div[3]/span/img"));
            Point point = verifyCode.getLocation();
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage fullImg = ImageIO.read(screenshot);
            int eleWidth = verifyCode.getSize().getWidth();
            int eleHeight = verifyCode.getSize().getHeight();
            BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(),
                    eleWidth, eleHeight);
            ImageIO.write(eleScreenshot, "png", screenshot);
            File screenshotLocation = new File("E:\\project\\cloud-deer-account\\account\\src\\main\\resources\\upload\\" + cdAccount.getAccountName() + "btime.png");
            FileUtils.copyFile(screenshot, screenshotLocation);
            synchronized (this) {
                wait();
            }
            String btime = operations.get("btime");
            WebElement verifycodeInput = driver.findElement(By.id("log-code"));
            verifycodeInput.sendKeys(btime);
            WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"login-btn\"]"));
            loginBtn.click();
            Thread.sleep(5000l);
            return driver;
        } catch (Exception e){
            ExceptionUtil.throwBusinessException("111", e);
        }
        return DriverFactory.create();
    }

    public void open(){
        synchronized (this) {
            notify();
        }
    }
}
