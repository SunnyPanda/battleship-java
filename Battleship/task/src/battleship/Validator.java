package battleship;

public class Validator {

    private static final char SHIP = 'O';

    public void validateCoordinates(int[] coordinates, Ships ship, char[][] field) throws Exception {
        int startRow = coordinates[0];
        int startCol = coordinates[1];
        int endRow = coordinates[2];
        int endCol = coordinates[3];

        if (startRow == endRow) {
            if (Math.abs(startCol - endCol) + 1 != ship.size) {
                throw new Exception("Error! Wrong length of the " + ship.name + "! Try again:");
            }
        } else if (startCol == endCol) {
            if (Math.abs(startRow - endRow) + 1 != ship.size) {
                throw new Exception("Error! Wrong length of the " + ship.name + "! Try again:");
            }
        }

        if (startRow != endRow && startCol != endCol) {
            throw new Exception("Error! Wrong ship location! Try again:");
        }

        if (checkCells(coordinates[0], coordinates[1], coordinates[2], coordinates[3], field)) {
            throw new Exception("Error! You placed it too close to another one. Try again:");
        }

    }

    private boolean checkCells(int startRow, int startCol, int endRow, int endCol, char[][] field) {
        if (startRow == endRow) {   // if horizontal
            for (int col = startCol; col <= endCol; col++) {    // check all cell in the ship
                if (field[startRow][col] == SHIP) {  // if cell is occupied
                    return true;
                }
                if (col == startCol && col > 1) {   // if this is the first cell and it is placed with gap from the edge
                    if (field[startRow][col - 1] == SHIP) {  // if cell to the left from start is occupied
                        return true;
                    }
                }
                if (col == endCol && col < field[startRow].length - 1) { // if this is the last cell and it is placed with gap from the edge
                    if (field[startRow][col + 1] == SHIP) { // // if cell to the right from end is occupied
                        return true;
                    }
                }
                if (startRow > 0 && field[startRow - 1][col] == SHIP) {  // if row is placed with gap from the top edge
                    return true;
                }
                if (startRow < field.length - 1 && field[startRow + 1][col] == SHIP) {   // if row is placed with gap from the bottom edge
                    return true;
                }
            }
        } else {    // if vertical
            for (int row = startRow; row <= endRow; row++) {
                if (field[row][startCol] == SHIP) {
                    return true;
                }
                if (row == startRow && row > 0) {
                    if (field[row - 1][startCol] == SHIP) {
                        return true;
                    }
                }
                if (row == endRow && row < field.length - 1) {
                    if (field[row + 1][startCol] == SHIP) {
                        return true;
                    }
                }
                if (startCol > 1 && field[row][startCol - 1] == SHIP) {
                    return true;
                }
                if (startCol < field[startRow].length - 1 && field[row][startCol + 1] == SHIP) {
                    return true;
                }
            }
        }

        return false;
    }
}
