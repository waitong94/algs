import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class PercolationStats {
   private Percolation perc;
   private double[] openSites;
   private int n;
   private static double meanValue;
   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   {
       if (n<=0 || trials<=0)
           throw new IllegalArgumentException();
       StdRandom.setSeed(1);
       this.n=n;
       openSites=new double[trials];
       for (int it=0;it<trials;it++)
       {       
           perc= new Percolation(n);
           while (!perc.percolates())
           {
               int i=StdRandom.uniform(n)+1;
               int j=StdRandom.uniform(n)+1;//choose a site at random
               perc.open(i,j);//open the site
           }
           openSites[it]=perc.numberOfOpenSites();

       }
       this.meanValue=mean();
   }
   
   public double mean()                          // sample mean of percolation threshold
   {
       return StdStats.mean(openSites)/(n*n);
   }
//   public double stddev()                        // sample standard deviation of percolation threshold
//   public double confidenceLo()                  // low  endpoint of 95% confidence interval
//   public double confidenceHi()                  // high endpoint of 95% confidence interval
//
   public static void main(String[] args)        // test client (described below)
   {
          // input file
       int n=Integer.valueOf(args[0]);
       int trials=Integer.valueOf(args[1]);
       PercolationStats percStats= new PercolationStats(n,trials);
       StdOut.println("Mean: " + PercolationStats.meanValue);
//       StdOut.println("Standard Deviation: " + PercolationStats.stddev());
//       StdOut.println("Mean: " + PercolationStats.confidenceLo());
//       StdOut.println("Mean: " + PercolationStats.confidenceHi());
   }
           
}