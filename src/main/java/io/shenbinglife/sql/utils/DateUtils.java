package io.shenbinglife.sql.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/7/25
 * @since since
 */
public class DateUtils {

    public static boolean isDate(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("can not judge null is a date object");
        }
        return object instanceof Date || object instanceof LocalDate || object instanceof LocalDateTime;
    }

    public static String format(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("can not format null of date");
        }
        if (value instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(value);
        } else if (value instanceof LocalDate) {
            return ((LocalDate) value).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else if (value instanceof LocalDateTime) {
            return ((LocalDateTime) value).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        throw new IllegalArgumentException("this is not a date object, can not be formatted:" + value);
    }
}
