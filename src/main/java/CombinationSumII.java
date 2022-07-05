package main.java;

import java.util.*;

/**
 * 40 : https://leetcode.cn/problems/combination-sum-ii/
 */
public class CombinationSumII {

    private List<List<Integer>> result = new ArrayList<>();// 所有符合条件的结果组合
    private Stack<Integer> path = new Stack<>();// 符合条件的一个组合
    private Map<Integer, Boolean> used = new HashMap<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtracking(candidates, target, 0, 0);
        // System.out.println("result" + result);
        return result;
    }

    /**
     * @param targetSum 目标和
     * @param sum       path 中已经存在的数据的和
     */
    public void backtracking(int[] candidates, int targetSum, int sum, int startIndex) {
        // 如果已选元素的组合大于n则后续操作无意义，可以剪枝
        if (sum > targetSum) {
            // System.out.println("sum > targetSum;sum=" + sum);
            return;
        }
        if (targetSum == sum) {
            // System.out.println("====== 匹配一个 path" + path);
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= targetSum; i++) { // 横向遍历树
            // 树层不允许重复
            if (i > 0 && candidates[i] == candidates[i - 1] && (used.get(i - 1) == null || !used.get(i - 1))) {
                // System.out.println("continue i=" + i + " ,used=" + used.get(i - 1));
                continue;
            }
            // 标记被使用
            used.put(i, true);
            int nextNumber = candidates[i];
            // System.out.println("i = " + i);
            // System.out.println("横向遍历 " + nextNumber);
            sum += nextNumber;
            // System.out.println("push 前path = " + path);
            path.push(nextNumber); // 处理节点，选取一个值添加到符合条件的单个结果中
            backtracking(candidates, targetSum, sum, i + 1); // 递归处理，控制树的纵向遍历
            // System.out.println("回溯，撤销处理的节点 " + nextNumber);
            used.put(i, false);
            // 回溯，撤销处理的节点
            sum -= nextNumber;
            path.pop();
            // System.out.println("pop 后 path = " + path);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        CombinationSumII combinationSum = new CombinationSumII();
        combinationSum.combinationSum2(candidates, 8);
    }
}
