package battleship;

public class Field {
    private final char[][] field;

    Field() {
        field = new char[10][11];
        for (int i = 0, k = 65; i < field.length; i++, k++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = j == 0 ? (char) k : '~';
            }
        }
    }

    public void drawField() {
        System.out.print("  ");
        for (int i = 1; i < 11; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (char[] chars : field) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
    }

    public int[] convertCoordinates(String[] coordinates) {
        int[] convertedCoordinates = new int[4];
        int[] start = convertCoordinate(coordinates[0]);
        int[] end = convertCoordinate(coordinates[1]);
        convertedCoordinates[0] = Math.min(start[0], end[0]);
        convertedCoordinates[1] = Math.min(start[1], end[1]);
        convertedCoordinates[2] = Math.max(start[0], end[0]);
        convertedCoordinates[3] = Math.max(start[1], end[1]);

        return convertedCoordinates;
    }

    private int[] convertCoordinate(String coordinate) {
        int[] cell = new int[2];
        cell[0] = convertRow(coordinate.substring(0, 1));
        cell[1] = convertColumn(coordinate.substring(1));
        return cell;
    }


    public void placeShip(int[] coordinates) {
        int startRow = coordinates[0];
        int startCol = coordinates[1];
        int endRow = coordinates[2];
        int endCol = coordinates[3];
        fillCells(startRow, startCol, endRow, endCol);
    }

    private void fillCells(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow) {
            for (int col = startCol; col <= endCol; col++) {
                field[startRow][col] = 'O';
            }
        } else {
            for (int row = startRow; row <= endRow; row++) {
                field[row][startCol] = 'O';
            }
        }
    }

    private int convertRow(String row) {
        String rows = "ABCDEFGHIJ";
        return rows.indexOf(row);
    }

    private int convertColumn(String col) {
        return Integer.parseInt(col);
    }

    public void validateCoordinates(int[] coordinates, Ships ship) throws Exception {
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

        if (checkCells(coordinates[0], coordinates[1], coordinates[2], coordinates[3])) {
            throw new Exception("Error! You placed it too close to another one. Try again:");
        }

    }

    private boolean checkCells(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow) {   // if horizontal
            for (int col = startCol; col <= endCol; col++) {    // check all cell in the ship
                if (field[startRow][col] == 'O') {  // if cell is occupied
                    return true;
                }
                if (col == startCol && col > 1) {   // if this is the first cell and it is placed with gap from the edge
                    if (field[startRow][col - 1] == 'O') {  // if cell to the left from start is occupied
                        return true;
                    }
                }
                if (col == endCol && col < field[startRow].length - 1) { // if this is the last cell and it is placed with gap from the edge
                    if (field[startRow][col + 1] == 'O') { // // if cell to the right from end is occupied
                        return true;
                    }
                }
                if (startRow > 0 && field[startRow - 1][col] == '0') {  // if row is placed with gap from the top edge
                    return true;
                }
                if (startRow < field.length - 1 && field[startRow + 1][col] == 'O') {   // if row is placed with gap from the bottom edge
                    return true;
                }
            }
        } else {    // if vertical
            for (int row = startRow; row <= endRow; row++) {
                if (field[row][startCol] == 'O') {
                    return true;
                }
                if (row == startRow && row > 0) {
                    if (field[row - 1][startCol] == '0') {
                        return true;
                    }
                }
                if (row == endRow && row < field.length - 1) {
                    if (field[row + 1][startCol] == 'O') {
                        return true;
                    }
                }
                if (startCol > 1 && field[row][startCol - 1] == 'O') {
                    return true;
                }
                if (startCol < field[startRow].length - 1 && field[row][startCol + 1] == 'O') {
                    return true;
                }
            }
        }

        return false;
    }
}

