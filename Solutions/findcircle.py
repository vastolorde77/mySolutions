from collections import defaultdict
N,M = map(int,raw_input().split())
def check(node,mat,visited,string,origin):
    if(node == origin and len(string) > 0):
        #print string
        return True
    if(not visited[node]):
        visited[node] = True
        string.append(node)
        for i in mat[node]:
                if check(i,mat,visited,string,origin):
                    return True
        visited[node] = False        
mat = [[False for i in range(N+M)]for j in range(N+M)]
neh = defaultdict(list)
for i in range(M):
    x,y = map(int,raw_input().split())
    mat[x][y] = True
    neh[x].append(y)
h = False
for j in range(M):
    visited  = [False for i in range(N+M)]
    if(check(j+1,neh,visited,[],j+1)):
        h = True
        break
print "Yes" if h else "No"