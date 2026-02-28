import java.util.Scanner;

public class Battleships {

    //Do not change this line. 
    //Use this whenever you need to get any input from the user (so don't create any other scanners)
    public static Scanner input = new Scanner(System.in);


    public static void main(String[] args) {

        //Do not change this line. 
        //Use this variable when you need the enemy to position thier fleet or make an attack
        EnemyAI enemyAI = new EnemyAI(args);


        Fleet playerFleet = buildPlayerFleet(); //this is calling a helper method you will need to implement below
        
        Fleet enemyFleet = enemyAI.buildFleet();

        Guesses playerGuesses = new Guesses();
        Guesses enemyGuesses = new Guesses();
        
        BattleshipsBoard.display(playerFleet, enemyFleet, playerGuesses, enemyGuesses, null, null);

        while (playerFleet.shipsRemaining() > 0 && enemyFleet.shipsRemaining() > 0) {

            Cell playerGuess = getPlayerGuess(input, playerGuesses) ;//this is calling a helper method you will need to implement below
            playerGuesses.addGuess(playerGuess);
            Attack playerAttack = enemyFleet.handleAttack(playerGuess);

            Cell enemyGuess = enemyAI.getNextGuess();
            enemyGuesses.addGuess(enemyGuess);
            Attack enemyAttack = playerFleet.handleAttack(enemyGuess);
            enemyAI.informAboutResult(enemyAttack);

            BattleshipsBoard.display(playerFleet, enemyFleet, playerGuesses, enemyGuesses, playerAttack, enemyAttack);
        }

        if (playerFleet.shipsRemaining() > 0) {
            System.out.println("You win!") ;
        } else {
            System.out.println("You lose!");
        }

    }


    //helper method to allow player to build and position their ships
    private static Fleet buildPlayerFleet() {

        Fleet playerFleet = new Fleet();

        for (int i = 0; i < Fleet.names.length; i = i + 1) {

            BattleshipsBoard.display(playerFleet);

            Ship ship = new Ship(Fleet.names[i], Fleet.lengths[i]);

            addShipToFleet(ship, playerFleet);
        }

        return playerFleet ;

    }

    //helper method to add a single ship to the fleet
    //it should only return when the ship has been sucessfully added
    private static void addShipToFleet(Ship ship, Fleet fleet) {

        while (true) {

            System.out.println("Where would you like to place your " + ship.getName() + "?"); //edit this to show the ship name
            String response = input.nextLine();

            Position position = Position.fromString(response);

            if (position == null) {
                System.out.println("Invalid position");
                continue;
            }

            ship.setPosition(position.getLocation(), position.isHorizontal());
            
            if (ship.isInBounds() == false) {
                System.out.println("Out of bounds");
                continue;
            }

            boolean addedToFleet = fleet.addShip(ship);

            if (addedToFleet == false) {
                System.out.println("Already occupied");
                continue;
            }

            return;
        }
    }

    //helper method to get the player's guess
    private static Cell getPlayerGuess(Scanner input, Guesses playerGuesses) {

        while (true) {

            System.out.print("Please enter target: ") ;

            String response = input.nextLine();

            Cell cell = Cell.fromString(response);

            if (cell == null) {
                System.out.println("Invalid location");
                continue;
            }

            if (playerGuesses.isGuessed(cell)) {
                System.out.println("You guessed that already!");
                continue;
            }

            return cell;
        }
    }
}
