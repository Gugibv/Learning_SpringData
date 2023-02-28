package com.grey.entity.converter;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Objects.hash;



public class ItemBean implements Serializable {

    /**
     * 值
     */
    private BigDecimal value;

    /**
     * 单位
     */
    private int unit;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ItemBean itemBean = (ItemBean) o;
        return unit == itemBean.unit &&
                Objects.equals(value, itemBean.value);
    }

    @Override
    public int hashCode() {
        return hash(value, unit);
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "unit=" + unit +
                ", value=" + value +
                '}';
    }

    public static ItemBean fromString(String s) {
        ItemBean itemBean = JSON.parseObject(s, ItemBean.class);
        return itemBean;
    }
}