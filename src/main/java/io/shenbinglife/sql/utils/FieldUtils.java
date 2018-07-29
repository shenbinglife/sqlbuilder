package io.shenbinglife.sql.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/7/29
 * @since since
 */
public class FieldUtils {

    public static Field[] getAllFields(Class clazz) {
        if(clazz == Object.class) {
            return new Field[0];
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(declaredFields));
        for(Class p = clazz.getSuperclass(); p != Object.class; p = p.getSuperclass()) {
            declaredFields = p.getDeclaredFields();
            fields.addAll(Arrays.asList(declaredFields));
        }
        return fields.toArray(new Field[fields.size()]);
    }

    public static boolean isStatic(Field field) {
        return Modifier.isStatic(field.getModifiers());
    }

    public static String[] getFieldsAsUnderline(Class clz) {
        Field[] allFields = getAllFields(clz);
        List<String> strings = new ArrayList<>(allFields.length);
        for(Field field : allFields) {
            if(isStatic(field)){
                continue;
            }
            strings.add(StringUtils.toUnderline(field.getName()));

        }
        return strings.toArray(new String[strings.size()]);
    }
}
