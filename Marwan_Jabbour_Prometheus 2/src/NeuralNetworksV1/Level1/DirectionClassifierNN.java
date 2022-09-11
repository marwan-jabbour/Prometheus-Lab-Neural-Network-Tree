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

public class DirectionClassifierNN extends Neural_Network {
   private double[][] data;
   private double[][]testSet;

    public DirectionClassifierNN() {
        data = readCSV("dataDirectionClassifierNN.csv", 5456, 5);
        aNeuralNetwork=this.buildNN();
        splitTrainTest(50);
    }

    public double[][]getTestSet(){
        return testSet;
    }


    /**
     * Splits the data into training and test sets
     * @param length How large the test set is
     */
    private void splitTrainTest(int length){
        int index=5456/length;
        testSet=new double[length][5];

        for (int i=0;i<length;i++){
            for (int j=0;j<5;j++){
                testSet[i][j]=data[i*index][j];
            }
        }

        double[][]trainSet=new double[5456-length][5];

        int counter=0;
        for (int i=0;i<5456;i++){

            if (i%index==0) {
                counter++;
                continue;
            }

            for (int j=0;j<5;j++){
                trainSet[i-counter][j]=data[i][j];
            }
        }
        data=trainSet;

    }


    /**
     *
     * @param pInput the input
     * @return Whether the robot is moving forward, right, or left
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
     * @param pFileName The name of the file to be read from
     * @param pRows Number of rows in file
     * @param pCols Number of columns in file
     * @return The dataset
     */
    private double[][] readCSV(String pFileName, int pRows, int pCols) {
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

        double [][] tempData=new double[pRows][pCols];

        Iterator<String[]>it=lines.iterator();
        int rowCounter=0;
        while (it.hasNext()){
            String []aRow=it.next();
            for (int i=0;i<aRow.length-1;i++){
                tempData[rowCounter][i]=Double.parseDouble(aRow[i]);
            }
            tempData[rowCounter][4]=labelClass(aRow[4]);
            rowCounter++;
        }

        return tempData;
    }

    /**
     *
     * @param pClass The class of the data row
     * @return The corresponding index
     */
    private int labelClass(String pClass){
        if (pClass.equalsIgnoreCase("Move-Forward"))
            return 0;
       else if (pClass.equalsIgnoreCase("Slight-Right-Turn")||pClass.equalsIgnoreCase("Sharp-Right-Turn"))
            return 1;
        else if (pClass.equalsIgnoreCase("Slight-Left-Turn"))
            return 2;

        else {
            System.out.println(pClass);
            return -1;
        }
    }

    @Override
    protected NeuralNetwork buildNN() {
        NeuralNetwork aPerceptron = new MultiLayerPerceptron(TransferFunctionType.GAUSSIAN, 4, 12, 3);

        BackPropagation learningRule = (BackPropagation) aPerceptron.getLearningRule();
        learningRule.setMaxIterations(1000);
        learningRule.setLearningRate(0.01);
        learningRule.setMaxError(0.01);

        //changing weights of neural network
        double[] weights = new double[63];
        for(int i = 0; i < 63; i++){
            weights[i] = 0.15;
        }
        return aPerceptron;
    }

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


    @Override
    protected double[][] getExpectedData() {
        double[][] outputData = new double[this.data.length][this.data[0].length];
        for (int i = 0; i < this.data.length; i++) {
            double []aRow=new double[3];
           if (this.data[i][this.data[i].length-1]==0)
               aRow= new double[]{1,0,0};
           else if (this.data[i][this.data[i].length-1]==1)
               aRow= new double[]{0,1,0};
            else if (this.data[i][this.data[i].length-1]==2)
                aRow= new double[]{0,0,1};
            //None of the options
            else {
               System.out.println("An error has occured");
               System.exit(0);
           }
            outputData[i] = aRow;
        }
        return outputData;
    }



    @Override
    protected DataSet generateTrainingSet() {
        double[][] inputData = this.getInputData();
        DataSet trainSet = new DataSet(inputData[0].length, 3);
        double[][] expectedData = this.getExpectedData();

        for (int i = 0; i < data.length; i++) {
            trainSet.add(new DataSetRow(inputData[i], expectedData[i]));
        }
        return trainSet;
    }

}
