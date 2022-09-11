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

public class TouchSensorNN extends Neural_Network {
    private int aPartitionSize=2;
    private double[] aPartition;

    //capacitance and whisker spread (how hard pushing)
    private double [][]data= {
            {1025,0.5},
            {700,1},
            {500,1.5},
            {450,2.0},
            {400,2.5},
            {375,3.0},
            {350,2.5},
            {310,4.0},
            {290,4.5},
            {275,5.0},
            {255,5.5},
            {250,6.0},
            {240,6.5},
            {230,7.0},
            {220,7.5},

            {210,8.0},
            {205,8.5},
            {200,9.0},
            {195,9.5},
            {190,10.0},
            {187,10.5},
            {182,11.0},
            {180,11.5},
            {178,12.0},
            {172,12.5},
            {165,13.0},
            {162,13.5},
            {158,14.0},
            {154,14.5},
            {150,15.0},
    };

    private double[][]testSet={
            {1.25, 1},
            {2.25, 1},
            {3.25, 1},
            {8.25, 0},
            {9.25, 0},
            {2.75, 1},
            {11.25,0},
            {11.75, 0},
            {1.75, 1},
    };

    /**
     * default constructor
     */
    public TouchSensorNN(){
        aNeuralNetwork=this.buildNN();
        this.equalFrequencyBinning(aPartitionSize);
    }

    /**
     *
     * @return test set for simulation
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
        NeuralNetwork aPerceptron = new MultiLayerPerceptron(TransferFunctionType.LINEAR, 1, 4, aPartitionSize);
        BackPropagation learningRule = (BackPropagation) aPerceptron.getLearningRule();
        learningRule.setMaxIterations(500);
        learningRule.setLearningRate(0.01);
        learningRule.setMaxError(0.01);

        double[]weights=new double[63];
        for(int i = 0; i < 63; i++){
            weights[i] = 0.15;
        }

        aPerceptron.setWeights(weights);

        return aPerceptron;
    }

    /**
     *
     * @return input data to be train on
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
     * @return expected data to be train on
     */
    @Override
    protected double[][] getExpectedData() {
        double[][] outputData = new double[this.data.length][this.data[0].length];
        for (int i = 0; i < this.data.length; i++) {
            double[]aRow=new double[aPartition.length];
            int k=0;
            for (k=0;k<aRow.length-1;k++){
                if (this.data[i][this.data[i].length - 1] <= aPartition[k]){
                    aRow[k]=1;
                    break;
                }
            }
            if (k==aRow.length-1) {
                aRow[aPartition.length-1] = 1;
            }

            outputData[i] = aRow;
        }
        return outputData;
    }


    /**
     *
     * @return the train dataset
     */
    @Override
    public DataSet generateTrainingSet() {
        double[][] inputData = getInputData();
        DataSet trainSet = new DataSet(inputData[0].length, aPartition.length);
        double[][] expectedData = this.getExpectedData();
        for (int i = 0; i < this.data.length; i++) {
            trainSet.add(new DataSetRow(inputData[i], expectedData[i]));
        }
        return trainSet;
    }

    /**
     *
     * @param pInput the capacitance
     * @return low or strong touch
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
     * @param pPartition the number of classes for this dataset
     * @return the partition of the dataset
     *
     * this method essentially splits the data into equal frequency bins based on the number of classes the user wants
     * for instance, if we want to split the dataset into (light or hard) push, (light or medium or hard) push, etc.
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
