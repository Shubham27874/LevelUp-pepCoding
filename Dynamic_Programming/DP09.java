import java.util.*;

public class DP09 {

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
    
    // https://www.geeksforgeeks.org/subset-sum-problem-dp-25/

    public static int targetSum_memo(int[] arr, int tar, int n, int[][] dp){
        if(n == 0 || tar == 0){
            return dp[n][tar] = (tar == 0) ? 1 : 0; 
        }

        if(dp[n][tar] != -1){
            return dp[n][tar];
        }

        boolean res = false;
        if(tar - arr[n - 1] >= 0)
            res = res || (targetSum_memo(arr, tar - arr[n - 1], n - 1, dp) == 1);
        res = res || (targetSum_memo(arr, tar, n - 1, dp) == 1);

        return dp[n][tar] = res ? 1 : 0;
    }

    public static boolean targetSum_DP(int[] arr, int Tar){
        int N = arr.length;
        boolean[][] dp = new boolean[N + 1][Tar + 1];

        for(int n = 0; n <= N; n++){
            for(int tar = 0; tar <= Tar; tar++){
                if(n == 0 || tar == 0){
                    dp[n][tar] = (tar == 0);
                    continue;
                }

                if(tar - arr[n - 1] >= 0)
                    dp[n][tar] = dp[n][tar] || dp[n -1][tar - arr[n - 1]];
                dp[n][tar] = dp[n][tar] || dp[n -1][tar];
            }
        }

        return dp[N][Tar];
    }

    public static int targetSumTotalways_DP(int[] arr, int Tar){
        int N = arr.length;
        int[][] dp = new int[N + 1][Tar + 1];

        for(int n = 0; n <= N; n++){
            for(int tar = 0; tar <= Tar; tar++){
                if(n == 0 || tar == 0){
                    dp[n][tar] = (tar == 0) ? 1 : 0;
                    continue;
                }

                if(tar - arr[n - 1] >= 0)
                    dp[n][tar] += dp[n -1][tar - arr[n - 1]];
                dp[n][tar] += dp[n -1][tar];
            }
        }

        return dp[N][Tar];
    }


    public static void targetSum(int[] arr, int tar){
        int n = arr.length;
        int[][] dp = new int[n + 1][tar + 1];
        for(int[] d : dp)
            Arrays.fill(d, -1);

        boolean res = targetSum_memo(arr, tar, n, dp) == 1;
        System.out.println(res);
        print2D(dp);
    } 

    //Leetcode 416
    public boolean canPartition(int[] arr) {
        int sum = 0;
        for(int ele : arr)
            sum += ele;
        
        if(sum % 2 != 0)
            return false;
        
        int Tar = sum / 2; 
        int N = arr.length;
        boolean[][] dp = new boolean[N + 1][Tar + 1];

        for(int n = 0; n <= N; n++){
            for(int tar = 0; tar <= Tar; tar++){
                if(n == 0 || tar == 0){
                    dp[n][tar] = (tar == 0);
                    continue;
                }

                if(tar - arr[n - 1] >= 0)
                    dp[n][tar] = dp[n][tar] || dp[n -1][tar - arr[n - 1]];
                dp[n][tar] = dp[n][tar] || dp[n -1][tar];
            }
        }

        return dp[N][Tar];
    }

    //Leetcode 494
    public int findTargetSumWays(int[] nums, int n, int target) {
        if(n == 0){
            return (target == 0 ? 1 : 0);
        }
        
        int count = 0;
        count += findTargetSumWays(nums, n - 1, target - nums[n - 1]);  // as +ve number
        count += findTargetSumWays(nums, n - 1, target - (-nums[n - 1]));  // as -ve number
        
        return count;
    }

    public int findTargetSumWays_2DP(int[] nums, int n, int target, int sum, int[][] dp) {
        if(n == 0){
            return dp[n][sum] =  (sum == target ? 1 : 0);
        }
        
        if(dp[n][sum] != -1){
            return dp[n][sum];
        }

        int count = 0;
        count += findTargetSumWays_2DP(nums, n - 1, target, sum + nums[n - 1], dp);  // as +ve number
        count += findTargetSumWays_2DP(nums, n - 1, target, sum - nums[n - 1], dp);  // as -ve number
        
        return count;
    }
    
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        
        for(int ele : nums)
            sum += ele;
        
        if(sum < target || target < -sum)
            return 0;
        
        //return findTargetSumWays(nums, n, target);

        int[][] dp = new int[n + 1][2 * sum + 1];
        for(int[] d : dp)
            Arrays.fill(d, -1);

        int ans = findTargetSumWays_2DP(nums, n, target + sum, sum, dp);

        return ans;
    }

    public static void main(String[] args){
        targetSum(new int[] { 2, 3, 5, 7 }, 10);
    }
}
