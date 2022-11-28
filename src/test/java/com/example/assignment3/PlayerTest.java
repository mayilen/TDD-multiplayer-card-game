package com.example.assignment3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player player;

    @Test
    public void getPlayerID(){
         player = new Player(1);
        assertEquals(1,player.getPlayerID());
    }
}
