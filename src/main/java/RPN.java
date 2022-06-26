import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * 150. 逆波兰表达式求值
 * https://leetcode.cn/problems/evaluate-reverse-polish-notation/
 */
public class RPN {
    private static final List<String> OPE_STR = Arrays.asList("+", "-", "*", "/");

    public int evalRPN(String[] tokens) {
        if (Objects.isNull(tokens)) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (OPE_STR.contains(token)) {
                int right = stack.pop();
                int left = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(left + right);
                        break;
                    case "-":
                        stack.push(left - right);
                        break;
                    case "*":
                        stack.push(left * right);
                        break;
                    case "/":
                        stack.push(left / right);
                        break;
                }
            } else {
                stack.push(Integer.valueOf(token));
            }

        }
        return stack.pop();
    }
}
