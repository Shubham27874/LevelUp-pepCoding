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
    
    //Leecode 3
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), si = 0, ei = 0, len = 0, count = 0;
        if(n <= 1)
            return n;
            
        int[] freq = new int[128];
        int gsi = 0, gei = 0;
        
        while(ei < n){
            if(freq[s.charAt(ei++)]++ > 0)  //==1
                count++;
            
            while(count > 0){
                if(freq[s.charAt(si++)]-- > 1)   //==2
                    count--;
            }
            
            if(ei - si > len){
                len = ei - si;
                gsi = si;
                gei = ei;
            }
        }
        
        //System.out.println(s.substring(gsi, gei));
        
        return len;
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length(), si = 0, ei = 0, len = 0, count = 0;
        if(n <= 2)
            return n;
            
        int[] freq = new int[128];
        int gsi = 0, gei = 0;
        
        while(ei < n){
            if(freq[s.charAt(ei++)]++ == 0)  
                count++;
            
            while(count > 2){
                if(freq[s.charAt(si++)]-- == 1)   
                    count--;
            }
            
            if(ei - si > len){
                len = ei - si;
                gsi = si;
                gei = ei;
            }
        }
        
        //System.out.println(s.substring(gsi, gei));
        
        return len;
    }

    //Leetcode 76
    public String minWindow(String s, String t) {
        int ns = s.length();
        int nt = t.length();

        if(ns < nt){
            return "";
        }

        int[] freq = new int[128];
        for(int i = 0; i < nt; i++){
            freq[t.charAt(i)]++;
        }

        int si = 0, ei = 0, count = nt, len = (int)1e9, gsi = 0;

        while(ei < ns){
            if(freq[s.charAt(ei++)]-- > 0)
                count--;

            while(count == 0){
                if(ei - si < len){
                    len = ei - si;
                    gsi = si;
                }

                if(freq[s.charAt(si++)]++ == 0)
                    count++;
            }
        }

        return len == (int)1e9 ? "" : s.substring(gsi, gsi + len);
    }

    public static void main(String[] args){
        
    }
}
