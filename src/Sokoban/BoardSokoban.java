package Sokoban;

import javax.swing.*;
import java.awt.*;

public class BoardSokoban extends JPanel {

    private final int offset = 100;
    private final int space = 80;
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
        initializeBoard();
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

    private void drawWorld(Graphics g) {
        ImageIcon imageIcon1 = new ImageIcon("sources/woodenbox.png");
        ImageIcon imageIcon2 = new ImageIcon("sources/wall.png");
        ImageIcon imageIcon3 = new ImageIcon("sources/grass.png");
        ImageIcon imageIcon4 = new ImageIcon("sources/placespot.png");
        Image image1 = imageIcon1.getImage();
        Image image2 = imageIcon2.getImage();
        Image image3 = imageIcon3.getImage();
        Image image4 = imageIcon4.getImage();

        for (int y = 0; y < level.length; y++) {
            for (int x = 0; x < level[y].length; x++) {
                char character = level[y][x];
                boolean isTargetsForBoxes = targetsForBoxes[y][x];

                g.drawImage(image3, x * space + offset, y * space + offset, space, space, null);

                if (isTargetsForBoxes) {
                    g.drawImage(image4, x * space + offset, y * space + offset, space, space, null);
                }

                if (character == '#') {
                    g.drawImage(image2, x * space + offset, y * space + offset, space, space, null);
                } else if (character == '$') {
                    g.drawImage(image1, x * space + offset, y * space + offset, space, space, null);
                } else if (character == '@') {
                    g.setColor(new Color(0x017C83));
                    g.fillOval(x * space + offset, y * space + offset, space, space);
                }
            }
        }
    }

}