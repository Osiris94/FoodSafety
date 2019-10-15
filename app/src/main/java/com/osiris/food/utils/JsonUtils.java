package com.osiris.food.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.jessyan.autosize.utils.LogUtils;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * Description : Json转换工具类
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/17
 */
public class JsonUtils {

    public static JSONObject getJSONObject(String jsonString){
        JSONObject object = null;
        try {
            object = JSONObject.parseObject(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }


    /**
     * 将对象准换为json字符串
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String serialize(T object) {
        String jsonStr = "";
        try {
            jsonStr  = JSON.toJSONString(object);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return jsonStr;

    }

    /**
     * 将json字符串转换为对象
     * @param json
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T deserialize(String json, Class<T> clz){
        T t = null;
        try {
            t = parseObject(json, clz);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;

    }

    /**
     * 用fastjson 将jsonString 解析成 List<Map<String,Object>>
     *
     * @param jsonString
     * @return
     */
    public static List<Map<String, Object>> getListMap(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            // 两种写法
            // list = JSON.parseObject(jsonString, new
            // TypeReference<List<Map<String, Object>>>(){}.getType());

            list = parseObject(jsonString,
                    new TypeReference<List<Map<String, Object>>>() {
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    public static <T> List<T> deserializeList(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
//    /**
//     * 将json对象转换为实体对象
//     * @param json
//     * @param clz
//     * @param <T>
//     * @return
//     * @throws
//     */
//    public static <T> T deserialize(JSONObject json, Class<T> clz){
//
//        return json.;
//    }

//    /**
//     * 将json字符串转换为对象
//     * @param json
//     * @param type
//     * @param <T>
//     * @return
//     */
//    public static <T> T deserialize(String json, Type type) throws JsonSyntaxException {
//        return mGson.fromJson(json, type);
//    }


    public final static String JSON_SUFFIX = ".json";

    private final static Gson mGson = new Gson();

    private JsonUtils() {
    }

    public static boolean isBadJson(String json) {
        return !isGoodJson(json);
    }

    public static boolean isGoodJson(String json) {
        if (StringUtil.isBlank(json)) {
            return false;
        }
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            LogUtils.e("bad json: " + json);
            return false;
        }
    }

    public static <T> T fromJson(String json, Class<T> cls) {
        return mGson.fromJson(json, cls);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return mGson.fromJson(json, typeOfT);
    }

    public static <T> T fromJson(JsonElement json, Class<T> cls) {
        return mGson.fromJson(json, cls);
    }

    public static <T> T fromJson(JsonElement json, Type typeOfT) {
        return mGson.fromJson(json, typeOfT);
    }

    public static String toJson(JsonElement element) {
        return mGson.toJson(element);
    }

    public static String toJson(Object obj) {
        return mGson.toJson(obj);
    }

    public static String toJson(Object obj, Type typeOfObj) {
        return mGson.toJson(obj, typeOfObj);
    }

    public static JsonElement toJsonTree(Object obj) {
        return mGson.toJsonTree(obj);
    }

    public static JsonElement toJsonTree(Object obj, Type typeOfObj) {
        return mGson.toJsonTree(obj, typeOfObj);
    }



}
