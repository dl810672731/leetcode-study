package main.java;

import java.util.*;

/**
 * 491. 递增子序列
 */
public class IncreasingSubsequences {

    private List<List<Integer>> result = new ArrayList<>();// 所有符合条件的结果组合
    private Stack<Integer> path = new Stack<>();// 符合条件的一个组合


    public List<List<Integer>> findSubsequences(int[] nums) {
        backtracking(nums, 0);
        // System.out.println("result" + result);
        return result;
    }

    /**
     * @param candidates
     * @param startIndex
     */
    public void backtracking(int[] candidates, int startIndex) {
        Set<Integer> uset = new HashSet<>();
        for (int i = startIndex; i < candidates.length; i++) { // 横向遍历树
            int nextNumber = candidates[i];
            // 树层不允许重复
            if (i > 0 && uset.contains(nextNumber)) {
                continue;
            }

            if (!path.isEmpty() && path.peek() > nextNumber) {
                continue;
            }
            // 标记被使用
            uset.add(nextNumber);
            path.push(nextNumber); // 处理节点，选取一个值添加到符合条件的单个结果中
            if (path.size() >= 2) {
                // System.out.println("====== 匹配一个 path" + path);
                result.add(new ArrayList<>(path));
            }
            backtracking(candidates, i + 1); // 递归处理，控制树的纵向遍历
            // System.out.println("回溯，撤销处理的节点 " + nextNumber);
            // 回溯，撤销处理的节点
            path.pop();
            // System.out.println("pop 后 path = " + path);
        }
    }
}
