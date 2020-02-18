package com.noisepencil.maze;

import java.util.ArrayList;

public class Maze {
    public final int MAX_STEPS = 40;
    public Tile[][] tiles;
    public int numRows = 0;
    public int numCols= 0;

    public int startRow = 0;
    public int startCol = 0;
    public int endRow = 0;
    public int endCol = 0;

    public enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST,
        UNKNOWN
    }
    
    public Direction direction = Direction.UNKNOWN;
    public Direction cameFromDirection = Direction.UNKNOWN;

    public Tile curTile;
    public Tile prevTile;
    public int steps = 0;
    public Tile lastIntersection;

    public ArrayList<Tile> path;

    public Maze(int[][] mazeData, int startRow, int startCol, int endRow, int endCol){
        numRows = mazeData.length;
        numCols = mazeData[0].length;

        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
        
        tiles = new Tile[numRows][numCols];

        // load data into tile classes
        for (int row = 0; row < numRows; row++){
            for (int col = 0; col < numCols; col++){
                
                Tile tile = new Tile(row, col, (mazeData[row][col] == 0));
                if (row == startRow && col == startCol) {
                    tile.isStart = true;
                } else if (row == endRow && col == endCol) {
                    tile.isEnd = true;
                }
                tiles[row][col] = tile;  
                // 
            }
        }
    }

    public void walkMaze() {

        path = new ArrayList<Tile>();

        curTile = tiles[this.startRow][this.startCol];
        prevTile = curTile;
        
        printTileData(curTile);

        while (!amIatTheEnd(curTile) && (steps <= MAX_STEPS)) {
            makeAMove(curTile);
            printTileData(curTile);
        }
        System.out.println("");
    }

    public void makeAMove(Tile tile) {

        if (isIntersection(tile)) {
            lastIntersection = tile;
        }

        if (canIGoNorth(tile)) {
            direction = Direction.NORTH;
            curTile.beenNorth = true;
            prevTile = curTile;
            curTile = tileNorth(tile);
            curTile.beenSouth = true;
            System.out.println("moving north");

        } else if (canIGoEast(tile)) {
            direction = Direction.EAST;
            curTile.beenEast = true;
            prevTile = curTile;
            curTile = tileEast(tile);
            curTile.beenWest = true;
            System.out.println("moving east");


        }else if (canIGoSouth(tile)) {
            direction = Direction.SOUTH;
            curTile.beenSouth = true;
            prevTile = curTile;
            curTile = tileSouth(tile);
            curTile.beenNorth = true;
            System.out.println("moving south");


        } else if (canIGoWest(tile)){
            direction = Direction.WEST;
            curTile.beenWest = true;
            prevTile = curTile;
            curTile = tileWest(tile);
            curTile.beenEast = true;
            System.out.println("moving west");

        } else {
            // if I get here I am at a dead end, I know this since all of the canIGoDirection functions
            // prohibit me from backtracking as well as hitting walls..
            // so its time to backtrack! 
            // how far back do I need to go??
            // I need to keep my path taken so far (in an array of tiles)?
            // then walk back until I hit the last interection
            // then take a fork i hadnt taken yet
            System.out.println("Dead end! backing up to last intersection");
            prevTile = curTile;
            curTile = lastIntersection;
        }

        steps++;
    }
    
    // are there more than one paths out of tile?
    public boolean isIntersection(Tile tile) {
        
        int count = 0;

        Tile neighbor = tileNorth(tile);
        if (neighbor != null && !neighbor.isWall) count++;

        neighbor = tileEast(tile);
        if (neighbor != null && !neighbor.isWall) count++;

        neighbor = tileSouth(tile);
        if (neighbor != null && !neighbor.isWall) count++;

        neighbor = tileWest(tile);
        if (neighbor != null && !neighbor.isWall) count++;

        return (count > 2);

    }

    public boolean amIatTheEnd(Tile tile) {
        if (tile.row == this.endRow && tile.col == this.endCol) {
            System.out.println("I'm alive!!!!");
            return true;
        }
        return false;
    }

    public boolean canIGoNorth(Tile tile) {

        if (tile.beenNorth) {
            return false;
        }

        Tile tileNorth = tileNorth(tile);
        if (tileNorth == null) {
            return false;
        }

        if (tileNorth.isWall || tileNorth == prevTile) {
            return false;
        }

        return true;
    }

    public boolean canIGoEast(Tile tile) {
        if (tile.col == numCols-1) {
            return false;
        }

        if (tile.beenEast) {
            return false;
        }

        Tile tileEast = tileEast(tile);

        if (tileEast.isWall || tileEast == prevTile) {
            return false;
        }

        return true;
    }

    public boolean canIGoSouth(Tile tile) {
        if (tile.row == numRows-1) {
            return false;
        }

        if (tile.beenSouth) {
            return false;
        }

        Tile tileSouth = tileSouth(tile);
       
        if (tileSouth.isWall || tileSouth == prevTile) {
            return false;
        }

        return true;
    }

    public boolean canIGoWest(Tile tile) {

        if (tile.col == 0) {
            return false;
        }

        if (tile.beenWest) {
            return false;
        }

        Tile tileWest = tileWest(tile);

        if (tileWest.isWall || tileWest == prevTile) {
            return false;
        }

        return true;
    }

    public Tile tileNorth(Tile tile) {
        if (tile.row > 0) {
            return tiles[tile.row-1][tile.col];
        }
        return null;
    }

    public Tile tileEast(Tile tile) {
        if (tile.col < numCols -1) {
            return tiles[tile.row][tile.col+1];
        }
        return null;
    }

    public Tile tileSouth(Tile tile) {
        if (tile.row < numRows -1) {
            return tiles[tile.row+1][tile.col];
        }
        return null;
    }

    public Tile tileWest(Tile tile) {
        if (tile.col > 0) {
            return tiles[tile.row][tile.col-1];
        }
        return null;
    }

    public void printTileData(Tile tile) {
        System.out.println("Tile: [" + tile.row + "][" + tile.col + "] isWall: " + tile.isWall + " isStart: " + tile.isStart + " isEnd: " + tile.isEnd );
    }

    public void print() {
   
        char block = (char) 0x2588;
             
        for (int row = 0; row < numRows; row++){
         
            for (int col = 0; col < numCols; col++){
            
                if (tiles[row][col].isWall) {
                    System.out.print(block);
                } else{
                    System.out.print(" ");
                }
            }           
            System.out.println();

        }
    }
}