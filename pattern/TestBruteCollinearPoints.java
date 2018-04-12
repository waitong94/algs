public class TestBruteCollinearPoints {
    public TestBruteCollinearPoints() {
    }

    private Point[] pFour = new Point[4];
    private void populateArray()
    {
        pFour[0] = new Point(-1 , 0);
        pFour[1] = new Point(0 , 0);
        pFour[2] = new Point(+1 , 0);
        pFour[3] = new Point(+2 , 0);
    }
    private void testIsCollinear(BruteCollinearPoints brute) //conc: trouble with slopeTo int and double issue
    {
        Point p = new Point(10000, 0);
        Point q = new Point(3000, 7000);
        Point r = new Point(7000, 3000);
        Point s = new Point(6000, 7000);
        assert brute.isCollinear(p,q,r,s) == false: "p.q = " + p.slopeTo(q) + " p.r =" + p.slopeTo(r) + " p.s " + p.slopeTo(s);
    }


    public static void main(String[] args){
        TestBruteCollinearPoints test = new TestBruteCollinearPoints();
        test.populateArray();
        BruteCollinearPoints brute = new BruteCollinearPoints(test.pFour);
        test.testIsCollinear(brute);
        brute.segments();
        assert brute.numberOfSegments() == 3: "number of segmests = " + brute.numberOfSegments();
    }

}