package common;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description:
 * @author: Mr.DJ
 * @createTime: 2022-05-20 23:17
 **/
@Data
@Accessors(chain = true)
public class DjBean {
    private Integer age;
    private String name;

    /**
     * final关键字用于修饰方法参数时，意味着在方法内部该参数引用不能被重新赋值。
     * 但是，通过这个引用，可以修改引用对象的状态。
     * djBean = new DjBean(); // 这将导致编译错误，因为djBean是final的，不能重新赋值
     */
    public void djAgeChange(final DjBean djBean) {
        Integer age = djBean.getAge();
        age++;
        djBean.setAge(age);
    }

    public void djAgeChange(final int age) {
        this.setAge(age);
    }
}

