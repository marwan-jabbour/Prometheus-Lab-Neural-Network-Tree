package Simulations.NeuralNetworksV1.Level2.NextDirectionSimulation;

import javax.swing.*;

public class NextDirectionNN_Frame extends JFrame {

    public NextDirectionNN_Panel panel;

   public NextDirectionNN_Frame(){
        this.panel=new NextDirectionNN_Panel();
        this.add(panel);
        this.setTitle("Robot Grid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
