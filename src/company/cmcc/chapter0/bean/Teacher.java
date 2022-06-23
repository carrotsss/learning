package company.cmcc.chapter0.bean;

/**
 * 老师信息
 * @author jiajia
 * @date 2021/7/5 1:41 下午
 */
public class Teacher extends People {
    public String workNo;

    public Teacher(String name, Integer weight, String workNo) {
        super(name, weight);
        this.workNo = workNo;
    }
}
