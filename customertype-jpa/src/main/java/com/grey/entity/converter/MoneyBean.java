package com.grey.entity.converter;

import com.sun.istack.NotNull;
import com.grey.config.converter.ItemBeanConverter;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class MoneyBean {

    @Id
    @Column
    private String uuid;

    @NotNull
    @Convert(
            converter = ItemBeanConverter.class,
            disableConversion = false
    )
    @Column()
    private ItemBean itemBean;
}
