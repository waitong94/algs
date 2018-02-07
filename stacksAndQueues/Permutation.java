import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args){
        int k = Integer.parseInt(args[0]);      // input file
        RandomizedQueue<String> queue= new RandomizedQueue<String>();

        while(!StdIn.isEmpty())
        {
            String s = StdIn.readString();
          //  if (!s.equals("null"))
            queue.enqueue(s);//reads sequence of strings from standard input
        }
        for (int i =0; i<k; i++)//print exactly k of them at random
            StdOut.println(queue.dequeue());
    }
}
