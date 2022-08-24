package com.tuling.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * 一（客户）对多（信息）
 */
@Entity
@Table(name="tb_message")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="customer_id")
    private Customer customer;

}
