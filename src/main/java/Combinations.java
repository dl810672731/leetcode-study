package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/combinations/
 */
public class Combinations {
    private List<List<Integer>> result = new ArrayList<>();// 所有符合条件的结果组合
    private Stack<Integer> path = new Stack<>();// 符合条件的一个组合

    public List<List<Integer>> combine(int n, int k) {
        backtracking(n, k, 1);
        return result;

    }

    public void backtracking(int n, int k, int startIndex) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n; i++) { // 横向遍历树
            path.push(i); // 处理节点，选取一个值添加到符合条件的单个结果中
            backtracking(n, k, i + 1); // 递归处理，控制树的纵向遍历
            path.pop(); // 回溯，撤销处理的节点
        }
    }
}
