package Simulations.DFS_Simulation;

import Simulations.DataBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

public class DFS_Panel extends JPanel implements ActionListener {

    Block root;
    Block currentBlock;

    //BLOCK corresponds to one block on the grid
    static class Block{
        int index;
        int order;
        boolean visited;
        ArrayList<Block> neighbors;

        Block(int idx)
        {
            this.index=idx;
            this.neighbors=new ArrayList<>();
        }

        public void addNeighbor(Block neighbor)
        {
            this.neighbors.add(neighbor);
        }

        public ArrayList<Block> getNeighbours() {
            return neighbors;
        }
        public void setNeighbours(ArrayList<Block>pNeighbors) {
            this.neighbors = pNeighbors;
        }
    }


//    Variable Declaration

    int SCREEN_WIDTH;
    int SCREEN_HEIGHT=SCREEN_WIDTH;
    int UNIT_SIZE=100;
    int WORLD_UNITS=(SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    int DELAY=75;


    DataBlock[]inputData_Left;
    DataBlock[]inputData_Forward;
    DataBlock[]inputData_Right;
    DataBlock[]inputData_Downward;


    double predictedOutput=0;
    int blocksTraversed=-1 ;


    enum Direction{UP, RIGHT, LEFT, DOWN};

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


    boolean running=false;
    boolean hitObstacle=false;
    boolean reachedDest=false;
    static boolean simulationOn = false;

    Timer timer;


    int size;

    Block []grid= new Block[size];
    Block []orderBlocks= new Block[size];
    
    public void generateTestSets(int size, int[]obstacles){
        System.out.println("size is "+ size);
        grid= new Simulations.DFS_Simulation.DFS_Panel.Block[size];
        orderBlocks= new Simulations.DFS_Simulation.DFS_Panel.Block[size];

        for (int i=0;i<size;i++){
            grid[i]=new Simulations.DFS_Simulation.DFS_Panel.Block(i);
        }
        currentBlock=root=grid[0];

        for (int i=0;i<size;i++){
            double []aRow={1,1,1,1};

            for (int j=0;j<obstacles.length;j++){
                if (i+1==obstacles[j])
                    aRow[2]=0;
                if (i-1==obstacles[j])
                    aRow[0]=0;
                if (i-Math.sqrt(size)==obstacles[j])
                    aRow[1]=0;
                if (i+Math.sqrt(size)==obstacles[j])
                    aRow[3]=0;
            }

            if (i<Math.sqrt(size))
                aRow[1]=0;
            if (Math.abs(i-size)<=Math.sqrt(size))
                aRow[3]=0;
            if (i%Math.sqrt(size)==0)
                aRow[0]=0;
            if ((i+1)%Math.sqrt(size)==0)
                aRow[2]=0;


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

        }
    }

//  Fills grid array with blocks and their neighbours
//    public void generateTestSets(int size, int[]obstacles){
//        System.out.println("size is "+ size);
//        grid= new Block[size];
//        orderBlocks= new Block[size];
//
//        for (int i=0;i<size;i++){
//            grid[i]=new Block(i);
//        }
//        currentBlock=root=grid[0];
//
//        for (int i=0;i<size;i++){
//            double []aRow={1,1,1,1,1,1,1,1};
//
//            for (int j=0;j<obstacles.length;j++){
//                if (i+1==obstacles[j])
//                    aRow[2]=0;
//                if (i-1==obstacles[j])
//                    aRow[0]=0;
//                if (i-Math.sqrt(size)==obstacles[j])
//                    aRow[1]=0;
//                if (i+Math.sqrt(size)==obstacles[j])
//                    aRow[3]=0;
//
//
//                //top left
//                if (i-Math.sqrt(size)-1==obstacles[j])
//                    aRow[4]=0;
//                //top right
//                if (i-Math.sqrt(size)+1==obstacles[j])
//                    aRow[5]=0;
//
//                //btm left
//                if (i+Math.sqrt(size)-1==obstacles[j])
//                    aRow[6]=0;
//                //btm right
//                if (i+Math.sqrt(size)+1==obstacles[j])
//                    aRow[7]=0;
//            }
//
//            if (i<Math.sqrt(size))
//                aRow[1]=0;
//            if (Math.abs(i-size)<=Math.sqrt(size))
//                aRow[3]=0;
//            if (i%Math.sqrt(size)==0)
//                aRow[0]=0;
//            if ((i+1)%Math.sqrt(size)==0)
//                aRow[2]=0;
//
//            //top row
//            if (i<Math.sqrt(size)) {
//                aRow[4] = 0;
//                aRow[5] = 0;
//            }
//
//
//            //btm row
//            if (Math.abs(i-size)<=Math.sqrt(size)) {
//                aRow[6]=0;
//                aRow[7]=0;
//            }
//
//            //left col
//            if (i%Math.sqrt(size)==0) {
//                aRow[6] = 0;
//                aRow[4]=0;
//            }
//
//            if ((i+1)%Math.sqrt(size)==0) {
//                aRow[5] = 0;
//                aRow[7] = 0;
//            }
//            
//            
//
//            if (aRow[0]==1){
//                grid[i].addNeighbor(grid[i-1]);
//            }
//            if (aRow[1]==1){
//                grid[i].addNeighbor(grid[(int) (i-Math.sqrt(size))]);
//            }
//            if (aRow[2]==1){
//                grid[i].addNeighbor(grid[i+1]);
//            }
//            if (aRow[3]==1){
//                grid[i].addNeighbor(grid[(int) (i+Math.sqrt(size))]);
//            }
//
//            if (aRow[4]==1){
//                grid[i].addNeighbor(grid[(int) (i-Math.sqrt(size))-1]);
//            }
//
//            if (aRow[5]==1){
//                grid[i].addNeighbor(grid[(int) (i-Math.sqrt(size))+1]);
//            }
//
//            if (aRow[6]==1){
//                grid[i].addNeighbor(grid[(int) (i+Math.sqrt(size))-1]);
//            }
//
//            if (aRow[7]==1){
//                grid[i].addNeighbor(grid[(int) (i+Math.sqrt(size))+1]);
//            }
//        }
//    }

    public DFS_Panel(int width, int height){
        SCREEN_WIDTH=width;
        SCREEN_HEIGHT=height;
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        //this.dfs(root);
    }


    static int counterObstacle=0;
    public void newObstacle(int x, int y){

        obstacleX[counterObstacle]= x*UNIT_SIZE;
        obstacleY[counterObstacle]= y*UNIT_SIZE;
        counterObstacle++;
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
        if (reachedDest){
            destinationReached(g);
        }
        if (hitObstacle) {
            obstacleCollision(g);
        }


        g.setColor(Color.blue);
        g.setFont(new Font("Times New Roman",Font.BOLD, 18));

        FontMetrics metrics=getFontMetrics((g.getFont()));

        String text="DFS: "+predictedOutput;

        String text2="Blocks traversed: "+blocksTraversed;

        g.drawString(text, (SCREEN_WIDTH-metrics.stringWidth(text))/2, g.getFont().getSize());
        g.drawString(text2, (SCREEN_WIDTH-metrics.stringWidth(text2))/2, 2*g.getFont().getSize());


        timer.stop();

    }

    public void move(){


        blocksTraversed++;
        switch(direction){
            case 'R':
                if (x+UNIT_SIZE<SCREEN_WIDTH) {
                    x = x + UNIT_SIZE;
                    xCoordinate++;
                    currentBlock=grid[currentBlock.index+1];
                }
                break;

            case 'L':
                if (x-UNIT_SIZE>=0) {
                    x = x - UNIT_SIZE;
                    xCoordinate--;
                    currentBlock=grid[currentBlock.index-1];

                }
                break;

            case 'U':
                if (y-UNIT_SIZE>=0) {
                    y = y - UNIT_SIZE;
                    yCoordinate--;
                    currentBlock=grid[currentBlock.index-SCREEN_HEIGHT/UNIT_SIZE];
                }
                break;

            case 'D':
                if (y+UNIT_SIZE<SCREEN_HEIGHT) {
                    y = y + UNIT_SIZE;
                    yCoordinate++;
                    currentBlock=grid[currentBlock.index+SCREEN_HEIGHT/UNIT_SIZE];

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


    public  void dfs(Block b)
    {
        Stack<Block> stack=new  Stack<Block>();
        stack.add(b);
        int counter=0;
        while (!stack.isEmpty())
        {
            Block element=stack.pop();
            //Add the element we visited
            myPath.add(element.index);

            if(!element.visited)
            {
                orderBlocks[counter]=element;
                element.order=counter++;
                System.out.print(element.index + " "+element.order);

                System.out.println();
                element.visited=true;
            }

            ArrayList<Block> neighbours=element.getNeighbours();
            for (int i = 0; i < neighbours.size(); i++) {
                Block n=neighbours.get(i);
                if(n!=null && !n.visited)
                {
                    stack.add(n);
//                    myPath.add(n.index);
                }
            }
        }

        System.out.println("path is "+ myPath.toString());
    }


    static int orderCounter=0;
    public double predictBlockOutput(){
        System.out.println(currentBlock.index);
        System.out.println(predictedOutput);
        if (currentBlock.index==predictedOutput) {
            double blockOutput = orderBlocks[orderCounter].index;
            orderCounter++;
            predictedOutput = blockOutput;
        }
        return predictedOutput;
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
