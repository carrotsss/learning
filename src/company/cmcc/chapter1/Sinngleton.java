package company.cmcc.chapter1;

/**
 * 请将下面的预加载模式的单例实现，改成延迟加载模式
 */
public class Sinngleton {
//    private static Sinngleton insance = new Sinngleton();
//
//    private Sinngleton() {
//    }
//
//    public static Sinngleton getInsance() {
//        return insance;
//    }

    private Sinngleton() {
    }

    private volatile static Sinngleton instance = null;

    /**
     * 两种方法都是线程安全的，但是上面的方法会在类加载阶段就初始化对象在内存（8在堆），
     * 下面的方法在线程指令执行到方法对象时才会对该类进行初始化，且对所有线程是可见的
     * @return
     */
    public static Sinngleton getInstance() {
        if (instance == null) {
            synchronized (Sinngleton.class) {
                if (instance == null) {
                    instance = new Sinngleton();
                }
            }
        }
        return instance;
    }
}
