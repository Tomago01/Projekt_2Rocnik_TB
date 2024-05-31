package Sokoban;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BoardSokoban extends JPanel {

    private final int offset = 100;
    private final int space = 80;
    private final int width = 1600;
    private final int height = 1600;

    public char[][] level;

    public int playerX;
    public int playerY;

    private boolean[][] targetsForBoxes;

    private char[][] currentLevel;
    private int currentLevelNumber;

    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage image3;
    private BufferedImage image4;

    public BoardSokoban(char[][] level, int levelNumber) {
        currentLevelNumber = levelNumber;
        this.level = Levels.getLevel(currentLevelNumber);
        currentLevel = this.level;
        this.targetsForBoxes = new boolean[level.length][level[0].length];
        loadImages();
        initializeBoard();
    }

    private void loadImages() {
        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/sources/woodenbox.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/sources/wall.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/sources/grass.png"));
            image4 = ImageIO.read(getClass().getResourceAsStream("/sources/placespot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawWorld(g);
    }

    private void drawWorld(Graphics g) {
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

    public boolean checkAllBoxesOnPlace() {
        for (int y = 0; y < level.length; y++) {
            for (int x = 0; x < level[y].length; x++) {
                if (level[y][x] == '$' && !targetsForBoxes[y][x]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void restartLevel() {
        level = Levels.getLevel(currentLevelNumber);
        initializeWorld();
        requestFocusInWindow();
        repaint();
    }
}
