package main.java;

import java.util.*;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 */
public class LC47Permutations2 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        backtracking(nums);
        return result;
    }

    private List<List<Integer>> result = new ArrayList<>();// 所有符合条件的结果组合
    private Stack<Integer> path = new Stack<>();// 符合条件的一个组合
    private final Map<Integer, Boolean> used = new HashMap<>();

    public void backtracking(int[] candidates) {
        if (path.size() == candidates.length) {
            // System.out.println("====== 匹配一个 path" + path);
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < candidates.length; i++) { // 横向遍历树
            if (i > 0 && candidates[i] == candidates[i - 1] && used.getOrDefault(i - 1, false)) {
                continue;
            }

            if (!used.getOrDefault(i, false)) {
                // 标记被使用
                used.put(i, true);
                path.push(candidates[i]); // 处理节点，选取一个值添加到符合条件的单个结果中
                backtracking(candidates); // 递归处理，控制树的纵向遍历
                used.put(i, false);
                // 回溯，撤销处理的节点
                path.pop();
            }
        }
    }
}
