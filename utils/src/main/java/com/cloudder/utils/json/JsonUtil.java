package com.cloudder.utils.json;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

public class JsonUtil {
    private static SerializeConfig mapping = new SerializeConfig();
    private static String dateFormat;

    static {
        dateFormat = "yyyy-MM-dd HH:mm:ss";
    }

    /**
     * 默认的处理时间
     *
     * @param jsonText
     * @return
     */
    public static String toJSON(Object jsonText) {
        return JSON.toJSONString(jsonText, SerializerFeature.WriteDateUseDateFormat);
    }

    public Map<String, Object> strJsonToMap(String strJson) {
        JSONObject json = new JSONObject();
        return (Map<String, Object>) json.parse(strJson); // string转map
    }

    /**
     * 自定义时间格式
     *
     * @param jsonText
     * @return
     */
    public static String toJSON(String dateFormat, String jsonText) {
        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
        return JSON.toJSONString(jsonText, mapping);
    }

    public static String beanToJSON(Object jsonText) {
        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
        return JSON.toJSONString(jsonText, mapping);
    }

    public static Map<String, Object> jsonToMap(String jsonStr) {
        Map<String, Object> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, Object>>() {
        });
        return map;
    }

    public static Map<String, Object> jsonToLinkMap(String jsonStr) {
        Map<String, Object> map = JSON.parseObject(jsonStr, new TypeReference<LinkedHashMap<String, Object>>() {
        }, Feature.OrderedField);
        return map;
    }

    public static <T> T jsonToBean(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    public static <T> List<T> jsonToList(String json, Class<T> clazz) {
        JSONArray data = JSONArray.parseArray(json);
        return data.toJavaList(clazz);
    }

    public static <T> String toJSON(Object obj, final String ignoreName) {
        return JSON.toJSONString(obj, new PropertyFilter() {
            @Override
            public boolean apply(Object source, String name, Object value) {
                return !ignoreName.equals(name);
            }
        }, SerializerFeature.WriteDateUseDateFormat);
    }

    public static <T> String toJsonIgnorePage(Object obj) {
        return  toJSON(obj, "pageUtil");
    }

    public static boolean isEmpty(String str) {
        if (str == null || str.trim().isEmpty() || "null".equals(str)) {
            return true;
        }
        return false;
    }
}