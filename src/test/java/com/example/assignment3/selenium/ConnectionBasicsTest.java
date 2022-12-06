package com.example.assignment3.selenium;

import com.example.assignment3.Game;
import com.example.assignment3.GameController;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the ability to connect and disconnect without getting any weird errors.
 * <p/>
 * Created by Mike on 11/8/2015.
 */
//@SeleniumTest
public class ConnectionBasicsTest extends AbstractSeleniumTest {
    public GameController controller;

    @Test
    public void canConnect() {
        //this.indexPage.connect();
        GameController.game.restart();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();
        this.waitForDisplayed(p1.findElement(By.id("play")));
        // assertTrue(this.indexPage.showsGame());
        assertNotEquals("", p1.findElement(By.id("hand")).getText());
        GameController.game.restart();
        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow41() throws Exception {
        //this.indexPage.connect();
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "7C";
        GameController.game.players.get(0).setHand("3C,2H");
        p1.findElement(By.id("refresh")).click();
        assertEquals("[3C, 2H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("3C");
        p1.findElement(By.id("send")).click();
        assertEquals("3C",p2.findElement(By.id("topCard")).getText());
        assertEquals("Player 2's Turn", p1.findElement(By.id("turn")).getText());
        assertEquals("Your Turn", p2.findElement(By.id("turn")).getText());
        assertTrue(p2.findElement(By.id("play")).isDisplayed());

        assertEquals(1, GameController.game.currentTurn);
        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow42() throws Exception {
        //this.indexPage.connect();
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "7H";
        GameController.game.players.get(0).setHand("1H,2H");
        GameController.game.players.get(3).setHand("7H,9S");
        // assertTrue(this.indexPage.showsGame());
        p1.findElement(By.id("refresh")).click();
        assertEquals("[1H, 2H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("1H");
        assertEquals("To the Left",p1.findElement(By.id("direction")).getText());
        p1.findElement(By.id("send")).click();
        assertEquals("To the Right",p1.findElement(By.id("direction")).getText());
        assertEquals("1H",p4.findElement(By.id("topCard")).getText());
        p4.findElement(By.id("card")).sendKeys("7H");
        p4.findElement(By.id("send")).click();
        assertEquals("7H",p4.findElement(By.id("topCard")).getText());
        assertEquals("Player 3's Turn", p4.findElement(By.id("turn")).getText());
        assertTrue(p3.findElement(By.id("play")).isDisplayed());

        assertEquals(2, GameController.game.currentTurn);
        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow44() throws Exception {
        //this.indexPage.connect();
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "7C";
        GameController.game.players.get(0).setHand("QC,2H");
        p1.findElement(By.id("refresh")).click();
        assertEquals("[QC, 2H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("QC");
        p1.findElement(By.id("send")).click();
        assertEquals("QC",p4.findElement(By.id("topCard")).getText());
        assertEquals("Your Turn was skipped",p2.findElement(By.id("skipped")).getText());
        assertEquals("Player 3's Turn", p4.findElement(By.id("turn")).getText());
        assertTrue(p3.findElement(By.id("play")).isDisplayed());

        assertEquals(2, GameController.game.currentTurn);
        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }

    @Test
    public void TestRow45() throws Exception {
        //this.indexPage.connect();
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "7C";
        GameController.game.players.get(0).setHand("4C,2H");
        GameController.game.players.get(1).setHand("5C,2H");
        GameController.game.players.get(2).setHand("6C,7H,4D");
        GameController.game.players.get(3).setHand("3C,2H");
        p1.findElement(By.id("refresh")).click();
        assertEquals("[4C, 2H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("4C");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("5C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("6C");
        p3.findElement(By.id("send")).click();
        assertEquals("[3C, 2H]",p4.findElement(By.id("hand")).getText());
        assertEquals("Player 4's Turn", p1.findElement(By.id("turn")).getText());
        assertEquals(3, GameController.game.currentTurn);
        p4.findElement(By.id("card")).sendKeys("3C");
        p4.findElement(By.id("send")).click();
        assertEquals("3C",p3.findElement(By.id("topCard")).getText());
        assertEquals(0, GameController.game.currentTurn);
        assertEquals("Your Turn", p1.findElement(By.id("turn")).getText());
        assertEquals("Player 1's Turn", p4.findElement(By.id("turn")).getText());
        assertTrue(p1.findElement(By.id("play")).isDisplayed());


        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }

    @Test
    public void TestRow46() throws Exception {
        //this.indexPage.connect();
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "7H";
        GameController.game.players.get(0).setHand("4H,2H");
        GameController.game.players.get(1).setHand("5H,2H");
        GameController.game.players.get(2).setHand("6H,7H,4D");
        GameController.game.players.get(3).setHand("1H,2H");
        p1.findElement(By.id("refresh")).click();
        assertEquals("[4H, 2H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("4H");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("5H");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("6H");
        p3.findElement(By.id("send")).click();
        assertEquals("[1H, 2H]",p4.findElement(By.id("hand")).getText());
        assertEquals("Player 4's Turn", p1.findElement(By.id("turn")).getText());
        assertEquals(3, GameController.game.currentTurn);
        p4.findElement(By.id("card")).sendKeys("1H");
        assertEquals("To the Left",p3.findElement(By.id("direction")).getText());
        p4.findElement(By.id("send")).click();
        assertEquals("To the Right",p3.findElement(By.id("direction")).getText());
        assertEquals("1H",p3.findElement(By.id("topCard")).getText());
        assertEquals(2, GameController.game.currentTurn);
        assertEquals("Your Turn", p3.findElement(By.id("turn")).getText());
        assertEquals("Player 3's Turn", p1.findElement(By.id("turn")).getText());
        p3.findElement(By.id("card")).sendKeys("7H");
        p3.findElement(By.id("send")).click();
        assertEquals("Your Turn", p2.findElement(By.id("turn")).getText());
        assertEquals("Player 2's Turn", p1.findElement(By.id("turn")).getText());
        assertTrue(p2.findElement(By.id("play")).isDisplayed());
        assertEquals(1, GameController.game.currentTurn);

        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }

}