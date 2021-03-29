import java.util.*;

public class DP05 {
    //Leetcode 1458
    public int maxDotProduct_memo(int[] nums1, int[] nums2, int n, int m, int[][] dp) {
        if(n == 0 || m == 0){
            return dp[n][m] = -(int)1e7;
        }
        
        if(dp[n][m] != -(int)1e8)
            return dp[n][m];
        
        int val = nums1[n - 1] * nums2[m -1];
        int acceptbothnum = maxDotProduct_memo(nums1, nums2, n - 1, m - 1, dp) + val;
        int a = maxDotProduct_memo(nums1, nums2, n - 1, m, dp);
        int b = maxDotProduct_memo(nums1, nums2, n, m - 1, dp);
        
        return dp[n][m] = Math.max(Math.max(acceptbothnum, val),Math.max(a, b));
        
    }
    
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        
        int[][] dp = new int[n + 1][m + 1];
        for(int[] d : dp) 
            Arrays.fill(d, -(int)1e8);
        
        return maxDotProduct_memo(nums1, nums2, n, m, dp);
    }

    //Leetcode 72
    public int minDistance_memo(String word1, String word2, int n, int m, int[][] dp) {
        if(n == 0 || m == 0){
            return dp[n][m] = (n != 0) ? n : m;
        }
        
        if(dp[n][m] != -1)
            return dp[n][m];
        
        int insert = minDistance_memo(word1, word2, n, m - 1, dp);
        int delete = minDistance_memo(word1, word2, n - 1, m, dp);
        int replace = minDistance_memo(word1, word2, n - 1, m - 1, dp);
        
        if(word1.charAt(n - 1) == word2.charAt(m - 1))
            dp[n][m] = replace;
        else
             dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;
        
        return dp[n][m];
    } 
    //Follow Up Question
    //Cost : {insertCost, deleteCost, replaceCost}
    public int minDistanceWithCost_memo(String word1, String word2, int[]cost, int n, int m, int[][] dp) {
        if(n == 0 || m == 0){
            return dp[n][m] = (n != 0) ? n * cost[1] : m * cost[0];
        }
        
        if(dp[n][m] != -1)
            return dp[n][m];
        
        int insert = minDistance_memo(word1, word2, n, m - 1, dp) * cost[0];
        int delete = minDistance_memo(word1, word2, n - 1, m, dp) * cost[1];
        int replace = minDistance_memo(word1, word2, n - 1, m - 1, dp) * cost[2];
        
        if(word1.charAt(n - 1) == word2.charAt(m - 1))
            dp[n][m] = replace;
        else
             dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;
        
        return dp[n][m];
    } 

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        int[][] dp = new int[n + 1][m + 1];
        for(int[] d : dp)
            Arrays.fill(d, -1);
        
        return minDistance_memo(word1, word2, n, m, dp);
    }

    // Leetcode 44
    // -1 -> default, 0 -> false, 1 -> true
    public int isMatch(String s, String p, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            if (n == 0 && m == 0)
                return dp[n][m] = 1;
            else if (m == 1 && p.charAt(m - 1) == '*')
                return dp[n][m] = 1;
            else
                return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        char ch1 = s.charAt(n - 1);
        char ch2 = p.charAt(m - 1);

        if (ch1 == ch2 || ch2 == '?')
            return dp[n][m] = isMatch(s, p, n - 1, m - 1, dp);
        else if (ch2 == '*') {
            boolean res = false;
            res = res || (isMatch(s, p, n - 1, m, dp) == 1); // map to character
            res = res || (isMatch(s, p, n, m - 1, dp) == 1); // map to empty String

            return dp[n][m] = res ? 1 : 0;
        } else
            return dp[n][m] = 0;
    }

    public String removeStars(String s) {
        if (s.length() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));

        int i = 1;
        while (i < s.length()) {
            while (i < s.length() && sb.charAt(sb.length() - 1) == '*' && s.charAt(i) == '*')
                i++;
            if (i < s.length())
                sb.append(s.charAt(i));
            i++;
        }

        return sb.toString();
    }

    public boolean isMatch(String s, String p) {
        p = removeStars(p);
        int n = s.length();
        int m = p.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return isMatch(s, p, n, m, dp) == 1;
    }
}
