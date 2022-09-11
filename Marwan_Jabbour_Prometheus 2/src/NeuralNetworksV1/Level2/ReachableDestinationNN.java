package NeuralNetworksV1.Level2;

import NeuralNetworksV1.Neural_Network;
import org.neuroph.core.NeuralNetwork;
//import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

import java.util.*;

public class ReachableDestinationNN extends Neural_Network {

    private double [][]data={

            //ChargeBatteryNN -----> Battery needs to be charged (0) or not (1)
            //UltrasonicSensorNN: ---> how far is block
            //SurfaceClassifierNN: ---> How rough is surface

            {0,0,0,0},
            {0,0,1,0},
            {0,1,0,0},
            {0,1,1,0},
            {1,0,0,0},
            {1,0,1,0},
            {1,1,0,1},
            {1,1,1,1},
            {0,0,0,0},
            {0,0,1,0},
            {0,1,0,0},
            {0,1,1,0},
            {1,0,0,0},
            {1,0,1,0},
            {1,1,0,1},
            {1,1,1,1},
            {1,1,0,1},
            {1,1,1,1},
    };

    private double[][]testSet={
            //charge battery, surface classifier ( we get SurfaceClassifierNN from the csv)
            {1,0},
            {1,1},
            {1,0},
            {1,1},
            {1,0},
            {1,1},
            {1,0},
            {1,1},
            {1,0},
            {1,1},
            {1,0},
            {1,1},
            {1,0},
            {1,1},
            {1,0},
            {0,1},
            {0,0},
            {0,1},
            {0,0},
            {0,1},
            {0,0},
            {0,1},
            {0,0},
            {0,1},
            {0,0},
    };



    private double [][]testSetLeft={
            {0,0},
            {0,0},
            {1,1},
            {1,1},
            {1,1},

            {0,0},
            {1,1},
            {1,1},
            {1,1},
            {0,0},

            {0,0},
            {1,1},
            {1,1},
            {1,1},
            {1,1},

            {0,0},
            {0,0},
            {0,0},
            {1,0},
            {0,0},

            {0,0},
            {0,0},
            {0,0},
            {0,0},
            {1,0}
    };


    private double [][]testSetForward={
            {0,0},
            {0,0},
            {0,0},
            {0,0},
            {0,0},

            {0,0},
            {1,1},
            {1,1},
            {1,1},
            {0,0},

            {1,1},
            {1,1},
            {1,1},
            {1,1},
            {0,0},

            {1,0},
            {0,0},
            {1,0},
            {1,0},
            {0,0},

            {1,0},
            {0,0},
            {0,0},
            {1,0},
            {0,0}
    };

    private double [][]testSetRight={
            {0,0},
            {1,1},
            {1,1},
            {1,1},
            {0,0},

            {1,1},
            {1,1},
            {1,1},
            {0,0},
            {0,0},

            {1,1},
            {1,1},
            {1,1},
            {1,1},
            {0,0},

            {0,0},
            {0,0},
            {1,0},
            {0,0},
            {0,0},

            {0,0},
            {0,0},
            {0,0},
            {1,0},
            {0,0}
    };

    public ReachableDestinationNN(){
        aNeuralNetwork=this.buildNN();
    }

    //test sets for the various directions robot can take (left, right, up, down)
    public double[][]getTestSetLeft(){
        return testSetLeft;
    }
    public double[][]getTestSetRight(){
        return testSetRight;
    }
    public double[][]getTestSetForward(){
        return testSetForward;
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
        NeuralNetwork aPerceptron = new MultiLayerPerceptron(TransferFunctionType.GAUSSIAN, 3, 20, 2);
        BackPropagation learningRule = (BackPropagation) aPerceptron.getLearningRule();
        learningRule.setMaxIterations(5000);
        learningRule.setLearningRate(0.01);
        learningRule.setMaxError(0.01);
        return aPerceptron;
    }



    protected double[][] getInputData() {
        double[][] inputData = new double[this.data.length][data[0].length - 1];
        for (int i = 0; i < this.data.length; i++) {
            double[] aRow = new double[data[0].length - 1];
            if (this.data[0].length - 1 >= 0)
                System.arraycopy(this.data[i], 0, aRow, 0, this.data[0].length - 1);
            inputData[i] = aRow;
        }
        return inputData;
    }



    @Override
    protected double[][] getExpectedData() {
        double[][] outputData = new double[this.data.length][this.data[0].length];
        for (int i = 0; i < this.data.length; i++) {
            double[]aRow=new double[2];
            if (this.data[i][this.data[i].length - 1]==0)
                aRow=new double[]{1,0};
            else if (this.data[i][this.data[i].length - 1]==1)
                aRow=new double[]{0,1};
            outputData[i] = aRow;
        }
        return outputData;
    }


    @Override
    public void trainNN() {
        DataSet trainSet=this.generateTrainingSet();
        aNeuralNetwork.learn(trainSet);
    }

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

    @Override
    public double predictOutput(double []pInput) {
        aNeuralNetwork.setInput(pInput);
        aNeuralNetwork.calculate();
        double[] output = aNeuralNetwork.getOutput();
        int result=giveMeIndex(output);
        return result;
    }


}
