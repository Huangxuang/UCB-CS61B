package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solver {
    public int totalMove;
    public List<WorldState> solution = new ArrayList<>();

    //A nested Class to record all information, to be stored in PQ
    private class SearchNode implements Comparable<SearchNode>{
        private WorldState worldState;
        private int numberOfMoves;
        private SearchNode previous;

        public SearchNode(WorldState w, int n, SearchNode p) {
            worldState = w;
            numberOfMoves = n;
            previous = p;
        }

        // return negative if total move of "this" Node < "theOther" node
        @Override
        public int compareTo(SearchNode p) {
            int myTotalMove = this.numberOfMoves + this.worldState.estimatedDistanceToGoal();
            int theOtherMove = p.numberOfMoves + p.worldState.estimatedDistanceToGoal();
            return myTotalMove - theOtherMove;
        }
    }





    private MinPQ<SearchNode> fringe= new MinPQ<>();

    public Solver(WorldState initial) {

        SearchNode source = new SearchNode(initial,0,null);
        fringe.insert(source);

        //Remove the min Priority node fringeNode, if it is the goal, we are done
        //Otherwise, for each neighbor of fringeNode, create a new search node and
        //insert it into the priority queue in order
        while(!fringe.isEmpty()) {
            SearchNode fringeNode = fringe.delMin();

            //After finding the solution, update solution list and total move
            if (fringeNode.worldState.isGoal()) {

                totalMove = fringeNode.numberOfMoves;

                while(fringeNode != null) {
                    solution.add(fringeNode.worldState);
                    fringeNode = fringeNode.previous;
                }
                return;
            }
            String parentNodeWord = null;
            if(fringeNode.previous != null) {
                parentNodeWord = fringeNode.previous.worldState.toString();
            }

            for (WorldState ws:fringeNode.worldState.neighbors()) {

                //don't add parent Node
                if (!ws.toString().equals(parentNodeWord)){
                    //Make Node Comparable,let MinPQ fringe do the comparison
                    fringe.insert(new SearchNode(ws,fringeNode.numberOfMoves + 1, fringeNode));
                }
            }
        }

    }


    public int moves() {
        return totalMove;
    }

    public Iterable<WorldState> solution() {
        Collections.reverse(this.solution);
        return solution;
    }
}
