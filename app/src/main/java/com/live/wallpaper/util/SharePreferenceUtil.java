package com.live.wallpaper.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 作者：shaoshuai
 * 时间：2018/3/21 下午4:59
 * 邮箱：shaoshuai1@yonyou.com
 * 说明：SharePreference的存储工具类
 */
public class SharePreferenceUtil {

    /**
     * 保存在手机里面的文件名
     */
    public static final String                   FILE_NAME = "yonyou_data";
    public static       SharedPreferences        sp;
    private static      SharedPreferences.Editor editor;

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     */
    public static void put(Context context, String key, Object object) {

        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            //			editor.putString(key, object.toString());
        }
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(Context context, String key, Object defaultObject) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }

    public static boolean getValueBoolean(Context mContext, String key) {
        if (sp == null) {
            sp = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
            editor = sp.edit();
        }
        boolean value = sp.getBoolean(key, false);
        return value;
    }

    public static void saveValue(Context mContext, String key, boolean value) {
        if (sp == null) {
            sp = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
            editor = sp.edit();
        }
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void saveValue(Context mContext, String key, String value) {
        if (sp == null) {
            sp = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
            editor = sp.edit();
        }
        editor.putString(key, value);
        editor.commit();
    }


    public static void keepContent(Context context, String tag, Object content) {
        SharedPreferences        pref   = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (content instanceof Boolean) {
            Boolean new_content = (Boolean) content;
            editor.putBoolean(tag, new_content);

        } else if (content instanceof String) {
            String new_content = (String) content;
            editor.putString(tag, new_content);

        } else if (content instanceof Long) {
            Long new_content = (Long) content;
            editor.putLong(tag, new_content);

        } else if (content instanceof Integer) {
            int new_content = (Integer) content;
            editor.putInt(tag, new_content);

        } else if (content instanceof Float) {
            float new_content = (Float) content;
            editor.putFloat(tag, new_content);

        } else if (content instanceof Double) {
            editor.putLong(tag, Double.doubleToRawLongBits((Double) content));
        }
        editor.commit();
    }

}
