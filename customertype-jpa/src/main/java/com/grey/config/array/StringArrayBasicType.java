package com.grey.config.array;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;

public class StringArrayBasicType extends AbstractSingleColumnStandardBasicType<String[]> {

    /**
     * sql 和 java类型描述的映射
     */
    public StringArrayBasicType() {
        super(ArraySqlTypeDescriptor.INSTANCE, StringArrayTypeDescriptor.INSTANCE);

    }

    /**
     * 数据类型的名称
     * @return
     */
    @Override
    public String getName() {
        return "string-array";
    }

    /**
     * 是否向基本类型中注入java类型
     * @return
     */
    @Override
    protected boolean registerUnderJavaType() {
        return true;
    }

}