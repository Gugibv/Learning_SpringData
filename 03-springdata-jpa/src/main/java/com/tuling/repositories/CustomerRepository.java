package com.tuling.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tuling.pojo.Customer;


public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long>{

}
