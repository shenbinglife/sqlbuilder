package io.shenbinglife.sql.utils;

import io.shenbinglife.sql.anno.Table;
import io.shenbinglife.sql.anno.TableField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/7/29
 * @since since
 */
public class ReflectSqlUtils {

    private static final Logger logger = LoggerFactory.getLogger(ReflectSqlUtils.class);

    public static String getTableName(Class clz) {
        Table table = (Table) clz.getDeclaredAnnotation(Table.class);
        if(table == null) {
            return StringUtils.toUnderline(clz.getSimpleName());
        }
        return table.value();
    }

    public static String[] getTableFields(Class clz) {
        Field[] allFields = FieldUtils.getAllFields(clz);
        List<String> fieldsStr = new ArrayList<>(allFields.length);
        for (Field field : allFields) {
            if(FieldUtils.isStatic(field)) {
                continue;
            }
            TableField tableField = field.getAnnotation(TableField.class);
            if(tableField == null) {
                fieldsStr.add(StringUtils.toUnderline(field.getName()));
            } else if(!tableField.ignore()) {
                fieldsStr.add(tableField.value());
            }
        }
        return fieldsStr.toArray(new String[fieldsStr.size()]);
    }

}
