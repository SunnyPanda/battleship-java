package battleship.ships;

public class Ship {
    protected int hits;
    protected int size;
    protected String cells;

    public Ship() {

    }

    Ship(int size) {
        this.size = size;
    }

    public boolean isDestroyed() {
        return hits == size;
    }

    public void countHits() {
        hits++;
    }

    public void addCell(String cell) {
        cells += cell;
    }

    public static Ship createShip(Ships ship) {
        switch (ship) {
            case AIRCRAFT_CARRIER: return new AircraftCarrier(5);
            case BATTLESHIP: return new Battleship(4);
            case SUBMARINE: return new Submarine(3);
            case CRUISER: return new Cruiser(3);
            case DESTROYER: return new Destroyer(2);
            default: return new Ship();
        }
    }

    public String getCells() {
        return cells;
    }
}
