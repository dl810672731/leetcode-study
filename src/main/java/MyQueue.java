import java.util.Stack;

/**
 * https://leetcode.cn/problems/implement-queue-using-stacks/
 */
class MyQueue {

    private Stack<Integer> in;// 输入栈
    private Stack<Integer> out;// 输出栈

    public MyQueue() {
        in = new Stack<>();
        out = new Stack<>();
    }

    public void push(int x) {
        in.push(x);
    }

    public int pop() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    public int peek() {
        return out.push(pop());
    }

    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
}