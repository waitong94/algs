import edu.princeton.cs.algs4.StdOut;
public class TestRandomizedQueue {
    //private RandomizedQueue<String> q = new RandomizedQueue<String>();
    public TestRandomizedQueue(){

    }
    public void testSize(RandomizedQueue<String> q){
        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("C");
        assert q.size() == 3: "size = " + q.size();
        q.dequeue();
        assert q.size() == 2: "size = " + q.size();
    }
    public void testIterator(RandomizedQueue<String> q)
    {
        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("C");
        int count = 0;
        for (String s : q) {
            StdOut.println(s);
            count ++ ;

        }
        assert q.size() == count: "size = " + q.size() + " count = " + count;
    }
//test isEmpty
}