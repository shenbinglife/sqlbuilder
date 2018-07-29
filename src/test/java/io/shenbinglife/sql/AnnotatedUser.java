package io.shenbinglife.sql;

import io.shenbinglife.sql.anno.Table;
import io.shenbinglife.sql.anno.TableField;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/7/29
 * @since since
 */
@Table("user")
public class AnnotatedUser {

    @TableField("user_name")
    private String name;

    @TableField(ignore = true)
    private String age;

    private String createTime;
}
