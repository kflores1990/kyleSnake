package SnakeGamePKG;

import javax.swing.*;
import java.awt.*;

public class Snake  extends JFrame {

    public Snake(){
        add(new Board());
        setResizable(false);
        pack();

        setTitle("Ky's Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        Menu testingMenu = new Menu();
        testingMenu.setMenuItems();

        /*EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame ex = new Snake();
                ex.setVisible(true);
            }
        });*/
    }
}

/*
to-do
wall fixed ---fix left side of board - snake is able to get out and has to be directed back in
improve graphics - install photoshop
upload to git hub

 */