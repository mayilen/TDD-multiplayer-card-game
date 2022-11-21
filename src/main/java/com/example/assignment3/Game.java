package com.example.assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Game {
    public Game(){
    }
    public Set<String> suit=new HashSet<>(Arrays.asList("S","C","H","D"));
    public Set<String> rank=new HashSet<>(Arrays.asList("1","2","3","4","5","6","7","8","9","10","J","Q","K"));
    public ArrayList<String> deck = new ArrayList<>();


}
