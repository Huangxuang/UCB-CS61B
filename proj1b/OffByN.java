public class OffByN implements CharacterComparator {
    private int offBy;

    @Override
    public boolean equalChars(char x, char y) {
        return (x - y == offBy || y - x == offBy);
    }

    public OffByN(int N) {
        this.offBy = N;
    }
}
