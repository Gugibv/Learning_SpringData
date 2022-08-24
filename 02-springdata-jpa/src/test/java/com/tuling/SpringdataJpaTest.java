package com.tuling;

import com.tuling.config.SpringDataJPAConfig;
import com.tuling.pojo.Customer;
import com.tuling.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Optional;


// 基于junit4 spring单元测试
@ContextConfiguration("/spring.xml")
//@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringdataJpaTest {

    // jdk动态代理的实例
    @Autowired
    CustomerRepository repository;

    @Test
    public  void testR(){
        Optional<Customer> byId = repository.findById(102L);

        System.out.println(byId.orElse(null));
    }

    @Test
    public  void testC(){


        Customer customer = new Customer();
        customer.setCustName("李四");

        System.out.println(repository.save(customer));
    }

    @Test
    public  void testD(){


        Customer customer = new Customer();
        customer.setCustId(381L);
        customer.setCustName("李四");

        repository.delete(customer);
    }


    @Test
    public  void testFindAll(){

        Iterable<Customer> allById = repository.findAllById(Arrays.asList(82L, 162L, 182L));

        System.out.println(allById);
    }

}
