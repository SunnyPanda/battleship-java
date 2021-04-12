package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Field field = new Field();
        field.drawField();

        Scanner in = new Scanner(System.in);
        for (Ships ship : Ships.values()) {
            System.out.printf("Enter the coordinates of the %s (%d cells): \n", ship.name, ship.size);
            String[] coordinates = in.nextLine().split(" ");
            field.placeShip(coordinates, ship);
            field.drawField();
        }
    }
}