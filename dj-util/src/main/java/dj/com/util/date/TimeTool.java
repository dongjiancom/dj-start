

package dj.com.util.date;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.date.format.FastDateFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/**
 * @author jiandong
 */
public class TimeTool {


    /**
     * 比较多个时间是否相等
     */
    public static boolean isSame(LocalDateTime sourceDate, LocalDateTime... targetDates) {
        if(null == sourceDate || null == targetDates){
            return false;
        }
        for(LocalDateTime targetDate : targetDates){
            // 只比较到秒
            if(!sourceDate.withNano(0).isEqual(targetDate.withNano(0))){
                return false;
            }
        }
        return true;
    }

    /**
     * 返回 yyyyMMddHHmmss
     */
    public static String getDateByPure() {
        FastDateFormat sdf = FastDateFormat.getInstance(DatePattern.PURE_DATETIME_PATTERN);
        return sdf.format(new Date());
    }


    /**
     * 创建 LocalDateTime，只保留时分秒
     * 数据库 统一转换成只保留到时分秒，如果使用LocalDateTime.now()，json 序列化会变为 Long 型
     */
    public static LocalDateTime getLocalDateTimeNow() {
        return LocalDateTime.now().withNano(0);
    }

    public static String date2Str(LocalDateTime localDateTime) {
        return LocalDateTimeUtil.format(localDateTime, DatePattern.PURE_DATETIME_PATTERN);
    }

    public static LocalDateTime str2Date(String timeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DatePattern.PURE_DATETIME_PATTERN);
        LocalDateTime businessDatetime = LocalDateTime.parse(timeStr, formatter);
        return businessDatetime;
    }

}
