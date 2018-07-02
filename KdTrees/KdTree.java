import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
import java.util.LinkedList;

public class KdTree {
    private static final boolean RED = true;
    private static final boolean BLUE = false;

    private LinkedList<Point2D> queue  = new LinkedList<Point2D>();

    private Node root;
    private class Node {
        private Point2D point;
        private Node left, right, parent;
        private int size;
        private boolean color;
        private RectHV rect;

        public Node (Point2D point, boolean color, int size)
        {
            this.point = point;
            this.size = size;
            this.color = color;
        }
    }
    public         KdTree()                               // construct an empty set of points
    {

    }

    /* Node methods*/
    private boolean isRed(Node x)
    {
        if (x == null) return false;
        return x.color == RED;
    }
    private int size(Node x)
    {
        if (x == null) return 0;
        return x.size;
    }


    public           boolean isEmpty()                      // is the set empty?
    {
        return root == null;
    }
    public               int size()                         // number of points in the set
    {
        return size(root);
    }

    private Node put(Node h, Point2D p)
    {
        Node x = new Node(p, RED, 1);
        x.parent = h;
        x.color = !isRed(x.parent);
        if(h == null) return x;
        double cmp = h.point.x() - p.x();
        if (cmp < 0) {
            h.left = put(h.left, p);
        }
        else if (cmp > 0)
        {
            h.right = put(h.right, p);
        }
        else h.point = p;
        h.size = 1 + size(h.left) + size(h.right);

        return h;
    }
    public              void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if (p == null) throw new IllegalArgumentException("first point is null");
        root = put(root, p);
        queue.add(p);
    }

    public           boolean contains(Point2D p)            // does the set contain point p?
    {
        if (p == null) throw new IllegalArgumentException("first point is null");
        return get(root, p) != null;
    }

    private Point2D get(Node x, Point2D p)
    {
        while(x != null)
        {
            double cmp = x.point.x() - p.x();
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.point;
        }
        return null;
    }

    public              void draw()                         // draw all points to standard draw
    {
        for( Point2D p : points())
        {
            StdDraw.point(p.x(), p.y());
        }
        //todo draw lines
    }

    private Iterable<Point2D> points()
    {
        return queue;
    }
//    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)
//    {
//
//    }
//    public           Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args)                  // unit testing of the methods (optional)
    {
        Point2D point1 = new Point2D(0.5,0.5);
        Point2D point2 = new Point2D(0.4,0.4);
        Point2D point3 = new Point2D(0.6,0.6);
        Point2D point4 = new Point2D(0.2,0.5);
        KdTree testTree = new KdTree();
        testTree.insert(point1);
        testTree.insert(point2);
        testTree.insert(point3);
        StdOut.println(testTree.contains(point1));
        StdOut.println(testTree.contains(point2));
        StdOut.println(testTree.contains(point3));
        StdOut.println(testTree.contains(point4));
    }
}