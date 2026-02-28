public class Renderer {

    private static final char[] COL_LABELS = {'1','2','3','4','5','6','7','8'} ;
    private static final char[] ROW_LABELS = {'A','B','C','D','E','F','G','H'} ;

    private static final char DEFAULT = '·' ;
    private static final char HIT = '◉' ;
    private static final char MISS = '~' ;

    public static final int GRID_WIDTH = 17;
    public static final int GRID_HEIGHT = 9;

    private static int cellCol2GridCol(int cellCol) {
        return 2 + (2*cellCol);
    }

    private static int cellRow2GridRow(int cellRow) {
        return 1 + cellRow ;
    }



    public static char[][] renderEmptyGrid() {

        char[][] grid = new char[GRID_HEIGHT][GRID_WIDTH] ;

        for (int i = 0; i < GRID_HEIGHT; i = i + 1) {
            for (int j = 0; j < GRID_WIDTH; j = j + 1) {
                grid[i][j] = ' ';
            }
        }

        for (int i = 0; i < GRID_HEIGHT; i = i + 1) {
            if (i > 0) {
                grid[i][0] = ROW_LABELS[i - 1];
            }
            for (int j = 0; j < GRID_WIDTH; j = j + 1) {
                if (i == 0) {
                    for (int a = 2; a < GRID_WIDTH; a = a + 2) {
                        grid[i][a] = COL_LABELS[(a / 2) - 1];
                    }
                }

                if (i > 0 && j > 0 && (j % 2 == 0)) {
                    grid[i][j] = DEFAULT;
                }
            }
        }

        return grid ;
    }

    public static char[][] renderFleetGrid(Fleet fleet, Guesses guesses) {

        char[][] grid = renderEmptyGrid();

        for (int i = 0; i < Cell.ROW_COUNT; i = i + 1) {
            for (int j = 0; j < Cell.COL_COUNT; j = j + 1) {
                Cell cell = new Cell(i, j);

                if (fleet.getShipAt(cell) != null) {
                    grid[cellRow2GridRow(i)][cellCol2GridCol(j)] = fleet.getShipAt(cell).getName().charAt(0);
                }

                if (guesses.isGuessed(cell)) {
                    if (fleet.getShipAt(cell) != null) {
                        grid[cellRow2GridRow(i)][cellCol2GridCol(j)] = HIT;
                    } else {
                        grid[cellRow2GridRow(i)][cellCol2GridCol(j)] = MISS;
                    }
                }
            }
        }

        return grid ;
    }

    public static char[][] renderTargetingGrid(Fleet fleet, Guesses guesses) {

        char[][] grid = renderEmptyGrid();

        for (int i = 0; i < Cell.ROW_COUNT; i = i + 1) {
            for (int j = 0; j < Cell.COL_COUNT; j = j + 1) {
                Cell cell = new Cell(i, j);

                if (guesses.isGuessed(cell)) {
                    if (fleet.getShipAt(cell) != null) {
                        if (fleet.getShipAt(cell).getHealth() <= 0) {
                            grid[cellRow2GridRow(i)][cellCol2GridCol(j)] = fleet.getShipAt(cell).getName().charAt(0);
                        } else {
                            grid[cellRow2GridRow(i)][cellCol2GridCol(j)] = HIT;
                        }
                    } else {
                        grid [cellRow2GridRow(i)][cellCol2GridCol(j)] = MISS;
                    }
                }
            }
        }

        return grid ;
    }
}
