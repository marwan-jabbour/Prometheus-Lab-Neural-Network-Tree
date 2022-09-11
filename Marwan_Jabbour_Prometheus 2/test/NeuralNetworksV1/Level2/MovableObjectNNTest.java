package NeuralNetworksV1.Level2;

import NeuralNetworksV1.Neural_Network;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovableObjectNNTest {


    @Test
    void predictOutputTest_1() {
        double[]aInput={0,0,0,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_2() {
        double[]aInput={0,0,0,1};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new MovableObjectNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_3() {
        double[]aInput={0,0,1,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_4() {
        double[]aInput={0,0,1,1};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_5() {
        double[]aInput={0,1,0,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_6() {
        double[]aInput={0,1,0,1};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_7() {
        double[]aInput={0,1,1,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_8() {
        double[]aInput={0,1,1,1};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_9() {
        double[]aInput={1,0,0,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_10() {
        double[]aInput={1,0,0,1};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_11() {
        double[]aInput={1,0,1,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_12() {
        double[]aInput={1,0,1,1};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }


    @Test
    void predictOutputTest_13() {
        double[]aInput={1,1,0,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_14() {
        double[]aInput={1,1,0,1};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new MovableObjectNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_15() {
        double[]aInput={1,1,1,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_16() {
        double[]aInput={1,1,1,1};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }



}
