package com.example.assignment3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Greeting {
    public int player;
    public String error;
    public String card;
    public String playerTurn;

}
