package Simulations.NeuralNetworksV1.Level1.UltraSonicSensorSimulation;

import Simulations.DataBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UltraSonicSensorNN_Panel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH=500;
    static final int SCREEN_HEIGHT=500;
    static final int UNIT_SIZE=100;
    static final int WORLD_UNITS=(SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY=75;
    double ultraSonicSensorNNOutput_Left=-1;
    double actualOutput_Left=-1;
    boolean predictionOutput_Left=false;
    DataBlock[]inputData_Left;


    double ultraSonicSensorNNOutput_Right=-1;
    double actualOutput_Right=-1;
    boolean predictionOutput_Right=false;
    DataBlock[]inputData_Right;

    double ultraSonicSensorNNOutput_fwd=-1;
    double actualOutput_fwd=-1;
    boolean predictionOutput_fwd=false;
    DataBlock[]inputData_fwd;


    char direction='D';
    int xCoordinate=0;
    int yCoordinate=0;
    int x=0;
    int y=0;

    int destX=4*UNIT_SIZE;
    int destY=3*UNIT_SIZE;


    int []obstacleX=new int[SCREEN_WIDTH/UNIT_SIZE];
    int []obstacleY= new int[SCREEN_HEIGHT/UNIT_SIZE];


    boolean running=false;
    boolean hitObstacle=false;
    boolean reachedDest=false;
    static boolean simulationOn = false;


    Timer timer;


    public UltraSonicSensorNN_Panel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
    }

    public void setInputData(DataBlock[]leftData,DataBlock[]rightData, DataBlock[]fwdData){
        inputData_Left=leftData;
        inputData_Right=rightData;
        inputData_fwd=fwdData;
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
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }
            g.setColor(Color.LIGHT_GRAY);



            g.setColor(Color.red);

            for (int i = 0; i < counterObstacle; i++) {
                g.fillOval(obstacleX[i], obstacleY[i], UNIT_SIZE, UNIT_SIZE);
            }

            g.setColor(Color.green);

            g.fillRect(destX,destY,UNIT_SIZE,UNIT_SIZE);

            g.setColor(Color.gray);
            g.fillRect(x, y, UNIT_SIZE, UNIT_SIZE);

        }
        if (reachedDest){
            destinationReached(g);
        }
        if (hitObstacle) {
            obstacleCollision(g);
        }


        g.setColor(Color.blue);
        g.setFont(new Font("Times New Roman",Font.BOLD, 20));

        FontMetrics metrics=getFontMetrics((g.getFont()));


        //////////////////////////////
        String textPredictionOutputLeft=(ultraSonicSensorNNOutput_Left==0)?"close": "far";
        String textLeft="ultraSonicSensorNN: "+textPredictionOutputLeft;
        String textActualOutputLeft=(actualOutput_Left==0)?"close": "far";
        String textLeft2="Actual: "+textActualOutputLeft;
        String textLeft3="Prediction: "+predictionOutput_Left;
        //////////////////////////////


        String textPredictionOutputRight=(ultraSonicSensorNNOutput_Right==0)?"close": "far";
        String textRight=""+textPredictionOutputRight;
        String textActualOutputRight=(actualOutput_Right==0)?"close": "far";
        String textRight2=""+textActualOutputRight;
        String textRight3=""+predictionOutput_Right;
        //////////////////////////////


        String textPredictionOutputfwd=(ultraSonicSensorNNOutput_fwd==0)?"close": "far";
        String textFwd=""+textPredictionOutputfwd;
        //g.drawString(text, (SCREEN_WIDTH-metrics.stringWidth(text))/2, g.getFont().getSize());
        String textActualOutputfwd=(actualOutput_fwd==0)?"close": "far";
        String textFwd2=""+textActualOutputfwd;
        //g.drawString(text2, (SCREEN_WIDTH-metrics.stringWidth(text2))/2, 2*g.getFont().getSize());
        String textFwd3=""+predictionOutput_fwd;
        //g.drawString(text3, (SCREEN_WIDTH-metrics.stringWidth(text3))/2, 3*g.getFont().getSize());
        //////////////////////////////

        String text=textLeft+" "+textFwd+" "+textRight;
        String text2=textLeft2+" "+textFwd2+" "+textRight2;
        String text3=textLeft3+" "+textFwd3+" "+textRight3;

        g.drawString(text, (SCREEN_WIDTH-metrics.stringWidth(text))/2, g.getFont().getSize());
        g.drawString(text2, (SCREEN_WIDTH-metrics.stringWidth(text2))/2, 2*g.getFont().getSize());
        g.drawString(text3, (SCREEN_WIDTH-metrics.stringWidth(text3))/2, 3*g.getFont().getSize());

         timer.stop();

    }

    public void move(){

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
        //  timer.stop();

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

        double blockOutput=inputData_Left[yCoordinate*5+xCoordinate].predictBlock();
        ultraSonicSensorNNOutput_Left=blockOutput;
        String textBlockOutput=(blockOutput==0)?"close": "far";
        System.out.println("ultraSonicSensorNN(L): "+textBlockOutput);
        double blockActualOutput=inputData_Left[yCoordinate*5+xCoordinate].giveCorrectOutput();
        actualOutput_Left=blockActualOutput;
        String textActualOutput=(actualOutput_Left==0)?"close": "far";
        System.out.println("Actual output: "+textActualOutput);
        predictionOutput_Left=actualOutput_Left==ultraSonicSensorNNOutput_Left;
        System.out.println("Prediction: "+ predictionOutput_Left);

        
        ///////////////////

        blockOutput=inputData_Right[yCoordinate*5+xCoordinate].predictBlock();
        ultraSonicSensorNNOutput_Right=blockOutput;
        textBlockOutput=(blockOutput==0)?"close": "far";
        System.out.println("ultraSonicSensorNN(R): "+textBlockOutput);
        blockActualOutput=inputData_Right[yCoordinate*5+xCoordinate].giveCorrectOutput();
        actualOutput_Right=blockActualOutput;
        textActualOutput=(actualOutput_Right==0)?"close": "far";
        System.out.println("Actual output: "+textActualOutput);
        predictionOutput_Right=actualOutput_Right==ultraSonicSensorNNOutput_Right;
        System.out.println("Prediction: "+ predictionOutput_Right);


        ///////////////////

        blockOutput=inputData_fwd[yCoordinate*5+xCoordinate].predictBlock();
        ultraSonicSensorNNOutput_fwd=blockOutput;
        textBlockOutput=(blockOutput==0)?"close": "far";
        System.out.println("ultraSonicSensorNN(F): "+textBlockOutput);
        blockActualOutput=inputData_fwd[yCoordinate*5+xCoordinate].giveCorrectOutput();
        actualOutput_fwd=blockActualOutput;
        textActualOutput=(actualOutput_fwd==0)?"close": "far";
        System.out.println("Actual output: "+textActualOutput);
        predictionOutput_fwd=actualOutput_fwd==ultraSonicSensorNNOutput_fwd;
        System.out.println("Prediction: "+ predictionOutput_fwd);
        

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
