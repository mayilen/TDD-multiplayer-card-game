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
}
public int getPlayerID(){
        return playerID;
}
}
