package main.java;

import java.util.Map;

public class LC53MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE; // 初始化最小值
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            if (count > result) { // 将累计的结果与当前结果比对，累加的如果大于当前，则替换掉当前值
                result = count;
            }
            if (count < 0) { // 如果累加的值出现了负数，则连续的和清空，负数和不参与后续序列的累加
                count = 0;
            }
        }
        return result;
    }
}
