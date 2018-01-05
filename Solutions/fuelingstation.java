import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import static java.lang.System.out;

public class Solution {
    static  int solution = -1;

    static boolean isFinished(int [] cars){
    for(int x : cars){
      if(x == 0) return false;
    }
    return true;
  }
  
   static int minSteps (int type,int pos,int crry,int steps,int [] cars,int [] vis,String debug){
      
      debug = debug +"-"+pos;
      //out.println(pos+" | "+steps + " | " + debug);
      /*for(int x : vis){
          //out.print(x);
      }*/
      //out.println();
      if(isFinished(vis)){
          if(solution == -1){
              solution = steps;
              //out.println(debug);
              //out.println(sol.solution);
              return 0;
          }else if(steps < solution){
              solution = steps;
              //out.println(debug);
              //out.println(sol.solution);
              return 0;
          }
      }
      int r_step;     
       boolean isThere = false;
       for(int i = 1;i < cars.length-1;i++){
           if(type == cars[i]){
               isThere = true;
           }
           if(type == cars[i] && vis[i] != 1 && crry > 0){ 
               
               vis[i] = 1;
               r_step = steps + Math.abs(i-pos);
               //
               minSteps(type,i,crry-1,r_step,cars,vis,debug);
               vis[i] = 0;
           }
       }
       if(pos != 0 && pos != cars.length-1){
           
               r_step = steps + Math.abs(pos - (cars.length-1));
               minSteps(2,cars.length-1,2,r_step,cars,vis,debug);    
           
               r_step = steps + Math.abs(0 - pos);
               minSteps(1,0,2,r_step,cars,vis,debug);
           
       }else{

            if(!isThere){
                if(type == 1){
                    r_step = steps + Math.abs(pos - (cars.length-1));
                    minSteps(2,cars.length-1,2,r_step,cars,vis,debug); 
                }else{
                    r_step = steps + Math.abs(0 - pos);
                    minSteps(1,0,2,r_step,cars,vis,debug);
                }

           }
       }
       
      
       
       
       
      
      return 0;
       
   }
  
  
  public static void main(String [] args){
          Scanner s = new Scanner(System.in);
          int N = s.nextInt();
          int [] cars = new int [N+2];
          cars[0] = -1;
          cars[cars.length-1] = 3;
          for(int i = 0;i<N;i++){
            cars[i+1] = s.nextInt();
          }
          // Oh well, the object class thing doesn't bode well with recursion 
          //out.println();  
          int [] vis = new int[N+2];
          vis[0] = 1;
          vis[N+1] = 1;
          minSteps(1,0,2,0,cars,vis,"");
          out.println(solution);
          //out.println(count);    
          }
}

/*
Test Case



#14
5
2 1 1 2 1

#12
5
1 2 1 2 1

#39
8
2 2 2 2 1 2 1 1

#41
8
2 2 2 2 2 2 2 1

EDGE CASE :
#all 1s
#all 2s

*/