package Simulations.BFS_Simulation;


import Simulations.DFS_Simulation.DFS_Panel;
import Simulations.DataBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class BFS_Panel extends JPanel implements ActionListener {



    //BLOCK corresponds to one block on the grid
    static class Block{
        int index;
        int order;
        boolean visited;
        ArrayList<Simulations.BFS_Simulation.BFS_Panel.Block> neighbors;

        Block(int idx)
        {
            this.index=idx;
            this.neighbors=new ArrayList<>();
        }

        public void addNeighbor(Simulations.BFS_Simulation.BFS_Panel.Block neighbor)
        {
            this.neighbors.add(neighbor);
        }

        public ArrayList<Simulations.BFS_Simulation.BFS_Panel.Block> getNeighbours() {
            return neighbors;
        }
        public void setNeighbours(ArrayList<Simulations.BFS_Simulation.BFS_Panel.Block>pNeighbors) {
            this.neighbors = pNeighbors;
        }
    }


    //Common Variable Declaration
    int turn =0 ;
    enum Direction{UP, RIGHT, LEFT, DOWN};
    int SCREEN_WIDTH;
    int SCREEN_HEIGHT=SCREEN_WIDTH;
    int UNIT_SIZE=100;
    int WORLD_UNITS=(SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    int DELAY=75;
    int size;

    boolean running=false;
    boolean hitObstacle=false;


    static boolean simulationOn = false;
    Timer timer;




    //Robot 1 Variables
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Simulations.BFS_Simulation.BFS_Panel.Block root;
    Simulations.BFS_Simulation.BFS_Panel.Block currentBlock;

    boolean reachedDest=false;
    boolean nearDest=false;

    DataBlock[]inputData_Left;
    DataBlock[]inputData_Forward;
    DataBlock[]inputData_Right;
    DataBlock[]inputData_Downward;

    double predictedOutput=0;
    int blocksTraversed=-1 ;
    char direction='R';

    int xCoordinate=0;
    int yCoordinate=0;

    int x=0;
    int y=0;

    int destX;
    int destY;

    int []obstacleX=new int[SCREEN_WIDTH/UNIT_SIZE];
    int []obstacleY= new int[SCREEN_HEIGHT/UNIT_SIZE];


    int []visitedX=new int[300];
    int []visitedY=new int[300];
    static int visitCounter=0;

    Simulations.BFS_Simulation.BFS_Panel.Block[]grid= new Simulations.BFS_Simulation.BFS_Panel.Block[size];
    Simulations.BFS_Simulation.BFS_Panel.Block[]orderBlocks= new Simulations.BFS_Simulation.BFS_Panel.Block[size];



    static int orderCounter=0;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    Simulations.BFS_Simulation.BFS_Panel.Block root_2;
    Simulations.BFS_Simulation.BFS_Panel.Block currentBlock_2;


    boolean reachedDest_2=false;
    boolean nearDest_2=false;

    DataBlock[]inputData_Left_2;
    DataBlock[]inputData_Forward_2;
    DataBlock[]inputData_Right_2;
    DataBlock[]inputData_Downward_2;

    double predictedOutput_2=0;
    int blocksTraversed_2=-1 ;
    char direction_2='R';

    int xCoordinate_2=0;
    int yCoordinate_2=0;

    int x_2=50;
    int y_2=50;

    int destX_2;
    int destY_2;


    int []visitedX_2=new int[300];
    int []visitedY_2=new int[300];
    static int visitCounter_2=0;

    Simulations.BFS_Simulation.BFS_Panel.Block[]grid_2= new Simulations.BFS_Simulation.BFS_Panel.Block[size];
    Simulations.BFS_Simulation.BFS_Panel.Block[]orderBlocks_2= new Simulations.BFS_Simulation.BFS_Panel.Block[size];
    static int orderCounter_2=0;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public BFS_Panel(int width, int height){
        SCREEN_WIDTH=width;
        SCREEN_HEIGHT=height;
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new Simulations.BFS_Simulation.BFS_Panel.MyKeyAdapter());
    }

    public void startWorld(){
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


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //  Fills grid array with blocks and their neighbours
    public void generateTestSets(int size, int[]obstacles){
        System.out.println("size is "+ size);
        grid= new BFS_Panel.Block[size];
        orderBlocks= new BFS_Panel.Block[size];

        for (int i=0;i<size;i++){
            grid[i]=new BFS_Panel.Block(i);
        }
        currentBlock=root=grid[0];

        for (int i=0;i<size;i++){
            double []aRow={1,1,1,1,1,1,1,1};

            for (int j=0;j<obstacles.length;j++){
                if (i+1==obstacles[j])
                    aRow[2]=0;
                if (i-1==obstacles[j])
                    aRow[0]=0;
                if (i-Math.sqrt(size)==obstacles[j])
                    aRow[1]=0;
                if (i+Math.sqrt(size)==obstacles[j])
                    aRow[3]=0;


                //top left
                if (i-Math.sqrt(size)-1==obstacles[j])
                    aRow[4]=0;
                //top right
                if (i-Math.sqrt(size)+1==obstacles[j])
                    aRow[5]=0;

                //btm left
                if (i+Math.sqrt(size)-1==obstacles[j])
                    aRow[6]=0;
                //btm right
                if (i+Math.sqrt(size)+1==obstacles[j])
                    aRow[7]=0;
            }

            if (i<Math.sqrt(size))
                aRow[1]=0;
            if (Math.abs(i-size)<=Math.sqrt(size))
                aRow[3]=0;
            if (i%Math.sqrt(size)==0)
                aRow[0]=0;
            if ((i+1)%Math.sqrt(size)==0)
                aRow[2]=0;

            //top row
            if (i<Math.sqrt(size)) {
                aRow[4] = 0;
                aRow[5] = 0;
            }


            //btm row
            if (Math.abs(i-size)<=Math.sqrt(size)) {
                aRow[6]=0;
                aRow[7]=0;
            }

            //left col
            if (i%Math.sqrt(size)==0) {
                aRow[6] = 0;
                aRow[4]=0;
            }

            if ((i+1)%Math.sqrt(size)==0) {
                aRow[5] = 0;
                aRow[7] = 0;
            }

            if (aRow[0]==1){
                grid[i].addNeighbor(grid[i-1]);
            }
            if (aRow[1]==1){
                grid[i].addNeighbor(grid[(int) (i-Math.sqrt(size))]);
            }
            if (aRow[2]==1){
                grid[i].addNeighbor(grid[i+1]);
            }
            if (aRow[3]==1){
                grid[i].addNeighbor(grid[(int) (i+Math.sqrt(size))]);
            }

            if (aRow[4]==1){
                grid[i].addNeighbor(grid[(int) (i-Math.sqrt(size))-1]);
            }

            if (aRow[5]==1){
                grid[i].addNeighbor(grid[(int) (i-Math.sqrt(size))+1]);
            }

            if (aRow[6]==1){
                grid[i].addNeighbor(grid[(int) (i+Math.sqrt(size))-1]);
            }

            if (aRow[7]==1){
                grid[i].addNeighbor(grid[(int) (i+Math.sqrt(size))+1]);
            }
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("size is "+ size);
        grid_2= new BFS_Panel.Block[size];
        orderBlocks_2= new BFS_Panel.Block[size];

        for (int i=0;i<size;i++){
            grid_2[i]=new BFS_Panel.Block(i);
        }
        //unsure
//        root_2.index=0;
        currentBlock_2=root_2=grid_2[1];

        for (int i=0;i<size;i++){
            double []aRow_2={1,1,1,1,1,1,1,1};

            for (int j=0;j<obstacles.length;j++){
                if (i+1==obstacles[j])
                    aRow_2[2]=0;
                if (i-1==obstacles[j])
                    aRow_2[0]=0;
                if (i-Math.sqrt(size)==obstacles[j])
                    aRow_2[1]=0;
                if (i+Math.sqrt(size)==obstacles[j])
                    aRow_2[3]=0;


                //top left
                if (i-Math.sqrt(size)-1==obstacles[j])
                    aRow_2[4]=0;
                //top right
                if (i-Math.sqrt(size)+1==obstacles[j])
                    aRow_2[5]=0;

                //btm left
                if (i+Math.sqrt(size)-1==obstacles[j])
                    aRow_2[6]=0;
                //btm right
                if (i+Math.sqrt(size)+1==obstacles[j])
                    aRow_2[7]=0;
            }

            if (i<Math.sqrt(size))
                aRow_2[1]=0;
            if (Math.abs(i-size)<=Math.sqrt(size))
                aRow_2[3]=0;
            if (i%Math.sqrt(size)==0)
                aRow_2[0]=0;
            if ((i+1)%Math.sqrt(size)==0)
                aRow_2[2]=0;

            //top row
            if (i<Math.sqrt(size)) {
                aRow_2[4] = 0;
                aRow_2[5] = 0;
            }


            //btm row
            if (Math.abs(i-size)<=Math.sqrt(size)) {
                aRow_2[6]=0;
                aRow_2[7]=0;
            }

            //left col
            if (i%Math.sqrt(size)==0) {
                aRow_2[6] = 0;
                aRow_2[4]=0;
            }

            if ((i+1)%Math.sqrt(size)==0) {
                aRow_2[5] = 0;
                aRow_2[7] = 0;
            }

            if (aRow_2[0]==1){
                grid_2[i].addNeighbor(grid_2[i-1]);
            }
            if (aRow_2[1]==1){
                grid_2[i].addNeighbor(grid_2[(int) (i-Math.sqrt(size))]);
            }
            if (aRow_2[2]==1){
                grid[i].addNeighbor(grid[i+1]);
            }
            if (aRow_2[3]==1){
                grid[i].addNeighbor(grid[(int) (i+Math.sqrt(size))]);
            }

            if (aRow_2[4]==1){
                grid[i].addNeighbor(grid[(int) (i-Math.sqrt(size))-1]);
            }

            if (aRow_2[5]==1){
                grid[i].addNeighbor(grid[(int) (i-Math.sqrt(size))+1]);
            }

            if (aRow_2[6]==1){
                grid[i].addNeighbor(grid[(int) (i+Math.sqrt(size))-1]);
            }

            if (aRow_2[7]==1){
                grid[i].addNeighbor(grid[(int) (i+Math.sqrt(size))+1]);
            }
        }
    }



    static int counterObstacle=0;
    public void newObstacle(int x, int y){

        obstacleX[counterObstacle]= x*UNIT_SIZE;
        obstacleY[counterObstacle]= y*UNIT_SIZE;
        counterObstacle++;
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
                g.fillOval(obstacleX[i], obstacleY[i], UNIT_SIZE, UNIT_SIZE);
            }

            g.setColor(Color.green);

            g.fillRect(destX,destY,UNIT_SIZE,UNIT_SIZE);

            g.setColor(Color.gray);
            g.fillRect(x, y, UNIT_SIZE, UNIT_SIZE);

            //VISION FIELD
            g.setColor(Color.yellow);
            //right
            g.fillRect(x+UNIT_SIZE+UNIT_SIZE/3+3,y+UNIT_SIZE+UNIT_SIZE/3+3, UNIT_SIZE/5, UNIT_SIZE/5);
            g.fillRect(x+UNIT_SIZE+UNIT_SIZE/3+3,y-UNIT_SIZE/3-UNIT_SIZE/3, UNIT_SIZE/5, UNIT_SIZE/5);
            g.fillRect(x+UNIT_SIZE+UNIT_SIZE/3+3,y+UNIT_SIZE/3, UNIT_SIZE/5, UNIT_SIZE/5);

            //top
            g.fillRect(x+UNIT_SIZE/3+3,y-UNIT_SIZE/3-UNIT_SIZE/3, UNIT_SIZE/5, UNIT_SIZE/5);

            //btm
            g.fillRect(x+UNIT_SIZE/3+3,y+UNIT_SIZE+UNIT_SIZE/3, UNIT_SIZE/5, UNIT_SIZE/5);


            //left
            g.fillRect(x-UNIT_SIZE+UNIT_SIZE/3,y+UNIT_SIZE+UNIT_SIZE/3+3, UNIT_SIZE/5, UNIT_SIZE/5);
            g.fillRect(x-UNIT_SIZE+UNIT_SIZE/3,y-UNIT_SIZE/3-UNIT_SIZE/3, UNIT_SIZE/5, UNIT_SIZE/5);
            g.fillRect(x-UNIT_SIZE+UNIT_SIZE/3,y+UNIT_SIZE/3, UNIT_SIZE/5, UNIT_SIZE/5);

            g.setColor(Color.gray);
            visitedX[visitCounter]=x;
            visitedY[visitCounter++]=y;

            g.setColor(Color.black);
            for (int i=0;i<visitedX.length;i++){
//                System.out.println("vis"+ visitedX[i]);
                g.drawString("V",visitedX[i]+25,visitedY[i]+25);
            }
            g.setColor(Color.blue);
            double width=SCREEN_WIDTH/UNIT_SIZE;
        }

        if (nearDest)
            destinationNeared(g);
        if (reachedDest){
            destinationReached(g);
        }
        if (hitObstacle) {
            obstacleCollision(g);
        }

        g.setColor(Color.blue);
        g.setFont(new Font("Times New Roman",Font.BOLD, 18));
        FontMetrics metrics=getFontMetrics((g.getFont()));
        String text="BFS: "+predictedOutput;
        String text2="Blocks traversed: "+blocksTraversed;
        g.drawString(text, (SCREEN_WIDTH-metrics.stringWidth(text))/2, g.getFont().getSize());
        g.drawString(text2, (SCREEN_WIDTH-metrics.stringWidth(text2))/2, 2*g.getFont().getSize());


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (!reachedDest_2 && !hitObstacle) {
//            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
//                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
//                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
//            }

//            for (int i=0;i<(SCREEN_HEIGHT/UNIT_SIZE);i++){
//                for (int j=0;j<(SCREEN_HEIGHT/UNIT_SIZE);j++){
//                    String number=(i*(SCREEN_HEIGHT/UNIT_SIZE)+j)+"";
//                    g.drawString(number,j*UNIT_SIZE,i*UNIT_SIZE+UNIT_SIZE);
//                }
//            }
            g.setColor(Color.LIGHT_GRAY);

            g.setColor(Color.red);

//            for (int i = 0; i < counterObstacle; i++) {
//                g.fillOval(obstacleX[i], obstacleY[i], UNIT_SIZE, UNIT_SIZE);
//            }

            g.setColor(Color.green);

//            g.fillRect(destX_2,destY_2,UNIT_SIZE,UNIT_SIZE);

            g.setColor(Color.gray);
            g.fillRect(x_2, y_2, UNIT_SIZE, UNIT_SIZE);

            //VISION FIELD
            g.setColor(Color.yellow);
            //right
            g.fillRect(x_2+UNIT_SIZE+UNIT_SIZE/3+3,y_2+UNIT_SIZE+UNIT_SIZE/3+3, UNIT_SIZE/5, UNIT_SIZE/5);
            g.fillRect(x_2+UNIT_SIZE+UNIT_SIZE/3+3,y_2-UNIT_SIZE/3-UNIT_SIZE/3, UNIT_SIZE/5, UNIT_SIZE/5);
            g.fillRect(x_2+UNIT_SIZE+UNIT_SIZE/3+3,y_2+UNIT_SIZE/3, UNIT_SIZE/5, UNIT_SIZE/5);

            //top
            g.fillRect(x_2+UNIT_SIZE/3+3,y_2-UNIT_SIZE/3-UNIT_SIZE/3, UNIT_SIZE/5, UNIT_SIZE/5);

            //btm
            g.fillRect(x_2+UNIT_SIZE/3+3,y_2+UNIT_SIZE+UNIT_SIZE/3, UNIT_SIZE/5, UNIT_SIZE/5);


            //left
            g.fillRect(x_2-UNIT_SIZE+UNIT_SIZE/3,y_2+UNIT_SIZE+UNIT_SIZE/3+3, UNIT_SIZE/5, UNIT_SIZE/5);
            g.fillRect(x_2-UNIT_SIZE+UNIT_SIZE/3,y_2-UNIT_SIZE/3-UNIT_SIZE/3, UNIT_SIZE/5, UNIT_SIZE/5);
            g.fillRect(x_2-UNIT_SIZE+UNIT_SIZE/3,y_2+UNIT_SIZE/3, UNIT_SIZE/5, UNIT_SIZE/5);

            g.setColor(Color.gray);
            visitedX[visitCounter]=x_2;
            visitedY[visitCounter++]=y_2;

            g.setColor(Color.black);
            for (int i=0;i<visitedX_2.length;i++){
//                System.out.println("vis"+ visitedX[i]);
                g.drawString("V2",visitedX_2[i]+25,visitedY_2[i]+25);
            }
            g.setColor(Color.blue);
            double width=SCREEN_WIDTH/UNIT_SIZE;
        }

        if (nearDest)
            destinationNeared(g);
        if (reachedDest){
            destinationReached(g);
        }
        if (hitObstacle) {
            obstacleCollision(g);
        }

        g.setColor(Color.blue);
        g.setFont(new Font("Times New Roman",Font.BOLD, 18));
        FontMetrics metrics_2=getFontMetrics((g.getFont()));
        String text_2="BFS_2: "+predictedOutput_2;
        String text2_2="Blocks traversed 2: "+blocksTraversed_2;
        g.drawString(text_2, (SCREEN_WIDTH-metrics.stringWidth(text_2))/2, 4*g.getFont().getSize());
        g.drawString(text2_2, (SCREEN_WIDTH-metrics.stringWidth(text2_2))/2, 5*g.getFont().getSize());

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        timer.stop();
    }

    public void move() {
        turn++;
        turn = (turn) % 2;


        if (turn == 0) {

            blocksTraversed++;
            switch (direction) {
                case 'R':
                    if (x + UNIT_SIZE < SCREEN_WIDTH) {
                        x = x + UNIT_SIZE;
                        xCoordinate++;
                        currentBlock = grid[currentBlock.index + 1];
                    }
                    break;

                case 'L':
                    if (x - UNIT_SIZE >= 0) {
                        x = x - UNIT_SIZE;
                        xCoordinate--;
                        currentBlock = grid[currentBlock.index - 1];

                    }
                    break;

                case 'U':
                    if (y - UNIT_SIZE >= 0) {
                        y = y - UNIT_SIZE;
                        yCoordinate--;
                        currentBlock = grid[currentBlock.index - SCREEN_HEIGHT / UNIT_SIZE];
                    }
                    break;

                case 'D':
                    if (y + UNIT_SIZE < SCREEN_HEIGHT) {
                        y = y + UNIT_SIZE;
                        yCoordinate++;
                        currentBlock = grid[currentBlock.index + SCREEN_HEIGHT / UNIT_SIZE];

                    }
                    break;
            }

            running = false;
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        else if (turn == 1) {
            blocksTraversed_2++;
            switch (direction) {
                case 'R':
                    if (x_2 + UNIT_SIZE < SCREEN_WIDTH) {
                        x_2 = x_2 + UNIT_SIZE;
                        xCoordinate_2++;
                        currentBlock_2 = grid_2[currentBlock_2.index + 1];
                    }
                    break;

                case 'L':
                    if (x_2 - UNIT_SIZE >= 0) {
                        x_2 = x_2 - UNIT_SIZE;
                        xCoordinate_2--;
                        currentBlock_2 = grid_2[currentBlock_2.index - 1];

                    }
                    break;

                case 'U':
                    if (y_2 - UNIT_SIZE >= 0) {
                        y_2 = y_2 - UNIT_SIZE;
                        yCoordinate_2--;
                        currentBlock_2 = grid_2[currentBlock_2.index - SCREEN_HEIGHT / UNIT_SIZE];
                    }
                    break;

                case 'D':
                    if (y_2 + UNIT_SIZE < SCREEN_HEIGHT) {
                        y_2 = y_2 + UNIT_SIZE;
                        yCoordinate_2++;
                        currentBlock_2 = grid_2[currentBlock_2.index + SCREEN_HEIGHT / UNIT_SIZE];

                    }
                    break;
            }

            running = false;
        }
    }
    public void checkCollision(){
        //check if collision with obstalce
        for (int i=0;i<counterObstacle;i++){
            if (obstacleX[i]==x&&obstacleY[i]==y)
                hitObstacle=true;
        }

        //check if reached destination

        System.out.println("x: "+x +"y: "+y);
        System.out.println("destx: "+destX+" desty: "+destY);

        if (!(Math.abs(x-destX)/50==1 && Math.abs(y-destY)/50==1) || (Math.abs(x-destX)/50==1 && Math.abs(y-destY)/50==0)|| (Math.abs(x-destX)/50==0 && Math.abs(y-destY)/50==1)) {
            nearDest = false;
        }
        if ((Math.abs(x-destX)/50==1 && Math.abs(y-destY)/50==1) || (Math.abs(x-destX)/50==1 && Math.abs(y-destY)/50==0)|| (Math.abs(x-destX)/50==0 && Math.abs(y-destY)/50==1)) {
            nearDest = true;
        }
        if (x==destX&&y==destY)
            reachedDest=true;

        if (!running);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //check if collision with obstalce
        for (int i=0;i<counterObstacle;i++){
            if (obstacleX[i]==x_2&&obstacleY[i]==y_2)
                hitObstacle=true;
        }

        //check if reached destination

        System.out.println("x_2: "+x_2 +"y_2: "+y_2);
        System.out.println("destx_2: "+destX_2+" desty_2: "+destY_2);

        if (!(Math.abs(x_2-destX_2)/50==1 && Math.abs(y_2-destY_2)/50==1) || (Math.abs(x_2-destX_2)/50==1 && Math.abs(y_2-destY_2)/50==0)|| (Math.abs(x_2-destX_2)/50==0 && Math.abs(y_2-destY_2)/50==1)) {
            nearDest_2 = false;
        }
        if ((Math.abs(x_2-destX_2)/50==1 && Math.abs(y_2-destY_2)/50==1) || (Math.abs(x_2-destX_2)/50==1 && Math.abs(y_2-destY_2)/50==0)|| (Math.abs(x_2-destX_2)/50==0 && Math.abs(y_2-destY_2)/50==1)) {
            nearDest_2 = true;
        }
        if (x_2==destX_2&&y_2==destY_2)
            reachedDest_2=true;

        if (!running);

    }

    public void destinationReached(Graphics g){
        g.setColor(Color.GREEN);
        g.setFont(new Font("Times New Roman",Font.BOLD, 50));

        FontMetrics metrics=getFontMetrics((g.getFont()));
        String text="Destination Reached";
        g.drawString(text, (SCREEN_WIDTH-metrics.stringWidth(text))/2, SCREEN_HEIGHT/2);
    }

    public void destinationNeared(Graphics g){
        g.setColor(Color.green);
        g.setFont(new Font("Times New Roman",Font.BOLD, 18));

        FontMetrics metrics=getFontMetrics((g.getFont()));

        String text="Destination Located";

        g.drawString(text, (SCREEN_WIDTH-metrics.stringWidth(text))/2, 3*g.getFont().getSize());
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

        System.out.println("running2 is "+running);
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

    Stack <Integer> myPath = new Stack<>();




    public void BFS(Simulations.BFS_Simulation.BFS_Panel.Block b, Simulations.BFS_Simulation.BFS_Panel.Block b_2)
    {

        System.out.printf("bfs will begin");

        ////////////////////////////////////////////////////////////////////////////////////////////////////

        int counter=0;
        LinkedList<Simulations.BFS_Simulation.BFS_Panel.Block> queue = new LinkedList<Simulations.BFS_Simulation.BFS_Panel.Block>();
        b.visited=true;
        queue.add(b);

        while (queue.size() != 0)
        {
            Simulations.BFS_Simulation.BFS_Panel.Block element=queue.poll();
            orderBlocks[counter] = element;
            element.order = counter++;
            System.out.print(element.index+" ");
            ArrayList<Simulations.BFS_Simulation.BFS_Panel.Block> nb = element.getNeighbours();

            for (int i=0;i<nb.size();i++){
                Simulations.BFS_Simulation.BFS_Panel.Block n = nb.get(i);
                if (!n.visited){
                    n.visited= true;
                    queue.add(n);
                }
            }
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////

        int counter_2=0;
        LinkedList<Simulations.BFS_Simulation.BFS_Panel.Block> queue_2 = new LinkedList<Simulations.BFS_Simulation.BFS_Panel.Block>();
        b_2.visited=true;
        queue_2.add(b_2);

        while (queue_2.size() != 0)
        {
            Simulations.BFS_Simulation.BFS_Panel.Block element_2=queue_2.poll();
            orderBlocks_2[counter_2] = element_2;
            element_2.order = counter_2++;
            System.out.print(element_2.index+" ");
            ArrayList<Simulations.BFS_Simulation.BFS_Panel.Block> nb_2 = element_2.getNeighbours();

            for (int i=0;i<nb_2.size();i++){
                Simulations.BFS_Simulation.BFS_Panel.Block n_2 = nb_2.get(i);
                if (!n_2.visited){
                    n_2.visited= true;
                    queue_2.add(n_2);
                }
            }
        }



    }




    public double [] predictBlockOutput(){
        System.out.println(currentBlock.index);
        System.out.println(predictedOutput);
        if (currentBlock.index==predictedOutput) {
            double blockOutput = orderBlocks[orderCounter].index;
            orderCounter++;
            predictedOutput = blockOutput;
        }
        ///////////////////////

        System.out.println(currentBlock_2.index);
        System.out.println(predictedOutput_2);
        if (currentBlock_2.index==predictedOutput_2) {
            double blockOutput_2 = orderBlocks_2[orderCounter_2].index;
            orderCounter_2++;
            predictedOutput_2 = blockOutput_2;
        }


        return new double[]{predictedOutput, predictedOutput_2};

    }



    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            System.out.println("running = "+ running);

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
