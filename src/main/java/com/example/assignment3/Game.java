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
    public int skippedIndex=-1;
    public int currentTurn=0;
    public int startRoundIndex=0;
    public int plus2Count=1;
    public boolean drew2=false;
    public String topCard;
    public int maxPlayer=3;
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
        while(drawcard.contains("8")){

            deck.add( (int)(Math.random()*deck.size()),drawcard);
            drawcard=drawCard();
        }
        topCard=drawcard;
        return topCard;
    }
    public void restart(){
        players.clear();
        currentTurn=0;
        startRoundIndex=0;
       plus2Count=1;
        drew2=false;
        deckInitializer();
    }
    public int incrementPlus2(String c){
        if(Objects.equals(getCardRank(topCard), "2") && Objects.equals(getCardRank(c), "2")){
            plus2Count+=1;
        }else{
            plus2Count=1;
        }
        return plus2Count;
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
        if(currentTurn>3){
            currentTurn=0;
        } else if (currentTurn<0) {
            currentTurn=3;
        }
        return currentTurn;
    }
    public boolean isGameDone(){
        for(int i=0;i<players.size();i++){
            if(players.get(i).canPlay){
                return false;
            }
        }
        return true;
    }
    public int nextRound(){
        startRoundIndex++;

        if(startRoundIndex>3){
            startRoundIndex=0;
        } else if (startRoundIndex<0) {
            startRoundIndex=3;
        }
        currentTurn=startRoundIndex;
        return startRoundIndex;
    }
    public void calcScores(){
        for (Player p:players ) {
            p.scoreCalc();
        }
    }
    public boolean canPlay(String c1){

       String[] cards= c1.split(",");
       Boolean verify;

       if(Objects.equals(getCardRank(topCard), "2")){

           if (cards.length!=plus2Count*2&&!drew2) {
               return false;
           }else if(cards.length!=1&&drew2){
               return false;
           }
           HashSet<String> temp=new HashSet<>(List.of(cards));
           if(temp.size()!=cards.length){
               return false;
           }
           for(int i=0;i<cards.length;i++){
           if(i==0){
            verify=Objects.equals(getCardRank(cards[0]),"8")|| Objects.equals(getCardSuit(cards[0]), getCardSuit(topCard)) ||(Objects.equals(getCardRank(cards[0]), getCardRank(topCard)));
           }else {
               verify=Objects.equals(getCardRank(cards[i]),"8")||Objects.equals(getCardSuit(cards[i-1]), getCardSuit(cards[i])) ||(Objects.equals(getCardRank(cards[i-1]), getCardRank(cards[i])));
           }
           if(!verify) return false;
       }
       }else{
           if(cards.length==1) {
               return Objects.equals(getCardRank(cards[0]),"8")||Objects.equals(getCardSuit(cards[0]), getCardSuit(topCard)) || (Objects.equals(getCardRank(cards[0]), getCardRank(topCard)));
           }else{
               return false;
           }

           }
        return true;
    }
    public void removeFromPlayerHand(int p,String c){
        String[] card= c.split(",");
        players.get(p).cards.removeAll(List.of(card));
    }
    public void addToPlayerHand(int p,String c){
        String[] card= c.split(",");
        players.get(p).cards.addAll(List.of(card));
    }
    public boolean canDraw(){
        return getDeckSize()>0;
    }
    public String drawCard(){
        return deck.remove(deck.size()-1);
    }
    public ArrayList<String> drawtwo(){
        drew2=true;
        ArrayList<String> drew=new ArrayList<>();
        if(deck.size()>=2*plus2Count){
            for(int i=0;i<2*plus2Count;i++){
            drew.add(drawCard());
        }
        }else{
            if(canDraw()){
                drew.add(drawCard());
            }
        }
        return drew;
    }
    public String getCardSuit(String c){
        return c.substring(c.length()-1);
    }
    public String getCardRank(String c){
        if(c.length()==2) {
            return String.valueOf(c.charAt(0));
        }else{
            return "10";
        }
    }
    public String winnerIs(){
        int index=0;
        String s=null;
        for (int i=0;i<players.size();i++ ) {
            if(players.get(i).score>=100){
                int smallest = players.get(i).score;
                for (int j=0;j<players.size();j++ ) {
                     if (players.get(j).score < smallest) {
                     smallest = players.get(j).score;
                         index=j;
                     }
                }
               s= "Player "+(index+1)+" has Won!";
            }
        }
        return s;
    }
    public String playCard(String c){
        String[] cards= c.split(",");
        for(int i=0;i<cards.length;i++){
            incrementPlus2(cards[i]);
        topCard=cards[i];
        if(Objects.equals(getCardRank(cards[i]),"2")){
            drew2=false;
        }
        if(Objects.equals(getCardRank(topCard), "1")) {
            direction=direction*-1;
        } else if (Objects.equals(getCardRank(topCard), "Q")) {
            skippedIndex();
            nextTurn();
        }
        }
        return topCard;
    }
    public void skippedIndex(){
        int skipped=currentTurn;
        if(direction>0){
            skipped++;
        }else{
            skipped--;
        }
        if(skipped>3){
            skipped=0;
        } else if (skipped<0) {
            skipped=3;
        }
        skippedIndex=skipped;
    }
    public int getDeckSize(){
        return deck.size();
    }
    public boolean cardsExist(int p,String c){
        String[] cards= c.split(",");
        return (players.get(p).cards.containsAll(List.of(cards)));
    }
    public void newRound(){
        deckInitializer();
        direction=1;
        skippedIndex=-1;
        for (Player p: players) {
            p.cards=dealHand();
            p.canPlay=true;
            p.skipped=false;
            p.drew="";
        }
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

