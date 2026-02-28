import java.util.LinkedList;

public class Guesses {

    private LinkedList<Cell> linkedList = new LinkedList<Cell>();
    private int totalGuesses = 0;

    public Guesses() {

    }

    public void addGuess(Cell cell) {
        if(isGuessed(cell) == false && cell.isInBounds()) {
            linkedList.add(cell);
            totalGuesses = totalGuesses + 1;
        }
    }

    public boolean isGuessed(Cell cell) {

        int cellOneRow = cell.getRow();
        int cellOneColumn = cell.getCol();

        for (int i = 0; i < linkedList.size(); i = i + 1) {
            Cell temporaryCell = linkedList.get(i);

            int cellTwoRow = temporaryCell.getRow();
            int cellTwoColumn = temporaryCell.getCol();

            if ((cellOneRow == cellTwoRow) && (cellOneColumn == cellTwoColumn)) {
                return true;
            } 
        }
        return false;
    }

    public int getTotalGuesses() {
        return totalGuesses;
    }
}
