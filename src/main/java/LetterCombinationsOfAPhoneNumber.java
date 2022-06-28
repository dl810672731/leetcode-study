package main.java;

import com.sun.deploy.util.StringUtils;

import java.util.*;

/**
 * 17. 电话号码的字母组合
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinationsOfAPhoneNumber {
    private static Map<String, String> map = new HashMap<>();
    private static int pathLength;

    static {
        map.put("", "");
        map.put("0", "");
        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");
    }

    private List<List<String>> result = new ArrayList<>();// 所有符合条件的结果组合
    private Stack<String> path = new Stack<>();// 符合条件的一个组合

    public List<String> letterCombinations(String digits) {
        pathLength = digits.length();
        backtracking(digits, 0);
        List<String> res = new ArrayList<>();
        if (result.size() > 0) {
            result.forEach(s -> {
                        StringBuilder str = new StringBuilder();
                        s.forEach(str::append);
                        if (!"".equals(str.toString())) {
                            res.add(str.toString());
                        }
                    }
            );
        }
        return res;
    }

    /**
     * @param startIndex 下一层循环的开始位置
     */
    public void backtracking(String digits, int startIndex) {
        if (path.size() == pathLength) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < pathLength; i++) { // 横向遍历树
            String str = map.get(String.valueOf(digits.charAt(i)));
            if (str == null) {
                return;
            }
            for (int j = 0; j < str.length(); j++) {
                path.push(String.valueOf(str.charAt(j)));
                backtracking(digits, i + 1); // 递归处理，控制树的纵向遍历
                // 回溯，撤销处理的节点
                path.pop();
            }
        }
    }

    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber t = new LetterCombinationsOfAPhoneNumber();
        t.letterCombinations("23");
    }
}
