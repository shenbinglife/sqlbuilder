package io.shenbinglife.sql.utils;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/7/25
 * @since since
 */
public class NumberUtils {

    public static boolean isNumber(Object object) {
        if(object == null) {
            throw new IllegalArgumentException("can not judge null is a number object");
        }

        Class clz = object.getClass();
        return clz == Integer.TYPE || clz == Double.TYPE || clz == Float.TYPE ||
                Number.class.isAssignableFrom(clz);
    }


}
