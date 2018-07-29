package io.shenbinglife.sql;

import io.shenbinglife.sql.utils.*;

import java.util.Arrays;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/7/25
 * @since since
 */
public class SqlBuilder {

    StringBuilder sql;

    public SqlBuilder() {
        this.sql = new StringBuilder();
    }

    public static SqlBuilder sql() {
        SqlBuilder builder = new SqlBuilder();
        builder.sql = new StringBuilder();
        return builder;
    }

    public static SqlBuilder sql(String sql) {
        SqlBuilder builder = new SqlBuilder();
        builder.sql = new StringBuilder(sql);
        return builder;
    }



    public static SqlBuilder like(String field, String value) {
        return SqlBuilder.sql(field).append(" like '%").append(value).append("%'");
    }

    public static SqlBuilder eq(String field, Object value) {
        if (NumberUtils.isNumber(value)) {
            return SqlBuilder.sql(field).append(" = ").append(value);
        } else if (DateUtils.isDate(value)) {
            return sql(field).append(" = '").append(DateUtils.format(value)).append("'");
        } else {
            return SqlBuilder.sql(field).append(" = '").append(value).append("'");
        }
    }

    public static SqlBuilder ne(String field, Object value) {
        if (NumberUtils.isNumber(value)) {
            return SqlBuilder.sql(field).append(" <> ").append(value);
        } else if (DateUtils.isDate(value)) {
            return sql(field).append(" <> '").append(DateUtils.format(value)).append("'");
        } else {
            return SqlBuilder.sql(field).append(" <> '").append(value).append("'");
        }
    }

    public static SqlBuilder gt(String field, Object value) {
        if (NumberUtils.isNumber(value)) {
            return SqlBuilder.sql(field).append(" > ").append(String.valueOf(value));
        } else if (DateUtils.isDate(value)) {
            return sql(field).append(" > '").append(DateUtils.format(value)).append("'");
        } else {
            return SqlBuilder.sql(field).append(" > '").append(String.valueOf(value)).append("'");
        }
    }

    public static SqlBuilder lt(String field, Object value) {
        if (NumberUtils.isNumber(value)) {
            return SqlBuilder.sql(field).append(" < ").append(String.valueOf(value));
        } else if (DateUtils.isDate(value)) {
            return sql(field).append(" < '").append(DateUtils.format(value)).append("'");
        } else {
            return SqlBuilder.sql(field).append(" < '").append(String.valueOf(value)).append("'");
        }
    }

    public static SqlBuilder isNull(String field) {
        return sql().append(field).append(" is NULL ");
    }

    public static SqlBuilder in(String field, Object... array) {
        if (array == null || array.length == 0) {
            return sql();
        }
        String collect = Arrays.stream(array).map(Object::toString).map(item -> "'" + item + "'")
                        .collect(Collectors.joining(","));
        return sql(field).append(" in (").append(collect).append(")");
    }

    public static SqlBuilder notIn(String field, Object... array) {
        if (array == null || array.length == 0) {
            return sql();
        }
        String collect = Arrays.stream(array).map(Object::toString).map(item -> "'" + item + "'")
                .collect(Collectors.joining(","));
        return sql(field).append(" not in (").append(collect).append(")");
    }


    public String build() {
        return toString();
    }

    /**
     * 字符串插值设置SQL参数 <br>
     * sql example : select {0} from {1} <br>
     * params example: "id,name,age", "user" <br>
     * return : select id,name,age from user
     * 
     * @param params 插值数组
     * @return sql
     */
    public String build(Object... params) {
        String sql = toString();
        return Interpolations.indexed(sql, params);
    }

    public SqlBuilder append(String sql) {
        this.sql.append(sql);
        return this;
    }

    public SqlBuilder append(Object sql) {
        this.sql.append(sql);
        return this;
    }

    public SqlBuilder where(SqlBuilder builder) {
        if(!builder.isBlank()) {
            sql.append(" where ").append(builder);
        }
        return this;
    }

    public SqlBuilder where(String builder) {
        if(!StringUtils.isBlank(builder)) {
            sql.append(" where ").append(builder);
        }
        return this;
    }

    public SqlBuilder where() {
        sql.append(" where 1=1 ");
        return this;
    }

    public SqlBuilder order(String field, boolean asc) {
        sql.append(" order by ").append(field);
        if (!asc) {
            sql.append(" desc ");
        }
        return this;
    }

    public SqlBuilder order(String field) {
        return order(field, true);
    }

    public SqlBuilder and(SqlBuilder builder) {
        if (!builder.isBlank()) {
            sql.append(" and ").append(builder);
        }
        return this;
    }

    public SqlBuilder and_(SqlBuilder builder) {
        if (!builder.isBlank()) {
            sql.append(" and (").append(builder).append(")");
        }
        return this;
    }

    public SqlBuilder and(String builder) {
        if (StringUtils.notBlank(builder)) {
            sql.append(" and ").append(builder);
        }
        return this;
    }

    public SqlBuilder and_(String builder) {
        if (StringUtils.notBlank(builder)) {
            sql.append(" and (").append(builder).append(")");
        }
        return this;
    }

    public SqlBuilder or(SqlBuilder builder) {
        if (!builder.isBlank()) {
            sql.append(" or ").append(builder);
        }
        return this;
    }

    public SqlBuilder or_(SqlBuilder builder) {
        if (!builder.isBlank()) {
            sql.append(" or (").append(builder).append(")");
        }
        return this;
    }

    public SqlBuilder or(String builder) {
        if (StringUtils.notBlank(builder)) {
            sql.append(" or ").append(builder);
        }
        return this;
    }

    public SqlBuilder or_(String builder) {
        if (StringUtils.notBlank(builder)) {
            sql.append(" or (").append(builder).append(")");
        }
        return this;
    }

    public SqlBuilder when(boolean test) {
        return test ? this : SqlBuilder.sql();
    }

    public SqlBuilder when(BooleanSupplier supplier) {
        return supplier.getAsBoolean() ? this : SqlBuilder.sql();
    }

    public boolean isBlank() {
        String str = this.sql.toString();
        return StringUtils.isBlank(str);
    }

    @Override
    public String toString() {
        return sql.toString();
    }
}
