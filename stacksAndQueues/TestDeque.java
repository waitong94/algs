public class TestDeque{
    private Deque<String> deque = new Deque<String>();
    //test isEmpty
    public TestDeque(){

    }
    //testing isEmpty returns
    public void testIsEmpty(Deque<String> deque)
    {
        if (deque.size() == 0) assert deque.isEmpty() : "Size: " + deque.size() +", isEmpty: "+ deque.isEmpty();
    }

    public void testAddFirstRemoveLastAscending(Deque<Integer> deque, int n)
    {
        for (int i = 1; i < n+1; i++)
        {
            deque.addFirst(i);
        }
        for (int i = 1; i < n+1; i++)
        {
            int j = deque.removeLast();
            assert i == j:"i = "+ i + " , j = " + j;

        }
        assert deque.isEmpty(): n;
    }

    public void testAddLastRemoveFirstAscending(Deque<Integer> deque, int n)
    {
        for (int i = 1; i < n+1; i++)
        {
            deque.addLast(i);
        }
        for (int i = 1; i < n+1; i++)
        {
            int j = deque.removeFirst();
            assert i == j:"i = "+ i + " , j = " + j;
        }
        assert deque.isEmpty(): n;
    }

    public void testAddLastRemoveLastDescending(Deque<Integer> deque, int n)
    {
        for (int i = n; i > 0; i--)
        {
            deque.addLast(i);
        }
        for (int i = 1; i < n+1; i++)
        {
            int j = deque.removeLast();
            assert i == j:"i = "+ i + " , j = " + j;
        }
        assert deque.isEmpty(): n;

    }

    public void testAddFirstRemoveFirstDescending(Deque<Integer> deque, int n)
    {
        for (int i = n; i > 0; i--)
        {
            deque.addFirst(i);
        }
        for (int i = 1; i < n+1; i++)
        {
            int j = deque.removeFirst();
            assert i == j:"i = "+ i + " , j = " + j;
        }
        assert deque.isEmpty(): n;

    }

    public static void main(String[] args){

    }
}