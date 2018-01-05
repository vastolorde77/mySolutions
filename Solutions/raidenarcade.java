import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static boolean isSafe(int x,int y,int [][] map,int goldcount){
        if(y == -1 || y == 5){
            return false;
        }
        //System.out.println(x+"-"+y);
        return true;
    }
    
    public static int trav(int level,int x,int y,int goldcount,int [][] map,int [][] map2,ArrayList<Integer> sol,boolean use,int countb,int en){
       
        
        if( map[x][y] == 2){
            if(countb <= 0){              
                goldcount -= 1;
                if(goldcount < 0){
                    return 1;
                }
            }
            
        }
        if( map[x][y] == 1 ){
            goldcount+=1;
        }
        countb -= 1;
        
        if(x == 0){ 
            //System.out.println(x+"-"+y+goldcount);
            if(!sol.isEmpty()){
                int xx = sol.get(0);
                if(goldcount>xx){
                    sol.set(0,goldcount);
                }
            }else{
                sol.add(goldcount);
            }
            return 1;
        }
        
        
            
            
        
        if(isSafe(x-1,y,map,goldcount)){
            trav(level,x-1,y,goldcount,map,map2,sol,use,countb,en);
            if(!use){
            trav(level,x-1,y,goldcount,map,map2,sol,true,5,en);
            }
        }        
        if(isSafe(x-1,y-1,map,goldcount)){
            trav(level,x-1,y-1,goldcount,map,map2,sol,use,countb,en);
            if(!use){
            trav(level,x-1,y-1,goldcount,map,map2,sol,true,5,en);
            }
        }
        if(isSafe(x-1,y+1,map,goldcount)){
            trav(level,x-1,y+1,goldcount,map,map2,sol,use,countb,en);
            if(!use){
            trav(level,x-1,y+1,goldcount,map,map2,sol,true,5,en);
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int level = sc.nextInt();
        int [][] map = new int[level][5];
        int [][] map2 = new int[level][5];
        ArrayList<Integer> sol = new ArrayList<Integer>();
        for(int i = 0;i<level;i++){
            for(int j = 0;j<5;j++){
                int pp =  sc.nextInt();
                map[i][j] = pp;
                if(pp == 2){
                    map2[i][j] = 0;
                }else{
                    map2[i][j] = pp;
                }
            }
        }
        //sol.add(0);
        int en = 0;
        trav(level-1,level-1,2,0,map,map2,sol,false,0,en);
        trav(level-1,level-1,2,0,map,map2,sol,true,5,en);
        trav(level-1,level-1,1,0,map,map2,sol,false,0,en);
        trav(level-1,level-1,1,0,map,map2,sol,true,5,en);
        trav(level-1,level-1,3,0,map,map2,sol,false,0,en);
        trav(level-1,level-1,3,0,map,map2,sol,true,5,en);
        if(sol.size() > 0){
            //System.out.println(sol.size());
            int nn = sol.get(0);
            System.out.println(nn);
                
        
        }else{
            System.out.println(-1);
        }
        
        
    }
}