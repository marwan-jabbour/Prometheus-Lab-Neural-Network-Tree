package NeuralNetworksV1.Level1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SurfaceClassifierNNTest {
    static double[][]testSet;

    @BeforeAll
    static void setUp(){
        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();
        aNeuralNetwork.trainNN();
        testSet=aNeuralNetwork.getTestSet();
    }

    @Test
    void predictOutputTest_1() {
        int index=0;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_2() {
        int index=1;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_3() {
        int index=2;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_4() {
        int index=3;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_5() {
        int index=4;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_6() {
        int index=5;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_7() {
        int index=6;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_8() {
        int index=7;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_9() {
        int index=8;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_10() {
        int index=9;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_11() {
        int index=10;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_12() {
        int index=11;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_13() {
        int index=12;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_14() {
        int index=13;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_15() {
        int index=14;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_16() {
        int index=15;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_17() {
        int index=16;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_18() {
        int index=17;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_19() {
        int index=18;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_20() {
        int index=19;
        double[]aInput=new double[65];
        for (int i=0;i<65;i++){
            aInput[i]=testSet[index][i];
        }
        double aExpectedOutput=testSet[index][65];

        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();

        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }
}