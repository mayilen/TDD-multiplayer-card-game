package com.example.assignment3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player player;

    @Test
    public void getPlayerID(){
        Game game=new Game();
         player = new Player(1);
        assertEquals(1,player.getPlayerID());
    }
    @Test
    public void dealHand()
    {
        Game game=new Game();
        player = new Player(1);

         ArrayList<String> cards=new ArrayList<>();
        cards=player.drawIntialHand(game);
        assertEquals(cards,player.cards);
    }
    @Test
    public void drawCard()
    {
        Game game=new Game();
        player = new Player(1);


        ArrayList<String> cards=new ArrayList<>();
        cards=player.drawIntialHand(game);
        game.topCard="5S";
        assertEquals(1,player.drawCard(game).size());

        assertEquals(6,cards.size());
        game.topCard="2S";
        assertEquals(cards,player.cards);
        assertEquals(2,player.drawCard(game).size());

        assertEquals(8,cards.size());
    }
    @Test
    public void playCard()
    {
        Game game=new Game();
        player = new Player(1);


        ArrayList<String> cards=new ArrayList<>();
        player.cards.add("4S");
        player.cards.add("7H");

        game.topCard="5S";
        assertTrue(player.playCards("4S",game));
        assertFalse(player.playCards("7H",game));
        player.cards.add("5H");
        game.topCard="2H";
       assertTrue(player.playCards("7H,5H",game));
        assertFalse(player.playCards("7H",game));
    }
@Test
    public void scoreCalc(){
    Game game=new Game();
    player = new Player(1);
    player.cards.add("8S");
    assertEquals(50,player.scoreCalc());
    player.cards.add("QS");
    assertEquals(60,player.scoreCalc());
    player.cards.add("KS");
    assertEquals(70,player.scoreCalc());
    player.cards.add("JS");
    assertEquals(80,player.scoreCalc());
    player.cards.add("5S");
    assertEquals(85,player.scoreCalc());
}
}
