package io.shenbinglife.sql.utils;

/**
 * 字符串插值引擎
 *
 * @author shenbing
 * @version 2018/7/25
 * @since since
 */
public interface InterpolationEngine {
    public interface Bindings {
        Object get(String name);
    }

    String combine(String template, Bindings bindings);
}