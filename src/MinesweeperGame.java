import javax.swing.JFrame;

public class MinesweeperGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameBoard board = new GameBoard(8, 8, 10);
        frame.add(board);

        frame.pack();
        frame.setVisible(true);
    }
}