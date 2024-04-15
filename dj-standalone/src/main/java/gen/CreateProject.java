package gen;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.WorkbookUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiandong 2024-04-06 create
 */
public class CreateProject {

    public static void main(String[] args) {
        // 项目路径
        String directoryPath = "/Users/jiandong/Desktop/dj-java-project/";
        // 项目名称
        String projectName = "fei-zhou-dj";
        // 项目模板
        String projectTemplate = "/Users/jiandong/Desktop/dj-java-project/Template.txt";

        // 解析模板 转为实体
        List<List<String>> module_dir = transferList(projectTemplate);
        System.out.println(JSON.toJSONString(module_dir, SerializerFeature.DisableCircularReferenceDetect));

        // 创建项目
        List<String> toMk = mkdirRows(directoryPath + projectName, module_dir);
        System.out.println(JSON.toJSONString(toMk, SerializerFeature.DisableCircularReferenceDetect));

        toMk.stream().forEach(s -> realMk(s));
    }

    public static List<List<String>> transferList(String projectTemplate) {
        List<String> strings = FileUtil.readUtf8Lines(new File(projectTemplate));
        List<List<String>> result = new ArrayList<>();
        List<String> inner = null;

        for (String str : strings) {
            if (0 == getLevel(str)) {
                if(null != inner){
                    result.add(inner);
                }
                inner = new ArrayList<>();
            }
            inner.add(str);
        }
        result.add(inner);
        return result;
    }

    static int getLevel(String str) {
        return StrUtil.count(str, StrUtil.TAB);
    }

    static List<String> mkdirRows(String directoryPath, List<List<String>> module_dir) {
        List<String> mkdirRows = new ArrayList<>();

        StringBuilder builder = StrUtil.builder(directoryPath);
        // 循环
        for(List<String> module : module_dir){
            // 得到最大的等级
            Integer level = module.stream().map(s->getLevel(s)).max(Integer::compareTo).get();
            // 多维数组存储要建的目录
            String[][] moduleCell = new String[module.size()][level+1];
            // for循环把nameEntity的name放到对应的位置
            for(int i = 0; i < module.size(); i++){
                moduleCell[i][getLevel(module.get(i))] = module.get(i);
            }
            // 循环二维数组，如果是空的，就递归，取上一行此列的不为空的值
            for (int i = 0; i < module.size(); i++) {
                for (int j = 0; j < level; j++) {
                    if (StrUtil.isBlank(moduleCell[i][j])) {
                        moduleCell[i][j] = getPreCell(moduleCell, i, j);
                    }
                }
            }


            for (int i = 0; i < moduleCell.length; i++) {
                mkdirRows.add(builder.toString());
                builder = StrUtil.builder();
                builder.append(directoryPath);
//                System.out.print("\n");
//                System.out.print(directoryPath);

                for (int j = 0; j < moduleCell[i].length; j++) {
                    String path = StrUtil.trim(moduleCell[i][j]);
                    if(StrUtil.isNotBlank(path)){
                        // 打印
//                        System.out.print("/");
//                        System.out.print(path);
                        builder.append("/");
                        builder.append(path);
                    }
                }
            }
        }
        return mkdirRows;
    }

    static void realMk(String path){
        boolean isFile = StrUtil.contains(path, StrUtil.DOT);
        if (isFile) {
            // 创建文件 判断是否存在
            if(!FileUtil.exist(path)){
                System.out.println("创建文件："+path);
                FileUtil.touch(path);
            }
        } else {
            // 创建目录 判断是否存在
            if(!FileUtil.exist(path)){
                System.out.println("创建目录："+path);
                FileUtil.mkdir(path);
            }
        }
    }



    private static String getPreCell(String[][] moduleCell, int i, int j) {
        if (i == 0) {
            return "";
        }
        String s = moduleCell[i - 1][j];
        if (StrUtil.isNotBlank(s)) {
            return s;
        }else {
            return getPreCell(moduleCell, i - 1, j);
        }
    }
}