import java.util.Stack;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Question {
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

    //Leetcode 20
    public boolean isValid(String s) {
        int n = s.length();
        if(n == 0)
            return false;

        if(s.charAt(0) == ')' || s.charAt(0) == ']' || s.charAt(0) == '}')
            return false;
        if(s.charAt(n - 1) == '(' || s.charAt(n - 1) == '[' || s.charAt(n - 1) == '{')
            return false;

        Stack<Character> st = new Stack<>();
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            if(ch == '(' || ch == '[' || ch == '{')
                st.push(ch);
            else {
                if(st.size() == 0) // Closing brackets
                    return false;
                else if(ch == ')' && st.peek() != '(')
                    return false;
                else if(ch == ']' && st.peek() != '[')
                    return false;
                else if(ch == '}' && st.peek() != '{')
                    return false;
                else 
                    st.pop();
            }
        }
        
        return st.size() == 0; //st.size() != 0 -> means more no of opening brackets 
    }

    //LeetCode 946
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> st = new Stack<>();
        int i = 0;
        for(int ele : pushed){
            st.push(ele);
            while(st.size() != 0 && st.peek() == popped[i]){
                st.pop();
                i++;
            }
        }

        return i != popped.length;
    }

    //Leetcode 1249
    public String minRemoveToMakeValid(String s) {
        ArrayDeque<Integer> st = new ArrayDeque<>();
        int n = s.length();
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            if(ch == '(')
                st.addFirst(i);
            else if(ch == ')') {
                if(st.size() != 0 && s.charAt(st.getFirst()) == '(')
                    st.removeFirst();
                else    
                    st.addFirst(i);
            }
        }    

        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < n; i++){
            if(st.size() != 0 && st.getLast() == i){
                st.removeLast();
                continue;
            }

            ans.append(s.charAt(i));
        }

        return ans.toString();
    }

    //Leetcode 32
    public int longestValidParentheses(String s) {
        int n = s.length();
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int len = 0;
        
        for(int i = 0; i < n; i++){
            if(st.peek() != -1 && s.charAt(st.peek()) == '(' && s.charAt(i) == ')'){
                st.pop();
                len = Math.max(len, i - st.peek());
            } else {
                st.push(i);
            }
        }
        return len;
    } 


    public static void main(String[] args){
        
    }
}

