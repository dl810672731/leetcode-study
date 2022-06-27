package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/combination-sum-iii/
 */
public class CombinationSumIII {

    private List<List<Integer>> result = new ArrayList<>();// 所有符合条件的结果组合
    private Stack<Integer> path = new Stack<>();// 符合条件的一个组合

    public List<List<Integer>> combinationSum3(int k, int n) {
        backtracking(n, 0, k, 1);
        return result;
    }

    /**
     * @param targetSum  目标和
     * @param sum        path 中已经存在的数据的和
     * @param k          k个数
     * @param startIndex 下一层循环的开始位置
     */
    public void backtracking(int targetSum, int sum, int k, int startIndex) {
        if (path.size() == k) {
            if (targetSum == sum) {
                result.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = startIndex; i <= 9; i++) { // 横向遍历树
            sum += i;
            path.push(i); // 处理节点，选取一个值添加到符合条件的单个结果中
            backtracking(targetSum, sum, k, i + 1); // 递归处理，控制树的纵向遍历
            // 回溯，撤销处理的节点
            sum -= i;
            path.pop();
        }
    }
}
