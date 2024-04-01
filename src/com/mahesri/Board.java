package com.mahesri;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter; // Import class yang menangani event pencetan keyboard

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel  {
    
    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

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
            setFocusable(true); // Mengatur agar papan dapat menerima fokus dari keyboard. Dengan menambahkan method ini papan akan merespon aler keyboard secara langsung.
            loadImage(); // Memanggil gambar untuk ditampilkan didalam papan
            initGame(); // Menjalankan Game
        }

        private void loadImage(){

            ImageIcon iid = new ImageIcon("src/resources/dot.png");
            ball = iid.getImage(); 
    
            ImageIcon iia = new ImageIcon("src/resources/apple.png");
            apple = iia.getImage(); 
           
            ImageIcon iiy = new ImageIcon("src/resources/apple.png");
            head = iiy.getImage();
            }
    
            private void initGame(){

                dots = 3;
                for(int z = 0; z < dots;z++){
                    x[z] = 50 -z * 10;
                    y[z] = 50;
                }

                locateApple();

                timer = new Timer(DELAY, this);
                timer.start();
            }

            private void locateApple(){
                
            }


        // ============= Class yang mengatur jalannya permainan =============

    private class TAdapter extends KeyAdapter{ // Class yang menangani control keyboard terhadap permainan | KeyAdapter mewarisi class keyListener sehingga dapat merecord event yang terjadi didalam keyboard

    }


}
