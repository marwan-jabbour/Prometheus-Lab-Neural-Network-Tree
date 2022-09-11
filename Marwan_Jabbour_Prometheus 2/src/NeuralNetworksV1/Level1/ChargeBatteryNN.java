package NeuralNetworksV1.Level1;

import NeuralNetworksV1.Neural_Network;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

import java.util.Arrays;

public class ChargeBatteryNN extends Neural_Network {

    private double[][]data= {
            // input current - voltage - remaining capacity - full capacity
            //Input Current = 1 mA
            {1,10,0,1.0},
            {1,9.5,0.02,1.0},
            {1,9.0,0.275,1.0},
            {1,8.5,0.85,1.0},
            {1,8.0,0.91,1.0},
            {1,7.5,0.94,1.0},
            {1,7.0,0.955,1.0},
            {1,6.5,0.975,1.0},
            {1,6.0,0.98,1.0},
            {1,5.5,0.995,1.0},
            {1,5.0,1.0,1.0},
            //Input Current = 10 mA
            {10,9.6,0.0,0.805},
            {10,9.0,0.03,0.805},
            {10,8.5,0.3,0.805},
            {10,8.0,0.64,0.805},
            {10,7.5,0.72,0.805},
            {10,7.0,0.75,0.805},
            {10,6.5,0.77,0.805},
            {10,6.0,0.78,0.805},
            {10,5.5,0.79,0.805},
            {10,5.0,0.805,0.805},
            //Input Current = 50 mA
            {50,9.25,0.0,0.2825},
            {50,9.0,0.015,0.2825},
            {50,8.5,0.04,0.2825},
            {50,8.0,0.095,0.2825},
            {50,7.5,0.2,0.2825},
            {50,7.0,0.24,0.2825},
            {50,6.5,0.265,0.2825},
            {50,6.0,0.275,0.2825},
            {50,5.5,0.28,0.2825},
            {50,5.0,0.2825,0.2825},
            //Input Current = 100 mA
            {100,9.0,0.0,0.125},
            {100,8.5,0.01,0.125},
            {100,8.0,0.022,0.125},
            {100,7.5,0.07,0.125},
            {100,7.0,0.1,0.125},
            {100,6.5,0.105,0.125},
            {100,6.0,0.11,0.125},
            {100,5.5,0.115,0.125},
            {100,5.0,0.125,0.125},
    };

    //Test set to be used in simulation
    private double[][]testSet={
            {1, 9.75, 0},
            {1,9.3, 0},
            {1, 8.25, 1},
            {1, 7.25, 1},
            {1, 6.25, 1},
            {1,5.25,1},

            {10, 8.75, 0},
            {10,8.35,0},
            {10, 7.25, 1},
            {10, 6.25, 1},
            {10, 5.25, 1},
            {10,5.15,1},

            {50, 12.15, 0},
            {50,11.75,0},
            {50, 10.25, 0},
            {50, 5.25, 1},
            {50, 4.25, 1},
            {50, 3.25, 1},

            {100,11.75, 0},
            {100,8.55,0},
            {100,8.15,0},
            {100,7.75, 0},
            {100,6.75, 1},
            {100,5.75, 1},
            {100,2.35,1}
    };

    /**
     * Default Constructor
     */
    public ChargeBatteryNN(){
        aNeuralNetwork=this.buildNN();
    }

    /**
     *
     * @return test set data
     */
    public double[][]getTestSet(){
        return testSet;
    }


    /**
     *
     * @return the built neural network
     */
    @Override
    protected NeuralNetwork buildNN() {
        NeuralNetwork aPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 2, 4, 2);
        BackPropagation learningRule = (BackPropagation) aPerceptron.getLearningRule();
        learningRule.setMaxIterations(5000);
        learningRule.setLearningRate(0.01);
        learningRule.setMaxError(0.01);
        aPerceptron.randomizeWeights();
        return aPerceptron;
    }

    /**
     *
     * @param pInput The input current and voltage
     * @return Should the battery be charged or not
     */
    public double predictOutput(double []pInput) {
        aNeuralNetwork.setInput(pInput);
        aNeuralNetwork.calculate();
        double[] output = aNeuralNetwork.getOutput();
        int result=giveMeIndex(output);
        return result;
    }


    /**
     *
     * @return Input data for neural network to be trained on
     */
    @Override
    protected double[][] getInputData() {
        double[][] inputData = new double[this.data.length][data[0].length - 2];
        for (int i = 0; i < this.data.length; i++) {
            double[] aRow = new double[data[0].length - 2];
            if (this.data[0].length - 2 >= 0)
                System.arraycopy(this.data[i], 0, aRow, 0, this.data[0].length - 2);
            inputData[i] = aRow;
        }
        return inputData;
    }

    /**
     *
     * @return The expected data for neural network to be trained on
     */
    @Override
    protected double[][] getExpectedData() {
        double[][] outputData = new double[this.data.length][2];

        for (int i = 0; i < this.data.length; i++) {
            double[]aRow=new double[2];
            double percent=(data[i][2]/data[i][3])*100;
            //No need charge
            if (percent>=50){
                aRow=new double[]{0,1};
            }

            //Must charge
            else {
                aRow=new double[]{1,0};
            }
            outputData[i] = aRow;
        }
        return outputData;
    }



    /**
     *
     * @return The dataset for neural network to be trained on
     */
    @Override
    protected DataSet generateTrainingSet() {
        double[][] inputData = getInputData();
        DataSet trainSet = new DataSet(inputData[0].length, 2);
        double[][] expectedData = this.getExpectedData();
        for (int i = 0; i < this.data.length; i++) {
            trainSet.add(new DataSetRow(inputData[i], expectedData[i]));
        }
        return trainSet;
    }

}
