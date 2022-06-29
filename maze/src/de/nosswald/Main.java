package de.nosswald;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Nils Osswald
 */
public class Main {

    private static int[][] maze;
    private static int width, height;
    private static int[][] solution;

    public static void main(String[] args) throws IOException {
        maze = new int[][]{
                { 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 1, 1, 1, 1, 0, 0, 0 },
                { 0, 0, 1, 0, 1, 0, 1, 0, 0, 0 },
                { 1, 1, 1, 0, 1, 0, 1, 1, 1, 0 },
                { 0, 0, 1, 0, 1, 0, 0, 0, 1, 0 },
                { 0, 0, 1, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 1, 1, 0, 0, 0, 0 },
                { 0, 0, 1, 1, 0, 1, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 1, 1, 1, 1, 0, 1, 1, 1 }
        };

        width = maze[0].length;
        height = maze.length;
        solution = new int[height][width];

        System.out.printf("Given dimension: %dx%d%n", width, height);

        if (solve(0, 0))
            System.out.println("Solved");
        else {
            System.out.println("Impossible");
            return;
        }

        save(maze, "input");
        save(solution, "output");
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height
                && maze[y][x] == 1;
    }

    private static boolean solve(int x, int y) {
        if (x == width - 1 && y == height - 1) { // check if finish is reached
            solution[y][x] = 1;
            return true;
        }

        if (isValid(x, y)) {
            solution[y][x] = 1;

            if (solve(x + 1, y) || solve(x, y + 1))
                return true;
            else
                solution[y][x] = 0;
        }

        return false;
    }

    private static void save(int[][] input, String name) throws IOException {
        final BufferedImage image = new BufferedImage(width, height, 1);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = input[y][x] == 1 ? Color.WHITE : Color.BLACK;
                image.setRGB(x, y, color.getRGB());
            }
        }

        File out = new File(name + ".jpg");
        ImageIO.write(image, "jpg", out);
        System.out.println("Wrote " + out.getName());
    }
}
