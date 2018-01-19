import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
//TODO play around with refactoring code.
import edu.princeton.cs.algs4.WeightedQuickUnionUF; 

//import edu.princeton.cs.algs4.StdArrayIO; 

public class Percolation {
   
   private int[] array;
   private int n;
   private static int top;
   private static int bottom;
   private static int empty;
   private static WeightedQuickUnionUF qUnion;
   private int index(int row,int col)
   {
       int i=n*(row-1)+col;
       return i;
   }
    
   public Percolation(int n)                // create n-by-n grid, with all sites blocked
   {
       if (n<=0)
           throw new IllegalArgumentException();
       array= new int[n*n+1];
       this.n=n;
       Percolation.qUnion=new WeightedQuickUnionUF((n*n)+3);
       Percolation.empty=0;
       Percolation.top=n*n+1;
       Percolation.bottom=n*n+2;
       for(int i = 0; i<array.length; i++)
       {
           array[i]=Percolation.empty;
       }
      
   }
   public    void open(int row, int col)    // open site (row, col) if it is not open already
   {
       if (row<=0 || col<=0 || row>n || col>n)
           throw new IllegalArgumentException();
       int i=index(row,col);
       array[i]=i;
       int rowMinus=row-1;
       int rowPlus=row+1;
       int colPlus=col+1;
       int colMinus=col-1;
       if(row==1) //union with top ghost cell if on first row 
       {
           qUnion.union(Percolation.top,array[i]);
       }
  
       if(rowMinus>0&&isOpen(rowMinus,col))//union with adjecent open cell  
       {
              qUnion.union(array[index(rowMinus,col)],array[i]);
       }
       if(rowPlus<=n && isOpen(rowPlus,col))//union with adjecent open cell
       {
              qUnion.union(array[index(rowPlus,col)],array[i]);
       }
       if(colPlus<=n&&isOpen(row,colPlus))//union with adjecent open cell
       {
              qUnion.union(array[index(row,colPlus)],array[i]);
       }
       if(colMinus>0&&isOpen(row,colMinus))//union with adjecent open cell
       {
              qUnion.union(array[index(row,colMinus)],array[i]);
       }

       for (int it=0;it<n;it++)
       {
           if (qUnion.connected(array[i], array[index(n,it+1)])&&qUnion.connected(Percolation.top,array[i]))
               qUnion.union(array[i],Percolation.bottom);
       }
   }
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   {
       if (row<=0 || col<=0 || row>n || col>n)
           throw new IllegalArgumentException();
       int i=index(row,col);
       return array[i]!=Percolation.empty;   
   }
   public boolean isFull(int row, int col)  // is site (row, col) full?
   {
       if (row<=0 || col<=0 || row>n || col>n)
           throw new IllegalArgumentException();
       int i=index(row,col);
       return qUnion.connected(Percolation.top,array[i]);


   }
   public     int numberOfOpenSites()       // number of open sites TODO number ofOpen sites not reliale
   {
    int sum=0;
    for (int i=0; i<array.length; i++)
    {
           if (array[i]!=Percolation.empty)
           sum=sum+1;
    }
    return sum; //FIX FOR CORRECTNESS TEST 2 TODO doesnt work
   }
   public boolean percolates()              // does the system percolate?
   {
       return qUnion.connected(Percolation.top,Percolation.bottom); 
   }
//
   public static void main(String[] args)       // test client (optional)
   {
       int n=3;
       Percolation grid= new Percolation(n); //initialise all sites to be blocked
       boolean isPercolated= grid.percolates();
      while(!isPercolated)
       {
   //    StdArrayIO.print(array);
       StdOut.println("Open sites: "+grid.numberOfOpenSites());
       int i=StdRandom.uniform(n)+1;
       int j=StdRandom.uniform(n)+1;//choose a site at random



       grid.open(i,j);//open the site

      
       isPercolated=grid.percolates();//check

       }
       
   }
}