public class TestBruteCollinearPoints {
    public TestBruteCollinearPoints() {
    }

    private Point[] pFour = new Point[4];
    private void populateArray()
    {
        pFour[0] = new Point(-1 , 0);
        pFour[1] = new Point(0 , -1);
        pFour[2] = new Point(+1 , 0);
        pFour[3] = new Point(0 , +1);
    }


    public static void main(String[] args){
        TestBruteCollinearPoints test = new TestBruteCollinearPoints();
        test.populateArray();
        BruteCollinearPoints brute = new BruteCollinearPoints(test.pFour);
        assert brute.numberOfSegments() == 3;

    }

}