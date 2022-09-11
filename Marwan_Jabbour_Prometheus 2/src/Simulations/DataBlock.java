package Simulations;

import NeuralNetworksV1.Neural_Network;

public class DataBlock {

    public double[]inputData;
    public double correctOutput;

    protected Neural_Network aNeuralNetwork;

    public DataBlock(Neural_Network pNeuralNetwork, double[]pData,  double aOutput){
        this.aNeuralNetwork=pNeuralNetwork;
        inputData=pData;
        this.correctOutput=aOutput;
    }

    public double predictBlock(){
        double[]tempInputData= new double[inputData.length];
        System.arraycopy(inputData,0,tempInputData,0,inputData.length);
        double output=aNeuralNetwork.predictOutput(tempInputData);
        return output;
    }

    public double predictBlock(double [] input){
        double[]tempInputData= new double[input.length];
        System.arraycopy(input,0,tempInputData,0,input.length);
        double output=aNeuralNetwork.predictOutput(tempInputData);
        return output;
    }

    public double giveCorrectOutput(){
        return correctOutput;
    }
}
