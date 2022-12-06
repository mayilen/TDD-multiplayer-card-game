package com.example.assignment3.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * Mock a user (aka a second browser).
 * <p/>
 * Created by Mike on 11/8/2015.
 */
@Service
public class MockUserFactory {
  {
    WebDriverManager.chromedriver().setup();
  }
    public WebDriver getSecondUser(final String location) {
        return this.buildNewUser(location);
    }

    private WebDriver buildNewUser(final String location) {
      final DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setJavascriptEnabled(true);

      final WebDriver chromeDriver = new ChromeDriver(new ChromeOptions().merge(capabilities));
      chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        chromeDriver.get(location);
        return chromeDriver;
    }
}
