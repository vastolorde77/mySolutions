import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Solution {
    static int ceiling;
    static int width;
    static int e_X,e_Y;
    static int [][] map = new int [100][100];
    static int [][] visited = new int [100][100];
    
    static boolean finished = false;
    
    static void pr(Object o){
        System.out.println(o);
    }
    
    static boolean safe(int x,int y){
        return x >= 0 && x < ceiling && y >= 0 && y < width; 
    }
    static void climb(int x,int y,int cur_max){
        if(!safe(x,y) || (map[x][y] < 1 || map[x][y] > 3 ) ) return;
        if(x == e_X && y == e_Y){
            finished = true;
            return;
        }
        if(visited[x][y] == 0 && !finished){
            visited[x][y] = 1;
            
            int jump = 1;
            while(jump < cur_max){
                climb(x+jump,y,cur_max);
                climb(x-jump,y,cur_max);
                jump++;
            }
            
            climb(x,y+1,cur_max);
            climb(x,y-1,cur_max);
            //backtrack
            visited[x][y] = 0;
        }
         
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        width = s.nextInt();
        ceiling = s.nextInt();
        int s_X = 0;
        int s_Y = 0;
        for(int x = 0;x<ceiling;x++){
            for(int y = 0;y<width;y++){
                map[x][y] = s.nextInt();
                if(map[x][y] == 2){ s_X = x;s_Y = y;}
                if(map[x][y] == 3){ e_X = x;e_Y = y;}
            }
        }
        for(int i = 1;i <= ceiling;i++){
            climb(s_X,s_Y,i);
            if(finished){
                pr(i-1);
                break;
            }
        }
        
      
    }
}
