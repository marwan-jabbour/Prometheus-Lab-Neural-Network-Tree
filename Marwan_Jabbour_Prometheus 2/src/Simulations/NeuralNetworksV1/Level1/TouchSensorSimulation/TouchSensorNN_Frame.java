package Simulations.NeuralNetworksV1.Level1.TouchSensorSimulation;

import Simulations.NeuralNetworksV1.Level1.SurfaceClassifierSimulation.SurfaceClassifierNN_Panel;

import javax.swing.*;

public class TouchSensorNN_Frame extends JFrame {

    public TouchSensorNN_Panel panel;

   public TouchSensorNN_Frame(){
        this.panel=new TouchSensorNN_Panel();
        this.add(panel);
        this.setTitle("Robot Grid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
