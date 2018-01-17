import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class PercolationStats {
   private Percolation perc;
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
       StdRandom.setSeed(1);
       
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
       this.stddevValue=stddev();
       this.conLo=confidenceLo();
       this.conHi=confidenceHi();
   }
   
   public double mean()                          // sample mean of percolation threshold
   {
       return StdStats.mean(openSites)/(n*n);
   }
   public double stddev()                        // sample standard deviation of percolation threshold
   {
       return StdStats.stddev(openSites)/(n*n);
   }
   public double confidenceLo()                  // low  endpoint of 95% confidence interval{
   {
       return mean()-1.96*stddev()/Math.sqrt(trials);
   }
   public double confidenceHi()                  // high endpoint of 95% confidence interval
   {
       return mean()+1.96*stddev()/Math.sqrt(trials);
   }

   public static void main(String[] args)        // test client (described below)
   {
          // input file
       int n=Integer.valueOf(args[0]);
       int trials=Integer.valueOf(args[1]);
       PercolationStats percStats= new PercolationStats(n,trials);
       StdOut.println("Mean: " + percStats.meanValue);
       StdOut.println("Std Dev: "+ percStats.stddevValue);
       StdOut.println("95% confidence interval: ["+ percStats.conLo + "," + percStats.conHi + "]");
   }
           
}