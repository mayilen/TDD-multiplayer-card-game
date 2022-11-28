package com.example.assignment3;

import java.util.ArrayList;

public class Player {
    public int playerID;
    public ArrayList<String> cards=new ArrayList<>();
    public Player(int id){
        playerID=id;
    }
public ArrayList<String> drawIntialHand(Game g){
        cards=g.dealHand();
        return cards;
}
public ArrayList<String> drawCard(Game g){
        if(g.topCard.charAt(0)=='2'){
            cards.add(g.drawCard());
            cards.add(g.drawCard());
        }else{
            cards.add(g.drawCard());
        }
        return cards;

}
public int getPlayerID(){
        return playerID;
}
}
