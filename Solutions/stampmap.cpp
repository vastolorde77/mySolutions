#include <iostream>
using namespace std;

int N, M;
char map[100][100]; 
int  map2[100][100];
int  map3[100][100];


int main() {
    
    cin >> N;
    cin >> M;
    
    for(int i = 0;i<N;i++){
        for(int j =0;j<M;j++){
            cin >> map[i][j];
        }
    }
   
    for(int i = 0;i<N;i++){
        for(int j =0;j<M;j++){
            
           if(map[i][j] == '#')map2[i][j] = 0;
           else if(i-1<0 || j-1< 0 )map2[i][j] = 1;
           else if(map[i-1][j] == '#' || map[i-1][j-1] == '#' || map[i][j-1] == '#')map2[i][j] = 1;
           else{
               int nn = 9999;
               if(map2[i-1][j] < nn) nn = map2[i-1][j];
               if(map2[i-1][j-1] < nn) nn = map2[i-1][j-1];
               if(map2[i][j-1] < nn) nn = map2[i][j-1];
               
               map2[i][j] = nn+1;
               
           } 
            
        }
    }
    for(int i = 0;i<N;i++){
        for(int j =0;j<M;j++){
            map3[i][j] = 0;
        }
    }
    for(int i = N-1;i>=0;i--){
        for(int j =N-1;j>=0;j--){            
            int dim = map2[i][j];
            for(int x = i-dim+1;x<=i;x++){
                for(int y = j-dim+1;y<=j;y++){
                    if(map3[x][y] < dim){
                        map3[x][y] = dim;
                    }
                }
                
            }        
        }
        
    }
    int min = 9999;
    for(int i = 0;i<N;i++){
        for(int j =0;j<M;j++){
          if(map3[i][j] < min && map3[i][j] != 0 && map[i][j] != '?'){
              min = map3[i][j];
          }        
        }
    }
    cout << min;
    return 0;
}