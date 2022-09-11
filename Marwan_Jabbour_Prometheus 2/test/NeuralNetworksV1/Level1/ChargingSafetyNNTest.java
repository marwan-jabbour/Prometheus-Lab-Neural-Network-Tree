package NeuralNetworksV1.Level1;

import NeuralNetworksV1.Neural_Network;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChargingSafetyNNTest {

    @Test
    void predictOutputTest_1() {
        double[]aInput={16.25, -39};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new ChargingSafetyNN();
        aNeuralNetwork.trainNN();

        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        System.out.println(aActualOutput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_2() {
        double[]aInput={15.4,-7};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new ChargingSafetyNN();
        aNeuralNetwork.trainNN();

        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        System.out.println(aActualOutput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_3() {
        double[]aInput={14.95,5};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new ChargingSafetyNN();
        aNeuralNetwork.trainNN();

        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        System.out.println(aActualOutput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_4() {
        double[]aInput={14.3,28};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new ChargingSafetyNN();
        aNeuralNetwork.trainNN();

        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        System.out.println(aActualOutput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_5() {
        double[]aInput={13.9,40};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new ChargingSafetyNN();
        aNeuralNetwork.trainNN();

        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        System.out.println(aActualOutput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_6() {
        double[]aInput={13.25,-40};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new ChargingSafetyNN();
        aNeuralNetwork.trainNN();

        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        System.out.println(aActualOutput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_7() {
        double[]aInput={13.75,-28};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new ChargingSafetyNN();
        aNeuralNetwork.trainNN();

        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        System.out.println(aActualOutput);
        assertTrue(aActualOutput==aExpectedOutput);
    }
    @Test
    void predictOutputTest_8() {
        double[]aInput={14,-20};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new ChargingSafetyNN();
        aNeuralNetwork.trainNN();

        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        System.out.println(aActualOutput);
        assertTrue(aActualOutput==aExpectedOutput);
    }
    @Test
    void predictOutputTest_9() {
        double[]aInput={14.5,-4};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new ChargingSafetyNN();
        aNeuralNetwork.trainNN();

        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        System.out.println(aActualOutput);
        assertTrue(aActualOutput==aExpectedOutput);
    }
    @Test
    void predictOutputTest_10() {
        double[]aInput={16,30};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new ChargingSafetyNN();
        aNeuralNetwork.trainNN();

        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        System.out.println(aActualOutput);
        assertTrue(aActualOutput==aExpectedOutput);
    }



}