package com.grey.config.array;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.MutabilityPlan;
import org.hibernate.type.descriptor.java.MutableMutabilityPlan;
import com.grey.utils.ArrayUtil;

import java.sql.Array;
import java.sql.SQLException;
import java.util.Arrays;


public abstract class AbstractArrayTypeDescriptor<T>
        extends AbstractTypeDescriptor<T> {

    private Class<T> arrayObjectClass;

    private String sqlArrayType;

    public AbstractArrayTypeDescriptor(Class<T> arrayObjectClass, String sqlArrayType) {
        this(arrayObjectClass, (MutabilityPlan<T>) new MutableMutabilityPlan<Object>() {
            @Override
            protected T deepCopyNotNull(Object value) {
                return ArrayUtil.deepCopy(value);
            }
        }, sqlArrayType);
    }

    protected AbstractArrayTypeDescriptor(Class<T> arrayObjectClass, MutabilityPlan<T> mutableMutabilityPlan,
                                          String sqlArrayType) {
        super(arrayObjectClass, mutableMutabilityPlan);
        this.arrayObjectClass = arrayObjectClass;
        this.sqlArrayType = sqlArrayType;
    }

    @Override
    public boolean areEqual(Object one, Object another) {
        if (one == another) {
            return true;
        }
        if (one == null || another == null) {
            return false;
        }
        return ArrayUtil.isEquals(one, another);
    }

    @Override
    public String toString(Object value) {
        return Arrays.deepToString(ArrayUtil.wrapArray(value));
    }

    /**
     * 展开数据的格式
     *
     * @param string
     * @return
     */
    @Override
    public T fromString(String string) {
        T t = ArrayUtil.fromString(string, arrayObjectClass);
        return t;
    }

    @Override
    public String extractLoggableRepresentation(T value) {
        return (value == null) ? "null" : toString(value);
    }

    /**
     * 将处理过的Java类型的实例展开为请求的类型。
     *
     * @param value
     * @param type
     * @param options
     * @param <X>
     * @return
     */
    @SuppressWarnings({"unchecked"})
    @Override
    public <X> X unwrap(T value, Class<X> type, WrapperOptions options) {
        return (X) ArrayUtil.wrapArray(value);
    }

    /**
     * 将值包装为我们处理过的Java类型。
     *
     * @param value
     * @param options
     * @param <X>
     * @return
     */
    @Override
    public <X> T wrap(X value, WrapperOptions options) {
        if (value instanceof Array) {
            Array array = (Array) value;
            try {
                return ArrayUtil.unwrapArray((Object[]) array.getArray(), arrayObjectClass);
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return (T) value;
    }

    protected String getSqlArrayType() {
        return sqlArrayType;
    }
}

