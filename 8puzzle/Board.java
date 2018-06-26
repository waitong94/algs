import java.util.LinkedList;
import java.util.Comparator;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdArrayIO;

public class Board {
    private int[][] blocks;
    private int N;
    private int moves;
    private int rowLength;
    private int indexToGoalVal(int i, int j)
    {
        return i*N + j + 1;
    }
    private int valToRow(int val)
    {
        return (val - 1) / N;
    }
    private int valToCol(int val)
    {
        return (val - 1) % N;
    }
    public Board(int[][] blocks)           // construct a board from an n-by-n array of blocks
    {
        this.blocks = blocks;
        N = blocks.length;
    }
    // (where blocks[i][j] = block in row i, column j)
    public int dimension()                 // board dimension n
    {
        return N;
    }
    public int hamming()                   // number of blocks out of place
    {
        int count = 0;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if(blocks[i][j] != 0 && blocks[i][j] != indexToGoalVal(i,j))
                    count++;
            }
        }
        return count; //no of moves not included in this hamming priority
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        int distance = 0;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if(blocks[i][j] != 0)
                    distance = distance + Math.abs(valToRow(blocks[i][j]) - i) + Math.abs(valToCol(blocks[i][j]) - j);
            }
        }
        return distance;
    }
    public boolean isGoal()                // is this board the goal board?
    {
        for (int i = 0; i < blocks.length;i++)
        {
            for (int j = 0; j < blocks.length; j++)
            {
                if (blocks[i][j] == 0)
                {

                }else{
                    if (blocks[i][j] != indexToGoalVal(i,j))
                        return false;
                }
            }
        }
        return true;
    }
    private int[][] deepCopy(int[][] array)
    {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                copy[i][j] = array[i][j];
            }
        }
        return copy;
    }
    private int[][] arraySwap(int iOne, int jOne, int iTwo, int jTwo) //takes int  array coordinates and returns swapped array
    {
        int[][] copy = new int[N][N];
        copy = deepCopy(blocks);
        int temp = copy[iOne][jOne];
        copy[iOne][jOne] = copy[iTwo][jTwo];
        copy[iTwo][jTwo] = temp;
        return copy;
    }
    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
        int temp;
        for (int i = 0; i < blocks.length;i++)
        {
            for (int j = 0; j < blocks.length - 1; j++)
            {
                if (blocks[i][j] != 0 && blocks[i][j+1] != 0 )
                    return new Board(arraySwap(i, j, i, j+1));
            }
        }
        return null;
    }
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Board y = (Board) other;
        for (int i = 0; i < blocks.length;i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[i][j] != y.blocks[i][j])
                    return false;
            }
        }
        return this.dimension() == y.dimension();
    }
    //TODO FIX ITERABLE: issue - code doesnt know that new object has been genrated
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        LinkedList<Board> neighbor = new LinkedList<Board>();
        //locate index of 0
        int iZero = 0;
        int jZero = 0;
        int iMinus = 0;
        int jMinus = 0;
        int iPlus = 0;
        int jPlus = 0;
        for (int i = 0; i < blocks.length;i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[i][j] == 0) {
                    iZero = i;
                    jZero = j;
                }
            }
        }
        //calculate neighbour arrays
        iMinus = iZero - 1;
        iPlus = iZero + 1;
        jMinus = jZero - 1;
        jPlus = jZero + 1;

        //generate arrays
        if (iMinus >= 0) {
            int[][] arrayOne = arraySwap(iZero, jZero, iMinus, jZero);
            neighbor.addLast(new Board(arrayOne));
        }
        if (jMinus >= 0) {
            int[][] arrayTwo = arraySwap(iZero, jZero, iZero, jMinus);
            neighbor.addLast(new Board(arrayTwo));
        }
        if (iPlus < N) {
            int[][] arrayThree = arraySwap(iZero, jZero, iPlus, jZero);
            neighbor.addLast(new Board(arrayThree));
        }
        if (jPlus < N) {
            int[][] arrayFour = arraySwap(iZero, jZero, iZero, jPlus);
            neighbor.addLast(new Board(arrayFour));
        }
        return neighbor;
    }

    public String toString()               // string representation of this board (in the output format specified below)
    {
        StringBuilder str = new StringBuilder();
        str.append(dimension() + "\n");
        for (int i = 0; i < blocks.length;i++) {
            for (int j = 0; j < blocks.length; j++) {
                str.append(String.format("%2d ", blocks[i][j]));
            }
            str.append("\n");
        }
        return str.toString();
    }
    public static void main(String[] args) // unit tests (not graded){
    {
        // read file top create board
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] array = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = in.readInt();
            }
        }
        Board board = new Board(array);
        StdOut.println(board);
        //test twin
        Board testTwin = board.twin();
        StdOut.println("twin: " + testTwin);
        //test hamming
        int testHamming = board.hamming();
        StdOut.println("hamming: " + testHamming);
        //test manhattan
        int testManhattan = board.manhattan();
        StdOut.println("manhattan: " + testManhattan);
        StdOut.println(board);
        //test neighbour
        for (Board i : board.neighbors())
        {
            StdOut.println(i.toString());
        }

    }
}