package com.mahesri;

import java.awt.EventQueue; // Menyimpan antrian event dari listener. 


import javax.swing.JFrame; // mengimpor class JFrame dari package javax.swing;
// javax.swing: Ini merujuk ke package di dalam library Java yang khusus dirancang untuk membuat antarmuka pengguna grafis (GUI) menggunakan framework Swing.
// JFrame adalah adalah pondasi untuk membuat jendela dasar dalam aplikasi Swing. Ini mewakili frame atau jendela yang akan digunakan sebagai board game.

public class Snake extends JFrame { // Keyword 'extends' dimaksudkan untuk class 'Snake' memiliki kemampuan seperti class 'JFrame' seperti menampilkan jendela dan semua fitur yang ada di class 'JFrame'

    public Snake(){ // Konstruktor class Snake | Saat program dijalankan konstrukor otomatis akan dieksekusi.
     
        initUI(); // Setiap konstruktor dipanggil maka akan menciptakan objek baru yakni 'initUI()'
    }

     private void initUI(){
       
        add(new Board()); // Mebuka Board/papan permainan/jendela permainan ketika program dijalankan
        
        setResizable(false); // Membuat agar papan tidak dapat diubah ukurannya.
        pack(); // Method untuk mengatur agar papan otomatis ditampilkan sesuai komponennya.

        setTitle("Snake"); // Mengatur judul papan menjadi 'Snake'
        setLocationRelativeTo(null); // Mengatur agar papan saat ditampilkan berada di tenggah layar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public static void main(String[] args) {
    
    EventQueue.invokeLater(  () -> { // Lamda exspresion
        JFrame ex = new Snake(); // Membuat instance baru untuk permainan 
        ex.setVisible(true); // Menampilkan papan 
    });

    }
}
