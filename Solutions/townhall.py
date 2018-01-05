from copy import copy,deepcopy
def issafe(a,x):
    if(x < 0 or x >= len(a)):
        return False
    return True
def fu(st1,st2,a):
      while(st1):
        p = st1.pop()
        x = p[0]
        y = p[1]
        if issafe(a,x-1) and issafe(a,y) and a[x-1][y] != 5 and a[x-1][y] != 0 :
                st2.append([x-1,y])
                a[x-1][y] = 5
        if issafe(a,x) and issafe(a,y-1) and a[x][y-1] != 5 and a[x][y-1] != 0:
                st2.append([x,y-1])
                a[x][y-1] = 5
        if issafe(a,x+1) and issafe(a,y) and a[x+1][y] != 5 and a[x+1][y] != 0:
                st2.append([x+1,y])
                a[x+1][y] = 5
        if issafe(a,x) and issafe(a,y+1) and a[x][y+1] != 5 and a[x][y+1] != 0:
                st2.append([x,y+1])
                a[x][y+1] = 5
                
                
N,K = map(int,raw_input().split())
cor = []
for i in range(K):
    x,y = map(int,raw_input().split())
    cor.append([x,y])
mat = []
sol = []
for _ in range(N):
    mat.append(map(int,raw_input().split()))
for p in cor:
    mat[p[0]-1][p[1]-1] = 2
for m in range(N):
    for i in range(N):
        if  mat[m][i] == 1 :
            st1 = [[m,i]]
            st2 = []
            count = 0
            temp = [row[:] for row in mat]
            
            temp[m][i] = 5
            allfound = False
            while(not allfound and st1 != []):
                fu(st1,st2,temp)
                st1 = st2
                st2 = []
                count+=1
                allfound = True
                for v in cor:
                    if temp[v[0]-1][v[1]-1] == 2:
                        allfound = False
                    
            #for _ in temp:
               # print _
            if(not allfound):
                #print 'invalid'
                continue
            sol.append(count)
            
print min(sol)