package NeuralNetworksV1.Level2;

import NeuralNetworksV1.Level1.ChargeBatteryNN;
import NeuralNetworksV1.Neural_Network;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BatteryFailureNNTest {

    @Test
    void predictOutputTest_1() {
        double[]aInput={-45,1,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_2() {
        double[]aInput={-45,0,1};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new BatteryFailureNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_3() {
        double[]aInput={-45,1,1};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_4() {
        double[]aInput={-45,0,0};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new BatteryFailureNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_5() {
        double[]aInput={-10,1,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_6() {
        double[]aInput={-10,1,1};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }


    @Test
    void predictOutputTest_7() {
        double[]aInput={-10,0,1};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_8() {
        double[]aInput={-10,0,0};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new BatteryFailureNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_9() {
        double[]aInput={20,1,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_10() {
        double[]aInput={20,1,1};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_11() {
        double[]aInput={20,0,1};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_12() {
        double[]aInput={20,0,0};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new BatteryFailureNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_13() {
        double[]aInput={45,1,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_14() {
        double[]aInput={45,1,1};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_15() {
        double[]aInput={45,0,1};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new BatteryFailureNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_16() {
        double[]aInput={45,0,0};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new BatteryFailureNN();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }
}
