public class questions {
    //Leetcode 189
    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public void reverse(int[] arr, int i, int j){
        while(i < j){
            swap(arr, i++, j--);
        }
    }
    
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        
        k = (k % n + n) % n;
        
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public void segregatePositiveNegative(int[] arr){
        int n = arr.length, pt = -1, itr = 0;

        while(itr < n){
            if(arr[itr] < 0)
                swap(arr, ++pt, itr);
            itr++;
        }
    }

    public void segregateZeroesAndOnes(int[] arr){
        int n = arr.length, pt = -1, itr = 0;

        while(itr < n){
            if(arr[itr] == 0)
                swap(arr, ++pt, itr);
            itr++;
        }
    }

    public void segregateZeroeOneAndTwos(int[] arr){
        int n = arr.length, pt1 = -1, pt2 = n - 1, itr = 0;

        while(itr <= pt2){
            if(arr[itr] == 0)
                swap(arr, ++pt1, itr++);
            else if(arr[itr] == 2)
                swap(arr, pt2--, itr);
            else
                itr++;
        }
    }

    //GFG - MaxSum in Configuration
    public int max_sum(int arr[], int n){
        int sum = 0, sumWithIndex = 0;
        for(int i = 0; i < n; i++){
            sum += arr[i];
            sumWithIndex += arr[i] * i;
        }

        int maxAns = -(int)1e9;
        for(int i = 0; i < n; i++){
            sumWithIndex = sumWithIndex - sum + arr[i] * n;
            maxAns = Math.max(maxAns, sumWithIndex);
        }
        
        return maxAns;
    }

    //Leetcode 11
    public int maxArea(int[] arr) {
        int n = arr.length, maxArea = 0;
        int i = 0, j = n - 1;
        while(i < j){
            int w = j - i;
            maxArea = arr[i] < arr[j] ? Math.max(maxArea, arr[i++] * w) : Math.max(maxArea, arr[j--] * w);
        }
        
        return maxArea;
    }
    


    public static void main(String[] args){
        System.out.println("SHUBHAM");
    }
}
