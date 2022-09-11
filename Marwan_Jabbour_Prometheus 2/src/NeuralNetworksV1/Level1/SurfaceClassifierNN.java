package NeuralNetworksV1.Level1;

import NeuralNetworksV1.Neural_Network;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

import java.io.*;
import java.util.*;

public class SurfaceClassifierNN extends Neural_Network {
   private double[][] trainData_X;
   private double[][] trainData_Y;
   private double[][]testSet;


    public SurfaceClassifierNN() {
        trainData_X = readXCSV("X_train.csv", 487681, 13);
        trainData_X=featureEngineering(trainData_X);
        trainData_X=featureEngineeringStats(trainData_X);
        trainData_Y= readYCSV("y_train.csv", 3810, 3);

        aNeuralNetwork=this.buildNN();
    }

    public double[][]getTestSet(){
        return testSet;
    }

    /**
     * Splits dataset into train and test
     * @param length the size of the test set
     */
    private void splitTrainTest(int length){
        int index=3810/length;
        testSet=new double[length][66];

        for (int i=0;i<length;i++){
            for (int j=0;j<65;j++){
                testSet[i][j]=trainData_X[i*index][j];
            }
        }

        for (int i=0;i<length;i++){
                testSet[i][65]=trainData_Y[i*index][0];

        }

        double[][]trainSet=new double[3810-25][65];
        double[][]trainSet_Y=new double[3810-25][65];


        int counter=0;
        for (int i=0;i<3810;i++){

            if (i%index==0) {
                counter++;
                continue;
            }

            for (int j=0;j<65;j++){
                trainSet[i-counter][j]=trainData_X[i][j];
            }
        }

        int counter2=0;

        for (int i=0;i<3810;i++){
            if (i%index==0) {
                counter2++;
                continue;
            }

            for (int j=0;j<1;j++){
                trainSet_Y[i-counter2][j]=trainData_Y[i][j];
            }
        }

        trainData_X=trainSet;
        trainData_Y=trainSet_Y;

    }


    /**
     *
     * @param pInput The input after feature engineering
     * @return Whether surface is low or high friction
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
     * @param pFileName name of x data
     * @param pRows rows in x data
     * @param pCols columns in x data
     * @return the X dataset
     */
    private double[][] readXCSV(String pFileName, int pRows, int pCols) {
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

        Iterator<String[]>it=lines.iterator();
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

    private int labelClass(String pClass){

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

    /**
     *
     * @param pFileName name of Y data
     * @param pRows rows in Y data
     * @param pCols columns in Y data
     * @return the Y dataset (output)
     */
    private double[][] readYCSV(String pFileName, int pRows, int pCols) {
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
            int temp=labelClass(aRow[2]);


            //I consider these floor types to be of high friction
            if (temp==5||temp==0)
            tempData[rowCounter][0]=0;
            else
                //Low friction floor types
                tempData[rowCounter][0]=1;
            rowCounter++;
        }

        return tempData;
    }

    /**
     *
     * @param pArray The dataset
     * @return The dataset after calculating mean, median, max, min, and standard deviation
     * (second round of feature engineering)
     */
    private double[][]featureEngineeringStats(double [][]pArray){

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

    /**
     *
     * @param pData The data array
     * @return The corresponding standard deviation
     */
    private double calculateSTD(double []pData){
        double temp=0;
        double mean=calculateMean(pData);
        for (int i=0;i<pData.length;i++){
            temp+=Math.pow(pData[i] - mean, 2);
        }
        return Math.sqrt(temp/pData.length);
    }


    /**
     *
     * @param pData The data array
     * @return The corresponding mean
     */
    private double calculateMean(double []pData){
        double total=0;
        for (int i=0;i<pData.length;i++){
            total+=pData[i];
        }
        return total/pData.length;
    }

    /**
     *
     * @param pData The data array
     * @return The corresponding max
     */
    private double calculateMax(double []pData){
        double max=pData[0];
        for (int i=1;i<pData.length;i++){
            if (pData[i]>max)
                max=pData[i];
        }
        return max;
    }

    /**
     *
     * @param pData The data array
     * @return The corresponding min
     */
    private double calculateMin(double []pData){
        double min=pData[0];
        for (int i=1;i<pData.length;i++){
            if (pData[i]<min)
                min=pData[i];
        }
        return min;
    }

    /**
     *
     * @param pData The data array
     * @return The corresponding median
     */
    private double calculateMedian(double []pData){
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
     * @param pArray The dataset
     * @return The dataset after calculating the totals (first round of feature engineering)
     *
     *         // data['angular_velocity_total'] = (data['angular_velocity_X']**2 + data['angular_velocity_Y']**2 + data['angular_velocity_Z']**2)** 0.5
     *         // data['linear_acceleration_total'] = (data['linear_acceleration_X']**2 + data['linear_acceleration_Y']**2 + data['linear_acceleration_Z']**2)**0.5
     *         // data['orientation_total'] = (data['orientation_X']**2 + data['orientation_Y']**2 + data['orientation_Z']**2)**0.5
     */
    private double[][]featureEngineering(double [][]pArray){

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



    @Override
    protected NeuralNetwork buildNN() {
        NeuralNetwork aPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 65, 10, 2);

        BackPropagation learningRule = (BackPropagation) aPerceptron.getLearningRule();
        learningRule.setMaxIterations(500);
        learningRule.setLearningRate(0.01);
        learningRule.setMaxError(0.01);

        return aPerceptron;
    }

    @Override
    protected double[][] getInputData() {
        double [][]input=new double[trainData_X.length][trainData_X[0].length];
        for (int i=0;i< trainData_X.length;i++){
            for (int j=0;j< trainData_X[i].length;j++)
                input[i][j]=trainData_X[i][j];

        }
        return input;
    }

    @Override
    protected double[][] getExpectedData() {
        double[][]temp=new double[trainData_Y.length][2];
        for (int i=0;i< trainData_Y.length;i++){
            double [] aRow=new double[2];
            if (trainData_Y[i][0]==-1) {
                continue;
            }

            aRow[(int) trainData_Y[i][0]]=1;
            temp[i]=aRow;
        }

        return temp;
    }

    @Override
    public void trainNN() {
        DataSet trainSet=this.generateTrainingSet();
        splitTrainTest(25);
        aNeuralNetwork.learn(trainSet);

    }

    @Override
    protected DataSet generateTrainingSet() {
        double[][] inputData = this.getInputData();
        DataSet trainSet = new DataSet(inputData[0].length, 2);
        double[][] expectedData = this.getExpectedData();

        for (int i = 0; i < inputData.length; i++) {
            trainSet.add(new DataSetRow(inputData[i], expectedData[i]));
        }
        return trainSet;
    }


}
