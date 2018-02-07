import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.ResizingArrayBag; //TODO remove
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;                        //array of items
    private int n;                           //number of elements on stack
    public RandomizedQueue()                 // construct an empty randomized queue
    {
        q = (Item[]) new Object[2];
        n = 0;
    }
    public boolean isEmpty()                 // is the randomized queue empty?
    {
        return n == 0;
    }
    public int size()                        // return the number of items on the randomized queue
    {
        return n;
    }
    private void resize(int capacity)
    {
        Item[] temp = (Item[]) new Object [capacity];
        for (int i = 0; i < n; i++)
        {
            temp[i] = q[i];
        }
        q = temp;
    }
    public void enqueue(Item item)           // add the item
    {
        if (n == q.length) resize(2*q.length);
        q[n++] = item;
    }
    public Item dequeue()                    // remove and return a random item
    {
        shuffle(n-1 , StdRandom.uniform(n)); //todo shffle from index 0 to n
        Item item = q[n-1];
        q[n-1]=null;
        n--;
        if (n > 0 && n == q.length/4)
            resize(q.length/2);
        return item;
    }
    public Item sample()                     // return a random item (but do not remove it)
    {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        shuffle(n-1 , StdRandom.uniform(n));
        return q[n-1];
    }

    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>
    {
        private int i = n-1;
        public boolean hasNext(){return i>=0;}
        public Item next()
        {
           // StdRandom.shuffle(q); //todo shuffle without null -i.e. from 0 to n
            if (!hasNext()) throw new NoSuchElementException();
           //todo random ORDER
            shuffle(i , StdRandom.uniform(i+1));
            return q[i--];
        }
    }
    private void shuffle(int x , int y) //replaces first array element with random element [0,n]
    {
        Item temp = q[x];
        q[x] = q[y];
        q[y] = temp;
    }
    public static void main(String[] args)   // unit testing (optional)
    {
        TestRandomizedQueue test = new TestRandomizedQueue();
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        test.testSize(q);
        RandomizedQueue<String> a = new RandomizedQueue<String>();
        test.testIterator(a);
    }
}