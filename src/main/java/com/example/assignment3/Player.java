package com.example.assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Player {
    public int playerID;
    public int score=0;
    Game game=new Game();
    public ArrayList<String> cards=new ArrayList<>();
    public Player(int id){
        playerID=id;

    }
    public int scoreCalc(){
        int c=0;
        for (String card:cards
             ) {
            if(Objects.equals(game.getCardRank(card), "8")){
                c+=50;
            } else if (Objects.equals(game.getCardRank(card), "J")||Objects.equals(game.getCardRank(card), "Q")||Objects.equals(game.getCardRank(card), "K")) {
                c+=10;
            }else {
                c += Integer.parseInt(game.getCardRank(card));
            }
        }
        score+=c;
        return c;
    }
    public Boolean hasWon(){
        return cards.size()==0;
    }
public void setHand(String c){
    String[] c2= c.split(",");
    cards.clear();
    cards.addAll(Arrays.asList(c2));
}
public int getPlayerID(){
        return playerID;
}
}
