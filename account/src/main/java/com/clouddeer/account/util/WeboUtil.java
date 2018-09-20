package com.clouddeer.account.util;

import com.clouddeer.account.entity.CdAccount;
import com.clouddeer.account.entity.CdAccountWeibo;
import com.clouddeer.account.entity.CdArticle;
import com.cloudder.utils.ExceptionUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Component
public class WeboUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    /**
     * 获取微博信息
     *
     * @param cdAccount
     * @return
     */
    public CdAccountWeibo getWeboInfo(CdAccount cdAccount) {
        CdAccountWeibo cdAccountWeibo = new CdAccountWeibo();
        try {
            WebDriver driver = getWeiboDrive(cdAccount);
            WebElement headImg = driver.findElement(By.xpath("//*[@id=\"skin_cover_s\"]/div/a/img"));
            WebElement nickname = driver.findElement(By.xpath("//*[@id=\"v6_pl_rightmod_myinfo\"]/div/div/div[2]/div/a[1]"));
            WebElement followersCount = driver.findElement(By.xpath("//*[@id=\"v6_pl_rightmod_myinfo\"]/div/div/div[2]/ul/li[2]/a/strong"));
            WebElement friendsCount = driver.findElement(By.xpath("//*[@id=\"v6_pl_rightmod_myinfo\"]/div/div/div[2]/ul/li[1]/a/strong"));
            cdAccount.setNickname(nickname.getText());
            //微博数
            WebElement statusesCount = driver.findElement(By.xpath("//*[@id=\"v6_pl_rightmod_myinfo\"]/div/div/div[2]/ul/li[3]/a/strong"));
            cdAccount.setHeadPortrait(headImg.getAttribute("src"));
            cdAccountWeibo.setFollowersCount(Integer.parseInt(followersCount.getText()));
            cdAccountWeibo.setFriendsCount(Integer.parseInt(friendsCount.getText()));
            cdAccountWeibo.setStatusesCount(Integer.parseInt(statusesCount.getText()));
        } catch (Exception e) {
            ExceptionUtil.throwBusinessException("111", e);
        }
        return cdAccountWeibo;
    }


    /**
     * 发送微博
     *
     * @param cdAccount
     * @param cdArticle
     */
    public void sendWeiBo(CdAccount cdAccount, CdArticle cdArticle) {
        try {
            WebDriver driver = getWeiboDrive(cdAccount);
            WebElement wbSendContent = driver.findElement(By.xpath
                    ("//*[@id=\"v6_pl_content_publishertop\"]/div/div[2]/textarea"));
            wbSendContent.sendKeys(cdArticle.getContent());
            WebElement contentSendBtn = driver.findElement(By.xpath("//*[@id=\"v6_pl_content_publishertop\"]/div/div[3]/div[1]/a"));
            contentSendBtn.click();
        } catch (Exception e) {
            ExceptionUtil.throwBusinessException("111", e);
        }
    }

    /**
     * 获得cookie登陆
     *
     * @param account
     */
    public Integer getCookie(String account) {
        Set<Cookie> coo = redisCacheUtil.getValue(RedisCacheKeyEnum.redis_test, account);
        Date date = new Date();
        for (Cookie c : coo) {
            System.out.println("c.getName():" + c.getName());
            if (c.getName().equals("un")) {
                if (date.getTime() > c.getExpiry().getTime()) {
                    return 1;
                }
            }
        }
        return 0;
    }

    /**
     * 开启驱动，开始登陆
     *
     * @param cdAccount
     * @return
     */
    private WebDriver getWeiboDrive(CdAccount cdAccount) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        try {
            WebDriver driver = DriverFactory.create();
            driver.get("https://weibo.com/login.php");
            WebElement wbAccount = driver.findElement(By.id("loginname"));
            WebElement wbPwd = driver.findElement(By.xpath("//*[@id=\"pl_login_form\"]/div/div[3]/div[2]/div/input"));
            wbAccount.sendKeys(cdAccount.getAccountName());
            wbPwd.sendKeys(cdAccount.getAccountPwd());
            WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"pl_login_form\"]/div/div[3]/div[6]/a"));
            loginBtn.click();
            Thread.sleep(10000L);
            String requesUrl = driver.getCurrentUrl();
            if (requesUrl.equals("https://weibo.com/login.php")) {
                WebElement verifyCode = driver.findElement(By.xpath("//*[@id=\"pl_login_form\"]/div/div[3]/div[3]/a/img"));
                Point point = verifyCode.getLocation();
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                BufferedImage fullImg = ImageIO.read(screenshot);
                int eleWidth = verifyCode.getSize().getWidth();
                int eleHeight = verifyCode.getSize().getHeight();
                BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(),
                        eleWidth, eleHeight);
                ImageIO.write(eleScreenshot, "png", screenshot);
                File screenshotLocation = new File("E:\\project\\cloud-deer-account\\account\\src\\main\\resources\\upload\\" + cdAccount.getAccountName() + "weibo.png");
                FileUtils.copyFile(screenshot, screenshotLocation);
                WebElement verifycodeInput = driver.findElement(By.xpath("//*[@id=\"pl_login_form\"]/div/div[3]/div[3]/div/input"));
                synchronized (this) {
                    wait();
                }
                verifycodeInput.sendKeys(operations.get("vcode"));
                loginBtn.click();
                Thread.sleep(5000l);
            }
            Set<Cookie> coo = driver.manage().getCookies();
            redisCacheUtil.setValue(coo, RedisCacheKeyEnum.redis_test, cdAccount.getAccountName());
            return driver;
        } catch (Exception e) {
            ExceptionUtil.throwBusinessException("111", e);
        }
        return DriverFactory.create();
    }


    public void open() {
        synchronized (this) {
            notify();
        }
    }
}
