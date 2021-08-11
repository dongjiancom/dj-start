package stream.old;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Mr.DJ
 * @createTime: 2021-01-21 15:01
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    Long id;
    String title;
    String author;
    Integer pageCount;
    Double price;
}
