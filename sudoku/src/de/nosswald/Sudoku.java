package de.nosswald;

/**
 * @author Nils Osswald
 */
public class Sudoku {

    private int[][] field;

    /**
     * initializes the field with the "hardest sudoku":
     * <a href="https://www.conceptispuzzles.com/de/index.aspx?uri=info/article/424">https://www.conceptispuzzles.com/de/index.aspx?uri=info/article/424</a>
     */
    public Sudoku() {
        field = new int[][] {
                { 8, 0, 0,  0, 0, 0,  0, 0, 0 },
                { 0, 0, 3,  6, 0, 0,  0, 0, 0 },
                { 0, 7, 0,  0, 9, 0,  2, 0, 0 },

                { 0, 5, 0,  0, 0, 7,  0, 0, 0 },
                { 0, 0, 0,  0, 4, 5,  7, 0, 0 },
                { 0, 0, 0,  1, 0, 0,  0, 3, 0 },

                { 0, 0, 1,  0, 0, 0,  0, 6, 8 },
                { 0, 0, 8,  5, 0, 0,  0, 1, 0 },
                { 0, 9, 0,  0, 0, 0,  4, 0, 0 }
        };
    }

    /**
     * clears the entire field
     */
    public void clear() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++)
                field[row][col] = 0;
        }
    }

    /**
     * @return if the field is completely filled up
     */
    public boolean isDone() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (field[row][col] == 0)
                    return false;
            }
        }
        return true;
    }

    /**
     * @param row       the row
     * @param number    number from 1 to 9
     *
     * @return if the given number is present in the given row
     */
    public boolean isInRow(int row, int number) {
        for (int i = 0; i < 9; i++) {
            if (field[row][i] == number)
                return true;
        }
        return false;
    }

    /**
     * @param col       the col
     * @param number    number from 1 to 9
     *
     * @return if the given number is present in the given column
     */
    public boolean isInCol(int col, int number) {
        for (int i = 0; i < 9; i++) {
            if (field[i][col] == number)
                return true;
        }
        return false;
    }

    /**
     * @param row       the row
     * @param col       the col
     * @param number    number from 1 to 9
     *
     * @return if the given number is present in the given 3x3 box
     */
    public boolean isInBox(int row, int col, int number) {
        // calc box
        row = row - row % 3;
        col = col - col % 3;

        for (int r = row; r < row + 3; r++) {
            for (int c = col; c < col + 3; c++) {
                if (field[r][c] == number)
                    return true;
            }
        }
        return false;
    }

    /**
     * @param row       the row
     * @param col       the col
     * @param number    number from 1 to 9
     *
     * @return if the given number is not present in either the given row, col or box
     */
    public boolean isValid(int row, int col, int number) {
        return !isInRow(row, number) && !isInCol(col, number) && !isInBox(row, col, number);
    }

    /**
     * @return the field (empty slots are marked as zero)
     */
    public int[][] getField() {
        return field;
    }
}
