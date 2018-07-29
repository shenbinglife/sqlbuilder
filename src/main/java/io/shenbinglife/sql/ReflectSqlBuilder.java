package io.shenbinglife.sql;

import io.shenbinglife.sql.utils.FieldUtils;
import io.shenbinglife.sql.utils.ReflectSqlUtils;
import io.shenbinglife.sql.utils.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/7/28
 * @since since
 */
public class ReflectSqlBuilder extends SqlBuilder{

    public static SqlBuilder select(Class clz) {
        if(clz == null) {
            throw new NullPointerException("Class param can not be null");
        }
        String table = ReflectSqlUtils.getTableName(clz);
        String[] fields_as_underline = ReflectSqlUtils.getTableFields(clz);
        String fields = Arrays.stream(fields_as_underline).collect(Collectors.joining(","));
        return sql("select ").append(fields).append(" from ").append(table);
    }
}
