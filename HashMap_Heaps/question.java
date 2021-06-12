import java.util.*;

public class question {

    //Leetcode 215
    public int findKthLargest(int[] nums, int k) { //MinPQ
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int ele : nums){
            pq.add(ele);

            if(pq.size() > k)
                pq.remove();
        }

        return pq.peek();
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void downHeapify(int[] arr, int pi, int li){
        int maxIdx = pi;
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;
        
        if(lci <= li && arr[lci] > arr[maxIdx])
            maxIdx = lci;
        if(rci <= li && arr[rci] > arr[maxIdx])
            maxIdx = rci;

        if(maxIdx != pi){
            swap(arr, pi, maxIdx);
            downHeapify(arr, maxIdx, li);
        }
    }

    public int findKthLargest_opti(int[] nums, int k) { //MinPQ
        int li = nums.length - 1;
        for(int i = li; i >= 0; i--){
            downHeapify(nums, i, li);
        }

        while(k-- > 1){
            swap(nums, 0, li--);
            downHeapify(nums, 0, li);
        }

        return nums[0];
    }

    //GFG
    public int findKthSmallest(int[] nums, int k) { //MaxPQ
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {
            return b - a;
        });
        
        for(int ele : nums){
            pq.add(ele);

            if(pq.size() > k)
                pq.remove();
        }

        return pq.peek();
    }

    //Leetcode 703
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int K = 0;
    public void KthLargest(int k, int[] nums) {
        this.K = k;
        for(int ele : nums){
            this.pq.add(ele);
            if(this.pq.size() > this.K)
                this.pq.remove();
        }
    }
    
    public int add(int val) {
        this.pq.add(val);
        if(this.pq.size() > this.K)
            this.pq.remove();

        return this.pq.peek();
    }


}
