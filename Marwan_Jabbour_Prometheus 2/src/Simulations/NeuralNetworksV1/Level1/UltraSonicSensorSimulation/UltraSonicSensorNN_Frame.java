package Simulations.NeuralNetworksV1.Level1.UltraSonicSensorSimulation;

import Simulations.NeuralNetworksV1.Level1.TouchSensorSimulation.TouchSensorNN_Panel;

import javax.swing.*;

public class UltraSonicSensorNN_Frame extends JFrame {

    public UltraSonicSensorNN_Panel panel;

   public UltraSonicSensorNN_Frame(){
        this.panel=new UltraSonicSensorNN_Panel();
        this.add(panel);
        this.setTitle("Robot Grid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
