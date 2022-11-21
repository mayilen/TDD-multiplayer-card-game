package com.example.assignment3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    @Test
    public void getSuit(){
        Game game = new Game();
        assertEquals(4,game.suit.size());
    }
    @Test
    public void getRank(){
        Game game = new Game();
        assertEquals(13,game.rank.size());
    }
}
