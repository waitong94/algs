import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdArrayIO; //TODO remove this after testing

import edu.princeton.cs.algs4.WeightedQuickUnionUF; 

public class Percolation {
   
   private static int[] array;
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
       this.array= new int[n*n];
       this.n=n;
   }
   public    void open(int row, int col)    // open site (row, col) if it is not open already
   {
       if (row<=0 || col<=0 || row>n || col>n)
           throw new IllegalArgumentException();
       int i=index(row,col);
       StdOut.println(i);
       Percolation.array[i]=1;
       int rowMinus=row-1;
       int rowPlus=row+1;
       int colPlus=col+1;
       int colMinus=col-1;
       StdOut.println("index="+i);
       StdOut.println(index(rowMinus,col)+" "+rowMinus+" "+row+" "+col+" "+Percolation.n);
       
       if(row==1) //union with top ghost cell if on first row 
           qUnion.union(top,Percolation.array[i]);
       if(row==Percolation.n) //union with top ghost cell if on first row 
           qUnion.union(bottom,Percolation.array[i]);
       if(rowMinus>0&&isOpen(rowMinus,col))//union with adjecent open cell  
              qUnion.union(Percolation.array[index(rowMinus,col)],Percolation.array[i]);
       if(rowPlus<=Percolation.n && isOpen(rowPlus,col))//union with adjecent open cell
              qUnion.union(Percolation.array[index(rowPlus,col)],Percolation.array[i]);
       if(colPlus<=Percolation.n&&isOpen(row,colPlus))//union with adjecent open cell
              qUnion.union(Percolation.array[index(row,colPlus)],Percolation.array[i]);
       if(colMinus>0&&isOpen(row,colMinus))//union with adjecent open cell
              qUnion.union(Percolation.array[index(row,colMinus)],Percolation.array[i]);
   }
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   {
       if (row<=0 || col<=0 || row>n || col>n)
           throw new IllegalArgumentException();
       int i=index(row,col);
       return Percolation.array[i]==1;   
   }
   public boolean isFull(int row, int col)  // is site (row, col) full?
   {
       if (row<=0 || col<=0 || row>n || col>n)
           throw new IllegalArgumentException();
       int i=index(row,col);
       return qUnion.connected(top,Percolation.array[i]);
   }
   public     int numberOfOpenSites()       // number of open sites
   {
    int sum=0;
       for (int i:Percolation.array)
           sum=sum+Percolation.array[i];
    return sum; //TODO this is still wrong
   }
   public boolean percolates()              // does the system percolate?
   {
       return qUnion.connected(top,bottom); //TODO placeholder
   }
//
   public static void main(String[] args)       // test client (optional)
   {
       int n=3;
       Percolation grid= new Percolation(n); //initialise all sites to be blocked
       StdArrayIO.print(grid.array);
       boolean isPercolated= grid.percolates();
       StdRandom.setSeed(1);
       while(!isPercolated)
       {
           int i=StdRandom.uniform(n)+1;
           int j=StdRandom.uniform(n)+1;//choose a site at random
//           i=2;
//           j=1;
//           grid.open(i,j);//open the site
//           i=1;
//           grid.open(i,j);//open the site
//           i=3;
           grid.open(i,j);//open the site
           StdArrayIO.print(grid.array);
           StdOut.println("i="+String.valueOf(i)+" j="+j+" n="+Percolation.n);
           isPercolated=grid.percolates();//check
           StdOut.println(isPercolated);
           StdOut.println(grid.numberOfOpenSites());
       }
       
   }
}