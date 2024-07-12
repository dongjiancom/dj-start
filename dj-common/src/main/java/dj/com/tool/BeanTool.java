/*******************************************************************************
 * 系统名称:  新一代支付系统天禄项目
 * 子系统名： tianlu-pp
 * 模块名称： tianlu-pp-util
 * 文件名称： BeanTool.java
 * 创建人：   jiandong
 * Copyright (c) 2022-2023,  All Rights Reserved.
 * 所有版权归易生支付有限公司所有
 *
 *
 * 注意： 本内容仅限于易生支付有限公司内部使用，禁止转发
 ******************************************************************************/

package dj.com.tool;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.cglib.beans.BeanMap;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @description:
 * @author: Mr.DJ
 * @createTime: 2021-05-02 16:30
 **/
public class BeanTool {

    /**
     * 返回需要落库的Str
     * 删除 respObj 属性里的值 by removeDO、removeSubDO 里的值
     *
     * @param respObj
     * @param removeDO
     * @param removeSubDO
     * @return
     */
    public static String removeFieldByAssetDO(Object respObj, Object removeDO, Object removeSubDO) {
        BeanTool.copyProperties(removeDO, respObj, true);
        BeanTool.copyProperties(removeSubDO, respObj, true);
        return JSONObject.toJSONString(respObj);
    }

    /**
     * 对象是否包含某个属性
     */
    public static boolean hasField(Object obj, String objKey) {
        return ReflectUtil.hasField(obj.getClass(), objKey);
    }

    /**
     * @description: just for 去除 魔法值
     * @author: Mr.DJ
     * @createTime: 2021-07-09 11:24
     **/
    public interface FieldConstants {
        String businessDatetime = "businessDatetime";
    }

    /**
     * @param source              源对象
     * @param target              目标对象
     * @param copySourceNullField 是否拷贝source的null值；默认 false；正常情况 不拷贝 source 的 null 值到 target
     * @param coverTargetNotNull  是否覆盖target的值（如果 target 有值，source）；默认 true；正常情况 source的值会覆盖target的值
     */
    private static void copyProperties(Object source, Object target, boolean copySourceNullField, boolean coverTargetNotNull) {
        if (ObjectUtil.isNull(source) || ObjectUtil.isNull(target)) {
            return;
        }

        String[] sourceNullField = null;
        if (!copySourceNullField) {
            //  source为null的属性
            sourceNullField = getNullPropertyNames(source);
        }
        String[] targetNullField = null;
        if (!coverTargetNotNull) {
            // target不为null的属性
            targetNullField = getNotNullPropertyNames(target);
        }

        String[] ignoreProperties = ArrayUtil.addAll(sourceNullField, targetNullField);
        BeanUtils.copyProperties(source, target, ignoreProperties);

    }

    /**
     * 忽略 source 的 null
     * 忽略 target 的 非null
     */
    public static void copyPropertiesNoCovert(Object source, Object target) {
        copyProperties(source, target, false, false);
    }

    /**
     * 性能和功能兼顾-具体实现使用：org.springframework.beans.BeanUtils
     * isCopyNullField 是否copy 值为 null的 属性
     *
     * @param source
     * @param isCopyNullField
     * @param target
     */
    public static void copyProperties(Object source, Object target, boolean isCopyNullField) {
        if (isCopyNullField) {
            BeanUtils.copyProperties(source, target);
        } else {
            copyProperties(source, target);
        }
    }

    /**
     * 能确定是5个以下的属性，就不要copy了；set、get就好了
     * 不会把值为 null的 属性 copy
     * source 或者 target 为null 会直接返回
     *
     * @param source
     * @param target
     */
    public static void copyProperties(Object source, Object target) {
        if (ObjectUtil.isNull(source) || ObjectUtil.isNull(target)) {
            return;
        }
        /**
         * for 性能和功能兼顾
         * 具体实现使用：org.springframework.beans.BeanUtils
         */
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    /**
     * set bean 属性值
     */
    public static void setFieldByName(Object obj, String fieldName, Object value) {
        if (ObjectUtil.isNull(obj)) {
            return;
        }
        if (BeanTool.hasField(obj, fieldName)) {
            BeanUtil.setFieldValue(obj, fieldName, value);
        }
    }

    /**
     * get bean 属性值 返回 String
     *
     * @param obj
     * @param fieldName
     * @return
     */
    public static String getFieldByName(Object obj, String fieldName) {
        return ObjectUtil.isNotNull(BeanUtil.getFieldValue(obj, fieldName))
                ? BeanUtil.getFieldValue(obj, fieldName).toString()
                : null;
    }

    /**
     * get bean 属性值 返回 Object
     *
     * @param obj
     * @param fieldName
     * @return
     */
    public static Object getFieldObjByName(Object obj, String fieldName) {
        return ObjectUtil.isNotNull(BeanUtil.getFieldValue(obj, fieldName))
                ? BeanUtil.getFieldValue(obj, fieldName)
                : null;
    }


    /**
     * 对象转Map
     *
     * @param object
     * @return
     */
    public static Map<String, Object> beanToMap(Object object) {
        Map<String, Object> map = new HashMap<>();
        if (object != null) {
            BeanMap beanMap = BeanMap.create(object);
            for (Object key : beanMap.keySet()) {
                Object obj = beanMap.get(key);
                if (!StrUtil.isEmptyIfStr(obj)) {
                    map.put(key + "", beanMap.get(key));
                }
            }
        }
        return map;
    }

//    public static Map<String, String> beanToJsonStrMap(Object object) {
//        Map<String, String> map = new HashMap<>();
//        if (object != null) {
//            BeanMap beanMap = BeanMap.create(object);
//            for (Object key : beanMap.keySet()) {
//                Object obj = beanMap.get(key);
//                if(!StrUtil.isEmptyIfStr(obj)){
//                    map.put(key + "", JSON.toJSONString(obj));
//                }
//            }
//        }
//        return map;
//    }

    public static Map<String, String> beanToStrMap(Object object) {
        Map<String, String> map = new HashMap<>();
        if (object != null) {
            BeanMap beanMap = BeanMap.create(object);
            for (Object key : beanMap.keySet()) {
                Object obj = beanMap.get(key);
                if (!StrUtil.isEmptyIfStr(obj)) {
                    map.put(key + "", obj.toString());
                }
            }
        }
        return map;
    }

    public static Map<String, String> beanToJsonStrMap(Object object) {
        Map<String, String> map = new HashMap<>();
        if (object != null) {
            BeanMap beanMap = BeanMap.create(object);
            for (Object key : beanMap.keySet()) {
                Object obj = beanMap.get(key);
                if (!StrUtil.isEmptyIfStr(obj)) {
                    map.put(key + "", JSON.toJSONString(obj));
                }
            }
        }
        return map;
    }

    /**
     * map转对象
     *
     * @param map
     * @param beanClass
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T mapToBean(Map map, Class<T> beanClass) throws Exception {
        T bean = beanClass.newInstance();
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    private static String[] getNotNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue != null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 传来 get方法，返回属性名（not属性值）
     *
     * @param method
     * @return
     */
    public static String getFieldName(Method method) {
        try {
            Class<?> clazz = method.getDeclaringClass();
            BeanInfo info = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] props = info.getPropertyDescriptors();
            for (PropertyDescriptor pd : props) {
                if (method.equals(pd.getWriteMethod()) || method.equals(pd.getReadMethod())) {
                    System.out.println(pd.getDisplayName());
                    return pd.getName();
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 转换list
     */
    @SneakyThrows
    public static <S, T> List<T> convertList(List<S> sources, Class<T> outputClass) {
        if (null == sources || CollectionUtil.isEmpty(sources)) {
            return null;
        }
        List<T> list = new ArrayList<>(sources.size());
        for (S s : sources) {
            T t = outputClass.newInstance();
            copyProperties(s, t);
            list.add(t);
        }
        return list;
    }

    /**
     * 转换list；用fastJson实现
     */
    public static <S, T> List<T> convertListByJson(List<S> sources, Class<T> outputClass) {
        if (null == sources || CollectionUtil.isEmpty(sources)) {
            return null;
        }
        return JSONArray.parseArray(JSONArray.toJSONString(sources), outputClass);
    }

    /**
     * fastjson 序列化、反序列化。完成转换
     */
    public static <T> T convertObj(Object source, Class<T> outputClass){
        return JSON.parseObject(JSON.toJSONString(source), outputClass);
    }

}
