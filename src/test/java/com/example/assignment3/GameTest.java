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
    public void restart(){
        game = new Game();
        game.players.add(new Player(1));
        assertEquals(1,game.players.size());
        game.restart();
        assertEquals(0,game.players.size());
    
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
        game.topCard="3S";
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
        game.plus2Count=1;
        game.topCard="2S";
        game.deck.add("3S");
        game.deck.add("2H");
        game.deck.add("5D");
        ArrayList<String> cards=game.drawtwo();
        assertTrue(game.canPlay(cards.get(1)));
        game.playCard(cards.get(1));
        assertEquals(2,game.plus2Count);


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
        assertEquals("10",game.getCardRank("10S"));

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
        assertFalse(game.canPlay("8S,1D"));
       // game.playCard("1D");
        assertEquals(1,game.currentTurn);
        assertEquals(-1,game.direction);
        assertEquals(0,game.nextTurn());
        game.playCard("3D");
        assertEquals(-1,game.direction);
        assertEquals(3,game.nextTurn());
        assertEquals(2,game.nextTurn());
        assertEquals(1,game.nextTurn());
        assertEquals(0,game.nextTurn());
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
    public void scoreCalc() {
        game = new Game();
        game.players.add(new Player(1));
        game.players.get(0).setHand("3H");
        game.calcScores();
        assertEquals(3,game.players.get(0).score);
    }
    @Test
    public void addToPlayerHand(){
        game=new Game();
        game.players.add(new Player(1));
        game.players.get(0).setHand("3H,7D,9H");
        game.addToPlayerHand(0,"9D");
        assertEquals(4,game.players.get(0).cards.size());
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
    public void testtwos(){
        game=new Game();
        game.topCard="3H";
        game.playCard("2D");
        assertEquals(1,game.plus2Count);
        assertFalse(game.drew2);
        game.playCard("2S,4S");
        assertEquals(1,game.plus2Count);
        game.playCard("2S");
        game.drew2=true;
        game.playCard("2S");
        assertEquals(2,game.plus2Count);
        assertEquals(4,game.drawtwo().size());
    }
    @Test
    public void winnerIs(){
        Game game=new Game();
        game.players.add(new Player(1));
        game.players.add(new Player(2));
        game.players.get(0).score=100;
        game.players.get(1).score=6;
        assertEquals("Player 2 has Won!",game.winnerIs());
        game.players.get(0).score=8;
       assertNull(game.winnerIs());
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
        assertFalse(game.canPlay("7D,3D,9D"));
        assertFalse(game.canPlay("7D,7D"));
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
       assertEquals(2,game.nextTurn());
       assertEquals(3,game.nextTurn());

   }
    @Test
    public void skippedIndex(){
        Game game=new Game();
        game.topCard="QC";
        game.playCard("QD");
        assertEquals(1,game.skippedIndex);
        assertEquals(2,game.nextTurn());
    }
    @Test
    public void newRound(){
        Game game=new Game();
        game.players.add(new Player(1));
        game.players.get(0).canPlay=false;
        game.newRound();
        assertTrue(game.players.get(0).canPlay);
    }
    @Test
    public void isGameDone(){
        Game game=new Game();
        game.players.add(new Player(1));
        game.players.add(new Player(2));
        game.topCard="9H";
        game.players.get(0).setHand("3C");
        game.players.get(1).setHand("4H");
        game.players.get(0).canPlay=game.canPlayerPlay(0);
        game.players.get(1).canPlay=game.canPlayerPlay(1);
        assertTrue(game.players.get(0).canPlay);
        assertTrue(game.players.get(1).canPlay);
        assertFalse(game.isGameDone());
        game.deck.clear();
        game.players.get(0).canPlay=game.canPlayerPlay(0);
        game.players.get(1).canPlay=game.canPlayerPlay(1);

        assertFalse(game.players.get(0).canPlay);
        assertTrue(game.players.get(1).canPlay);
        assertFalse(game.isGameDone());
        game.players.get(1).setHand("4D");
        game.players.get(0).canPlay=game.canPlayerPlay(0);
        game.players.get(1).canPlay=game.canPlayerPlay(1);
        assertFalse(game.players.get(0).canPlay);
        assertFalse(game.players.get(1).canPlay);
        assertTrue(game.isGameDone());
    }

    @Test
    public void nextRound(){
        Game game=new Game();

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
