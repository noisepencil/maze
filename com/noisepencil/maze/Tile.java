package com.noisepencil.maze;

public class Tile {

    //public class properties
    public int row = 0;
    public int col = 0;

    public boolean isWall = false;
    public boolean isStart = false;
    public boolean isEnd = false;
    
    // constructor
    public Tile(int row, int col, boolean isWall){
        this.row = row;
        this.col = col;
        this.isWall = isWall;
    }

}