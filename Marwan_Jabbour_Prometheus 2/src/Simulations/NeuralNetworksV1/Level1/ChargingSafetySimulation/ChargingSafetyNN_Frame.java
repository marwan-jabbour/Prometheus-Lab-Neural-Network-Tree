package Simulations.NeuralNetworksV1.Level1.ChargingSafetySimulation;

import javax.swing.*;

public class ChargingSafetyNN_Frame extends JFrame {

    public ChargingSafetyNN_Panel panel;

   public ChargingSafetyNN_Frame(){
        this.panel=new ChargingSafetyNN_Panel();
        this.add(panel);
        this.setTitle("Robot Grid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
