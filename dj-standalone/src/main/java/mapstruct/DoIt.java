package mapstruct;

import mapstruct.cover.UserCovert;
import mapstruct.model.User;
import mapstruct.model.UserTypeEnum;
import mapstruct.model.UserVO5;
import org.junit.Test;

/**
 * @description:
 * @author: Mr.DJ
 * @createTime: 2021-08-13 15:01
 * <p>
 *     官方例子： https://github.com/mapstruct/mapstruct-examples/tree/master/mapstruct-lombok
 *     https://juejin.cn/post/6931354963406913550
 *     https://blog.csdn.net/qq_43489936/article/details/114873921
 *     https://zhuanlan.zhihu.com/p/357402881
 * <p>
 * init一个Convert
 * default: 这是默认的情况，mapstruct不使用任何组件类型, 可以通过Mappers.getMapper(Class)方式获取自动生成的实例对象。
 * cdi: the generated mapper is an application-scoped CDI bean and can be retrieved via @Inject
 * spring: 生成的实现类上面会自动添加一个@Component注解，可以通过Spring的 @Autowired方式进行注入
 * jsr330: 生成的实现类上会添加@javax.inject.Named 和@Singleton注解，可以通过 @Inject注解获取。
 **/
public class DoIt {
    @Test
    public void doIt() {
        User user = new User(22, "dj", UserTypeEnum.Java);
        System.out.println(user);

        UserCovert mapperConvert = UserCovert.INSTANCE;
        UserVO5 userVO5 = mapperConvert.toConvertVO5(user);
        System.out.println("user to userVO5");
        System.out.println(userVO5);

        User user1 = mapperConvert.fromConvertEntity5(userVO5);
        System.out.println("userVO5 to user");
        System.out.println(user1);

    }
}
