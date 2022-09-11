package Simulations.NeuralNetworksV1.Level2.MovableObjectSimulation;

import NeuralNetworksV1.Level2.MovableObjectNN;
import Simulations.DataBlock;

public class MovableObjectNN_Simulation {
    public static void main(String[] args) {
        //try pushing against walls to simulate no change in position

        DataBlock[]aData= new DataBlock[9];
        MovableObjectNN nn = new MovableObjectNN();
        nn.trainNN();


        double[][]data=nn.getTestSet();
        for (int i=0;i<aData.length;i++){
            double []inputData=new double[data[0].length-1];
            double outputData;
            for (int k=0;k<inputData.length;k++){
                inputData[k]=data[i][k];
            }
            outputData=data[i][0];

            aData[i]=new DataBlock(nn,inputData,outputData);
        }


        MovableObjectNN_Frame frame = new MovableObjectNN_Frame();
        MovableObjectNN_Panel panel=  frame.panel;

        panel.setInputData(aData);

    }
}