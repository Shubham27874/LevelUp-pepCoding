import java.util.*;


public class Question {
    //Leetcdoe 704
    public static int binarySearch(int[] arr, int data){
        int n = arr.length, si = 0, ei = n - 1;

        while(si <= ei){
            int mid = (si + ei) / 2;
            if(arr[mid] == data)
                return mid;
            else if(arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return -1;
    }

    public static int firstIndex(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;

        while(si <= ei){
            int mid = (si + ei) / 2;
            if(arr[mid] == data){
                if(mid - 1 >= 0 && arr[mid - 1] == data)
                    ei = mid - 1;
                else  
                    return mid;
            } else if(arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return -1;
    }

    public static int lastIndex(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;

        while(si <= ei){
            int mid = (si + ei) / 2;
            if(arr[mid] == data){
                if(mid + 1 < n && arr[mid + 1] == data)
                    si = mid + 1;
                else  
                    return mid;
            } else if(arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return -1;
    }

    public static int firstIndex_01(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;

        while(si <= ei){
            int mid = (si + ei) / 2;
            if(arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return arr[si] == data ? si : -1;
    }

    public static int lastIndex_01(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;

        while(si <= ei){
            int mid = (si + ei) / 2;
            if(arr[mid] <= data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        si--;
        return si >= 0 && arr[si] == data ? si  : -1;
    }

    //Leetcode 34
    public int[] searchRange(int[] arr, int data) {
        return new int[] {firstIndex(arr, data), lastIndex(arr, data)};
    }

    public int insertLocation(int[] arr, int data){
        int n = arr.length, si = 0, ei = n - 1;
        while (si <= ei){
            int mid = (si + ei) / 2;
            if (arr[mid] <= data)
                si = mid + 1;
            else
                ei = mid - 1;
            }

        return si;
    }

    int perfectPosOfElement(int[] arr, int data){
        int insertPos = insertLocation(arr, data);
        int lastIndex = insertPos - 1;

        return (lastIndex >= 0 && arr[lastIndex] == data) ? lastIndex : insertPos;
    }

    int nearestElement(int[] arr, int data){
        if(arr.length == 0)
            return 0;

        int n = arr.length;
        if(data <= arr[0] || data >= arr[n - 1])
            return data <= arr[0] ? arr[0] : arr[n - 1];

        int si = 0, ei = n - 1;
        while(si <= ei){
            int mid = (si + ei) / 2;
            if(arr[mid] <= data)
                si = mid + 1;
            else    
                ei = mid - 1;
        }

        return data - arr[ei] <= arr[si] - data ? arr[ei] : arr[si];
    }

    //Leetcode 74
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length, si = 0, ei = n * m - 1;
        while(si <= ei){
            int mid = (si + ei) / 2;
            int r = mid / m, c = mid % m;

            if(matrix[r][c] == target)
                return true;
            else if(matrix[r][c] < target)
                si = mid + 1;
            else  
                ei = mid - 1;
        } 

        return false;
    }

    //Leetcode 240
    public boolean searchMatrix_2(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length, si = n - 1, ei = 0;
        
        while(si >= 0 && ei < m){
            int ele = matrix[si][ei];
            if(ele == target)
                return true;
            else if(ele < target)
                ei++;
            else  
                si--;
        }

        return false;
    }

    //GFG - Count Inversion
    public static long inversionAcrossArray(long arr[], int l, int r, int mid, long[] sortedArray){
        int lsi = l, lei = mid;
        int rsi = mid + 1, rei = r;

        int k = 0;
        long count = 0;

        //long[] sortedArray = new long[r - l + 1];

        while(lsi <= lei && rsi <= rei){
            if(arr[lsi] > arr[rsi]){
                count += (lei - lsi + 1);
                sortedArray[k++] = arr[rsi++];
            } else {
                sortedArray[k++] = arr[lsi++];
            }
        }

        while(lsi <= lei)
            sortedArray[k++] = arr[lsi++];
        while(rsi <= rei)
            sortedArray[k++] = arr[rsi++];

        k = 0;
        for(int i = l; i <= r; i++){
            arr[i] = sortedArray[k++];
        }

        return count;
    }
    
    public static long inversionCount(long arr[], int si, int ei, long[] sortedArray){
        if(si >= ei)
            return 0;

        int mid = (si + ei) / 2;
        long ICL = inversionCount(arr, si, mid, sortedArray);
        long ICR = inversionCount(arr, mid + 1, ei, sortedArray);

        return (ICL + ICR + inversionAcrossArray(arr, si, ei, mid, sortedArray));
    }

    public static long inversionCount(long arr[], long N){
        if(N == 0)
            return 0;

        long[] sortedArray = new long[(int)N];
        return inversionCount(arr, 0, (int)N - 1, sortedArray);
    }

    //Leetcode 33
    public int search(int[] nums, int target) {
        int n = nums.length, si = 0, ei = n - 1;

        while(si <= ei){
            int mid = (si + ei) / 2;
            if(nums[mid] == target)
                return mid;
            if(nums[si] <= nums[mid]){
                if(nums[si] <= target && target < nums[mid])
                    ei = mid - 1;
                else    
                    si = mid + 1;
            } else {
                if(nums[mid] < target && target <= nums[ei])
                    si = mid + 1;
                else    
                    ei = mid - 1;
            }
        }

        return -1;
    }

    //Leetcode 81
    public boolean search(int[] nums, int target) {
        int n = nums.length, si = 0, ei = n - 1;

        while(si <= ei){
            int mid = (si + ei) / 2;
            if(nums[mid] == target || nums[si] == target)
                return true;
            if(nums[si] < nums[mid]){
                if(nums[si] <= target && target < nums[mid])
                    ei = mid - 1;
                else
                    si = mid + 1;
            } else if(nums[mid] < nums[ei]){
                if(nums[mid] < target && target <= nums[ei])
                    si = mid + 1;
                else 
                    ei = mid - 1;;
            } else si++;
        }

        return false;
    }

    //Leetcode 153
    public int findMin(int[] nums) {
        int n = nums.length, si = 0, ei = n - 1;
        
        while(si < ei){
            int mid = (si + ei) / 2;
            if(nums[mid] < nums[ei])
                ei = mid;
            else if(nums[si] <= nums[mid])
                si = mid + 1;
        }

        return nums[si];
    }

    //Leetcode 154
    public int findMin(int[] nums) {
        int n = nums.length, si = 0, ei = n - 1;
        if(nums[si] < nums[ei])
            return nums[si];

        while(si < ei){
            int mid = (si + ei) / 2;
            if(nums[mid] < nums[ei])
                ei = mid;
            else if(nums[mid] > nums[ei])
                si = mid + 1;
            else 
                ei--;
        }

        return nums[si];
    }

    //Leetcode 167
    public int[] twoSum(int[] arr, int tar) {
        int n = arr.length, si = 0, ei = n - 1;
        
        while(si < ei){
            int sum = arr[si] + arr[ei];
            if(sum == tar)
                return new int[]{si + 1, ei + 1};
            else if(sum < tar)
                si++;
            else 
                ei--;
        }
        
        return new int[0];
    }

    //FollowUp
    public List<List<Integer>> twoSum_(int[] arr, int tar, int si, int ei) {
        List<List<Integer>> ans = new ArrayList<>();

        while(si < ei){
            int sum = arr[si] + arr[ei];
            if(sum == tar){
                List<Integer> smallAns = new ArrayList<>();
                smallAns.add(arr[si]);
                smallAns.add(arr[ei]);
                ans.add(smallAns);

                si++;
                ei--;
                while(si < ei && arr[si] == arr[si - 1]) si++;
                while(si < ei && arr[ei] == arr[ei + 1]) ei--;
            }
            else if(sum < tar)
                si++;
            else 
                ei--;
        }
        
        return ans;
    }

    public void prepareAns(List<List<Integer>> ans, List<List<Integer>> smallAns, int fixEle){
        for(List<Integer> arr : smallAns) {
            List<Integer> ar = new ArrayList<>();
            ar.add(fixEle);
            for(int ele : arr)
                ar.add(ele);
            ans.add(ar);
        }
    }

    //Leetcode 15
    public List<List<Integer>> threeSum(int[] arr, int target, int si, int ei) {
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = si; i < ei;){
            List<List<Integer>> smallAns = twoSum_(arr, target - arr[i], i + 1, ei);
            prepareAns(ans, smallAns, arr[i]);
            i++;
            while(i < ei && arr[i] == arr[i - 1])
                i++;
        }

        return ans;
    }

    //Leetcode 18
    public List<List<Integer>> fourSum(int[] arr, int target, int si, int ei) {
        List<List<Integer>> ans = new ArrayList<>();

        for(int i = si; i < ei;){
            List<List<Integer>> smallAns = threeSum(arr, target - arr[i], i + 1, ei);
            prepareAns(ans, smallAns, arr[i]);
            i++;
            while(i < ei && arr[i] == arr[i - 1])
                i++;
        }

        return ans;
    }

    public List<List<Integer>> kSum(int[] arr, int target, int k, int si, int ei) {
        if(k == 2)
            return twoSum_(arr, target, si, ei);

        List<List<Integer>> ans = new ArrayList<>();
        for(int i = si; i < ei;){
            List<List<Integer>> smallAns = kSum(arr, target - arr[i], k - 1, i + 1, ei);
            prepareAns(ans, smallAns, arr[i]);
            i++;
            while(i < ei && arr[i] == arr[i - 1])
                i++;
        }

        return ans;
    }

    public int twoSumCount(int[] nums1, int[] nums2, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int ele : nums1)
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        
        int count = 0;
        for(int ele : nums2)
            if(map.containsKey(target - ele))
                count += map.get(target - ele);

        return count;
    }

    //Leetcode 454
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int e1 : nums1)
            for (int e2 : nums2)
                map.put(e1 + e2, map.getOrDefault(e1 + e2, 0) + 1);

        int count = 0, target = 0;
        for (int e1 : nums3)
            for (int e2 : nums4)
                if (map.containsKey(target - e1 - e2))
                    count += map.get(target - e1 - e2);

        return count;
    }


    int fourSumCount_(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int n = nums1.length, idx = 0;
        int[] A = new int[n * n];
        int[] B = new int[n * n];

        for (int e1 : nums1)
            for (int e2 : nums2)
                A[idx++] = e1 + e2;

        idx = 0;
        for (int e1 : nums3)
            for (int e2 : nums4)
                B[idx++] = e1 + e2;

        return twoSumCount(A, B, 0);
    }
}
