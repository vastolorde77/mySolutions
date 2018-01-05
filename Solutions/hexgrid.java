
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import static java.lang.System.out;

public class Solution {


    static int sol = -1;
    static int[][] odd = {{1,0},{0,1},{0,-1},{-1,0},{-1,-1},{-1,1}};
    static int[][] even = {{1,0},{0,1},{0,-1},{-1,0},{1,1},{1,-1}};

    static void circ(int[][] map,int x,int y,int [][] vis,int steps,int cost,boolean iseven,String debug){
        //debug+= map[x][y]+" ";
        //if(vis[x][y] == 0) return;
        if(steps == 4){
            if(sol < cost*cost) {
                sol = cost * cost;

            }
            //if(cost*cost > 2250000)println((cost*cost)+"-"+debug);
            return;
        }
        int [] bis = new int[4];
        int f = 0;
        for(String s : debug.split(" ")){
            bis[f++] = Integer.parseInt(s);
        }

        if(!iseven){
            for(int [] p : even){
                int i = x+p[0];
                int j = y+p[1];
                if(safe(i,j,map[0].length,map.length)){
                    boolean in = false;
                    for(int l : bis){
                        if(l == map[i][j]){
                            in = true;
                        }
                    }
                    if(!in){
                        //vis[i][j] = 1;
                        circ(map,x,y,vis,steps+1,cost+map[i][j],iseven,debug+ map[i][j]+" ");
                        //vis[i][j] = 0;
                    }
                }
            }
        }else{
            for(int [] p : odd){
                int i = x+p[0];
                int j = y+p[1];
                if(safe(i,j,map[0].length,map.length)){
                    boolean in = false;
                    for(int l : bis){
                        if(l == map[i][j]){
                            in = true;
                        }
                    }
                    if(!in){
                        //vis[i][j] = 1;
                        circ(map,x,y,vis,steps+1,cost+map[i][j],iseven,debug+ map[i][j]+" ");
                        //vis[i][j] = 0;
                    }
                }
            }
        }

    }

    public static void rec(int x,int y,int [][] map,int [][] vis,int cost,int step,String debug){
        debug+= map[x][y]+" ";
        if(step == 4){
            //println(debug);
            sol = Math.max(sol,cost*cost);

            return;
        }
        vis[x][y] = 1;
        if(y%2 != 0){

            int [][] circ_vis = new int[map.length][map[0].length];
            circ_vis[x][y] = 1;
            int temp = step;
            circ(map,x,y,circ_vis,temp,cost,false,debug);
            for(int[] p : even){
                int i = x+p[0];
                int j = y+p[1];
                if(safe(i,j,map[0].length,map.length)){
                    if(vis[i][j] == 0) {
                        rec(i,j,map,vis,cost+map[i][j],step+1,debug);
                    }
                }
            }
        }else{
            int [][] circ_vis = new int[map.length][map[0].length];
            circ_vis[x][y] = 1;
            int temp = step;
            circ(map,x,y,circ_vis,temp,cost,true,debug);
            for(int[] p : odd){
                int i = x+p[0];
                int j = y+p[1];
                if(safe(i,j,map[0].length,map.length)){
                    if(vis[i][j] == 0) rec(i,j,map,vis,cost+map[i][j],step+1,debug);
                }
            }
        }
        vis[x][y] = 0;
    }

    public static boolean safe(int x,int y,int r,int c){
        return x < c && x >= 0 && y < r && y >= 0;
    }
    public static void print(Object b){
        out.print(b);
    }
    public static void println(Object b){
        out.println(b);
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int _ = 0; _ < n; _++){
            sol = -1;
            int r = sc.nextInt();
            int c = sc.nextInt();
            int[][] odd = {{1,0},{0,1},{0,-1},{-1,0},{-1,-1},{-1,1}};
            int[][] even = {{1,0},{0,1},{0,-1},{-1,0},{1,1},{1,-1}};
            int[][] map = new int[c][r];
            int[][] vis = new int[c][r];
            for(int i = 0; i < c;i++){
                for(int j = 0; j < r; j++){
                    map[i][j] = sc.nextInt();
                }
            }

            for(int i = 0; i < c;i++){
                for(int j = 0; j < r; j++){
                    rec(i,j,map,vis,map[i][j],1,"");
                }
            }
            //circ(map,1,2,vis,1,map[1][2],false,"debug : ");
            println(sol);
        }

        /*
        for(int i = 0; i < 3;i++){
            for(int j = 0;j<5;j++){
                if(j%2 != 0){
                   print(i+"-"+j+"  ");
                   for(int [] p : even){
                       if(safe(i+p[0],j+p[1],5,3)){
                           print((i+p[0])+":"+(j+p[1])+" ");
                       }
                   }
                }else{
                   print(i+"-"+j+"  ");
                   for(int [] p : odd){
                       if(safe(i+p[0],j+p[1],5,3)){
                           print((i+p[0])+":"+(j+p[1])+" ");
                       }
                   }
                }
                println("");
            }
        }*/

    }
}

/*Test case
5
5 3
300 410 150 55 370
120 185 440 190 450
165 70 95 420 50
5 5
356 55 41 453 12
401 506 274 506 379
360 281 421 311 489
425 74 276 371 164
138 528 461 477 470
13 3
197 51 443 274 47 552 160 96 501 102 469 318 308
516 128 506 471 381 418 328 517 380 78 569 58 90
113 238 179 444 541 27 444 62 264 93 245 353 37
7 11
292 182 586 607 259 190 239
511 716 425 367 511 462 714
593 713 231 60 118 442 82
626 577 579 682 136 176 681
240 23 410 193 230 729 109
453 231 287 383 444 578 409
729 401 408 330 213 574 54
684 224 75 62 660 472 227
606 37 473 487 222 185 476
84 477 158 94 141 484 122
616 333 302 626 29 99 674
15 15
488 923 92 659 783 908 167 332 467 205 457 371 536 189 642
676 729 520 687 276 13 709 305 315 621 19 606 201 722 671
631 829 973 318 487 140 411 633 530 981 594 372 787 586 895
520 938 375 770 495 310 59 820 840 785 457 454 967 178 507
498 368 377 326 247 79 875 38 778 800 205 186 131 543 948
672 530 848 342 397 751 192 265 763 447 869 223 950 636 34
669 929 802 500 979 978 322 185 598 618 663 192 746 289 44
77 271 943 874 211 532 441 567 396 141 527 286 755 95 206
458 803 319 490 384 736 328 977 954 651 975 472 405 344 189
624 725 838 159 624 269 400 855 63 924 349 711 473 115 446
937 359 820 851 629 698 437 834 18 257 632 534 153 478 908
205 875 185 508 373 826 432 487 522 10 663 870 711 566 941
773 663 954 237 166 957 722 198 346 337 708 592 443 809 41
634 174 193 733 800 227 418 503 903 405 261 805 234 461 191
176 891 203 825 575 508 627 845 610 814 263 159 719 450 903
*/