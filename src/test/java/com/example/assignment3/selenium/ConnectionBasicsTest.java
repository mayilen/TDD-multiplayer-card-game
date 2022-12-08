package com.example.assignment3.selenium;

import com.example.assignment3.Game;
import com.example.assignment3.GameController;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
        GameController.game.topCard = "4H";
        GameController.game.players.get(0).setHand("3H,4D,5D,10H,5H");
        GameController.game.players.get(1).setHand("5C,4C,9D,10D,5S");
        GameController.game.players.get(2).setHand("1S,3C,7D,KD,10S");
        GameController.game.players.get(3).setHand("3S,7C,6D,JD,10C");

        GameController.game.deck.clear();
        GameController.game.deck.add("2C");
        GameController.game.deck.add("6C");
        p1.findElement(By.id("refresh")).click();
        assertEquals("4H",p1.findElement(By.id("topCard")).getText());
        assertEquals("[3H, 4D, 5D, 10H, 5H]",p1.findElement(By.id("hand")).getText());
        assertEquals("[5C, 4C, 9D, 10D, 5S]",p2.findElement(By.id("hand")).getText());
        assertEquals("[1S, 3C, 7D, KD, 10S]",p3.findElement(By.id("hand")).getText());
        assertEquals("[3S, 7C, 6D, JD, 10C]",p4.findElement(By.id("hand")).getText());

        p1.findElement(By.id("card")).sendKeys("5H");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("5S");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("10S");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("10C");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("10H");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("10D");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("KD");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("JD");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("5D");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("9D");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("7D");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("6D");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("4D");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("4C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("3C");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("7C");
        p4.findElement(By.id("send")).click();
        assertEquals("7C",p1.findElement(By.id("topCard")).getText());
        assertEquals("[3H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("draw")).click();
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
        GameController.game.topCard = "9H";
        GameController.game.players.get(0).setHand("3H,4D,JH,KC,10H");
        GameController.game.players.get(1).setHand("5C,4C,KH,9C,10S");
        GameController.game.players.get(2).setHand("1S,3C,5H,JC,10D");
        GameController.game.players.get(3).setHand("3S,7C,5D,JD,10C");

        GameController.game.deck.clear();
        GameController.game.deck.add("2H");
        GameController.game.deck.add("5C");
        GameController.game.deck.add("6D");
        p1.findElement(By.id("refresh")).click();
        assertEquals("9H",p1.findElement(By.id("topCard")).getText());
        assertEquals("[3H, 4D, JH, KC, 10H]",p1.findElement(By.id("hand")).getText());
        assertEquals("[8D, 4C, KH, 9C, 10S]",p2.findElement(By.id("hand")).getText());
        assertEquals("[1S, 3C, 5H, JC, 10D]",p3.findElement(By.id("hand")).getText());
        assertEquals("[3S, 7C, 5D, JD, 10C]",p4.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("10H");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("10S");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("10D");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("10C");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("KC");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("9C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("JC");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("JD");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("JH");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("KH");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("5H");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("5D");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("4D");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("4C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("3C");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("7C");
        p4.findElement(By.id("send")).click();

        assertEquals("7C",p1.findElement(By.id("topCard")).getText());
        assertEquals("[3H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("draw")).click();
        assertEquals("[3H, 6D]",p1.findElement(By.id("hand")).getText());
        assertEquals("5C",p1.findElement(By.id("topCard")).getText());
        assertEquals("6D,5C & played 5C",p1.findElement(By.id("drew")).getText());
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
        GameController.game.topCard = "9H";
        GameController.game.players.get(0).setHand("3H,4D,JH,KC,10H");
        GameController.game.players.get(1).setHand("5C,4C,KH,9C,10S");
        GameController.game.players.get(2).setHand("1S,3C,5H,JC,10D");
        GameController.game.players.get(3).setHand("3S,7C,5D,JD,10C");

        GameController.game.deck.clear();
        GameController.game.deck.add("2H");
        GameController.game.deck.add("7H");
        GameController.game.deck.add("5S");
        GameController.game.deck.add("6D");
        p1.findElement(By.id("refresh")).click();
        assertEquals("9H",p1.findElement(By.id("topCard")).getText());
        assertEquals("[3H, 4D, JH, KC, 10H]",p1.findElement(By.id("hand")).getText());
        assertEquals("[5C, 4C, KH, 9C, 10S]",p2.findElement(By.id("hand")).getText());
        assertEquals("[1S, 3C, 5H, JC, 10D]",p3.findElement(By.id("hand")).getText());
        assertEquals("[3S, 7C, 5D, JD, 10C]",p4.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("10H");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("10S");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("10D");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("10C");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("KC");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("9C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("JC");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("JD");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("JH");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("KH");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("5H");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("5D");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("4D");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("4C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("3C");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("7C");
        p4.findElement(By.id("send")).click();

        assertEquals("7C",p1.findElement(By.id("topCard")).getText());
        assertEquals("[3H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("draw")).click();
        assertEquals("[3H, 6D, 5S]",p1.findElement(By.id("hand")).getText());
        assertEquals("7H",p1.findElement(By.id("topCard")).getText());
        assertEquals("6D,5S,7H & played 7H",p1.findElement(By.id("drew")).getText());
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
        GameController.game.topCard = "9H";
        GameController.game.players.get(0).setHand("3H,4D,JH,KC,10H");
        GameController.game.players.get(1).setHand("5C,4C,KH,9C,10S");
        GameController.game.players.get(2).setHand("1S,3C,5H,JC,10D");
        GameController.game.players.get(3).setHand("3S,7C,5D,JD,10C");

        GameController.game.deck.clear();
        GameController.game.deck.add("4H");
        GameController.game.deck.add("5S");
        GameController.game.deck.add("6D");
        p1.findElement(By.id("refresh")).click();
        assertEquals("9H",p1.findElement(By.id("topCard")).getText());
        assertEquals("[3H, 4D, JH, KC, 10H]",p1.findElement(By.id("hand")).getText());
        assertEquals("[5C, 4C, KH, 9C, 10S]",p2.findElement(By.id("hand")).getText());
        assertEquals("[1S, 3C, 5H, JC, 10D]",p3.findElement(By.id("hand")).getText());
        assertEquals("[3S, 7C, 5D, JD, 10C]",p4.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("10H");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("10S");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("10D");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("10C");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("KC");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("9C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("JC");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("JD");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("JH");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("KH");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("5H");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("5D");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("4D");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("4C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("3C");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("7C");
        p4.findElement(By.id("send")).click();

        assertEquals("7C",p1.findElement(By.id("topCard")).getText());
        assertEquals("[3H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("draw")).click();
        assertEquals("[3H, 6D, 5S, 4H]",p1.findElement(By.id("hand")).getText());
        assertEquals("7C",p1.findElement(By.id("topCard")).getText());
        assertEquals("6D,5S,4H & can't play",p1.findElement(By.id("drew")).getText());
        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow62() throws Exception {
        //this.indexPage.connect();
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "9H";
        GameController.game.players.get(0).setHand("3H,4D,JH,KC,10H");
        GameController.game.players.get(1).setHand("5C,4C,KH,9C,10S");
        GameController.game.players.get(2).setHand("1S,3C,5H,JC,10D");
        GameController.game.players.get(3).setHand("3S,7C,5D,JD,10C");

        GameController.game.deck.clear();
        GameController.game.deck.add("4H");
        GameController.game.deck.add("8H");
        GameController.game.deck.add("6D");
        p1.findElement(By.id("refresh")).click();
        assertEquals("9H",p1.findElement(By.id("topCard")).getText());
        assertEquals("[3H, 4D, JH, KC, 10H]",p1.findElement(By.id("hand")).getText());
        assertEquals("[5C, 4C, KH, 9C, 10S]",p2.findElement(By.id("hand")).getText());
        assertEquals("[1S, 3C, 5H, JC, 10D]",p3.findElement(By.id("hand")).getText());
        assertEquals("[3S, 7C, 5D, JD, 10C]",p4.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("10H");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("10S");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("10D");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("10C");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("KC");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("9C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("JC");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("JD");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("JH");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("KH");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("5H");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("5D");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("4D");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("4C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("3C");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("7C");
        p4.findElement(By.id("send")).click();

        assertEquals("7C",p1.findElement(By.id("topCard")).getText());
        assertEquals("[3H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("draw")).click();
        assertTrue(p1.findElement(By.id("changesuite")).isDisplayed());
        Select dropdown = new Select(p1.findElement(By.id("suite2")));
        dropdown.selectByValue("D");
        p1.findElement(By.id("change")).click();
        assertEquals("8D",p1.findElement(By.id("topCard")).getText());
        assertEquals("[3H, 6D]",p1.findElement(By.id("hand")).getText());
        assertEquals("6D,8H & played 8H",p1.findElement(By.id("drew")).getText());
        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow63() throws Exception {
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "10C";
        GameController.game.players.get(0).setHand("KS,3H,4D,JH,KC");
        GameController.game.players.get(1).setHand("5C,KH,4C,KH,9C");
        GameController.game.players.get(2).setHand("1S,5H,3C,5H,JC");
        GameController.game.players.get(3).setHand("3S,5D,7C,5D,JD");

        GameController.game.deck.clear();
        GameController.game.deck.add("4H");
        GameController.game.deck.add("5S");
        GameController.game.deck.add("6C");
        p1.findElement(By.id("refresh")).click();
        assertEquals("10C",p1.findElement(By.id("topCard")).getText());
        assertEquals("[KS, 3H, 4D, JH, KC]",p1.findElement(By.id("hand")).getText());
        assertEquals("[5C, KH, 4C, KH, 9C]",p2.findElement(By.id("hand")).getText());
        assertEquals("[1S, 5H, 3C, 5H, JC]",p3.findElement(By.id("hand")).getText());
        assertEquals("[3S, 5D, 7C, 5D, JD]",p4.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("KC");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("9C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("JC");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("JD");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("JH");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("KH");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("5H");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("5D");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("4D");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("4C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("3C");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("7C");
        p4.findElement(By.id("send")).click();
        assertEquals("7C",p1.findElement(By.id("topCard")).getText());
        assertEquals("[KS, 3H]",p1.findElement(By.id("hand")).getText());
        p1.findElement(By.id("draw")).click();
        assertEquals("6C",p1.findElement(By.id("topCard")).getText());
        assertEquals("[KS, 3H]",p1.findElement(By.id("hand")).getText());
        assertEquals("6C & played 6C",p1.findElement(By.id("drew")).getText());

        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow67() throws Exception {
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "6C";
        GameController.game.players.get(0).setHand("2C,3C,10D,JD,7C");
        GameController.game.players.get(1).setHand("4H,QC,10S,KD,9C");
        GameController.game.players.get(2).setHand("1S,5C,3S,5D,KC");
        GameController.game.players.get(3).setHand("3S,4C,QS,7D,JC");

        GameController.game.deck.clear();
        GameController.game.deck.add("4C");
        GameController.game.deck.add("9D");
        GameController.game.deck.add("6C");
        p1.findElement(By.id("refresh")).click();
        assertEquals("6C",p1.findElement(By.id("topCard")).getText());
        assertEquals("[2C, 3C, 10D, JD, 7C]",p1.findElement(By.id("hand")).getText());
        assertEquals("[4H, QC, 10S, KD, 9C]",p2.findElement(By.id("hand")).getText());
        assertEquals("[1S, 5C, 3S, 5D, KC]",p3.findElement(By.id("hand")).getText());
        assertEquals("[3S, 4C, QS, 7D, JC]",p4.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("7C");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("9C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("KC");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("JC");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("JD");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("KD");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("5D");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("7D");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("10D");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("10S");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("3S");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("QS");
        p4.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("QC");
        p2.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("4C");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("2C");
        p1.findElement(By.id("send")).click();
        assertEquals("2C",p2.findElement(By.id("topCard")).getText());
        assertEquals("[4H]",p2.findElement(By.id("hand")).getText());
        p2.findElement(By.id("draw")).click();
        assertEquals("6C",p2.findElement(By.id("topCard")).getText());
        assertEquals("[4H, 9D]",p2.findElement(By.id("hand")).getText());
        assertEquals("6C,9D & played 6C",p2.findElement(By.id("drew")).getText());

        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow68() throws Exception {
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "6C";
        GameController.game.players.get(0).setHand("2C,3C,10D,JD,7C");
        GameController.game.players.get(1).setHand("4H,QC,10S,KD,9C");
        GameController.game.players.get(2).setHand("1S,5C,3S,5D,KC");
        GameController.game.players.get(3).setHand("3S,4C,QS,7D,JC");

        GameController.game.deck.clear();
        GameController.game.deck.add("6C");
        GameController.game.deck.add("9H");
        GameController.game.deck.add("9D");
        GameController.game.deck.add("6S");
        p1.findElement(By.id("refresh")).click();
        assertEquals("6C",p1.findElement(By.id("topCard")).getText());
        assertEquals("[2C, 3C, 10D, JD, 7C]",p1.findElement(By.id("hand")).getText());
        assertEquals("[4H, QC, 10S, KD, 9C]",p2.findElement(By.id("hand")).getText());
        assertEquals("[1S, 5C, 3S, 5D, KC]",p3.findElement(By.id("hand")).getText());
        assertEquals("[3S, 4C, QS, 7D, JC]",p4.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("7C");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("9C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("KC");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("JC");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("JD");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("KD");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("5D");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("7D");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("10D");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("10S");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("3S");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("QS");
        p4.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("QC");
        p2.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("4C");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("2C");
        p1.findElement(By.id("send")).click();
        assertEquals("2C",p2.findElement(By.id("topCard")).getText());
        assertEquals("[4H]",p2.findElement(By.id("hand")).getText());
        p2.findElement(By.id("draw")).click();
        assertEquals("6C",p2.findElement(By.id("topCard")).getText());
        assertEquals("[4H, 6S, 9D, 9H]",p2.findElement(By.id("hand")).getText());
        assertEquals("6S,9D,9H,6C & played 6C",p2.findElement(By.id("drew")).getText());

        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow69() throws Exception {
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "6C";
        GameController.game.players.get(0).setHand("2C,3C,10D,JD,7C");
        GameController.game.players.get(1).setHand("4H,QC,10S,KD,9C");
        GameController.game.players.get(2).setHand("1S,5C,3S,5D,KC");
        GameController.game.players.get(3).setHand("3S,4C,QS,7D,JC");

        GameController.game.deck.clear();
        GameController.game.deck.add("5H");
        GameController.game.deck.add("7S");
        GameController.game.deck.add("9H");
        GameController.game.deck.add("9D");
        GameController.game.deck.add("6S");
        p1.findElement(By.id("refresh")).click();
        assertEquals("6C",p1.findElement(By.id("topCard")).getText());
        assertEquals("[2C, 3C, 10D, JD, 7C]",p1.findElement(By.id("hand")).getText());
        assertEquals("[4H, QC, 10S, KD, 9C]",p2.findElement(By.id("hand")).getText());
        assertEquals("[1S, 5C, 3S, 5D, KC]",p3.findElement(By.id("hand")).getText());
        assertEquals("[3S, 4C, QS, 7D, JC]",p4.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("7C");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("9C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("KC");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("JC");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("JD");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("KD");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("5D");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("7D");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("10D");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("10S");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("3S");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("QS");
        p4.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("QC");
        p2.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("4C");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("2C");
        p1.findElement(By.id("send")).click();
        assertEquals("2C",p2.findElement(By.id("topCard")).getText());
        assertEquals("[4H]",p2.findElement(By.id("hand")).getText());
        p2.findElement(By.id("draw")).click();
        assertEquals("2C",p2.findElement(By.id("topCard")).getText());
        assertEquals("[4H, 6S, 9D, 9H, 7S, 5H]",p2.findElement(By.id("hand")).getText());
        assertEquals("6S,9D,9H,7S,5H & can't play",p2.findElement(By.id("drew")).getText());

        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow70() throws Exception {
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "6C";
        GameController.game.players.get(0).setHand("2C,5C,10D,JD,7C");
        GameController.game.players.get(1).setHand("4H,3S,10S,KD,9C");
        GameController.game.players.get(2).setHand("7D,3C,9S,5D,KC");
        GameController.game.players.get(3).setHand("5H,4C,QS,7D,JC");

        GameController.game.deck.clear();

        GameController.game.deck.add("7D");
        GameController.game.deck.add("7C");
        GameController.game.deck.add("6H");
        GameController.game.deck.add("6D");
        GameController.game.deck.add("5S");
        GameController.game.deck.add("9D");
        GameController.game.deck.add("2H");
        p1.findElement(By.id("refresh")).click();
        assertEquals("6C",p1.findElement(By.id("topCard")).getText());
        assertEquals("[2C, 5C, 10D, JD, 7C]",p1.findElement(By.id("hand")).getText());
        assertEquals("[4H, 3S, 10S, KD, 9C]",p2.findElement(By.id("hand")).getText());
        assertEquals("[7D, 3C, 9S, 5D, KC]",p3.findElement(By.id("hand")).getText());
        assertEquals("[5H, 4C, QS, 7D, JC]",p4.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("7C");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("9C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("KC");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("JC");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("JD");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("KD");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("5D");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("7D");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("10D");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("10S");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("9S");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("QS");
        p4.findElement(By.id("send")).click();

        p2.findElement(By.id("card")).sendKeys("3S");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("3C");
        p3.findElement(By.id("send")).click();

        p4.findElement(By.id("card")).sendKeys("4C");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("2C");
        p1.findElement(By.id("send")).click();
        assertEquals("2C",p2.findElement(By.id("topCard")).getText());
        assertEquals("[4H]",p2.findElement(By.id("hand")).getText());
        p2.findElement(By.id("draw")).click();
        assertEquals("2H",p2.findElement(By.id("topCard")).getText());
        assertEquals("[4H, 9D]",p2.findElement(By.id("hand")).getText());
        assertEquals("2H,9D & played 2H",p2.findElement(By.id("drew")).getText());
        p3.findElement(By.id("draw")).click();
        assertEquals("6H",p3.findElement(By.id("topCard")).getText());
        assertEquals("[7D, 5S, 6D, 7C]",p3.findElement(By.id("hand")).getText());
        assertEquals("5S,6D,6H,7C & played 6H",p3.findElement(By.id("drew")).getText());
        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow72() throws Exception {
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "6C";
        GameController.game.players.get(0).setHand("2C,5C,2C,JD,7C");
        GameController.game.players.get(1).setHand("4C,6C,9D,KD,9C");
        GameController.game.players.get(2).setHand("7D,3C,9S,5D,KC");
        GameController.game.players.get(3).setHand("5H,4C,QS,5C,JC");

        GameController.game.deck.clear();

        GameController.game.deck.add("7D");
        GameController.game.deck.add("7C");
        GameController.game.deck.add("6H");
        GameController.game.deck.add("6D");
        GameController.game.deck.add("5S");
        GameController.game.deck.add("9D");
        GameController.game.deck.add("2H");
        p1.findElement(By.id("refresh")).click();
        assertEquals("6C",p1.findElement(By.id("topCard")).getText());
        assertEquals("[2C, 5C, 2C, JD, 7C]",p1.findElement(By.id("hand")).getText());
        assertEquals("[4C, 6C, 9D, KD, 9C]",p2.findElement(By.id("hand")).getText());
        assertEquals("[7D, 3C, 9S, 5D, KC]",p3.findElement(By.id("hand")).getText());
        assertEquals("[5H, 4C, QS, 5C, JC]",p4.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("7C");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("9C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("KC");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("JC");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("JD");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("KD");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("5D");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("5C");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("2C");
        p1.findElement(By.id("send")).click();
        assertEquals("2C",p2.findElement(By.id("topCard")).getText());
        assertEquals("[4C, 6C, 9D]",p2.findElement(By.id("hand")).getText());
        p2.findElement(By.id("card")).sendKeys("4C,6C");
        p2.findElement(By.id("send")).click();
        assertEquals("[9D]",p2.findElement(By.id("hand")).getText());
        assertEquals("Player 3's Turn", p1.findElement(By.id("turn")).getText());

        assertEquals("Your Turn", p3.findElement(By.id("turn")).getText());
        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow73() throws Exception {
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "6C";
        GameController.game.players.get(0).setHand("2H,2C,10C,JD,7C");
        GameController.game.players.get(1).setHand("4C,6C,9C,KD,3C");
        GameController.game.players.get(2).setHand("7D,3C,JC,5D,KC");
        GameController.game.players.get(3).setHand("5H,4C,7C,5C,JC");

        GameController.game.deck.clear();

        GameController.game.deck.add("7D");
        GameController.game.deck.add("7C");
        GameController.game.deck.add("6H");
        GameController.game.deck.add("6D");
        GameController.game.deck.add("5S");
        GameController.game.deck.add("9D");
        GameController.game.deck.add("2H");
        p1.findElement(By.id("refresh")).click();
        assertEquals("6C",p1.findElement(By.id("topCard")).getText());
        assertEquals("[2H, 2C, 10C, JD, 7C]",p1.findElement(By.id("hand")).getText());
        assertEquals("[4C, 6C, 9C, KD, 3C]",p2.findElement(By.id("hand")).getText());
        assertEquals("[7D, 3C, JC, 5D, KC]",p3.findElement(By.id("hand")).getText());
        assertEquals("[5H, 4C, 7C, 5C, JC]",p4.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("7C");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("3C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("KC");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("JC");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("JD");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("KD");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("5D");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("5C");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("10C");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("9C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("JC");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("7C");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("2C");
        p1.findElement(By.id("send")).click();
        assertEquals("2C",p2.findElement(By.id("topCard")).getText());
        assertEquals("[4C, 6C]",p2.findElement(By.id("hand")).getText());
        p2.findElement(By.id("card")).sendKeys("4C,6C");
        p2.findElement(By.id("send")).click();
        assertEquals("2",p1.findElement(By.id("score1")).getText());
        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow77() throws Exception {
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "3D";
        GameController.game.players.get(0).setHand("1S,3S,6H,JD,4D");
        GameController.game.players.get(1).setHand("4C,QS,QH,QD,5D");
        GameController.game.players.get(2).setHand("8H,JH,6H,KH,KS");
        GameController.game.players.get(3).setHand("8C,QC,2D,8D,7D");

        GameController.game.deck.clear();

        GameController.game.deck.add("7D");
        GameController.game.deck.add("7C");
        GameController.game.deck.add("6H");
        GameController.game.deck.add("6D");
        GameController.game.deck.add("3H");
        GameController.game.deck.add("6D");
        GameController.game.deck.add("3D");
        p1.findElement(By.id("refresh")).click();
        assertEquals("3D",p1.findElement(By.id("topCard")).getText());
        assertEquals("[1S, 3S, 6H, JD, 4D]",p1.findElement(By.id("hand")).getText());
        assertEquals("[4C, QS, QH, QD, 5D]",p2.findElement(By.id("hand")).getText());
        assertEquals("[8H, JH, 6H, KH, KS]",p3.findElement(By.id("hand")).getText());
        assertEquals("[8C, QC, 2D, 8D, 7D]",p4.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("4D");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("5D");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("draw")).click();
        p4.findElement(By.id("card")).sendKeys("7D");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("JD");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("QD");
        p2.findElement(By.id("send")).click();
        p4.findElement(By.id("draw")).click();
        p1.findElement(By.id("card")).sendKeys("6H");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("QH");
        p2.findElement(By.id("send")).click();
        p4.findElement(By.id("draw")).click();
        p1.findElement(By.id("card")).sendKeys("3S");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("QS");
        p2.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("QC");
        p4.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("4C");
        p2.findElement(By.id("send")).click();
        assertEquals( "Player 2 has Won!",p2.findElement(By.id("winner")).getText());
        assertEquals("[1S]",p1.findElement(By.id("hand")).getText());
        assertEquals("[]",p2.findElement(By.id("hand")).getText());
        assertEquals("[8H, JH, 6H, KH, KS]",p3.findElement(By.id("hand")).getText());
        assertEquals("[8C, 2D, 8D]",p4.findElement(By.id("hand")).getText());
        assertEquals("1",p2.findElement(By.id("score1")).getText());
        assertEquals("0",p2.findElement(By.id("score2")).getText());
        assertEquals("86",p2.findElement(By.id("score3")).getText());
        assertEquals("102",p2.findElement(By.id("score4")).getText());
        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
    @Test
    public void TestRow81() throws Exception {
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard = "4D";
        GameController.game.players.get(0).setHand("4H,7S,5D,6D,9D");
        GameController.game.players.get(1).setHand("4S,6S,KC,8H,10D");
        GameController.game.players.get(2).setHand("9S,6C,9C,JD,3H");
        GameController.game.players.get(3).setHand("7D,JH,QH,KH,5C");

        GameController.game.deck.clear();

        GameController.game.deck.add("7D");
        GameController.game.deck.add("7C");
        GameController.game.deck.add("JC");
        GameController.game.deck.add("10C");
        GameController.game.deck.add("4C");
        GameController.game.deck.add("3C");
        GameController.game.deck.add("2C");
        p1.findElement(By.id("refresh")).click();
        assertEquals("4D",p1.findElement(By.id("topCard")).getText());
        assertEquals("[4H, 7S, 5D, 6D, 9D]",p1.findElement(By.id("hand")).getText());
        assertEquals("[4S, 6S, KC, 8H, 10D]",p2.findElement(By.id("hand")).getText());
        assertEquals("[9S, 6C, 9C, JD, 3H]",p3.findElement(By.id("hand")).getText());
        assertEquals("[7D, JH, QH, KH, 5C]",p4.findElement(By.id("hand")).getText());
        p1.findElement(By.id("card")).sendKeys("4H");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("4S");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("9S");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("draw")).click();
        assertEquals("2C,3C,4C & can't play",p4.findElement(By.id("drew")).getText());
        p1.findElement(By.id("card")).sendKeys("7S");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("6S");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("6C");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("2C");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("draw")).click();
        assertEquals("10C,JC & played JC",p1.findElement(By.id("drew")).getText());
        p2.findElement(By.id("card")).sendKeys("KC");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("9C");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("3C");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("draw")).click();
        assertEquals("7C & played 7C",p1.findElement(By.id("drew")).getText());
        p2.findElement(By.id("card")).sendKeys("8H");
        assertTrue(p2.findElement(By.id("suite")).isDisplayed());
        Select dropdown = new Select(p2.findElement(By.id("suite")));
        dropdown.selectByValue("D");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("JD");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("7D");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("9D");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("10D");
        p2.findElement(By.id("send")).click();
        assertEquals("21",p2.findElement(By.id("score1")).getText());
        assertEquals("0",p2.findElement(By.id("score2")).getText());
        assertEquals("3",p2.findElement(By.id("score3")).getText());
        assertEquals("39",p2.findElement(By.id("score4")).getText());
        assertEquals(1,GameController.game.startRoundIndex);
        assertEquals("Player 2's Turn", p1.findElement(By.id("turn")).getText());



        GameController.game.topCard = "10D";
        GameController.game.players.get(0).setHand("7D,4S,7C,4H,5D");
        GameController.game.players.get(1).setHand("9D,3S,9C,3H,JC");
        GameController.game.players.get(2).setHand("3D,9S,3C,9H,5H");
        GameController.game.players.get(3).setHand("4D,7S,4C,5S,8D");

        GameController.game.deck.clear();
        GameController.game.deck.add("10S");
        GameController.game.deck.add("JS");
        GameController.game.deck.add("6S");
        GameController.game.deck.add("JD");
        GameController.game.deck.add("QD");
        GameController.game.deck.add("6D");
        GameController.game.deck.add("KH");
        GameController.game.deck.add("QS");
        GameController.game.deck.add("KS");
        p2.findElement(By.id("refresh")).click();
        assertEquals("10D",p1.findElement(By.id("topCard")).getText());
        assertEquals("[7D, 4S, 7C, 4H, 5D]",p1.findElement(By.id("hand")).getText());
        assertEquals("[9D, 3S, 9C, 3H, JC]",p2.findElement(By.id("hand")).getText());
        assertEquals("[3D, 9S, 3C, 9H, 5H]",p3.findElement(By.id("hand")).getText());
        assertEquals("[4D, 7S, 4C, 5S, 8D]",p4.findElement(By.id("hand")).getText());
        p2.findElement(By.id("card")).sendKeys("9D");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("3D");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("4D");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("4S");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("3S");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("9S");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("7S");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("7C");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("9C");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("3C");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("card")).sendKeys("4C");
        p4.findElement(By.id("send")).click();
        p1.findElement(By.id("card")).sendKeys("4H");
        p1.findElement(By.id("send")).click();
        p2.findElement(By.id("card")).sendKeys("3H");
        p2.findElement(By.id("send")).click();
        p3.findElement(By.id("card")).sendKeys("9H");
        p3.findElement(By.id("send")).click();
        p4.findElement(By.id("draw")).click();
        assertEquals("KS,QS,KH & played KH",p4.findElement(By.id("drew")).getText());
        p1.findElement(By.id("draw")).click();
        assertEquals("6D,QD,JD & can't play",p1.findElement(By.id("drew")).getText());
        p2.findElement(By.id("draw")).click();
        assertEquals("6S,JS,10S & can't play",p2.findElement(By.id("drew")).getText());
        p3.findElement(By.id("card")).sendKeys("5H");
        p3.findElement(By.id("send")).click();


        assertEquals( "Player 3 has Won!",p2.findElement(By.id("winner")).getText());
        assertEquals("[7D, 5D, 6D, QD, JD]",p1.findElement(By.id("hand")).getText());
        assertEquals("[JC, 6S, JS, 10S]",p2.findElement(By.id("hand")).getText());
        assertEquals(0,GameController.game.players.get(2).cards.size());
        assertEquals("[]",p3.findElement(By.id("hand")).getText());
        assertEquals("[5S, 8D, KS, QS]",p4.findElement(By.id("hand")).getText());
        assertEquals("59",p2.findElement(By.id("score1")).getText());
        assertEquals("36",p2.findElement(By.id("score2")).getText());
        assertEquals("3",p2.findElement(By.id("score3")).getText());
        assertEquals("114",p2.findElement(By.id("score4")).getText());
        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
}