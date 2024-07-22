package dj.com.tool;

import cn.hutool.core.util.IdUtil;

/**
 * @author jiandong 2024-06-26 create
 */
public class RandomTool {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            System.out.println(IdUtil.fastSimpleUUID());
        }
    }
}
