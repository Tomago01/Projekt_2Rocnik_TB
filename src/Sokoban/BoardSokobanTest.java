package Sokoban;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardSokobanTest {

    private BoardSokoban boardSokoban;

    @BeforeEach
    public void setUp() {
        char[][] level = Levels.getLevel(1);
        boardSokoban = new BoardSokoban(level, 1);
    }

    @Test
    public void testinitializeWorld() {
        boardSokoban.initializeWorld();

        boolean playerFound = false;
        for (int y = 0; y < boardSokoban.level.length; y++) {
            for (int x = 0; x < boardSokoban.level[y].length; x++) {
                if (boardSokoban.level[y][x] == '@') {
                    playerFound = true;
                    assertEquals(boardSokoban.playerX, x);
                    assertEquals(boardSokoban.playerY, y);
                    break;
                }
            }
        }
        assertTrue(playerFound, "Player should be found on the board.");
    }

    @Test
    public void testMovePlayer() {
        boardSokoban.initializeWorld();

        int initialX = boardSokoban.playerX;
        int initialY = boardSokoban.playerY;

        boardSokoban.movePlayer(1, 0);

        assertEquals(initialX + 1, boardSokoban.playerX);
        assertEquals(initialY, boardSokoban.playerY);
        assertEquals('@', boardSokoban.level[boardSokoban.playerY][boardSokoban.playerX]);
        assertNotEquals('@', boardSokoban.level[initialY][initialX]);
    }

    @Test
    public void testRestartLevel() {
        boardSokoban.initializeWorld();

        boardSokoban.movePlayer(1, 0);

        boardSokoban.restartLevel();

        char[][] originalLevel = Levels.getLevel(1);
        for (int y = 0; y < originalLevel.length; y++) {
            for (int x = 0; x < originalLevel[y].length; x++) {
                assertEquals(originalLevel[y][x], boardSokoban.level[y][x]);
            }
        }
    }

}
