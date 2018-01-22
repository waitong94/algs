import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class PercolationStats {
   private double[] openSites;
   private double[] confidence=new double[2];
   private int n;
   private int trials;
   private double meanValue;
   private double stddevValue;
   private double conLo;
   private double conHi;
   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   {
       this.n=n;
       this.trials=trials;
       if (n<=0 || trials<=0)
           throw new IllegalArgumentException();
       
       openSites=new double[trials];
       for (int it=0;it<trials;it++)
       {       
           Percolation perc= new Percolation(n);
           while (!perc.percolates())
           {
               int i=StdRandom.uniform(n)+1;
               int j=StdRandom.uniform(n)+1;//choose a site at random
               perc.open(i,j);//open the site
           }
           openSites[it]=perc.numberOfOpenSites();

       }
   }
   
   public double mean()                          // sample mean of percolation threshold
   {
      // StdOut.println("called mean in stdstats");
       meanValue=StdStats.mean(openSites)/(n*n);
       return meanValue;

   }
   public double stddev()                        // sample standard deviation of percolation threshold
   {
       stddevValue= StdStats.stddev(openSites)/(n*n);
       return stddevValue;
   }
   public double confidenceLo()                  // low  endpoint of 95% confidence interval{
   {
       conLo= meanValue-1.96*stddevValue/Math.sqrt(trials);
       return conLo;
   }
   public double confidenceHi()                  // high endpoint of 95% confidence interval
   {
       conHi=meanValue+1.96*stddevValue/Math.sqrt(trials);
       return conHi;
   }

   public static void main(String[] args)        // test client (described below)
   {
          // input file
       int n=Integer.valueOf(args[0]);
       int trials=Integer.valueOf(args[1]);
       PercolationStats percStats= new PercolationStats(n,trials);
       StdOut.println("Mean: " + percStats.mean());
       StdOut.println("Std Dev: "+ percStats.stddev());
       StdOut.println("95% confidence interval: ["+ percStats.confidenceLo() + "," + percStats.confidenceHi() + "]");
   }
           
}