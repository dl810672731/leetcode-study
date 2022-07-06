package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 131 分隔回文串:https://leetcode.cn/problems/palindrome-partitioning/
 */
public class PalindromePartitioning {

    private List<List<String>> result = new ArrayList<>();// 所有符合条件的结果组合
    private Stack<String> path = new Stack<>();// 符合条件的一个组合
    private int pathLength;

    public List<List<String>> partition(String s) {
        backtracking(s, 0);
        return result;
    }

    public void backtracking(String s, int startIndex) {
        for (int i = startIndex; i < s.length(); i++) {
            String temp = s.substring(startIndex, i + 1);
            if (reverseStringBuffer(temp)) {
                path.push(temp);
                pathLength += temp.length();
                if (pathLength == s.length()) {
                    result.add(new ArrayList<>(path));
                }
            } else {
                continue;
            }
            backtracking(s, i + 1);
            path.pop();
            pathLength -= temp.length();
        }
    }

    private boolean reverseStringBuffer(String s) {
        StringBuilder sb = new StringBuilder(s);
        String afterReverse = sb.reverse().toString();
        return afterReverse.equals(s);
    }

    public static void main(String[] args) {
        PalindromePartitioning palindromePartitioning = new PalindromePartitioning();
        palindromePartitioning.partition("aab");
    }
}
