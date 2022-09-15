package com.tuling.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 一（客户）对多（信息）
 */
@Entity
@Table(name="tb_message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String info;


    public Message(String info) {
        this.info = info;
    }

    public Message(String info, Customer customer) {
        this.info = info;
        this.customer = customer;
    }

    // 一定要有、否则查询就会有问题
    public Message() {
    }

    // 多对一
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="customer_id")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
