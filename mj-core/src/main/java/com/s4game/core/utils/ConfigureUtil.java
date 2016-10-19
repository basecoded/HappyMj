package com.s4game.core.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author zeusgooogle
 * @date 2014-9-30 下午05:03:39
 */
public class ConfigureUtil {

    private static Map<String, Properties> propMap = new HashMap<String, Properties>();

    public ConfigureUtil() {
    }

    public static int getIntProp(String fileName, String key) {
        return Integer.valueOf(getStringPro(getProperties(fileName), key)).intValue();
    }

    public static double getDoubleProp(String fileName, String key) {
        return Double.valueOf(getStringPro(getProperties(fileName), key)).doubleValue();
    }

    public static String getProp(String fileName, String key) {
        return getStringPro(getProperties(fileName), key);
    }

    private static Properties getProperties(String name) {
        if (!propMap.containsKey(name)) {
            synchronized (propMap) {
                if (!propMap.containsKey(name)) {
                    try {
                        Properties properties = new Properties();
                        InputStream inputStream = ClassLoader.getSystemResourceAsStream(name);
                        
                        properties.load(inputStream);
                        propMap.put(name, properties);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return (Properties) propMap.get(name);
    }

    private static String getStringPro(Properties properties, String key) {
        if (null == properties) {
            throw new IllegalStateException("' properties ' has not been initialized.");
        }
        
        String str = properties.getProperty(key);
        if (null == str) {
            throw new RuntimeException("' " + key + " ' was not configured.");
        }
        return str.trim();
    }

}
