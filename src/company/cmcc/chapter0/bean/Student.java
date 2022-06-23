package company.cmcc.chapter0.bean;

import java.util.Objects;

/**
 * 学生信息
 * @author jiajia
 * @date 2021/7/5 1:40 下午
 */
public class Student extends People{
    /**
     * 学生父亲
     */
    private People father;
    /**
     * 学生母亲
     */
    private People mather;

    public Student(String name, Integer weight, People father, People mather) {
        super(name, weight);
        this.father = father;
        this.mather = mather;
    }

    /**
     * linkedHashset 的add方法是根据hashCode()来判断是否key冲突，然后利用hashmap的put方法赋值，
     * 如果不重写hashcode()，会根据name判断是同一个对象，hash冲突从而覆盖原来的值，导致少一个对象
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(name + father != null ? father.name : "" + mather != null ? mather.name : "");
    }
}
