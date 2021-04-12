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

    public void placeShip(String[] coordinates, Ships ship) {
        String[] startCoordinates = convertCoordinate(coordinates[0]);
        String[] endCoordinates = convertCoordinate(coordinates[1]);
        int startRow = convertRow(startCoordinates[0]);
        int startCol = convertColumn(startCoordinates[1]);
        int endRow = convertRow(endCoordinates[0]);
        int endCol = convertColumn(endCoordinates[1]);
        fillCells(startRow, startCol, endRow, endCol);
    }

    private String[] convertCoordinate(String coordinate) {
        String[] cell = new String[2];
        cell[0] = coordinate.substring(0, 1);
        cell[1] = coordinate.substring(1);
        return cell;
    }

    private int convertRow(String row) {
        String rows = "ABCDEFGHIJ";
        return rows.indexOf(row);
    }

    private int convertColumn(String col) {
        return Integer.parseInt(col);
    }

//    private void validateCoordinates(int startRow, int startCol, int endRow, int endCol) {
//
//    }

    private void fillCells(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow) {
            if (startCol < endCol) {
                for (int col = startCol; col <= endCol; col++) {
                    field[startRow][col] = 'O';
                }
            } else {
                for (int col = startCol; col >= endCol; col--) {
                    field[startRow][col] = 'O';
                }
            }
        } else {
            if (startRow < endRow) {
                for (int row = startRow; row <= endRow; row++) {
                    field[row][startCol] = 'O';
                }
            } else {
                for (int row = startRow; row >= endRow; row--) {
                    field[row][startCol] = 'O';
                }
            }
        }
    }
}

