/*******************************************************************************
 * 系统名称:  新一代支付系统天禄项目
 * 子系统名： tianlu-pp
 * 模块名称： tianlu-pp-util
 * 文件名称： TimeUtils.java
 * 创建人：   jiandong
 * Copyright (c) 2022-2023,  All Rights Reserved.
 * 所有版权归易生支付有限公司所有
 *
 *
 * 注意： 本内容仅限于易生支付有限公司内部使用，禁止转发
 ******************************************************************************/

package dj.com.tool;

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
