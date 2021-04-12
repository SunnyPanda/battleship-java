package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Field field = new Field();
        field.drawField();

        Scanner in = new Scanner(System.in);
        for (Ships ship : Ships.values()) {
            boolean isValid = false;
            int[] convertedCoordinates = new int[4];
            System.out.printf("Enter the coordinates of the %s (%d cells): \n", ship.name, ship.size);
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
            field.drawField();
        }
    }
}