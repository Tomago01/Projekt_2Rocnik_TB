package Sokoban;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeysInputs extends KeyAdapter {
    private final BoardSokoban boardSokoban;

    public KeysInputs(BoardSokoban boardSokoban) {
        this.boardSokoban = boardSokoban;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                boardSokoban.movePlayer(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                boardSokoban.movePlayer(1, 0);
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                boardSokoban.movePlayer(0, -1);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                boardSokoban.movePlayer(0, 1);
                break;
        }

        boardSokoban.repaint();
    }

}
