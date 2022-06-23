package company;

/**
 * Created by carrots on 2022/2/22.
 */

public class Main3 {

    private int value;

    public Main3(int value) {
        this.value = value;
    }

    void writeFirst() {
        Thread thread = new Thread(() -> {
            while (0 == value) {
            }
            System.out.println(value);
        });
        value = 1;
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException ignored) {
        }
    }

    void readFirst() {
        Thread thread = new Thread(() -> {
            while (0 == value) {
            }
            System.out.println(value);
        });
        thread.start();

        try {
            Thread.sleep(1000L);
            value = 1;
            thread.join();
        } catch (InterruptedException ignored) {
        }
    }

    public static void main(String[] args) {
        // 请问 writeFirst, readFirst 各自输出什么？
        new Main3(0).writeFirst();
        new Main3(0).readFirst();
    }
}