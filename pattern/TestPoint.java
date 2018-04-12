public class TestPoint
{
    public TestPoint()
    {

    }
    private Point minusX = new Point(-1 , 0);
    private Point minusY = new Point(0 , -1);
    private Point plusX = new Point(+1 , 0);
    private Point plusY = new Point(0 , +1);

    public void testSlopeTo()
    {
        assert minusY.slopeTo(plusY) == Double.POSITIVE_INFINITY: "vertical";  // test vertical
        assert minusX.slopeTo(plusX) == +0.0;//test horizontal
        assert minusY.slopeTo(minusY) == Double.NEGATIVE_INFINITY; // test vertical//test degenerate
        assert minusX.slopeTo(plusY) == +1.0;//test positive slope
        assert minusX.slopeTo(minusY) == -1.0;//test negative slope
    }

    public void testCompareTo() //compareeeeee y, break ties with x
    {
        assert minusY.compareTo(plusY) == -1;//negative if this is less than that
        assert minusX.compareTo(plusX) == -1;
        assert plusY.compareTo(minusY) == +1;//positive if that is greater than this
        assert plusX.compareTo(minusX) == +1;
        assert minusY.compareTo(minusY) == 0;//zero if equal
    }
    public static void main(String[] args){
        TestPoint test = new TestPoint();
        test.testSlopeTo(); //passed
    }
}