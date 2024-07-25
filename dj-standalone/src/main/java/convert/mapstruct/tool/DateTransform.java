package convert.mapstruct.tool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description:
 * @author: Mr.DJ
 * @createTime: 2021-08-13 14:44
 **/
public class DateTransform {
    public static LocalDateTime strToDate(String str) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse("2021-08-13 17:07:05", df);
    }
}
