package NeuralNetworksV1.Level1;

import NeuralNetworksV1.Neural_Network;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

import java.util.Arrays;

public class ChargingSafetyNN extends Neural_Network {

    // Voltage and Temperature
    private double [][]data={
            {16.3,-40,1},
            {16.25,-35,1},
            {16,-28,1},
            {15.75,-20,1},
            {15.5,-12,1},
            {15.25,-4,1},
            {15,4,1},
            {14.75,13,1},
            {14.5,21,1},
            {14.25,30,1},
            {14,37,1},
            {13.75,46,1},
            {13.5,55,1},
            {13.25,62,1},

    };

    private double[][]testSet={
            //Safe
            {16.25, -39,1},
            {15.85,-24,1},
            {15.4,-7,1},
            {15.3,-5,1},
            {14.85,8,1},
            {14.95,5,1},
            {14.4,23,1},
            {14.3,28,1},
            {13.85,43,1},
            {13.9,40,1},
            {13.4,57,1},
            {13.2,60,1},


            //Unsafe
            {13.25,-40,0},
            {13.5,-35,0},
            {13.75,-28,0},
            {14,-20,0},
            {14.25,-12,0},
            {14.5,-4,0},
            {14.75,4,0},
            {16.3,13,0},
            {16.25,21,0},
            {16,30,0},
            {15.75,37,0},
            {15.5,46,0},
            {15.25,55,0},

    };

    public ChargingSafetyNN(){
        aNeuralNetwork=this.buildNN();
    }


    public double[][]getTestSet(){
        return testSet;
    }

    /**
     *
     * @return the built neural network
     */
    @Override
    protected NeuralNetwork buildNN() {
        NeuralNetwork aPerceptron = new MultiLayerPerceptron(TransferFunctionType.GAUSSIAN, 1, 4, 1);
        BackPropagation learningRule = (BackPropagation) aPerceptron.getLearningRule();
        learningRule.setMaxIterations(10000);
        learningRule.setLearningRate(0.01);
        learningRule.setMaxError(0.01);
        aPerceptron.randomizeWeights();


        return aPerceptron;
    }


    /**
     *
     * @param pArray The dataset
     * @return the normalized dataset by max min method
     * B = (A - min(A)) / (max(A) - min(A))
     * B * (max(A) - min(A))+ min(A)
     */
    private double[][]normalizeMaxMin(double[][]pArray){
        double []temp=new double[pArray.length];
        for (int i=0;i<temp.length;i++){
            temp[i]=pArray[i][0];
        }

        double min = temp[0];
        for (int i=1;i<temp.length;i++){
            if (temp[i]<min){
                min=temp[i];
            }
        }

        double max = temp[0];
        for (int i=1;i<temp.length;i++){
            if (temp[i]>max){
                max=temp[i];
            }
        }

        double[][]normalizedArray=new double[pArray.length][pArray[0].length];

        for (int i=0;i<normalizedArray.length;i++){
            double []row=new double[pArray[0].length];
            row[0]=(pArray[i][0]-min)/(max-min);
            normalizedArray[i]=row;
        }
        return normalizedArray;

    }


    /**
     *
     * @return Input data after normalization
     */
    @Override
    protected double[][] getInputData() {
        double[][] inputData = new double[this.data.length][data[0].length - 1];
        for (int i = 0; i < this.data.length; i++) {
            double[] aRow = new double[1];
           aRow[0]=data[i][1];
           inputData[i]=aRow;
        }

        //B = (A - min(A)) / (max(A) - min(A))
        inputData=normalizeMaxMin(inputData);
        return inputData;
    }

    /**
     *
     * @return The expected data after normalization
     */
    @Override
    protected double[][] getExpectedData() {
        double[][] outputData = new double[this.data.length][data[0].length - 1];
        for (int i = 0; i < this.data.length; i++) {
            double[]aRow=new double[1];
            aRow[0]=data[i][0];
            outputData[i]=aRow;
        }
        outputData=normalizeMaxMin(outputData);
        return outputData;
    }


    /**
     *
     * @return The dataset to be trained on
     */
    @Override
    protected DataSet generateTrainingSet() {
        double[][] inputData = getInputData();
        DataSet trainSet = new DataSet(inputData[0].length, 1);
        double[][] expectedData = this.getExpectedData();
        for (int i = 0; i < this.data.length; i++) {
            trainSet.add(new DataSetRow(inputData[i], expectedData[i]));
        }
        return trainSet;
    }

    /**
     *
     * @param pInput The voltage and temperature
     * @return Whether it is safe or not to charge
     */
    public double predictOutput(double []pInput) {

        double temp=pInput[1];
        double volt=pInput[0];

        pInput[0]=temp;
        pInput[1]=volt;

        pInput[0]=(pInput[0]-(-40))/(62+40);
        double[]tempInput=new double[1];
        tempInput[0]=pInput[0];
        aNeuralNetwork.setInput(tempInput);
        aNeuralNetwork.calculate();
        double[] output = aNeuralNetwork.getOutput();

        double expectedValue=pInput[1];
        double actualValue=output[0];

        expectedValue=(expectedValue-(13.25))/(16.3-13.25);
        double error= (Math.abs((actualValue - expectedValue)/expectedValue)*100);
        if (error<25)
            return 1;
        else
            return 0;

    }

}
