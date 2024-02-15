// Cell.java
import javax.swing.JButton;

public class Cell extends JButton {
    private int row;
    private int col;
    private boolean isMine;
    private boolean isRevealed;
    private int neighborMineCount;
    private boolean isFlagged;

    // Constructor, getters, setters...

    public Cell(int row, int col, boolean isMine, boolean isRevealed, boolean isFlagged, int neighborMineCount) {
        this.row = row;
        this.col = col;
        this.isMine = isMine;
        this.isRevealed = isRevealed;
        this.isFlagged = isFlagged;
        this.neighborMineCount = neighborMineCount;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public int getNeighborMineCount() {
        return neighborMineCount;
    }

    public void setNeighborMineCount(int neighborMineCount) {
        this.neighborMineCount = neighborMineCount;
    }
}