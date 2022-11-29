package com.example.assignment3;

import java.util.ArrayList;

public class Player {
    public int playerID;
    public Game g;
    public ArrayList<String> cards=new ArrayList<>();
    public Player(int id,Game game){
        playerID=id;
        g=game;
    }
public ArrayList<String> drawIntialHand(){
        cards=g.dealHand();
        return cards;
}
public ArrayList<String> drawCard(){
    ArrayList<String> drew=new ArrayList<>();
    String card;
        if(g.topCard.charAt(0)=='2'&&g.getDeckSize()>1){
            card=g.drawCard();
            cards.add(card);
            drew.add(card);
            card=g.drawCard() ;
            cards.add(card);
            drew.add(card);
        }else{
            card=g.drawCard();
            cards.add(card);
            drew.add(card);
        }

        return drew;

}
public int getPlayerID(){
        return playerID;
}
}
