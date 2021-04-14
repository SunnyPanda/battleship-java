package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Field field = new Field();
        field.drawField(FieldType.MINE);

        Scanner in = new Scanner(System.in);

        placeShips(in, field);

        System.out.println("The game starts!");
        field.drawField(FieldType.ENEMY);

        System.out.println("Take a shot!");
        boolean isWrongCoordinates = true;
        while (isWrongCoordinates) {
            try {
                String shot = in.nextLine();
                boolean isHit = field.processShot(shot);
                field.drawField(FieldType.ENEMY);
                System.out.println(isHit ? "You hit a ship!" : "You missed!");
                field.drawField(FieldType.MINE);
                isWrongCoordinates = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void placeShips(Scanner in, Field field) {
p
        for (Ships ship : Ships.values()) {
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
            field.placeShip(convertedCoordinates);
            field.drawField(FieldType.MINE);
        }
    }
}