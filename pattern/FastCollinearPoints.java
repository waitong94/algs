import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints
{
    private ArrayList<LineSegment> sArray = new ArrayList<>();
    public FastCollinearPoints(Point[] points)      //finds all line segments containing 4 or more points
    {
        Point[] pArray = points.clone();

        for(int i = 0; i < pArray.length - 3; i++)
        {
            Arrays.sort(pArray); //so that biggest point is at bottom
            Arrays.sort(pArray, pArray[i].slopeOrder());
            for (int p = 0, first = 1, last = 2; last < pArray.length; last++)
            {
                while(last < pArray.length && Double.compare(pArray[p].slopeTo(pArray[first]), pArray[p].slopeTo(pArray[last])) == 0)
                {
                    last++;
                }
                if (last - first >= 3 && pArray[p].compareTo(pArray[first]) < 0)
                {
                    sArray.add(new LineSegment(pArray[p], pArray[last-1]));
                }
                first = last;
            }
        }


    }
    public int numberOfSegments()                   //the number of lineSegments
    {
        return sArray.size();
    }
    public LineSegment[] segments()                 // the line segments
    {
        return sArray.toArray(new LineSegment[sArray.size()]);
    }
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}