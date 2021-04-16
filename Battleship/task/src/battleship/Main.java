package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Field field = new Field();
        field.drawField(FieldType.MINE);

        Scanner in = new Scanner(System.in);

        placeShips(in, field);
        play(in, field);
    }

    private static void placeShips(Scanner in, Field field) {
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
    }

    private static void play(Scanner in, Field field) {
        System.out.println("The game starts!");
        field.drawField(FieldType.ENEMY);

        System.out.println("Take a shot!");
        while (Ship.isShipsLeft()) {
            takeShot(in, field);
        }
    }

    private static void takeShot(Scanner in, Field field) {
        boolean isWrongCoordinates = true;
        while (isWrongCoordinates) {
            try {
                String shot = in.nextLine();

                boolean isHit = field.processShot(shot);
                field.drawField(FieldType.ENEMY);
                if (isHit) {
                    Ship ship = field.getBrokenShip(shot);
                    if (Ship.isShipsLeft()) {
                        if (ship.isDestroyed()) {
                            System.out.println("You sank a ship! Specify a new target:");
                        } else {
                            System.out.println("You hit a ship! Try again:");
                        }
                    } else {
                        System.out.println("You sank the last ship. You won. Congratulations!");
                    }
//                    System.out.println(Ship.isShipsLeft() ? ship.isDestroyed() ? "You sank a ship! Specify a new target:" : "You hit a ship! Try again:" : "You sank the last ship. You won. Congratulations!");
                } else {
                    System.out.println("You missed. Try again:");
                }
                isWrongCoordinates = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}