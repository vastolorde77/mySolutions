import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.*;

public class Solution {
      static class Node{
    int data;
    Node left;
    Node right;
    int count;
    int smallerCount;
    public Node(int data){
      this.data = data;
    }
  }

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);  
    int t = 0;
    t = sc.nextInt();
    for(int test = 0;test<t;test++){
    int kj = 0;
    kj = sc.nextInt();
    int[] arr = new int[kj];
    //System.out.println(kj);
    for(int i=0;i<kj;i++){
      
      int u = 0;
      u = sc.nextInt();
      arr[i] = u;
    }
    Node dummyNode = new Node(-Integer.MAX_VALUE);
    int[] countSmaller = new int[arr.length];
    for(int i=arr.length-1;i>=0;i--){
      Node node = new Node(arr[i]);
      push(node,dummyNode);
      countSmaller[i] = node.smallerCount;
    }

    //System.out.println("\nOutput array");
    for(int i: countSmaller)
      System.out.print(i+" ");
    System.out.println("");
    }
  }

  private static void push(Node node, Node head){
    if(head != null){
      if(head.data < node.data){
        head.count++;
        //System.out.println("head.data="+head.data);
        node.smallerCount += (head.left == null)?1:(head.left.count+2);
        if(head.right == null){
          node.smallerCount--;
          head.right = node;
        } else{
          push(node, head.right);
        }
      } else if(head.data > node.data){
        head.count++;
        if(head.left == null){
          head.left = node;
          node.smallerCount--;
        } else{
          push(node,head.left);
        }
      }
    }
  }

    
}