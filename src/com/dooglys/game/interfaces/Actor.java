package com.dooglys.game.interfaces;

public interface Actor extends Entity {

    void moveTo(int x, int y, Area area);//move for (x,y) in area
    void turnTo(String turn);//turn the direction
    void face(Block block);//do smth when face the block
}
