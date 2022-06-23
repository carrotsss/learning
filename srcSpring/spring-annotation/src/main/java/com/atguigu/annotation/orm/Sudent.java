
package com.atguigu.annotation.orm;




@SetTable("stu_dent")
public class Sudent {

    @SetProperty(name="stu_name", leng = 30)
    private String name;
    @SetProperty(name="stu_age", leng = 10)
    private Integer age;

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Integer getAge() {

        return age;
    }

    public void setAge(Integer age) {

        this.age = age;
    }

}
