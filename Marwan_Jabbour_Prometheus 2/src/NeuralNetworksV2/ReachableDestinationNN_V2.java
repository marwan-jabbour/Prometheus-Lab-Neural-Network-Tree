package NeuralNetworksV2;

import NeuralNetworksV1.Neural_Network;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ReachableDestinationNN_V2 extends Neural_Network {
    protected NeuralNetwork aNeuralNetwork;

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


    private double[][] dataUltraSonicSensor={
            ////////
            {3,7},
            {2.9,7.5},
            {2.75,8},
            {2.5,9},
            {2.25,10.5},
            {2,12.5},
            {1.9,13},
            ////////////
            {1.75,14},
            {1.5,16},
            {1.25,21},
            {1,27.5},
            {0.9,32},
            {0.75,37.5},
            {0.5,60},
           ////////
            {3,7},
            {2.9,7.5},
            {2.75,8},
            {2.5,9},
            {2.25,10.5},
            {2,12.5},
            {1.9,13},
            ////////////
            {1.75,14},
            {1.5,16},
            {1.25,21},
            {1,27.5},
            {0.9,32},
            {0.75,37.5},
            {0.5,60},
            ////////
            {3,7},
            {2.9,7.5},
            {2.75,8},
            {2.5,9},
            {2.25,10.5},
            {2,12.5},
            {1.9,13},
            ////////////
            {1.75,14},
            {1.5,16},
            {1.25,21},
            {1,27.5},
            {0.9,32},
            {0.75,37.5},
            {0.5,60},
            ////////
            {3,7},
            {2.9,7.5},
            {2.75,8},
            {2.5,9},
            {2.25,10.5},
            {2,12.5},
            {1.9,13},
            ////////////
            {1.75,14},
            {1.5,16},
            {1.25,21},
            {1,27.5},
            {0.9,32},
            {0.75,37.5},
            {0.5,60},
            ////////
            {3,7},
            {2.9,7.5},
            {2.75,8},
            {2.5,9},
            {2.25,10.5},
            {2,12.5},
            {1.9,13},
            ////////////
            {1.75,14},
            {1.5,16},
            {1.25,21},
            {1,27.5},
            {0.9,32},
            {0.75,37.5},
            {0.5,60},
            ////////
            {3,7},
            {2.9,7.5},
            {2.75,8},
            {2.5,9},
            {2.25,10.5},
            {2,12.5},
            {1.9,13},
            ////////////
            {1.75,14},
            {1.5,16},
            {1.25,21},
            {1,27.5},
            {0.9,32},
            {0.75,37.5},
            {0.5,60},
            ////////
            {3,7},
            {2.9,7.5},
            {2.75,8},
            {2.5,9},
            {2.25,10.5},
            {2,12.5},
            {1.9,13},
            ////////////
            {1.75,14},
            {1.5,16},
            {1.25,21},
            {1,27.5},
            {0.9,32},
            {0.75,37.5},
            {0.5,60},
            ////////
            {3,7},
            {2.9,7.5},
            {2.75,8},
            {2.5,9},
            {2.25,10.5},
            {2,12.5},
            {1.9,13},
            ////////////
            {1.75,14},
            {1.5,16},
            {1.25,21},
            {1,27.5},
            {0.9,32},
            {0.75,37.5},
            {0.5,60}
    };



    //charge battery data
    private double[][]dataX_1;
    private double[][]dataY_1;


    //ultrasonic sensor data
    private double[][]dataX_2;
    private double[][]dataY_2;

    //surface classifier data
    private double[][]dataX_3;
    private double[][]dataY_3;


    public ReachableDestinationNN_V2(){

        dataX_1=readX_ChargeBattery(dataChargeBattery);
        dataY_1=readY_ChargeBattery(dataChargeBattery);

        dataX_2=readX_UltraSonic(dataUltraSonicSensor);
        dataY_2=readY_UltraSonic(dataUltraSonicSensor);


        dataX_3 = readXCSV_Surface("X_train.csv", 487681, 13);
        dataX_3=featureEngineering(dataX_3);
        dataX_3=featureEngineeringStats(dataX_3);
        dataY_3= readYCSV_Surface("y_train.csv", 3810, 3);

        aNeuralNetwork=this.buildNN();

    }


    /**
     *
     * @param pArray
     * @return Dataset after round 2 of feature engineering : calculate mean, median, max, min, standard deviation
     */
    public double[][]featureEngineeringStats(double [][]pArray){

        double[][]newData=new double[pArray.length/128][65];
        int rowNewData=0;
        int counter=0;
        int bigCounter=0;

        double[][]dFrame=new double[15][128];
        for (int i=0;i<pArray.length;i++){
            for (int j=0;j<15;j++){
                dFrame[j][i-128*bigCounter]=pArray[i][j];

            }

            counter++;
            if (counter==128){
                int newDataCounter=0;
                for (int p=0;p<13;p++){
                    newData[bigCounter][newDataCounter++]=calculateMean(dFrame[p+2]);
                    newData[bigCounter][newDataCounter++]=calculateMedian(dFrame[p+2]);
                    newData[bigCounter][newDataCounter++]=calculateMax(dFrame[p+2]);
                    newData[bigCounter][newDataCounter++]=calculateMin(dFrame[p+2]);
                    newData[bigCounter][newDataCounter++]=calculateSTD(dFrame[p+2]);
                }


                bigCounter++;
                counter=0;
                dFrame=new double[15][128];

            }
        }

        pArray=newData;
        return newData;
    }

    public double calculateSTD(double []pData){
        double temp=0;
        double mean=calculateMean(pData);


        for (int i=0;i<pData.length;i++){
            temp+=Math.pow(pData[i] - mean, 2);
        }
        return Math.sqrt(temp/pData.length);
    }

    public double calculateMean(double []pData){

        double total=0;
        for (int i=0;i<pData.length;i++){
            total+=pData[i];
        }
        return total/pData.length;


    }

    public double calculateMax(double []pData){
        double max=pData[0];
        for (int i=1;i<pData.length;i++){
            if (pData[i]>max)
                max=pData[i];
        }
        return max;
    }

    public double calculateMin(double []pData){
        double min=pData[0];
        for (int i=1;i<pData.length;i++){
            if (pData[i]<min)
                min=pData[i];
        }
        return min;
    }

    public double calculateMedian(double []pData){
        double []tempData=new double[pData.length];
        for (int i=0;i<pData.length;i++){
            tempData[i]=pData[i];
        }

        Arrays.sort(pData);

        int mid = pData.length/2;
        if (pData.length%2 == 1) {
            return pData[mid];
        } else {
            return (pData[mid]+pData[mid-1] ) / 2.0;
        }
    }

    /**
     *
     * @param pArray
     * @return Dataset after round 1 of feature engineering : calculate totals
     */
    public double[][]featureEngineering(double [][]pArray){
        // data['angular_velocity_total'] = (data['angular_velocity_X']**2 + data['angular_velocity_Y']**2 + data['angular_velocity_Z']**2)** 0.5
        // data['linear_acceleration_total'] = (data['linear_acceleration_X']**2 + data['linear_acceleration_Y']**2 + data['linear_acceleration_Z']**2)**0.5
        // data['orientation_total'] = (data['orientation_X']**2 + data['orientation_Y']**2 + data['orientation_Z']**2)**0.5

        double [][]newData=new double[pArray.length][pArray[0].length+3];
        for (int i=0;i<pArray.length;i++){
            int j=0;
            for (j=0;j<pArray[i].length;j++){
                newData[i][j]=pArray[i][j];
            }
            newData[i][j]= Math.pow((Math.pow(pArray[i][6],2)+Math.pow(pArray[i][7],2)+Math.pow(pArray[i][8],2)),0.5);
            newData[i][j+1]= Math.pow((Math.pow(pArray[i][9],2)+Math.pow(pArray[i][10],2)+Math.pow(pArray[i][11],2)),0.5);
            newData[i][j+2]= Math.pow((Math.pow(pArray[i][2],2)+Math.pow(pArray[i][3],2)+Math.pow(pArray[i][4],2)),0.5);

        }
        return newData;
    }



    public int labelClassSurface(String pClass){

        if (pClass.equalsIgnoreCase("carpet"))
            return 0;
        else if (pClass.equalsIgnoreCase("concrete"))
            return 1;
        else if (pClass.equalsIgnoreCase("fine_concrete"))
            return 2;
        else if (pClass.equalsIgnoreCase("hard_tiles"))
            return 3;
        else if (pClass.equalsIgnoreCase("hard_tiles_large_space"))
            return 4;
        else if (pClass.equalsIgnoreCase("soft_pvc"))
            return 5;
        else if (pClass.equalsIgnoreCase("soft_tiles"))
            return 6;
        else if (pClass.equalsIgnoreCase("tiled"))
            return 7;
        else if (pClass.equalsIgnoreCase("wood"))
            return 8;
        else
            return -1;
    }


    public double[][] readXCSV_Surface(String pFileName, int pRows, int pCols) {
        List<String[]> lines = new ArrayList<String[]>();
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(pFileName));
            while ((line = br.readLine()) != null)
            {
                String[] aLine = line.split(splitBy);
                lines.add(aLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double [][] tempData=new double[pRows][pCols-1];

        Iterator<String[]> it=lines.iterator();
        int rowCounter=0;
        it.next();
        while (it.hasNext()){
            String []aRow=it.next();
            for (int i=0;i<aRow.length-1;i++){
                tempData[rowCounter][i]=Double.parseDouble(aRow[i+1]);
            }
            rowCounter++;
        }

        return tempData;
    }

    public double[][] readYCSV_Surface(String pFileName, int pRows, int pCols) {
        List<String[]> lines = new ArrayList<String[]>();
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(pFileName));
            while ((line = br.readLine()) != null)
            {
                String[] aLine = line.split(splitBy);
                lines.add(aLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double [][] tempData=new double[pRows][1];

        Iterator<String[]>it=lines.iterator();
        int rowCounter=0;
        it.next();
        while (it.hasNext()){
            String []aRow=it.next();
            int temp=labelClassSurface(aRow[2]);

            //tempData[rowCounter][0]=temp;
            if (temp==5||temp==0)
                tempData[rowCounter][0]=0;
            else
                tempData[rowCounter][0]=1;
            rowCounter++;
        }

        return tempData;
    }


    /**
     *
     * @param pData dataset for ultrasonic
     * @return the x data (voltage)
     */
    public double[][]readX_UltraSonic(double [][]pData){
        double [][] tempData=new double[pData.length][pData[0].length-1];
        for (int i=0;i<pData.length;i++){
            double[] aRow=new double[pData[0].length-1];
            aRow[0]=pData[i][0];
            tempData[i]=aRow;
        }
        return tempData;

    }
    /**
     *
     * @param pData dataset for ultrasonic
     * @return the y data
     */
    public double[][]readY_UltraSonic(double [][]pData){
        double [][] tempData=new double[pData.length][pData[0].length-1];
        for (int i=0;i<pData.length;i++){
            double[] aRow=new double[pData[0].length-1];
            if (pData[i][1]<=13)
                aRow[0]=0;
            else
                aRow[0]=1;
            tempData[i]=aRow;

        }
        return tempData;

    }

    /**
     *
     * @param pData dataset for charge battery
     * @return the x data set
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
     * @param pData dataset for charge battery
     * @return the y data set
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
     * @return the built neural network
     */
    public NeuralNetwork buildNN() {

        NeuralNetwork aPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 3+65, 12, 2);

        BackPropagation learningRule = (BackPropagation) aPerceptron.getLearningRule();
        learningRule.setMaxIterations(1000);
        learningRule.setLearningRate(0.01);
        learningRule.setMaxError(0.01);

        return aPerceptron;
    }


    /**
     *
     * @return input data to train neural network
     */
    public double[][] getInputData() {
        double[][] inputData = new double[this.dataX_1.length][68];
        for (int c1=0;c1<this.dataX_1.length;c1++){
            double[] aRow = new double[68];
            aRow[0]=dataX_1[c1][0];
            aRow[1]=dataX_1[c1][1];
            aRow[2]=dataX_2[c1][0];

            for (int c2=0; c2<65; c2++){
                aRow[c2+3]=dataX_3[c1][c2];
            }
            inputData[c1] = aRow;
        }
        return inputData;
    }

    /**
     *
     * @return expected data to train neural network
     */
    @Override
    public double[][] getExpectedData() {
        double[][] outputData =new double[this.dataY_1.length][2];
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
     * @return merged output
     */
    public double[] giveMergedOutput() {
        double[] mergedOutput = new double[dataY_1.length];
        for (int i=0;i<dataY_1.length;i++) {
            if (dataY_1[i][0]==1&&dataY_2[i][0]==1)
                mergedOutput[i] = 1;
            else
                mergedOutput[i] = 0;
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


}

