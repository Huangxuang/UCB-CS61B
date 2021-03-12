package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    //private int size;
    private final int[][] board;
    private final int BLANK = 0;
    private final int[][] goal;

    public Board(int[][] tiles) {

        //this is not immutable
        //this.board = tiles; board = tiles.clone();
        //Any change to tiles will also change board
        //board = tiles.clone();
        board = new int[tiles.length][];
        for (int i = 0; i < tiles.length; i++) {
            board[i] = tiles[i].clone();
        }
        goal = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                goal[i][j] = i  * tiles.length + (j + 1);
            }
        }
        goal[tiles.length - 1][tiles.length - 1] = 0;

    }

    public int tileAt(int i, int j) {
        if (i >= size() || j >= size()) {
            throw new IndexOutOfBoundsException("out of Bound");
        }
        return board[i][j];
    }

    public int size() {
        return board.length;
    }

    /**
     *
     * Returns neighbors of this board.
     * SPOILERZ: This is the answer.From
     * http://joshh.ug/neighbors.html
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int res = 0;
        for(int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (board[i][j] != goal[i][j]) {
                    res++;
                }
            }
        }
        return res;
    }

    public int manhattan() {
        //need find out the position of
        int res = 0;
        for(int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                int valueInBoard = board[i][j];

                if (valueInBoard != 0) {
                    //calculate the x,y position of valueInBoard in goal
                    int xInGoal = (valueInBoard - 1) / size();
                    int yInGoal = (valueInBoard - 1) % size();
                    int temp = Math.abs(xInGoal - i) + Math.abs(yInGoal - j);
                    res += temp;
                }

            }
        }
        return res;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (this == null || this.getClass() != y.getClass()) {
            return false;
        }

        Board temp = (Board) y;
        for(int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (this.board[i][j] != temp.board[i][j]) {
                    return false;
                }

            }
        }
        return true;

    }


    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
