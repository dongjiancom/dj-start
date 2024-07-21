

package dj.com.tool;

import cn.hutool.core.util.NumberUtil;

/**
 * @author jiandong 2023-09-08 create
 */
public class LongAmountTool {
    public static final Long ZERO = 0L;

    public static void main(String[] args) {
        boolean b;

//        b = longEqual(1L, 1L);
//        System.out.println(b);
//        b = longNotEqual(1L, 1L);
//        System.out.println(b);

//        b = canNullNotZero(0L);
//        System.out.println(b);
//        b = canNullNotZero(null);
//        System.out.println(b);
//        b = canNullNotZero(2L);
//        System.out.println(b);

//        b = nullOrZero(0L);
//        System.out.println(b);
//        b = nullOrZero(null);
//        System.out.println(b);
//        b = nullOrZero(1L);
//        System.out.println(b);

//        b = notNullAndNotZero(0L);
//        System.out.println(b);
//        b = notNullAndNotZero(null);
//        System.out.println(b);
//        b = notNullAndNotZero(1L);
//        System.out.println(b);

//        b = canNullNotZero(0L);
//        System.out.println(b);
//        b = canNullNotZero(null);
//        System.out.println(b);
//        b = canNullNotZero(1L);
//        System.out.println(b);


//        b = sumEqual(3L, 2L, 1L, null);
//        System.out.println(b);
//        b = sumEqual(3L, 2L, 1L, 0L);
//        System.out.println(b);
//        b = sumEqual(0L, 2L, 1L, 0L);
//        System.out.println(b);
//        b = sumEqual(3L, 2L, 1L);
//        System.out.println(b);
//        b = sumEqual(9L, 2L, 1L, 6L);
//        System.out.println(b);
    }

    /**
     * 不能为 null；也不能=0
     */
    public static boolean notNullAndNotZero(Long source) {
        return !nullOrZero(source);
    }

    /**
     * 为 null 或 =0
     */
    public static boolean nullOrZero(Long source) {
        if (null == source || longEqual(source, ZERO)) {
            return true;
        }
        return false;
    }

    /**
     * 可以是 null，但不能为 0
     */
    public static boolean canNullNotZero(Long source) {
        if (null == source) {
            return true;
        }
        return LongAmountTool.longNotEqual(source, ZERO);
    }

    /**
     * source != target
     */
    public static boolean longNotEqual(Long source, Long target) {
        return !longEqual(source, target);
    }

    /**
     * source == target
     */
    public static boolean longEqual(Long source, Long target) {
        return NumberUtil.compare(source, target) == 0;
    }

    /**
     * @return source 比较结果 targets相加
     */
    public static boolean sumEqual(Long source, Long... targets) {
        long sum = 0L;
        for (Long target : targets) {
            sum += target;
        }
        // targets相加 比较 source
        return longEqual(source, sum);
    }
}
