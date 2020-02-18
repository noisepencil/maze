package com.noisepencil.maze;

public class Maze {
    
    public Tile[][] tiles;
    public int numRows = 0;
    public int numCols= 0;

    public int startRow = 0;
    public int startCol = 0;
    public int endRow = 0;
    public int endCol = 0;

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

        int direction = 0;  // 0 = north, 1=east, 2=south, 3=west

        Tile curTile = tiles[this.startRow][this.startCol];
        
        printTileData(curTile);

        while (!amIatTheEnd(curTile)) {

            if (canIGoNorth(curTile)) {
                curTile = moveNorth(curTile);
                printTileData(curTile);
            }
    
            if (canIGoEast(curTile)) {
                curTile = moveEast(curTile);
                printTileData(curTile);
            }

            if (canIGoSouth(curTile)) {
                curTile = moveSouth(curTile);
                printTileData(curTile);
            }
    
            if (canIGoWest(curTile)) {
                curTile = moveWest(curTile);
                printTileData(curTile);
            }

        }
        System.out.println("I made it!");
    }

    public boolean amIatTheEnd(Tile tile) {
        if (tile.row == this.endRow && tile.col == this.endCol) {
            return true;
        }
        return false;
    }

    public boolean canIGoNorth(Tile tile) {
        if (tile.row == 0) {
            return false;
        }
        Tile tileAboveMe = tiles[tile.row-1][tile.col];
       
        if (tileAboveMe.isWall) {
            return false;
        }

        return true;
    }

    public Tile moveNorth(Tile tile) {
        return tiles[tile.row-1][tile.col];
    }

    public boolean canIGoEast(Tile tile) {

        if (tile.col == numCols-1) {
            return false;
        }
        if (tiles[tile.row][tile.col+1].isWall) {
            return false;
        }

        return true;
    }

    public Tile moveEast(Tile tile) {
        return tiles[tile.row][tile.col+1];
    }

    public boolean canIGoSouth(Tile tile) {
        if (tile.row == numRows-1) {
            return false;
        }
        Tile tileBelowMe = tiles[tile.row+1][tile.col];
       
        if (tileBelowMe.isWall) {
            return false;
        }

        return true;
    }

    public Tile moveSouth(Tile tile) {
        return tiles[tile.row+1][tile.col];
    }

    public boolean canIGoWest(Tile tile) {

        if (tile.col == 0) {
            return false;
        }
        if (tiles[tile.row][tile.col-1].isWall) {
            return false;
        }

        return true;
    }

    public Tile moveWest(Tile tile) {
        return tiles[tile.row][tile.col-1];
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