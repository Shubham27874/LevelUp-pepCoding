
public class DP10 {
    public static void print1D(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void print2D(int[][] arr) {
        for (int[] ar : arr) {
            print1D(ar);
        }
    }

    public static int mcm_memo(int[] arr, int si, int ei, int[][] dp){
        //int n = arr.length;
        if(si + 1 == ei){
            return dp[si][ei] = 0;
        }

        if(dp[si][ei] != 0){
            return dp[si][ei];
        }

        int minAns = (int)1e9;
        for(int cut = si + 1; cut < ei; cut++){
            int lans = mcm_memo(arr, si, cut, dp);
            int rans = mcm_memo(arr, cut, ei, dp);

            minAns = Math.min(minAns, lans + rans + arr[si] * arr[cut] * arr[ei]);
        }

        return dp[si][ei] = minAns;
    }   

    //Cost of multiplication is 1;
    public static int mcm_dp(int[] arr, int SI, int EI, int[][] dp){
        int n = arr.length;
        for(int gap = 2; gap < n; gap++){
            for(int si = 0, ei = gap; ei < n; si++, ei++){
                if(si + 1 == ei){
                    dp[si][ei] = 0;
                    continue;
                }
        
                int minAns = (int)1e9;
                for(int cut = si + 1; cut < ei; cut++){
                    int lans = dp[si][cut]; 
                    int rans = dp[cut][ei];
        
                    minAns = Math.min(minAns, lans + rans + arr[si] * arr[cut] * arr[ei]);
                }

                dp[si][ei] = minAns;
            }
        }

        return dp[SI][EI];
    }

    //Cost of multiplication is 3 and that of addition is 5;
    public static int mcm_dp2(int[] arr, int SI, int EI, int[][] dp){
        int n = arr.length;
        for(int gap = 2; gap < n; gap++){
            for(int si = 0, ei = gap; ei < n; si++, ei++){
                if(si + 1 == ei){
                    dp[si][ei] = 0;
                    continue;
                }
        
                int minAns = (int)1e9;
                for(int cut = si + 1; cut < ei; cut++){
                    int lans = dp[si][cut]; 
                    int rans = dp[cut][ei];
        
                    minAns = Math.min(minAns, lans + rans + arr[si] * arr[ei] * (3 * arr[cut] + 5 * arr[cut] - 1));
                }

                dp[si][ei] = minAns;
            }
        }

        return dp[SI][EI];
    }

    public static void mcm() {
        //int[] arr = { 40, 20, 30, 10, 30};
        int[] arr = {10, 30, 5, 60};
        int n = arr.length;
        int[][] dp = new int[n][n];

        System.out.println(mcm_dp(arr, 0, n - 1, dp));
        print2D(dp);
    }   

    public static class pair {
        int minValue = (int)1e9;
        int maxValue = -(int)1e9;

        String minExp = "";
        String maxExp = "";

        pair(){

        }

        pair(int minValue, int maxValue, String minExp, String maxExp){
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.minExp = minExp;
            this.maxExp = maxExp;
        }

    }

    public static int evaluate(int a, int b, char ch){
        if(ch == '*')
            return a * b;
        else 
            return a + b;
    }

    public static pair minMaxEvaluation(String str, int si, int ei, pair[][] dp){
        if(si == ei){
            int val = str.charAt(si) - '0';
            return dp[si][ei] = new pair(val, val, val + "", val + "");
        }

        if(dp[si][ei] != null){
            return dp[si][ei];
        }

        pair res = new pair();
        for(int cut = si + 1; cut < ei; cut += 2){
            pair lans = minMaxEvaluation(str, si, cut - 1, dp);
            pair rans = minMaxEvaluation(str, cut + 1, ei, dp);

            int minValue = evaluate(lans.minValue, rans.minValue, str.charAt(cut));
            int maxValue = evaluate(lans.maxValue, rans.maxValue, str.charAt(cut));

            //res.minValue = Math.min(res.minValue, minValue);
            //res.maxValue = Math.max(res.maxValue, maxValue);

            if(minValue < res.minValue){
                res.minValue = minValue;
                res.minExp = "(" + lans.minExp + " " + str.charAt(cut) + " " + rans.minExp + ")";
            }

            if(maxValue > res.maxValue){
                res.maxValue = maxValue;
                res.maxExp = "(" + lans.maxExp + " " + str.charAt(cut) + " " + rans.maxExp + ")";
            }
        }

        return dp[si][ei] = res;
    }

    public static void minMaxEvaluation(){
        String str = "1+2*3+4*5";
        int n = str.length();
        pair[][] dp = new pair[n][n];

        pair res = minMaxEvaluation(str, 0, n - 1, dp);
        System.out.println("Min Value: " + res.minValue + "\nMin Expression: " + res.minExp);
        System.out.println("Max Value: " + res.maxValue + "\nMax Expression: " + res.maxExp);
    }

    //Leetcode 5
    public String longestPalindrome(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        int len = 0;
        int si = 0;
        int count = 0;

        for(int gap = 0; gap < n; gap++){
            for(int i = 0, j = gap; j < n; i++, j++){
                if(gap == 0)
                    dp[i][j] = 1;
                else if(gap == 1)
                    dp[i][j] = s.charAt(i) == s.charAt(j) ? 2 : 0;
                else dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] != 0? dp[i + 1][j - 1] + 2: 0;
                
                if(dp[i][j] > len){
                    len = dp[i][j];
                    si = i;
                }

                if(dp[i][j] != 0){
                    count++;
                }
            }
        }
    
        return s.substring(si, si + len);
    }

    public static void main(String[] args){
        //mcm();
        minMaxEvaluation();
    }
}
