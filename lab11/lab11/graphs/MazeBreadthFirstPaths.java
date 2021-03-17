package lab11.graphs;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int source;
    private int target;
    private boolean targetFound;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        //Convert Source and Target to 1D;
        source = m.xyTo1D(sourceX, sourceY);
        target = m.xyTo1D(targetX, targetY);
        targetFound = false;
        distTo[source] = 0;
        edgeTo[source] = source;

    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> fringe = new PriorityQueue<>();
        fringe.add(source);
        marked[source] = true;
        announce();
        while(!fringe.isEmpty()) {
            int fringeNode = fringe.remove();
            if (fringeNode == target) {
                return;
            }
            for(int neighbor:maze.adj(fringeNode)) {
                if(!marked[neighbor]) {
                    fringe.add(neighbor);
                    marked[neighbor] = true;
                    distTo[neighbor] = distTo[fringeNode] + 1;
                    edgeTo[neighbor] = fringeNode;
                    announce();
                }

            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

