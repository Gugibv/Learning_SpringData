package com.tuling.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tuling.pojo.Customer;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long>{

}
