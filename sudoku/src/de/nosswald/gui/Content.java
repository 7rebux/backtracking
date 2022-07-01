package de.nosswald.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Content extends JPanel {
    @Override
    public void paint(Graphics g) {
        new Thread(() -> {
            Color color = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
            g.setColor(color);
            g.fillRect(0, 0, getWidth(), getHeight());

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).run();

        repaint();
    }
}
