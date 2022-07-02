package de.nosswald.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Content extends JPanel {

    private final int[][] grid;

    public Content() {
        this.grid = new int[][] {
                { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
                { 0, 7, 0, 0, 0, 0, 2, 0, 0 },
                { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
                { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
                { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
                { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
        };

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("dwad");
                new Thread(() -> {
                    long start = System.currentTimeMillis();
                    solve();
                    System.out.println(System.currentTimeMillis() - start);
                    System.out.println(steps);
                }).start();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // draw grid
        for (int pos = 50; pos < 450; pos += 50)
        {
            // set color
            g.setColor(Color.BLACK);
            if (pos / 50 % 3 == 0)
                g.setColor(Color.RED);

            g.drawLine(pos, 0, pos, 450);
            g.drawLine(0, pos, 450, pos);
        }

        // draw numbers
        for (int row = 0; row < 9; ++row)
        {
            for (int column = 0; column < 9; ++column)
            {
                int number = grid[row][column];

                if (number == 0)
                    continue;

                g.setFont(new Font("Verdana", Font.PLAIN, 32));
                g.drawString(String.valueOf(number), column * 50 + 15, (row + 1) * 50 - 10);
            }
        }
    }

    private boolean isInRow(int row, int number)
    {
        for (int i = 0; i < 9; ++i)
        {
            if (grid[row][i] == number)
                return true;
        }
        return false;
    }

    private boolean isInColumn(int column, int number)
    {
        for (int i = 0; i < 9; ++i)
        {
            if (grid[i][column] == number)
                return true;
        }
        return false;
    }

    private boolean isInBox(int row, int column, int number)
    {
        row = row - row % 3;
        column = column - column % 3;

        for (int r = row; r < row + 3; ++r)
        {
            for (int c = column; c < column + 3; ++c)
            {
                if (grid[r][c] == number)
                    return true;
            }
        }
        return false;
    }

    private boolean isValid(int row, int column, int number)
    {
        return !isInRow(row, number) && !isInColumn(column, number) && !isInBox(row, column, number);
    }

    private int steps = 0;

    public boolean solve()
    {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        steps++;
        repaint();

        for (int row = 0; row < 9; ++row)
        {
            for (int column = 0; column < 9; ++column)
            {
                // check if slot is empty
                if (grid[row][column] == 0)
                {
                    for (int number = 1; number <= 9; ++number)
                    {
                        // check if number is valid
                        if (isValid(row, column, number))
                        {
                            grid[row][column] = number;

                            if (solve())
                                return true;
                            else
                                grid[row][column] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
