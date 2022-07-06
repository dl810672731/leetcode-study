package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 78. 子集
 */
public class Subsets {
    private List<List<Integer>> result = new ArrayList<>();// 所有符合条件的结果组合
    private Stack<Integer> path = new Stack<>();// 符合条件的一个组合

    public void backtracking(int[] nums, int startIndex) {
        result.add(new ArrayList<>(path));
        if (startIndex == nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            path.push(nums[i]);
            backtracking(nums, i + 1);
            path.pop();
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        backtracking(nums, 0);
        return result;
    }
}
