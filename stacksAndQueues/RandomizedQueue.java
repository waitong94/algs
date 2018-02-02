import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
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
            temp[i] = q[i]
        }
        q = temp;
    }
    public void enqueue(Item item)           // add the item
    {
        if (n == q.length) resize(2*q.length);
        q[n++] = item;
    }
    public Item dequeue()                    // remove and return a random item
    public Item sample()                     // return a random item (but do not remove it)
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    public static void main(String[] args)   // unit testing (optional)
}