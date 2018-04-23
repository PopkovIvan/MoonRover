package com.dooglys.game.entities;

import com.dooglys.game.interfaces.Actor;
import com.dooglys.game.interfaces.Area;
import com.dooglys.game.interfaces.Block;

public class MoonRover implements Actor {

    private boolean alive;
    private int x,y;
    private String turn;

    public MoonRover(){
        //defaults
        x = 0;
        y = 0;
        turn = "SOUTH";
        alive = true;
    }

    @Override
    public void moveTo(int x, int y, Area area) {

        //got out of the area
        if(((x < area.getX1())||(x > area.getX2())) || ((y < area.getY1())||(y < area.getY2()))){
            alive = false;
            return;
        }

        //faces block
        Block block = area.getBlock(x, y);
        if(block != null){
            this.face(block);//block hurts MoonRover
            block.smash(this);//and MoonRover hurts block
            return;
        }

        //if everything is ok
        this.x = x;
        this.y = y;
    }

    @Override
    public void turnTo(String turn) {
        this.turn = turn;
    }

    @Override
    public void face(Block block) {
        if(block instanceof SimpleBlock) {
            alive = false;
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

    @Override
    public String toString(){
        return String.format("%d %d %s", y, x, turn);
    }
}
