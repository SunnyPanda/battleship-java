package battleship;

import java.util.List;
import java.util.ArrayList;

public class Ship {
    private static int amountOfShips = 5;
    public static List<Ship> ships = new ArrayList<>();

    protected int hits;
    protected int size;
    protected String cells;

    public boolean isDestroyed() {
        return hits == size;
    }

    public void countHits() {
        hits++;
    }

    public int getSize() {
        return size;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        hits = hits;
    }

    public void addCell(String cell) {
        cells += cell;
    }

    public static Ship createShip(Ships ship) {
        switch (ship) {
            case AIRCRAFT_CARRIER: return new AircraftCarrier();
            case BATTLESHIP: return new Battleship();
            case SUBMARINE: return new Submarine();
            case CRUISER: return new Cruiser();
            case DESTROYER: return new Destroyer();
            default: return new Ship();
        }
    }

    public static void reduceAmountOfShips() {
        amountOfShips--;
    }

    public static boolean isShipsLeft() {
        return amountOfShips > 0;
    }

    public static void addShip(Ship ship) {
        ships.add(ship);
    }
}
