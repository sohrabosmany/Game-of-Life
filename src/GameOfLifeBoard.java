import javax.swing.*;
import java.awt.*;

public class GameOfLifeBoard extends JPanel {
    int xPanel = 1545;
    int yPanel = 830;
    int size = 10;
    boolean[][] life = new boolean[xPanel/size][yPanel/size];
    boolean start = true;

    public GameOfLifeBoard() {
        setSize(xPanel, yPanel);
        setLayout(null);

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
                for(int j = 0; j < (yPanel / size); j++) {
                    if((int)(Math.random() * 5) == 0) {
                        life[i][j] = true;
                    }
                }
            }
        }
        start = false;
    }

    private void display(Graphics g) {
        g.setColor(Color.ORANGE);

        for(int i = 0; i < life.length; i++) {
                for(int j = 0; j < yPanel/size; j++) {
                    if(life[i][j]){
                        g.fillRect(i * size, j * size, size, size);
                    }
                }
            }
    }
}
