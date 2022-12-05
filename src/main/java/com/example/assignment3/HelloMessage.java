package com.example.assignment3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloMessage {

    private int id;
    public String card;
    public String suite;
    public String drew;


    @Override
    public String toString() {
        return super.toString();
    }

    public int getId() {
        return id;
    }


}
