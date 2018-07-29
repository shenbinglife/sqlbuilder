package io.shenbinglife.sql.utils;

/**
 * 字符串插值工具
 *
 * @author shenbing
 * @version 2018/7/25
 * @since since
 */
public class Interpolations {
    private static final InterpolationEngine INDEXED_ENGINE = new IndexedInterpolationEngine();

    public static String indexed(String template, Object... bindings) {
        return INDEXED_ENGINE.combine(template,
                IndexedInterpolationEngine.createBindings(bindings));
    }

}
