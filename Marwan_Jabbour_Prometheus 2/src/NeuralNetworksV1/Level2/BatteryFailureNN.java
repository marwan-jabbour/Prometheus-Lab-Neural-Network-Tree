package NeuralNetworksV1.Level2;

import NeuralNetworksV1.Level1.ChargeBatteryNN;
import NeuralNetworksV1.Level1.ChargingSafetyNN;
import NeuralNetworksV1.Neural_Network;
import org.neuroph.core.NeuralNetwork;
//import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

import java.util.*;

public class BatteryFailureNN extends Neural_Network {
    private double[] aPartition={0,1};

    private double [][]data={
            //Very Low Temperatures
            //ch safety (=0 unsafe), ch battery (=0 must charge)
            {-40,1,0,0},
            {-40,1,1,0},
            {-40,0,1,1},
            {-40,0,0,1},

            {-30,1,0,0},
            {-30,1,1,0},
            {-30,0,1,1},
            {-30,0,0,1},

            {-25,1,0,0},
            {-25,1,1,0},
            {-25,0,1,1},
            {-25,0,0,1},

            //Low Temperatures
            {-15,1,0,0},
            {-15,1,1,0},
            {-15,0,1,0},
            {-15,0,0,1},

            {-5,1,0,0},
            {-5,1,1,0},
            {-5,0,1,0},
            {-5,0,0,1},

            {5,1,0,0},
            {5,1,1,0},
            {5,0,1,0},
            {5,0,0,1},

            //Room Temperatures
            {15,1,0,0},
            {15,1,1,0},
            {15,0,1,0},
            {15,0,0,1},

            {25,1,0,0},
            {25,1,1,0},
            {25,0,1,0},
            {25,0,0,1},

            //Very High Temperatures
            {35,1,0,0},
            {35,1,1,0},
            {35,0,1,1},
            {35,0,0,1},

            {40,1,0,0},
            {40,1,1,0},
            {40,0,1,1},
            {40,0,0,1},

            {50,1,0,0},
            {50,1,1,0},
            {50,0,1,1},
            {50,0,0,1},
    };

    private double[][]testSet={

            {-45,1,0,0},
            {-45,1,1,0},
            {-45,0,1,1},
            {-45,0,0,1},


            {-35,1,0,0},
            {-35,1,1,0},
            {-35,0,1,1},
            {-35,0,0,1},

            {-10,1,0,0},
            {-10,1,1,0},
            {-10,0,1,0},
            {-10,0,0,1},

            {20,1,0,0},
            {20,1,1,0},
            {20,0,1,0},
            {20,0,0,1},

            {45,1,0,0},
            {45,1,1,0},
            {45,0,1,1},
            {45,0,0,1},
            {45,0,0,1},

            {55,1,0,0},
            {55,1,1,0},
            {55,0,1,1},
            {55,0,0,1},

    };

    public BatteryFailureNN(){
        aNeuralNetwork=this.buildNN();
    }

    public double[][]getTestSet(){
        return testSet;
    }

    @Override
    protected NeuralNetwork buildNN() {

        NeuralNetwork aPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 3, 4, 2);

        BackPropagation learningRule = (BackPropagation) aPerceptron.getLearningRule();
        learningRule.setMaxIterations(30000);
        learningRule.setLearningRate(0.01);
        learningRule.setMaxError(0.01);

        // 3 layers, 3 7 3
        double[] weights = new double[63];

        for(int i = 0; i < 63; i++){
            weights[i] = 0.15;
        }

        aPerceptron.setWeights(weights);


        return aPerceptron;
    }


    /**
     *
     * @param pInput input after being processed by first-level neural network
     * @return is battery likely to fail or not
     */
    public double predictOutput(double []pInput) {
        aNeuralNetwork.setInput(pInput);
        aNeuralNetwork.calculate();
        double[] output = aNeuralNetwork.getOutput();
        int result=giveMeIndex(output);
        return result;
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
            double[]aRow=new double[aPartition.length];
            for (int k=0;k<aRow.length;k++){
                if (this.data[i][this.data[i].length - 1] <= aPartition[k]){
                    aRow[k]=1;
                    break;
                }
            }
            outputData[i] = aRow;
        }
        return outputData;
    }

    @Override
    protected DataSet generateTrainingSet() {
        double[][] inputData = getInputData();
        DataSet trainSet = new DataSet(inputData[0].length, aPartition.length);
        double[][] expectedData = this.getExpectedData();

        for (int i = 0; i < this.data.length; i++) {
            trainSet.add(new DataSetRow(inputData[i], expectedData[i]));
        }
        return trainSet;
    }


}
