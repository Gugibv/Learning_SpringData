package com.grey.config.converter;

import com.alibaba.fastjson.JSON;
import com.grey.entity.converter.ItemBean;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)//Default for  MonetaryAmount properties
public class ItemBeanConverter implements AttributeConverter<ItemBean, String> {
    @Override
    public String convertToDatabaseColumn(ItemBean attribute) {
        return JSON.toJSONString(attribute);
    }

    @Override
    public ItemBean convertToEntityAttribute(String dbData) {
        return ItemBean.fromString(dbData);
    }
}