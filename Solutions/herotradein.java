import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Scanner;;
public class Solution {

    static int[]glob_calc= new int[5];
    static int[]glob_calc2= new int[5];
   

    
    public static int find_zero(int[][]a,int[][]con_zero, int n,int prev,int y,int x, int[]calc,int[][]vis)
    {
            
        
            if(x<0||x>=n  ||y<0 ||y>=n)
            {
                
                return -1;
            }
            if(vis[y][x]==1)
            {
                return -1;
            }
            

        
        if(prev!=a[y][x] )
        {
            if(prev!=0)
            {
                return-1;    
            }
            else
            {
                if(a[y][x]!=0)
                {
                    
                    glob_calc[a[y][x]-1]++;
                }
            }
        }
        else
        {
            if(a[y][x]!=0)
            {
                

                glob_calc[a[y][x]-1]++;
            }
                    
        }

            
        vis[y][x]=1;
            
            
            
            
        find_zero(a,con_zero, n,a[y][x],y, x+1,  glob_calc,vis);
        find_zero(a,con_zero, n,a[y][x],y, x-1,  glob_calc,vis);
        find_zero(a,con_zero, n,a[y][x],y+1, x,  glob_calc,vis);
        find_zero(a,con_zero, n,a[y][x],y-1, x,  glob_calc,vis);
        
        int i=0;
        int max=glob_calc[i];
        for(int z=1;z<5;z++)
        {
   
                if(glob_calc[z]>=max)
                {
    
                     if(glob_calc[z]!=0) 
                     {
                         
                        max =glob_calc[z];
                        i=z;
                     }

                            
                }
        }


        
        if(max==0)
            return i;
        
        return i+1;
        
        
    }
    public static int set_neigbour(int c,int[][] a,int[][] count_zero, int n,int x, int y)
    {
        if(x<0||x>=n  ||y<0 ||y>=n)
        {
            
            return 0;
        }
        if(count_zero[y][x]!=0 )
        {
            return 1;
            
        }
        if(c>1)
        {
            return 0;
        }
    
        if(set_neigbour( c+1,a,count_zero, n, x+1,  y)==1)
        {
            count_zero[y][x]=count_zero[y][x+1];
            return 1;
        }
        if(set_neigbour( c+1,a,count_zero, n, x-1,  y)==1)
        {
            count_zero[y][x]=count_zero[y][x-1];
            return 1;
        }        
        if(set_neigbour( c+1,a,count_zero, n, x,  y+1)==1)
        {
            count_zero[y][x]=count_zero[y+1][x];
            return 1;
        }
        if(set_neigbour( c+1,a,count_zero, n, x,  y-1)==1)
        {
            count_zero[y][x]=count_zero[y-1][x];
            return 1;
        }
        return 0;
    }
    
    public static void reset_2d(int[][]a,int n)
    {
        for(int y=0;y<n;y++)
        {
            for(int x=0;x<n;x++)
                {
                a[y][x]=0;
                }
        }
        
    }
    public static void reset()
    {
        for(int x=0;x<5;x++)
        {
            
            glob_calc[x]=0;
            glob_calc2[x]=0;
        }
        
        
    }
    public static int count_same(int[][]a, int n , int x, int y,int prev,int[][]vis,int[][]map)
    {
        if(x<0||x>=n  ||y<0 ||y>=n)
        {
            
            return -1;
        }

        
    
        if(prev!=a[y][x])
        {


                //map[y][x]=1;
                return -2;
            
        }

        if(vis[y][x]!=0)
        {
            return -1;
        }
        vis[y][x]=1;

        


        
        
        count_same(a, n , x+1, y,a[y][x],vis,map);count_same(a, n , x-1, y,a[y][x],vis,map);
                count_same(a, n , x, y+1,a[y][x],vis,map);count_same(a, n , x, y-1,a[y][x],vis,map);
                
        
    
        if(prev==a[y][x])
        {
    
            map[y][x]=1;
            return 1;
        }
        return -2;
        
        
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //Random rn = new Random();
        int[][] a=new int[n][n];
        int[][] con_zero = new int[n][n];
        int[]calc= new int[5];
        int[][] vis=new int[n][n];
        //init arr
        reset();
        reset_2d(con_zero, n);
        reset_2d(vis, n);
        //get input
        for(int y=0;y<n;y++)
            {    for(int x=0;x<n;x++)
                {
                    
                    //a[y][x]=rn.nextInt(5 - 0 + 1) + 0;
                    
                a[y][x]=sc.nextInt();
                    //System.out.print(a[y][x]);
                }
                //System.out.println();
            }
        //bfs
        for(int y=0;y<n;y++)
        {    for(int x=0;x<n;x++)
            {
                reset();
                reset_2d(vis, n);
                
                if(a[y][x]==0)
                {        

                    //if(set_neigbour(1,a,con_zero,  n, x,  y)==0)
                    {
                            
                            int val =find_zero(a,con_zero, n,0,y,x,calc,vis);
                            if(val!=-1)
                            {
                                //System.out.println(y+",,"+x+":"+val);
                                //a[y][x]=val;
                                con_zero[y][x]=val;
                            }
                            
                    
                    }
                    
                        
                    
                }

            }
        }

        for(int y=0;y<n;y++)
        {    for(int x=0;x<n;x++)
            {
                if(con_zero[y][x]!=0)
                    a[y][x]=con_zero[y][x];
            //System.out.print(a[y][x]+" ");

            }
            //System.out.println();
        }
        int[][] map=new int[n][n];
        reset_2d(vis, n);
        reset_2d(map, n);
        reset();


        
        

        for(int y=0;y<n;y++)
        {    for(int x=0;x<n;x++)
            {
                
                int v=count_same(a, n , x, y,a[y][x],vis,map);
                //System.out.print(map[y][x]+","+v+" ");
                if(map[y][x]==1 )
                {
                    if(v!=-1)
                        glob_calc2[a[y][x]-1]++; 
                }
                else
                {
                    glob_calc2[a[y][x]-1]++; 
                }
            }
            //System.out.println();
        }
        int sum=0;
        for(int x=0;x<5;x++)
        {
            //System.out.print(glob_calc2[x]);
            sum+=glob_calc2[x];

        }
        //System.out.println();
        System.out.println(sum);

    }
}