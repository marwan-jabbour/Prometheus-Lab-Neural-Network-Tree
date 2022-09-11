package Simulations.NeuralNetworksV1.Level1.ChargeBatterySimulation;

import Simulations.NeuralNetworksV1.Level1.SurfaceClassifierSimulation.SurfaceClassifierNN_Panel;

import javax.swing.*;

public class ChargeBatteryNN_Frame extends JFrame {

    public ChargeBatteryNN_Panel panel;

   public ChargeBatteryNN_Frame(){
        this.panel=new ChargeBatteryNN_Panel();
        this.add(panel);
        this.setTitle("Robot Grid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
