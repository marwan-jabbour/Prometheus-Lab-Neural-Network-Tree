package Simulations.NeuralNetworksV1.Level2.BatteryFailureSimulation;

import NeuralNetworksV1.Level1.ChargeBatteryNN;
import NeuralNetworksV1.Level2.BatteryFailureNN;
import Simulations.DataBlock;
import Simulations.NeuralNetworksV1.Level1.ChargeBatterySimulation.ChargeBatteryNN_Frame;
import Simulations.NeuralNetworksV1.Level1.ChargeBatterySimulation.ChargeBatteryNN_Panel;

public class BatteryFailureNN_Simulation {
    public static void main(String[] args) {

        DataBlock[]aData= new DataBlock[25];
        BatteryFailureNN nn = new BatteryFailureNN();
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



        BatteryFailureNN_Frame frame = new BatteryFailureNN_Frame();
        BatteryFailureNN_Panel panel=frame.panel;
        panel.setInputData(aData);

    }
}