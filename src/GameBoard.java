import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GameBoard extends JPanel implements MouseListener {
    private int rows;
    private int cols;
    private int numMines;
    private Cell[][] cells;
    private boolean gameOver;

    public GameBoard(int rows, int cols, int numMines) {
        this.rows = rows;
        this.cols = cols;
        this.numMines = numMines;
        initializeBoard();
        addMouseListener(this);
    }

    private void initializeBoard() {
        setLayout(new GridLayout(rows, cols));
        cells = new Cell[rows][cols];
        gameOver = false;

        // Create Cell objects
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col] = new Cell(row, col, false, false, false, 0);
                add(cells[row][col]);
            }
        }

        // Place mines randomly
        placeMines();

        // Calculate neighbor mine counts
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col].setNeighborMineCount(countNeighborMines(row, col));
            }
        }
    }

    private void placeMines() {
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < numMines) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);
            if (!cells[row][col].isMine()) {
                cells[row][col].setMine(true);
                minesPlaced++;
            }
        }
    }

    private int countNeighborMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < cols && cells[i][j].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    private void revealCell(int row, int col) {
        if (gameOver || cells[row][col].isRevealed() || cells[row][col].isFlagged()) {
            return;
        }

        cells[row][col].setRevealed(true);

        if (cells[row][col].isMine()) {
            gameOver = true;
            revealAllMines();
            // Handle game over (e.g., show a message)
            JOptionPane.showMessageDialog(this, "Game Over!");
        } else if (cells[row][col].getNeighborMineCount() == 0) {
            // Recursively reveal neighbors
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    if (i >= 0 && i < rows && j >= 0 && j < cols) {
                        revealCell(i, j);
                    }
                }
            }
        }

        // Check for win condition
        if (isGameWon()) {
            gameOver = true;
            // Handle win (e.g., show a message)
            JOptionPane.showMessageDialog(this, "You Win!");
        }
    }

    private void revealAllMines() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (cells[row][col].isMine()) {
                    cells[row][col].setRevealed(true);
                }
            }
        }
    }

//    private boolean isGameWon() {
//        for (int row = 0; row < rows; row++) {
//            for (int col = 0; col < cols; col++) {
//                if (!cells[row][col].isMine() && !cells[row][col].isRevealed()) {
//                    return false; // Still unrevealed non-mine cells
//                }
//            }
//        }
//        return true; // All non-mine cells have been revealed
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        if (gameOver) {
//            return;
//        }
//
//        for (int row = 0; row < rows; row++) {
//            for (int col = 0; col < cols; col++) {
//                if (e.getSource() == cells[row][col]) {
//                    if (SwingUtilities.isLeftMouseButton(e)) {
//                        revealCell(row, col);
//                    } else if (SwingUtilities.isRightMouseButton(e)) {
//                        cells[row][col].setFlagged(!cells[row][col].isFlagged());
//                    }
//                    repaint(); // Update the display after any cell interaction
//                }
//            }
//        }
//    }


//    private void toggleFlag(int row, int col) {
//        if (!cells[row][col].isRevealed()) {
//            cells[row][col].setFlagged(!cells[row][col].isFlagged());
//            cells[row][col].setText(cells[row][col].isFlagged() ? "F" : "");
//
//            // You'll likely want to modify how flags are displayed visually
//            // e.g., cells[row][col].setText(cells[row][col].isFlagged() ? "F" : "");
//        }
//    }

    private boolean isGameWon() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!cells[row][col].isMine() && !cells[row][col].isRevealed()) {
                    return false; // Still unrevealed cells that are not mines
                }
            }
        }
        return true; // All non-mine cells are revealed
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gameOver) {
            return; // Ignore clicks if the game is over
        }

        Cell cell = (Cell) e.getSource();

        if (SwingUtilities.isLeftMouseButton(e)) {
            revealCell(cell.getRow(), cell.getCol());
        } else if (SwingUtilities.isRightMouseButton(e)) {
            toggleFlag(cell.getRow(), cell.getCol());
        }

        repaint(); // Update the board visually
    }

    private void toggleFlag(int row, int col) {
        if (!cells[row][col].isRevealed()) {
            cells[row][col].setFlagged(!cells[row][col].isFlagged());
            cells[row][col].setText(cells[row][col].isFlagged() ? "F" : "");

            // You'll likely want to modify how flags are displayed visually
            // e.g., cells[row][col].setText(cells[row][col].isFlagged() ? "F" : "");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}