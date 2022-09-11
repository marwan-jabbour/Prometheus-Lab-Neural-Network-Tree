package Simulations.NeuralNetworksV1.Level1.ChargingSafetySimulation;

import Simulations.DataBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChargingSafetyNN_Panel extends JPanel  implements ActionListener {
    static final int SCREEN_WIDTH=500;
    static final int SCREEN_HEIGHT=500;
    static final int UNIT_SIZE=100;
    static final int WORLD_UNITS=(SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY=75;
    double chargingSafetyNNOutput=-1;
    double actualOutput=-1;
    boolean predictionOutput=false;
    String voltage;
    String temperature;

    DataBlock[]inputData;



    char direction='D';
    int xCoordinate=0;
    int yCoordinate=0;
    int x=0;
    int y=0;

    int destX=-1*UNIT_SIZE;
    int destY=-1*UNIT_SIZE;


    int []obstacleX=new int[SCREEN_WIDTH/UNIT_SIZE];
    int []obstacleY= new int[SCREEN_HEIGHT/UNIT_SIZE];


    boolean running=false;
    boolean hitObstacle=false;
    boolean reachedDest=false;
    static boolean simulationOn = false;


    Timer timer;


    public ChargingSafetyNN_Panel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
    }

    public void setInputData(DataBlock[]pData){
        inputData=pData;
        startWorld();
    }


    static int counterObstacle=0;
    public void newObstacle(int x, int y){

        obstacleX[counterObstacle]= x*UNIT_SIZE;
        obstacleY[counterObstacle]= y*UNIT_SIZE;
        counterObstacle++;
    }

    public void startWorld(){
        //newObstacle(1,0);
        running=true;
        timer=new Timer(DELAY, this);
        timer.start();
    }

    public void pause() {
        simulationOn = true;
        timer.stop();
    }

    public void resume() {
        simulationOn = false;
        timer.start();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        if (!reachedDest && !hitObstacle) {
            for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }
            g.setColor(Color.RED);



            for (int i = 0; i < inputData.length; i++) {
                if (inputData[i].giveCorrectOutput()==0) {
                    g.setColor(Color.red);

                    int temp_X=i%5;
                    int temp_Y=i/5;
                    g.fillRect(temp_X * UNIT_SIZE, temp_Y * UNIT_SIZE, UNIT_SIZE-1, UNIT_SIZE-1);
                }
                else{
                    g.setColor(Color.green);
                    int temp_X=i%5;
                    int temp_Y=i/5;
                    g.fillRect(temp_X * UNIT_SIZE, temp_Y * UNIT_SIZE, UNIT_SIZE-1, UNIT_SIZE-1);
                }
            }

            g.setColor(Color.red);

            for (int i = 0; i < counterObstacle; i++) {
                g.fillOval(obstacleX[i], obstacleY[i], UNIT_SIZE, UNIT_SIZE);
            }

            g.setColor(Color.green);

            //g.fillRect(destX,destY,UNIT_SIZE,UNIT_SIZE);

            g.setColor(Color.gray);
            g.fillRect(x, y, UNIT_SIZE, UNIT_SIZE);

        }
        if (reachedDest){
            //g.setColor(Color.green);
            // g.fillRect(destX, destY, UNIT_SIZE, UNIT_SIZE);
            destinationReached(g);
        }
        if (hitObstacle) {
            // g.setColor(Color.red);
            //g.fillRect(x, y, UNIT_SIZE, UNIT_SIZE);
            obstacleCollision(g);
        }

        //

        g.setColor(Color.blue);
        g.setFont(new Font("Times New Roman",Font.BOLD, 20));

        FontMetrics metrics=getFontMetrics((g.getFont()));
        String textPredictionOutput=(chargingSafetyNNOutput==0)?"unsafe charge": "safe charge";

        String text="chargingSafetyNN: "+textPredictionOutput;
        System.out.println("Inside draw, chargingSafetyNN: "+chargingSafetyNNOutput);
        System.out.println("Inside draw, actualOutput: "+actualOutput);

        g.drawString(text, (SCREEN_WIDTH-metrics.stringWidth(text))/2, g.getFont().getSize());


        String textActualOutput=(actualOutput==0)?"unsafe charge": "safe charge";
        String text2="Actual Output: "+textActualOutput;

        g.drawString(text2, (SCREEN_WIDTH-metrics.stringWidth(text2))/2, 2*g.getFont().getSize());


        String text3="Prediction: "+predictionOutput;
        g.drawString(text3, (SCREEN_WIDTH-metrics.stringWidth(text3))/2, 3*g.getFont().getSize());


        String text4="Temp: "+temperature+" Voltage: "+voltage;
        g.drawString(text4, (SCREEN_WIDTH-metrics.stringWidth(text4))/2, 4*g.getFont().getSize());

        if (!running);
        timer.stop();

    }

    public void move(){

        //diagonal movement

        switch(direction){
            case 'R':
                if (x+UNIT_SIZE<SCREEN_WIDTH) {
                    x = x + UNIT_SIZE;
                    xCoordinate++;
                }
                break;

            case 'L':
                if (x-UNIT_SIZE>=0) {
                    x = x - UNIT_SIZE;
                    xCoordinate--;
                }
                break;

            case 'U':
                if (y-UNIT_SIZE>=0) {
                    y = y - UNIT_SIZE;
                    yCoordinate--;
                }
                break;

            case 'D':
                if (y+UNIT_SIZE<SCREEN_HEIGHT) {
                    y = y + UNIT_SIZE;
                    yCoordinate++;
                }
                break;
        }

        running=false;


    }

    public void checkCollision(){
        //check if collision with obstalce
        for (int i=0;i<counterObstacle;i++){
            if (obstacleX[i]==x&&obstacleY[i]==y)
                hitObstacle=true;
        }

        //check if reached destination
        if (x==destX&&y==destY)
            reachedDest=true;

        if (!running);

    }

    public void destinationReached(Graphics g){
        g.setColor(Color.GREEN);
        g.setFont(new Font("Times New Roman",Font.BOLD, 50));

        FontMetrics metrics=getFontMetrics((g.getFont()));
        String text="Destination Reached";
        g.drawString(text, (SCREEN_WIDTH-metrics.stringWidth(text))/2, SCREEN_HEIGHT/2);
    }

    public void obstacleCollision(Graphics g){
        g.setColor(Color.RED);
        g.setFont(new Font("Times New Roman",Font.BOLD, 50));

        FontMetrics metrics=getFontMetrics((g.getFont()));
        String text="Sorry, collision occured";
        g.drawString(text, (SCREEN_WIDTH-metrics.stringWidth(text))/2, SCREEN_HEIGHT/2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (hitObstacle || reachedDest)
            return;
        if (running) {
            move();
            checkCollision();
            try {
                predictBlockOutput();
            }
            catch(NullPointerException a){
                a.printStackTrace();
            }
        }
        repaint();
    }


    public double predictBlockOutput(){

        double blockOutput=inputData[yCoordinate*5+xCoordinate].predictBlock();
        chargingSafetyNNOutput=blockOutput;
        double tempVolt=inputData[yCoordinate*5+xCoordinate].inputData[0];
        voltage=tempVolt+" mA";
        temperature=inputData[yCoordinate*5+xCoordinate].inputData[1]+" C";
        String textBlockOutput=(blockOutput==0)?"unsafe charge": "safe charge";
        System.out.println("chargingSafetyNN: "+textBlockOutput);
        double blockActualOutput=inputData[yCoordinate*5+xCoordinate].giveCorrectOutput();
        actualOutput=blockActualOutput;
        String textActualOutput=(actualOutput==0)?"unsafe charge": "safe charge";
        System.out.println("Actual output: "+textActualOutput);
        predictionOutput=actualOutput==chargingSafetyNNOutput;
        System.out.println("Prediction: "+ predictionOutput);



        return blockOutput;
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){

            running=true;
            timer.start();

            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    direction='L';
                    break;

                case KeyEvent.VK_RIGHT:
                    direction='R';
                    break;

                case KeyEvent.VK_UP:
                    direction='U';
                    break;

                case KeyEvent.VK_DOWN:
                    direction='D';
                    break;

                case KeyEvent.VK_SPACE:
                    if(simulationOn) {
                        resume();
                    } else {
                        pause();
                    }
                    break;

            }


        }
    }
}
