import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdArrayIO; 

import edu.princeton.cs.algs4.QuickUnionUF; 
import edu.princeton.cs.algs4.WeightedQuickUnionUF; 

public class Percolation {
   
   private static int[] Grid;
   private static int n;
   private static int top=1;
   private static int bottom;
   private static WeightedQuickUnionUF qUnion=new WeightedQuickUnionUF((Percolation.n*Percolation.n)+2);
   
   private static int index(int row,int col)
   {
       int i=Percolation.n*(row-1)+col-1;
       return i;
   }
    
   public Percolation(int n)                // create n-by-n grid, with all sites blocked
   {
       if (n<=0)
           throw new IllegalArgumentException();
       this.Grid= new int[n*n];
       this.n=n;
   }
   public    void open(int row, int col)    // open site (row, col) if it is not open already
   {
       //TODO throw exception if out of bound here
       int i=index(row,col);
       Percolation.Grid[i]=1;
       int rowMinus=row-1;
       int rowPlus=row+1;
       int colPlus=col+1;
       int colMinus=col-1;
       StdOut.println("index="+i);
       if(row==1) //union with top ghost cell if on first row 
           qUnion.union(top,Percolation.Grid[i]);
       if(row==n) //union with top ghost cell if on first row 
           qUnion.union(bottom,Percolation.Grid[i]);
       if(isOpen(rowMinus,col)&&rowMinus>0)//union with adjecent open cell
              qUnion.union(Percolation.Grid[index(rowMinus,col)],Percolation.Grid[i]);
       if(isOpen(rowPlus,col)&&rowPlus<=Percolation.n)//union with adjecent open cell
              qUnion.union(Percolation.Grid[index(rowPlus,col)],Percolation.Grid[i]);
       if(isOpen(row,colPlus)&&colPlus<=Percolation.n)//union with adjecent open cell
              qUnion.union(Percolation.Grid[index(row,colPlus)],Percolation.Grid[i]);
       if(isOpen(row,colMinus)&&colMinus>0)//union with adjecent open cell
              qUnion.union(Percolation.Grid[index(row,colMinus)],Percolation.Grid[i]);
   }
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   {
       int i=index(row,col);
       return Percolation.Grid[i]==1;   
   }
   public boolean isFull(int row, int col)  // is site (row, col) full?
   {
       int i=index(row,col);
       return qUnion.connected(top,Percolation.Grid[i]);
   }
//   public     int numberOfOpenSites();       // number of open sites
   public boolean percolates()              // does the system percolate?
   {
       return qUnion.connected(top,bottom); //TODO placeholder
   }
//
   public static void main(String[] args)       // test client (optional)
   {
       int n=3;
       Percolation grid= new Percolation(n); //initialise all sites to be blocked
       StdArrayIO.print(grid.Grid);
       boolean isPercolated= grid.percolates();
       StdRandom.setSeed(1);
//       while(isPercolated != true)
//       {
           int i=StdRandom.uniform(n)+1;
           int j=StdRandom.uniform(n)+1;//choose a site at random
           i=2;
           j=1;
           grid.open(i,j);//open the site
           i=1;
           grid.open(i,j);//open the site
           i=3;
          // grid.open(i,j);//open the site
           StdArrayIO.print(grid.Grid);
           StdOut.println("i="+String.valueOf(i)+" j="+j+" n="+Percolation.n);
           isPercolated=grid.percolates();//check
           StdOut.println(isPercolated);
//       }
       
   }
}