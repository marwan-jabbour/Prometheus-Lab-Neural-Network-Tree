package NeuralNetworksV2;

import NeuralNetworksV1.Neural_Network;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

import java.util.ArrayList;
import java.util.Arrays;

public class MovableObjectNN_V2 extends Neural_Network {
    protected NeuralNetwork aNeuralNetwork;
    private double[] aPartition;


    //previous touch sensor data
    private double[][] dataTouchSensorNN_a={
            {0.5,1025},
            {1,700},
            {1.5,500},
            {2.0,450},
            {2.5,400},
            {3.0,375},
            {3.5,350},
            {4.0,310},
            {4.5,290},
            {5.0,275},
            {5.5,255},
            {6.0,250},
            {6.5,240},
            {7.0,230},
            {7.5,220},
            /////////
            {8.0,210},
            {8.5,205},
            {9.0,200},
            {9.5,195},
            {10.0,190},
            {10.5,187},
            {11.0,182},
            {11.5,180},
            {12.0,178},
            {12.5,172},
            {13.0,165},
            {13.5,162},
            {14.0,158},
            {14.5,154},
            {15.0,150},

    };

    //current touch sensor data
    private double[][] dataTouchSensorNN_b={
            {0.5,1025},
            {1,700},
            {1.5,500},
            {2.0,450},
            {2.5,400},
            {3.0,375},
            {3.5,350},
            {4.0,310},
            {4.5,290},
            {5.0,275},
            {8.0,210},
            {8.5,205},
            {9.0,200},
            {9.5,195},
            {10.0,190},
            {5.5,255},
            {6.0,250},
            {6.5,240},
            {7.0,230},
            {7.5,220},
            {10.5,187},
            {11.0,182},
            {11.5,180},
            {12.0,178},
            {12.5,172},
            {13.0,165},
            {13.5,162},
            {14.0,158},
            {14.5,154},
            {15.0,150},
    };

    //position data
    private double[][]dataPosition={
            {0,0},
            {0,1},
            {1,0},
            {1,1},
    };


    //touch sensor previous
    private double[][]dataX_1_a;
    private double[][]dataY_1_a;

    //touch sensor current
    private double[][]dataX_1_b;
    private double[][]dataY_1_b;

    public MovableObjectNN_V2(){
        this.equalFrequencyBinning(dataTouchSensorNN_a,2);
        dataX_1_a=readX_TouchSensor(dataTouchSensorNN_a);
        dataX_1_b=readX_TouchSensor(dataTouchSensorNN_b);
        dataY_1_a=readY_TouchSensor(dataTouchSensorNN_a);
        dataY_1_b=readY_TouchSensor(dataTouchSensorNN_b);
        aNeuralNetwork=this.buildNN();
    }


    /**
     *
     * @param pData touch sensor dataset
     * @return Touch sensor x data
     */
    public double[][]readX_TouchSensor(double [][]pData){
        double[][]tempData=new double[pData.length][pData[0].length-1];
        for (int i=0;i<pData.length;i++){
            double[] aRow = new double[1];
            aRow[0]=pData[i][0];
            tempData[i]=aRow;
        }
        return pData;
    }


    /**
     *
     * @param pData touch sensor dataset
     * @return Touch sensor y data
     */
    public double[][] readY_TouchSensor(double [][]pData) {
        double[][] dataY = new double[pData.length][1];
        for (int i = 0; i < pData.length; i++) {
            if (pData[i][pData[i].length - 1] <= aPartition[0]){
                dataY[i][0]=0;
            }
            else if (pData[i][pData[i].length - 1] <= aPartition[1]){
                dataY[i][0]=1;
            }
        }
        return dataY;
    }

    /**
     *
     * @return the built neural network
     */
    public NeuralNetwork buildNN() {

        NeuralNetwork aPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 4, 8, 2);

        BackPropagation learningRule = (BackPropagation) aPerceptron.getLearningRule();
        learningRule.setMaxIterations(5000);
        learningRule.setLearningRate(0.01);
       learningRule.setMaxError(0.01);

        return aPerceptron;
    }


    /**
     *
     * @return The input data for neural network to be trained on
     */
    public double[][] getInputData() {
        double[][] inputData = new double[this.dataX_1_a.length*4][4];

        int counter=0;
        //Combine every row of touch sensor with all the rows of position data
        for (int c1=0;c1<this.dataX_1_a.length*4;c1++){
            //ensures combination of position data and touch sensor data (since position data is smaller)
            if (c1%(dataX_1_a.length)==0&&c1!=0) {
                counter++;
            }

            //input row
            double[] aRow = new double[4];
            aRow[0]=dataX_1_a[c1-counter*dataX_1_a.length][0];
            aRow[1]=dataX_1_b[c1-counter*dataX_1_a.length][0];

            aRow[2]=dataPosition[counter][0];
            aRow[3]=dataPosition[counter][1];
            inputData[c1] = aRow;
        }
        return inputData;
    }


    /**
     *
     * @return The expected data to be trained on
     */
    @Override
    public double[][] getExpectedData() {
        double[][] outputData =new double[this.dataY_1_a.length*4][2];
        double[]mergedOutput=giveMergedOutput();


        for (int i = 0; i < mergedOutput.length; i++) {
            double[]aRow=new double[2];
            if (mergedOutput[i]==0)
                aRow=new double[]{1,0};
            else if (mergedOutput[i]==1)
                aRow=new double[]{0,1};
            outputData[i] = aRow;
        }
        return outputData;
    }

    /**
     *
     * @return The merged output of the dataset
     * this method is the core of this neural network
     * it draws logical outputs from ALL inputs fed into this neural network
     * the output is then used in the training process, along with the inputs themselves
     * This method allows the elimination of the first-level neural networks
     */
    public double[] giveMergedOutput() {
        double[] mergedOutput = new double[dataY_1_a.length*4];

        int counter=0;
        for (int c1=0;c1<this.dataX_1_a.length*4;c1++){

            if (c1%(dataX_1_a.length)==0&&c1!=0) {
                counter++;
            }
            //this is the pattern used:
            // if (t = t x < x ) then 1
            //else then 0
            if (dataY_1_a[c1-counter*dataY_1_a.length][0]==dataY_1_b[c1-counter*dataY_1_b.length][0]&&(dataPosition[counter][0]<dataPosition[counter][1])) {
                mergedOutput[c1] = 1;
            }
            else
                mergedOutput[c1]=0;

        }

        return mergedOutput;
    }


    @Override
    public DataSet generateTrainingSet() {
        double[][] inputData = getInputData();
        DataSet trainSet = new DataSet(inputData[0].length, 2);
        double[][] expectedData = this.getExpectedData();

        for (int i = 0; i < inputData.length; i++) {
            trainSet.add(new DataSetRow(inputData[i], expectedData[i]));
        }
        return trainSet;
    }

    @Override
    public double predictOutput(double[] pInput) {
        aNeuralNetwork.setInput(pInput);
        aNeuralNetwork.calculate();
        double[]actualOutput=aNeuralNetwork.getOutput();
        int index=0;
        double max=actualOutput[0];
        for (int counter=1;counter<actualOutput.length;counter++){
            if (actualOutput[counter]>max){
                max=actualOutput[counter];
            }
        }

        return index;
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
    public double[] equalFrequencyBinning(double[][]pData,int pPartition){
        int length=pData.length;
        int n=length/pPartition;
        double[] output=new double[length];
        for (int i=0;i<length;i++){
            output[i]=pData[i][pData[i].length-1];
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
