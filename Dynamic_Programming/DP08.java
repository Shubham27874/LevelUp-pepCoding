public class DP08 {
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

    //Coin Set
    public static int coinChangePermutation_memo(int[] arr, int tar, int[] dp){
        if(tar == 0){
            return dp[tar] = 1;
        }

        if(dp[tar] != -1){
            return dp[tar];
        }
        
        int count = 0;
        for(int ele : arr){
            if(tar - ele >= 0){
                count += coinChangePermutation_memo(arr, tar - ele, dp);
            }
        }

        return dp[tar] = count;
    }

    public static int coinChangePermutation_DP(int[] arr, int Tar, int[] dp){
        dp[0] = 1; /*if(tar == 0){
                     return dp[tar] = 1;
                     }*/

        for(int tar = 0; tar <= Tar; tar++){
            for(int ele : arr){
                if(tar - ele >= 0){
                    dp[tar] += dp[tar - ele]; //coinChangePermutation_memo(arr, tar - ele, dp);
                }
            }
        }

        return dp[Tar];
    }

    public static void coinChangeCombination(int[] arr, int amt){
        
    }

    public static void coinChange(int[] arr, int amt){

    }

    public static void main(String[] args){
        
    }
}
