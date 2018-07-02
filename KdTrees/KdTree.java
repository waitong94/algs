public class KdTree {
    private static final boolean RED = true;
    private static final boolean BLUE = false;

    private Node root;
    private class Node {
        private Point2D point;
        private Node left, right;
        private int size;
        private boolean color;

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
        if(h == null) return new Node(p, RED, 1);
        int cmp = h.point.x() - p.x();
        if (cmp < 0) h.left = put(h.left, p);
        else if (cmp > 0) h.right = put(h.right, p);
        else h.point = p;

        //change color
        if (isRed(h))
    }
    public              void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    public           boolean contains(Point2D p)            // does the set contain point p?
    public              void draw()                         // draw all points to standard draw
    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)
    public           Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args)                  // unit testing of the methods (optional)
}