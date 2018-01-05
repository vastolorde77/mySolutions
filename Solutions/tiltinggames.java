
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import static java.lang.System.out;

public class Solution {

    static int sol = Integer.MAX_VALUE;
    static int [][] defdir = {{0,1},{-1,0},{1,0},{0,-1}};
    static int e1x = 0; static int e1y = 0;
    static int e2x = 0; static int e2y = 0;
    static void print(Object o){
        out.print(o);
    }
    static void println(Object o){
        out.println(o);
    }
    static boolean samemap(int [][] map,int [][] temp){
        for(int i = 0;i < map.length;i++){
            for(int j = 0;j<map[0].length;j++){
                if(map[i][j] != temp[i][j]) return false;
            }
        }
        return true;
    }
    static int [][] clonemap(int [][] map){
        int[][] temp = new int[map.length][map[0].length];
        for(int i = 0;i<map.length;i++){
            for(int j = 0;j< map[0].length;j++){
                temp[i][j] = map[i][j];
            }
        }
        return temp;

    }
    static boolean safe(int x,int y,int [][] map){
        int r = map.length;
        int c = map[0].length;
        return x < r && x >= 0 && y < c && y >= 0;
    }
    static void printmap(int [][] map){
        for(int i = 0;i < map.length;i++){
            for(int j = 0;j<map[0].length;j++){
                print(map[i][j]);
            }
            println("");
        }
    }

    static int [] move(int x1,int y1,int x2,int y2,int [][] map,int dirx,int diry){
        int r = map.length;
        int c = map[0].length;
        int xa = x1;
        int xb = x2;
        int ya = y1;
        int yb = y2;
        int vala = map[x1][y1];
        int valb = map[x2][y2];
        if(dirx != 0){
            while(( (safe(xa+dirx,ya,map) && map[xa+dirx][ya] == 0 ) || (safe(xb+dirx,yb,map) && map[xb+dirx][yb] == 0)  )){
                if(safe(xa+dirx,ya,map) && map[xa+dirx][ya] == 0){
                    xa+=dirx;
                    map[xa][ya] = vala;
                    map[xa-dirx][ya] = 0;
                }
                if(safe(xb+dirx,yb,map) && map[xb+dirx][yb] == 0){
                    xb+=dirx;
                    map[xb][yb] = valb;
                    map[xb-dirx][yb] = 0;
                }

            }
        }else if (diry != 0){
            while(( (safe(xa,ya+diry,map) && map[xa][ya+diry] == 0 ) || (safe(xb,yb+diry,map) && map[xb][yb+diry] == 0)  )){
                if(safe(xa,ya+diry,map) && map[xa][ya+diry] == 0){
                    ya+=diry;
                    map[xa][ya] = vala;
                    map[xa][ya - diry] = 0;
                }
                if(safe(xb,yb+diry,map) && map[xb][yb+diry] == 0){
                    yb+=diry;
                    map[xb][yb] = valb;
                    map[xb][yb - diry] = 0;
                }

            }
        }
        //println(xa+":"+ya+";"+xb+":"+yb);

        if(map[x1][y1] != map[xa][ya]){
            map[x1][y1] = 1;
            map[xa][ya] = 0;
        }
        if(map[x2][y2] != map[xb][yb]){
            map[x2][y2] = 2;
            map[xb][yb] = 0;
        }

        return new int [] {xa,ya,xb,yb};
    }

    static void solve(int x1,int y1,int x2,int y2,int [][] map,int [][] vis,int steps,String debug){
        //println(x1+":"+y1+" "+x2+":"+y2);
        //println(debug);
        if(steps > 8){
            //println(steps);
            return;
        }

        if(map[e1x][e1y] == 1 && map[e2x][e2y] == 2){
            //println("found");
            //println(debug);
            if(sol > steps) sol = steps;
            return;
        }
        int ix = 0;
        for(int [] p : defdir){
            map[x1][y1] = 1;
            map[x2][y2] = 2;
            int [][] temap = clonemap(map);

            int [] t = move(x1,y1,x2,y2,temap,p[0],p[1]);
            if(!samemap(map,temap)){
//                printmap(map);
//                println("/");
//                printmap(temap);
            }

            //map[t[0]][t[1]] = 0;
            //map[t[2]][t[3]] = 0;
            map[x1][y1] = 1;
            map[x2][y2] = 2;
            if(vis[t[0]][t[1]] != 1 || vis[t[2]][t[3]] != 1) {

                //printmap(map);

                map[x1][y1] = 0;
                map[x2][y2] = 0;
                int ta = map[t[0]][t[1]];
                int tb = map[t[2]][t[3]];
                map[t[0]][t[1]] = 1;
                map[t[2]][t[3]] = 2;
                vis[t[0]][t[1]] = 1; vis[t[2]][t[3]] = 1;
                solve(t[0], t[1], t[2], t[3], map, vis, steps + 1,debug+ix);
                vis[t[0]][t[1]] = 0; vis[t[2]][t[3]] = 0;
                map[t[0]][t[1]] = 0;
                map[t[2]][t[3]] = 0;
            }
            ix++;
        }

    }




    public static void main(String [] args){
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        for(int tx = 0;tx<N;tx++){
            sol = Integer.MAX_VALUE;
            int r = s.nextInt();
            int c = s.nextInt();
            int [][] map = new int[r][c];
            int [][] vis = new int[r][c];
            int s1x = 0;
            int s1y = 0;
            int s2x = 0;
            int s2y = 0;

            for(int x = 0; x < r;x++){
                for(int y = 0;y<c;y++){
                    map[x][y] = s.nextInt();

                    switch(map[x][y]){
                        case 1:
                            s1x = x;s1y = y;
                            break;
                        case 2:
                            s2x = x;s2y = y;
                            break;
                        case -1:
                            e1x = x;e1y = y;
                            break;
                        case -2:
                            e2x = x;e2y = y;
                            break;
                    }
                    if(map[x][y] < 0) map[x][y] = 0;
                }
            }

            solve(s1x,s1y,s2x,s2y,map,vis,0,"");


            if(map[e1x][e1y] == 1 && map[e2x][e2y] == 2){
                println("SOL");
            }
            println(sol == Integer.MAX_VALUE ? -1 : sol);
            //printmap(map);

        }


    }
}

/* TEST CASE

Output
----------------------------------------------------------------------------------------------------------------------------------------------
#1 3
#2 4
#3 -1
#4 5
#5 8


INPUT
----------------------------------------------------------------------------------------------------------------------------------------------
5
5 5
0 1 0 0 0
0 0 0 0 0
0 2 0 9 0
0 0 0 0 0
0 9 -1 0 -2
4 6
-1 -2 1 0 9 0
0 2 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
7 4
0 0 0 0
0 0 0 9
2 0 1 0
0 0 9 0
0 9 0 0
0 0 0 0
-1 0 -2 0
4 7
0 0 0 0 0 0 0
0 0 1 9 0 0 0
0 0 0 0 0 0 0
9 2 -1 9 0 0 -2
10 10
0 0 0 0 0 0 0 9 -1 0
0 9 -2 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 9
0 0 0 9 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0
0 0 0 9 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 2 0 0 0 0 9 9
9 0 0 0 0 0 9 0 0 0
*/