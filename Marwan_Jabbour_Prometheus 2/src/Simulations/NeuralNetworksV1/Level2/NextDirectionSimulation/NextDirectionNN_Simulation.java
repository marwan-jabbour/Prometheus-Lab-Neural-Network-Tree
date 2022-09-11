package Simulations.NeuralNetworksV1.Level2.NextDirectionSimulation;

import NeuralNetworksV1.Level2.NextDirectionNN;
import Simulations.DataBlock;

public class NextDirectionNN_Simulation {
    public static void main(String[] args) {

        //user MUST follow the arrows

        DataBlock[]aData= new DataBlock[25];
        NextDirectionNN nn = new NextDirectionNN();
        nn.trainNN();


        double[][]data=nn.getTestSet();

        for (int i=0;i<aData.length;i++){
            double []inputData=new double[data[0].length-1];
            double outputData;
            for (int k=0;k<inputData.length;k++){
                inputData[k]=data[i][k];
            }
            outputData=data[i][data[i].length-1];

            for (int a=0;a<inputData.length;a++){
                System.out.print(inputData[a]+" ");
            }
            System.out.println();
            aData[i]=new DataBlock(nn,inputData,outputData);
        }


        for (int i=0;i<aData.length;i++){
            System.out.println(aData[i].giveCorrectOutput());
        }


        NextDirectionNN_Frame frame = new NextDirectionNN_Frame();
        NextDirectionNN_Panel panel=frame.panel;

        panel.setInputData(aData);

        panel.newObstacle(7,7);





    }



}