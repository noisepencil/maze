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
                
                Tile tile = new Tile(0, 0, (mazeData[row][col] == 0));
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
        
        // top border (how to leave hole for start/end?)
        System.out.print(block); // corner
        for (int c =0; c < numCols;c++){
            if (tiles[numRows-1][c].isEnd || tiles[numRows-1][c].isStart) {
                System.out.print((char) 0x00A0);
            } else {
                System.out.print(block);
            }
        }
        System.out.println(block); // bottom right corner
     
        for (int row = numRows-1; row >= 0; row--){
            // left border (unless leaving hole for start/end) (check first tile in this row)
            if (tiles[row][0].isStart || tiles[row][0].isEnd) {
                System.out.print((char) 0x00A0);
            } else {
                System.out.print(block);
            }
         
            for (int col = 0; col < numCols; col++){
            
                if (tiles[row][col].isWall) {
                    System.out.print(block);
                } else{
                    System.out.print(" ");
                }
            }

            // right border unless leaving hole for start/end (check last tile in this row)
            if (tiles[row][numCols-1].isStart || tiles[row][numCols-1].isEnd) {
                System.out.println((char) 0x00A0);
            } else {
                System.out.println(block);
            }
           
        }

        // bottom border (how to leave hole for start/end?)
        System.out.print(block); // top left corner
        for (int c =0; c < numCols;c++){
            if (tiles[0][c].isEnd || tiles[0][c].isStart) {
                System.out.print((char) 0x00A0);
            } else {
                System.out.print(block);
            }
        }
        System.out.println(block); // top right corner

    }
}