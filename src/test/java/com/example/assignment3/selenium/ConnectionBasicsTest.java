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
    @Test
    public void TestRow48() throws Exception {
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
        GameController.game.players.get(3).setHand("QC,2H");
        p1.findElement(By.id("refresh")).click();
        assertEquals("[4C, 2H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("4C");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("5C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("6C");
        p3.findElement(By.id("send")).click();
        assertEquals("[QC, 2H]",p4.findElement(By.id("hand")).getText());
        assertEquals("Player 4's Turn", p1.findElement(By.id("turn")).getText());
        assertEquals(3, GameController.game.currentTurn);
        p4.findElement(By.id("card")).sendKeys("QC");
        p4.findElement(By.id("send")).click();
        assertEquals("Your Turn was skipped",p1.findElement(By.id("skipped")).getText());
        assertEquals(1, GameController.game.currentTurn);
        assertEquals("Your Turn", p2.findElement(By.id("turn")).getText());
        assertEquals("Player 2's Turn", p1.findElement(By.id("turn")).getText());
        assertTrue(p2.findElement(By.id("play")).isDisplayed());
        assertEquals(1, GameController.game.currentTurn);

        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow51() throws Exception {
        //this.indexPage.connect();
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "KC";
        GameController.game.players.get(0).setHand("KH,2H");
        p1.findElement(By.id("refresh")).click();
        assertEquals("KC",p1.findElement(By.id("topCard")).getText());
        assertEquals("[KH, 2H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("KH");
        p1.findElement(By.id("send")).click();
        assertEquals("KH",p2.findElement(By.id("topCard")).getText());

        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow52() throws Exception {
        //this.indexPage.connect();
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "KC";
        GameController.game.players.get(0).setHand("7C,2H");
        p1.findElement(By.id("refresh")).click();

        assertEquals("KC",p1.findElement(By.id("topCard")).getText());
        assertEquals("[7C, 2H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("7C");
        p1.findElement(By.id("send")).click();
        assertEquals("7C",p2.findElement(By.id("topCard")).getText());

        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow53() throws Exception {
        //this.indexPage.connect();
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "KC";
        GameController.game.players.get(0).setHand("8H,2H");
        p1.findElement(By.id("refresh")).click();
        assertEquals("KC",p1.findElement(By.id("topCard")).getText());
        assertEquals("[8H, 2H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("8H");
        assertTrue(p1.findElement(By.id("suite")).isDisplayed());

        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow54() throws Exception {
        //this.indexPage.connect();
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "KC";
        GameController.game.players.get(0).setHand("5S,2H");
        p1.findElement(By.id("refresh")).click();
        assertEquals("KC",p1.findElement(By.id("topCard")).getText());
        assertEquals("[5S, 2H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("5S");
        p1.findElement(By.id("send")).click();
        assertEquals("Invalid Input",p1.findElement(By.id("error")).getText());
        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow58() throws Exception {
        //this.indexPage.connect();
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "7C";
        GameController.game.players.get(0).setHand("3H");
        GameController.game.deck.clear();
        GameController.game.deck.add("2C");
        GameController.game.deck.add("6C");
        p1.findElement(By.id("refresh")).click();
        assertEquals("7C",p1.findElement(By.id("topCard")).getText());
        assertEquals("[3H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("draw")).click();
        assertEquals("[3H]",p1.findElement(By.id("hand")).getText());
        assertEquals("6C",p1.findElement(By.id("topCard")).getText());
        assertEquals("6C & played 6C",p1.findElement(By.id("drew")).getText());
        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow59() throws Exception {
        //this.indexPage.connect();
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "7C";
        GameController.game.players.get(0).setHand("3H");
        GameController.game.deck.clear();
        GameController.game.deck.add("5C");
        GameController.game.deck.add("6D");
        p1.findElement(By.id("refresh")).click();
        assertEquals("7C",p1.findElement(By.id("topCard")).getText());
        assertEquals("[3H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("draw")).click();
        assertEquals("[3H, 6D]",p1.findElement(By.id("hand")).getText());
        assertEquals("5C",p1.findElement(By.id("topCard")).getText());
        assertEquals("6D, 5C & played 5C",p1.findElement(By.id("drew")).getText());
        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow60() throws Exception {
        //this.indexPage.connect();
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "7C";
        GameController.game.players.get(0).setHand("3H");
        GameController.game.deck.clear();
        GameController.game.deck.add("7D");
        GameController.game.deck.add("7H");
        GameController.game.deck.add("5S");
        GameController.game.deck.add("6D");
        p1.findElement(By.id("refresh")).click();
        assertEquals("7C",p1.findElement(By.id("topCard")).getText());
        assertEquals("[3H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("draw")).click();
        assertEquals("[3H, 6D, 5S]",p1.findElement(By.id("hand")).getText());
        assertEquals("7H",p1.findElement(By.id("topCard")).getText());
        assertEquals("6D, 5S, 7H & played 7H",p1.findElement(By.id("drew")).getText());
        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow61() throws Exception {
        //this.indexPage.connect();
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "7C";
        GameController.game.players.get(0).setHand("3H");
        GameController.game.deck.clear();
        GameController.game.deck.add("7D");
        GameController.game.deck.add("4H");
        GameController.game.deck.add("5S");
        GameController.game.deck.add("6D");
        p1.findElement(By.id("refresh")).click();
        assertEquals("7C",p1.findElement(By.id("topCard")).getText());
        assertEquals("[3H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("draw")).click();
        assertEquals("[3H, 6D, 5S, 4H]",p1.findElement(By.id("hand")).getText());
        assertEquals("7C",p1.findElement(By.id("topCard")).getText());
        assertEquals("6D, 5S, 4H & can't play",p1.findElement(By.id("drew")).getText());
        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    
}