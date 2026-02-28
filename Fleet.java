import java.util.ArrayList;
import java.util.List;

public class Fleet {

    public static final String[] names = {"Carrier", "Battleship", "Destroyer", "Submarine", "Patrol Boat"};
    public static final int[] lengths = {5,4,3,3,2};

    private ArrayList<Ship> ships = new ArrayList<Ship>();
    private int shipsRemaining = 5;

    public Fleet() {

    }

    public List getShips() {
        return ships;
    }

    public int shipsRemaining() {
        return shipsRemaining;
    }

    public boolean addShip(Ship ship) {
        if (ship.isPositioned() && ship.isInBounds()) {
            for (int i = 0; i < ships.size(); i = i + 1) {
                if (ship.intersectsWith(ships.get(i))) {
                    return false;
                }
            }

            ships.add(ship);
            return true;
        } else {
            return false;
        }
    }

    public Ship getShipAt(Cell cell) {
        for (int i = 0; i < ships.size(); i = i + 1) {
            if (ships.get(i).intersectsWith(cell)) {
                return ships.get(i);
            }
        }

        return null;
    }

    public Attack handleAttack(Cell cell) {
        for (int i = 0; i < ships.size(); i = i + 1) {
            Attack attack = ships.get(i).handleAttack(cell);

            if (attack.isHit()) {
                if (attack.getSunkShip() != null) {
                    shipsRemaining = shipsRemaining - 1;
                }
                return attack;
            }
        }

        return new Attack(cell, false, null);
    }

}
