package de.nosswald;

/**
 * @author Nils Osswald
 */
public class Sudoku {

    private int[][] field;

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

    public boolean isDone() {
        for (int row = 0; row < 9; ++row) {
            for (int column = 0; column < 9; ++column) {
                if (field[row][column] == 0)
                    return false;
            }
        }
        return true;
    }

    public boolean isInRow(int row, int number) {
        for (int i = 0; i < 9; i++) {
            if (field[row][i] == number)
                return true;
        }
        return false;
    }

    public boolean isInColumn(int column, int number) {
        for (int i = 0; i < 9; i++) {
            if (field[i][column] == number)
                return true;
        }
        return false;
    }

    public boolean isInBox(int row, int column, int number) {
        row = row - row % 3;
        column = column - column % 3;

        for (int r = row; r < row + 3; ++r) {
            for (int c = column; c < column + 3; c++) {
                if (field[r][c] == number)
                    return true;
            }
        }
        return false;
    }

    public boolean isValid(int row, int column, int number) {
        return !isInRow(row, number) && !isInColumn(column, number) && !isInBox(row, column, number);
    }

    public int[][] getField() {
        return field;
    }

    public void clear() {
        for (int row = 0; row < 9; ++row) {
            for (int column = 0; column < 9; ++column)
                field[row][column] = 0;
        }
    }
}
