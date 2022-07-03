package de.nosswald.gui;

import de.nosswald.Sudoku;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * @author Nils Osswald
 */
public class Window extends JFrame {

    public Window(Sudoku sudoku) {
        this.setTitle("Sudoku");
        this.setSize(630, 510);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);

        SudokuPanel content = new SudokuPanel(sudoku);
        content.setBounds(10, 10, 450, 450);
        content.setBorder(new LineBorder(Color.BLACK, 3));

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
        delayDropDown.setSelectedItem(content.getSolver().getDelay());
        delayDropDown.addActionListener(e -> content.getSolver().setDelay((int) delayDropDown.getSelectedItem()));

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> { sudoku.clear(); content.repaint(); });

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(e -> new Thread(() -> {
            solveButton.setEnabled(false);
            solveButton.setText("Solving...");
            clearButton.setEnabled(false);

            content.getSolver().reset();

            long start = System.currentTimeMillis();

            if (!content.trySolve()) {
                JOptionPane.showMessageDialog(this,
                        "Already solved or impossible.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

                solveButton.setEnabled(true);
                solveButton.setText("Solve");
                clearButton.setEnabled(true);

                return;
            }

            timeLabel.setText("Last time: " + (System.currentTimeMillis() - start) + "ms");
            stepsLabel.setText("Last steps: " + content.getSolver().getSteps());

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
        this.add(content);

        this.setVisible(true);
    }
}
