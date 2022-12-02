package com.example.assignment3;

import java.util.*;

public class Game {
    public ArrayList<String> suit=new ArrayList<>(Arrays.asList("S","C","H","D"));
    public ArrayList<String> rank=new ArrayList<>(Arrays.asList("1","2","3","4","5","6","7","8","9","10","J","Q","K"));
    public ArrayList<String> deck = new ArrayList<>();
    public ArrayList<Player> players=new ArrayList<>();
    public Game(){
        deckInitializer();
    }
    public int direction=1;
    public int currentTurn=0;
    public int startRoundIndex=0;
    public boolean drew2=false;
    public String topCard;
    public int maxPlayer;
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
    public int nextTurn(){

        if(direction>0){
            currentTurn++;
        }else{
            currentTurn--;
        }
        if(currentTurn>maxPlayer){
            currentTurn=0;
        } else if (currentTurn<0) {
            currentTurn=maxPlayer;
        }
        return currentTurn;
    }
    public boolean canPlay(String c1){

       String[] cards= c1.split(",");
       Boolean verify;

       if(Objects.equals(getCardRank(topCard), "2")){

           if (cards.length==1&&!drew2) {
               return false;
           }else if(cards.length!=1&&drew2){
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
    public ArrayList<String> drawtwo(){
        ArrayList<String> drew=new ArrayList<>();
        if(deck.size()>1){
            drew.add(drawCard());
            drew.add(drawCard());
        }else{
            if(canDraw()){
                drew.add(drawCard());
            }
        }
        return drew;
    }
    public String getCardSuit(String c){
        return c.substring(1);
    }
    public String getCardRank(String c){
        return String.valueOf(c.charAt(0));
    }
    public String playCard(String c){
        topCard=c;
        if(Objects.equals(getCardRank(topCard), "1")) {
            direction=direction*-1;
        } else if (Objects.equals(getCardRank(topCard), "Q")) {
            nextTurn();
        }
        return topCard;
    }
    public int getDeckSize(){
        return deck.size();
    }
    public boolean canPlayerPlay(int p){
        if(deck.size()>1){
            return true;
        }else{
            for (String c:
                 players.get(p).cards) {
                if(canPlay(c)){
                    return true;
                }
            }
        }
        return false;
    }
    public  ArrayList<String>  dealHand(){
        ArrayList<String> hand=new ArrayList<>();
        for(int i=0;i<5;i++){
            hand.add(drawCard());
        }

        return hand;
    }
    public void setMaxPlayer(int p){
        maxPlayer=p;
    }

}

