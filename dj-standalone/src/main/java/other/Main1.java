package other;

import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.Test;

/**
 * 测试一个对象当成 方法的 参数时，使用final修饰，还能不能被修改。
 *  结论：
 *      1. 可以被修改属性
 *      2. 不能重新指定引用obj = new Object();
 *
 * @author jiandong 2024-07-24 create
 */
public class Main1 {

    @Test
    public void t1() {
        DjBean1 dj = new DjBean1().setAge(18).setName("dj");
        dj.djAgeChange(dj);
        System.out.println(dj);
    }

    @Data
    @Accessors(chain = true)
    public class DjBean1 {
        private Integer age;
        private String name;

        /**
         * final关键字用于修饰方法参数时，意味着在方法内部该参数引用不能被重新赋值。
         * 但是，通过这个引用，可以修改引用对象的状态。
         * djBean = new DjBean(); // 这将导致编译错误，因为djBean是final的，不能重新赋值
         */
        public void djAgeChange(final DjBean1 djBean) {
            Integer age = djBean.getAge();
            age++;
            djBean.setAge(age);
        }

        public void djAgeChange(final int age) {
            this.setAge(age);
        }
    }
}
