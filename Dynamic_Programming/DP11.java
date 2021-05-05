import java.util.*;

public class DP11 {
    //Leetcode 132
    public int minCut_memo(String s, int si, boolean[][] isPalindrome, int[] dp) {
        if(isPalindrome[si][s.length() - 1]){
            return dp[si] = 0;
        }

        if(dp[si] != -1){
            return dp[si];
        }
        
        int minAns = (int)1e9;
        for(int cut = si; cut < s.length(); cut++){
            if(isPalindrome[si][cut]){
                minAns = Math.min(minAns, minCut_memo(s, cut + 1, isPalindrome, dp) + 1);
            }
        }

        return dp[si] = minAns;
    }

    public int minCut_DP(String s, int SI, boolean[][] isPalindrome, int[] dp) {
        
        for(int si = s.length() - 1; si >= 0; si--){
            if(isPalindrome[si][s.length() - 1]){
                dp[si] = 0;
                continue;
            }

            int minAns = (int)1e9;
            for(int cut = si; cut < s.length(); cut++){
                if(isPalindrome[si][cut]){
                    minAns = Math.min(minAns, dp[cut + 1] + 1);
                }
            }

            dp[si] = minAns;
        }

        return dp[SI];
    }
    
    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];

        for(int gap = 0; gap < n; gap++){
            for(int i = 0, j = gap; j < n; i++, j++){
                if(gap == 0)
                    isPalindrome[i][j] = true;
                else if(gap == 1)
                    isPalindrome[i][j] = s.charAt(i) == s.charAt(j);
                else 
                    isPalindrome[i][j] = s.charAt(i) == s.charAt(j) && isPalindrome[i + 1][j - 1];
            }
        }

        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        return minCut_memo(s, 0, isPalindrome, dp);
    }

    //Leetcode 312
    public int maxCoins(int[] nums, int si, int ei, int[][] dp) {
        if(dp[si][ei] != -1){
            return dp[si][ei];
        }
        
        int lval = (si - 1) == -1 ? 1 : nums[si - 1];
        int rval = (ei + 1) == nums.length ? 1 : nums[ei + 1];
        
        int maxAns = 0;
        for(int cut = si; cut <= ei; cut++){
            int lans = (cut == si) ? 0 : maxCoins(nums, si, cut - 1, dp);
            int rans = (cut == ei) ? 0 : maxCoins(nums, cut + 1, ei, dp);
            
            maxAns = Math.max(maxAns, lans + lval * nums[cut] * rval + rans);
        }
        
        return dp[si][ei] = maxAns;
    }
    
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for(int[] d : dp)
            Arrays.fill(d, -1);
        
        return maxCoins(nums, 0, n - 1, dp);
    }

    //Leetcode 1039
    public int minScoreTriangulation(int[] values, int si, int ei, int[][] dp) {
        if(ei - si <= 1){
            return dp[si][ei] = 0;        
        }
        
        if(dp[si][ei] != -1){
            return dp[si][ei];
        }
        
        int minAns = (int)1e9;
        for(int cut = si + 1; cut < ei; cut++){
            int lans = minScoreTriangulation(values, si, cut, dp);
            int rans = minScoreTriangulation(values, cut, ei, dp);
            
            minAns = Math.min(minAns, lans + values[si] * values[cut] * values[ei] + rans);
            
        }
        
        return dp[si][ei] = minAns;
    }
    
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        
        for(int[] d : dp)
            Arrays.fill(d, -1);
        
        return minScoreTriangulation(values, 0, n - 1, dp);
    }

    //https://www.geeksforgeeks.org/boolean-parenthesization-problem-dp-37/
    public static class pairboolean {
        int trueWays = 0;
        int falseWays = 0;
        
        pairboolean(int trueWays, int falseWays) {
            this.trueWays = trueWays;
            this.falseWays = falseWays;
        }
    }

    public static pairboolean Evaluate(pairboolean left, pairboolean right, char operator){
        int mod = 1003;
        int TotalWays = ((left.trueWays + left.falseWays) % mod * (right.trueWays + right.falseWays) % mod) % mod;
        
        pairboolean ans = new pairboolean(0, 0);
        if(operator == '&'){
            ans.trueWays = (left.trueWays * right.trueWays) % mod;
            ans.falseWays = (TotalWays - ans.trueWays + mod) % mod;
        } else if(operator == '|'){
            ans.falseWays = (left.falseWays * right.falseWays) % mod;
            ans.trueWays = (TotalWays - ans.falseWays + mod) % mod;
        } else {
            ans.trueWays = (left.trueWays * right.falseWays) % mod + (left.falseWays * right.trueWays) % mod;
            ans.falseWays = (TotalWays - ans.trueWays + mod) % mod;
        }

        return ans;
    }

    public static pairboolean booleanPar(String str, int si, int ei, pairboolean[][] dp){
        if(si == ei){
            char ch = str.charAt(si);
            return new pairboolean(ch == 'T' ? 1 : 0, ch == 'F' ? 1 : 0);
        }
        
        if(dp[si][ei] != null)
            return dp[si][ei];
        
        pairboolean myAns = new pairboolean(0, 0);
        for(int cut = si + 1; cut < ei; cut += 2){
            char operator = str.charAt(cut);
            pairboolean lans = booleanPar(str, si, cut - 1, dp);
            pairboolean rans = booleanPar(str, cut + 1, ei, dp);

            pairboolean recAns = Evaluate(lans, rans, operator);
            
            myAns.trueWays = (myAns.trueWays + recAns.trueWays) % 1003;
            myAns.falseWays = (myAns.falseWays + recAns.falseWays) % 1003;
        }

        return dp[si][ei] = myAns;
    }

    public static int countWays(int N, String S){
        pairboolean[][] dp = new pairboolean[N][N];
        pairboolean ans = booleanPar(S, 0, N - 1, dp);

        return ans.trueWays;
    }

}
