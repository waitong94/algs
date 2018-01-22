import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first,last;
    private int n;
    private class Node
    {
        private Item item;
        private Node next;
    }
    public Deque()                           // construct an empty deque
    {
        first = null;
        n = 0;
    //    assert check();
    }
    public boolean isEmpty()                 // is the deque empty?
    {return first==null;}
    public int size()                        // return the number of items on the deque
    {
        return n;
    }
    public void addFirst(Item item)          // add the item to the front
    {
        Node oldFirst=first; //save oldFirst
        first = new Node();//create new first
        first.item = item;
        first.next =oldFirst;        //assign new node as new
        n++;
        //TODO: LAST=FIRST IF N=1?
    }
    public void addLast(Item item)           // add the item to the end
    {
        Node oldLast=last;
        last = new Node();
        last.item=item;
        n++;
        if (isEmpty()) first=last;
        else last.next=oldLast;

    }

    public Item removeFirst()                // remove and return the item from the front
    {
        if(isEmpty()) throw new IllegalArgumentException();
        Item item=first.item;
        first = first.next;
        n--;
        if(isEmpty()) last = null;
        return item;
    }
    public Item removeLast()                 // remove and return the item from the end
    {
        if(isEmpty()) throw new IllegalArgumentException();
        Item item = last.item;//store last
        last=last.next;//oldLast=last
        n--;//count
        if(isEmpty()) first = null;//TODO if last thing is removed, then first =null
        return item;
    }
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new ListIterator(); //TODO this is for LIFO
    }

    private class ListIterator implements Iterator<Item> //PLACEHOLDER LIFO; TODO EDIT AS NEEDED
    {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args)   // unit testing (optional)
    {
        Deque<String> deque = new Deque<String>();
        while(!StdIn.isEmpty())
        {
            String item = StdIn.readString();
         //   if (!iem.equals())
        }

    }
}
