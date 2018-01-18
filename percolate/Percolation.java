import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
//TODO play around with refactoring code.
import edu.princeton.cs.algs4.WeightedQuickUnionUF; 

public class Percolation {
   
   private static int[] array;
   private static int n;
   private static int top;
   private static int bottom;
   private static int empty;
   private static WeightedQuickUnionUF qUnion;
   private static int index(int row,int col)
   {
       int i=Percolation.n*(row-1)+col;
       return i;
   }
    
   public Percolation(int n)                // create n-by-n grid, with all sites blocked
   {
       if (n<=0)
           throw new IllegalArgumentException();
       Percolation.array= new int[n*n+1];
       Percolation.n=n;
       Percolation.qUnion=new WeightedQuickUnionUF((Percolation.n*Percolation.n)+3);
       Percolation.empty=0;
       Percolation.top=Percolation.n*Percolation.n+1;
       Percolation.bottom=Percolation.n*Percolation.n+2;
       for(int i = 0; i<Percolation.n*Percolation.n+1; i++)
       {
           Percolation.array[i]=Percolation.empty;
       }
      
   }
   public    void open(int row, int col)    // open site (row, col) if it is not open already
   {
       if (row<=0 || col<=0 || row>n || col>n)
           throw new IllegalArgumentException();
       int i=index(row,col);
       Percolation.array[i]=i;
       int rowMinus=row-1;
       int rowPlus=row+1;
       int colPlus=col+1;
       int colMinus=col-1;
       if(row==1) //union with top ghost cell if on first row 
       {
           qUnion.union(Percolation.top,Percolation.array[i]);
       }
  
       if(rowMinus>0&&isOpen(rowMinus,col))//union with adjecent open cell  
       {
              qUnion.union(Percolation.array[index(rowMinus,col)],Percolation.array[i]);
       }
       if(rowPlus<=Percolation.n && isOpen(rowPlus,col))//union with adjecent open cell
       {
              qUnion.union(Percolation.array[index(rowPlus,col)],Percolation.array[i]);
       }
       if(colPlus<=Percolation.n&&isOpen(row,colPlus))//union with adjecent open cell
       {
              qUnion.union(Percolation.array[index(row,colPlus)],Percolation.array[i]);
       }
       if(colMinus>0&&isOpen(row,colMinus))//union with adjecent open cell
       {
              qUnion.union(Percolation.array[index(row,colMinus)],Percolation.array[i]);
       }

       for (int it=0;it<n;it++)
       {
           if (qUnion.connected(Percolation.array[i], Percolation.array[index(n,it+1)])&&qUnion.connected(Percolation.top,Percolation.array[i]))
               qUnion.union(Percolation.array[i],Percolation.bottom);
       }
   }
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   {
       if (row<=0 || col<=0 || row>n || col>n)
           throw new IllegalArgumentException();
       int i=index(row,col);
       return Percolation.array[i]!=Percolation.empty;   
   }
   public boolean isFull(int row, int col)  // is site (row, col) full?
   {
       if (row<=0 || col<=0 || row>n || col>n)
           throw new IllegalArgumentException();
       int i=index(row,col);
       return qUnion.connected(Percolation.top,Percolation.array[i]);


   }
   public     int numberOfOpenSites()       // number of open sites TODO number ofOpen sites not reliale
   {
    int sum=0;
    for (int i=0; i<Percolation.n*Percolation.n; i++)
    {
           if (Percolation.array[i]!=Percolation.empty)
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
//       while(!isPercolated)
//       {
       int i=StdRandom.uniform(n)+1;
       int j=StdRandom.uniform(n)+1;//choose a site at random
       i=2;
       j=1;
//       grid.open(i,j);//open the site
       i=1;
       grid.open(i,j);//open the site
       i=3;
       grid.open(i,j);//open the site
       grid.open(2,2);//open the site
       isPercolated=grid.percolates();//check
//       }
       
   }
}