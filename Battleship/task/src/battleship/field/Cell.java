package battleship.field;

public enum Cell {
    EMPTY('~'),
    HIT('X'),
    MISS('M'),
    SHIP('O');

    private final char letter;

    Cell(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }
}
