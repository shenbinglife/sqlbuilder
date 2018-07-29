package io.shenbinglife.sql.utils;

import java.util.regex.Pattern;

/**
 * 基于索引的字符串插值引擎
 */
public class IndexedInterpolationEngine extends PatternInterpolationEngine {
    private static final Pattern PATTERN = Pattern.compile("\\{([0-9]+)\\}");

    public IndexedInterpolationEngine() {
        super(PATTERN);
    }

    public static Bindings createBindings(Object... array) {
        return new ArrayBindings(array);
    }

    private static final class ArrayBindings implements Bindings {
        private final Object[] array;

        public ArrayBindings(Object[] array) {
            this.array = array;
        }

        @Override
        public Object get(String name) {
            return array[Integer.parseInt(name)];
        }
    }
}