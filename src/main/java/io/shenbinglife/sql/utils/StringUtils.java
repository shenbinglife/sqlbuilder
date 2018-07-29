package io.shenbinglife.sql.utils;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/7/25
 * @since since
 */
public class StringUtils {

    public static boolean notNull(Object object) {
        return object != null;
    }

    public static boolean isBlank(String string) {
        return !notBlank(string);
    }

    public static boolean notBlank(String cs) {
        if(cs == null)
            return false;
        int strLen = cs.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return true;
            }
        }
        return false;
    }

    public static String toUnderline(String simpleName) {
        return simpleName.replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
    }


}
