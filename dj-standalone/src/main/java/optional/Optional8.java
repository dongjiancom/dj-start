package optional;

import dj.com.entity.DjBean;
import org.junit.Test;

import java.util.Optional;

/**
 * @description:
 *  https://mp.weixin.qq.com/s/6DXu5FO-lfB-q2WZ8E9DZg
 *  https://mp.weixin.qq.com/s/l9odE-ezRY0cJjntHYkkpg
 *
 * @author: Mr.DJ
 * @createTime: 2021-08-07 11:34
 **/
public class Optional8 {

    /**
     * 一个对象是否为null、一个对象的属性 不为null 才有后续
     * 可以直接使用：
     * Optional.ofNullable(dj).map(Dj::getAge)
     * map后续的操作，已经是那个属性而不是原对象了
     */
    @Test
    public void objFeildIsNUll() {
        DjBean dj = new DjBean();
        dj.setAge(19);

//        dj = null;

//        dj = new Dj();

        Optional.ofNullable(dj).map(DjBean::getAge)
                .filter(age -> age >= 18)
                .ifPresent(age -> System.out.println("成年，年龄：" + age));
        System.out.println("over");

        //对get出来的属性做处理、或者变换类型 都会使用的是新的数据
        Optional.ofNullable(dj).map(DjBean::getAge).map(age -> age + 1)
                .filter(age1 -> age1 >= 18)
                .ifPresent(age1 -> System.out.println("至少明年成年，明年年龄：" + age1));
        System.out.println("over2");

    }


}
