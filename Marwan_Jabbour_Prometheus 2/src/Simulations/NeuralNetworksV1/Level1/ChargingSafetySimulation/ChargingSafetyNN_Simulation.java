package Simulations.NeuralNetworksV1.Level1.ChargingSafetySimulation;

import NeuralNetworksV1.Level1.ChargingSafetyNN;
import Simulations.DataBlock;

public class ChargingSafetyNN_Simulation {
    public static void main(String[] args) {


        DataBlock[]aData= new DataBlock[25];
        ChargingSafetyNN nn = new ChargingSafetyNN();
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



        ChargingSafetyNN_Frame frame = new ChargingSafetyNN_Frame();
        ChargingSafetyNN_Panel panel=frame.panel;
        panel.setInputData(aData);






    }
}