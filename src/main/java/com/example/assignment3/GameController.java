package com.example.assignment3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Controller
public class GameController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public Game game=new Game();

    public String sendScores(){
        ArrayList<Integer> scores=new ArrayList<Integer>();
        for (Player p:
             game.players) {
            scores.add(p.score);
        }
        return scores.toString();
           }
    @MessageMapping("/play")
    //@SendTo("/topic/greetings")
    public void play(HelloMessage m) throws Exception{
        System.out.println(m.getCard());
        Greeting g=new Greeting();
        if(game.cardsExist(game.currentTurn,m.getCard())){
            if(game.canPlay(m.getCard())){
                game.playCard(m.getCard());
                simpMessagingTemplate.convertAndSend("/topic/topcard",new Message(game.topCard));
                game.removeFromPlayerHand(game.currentTurn,m.getCard());
                if(m.getSuite()!=null) {
                    game.changeSuit(m.getSuite());
                }
                g.card=game.players.get(game.currentTurn).cards.toString();
                sendSpecific(game.players.get(game.currentTurn).getPlayerID(),g);
                playerTurn(game.nextTurn());
            }else{
                g.error="Invalid Input";
                sendSpecific(game.players.get(game.currentTurn).getPlayerID(),g);
            }

        }else{

            g.error="You do not have those cards";
            sendSpecific(game.players.get(game.currentTurn).getPlayerID(),g);
            System.out.println("invalid");
        }

        System.out.println(m.getCard()+" "+m.getSuite());

    }
    @MessageMapping("/draw")
    public void draw() throws Exception {
        if(game.canDraw()){
            if(Objects.equals(game.getCardRank(game.topCard), "2")){
                ArrayList<String> drew=game.drawtwo();
                for (String s : drew) {
                    if (game.canPlay(s)) {
                        HelloMessage m = new HelloMessage();
                        m.card = s;
                        play(m);
                        return;
                    }
                }
            }
                for(int i=0;i<3;i++){
                    if(game.canDraw()){
                        String card=game.drawCard();
                        if(game.canPlay(card)){
                            HelloMessage m = new HelloMessage();
                            m.card = card;
                            play(m);
                            return;
                        }
                    }
                }


        }
    }
    public void playerTurn(int p) throws Exception {
        for(int i=0;i<game.players.size();i++){
            if(i!=p){
                Greeting g=new Greeting();
                g.player=i+1;
                g.card=game.players.get(i).cards.toString();
                g.playerTurn="Player "+(p+1)+"'s Turn";
                sendSpecific(game.players.get(i).getPlayerID(),g);
            }else{
                Greeting g=new Greeting();
                g.player=i+1;
                g.card=game.players.get(i).cards.toString();
                g.playerTurn="Your Turn";
                sendSpecific(game.players.get(i).getPlayerID(),g);

            }
        }
    }
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public int greet(HelloMessage m) throws Exception {
        Thread.sleep(500);

        game.players.add(new Player(m.getId()));

        if(game.players.size()>3){
            game.setMaxPlayer(4);
            game.players.get(0).cards=game.dealHand();
            game.players.get(1).cards=game.dealHand();
            game.players.get(2).cards=game.dealHand();
            game.players.get(3).cards=game.dealHand();
            System.out.println("sfgsdfg");
            playerTurn(game.currentTurn);
            simpMessagingTemplate.convertAndSend("/topic/deck",new Message(""+game.getDeckSize()));
            simpMessagingTemplate.convertAndSend("/topic/scores",sendScores());
         simpMessagingTemplate.convertAndSend("/topic/topcard", new Message( game.intializeTopCard()));
        }
        return game.players.size();
    }
    @MessageMapping("/room")
    public void sendSpecific(@DestinationVariable int id,Greeting msg) throws Exception {
        System.out.println(id);
        System.out.println(msg);
        simpMessagingTemplate.convertAndSend( "/user/queue/hand/"+id, msg);
    }
}
