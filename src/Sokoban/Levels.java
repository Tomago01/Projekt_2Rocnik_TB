package Sokoban;

/**
 * Access to different levels themselves made from characters for the Sokoban game.
 */
public class Levels {
    private static final char[][] level1 = {
            {'#', '#', '#', '#', '#', '#', '#'},
            {'#', '.', ' ', ' ', ' ', '.', '#'},
            {'#', ' ', '$', '@', '$', ' ', '#'},
            {'#', ' ', '#', '#', '#', ' ', '#'},
            {'#', ' ', '$', ' ', '$', ' ', '#'},
            {'#', '.', ' ', ' ', ' ', '.', '#'},
            {'#', '#', '#', '#', '#', '#', '#'},

    };

    private static final char[][] level2 = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', ' ', ' ', '#', '#', '#', '#', '#', '#'},
            {'#', '#', ' ', ' ', ' ', ' ', ' ', '#', '#', '#'},
            {'#', '#', ' ', '#', '#', ' ', ' ', ' ', '#', '#'},
            {'#', '.', ' ', '.', '#', ' ', '@', '$', '#', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', '$', '$', ' ', '#'},
            {'#', ' ', ' ', '.', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
    };

    private static final char[][] level3 = {
            {'#', '#', '#', '#', '#', '#'},
            {'#', ' ', '@', ' ', '#', '#'},
            {'#', '.', '.', '.', '#', '#'},
            {'#', '$', '$', '$', '#', '#'},
            {'#', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#'},
    };
    private static final char[][] level4 = {
            {'#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', '.', ' ', '.', ' ', ' ', '#'},
            {'#', ' ', '#', '#', ' ', '#', '#'},
            {'#', ' ', ' ', '$', ' ', '#', '#'},
            {'#', '#', '#', '$', ' ', '#', '#'},
            {'#', '#', '#', '@', ' ', '#', '#'},
            {'#', '#', '#', ' ', ' ', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#'},
    };
    private static final char[][] level5 = {
            {'#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', '#', '#', '#', '#'},
            {'#', ' ', '.', ' ', '.', ' ', '#'},
            {'#', ' ', '$', '$', '#', '@', '#'},
            {'#', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#'},

    };
    private static final char[][] level6 = {
            {'#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', '.', '#', '#'},
            {'#', ' ', '#', '#', ' ', '#', '#'},
            {'#', ' ', ' ', '$', '$', '@', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', '#'},
            {'#', '.', ' ', ' ', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#'},
    };
    private static final char[][] level7 = {
            {'#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', '#', '#', '#'},
            {'#', ' ', '@', ' ', '#', '#', '#'},
            {'#', ' ', '$', '$', '#', '#', '#'},
            {'#', '#', '.', ' ', '.', ' ', '#'},
            {'#', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#'},
    };
    private static final char[][] level8 = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', ' ', ' ', ' ', '#', '#'},
            {'#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', '#'},
            {'#', '#', ' ', ' ', ' ', ' ', ' ', '#', '.', ' ', '#'},
            {'#', ' ', '$', ' ', '$', ' ', '@', ' ', ' ', '#', '#'},
            {'#', ' ', '#', '#', '#', '#', '#', '#', '.', '#', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},

    };
    private static final char[][] level9 = {
            {'#', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', '#', '#', '#'},
            {'#', ' ', '$', '$', ' ', '#'},
            {'#', '.', '.', '.', ' ', '#'},
            {'#', ' ', '$', ' ', ' ', '#'},
            {'#', ' ', '@', ' ', '#', '#'},
            {'#', '#', '#', '#', '#', '#'},
    };
    private static final char[][] level10 = {
            {'#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', ' ', ' ', '#', '#'},
            {'#', '#', '@', '$', '.', '#', '#'},
            {'#', ' ', '$', '$', ' ', ' ', '#'},
            {'#', ' ', '.', ' ', '.', ' ', '#'},
            {'#', '#', '#', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#'},
    };

    /**
     * Creates a copy of that level specified there.
     *
     * @param level the level we are making copy of.
     * @return returns copy of specified level.
     */
    private static char[][] copyLevel(char[][] level) {
        char[][] copy = new char[level.length][];
        for (int i = 0; i < level.length; i++) {
            copy[i] = level[i].clone();
        }
        return copy;
    }

    /**
     * Gets the specified level.
     *
     * @param levelNumber the number of the level we want to retrieve
     * @return returns specified level as a two-dimensional char array
     * @throws IllegalArgumentException if the level number is invalid
     */
    public static char[][] getLevel(int levelNumber) {
        switch (levelNumber) {
            case 1:
                return copyLevel(level1);
            case 2:
                return copyLevel(level2);
            case 3:
                return copyLevel(level3);
            case 4:
                return copyLevel(level4);
            case 5:
                return copyLevel(level5);
            case 6:
                return copyLevel(level6);
            case 7:
                return copyLevel(level7);
            case 8:
                return copyLevel(level8);
            case 9:
                return copyLevel(level9);
            case 10:
                return copyLevel(level10);
            default:
                throw new IllegalArgumentException("Invalid level number: " + levelNumber);
        }
    }
}

