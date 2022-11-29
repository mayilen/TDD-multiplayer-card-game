package com.example.assignment3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    private int counter=-1;
    private Game game;
    public ArrayList<Player> players=new ArrayList<>();
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greet(HelloMessage m) throws Exception {
        Thread.sleep(1000);
        game=new Game();
        players.add(new Player(m.getId()));
        if(players.size()>1){
            System.out.println("sfgsdfg");

            sendSpecific(players.get(0).getPlayerID(), players.get(0).drawIntialHand(game).toString());
            sendSpecific(players.get(1).getPlayerID(), players.get(1).drawIntialHand(game).toString());
        }
        return new Greeting(""+HtmlUtils.htmlEscape(""+m.getId()));
    }

    @MessageMapping("/room")
    public void sendSpecific(@DestinationVariable int id,String msg) throws Exception {
        System.out.println(id);
        System.out.println(msg);
        Greeting out = new Greeting(msg);
        simpMessagingTemplate.convertAndSend( "/user/queue/specific-user/"+id, out);
    }

}
