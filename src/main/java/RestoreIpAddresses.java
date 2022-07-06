package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 93. 复原 IP 地址
 */
public class RestoreIpAddresses {
    private List<List<String>> result = new ArrayList<>();// 所有符合条件的结果组合
    private Stack<String> path = new Stack<>();// 符合条件的一个组合
    private int pointNum; // .号分隔符的数量
    private int pathLength;
    private static final int TARGET_POINT_NUM = 4;

    public List<String> restoreIpAddresses(String s) {
        backtracking(s, 0);
        return result.stream().map(ip -> String.join(".", ip))
                .collect(Collectors.toList());
    }


    public void backtracking(String s, int startIndex) {
        for (int i = startIndex; i < s.length(); i++) {
            String temp = s.substring(startIndex, i + 1);
            if (inRange(temp)) {
                path.push(temp);
                pathLength += temp.length();
                pointNum++;
                if (TARGET_POINT_NUM == pointNum && pathLength == s.length()) {
                    result.add(new ArrayList<>(path));
                }
            } else {
                continue;
            }
            backtracking(s, i + 1);
            path.pop();
            pathLength -= temp.length();
            pointNum--;
        }
    }

    private boolean inRange(String temp) {
        if (temp.length() > 3) {
            return false;
        }
        if (temp.length() == 1) {
            return true;
        }
        return temp.charAt(0) != '0' && Integer.parseInt(temp) > 0 && Integer.parseInt(temp) <= 255;
    }
}
