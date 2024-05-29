package Sokoban;

import javax.swing.*;
import java.awt.*;

public class BoardSokoban extends JPanel {

    private final int width = 1600;
    private final int height = 1600;

    private char[][] level;

    private int playerX, playerY;

    private boolean[][] targetsForBoxes;

    private char[][] currentLevel;
    private int currentLevelNumber;

    public BoardSokoban(char[][] level, int levelNumber) {
        currentLevelNumber = levelNumber;
        currentLevel = this.level;
        this.targetsForBoxes = new boolean[level.length][level[0].length];
    }

    private void initializeBoard() {
        setFocusable(true);
        setPreferredSize(new Dimension(width, height));
        initializeWorld();
        requestFocusInWindow();
    }



    public void initializeWorld() {
        for (int y = 0; y < level.length; y++) {
            for (int x = 0; x < level[y].length; x++) {
                char item = level[y][x];
                if (item == '@') {
                    playerX = x;
                    playerY = y;
                } else if (item == '.') {
                    targetsForBoxes[y][x] = true;
                }
            }
        }
    }



}