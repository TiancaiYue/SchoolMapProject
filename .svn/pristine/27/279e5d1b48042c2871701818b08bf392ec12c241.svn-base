package com.keyuan.schoolmap.common;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */

public class GsonUtil {
    // 将Json数据解析成相应的映射对象
    public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        T result = gson.fromJson(jsonData, type);
        return result;
    }

    // 将Json数组解析成相应的映射对象列表
    public static <T> List<T> parseJsonArrayWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>() {
        }.getType());
        return result;
    }

    /**
     * 加载所有数据不分页
     */
    public static <T> ArrayList<T> fromJsonList(String json, Class<T> cls) {
        if (!json.isEmpty()) {
            ArrayList<T> mList = new ArrayList<T>();
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (final JsonElement elem : array) {
                mList.add(new Gson().fromJson(elem, cls));
            }
            return mList;
        }
        return null;
    }

    /**
     * 加载所有数据分页
     */
    public static <T> ArrayList<T> fromJsonPageList(String json, Class<T> cls, ArrayList<T> mList) {
        if (!json.isEmpty()) {
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (final JsonElement elem : array) {
                mList.add(new Gson().fromJson(elem, cls));
            }
        }
        return mList;
    }

    /**
     * 加载所有数据分页
     */
    public static <T> List<T> fromJsonPageList1(String json, Class<T> cls, List<T> mList) {
        if (!json.isEmpty()) {
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (final JsonElement elem : array) {
                mList.add(new Gson().fromJson(elem, cls));
            }
        }
        return mList;
    }

    /**
     * 判断字符串是不是Json类型
     *
     * @param json
     * @return
     */
    public static boolean isGoodJson(String json) {
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            System.out.println("bad json: " + json);
            return false;
        }
    }
}
