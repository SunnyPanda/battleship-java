package battleship;

import battleship.field.Field;
import battleship.field.FieldType;
import battleship.ships.Ship;
import battleship.ships.Ships;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Player player1 = new Player("Player 1", new Field(), true);
        Player player2 = new Player("Player 2", new Field(), false);

        Scanner in = new Scanner(System.in);

        placeShips(in, player1);
        placeShips(in, player2);
        play(in, player1, player2);
    }

    private static void placeShips(Scanner in, Player player) {
        Field field = player.getField();

        System.out.printf("%s, place your ships on the game field\n", player.getName());
        field.drawField(FieldType.MINE);

        for (Ships ship : Ships.values()) {
            Ship newShip = Ship.createShip(ship);
            Ship.addShip(newShip);
            boolean isValid = false;
            int[] convertedCoordinates = new int[4];
            System.out.printf("Enter the coordinates of the %s (%d cells): \n", ship.getName(), ship.getSize());
            while (!isValid) {
                try {
                    String[] coordinates = in.nextLine().split(" ");
                    convertedCoordinates = field.convertCoordinates(coordinates);
                    field.validateCoordinates(convertedCoordinates, ship);
                    isValid = true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            field.placeShip(convertedCoordinates, newShip);
            field.drawField(FieldType.MINE);
        }

        System.out.println("Press Enter and pass the move to another player");
        in.nextLine();
    }

    private static void play(Scanner in, Player player1, Player player2) {
        Player friend;
        Player foe;
        while (player1.isShipsLeft() && player2.isShipsLeft()) {
            friend = player1.isMyTurn() ? player1 : player2;
            foe = player1.isMyTurn() ? player2 : player1;

            drawField(friend, foe);
            takeShot(in, friend, foe);

            player1.setMyTurn(!player1.isMyTurn());
            player2.setMyTurn(!player2.isMyTurn());
        }
    }

    private static void drawField(Player friend, Player foe) {
        foe.getField().drawField(FieldType.ENEMY);
        System.out.println("---------------------");
        friend.getField().drawField(FieldType.MINE);
    }

    private static void takeShot(Scanner in, Player friend, Player foe) {
        System.out.printf("%s, it's your turn:", friend.getName());
        boolean isWrongCoordinates = true;
        String shot;
        while (isWrongCoordinates) {
            try {
                shot = in.nextLine();

                processShot(shot, foe, in);

                isWrongCoordinates = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void processShot(String shot, Player player, Scanner in) throws Exception {
        Field field = player.getField();
        boolean isHit = field.processShot(shot);
        if (isHit) {
            Ship ship = field.getBrokenShip(shot);
            if (ship.isDestroyed()) {
                player.reduceAmountOfShips();
                if (player.isShipsLeft()) {
                    System.out.println("You sank a ship!");
                    System.out.println("Press Enter and pass the move to another player");
                    in.nextLine();
                } else {
                    System.out.println("You sank the last ship. You won. Congratulations!");
                }
            } else {
                System.out.println("You hit a ship!");
                System.out.println("Press Enter and pass the move to another player");
                in.nextLine();
            }
        } else {
            System.out.println("You missed!");
            System.out.println("Press Enter and pass the move to another player");
            in.nextLine();
        }
    }
}