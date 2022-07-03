package de.nosswald;

import de.nosswald.gui.SudokuPanel;

/**
 * @author Nils Osswald
 */
public class Solver {

    private SudokuPanel panel;
    private Sudoku sudoku;

    private int steps, delay = 0;

    /**
     * @param panel the parent JPanel
     */
    public Solver(SudokuPanel panel) {
        this.panel = panel;
        this.sudoku = panel.getSudoku();
    }

    /**
     * Solves the sudoku with backtracking
     *
     * @return if the solution was possible or not
     */
    public boolean solve() {
        // wait delay
        try { Thread.sleep(delay); } catch (InterruptedException ignored) { }

        // paint changes
        panel.repaint();

        // increment steps
        steps++;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (sudoku.getField()[row][col] == 0) {
                    for (int number = 1; number <= 9; number++) {
                        if (sudoku.isValid(row, col, number)) {
                            sudoku.getField()[row][col] = number;

                            if (solve())
                                return true;

                            sudoku.getField()[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * resets the statistics
     */
    public void reset() {
        steps = 0;
    }

    /**
     * sets the delay between each recursive method call
     *
     * @param delay the delay measured in milliseconds
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * @return the delay between each recursive method call
     */
    public int getDelay() {
        return delay;
    }

    /**
     * @return how many times the solve method was called
     */
    public int getSteps() {
        return steps;
    }
}
