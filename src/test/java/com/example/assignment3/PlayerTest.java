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
        player.cards=game.dealHand();

        assertEquals(5,player.cards.size());
    }

    @Test
    public void setHandTest(){
    Game game = new Game();
    player = new Player(1);
    ArrayList<String> card=new ArrayList<>();
    player.cards=game.dealHand();
    card= new ArrayList<>(player.cards);
    player.setHand(new String[]{"KD"});
    assertNotEquals(card.size(),player.cards.size());

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
    @Test
    public void hasWon(){
        Game game=new Game();
        player = new Player(1);
        assertTrue(player.hasWon());
        player.cards.add("3S");
        assertFalse(player.hasWon());
    }
}
