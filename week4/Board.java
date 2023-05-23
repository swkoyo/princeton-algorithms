import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private final int[][] board;
    private final int N;

    public Board(int[][] tiles) {
        N = tiles.length;
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = tiles[i][j];
            }
        }
    }

    public String toString() {
        StringBuilder s= new StringBuilder();
        s.append(N).append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public int dimension() {
        return N;
    }

    public int hamming() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0 && board[i][j] != i * N + j + 1) {
                    result++;
                }
            }
        }
        return result;
    }

    public int manhattan() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0 && board[i][j] != i * N + j + 1) {
                    int v = board[i][j] - 1;
                    int x = v / N;
                    int y = v - x * N;
                    result += Math.abs(i - x) + Math.abs(j - y);
                }
            }
        }
        return result;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }

        if ((y == null) || (y.getClass() != this.getClass())) {
            return false;
        }

        Board b = (Board) y;
        if (dimension() != b.dimension()) {
            return false;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != b.board[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public Iterable<Board> neighbors() {
        List<Board> nbrs = new LinkedList<Board>();
        int[][] nbrsBoard = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nbrsBoard[i][j] = board[i][j];
            }
        }

        boolean found = false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    if (i > 0) {
                        nbrsBoard[i - 1][j] = board[i][j];
                        nbrsBoard[i][j] = board[i - 1][j];
                        nbrs.add(new Board(nbrsBoard));
                        nbrsBoard[i - 1][j] = board[i - 1][j];
                        nbrsBoard[i][j] = board[i][j];
                    }

                    if (i < N - 1) {
                        nbrsBoard[i + 1][j] = board[i][j];
                        nbrsBoard[i][j] = board[i + 1][j];
                        nbrs.add(new Board(nbrsBoard));
                        nbrsBoard[i + 1][j] = board[i + 1][j];
                        nbrsBoard[i][j] = board[i][j];
                    }

                    if (j > 0) {
                        nbrsBoard[i][j - 1] = board[i][j];
                        nbrsBoard[i][j] = board[i][j - 1];
                        nbrs.add(new Board(nbrsBoard));
                        nbrsBoard[i][j - 1] = board[i][j - 1];
                        nbrsBoard[i][j] = board[i][j];
                    }

                    if (j < N - 1) {
                        nbrsBoard[i][j + 1] = board[i][j];
                        nbrsBoard[i][j] = board[i][j + 1];
                        nbrs.add(new Board(nbrsBoard));
                        nbrsBoard[i][j + 1] = board[i][j + 1];
                        nbrsBoard[i][j] = board[i][j];
                    }

                    found = true;
                    break;
                }
            }

            if (found) {
                break;
            }
        }

        return nbrs;
    }

    public Board twin() {
        int[][] twinBoard = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                twinBoard[i][j] = board[i][j];
            }
        }

        if (board[0][0] == 0 || board[1][0] == 0) {
            twinBoard[0][1] = board[0][2];
            twinBoard[0][2] = board[0][1];
        } else {
            twinBoard[0][0] = board[0][1];
            twinBoard[0][1] = board[0][0];
        }

        return new Board(twinBoard);
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = StdIn.readInt();
            }
        }
        StdOut.println("-----------");
        Board board = new Board(tiles);
        StdOut.println("Board: ");
        StdOut.println(board);
        StdOut.println("Dimension: ");
        StdOut.println(board.dimension());
        StdOut.println("Hamming: ");
        StdOut.println(board.hamming());
        StdOut.println("Is Goal: ");
        StdOut.println(board.isGoal());
        StdOut.println("Manhattan: ");
        StdOut.println(board.manhattan());
        StdOut.println("Neighbors: ");
        for (Board nbr : board.neighbors()) {
            StdOut.println(nbr);
        }
        StdOut.println("Twin: ");
        StdOut.println(board.twin());
    }
}
