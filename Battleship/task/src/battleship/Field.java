package battleship;

public class Field {
    private final char[][] field;
    private final Converter converter = new Converter();
    private final Validator validator = new Validator();

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
        return converter.convertCoordinates(coordinates);
    }

    public void validateCoordinates(int[] convertedCoordinates, Ships ship) throws Exception {
        validator.validateCoordinates(convertedCoordinates, ship, field);
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
}

