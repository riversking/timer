package com.clouddeer.account.util;

import com.clouddeer.account.entity.CdAccount;
import com.clouddeer.account.entity.CdAccountZhihu;
import com.rivers.core.util.ExceptionUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ZhihuUtil {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public CdAccountZhihu getZhihuInfo(CdAccount cdAccount) {
        CdAccountZhihu cdAccountZhihu = new CdAccountZhihu();
        try {
            WebDriver driver = getDrive(cdAccount);
            WebElement self = driver.findElement(By.xpath("//*[@id=\"Popover37-toggle\"]/img"));
            self.click();
            WebElement self2 = driver.findElement(By.xpath("//*[@id=\"Popover12-content\"]/div/a[1]"));
            self2.click();
            WebElement headImg = driver.findElement(By.xpath("//*[@id=\"ProfileHeader\"]/div/div[2]/div/div[1]/div/div[1]/img"));
            WebElement nickname = driver.findElement(By.xpath("//*[@id=\"ProfileHeader\"]/div/div[2]/div/div[2]/div[1]/h1/span[1]"));
            WebElement followersCount = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div[2]/div[2]/div[1]/div/a[2]/div/strong"));
            WebElement friendsCount = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div[2]/div[2]/div[1]/div/a[1]/div/strong"));
            cdAccount.setNickname(nickname.getText());
            WebElement articleCount = driver.findElement(By.xpath("//*[@id=\"ProfileMain\"]/div[1]/ul/li[4]/a/span"));
            cdAccount.setHeadPortrait(headImg.getAttribute("src"));
            cdAccountZhihu.setFollowersCount(Integer.parseInt(followersCount.getText()));
            cdAccountZhihu.setFriendsCount(Integer.parseInt(friendsCount.getText()));
            cdAccountZhihu.setArticleCount(Integer.parseInt(articleCount.getText()));
        } catch (Exception e) {
            ExceptionUtil.throwBusinessException("111", e);
        }
        return cdAccountZhihu;
    }

    public void sendZhihu(CdAccount cdAccount){
        try {
            WebDriver driver = getDrive(cdAccount);
            WebElement wbSendContent = driver.findElement(By.xpath("//*[@id=\"v6_pl_content_publishertop\"]/div/div[2]/textarea"));
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowDate = sdf.format(date);
            wbSendContent.sendKeys("测试知乎提问！！！" + nowDate);
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
            driver.get("https://www.zhihu.com/");
            WebElement login = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div[2]/div/div/div/div[1]/div/div[1]/div[2]/button[1]"));
            login.click();
            WebElement wbAccount = driver.findElement(By.xpath("/html/body/div[5]/div/span/div/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div[2]/div[1]/input"));
            WebElement wbPwd = driver.findElement(By.name("password"));
            wbAccount.sendKeys(cdAccount.getAccountName());
            wbPwd.sendKeys(cdAccount.getAccountPwd());
            WebElement loginBtn = driver.findElement(By.xpath("/html/body/div[5]/div/span/div/div[2]/div/div/div/div[2]/div[1]/form/button"));
            loginBtn.click();
            Thread.sleep(10000L);
            return driver;
        } catch (Exception e){
            ExceptionUtil.throwBusinessException("111", e);
        }
        return DriverFactory.create();
    }
}
