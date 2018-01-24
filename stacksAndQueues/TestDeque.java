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
    }
    public static void main(String[] args){

    }
}