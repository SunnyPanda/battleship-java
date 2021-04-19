package battleship;

import battleship.field.Field;

public class Player {
    public int numberOfShips;

    private final Field field;
    private final String name;
    private boolean isMyTurn;

    public Player(String name, Field field, boolean isMyTurn) {
        this.name = name;
        this.field = field;
        this.isMyTurn = isMyTurn;
        numberOfShips = 5;
    }

    public Field getField() {
        return field;
    }

    public String getName() {
        return name;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void setMyTurn(boolean myTurn) {
        isMyTurn = myTurn;
    }

    public void reduceAmountOfShips() {
        numberOfShips--;
    }

    public boolean isShipsLeft() {
        return numberOfShips > 0;
    }
}
