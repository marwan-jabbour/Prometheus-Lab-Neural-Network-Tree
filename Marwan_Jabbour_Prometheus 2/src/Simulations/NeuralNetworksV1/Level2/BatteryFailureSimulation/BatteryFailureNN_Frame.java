package Simulations.NeuralNetworksV1.Level2.BatteryFailureSimulation;

import Simulations.NeuralNetworksV1.Level1.ChargeBatterySimulation.ChargeBatteryNN_Panel;

import javax.swing.*;

public class BatteryFailureNN_Frame extends JFrame {

    public BatteryFailureNN_Panel panel;

   public BatteryFailureNN_Frame(){
        this.panel=new BatteryFailureNN_Panel();
        this.add(panel);
        this.setTitle("Robot Grid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
