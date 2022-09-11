package NeuralNetworksV1.Level1;

import NeuralNetworksV1.Neural_Network;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

import java.util.ArrayList;
import java.util.Arrays;

public class UltraSonicSensorNN extends Neural_Network {
    //binary partition
    private int aPartitionSize=2;
    private double[] aPartition;

    //Voltage and Distance
    private double [][]data= {
            {3,7},
            {2.9,7.5},
            {2.75,8},
            {2.5,9},
            {2.25,10.5},
            {2,12.5},
            {1.9,13},

            {1.75,14},
            {1.5,16},
            {1.25,21},
            {1,27.5},
            {0.9,32},
            {0.75,37.5},
            {0.5,60}
    };

    //FOR SIMULATION: Test set for left ultrasonic sensor
    private double[][]testSetLeft={
            //0 0 1 1 1
            {2.95,0},
            {2.8,0},
            {0.8,1},
            {0.85,1},
            {1.15,1},

            //0 1 1 1 0
            {2.55,0},
            {0.81,1},
            {0.83,1},
            {1.2,1},
            {3.25,0},

            //0 1 1 1 1
            {2.75,0},
            {1.6,1},
            {1.4,1},
            {1.22,1},
            {1.32,1},

            //0 0 0 1 0
            {1.99,0},
            {2.12,0},
            {2.31,0},
            {1,1},
            {1.89,0},


            //0 0 0 0 1
            {2.83,0},
            {2.84,0},
            {2.34,0},
            {2.99,0},
            {0.45,1}
    };

    //FOR SIMULATION: Test set for forward ultrasonic sensor
    private double[][]testSetForward={
            //0 0 0 0 0
            {2.93,0},
            {2.82,0},
            {1.89,0},
            {2.15,0},
            {2.35,0},

            //0 1 1 1 0
            {2.495,0},
            {0.821,1},
            {0.845,1},
            {1.25,1},
            {3.29,0},

            //1 1 1 1 0
            {1.95,1},
            {1.665,1},
            {1.445,1},
            {1.260,1},
            {2.350,0},

            //1 0 1 1 0
            {1.20,1},
            {2.5,0},
            {1.30,1},
            {1,1},
            {1.85,0},


            //1 0 0 1 0
            {1.25,1},
            {2.8,0},
            {2.3,0},
            {0.55,1},
            {2.45,0}
    };

    //FOR SIMULATION: Test set for right ultrasonic sensor
    private double[][]testSetRight={
            //0 1 1 1 0
            {2.95,0},
            {1.1,1},
            {1.2,1},
            {1.3,1},
            {2.3,0},

            //1 1 1 0 0
            {0.82,1},
            {0.84,1},
            {1.2,1},
            {2.55,0},
            {3.25,0},

            //1 1 1 1 0
            {1.75,1},
            {1.80,1},
            {1.825,1},
            {1.85,1},
            {2.775,0},

            //0 0 1 0 0
            {2.5,0},
            {1.85,0},
            {1.20,1},
            {2.3,0},
            {2.45,0},


            //0 0 0 1 0
            {2.8,0},
            {2.3,0},
            {2.5,0},
            {1.25,1},
            {3.0,0}
    };

    public UltraSonicSensorNN(){
        aNeuralNetwork=this.buildNN();
        this.equalFrequencyBinning(aPartitionSize);
    }

    public double[][]getTestSetLeft(){
        return testSetLeft;
    }
    public double[][]getTestSetRight(){
        return testSetRight;
    }
    public double[][]getTestSetForward(){
        return testSetForward;
    }


    /**
     *
     * @param pInput Voltage
     * @return how far obstacle is
     */
    public double predictOutput(double []pInput) {

        aNeuralNetwork.setInput(pInput);
        aNeuralNetwork.calculate();
        double[] output = aNeuralNetwork.getOutput();

        int index=giveMeIndex(output);
        return index;

    }


    /**
     *
     * @return how built neural network
     */
    @Override
    protected NeuralNetwork buildNN() {
        NeuralNetwork aPerceptron = new MultiLayerPerceptron(TransferFunctionType.GAUSSIAN, 1, 4, aPartitionSize+1);
        BackPropagation learningRule = (BackPropagation) aPerceptron.getLearningRule();
        learningRule.setMaxIterations(5000);
        learningRule.setLearningRate(0.01);
        learningRule.setMaxError(0.01);
        double[] weights = new double[63];

        for(int i = 0; i < 63; i++){
            weights[i] = 0.15;
        }

        aPerceptron.setWeights(weights);


        return aPerceptron;
    }

    /**
     *
     * @return input data
     */
    @Override
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
     * @return expected data
     */
    @Override
    protected double[][] getExpectedData() {
        double[][] outputData = new double[this.data.length][this.data[0].length];
        for (int i = 0; i < this.data.length; i++) {
            double[]aRow=new double[aPartition.length+1];
            int k=0;
            for (k=0;k<aRow.length-1;k++){
                if (this.data[i][this.data[i].length - 1] <= aPartition[k]){
                    aRow[k]=1;
                    break;
                }
            }
            if (k==aRow.length-1) {
                aRow[aPartition.length] = 1;
            }
            outputData[i] = aRow;
        }
        return outputData;
    }


    /**
     *
     * @return training set
     */
    @Override
    public DataSet generateTrainingSet() {
        double[][] inputData = getInputData();
        DataSet trainSet = new DataSet(inputData[0].length, aPartition.length+1);
        double[][] expectedData = this.getExpectedData();
        for (int i = 0; i < this.data.length; i++) {
            trainSet.add(new DataSetRow(inputData[i], expectedData[i]));
        }
        return trainSet;
    }


    /**
     *
     * @param pPartition the number of classes for this dataset
     * @return the partition of the dataset
     *
     * this method essentially splits the data into equal frequency bins based on the number of classes the user wants
     * for instance, if we want to split the dataset into (close or far) obstacle, (close medium far) obstacle, etc.
     *
     * to avoid hard-coded values, this method splits the dataset into these classes, ensuring equal frequency in each class
     */
    private double[] equalFrequencyBinning(int pPartition){
        int length=data.length;
        int n=length/pPartition;
        double[] output=new double[length];
        for (int i=0;i<length;i++){
            output[i]=data[i][data[i].length-1];
        }
        Arrays.sort(output);

        double[]partition=new double[pPartition];
        for (int i=0;i<pPartition;i++){
            ArrayList arr=new ArrayList();
            for (int j=i*n;j<(i+1)*n;j++){
                if (j>=length)
                    break;
                arr.add(output[j]);
                partition[i]=output[j];
            }
        }
        this.aPartition=partition;

        for (int i=0;i<partition.length;i++){
            System.out.println(partition[i]);
        }
        return partition;
    }


}
