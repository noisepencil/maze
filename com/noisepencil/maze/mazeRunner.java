package com.noisepencil.maze;

public class mazeRunner {
    
    public static void main (String[] args) {
        System.out.println("MazeRunner 1.0");

        // Create some maze data
        int[][] mazeData = new int[8][8];

        mazeData[0] = new int[] {0,1,0,1,0,1,1,1};
        mazeData[1] = new int[] {0,1,1,1,0,0,0,1};
        mazeData[2] = new int[] {0,0,0,1,1,0,1,1};
        mazeData[3] = new int[] {1,1,1,0,1,0,1,0};
        mazeData[4] = new int[] {0,0,1,0,1,1,1,1};
        mazeData[5] = new int[] {1,0,1,1,0,0,1,0};
        mazeData[6] = new int[] {1,0,0,1,0,0,1,0};
        mazeData[7] = new int[] {1,1,1,1,1,1,1,1};

        // create a mazeRunner, initialize it with our data
        Maze maze = new Maze(mazeData, 3, 0, 0, 1);

        // print maze data
        maze.print();

        // adios
        System.out.println("Bye!");

    }
}