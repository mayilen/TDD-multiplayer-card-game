package com.example.assignment3;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Game game;
    @Test
    public void getSuit(){
         game = new Game();
        assertEquals(4,game.suit.size());
    }
    @Test
    public void changeSuit(){
        game = new Game();
        game.topCard="3S";
        String topcard=game.topCard;
        game.changeSuit("D");
        assertNotEquals(topcard,game.topCard);
        assertEquals("3D",game.topCard);
    }
    @Test
    public void getRank(){
         game = new Game();
        assertEquals(13,game.rank.size());
    }
    @Test
    public void intializeTopCard(){
        game = new Game();
        game.deck= new ArrayList<>();
        game.deck.add("8S");
        game.deck.add("1S");
        assertEquals("1S",game.intializeTopCard());
    }
    @Test
    public void deckInitializer(){
        game=new Game();
        assertEquals(52,game.deckInitializer().size());
    }
    @Test
    public void drawCard(){
        game=new Game();
        assertEquals(52,game.deck.size());
        game.drawCard();
        assertEquals(51,game.deck.size());

    }
    @Test
    public void incrementPlus2(){
        game=new Game();
        game.topCard="2S";
        assertEquals(2,game.incrementPlus2("2S"));
        assertEquals(1,game.incrementPlus2("JS"));


    }

    @Test
    public void drawTwo(){
        game=new Game();
        assertEquals(52,game.deck.size());
        game.drawtwo();
        assertEquals(50,game.deck.size());
        game.plus2Count=2;
        game.drawtwo();
        assertEquals(46,game.deck.size());
        game.deck.clear();
        game.deck.add("3S");
        game.drawtwo();
        assertEquals(0,game.deck.size());

    }
    @Test
    public void getCardSuit(){
        game=new Game();
        assertEquals("S",game.getCardSuit("7S"));
    }
    @Test
    public void getCardRank(){
        game=new Game();
        assertEquals("7",game.getCardRank("7S"));
    }
    @Test
    public void dealCards(){
        game=new Game();
        assertEquals(5,game.dealHand().size());
        assertEquals(47,game.deck.size());
    }
    @Test
    public void PlayCard(){
        game=new Game();
        game.topCard="3D";
        game.playCard("5D");
        assertEquals("5D",game.topCard);
        game.playCard("1D");
        assertEquals(0,game.currentTurn);
        assertEquals(-1,game.direction);
        assertEquals(3,game.nextTurn());
        game.playCard("QD");
        assertEquals(1,game.nextTurn());
        game.topCard="3D";
        game.playCard("5D,4D");
        assertEquals("4D",game.topCard);
        game.playCard("2D");
        assertFalse(game.drew2);
        assertFalse(game.canPlay("2S"));
    }
    @Test
    public void maxPlayer() {
        game = new Game();
        game.setMaxPlayer(3);
        assertEquals(3,game.maxPlayer);
    }
    @Test
    public void getDeckSize(){
        game=new Game();
        assertEquals(52,game.getDeckSize());
    }
    @Test
    public void removeFromPlayer(){
        game=new Game();
        game.players.add(new Player(1));
        game.players.get(0).setHand("3H,7D,9H");
        game.removeFromPlayerHand(0,"3H");
        assertEquals(2,game.players.get(0).cards.size());
    }
    @Test
    public void canDraw(){
        game=new Game();
        assertTrue(game.canDraw());
        game.deck=new ArrayList<>();
        game.deck.add("3H");
        assertTrue(game.canDraw());
        game.drawCard();
        assertFalse(game.canDraw());
    }
    @Test
    public void canPlayTest(){
        game=new Game();
        game.topCard="7H";
        assertTrue(game.canPlay("7D"));
        assertTrue(game.canPlay("9H"));
        assertFalse(game.canPlay("2C"));
        game.topCard="2D";
        assertFalse(game.canPlay("2S"));
        assertTrue(game.canPlay("7D,3D"));
        game.drew2=true;
        assertTrue(game.canPlay("7D"));
        assertFalse(game.canPlay("2D,7D"));
        game.topCard="7H";
        assertFalse(game.canPlay("7D,3H"));
        assertTrue(game.canPlay("8D"));

    }
   @Test
    public void nextTurn(){
        Game game=new Game();
        assertEquals(1,game.nextTurn());
       assertEquals(2,game.nextTurn());
       assertEquals(3,game.nextTurn());
       assertEquals(0,game.nextTurn());
       assertEquals(1,game.nextTurn());
   }
    @Test
    public void nextRound(){
        Game game=new Game();
        assertEquals(0,game.nextRound());
        assertEquals(1,game.nextRound());
        assertEquals(2,game.nextRound());
        assertEquals(3,game.nextRound());
        assertEquals(0,game.nextRound());
    }
    @Test
    public void canPlayerPlay(){
        Game game=new Game();
        game.players.add(new Player(1));
        assertTrue(game.canPlayerPlay(0));
        game.topCard="3S";
        game.deck.clear();
        game.players.get(0).setHand("3H");
        assertTrue(game.canPlayerPlay(0));
        game.players.get(0).setHand("4D");
        assertFalse(game.canPlayerPlay(0));
    }
    @Test
    public void cardExist(){
        Game game=new Game();
        game.players.add(new Player(1));
        game.topCard="3S";
        game.players.get(0).cards.add("5S");
        game.players.get(0).cards.add("2S");
        assertTrue(game.cardsExist(0,"5S,2S"));

    }
}
