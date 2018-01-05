import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import static java.lang.System.out;
class StackF{
    private int top = -1;
    private int size;
    private Coordinate [] arr;
    public StackF(int size){
        this.arr = new Coordinate[size];
        
    }
    public void push(Coordinate x){
        top++;
        arr[top] = x;
    }
    public Coordinate pop(){
        return arr[--top];
    }
    public boolean empty(){
        return top == -1;
    }
    
}
class Coordinate{
    public int x;
    public int y;
    public Coordinate(int x,int y){
        this.x = x;
        this.y = y;
    }
}
public class Solution {
    
    public static int Fill(int x,int y,int[][][]fmap,int [][] map,int dis,int num,int n){
        if((x<0) || (y < 0) || (x>=n) || (y >= n)){
            return 0;
        }
        if(map[x][y] == 0){
            return 0;
        }
        if((fmap[x][y][num] == 0) || (fmap[x][y][num] > dis)){
            fmap[x][y][num] = dis;
            
             dis+=1;
             Fill(x+1,y,fmap,map,dis,num,n);
             Fill(x-1,y,fmap,map,dis,num,n);
             Fill(x,y+1,fmap,map,dis,num,n);
             Fill(x,y-1,fmap,map,dis,num,n);
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][]map = new int [N][N];
        int[][][]fmap = new int [N][N][M];
        Coordinate [] gmap = new Coordinate[M];
        StackF stack = new StackF(N*N);
        for(int i = 0;i<M;i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            gmap[i] = new Coordinate(x-1,y-1);
        }
        
        for(int i = 0;i<N;i++){
            for(int j = 0;j<N;j++){
                map[i][j] = sc.nextInt();
                for(int l = 0;l<M;l++) fmap[i][j][l] = 0;
            }
        }
        for(int l =0;l<M;l++){
            map[gmap[l].x][gmap[l].y] = 2;
        }
        
        for(int g = 0;g<M;g++){
            Fill(gmap[g].x,gmap[g].y,fmap,map,0,g,N);
        }
        
        int min = 9999;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) continue;
				if (map[i][j] == 2) continue;
				int max = 0;
				for (int k = 0; k < gmap.length; k++){
					if (fmap[i][j][k] > max) max = fmap[i][j][k];
                }
                min = Math.min(min,max);
			}
		}
        out.print(min);
        
        
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}