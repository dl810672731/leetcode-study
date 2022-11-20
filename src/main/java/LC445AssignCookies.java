package main.java;

import java.util.Arrays;

/**
 * https://leetcode.cn/submissions/detail/383576024/
 */
public class LC445AssignCookies {
    public int result;

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        for (int i = 0, start = 0; i < s.length && start < g.length; i++) {
            if (s[i] >= g[start]) {
                start++;
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LC445AssignCookies t = new LC445AssignCookies();
        int[] g = {1, 2};
        int[] s = {1, 2, 3};
        t.findContentChildren(g, s);
    }
}
