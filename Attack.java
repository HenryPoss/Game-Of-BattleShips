public class Attack {

    Cell attackedCell;
    boolean successful;
    Ship ship;

    public Attack(Cell cell, boolean successful, Ship ship) {
        attackedCell = cell;
        this.successful = successful;
        this.ship = ship;
    }

    public Cell getTarget() {
        return attackedCell;
    }

    public boolean isHit() {
        return successful;
    }

    public Ship getSunkShip() {
        
        if (ship != null) {
            return ship;
        } else {
            return null;
        }
    }

    public String toString() {
        if (isHit() && getSunkShip() == null) {
            return attackedCell + " hits!";
        } else if (isHit() == false) {
            return attackedCell + " misses!";
        } else {
            return attackedCell + " hits, and sinks the " + getSunkShip().getName() + "!";
        }
    }
    
}
