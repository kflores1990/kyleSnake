package SnakeGamePKG;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Menu {

public static void setMenuItems(){
//window creations
    JFrame mainMenu = new JFrame();


//button instantiation
/*
    JButton loadProfile = new JButton("Profile");
*/
    JButton startGame = new JButton("Start");
    JButton exitGame = new JButton("Exit");

//button bounds
    startGame.setBounds(25, 300, 100, 40);
    exitGame.setBounds(150, 300, 100, 40);

//button listeners
    startGame.addActionListener(Menu::startGameAction);
    exitGame.addActionListener(Menu::exitGameAction);

//add buttons to menu
    mainMenu.add(startGame);
    mainMenu.add(exitGame);


//menu defaults
    mainMenu.setSize(300, 400);
    mainMenu.setLayout(null);
    mainMenu.setVisible(true);



}

//action listeners
public static void startGameAction(ActionEvent e){
    EventQueue.invokeLater(new Runnable() {

        @Override
        public void run() {
            JFrame ex = new Snake();
            ex.setVisible(true);
        }
    });

}

public static void exitGameAction(ActionEvent e){
    System.exit(0);
}
}
