import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.swing.Timer;

public class GameOfLifeBoard extends JPanel implements ActionListener {
    int xPanel = 1545;
    int yPanel = 830;
    int size = 10;
    int xWidth = xPanel / size;
    int yHeight = yPanel / size;

    int[][] life = new int[xWidth][yHeight];
    int[][] afterLife = new int[xWidth][yHeight];
    boolean start = true;

    public GameOfLifeBoard() {
        setSize(xPanel, yPanel);
        setLayout(null);
        // setBackground(Color.BLACK);
        new Timer(80, this).start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        grid(g);
        spawn(g);
        display(g);
    }

    private void grid(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        for(int i = 0; i < life.length; i++) {
            g.drawLine(0, i * size, xPanel, i * size);
            g.drawLine(i * size, 0, i * size, yPanel);
        }
    }
    private void spawn(Graphics g) {
        if(start) {
            for(int i = 0; i < life.length; i++) {
                for(int j = 0; j < (yHeight); j++) {
                    if((int)(Math.random() * 5) == 0) {
                        afterLife[i][j] = 1;
                    }
                }
            }
        }
        start = false;
    }

    private void display(Graphics g) {
        g.setColor(Color.ORANGE);
        copyDisplay();

        for(int i = 0; i < life.length; i++) {
            for(int j = 0; j < yHeight; j++) {
                if(life[i][j] == 1){
                    g.fillRect(i * size, j * size, size, size);
                }
            }
        }
    }

    private void copyDisplay() {
        for(int i = 0; i < life.length; i++) {
            for(int j = 0; j < yHeight; j++) {
                life[i][j] = afterLife[i][j];
            }
        }
    }
    

    private int checkLive(int x, int y) {
        int alive = 0;
        alive += life[(x + xWidth - 1) % xWidth][(y + yHeight - 1) % yHeight];
        alive += life[(x + xWidth) % xWidth][(y + yHeight - 1)  % yHeight];

        alive += life[(x + xWidth  + 1) % xWidth][(y + yHeight - 1)  % yHeight];
        alive += life[(x + xWidth - 1) % xWidth][(y + yHeight)  % yHeight];

        alive += life[(x + xWidth + 1) % xWidth][(y + yHeight) % yHeight];
        alive += life[(x + xWidth - 1) % xWidth][(y + yHeight + 1)  % yHeight];

        alive += life[(x + xWidth + 1) % xWidth][(y + yHeight + 1)  % yHeight];
        alive += life[(x + xWidth) % xWidth][(y + yHeight + 1)  % yHeight];

        
        return alive;
    }

    public void actionPerformed(ActionEvent e) {
        int alive;

        for(int i = 0; i < life.length; i++) {
            for(int j = 0; j < (yHeight); j++) {
                alive = checkLive(i, j);

                if(alive == 3) {
                    afterLife[i][j] = 1;
                }else if(alive == 2 && life[i][j] == 1) {
                    afterLife[i][j] = 1;
                }else {
                    afterLife[i][j] = 0;
                }
            }
        }
        repaint();
    }
}
