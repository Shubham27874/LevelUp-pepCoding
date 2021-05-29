import java.util.Stack;
import java.util.Arrays;

public class questions {
    public static void NGOR(int[] arr, int[] ans){
        int n = arr.length;
        Arrays.fill(ans, n);

        Stack<Integer> st = new Stack<>();
        for(int i = 0; i <= n; i++){
            while(st.size() != 0 && arr[st.peek()] < arr[i]){
                ans[st.pop()] = i;
            }

            st.push(i);
        }
    }

    public static void NGOL(int[] arr, int[] ans){
        int n = arr.length;
        Arrays.fill(ans, n);

        Stack<Integer> st = new Stack<>();
        for(int i = n - 1; i >= 0; i--){
            while(st.size() != 0 && arr[st.peek()] < arr[i]){
                ans[st.pop()] = i;
            }

            st.push(i);
        }
    }

    public static void NSOR(int[] arr, int[] ans){
        int n = arr.length;
        Arrays.fill(ans, n);

        Stack<Integer> st = new Stack<>();
        for(int i = 0; i <= n; i++){
            while(st.size() != 0 && arr[st.peek()] > arr[i]){
                ans[st.pop()] = i;
            }

            st.push(i);
        }
    }

    public static void NSOL(int[] arr, int[] ans){
        int n = arr.length;
        Arrays.fill(ans, n);

        Stack<Integer> st = new Stack<>();
        for(int i = n - 1; i >= 0; i--){
            while(st.size() != 0 && arr[st.peek()] > arr[i]){
                ans[st.pop()] = i;
            }

            st.push(i);
        }
    }

    //LeetCode 503
    public int[] nextGreaterElements(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        
        Arrays.fill(ans, -1);

        Stack<Integer> st = new Stack<>();
        for(int i = 0; i <= 2 * n; i++){
            while(st.size() != 0 && arr[st.peek()] < arr[i % n]){
                ans[st.pop()] = arr[i % n];
            }
            
            if(i < n)
                st.push(i);
        }
        
        return ans;
    }

    //StockSpanProblem - GFGpractice
    public int[] calculateSpan(int arr[], int n)
    {
        int[] ans = NGOL(arr, n);
        for(int i = 0; i < n; i++){
            ans[i] = i - ans[i];
        }

        return ans;
    }
    
    public int[] NGOL(int[] arr, int n){
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans, -1);

        for(int i = n - 1; i >= 0; i--){
            while(st.size() != 0 && arr[st.peek()] < arr[i]){
                ans[st.pop()] = i;
            }
            st.push(i);
        }

        return ans;
    }

    //LeetCode 901
    Stack<int[]> st;
    int day;

    public void StockSpanner() {
        this.st = new Stack<>();
        this.day = 0;
        
        st.push(new int[]{-1, -1});
    }
    
    public int next(int price) {
        while(st.peek()[0] != -1 && st.peek()[1] <= price)
            st.pop();

        int span = day - st.peek()[0];
        st.push(new int[]{day++, price});
        return span;
    }

    public static void main(String[] args){
        System.out.println("hello");
    }
}