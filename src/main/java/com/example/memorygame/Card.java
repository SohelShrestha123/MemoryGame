package com.example.memorygame;

public class Card {
    private int number;
    private int index;
    private boolean isMatch;
    public boolean isFlipped;

    public Card(int number){
        this.number=number;
        this.isMatch=false;
        this.isFlipped=false;
    }

    public int getNumber(){
        return this.number;
    }

    public boolean isMatch(){
        return this.isMatch();
    }

    public void setMatch(boolean matched){
        this.isMatch=matched;
    }

    public boolean isFlipped(){
        return this.isFlipped();
    }

    public void setFlipped(boolean flipped){
        this.isFlipped=flipped;
    }
}
