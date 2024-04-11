package mahesri;

import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 


public class Board extends JPanel implements ActionListener {

    static final int borderWidth = 600;
    static final int borderHeight = 600;
    static final int unitsSize = 20;
    static final int gameUnits = (borderWidth*borderHeight)/unitsSize;
    static final int delay = 100;

    final int x[] = new int[gameUnits];
    final int y[] = new int[gameUnits];

    int bodyParts = 6; 

    boolean running = false;

    int appleX;
    int appleY;

    Timer timer;
    Random random;

    Image apple;
    Image headX;
    Image headY;


    public Board(){
      random = new Random();
      this.setPreferredSize(new Dimension(borderWidth, borderHeight));
      this.setBackground(new Color(333333));
      startGame();
      loadImage();
    }
    
    public void startGame(){
      running = true;
      timer = new Timer(delay, this);
      timer.start();
      locateApple();
    }

    public void loadImage(){
      ImageIcon iia = new ImageIcon("src/resources/apple.png");
      apple = iia.getImage();

      ImageIcon ihx = new ImageIcon("src/resources/headX.png");
      headX = ihx.getImage();

      ImageIcon ihy = new ImageIcon("src/resources/headY.png");
      headY = ihy.getImage();
    }

   @Override
   public void paintComponent(Graphics g){

      super.paintComponent(g);
      doDraw(g);
    }

    public void doDraw(Graphics g){
      

    if(running){
    g.drawImage(apple, appleX, appleY, unitsSize, unitsSize, null);
    
    }

    }

    public void locateApple(){
      appleX = random.nextInt((int)(borderWidth/unitsSize))*unitsSize;
      appleY = random.nextInt((int)(borderHeight/unitsSize))*unitsSize;
    }

    public void checkApple(){
      if((x[0] == appleX)&& (y[0]== appleY)){

      }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

      if(running){
        checkApple();
      }
      repaint();
    }}