package io.shenbinglife.sql;

import java.util.function.BooleanSupplier;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/7/25
 * @since since
 */
public class When {

    private boolean bool;

    private When(boolean test) {
        this.bool = test;
    }

    private When(BooleanSupplier supplier) {
        this.bool = supplier.getAsBoolean();
    }

    public static When when(boolean test) {
        return new When(test);
    }

    public static When when(BooleanSupplier supplier) {
        return new When(supplier);
    }

    public static SqlBuilder when(boolean test, SqlBuilder builder) {
        return test ? builder : SqlBuilder.sql();
    }

    public static SqlBuilder when(BooleanSupplier supplier, SqlBuilder builder) {
        return supplier.getAsBoolean() ? builder : SqlBuilder.sql();
    }

    public SqlBuilder like(String field, String value) {
        return bool? SqlBuilder.like(field, value) : SqlBuilder.sql();
    }

    public SqlBuilder eq(String field, String value) {
        return bool? SqlBuilder.eq(field, value) : SqlBuilder.sql();
    }

    public SqlBuilder ne(String field, String value) {
        return bool? SqlBuilder.ne(field, value) : SqlBuilder.sql();
    }

    public SqlBuilder gt(String field, String value) {
        return bool? SqlBuilder.gt(field, value) : SqlBuilder.sql();
    }

    public SqlBuilder lt(String field, String value) {
        return bool? SqlBuilder.lt(field, value) : SqlBuilder.sql();
    }


}
