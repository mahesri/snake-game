package mahesri;

import java.util.Random;
import javax.sound.sampled.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener {

    static final int borderWidth = 600;
    static final int borderHeight = 600;
    static final int unitsSize = 20;
    static final int gameUnits = (borderWidth*borderHeight)/unitsSize;
    static final int delay = 100;

    final int x[] = new int[gameUnits];
    final int y[] = new int[gameUnits];

    int bodyParts = 6;
    int appleEaten;

    boolean running = false;

    char direction = 'R';

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
      this.addKeyListener(new MyKeyAdapter());
      this.setFocusable(true);
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
      ImageIcon iia = new ImageIcon("src/resources/Image/apple.png");
      apple = iia.getImage();

      ImageIcon ihx = new ImageIcon("src/resources/Image/headX.png");
      headX = ihx.getImage();

      ImageIcon ihy = new ImageIcon("src/resources/Image/headY.png");
      headY = ihy.getImage();
    }

    public static void playAudio(AudioInputStream ais)throws Exception {

        
      Clip clip = AudioSystem.getClip();
      clip.open(ais);
      clip.start();
 }

 public static void loadSound( int keySound){
  if(keySound == 1)  
     try{
         AudioInputStream ais = AudioSystem.getAudioInputStream(new File("src/resources/Sound/mixkit-arcade-retro-changing-tab-206.wav"));
         playAudio(ais);
     }catch (Exception e){
         e.printStackTrace();
     }

     if(keySound == 2){
         try{
             AudioInputStream ais = AudioSystem.getAudioInputStream(new File("src/resources/Sound/mixkit-retro-game-notification-212.wav"));
             playAudio(ais);
         }catch (Exception e){
             e.printStackTrace();
         }
         
     }
    }

   @Override
   public void paintComponent(Graphics g){

      super.paintComponent(g);
      doDraw(g);
    }

    public void doDraw(Graphics g){


    if(running){
    g.drawImage(apple, appleX, appleY, unitsSize, unitsSize, null);
    
      for(int i = 0; i < bodyParts;i++){
        
        if(i == 0){
          if(direction == 'R' || direction == 'L'){
            g.drawImage(headY, x[i], y[i], unitsSize, unitsSize, null);
          }else{
            g.drawImage(headX, x[i], y[i], unitsSize, unitsSize, null);
          }

        }else{                   
          g.setColor(new Color(random.nextInt(225),random.nextInt(255),random.nextInt(255),random.nextInt(255)));
          g.fillOval(x[i], y[i], unitsSize, unitsSize);
        }
      }
      g.setColor(Color.white);
      g.setFont(new Font("Waltograph UI",Font.BOLD, 20));
      FontMetrics metrics = getFontMetrics(g.getFont());
      g.drawString("Score : "+appleEaten, (borderWidth - metrics.stringWidth("Score : "+appleEaten))/2, borderHeight/7);

    }else{
      gameOver(g);
    }

    }



    public void locateApple(){
      appleX = random.nextInt((int)(borderWidth/unitsSize))*unitsSize;
      appleY = random.nextInt((int)(borderHeight/unitsSize))*unitsSize;
    }

    public void move(){

      for(int i = bodyParts; i > 0; i--){
        x[i] = x[i-1];
        y[i] = y[i-1];
      }

      switch (direction) {
        case 'U':
          y[0] = y[0] - unitsSize;
          break;
          case 'D':
          y[0] = y[0] + unitsSize;
          break;
          case 'L':
          x[0] = x[0] - unitsSize;
          break;
          case 'R':
          x[0] = x[0] + unitsSize;
          break;
 
      }


    }

    public void checkApple(){
      if((x[0] == appleX) && (y[0]== appleY)){
       
        loadSound(1);

        bodyParts++;
        appleEaten++;
        locateApple();
      }
    }

    public void checkCollisions() {

      for(int i = bodyParts; i > 0; i--){
        if((x[0] == x[i])&&(y[0] == y[i])){
          running = false;
        }
      }

      if(x[0] < 0){
        running = false;
      }

      if(x[0] >= borderWidth){
        running = false;
      }

      if(y[0] < 0){
        running = false;
      }

      if(y[0]>=borderHeight){
        running = false;
      }

      if(!running){
        timer.stop();
        loadSound(2);
      }
    }

    public void gameOver(Graphics g){


      g.setColor(new Color(115, 255, 65));
        g.setFont(new Font("Waltograph UI",Font.BOLD, 50));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Game Over!", (borderWidth - metrics1.stringWidth("Game Over!"))/2 , borderHeight /5);

        g.setColor(Color.white);
        g.setFont(new Font("Waltograph UI",Font.BOLD, 20));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score : "+appleEaten, (borderWidth - metrics2.stringWidth("Score"+appleEaten))/2, borderHeight/3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

      if(running){
        checkApple();
        move();
        checkCollisions();
      }
      repaint();
    }
  
  public class MyKeyAdapter extends KeyAdapter {
  
    @Override
    public void keyPressed(KeyEvent e){
      switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
        if(direction != 'R'){
          direction = 'L';
        }
          break;
        case KeyEvent.VK_RIGHT:
        if(direction != 'L'){
          direction = 'R';
        }
          break;
          case KeyEvent.VK_UP:
          if(direction != 'D'){
            direction = 'U';
          }
            break;
            case KeyEvent.VK_DOWN:
            if(direction != 'U'){
              direction = 'D';
            }
              break;  
      
      }
    }
    
  }}

