import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private  int size; //TODO define size from input
    private  Point[] points;
    private int nLines;
    private LineSegment[] lines;

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        this.points = points;
        this.size = points.length;
    }
    public  int numberOfSegments()        // the number of line segments
    {
        return nLines;
    }
    public LineSegment[] segments()                // the line segments
    {
        Point p;
        Point s;
        LineSegment[] lines = new LineSegment[size*2];
        StdOut.print(size);
        for (int i = 0; i < size - 3; i++)
        {
            for (int j = i+1; j < size - 2; j++)
            {
                for (int k = j+1; k < size - 1; k++)
                {
                    for (int l = k+1 ; l < size; l++)
                    {

                        if (isCollinear(points[i],points[j],points[k],points[l]))
                        {
                            StdOut.print(i);
                            StdOut.print(j);
                            StdOut.print(k);
                            StdOut.println(l);
                            p = findFirst(points[i],points[j],points[k],points[l]);
                            s = findLast(points[i],points[j],points[k],points[l]);

                            lines[nLines] = new LineSegment(p,s);
                            nLines++;
                            StdOut.println(nLines);
                        }
                    }
                }
            }
        }
        LineSegment[] ans = new LineSegment[nLines];
        for (int i = 0; i < nLines; i++)
            ans[i] = lines[i];
        StdOut.println("lines: "+nLines);
        return ans;
    }

    public boolean isCollinear(Point p, Point q, Point r, Point s)
    {
        if (p.slopeTo(q) == p.slopeTo(r))
            return p.slopeTo(q) == p.slopeTo(s);
        return false;
    }
    private Point findFirst(Point p, Point q, Point r, Point s)
    {
        Point first = p;
        if (first.compareTo(q) < 0)
            first = q;
        if (first.compareTo(r) < 0)
            first = r;
        if (first.compareTo(s) < 0)
            first = s;
        return first;
    }
    private Point findLast(Point p, Point q, Point r, Point s)
    {
        Point last = p;
        if (last.compareTo(q) > 0)
            last = q;
        if (last.compareTo(r) > 0)
            last = r;
        if (last.compareTo(s) > 0)
            last = s;
        return last;
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
            StdOut.println("Points printed");
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}