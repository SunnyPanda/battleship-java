package battleship.field;

import battleship.utils.Converter;
import battleship.utils.Validator;
import battleship.ships.Ship;
import battleship.ships.Ships;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private final char[][] myField;
    private final char[][] enemyField;
    private List<Ship> ships = new ArrayList<>();
    private final Converter converter = new Converter();
    private final Validator validator = new Validator();

    public Field() {
        myField = new char[10][11];
        for (int i = 0, k = 65; i < myField.length; i++, k++) {
            for (int j = 0; j < myField[i].length; j++) {
                myField[i][j] = j == 0 ? (char) k : Cell.EMPTY.getLetter();
            }
        }

        enemyField = new char[10][11];
        for (int i = 0, k = 65; i < enemyField.length; i++, k++) {
            for (int j = 0; j < enemyField[i].length; j++) {
                enemyField[i][j] = j == 0 ? (char) k : Cell.EMPTY.getLetter();
            }
        }
    }

    public void drawField(FieldType fieldType) {
        System.out.print("  ");
        for (int i = 1; i < 11; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        char[][] field = fieldType == FieldType.MINE ? myField : enemyField;
        drawCells(field);
    }

    private void drawCells(char[][] field) {
        for (char[] chars : field) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
    }

    public int[] convertCoordinates(String[] coordinates) {
        return converter.convertCoordinates(coordinates);
    }

    public void validateCoordinates(int[] convertedCoordinates, Ships ship) throws Exception {
        validator.validateCoordinates(convertedCoordinates, ship, myField);
    }

    public void placeShip(int[] coordinates, Ship ship) {
        int startRow = coordinates[0];
        int startCol = coordinates[1];
        int endRow = coordinates[2];
        int endCol = coordinates[3];
        fillCells(startRow, startCol, endRow, endCol, ship);
    }

    private void fillCells(int startRow, int startCol, int endRow, int endCol, Ship ship) {
        if (startRow == endRow) {
            for (int col = startCol; col <= endCol; col++) {
                myField[startRow][col] = Cell.SHIP.getLetter();
                ship.addCell(converter.convertCoordinates(startRow, col));
            }
        } else {
            for (int row = startRow; row <= endRow; row++) {
                myField[row][startCol] = Cell.SHIP.getLetter();
                ship.addCell(converter.convertCoordinates(row, startCol));
            }
        }
    }

    public boolean processShot(String coordinate) throws Exception {
        int[] cell = converter.convertCoordinate(coordinate);
        char targetCell = myField[cell[0]][cell[1]];
        if (validator.isValidCoordinate(cell, myField)) {
            if (targetCell == Cell.HIT.getLetter()) {
                return true;
            }
            boolean isHit = targetCell == Cell.SHIP.getLetter();
            char shot = isHit ? Cell.HIT.getLetter() : Cell.MISS.getLetter();
            myField[cell[0]][cell[1]] = shot;
            enemyField[cell[0]][cell[1]] = shot;
            return isHit;
        }

        throw new Exception("Error! You entered the wrong coordinates! Try again:");
    }

    public Ship getBrokenShip(String coordinate) {
        Ship brokenShip = new Ship();
        for(Ship ship : ships) {
            if (ship.getCells().contains(coordinate)) {
                ship.countHits();
                brokenShip = ship;
                break;
            }
        }
        return brokenShip;
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }
}

