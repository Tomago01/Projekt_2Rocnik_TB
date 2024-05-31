package LightsOut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class BoardLightsOutTest {

    private BoardLightsOut boardLightsOut;

    @BeforeEach
    public void setUp() {
        boardLightsOut = new BoardLightsOut(5);
    }

    @Test
    public void testAreAllLightsOff() {
        boardLightsOut.initializeBoard();
        assertFalse(boardLightsOut.areAllLightsOff(), "After initialization, all lights should not be off.");
    }

    @Test
    public void testMoveCounter() {
        boardLightsOut.initializeGame();
        int initialMoveCounter = boardLightsOut.moveCounter;
        JButton button = boardLightsOut.buttons[0][0];
        button.doClick();
        assertEquals(initialMoveCounter + 1, boardLightsOut.moveCounter, "Movecounter should increment by 1 after pressing button.");
    }
}
