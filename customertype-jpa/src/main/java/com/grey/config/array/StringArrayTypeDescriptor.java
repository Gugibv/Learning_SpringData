package com.grey.config.array;

/**
 * java中数据类型的描述
 */
public class StringArrayTypeDescriptor extends AbstractArrayTypeDescriptor<String[]> {

    public static final StringArrayTypeDescriptor INSTANCE = new StringArrayTypeDescriptor();

    /**
     * java数组类型和其子元素在数据库中类型
     */
    public StringArrayTypeDescriptor() {
        super(String[].class,"text");
    }
}