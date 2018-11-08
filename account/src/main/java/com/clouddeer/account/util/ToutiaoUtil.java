package com.clouddeer.account.util;

import com.clouddeer.account.entity.CdAccount;
import com.clouddeer.account.entity.CdAccountToutiao;
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
import java.util.concurrent.TimeUnit;
@Component
public class ToutiaoUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 获取头条信息
     *
     * @param cdAccount
     * @return
     */
    public CdAccountToutiao getToutiaoInfo(CdAccount cdAccount) {
        CdAccountToutiao cdAccountToutiao = new CdAccountToutiao();
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        try {
            WebDriver driver = DriverFactory.create();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.get("https://mp.toutiao.com/profile_v3/index");
            WebElement login = driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/div/img[3]"));
            login.click();
            Thread.sleep(1000L);
            WebElement mobileLogin = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/ul/li[1]"));
            mobileLogin.click();
            Thread.sleep(1000L);
            WebElement wbAccount = driver.findElement(By.id("mobile"));
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage fullImg = ImageIO.read(screenshot);
            wbAccount.sendKeys(cdAccount.getAccountName());
            WebElement verifyCode = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/form/div[2]/div/img"));
            //获取登录图片验证码
            Point point = verifyCode.getLocation();
            int eleWidth = verifyCode.getSize().getWidth();
            int eleHeight = verifyCode.getSize().getHeight();
            BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(),
                    eleWidth, eleHeight);
            ImageIO.write(eleScreenshot, "gif", screenshot);
            File screenshotLocation = new File("account\\src\\main\\resources\\upload\\" + cdAccount.getAccountName() + "toutiao.gif");
            FileUtils.copyFile(screenshot, screenshotLocation);
            WebElement tCode = driver.findElement(By.xpath("//*[@id=\"captcha1\"]"));
            synchronized (this) {
                wait();
            }
            String code = operations.get("tcode");
            tCode.sendKeys(code);
            Thread.sleep(5000L);
            WebElement verifyingCode = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/form/div[3]/span"));
            verifyingCode.click();
            //输入短线验证码
            WebElement mobileCode = driver.findElement(By.xpath("//*[@id=\"code\"]"));
            synchronized (this) {
                wait();
            }
            mobileCode.sendKeys(operations.get("MCode"));
            Thread.sleep(5000L);
            //点击登录按钮
            WebElement loginBtn = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/div/form/input"));
            loginBtn.click();
            Thread.sleep(5000L);
            WebElement Btn = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/span"));
            Btn.click();
            WebElement imgBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/div[2]/div[1]/a/div[1]/img"));
            imgBtn.click();
            WebElement followersCount = driver.findElement(By.xpath("//*[@id=\"index\"]/div/div/div[1]/div[1]/div[1]/a/span"));
            WebElement readCount = driver.findElement(By.xpath("//*[@id=\"index\"]/div/div/div[1]/div[1]/div[2]/a/span"));
            //头条数
            WebElement publishCount = driver.findElement(By.xpath("//*[@id=\"index\"]/div/div/div[1]/div[1]/div[3]/a/span"));
            cdAccountToutiao.setFollowersCount(Integer.parseInt(followersCount.getText()));
            cdAccountToutiao.setReadCount(Integer.parseInt(readCount.getText()));
            cdAccountToutiao.setPublishCount(Integer.parseInt(publishCount.getText()));
//            Thread.sleep(5000L);
//            driver.get("https://www.toutiao.com/c/user/101243739571/#mid=1605591520520205");
            WebElement headImg = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/div[2]/div[1]/a/div[1]/img"));
            WebElement nickname = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/div[2]/div[1]/a/div[2]/div/span"));
            cdAccount.setHeadPortrait(headImg.getAttribute("src"));
            cdAccount.setNickname(nickname.getText());
        } catch (Exception e) {
            ExceptionUtil.throwBusinessException("抓取失败", e);
        }
        return cdAccountToutiao;
    }

    public void open(){
        System.out.println("a");
        synchronized (this) {
            notify();
        }
    }
}
