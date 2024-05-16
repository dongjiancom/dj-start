package dj.com.tool;

import lombok.SneakyThrows;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @description:
 * @author: Mr.DJ
 * @createTime: 2021-07-19 13:57
 **/
public class DevlopTool {

    @SneakyThrows
    public static void main(String[] args) {
        method();
        fields();
    }

    @SneakyThrows
    public static void method() {
        Class c = Class.forName(Object.class.getName());
        Method[] method = c.getDeclaredMethods();
        for(Method m : method){
            if(m.getName().startsWith("set")){
                System.out.println(m.getName());
            }
        }
    }

    public static void fields() {
        Object dj = new Object();
        Field[] fields = dj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            //有的字段是用private修饰的 将他设置为可读
            fields[i].setAccessible(true);
            try {
                // 输出属性名和属性值
                System.out.println("属性名"+fields[i].getName()+"-----属性值"+fields[i].get(dj));
            }catch (IllegalAccessException e) {
                    e.printStackTrace();
            }
        }
    }

    /**
     * 打印 时间段内 日期
     */
    @Test
    public void print_everyDay() {
        Calendar start = Calendar.getInstance();
        start.set(2021, 0, 1); //2013-1-1 开始
        Calendar end = Calendar.getInstance();
        end.set(2022, 0, 0); // 2014--0-0结束，2014-1-1不算

        int sumSunday = 0;
        int sumSat = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        while (start.compareTo(end) <= 0) {
            int w = start.get(Calendar.DAY_OF_WEEK);
            if (w == Calendar.SUNDAY)
                sumSunday++;
            if (w == Calendar.SATURDAY)
                sumSunday++;
            //打印每天
            System.out.println(format.format(start.getTime()));
            //循环，每次天数加1
            start.set(Calendar.DATE, start.get(Calendar.DATE) + 1);
        }
        System.out.println("星期天总数为:" + sumSunday);
        System.out.println("星期六总数为:" + sumSunday);
    }



}
