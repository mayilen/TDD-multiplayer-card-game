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
        g.game.players=new ArrayList<>();
        g.game.players.add(new Player(1));
        g.game.players.get(0).score=2;
        assertEquals("[2]",g.sendScores());
    }
}
