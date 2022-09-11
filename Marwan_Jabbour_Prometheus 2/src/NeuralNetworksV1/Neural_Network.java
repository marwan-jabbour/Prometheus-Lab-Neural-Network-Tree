package NeuralNetworksV1;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;

public abstract class Neural_Network {
    protected NeuralNetwork aNeuralNetwork;

    protected abstract NeuralNetwork buildNN();

    protected abstract double[][]getInputData();

    protected abstract double[][]getExpectedData();

    protected abstract DataSet generateTrainingSet();

    abstract public double predictOutput(double[]pInput);

    /**
     * Trains the neural network
     */
    public void trainNN() {
        DataSet trainSet=this.generateTrainingSet();
        aNeuralNetwork.learn(trainSet);
    }


    /**
     *
     * @param actualOutput from neural network
     * @return the index with most votes by neural network
     */
    protected int giveMeIndex(double[] actualOutput){
        int index=0;
        double max=actualOutput[0];
        for (int counter=1;counter<actualOutput.length;counter++){
            if (actualOutput[counter]>max){
                max=actualOutput[counter];
                index=counter;
            }
        }

        return index;
    }









    //Not useful method: can be used to display the correct input and output, line by line

//    public void testNeuralNetwork(DataSet testSet) {
//        int correct=0;
//        int total=testSet.size();
//        DataSet b=testSet;
//        double[]expectedOutput=new double[testSet.size()];
//        int counter2=0;
//        for (DataSetRow r: b){
//            double[]p= Arrays.stream(r.getDesiredOutput()).toArray();
//            int index=-1;
//
//            for (int i=0;i<p.length;i++){
//                if (p[i]==1){
//                    index=i;
//                    break;
//                }
//            }
//            expectedOutput[counter2]=index;
//            counter2++;
//        }
//
//
//        int counter3=0;
//        for (DataSetRow aRow : testSet.getRows()) {
//            aNeuralNetwork.setInput(aRow.getInput());
//            aNeuralNetwork.calculate();
//
//            double[] actualOutput = aNeuralNetwork.getOutput();
//
//            int index=0;
//            double max=actualOutput[0];
//
//            for (int counter=1;counter<actualOutput.length;counter++){
//                if (actualOutput[counter]>max){
//                    max=actualOutput[counter];
//                    index=counter;
//                }
//            }
//
//
//            System.out.print("Input: " + Arrays.toString(aRow.getInput()));
//            System.out.print(" Output: " + index);
//            double ex=expectedOutput[counter3++];
//            System.out.println(" Expected: " +ex);
//            if (index==ex){
//                correct++;
//            }
//
//        }
//        System.out.println("Correct: "+correct+" Total: "+total);
//    }
}
