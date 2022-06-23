package lambda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaTest {
    public static void main(String[] args) {
        List<String> features = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400);

        /**
         * 1、实现runnable
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("before java8");
            }
        }).start();

        new Thread(() -> System.out.println("lambda")).start();

        /**
         * 2、事件处理
         */
        JButton show = new JButton("show");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("before java8");
            }
        });

        show.addActionListener((e) -> {
            System.out.println("lambda");
        });

        /**
         * 3、对列表迭代
         */
        for (String feature : features) {
            System.out.print(feature + " ");
        }
        System.out.println();
        features.forEach(n -> System.out.print(n + " "));

        /**
         * 4、函数式接口predicate
         */
        filter(features, (str) -> str.startsWith("a"));
        filter(features, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.startsWith("a");
            }
        });

        /**
         * 5、在表达式中加入predicate
         */
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.startsWith("a");
            }
        };
        Predicate<String> startsWithJ = (n) -> n.startsWith("a");
        Predicate<String> fourLetterLong = (n) -> n.length() == 3;
        features.stream()
                .filter(startsWithJ.and(fourLetterLong))
                .forEach((n) -> System.out.println("features which starts with 'j' and the letter is " + n));

        /**
         * 6、map和reduce
         */
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            System.out.print(price);
        }
        System.out.println();
        costBeforeTax.stream().map((cost) -> cost + .12 * cost).forEach(System.out::print);

        double bill = costBeforeTax.stream().map((cost) -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("total:" + bill);

        /**
         * 7、通过过滤创建一个String列表
         */
        List<String> filtered = features.stream().filter(x -> x.length() > 2).collect(Collectors.toList());
        System.out.printf("origin list : %s, filtered list : %s %n", features, filtered);

        /**
         * 8、对列表每个元素应用函数
         */
        String featureGroup = features.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(","));
        System.out.println(featureGroup);

        /**
         * 9、赋值不同的值，创建一个列表
         */
        List<Integer> nums = Arrays.asList(9, 10, 2, 2, 2, 4, 7);
        List<Integer> distinct = nums.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.printf("original list : %s, square without duplicates: %s %n", nums, distinct);

        /**
         * 10、计算集合元素的最大值，最小值，总和及平均值
         */
        IntSummaryStatistics statistics = nums.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("highest nums in list " + statistics.getMax());
        System.out.println("Lowest nums in list " + statistics.getMin());
        System.out.println("Average of all prime nums " + statistics.getAverage());
    }

    public static void filter(List<String> names, Predicate<String> condition) {
        for (String name : names) {
            if (condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }
}
