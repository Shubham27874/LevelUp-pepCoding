public class BuyAndSellStocks {
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
}
