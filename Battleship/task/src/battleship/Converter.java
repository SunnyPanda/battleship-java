package battleship;

public class Converter {

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

    public int[] convertCoordinate(String coordinate) {
        int[] cell = new int[2];
        cell[0] = convertRow(coordinate.substring(0, 1));
        cell[1] = convertColumn(coordinate.substring(1));
        return cell;
    }

    private int convertRow(String row) {
        String rows = "ABCDEFGHIJ";
        return rows.indexOf(row);
    }

    private int convertColumn(String col) {
        return Integer.parseInt(col);
    }
}
