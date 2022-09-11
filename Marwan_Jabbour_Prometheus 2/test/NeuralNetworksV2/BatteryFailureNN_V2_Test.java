package NeuralNetworksV2;

import NeuralNetworksV1.Level2.BatteryFailureNN;
import NeuralNetworksV1.Neural_Network;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BatteryFailureNN_V2_Test {

    @Test
    void predictOutputTest_1() {
        double[]aInput={-45,16.5,10,9.0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_2() {
        double[]aInput={-45,13,1,5.5};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new BatteryFailureNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_3() {
        double[]aInput={-45,16.5,1,8.5};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_4() {
        double[]aInput={-45,13.25,1,10};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new BatteryFailureNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }


    @Test
    void predictOutputTest_5() {
        double[]aInput={-10,15.4,1,9.5};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_6() {
        double[]aInput={-10,15.4,1,7.0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_7() {
        double[]aInput={-10,14.25,1,6.5};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_8() {
        double[]aInput={-10,14.0,10,9.6};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new BatteryFailureNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }


    @Test
    void predictOutputTest_9() {
        double[]aInput={20,14.5,10,9.0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_10() {
        double[]aInput={20,14.5,10,7.0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_11() {
        double[]aInput={20,16.25,10,6.5};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_12() {
        double[]aInput={20,16.3,10,8.5};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new BatteryFailureNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_13() {
        double[]aInput={45,13.75,50,9.25};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_14() {
        double[]aInput={45,13.75,50,6.0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new BatteryFailureNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_15() {
        double[]aInput={45,15.5,50,5.5};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new BatteryFailureNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_16() {
        double[]aInput={45,16.0,100,9.0};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new BatteryFailureNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }



}
