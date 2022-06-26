import java.util.Stack;

public class Test {


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        int chushu = 6;
        int yushu = chushu % 2;
        System.out.println("yushu" + yushu);
        int shang = chushu / 2;
        System.out.println("shang" + shang);
        stack.push(yushu);
        if (shang < 2) {
            stack.push(shang);
        }
        while (shang >= 2) {
            yushu = shang % 2;
            stack.push(yushu);
            shang = shang / 2;
            if (shang < 2) {
                stack.push(shang);
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }
}
