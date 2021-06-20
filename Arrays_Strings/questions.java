import java.util.*;

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

    //https://practice.geeksforgeeks.org/problems/smallest-distant-window3132/1
    public String findSubString(String str) {
        if(str.length() <= 1)
            return str;

        int count = 0;
        int[] freq = new int[128];
        for(int i = 0; i < str.length(); i++){
            if(freq[str.charAt(i)] == 0){
                freq[str.charAt(i)]++;
                count++;
            }
        }

        int si = 0, ei = 0, len = (int)1e9, gsi = 0, n = str.length();
        int actualLen = count;

        while(ei < n){
            if(freq[str.charAt(ei++)]-- > 0)
                count--;
            
            while(count == 0){
                if(ei - si  < len){
                    len = ei - si;
                    gsi = si;
                }

                if(freq[str.charAt(si++)]++ == 0)
                    count++;
            }

            if(actualLen == count)
                break;
        }

        return str.substring(gsi, gsi + len);
    }

    //Leetcode 340
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length(), si = 0, ei = 0, len = 0, count = 0;
        if(n <= k)
            return n;
            
        int[] freq = new int[128];
        
        while(ei < n){
            if(freq[s.charAt(ei++)]++ == 0)  
                count++;
            
            while(count > k){
                if(freq[s.charAt(si++)]-- == 1)   
                    count--;
            }
            
            len = Math.max(len, ei - si);
        }
        
        return len;
    }

    //Leetcode 1456
    public boolean isVowel(Character ch){     //Can also use HashMap, HashSet, Arraylist in main funciton(complexity would be same)
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public int maxVowels(String s, int k) {
        int n = s.length(), ei = 0, si = 0, vowelCount = 0, maxVowelCount = 0;

        while(ei < n){
            if(isVowel(s.charAt(ei++)))
                vowelCount++;

            if(ei - si == k){
                maxVowelCount = Math.max(maxVowelCount, vowelCount);

                if(isVowel(s.charAt(si++)))
                    vowelCount--;
            }
        }

        return maxVowelCount;
    }

    public int atMostKDistinct(int[] arr, int k){
        HashMap<Integer, Integer> freq = new HashMap<>();
        int n = arr.length, ei = 0, si = 0, ans = 0;

        while(ei < n){
            freq.put(arr[ei], freq.getOrDefault(arr[ei], 0) + 1);
            ei++;
            while(freq.size() > k){
                freq.put(arr[si], freq.get(arr[si]) - 1);
                if(freq.get(arr[si]) == 0){
                    freq.remove(arr[si]);
                }
                si++;
            }

            ans += ei - si;
        }


        return ans;
    }

    public int atMostKDistinct_02(int[] arr, int k){
        int[] freq = new int[20000 + 1];
        int n = arr.length, ei = 0, si = 0, ans = 0, count = 0;

        while(ei < n){
            if(freq[arr[ei++]]++ == 0)
                count++;

            while(count > k){
                if(freq[arr[si++]]-- == 1)
                    count--;
            }
                ans += ei - si;
        }

        return ans;
    }    

    //Leetcode 992
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMostKDistinct(nums, k) - atMostKDistinct(nums, k - 1);
    }

    //Leetcode 1248
    public int atMostOdd(int[] arr, int k){
        int n = arr.length, ei = 0, si = 0, ans = 0, count = 0;

        while(ei < n){
            if((arr[ei++] & 1) != 0)
                count++;

            while(count > k){
                if((arr[si++] & 1) != 0)
                    count--;
            }

            ans += ei - si;
        }

        return ans;
    }

    public int numberOfSubarrays(int[] arr, int k) {
        return atMostOdd(arr, k) - atMostOdd(arr, k - 1);
    }

    //Leetcode 904
    public int totalFruit(int[] arr) {
        int n = arr.length, ei = 0, si = 0, len = 0, count = 0;
        int[] freq = new int[40000 + 1];
        
        while(ei < n){
            if(freq[arr[ei++]]++ == 0)
                count++;
            
            while(count > 2){
                if(freq[arr[si++]]-- == 1)
                    count--;
            }
            
            len = Math.max(len, ei - si);
        }
        
        return len;
    }

    //Leetcode 930
    public int atMostSum(int[] arr, int tar){
        int n = arr.length, ei = 0, si = 0, count = 0, sum = 0;
        
        while(ei < n){
            sum += arr[ei++];
            
            while(sum > tar)
                sum -= arr[si++];
            
            count += ei - si;
        }
        
        return count;
    }
    
    public int numSubarraysWithSum(int[] arr, int tar) {
        return atMostSum(arr, tar) - (tar != 0 ? atMostSum(arr, tar - 1) : 0);
    }

    //Leetcode 485
    public int findMaxConsecutiveOnes(int[] arr) {
        int n = arr.length, ei = 0, si = 0, count = 0, len = 0;
        
        while(ei < n){
            if(arr[ei++] == 0)
                count++;
            
            while(count == 1){
                if(arr[si++] == 0)
                    count--;
            }
            
            len = Math.max(len, ei - si);
        }
        
        return len;
    }

    public int findMaxConsecutiveOnes_01(int[] arr) {
        int n = arr.length, ei = 0, si = 0, len = 0;
        
        while(ei < n){
            if(arr[ei++] == 0)
                si = ei;
            
            len = Math.max(len, ei - si);
        }
        
        return len;
    }

    //Leetcode 487 - 1 Zero is allowed
    public int findMaxConsecutiveOnes_(int[] arr) {
        int n = arr.length, ei = 0, si = 0, count = 0, len = 0;
        
        while(ei < n){
            if(arr[ei++] == 0)
                count++;
            
            while(count > 1){       //or (==2)
                if(arr[si++] == 0)
                    count--;
            }
            
            len = Math.max(len, ei - si);
        }
        
        return len;
    }

    public int findMaxConsecutiveOnes_opti(int[] arr) {
        int n = arr.length, ei = 0, si = 0, firstZeroIndex = 0, len = 0;
        
        while(ei < n){
            if(arr[ei++] == 0){
                si = firstZeroIndex + 1;
                firstZeroIndex = ei - 1;
            }
            
            len = Math.max(len, ei - si);
        }
        
        return len;
    }

    //Leetcode 1004
    public int longestOnes(int[] arr, int k) {
        int n = arr.length, ei = 0, si = 0, count = 0, len = 0;
        
        while(ei < n){
            if(arr[ei++] == 0)
                count++;
            
            while(count > k){
                if(arr[si++] == 0)
                    count--;
            }
            
            len = Math.max(len, ei - si);
        }
        
        return len;
    }

    //Leetcode 974
    public int subarraysDivByK(int[] arr, int k) {
        int[] rem = new int[k];
        int n = arr.length, ei = 0, sum = 0, ans = 0;
        rem[0] = 1;
        
        while(ei < n){
            sum += arr[ei++];
            int r = (sum % k + k) % k;
            
            ans += rem[r];
            rem[r]++;
        }
        
        return ans;
    }

    //followUp
    public int longestSubarraysDivByK(int[] arr, int k) {
        int[] rem = new int[k];
        int n = arr.length, ei = 0, sum = 0, len = 0;
        Arrays.fill(rem, -2);
        rem[0] = -1;
        while (ei < n) {
            sum += arr[ei];
            int r = (sum % k + k) % k;

            if (rem[r] == -2)
                rem[r] = ei;
            else
                len = Math.max(len, ei - rem[r]);
            ei++;
        }

        return len;
    }

    public int subarraysDivByK_map(int[] arr, int k) {
        HashMap<Integer, Integer> rem = new HashMap<>();
        int n = arr.length, ei = 0, sum = 0, ans = 0;
        rem.put(0, 1);
        
        while(ei < n){
            sum += arr[ei++];
            int r = (sum % k + k) % k;
            
            ans += rem.getOrDefault(r, 0);
            rem.put(r, rem.getOrDefault(r, 0) + 1);
        }
        
        return ans;
    }

    //followUp
    public int longestSubarraysDivByK_map(int[] arr, int k) {
        HashMap<Integer, Integer> rem = new HashMap<>();
        int n = arr.length, ei = 0, sum = 0, len = 0;
        rem.put(0, -1);

        while (ei < n) {
            sum += arr[ei];
            int r = (sum % k + k) % k;

            rem.putIfAbsent(r, ei);
            len = Math.max(len, ei - rem.get(r));
            ei++;
        }

        return len;
    }

    //Subarrays with equal 1s and 0s - GFG
    public int countSubarrWithEqualZeroAndOne(int arr[], int n){
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ei = 0, count = 0, sum = 0;
        
        while(ei < n){
            int val = arr[ei++];
            sum += val;
            if(val == 0)
                sum += -1;

            count += map.getOrDefault(sum, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    //Leetcode 525
    //followUp
    public int findMaxLength(int[] arr) {
        // rem, FirstIndex
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int n = arr.length, ei = 0, len = 0, sum = 0;
        
        while(ei < n){
            int val = arr[ei];
            sum += val;
            if(val == 0)
                sum += -1;
    
            map.putIfAbsent(sum, ei);
            len = Math.max(len, ei - map.get(sum));
            ei++;
            
        }

        return len;
    }

    public static void main(String[] args){
        
    }
}
