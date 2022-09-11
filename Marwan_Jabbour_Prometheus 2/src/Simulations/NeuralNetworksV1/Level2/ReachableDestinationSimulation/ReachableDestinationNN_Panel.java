package Simulations.NeuralNetworksV1.Level2.ReachableDestinationSimulation;

import Simulations.DataBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ReachableDestinationNN_Panel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH=500;
    static final int SCREEN_HEIGHT=500;
    static final int UNIT_SIZE=100;
    static final int WORLD_UNITS=(SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY=75;


    DataBlock[]inputData;


    double reachableDestinationNNOutput_Left=-1;
    double actualOutput_Left=-1;
    boolean predictionOutput_Left=false;
    DataBlock[]inputData_Left;


    double reachableDestinationNNOutput_Right=-1;
    double actualOutput_Right=-1;
    boolean predictionOutput_Right=false;
    DataBlock[]inputData_Right;

    double reachableDestinationNNOutput_fwd=-1;
    double actualOutput_fwd=-1;
    boolean predictionOutput_fwd=false;
    DataBlock[]inputData_fwd;

    String chargeBatteryOutput="null";
    String surfaceClassifierOutput="null";


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


    public ReachableDestinationNN_Panel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
    }

    public void setInputData( DataBlock[]data, DataBlock[]leftData, DataBlock[]fwdData,DataBlock[]rightData){
        inputData=data;
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

            for (int i = 0; i < inputData.length; i++) {
                if (inputData[i].inputData[0]==0) {
                    g.setColor(Color.PINK);

                    int temp_X=i%5;
                    int temp_Y=i/5;
                    g.fillRect(temp_X * UNIT_SIZE, temp_Y * UNIT_SIZE, UNIT_SIZE-1, UNIT_SIZE-1);
                }
                else{
                    g.setColor(Color.yellow);
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
        g.setFont(new Font("Times New Roman",Font.BOLD, 18));

        FontMetrics metrics=getFontMetrics((g.getFont()));


        //////////////////////////////
        String textPredictionOutputLeft=(reachableDestinationNNOutput_Left==0)?"unreachable": "reachable";
        String textLeft="reachableDestinationNN: "+textPredictionOutputLeft;
        String textActualOutputLeft=(actualOutput_Left==0)?"unreachable": "reachable";
        String textLeft2="Actual: "+textActualOutputLeft;
        String textLeft3="Prediction: "+predictionOutput_Left;


        //////////////////////////////
        String textPredictionOutputRight=(reachableDestinationNNOutput_Right==0)?"unreachable": "reachable";
        String textRight=""+textPredictionOutputRight;
        String textActualOutputRight=(actualOutput_Right==0)?"unreachable": "reachable";
        String textRight2=""+textActualOutputRight;
        String textRight3=""+predictionOutput_Right;
        //////////////////////////////


        String textPredictionOutputfwd=(reachableDestinationNNOutput_fwd==0)?"unreachable": "reachable";
        String textFwd=""+textPredictionOutputfwd;
        String textActualOutputfwd=(actualOutput_fwd==0)?"unreachable": "reachable";
        String textFwd2=""+textActualOutputfwd;
        String textFwd3=""+predictionOutput_fwd;
        //////////////////////////////

        String text=textLeft+" "+textFwd+" "+textRight;
        String text2=textLeft2+" "+textFwd2+" "+textRight2;
        String text3=textLeft3+" "+textFwd3+" "+textRight3;
        String text4="ChargeBatteryNN: "+chargeBatteryOutput+", SurfaceClassifierNN: "+surfaceClassifierOutput;

        g.drawString(text, (SCREEN_WIDTH-metrics.stringWidth(text))/2, g.getFont().getSize());
        g.drawString(text2, (SCREEN_WIDTH-metrics.stringWidth(text2))/2, 2*g.getFont().getSize());
        g.drawString(text3, (SCREEN_WIDTH-metrics.stringWidth(text3))/2, 3*g.getFont().getSize());
        g.drawString(text4, (SCREEN_WIDTH-metrics.stringWidth(text4))/2, 4*g.getFont().getSize());


        if (!running);
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

        double[]inputLeft=new double[3];
        //charge battery, ultrasonic, surface classifier
        inputLeft[0]=inputData[yCoordinate*5+xCoordinate].inputData[0];
        inputLeft[1]=inputData_Left[yCoordinate*5+xCoordinate].inputData[0];
        inputLeft[2]=inputData[yCoordinate*5+xCoordinate].inputData[1];

        chargeBatteryOutput=(inputLeft[0]==0)?"must":"no need";
        surfaceClassifierOutput=(inputLeft[2]==0)?"difficult":"easy";

        System.out.println(inputLeft[0]+" "+inputLeft[1]+" "+ inputLeft[2]);

        double blockOutput=inputData_Left[yCoordinate*5+xCoordinate].predictBlock(inputLeft);
        reachableDestinationNNOutput_Left=blockOutput;
        String textBlockOutput=(blockOutput==0)?"unreachable": "reachable";
        System.out.println("reachableDestinationNN(L): "+textBlockOutput);
        double blockActualOutput=inputData_Left[yCoordinate*5+xCoordinate].giveCorrectOutput();
        actualOutput_Left=blockActualOutput;
        String textActualOutput=(actualOutput_Left==0)?"unreachable": "reachable";
        System.out.println("Actual output: "+textActualOutput);
        predictionOutput_Left=actualOutput_Left==reachableDestinationNNOutput_Left;
        System.out.println("Prediction: "+ predictionOutput_Left);
        
        ///////////////////
        double[]inputRight=new double[3];
        inputRight[0]=inputData[yCoordinate*5+xCoordinate].inputData[0];
        inputRight[1]=inputData_Right[yCoordinate*5+xCoordinate].inputData[0];
        inputRight[2]=inputData[yCoordinate*5+xCoordinate].inputData[1];
        
        blockOutput=inputData_Right[yCoordinate*5+xCoordinate].predictBlock(inputRight);
        reachableDestinationNNOutput_Right=blockOutput;
         textBlockOutput=(blockOutput==0)?"unreachable": "reachable";
        System.out.println("reachableDestinationNN(R): "+textBlockOutput);
         blockActualOutput=inputData_Right[yCoordinate*5+xCoordinate].giveCorrectOutput();
        actualOutput_Right=blockActualOutput;
         textActualOutput=(actualOutput_Right==0)?"unreachable": "reachable";
        System.out.println("Actual output: "+textActualOutput);
        predictionOutput_Right=actualOutput_Right==reachableDestinationNNOutput_Right;
        System.out.println("Prediction: "+ predictionOutput_Right);


        ///////////////////
        double[]inputfwd=new double[3];
        inputfwd[0]=inputData[yCoordinate*5+xCoordinate].inputData[0];
        inputfwd[1]=inputData_fwd[yCoordinate*5+xCoordinate].inputData[0];
        inputfwd[2]=inputData[yCoordinate*5+xCoordinate].inputData[1];
         blockOutput=inputData_fwd[yCoordinate*5+xCoordinate].predictBlock(inputfwd);
        reachableDestinationNNOutput_fwd=blockOutput;
         textBlockOutput=(blockOutput==0)?"unreachable": "reachable";
        System.out.println("reachableDestinationNN(F): "+textBlockOutput);
         blockActualOutput=inputData_fwd[yCoordinate*5+xCoordinate].giveCorrectOutput();
        actualOutput_fwd=blockActualOutput;
         textActualOutput=(actualOutput_fwd==0)?"unreachable": "reachable";
        System.out.println("Actual output: "+textActualOutput);
        predictionOutput_fwd=actualOutput_fwd==reachableDestinationNNOutput_fwd;
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
