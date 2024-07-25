package dj.com.util.json.fastjson;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author jiandong 2024-07-24 create
 */
public class FastJsonUtil {

//    static JSONWriter.Context writeContext = JSONFactory.createWriteContext();{
//        writeContext.config(JSONWriter.Feature.ReferenceDetection, false);
//    }

    // DisableCircularReferenceDetect 会导致循环引用的对象不被序列化
    public static String toJsonStringNoDisableCircular(Object object) {
        // fastJson1 写法
        return JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);

        // fastJson2 写法
//        JSONWriter.Context writeContext = JSONFactory.createWriteContext();
//        writeContext.config(JSONWriter.Feature.ReferenceDetection, false);
//        return JSON.toJSONString(object, writeContext);
    }

    public static String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }
}
