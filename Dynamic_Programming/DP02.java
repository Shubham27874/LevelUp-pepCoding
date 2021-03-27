import java.util.*;

public class DP02 {
    public static void print1D(int[] arr){
        for(int ele : arr){
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void print2D(int[][] arr){
        for(int[] row : arr){
            print1D(row);
        }
    }
      
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

    //Leetcode 746
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

    // https://practice.geeksforgeeks.org/problems/friends-pairing-problem5425/1
    // Friends Pairing Problem

    long mod = (int) 1e9 + 7;

    public long countFriendsPairings_memo(int n, long[] dp) {

        if (n <= 1) {
            return dp[n] = 1;
        }

        if (dp[n] != 0)
            return dp[n];

        long single = countFriendsPairings_memo(n - 1, dp);
        long pairup = countFriendsPairings_memo(n - 2, dp) * (n - 1);

        return dp[n] = (single % mod + pairup % mod) % mod;
    }

    public long countFriendsPairings_dp(int N, long[] dp) {

        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }

            long single = dp[n - 1]; // countFriendsPairings_memo(n-1,dp);
            long pairup = dp[n - 2] * (n - 1); // countFriendsPairings_memo(n-2,dp) * (n-1);

            dp[n] = (single % mod + pairup % mod) % mod;
        }

        return dp[N];
    }

    public static long printFriendsPairing(String friends, String ans) {
        if (friends.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        char ch = friends.charAt(0);
        long count = 0;
        count += printFriendsPairing(friends.substring(1), ans + ch + " ");
        for (int i = 1; i < friends.length(); i++) {
            String rstr = friends.substring(1, i) + friends.substring(i + 1);
            count += printFriendsPairing(rstr, ans + ch + friends.charAt(i) + " ");
        }

        return count;
    }

    public static void countFriendsPairings(int n) {
        // long[] dp = new long[n + 1];
        // return countFriendsPairings_memo(n, dp);
        System.out.println(printFriendsPairing("ABCDEF", ""));
    }

    //Goldmine Problem
    public static int goldMine_memo(int r, int c, int[][] mat, int[][] dp, int[][] dir) {
        if (c == mat[0].length - 1) {
            return dp[r][c] = mat[r][c];
        }

        if (dp[r][c] != -1)
            return dp[r][c];

        int maxGold = 0;
        for (int d = 0; d < 3; d++) {
            int x = r + dir[d][0];
            int y = c + dir[d][1];
            if (x >= 0 && x < mat.length) {
                maxGold = Math.max(maxGold, goldMine_memo(x, y, mat, dp, dir));
            }
        }

        return dp[r][c] = maxGold + mat[r][c];
    }

    public static int goldMine_dp(int[][] mat, int[][] dp, int[][] dir) {
        int n = mat.length;
        int m = mat[0].length;

        for (int c = m - 1; c >= 0; c--) {
            for (int r = n - 1; r >= 0; r--) {

                if (c == mat[0].length - 1) {
                    dp[r][c] = mat[r][c];
                    continue;
                }

                int maxGold = 0;
                for (int d = 0; d < 3; d++) {
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];
                    if (x >= 0 && x < n) {
                        maxGold = Math.max(maxGold, dp[x][y]);
                    }
                }

                dp[r][c] = maxGold + mat[r][c];
            }
        }

        int maxGold = 0;
        for (int i = 0; i < mat.length; i++) {
            maxGold = Math.max(maxGold, dp[i][0]);
        }

        return maxGold;
    }

    public static void goldMine() {
        int[][] mat = { { 1, 3, 1, 5 }, { 2, 2, 4, 1 }, { 5, 0, 2, 3 }, { 0, 6, 1, 2 } };

        int[][] dp = new int[mat.length][mat[0].length];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int[][] dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } };

        int maxGold = 0;
        for (int i = 0; i < mat.length; i++) {
            maxGold = Math.max(maxGold, goldMine_memo(i, 0, mat, dp, dir));
        }

        System.out.println(goldMine_dp(mat, dp, dir));
        print2D(dp);
        // System.out.println(maxGold);
    }

    public static void main(String[] args){

    }

}