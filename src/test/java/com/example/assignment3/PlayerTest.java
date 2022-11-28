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
        String c=player.drawIntialHand(game);
        assertEquals(c,player.cards.toString());
    }
}
