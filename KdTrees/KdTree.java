import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
import java.util.LinkedList;
import edu.princeton.cs.algs4.In;

public class KdTree {
    private static final boolean VERT = true;
    private static final boolean HORZ = false;

    private LinkedList<Point2D> allPointsQueue  = new LinkedList<Point2D>();
    private LinkedList<Point2D> rangeQueue  = new LinkedList<Point2D>();

    private Node root;
    private class Node {
        private Point2D point;
        private Node leftBottom, rightTop;
        private int size;
        private boolean vertical;
        private RectHV rect;
        public Node (Point2D point, boolean vertical, int size)
        {
            this.point = point;
            this.size = size;
            this.vertical = vertical;
            rect = new RectHV(0.0, 0.0, 1.0, 1.0);
        }
    }
    public         KdTree()                               // construct an empty set of points
    {

    }

    /* Node methods*/
    private boolean isVert(Node node)
    {
        if (node == null) return false;
        return node.vertical == VERT;
    }
    private int size(Node node)
    {
        if (node == null) return 0;
        return node.size;
    }


    public           boolean isEmpty()                      // is the set empty?
    {
        return root == null;
    }
    public               int size()                         // number of points in the set
    {
        return size(root);
    }

    private void changeToHorizontal(Node node)
    {
        node.vertical = HORZ;
    }

    private void setRectHV(Node node, double xmin, double ymin, double xmax, double ymax)
    {
        if(node.rect.xmin() != xmin || node.rect.ymin() != ymin || node.rect.xmax() != xmax || node.rect.ymax() != ymax )
        {
            node.rect = new RectHV(xmin, ymin, xmax, ymax);
        }
    }
/*Private method for inserting points into trees: uses size to determine if vertical or not*/
    private Node put(Node node, Point2D point)
    {
        if (node == null) return new Node(point,VERT,1);
        if (node.vertical == VERT)
        {
            if (point.x() < node.point.x())//if x is smaller than parent go left and parent is vert;
            {
                node.leftBottom = put(node.leftBottom, point);
                setRectHV(node.leftBottom, node.rect.xmin(), node.rect.ymin(), node.point.x(), node.rect.ymax());
                changeToHorizontal(node.leftBottom);
            }
            else if (point.x() >= node.point.x()) // " bigger, go right and "
            {
                node.rightTop = put(node.rightTop, point);
                setRectHV(node.rightTop, node.point.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
                changeToHorizontal(node.rightTop);
            }

        }
        if (node.vertical == HORZ)
        {
            if (point.y() < node.point.y())//if x is smaller than parent go left and parent is vert;
            {
                node.leftBottom = put(node.leftBottom, point);
                setRectHV(node.leftBottom, node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.point.y());
            }
            else if (point.y() >= node.point.y()) // " bigger, go right and "
            {
                node.rightTop = put(node.rightTop, point);
                setRectHV(node.rightTop, node.rect.xmin(), node.point.y(), node.rect.xmax(), node.rect.ymax());
            }
        }
        node.size = size(node.leftBottom) + size(node.rightTop) + 1;        //size + 1
        return node;
    }
    public              void insert(Point2D point)              // add the point to the set (if it is not already in the set)
    {
        if (point == null) throw new IllegalArgumentException("first point is null");
        root = put(root, point);
        allPointsQueue.add(point);
    }

    public           boolean contains(Point2D point)            // does the set contain point p?
    {
        if (point == null) throw new IllegalArgumentException("first point is null");
        return get(root, point) != null;
    }


    private Point2D get(Node node, Point2D point)
    {
        double cmp = 0.0;
        double chk = 0.0;
        while(node != null) {
            if (node.vertical == VERT) {
                cmp = -node.point.x() + point.x();
                chk = -node.point.y() + point.y();
            } else {
                cmp = -node.point.y() + point.y();
                chk = -node.point.x() + point.x();
            }
            //StdOut.println("point: " + point + ", CMP: " + cmp +   " node.vertical: " + node.vertical +", Node.point: " + node.point);
            if (cmp < 0.0) node = node.leftBottom;
            else if (cmp > 0.0 )node = node.rightTop;
            else if (chk != 0.0)node = node.rightTop;
            else return node.point;
        }
        return null;
    }

    private Node getNode(Node node, Point2D point)
    {
        double cmp = 0.0;
        double chk = 0.0;
        while(node != null) {
            if (node.vertical == VERT) {
                cmp = -node.point.x() + point.x();
                chk = -node.point.y() + point.y();
            } else {
                cmp = -node.point.y() + point.y();
                chk = -node.point.x() + point.x();
            }
            StdOut.println("point: " + point + ", CMP: " + cmp + ", CHK: " + chk +  ", Node.vertical: " + node.vertical +", Node.point: " + node.point);
            if (cmp < 0.0) node = node.leftBottom;
            else if (cmp > 0.0 )node = node.rightTop;
            else if (chk != 0.0)node = node.rightTop;
            else return node;
        }
        return null;
    }

    public              void draw()                         // draw all points to standard draw
    {
        Node node;
        StdDraw.setPenRadius(0.01);

        for (Point2D point : points())
        {
            node = getNode(root, point);
            //StdOut.println(" Node.vertical: " + node.vertical +", Node.point: " + node.point+", Node.rect: " + node.rect.toString());
            if(node.vertical == VERT)//if vert draw red line
            {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(node.point.x(),node.rect.ymin(),node.point.x(),node.rect.ymax());
            }else{
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(node.rect.xmin(),node.point.y(),node.rect.xmax(),node.point.y());
            }
            StdDraw.setPenColor();
            StdDraw.point(point.x(), point.y());
        }
        //do after rect
    }

    private Iterable<Point2D> points()
    {
        return allPointsQueue;
    }

    private Node range(Node searchNode, RectHV rect){
        if (rect.contains(searchNode.point))
            rangeQueue.add(searchNode.point);

        if (searchNode.leftBottom != null && searchNode.leftBottom.rect.intersects(rect))//if right subtree doesnt intersect
        {
            searchNode = range(searchNode.leftBottom, rect); //dont explore right subtree
        }
        if (searchNode.rightTop != null && searchNode.rightTop.rect.intersects(rect)) {
            searchNode = range(searchNode.rightTop, rect);
        }
        return searchNode;
    }
    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)
    {
    //if the query rectangle does not intersect node rectangle , do not explore that node (or its subtrees)
        Node node = range(root,rect);
        return rangeQueue;
    }
//    public           Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args)                  // unit testing of the methods (optional)
    {
//        Point2D point1 = new Point2D(0.5,0.5);
//        Point2D point2 = new Point2D(0.4,0.4);
//        Point2D point3 = new Point2D(0.6,0.6);
//        Point2D point4 = new Point2D(0.2,0.5);
//        KdTree testTree = new KdTree();
//        testTree.insert(point1);
//        testTree.insert(point2);
//        testTree.insert(point3);
//        StdOut.println(testTree.contains(point1));
//        StdOut.println(testTree.contains(point2));
//        StdOut.println(testTree.contains(point3));
//        StdOut.println(testTree.contains(point4));
//        StdOut.println(testTree.points());
//        testTree.draw();
        String filename = args[0];
        In in = new In(filename);
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
        }
        //StdOut.println(kdtree.points());
        kdtree.draw();

    }
}