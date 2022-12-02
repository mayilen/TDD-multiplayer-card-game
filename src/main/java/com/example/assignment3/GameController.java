package com.example.assignment3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;

@Controller
public class GameController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private Game game;
    public ArrayList<Player> players=new ArrayList<>();

    public String sendScores(){
        ArrayList<Integer> scores=new ArrayList<Integer>();
        for (Player p:
             players) {
            scores.add(p.score);
        }
        return scores.toString();
           }
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public int greet(HelloMessage m) throws Exception {
        Thread.sleep(500);
        game=new Game();
        game.players.add(new Player(m.getId()));
        if(players.size()>1){
            game.players.get(0).cards=game.dealHand();
            game.players.get(1).cards=game.dealHand();
            System.out.println("sfgsdfg");
            sendSpecific(players.get(0).getPlayerID(), game.players.get(0).cards.toString());
            sendSpecific(players.get(1).getPlayerID(), game.players.get(1).cards.toString());

            simpMessagingTemplate.convertAndSend("/topic/scores",sendScores());
        }
        return players.size();
    }

    @MessageMapping("/room")
    public void sendSpecific(@DestinationVariable int id,String msg) throws Exception {
        System.out.println(id);
        System.out.println(msg);
        Greeting out = new Greeting(msg);
        simpMessagingTemplate.convertAndSend( "/user/queue/specific-user/"+id, out);
    }

}
