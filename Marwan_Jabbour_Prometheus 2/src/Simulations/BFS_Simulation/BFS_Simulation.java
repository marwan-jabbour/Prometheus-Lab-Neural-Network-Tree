package Simulations.BFS_Simulation;

import NeuralNetworksV1.Level2.ChangeDirectionNN;
import Simulations.DataBlock;

public class BFS_Simulation {
    public static int [] giveMeObstacleIndices(int[][] obs, int totalBlocks){

        int rowLength =  (int) (Math.sqrt(totalBlocks));
        int [] obstacleCoordinates = new int [obs.length];

        for (int i=0;i <obs.length;i++){

            int row = obs[i][0]*(rowLength);
            int col = row+ obs[i][1];
            obstacleCoordinates[i]= col;
        }

        return obstacleCoordinates;
    }

    public static void main(String[] args) {

        //WIDTH of the screen : 400, 500, 600, 700, 800, etc.
        int width=800;
        int height=width;

        //SIZE: corresponds to how many blocks are in the grid (e.g: 16, 25, 36, 49, 64)
        int size= (width*width/2500);

        //X AND Y coordinates of the destination (note that y starts from top, x starts from left)
        int destX=4;
        int destY=3;

        //******************************************************************************
        //THESE 2 ARRAYS MUST MATCH
        //IF UNSURE, RUN THE SIMULATION AND CHANGE THE obstacleCoordinates accordingly
        //******************************************************************************

        //X and Y coordinates of obstacles on the grid
        int[][]obstacles={
                {1,1},
                {2,2},
                {3,3},
                {0,4},
                {5,0},
                {6,1},
                {6,2},
                {6,3},
                {4,4}
        };


        int []obstacleCoordinates = giveMeObstacleIndices(obstacles, 256);
        for (int i=0; i <obstacleCoordinates.length;i++)
            System.out.println(obstacleCoordinates[i]+" ");
        //Coordinates of obstacles on the grid
//        int []obstacleCoordinates={6,12,18};

        //to achieve proper results, user must track the path the robot takes and move accordingly

        BFS_Frame frame = new BFS_Frame(width, height, size, destX,destY);
        BFS_Panel panel=  frame.panel;
        panel.startWorld();
        panel.generateTestSets(size,obstacleCoordinates);

//        panel.bfsss(panel.root);
        panel.BFS(panel.root, panel.root);


        for (int i=0;i<obstacles.length;i++){
            panel.newObstacle(obstacles[i][1],obstacles[i][0]);
        }

    }
}