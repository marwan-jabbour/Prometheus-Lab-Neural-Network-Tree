package Simulations.BFS_Simulation;

import Simulations.NeuralNetworksV1.Level2.ChangeDirectionSimulation.ChangeDirectionNN_Panel;

import javax.swing.*;

public class BFS_Frame extends JFrame {

    public BFS_Panel panel;

    /**
     * @param width
     * @param height
     * @param size
     * @param destX X coordinate of destination
     * @param destY Y coordinate of destination
     */
    public BFS_Frame(int width, int height, int size, int destX, int destY){
        this.panel=new BFS_Panel(width, height);
        panel.UNIT_SIZE=50;
        panel.WORLD_UNITS=(width*height)/panel.UNIT_SIZE;
        panel.obstacleX=new int[width/panel.UNIT_SIZE];
        panel.obstacleY=new int[width/panel.UNIT_SIZE];
        panel.size=size;
        panel.destX=destX*panel.UNIT_SIZE;
        panel.destY=destY*panel.UNIT_SIZE;
        this.add(panel);
        this.setTitle("Robot Grid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
