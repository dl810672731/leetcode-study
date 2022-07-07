package main.java;

import java.util.*;

/**
 * 90. 子集 II
 */
public class SubsetsII {

    private List<List<Integer>> result = new ArrayList<>(); // 所有符合条件的结果组合
    private Stack<Integer> path = new Stack<>(); // 符合条件的一个组合
    private Map<Integer, Boolean> used = new HashMap<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtracking(nums, 0);
        return result;
    }

    public void backtracking(int[] nums, int startIndex) {
        result.add(new ArrayList<>(path));
        if (startIndex == nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            // 树层不允许重复
            if (i > 0 && nums[i] == nums[i - 1] && (used.get(i - 1) == null || !used.get(i - 1))) {
                continue;
            }
            path.push(nums[i]);
            // 标记被使用
            used.put(i, true);
            backtracking(nums, i + 1);
            path.pop();
            used.put(i, false);
        }
    }

}
