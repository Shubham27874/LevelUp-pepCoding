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
    

}
