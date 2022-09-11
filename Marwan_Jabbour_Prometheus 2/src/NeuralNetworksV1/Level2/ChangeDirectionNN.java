package NeuralNetworksV1.Level2;

import NeuralNetworksV1.Level1.DirectionClassifierNN;
import NeuralNetworksV1.Level1.UltraSonicSensorNN;
import NeuralNetworksV1.Neural_Network;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

import java.util.Arrays;

public class ChangeDirectionNN extends Neural_Network {


    private double [][]data={
            //forward: 0
            //right: 1
            //left: 2
            //180 turn: 3
            //180 turn can correspond to either 2 right-turns or 2 left-turns , no distinction
            {0,1,0,0},
            {1,1,0,0},
            {1,1,1,0},
            {0,1,1,0},
            {1,0,0,2},
            {0,0,1,1},
            {1,0,1,1},
            {0,0,0,3},

    };


    public ChangeDirectionNN(){
        //generateDataSets();
        aNeuralNetwork=this.buildNN();
    }

    private double[][]tempTestSet=null;

    //Depending on the direction of the robot, there are 4 different datasets
    //these datasets are identical but shifted
    //E.X: when the robot turns right: up becomes left, right becomes forward, left becomes backward, and downward becomes right
    private double[][]testSetForward;
    private double[][]testSetDownward;
    private double[][]testSetLeft;
    private double[][]testSetRight;

    /**
     *
     * @param size the size of the grid
     * @param obstacles the obstacles in the grid
     * this method generates the test sets based on the obstacle locations on the grid
     *
     */

    public void generateTestSets(int size, int[]obstacles){
        tempTestSet= new double[size][4];

        for (int i=0;i<size;i++){
            //We assume there are no obstacles around the robot ( 1 means far)
            double []aRow={1,1,1,1};

            //if there are obstacles, set the index as 0 (close)
            for (int j=0;j<obstacles.length;j++){
                if (i+1==obstacles[j])
                    aRow[2]=0;
                if (i-1==obstacles[j])
                    aRow[0]=0;
                if (i-Math.sqrt(size)==obstacles[j])
                    aRow[1]=0;
                if (i+Math.sqrt(size)==obstacles[j])
                    aRow[3]=0;
            }

            //Check the blocks next to the walls of the grid
            if (i<Math.sqrt(size))
                aRow[1]=0;
            if (Math.abs(i-size)<=Math.sqrt(size))
                aRow[3]=0;
            if (i%Math.sqrt(size)==0)
                aRow[0]=0;
            if ((i+1)%Math.sqrt(size)==0)
                aRow[2]=0;


            tempTestSet[i]=aRow;
        }

        generateDataSets();
    }


    private void generateDataSets(){
        testSetForward=new double[tempTestSet.length][tempTestSet[0].length];
        testSetLeft=new double[tempTestSet.length][tempTestSet[0].length];
        testSetRight=new double[tempTestSet.length][tempTestSet[0].length];
        testSetDownward=new double[tempTestSet.length][tempTestSet[0].length];

        for (int i=0;i<tempTestSet.length;i++){
            double []row_forward=new double[tempTestSet[0].length];
            double []row_right=new double[tempTestSet[0].length];
            double []row_left=new double[tempTestSet[0].length];
            double []row_downward=new double[tempTestSet[0].length];


            for (int k=0;k<3;k++){
                row_forward[k]=tempTestSet[i][k];
            }

            //Perform shifting for the other datasets

            for (int k=0;k<3;k++){
                row_right[k]=tempTestSet[i][k+1];
            }

            row_left[0]=tempTestSet[i][3];
            row_left[1]=tempTestSet[i][0];
            row_left[2]=tempTestSet[i][1];


            row_downward[0]=tempTestSet[i][2];
            row_downward[1]=tempTestSet[i][3];
            row_downward[2]=tempTestSet[i][0];


            //Set the outputs by comparing with the original dataset
            for (int j=0;j<data.length;j++){
                if ((row_forward[0]==data[j][0])&&(row_forward[1]==data[j][1])&&(row_forward[2]==data[j][2])){
                    row_forward[3]=data[j][3];
                    break;
                }
            }

            for (int j=0;j<data.length;j++){
                if ((row_right[0]==data[j][0])&&(row_right[1]==data[j][1])&&(row_right[2]==data[j][2])){
                    row_right[3]=data[j][3];
                    break;
                }
            }

            for (int j=0;j<data.length;j++){
                if ((row_left[0]==data[j][0])&&(row_left[1]==data[j][1])&&(row_left[2]==data[j][2])){
                    row_left[3]=data[j][3];
                    break;
                }
            }

            for (int j=0;j<data.length;j++){
                if ((row_downward[0]==data[j][0])&&(row_downward[1]==data[j][1])&&(row_downward[2]==data[j][2])){
                    row_downward[3]=data[j][3];
                    break;
                }
            }


            testSetLeft[i]=row_left;
            testSetRight[i]=row_right;
            testSetForward[i]=row_forward;
            testSetDownward[i]=row_downward;

        }
    };


    //return the datasets for each direction
    public double[][]getTestSetLeft(){
        return testSetLeft;
    }
    public double[][]getTestSetRight(){
        return testSetRight;
    }
    public double[][]getTestSetForward(){
        return testSetForward;
    }
    public double[][]getTestSetDownward(){
        return testSetDownward;
    }

    @Override
    protected NeuralNetwork buildNN() {
        NeuralNetwork aPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 3, 20, 4);
        BackPropagation learningRule = (BackPropagation) aPerceptron.getLearningRule();
        learningRule.setMaxIterations(10000);
        learningRule.setLearningRate(0.01);
        learningRule.setMaxError(0.01);
        return aPerceptron;
    }

    /**
     *
     * @param pInput The network processed input
     * @return the next direction the robot should take

     */

    @Override
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
            double[]aRow=new double[4];
            if (this.data[i][this.data[i].length - 1]==0)
                aRow=new double[]{1,0,0,0};
            else if (this.data[i][this.data[i].length - 1]==1)
                aRow=new double[]{0,1,0,0};
            else if (this.data[i][this.data[i].length - 1]==2)
                aRow=new double[]{0,0,1,0};
            else if (this.data[i][this.data[i].length - 1]==3)
                aRow=new double[]{0,0,0,1};


            outputData[i] = aRow;
        }
        return outputData;
    }



    @Override
    protected DataSet generateTrainingSet() {
        double[][] inputData = getInputData();
        DataSet trainSet = new DataSet(inputData[0].length, 4);
        double[][] expectedData = this.getExpectedData();

        for (int i = 0; i < this.data.length; i++) {
            trainSet.add(new DataSetRow(inputData[i], expectedData[i]));
        }
        return trainSet;
    }



//Following deprecated method has no immediate purpose right now:

//    public double predictOutputFromData(double []pInput) {
//        //Ultrasonic
//        double []input1=new double[1];
//        double []input2=new double[1];
//        double []input3=new double[1];
//
//        //Direction
//        double []input4=new double[4];
//
//        for (int i=0;i< pInput.length;i++) {
//            if (i < 1)
//                input1[i] = pInput[i];
//            else if (i<2)
//                input2[i-1] = pInput[i];
//            else if (i<3)
//                input3[i-2] = pInput[i];
//            else
//                input4[i-3] = pInput[i];
//        }
//
//        UltraSonicSensorNN ultraSonicSensorNN = new UltraSonicSensorNN();
//        ultraSonicSensorNN.trainNN();
//
//        double output1= ultraSonicSensorNN.predictOutput(input1);
//        double output2= ultraSonicSensorNN.predictOutput(input2);
//        double output3= ultraSonicSensorNN.predictOutput(input3);
//
//        DirectionClassifierNN directionClassifierNN = new DirectionClassifierNN();
//        directionClassifierNN.trainNN();
//
//        double output4= directionClassifierNN.predictOutput(input4);
//
//
//        System.out.println("Inside NeuralNetworksV1.Level2.RotationIntensityNN, NeuralNetworksV1.Level1.UltraSonicSensorNN: "+output1+" "+output2+" "+output3);
//        System.out.println("Inside NeuralNetworksV1.Level2.RotationIntensityNN, NeuralNetworksV1.Level1.DirectionClassifierNN: "+output4);
//
//        double[]tempInput=new double[4];
//        tempInput[0]=output1;
//        tempInput[1]=output2;
//        tempInput[2]=output3;
//        tempInput[3]=output4;
//
//
//        aNeuralNetwork.setInput(tempInput);
//        aNeuralNetwork.calculate();
//        double[] output = aNeuralNetwork.getOutput();
//        int index=giveMeIndex(output);
//        System.out.println("Result "+index);
//        return index;
//    }



}
