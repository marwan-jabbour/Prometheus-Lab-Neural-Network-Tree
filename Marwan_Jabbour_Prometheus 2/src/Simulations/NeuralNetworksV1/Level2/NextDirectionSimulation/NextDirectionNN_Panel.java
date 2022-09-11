package Simulations.NeuralNetworksV1.Level2.NextDirectionSimulation;

import Simulations.DataBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NextDirectionNN_Panel extends JPanel  implements ActionListener {
    static final int SCREEN_WIDTH=500;
    static final int SCREEN_HEIGHT=500;
    static final int UNIT_SIZE=100;
    static final int WORLD_UNITS=(SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY=75;
    double rotationIntensityNNOutput=-1;
    double actualOutput=-1;
    boolean predictionOutput=false;

    String surfaceClassifierOutput="na";
    String directionClassifierOutput="na";


    DataBlock[]inputData;



    char direction='D';
    int xCoordinate=0;
    int yCoordinate=0;
    int x=0;
    int y=0;

    int destX=2*UNIT_SIZE;
    int destY=2*UNIT_SIZE;


    int []obstacleX=new int[SCREEN_WIDTH/UNIT_SIZE];
    int []obstacleY= new int[SCREEN_HEIGHT/UNIT_SIZE];


    boolean running=false;
    boolean hitObstacle=false;
    boolean reachedDest=false;
    static boolean simulationOn = false;


    Timer timer;


    public NextDirectionNN_Panel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        move();
        move();
        move();
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
                int temp_X=i%5;
                int temp_Y=i/5;
                FontMetrics metrics=getFontMetrics((g.getFont()));

                if (inputData[i].inputData[1]==1){
                    g.setColor(Color.ORANGE);
                    g.fillRect(temp_X * UNIT_SIZE+1, temp_Y * UNIT_SIZE+1, UNIT_SIZE-2, UNIT_SIZE-2);

                }

                g.setColor(Color.black);


                if (inputData[i].giveCorrectOutput()==0) {
                    if (i==1||i==2||i==3) {
                        String rightArrow=">";
                        g.drawString(rightArrow, temp_X * UNIT_SIZE+ UNIT_SIZE / 2, temp_Y * UNIT_SIZE+UNIT_SIZE/2);
                    }
                    else if (i==5||i==10||i==15||i==20||i==13||i==18||i==17){
                        String upArrow="^";
                        g.drawString(upArrow, temp_X * UNIT_SIZE+UNIT_SIZE/2, temp_Y * UNIT_SIZE+UNIT_SIZE/2);

                    }
                    //g.setColor(Color.pink);

                    else if (i==7){
                        String leftArrow="<";

                        //String leftArrow=" <---------  ";
                        g.drawString(leftArrow, temp_X * UNIT_SIZE+ UNIT_SIZE / 2, temp_Y * UNIT_SIZE+UNIT_SIZE/2);
                    }
                       // g.setColor(Color.DARK_GRAY);
                    else {
                        String downArrow = "v";
                        g.drawString(downArrow, temp_X * UNIT_SIZE + UNIT_SIZE / 2, temp_Y * UNIT_SIZE + UNIT_SIZE / 2);
                    }
                        //g.setColor(Color.ORANGE);

                   // g.fillRect(temp_X * UNIT_SIZE, temp_Y * UNIT_SIZE, UNIT_SIZE-1, UNIT_SIZE-1);


                }
                else if (inputData[i].giveCorrectOutput()==1||inputData[i].giveCorrectOutput()==2){
                    g.drawArc(temp_X * UNIT_SIZE + UNIT_SIZE / 3, temp_Y * UNIT_SIZE + UNIT_SIZE / 3,25,25,0,270);
                    String rightTurn = ">";
                    g.drawString(rightTurn, temp_X * UNIT_SIZE-10 + UNIT_SIZE / 2, temp_Y * UNIT_SIZE + UNIT_SIZE / 2);

                    //g.drawString(rightTurn, temp_X * UNIT_SIZE + UNIT_SIZE / 3, temp_Y * UNIT_SIZE + UNIT_SIZE / 3);

                    //g.setColor(Color.green);
                   // g.fillRect(temp_X * UNIT_SIZE, temp_Y * UNIT_SIZE, UNIT_SIZE-1, UNIT_SIZE-1);
                }
                else{
                    g.drawArc(temp_X * UNIT_SIZE + UNIT_SIZE / 3, temp_Y * UNIT_SIZE + UNIT_SIZE / 3,25,25,0,270);
                    String leftTurn = "<";
                    g.drawString(leftTurn, temp_X * UNIT_SIZE-10 + UNIT_SIZE / 2, temp_Y * UNIT_SIZE + UNIT_SIZE / 2);
                    //g.setColor(Color.yellow);
                   // g.fillRect(temp_X * UNIT_SIZE, temp_Y * UNIT_SIZE, UNIT_SIZE-1, UNIT_SIZE-1);
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
        g.setFont(new Font("Times New Roman",Font.BOLD, 17));

        FontMetrics metrics=getFontMetrics((g.getFont()));

        String t = "null";
        if (rotationIntensityNNOutput==0)
            t="forward";
        else if (rotationIntensityNNOutput==1)
            t="slight-right-turn";
        else if (rotationIntensityNNOutput==2)
            t="sharp-right-turn";
        else if (rotationIntensityNNOutput==3)
            t="slight-left-turn";
        else if (rotationIntensityNNOutput==4)
            t="sharp-left-turn";


        String textPredictionOutput=t;

        String text="RotationIntensityNN: "+textPredictionOutput;
        System.out.println("Inside draw, rotationIntensityNN: "+rotationIntensityNNOutput);
        System.out.println("Inside draw, actualOutput: "+actualOutput);

        g.drawString(text, (SCREEN_WIDTH-metrics.stringWidth(text))/2, g.getFont().getSize());

        String t2 = "null";
        if (actualOutput==0)
            t2="forward";
        else if (actualOutput==1)
            t2="slight-right-turn";
        else if (actualOutput==2)
            t2="sharp-right-turn";
        else if (actualOutput==3)
            t2="slight-left-turn";
        else if (actualOutput==4)
            t2="sharp-left-turn";


        String textActualOutput=t2;
        String text2="Actual Output: "+textActualOutput;

        g.drawString(text2, (SCREEN_WIDTH-metrics.stringWidth(text2))/2, 2*g.getFont().getSize());


        String text3="Prediction: "+predictionOutput;
        g.drawString(text3, (SCREEN_WIDTH-metrics.stringWidth(text3))/2, 3*g.getFont().getSize());


        String text4="SurfaceNN: "+surfaceClassifierOutput+" - DirectionNN: "+directionClassifierOutput;
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

        surfaceClassifierOutput=(inputData[yCoordinate*5+xCoordinate].inputData[1]==1)?"rough":"smooth";
        if (inputData[yCoordinate*5+xCoordinate].inputData[0]==0)
            directionClassifierOutput="forward";
        else if (inputData[yCoordinate*5+xCoordinate].inputData[0]==1)
            directionClassifierOutput="right";
        else
            directionClassifierOutput="left";






        // directionClassifierOutput

        double blockOutput=inputData[yCoordinate*5+xCoordinate].predictBlock();
        rotationIntensityNNOutput=blockOutput;



        
        //String textBlockOutput=t;
        //System.out.println("rotationIntensityNN: "+textBlockOutput);

        double blockActualOutput=inputData[yCoordinate*5+xCoordinate].giveCorrectOutput();
        actualOutput=blockActualOutput;
        String textActualOutput=(actualOutput==0)?"charge required": "charge sufficient";

        System.out.println("Actual output: "+textActualOutput);

        predictionOutput=actualOutput==rotationIntensityNNOutput;

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
                    // if (direction!='R'){
                    direction='L';
                    // }
                    break;

                case KeyEvent.VK_RIGHT:
                    //if (direction!='L'){
                    direction='R';
                    //}
                    break;

                case KeyEvent.VK_UP:
                    // if (direction!='D'){
                    direction='U';
                    // }
                    break;

                case KeyEvent.VK_DOWN:
                    // if (direction!='U'){
                    direction='D';
                    //}
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
