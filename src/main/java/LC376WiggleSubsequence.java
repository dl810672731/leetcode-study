package main.java;

public class LC376WiggleSubsequence {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int result = 1;
        int curDiff = 0;
        int preDiff = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // 为什么是 nums.length - 1，为了避免  nums[i + 1] 下标越界 i值最大是倒数第 2个下标
            // 当前坡度 后一个值减去前一个值
            curDiff = nums[i + 1] - nums[i];
            if ((preDiff <= 0 && curDiff > 0) || (preDiff >= 0 && curDiff < 0)) {
                result++;
                preDiff = curDiff;
            }
        }
        return result;
    }
}
