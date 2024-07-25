package dj.com.pojo;
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
}

