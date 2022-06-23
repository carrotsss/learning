package company.cmcc.chapter0;

import company.cmcc.chapter0.bean.People;
import company.cmcc.chapter0.bean.Student;
import company.cmcc.chapter0.bean.Teacher;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 学校对部分师生进行体重排序，抽测师生一共7名，程序排序后结果仅剩6名师生，请确认问题原因并修复。
 *
 * @author jiajia
 * @date 2021/7/5 1:46 下午
 */
public class WeightCompare {
    /**
     * 师生清单
     */
    private Set<People> peoples;
    /**
     * 总人数
     */
    private int TOTAL = 7;
    /**
     * 最小体重
     */
    private int MIN_WEIGHT = 60;
    /**
     * 最大体重
     */
    private int MAX_WEIGHT = 99;

    private WeightCompare() {
        peoples = new LinkedHashSet<>();
        peoples.add(new Teacher("小王", 99, "T01"));
        peoples.add(new Teacher("小李", 91, "T02"));
        peoples.add(new Student("小周", 60, new People("小周爸", 140), new People("小周妈", 98)));
        peoples.add(new Student("小孙", 61, new People("小孙爸", 140), new People("小孙妈", 98)));
        peoples.add(new Student("小张", 62, new People("小张爸", 140), new People("小张妈", 98)));
        peoples.add(new Student("小乔", 63, new People("小乔爸", 140), new People("小乔妈", 98)));
        peoples.add(new Student("小王", 64, new People("小王爸", 140), new People("小王妈", 98)));
    }

    public void process() {
        TreeSet<People> sortedPeoples = new TreeSet<>(peoples);
        if (sortedPeoples.first().getWeight() != MIN_WEIGHT) {
            throw new RuntimeException("排序不对");
        }
        if (sortedPeoples.last().getWeight() != MAX_WEIGHT) {
            throw new RuntimeException("排序不对");
        }
        if (sortedPeoples.size() != TOTAL) {
            throw new RuntimeException("排序后人数与实际不符");
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        new WeightCompare().process();
    }
}
