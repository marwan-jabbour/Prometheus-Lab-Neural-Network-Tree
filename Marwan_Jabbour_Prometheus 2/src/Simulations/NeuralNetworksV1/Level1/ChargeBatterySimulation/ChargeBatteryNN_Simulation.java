package Simulations.NeuralNetworksV1.Level1.ChargeBatterySimulation;

import NeuralNetworksV1.Level1.ChargeBatteryNN;
import Simulations.DataBlock;
import Simulations.NeuralNetworksV1.Level1.SurfaceClassifierSimulation.SurfaceClassifierNN_Panel;
import Simulations.NeuralNetworksV1.Level1.SurfaceClassifierSimulation.SurfaceClassifierNN_Frame;

public class ChargeBatteryNN_Simulation {
    public static void main(String[] args) {

        DataBlock[]aData= new DataBlock[25];
        ChargeBatteryNN nn = new ChargeBatteryNN();
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


        ChargeBatteryNN_Frame frame = new ChargeBatteryNN_Frame();
        ChargeBatteryNN_Panel panel=frame.panel;

        panel.setInputData(aData);






    }
}