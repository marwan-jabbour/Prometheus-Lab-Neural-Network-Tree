package NeuralNetworksV1.Level1;

import NeuralNetworksV1.Neural_Network;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UltraSonicSensorNNTest {


    @Test
    void predictOutputTest_1() {
        double[]aInput={2.8};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new UltraSonicSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_2() {
        double[]aInput={2.6};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new UltraSonicSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_3() {
        double[]aInput={2.3};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new UltraSonicSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_4() {
        double[]aInput={2.15};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new UltraSonicSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_5() {
        double[]aInput={1.8};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new UltraSonicSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_6() {
        double[]aInput={1.6};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new UltraSonicSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_7() {
        double[]aInput={1.3};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new UltraSonicSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_8() {
        double[]aInput={1.26};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new UltraSonicSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_9() {
        double[]aInput={0.8};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new UltraSonicSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_10() {
        double[]aInput={0.6};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new UltraSonicSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_11() {
        double[]aInput={0.55};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new UltraSonicSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }


}