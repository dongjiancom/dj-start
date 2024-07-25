package dj.com.util.id;

import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @author jiandong 2024-06-26 create
 */
public class RandomTool {
    public static void main(String[] args) {
        for (int i = 0; i < 14; i++) {
            System.out.println(IdUtil.fastSimpleUUID());
        }
        Console.log(SNOWFLAKE.nextId());
    }

    private static final Snowflake SNOWFLAKE = IdUtil.getSnowflake(0 , 1);

}
