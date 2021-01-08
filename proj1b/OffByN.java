public class OffByN implements CharacterComparator {
    int offBy;

    @Override
    public boolean equalChars(char x, char y) {
        return (x - y == offBy || y - x == offBy);
    }

    OffByN(int N) {
        this.offBy = N;
    }
}
