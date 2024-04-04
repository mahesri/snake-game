package com.mahesri;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image; // Class dasar untuk merepresentasikan gambar. Berfokus pada manipulasi gambar seperti resize, rotate, dan transformasi lainnya.
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter; // Import class yang menangani event pencetan keyboard
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon; // Class untuk mengintegrasikan file foto kedalam sebuah GUI 
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
    
    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    private final int x[] = new int[ALL_DOTS]; // Akan menampung semua koordinat x bagian tubuh termasuk kepala ular.
    private final int y[] = new int[ALL_DOTS]; // Akan menampung semua koordinat y bagian tubuh termasuk kepala ular.

    private int dots;
    private int apple_x; // Akan menjadi x-cordinat saat apel muncul ke papan secara acak.
    private int apple_y; // Posisi munculnya apel di kordinat Y.


    private boolean leftDirection = false;
    private boolean rightDirection = false;
    private boolean upDirection = false;
    private boolean dwonDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;


        public Board(){ // Saat class Board dipanggil otomatis akan mengeksekusi konstruktor ini.  
            initBoard();
        }

        private void initBoard(){

            addKeyListener(new TAdapter()); // Memanggil metode yang mengelola event yang terjadi dalam keyboard
            setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT)); // Mengatur tinggi dan lebar sesuai dengan dimensi yang sudah diatur sebelumnya dalam didalam B_WIDTH dan B_HEIGHT
            setBackground(Color.BLACK); // Mengatur warna background dalam papan
            setFocusable(true); // Mengatur agar papan dapat menerima fokus dari keyboard. Dengan menambahkan method ini papan akan merespon alur keyboard secara langsung.
            loadImage(); // Memanggil gambar untuk ditampilkan didalam papan
            initGame(); // Menjalankan Game
        }

        private void loadImage(){

            ImageIcon iid = new ImageIcon("src/resources/dot.png"); // memuat gambar 
            ball = iid.getImage(); // memberikan Image kepada objek 'iid' dan di representasikan kepada objek 'ball'
    
            ImageIcon iia = new ImageIcon("src/resources/apple.png");
            apple = iia.getImage(); 
           
            ImageIcon iih = new ImageIcon("src/resources/head.png");
            head = iih.getImage();
            }
    
            private void initGame(){

                dots = 3;
                for(int z = 0; z < dots;z++){

                    x[z] = 50 - z * 10; // Menetapkan nilai array-x pada index z |
                    y[z] = 50;
                }

                locateApple();

                timer = new Timer(DELAY, this);
                timer.start();
            }

            private void locateApple(){ // Method untuk meletakan apel secara random didalam papan 
               
               int r = (int) (Math.random() * RAND_POS);
               apple_x = ((r * DOT_SIZE));
               
               r = (int) (Math.random() * RAND_POS);
               apple_y = ((r * DOT_SIZE));

            }

            @Override
            public void paintComponent(Graphics g){

                super.paintComponent(g);
                doDrawing(g);

            }

            private void doDrawing(Graphics g){
               
                if(inGame){
                    g.drawImage(apple, apple_x, apple_y, this);

                    for(int z = 0; z < dots;z++){
                        if(z==0){
                            g.drawImage(head, x[z], y[z], this);
                        }else{
                            g.drawImage(ball, x[z], y[z], this);
                        }
                    }

                    Toolkit.getDefaultToolkit().sync();

                }else{
                    gameOver(g);
                }
            }

            private void gameOver(Graphics g){

                String msg = "GAME OVER!";
                Font small = new Font("HELVETICA", Font.BOLD, 14);
                FontMetrics metr = getFontMetrics(small);

                g.setColor(Color.RED);
                g.setFont(small);
                g.drawString(msg, B_WIDTH - metr.stringWidth(msg) / 2, B_HEIGHT /2);
            }

            private void checkApple(){ // method ketika apel bertabrakan dengan kepala ular
                
                if((x[0] == apple_x) && (y[0] == apple_y)){ // Ketika kepala ular dan posisi apel berada diposisi yang sama // secara langsung bertabrakan
                dots++;
                locateApple(); // memanggil method yang mengacak posisi array baru 
                }
            }

            private void move(){

                for(int z = dots;z > 0; z--){
                    x[z] = x[(z - 1)];
                    y[z] = y[(z - 1)];
                }

                if(leftDirection){
                    x[0] -= DOT_SIZE;
                }

                if (rightDirection) {
                    x[0] += DOT_SIZE;
                }

                if(upDirection){
                    y[0] -= DOT_SIZE;
                }

                if(dwonDirection){
                    y[0] += DOT_SIZE;
                }

            }

            private void checkCollision() {

                for (int z = dots; z > 0; z--) {
        
                    if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                        inGame = false;
                    }
                }
        
                if (y[0] >= B_HEIGHT) {
                    inGame = false;
                }
        
                if (y[0] < 0) {
                    inGame = false;
                }
        
                if (x[0] >= B_WIDTH) {
                    inGame = false;
                }
        
                if (x[0] < 0) {
                    inGame = false;
                }
                
                if (!inGame) {
                    timer.stop();
                }
            }

            @Override
            public void actionPerformed(ActionEvent e){

                if(inGame) {
                    checkApple();
                    checkCollision();
                    move();

                }
             repaint();
            }


        // ============= Class yang mengatur arah pergerakan ular ============= //

    private class TAdapter extends KeyAdapter{ // Class yang menangani control keyboard terhadap permainan | KeyAdapter mewarisi class keyListener sehingga dapat merecord event yang terjadi didalam keyboard

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                dwonDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                dwonDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!dwonDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                dwonDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }

    }


}
