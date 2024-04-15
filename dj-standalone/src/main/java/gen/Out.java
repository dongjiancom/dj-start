package gen;

import cn.hutool.core.util.StrUtil;

import java.io.File;

/**
 * @author jiandong 2024-04-07 create
 * 输出目录
 */
public class Out {
    public static void main(String[] args) {
        // 指定要打印的目录路径
        String directoryPath = "/Users/jiandong/Desktop/java-project-work-new/tianlu-pp/";

        // 调用方法打印目录
        printDirectories(new File(directoryPath), 0);
    }

    public static void printDirectories(File directory, int depth) {
        if ("target".equals(directory.getName())
                || "out".equals(directory.getName())
                || "class".equals(directory.getName())) {
            return;
        }
        // 检查给定文件是否是一个目录
        if (directory.isDirectory()) {
            // 打印目录路径，前面加上TAB
            for (int i = 0; i < depth; i++) {
                System.out.print(StrUtil.TAB);
            }
            System.out.println(directory.getName());

            // 获取目录中的所有文件和子目录
            File[] subFiles = directory.listFiles();

            // 遍历所有文件和子目录
            for (File file : subFiles) {
                // 如果是子目录，则递归调用printDirectories方法
                if (file.isDirectory()) {
                    printDirectories(file, depth + 1);
                }
            }
        }
    }
}