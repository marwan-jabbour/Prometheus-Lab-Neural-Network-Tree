package NeuralNetworksV2;

import NeuralNetworksV1.Neural_Network;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovableObjectNN_V2_Test {


    @Test
    void predictOutputTest_1() {
        double[]aInput={9.0,9.0,0,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_2() {
        double[]aInput={9.5,9.5,0,1};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new MovableObjectNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_3() {
        double[]aInput={10.0,10.0,1,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_4() {
        double[]aInput={10.5,10.5,1,1};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_5() {
        double[]aInput={11.0,7.5,0,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_6() {
        double[]aInput={12.0,7.0,0,1};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_7() {
        double[]aInput={12.5,6.5,1,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_8() {
        double[]aInput={13.0,6.0,1,1};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_9() {
        double[]aInput={5.5,13.5,0,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_10() {
        double[]aInput={5.0,14.0,0,1};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_11() {
        double[]aInput={4.5,14.5,1,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_12() {
        double[]aInput={4.0,15.0,1,1};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }


    @Test
    void predictOutputTest_13() {
        double[]aInput={3.5,3.5,0,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_14() {
        double[]aInput={3.0,3.0,0,1};
        double aExpectedOutput=1;

        Neural_Network aNeuralNetwork= new MovableObjectNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_15() {
        double[]aInput={2.5,2.5,1,0};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_16() {
        double[]aInput={2.0,2.0,1,1};
        double aExpectedOutput=0;

        Neural_Network aNeuralNetwork= new MovableObjectNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }



}
