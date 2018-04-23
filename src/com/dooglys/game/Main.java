package com.dooglys.game;

import java.io.File;

public class Main {

    public static void main(String[] args){

        File area = new File("./map.txt");
        File commands = new File("./commands.txt");
        GameEngine engine = new GameEngine(area, commands);
        engine.start();
    }
}
