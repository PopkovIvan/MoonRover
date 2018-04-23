package com.dooglys.game.interfaces;

public interface Entity {

    boolean alive();//true if alive
    void nextStep();//smth to do every step
    int getX1();//coords
    int getY1();
    int getX2();
    int getY2();
}
