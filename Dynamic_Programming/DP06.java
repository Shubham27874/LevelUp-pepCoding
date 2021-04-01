import java.util.*;

public class DP06 {
    //https://practice.geeksforgeeks.org/problems/count-palindromic-subsequences/1
    //Longest Palindromic Subsequences

    int mod = (int)1e9 + 7;
    long countPS(String str,int i, int j, long[][] dp)
    {
        if(i >= j)
            return dp[i][j] = (i == j) ? 1 : 0;
            
        if(dp[i][j] != -1)
            return dp[i][j];
            
        long a = countPS(str, i + 1, j, dp);
        long b = countPS(str, i, j - 1, dp);
        long c = countPS(str, i + 1, j - 1, dp);
        
        return dp[i][j] = ((str.charAt(i) != str.charAt(j)) ? a + b - c + mod : a + b + 1 % mod) % mod;
    }
    
    long countPS(String str)
    {
        int n = str.length();
        long[][] dp = new long[n][n];
        for(long[] d : dp)
            Arrays.fill(d, -1);
            
        return countPS(str, 0, n - 1, dp);
    }

    //Leetcode 300
    public static int LIS_LR(int[] arr, int i, int[] dp) {
        if(i == 0)
            return dp[i] = 1;

        if (dp[i] != 0)
            return dp[i];
        int longestLen = 1;
        for (int j = i - 1; j >= 0; j--) {
            if (arr[j] < arr[i]) {
                int len = LIS_LR(arr, j, dp);
                longestLen = Math.max(longestLen, len + 1);
            }
        }

        return dp[i] = longestLen;
    }

    public static int LIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            len = Math.max(len, LIS_LR(nums, i, dp));
        }

        return len;
    }
    
    public static int LIS_DP(int[] arr, int[] dp){
        int n = arr.length;
        int len = 0;

        for(int i = 0; i < n; i++){
            dp[i] = 1;
            for(int j = i - 1; j >= 0; j--){
                if(arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            len = Math.max(len, dp[i]);
        }

        return len;
    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length + 1];
        return LIS_DP(nums, dp);
    }

    //Follow Up Question
    // minimum Number of deletion to be performed to make array sorted (array
    // contain -1e7 <= ele <= 1e7)
    public static int minDeletion(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] <= arr[i]) {        //*
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            len = Math.max(len, dp[i]);
        }

        return n - len;                  //*
    }

    // https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1
    public int maximumSum_IS(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int maxIncrSum = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }

            maxIncrSum = Math.max(maxIncrSum, dp[i]);
        }

        return maxIncrSum;
    }



}

