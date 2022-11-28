package com.example.assignment3;

import java.util.*;

public class Game {
    public ArrayList<String> suit=new ArrayList<>(Arrays.asList("S","C","H","D"));
    public ArrayList<String> rank=new ArrayList<>(Arrays.asList("1","2","3","4","5","6","7","8","9","10","J","Q","K"));
    public ArrayList<String> deck = new ArrayList<>();
    public ArrayList<String> hand=new ArrayList<>();
    public Game(){
        deckInitializer();
    }
    public String topCard;
    public Boolean drewPlus2=false;
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
    public String intializeTopCard(){
        String drawcard=drawCard();
        while(drawcard.contains("2")||drawcard.contains("Q")||drawcard.contains("1")){
            deck.add(0,drawcard);
            drawcard=drawCard();
        }
        topCard=drawcard;
        return topCard;
    }
    public boolean changeSuit(String suite){
        topCard=getCardRank(topCard)+suite;
        return true;
    }
    public boolean canPlay(String c1){
       String[] cards= c1.split(",");
       Boolean verify;
       if(Objects.equals(getCardRank(topCard), "2")){
           if(cards.length==1&&Objects.equals(getCardRank(cards[0]), "2")){
               return true;
           }else if(cards.length!=1&&Objects.equals(getCardRank(cards[0]), "2")){
               return false;
           } else if (cards.length==1&&!Objects.equals(getCardRank(cards[0]), "2")) {
               return false;
           }

           for(int i=0;i<cards.length;i++){
           if(i==0){
            verify=Objects.equals(getCardSuit(cards[0]), getCardSuit(topCard)) ||(Objects.equals(getCardRank(cards[0]), getCardRank(topCard)));
           }else {
               verify=Objects.equals(getCardSuit(cards[i-1]), getCardSuit(cards[i])) ||(Objects.equals(getCardRank(cards[i-1]), getCardRank(cards[i])));
           }
           if(!verify) return false;
       }
       }else{
           if(cards.length==1) {
               return Objects.equals(getCardSuit(cards[0]), getCardSuit(topCard)) || (Objects.equals(getCardRank(cards[0]), getCardRank(topCard)));
           }else{
               return false;
           }

           }
        return true;
    }
    public boolean canDraw(){
        return getDeckSize()>0;
    }
    public String drawCard(){
        return deck.remove(deck.size()-1);
    }
    public String getCardSuit(String c){
        return c.substring(1);
    }
    public String getCardRank(String c){
        return String.valueOf(c.charAt(0));
    }
    public String playCard(String c){
        topCard=c;
        return topCard;
    }
    public int getDeckSize(){
        return deck.size();
    }
    public  ArrayList<String>  dealHand(){
        ArrayList<String> hand=new ArrayList<>();
        for(int i=0;i<5;i++){
            hand.add(drawCard());
        }
        return hand;
    }

    public void setHand(ArrayList<String> hand) {
        this.hand = hand;
    }

}
