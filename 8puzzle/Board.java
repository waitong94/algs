public class Board {
    private int[][] blocks;
    private int N;
    private int moves;
    private int rowLength;
    private int indexToGoalVal(int i, int j)
    {
        i*N + j + 1;
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
        int distance;
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
    private int[][] arraySwap(int iOne, int jOne, int iTwo, int jTwo) //takes int  array coordinates and returns swapped array
    {
        int[][] copy = blocks;
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
                {
                    return new Board(arraySwap(i, j, i, j+1));
                }
            }
        }
    }

    public boolean equals(Object y)        // does this board equal y?
    {
        for (int i = 0; i < blocks.length;i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[i][j] != y.blocks[i][j])
                    return false
            }
        }
        return true;
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        LinkedList<Board> neighbor = new LinkedList<Board>;
        //locate index of 0
        int iZero;
        int jZero;
        int iMinus;
        int[][] copy;
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
        jMinus = jMinus + 1;
        jPlus = jPlus + 1;

        //generate arrays
        if (iMinus >= 0)
            neighbor.add(Board(arraySwap(iZero, jZero, iMinus, jZero)));
        if (jMinus >= 0)
            neighbor.add(Board(arraySwap(iZero, jZero, iZero, jMinus)));
        if (iPlus < N)
            neighbor.add(Board(arraySwap(iZero, jZero, iPlus, jZero)));
        if (jPlus >= 0)
            neighbor.add(Board(arraySwap(iZero, jZero, iZero, jPlus)));

        return neighbor;
    }

    public String toString()               // string representation of this board (in the output format specified below)
    {
        StringBuilder str = new StringBuilder();
        str.append(dimension() + "\n");
        for (int i = 0; i < blocks.length;i++) {
            for (int j = 0; j < blocks.length; j++) {
                str.append(String.format("%2d ", blocks[i][j]))
            }
            str.append("\n");
        }
        return str.toString();
    }
    public static void main(String[] args) // unit tests (not graded){
    {

    }
}