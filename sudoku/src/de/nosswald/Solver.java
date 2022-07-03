package de.nosswald;

import de.nosswald.gui.SudokuPanel;

/**
 * @author Nils Osswald
 */
public class Solver {

    private SudokuPanel content;
    private Sudoku sudoku;

    private int steps, delay = 0;

    public Solver(SudokuPanel content) {
        this.content = content;
        this.sudoku = content.getSudoku();
    }

    public boolean solve() {
        try { Thread.sleep(delay); } catch (InterruptedException ignored) { }
        content.repaint();

        steps++;

        for (int row = 0; row < 9; ++row) {
            for (int column = 0; column < 9; ++column) {
                if (sudoku.getField()[row][column] == 0) {
                    for (int number = 1; number <= 9; ++number) {
                        if (sudoku.isValid(row, column, number)) {
                            sudoku.getField()[row][column] = number;

                            if (solve())
                                return true;

                            sudoku.getField()[row][column] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void reset() {
        steps = 0;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }

    public int getSteps() {
        return steps;
    }
}
