import java.util.*;

public class DP02 {
    // Leetcode 70
    public static int climbStairs_memo(int n, int[] dp) {
        if(n <= 1){
            return dp[n] = 1;
        }

        if(dp[n] != -5)
            return dp[n];

        return dp[n] = climbStairs_memo(n - 1, dp) + climbStairs_memo(n - 2, dp);
    }

    public static int climbStairs_DP(int N, int[] dp) {
        
        for(int n = 0; n < N; n++) {
            if(n <= 1){
                dp[n] = 1;
                continue;
            }

            dp[n] = dp[n - 1] + dp[n - 2]; //climbStairs_memo(n - 1, dp) + climbStairs_memo(n - 2, dp);
        }

        return dp[N];
    }

    public static int climbStairs(int n) {
        int[] dp = new int[n + 1];
        return climbStairs_memo(n, dp);
    }

    public int minCostClimbingStairs(int n, int[] cost, int[] dp) {
        if(n <= 1)
            return dp[n] = cost[n];

        if(dp[n] != -1)
            return dp[n];    
        
        int minCost = Math.min(minCostClimbingStairs(n - 1, cost, dp), minCostClimbingStairs(n - 2, cost, dp));

        return dp[n] = minCost + (n == cost.length ? 0 : cost[n]);
    }

    public int minCostClimbingStairs_DP(int N, int[] cost, int[] dp) {
        
        for(int n = 0; n <= N; n++){
            if(n <= 1) {
                dp[n] = cost[n];
                continue;
            }

            int minCost = Math.min(dp[n - 1] ,dp[n - 2]);

            dp[n] = minCost + (n == cost.length ? 0 : cost[n]);
        }

        return dp[N];
    }

    public static int minCostClimbingStairs_opti(int n, int[] cost){
        int a = cost[0];
        int b = cost[1];

        for(int i = 2; i < n; i++) {
            int minCost = Math.min(a,b) + cost[i];
            a = b;
            b = minCost;
        }

        return Math.min(a, b);
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        //Array.fill(dp, -1);

        //return minCostClimbingStairs(n, cost, dp);
        //return minCostClimbingStairs_DP(n, cost, dp);
        return minCostClimbingStairs_opti(n, cost);
    }


    public static void main(String[] args){

    }

}