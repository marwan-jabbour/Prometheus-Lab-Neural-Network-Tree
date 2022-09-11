package Simulations.NeuralNetworksV1.Level2.ChangeDirectionSimulation;

import Simulations.DataBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class ChangeDirectionNN_Panel extends JPanel implements ActionListener {
     int SCREEN_WIDTH=500;
     int SCREEN_HEIGHT=500;
     int UNIT_SIZE=100;
     int WORLD_UNITS=(SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY=75;


    DataBlock[]inputData_Left;
    DataBlock[]inputData_Forward;
    DataBlock[]inputData_Right;
    DataBlock[]inputData_Downward;

    boolean changingDirection=false;

    double actualOutput=-1;
    double predictedOutput=-1;
    int blocksTraversed=0;


    enum Direction{UP, RIGHT, LEFT, DOWN};

    Direction nextDirection;
    char direction='R';
    char pointer='u';


    int xCoordinate=0;
    int yCoordinate=0;
    int x=0;
    int y=0;

    int destX;
    int destY;

    int []obstacleX=new int[SCREEN_WIDTH*SCREEN_HEIGHT/(UNIT_SIZE*UNIT_SIZE)];
    int []obstacleY=new int[SCREEN_WIDTH*SCREEN_HEIGHT/(UNIT_SIZE*UNIT_SIZE)];

    int size;

    boolean running=false;
    boolean hitObstacle=false;
    boolean reachedDest=false;
    static boolean simulationOn = false;


    Timer timer;


    public ChangeDirectionNN_Panel(int width, int height, int destX, int destY){
        SCREEN_WIDTH=width;
        SCREEN_HEIGHT=height;
        this.destX=destX*UNIT_SIZE;
        this.destY=destY*UNIT_SIZE;
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
    }




    static int counterObstacle=0;
    public void newObstacle(int x, int y){

        obstacleX[counterObstacle]= x*UNIT_SIZE;
        obstacleY[counterObstacle]= y*UNIT_SIZE;
        counterObstacle++;

        System.out.println(counterObstacle);

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

            for (int i=0;i<(SCREEN_HEIGHT/UNIT_SIZE);i++){
                for (int j=0;j<(SCREEN_HEIGHT/UNIT_SIZE);j++){
                    String number=(i*(SCREEN_HEIGHT/UNIT_SIZE)+j)+"";
                    g.drawString(number,j*UNIT_SIZE,i*UNIT_SIZE+UNIT_SIZE);
                }
            }
            g.setColor(Color.LIGHT_GRAY);

            g.setColor(Color.red);

            for (int i = 0; i < counterObstacle; i++) {
                System.out.println(""+obstacleX[i]+" "+obstacleY[i]);
                g.fillOval(obstacleX[i], obstacleY[i], UNIT_SIZE, UNIT_SIZE);
            }

            g.setColor(Color.green);

            g.fillRect(destX,destY,UNIT_SIZE,UNIT_SIZE);



            g.setColor(Color.blue);

            double width=SCREEN_WIDTH/UNIT_SIZE;

            if (pointer=='u') {
                int [] x_coordinates = {50+x,100+x,x};
                int [] y_coordinates = {0+y,100+y,100+y};
                g.drawPolygon(x_coordinates, y_coordinates, 3);

            }

            else if (pointer=='d') {
                int [] x_coordinates = {x,50+x,100+x};
                int [] y_coordinates = {y,100+y,y};
                g.drawPolygon(x_coordinates, y_coordinates, 3);

            }

            else if (pointer=='r') {
                int [] x_coordinates = {x,x,100+x};
                int [] y_coordinates = {0+y,100+y,50+y};
                g.drawPolygon(x_coordinates, y_coordinates, 3);
            }

            else if (pointer=='l') {
                int [] x_coordinates = {x,100+x,100+x};
                int [] y_coordinates = {50+y,0+y,100+y};
                g.drawPolygon(x_coordinates, y_coordinates, 3);
            }


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

        String dir="";
        if (predictedOutput==0)
            dir="forward";
        else if (predictedOutput==1)
            dir="right";
        else if (predictedOutput==2)
            dir="left";
        else if (predictedOutput==3)
        dir="180 turn";

        String text="changeDirectionNN: "+dir;

        dir="";
        if (actualOutput==0)
            dir="forward";
        else if (actualOutput==1)
            dir="right";
        else if (actualOutput==2)
            dir="left";
        else if (actualOutput==3)
            dir="180 turn";


        String text2="actual output: "+dir;
        String text3="prediction: "+(predictedOutput==actualOutput);

        String text4="Blocks traversed: "+blocksTraversed;
        String text5="next direction: "+nextDirection.toString();

        g.drawString(text, (SCREEN_WIDTH-metrics.stringWidth(text))/2, g.getFont().getSize());


        g.drawString(text2, (SCREEN_WIDTH-metrics.stringWidth(text2))/2, 2*g.getFont().getSize());


        g.drawString(text3, (SCREEN_WIDTH-metrics.stringWidth(text3))/2, 3*g.getFont().getSize());

        g.drawString(text4, (SCREEN_WIDTH-metrics.stringWidth(text4))/2, 4*g.getFont().getSize());

        g.drawString(text5, (SCREEN_WIDTH-metrics.stringWidth(text5))/2, 5*g.getFont().getSize());


        timer.stop();

    }

    public void move(){

        blocksTraversed++;
        switch(pointer){
            case 'r':
                if (x+UNIT_SIZE<SCREEN_WIDTH) {
                    x = x + UNIT_SIZE;
                    xCoordinate++;
                }
                break;

            case 'l':
                if (x-UNIT_SIZE>=0) {
                    x = x - UNIT_SIZE;
                    xCoordinate--;
                }
                break;

            case 'u':
                if (y-UNIT_SIZE>=0) {
                    y = y - UNIT_SIZE;
                    yCoordinate--;
                }
                break;

            case 'd':
                if (y+UNIT_SIZE<SCREEN_HEIGHT) {
                    y = y + UNIT_SIZE;
                    yCoordinate++;
                }
                break;
        }

        running=false;
        changingDirection=false;


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
       if (changingDirection){
            repaint();
            changingDirection=false;
            return;
        }


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

        int multiply=SCREEN_HEIGHT/UNIT_SIZE;

        double[]input=new double[3];
        double blockOutput=-1;

        if (pointer=='u'){
            input[0]=inputData_Forward[yCoordinate*multiply+xCoordinate].inputData[0];
            input[1]=inputData_Forward[yCoordinate*multiply+xCoordinate].inputData[1];
            input[2]=inputData_Forward[yCoordinate*multiply+xCoordinate].inputData[2];
            blockOutput=inputData_Forward[yCoordinate*multiply+xCoordinate].predictBlock(input);

            actualOutput=inputData_Forward[yCoordinate*multiply+xCoordinate].giveCorrectOutput();
            predictedOutput=blockOutput;
        }

        else if (pointer=='r'){
            input[0]=inputData_Right[yCoordinate*multiply+xCoordinate].inputData[0];
            input[1]=inputData_Right[yCoordinate*multiply+xCoordinate].inputData[1];
            input[2]=inputData_Right[yCoordinate*multiply+xCoordinate].inputData[2];
            blockOutput=inputData_Right[yCoordinate*multiply+xCoordinate].predictBlock(input);
            actualOutput=inputData_Right[yCoordinate*multiply+xCoordinate].giveCorrectOutput();
            predictedOutput=blockOutput;
        }

        else if (pointer=='l'){
            input[0]=inputData_Left[yCoordinate*multiply+xCoordinate].inputData[0];
            input[1]=inputData_Left[yCoordinate*multiply+xCoordinate].inputData[1];
            input[2]=inputData_Left[yCoordinate*multiply+xCoordinate].inputData[2];
            blockOutput=inputData_Left[yCoordinate*multiply+xCoordinate].predictBlock(input);

            actualOutput=inputData_Left[yCoordinate*multiply+xCoordinate].giveCorrectOutput();
            predictedOutput=blockOutput;
        }

        else if (pointer=='d'){
            input[0]=inputData_Downward[yCoordinate*multiply+xCoordinate].inputData[0];
            input[1]=inputData_Downward[yCoordinate*multiply+xCoordinate].inputData[1];
            input[2]=inputData_Downward[yCoordinate*multiply+xCoordinate].inputData[2];
            blockOutput=inputData_Downward[yCoordinate*multiply+xCoordinate].predictBlock(input);

            actualOutput=inputData_Downward[yCoordinate*multiply+xCoordinate].giveCorrectOutput();
            predictedOutput=blockOutput;
        }

        if (pointer=='u'){
            if(predictedOutput==0)
                nextDirection=Direction.UP;
            else if (predictedOutput==1)
                nextDirection=Direction.RIGHT;
            else if (predictedOutput==2)
                nextDirection=Direction.LEFT;
            else if (predictedOutput==3)
                nextDirection=Direction.DOWN;
        }

        else if (pointer=='r'){
            if(predictedOutput==0)
                nextDirection=Direction.RIGHT;
            else if (predictedOutput==1)
                nextDirection=Direction.DOWN;
            else if (predictedOutput==2)
                nextDirection=Direction.UP;
            else if (predictedOutput==3)
                nextDirection=Direction.LEFT;
        }

        else if (pointer=='l'){
            if(predictedOutput==0)
                nextDirection=Direction.LEFT;
            else if (predictedOutput==1)
                nextDirection=Direction.UP;
            else if (predictedOutput==2)
                nextDirection=Direction.DOWN;
            else if (predictedOutput==3)
                nextDirection=Direction.RIGHT;
        }

        else if (pointer=='d'){
            if(predictedOutput==0)
                nextDirection=Direction.DOWN;
            else if (predictedOutput==1)
                nextDirection=Direction.LEFT;
            else if (predictedOutput==2)
                nextDirection=Direction.RIGHT;
            else if (predictedOutput==3)
                nextDirection=Direction.UP;
        }


        return blockOutput;
    }

    public void setInputData(DataBlock[] aDataLeft, DataBlock[] aDataFwd, DataBlock[] aDataRight, DataBlock[] aDataDown) {
        inputData_Left=aDataLeft;
        inputData_Forward=aDataFwd;
        inputData_Right=aDataRight;
        inputData_Downward=aDataDown;
        startWorld();
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){



           if (e.getKeyCode()==KeyEvent.VK_ENTER) {
               running = true;
               timer.start();
               return;
            }

           else if (e.getKeyCode()==KeyEvent.VK_R||e.getKeyCode()==KeyEvent.VK_U||
                   e.getKeyCode()==KeyEvent.VK_L||e.getKeyCode()==KeyEvent.VK_D) {
               changingDirection = true;
               timer.start();
           }


            switch (e.getKeyCode()){

                case KeyEvent.VK_U:
                    pointer='u';
                    break;

                case KeyEvent.VK_D:
                    pointer='d';
                    break;

                case KeyEvent.VK_R:
                    pointer='r';
                    break;

                case KeyEvent.VK_L:
                    pointer='l';
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
