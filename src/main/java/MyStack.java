import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    private Queue<Integer> q1;
    private Queue<Integer> q2; // 临时队列

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {
        // offer 在原先的尾部添加
        q2.offer(x);
        // q1取出元素依次加到q2
        while (!q1.isEmpty()) {
            // poll 是弹出头部元素
            q2.offer(q1.poll());
        }
        // q1 清空之后，因为 q2 是临时队列，所以交换q1和q2
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public int pop() {
        if (q1.isEmpty()) {
            return -1;
        }
        // poll 是弹出头部元素
        return q1.poll();
    }

    public int top() {
        if (q1.isEmpty()) {
            return -1;
        }
        return q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}