package Simulations.NeuralNetworksV1.Level2.ReachableDestinationSimulation;

import NeuralNetworksV1.Level1.UltraSonicSensorNN;
import NeuralNetworksV1.Level2.ReachableDestinationNN;
import Simulations.DataBlock;
import Simulations.NeuralNetworksV1.Level1.UltraSonicSensorSimulation.UltraSonicSensorNN_Frame;
import Simulations.NeuralNetworksV1.Level1.UltraSonicSensorSimulation.UltraSonicSensorNN_Panel;

import javax.xml.crypto.Data;

public class ReachableDestinationNN_Simulation {
    public static void main(String[] args) {

        //(reachable reachable unreachable) corresponds to left, forward, right


        DataBlock[]aData= new DataBlock[25];
        DataBlock[]aDataLeft= new DataBlock[25];
        DataBlock[]aDataRight= new DataBlock[25];
        DataBlock[]aDataFwd= new DataBlock[25];

        ReachableDestinationNN nn = new ReachableDestinationNN();
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


        double[][]data=nn.getTestSet();

        for (int i=0;i<aData.length;i++){
            double []inputData=new double[data[0].length];
            double outputData;
            for (int k=0;k<inputData.length;k++){
                inputData[k]=data[i][k];
            }
            outputData=data[i][data[i].length-1];
            aData[i]=new DataBlock(nn,inputData,outputData);
        }
        
        
        
        ReachableDestinationNN_Frame frame = new ReachableDestinationNN_Frame();
        ReachableDestinationNN_Panel panel=  frame.panel;

        panel.setInputData(aData,aDataLeft,aDataFwd, aDataRight);

        panel.newObstacle(0,0);
        panel.newObstacle(4,1);
        panel.newObstacle(2,4);
        panel.newObstacle(1,3);
        panel.newObstacle(1,4);








    }
}