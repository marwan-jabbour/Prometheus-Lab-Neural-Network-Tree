package NeuralNetworksV2;

import NeuralNetworksV1.Level1.SurfaceClassifierNN;
import NeuralNetworksV1.Neural_Network;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReachableDestinationNN_V2_Test {
    static double[][]testSet;

    @BeforeAll
    static void setUp(){
        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();
        aNeuralNetwork.trainNN();
        testSet=aNeuralNetwork.getTestSet();
    }

    @Test
    void predictOutputTest_1() {
        double []aInput=new double[68];
        double aExpectedOutput=0;
        int index=0;
        aInput[0]=1;
        aInput[1]=9.75;
        aInput[2]=3;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }
        Neural_Network aNeuralNetwork=new ReachableDestinationNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_2() {
        double []aInput=new double[68];
        double aExpectedOutput=0;
        int index=1;
        aInput[0]=1;
        aInput[1]=9.3;
        aInput[2]=1.6;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }
        Neural_Network aNeuralNetwork=new ReachableDestinationNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_3() {
        double []aInput=new double[68];
        double aExpectedOutput=0;
        int index=2;
        aInput[0]=10;
        aInput[1]=8.75;
        aInput[2]=1.55;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }
        Neural_Network aNeuralNetwork=new ReachableDestinationNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_4() {
        double []aInput=new double[68];
        double aExpectedOutput=0;
        int index=3;
        aInput[0]=1;
        aInput[1]=8.25;
        aInput[2]=2.95;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }
        Neural_Network aNeuralNetwork=new ReachableDestinationNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_5() {
        double []aInput=new double[68];
        double aExpectedOutput=0;
        int index=4;
        aInput[0]=1;
        aInput[1]=7.25;
        aInput[2]=2.80;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }
        Neural_Network aNeuralNetwork=new ReachableDestinationNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_6() {
        double []aInput=new double[68];
        double aExpectedOutput=1;
        int index=5;
        aInput[0]=1;
        aInput[1]=6.25;
        aInput[2]=1.15;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }
        Neural_Network aNeuralNetwork=new ReachableDestinationNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_7() {
        double []aInput=new double[68];
        double aExpectedOutput=1;
        int index=6;
        aInput[0]=1;
        aInput[1]=5.25;
        aInput[2]=0.95;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }
        Neural_Network aNeuralNetwork=new ReachableDestinationNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }
}
