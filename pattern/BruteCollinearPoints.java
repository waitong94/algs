public class BruteCollinearPoints {
    private  int size; //TODO define size from input
    private  Point[] points;
    private int nLines;
    private LineSegment[] lines;

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        this.points = points;
    }
    public  int numberOfSegments()        // the number of line segments
    {
        return nLines;
    }
    public LineSegment[] segments()                // the line segments
    {
        Point p;
        Point s;
        LineSegment[] lines = new LineSegment[size/2];

        for (int i = 0; i < size - 3; i++)
        {
            for (int j = i+1; j < size - 2; j++)
            {
                for (int k = j+1; k < size - 1; j++)
                {
                    for (int l = k+1 ; l < size; k++)
                    {
                        if (isCollinear(points[i],points[j],points[k],points[l]))
                        {
                            p = findFirst(points[i],points[j],points[k],points[l]);
                            s = findLast(points[i],points[j],points[k],points[l]);
                            lines[nLines] = new LineSegment(p,s);
                            nLines++;
                        }
                    }
                }
            }
        }
        LineSegment[] ans = new LineSegment[nLines];
        for (int i = 0; i < nLines; i++)
            ans[i] = lines[i];
        return ans;
    }

    private boolean isCollinear(Point p, Point q, Point r, Point s)
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
}