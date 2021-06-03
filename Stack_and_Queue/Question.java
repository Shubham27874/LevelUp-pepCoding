import java.util.Stack;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.ArrayList;

public class Question {
    public static void NGOR(int[] arr, int[] ans){
        int n = arr.length;
        Arrays.fill(ans, n);

        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < n; i++){
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
        for(int i = 0; i < n; i++){
            while(st.size() != 0 && arr[st.peek()] > arr[i]){
                ans[st.pop()] = i;
            }

            st.push(i);
        }
    }

    public static void NSOL(int[] arr, int[] ans){
        int n = arr.length;
        Arrays.fill(ans, -1);

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

    //Leetcode 735
    public int[] asteroidCollision(int[] arr) {
        Stack<Integer> st = new Stack<>();

        for(int ele : arr){
            if(ele > 0){
                st.push(ele);
                continue;
            }

            while(st.size() != 0 && st.peek() > 0 && st.peek() < -ele)
                st.pop();
            
            if(st.size() != 0 && st.peek() == -ele)
                st.pop();
            else 
            if(st.size() == 0 || st.peek() < 0)
                st.push(ele);
            else{

            }
            
        }

        int[] ans = new int[st.size()];
        int idx = st.size() - 1;
        while(st.size() != 0){
            ans[idx--] = st.peek();
            st.pop();
        }

        return ans;
    }

    //Leetcode 84
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] nsor = new int[n];
        int[] nsol = new int[n];

        NSOR(heights, nsor);
        NSOL(heights, nsol);

        int maxArea = 0;

        for(int i = 0; i < n; i++){
            int h = heights[i];
            int w = nsor[i] - nsol[i] - 1;

            maxArea = Math.max(maxArea, h * w);
        }

        return maxArea;
    }

    //Optimal Method
    public int largestRectangleArea_2(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int maxArea = 0;

        int i = 0;
        while(i < n){
            while(st.peek() != -1 && heights[st.peek()] >= heights[i]){
                int idx = st.peek();
                st.pop();
                int h = heights[idx];
                int w = i - st.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }

            st.push(i++);
        }

        while(st.peek() != -1){
                int idx = st.peek();
                st.pop();
                int h = heights[idx];
                int w = n - st.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
        }

        return maxArea;
    }

    ///Leetcode 85
    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        if(n == 0 || m == 0)
            return 0;

        int[] heights = new int[m];
        int maxRec = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0 ; j < m; j++){
                char ch = matrix[i][j];
                heights[j] = ch == '1' ? heights[j] + 1 : 0;
            }

            maxRec = Math.max(maxRec, largestRectangleArea(heights));
        }

        return maxRec;
    }

    //Leetcode 221
    public int largestSquareArea(int[] heights) {
        int n = heights.length;
        int[] nsor = new int[n];
        int[] nsol = new int[n];

        NSOR(heights, nsor);
        NSOL(heights, nsol);

        int maxArea = 0;

        for(int i = 0; i < n; i++){
            int h = heights[i];
            int w = nsor[i] - nsol[i] - 1;

            maxArea = Math.max(maxArea, (h < w) ? h * h : w * w);  //Only change from maximalRectangle
        }

        return maxArea;
    }
    
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0)
            return 0;
        
        int n = matrix.length;
        int m = matrix[0].length;

        int[] heights = new int[m];
        int maxRec = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0 ; j < m; j++){
                char ch = matrix[i][j];
                heights[j] = ch == '1' ? heights[j] + 1 : 0;
            }

            maxRec = Math.max(maxRec, largestRectangleArea(heights));
        }

        return maxRec;
    }

    //Leetcode 402
    public String removeKdigits(String num, int k) {
        ArrayList<Character> st = new ArrayList<>();

        for(int i = 0; i < num.length(); i++){
            char ch = num.charAt(i);
            while(st.size() != 0 && st.get(st.size() - 1) > ch && k > 0){
                st.remove(st.size() - 1);
                k--;
            }

            st.add(ch);
        }

        while(k-- > 0){
            st.remove(st.size() - 1);
        }

        //Handling 0's in front of the String
        StringBuilder ans = new StringBuilder();
        boolean flag = false;
        for(Character ch : st){
            if(ch == '0' && !flag){
                continue;
            }

            flag = true;
            ans.append(ch);
        }

        String res = ans.toString();
        return res.length() == 0 ? "0" : res;
    }

    //LeetCode 316/1081
    public String removeDuplicateLetters(String s) {
        int n = s.length();
        StringBuilder st = new StringBuilder();
        boolean[] vis = new boolean[26];
        int[] freq = new int[26];
            
        for(int i = 0; i < n; i++){
            freq[s.charAt(i) - 'a']++;
        }
        
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            freq[ch - 'a']--;
            if(vis[ch - 'a']) 
                continue;
            
            while(st.length() != 0 && st.charAt(st.length() - 1) > ch && freq[st.charAt(st.length() - 1) - 'a'] > 0){
                vis[st.charAt(st.length() - 1) - 'a'] = false;
                st.deleteCharAt(st.length() - 1);
            }
            
            vis[ch - 'a'] = true;
            st.append(ch);
        }
        
        return st.toString();
    }

    //LeetCode 42
    public int trap(int[] height) {
        int n = height.length;
        int[] lHeight = new int[n];
        int[] rHeight = new int[n];
        
        int prev = 0;
        for(int i = 0; i < n; i++){
            lHeight[i] = Math.max(height[i], prev);
            prev = lHeight[i];
        }
        
        prev = 0;
        for(int i = n - 1; i >= 0; i--){
            rHeight[i] = Math.max(height[i], prev);
            prev = rHeight[i];
        }
        
        int totalWater = 0;
        for(int i = 0; i < n; i++){
            totalWater += Math.min(lHeight[i], rHeight[i]) - height[i];
        }
        
        return totalWater;
    }

    public int trap_02(int[] height) {
        int n = height.length;
        Stack<Integer> st = new Stack<>();

        int totalWater = 0;
        for(int i = 0; i < n; i++){
            while(st.size() != 0 && height[st.peek()] < height[i]){
                int idx = st.pop();
                if(st.size() == 0)
                    break;

                int w = i - st.peek() - 1;
                int h = height[idx];

                totalWater += w * (Math.min(height[st.peek()], height[i]) - h);
            }

            st.push(i);
        }

        return totalWater;
    }

    public int trap_03(int[] height) {
        int n = height.length;
        int lmax = 0, rmax = 0;
        int l = 0, r = n - 1, totalWater = 0;

        while(l < r){
            lmax = Math.max(lmax, height[l]);
            rmax = Math.max(rmax, height[r]);

            totalWater += (lmax < rmax) ? lmax - height[l++] : rmax - height[r--];  
        }

        return totalWater;
    }

    //Leetcode 155
    Stack<Long> stk = new Stack<>();
    long min = 0;

    public void MinStack() {
        
    }
    
    public void push(int val) {
        long a = val;
        if(stk.size() == 0){
            stk.push(a);
            min = val;
        } else {
            if(val < min){
                stk.push(val + (val - min));
                min = val;
            } else
                stk.push(a);
        }
    }
    
    public void pop() {
        if(stk.peek() < min)
            min = min + (min - stk.peek());
        stk.pop();
    }
    
    public int top() {
        if(stk.peek() < min)
            return (int)min;
        
        return stk.peek().intValue();
    }
    
    public int getMin() {
        return (int)min;
    }

    //--------------------------------------------------------------------------------------------

    //Leetcode 121
    public int maxProfit(int[] prices) {
        int dpi10 = 0, dpi11 = -(int)1e9;
        
        for(int price : prices){
            dpi10 = Math.max(dpi10, dpi11 + price);
            dpi11 = Math.max(dpi11, 0 - price);
        }
        
        return dpi10;
    }

    //Leetcode 122
    public int maxProfitK_infinite(int[] prices) {
        int dpik0 = 0, dpik1 = -(int)1e9;
        
        for(int price : prices){
            int dpik_10 = dpik0; //MaxProfit generate by (k-1) times transaction;
            dpik0 = Math.max(dpik0, dpik1 + price);
            dpik1 = Math.max(dpik1, dpik_10 - price);
        }
        
        return dpik0;
    }

    //Leetcode 123
    public int maxProfitK_2(int[] prices) {
        int dpi10 = 0;
        int dpi11 = -(int)1e9;
        int dpi20 = 0;
        int dpi21 = -(int)1e9;

        for(int price : prices){
            dpi10 = Math.max(dpi10, dpi11 + price);
            dpi11 = Math.max(dpi11, 0 - price);

            dpi20 = Math.max(dpi20, dpi21 + price);
            dpi21 = Math.max(dpi21, dpi10 - price);
        }

        return dpi20;
    }

    //Leetcode 714
    public int maxProfit(int[] prices, int fee) {
        int dpik0 = 0, dpik1 = -(int)1e9;
        
        for(int price : prices){
            int dpik_10 = dpik0; //MaxProfit generate by (k-1) times transaction;
            dpik0 = Math.max(dpik0, dpik1 + price);
            dpik1 = Math.max(dpik1, dpik_10 - price - fee);
        }
        
        return dpik0;
    }

    //Leetcode 309
    public int maxProfit_i_2(int[] prices) {
        int dpik0 = 0, dpik1 = -(int)1e9;
        int i_2 = 0;
        
        for(int price : prices){
            int dpik_10 = dpik0; //MaxProfit generate by (k-1) times transaction;
            dpik0 = Math.max(dpik0, dpik1 + price);
            dpik1 = Math.max(dpik1, i_2 - price);
            i_2 = dpik_10;
        }
        
        return dpik0;
    }

    //Leetcode 188 - To be done
    public static void main(String[] args){
        
    }
}

