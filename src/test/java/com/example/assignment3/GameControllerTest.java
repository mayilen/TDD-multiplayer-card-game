package com.example.assignment3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameControllerTest
{
    Game game;
    @Test
    public void getScoresTest(){
        GameController g = new GameController();
        g.players=new ArrayList<>();
        g.players.add(new Player(1));
        g.players.get(0).score=2;
        assertEquals("[2]",g.sendScores());
    }
}
