import javax.swing.JFrame;

public class GameOfLifeBody extends JFrame{

    public GameOfLifeBody() {
        add(new GameOfLifeBoard());
        
        setSize(1545, 830);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main(String[] args) {
        new GameOfLifeBody();
    }
}