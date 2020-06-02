package SnakeGamePKG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {

    private final int bWidth = 300;
    private final int bHeight = 300;
    private final int dSize = 10;
    private final int aDots = 900;
    private final int randPos =29;
    private final int delay = 140; //speed of game
    private int scoreCounter = 0;


    private final int x[] = new int[aDots];
    private final int y[] = new int[aDots];

    private int dots;
    private int apple_x;
    private int apple_y;


    private boolean lDir = false;
    private boolean rDir = true;
    private boolean uDir = false;
    private boolean dDir = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;

    public Board(){
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(bWidth, bHeight));
        loadImages();
        initGame();
    }
    private void loadImages(){
        ImageIcon lid = new ImageIcon(getClass().getResource("/res/sBody.png"));
        ball = lid.getImage();

        ImageIcon lia = new ImageIcon(getClass().getResource("/res/sApple.png"));
        apple = lia.getImage();

        ImageIcon lih = new ImageIcon(getClass().getResource("/res/sHead.png"));
        head = lih.getImage();
    }

    private void initGame(){
        dots = 3;
        for (int z=0; z < dots; z++){
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        locateApple();

        //we us a timer on a timer to call action performed method at fixed dela
        timer = new Timer(delay, this);
        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        doDrawing(g);
    }
    private void doDrawing(Graphics g){
        if (inGame){
            g.drawImage(apple, apple_x, apple_y, this);
            for (int z =0; z < dots; z++){
                if (z== 0){
                    g.drawImage(head, x[z], y[z], this);
                } else{
                    g.drawImage(ball, x[z], y[z], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        }else{
            gameOver(g);
        }

    }
    private void gameOver(Graphics g){
            String msg = "game over";
            Font small = new Font("Helvetica", Font.BOLD, 14);
            FontMetrics metr = getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (bWidth-metr.stringWidth(msg))/ 2, bHeight/2);
    }
    private void checkApple(){
            if((x[0] == apple_x) && (y[0] == apple_y)){
                dots++;
                locateApple();
        }
    }
    private void move(){
            for (int z = dots; z > 0; z--){
                x[z] = x[z-1];
                y[z] = y[z-1];
            }
            if (lDir){
                x[0] -= dSize;
            }
        if (rDir){
            x[0] += dSize;
        }
        if (uDir){
            y[0] -= dSize;
        }
        if (dDir){
            y[0] += dSize;
        }
    }
    private void checkCollision(){
            for (int z =dots; z > 0; z--){
                if((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                    inGame = false;

                }
            }
            if(y[0] >= bHeight){
                inGame = false;
            }
        if(y[0] < 0){
            inGame = false;
        }
        if(x[0] >=bWidth){
            inGame = false;
        }
        if(x[0] < 0){
            inGame = false;
        }
        if (!inGame){
            timer.stop();
        }
    }
    private void locateApple(){
            int r = (int) (Math.random() * randPos);
            apple_x = r * dSize;

            r = (int) (Math.random() * randPos);
            apple_y = r* dSize;
    }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(inGame){
                checkApple();
                checkCollision();
                move();
            }
            repaint();
        }
        private class TAdapter extends KeyAdapter{
            @Override
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT && !rDir){
                    lDir = true;
                    uDir = false;
                    dDir = false;
                }
                if (key == KeyEvent.VK_RIGHT && !lDir){
                    rDir = true;
                    uDir = false;
                    dDir = false;
                }
                if (key == KeyEvent.VK_UP && !dDir){
                    uDir = true;
                    rDir = false;
                    lDir = false;
                }
                if (key == KeyEvent.VK_DOWN && !uDir){
                    dDir = true;
                    rDir = false;
                    lDir = false;
                }
            }
    }



}
