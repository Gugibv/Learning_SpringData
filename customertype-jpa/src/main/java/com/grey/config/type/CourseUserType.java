package com.grey.config.type;

import com.grey.entity.type.CourseBean;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.hibernate.usertype.DynamicParameterizedType;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class CourseUserType implements CompositeUserType, DynamicParameterizedType {
    /**
     * 属性名称
     * @return
     */
    @Override
    public String[] getPropertyNames() {
        return new String[]{"course","grade"};
    }

    /**
     * 属性数据类型
     * @return
     */
    @Override
    public Type[] getPropertyTypes() {
        return new Type[]{StandardBasicTypes.TEXT,StandardBasicTypes.LONG};
    }

    /**
     * 从自定义对象中获取值
     * @param o     自定义数据对象
     * @param i     获取对象中指定顺序的值
     * @return
     * @throws HibernateException
     */
    @Override
    public Object getPropertyValue(Object o, int i) throws HibernateException {
        CourseBean courseBean = (CourseBean) o;
        if (i == 0) {
            return courseBean.getCourse();
        } else {
            return courseBean.getGrade();
        }

    }

    @Override
    public void setPropertyValue(Object o, int i, Object o1) throws HibernateException {
        throw new UnsupportedOperationException("Course is immutable");
    }

    @Override
    public Class returnedClass() {
        return CourseBean.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        return o == o1 || !(o == null || o1 == null) && o.equals(o1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    /**
     * 从自定义数据的结果中获取值
     * @return
     */
    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings,
                              SharedSessionContractImplementor sharedSessionContractImplementor,
                              Object o) throws HibernateException, SQLException {
        if (resultSet.wasNull()) {
            return null;
        }
        String course = resultSet.getString(strings[0]);
        int grade = resultSet.getInt(strings[1]);
        return new CourseBean(course,grade);
    }

    /**
     * 将自定义对象设置到Statement中
     * @param preparedStatement
     * @param o
     * @param i
     * @param sharedSessionContractImplementor
     * @throws HibernateException
     * @throws SQLException
     */
    @Override
    public void nullSafeSet(PreparedStatement preparedStatement,
                            Object o,
                            int i,
                            SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        if (o == null) {
            preparedStatement.setNull(i, StandardBasicTypes.TEXT.sqlType());
            preparedStatement.setNull(i + 1, StandardBasicTypes.LONG.sqlType());
        } else {
            CourseBean amount = (CourseBean) o;
            preparedStatement.setString(i, amount.getCourse());
            preparedStatement.setLong(i + 1, amount.getGrade());
        }
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
        return o.toString();
    }

    @Override
    public Object assemble(Serializable serializable, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return CourseBean.fromString((String) serializable);
    }

    @Override
    public Object replace(Object o, Object o1, SharedSessionContractImplementor sharedSessionContractImplementor, Object o2) throws HibernateException {
        return o;
    }

    @Override
    public void setParameterValues(Properties properties) {

    }
}

