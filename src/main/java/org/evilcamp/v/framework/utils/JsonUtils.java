package org.evilcamp.v.framework.utils;


import com.alibaba.fastjson.JSON;

/**
 * json工具类
 */
public class JsonUtils {

    /**
     * @param obj
     * @return string
     */
    public static String getJsonString(Object obj){
        return JSON.toJSONString(obj);
    }

    /**
     *
     * @param objStr
     * @return object
     */
    public static Object getJsonObject(String objStr){
        return JSON.parse(objStr);
    }
}
