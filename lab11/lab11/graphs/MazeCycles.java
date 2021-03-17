package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int[] previousEdge;
    private int startPoint;
    //private boolean circleFound;
    private Maze maze;
    private int endPoint;


    public MazeCycles(Maze m) {
        super(m);
        previousEdge = distTo.clone();
        maze = m;
        //Choose 0 to be the starting point
        startPoint = 0;
        //circleFound = false;
        distTo[startPoint] = 0;
        edgeTo[startPoint] = startPoint;
    }
    /**Find a circle from source to every other reachable vertex,
     * visiting each node at most once, dfsFindCircle(V):
     * 1:mark vertex V and call announce to draw the vertex on the screen
     * 2:For each vertex w adjacent to V:
     *      if ( w == marked && previousEdge[w] != v) {
     *           call circleFoundHelper method;
     *           return
     *       }
     *       if (w != marked) {
     *           previousDge[w] = V;
     *           dfs(w)
     *           to save termination speed:
     *           if ( w == marked && previousEdge[w] != v) {
     *               return;
     *           }
     *       }
     */
    private void dfsFindCircle(int v, boolean[] circleFound) {
        /** Need to really think about this if statement, when need this
         * */

        if (circleFound[0]) {
            return;
        }
        marked[v] = true;
        announce();
        for(int w : maze.adj(v)) {
            if (marked[w] && previousEdge[v] != w && previousEdge[w] != Integer.MAX_VALUE) {
                previousEdge[w] = v;
                circleFoundHelper(v);
                circleFound[0] = true;
                return;
            }
            if (!marked[w]) {
                previousEdge[w] = v;
                distTo[w] = distTo[v] + 1;
                dfsFindCircle(w,circleFound);
//                if (marked[w] && previousEdge[w] != v) {
//                    return;
//                }
                ;
            }
            endPoint = w + 1;
        }

    }
    /**This's the helper method to call when circle is found
     *    collect previousEdge of w and past to edgeTo[],
     *    call announce and draw them
     * */
    private void circleFoundHelper(int circleStartPoint) {
        int index = circleStartPoint;
        while(previousEdge[index] != circleStartPoint) {
            edgeTo[index] = previousEdge[index];
            announce();
            index = previousEdge[index];
        }
        edgeTo[index] = circleStartPoint;
        announce();
    }

    @Override
    public void solve() {
//        dfsFindCircle(startPoint);
        boolean[] circleFound = new boolean[] {false};
        while(!circleFound[0]) {
            dfsFindCircle(startPoint, circleFound);
            //reinitialize the arrays
            for (int i = startPoint; i < marked.length; i++ ) {
                if (!marked[i]) {
                    startPoint = i;
                    break;
                }
            }

        }

    }

    // Helper methods go here
}

