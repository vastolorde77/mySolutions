import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.util.regex.*;

public class Solution {
    
    static void walk2(int wdis,int sdis,int [] pos,HashMap<int[],int[]> map,int [] end,ArrayList<Integer> path,int mini,int ole,int recmax){
           int sols = wdis + (Math.abs(pos[0]-end[0])+Math.abs(pos[1]-end[1]));
           //System.out.println(sols);
           mini = Math.min(mini,sols);
           if ( ole > recmax || wdis > mini  ){
               return;
           }
           for(Map.Entry<int [],int []> temp : map.entrySet()){
               int [] key = temp.getKey();
               int [] value = temp.getValue();
               int stx = key[0];int sty = key[1];
               int endx = value[0];int endy = value[1];
               
               walk2(wdis+(Math.abs(pos[0]-stx)+Math.abs(pos[1]-sty)),sdis,value,map,end,path,mini,ole+1,recmax);
               walk2(wdis+(Math.abs(pos[0]-endx)+Math.abs(pos[1]-endy)),sdis,key,map,end,path,mini,ole+1,recmax);
           }
           if(path.contains(sols)){
               return;
           } 
           
            path.add(sols);
    }


    public static void main(String[] args) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int startX = s.nextInt();int startY = s.nextInt();
        int endX = s.nextInt();int endY = s.nextInt();
        int N = s.nextInt();
        int mX,mY;
        int normal = Math.abs(endX-startX)+Math.abs(endY-startY);
        mX = 0;
        mY = 0;
        int max = 0;
        HashMap<int [],int []> eX = new HashMap<int [],int[]>();
        //HashMap<Integer,Integer> eY = new HashMap<Integer,Integer>();
        for(int i = 0;i<N;i++){
            int sX,sY,weX,weY;
            sX = s.nextInt();sY = s.nextInt();weX = s.nextInt();weY = s.nextInt();
            int [] st1 = new int[2];
            st1[0] = sX;st1[1] = sY;
            int [] ed1 = new int[2];
            ed1[0] = weX;ed1[1] = weY;
            eX.put(st1,ed1);
            int temp = Math.abs(weX-sX)+Math.abs(weY-sY);
            if(temp>max){max = temp;mX = sX;mY = sY;}
        }
        int [] path = new int[99999];
        path[0] = startX;
        Set<Integer> vis = new HashSet<Integer>();
        ArrayList<Integer> pwath = new ArrayList<Integer>();
        int mawx = 0;
        int mini = Math.abs(endX - startX);
        int [] endpos = new int [2];
        endpos[0] = endX;endpos[1] = endY;
        for(Map.Entry<int [],int []> temp : eX.entrySet()){
            int [] key = temp.getKey();
            int [] value = temp.getValue();
            int fsx = key[0];int fsy = key[1];
            int esx = value[0];int esy = value[1];
            
    walk2(Math.abs(fsx-startX)+Math.abs(fsy-startY),Math.abs(0-0),value,eX,endpos,pwath,mini,0,N*2);
            int te = Integer.MAX_VALUE;
            int lm = pwath.size();
            for(int g = 0;g<lm;g++){
                if(pwath.get(g)< te){
                    te = pwath.get(g);
                }
            }
            mini = te;
            walk2(Math.abs(esx-startX)+Math.abs(esy-startY),Math.abs(0-0),key,eX,endpos,pwath,mini,0,N*2);
            te = Integer.MAX_VALUE;
            lm = pwath.size();
            for(int g = 0;g<lm;g++){
                if(pwath.get(g)< te){
                    te = pwath.get(g);
                }
            }
            mini = te;
            
            
        }
        mawx = Integer.MAX_VALUE;
        int H = pwath.size();
        for(int y = 0;y<H;y++){
            if(pwath.get(y) < mawx){
                mawx = pwath.get(y);
            }
        }
        
        System.out.println(Math.min(normal,mawx));
        
    }
}