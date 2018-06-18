import java.util.LinkedList;
import java.util.Comparator;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private int moves;
    private Board initial;
    private boolean isSolvable;
    private LinkedList<Board> solution = new LinkedList<Board>();
    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        this.initial = initial;
        if(isSolvable())
        {
            isSolvable = true;
            MinPQ<Board> pq = new MinPQ<Board>();
            //insert initial board into PQ
            pq.insert(initial);
            //generate all neighbouring queues
            Board minBoard = initial;
            while (!minBoard.isGoal()) {
                for (Board i : minBoard.neighbors()) //insert neihboards in pq that arent already there
                {
                    for (Board j : pq) {
                        if (i != j)
                            pq.insert(i);
                    }
                }
                minBoard = pq.delMin(); //pick board with lowest priority#
                solution.addLast(minBoard);
                moves++;
            }
        }
        else {isSolvable = false;}
    }


    //comparators for MinPQ

    private static final Comparator<Board> BY_HAMMING = new ByHamming();
  //  private static final Comparator<Board> BY_MANHATTAN = new ByManhattan();
    private static class ByHamming implements Comparator<Board>
    {
        public int compare(Board x, Board y)
        {return x.hamming() - y.hamming();}
    }
 /*   private static class ByManhattan impelements Comparator<Board>
    {
        public int compare(Board x, Board y)
        {return x.manhattan - y.manhattan;}
    }
*/
    public boolean isSolvable()            // is the initial board solvable?
    {
        MinPQ<Board> pq = new MinPQ<Board>();
        MinPQ<Board> pqTwin = new MinPQ<Board>();
        //insert initial board into PQ
        pq.insert(initial);
        Board twin = initial.twin();
        pqTwin.insert(twin);
        //generate all neighbouring queues
        Board minBoard = initial;
        Board minBoardTwin = twin;
        while (!minBoard.isGoal() || !minBoardTwin.isGoal()) {
            for (Board i : minBoard.neighbors()) //insert neihboards in pq that arent already there
            {
                for (Board j : pq) {
                    if (i != j)
                        pq.insert(i);
                }
            }
            for (Board i : minBoardTwin.neighbors()) //insert neihboards in pq that arent already there
            {
                for (Board j : pqTwin) {
                    if (i != j)
                        pqTwin.insert(i);
                }
            }
            minBoard = pq.delMin(); //pick board with lowest priority
            minBoardTwin = pqTwin.delMin();
        }
        if (minBoard.isGoal())
            return true;
        return false;
    }
    public int moves()
    {
        if (isSolvable)
            return moves;
        return -1;
    }// min number of moves to solve initial board; -1 if unsolvable
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        if (isSolvable)
        {
            return solution;

        }
        return null;
    }
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

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