package de.nosswald.gui;

import de.nosswald.Sudoku;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * @author Nils Osswald
 */
public class Window extends JFrame {

    /**
     * @param sudoku the sudoku to render
     */
    public Window(Sudoku sudoku) {
        this.setTitle("Sudoku");
        this.setSize(630, 510);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // set layout to null, so we can provide custom coordinates for each element
        this.setLayout(null);

        SudokuPanel sudokuPanel = new SudokuPanel(sudoku);
        sudokuPanel.setBounds(10, 10, 450, 450);
        sudokuPanel.setBorder(new LineBorder(Color.BLACK, 3));

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new FlowLayout());

        JLabel timeLabel = new JLabel("Last time: None");
        JLabel stepsLabel = new JLabel("Last steps: None");

        JComboBox<Integer> delayDropDown = new JComboBox<>();
        delayDropDown.addItem(0);
        delayDropDown.addItem(10);
        delayDropDown.addItem(20);
        delayDropDown.addItem(50);
        delayDropDown.addItem(100);
        delayDropDown.addItem(200);
        delayDropDown.addItem(500);
        delayDropDown.addItem(1000);
        delayDropDown.setSelectedItem(sudokuPanel.getSolver().getDelay());
        delayDropDown.addActionListener(e -> sudokuPanel.getSolver().setDelay((int) delayDropDown.getSelectedItem()));

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> { sudoku.clear(); sudokuPanel.repaint(); });

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(e -> new Thread(() -> {
            // run this in a separate thread otherwise the window would only update if the method call is done
            solveButton.setEnabled(false);
            solveButton.setText("Solving...");
            clearButton.setEnabled(false);

            // reset old statistics
            sudokuPanel.getSolver().reset();

            long start = System.currentTimeMillis();

            if (!sudokuPanel.trySolve()) {
                JOptionPane.showMessageDialog(this,
                        "Already solved or impossible.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                timeLabel.setText("Last time: " + (System.currentTimeMillis() - start) + "ms");
                stepsLabel.setText("Last steps: " + sudokuPanel.getSolver().getSteps());
            }

            solveButton.setEnabled(true);
            solveButton.setText("Solve");
            clearButton.setEnabled(true);
        }).start());

        JPanel delayPanel = new JPanel();
        delayPanel.add(new JLabel("Delay: "));
        delayPanel.add(delayDropDown);
        delayPanel.setBackground(Color.LIGHT_GRAY);

        sidebar.add(delayPanel);
        sidebar.add(solveButton);
        sidebar.add(clearButton);
        sidebar.add(timeLabel);
        sidebar.add(stepsLabel);
        sidebar.setBackground(Color.LIGHT_GRAY);
        sidebar.setBounds(470, 10, 130, 450);

        this.add(sidebar);
        this.add(sudokuPanel);

        this.setVisible(true);
    }
}
