package Simulations.NeuralNetworksV1.Level1.DirectionClassifierSimulation;

import NeuralNetworksV1.Level1.ChargeBatteryNN;
import NeuralNetworksV1.Level1.DirectionClassifierNN;
import Simulations.DataBlock;
import Simulations.NeuralNetworksV1.Level1.ChargeBatterySimulation.ChargeBatteryNN_Frame;
import Simulations.NeuralNetworksV1.Level1.ChargeBatterySimulation.ChargeBatteryNN_Panel;

public class DirectionClassifierNN_Simulation {
    public static void main(String[] args) {

        //USER must follow the path given the arrows


        DataBlock[]aData= new DataBlock[25];
        DirectionClassifierNN nn = new DirectionClassifierNN();
        nn.trainNN();
        double[][]dataTemp=nn.getTestSet();

        double[][]data=new double[25][5];
        int counter=0;
        data[counter++]=dataTemp[0];
        data[counter++]=dataTemp[7];
        data[counter++]=dataTemp[13];
        data[counter++]=dataTemp[18];
        data[counter++]=dataTemp[2];

        data[counter++]=dataTemp[22];
        data[counter++]=dataTemp[11];
        data[counter++]=dataTemp[23];
        data[counter++]=dataTemp[24];
        data[counter++]=dataTemp[27];

        data[counter++]=dataTemp[31];
        data[counter++]=dataTemp[32];
        data[counter++]=dataTemp[33];
        data[counter++]=dataTemp[36];
        data[counter++]=dataTemp[37];

        data[counter++]=dataTemp[38];
        data[counter++]=dataTemp[40];
        data[counter++]=dataTemp[43];
        data[counter++]=dataTemp[44];
        data[counter++]=dataTemp[45];

        data[counter++]=dataTemp[47];
        data[counter++]=dataTemp[41];
        data[counter++]=dataTemp[49];
        data[counter++]=dataTemp[39];
        data[counter++]=dataTemp[42];


        for (int i=0;i<aData.length;i++){
            double []inputData=new double[data[0].length-1];
            double outputData;
            for (int k=0;k<inputData.length;k++){
                inputData[k]=data[i][k];
            }
            outputData=data[i][data[i].length-1];
            aData[i]=new DataBlock(nn,inputData,outputData);
        }




        DirectionClassifierNN_Frame frame = new DirectionClassifierNN_Frame();
        DirectionClassifierNN_Panel panel=frame.panel;
        panel.setInputData(aData);
    }
}