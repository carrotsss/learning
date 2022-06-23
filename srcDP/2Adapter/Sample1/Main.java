public class Main {
    public static void main(String[] args) {
        /**
         * printBanner通过继承Banner，并写printWeak方法兼容banner的showwithparren，写printString兼容banner的showwithAster
         */
        Print p = new PrintBanner("Hello");
        p.printWeak();
        p.printStrong();
    }
}
