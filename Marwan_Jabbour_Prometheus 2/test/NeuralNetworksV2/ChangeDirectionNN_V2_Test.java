package NeuralNetworksV2;

import NeuralNetworksV1.Level1.SurfaceClassifierNN;
import NeuralNetworksV1.Neural_Network;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangeDirectionNN_V2_Test {
    static double[][]testSet;

    @BeforeAll
    static void setUp(){
        SurfaceClassifierNN aNeuralNetwork= new SurfaceClassifierNN();
        aNeuralNetwork.trainNN();
        testSet=aNeuralNetwork.getTestSet();
    }

    @Test
    void predictOutputTest_1() {
        double []aInput=new double[68*3];
        double aExpectedOutput=0;
        int index=0;

        aInput[0]=1;
        aInput[1]=9.75;
        aInput[2]=3;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }

        aInput[68]=1;
        aInput[69]=6.25;
        aInput[70]=1.15;

        for (int i=0;i<65;i++){
            aInput[i+71]=testSet[index][i];
        }

        aInput[136]=1;
        aInput[137]=9.3;
        aInput[138]=1.6;

        for (int i=0;i<65;i++){
            aInput[i+139]=testSet[index][i];
        }


        Neural_Network aNeuralNetwork=new ChangeDirectionNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }


    @Test
    void predictOutputTest_2() {
        double []aInput=new double[68*3];
        double aExpectedOutput=0;
        int index=1;

        aInput[0]=1;
        aInput[1]=5.25;
        aInput[2]=0.95;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }

        aInput[68]=50;
        aInput[69]=7.0;
        aInput[70]=0.75;

        for (int i=0;i<65;i++){
            aInput[i+71]=testSet[index][i];
        }

        aInput[136]=1;
        aInput[137]=9.3;
        aInput[138]=1.6;

        for (int i=0;i<65;i++){
            aInput[i+139]=testSet[index][i];
        }


        Neural_Network aNeuralNetwork=new ChangeDirectionNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_3() {
        double []aInput=new double[68*3];
        double aExpectedOutput=0;
        int index=2;

        aInput[0]=100;
        aInput[1]=8;
        aInput[2]=0.5;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }

        aInput[68]=50;
        aInput[69]=7.0;
        aInput[70]=0.75;

        for (int i=0;i<65;i++){
            aInput[i+71]=testSet[index][i];
        }

        aInput[136]=1;
        aInput[137]=6.25;
        aInput[138]=1.15;

        for (int i=0;i<65;i++){
            aInput[i+139]=testSet[index][i];
        }


        Neural_Network aNeuralNetwork=new ChangeDirectionNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_4() {
        double []aInput=new double[68*3];
        double aExpectedOutput=0;
        int index=3;

        aInput[0]=1;
        aInput[1]=7.25;
        aInput[2]=2.80;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }

        aInput[68]=100;
        aInput[69]=6.0;
        aInput[70]=1.25;

        for (int i=0;i<65;i++){
            aInput[i+71]=testSet[index][i];
        }

        aInput[136]=1;
        aInput[137]=6.25;
        aInput[138]=1.15;

        for (int i=0;i<65;i++){
            aInput[i+139]=testSet[index][i];
        }


        Neural_Network aNeuralNetwork=new ChangeDirectionNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_5() {
        double []aInput=new double[68*3];
        double aExpectedOutput=2;
        int index=4;

        aInput[0]=100;
        aInput[1]=6.50;
        aInput[2]=1.15;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }

        aInput[68]=1;
        aInput[69]=7.25;
        aInput[70]=2.80;

        for (int i=0;i<65;i++){
            aInput[i+71]=testSet[index][i];
        }

        aInput[136]=1;
        aInput[137]=8.25;
        aInput[138]=2.95;

        for (int i=0;i<65;i++){
            aInput[i+139]=testSet[index][i];
        }


        Neural_Network aNeuralNetwork=new ChangeDirectionNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_6() {
        double []aInput=new double[68*3];
        double aExpectedOutput=2;
        int index=5;

        aInput[0]=100;
        aInput[1]=6.25;
        aInput[2]=1.30;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }

        aInput[68]=1;
        aInput[69]=7.50;
        aInput[70]=3.0;

        for (int i=0;i<65;i++){
            aInput[i+71]=testSet[index][i];
        }

        aInput[136]=1;
        aInput[137]=8.50;
        aInput[138]=3.0;

        for (int i=0;i<65;i++){
            aInput[i+139]=testSet[index][i];
        }


        Neural_Network aNeuralNetwork=new ChangeDirectionNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_7() {
        double []aInput=new double[68*3];
        double aExpectedOutput=1;
        int index=6;

        aInput[0]=1;
        aInput[1]=8.25;
        aInput[2]=2.95;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }

        aInput[68]=1;
        aInput[69]=7.25;
        aInput[70]=2.80;

        for (int i=0;i<65;i++){
            aInput[i+71]=testSet[index][i];
        }

        aInput[136]=100;
        aInput[137]=6.0;
        aInput[138]=1.25;

        for (int i=0;i<65;i++){
            aInput[i+139]=testSet[index][i];
        }


        Neural_Network aNeuralNetwork=new ChangeDirectionNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_8() {
        double []aInput=new double[68*3];
        double aExpectedOutput=1;
        int index=7;

        aInput[0]=1;
        aInput[1]=5.5;
        aInput[2]=1.9;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }

        aInput[68]=10;
        aInput[69]=9.6;
        aInput[70]=0.5;

        for (int i=0;i<65;i++){
            aInput[i+71]=testSet[index][i];
        }

        aInput[136]=1;
        aInput[137]=5.5;
        aInput[138]=1.9;

        for (int i=0;i<65;i++){
            aInput[i+139]=testSet[index][i];
        }


        Neural_Network aNeuralNetwork=new ChangeDirectionNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_9() {
        double []aInput=new double[68*3];
        double aExpectedOutput=1;
        int index=8;

        aInput[0]=50;
        aInput[1]=7.0;
        aInput[2]=0.75;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }

        aInput[68]=1;
        aInput[69]=7.25;
        aInput[70]=2.80;

        for (int i=0;i<65;i++){
            aInput[i+71]=testSet[index][i];
        }

        aInput[136]=100;
        aInput[137]=6.0;
        aInput[138]=1.25;

        for (int i=0;i<65;i++){
            aInput[i+139]=testSet[index][i];
        }


        Neural_Network aNeuralNetwork=new ChangeDirectionNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_10() {
        double []aInput=new double[68*3];
        double aExpectedOutput=1;
        int index=7;

        aInput[0]=100;
        aInput[1]=5.0;
        aInput[2]=0.5;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }

        aInput[68]=50;
        aInput[69]=5.0;
        aInput[70]=1.9;

        for (int i=0;i<65;i++){
            aInput[i+71]=testSet[index][i];
        }

        aInput[136]=50;
        aInput[137]=5;
        aInput[138]=0.5;

        for (int i=0;i<65;i++){
            aInput[i+139]=testSet[index][i];
        }


        Neural_Network aNeuralNetwork=new ChangeDirectionNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_11() {
        double []aInput=new double[68*3];
        double aExpectedOutput=3;
        int index=10;

        aInput[0]=1;
        aInput[1]=9.25;
        aInput[2]=1.5;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }

        aInput[68]=10;
        aInput[69]=8.55;
        aInput[70]=1.55;

        for (int i=0;i<65;i++){
            aInput[i+71]=testSet[index][i];
        }

        aInput[136]=50;
        aInput[137]=9.25;
        aInput[138]=1.30;

        for (int i=0;i<65;i++){
            aInput[i+139]=testSet[index][i];
        }


        Neural_Network aNeuralNetwork=new ChangeDirectionNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

    @Test
    void predictOutputTest_12() {
        double []aInput=new double[68*3];
        double aExpectedOutput=3;
        int index=11;

        aInput[0]=1;
        aInput[1]=9.25;
        aInput[2]=1.5;

        for (int i=0;i<65;i++){
            aInput[i+3]=testSet[index][i];
        }

        aInput[68]=10;
        aInput[69]=8.55;
        aInput[70]=1.55;

        for (int i=0;i<65;i++){
            aInput[i+71]=testSet[index][i];
        }

        aInput[136]=50;
        aInput[137]=9.25;
        aInput[138]=1.30;

        for (int i=0;i<65;i++){
            aInput[i+139]=testSet[index][i];
        }


        Neural_Network aNeuralNetwork=new ChangeDirectionNN_V2();
        aNeuralNetwork.trainNN();
        double aActualOutput=aNeuralNetwork.predictOutput(aInput);
        assertTrue(aActualOutput==aExpectedOutput);
    }

}
