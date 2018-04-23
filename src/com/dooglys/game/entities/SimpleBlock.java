package com.dooglys.game.entities;

import com.dooglys.game.interfaces.Actor;
import com.dooglys.game.interfaces.Block;

public class SimpleBlock implements Block {

    private int x,y;
    private boolean alive;

    public SimpleBlock(int x, int y){
        alive = true;
        this.x = x;
        this.y = y;
    }

    @Override
    public void smash(Actor actor) {
        if(actor instanceof MoonRover) {
            //MoonRover cann't hurt SimpleBlock
            return;
        }
    }

    @Override
    public boolean alive() {
        return alive;
    }

    @Override
    public void nextStep() {
        //does nothing
    }

    @Override
    public int getX1() {
        return x;
    }

    @Override
    public int getY1() {
        return y;
    }

    @Override
    public int getX2() {
        return x;
    }

    @Override
    public int getY2() {
        return y;
    }
}
