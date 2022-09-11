package NeuralNetworksV1.Level1;

import NeuralNetworksV1.Neural_Network;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TouchSensorNNTest {


    @Test
    void predictOutputTest_1() {
        double[]aInput={600};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new TouchSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_2() {
        double[]aInput={420};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new TouchSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_3() {
        double[]aInput={360};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new TouchSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_4() {
        double[]aInput={207};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new TouchSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_5() {
        double[]aInput={198};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new TouchSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_6() {
        double[]aInput={189};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new TouchSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_7() {
        double[]aInput={179};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new TouchSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_8() {
        double[]aInput={163};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new TouchSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }


    @Test
    void predictOutputTest_9() {
        double[]aInput={300};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new TouchSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_10() {
        double[]aInput={235};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new TouchSensorNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }
}