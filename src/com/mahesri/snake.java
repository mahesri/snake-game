package mahesri;

import javax.swing.*;
import java.awt.*;

public class Snake extends JFrame {

Snake(){
    initUI();
}

void initUI(){

    this.add(new Board());
    setResizable(false);
    pack();
    setVisible(true);
    setLocationRelativeTo(null);
    setTitle("Snake");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

public static void main(String[]args){
    EventQueue.invokeLater(() -> {
        new Snake();
    });
}}
