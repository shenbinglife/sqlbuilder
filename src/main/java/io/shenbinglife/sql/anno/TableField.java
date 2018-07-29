package io.shenbinglife.sql.anno;

import java.lang.annotation.*;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/7/28
 * @since since
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableField {

    /**
     * table field name
     */
    String value() default "";

    boolean ignore() default false;

    JdbcType jdbcType() default JdbcType.AUTO;
}
