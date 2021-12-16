package mapstruct.cover;

import mapstruct.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: Mr.DJ
 * @createTime: 2021-08-12 15:32
 *
 * 参考：
 * https://juejin.cn/post/6931354963406913550
 * https://blog.csdn.net/qq_43489936/article/details/114873921
 * https://zhuanlan.zhihu.com/p/357402881
 *
 *init一个convert
 * default: 这是默认的情况，mapstruct不使用任何组件类型, 可以通过Mappers.getMapper(Class)方式获取自动生成的实例对象。
 * cdi: the generated mapper is an application-scoped CDI bean and can be retrieved via @Inject
 * spring: 生成的实现类上面会自动添加一个@Component注解，可以通过Spring的 @Autowired方式进行注入
 * jsr330: 生成的实现类上会添加@javax.inject.Named 和@Singleton注解，可以通过 @Inject注解获取。
 *
 * 官方示例：
 * https://github.com/mapstruct/mapstruct-examples
 *
 * 自定义常量
 * 如果我们在转换映射过程中，想要给一些属性定义一个固定的值，这个时候可以使用 constant
 * @Mapping(source = "name", constant = "hollis")
 * String和Date之间的转换：
 * @Mapping(target = "birthday",dateFormat = "yyyy-MM-dd HH:mm:ss")
 * 自定义方法：转json等
 *  @Mapping(target = "address",expression = "java(homeAddressToString(dto2do.getAddress()))")
 *
 *
 */
@Mapper
public interface UserCovert {
    UserCovert INSTANCE = Mappers.getMapper(UserCovert.class);

//    @Mappings({
//            @Mapping(target = "createTime", expression = "java(mapstruct.tool.DateTransform.strToDate(source.getCreateTime()))"),
//    })

    @Mapping(source = "userTypeEnum", target = "type")
    UserVO5 toConvertVO5(User source);

    User fromConvertEntity5(UserVO5 userVO5);


}
