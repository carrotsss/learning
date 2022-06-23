import framework.*;
import idcard.*;

public class Main {
    public static void main(String[] args) {
        /**
         * 通过工厂create（）不同类型的实例对象
         */
        Factory factory = new IDCardFactory();
        Product card1 = factory.create("小明");
        Product card2 = factory.create("小红");
        Product card3 = factory.create("小刚");
        card1.use();
        card2.use();
        card3.use();
    }
}
