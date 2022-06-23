package lambda;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

public class LambdaFunctionCall {
    static class Person {
        String name;
        LocalDate birthday;
        public Person(String name, LocalDate birthday) {
            this.name = name;
            this.birthday = birthday;
        }
        public LocalDate getBirthday() {
            return birthday;
        }
        public static int compareByAge(Person personA, Person personB) {
            return personA.birthday.compareTo(personB.birthday);
        }
    }

    public static void main(String[] args) {
        Person[] persons = new Person[]{new Person("001", LocalDate.of(2021, 6, 2)),
                new Person("002", LocalDate.of(2021, 11, 2)),
                new Person("003", LocalDate.of(2021, 9, 31)),
                new Person("003", LocalDate.of(2021, 12, 31))};
        //1、使用匿名内部类
        Arrays.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.birthday.compareTo(o2.birthday);
            }
        });
        //2、使用lambda表达式
        Arrays.sort(persons, (Person a, Person b) -> {
            return a.birthday.compareTo(b.birthday);
        });
        //3、使用lambda表达式和静态方法
        Arrays.sort(persons, (a, b) -> Person.compareByAge(a, b));
        //4、使用方法引用，引用的是类的静态方法
        Arrays.sort(persons, Person::compareByAge);
        System.out.println(Arrays.asList(persons));
    }
}
