import sys
import itertools
T = int(raw_input())
for test in range(T):
    N = int(raw_input())
    sums = []
    jsum = []
    ar1 =[]
    ar2=[]
    ar3=[]
    fsum = 0
    for _ in range(N):
        kl = map(int,raw_input().split())
        ar1.append(kl[0])
        ar2.append(kl[1])
        ar3.append(kl[2])
    dp = [ar1,ar2,ar3]
    
    for k in range(1,N):
        dp[0][k] = min(dp[1][k-1]+dp[0][k],dp[2][k-1]+dp[0][k])
        dp[1][k] = min(dp[0][k-1]+dp[1][k],dp[2][k-1]+dp[1][k])
        dp[2][k] = min(dp[0][k-1]+dp[2][k],dp[1][k-1]+dp[2][k])
    print min(dp[0][N-1],dp[1][N-1],dp[2][N-1])
        
  