package NeuralNetworksV2;

import NeuralNetworksV1.Neural_Network;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

import java.util.Arrays;

public class BatteryFailureNN_V2 extends Neural_Network {
    protected NeuralNetwork aNeuralNetwork;

    private double[][]dataChargingSafetyNN={
            //Safe
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
            {15,62,0},

            //Safe
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
            {15,62,0},
            //Safe
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
            {15,62,0},
            //Safe
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
            {15,62,0},


    };
    private double[][]dataChargeBattery={
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
            {1,10,0,1.0},
            {1,9.0,0.275,1.0},
            {1,8.0,0.91,1.0},
            {1,7.0,0.955,1.0},
            {1,6.0,0.98,1.0},
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
            {10,9.6,0.0,0.805},
            {10,8.5,0.3,0.805},
            {10,8.0,0.64,0.805},
            {10,7.5,0.72,0.805},
            {10,7.0,0.75,0.805},
            {10,6.5,0.77,0.805},
            {10,6.0,0.78,0.805},
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
            {50,9.25,0.0,0.2825},
            {50,8.5,0.04,0.2825},
            {50,8.0,0.095,0.2825},
            {50,7.5,0.2,0.2825},
            {50,7.0,0.24,0.2825},
            {50,6.5,0.265,0.2825},
            {50,6.0,0.275,0.2825},
            {50,5.0,0.2825,0.2825},

            //Input Current = 100 mA
            {100,9.0,0.0,0.125},
            {100,8.5,0.01,0.125},
            {100,8.0,0.022,0.125},
            {100,7.5,0.07,0.125},
            {100,7.0,0.1,0.125},
            {100,6.5,0.105,0.125},
            {100,6.0,0.11,0.125},
            {100,6.0,0.115,0.125},
            {100,5.0,0.125,0.125},
            {100,9.0,0.0,0.125},
            {100,8.5,0.01,0.125},
            {100,8.0,0.022,0.125},
            {100,7.5,0.07,0.125},
            {100,7.0,0.1,0.125},
            {100,6.5,0.105,0.125},
            {100,6.0,0.11,0.125},
            {100,6.0,0.115,0.125},
            {100,5.0,0.125,0.125},
            {100,9.0,0.0,0.125},
            {100,8.5,0.01,0.125},
            {100,8.0,0.022,0.125},
            {100,7.5,0.07,0.125},
            {100,7.0,0.1,0.125},
            {100,6.5,0.105,0.125},
            {100,6.0,0.11,0.125},
            {100,6.0,0.115,0.125},
            {100,5.0,0.125,0.125},
            {100,5.0,0.125,0.125},
    };


    //data for first neural network (charging safety)
    private double[][]dataX_1;
    private double[][]dataY_1;
    //data for second neural network (charge battery)
    private double[][]dataX_2;
    private double[][]dataY_2;


    public BatteryFailureNN_V2(){
        //read the data for the PREVIOUSLY separate neural networks
        dataX_1=readX_ChargingSafety(dataChargingSafetyNN);
        dataY_1=readY_ChargingSafety(dataChargingSafetyNN);
        dataX_2=readX_ChargeBattery(dataChargeBattery);
        dataY_2=readY_ChargeBattery(dataChargeBattery);

        aNeuralNetwork=this.buildNN();

    }


    /**
     *
     * @param pData The dataset for charge battery
     * @return the x data (no output)
     */
    public double[][]readX_ChargeBattery(double [][]pData){
        double[][]dataX= new double[pData.length][2];
        for (int i=0;i<pData.length;i++){
            dataX[i][0]=pData[i][0];
            dataX[i][1]=pData[i][1];
        }
        return dataX;
    }

    /**
     *
     * @param pData The dataset for charge battery
     * @return the y data (output)
     */
    public double[][]readY_ChargeBattery(double [][]pData){
        double[][]dataY= new double[pData.length][1];
        for (int i=0;i<pData.length;i++){
            double percent=(pData[i][2]/pData[i][3])*100;
            //No need charge
            if (percent>=50){
                dataY[i][0]=1;
            }
            //Must charge
            else {
                dataY[i][0]=0;
            }
        }
        return dataY;
    }


    /**
     *
     * @param pData The dataset for charging safety
     * @return the x data (no output)
     */
    public double[][]readX_ChargingSafety(double [][]pData){
            double[][]dataX= new double[pData.length][2];
            for (int i=0;i<pData.length;i++){
                dataX[i][0]=pData[i][0];
                dataX[i][1]=pData[i][1];
            }
            return dataX;
    }

    /**
     *
     * @param pData The dataset for charging safety
     * @return the y data (output)
     */
    public double[][]readY_ChargingSafety(double [][]pData){
        double[][]dataY= new double[pData.length][1];
        for (int i=0;i<pData.length;i++){
            dataY[i][0]=pData[i][2];
        }
        return dataY;
    }


    /**
     *
     * @return the built neural network
     */
    public NeuralNetwork buildNN() {

        NeuralNetwork aPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 4, 12, 2);

        BackPropagation learningRule = (BackPropagation) aPerceptron.getLearningRule();
        learningRule.setMaxIterations(1000);
        learningRule.setLearningRate(0.01);
        learningRule.setMaxError(0.01);

        return aPerceptron;
    }


    /**
     *
     * @return The input data for neural network to be trained on
     */
    @Override
    public double[][] getInputData() {
        double[][] inputData = new double[this.dataX_2.length][4];
        for (int c1=0;c1<this.dataX_2.length;c1++){
            double[] aRow = new double[4];
            aRow[0]=dataX_1[c1][0];
            aRow[1]=dataX_1[c1][1];
            aRow[2]=dataX_2[c1][0];
            aRow[3]=dataX_2[c1][1];

            inputData[c1] = aRow;
        }
        return inputData;
    }

    /**
     *
     * @return The expected data for neural network to be trained on
     */
    @Override
    public double[][] getExpectedData() {
        double[][] outputData =new double[this.dataY_2.length][2];
        double[]mergedOutput=giveMergedOutput();
        for (int i = 0; i < this.dataX_1.length; i++) {
            double[]aRow=new double[4];
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
        double[] mergedOutput = new double[dataY_1.length];

        for (int i=0;i<dataY_1.length;i++) {

            //temperature check (extreme temperature)
            if ((dataX_1[i][1] <-20||(dataX_1[i][1] >=30))){
                if (dataY_1[i][0]==1)
                    //unlikely to fail
                    mergedOutput[i] = 0;
                else if (dataY_1[i][0]==0)
                    //likely to fail
                    mergedOutput[i] = 1;
            }


            //moderate temperature
            else{
                if (dataY_1[i][0]==1)
                    //unlikely to fail
                    mergedOutput[i] = 0;
                else{
                    if (dataY_2[i][0]==1)
                        //unlikely to fail
                        mergedOutput[i] = 0;
                    else
                        //likely to fail
                        mergedOutput[i] = 1;
                }
            }
        }
        return mergedOutput;
    }

    @Override
    public DataSet generateTrainingSet() {
        double[][] inputData = getInputData();
        DataSet trainSet = new DataSet(inputData[0].length, 2);
        double[][] expectedData = this.getExpectedData();

        for (int i = 0; i < this.dataY_1.length; i++) {
            trainSet.add(new DataSetRow(inputData[i], expectedData[i]));
        }
        return trainSet;
    }



    /**
     *
     * @param pInput The raw data for this neural network: temp - volt (charge battery) - current - volt (charging safety)
     * @return battery likely to fail or not
     */
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
                index=counter;
            }
        }

        return index;
    }





}

