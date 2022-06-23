package company.cmcc.chapter0.bean;

import java.util.Objects;

/**
 * 人的基本信息
 * @author jiajia
 * @date 2021/7/5 1:41 下午
 */
public class People implements Comparable<People> {
    /**
     * 姓名
     */
    public String name;
    /**
     * 体重
     */
    private Integer weight;

    public People(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else {
            People people = (People) o;
            return Objects.equals(name, people.name);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(People o) {
        return this.weight.compareTo(o.weight);
    }
}
