import java.util.*;

public class DP12 {

    //Leetcode 96 - Example of Catalan number - (using dp)
    public int numTrees(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 1;
        for(int i = 1; i <= n; i++){
            for(int j = 0, k = i - 1; k >= 0; j++, k--){
                ans[i] += ans[j] * ans[k];
            }
        }
        return ans[n];
    }

    //https://www.geeksforgeeks.org/applications-of-catalan-numbers/
    
    //Leetcode 1216 - Valid Palindrome III
    //solution : (Length of String - Longest Palindromic subsequence);
    public static boolean isValidPalindrome(String s, int k){
        int n = s.length();
        int[][] dp = new int[n][n];

        for(int gap = 0; gap < n; gap++){
            for(int i = 0, j = gap; j < n; i++, j++){
                if(gap == 0)
                    dp[i][j] = 1;
                else if(s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else 
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }

        return n - dp[0][n - 1] <= k;
    }

    public static boolean isValidPalindrome(){
        String s = "abcdeca";
        int k = 2;

        return isValidPalindrome(s, k);
    }

    //Leetcode 940 - Distinct Subsequence II
    public int distinctSubseqII(String S) {
        S = '$' + S;
        int n = S.length();
        int[] dp = new int[n];
        int[] lastOcc = new int[26];
        Arrays.fill(lastOcc, -1);

        int mod = (int)1e9 + 7;

        dp[0] = 1;
        for(int i = 1; i < n; i++){
            char ch = S.charAt(i);
            int idx = ch - 'a';
            dp[i] = (2 * dp[i - 1]) % mod;

            if(lastOcc[idx] != -1){
                dp[i] = (dp[i] - dp[lastOcc[idx] - 1] + mod) % mod;
            }

            lastOcc[idx] = i;
        }

        return dp[n - 1] - 1;
    }

    public static void main(String[] args){
        System.out.println(isValidPalindrome());

    }

}
