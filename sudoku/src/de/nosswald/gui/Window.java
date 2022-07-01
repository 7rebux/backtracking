package de.nosswald.gui;

import javax.swing.*;

/**
 * @author Nils Osswald
 */
public class Window extends JFrame {
    public Window() {
        this.setTitle("Sudoku");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(new Content());

        this.setVisible(true);
    }
}
