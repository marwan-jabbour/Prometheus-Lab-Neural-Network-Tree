package Simulations.NeuralNetworksV1.Level1.TouchSensorSimulation;

import NeuralNetworksV1.Level1.TouchSensorNN;
import Simulations.DataBlock;

public class TouchSensorNN_Simulation {
    public static void main(String[] args) {
        DataBlock[]aData= new DataBlock[9];
        TouchSensorNN nn = new TouchSensorNN();
        nn.trainNN();


        double[][]data=nn.getTestSet();
        for (int i=0;i<aData.length;i++){
            double []inputData=new double[data[0].length-1];
            double outputData;
            for (int k=0;k<inputData.length;k++){
                inputData[k]=data[i][k];
            }
            outputData=data[i][data[i].length-1];
            aData[i]=new DataBlock(nn,inputData,outputData);
        }


        TouchSensorNN_Frame frame = new TouchSensorNN_Frame();
        TouchSensorNN_Panel panel= (TouchSensorNN_Panel) frame.panel;
        panel.setInputData(aData);
    }
}