package main.java;

import java.util.*;

public class LC46Permutations {
    private List<List<Integer>> result = new ArrayList<>();// 所有符合条件的结果组合
    private Stack<Integer> path = new Stack<>();// 符合条件的一个组合
    private Set<Integer> uset = new HashSet();

    public List<List<Integer>> permute(int[] nums) {
        backtracking(nums);
        return result;
    }


    public void backtracking(int[] candidates) {
        if (path.size() == candidates.length) {
            // System.out.println("====== 匹配一个 path" + path);
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < candidates.length; i++) { // 横向遍历树
            if (uset.contains(i)) {
                continue;
            }
            // 标记被使用
            uset.add(i);
            int nextNumber = candidates[i];
            path.push(nextNumber); // 处理节点，选取一个值添加到符合条件的单个结果中
            backtracking(candidates); // 递归处理，控制树的纵向遍历
            uset.remove(i);
            // 回溯，撤销处理的节点
            path.pop();
        }
    }
}
