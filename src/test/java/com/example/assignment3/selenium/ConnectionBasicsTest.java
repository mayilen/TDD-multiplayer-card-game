package com.example.assignment3.selenium;

import com.example.assignment3.Game;
import com.example.assignment3.GameController;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    public void TestRo41() {
        //this.indexPage.connect();
        GameController.game.restart();
        GameController.game.players.clear();
        final WebDriver p1 = this.quickConnectSecondUser();
        final WebDriver p2 = this.quickConnectSecondUser();
        final WebDriver p3 = this.quickConnectSecondUser();
        final WebDriver p4 = this.quickConnectSecondUser();

        this.waitForDisplayed(p1.findElement(By.id("play")));
        GameController.game.topCard="5C";
        GameController.game.players.get(0).setHand("3C,2H");
        // assertTrue(this.indexPage.showsGame());
        p1.findElement(By.id("card")).sendKeys("3C");
        p1.findElement(By.id("send")).click();

        assertEquals(1,GameController.game.currentTurn);
       assertEquals(4, GameController.game.players.size());

        p1.quit();
        p2.quit();
        p3.quit();
        p4.quit();
    }
//
//    @Test
//    public void canOpenLobby() {
//        this.indexPage.connect();
//        assertThat(this.waitForDisplayed(this.indexPage.numberPlayers).isEnabled(), is(true));
//        this.indexPage.open.click();
//        assertThat(this.indexPage.hasText("Opening the lobby with specified settings."), is(true));
//        this.indexPage.disconnect();
//    }
//
//    @Test
//    public void canStartGame() {
//        this.indexPage.connect();
//        this.waitForDisplayed(this.indexPage.numberPlayers).isEnabled();
//        this.indexPage.numberPlayers.sendKeys("1");
//        this.indexPage.open.click();
//        assertThat(this.indexPage.hasText("The game is now ready to begin. Press start when ready."), is(true));
//        this.indexPage.start.click();
//        assertThat(this.indexPage.hasText("The game has started! Please wait for your turn."), is(true));
//        this.indexPage.disconnect();
//    }
//
//    @Test
//    public void canSeeCardsAfterStart() throws InterruptedException {
//        this.indexPage.quickStart();
//        assertThat(this.indexPage.countNumberOfCardsFor(this.indexPage.playerCards), is(2));
//        assertThat(this.indexPage.countNumberOfCardsFor(this.indexPage.dealerCards), is(2));
//        assertThat(this.indexPage.countNumberOfCardsFor(this.indexPage.otherPlayer1Cards), is(2));
//        assertThat(this.indexPage.countNumberOfCardsFor(this.indexPage.otherPlayer2Cards), is(2));
//
//        // Want to see if first card is hidden as it should be
//        final List<WebElement> dealerCards = this.indexPage.getAllCardsFor(this.indexPage.dealerCards);
//        final String hiddenCardHTML = dealerCards.get(0).getAttribute("innerHTML");
//        assertThat(hiddenCardHTML.contains("card back"), is(true));
//
//        // However, our cards should always be visible.
//        final List<WebElement> playerCards = this.indexPage.getAllCardsFor(this.indexPage.playerCards);
//        final String visibleCardHTML = playerCards.get(0).getAttribute("innerHTML");
//        assertThat(visibleCardHTML.contains("card back"), is(false));
//        assertThat(visibleCardHTML.contains("<span class=\"rank\">"), is(true));
//        this.indexPage.disconnect();
//    }
}
