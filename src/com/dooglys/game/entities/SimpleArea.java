package com.dooglys.game.entities;

import com.dooglys.game.interfaces.Area;
import com.dooglys.game.interfaces.Block;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class SimpleArea implements Area {

    private int x1,x2,y1,y2;
    private Block cells[][];

    public SimpleArea(File map){
        x1 = 0;
        y1 = 0;
        readMap(map);
    }

    //reads coords
    private void readMap(File map){
        Path file = map.toPath();
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String text = reader.readLine();
            String[] lines = text.split("\\s");

            x2 = Integer.parseInt(lines[0]);
            y2 = Integer.parseInt(lines[1]);
            cells = new Block[x2][y2];

            for(int i = 2; i<lines.length; i += 5){
                for(int j = i; j<(i+5); j++){
                    switch(lines[j]){
                        case "1":
                            cells[j-i][(i-2)/5] = new SimpleBlock((i-2)/5, j-i);
                            break;
                        default:
                            cells[j-i][(i-2)/5] = null;
                            break;
                    }
                }
            }

        } catch (IOException x) {
            System.err.println("Map file is wrong");
        }
    }

    @Override
    public ArrayList<Block> getBlocksList() {
        ArrayList<Block> blocks = new ArrayList<Block>();
        for(int i = 0; i<cells.length; i++){
            for(int j = 0; j<cells[i].length; j++){
                if(cells[i][j] != null)
                    blocks.add(cells[i][j]);
            }
        }

        return blocks;
    }

    @Override
    public Block getBlock(int x, int y) {
        return cells[x][y];
    }

    @Override
    public boolean alive() {
        //can not be killed
        return true;
    }

    @Override
    public void nextStep() {
        //does nothing
    }

    @Override
    public int getX1() {
        return x1;
    }

    @Override
    public int getY1() {
        return y1;
    }

    @Override
    public int getX2() {
        return x2;
    }

    @Override
    public int getY2() {
        return y2;
    }
}
