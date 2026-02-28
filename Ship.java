import java.util.ArrayList;

public class Ship {

    private String name;
    private int length;
    private int health;
    private ArrayList<Cell> arrayList = new ArrayList<Cell>();
    private boolean positioned;
    

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        health = length; 
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getHealth() {
        return health;
    }

    public void setPosition(Cell cell, boolean horizontal) {

        arrayList = new ArrayList<Cell>();

        int row;
        int column;

        if (horizontal == true) {
            row = cell.getRow();
            column = cell.getCol();

            for (int j = 0; j < length; j = j + 1) {
                Cell positionCell = new Cell(row, column + j);

                arrayList.add(positionCell);
            }
        }

        if (horizontal == false) {
            row = cell.getRow();
            column = cell.getCol();

            for (int j = 0; j < length; j = j + 1) {
                Cell positionCell = new Cell(row + j, column);

                arrayList.add(positionCell);
            }
        }
        
        positioned = true;
        
    }

    public boolean isPositioned() {
        return positioned;
    }

    public boolean isInBounds() {

        if (positioned) {

            for (int i = 0; i < arrayList.size(); i = i + 1) {
                Cell currentCell = arrayList.get(i);

                if (currentCell.isInBounds() == false) {
                    return false;
                }
            }

            return true;

        } else {
            return false;
        }
    }

    public boolean intersectsWith(Cell cell) {

        if (positioned) {
            int cellOneRow = cell.getRow();
            int cellOneColumn = cell.getCol();

            for (int i = 0; i < arrayList.size(); i = i + 1) {
                Cell currentCell = arrayList.get(i);

                int cellTwoRow = currentCell.getRow();
                int cellTwoColumn = currentCell.getCol();

                if ((cellOneRow == cellTwoRow) && (cellOneColumn == cellTwoColumn)) {
                    return true;
                }
            }

            return false;

        } else {
            return false;
        }
    }

    public boolean intersectsWith(Ship ship) {
        if (positioned && ship.isPositioned()) {
            for (int i = 0; i < arrayList.size(); i = i + 1) {
                Cell shipOneCell = arrayList.get(i);
                
                if (ship.intersectsWith(shipOneCell) == true) {
                    return true;
                }
                
            }
        } else {
            return false;
        }

        return false;
    }

    public Attack handleAttack(Cell cell) {
        
        boolean successful = false;
        boolean shipSunk = false;
        
        if (positioned) {
            if (intersectsWith(cell) == true) {
                successful = true;
                health = health - 1;
            }
        }

        if (health <= 0) {
            shipSunk = true;
        }
        
        if (shipSunk) {
            Attack attack = new Attack(cell, successful, this);
            return attack;
        } else {
            Attack attack = new Attack(cell, successful, null);
            return attack;
        }
    }
}
