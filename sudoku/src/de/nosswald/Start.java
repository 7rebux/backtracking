package de.nosswald;

import de.nosswald.gui.Window;

/**
 * @author Nils Osswald
 */
public class Start {

    /**
     * entry point of the application
     *
     * @param args the supplied program arguments
     */
    public static void main(String[] args) {
        new Window(new Sudoku());
    }
}
