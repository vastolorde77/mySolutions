R,C = map(int,raw_input().split())
dist = [[0 for _ in range(C)] for _ in range(R)]
dis = [[0 for _ in range(C)] for _ in range(R)]
mat = []

for _ in range(R):
    ar = raw_input()
    ar = [str(ar[x]) for x in range(C)]
    mat.append(ar)

for x in range(R):
    for y in range(C):
        if mat[x][y] in ["_","?"]:
            dist[x][y] = 1
            if x > 0 and y > 0:
                if mat[x-1][y] in ["_","?"] and mat[x-1][y-1] in ["_","?"] and mat[x][y-1] in ["_","?"]:
                    dist[x][y] = min(dist[x-1][y],dist[x-1][y-1],dist[x][y-1]) + 1

for x in range(R-1,-1,-1):
    for y in range(C-1,-1,-1):
        m = dist[x][y]
        for i in range(m):
            for j in range(m):
                dis[x-i][y-j] = max(dis[x-i][y-j],m)
sol = 10**9                
for x in range(R):
    for y in range(C):
        if mat[x][y] == "?" or dis[x][y] == 0:
            continue
        sol = min(sol,dis[x][y])   
print sol     