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
        KeysInputs adapter = new KeysInputs(this);
        addKeyListener(adapter);
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawWorld(g);
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


    public void movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;

        if (level[newY][newX] != '#') {
            if (level[newY][newX] == '$') {
                int nextX = newX + dx;
                int nextY = newY + dy;

                if (level[nextY][nextX] == ' ' || level[nextY][nextX] == '.') {
                    level[newY][newX] = ' ';
                    level[nextY][nextX] = '$';
                } else {
                    return;
                }
            }

            if (targetsForBoxes[playerY][playerX]) {
                level[playerY][playerX] = '.';
            } else {
                level[playerY][playerX] = ' ';
            }

            if (level[newY][newX] != '$') {
                level[newY][newX] = '@';
            }

            playerX = newX;
            playerY = newY;
        }
    }


}