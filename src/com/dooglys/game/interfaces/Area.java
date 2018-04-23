package com.dooglys.game.interfaces;

import java.util.ArrayList;

public interface Area extends Entity {
    ArrayList<Block> getBlocksList();//get list of all blocks
    Block getBlock(int x, int y);//returns null, if there is no block
}
