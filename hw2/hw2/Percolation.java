package hw2;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int[][] position;
    int size;
    int numberOfOpen = 0;
    WeightedQuickUnionUF uf;
    int topNode;
    int bottomNode;
    boolean percolation;
    //StdRandom random = new StdRandom();

    //Create N-by-N grid, with all sites initialize to blocked
    //0 means blocked, 1 means open
    public Percolation(int N) {
        if (N < 1) {
            throw new IllegalArgumentException("Size must be larger than 0");
        }
        size = N;
        position = new int[N][N];
        //Add another two node for fast check full or percolation
        uf = new WeightedQuickUnionUF(N * N + 2);
        topNode = N * N;
        bottomNode = topNode + 1;
        percolation = false;
    }

    //convert (x,y) position to an int number,
    private int xyTo1D(int x, int y) {

        return x * size  + y;
    }
    //Method to check if row and col are out of Bounds
    private void checkOutOfRange(int row, int col) {
        if ((row > size - 1) || row < 0) {
            throw new IndexOutOfBoundsException("Row out of Bound");
        }
        if (col > size - 1 || col < 0) {
            throw new IndexOutOfBoundsException("col out of Bound!");
        }
    }

    //open(1) the site of row if it is not open
    public void open(int row, int col) {
        checkOutOfRange(row, col);
        if (!isOpen(row, col)) {
            position[row][col] = 1;
            numberOfOpen += 1;
            //check if current site is  is connected with previous open site
            updateUf(row, col);
            //System.out.println("numberOfOpen = " + numberOfOpen);
        }

    }

    //update uf is current opening site is connected with previous open site
    private void updateUf(int row, int col) {
        int currentPosition = xyTo1D(row, col);
        // union the sites on the first row with uf N*N+1 item
        if (row == 0) {
            uf.union(currentPosition, topNode);
        }
        // union the sites on the last row with uf N*N+2 item
        if (row == size - 1) {
            uf.union(currentPosition, bottomNode);
        }

        if (row + 1 < size && position[row + 1][col] == 1) {
            uf.union(currentPosition, currentPosition + size);
        }

        if (row - 1 >= 0 && position[row - 1][col] == 1) {
            uf.union(currentPosition, currentPosition - size);
            // System.out.println("unit" + currentPosition + "and" + (currentPosition - size));
        }

        if (col + 1 < size && position[row][col + 1] == 1) {
            uf.union(currentPosition, currentPosition + 1);
        }

        if (col - 1 >= 0 && position[row][col - 1] == 1) {
            uf.union(currentPosition, currentPosition - 1);
        }
    }

    public int numberOfOpenSites() {
        return numberOfOpen;
    }

    //return true if the site is open(1)
    public boolean isOpen(int row, int col) {
        checkOutOfRange(row, col);
        return (position[row][col] == 1);
    }
    /** 1: check if the position is open
     * 2:check if the position is at the first row
     * 3: check if the position is connected to other full position
     * */
    public boolean isFull(int row, int col) {
        checkOutOfRange(row, col);

        if (position[row][col] == 0) {
            return false;
        } else if (row == 0) {
            return true;
        }
        int currentPosition = xyTo1D(row, col);
        // check if current Position is connected with top site
        return uf.connected(currentPosition, topNode);
    }

    public boolean percolates() {

        return uf.connected(topNode, bottomNode);
    }
    public static void main(String[] args) {
        Percolation per = new Percolation(-10);
        per.open(-1, 1);
        per.open(1, 1);
        System.out.println("unit ");
        System.out.println(per.isFull(1, 1));
    }

}
