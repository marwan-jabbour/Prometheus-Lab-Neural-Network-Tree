package NeuralNetworksV1.Level1;

import NeuralNetworksV1.Neural_Network;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChargeBatteryNNTest {


    @Test
    void predictOutputTest_1() {
        double[]aInput={1, 9.75};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new ChargeBatteryNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_2() {
        double[]aInput={1, 8.25};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new ChargeBatteryNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_3() {
        double[]aInput={1, 7.25};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new ChargeBatteryNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_4() {
        double[]aInput={1, 6.25};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new ChargeBatteryNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }



    @Test
    void predictOutputTest_5() {
        double[]aInput={10, 8.75};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new ChargeBatteryNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_6() {
        double[]aInput={10, 7.25};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new ChargeBatteryNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_7() {
        double[]aInput={10, 6.25};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new ChargeBatteryNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_8() {
        double[]aInput={10, 5.25};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new ChargeBatteryNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }




    @Test
    void predictOutputTest_9() {
        double[]aInput={50, 9.15};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new ChargeBatteryNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_10() {
        double[]aInput={50, 8.25};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new ChargeBatteryNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_11() {
        double[]aInput={50, 7.25};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new ChargeBatteryNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_12() {
        double[]aInput={50, 6.25};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new ChargeBatteryNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_13() {
        double[]aInput={50, 5.25};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new ChargeBatteryNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_14() {
        double[]aInput={100,8.75};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new ChargeBatteryNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_15() {
        double[]aInput={100,7.75};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new ChargeBatteryNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_16() {
        double[]aInput={100,6.75};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new ChargeBatteryNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_17() {
        double[]aInput={100,5.75};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new ChargeBatteryNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }


}