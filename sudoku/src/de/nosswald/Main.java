package de.nosswald;

import de.nosswald.gui.Window;

/**
 * @author Nils Osswald
 */
public class Main {

    private final Main instance;

    private Window window;
    private Sudoku sudoku;

    public Main() {
        instance = this;
    }

    public void run() {
        sudoku = new Sudoku();
        window = new Window();
    }

    public Sudoku getSudoku() {
        return sudoku;
    }

    public Window getWindow() {
        return window;
    }

    public Main getInstance() {
        return instance;
    }
}
