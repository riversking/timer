package com.clouddeer.account.util;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static WebDriver create() {

        // TODO Auto-generated method stub
        String chromdriver="C:\\Users\\wangyichuan\\Desktop\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromdriver);
//        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches",
                Arrays.asList("--start-maximized"));
//        options.addArguments("--test-type", "--start-maximized");
        WebDriver driver=new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280,800));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

}
