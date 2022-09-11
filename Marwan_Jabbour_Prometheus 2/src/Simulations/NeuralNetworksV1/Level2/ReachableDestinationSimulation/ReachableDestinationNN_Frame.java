package Simulations.NeuralNetworksV1.Level2.ReachableDestinationSimulation;

import Simulations.NeuralNetworksV1.Level1.UltraSonicSensorSimulation.UltraSonicSensorNN_Panel;

import javax.swing.*;

public class ReachableDestinationNN_Frame extends JFrame {

    public ReachableDestinationNN_Panel panel;

   public ReachableDestinationNN_Frame(){
        this.panel=new ReachableDestinationNN_Panel();
        this.add(panel);
        this.setTitle("Robot Grid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
