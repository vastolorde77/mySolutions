import java.util.*;
import static java.lang.System.out;

public class Solution {
static void println(Object o){
    out.println(o);
}
public static class Coordinate{
    int x;
    int y;
    public  Coordinate(int x,int y){
        this.x = x;
        this.y = y;
    }
    
}
static class Movement{
    Coordinate start;
    Coordinate end;
    boolean isV;
    boolean isH;
    public Movement(Coordinate start,Coordinate end){
        this.start = start;
        this.end = end;
        this.isH = start.x == end.x;
        this.isV = !this.isH;
    }
}
static boolean bw(int x,int a,int b){
    return (a < x && x < b) || (b < x && x < a);
}
static boolean overlap(int a1,int a2,int b1,int b2){
    if(bw(a1,b1,b2)) return true;
    if(bw(a2,b1,b2)) return true;
    if(bw(b1,a1,a2)) return true;
    if(bw(b2,a1,a2)) return true;
    if(a1 == b1 && a2 == b2) return true;
    if(a1 == b2 && a2 == b1) return true;
    return false;
}
static boolean compare(Movement a,Movement b){
    //Overlap
    if(a.isH && b.isH && a.start.x == b.start.x){
        if(overlap(a.start.y,a.end.y,b.start.y,b.end.y)){
            return true;
        }
    }
    if(!a.isH && !b.isH && a.start.y == b.start.y){
        if(overlap(a.start.x,a.end.x,b.start.x,b.end.x)){
            return true;
        }
    }
    //Intersect
    if(bw(a.start.x,b.start.x,b.end.x) && bw(b.start.y,a.start.y,a.end.y) || bw(a.start.y,b.start.y,b.end.y) && bw(b.start.x,a.start.x,a.end.x)){
        return true;
    }
    return false;
}
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [][] compass = {{1,0},{-1,0},{0,1},{0,-1}};
        Movement [] mlist = new Movement[n];
        Coordinate f = new Coordinate(0,0);
        int [] dis = new int[n];
        int [] dir = new int[n];
        for(int i = 0;i<n;i++){
            dir[i] = sc.nextInt();
            dis[i] = sc.nextInt();
        }
        Coordinate ff = new Coordinate(0+dis[0]*compass[dir[0]-1][0],0+dis[0]*compass[dir[0]-1][1]);
        mlist[0] = new Movement(f,ff);
        boolean found = false;
        int sol = -1;
        for(int i = 1;i<n;i++){
            Coordinate s = mlist[i-1].end;
            Coordinate e = new Coordinate(s.x+dis[i]*compass[dir[i]-1][0],s.y+dis[i]*compass[dir[i]-1][1]);
            Movement m = new Movement(s,e);
            for(int j = i-1;j >= 0;j--){
                if(compare(mlist[j],m)){
                    found = true;break;
                }
                continue;
            }
            if(found){
                sol = i+1;
                break;
            }
            mlist[i] = m;
            //println(mlist[i].start.x+" "+mlist[i].start.y+" "+mlist[i].end.x+" "+mlist[i].end.y);
        }
        println(sol);
    }
}