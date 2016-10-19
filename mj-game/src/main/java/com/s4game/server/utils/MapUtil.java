package com.s4game.server.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class MapUtil {
    
    public static final String SPLIT1 = ":";
    public static final String SPLIT2 = ";";

    public static void addMapValue(Map<String, Float> paramMap, String paramString, Float paramFloat) {
        Float localFloat = (Float) paramMap.get(paramString);
        if (localFloat == null) {
            paramMap.put(paramString, paramFloat);
        } else {
            paramMap.put(paramString, Float.valueOf(localFloat.floatValue() + paramFloat.floatValue()));
        }
    }

    public static void addMapValue(Map<String, Integer> paramMap, String paramString, Integer paramInteger) {
        Integer localInteger = (Integer) paramMap.get(paramString);
        if (localInteger == null) {
            paramMap.put(paramString, paramInteger);
        } else {
            paramMap.put(paramString, Integer.valueOf(localInteger.intValue() + paramInteger.intValue()));
        }
    }

    public static void addMapValue(Map<String, Float> paramMap1, Map<String, Float> paramMap2) {
        if ((paramMap2 == null) || (paramMap2.size() == 0)) {
            return;
        }
        Iterator localIterator = paramMap2.keySet().iterator();
        while (localIterator.hasNext()) {
            String str = (String) localIterator.next();
            Float localFloat1 = (Float) paramMap2.get(str);
            Float localFloat2 = (Float) paramMap1.get(str);
            if (localFloat2 == null) {
                paramMap1.put(str, localFloat1);
            } else {
                paramMap1.put(str, Float.valueOf(localFloat2.floatValue() + localFloat1.floatValue()));
            }
        }
    }

    public static <K, V> Object[] map2Array(Map<K, V> paramMap) {
        if ((paramMap == null) || (paramMap.size() == 0)) {
            return null;
        }
        Object[] arrayOfObject = new Object[paramMap.size()];
        int i = 0;
        Iterator localIterator = paramMap.keySet().iterator();
        while (localIterator.hasNext()) {
            Object localObject = localIterator.next();
            arrayOfObject[(i++)] = new Object[] { localObject, paramMap.get(localObject) };
        }
        return arrayOfObject;
    }

    public static void multipleAttribute(Map<String, Float> paramMap1, float paramFloat, Map<String, Float> paramMap2) {
        if ((paramMap1 == null) || (paramMap1.size() == 0) || (paramFloat == 0.0F)) {
            return;
        }
        Iterator localIterator = paramMap1.keySet().iterator();
        while (localIterator.hasNext()) {
            String str = (String) localIterator.next();
            float f = ((Float) paramMap1.get(str)).floatValue();
            addMapValue(paramMap2, str, Float.valueOf(f * paramFloat));
        }
    }

    public static void multipleAttribute(Map<String, Integer> paramMap1, int paramInt, Map<String, Integer> paramMap2) {
        if ((paramMap1 == null) || (paramMap1.size() == 0) || (paramInt == 0)) {
            return;
        }
        Iterator localIterator = paramMap1.keySet().iterator();
        while (localIterator.hasNext()) {
            String str = (String) localIterator.next();
            int i = ((Integer) paramMap1.get(str)).intValue();
            addMapValue(paramMap2, str, Integer.valueOf(i * paramInt));
        }
    }

    public static Map<String, Integer> convertDanyaoInfo(JSONObject paramJSONObject) {
        if ((paramJSONObject == null) || (paramJSONObject.size() == 0)) {
            return null;
        }
        HashMap localHashMap = new HashMap();
        Iterator localIterator = paramJSONObject.keySet().iterator();
        while (localIterator.hasNext()) {
            String str = (String) localIterator.next();
            Object localObject = paramJSONObject.get(str);
            localHashMap.put((String) str, (Integer) localObject);
        }
        return localHashMap;
    }

    public static <K, V> Object[] convertMap2Array(Map<K, V> paramMap) {
        if ((paramMap == null) || (paramMap.size() == 0)) {
            return null;
        }
        Object[] arrayOfObject = new Object[paramMap.size()];
        int i = 0;
        Iterator localIterator = paramMap.keySet().iterator();
        while (localIterator.hasNext()) {
            Object localObject = localIterator.next();
            arrayOfObject[(i++)] = new Object[] { localObject, paramMap.get(localObject) };
        }
        return arrayOfObject;
    }

    public static <K, V> String map2String(Map<K, V> paramMap) {
        if ((paramMap == null) || (paramMap.size() == 0)) {
            return null;
        }
        StringBuilder localStringBuilder = new StringBuilder();
        Iterator localIterator = paramMap.keySet().iterator();
        while (localIterator.hasNext()) {
            Object localObject = localIterator.next();
            localStringBuilder.append(";").append(localObject).append(":").append(paramMap.get(localObject));
        }
        return localStringBuilder.substring(1);
    }

    public static Map<String, Long> convertStr2Map(String paramString) {
        if (StringUtils.isEmpty(paramString)) {
            return null;
        }
        HashMap localHashMap = new HashMap();
        String[] arrayOfString1 = paramString.split(";");
        for (String str : arrayOfString1) {
            String[] arrayOfString3 = str.split(":");
            localHashMap.put(arrayOfString3[0], Long.valueOf(arrayOfString3[1]));
        }
        return localHashMap;
    }

    public static Map<String, Integer> convertStr2MapInteger(String paramString) {
        if (StringUtils.isEmpty(paramString)) {
            return null;
        }
        HashMap localHashMap = new HashMap();
        String[] arrayOfString1 = paramString.split(";");
        for (String str : arrayOfString1) {
            String[] arrayOfString3 = str.split(":");
            if (arrayOfString3.length < 2) {
                localHashMap.put(arrayOfString3[0], Integer.valueOf(1));
            } else {
                localHashMap.put(arrayOfString3[0], Integer.valueOf(arrayOfString3[1]));
            }
        }
        return localHashMap;
    }

    public static Map<Integer, Integer> convertStr2MapIntInt(String paramString) {
        if (StringUtils.isEmpty(paramString)) {
            return null;
        }
        HashMap localHashMap = new HashMap();
        String[] arrayOfString1 = paramString.split(";");
        for (String str : arrayOfString1) {
            String[] arrayOfString3 = str.split(":");
            localHashMap.put(Integer.valueOf(arrayOfString3[0]), Integer.valueOf(arrayOfString3[1]));
        }
        return localHashMap;
    }

    public static <T> int getIntVal(Map<T, Integer> paramMap, T paramT) {
        Integer localInteger = (Integer) paramMap.get(paramT);
        return localInteger == null ? 0 : localInteger.intValue();
    }
}