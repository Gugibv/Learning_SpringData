package com.grey.config.array;

import org.hibernate.type.descriptor.ValueBinder;
import org.hibernate.type.descriptor.ValueExtractor;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.JavaTypeDescriptor;
import org.hibernate.type.descriptor.sql.BasicBinder;
import org.hibernate.type.descriptor.sql.BasicExtractor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

import java.sql.*;

/**
 * 数据库中数据类型的描述
 */
public class ArraySqlTypeDescriptor implements SqlTypeDescriptor {

    public static final ArraySqlTypeDescriptor INSTANCE = new ArraySqlTypeDescriptor();

    /**
     * 描述的SQL类型
     * @return
     */
    @Override
    public int getSqlType() {
        return Types.ARRAY;
    }

    /**
     * 是否可以映射
     * @return
     */
    @Override
    public boolean canBeRemapped() {
        return true;
    }

    /**
     * 获取能够处理传递的描述符所描述类型的值的绑定器。
     * SQL数据转java数据
     * @param javaTypeDescriptor
     * @param <X>
     * @return
     */
    @Override
    public <X> ValueBinder<X> getBinder(final JavaTypeDescriptor<X> javaTypeDescriptor) {
        return new BasicBinder<X>(javaTypeDescriptor, this) {
            @Override
            protected void doBind(PreparedStatement st, X value, int index, WrapperOptions options) throws SQLException {
                AbstractArrayTypeDescriptor<Object> abstractArrayTypeDescriptor = (AbstractArrayTypeDescriptor<Object>) javaTypeDescriptor;
                st.setArray(index, st.getConnection().createArrayOf(
                        abstractArrayTypeDescriptor.getSqlArrayType(),
                        abstractArrayTypeDescriptor.unwrap(value, Object[].class, options)
                ));
            }

            @Override
            protected void doBind(CallableStatement st, X value, String name, WrapperOptions options)
                    throws SQLException {
                throw new UnsupportedOperationException("Binding by name is not supported!");
            }
        };
    }

    /**
     * 获取能够处理传递的描述符所描述类型的值的提取器。
     * java数据转SQL数据
     * @param javaTypeDescriptor
     * @param <X>
     * @return
     */
    @Override
    public <X> ValueExtractor<X> getExtractor(final JavaTypeDescriptor<X> javaTypeDescriptor) {
        return new BasicExtractor<X>(javaTypeDescriptor, this) {
            @Override
            protected X doExtract(ResultSet rs, String name, WrapperOptions options) throws SQLException {
                return javaTypeDescriptor.wrap(rs.getArray(name), options);
            }

            @Override
            protected X doExtract(CallableStatement statement, int index, WrapperOptions options) throws SQLException {
                return javaTypeDescriptor.wrap(statement.getArray(index), options);
            }

            @Override
            protected X doExtract(CallableStatement statement, String name, WrapperOptions options) throws SQLException {
                return javaTypeDescriptor.wrap(statement.getArray(name), options);
            }
        };
    }

}