package Sokoban;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class represents game board of the Sokoban puzzle.
 */
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

    /**
     * Constructor of a Sokoban game board with the specified level and level number.
     *
     * @param level one game board level that are seen in class Levels so two-dimensional set of characters.
     * @param levelNumber number of the current level.
     */
    public BoardSokoban(char[][] level, int levelNumber) {
        currentLevelNumber = levelNumber;
        this.level = Levels.getLevel(currentLevelNumber);
        currentLevel = this.level;
        this.targetsForBoxes = new boolean[level.length][level[0].length];
        loadImages();
        initializeBoard();
    }

    /**
     * Loads textures that are used in the game board.
     */
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

    /**
     * Initializes the game board.
     */
    private void initializeBoard() {
        setFocusable(true);
        setPreferredSize(new Dimension(width, height));
        KeysInputs adapter = new KeysInputs(this);
        addKeyListener(adapter);
        initializeWorld();
        requestFocusInWindow();
    }

    /**
     * Initializes the game world that is different for individual levels.
     */
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

    /**
     * repaints the game board.
     *
     * @param g object of class Graphics to paint with.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawWorld(g);
    }

    /**
     * Draws the game world with the textures.
     *
     * @param g the Graphics object that helps to draw.
     */
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

    /**
     * Moves the player on the game board.
     *
     * @param dx change in x coordinate.
     * @param dy change in y coordinate.
     */
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

    /**
     * Checks if all boxes are on their target spots.
     *
     * @return false when all boxes are not on target spots, otherwise true.
     */
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

    /**
     * Restarts current level.
     */
    public void restartLevel() {
        level = Levels.getLevel(currentLevelNumber);
        initializeWorld();
        requestFocusInWindow();
        repaint();
    }
}
