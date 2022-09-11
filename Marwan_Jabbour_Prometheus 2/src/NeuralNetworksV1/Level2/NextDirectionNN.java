package NeuralNetworksV1.Level2;

import NeuralNetworksV1.Level1.DirectionClassifierNN;
import NeuralNetworksV1.Level1.SurfaceClassifierNN;
import NeuralNetworksV1.Neural_Network;
import org.neuroph.core.NeuralNetwork;
//import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

import java.util.*;

public class NextDirectionNN extends Neural_Network {
    //protected Neural_Network aNeuralNetwork;

    private double [][]data={
            // ChangeDirectionNN - SurfaceClassifierNN
            //based on new direction and surface we determine intensity and direction
            //Forward
            {0,0,0},
            {0,1,0},
            {0,0,0},
            {0,1,0},

            //Right (slight and sharp)
            {1,0,1},
            {1,1,2},
            {1,0,1},
            {1,1,2},

            //Left (slight and sharp)
            {2,0,3},
            {2,1,4},
            {2,0,3},
            {2,1,4}

    };

    private double testSet[][]={

            //1 0 0 0 1
            {1,0,1},
            {0,0,0},
            {0,1,0},
            {0,1,0},
            {1,1,2},

            //0 2 0 2 0
            {0,0,0},
            {2,0,3},
            {0,1,0},
            {2,1,4},
            {0,0,0},

            //0 0 0 0 0
            {0,0,0},
            {0,0,0},
            {0,0,0},
            {0,1,0},
            {0,1,0},

            //0 0 0 0 0
            {0,1,0},
            {0,1,0},
            {0,0,0},
            {0,0,0},
            {0,0,0},

            //0 2 2 1 1
            {0,0,0},
            {2,0,3},
            {2,1,4},
            {1,0,1},
            {1,1,2},
    };

    public double[][] getTestSet(){
        return testSet;
    };

    public NextDirectionNN(){
        aNeuralNetwork=this.buildNN();
    }


    /**
     *
     * @return the built neural network
     */
    @Override
    protected NeuralNetwork buildNN() {

        NeuralNetwork aPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 2, 20, 5);

        BackPropagation learningRule = (BackPropagation) aPerceptron.getLearningRule();
        learningRule.setMaxIterations(6000);
        learningRule.setLearningRate(0.01);
        learningRule.setMaxError(0.01);

        return aPerceptron;
    }

    /**
     *
     * @param pInput The network processed input
     * @return the next direction and intensity
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
     * @return the input data to be trained on
     */
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


    /**
     *
     * @return the expected data to be trained on
     */
    @Override
    protected double[][] getExpectedData() {
        double[][] outputData = new double[this.data.length][this.data[0].length];
        for (int i = 0; i < this.data.length; i++) {
            double[]aRow=new double[5];
            if (this.data[i][this.data[i].length - 1]==0)
                aRow=new double[]{1,0,0,0,0};
            else if (this.data[i][this.data[i].length - 1]==1)
                aRow=new double[]{0,1,0,0,0};
            else if (this.data[i][this.data[i].length - 1]==2)
                aRow=new double[]{0,0,1,0,0};
            else if (this.data[i][this.data[i].length - 1]==3)
                aRow=new double[]{0,0,0,1,0};
            else if (this.data[i][this.data[i].length - 1]==4)
                aRow=new double[]{0,0,0,0,1};

            outputData[i] = aRow;
        }
        return outputData;
    }


    /**
     *
     * @return the dataset to be trained on
     */
    @Override
    protected DataSet generateTrainingSet() {
        double[][] inputData = getInputData();
        DataSet trainSet = new DataSet(inputData[0].length, 5);
        double[][] expectedData = this.getExpectedData();

        for (int i = 0; i < this.data.length; i++) {
            trainSet.add(new DataSetRow(inputData[i], expectedData[i]));
        }
        return trainSet;
    }



}
