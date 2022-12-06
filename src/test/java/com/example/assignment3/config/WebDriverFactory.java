package com.example.assignment3.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * Web ca.carleton.blackjack.driver factory.
 *
 * Created by Mike on 10/6/2015.
 */
@Service
@Lazy
public class WebDriverFactory {

  {
    WebDriverManager.chromedriver().setup();
  }
    public WebDriverFactory() {}

  @Bean(destroyMethod = "quit")
    public WebDriver getWebDriver() {
      final DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setJavascriptEnabled(true);

      final WebDriver chromeDriver = new ChromeDriver(new ChromeOptions().merge(capabilities));
      chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
      chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(4));
        return chromeDriver;
    }
}
