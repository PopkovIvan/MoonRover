package com.dooglys.game;

import com.dooglys.game.entities.MoonRover;
import com.dooglys.game.entities.SimpleArea;
import com.dooglys.game.interfaces.Actor;
import com.dooglys.game.interfaces.Area;
import com.dooglys.game.interfaces.Block;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.*;

public class GameEngine {

    private Actor actor;
    private Area area;
    private ArrayList<String> commands;

    GameEngine(File area, File actor){
        this.actor = new MoonRover();
        this.area = new SimpleArea(area);
        commands = new ArrayList<String>();

        Path file = actor.toPath();
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                commands.add(line);
            }
        } catch (IOException x) {
            System.err.println("Commands file is wrong");
        }
    }

    //parse commands
    private void commandActor(String command){
        String[] parts = command.split("\\s");
        switch (parts[0]){
            case "MOVE":
                actor.moveTo(Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), area);
                break;
            case "TURN":
                actor.turnTo(parts[1]);
                break;
            default:
                //in case of error does nothing
                break;
        }
    }

    //make smth each step
    private void makeNextStep(){
        area.nextStep();
        ArrayList<Block> blocks = area.getBlocksList();
        for(Block block: blocks)
            block.nextStep();
    }

    //form reports.txt with message report
    private void makeReport(String report){
        byte data[] = report.getBytes();
        Path p = Paths.get("./report.txt");

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p, CREATE, APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    //main game loop
    public int start(){
        for(String command: commands){
            commandActor(command);
            makeNextStep();
            if(!actor.alive()){
                makeReport("last " + actor.toString() + " killed");
                return 1;
            }
        }

        makeReport(actor.toString());
        return 0;
    }
}
