import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdArrayIO; //TODO remove this after testing
//TODO refactor
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
       this.array= new int[n*n+1];
       this.n=n;
       this.qUnion=new WeightedQuickUnionUF((this.n*this.n)+3);
       this.empty=0;
       this.top=this.n*this.n+1;
       this.bottom=this.n*this.n+2;
       for(int i = 0; i<this.n*this.n; i++)
       {
           this.array[i]=this.empty;
       }
      
   }
   public    void open(int row, int col)    // open site (row, col) if it is not open already
   {
       if (row<=0 || col<=0 || row>n || col>n)
           throw new IllegalArgumentException();
       int i=index(row,col);
       StdOut.println(i);
       Percolation.array[i]=i;
       int rowMinus=row-1;
       int rowPlus=row+1;
       int colPlus=col+1;
       int colMinus=col-1;
       StdOut.println("index="+i);
       StdOut.println(index(rowMinus,col)+" "+rowMinus+" "+row+" "+col+" "+Percolation.n);
       
       if(row==1) //union with top ghost cell if on first row 
       {
           qUnion.union(Percolation.top,Percolation.array[i]);
           StdOut.println("union with top");
           StdOut.println("Top: "+Percolation.top);
       }
  
       if(rowMinus>0&&isOpen(rowMinus,col))//union with adjecent open cell  
       {
              qUnion.union(Percolation.array[index(rowMinus,col)],Percolation.array[i]);
              StdOut.println("union with above");
       }
       if(rowPlus<=Percolation.n && isOpen(rowPlus,col))//union with adjecent open cell
       {
              qUnion.union(Percolation.array[index(rowPlus,col)],Percolation.array[i]);
              StdOut.println("union with below");
       }
       if(colPlus<=Percolation.n&&isOpen(row,colPlus))//union with adjecent open cell
       {
              qUnion.union(Percolation.array[index(row,colPlus)],Percolation.array[i]);
              StdOut.println("union with right");
       }
       if(colMinus>0&&isOpen(row,colMinus))//union with adjecent open cell
       {
              qUnion.union(Percolation.array[index(row,colMinus)],Percolation.array[i]);
              StdOut.println("union with left");
       }
       if(row==Percolation.n && qUnion.connected(Percolation.top,Percolation.array[i])) //union with bottom ghost cell if on first row 
       {
           StdOut.println("Bottom: "+Percolation.bottom);
           qUnion.union(Percolation.array[i],Percolation.bottom);
           StdOut.println("union with bottom");
       }
  
       StdOut.println("Full? "+isFull(row,col));
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
       
       
       if(row==Percolation.n && qUnion.connected(Percolation.top,Percolation.array[i])) //union with bottom ghost cell if on first row 
       {
           StdOut.println("Bottom: "+Percolation.bottom);
           qUnion.union(Percolation.array[i],Percolation.bottom);
           StdOut.println("union with bottom");
       }
       return qUnion.connected(Percolation.top,Percolation.array[i]);


   }
   public     int numberOfOpenSites()       // number of open sites
   {
    int sum=0;
    for (int i=0; i<Percolation.n*Percolation.n; i++)
    {
           if (Percolation.array[i]!=Percolation.empty)
           sum=sum+1;
    }
    return sum; 
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
       StdArrayIO.print(grid.array);
       boolean isPercolated= grid.percolates();
       StdOut.println("Percolates? "+grid.percolates());
       StdRandom.setSeed(1);
//       while(!isPercolated)
//       {
       int i=StdRandom.uniform(n)+1;
       int j=StdRandom.uniform(n)+1;//choose a site at random
       i=2;
       j=1;
//       grid.open(i,j);//open the site
       i=1;
       grid.open(i,j);//open the site
       StdOut.println("Percolates? "+grid.percolates());
       i=3;
       grid.open(i,j);//open the site
       StdOut.println("Percolates? "+grid.percolates());
       grid.open(2,2);//open the site
       StdArrayIO.print(grid.array);
       StdOut.println("i="+String.valueOf(i)+" j="+j+" n="+Percolation.n);
       isPercolated=grid.percolates();//check
       StdOut.println(isPercolated);
       StdOut.println(grid.numberOfOpenSites());
//       }
       
   }
}