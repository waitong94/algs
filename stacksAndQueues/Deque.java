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
        private Node prev;
    }
    public Deque()                           // construct an empty deque
    {
        first = null;
        last = null;
        n = 0;
    //    assert check();
    }
    public boolean isEmpty()                 // is the deque empty?
    {return first == null;} //TODO change to tru

    public int size()                        // return the number of items on the deque
    {
        return n;
    }
    public void addFirst(Item item)          // add the item to the front
    {
        Node oldFirst=first; //save oldFirst
        first = new Node();//create new first
        first.item = item;
        first.next = null;
        n++;
        if(n <= 1) last = first;//TODO: LAST=FIRST IF N=
        else { oldFirst.next = first; first.prev = oldFirst;}
    }
    public void addLast(Item item)           // add the item to the end
    {
        Node oldLast=last;
        last = new Node();
        last.item=item;
        n++;
        if (isEmpty()) first=last;
        else {last.next=oldLast;oldLast.prev=last;}

    }

    public Item removeFirst()                // remove and return the item from the front
    {
        if(isEmpty()) throw new  NoSuchElementException();
        Item item=first.item;
        first = first.prev;
        n--;
        if(isEmpty()) last = null;
        return item;
    }
    public Item removeLast()                 // remove and return the item from the end
    {
        if(isEmpty()) throw new NoSuchElementException();
        Item item = last.item;//store last
        last=last.next;//oldLast=last
        n--;//count
        if(n == 0) first = null;//TODO if last thing is removed, then first =null
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
            current = current.prev;
            return item;
        }
    }


    public static void main(String[] args)   // unit testing (optional)
    {
        Deque<String> deque = new Deque<String>();
        Deque<Integer> deqInt = new Deque<Integer>();
        TestDeque test = new TestDeque();
        test.testIsEmpty(deque);
        test.testAddFirstRemoveLastAscending(deqInt, 100);
        test.testAddLastRemoveFirstAscending(deqInt, 100);
        test.testAddLastRemoveLastDescending(deqInt, 100);
        test.testAddFirstRemoveFirstDescending(deqInt, 100);
        //        while(!StdIn.isEmpty())
//        {
//            String item = StdIn.readString();
//         //   if (!iem.equals())
//        }

    }
}
