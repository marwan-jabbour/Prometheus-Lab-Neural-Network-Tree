package Simulations.NeuralNetworksV1.Level1.SurfaceClassifierSimulation;

import NeuralNetworksV1.Level1.SurfaceClassifierNN;
import Simulations.DataBlock;

public class SurfaceClassifierNN_Simulation {
    public static void main(String[] args) {
        DataBlock[]aData= new DataBlock[25];
        SurfaceClassifierNN nn = new SurfaceClassifierNN();
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


        SurfaceClassifierNN_Frame frame = new SurfaceClassifierNN_Frame();
        SurfaceClassifierNN_Panel panel= (SurfaceClassifierNN_Panel) frame.panel;
        panel.setInputData(aData);

    }
}