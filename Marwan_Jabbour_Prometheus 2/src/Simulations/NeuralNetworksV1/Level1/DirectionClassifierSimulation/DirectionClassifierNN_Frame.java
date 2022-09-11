package Simulations.NeuralNetworksV1.Level1.DirectionClassifierSimulation;

import Simulations.NeuralNetworksV1.Level1.ChargeBatterySimulation.ChargeBatteryNN_Panel;

import javax.swing.*;

public class DirectionClassifierNN_Frame extends JFrame {

    public DirectionClassifierNN_Panel panel;

   public DirectionClassifierNN_Frame(){
        this.panel=new DirectionClassifierNN_Panel();
        this.add(panel);
        this.setTitle("Robot Grid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
