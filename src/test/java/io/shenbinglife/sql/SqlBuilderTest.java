package io.shenbinglife.sql;

import static io.shenbinglife.sql.SqlBuilder.*;
import static io.shenbinglife.sql.utils.StringUtils.notBlank;
import static org.junit.Assert.*;

import io.shenbinglife.sql.utils.ReflectSqlUtils;
import io.shenbinglife.sql.utils.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/7/29
 * @since since
 */
public class SqlBuilderTest {

    /**
     * sql builder
     */
    @Test
    public void builder() {
        String build = sql("select * from user").build();
        String sql = "select * from user";
        assertEquals("sql build", sql, build);
    }

    /**
     * SQL 追加字符串
     */
    @Test
    public void append() {
        String build = sql("select * from").append(" user").build();
        String sql = "select * from user";
        assertEquals("sql build", sql, build);
    }

    /**
     * SQL and和or 连接语句
     */
    @Test
    public void sqlAnd() {
        String build = sql("select * from user")
                .where()
                .and("age = 1")
                .or("name = 'shenbing'")
                .build();
        String sql = "select * from user where 1=1  and age = 1 or name = 'shenbing'";
        assertEquals("sql build with 'and', 'or' using string", sql, build);

        String build_2 = sql("select * from user")
                .where().and(sql("age = 1"))
                .or(sql("name = 'shenbing'"))
                .build();
        assertEquals("sql build with 'and', 'or' using sqlBuilder", sql, build_2);

        String build_3 = sql("select * from user")
                .where().and("   ")
                .or(sql("   \t\n")).build();
        String sql_3 = "select * from user where 1=1 ";
        assertEquals("sql build with 'and', 'or' using blank string", sql_3, build_3);
    }

    /**
     * SQL查询条件：in, not in, is NULL, like, > , < , <>
     */
    @Test
    public void moreBuilder() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-11");
        String build = sql("select * from user").where()
                .and(like("name", "shen"))
                .and_(gt("age", 8))
                .or(lt("create_time", date))
                .or(in("role", "admin", "test"))
                .or_(isNull("tenant"))
                .order("modify_time", false)
                .build();
        String sql = "select * from user " +
                "where 1=1  " +
                "and name like '%shen%' " +
                "and (age > 8) " +
                "or create_time < '2018-11-11 00:00:00' " +
                "or role in ('admin','test') " +
                "or (tenant is NULL ) " +
                "order by modify_time desc ";
        assertEquals("build with sql grammar", sql, build);

    }

    /**
     * 带条件的SQL构建
     */
    @Test
    public void sqlCondition() {
        String build = sql("select * from user").when(false).build();
        assertEquals("sqlBuilder when false returns empty", "", build);

        int age = 999;
        String name = "shen";
        String build_2 = sql("select * from user")
                .where()
                .and(gt("age", age).when(age < 99))
                .and(like("name", name).when(notBlank(name)))
                .and(like("account", name).when(() -> !name.isEmpty()))
                .build();
        String sql_2 = "select * from user where 1=1  and name like '%shen%' and account like '%shen%'";
        assertEquals("sqlBuilder when false returns empty", sql_2, build_2);
    }

    /**
     * sql 插值测试
     */
    @Test
    public void interpolation() {
        String name = "shenbing";
        int age = 26;
        String build = sql("select * from user").where()
                .and(like("name", "{0}"))
                .and(eq("age", "{1}"))
                .or("account = {0}")
                .build(name, age);

        String sql = "select * from user where 1=1  and name like '%shenbing%' " +
                "and age = '26' or account = shenbing";
        assertEquals("sql interpolation", sql, build);
    }

    /**
     * 基于反射类的字段构建 SQL select语句
     */
    @Test
    public void reflectSqlBuilder() {
        String build = ReflectSqlBuilder.select(User.class).build();
        String sql = "select name,age,create_time from user";

        assertEquals("select sql using reflected fields", build, sql);
    }

    /**
     * 带注解标记的User类，控制SQL的表名和字段名，允许忽略字段
     */
    @Test
    public void reflectSqlBuilderUsingAnno() {
        String build = ReflectSqlBuilder.select(AnnotatedUser.class).build();
        String sql = "select user_name,create_time from user";

        assertEquals("select sql using reflected fields", build, sql);
    }
}
