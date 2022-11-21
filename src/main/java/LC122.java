package main.java;

public class LC122 {
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int increase = prices[i + 1] - prices[i];
            if (increase > 0) {
                result += increase;
            }
        }
        return result;
    }
}
