package stream;

import lombok.Data;

/**
 * @description:
 * @author: Mr.DJ
 * @createTime: 2021-01-30 12:31
 **/
@Data
public class Person {
        private String name;  // 姓名
        private int salary; // 薪资
        private int age; // 年龄
        private String sex; //性别
        private String area;  // 地区

        // 构造方法
        public Person(String name, int salary, int age,String sex,String area) {
                this.name = name;
                this.salary = salary;
                this.age = age;
                this.sex = sex;
                this.area = area;
        }
}
