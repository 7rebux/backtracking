package de.nosswald.gui;

import de.nosswald.Solver;
import de.nosswald.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Nils Osswald
 */
public class SudokuPanel extends JPanel {

    private Sudoku sudoku;
    private Solver solver;

    private int selectedRow, selectedCol = -1;

    public SudokuPanel(Sudoku sudoku) {
        this.sudoku = sudoku;
        this.solver = new Solver(this);
        solver.setDelay(20);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    selectedCol = -1;
                    selectedRow = -1;
                } else {
                    selectedCol = e.getX() / 50;
                    selectedRow = e.getY() / 50;
                }

                repaint();
                requestFocus();
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (selectedRow != -1) {
                    if (Character.isDigit(e.getKeyChar()))
                        sudoku.getField()[selectedRow][selectedCol] = Character.digit(e.getKeyChar(), 10);
                    else
                        sudoku.getField()[selectedRow][selectedCol] = 0;

                    repaint();
                }
            }
        });
    }

    public boolean trySolve() {
        if (!sudoku.isDone())
            return solver.solve();
        return false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // draw grid
        for (int pos = 50; pos < 450; pos += 50) {
            // set color
            g.setColor(Color.BLACK);
            if (pos / 50 % 3 == 0)
                g.setColor(Color.RED);

            g.drawLine(pos, 0, pos, 450);
            g.drawLine(0, pos, 450, pos);
        }

        // draw numbers
        for (int row = 0; row < 9; ++row) {
            for (int column = 0; column < 9; ++column) {
                int number = sudoku.getField()[row][column];

                if (selectedRow == row && selectedCol == column) {
                    g.setColor(new Color(255, 0, 0, 40));
                    g.fillRect(column * 50, row * 50, 50, 50);
                }

                if (number == 0)
                    continue;

                g.setColor(Color.BLACK);
                g.setFont(new Font("Verdana", Font.PLAIN, 32));
                g.drawString(String.valueOf(number), column * 50 + 15, (row + 1) * 50 - 10);
            }
        }
    }

    public Sudoku getSudoku() {
        return sudoku;
    }

    public Solver getSolver() {
        return solver;
    }
}
