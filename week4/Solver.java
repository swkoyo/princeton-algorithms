package week4;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private Board[] path = null;
    private boolean solvable = false;

    private static class SearchNode implements Comparable<SearchNode> {
        public final Board board;
        public final SearchNode prev;
        public final int moves;
        public final int priority;

        public SearchNode(Board board, SearchNode prev) {
            this.board = board;
            this.prev = prev;
            if (prev != null) {
                moves = prev.moves + 1;
            } else {
                moves = 0;
            }
            priority = this.board.manhattan() + moves;
        }

        public int compareTo(SearchNode that) {
            return Integer.compare(priority, that.priority);
        }
    }

    public Solver(Board initial) {
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();

        pq.insert(new SearchNode(initial, null));
        pq.insert(new SearchNode(initial.twin(), null));

        while (!pq.isEmpty()) {
            SearchNode delNode = pq.delMin();

            if (delNode.board.isGoal()) {
                SearchNode curr = delNode;
                path = new Board[curr.moves + 1];
                for (int i = delNode.moves; i >= 0; i--) {
                    path[i] = curr.board;
                    curr = curr.prev;
                }
                if (path[0].equals(initial)) {
                    solvable = true;
                }
                break;
            }

            for (Board nbr: delNode.board.neighbors()) {
                if (delNode.moves < 1 || !nbr.equals(delNode.prev.board)) {
                    pq.insert(new SearchNode(nbr, delNode));
                }
            }
        }
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        if (!solvable) return -1;
        return path.length - 1;
    }

    public Iterable<Board> solution() {
        if (!solvable) return null;
        return Arrays.asList(path);
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}