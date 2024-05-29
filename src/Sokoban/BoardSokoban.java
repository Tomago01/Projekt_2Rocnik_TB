package Sokoban;

public class BoardSokoban{

    private char[][] level;

    private int playerX, playerY;

    private char[][] currentLevel;
    private int currentLevelNumber;

    public BoardSokoban(char[][] level, int levelNumber) {
        currentLevelNumber = levelNumber;
        currentLevel = this.level;
    }


}