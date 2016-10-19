package com.s4game.server.utils;

public class ConvertObjectUtil {
    
    public static Integer object2Integer(Object object) {
        if ((null != object) && (!"".equals(object.toString().trim()))) {
            String str = object.toString();
            if (str.equals("true")) {
                return Integer.valueOf(1);
            }
            if (str.equals("false")) {
                return Integer.valueOf(0);
            }
            if ((object instanceof Double)) {
                return Integer.valueOf(((Double) object).intValue());
            }
            if ((object instanceof Integer)) {
                return (Integer) object;
            }
            if ((object instanceof Long)) {
                return Integer.valueOf(((Long) object).intValue());
            }
            if ((object instanceof Float)) {
                return Integer.valueOf(((Float) object).intValue());
            }
            int i = str.indexOf(".");
            if (i != -1) {
                str = str.substring(0, i);
                return Integer.valueOf(Integer.parseInt(str));
            }
            return Integer.valueOf(Integer.parseInt(str));
        }
        return null;
    }

    public static Integer object2int(Object object) {
        if ((null != object) && (!"".equals(object.toString().trim()))) {
            String str = object.toString();
            if (str.equals("true")) {
                return Integer.valueOf(1);
            }
            if (str.equals("false")) {
                return Integer.valueOf(0);
            }
            if ((object instanceof Double)) {
                return Integer.valueOf(((Double) object).intValue());
            }
            if ((object instanceof Integer)) {
                return (Integer) object;
            }
            if ((object instanceof Long)) {
                return Integer.valueOf(((Long) object).intValue());
            }
            if ((object instanceof Float)) {
                return Integer.valueOf(((Float) object).intValue());
            }
            try {
                int i = str.indexOf(".");
                if (i != -1) {
                    str = str.substring(0, i);
                    return Integer.valueOf(Integer.parseInt(str));
                }
                return Integer.valueOf(Integer.parseInt(str));
            } catch (Exception localException) {
                return Integer.valueOf(0);
            }
        }
        return Integer.valueOf(0);
    }

    public static String object2String(Object object) {
        if (object == null) {
            return "";
        }
        return object.toString();
    }

    public static String obj2StrOrNull(Object object) {
        if (object == null) {
            return null;
        }
        String str = object.toString();
        if (str.trim().isEmpty()) {
            return null;
        }
        return str;
    }

    public static boolean object2Boolean(Object object) {
        if ((object != null) && (!"".equals(object.toString().trim()))) {
            String str = object.toString().trim();
            return (!str.equals("0")) && (!str.equals("false"));
        }
        return false;
    }

    public static boolean object2boolean(Object object) {
        if (object == null) {
            return false;
        }
        if ((object instanceof Boolean)) {
            return ((Boolean) object).booleanValue();
        }
        Object localObject;
        if ((object instanceof String)) {
            localObject = (String) object;
            return (((String) localObject).equals("true")) || (((String) localObject).equals("1"));
        }
        if ((object instanceof Integer)) {
            localObject = (Integer) object;
            return ((Integer) localObject).intValue() > 0;
        }
        if ((object instanceof Long)) {
            localObject = (Long) object;
            return ((Long) localObject).longValue() > 0L;
        }
        if ((object instanceof Float)) {
            localObject = (Float) object;
            return ((Float) localObject).intValue() > 0;
        }
        if ((object instanceof Double)) {
            localObject = (Double) object;
            return ((Double) localObject).intValue() > 0;
        }
        return false;
    }

    public static Float object2Float(Object object) {
        if ((object != null) && (!"".equals(object.toString().trim()))) {
            String str = object.toString().trim();
            if (!str.equals("")) {
                return Float.valueOf(Float.parseFloat(str));
            }
        }
        return Float.valueOf(0.0F);
    }

    public static float obj2float(Object object) {
        if (obj2StrOrNull(object) == null) {
            return 0.0F;
        }
        if ((object instanceof Float)) {
            return ((Float) object).floatValue();
        }
        if ((object instanceof Integer)) {
            return ((Integer) object).floatValue();
        }
        if ((object instanceof Short)) {
            return ((Short) object).floatValue();
        }
        if ((object instanceof Double)) {
            return ((Double) object).floatValue();
        }
        if ((object instanceof Long)) {
            return ((Long) object).floatValue();
        }
        String str = object.toString().trim();
        try {
            return Float.parseFloat(str);
        } catch (Exception localException) {
        }
        return 0.0F;
    }

    public static Long object2Long(Object object) {
        if ((null != object) && (!"".equals(object.toString().trim()))) {
            String str = object.toString();
            if (str.equals("true")) {
                return Long.valueOf(1L);
            }
            if (str.equals("false")) {
                return Long.valueOf(0L);
            }
            if ((object instanceof Double)) {
                return Long.valueOf(((Double) object).longValue());
            }
            if ((object instanceof Integer)) {
                return Long.valueOf(((Integer) object).longValue());
            }
            if ((object instanceof Long)) {
                return (Long) object;
            }
            if ((object instanceof Float)) {
                return Long.valueOf(((Float) object).longValue());
            }
            int i = str.indexOf(".");
            if (i != -1) {
                str = str.substring(0, i);
                return Long.valueOf(Long.parseLong(str));
            }
            return Long.valueOf(Long.parseLong(str));
        }
        return null;
    }

    public static long obj2long(Object object) {
        if (obj2StrOrNull(object) == null) {
            return 0L;
        }
        if ((object instanceof Long)) {
            return ((Long) object).longValue();
        }
        if ((object instanceof Integer)) {
            return ((Integer) object).longValue();
        }
        if ((object instanceof Short)) {
            return ((Short) object).longValue();
        }
        if ((object instanceof Double)) {
            return ((Double) object).longValue();
        }
        if ((object instanceof Float)) {
            return ((Float) object).longValue();
        }
        String str = object.toString().trim();
        int i = str.indexOf(".");
        try {
            if (i > -1) {
                str = str.substring(0, i);
            }
            return Long.parseLong(str);
        } catch (Exception localException) {
        }
        return 0L;
    }

    public static boolean isEmpty(String paramString) {
        return (null == paramString) || (paramString.trim().isEmpty());
    }
}