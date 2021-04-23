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

    // https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
    public static int knapSack(int W, int wt[], int val[], int n, int[][] dp) { 
        if(n == 0 || W == 0){
            return dp[n][W] = 0;
        }

        if(dp[n][W] != 0){
            return dp[n][W];
        }

        int maxCost = 0;
        if(W - wt[n - 1] >= 0)
            maxCost = knapSack(W - wt[n - 1], wt, val, n - 1, dp) + val[n - 1];
        maxCost = Math.max(maxCost, knapSack(W, wt, val, n - 1, dp));

        return dp[n][W] = maxCost;
    }

    public static int knapSack_DP(int W, int wt[], int val[], int n) {
        
        int[][] dp = new int[n + 1][W + 1];
        
        for(int N = 0; N <= n; N++){
            for(int w = 0; w <= W; w++){
                if(N == 0 || w == 0){
                    dp[N][w] = 0;
                    continue;
                }
                
                if(w - wt[N - 1] >= 0)
                    dp[N][w] = dp[N - 1][w - wt[N - 1]] + val[N -1];
                dp[N][w] = Math.max(dp[N][w],dp[N - 1][w]); 
        
            }
        }
        

        return dp[n][W];
    }

    int knapSack(int W, int wt[], int val[], int n) 
    { 
       int[][] dp = new int[n][W];
       return knapSack(W, wt, val, n, dp);
    }

    public static int knapsack_unbounded(int[] weight, int[] value, int BagWeight) {
        // combination
        int n = weight.length;
        int[] dp = new int[BagWeight + 1];
        for (int i = 0; i < n; i++) {
            for (int bagWeight = weight[i]; bagWeight <= BagWeight; bagWeight++) {
                dp[bagWeight] = Math.max(dp[bagWeight], dp[bagWeight - weight[i]] + value[i]);
            }
        }

        return dp[BagWeight];
    }

    //Leetcode 698
    public boolean canPartitionKSubsets(int[] arr, int k, int idx, int sumSF, int tar, boolean[] vis) {
        if (k == 0)
            return true;
        if (sumSF > tar)
            return false;
        if (sumSF == tar) {
            return canPartitionKSubsets(arr, k - 1, 0, 0, tar, vis);
        }

        boolean res = false;
        for (int i = idx; i < arr.length; i++) {
            if (vis[i])
                continue;
            vis[i] = true;
            res = res || canPartitionKSubsets(arr, k, i + 1, sumSF + arr[i], tar, vis);
            vis[i] = false;
        }

        return res;

    }

    public boolean canPartitionKSubsets(int[] arr, int k) {
        int n = arr.length;
        int sum = 0;
        int maxEle = 0;
        for (int ele : arr) {
            sum += ele;
            maxEle = Math.max(maxEle, ele);
        }

        if (sum % k != 0 || maxEle > sum / k)
            return false;
        boolean[] vis = new boolean[n];

        return canPartitionKSubsets(arr, k, 0, 0, sum / k, vis);
    }

    public static void main(String[] args){
        targetSum(new int[] { 2, 3, 5, 7 }, 10);
    }
}
