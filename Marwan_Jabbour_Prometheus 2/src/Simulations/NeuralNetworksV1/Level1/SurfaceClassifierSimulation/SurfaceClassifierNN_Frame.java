package Simulations.NeuralNetworksV1.Level1.SurfaceClassifierSimulation;

import Simulations.NeuralNetworksV1.Level1.SurfaceClassifierSimulation.SurfaceClassifierNN_Panel;

import javax.swing.*;

public class SurfaceClassifierNN_Frame extends JFrame {

    public SurfaceClassifierNN_Panel panel;

   public SurfaceClassifierNN_Frame(){
        this.panel=new SurfaceClassifierNN_Panel();
        this.add(panel);
        this.setTitle("Robot Grid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
