package com.noisepencil.maze;

public class mazeRunner {
    
    public static void main (String[] args) {
        System.out.println("MazeRunner 1.0");

        // Create some maze data
        int[][] mazeData = new int[10][10];

        mazeData[0] = new int[] {0,0,0,0,0,0,0,0,0,0};
        mazeData[1] = new int[] {0,1,1,1,1,1,1,1,1,0};
        mazeData[2] = new int[] {0,1,0,0,1,0,0,1,0,0};
        mazeData[3] = new int[] {0,1,0,1,1,0,0,1,0,0};
        mazeData[4] = new int[] {0,0,0,1,0,1,1,1,1,0};
        mazeData[5] = new int[] {1,1,1,1,0,1,0,1,0,0};
        mazeData[6] = new int[] {0,0,0,0,1,1,0,1,1,0};
        mazeData[7] = new int[] {0,0,1,1,1,0,0,0,1,0};
        mazeData[8] = new int[] {0,0,1,0,1,0,1,1,1,0};
        mazeData[9] = new int[] {0,0,1,0,0,0,0,0,0,0};

        // create a mazeRunner, initialize it with our data
        Maze maze = new Maze(mazeData, 5, 0, 9, 2);

        // print maze data
        maze.print();

        // walk the maze
        maze.walkMaze();

        // adios
        System.out.println("Bye!");

    }
}