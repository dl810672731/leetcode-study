
public class Demo {
    public int add(int a, int b) {
        try {
            System.out.println("开始计算");
            return a + b;
        } catch (Exception e) {
            System.out.println("catch 语句块");
        } finally {
            System.out.println("finally 语句块");
        }
        return 0;
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        System.out.println("和是：" + demo.add(9, 34));
    }
}