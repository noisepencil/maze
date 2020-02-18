package com.noisepencil.maze;

public class Maze {
    
    public Tile[][] tiles;
    public int numRows = 0;
    public int numCols= 0;
    
    public Maze(int[][] mazeData, int startRow, int startCol, int endRow, int endCol){
        numRows = mazeData.length;
        numCols = mazeData[0].length;

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
                
            }
        }
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