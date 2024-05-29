package Sokoban;

public class BoardSokoban{

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