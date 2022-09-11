package Simulations.NeuralNetworksV1.Level2.ChangeDirectionSimulation;

import Simulations.DFS_Simulation.DFS_Panel;
import Simulations.NeuralNetworksV1.Level2.ReachableDestinationSimulation.ReachableDestinationNN_Panel;

import javax.swing.*;

public class ChangeDirectionNN_Frame extends JFrame {

    public ChangeDirectionNN_Panel panel;

   public ChangeDirectionNN_Frame(int width, int height, int size, int destX, int destY){
           this.panel=new ChangeDirectionNN_Panel(width, height, destX, destY);

        this.add(panel);
        this.setTitle("Robot Grid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
