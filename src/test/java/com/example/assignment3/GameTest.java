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
        game.deck.add("5S");
        game.deck.add("1S");
        game.deck.add("QS");
        game.deck.add("2S");
        assertEquals("5S",game.intializeTopCard());
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
        assertEquals(-1,game.direction);
        assertEquals(3,game.nextTurn());
        game.playCard("QD");
        assertEquals(1,game.nextTurn());
    }
    @Test
    public void getDeckSize(){
        game=new Game();
        assertEquals(52,game.getDeckSize());
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
        assertTrue(game.canPlay("2S"));
        assertFalse(game.canPlay("7D"));
        assertFalse(game.canPlay("2D,7D"));
        assertTrue(game.canPlay("7D,3D"));
        game.topCard="7H";
        assertFalse(game.canPlay("7D,3H"));

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

}
