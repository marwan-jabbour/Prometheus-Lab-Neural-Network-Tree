package Simulations.NeuralNetworksV1.Level2.ChangeDirectionSimulation;

import NeuralNetworksV1.Level2.ChangeDirectionNN;
import Simulations.DataBlock;

public class ChangeDirectionNN_Simulation {
    public static void main(String[] args) {
        //when you begin program, leave block 0 and come back to it
        //then follow the directions given at the last row, for example right means click R, down means D, etc.
        //WIDTH of the screen : 400, 500, 600, 700, 800, etc.
        int width=700;
        int height=width;


        //SIZE: corresponds to how many blocks are in the grid (e.g: 16, 25, 36, 49, 64)
        int size= (width*width/10000);

        //X AND Y corrdinates of the destination (note that y starts from top, x starts from left)
        int destX=4;
        int destY=3;

        DataBlock[]aDataLeft= new DataBlock[size];
        DataBlock[]aDataRight= new DataBlock[size];
        DataBlock[]aDataFwd= new DataBlock[size];
        DataBlock[]aDataDwd= new DataBlock[size];

        ChangeDirectionNN nn = new ChangeDirectionNN();
        nn.trainNN();

        //******************************************************************************
        //THESE 2 ARRAYS MUST MATCH
        //IF UNSURE, RUN THE SIMULATION AND CHANGE THE obstacleCoordinates accordingly
        //******************************************************************************

        int[][]obstacles={
                {1,0},
                {4,1},
                {2,4},
                {1,3},
                {1,4}
        };

        int []obstacleCoordinates=new int[]{1,11,22,29,30};
        nn.generateTestSets(size, obstacleCoordinates);




        double[][]dataLeft=nn.getTestSetLeft();
        for (int i=0;i<aDataLeft.length;i++){
            double []inputDataLeft=new double[dataLeft[0].length-1];
            double outputDataLeft;
            for (int k=0;k<inputDataLeft.length;k++){
                inputDataLeft[k]=dataLeft[i][k];
            }
            outputDataLeft=dataLeft[i][dataLeft[i].length-1];
            aDataLeft[i]=new DataBlock(nn,inputDataLeft,outputDataLeft);
        }

        double[][]dataRight=nn.getTestSetRight();
        for (int i=0;i<aDataRight.length;i++){
            double []inputDataRight=new double[dataRight[0].length-1];
            double outputDataRight;
            for (int k=0;k<inputDataRight.length;k++){
                inputDataRight[k]=dataRight[i][k];
            }
            outputDataRight=dataRight[i][dataRight[i].length-1];
            aDataRight[i]=new DataBlock(nn,inputDataRight,outputDataRight);
        }

        double[][]datafwd=nn.getTestSetForward();
        for (int i=0;i<aDataFwd.length;i++){
            double []inputDatafwd=new double[datafwd[0].length-1];
            double outputDatafwd;
            for (int k=0;k<inputDatafwd.length;k++){
                inputDatafwd[k]=datafwd[i][k];
            }
            outputDatafwd=datafwd[i][datafwd[i].length-1];
            aDataFwd[i]=new DataBlock(nn,inputDatafwd,outputDatafwd);
        }


        double[][]datadwd=nn.getTestSetDownward();
        for (int i=0;i<aDataDwd.length;i++){
            double []inputDatadwd=new double[datadwd[0].length-1];
            double outputDatadwd;
            for (int k=0;k<inputDatadwd.length;k++){
                inputDatadwd[k]=datadwd[i][k];
            }
            outputDatadwd=datadwd[i][datadwd[i].length-1];
            aDataDwd[i]=new DataBlock(nn,inputDatadwd,outputDatadwd);
        }

        int size2=size;

        ChangeDirectionNN_Frame frame = new ChangeDirectionNN_Frame(width,height,size2, destX,destY);
        ChangeDirectionNN_Panel panel=  frame.panel;

        panel.setInputData(aDataLeft,aDataFwd, aDataRight, aDataDwd);


        for (int i=0;i<obstacles.length;i++){
            panel.newObstacle(obstacles[i][0],obstacles[i][1]);
        }

    }
}