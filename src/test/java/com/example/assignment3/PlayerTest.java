package com.example.assignment3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player player;

    @Test
    public void getPlayerID(){
         player = new Player(1);
        assertEquals(1,player.getPlayerID());
    }
    @Test
    public void dealHand()
    {
        player = new Player(1);
        Game game=new Game();
         ArrayList<String> cards=new ArrayList<>();
        cards=player.drawIntialHand(game);
        assertEquals(cards,player.cards);
    }
    @Test
    public void drawCard()
    {
        player = new Player(1);
        Game game=new Game();

        ArrayList<String> cards=new ArrayList<>();
        cards=player.drawIntialHand(game);
        game.topCard="5S";
        player.drawCard(game);
        assertEquals(6,cards.size());
        game.topCard="2S";
        assertEquals(cards,player.cards);
        player.drawCard(game);
        assertEquals(8,cards.size());
    }
}
