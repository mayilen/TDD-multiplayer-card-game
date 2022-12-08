package com.example.assignment3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class GameController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public static Game game = new Game();

    public  String sendScores() {
        ArrayList<Integer> scores = new ArrayList<Integer>();
        for (Player p :
                game.players) {
            scores.add(p.score);
        }
        simpMessagingTemplate.convertAndSend("/topic/scores", scores.toString());

        return scores.toString();
    }
    @MessageMapping("/change")
    public void changeSuite(HelloMessage m) throws Exception {
        System.out.println(m.card);
        System.out.println(m.suite);
        play(m);
    }
    @MessageMapping("/refresh")
    public void refresh() throws Exception {
        playerTurn(game.currentTurn);
        simpMessagingTemplate.convertAndSend("/topic/topcard", new Message(game.topCard));
    }
    @MessageMapping("/play")
    //@SendTo("/topic/greetings")
    public void play(HelloMessage m) throws Exception {
        System.out.println("card to play: "+m.getCard());
        System.out.println("top card is: " + game.topCard);
        Greeting g = new Greeting();
        int playerIndex = game.currentTurn;
        System.out.println(game.players.get(game.currentTurn).cards.toString());
        if (game.cardsExist(playerIndex, m.getCard())) {
            if (game.canPlay(m.getCard())) {
                game.playCard(m.getCard());
                game.removeFromPlayerHand(playerIndex, m.getCard());
                if (m.getSuite() != null) {
                    game.changeSuit(m.getSuite());
                }
                simpMessagingTemplate.convertAndSend("/topic/topcard", new Message(game.topCard));
                System.out.println("Diretion " + game.direction);
                if (game.players.get(playerIndex).hasWon()) {
                    game.calcScores();
                   // simpMessagingTemplate.convertAndSend("/topic/winner", new Message("Player" + (playerIndex + 1 )+ " has Won!"));
                    sendScores();
                    newRound();
                }
                System.out.println("new top card is: " + game.topCard);

                playerTurn(game.nextTurn());
                System.out.println("Diretion after nextTurn " + game.direction);

            } else {
                g.error = "Invalid Input";
                sendSpecific(game.players.get(game.currentTurn).getPlayerID(), g);
            }

        } else {

            g.error = "You do not have those cards";
            sendSpecific(game.players.get(game.currentTurn).getPlayerID(), g);
            System.out.println("invalid");
        }


    }

    @MessageMapping("/draw")
    public void draw() throws Exception {
        Greeting g = new Greeting();
        if (game.canDraw()) {

            if (Objects.equals(game.getCardRank(game.topCard), "2")) {
                ArrayList<String> drew = game.drawtwo();
                String card = drew.stream().map(Object::toString)
                        .collect(Collectors.joining(","));
                game.players.get(game.currentTurn).drew = card;
                game.addToPlayerHand(game.currentTurn, card);

                for (String s : drew) {
                    if (game.canPlay(s)) {
                        System.out.println(s);
                        game.players.get(game.currentTurn).drew += " & played " + s;
                        HelloMessage m = new HelloMessage();
                        m.card = s;
                        if(Objects.equals(game.getCardRank(s), "8")){
                            g.suite=true;
                            g.card = game.players.get(game.currentTurn).cards.toString();
                            g.cardToPlay=s;
                            g.drew=game.players.get(game.currentTurn).drew;
                            sendSpecific(game.players.get(game.currentTurn).getPlayerID(), g);
                        }else{

                        play(m);}
                        return;
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                if (game.canDraw()) {
                    String card = game.drawCard();
                    simpMessagingTemplate.convertAndSend("/topic/deck", new Message("" + game.getDeckSize()));
                    if (game.players.get(game.currentTurn).drew.isBlank()) {
                        game.players.get(game.currentTurn).drew += card;
                    } else {
                        game.players.get(game.currentTurn).drew += "," + card;
                    }
                    game.addToPlayerHand(game.currentTurn, card);
                    if (game.canPlay(card)) {
                        game.players.get(game.currentTurn).drew += " & played " + card;
                        HelloMessage m = new HelloMessage();
                        m.card = card;
                        if(Objects.equals(game.getCardRank(card), "8")){
                            g.suite=true;
                            g.cardToPlay=card;
                            g.card = game.players.get(game.currentTurn).cards.toString();
                            g.drew=game.players.get(game.currentTurn).drew;
                            sendSpecific(game.players.get(game.currentTurn).getPlayerID(), g);
                        }else{

                            play(m);}
                        return;
                    }
                }
            }
            game.players.get(game.currentTurn).drew +=" & can't play";
            playerTurn(game.nextTurn());
        }
    }

    public  void newRound() throws Exception {
        game.newRound();
        playerTurn(game.nextRound());
        simpMessagingTemplate.convertAndSend("/topic/deck", new Message("" + game.getDeckSize()));
        simpMessagingTemplate.convertAndSend("/topic/topcard", new Message(game.intializeTopCard()));

    }

    public  void  playerTurn(int p) throws Exception {
        String direction;
        if (game.isGameDone()) {
            game.calcScores();
            simpMessagingTemplate.convertAndSend("/topic/winner", new Message("Game Ended"));
            sendScores();
            newRound();
            return;
        }
        if (!game.canPlayerPlay(p)) {
            game.players.get(p).canPlay = false;
            playerTurn(game.nextTurn());
            return;
        }
        if (game.direction == 1) {
            direction = "To the Left";
        } else {
            direction = "To the Right";
        }
        simpMessagingTemplate.convertAndSend("/topic/direction", new Message(direction));
        System.out.println("p is: " + p);
        if (game.skippedIndex != -1) {
            game.players.get(game.skippedIndex).skipped = true;
        }
        for (int i = 0; i < game.players.size(); i++) {
            if (i != p) {
                Greeting g = new Greeting();
                g.player = i + 1;
                g.card = game.players.get(i).cards.toString();
                g.drew = game.players.get(i).drew;
                g.skipped = game.players.get(i).skipped;
                g.playerTurn = "Player " + (p + 1) + "'s Turn";
                sendSpecific(game.players.get(i).getPlayerID(), g);
                game.players.get(i).drew = "";
                game.players.get(i).skipped = false;
                game.skippedIndex = -1;
            } else {
                Greeting g = new Greeting();
                g.player = i + 1;
                g.card = game.players.get(i).cards.toString();
                g.drew = game.players.get(i).drew;
                g.skipped = game.players.get(i).skipped;
                g.playerTurn = "Your Turn";
                sendSpecific(game.players.get(i).getPlayerID(), g);
                game.players.get(i).drew = "";
                game.players.get(i).skipped = false;
                game.skippedIndex = -1;
            }
        }
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public int greet(HelloMessage m) throws Exception {
       // Thread.sleep(500);

        game.players.add(new Player(m.getId()));

        if (game.players.size() > 3) {
           // game.deckInitializer();
            game.setMaxPlayer(3);
            game.players.get(0).cards = game.dealHand();
            game.players.get(1).cards = game.dealHand();
            game.players.get(2).cards = game.dealHand();
            game.players.get(3).cards = game.dealHand();

            playerTurn(game.currentTurn);
            simpMessagingTemplate.convertAndSend("/topic/deck", new Message("" + game.getDeckSize()));
            sendScores();
            game.nextRound();
            simpMessagingTemplate.convertAndSend("/topic/topcard", new Message(game.intializeTopCard()));
        }
        return game.players.size();
    }

    @MessageMapping("/room")
    public  void sendSpecific(@DestinationVariable int id, Greeting msg) throws Exception {
        System.out.println(msg);
        simpMessagingTemplate.convertAndSend("/user/queue/hand/" + id, msg);
    }
}
