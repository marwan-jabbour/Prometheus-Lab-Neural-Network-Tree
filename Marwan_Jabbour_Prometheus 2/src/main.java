import NeuralNetworksV1.Level1.*;
import NeuralNetworksV1.Level2.BatteryFailureNN;
import NeuralNetworksV1.Level2.ChangeDirectionNN;
import NeuralNetworksV1.Level2.MovableObjectNN;
import NeuralNetworksV1.Level2.ReachableDestinationNN;
import NeuralNetworksV2.BatteryFailureNN_V2;
import NeuralNetworksV2.ChangeDirectionNN_V2;
import NeuralNetworksV2.MovableObjectNN_V2;
import NeuralNetworksV2.ReachableDestinationNN_V2;

public class main {
    public static void main(String[] args) {

        //use this class for any testing of methods

        ChangeDirectionNN nn = new ChangeDirectionNN();
        nn.trainNN();



    }
}
