import java.util.Arrays;

public class UnionFind {

    // TODO - Add instance variables?
    int vertex[];
    int size = 1;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
      vertex  = new int[n];
      Arrays.fill(vertex,-1);

    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex > this.vertex.length - 1 || vertex < 0  ){
            throw new IndexOutOfBoundsException("Vertex Out Of Boundary");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
       if (vertex[v1] < 0){
           return -1 * vertex[v1];
       }
       return sizeOf(vertex[v1]);
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {

        return vertex[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return (find(v1) == find(v2));
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        if (sizeOf(v1) <= sizeOf(v2)){
            //update the size of v2's root
            vertex[find(v2)] -= sizeOf(v1);
            //connect the root of v1 to the root of v2
            vertex[find(v1)] = find(v2);

        } else {
            //update the size of v2's root
            vertex[find(v1)] -= sizeOf(v2);
            //connect the root of v2 to the root of v1
            vertex[find(v2)] = find(v1);

        }

    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        int currentNode = vertex;
        int parentNode = this.vertex[currentNode];
        while(parentNode >= 0){
            currentNode = parentNode;
            parentNode = this.vertex[currentNode];
        }

        //For Path-compression: connect every signal node to it's root
        if (this.vertex[vertex] >= 0){
            this.vertex[vertex] = currentNode;
        }
        return currentNode;
    }

    public static void main(String args[]){
        UnionFind test = new UnionFind(10);
        int x1 = test.sizeOf(9);
        test.union(1,3);
        test.union(2,4);
        test.union(1,4);
        //test.union(3,7);
        test.find(3);
        test.find(1);
        test.union(0,9);
        test.union(5,6);
        test.union(0,5);
        test.union(0,1);
        test.find(0);




    }

}
