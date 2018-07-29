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
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    /**
     * table name
     */
    String value() default "";
}
