package Simulations.NeuralNetworksV1.Level1.UltraSonicSensorSimulation;

import NeuralNetworksV1.Level1.UltraSonicSensorNN;
import Simulations.DataBlock;

public class UltraSonicSensorNN_Simulation {
    public static void main(String[] args) {

        //(far far close) means: no left obstacle, no forward obstacle, right obstacle


        DataBlock[]aDataLeft= new DataBlock[25];
        DataBlock[]aDataRight= new DataBlock[25];
        DataBlock[]aDataFwd= new DataBlock[25];

        UltraSonicSensorNN nn = new UltraSonicSensorNN();
        nn.trainNN();


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
        
        
        
        
        UltraSonicSensorNN_Frame frame = new UltraSonicSensorNN_Frame();
        UltraSonicSensorNN_Panel panel= (UltraSonicSensorNN_Panel) frame.panel;

        panel.setInputData(aDataLeft, aDataRight, aDataFwd);

        panel.newObstacle(0,0);
        panel.newObstacle(4,1);
        panel.newObstacle(2,4);
        panel.newObstacle(1,3);
        panel.newObstacle(1,4);
        panel.newObstacle(4,3);








    }
}