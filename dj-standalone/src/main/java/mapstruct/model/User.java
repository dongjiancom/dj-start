package mapstruct.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Mr.DJ
 * @createTime: 2021-08-13 14:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private UserTypeEnum userTypeEnum;
}
