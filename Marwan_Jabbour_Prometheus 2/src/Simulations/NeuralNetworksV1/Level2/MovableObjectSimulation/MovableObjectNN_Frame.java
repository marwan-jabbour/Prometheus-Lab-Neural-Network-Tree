package Simulations.NeuralNetworksV1.Level2.MovableObjectSimulation;

import Simulations.NeuralNetworksV1.Level1.TouchSensorSimulation.TouchSensorNN_Panel;

import javax.swing.*;

public class MovableObjectNN_Frame extends JFrame {

    public MovableObjectNN_Panel panel;

   public MovableObjectNN_Frame(){
        this.panel=new MovableObjectNN_Panel();
        this.add(panel);
        this.setTitle("Robot Grid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
