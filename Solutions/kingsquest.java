import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static int traverse(int sum,int step,int newsum,int [] gold,int [] orcs,int ocount,int orc,String steps,int[] sol){
 

        if(step == orcs.length){
            sum = Math.min(sum,newsum);
            if(sol[0] == 0){
                sol[0] = sum;
            }else{
                if(sum<sol[0]){
                    sol[0] = sum;
                }
            }
            //sol[i] = sum;
            //System.out.println(steps);
            return newsum;
        }
        if(orcs[step] <= orc){
            if(ocount+1 == 3){
                traverse(sum,step+1,newsum,gold,orcs,0,0,steps+"1",sol);    
            } else{
                traverse(sum,step+1,newsum,gold,orcs,ocount+1,orc-orcs[step],steps+"1",sol);
            }              
        }
        traverse(sum,step+1,newsum+gold[step],gold,orcs,ocount,orc,steps+"2",sol);
        traverse(sum,step+1,newsum+(gold[step]*2),gold,orcs,ocount,orc+orcs[step],steps+"3",sol); 
        
              
        
        
        return newsum;
    }
    

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int sum = 0;
        int [] gold = new int [N];
        int [] orcs = new int [N];
        for(int i = 0; i<N;i++){
            int o = s.nextInt();
            orcs[i] = o;
            int v = s.nextInt();
            gold[i] = v;
            sum+= v;
        }
        //System.out.println(sum);
        //ArrayList<Integer> sol = new ArrayList<Integer>();
        int [] sol = new int[1];
        traverse(sum,0,0,gold,orcs,0,0,"",sol);
        int k = 0;
        int max = 999999;
        
        System.out.println(sol[0]);
    }
}