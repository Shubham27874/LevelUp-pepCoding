import java.util.*;
;
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
}
