public class Cell {

    public static final String[] ROW_LABELS = {"A","B","C","D","E","F","G","H"};
    public static final String[] COL_LABELS = {"1","2","3","4","5","6","7","8"};

    public static final int ROW_COUNT = 8 ;
    public static final int COL_COUNT = 8 ;

    private int row;
    private int column;

    public Cell(int row, int column) {
            this.row = row;
            this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return column;
    }

    public boolean isInBounds() {
        if ((row >= 0 && row < ROW_COUNT) && (column >= 0 && column < COL_COUNT)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        if (isInBounds()) {
            String row = ROW_LABELS[this.row];
            String column = COL_LABELS[this.column];
            return row + column;
        } else {
            return "INVALID";
        }
    }

    public static Cell fromString(String position) {
        
        if (position.length() != 2) {
            return null;
        }

        position = position.toUpperCase();

        char row = position.charAt(0);
        char column = position.charAt(1);
        int rowIndex = row;
        int columnIndex = column;
        rowIndex = rowIndex - 65;
        columnIndex = columnIndex - 49;

        if ((rowIndex >= 0 && rowIndex < ROW_COUNT) && (columnIndex >= 0 && columnIndex < COL_COUNT)) {
            Cell cell = new Cell(rowIndex, columnIndex);
            return cell;
        } else {
            return null;
        }
    }
}
