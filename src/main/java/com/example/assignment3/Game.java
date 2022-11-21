package com.example.assignment3;

import java.util.*;

public class Game {
    public Game(){
    }
    public ArrayList<String> suit=new ArrayList<>(Arrays.asList("S","C","H","D"));
    public ArrayList<String> rank=new ArrayList<>(Arrays.asList("1","2","3","4","5","6","7","8","9","10","J","Q","K"));
    public ArrayList<String> deck = new ArrayList<>();
    public ArrayList<String> deckInitializer(){
        deck.clear();
        for(int i=0;i<rank.size();i++){
            for(int s=0;s<suit.size();s++) {
                deck.add(rank.get(i)+suit.get(s));
            }
        }
        Collections.shuffle(deck);
        return deck;
    }

}
