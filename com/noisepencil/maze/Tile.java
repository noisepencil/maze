package com.noisepencil.maze;

public class Tile {

    //public class properties
    public int row = 0;
    public int col = 0;

    public boolean isWall = false;
    public boolean isStart = false;
    public boolean isEnd = false;

    public boolean isForkNorth = false;
    public boolean isForkEast = false;
    public boolean isForkSouth = false;
    public boolean isForkWest = false;

    public boolean beenNorth = false;
    public boolean beenEast = false;
    public boolean beenSouth = false;
    public boolean beenWest = false;

    // constructor
    public Tile(int row, int col, boolean isWall){
        this.row = row;
        this.col = col;
        this.isWall = isWall;
    }    
}