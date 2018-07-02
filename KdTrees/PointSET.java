import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.LinkedList;

public class PointSET {
    private SET<Point2D> set;
    public         PointSET()                               // construct an empty set of points
    {
        set = new SET<Point2D>();
    }
    public           boolean isEmpty()                      // is the set empty?
    {
        return set.isEmpty();
    }
    public               int size()                         // number of points in the set
    {
        return set.size();
    }
    public              void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        set.add(p);
    }
    public           boolean contains(Point2D p)            // does the set contain point p?
    {
        return set.contains(p);
    }
    public              void draw()                         // draw all points to standard draw
    {
        for(Point2D p : set)
            StdDraw.point( p.x() , p.y() );
    }
    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)
    {
        //may need to divide space into M by M squares
        LinkedList<Point2D> rng= new LinkedList<Point2D>();
        for(Point2D p : set)
        {
            if(rect.contains(p))
                rng.add(p);
        }
        return rng;
    }
    public           Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
    {
        if (isEmpty())
        {
            return null;
        }
        Point2D nearP = new Point2D(0.0,0.0);
        double oldDist = 1.0;
        double newDist = 0.0;
        for (Point2D that : set)
        {
            if(!p.equals(that))
            {
                newDist = p.distanceSquaredTo(that);
                if (newDist < oldDist)
                {
                    oldDist = newDist;
                    nearP = that;
                }
            }
        }
        return nearP;
    }

    public static void main(String[] args)                  // unit testing of the methods (optional)
    {

    }
}